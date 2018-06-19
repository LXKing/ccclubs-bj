package com.ccclubs.action.app.official.event;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.ccclubs.util.mq.ICarStatus;

public class Main {
	
	public synchronized void init(){
		
		/**
		 * 车辆状态消息队列
		 */
	    BlockingQueue<ICarStatus> carStatusQueue = new LinkedBlockingQueue<ICarStatus>();  
	    
		CarStatusProducer producer = new CarStatusProducer(carStatusQueue);
		producer.init();
		
		CarStatusConsumer consumer = new CarStatusConsumer(carStatusQueue);
		consumer.start();
	}
}
