package com.ccclubs.util;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.ccclubs.helper.EventHelper;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.*;

public class Util {

	public static void main(String...strings){
		
		
		$.trace($.date("2015-02-12 16:22:57", "yyyy-MM-dd HH:mm:ss").getTime());
		
		/*
		Velocity.init();
		VelocityContext context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", " Jakarta"); 
		
		
		String template = "We are using $project $name to render this.";

		StringWriter w = new StringWriter();
		Velocity.evaluate(context, w, "dd", template);

		System.out.println(" string : " + w);*/
	}
	
	
	public static Map jscv(){
		String url = $.getUrlPath();
	    if ($.empty(url)) return null;
	    String strHashCode = String.valueOf(Math.abs(url.hashCode()));
	    String strJson = $.getCookieValue(strHashCode);
		return (Map) ($.empty(strJson)?null:$.eval(strJson));
	}
	
}
