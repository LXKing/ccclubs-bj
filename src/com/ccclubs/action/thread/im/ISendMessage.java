package com.ccclubs.action.thread.im;

import com.ccclubs.model.CsWxMt;

public interface ISendMessage {
	/**
	 * 返回当前信息渠道的掩码
	 * <li>微信：1</li>
	 * <li>支付宝：2</li>
	 * <li>XX：4</li>
	 * 
	 * @return
	 */
	int getMask();

	/**
	 * 发送文本信息
	 * 
	 * @param csWxMt
	 * @return
	 */
	int sendTextMessage(CsWxMt csWxMt);

	/**
	 * 发送图片信息
	 * 
	 * @param csWxMt
	 * @return
	 */
	int sendPicMessage(CsWxMt csWxMt);

	/**
	 * 发送图文信息
	 * 
	 * @param csWxMt
	 * @return
	 */
	int sendNewsMessage(CsWxMt csWxMt);

	/**
	 * 发送模板信息
	 * 
	 * @param csWxMt
	 * @return
	 */
	int sendTemplateMessage(CsWxMt csWxMt);
}
