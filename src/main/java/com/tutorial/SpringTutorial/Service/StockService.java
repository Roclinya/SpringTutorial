package com.tutorial.SpringTutorial.Service;

import com.tutorial.SpringTutorial.vo.StockDto;

public interface StockService {

    StockDto checkStock(int productId, int amount) throws Exception;

    int addStock(int i, int i1);
    int performeRequiredNew(int productId, int amount);
    int performeRequiredNewWithOuterException(int productId, int amount);
    Long performeDeadLock(int productId, int amount) throws InterruptedException;


}
