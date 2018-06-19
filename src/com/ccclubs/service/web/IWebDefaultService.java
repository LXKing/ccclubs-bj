package com.ccclubs.service.web;

import com.lazy3q.util.Function;

public interface IWebDefaultService {

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);
	
}
