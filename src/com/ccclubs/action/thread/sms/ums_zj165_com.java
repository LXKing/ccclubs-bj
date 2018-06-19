package com.ccclubs.action.thread.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ParamHelper;

public class ums_zj165_com extends BaseSMS {

	@Override
	public String query() {
		return "当前通道ums_zj165_com短信余额查询未实现";
	}

	@Override
	public String send(String mobile, String content) {
		
		Map params = new HashMap();
		params.put("SpCode", "005630");//企业编号
		params.put("LoginName", "hz_clz");//用户名称
		params.put("Password", "clz1227");//用户密码
		params.put("MessageContent", content);//短信内容, 最大700个字符
		params.put("UserNumber", mobile);//手机号码(多个号码用”,”分隔)，最多1000个号码
		params.put("SerialNumber", $.date(new Date(), "yyyyMMddHHmmssS"+$.rand(999)));//流水号，20位数字，唯一
		params.put("ScheduleTime", "");//预约发送时间，格式:yyyyMMddhhmmss,如‘20090901010101’，立即发送请填空
		params.put("ExtendAccessNum", "");//接入号扩展号
		params.put("f", "1");//提交时检测方式 1 --- 提交号码中有效的号码仍正常发出短信，无效的号码在返回参数faillist中列出 不为1 或该参数不存在 --- 提交号码中只要有无效的号码，那么所有的号码都不发出短信，所有的号码在返回参数faillist中列出
				
		String queryString = "?";
		Set<String> keySet = params.keySet();
		for(String key:keySet){
			queryString+=key+"="+params.get(key)+"&";
		}
		try {
			return $.http("http://ums.zj165.com:8888/sms/Api/Send.do"+queryString, "GBK");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String...strings){
		//System.out.println(new ums_zj165_com().send("13575717846","您的订单时间07月08日06点34分到08月06日05时06分，取车点杭州市体育场路黄龙体育中心，车牌号zjd34rf，授权码345667"));
		System.out.println(new ums_zj165_com().send("13575717846","您的校验码为：123456，您正在注册车纷享，如有疑问请致电0571-88190338。"));
		//System.out.println(new ums_zj165_com().send("13634198360","您的校验码为：123456，您正在注册车纷享，如有疑问请致电0571-88190338。"));
	}

}
