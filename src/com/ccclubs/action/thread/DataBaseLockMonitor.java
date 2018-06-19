package com.ccclubs.action.thread;

import java.util.Date;

import com.ccclubs.helper.SystemHelper;

public class DataBaseLockMonitor extends Thread{
	
	
	public void run(){
		while(true){
			try{
				Thread.sleep(10000l);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			try{
				if(new Date().getHours()>6)
					SystemHelper.killLocks(60,true);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
