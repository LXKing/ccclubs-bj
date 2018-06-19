package com.ccclubs.action.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.service.common.ICMBPayService;
import com.lazy3q.web.helper.$;

public class CmbAction {
	
	ICMBPayService cmbPayService;
	
	final static Lock lock = new ReentrantLock();
	
	/**
	 * 异步通知页面
	 * @return
	 */
	public String notice(){
		Long recordid = null;
		try{
			lock.lock();
			recordid = cmbPayService.updateCMBPayResponse();
			$.SendHtml($.json($.add("respcod", "CMBMB99").add("respmsg", "处理成功，业务ID"+recordid)), "utf-8");
		}catch(Exception ex){
			ex.printStackTrace();
			$.SendHtml($.json($.add("respcod", "0").add("respmsg", "处理异常，业务ID"+recordid)), "utf-8");
		}finally{
			lock.unlock();
		}
		return null;
	}

	/**
	 * 同步通知页面
	 * @return
	 */
	public String callback(){
		try{
			lock.lock();
			Long recordid = cmbPayService.updateCMBPayResponse();
			CsAlipayRecord csAlipayRecord = CsAlipayRecord.get(recordid);
			if(!$.empty(csAlipayRecord.getCsarThirdBack())){
				return $.Redirect(csAlipayRecord.getCsarThirdBack());
			}else{
				$.SendHtml("SUCCESS", $.UTF8);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return $.SendHtml("ERROR", $.UTF8);
		}finally{
			lock.unlock();
		}
		return $.SendHtml("OK", $.UTF8);
	}

	public ICMBPayService getCmbPayService() {
		return cmbPayService;
	}

	public void setCmbPayService(ICMBPayService cmbPayService) {
		this.cmbPayService = cmbPayService;
	}

}
