/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.ccclubs.action.weixin.alipay.util;

import java.util.Calendar;

import com.ccclubs.action.weixin.alipay.constants.AlipayServiceEnvConstants;
import com.lazy3q.web.helper.$;

/**
 * 消息构造工具
 * 
 * @author baoxing.gbx
 * @version $Id: AlipayMsgBuildUtil.java, v 0.1 Jul 24, 2014 5:47:19 PM baoxing.gbx Exp $
 */
public class AlipayMsgBuildUtil {

    /**
     * 构造单发图文消息
     * 
     * @param fromUserId
     * @return
     */
    public static String buildSingleImgTextMsg(String fromUserId) {

        StringBuilder sb = new StringBuilder();

        //构建json格式单发图文消息
        sb.append("{'articles':[{'actionName':'立即查看','desc':'租车可以不用押金？是真的，快去申请免押金服务吧','imageUrl':'http://img.ccclubs.com/weixin/alipay_chuxing.png','title':'免押金租车','url':'" + $.GetRequest("basePath") +
        		"m/oauth2_chuxing.html'}],'msgType':'image-text','toUserId':'" + fromUserId + "'}");

        return sb.toString();
    }

    /**
     * 构造群发图文消息
     * 
     * @return
     */
    public static String buildGroupImgTextMsg() {

        StringBuilder sb = new StringBuilder();

        //构建json格式群发图文消息: 所有内容开发者请根据自有业务自行设置响应值，这里只是个样例
        sb.append("{'articles':[{'actionName':'立即查看','desc':'这是图文内容','imageUrl':'http://pic.alipayobjects.com/e/201311/1PaQ27Go6H_src.jpg','title':'这是标题','url':'https://www.alipay.com/'}],'msgType':'image-text'}");

        return sb.toString();
    }

    /**
     * 构造单发纯文本消息
     * 
     * @param fromUserId
     * @return
     */
    public static String buildSingleTextMsg(String fromUserId) {

        StringBuilder sb = new StringBuilder();

        //构建json格式单发纯文本消息体： 所有内容开发者请根据自有业务自行设置响应值，这里只是个样例
        sb.append("{'msgType':'text','text':{'content':'这是纯文本消息'}, 'toUserId':'" + fromUserId
                  + "'}");

        return sb.toString();
    }

    /**
     * 构造群发纯文本消息
     * 
     * @return
     */
    public static String buildGroupTextMsg() {

        StringBuilder sb = new StringBuilder();

        //构建json格式群发纯文本消息体： 所有内容开发者请根据自有业务自行设置响应值，这里只是个样例
        sb.append("{'msgType':'text','text':{'content':'这是纯文本消息'}}");

        return sb.toString();
    }

    /**
     * 构造免登图文消息
     * 
     * @param fromUserId
     * @return
     */
    public static String buildImgTextLoginAuthMsg(String fromUserId) {

        StringBuilder sb = new StringBuilder();

        //免登连接地址，开发者需根据部署服务修改相应服务ip地址
        String url = "http://10.15.132.68:8080/AlipayFuwuDemo/loginAuth.html";

        //构建json格式的单发免登图文消息体     authType 等于 "loginAuth"表示免登消息 ： 所有内容开发者请根据自有业务自行设置响应值，这里只是个样例
        sb.append("{'articles':[{'actionName':'立即查看','desc':'这是图文内容','imageUrl':'http://pic.alipayobjects.com/e/201311/1PaQ27Go6H_src.jpg','title':'这是标题','url':'"
                  + url
                  + "', 'authType':'loginAuth'}],'msgType':'image-text', 'toUserId':'"
                  + fromUserId + "'}");

        return sb.toString();
    }

    /**
     * 构造基础的响应消息
     * 
     * @return
     */
    public static String buildBaseAckMsg(String fromUserId) {
        StringBuilder sb = new StringBuilder();
        sb.append("<XML>");
        sb.append("<ToUserId><![CDATA[" + fromUserId + "]]></ToUserId>");
        sb.append("<AppId><![CDATA[" + AlipayServiceEnvConstants.APP_ID + "]]></AppId>");
        sb.append("<CreateTime>" + Calendar.getInstance().getTimeInMillis() + "</CreateTime>");
        sb.append("<MsgType><![CDATA[ack]]></MsgType>");
        sb.append("</XML>");
        return sb.toString();
    }
    
    /**
     * 构建芝麻信用基础响应消息
     * 
     * @return
     */
    public static String buildCreditMsg(String fromUserId, String name, String certNo){
    	StringBuilder sb = new StringBuilder();
        sb.append("<XML>");
        sb.append("<OpenId><!CDATA[" + fromUserId + "]]></OpenId>");
        sb.append("<Name><!CDATA[" + name + "]]></Name>");
        sb.append("<CertNo><!CDATA[" + certNo + "]]></CertNo>");
        sb.append("<CertType><!CDATA[IDENTITY_CARD]]></CertType>");
        sb.append("</XML>");
        return sb.toString();
    }
    
    /**
     * 芝麻信用-是否免押金消息
     * @param fromUserId
     * @param name
     * @param certNo
     * @return
     */
    public static String buildCreditTextMsg(String fromUserId, String name, String certNo){
    	StringBuilder sb = new StringBuilder();
    	
    	//构建json格式单发图文消息: 所有内容开发者请根据自有业务自行设置响应值，这里只是个样例
        sb.append("{name:\""+name+"\",certNo:\""+certNo+"\",openId:\""+fromUserId+"\"}");
        return sb.toString();
    }

}
