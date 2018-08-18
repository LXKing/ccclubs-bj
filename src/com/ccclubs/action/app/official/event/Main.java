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
		/**
		 * FIXME 非常非常非常差的实现方式，了解业务的请重写
		 */
//		CarStatusOnsProducer.carStatusQueue = carStatusQueue;
		
		CarStatusConsumer consumer = new CarStatusConsumer(carStatusQueue);
		consumer.start();
	}
}
