# MySQL Lock Error 對應 Spring Exception 速查表

這份文件整理了在 MySQL (InnoDB) 下，常見鎖衝突錯誤訊息與 Spring DataAccessException 的常見對應，
方便你快速判讀為什麼 API 在高併發更新時會失敗。

> 注意：實際例外型別會受 JDBC Driver、Hibernate 版本、Spring 版本、SQL 執行型態影響。
> 同一種 MySQL 錯誤在不同版本環境，可能被轉譯成不同 Spring 例外。

## 0) 兩種情境差異（先看這裡）

| 項目 | Lock wait timeout exceeded (`1205`) | Deadlock found when trying to get lock (`1213`) |
|---|---|---|
| 核心原因 | 你在等別人的鎖，但等太久（超過 `innodb_lock_wait_timeout`） | 兩個以上交易互相卡住形成循環等待 |
| MySQL 行為 | 主要是「等待方」報錯超時 | InnoDB 會主動挑一個交易當受害者回滾 |
| 是否一定有循環等待 | 否，單純長時間占鎖就會發生 | 是，必須存在 deadlock cycle |
| 常見觸發場景 | 長交易、交易中有慢 I/O、`SELECT ... FOR UPDATE` 後停留太久 | 交易 A 先鎖 row1 再鎖 row2；交易 B 先鎖 row2 再鎖 row1 |
| Spring 常見例外 | `PessimisticLockingFailureException` / `CannotAcquireLockException` | `DeadlockLoserDataAccessException` |
| 處理策略 | 縮短交易時間、降低鎖持有時間、必要時 retry | 固定鎖順序、縮小交易範圍、加上 retry（通常更必要） |

### 快速判讀口訣

- 看到 `1205`：代表「等太久」，先查誰持鎖太久。
- 看到 `1213`：代表「互卡」，先查交易鎖順序是否不一致。

## 1) 常見對應表

| MySQL/DB Root Cause (關鍵字) | 常見 SQLSTATE / Error Code | Spring 常見例外 | 典型情境 | 備註 |
|---|---|---|---|---|
| `Lock wait timeout exceeded; try restarting transaction` | SQLSTATE `HY000`, Error `1205` | `PessimisticLockingFailureException` 或 `CannotAcquireLockException` | 交易 A 持有 row lock 太久，交易 B 等待超時 | 最常見於 `SELECT ... FOR UPDATE` 或更新衝突 |
| `Deadlock found when trying to get lock; try restarting transaction` | SQLSTATE `40001`, Error `1213` | `DeadlockLoserDataAccessException`（有時可能包裝成 lock 相關例外） | 兩個交易循環等待彼此鎖 | 通常建議重試機制 |
| `Lock wait timeout`（JPA pessimistic lock 路徑） | 依供應商回傳 | `PessimisticLockingFailureException` | 使用 `@Lock(PESSIMISTIC_WRITE)` 查詢同一筆資料 | 你的 `findByIdForUpdate` 屬於這一路徑 |
| `Duplicate entry ... for key ...` | SQLSTATE `23000`, Error `1062` | `DuplicateKeyException` / `DataIntegrityViolationException` | 併發 insert 或更新 unique key 衝突 | 這不是 lock timeout，而是約束衝突 |
| `Cannot add or update a child row: a foreign key constraint fails` | SQLSTATE `23000`, Error `1452` | `DataIntegrityViolationException` | FK 不存在或順序錯誤 | 非鎖衝突，但常在併發場景一起出現 |

## 2) 為什麼拿掉 `@Lock(PESSIMISTIC_WRITE)` 常就不會看到 `PessimisticLockingFailureException`

- 有 `@Lock(PESSIMISTIC_WRITE)`：
  - Hibernate/JPA 會走悲觀鎖流程（常見為 `SELECT ... FOR UPDATE`），
  - 第二個交易競爭同一列時，若超時就容易映射到 `PessimisticLockingFailureException`。
- 拿掉 `@Lock(PESSIMISTIC_WRITE)`：
  - 不再主動在查詢階段拿悲觀鎖，
  - 可能變成「最後寫入覆蓋」或在其他時點才出現不同例外，
  - 因此 `PessimisticLockingFailureException` 可能不再出現。

## 3) 你的專案對應位置

- Service：`src/main/java/com/tutorial/SpringTutorial/Service/Impl/MemberServiceImpl.java`
  - `updateMember(...)` 會先呼叫 `findByIdForUpdate(...)`，並可用 `holdLockMillis` 故意延長交易。
- Repository：`src/main/java/com/tutorial/SpringTutorial/repository/MemberRepository.java`
  - `findByIdForUpdate(...)` 使用 `@Lock(LockModeType.PESSIMISTIC_WRITE)`。

## 4) 如何快速判讀 production log

建議在 exception handler 或日誌中印出以下欄位：

1. `ex.getClass().getName()`（Spring 轉譯後例外）
2. `rootCause.getClass().getName()`
3. `rootCause.getMessage()`（看是否含 `1205` / `1213`）
4. SQLSTATE（若可從 SQLException 取出）

判讀優先順序：

- 先看 root cause 是否包含：
  - `1205` -> 鎖等待超時
  - `1213` -> deadlock
- 再看 Spring 轉譯層例外型別（方便應用層分類處理）

## 5) 實務建議

- 對 `1205/1213` 類錯誤加上有限次數 retry（含隨機退避 jitter）。
- 交易內避免長時間 I/O（外部 API、大量運算、sleep）。
- 固定更新順序，降低 deadlock 機率。
- 若可接受衝突後重試，評估改 `@Version` optimistic lock。

## 6) 你可用來重現的測試流程

1. 先用排程或手動 trigger 開第一個交易，設定較長 `holdLockMillis`。
2. 在交易未提交前，用 Postman 再打同一筆 `update`。
3. 觀察第二個請求是否出現 lock timeout / deadlock 對應例外。

---

如果你要，我可以再幫你補一版「Exception -> 建議 HTTP 狀態碼 + 前端訊息」對照表，讓 API 回應策略更一致。

