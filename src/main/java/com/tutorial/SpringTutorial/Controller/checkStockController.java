package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Service.Impl.ReportServiceTask;
import com.tutorial.SpringTutorial.Service.StockService;
import com.tutorial.SpringTutorial.vo.ReportServiceResponse;
import com.tutorial.SpringTutorial.vo.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequestMapping("/api")
@RestController
public class checkStockController {
    @Autowired
    ReportServiceTask reportServiceTask;
    @PostMapping("/performeRequiredNew")
    public int performeRequiredNew(){
        return stockService.performeRequiredNew(1,1);
    }
    @PostMapping("/performeRequiredNewWithOuterException")
    public int performeRequiredNewWithOuterException(){
        return stockService.performeRequiredNewWithOuterException(1,1);
    }
    @PostMapping("/performeDeadLock")
    public ResponseEntity<String> performeDeadLock(){
//        Long memberId = stockService.performeDeadLock(1, 1);
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                Long memberId = null;
                try {
                    memberId = stockService.performeDeadLock(1, 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("member ID: " +memberId);
            });
        }
//        return ResponseEntity.ok("MemberId: "+memberId);
        return ResponseEntity.ok("performeDeadLock multiThread success");
    }

    @PostMapping("/reportServiceTask")
    public ReportServiceResponse ReportServiceTask(@RequestParam(value = "planSN") String planSN) {
        Integer iStatus = 0;
        String sFileId = null;
        iStatus = reportServiceTask.createTask(planSN);
        if (iStatus == 3) {
            sFileId = reportServiceTask.getCompletedFileId(planSN);
        }

        ReportServiceResponse resp = new ReportServiceResponse();
        resp.setStatus(iStatus);
        resp.setFileId(sFileId);
        return resp;
    }

    @Autowired
    StockService stockService;

    // https://hackmd.io/@andydiary-java/rygsBdZhi
    @PostMapping("/checkStock")
    public ResponseEntity<StockDto> checkStock() throws Exception {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
//        for (int i = 0; i < threadCount; i++) {
//            executorService.execute(() -> {
//                StockDto stockDto = stockService.checkStock(1,1);
//                System.out.println("Product ID: " + stockDto.getProductId() + ", Available Amount: " + stockDto.getAmount());
//            });
//        }
        StockDto stockDto = stockService.checkStock(1,1);
        System.out.println("Product ID: " + stockDto.getProductId() + ", Available Amount: " + stockDto.getAmount());

        executorService.shutdown();
        return ResponseEntity.ok(stockDto);
    }

    @PostMapping("/checkStock2")
    public ResponseEntity<String> addStock() throws Exception {
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
//        for (int i = 0; i < threadCount; i++) {
//            executorService.execute(() -> {
//                StockDto stockDto = stockService.checkStock(1,1);
//                System.out.println("Product ID: " + stockDto.getProductId() + ", Available Amount: " + stockDto.getAmount());
//            });
//        }
        int amount = stockService.addStock(1,1);
        System.out.println("checkStock2 => amount " + amount );

        executorService.shutdown();
        return ResponseEntity.ok("Amount: "+amount);
    }
}


