package com.tutorial.SpringTutorial.Service.Impl;

import com.tutorial.SpringTutorial.Service.StockService;
import com.tutorial.SpringTutorial.entity.Member;
import com.tutorial.SpringTutorial.entity.Stock;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import com.tutorial.SpringTutorial.repository.StockRepository;
import com.tutorial.SpringTutorial.vo.StockDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    private final MemberRepository memberRepository;
    public StockServiceImpl(StockRepository stockRepository, MemberRepository memberRepository) {
        this.stockRepository = stockRepository;
        this.memberRepository = memberRepository;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public int performeRequiredNewWithOuterException(int productId, int amount) {
        Stock stock = new Stock();
        stock.setAmount(2000);
        Stock stockRtn = stockRepository.save(stock);
        insertNewData();
        System.out.println(1/0);
        return stock.getAmount();
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int performeRequiredNew(int productId, int amount) {
        System.out.println("-- performeRequiredNew --");
        Stock stock = getStock(productId, amount);
        insertNewData();
        try {
            System.out.println(1/0);
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println("調用者捕獲異常，調用者不受影響，不回滾。");
        }
        return stock.getAmount();
    }


    //TODO: 第一層
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addStock(int productId, int amount) {
        Stock stock = getStock(productId, amount);
        stockRepository.save(stock);// 無論是否有寫save(stock),前面因為有set數值,一樣會被執行save
        try {
            addExecute(stock.getAmount());
        } catch (Exception e) {
            System.out.println("addStock 錯誤 但不中斷程序");
            //手動rollback
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            throw new RuntimeException(e);
//            throw e; // 保持异常抛出以回滚事务
        }
        if(false){
            throw new RuntimeException();
        }else{
            return stock.getAmount();
        }
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    private Stock getStock(int productId, int amount) {
        Optional<Stock> optional = stockRepository.findByProductId(productId);
        if(!optional.isPresent()) {
            throw new RuntimeException("product id not found, id: " + productId);
        }
        Stock stock = optional.get();
        int existAmount = stock.getAmount();
        stock.setAmount(existAmount + amount);
        System.out.println("第一層 getStock , Main Thread: " + Thread.currentThread().getName());
        int newAmount = existAmount + amount;
        System.out.println(" Amount: "+newAmount);
        return stock;
    }


    //TODO: 第二層
//    @Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
    @Transactional(propagation = Propagation.REQUIRED)
    private void addExecute(int amount) {
        System.out.println("第二層 addExecute ,Thread: " + Thread.currentThread().getName());
        System.out.println(" Amount: "+amount);
        Stock stock = new Stock();
        stock.setAmount(amount);
        Stock stockRtn = stockRepository.save(stock);
        throw new RuntimeException();
//        try {
//            executeSave(stock);
//        } catch (Exception e) {
////            e.printStackTrace();
//            throw new RuntimeException(e);
////            System.out.println("stockSave2 錯誤 但不中斷程序");
//        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public StockDto checkStock(int productId, int amount) throws Exception{
        Optional<Stock> optional = stockRepository.findByProductId(productId);
        if(!optional.isPresent()) {
            throw new RuntimeException("product id not found, id: " + productId);
        }
        Stock stock = optional.get();

        int existAmount = stock.getAmount();
        stock.setAmount(existAmount - amount);

        System.out.println("Start CreateTask , Main Thread: " + Thread.currentThread().getName());
        Stock stockRtn = stockSave(stock);
        try {
            stockSave2(stockRtn);
        } catch (RuntimeException e) {
            System.out.println("stockSave2 錯誤 但不中斷程序");
//            throw new RuntimeException(e);
//            throw e; // 保持异常抛出以回滚事务
        }

        return convertToDto(stockRtn);
    }

    @Transactional
    @Override
    public Long performeDeadLock(int productId, int amount) throws InterruptedException {
        Stock stock = getStock(productId, amount);
        // 加入延遲以模擬交易未完成的情況
        try {
            Thread.sleep(100); // 延遲100毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Stock persistStock = stockRepository.save(stock);// 無論是否有寫save(stock),前面因為有set數值,一樣會被執行save

//        return saveMemberFromStock(persistStock);
        // 改為 REQUIRED 以增加死鎖的可能性
        return Long.valueOf(saveStockAgain(persistStock)) ;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private Long saveMemberFromStock(Stock stock) {
        System.out.println("第二層 performeDeadLock ,  Thread: " + Thread.currentThread().getName());
        Member member = new Member();
        member.seteMail("member1@gmail.com");
        member.setUsrName("performeDeadLock User");
        member.setUsrPwd("123");
        memberRepository.save(member);
        return member.getId();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    int saveStockAgain(Stock stock) throws InterruptedException {
        Thread.sleep(2000);
        stock.setAmount(stock.getAmount()-1);
        stockRepository.save(stock);
        return stock.getAmount();
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    public Stock stockSave(Stock stock){
        System.out.println("stockSave ,Thread: " + Thread.currentThread().getName());
        return  stockRepository.save(stock);
    }
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Transactional
    public Stock stockSave2(Stock stockRtn){
        System.out.println("stockSave2 ,Thread: " + Thread.currentThread().getName());
        Stock stock = new Stock();
        stock.setAmount(stockRtn.getAmount());
        try {
            executeSave(stock);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
//            System.out.println("stockSave2 錯誤 但不中斷程序");
        }
        return stock;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Stock insertNewData(){
        Stock stock = new Stock();
        stock.setAmount(1000);
        Stock stockRtn = stockRepository.save(stock);
        return stockRtn;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Stock executeSave(Stock stock){
        Stock stockRtn = stockRepository.save(stock);
        System.out.println(1/0);

        return stockRtn;
    }
    private StockDto convertToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setProductId(stock.getProductId());
        stockDto.setAmount(stock.getAmount());
        return stockDto;
    }

}
