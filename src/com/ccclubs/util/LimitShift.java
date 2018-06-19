package com.ccclubs.util;

import java.util.List;
import java.util.Map;

import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class LimitShift {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		$.trace(1|2);
		
		$.trace(1|2|4|8);
		
		$.trace(0|0|0|0|0|0|64|128|256|512|1024|2048|4096|8192|16384|32768|65536);
		
	}

}
