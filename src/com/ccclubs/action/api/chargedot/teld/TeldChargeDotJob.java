package com.ccclubs.action.api.chargedot.teld;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.quartz.JobExecutionException;

import com.ccclubs.action.api.chargedot.teld.SyncOrder;
import com.ccclubs.action.api.chargedot.teld.SyncPile;
import com.ccclubs.action.api.chargedot.teld.SyncPileStateThread;
import com.ccclubs.factory.QuartzManager;

/**
 * 特来电电桩任务
 * 
 * @author Joel
 * 
 */
public class TeldChargeDotJob extends Thread {

	public static AtomicBoolean init = new AtomicBoolean(false); 	// 是否已启动实例

	@Override
	public void run() {
		if (init.get()) {
			return;
		}

		super.run();

		try {
			// QuartzManager.addJob("SyncPileTask", new SyncPile(), "1/20 * * * * ? "); //每1分钟执行一次 //0/11 * * * * ? 1 23 22 * * ?
			// QuartzManager.addJob("SyncOrderTask", new SyncOrder(), "50 40 17 * * ? "); //每天1点10分开始执行一次 //0 10 1 * * ?

			long delay = 10000; 	// 从接收到指令延迟十秒后开始执行
			
			Timer t = new Timer();
			final SyncPile syncPile = new SyncPile();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						syncPile.execute(null);
					} catch (JobExecutionException e) {
						e.printStackTrace();
					}
				}
			}, delay, 60 * 1000);

			//订单数据，每天同步一次
			Timer odrTimer = new Timer();
			final SyncOrder syncOrder = new SyncOrder();
			odrTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						syncOrder.execute(null);
					} catch (JobExecutionException e) {
						e.printStackTrace();
					}
				}
			}, delay, 24 * 60 * 60 * 1000);

			SyncPileStateThread stateThread = new SyncPileStateThread();
			stateThread.start();
			
			System.out.println("=========================== job initilized ===========================");
		} catch (Exception e) {
			e.printStackTrace();
		}

		init.set(true);
	}
}
