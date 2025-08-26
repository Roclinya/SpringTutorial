package com.tutorial.SpringTutorial.Service.Impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ReportServiceTask {
    private ConcurrentHashMap<String, String> __cTaskList = new ConcurrentHashMap<String, String>();
    private ThreadPoolExecutor __cThreadPools = new ThreadPoolExecutor(0, 128, 300, TimeUnit.SECONDS, new SynchronousQueue<>());
    public int createTask(String planSN){
        //第一次執行一定進入else,因為還沒給他key planSN
        if(__cTaskList.containsKey(planSN)){
            String sFileId = __cTaskList.get(planSN);
            System.out.println("Task is Running , Main Thread: " + Thread.currentThread().getName());
            if (StringUtils.isBlank(sFileId)) {
                return 2;  //正在產生報告書
            } else {
                return 3;  //報告書已經產生完成
            }

        }else {
            //第一次執行
            __cTaskList.put(planSN, "");
            System.out.println("Start CreateTask , Main Thread: " + Thread.currentThread().getName());
            __cThreadPools.execute(()->{
                try {
                    // 打印當前執行緒名稱
                    System.out.println("createTask Thread: " + Thread.currentThread().getName() +" Started");
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                __cTaskList.put(planSN,"execute");
                System.out.println("Completed CreateTask Thread: " + Thread.currentThread().getName());
            });
        }

        return 0;
    }

    public String getCompletedFileId(String planSN) {
        if (__cTaskList.containsKey(planSN)) {
            String sFileId = __cTaskList.get(planSN);
            __cTaskList.remove(planSN);
            return sFileId;
        }
        return null;
    }
}

