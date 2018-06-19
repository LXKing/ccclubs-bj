package com.ccclubs.service.common;

import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsLucky;
import com.ccclubs.model.CsLuckyResult;

/**
 * 抽奖服务
 * @author uiu
 *
 */
public interface ILuckyService {

	public enum LuckyType{
		积分抽奖大转盘
	}
	
	/**
	 * 获取抽奖活动对象
	 * @param luckyType
	 * @return
	 */
	public CsLucky getCsLucky(LuckyType luckyType);
	
	
	/**
	 * 抽奖 
	 * @param member
	 * @param from 抽奖来源 0:网站 1:微信 2:app 3:其它  
	 * @param 错误代码 - 100到200之间为系统异常 201:未登录  202:活动已结束  203:积分不够  204:奖池奖品已经抽完
	 * @return
	 */
	public CsLuckyResult executeLucky(LuckyType luckyType,Long member,Short from)throws MessageException;	
	
	
}
