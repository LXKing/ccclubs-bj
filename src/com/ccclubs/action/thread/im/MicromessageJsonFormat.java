package com.ccclubs.action.thread.im;

import com.lazy3q.web.helper.WebHelper.LzMap;

/**
 * 微信模板信息json格式，通用
 * @author qsxiaogang
 *
 */
public class MicromessageJsonFormat {

	public String	touser		= "";
	public String	template_id	= "";
	public String	url			= "";
	public String	topcolor	= "#FF0000";
	public LzMap	data		= new LzMap();
}
