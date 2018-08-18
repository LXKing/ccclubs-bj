package com.ccclubs.action.vc.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 车辆订单任务调度
 * @author yaz
 * 2018-8-6
 */
public class CarOrderScheduledPoolExecuter {
    
    private ScheduledExecutorService executorService;
    
    public void init() {
        executorService = Executors.newScheduledThreadPool(2);// TODO threadFactory
    }
    
    public ScheduledFuture<?> addTask(Runnable task, int miliseconds) {
        ScheduledFuture<?> future = executorService.schedule(task, miliseconds, TimeUnit.MILLISECONDS);
        return future;
    }

}
