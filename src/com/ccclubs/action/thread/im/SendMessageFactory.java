package com.ccclubs.action.thread.im;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据 cswmContentType 消息类型，返回对应的发送实例
 * 
 * @author qsxiaogang
 * 
 */
public class SendMessageFactory {
	public static List<ISendMessage> factory(Short platform) {
		List<ISendMessage> list = new ArrayList<ISendMessage>();
		// 0:全部 1:微信 2:支付宝
		switch (platform) {
			case 0:
				list.add(new MicromessengerSendMessage());
				list.add(new AlipaySendMessage());
				break;
			case 1:
				list.add(new MicromessengerSendMessage());
				break;
			case 2:
				list.add(new AlipaySendMessage());
				break;
			default:
				break;
		}
		return list;
	}
}
