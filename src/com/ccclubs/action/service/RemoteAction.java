package com.ccclubs.action.service;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsRemote;
import com.ccclubs.service.common.impl.RemoteService;
import com.lazy3q.web.helper.$;

/**
 * 远程调用框架
 * @author uiu
 */
public class RemoteAction {
	
	/**
	 * 远程调用操作
	 * @return
	 */
	public String invoke(){
		try {
			Long id = $.getLong("id");
			String sign = $.getString("sign");
			if($.equals($.md5(id+"-"+SYSTEM.PRIVATE_KEY), sign)){
				return $.SendAjax($.add("success", false).add("message", "远程调用时签名出错"), SYSTEM.UTF8);
			}
			CsRemote remote = CsRemote.get(id);
			RemoteService.dealRemoteCommendFromService(remote);
			return $.SendAjax($.add("success", true).add("message", "操作成功"), SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请稍候再试"), SYSTEM.UTF8);
		}
	}
	

}
