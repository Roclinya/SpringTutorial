# Member API + Redisson Lock 說明

## 你現在可以用的 API

- 路徑: `POST /memberApi/create`
- 功能: 新增一筆 `Member`
- Request Body:

```json
{
  "usrName": "larry",
  "eMail": "larry@example.com",
  "usrPwd": "123456"
}
```

- 成功回應範例:

```json
{
  "id": 1,
  "usrName": "larry",
  "eMail": "larry@example.com"
}
```

## Redisson Lock 在這支 API 的使用方式

在 `MemberServiceImpl#createMember` 中，會先依照 `usrName` 產生鎖 key:

- lock key: `member:create:{usrName}`

流程如下:

1. 先嘗試取得分散式鎖 (`tryLock`)
2. 拿到鎖後，檢查 `usrName` 是否已存在
3. 不存在才新增 `Member`
4. 最後釋放鎖 (`unlock`)

## 使用 Redisson Lock 的好處

- 避免重複建立同一個使用者
  - 多台應用程式同時收到同一個 `usrName` 的請求時，只有一個節點會先取得鎖。
- 跨節點一致性
  - 傳統 `synchronized` 只在單機有效，Redisson lock 可在多實例環境共用同一把鎖。
- 降低資料競爭問題
  - 對「先檢查再寫入」這類流程，能大幅降低 race condition。
- 可設定等待時間與租約時間
  - 透過 `tryLock(waitTime, leaseTime, unit)` 可控制等待策略，避免無限阻塞。

## 設定方式

`application.yml` 已新增:

```yml
redisson:
  address: redis://127.0.0.1:6379
  password:
  database: 0
```

> 若你的 Redis 不在本機，請改成實際 host/port。

## 注意事項 (建議)

- 建議在資料庫也加上 `usr_name` 唯一索引
  - Lock 主要防止併發衝突，唯一索引才是最後一道資料完整性防線。
- `usrPwd` 建議改成雜湊後存入 (例如 BCrypt)，不要明碼儲存。
- 正式環境建議使用 Redis 高可用架構 (Sentinel/Cluster) 以提高可靠度。

## 排程鎖測試 (@Scheduled + @SchedulerLock)

已新增排程 `createMemberWithScheduler()`：

- 類別：`src/main/java/com/tutorial/SpringTutorial/Service/MemberCreateScheduler.java`
- 註解：`@Scheduled` + `@SchedulerLock`
- 呼叫：同一個 `memberService.createMember(...)`

另外也新增手動觸發端點（不用等 cron）：

- 路徑：`POST /memberApi/scheduler/trigger`
- 行為：立即走同一套 `createMember` 流程，便於你手動測鎖競爭

為了模擬「排程已持有 lock，很久才完成」，已在 `createMember` 加入可開關的延遲設定（僅測試用）：

```yml
member:
  scheduler:
    enabled: true
    cron: "0 */3 * * * *"
    test-usr-name: scheduler-lock-user
    test-email: scheduler-lock-user@example.com
    test-password: "123456"
  create-lock-test:
    enabled: true
    target-usr-name: scheduler-lock-user
    sleep-millis: 120000
```

測試步驟：

1. 啟用上面設定後啟動服務。
2. 等排程觸發（每 3 分鐘一次，或自行把 cron 調更頻繁）。
3. 在排程進入長時間 sleep 期間，手動打 API（usrName 要同一個）：

```bash
curl -X POST "http://localhost:8080/memberApi/create" \
  -H "Content-Type: application/json" \
  -d '{"usrName":"scheduler-lock-user","eMail":"manual@example.com","usrPwd":"123456"}'
```

預期：手動 API 會因拿不到 Redisson lock 而失敗 (`Unable to create member now, please retry.`)。

