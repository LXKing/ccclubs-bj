package com.ccclubs.action.api.chargedot.aishi.bio;

/**
 * 
 * 阻塞中断器
 * 
 * @author zhangjian
 *
 */
public interface BlockTnterruptor {
	
	enum Type{
		/**
		 * 阻塞
		 */
		BLOCK,
		
		/**
		 * 中断
		 */
		INTERRUPT,
		
		/**
		 * 结束
		 */
		END
	}
	
	/**
	 * 
	 * 是否中断阻塞
	 * 
	 * @param all	所有消息
	 * @param current	当前消息
	 * @return
	 */
	public Type interrupt(String all, String current);
	
}
