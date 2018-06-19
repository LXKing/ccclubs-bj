package com.ccclubs.action.m.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.action.weixin.WeixinTokenHelper;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.lazy3q.web.helper.$;

public class IndexAction {
	/**
	 * 后台管理首页
	 * 
	 * @return
	 */
	public String execute() {
		
		if ($.empty(WeixinHelper.getOpenId())) {
			// 微信授权登录
			$.Redirect($.GetRequest("basePath") + "weixin/oauth2.html?redirect=weixin/admin/index.html");
			return null;
		}
		// 查询扩展用户信息
		ISrvUserExpService srvUserExpService = $.getBean("srvUserExpService");
		SrvUserExp srvUserExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueWxOpenid, WeixinHelper.getOpenId()));
		if (srvUserExp == null) {
			$.Redirect($.GetRequest("basePath") + "weixin/index.html");
			return null;
		}
		$.SetRequest("srvUserExp", srvUserExp);
		
		/**
		 * 微信jsapi相关
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
		$.SetRequest("map", ret);
		
		return "index";
	}
}
