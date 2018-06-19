package com.ccclubs.action.api.chargedot.CMDT;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

/**
 * 移动电桩数据任务
 * 
 * @author Joel
 * 
 */
public class CMChargingPileDataJob extends Thread {

	public static AtomicBoolean init = new AtomicBoolean(false); 	// 是否已启动实例

	@Override
	public void run() {
		if (init.get()) {
			return;
		}

		super.run();

		try {

			long delay = 10000; 	// 从接收到指令延迟十秒后开始执行
			
			Timer t = new Timer();
			final CMDTChargingPileService pileService = new CMDTChargingPileService();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						pileService.getAndWriteChargingBill();
					} catch (Exception e) {
						Logger.getRootLogger().error("CMChargingPileDataJob err : ", e);
					}
				}
			}, delay, 60 * 1000);
			
			System.out.println("=========================== pileService initialized ===========================");
		} catch (Exception e) {
			e.printStackTrace();
		}

		init.set(true);
	}
}
