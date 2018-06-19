package com.ccclubs.action.thread;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.model.CsSmsMt;
import com.ccclubs.service.admin.ICsSmsMtService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.$;

public class UserPackThread extends Thread {

	
	ICsUserPackService csUserPackService;	
	
	@Override
	public void run() {
		while(true){
			int count = 0;
			try{
				count = scanUserPack();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程自动续订会员套餐出错",ex);
			}
			try {
				if(count==0)
					Thread.sleep(SYSTEM.HOUR);//一个小时处理一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 扫描需要处理的短信
	 */
	private int scanUserPack() {		
		
		return 0;
	}
	
	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}

	
	public static void main(String...strings){
		$.trace($.or(null,"#3#,#4#").replace("#", ""));
	}
	
	
}
