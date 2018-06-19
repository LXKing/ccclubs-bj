package com.ccclubs.action.thread;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ccclubs.action.thread.sms.BaseSMS;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsEmail;
import com.ccclubs.model.CsSmsMt;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsSmsMtService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class EmailThread extends Thread {
		
	@Override
	public void run() {
		while(true){
			int count = 0;
			try{
				count = scanEmail();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","邮件处理线程出错",ex);
			}
			try {
				if(count>0)
					Thread.sleep(1000);
				else
					Thread.sleep(1000*60*10);//10分钟执行一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	/**
	 * 扫描邮件内容
	 * @return
	 */
	private int scanEmail() {
		//初始化邮件发送服务列表
		List<SrvProperty> propertys = SystemHelper.getPropertys("邮件发送服务器", "EMAIL_SEND_SERVERS");
		for(SrvProperty srvProperty:propertys){
			JavaMailSenderImpl javaMailSenderImpl = senders.get(srvProperty.getSpId());				
			if(!$.empty(srvProperty.getSpText())){
				if(javaMailSenderImpl==null)
					javaMailSenderImpl = new JavaMailSenderImpl();
				Map<String,String> map = $.eval(srvProperty.getSpText());
				javaMailSenderImpl.setHost(map.get("host"));
				javaMailSenderImpl.setPassword(map.get("password"));
				javaMailSenderImpl.setUsername(map.get("username"));
				javaMailSenderImpl.setPort(Integer.parseInt($.or(map.get("port"), "465")));
				Properties javaMailProperties = new Properties();
				javaMailProperties.put("mail.smtp.auth", "true");
				if(!$.equals(map.get("ssl"), "true")){
					javaMailProperties.put("mail.smtp.starttls.enable", "true");
					javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					javaMailProperties.put("mail.smtp.timeout", "25000");
				}
				javaMailSenderImpl.setJavaMailProperties(javaMailProperties );
				senders.put(srvProperty.getSpId(),javaMailSenderImpl);
			}
		}
		if(senders.isEmpty()){
			new ErrorException("UN_SET_EMAIL_SENDER","未配置邮件发送服务器","请在系统属性中[邮件发送服务器]节点下配置邮件发送服务列表");			
			return 0;
		}
		
		int count = 0;
		
		//获取所有未发送的邮件，以目标地址分组
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> addrs = dao.executeQuery("select cse_to from cs_email where cse_status=0 group by cse_to");
		for(Map map:addrs){
			String cse_to = (String) map.get("cse_to");
			List<CsEmail> list = CsEmail.list($.add(CsEmail.F.cseTo, cse_to).add(CsEmail.F.cseStatus, 0), 50);
			String content = "";
			JavaMailSenderImpl sender = getSender();
			for(CsEmail csEmail:list){
				content+=csEmail.getCseContent();
				content+="<br/>";
				csEmail.setCseFrom(sender.getUsername());
				csEmail.cseSendTime(new Date());
				csEmail.cseStatus((short)1);
				csEmail.update();
			}
			try {
				sendHTMLMail(sender,cse_to,"车纷享系统事件提醒",content);
			} catch (Exception e) {
				e.printStackTrace();
				for(CsEmail csEmail:list){
					csEmail.cseStatus((short)2);
					csEmail.update();
				}
			}
			count+=list.size();
		}
		return count;
	}


	
	/**
     * 发送html邮件
     * @param sender 邮件发送人实体
     * @param toEmail 接收人的邮件地址
     * @param fromEmail	发送人的邮件地址
     * @param titleEmail 发送邮件的标题
     * @param contentEmail	发送邮件的内容
     * @throws Exception
     */
    public static void sendHTMLMail(JavaMailSenderImpl sender,String toEmail,String titleEmail,String contentEmail) throws Exception {
    	  MimeMessage msg = sender.createMimeMessage();
    	  MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
    	  helper.setTo(toEmail);
    	  String fromEmail = sender.getUsername();
    	  String nick = javax.mail.internet.MimeUtility.encodeText("车纷享"); 
    	  helper.setFrom(new InternetAddress(fromEmail,nick));
    	  helper.setSubject(titleEmail);
    	  helper.setSentDate(new Date());
    	  helper.setText(contentEmail,true);
	      sender.send(msg);
	      System.out.println("成功发送邮件 - "+fromEmail+" >> "+toEmail);
    }

	/**
	 * 获取邮件发送器
	 * @return
	 */
	private JavaMailSenderImpl getSender() {
		Object[] keys = senders.keySet().toArray();
		return senders.get(keys[$.rand(keys.length)]);
	}
	Map<Long,JavaMailSenderImpl> senders = new HashMap();

}
