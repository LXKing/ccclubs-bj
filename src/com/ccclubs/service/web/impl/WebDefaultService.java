package com.ccclubs.service.web.impl;

import com.ccclubs.service.web.IWebDefaultService;
import com.lazy3q.util.Function;

public class WebDefaultService implements IWebDefaultService {

	public <T> T executeTransaction(Function function) {
		return function.execute();
	}
}
