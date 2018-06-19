package com.ccclubs.action.web.activity.newyear2015;

import java.util.Date;

/**
 * 用户预定
 * @author joel
 *
 */
public class UserBook {
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 资源标识
	 */
	private Long resId;
	
	/**
	 * 日期
	 */
	private Date date;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
