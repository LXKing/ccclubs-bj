package com.ccclubs.action.m.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

@Component
public class JsonUtil {

	/*
	 * 输出JSON格式String
	 */
	public void outJsonString(String str, HttpServletResponse response) {
		response.setContentType("text/json;charset=utf-8");
		outString(str, response);
	}
	/*
	 * 输出JSONP格式String
	 */
	public void outJsonpString(String str, String callback, HttpServletResponse response) {
		response.setContentType("text/json;charset=utf-8");
		String jsoncallback = callback + "({'result':" + str + "})";
		outString(jsoncallback, response);
	}

	/*
	 * 输出JSON格式返回到页面
	 */
	public void outJson(Object obj, HttpServletResponse response) {
		outJsonString(JSONObject.fromObject(obj, overLoadJsonConfig())
				.toString(), response);
	}
	/*
	 * 输出JSONP格式返回到页面
	 */
	public void outJsonp(Object obj, String callback, HttpServletResponse response){
		outJsonpString(JSONObject.fromObject(obj, overLoadJsonConfig())
				.toString(), callback, response);
	}

	/*
	 * 输出String返回到页面
	 */
	public void outString(String str, HttpServletResponse response) {
		try {
			System.out.println(response.getContentType());
			System.out.println(str);
			PrintWriter out = response.getWriter();
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control", "no-cache");
			out.write(str);
		} catch (IOException e) {
		}
	}

	/*
	 * 输出JSON-Array格式返回到页面
	 */
	public void outJsonArray(Object array, HttpServletResponse response) {
		outJsonString(JSONArray.fromObject(array, overLoadJsonConfig())
				.toString(), response);
	}

	/*
	 * 输出XML格式返回到页面
	 */
	public void outXMLString(String xmlStr, HttpServletResponse response) {
		response.setContentType("application/xml;charset=utf-8");
		outString(xmlStr, response);
	}

	public void outHtmlString(Object obj, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		outString(JSONObject.fromObject(obj, overLoadJsonConfig()).toString(),
				response);
	}

	/*
	 * 专门处理AJAX请求后返回页面消息
	 */
	protected void ajaxResponseMsgToPage(String msg,
			HttpServletResponse response) {
		// 构造消息XML结构：<root><msg>*******</msg></root>
		String msgxml = null;// ChangeXmlElement.createStringResult(msg);
		// 信息返回页面
		this.responsexmltopage(msgxml, response);
	}

	protected void responsexmltopage(String s, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/xml");
			response.getWriter().write(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重写json config,处理页面上对 Integer,Long，为空时，json自动转换为0的bug
	 */
	@SuppressWarnings("rawtypes")
	protected JsonConfig overLoadJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerDefaultValueProcessor(Long.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});

		jsonConfig.registerDefaultValueProcessor(Integer.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});

		jsonConfig.registerDefaultValueProcessor(String.class,
				new DefaultValueProcessor() {
					public Object getDefaultValue(Class type) {
						return null;
					}
				});

		return jsonConfig;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private static Collection jsonToCollection(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection c = JSONArray.toCollection(jsonArray, HashMap.class);
		return c;
	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static List jsonToList(String json, Class objectClass) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		List list = JSONArray.toList(jsonArray, objectClass);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public static Object[] jsonToArray(String json, Class objClass) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		Object[] result = (Object[]) JSONArray.toArray(jsonArray, objClass);
		return result;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> jsonToMapList(String json) {
		JSONArray jsonArr = JSONArray.fromObject(json);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(jsonToMap(json2.toString()));
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map jsonToMap(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject jsonObject = JSONObject.fromObject(json);
		for (Object k : jsonObject.keySet()) {
			Object v = jsonObject.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(jsonToMap(json2.toString()));
				}
				map.put(k.toString(), list);
			} else if (v instanceof JSONObject) {
				if (((JSONObject) v).isNullObject()) {
					map.put(k.toString(), null);
				} else {
					map.put(k.toString(), v);
				}
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	public static Object jsonToBean(String json, Class objClass) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Object object = JSONObject.toBean(jsonObject, objClass);
		return object;
	}

	public String objectTojson(Object object) {
		return JSONObject.fromObject(object, overLoadJsonConfig()).toString();
	}
	
	public String arrayTojson(Object object) {
		return JSONArray.fromObject(object, overLoadJsonConfig()).toString();
	}
}
