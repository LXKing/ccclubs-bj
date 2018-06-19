package com.ccclubs.action.thread;

import java.util.Date;
import java.util.List;

import com.ccclubs.action.thread.im.ISendMessage;
import com.ccclubs.action.thread.im.SendMessageFactory;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.model.CsWxMt;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsTroubleService;
import com.ccclubs.service.admin.ICsViolatService;
import com.ccclubs.service.admin.ICsWxMtService;
import com.lazy3q.web.helper.$;

public class MessageThread extends Thread {

	ICsTroubleService	csTroubleService;
	ICsOrderService		csOrderService;
	ICsViolatService	csViolatService;
	ICsWxMtService		csWxMtService;

	@Override
	public void run() {
		while (true) {
			try {
				scanIMMessage();
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR", "线程处理IM信息出错", ex);
			}
		}
	}

	/**
	 * 扫描需要处理的IM信息
	 */
	private void scanIMMessage() { 
		 //$.trace("##################################### scanIMMessage ####################################");
		List<CsWxMt> list = CsWxMt.list($.add("definex", CsWxMt.C.cswmMask + " = 0 AND " + CsWxMt.C.cswmStatus + " = 0"),50);
		for(CsWxMt csWxMt:list){
			// 获得发送渠道列表
			List<ISendMessage> sendList = SendMessageFactory.factory(csWxMt.getCswmPlatform());
			if (sendList.size() > 0) {
				for (ISendMessage sendMessage : sendList) {
					try {
						int result = 0;
						// 根据信息的“类容类型”，发送信息
						switch (csWxMt.getCswmContentType()) {
							case 0:
								result = sendMessage.sendTextMessage(csWxMt);
								break;
							case 1:
								result = sendMessage.sendPicMessage(csWxMt);
								break;
							case 2:
								result = sendMessage.sendNewsMessage(csWxMt);
								break;
							case 3:
								result = sendMessage.sendTemplateMessage(csWxMt);
								break;
						}
						// if (result > 0) {
						// 修改当前信息对应渠道已发送
						CsWxMt csWxMtCurent = csWxMtService.getCsWxMt($.add(CsWxMt.F.cswmId, csWxMt.getCswmId()));
						// 做一次或运算
						csWxMtCurent.setCswmMask(csWxMtCurent.getCswmMask() | sendMessage.getMask());
						if (result > 0)
							csWxMtCurent.setCswmSuccess(csWxMtCurent.getCswmSuccess() | sendMessage.getMask());
						csWxMtCurent.setCswmSendTime(new Date());
						csWxMtCurent.update();
						// }else {

						// }
					} catch (Exception e) {
						e.printStackTrace();
						csWxMt.setCswmMask(-1);
						csWxMt.update();
					}
				}
			} else {
				csWxMt.setCswmMask(-1);
				csWxMt.update();
			}
		}
	}

	public ICsTroubleService getCsTroubleService() {
		return csTroubleService;
	}

	public void setCsTroubleService(ICsTroubleService csTroubleService) {
		this.csTroubleService = csTroubleService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public ICsViolatService getCsViolatService() {
		return csViolatService;
	}

	public void setCsViolatService(ICsViolatService csViolatService) {
		this.csViolatService = csViolatService;
	}

	public ICsWxMtService getCsWxMtService() {
		return csWxMtService;
	}

	public void setCsWxMtService(ICsWxMtService csWxMtService) {
		this.csWxMtService = csWxMtService;
	}

}
