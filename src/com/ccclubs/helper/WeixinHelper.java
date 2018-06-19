package com.ccclubs.helper;

import java.util.Date;

import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsWxMt;
import com.lazy3q.web.helper.$;

public class WeixinHelper {
	
	/**
	 * 发送文本消息
	 * @param platform [不能为空] 发送平台 1:微信 2:支付宝
	 * @param openid [可以为空，与下不能同时为空] 三方标识
	 * @param member [可以为空，与上不能同时为空] 会员ID
	 * @param content 消息内容
	 */
	public static Boolean sendWxTextMessage(Short platform,String openid,Long member,final String content){		
		if(platform==null)platform=0;
		CsMember csMember = null;
		if(member!=null){
			csMember = CsMember.get(member);
		}else if(!$.empty(openid)){
			if(platform.shortValue()==1){
				csMember = CsMember.where().csmWeixinFlag(openid).get();
			}else if(platform.shortValue()==2){
				csMember = CsMember.where().csmAlipayFlag(openid).get();
			}else
				return false;
		}else{
			return false;
		}
		if(csMember==null)
			return false;
		
		final CsMember fCsMember = csMember;
		new Thread(){
			public void run(){
				new CsWxMt(
					fCsMember.getCsmHost()//城市 [非空]
				 	,(short)0//平台类型 [非空]
				 	,null//三方标识
				 	,fCsMember.getCsmId()//关联会员
				 	,null//分类标识
				 	,(short)1//内容类型 [非空]
				 	,null//关联对象
				 	,content//消息内容
				 	,null//关联上行
				 	,null//发送时间
				 	,0//发送状态
				 	,0//成功状态
				 	,new Date()//添加时间 [非空]
				 	,(short)0//数据状态 [非空]
				 ).save();
			}
		}.start();
		
		return true;
	}
	/**
	 * 发送图片消息
	 * @param platform [不能为空] 发送平台 0:全部 1:微信 2:支付宝
	 * @param openid [可以为空，与下不能同时为空] 三方标识
	 * @param member [可以为空，与上不能同时为空] 会员ID
	 * @param url 图片地址
	 */
	public static Boolean sendWxImageMessage(Short platform,String openid,Long member,final String url){
		if(platform==null)platform=0;
		CsMember csMember = null;
		if(member!=null){
			csMember = CsMember.get(member);
		}else if(!$.empty(openid)){
			if(platform.shortValue()==1){
				csMember = CsMember.where().csmWeixinFlag(openid).get();
			}else if(platform.shortValue()==2){
				csMember = CsMember.where().csmAlipayFlag(openid).get();
			}else
				return false;
		}else{
			return false;
		}
		if(csMember==null)
			return false;
		
		
		final CsMember fCsMember = csMember;
		new Thread(){
			public void run(){
				new CsWxMt(
					fCsMember.getCsmHost()//城市 [非空]
				 	,(short)0//平台类型 [非空]
				 	,null//三方标识
				 	,fCsMember.getCsmId()//关联会员
				 	,null//分类标识
				 	,(short)2//内容类型 [非空]
				 	,null//关联对象
				 	,url//消息内容
				 	,null//关联上行
				 	,null//发送时间
				 	,0//发送状态
				 	,0//成功状态
				 	,new Date()//添加时间 [非空]
				 	,(short)0//数据状态 [非空]
				 ).save();
			}
		}.start();		
		
		return true;
	}
	
	/**
	 * 发送微信模板消息
	 * @param member 接收会员ID
	 * @param modelClass 关联的实体模型类型，比如CsOrder,CsViolat,CsTrouble
	 * @param id 实体模型对应的主键ID 
	 * @return
	 */
	public static Boolean sendWxTemplateMessage(Long member,final WxTemplateMsgType wxTemplateMsgType,final Class modelClass,final Long id){
		CsMember csMember = CsMember.get(member);
		if(csMember==null)
			return false;
		if($.empty(csMember.getCsmWeixinFlag()) && $.empty(csMember.getCsmAlipayFlag()))
			return false;
		
		final CsMember fCsMember = csMember;
		new Thread(){
			public void run(){		
				new CsWxMt(
						fCsMember.getCsmHost()//城市 [非空]
				 	,(short)0//平台类型 [非空]
				 	,null//三方标识
				 	,fCsMember.getCsmId()//关联会员
				 	,wxTemplateMsgType.name()//分类标识
				 	,(short)3//内容类型 [非空]
				 	,modelClass.getSimpleName()+"@"+id//关联对象
				 	,null//消息内容
				 	,null//关联上行
				 	,null//发送时间
				 	,0//发送状态
				 	,0//成功状态
				 	,new Date()//添加时间 [非空]
				 	,(short)0//数据状态 [非空]
				 ).save();
			}
		}.start();	
		
		 return true;
	}
	public enum WxTemplateMsgType{
		违章通知,订单预订,订单续订,订单取消,订单结算,信用卡帐单,系统通知,故障报修,系统报警,一般通知
	}
	
	/**
	 * 发送微信模板消息
	 * @param member 接收会员ID
	 * @param modelClass 关联的实体模型类型，比如CsOrder,CsViolat,CsTrouble
	 * @param id 实体模型对应的主键ID
	 * @return
	 */
	public static Boolean sendWxTemplateMessage(final Long host,final Short platform,final String openid,final WxTemplateMsgType wxTemplateMsgType,final Class modelClass,final Long id){
		
		new Thread(){
			public void run(){		
				new CsWxMt(
					host//城市 [非空]
				 	,platform//平台类型 [非空]
				 	,openid//三方标识
				 	,null//关联会员
				 	,wxTemplateMsgType.name()//分类标识
				 	,(short)3//内容类型 [非空]
				 	,modelClass.getSimpleName()+"@"+id//关联对象
				 	,null//消息内容
				 	,null//关联上行
				 	,null//发送时间
				 	,0//发送状态
				 	,0//成功状态
				 	,new Date()//添加时间 [非空]
				 	,(short)0//数据状态 [非空]
				 ).save();		
			}
		}.start();	
		
		 return true;
	}

}
