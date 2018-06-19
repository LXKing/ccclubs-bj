package com.ccclubs.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.*;
import com.lazy3q.xml.Item;
import com.lazy3q.xml.Xml;

public class SqlUtil {
	
	static String sqltemplate = "alter table {table} add {prefix}_host bigint NOT NULL default 1 after {prefix}_id;";
	static String tablenames="cs_recommend,cs_share_index,cs_chj_sign,cs_complain,cs_revisit,cs_member,cs_member_info,cs_member_ship,cs_gift,cs_record,cs_user_pack,cs_message,cs_free_hour,cs_use_record,cs_refund,cs_member_group,cs_alipay_record,cs_alipay_refund,cs_sms_mt,cs_car_photo,cs_car,cs_outlets,cs_ev_card,cs_recharge_card,cs_provid,cs_trouble,cs_violat,cs_order,cs_order_detail,cs_repair,cs_maintain,cs_remote,cs_alarm,cs_state,cs_history_state";
	
	
	public static void main(String...strings){
		createHistoryEnduranceSqls();
		//createHistoryHostSqls();
		//createHostSqls();
		//getAreaSql();
		//getRepairAdrSql();
	}
	
	
	/**
	 * 转称网点区域数据sql
	 */
	public static void getAreaSql(){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system_remote");
		List<Map> list = dao.executeQuery("select * from srv_property where sp_parent=(select sp_id from srv_property where sp_flag='AREA_TYPE')");
		$.trace("truncate table cs_area;");
		for(Map map:list){
			$.trace("insert into cs_area (csa_id,csa_host,csa_name,csa_status) values("+map.get("sp_id")+",1,'"+map.get("sp_name")+"',1);");
		}
	}

	
	
	/**
	 * 转称维修地点数据
	 */
	public static void getRepairAdrSql(){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system_remote");
		List<Map> list = dao.executeQuery("select * from srv_property where sp_parent=(select sp_id from srv_property where sp_flag='REPAIR_ADR')");
		$.trace("truncate table cs_repair_adr;");
		for(Map map:list){
			$.trace("insert into cs_repair_adr (csra_id,csra_host,csra_name) values("+map.get("sp_id")+",1,'"+map.get("sp_name")+"');");
		}
	}

	
	/**
	 * 批量处理config.xml
	 */
	public static void insertConfigXml(){		
		Xml xml = new Xml();
		try {
			xml.Parse($.readFile("E:/project/java_pro/车厘子项目/ccclubs-4.0/src/config.xml"),"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Boolean> mapping = new HashMap();
		for(String tableName:tablenames.split(","))
			mapping.put(tableName, true);
		List<Item> models = xml.getRoot().getItemList();
		for(Item model:models){
			String tableName = model.getAttribute("table");
			if(mapping.get(tableName)==null)continue;
			String prefix = tumb(tableName,true);
			Item property = new Item("Property");
			property.setAttribute("unique", "false");
			property.setAttribute("sum", "false");
			property.setAttribute("column", prefix+"_host");
			property.setAttribute("type", "java.lang.Long");
			property.setAttribute("length", "8");
			property.setAttribute("query", "true");
			property.setAttribute("group", "false");
			property.setAttribute("relate", "SrvHost");
			property.setAttribute("input", "combox");
			property.setAttribute("add", "editable");
			property.setAttribute("tree", "false");
			property.setAttribute("host", "true");
			property.setAttribute("join", "false");
			property.setAttribute("edit", "editable");
			property.setAttribute("sql_type", "BIGINT");
			property.setAttribute("major", "true");
			property.setAttribute("primary", "false");
			property.setAttribute("not_null", "true");
			property.setAttribute("order", "none");
			property.setAttribute("flag", "false");
			property.setAttribute("delete", "false");
			property.setAttribute("family", "false");
			property.setAttribute("list", "true");
			property.setAttribute("caption", "域");
			property.setAttribute("sign", "false");
			property.setAttribute("reladd", "false");
			property.setAttribute("name", prefix+"Host");
			model.getItemList().add(1, property);
		}
		$.writeFile("E:/project/java_pro/车厘子项目/ccclubs-4.0/src/config.xml", xml.getRoot().toString() , "utf-8");
	}

	
	

	/**
	 * 批量添加host字段
	 */
	public static void createHostSqls(){
		String[] array = tablenames.split(",");
		for(String table:array){
			String prefix = tumb(table,true);
			String sql = sqltemplate.replace("{table}", table).replace("{prefix}", prefix);
			$.trace(sql);
		}
	}
	
	
	
	/**
	 * 批量添加历史状态host字段
	 */
	public static void createHistoryHostSqls(){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system_remote");
		List<Map> list = dao.executeQuery("select csc_number from cs_car");

		for(Map map:list){
			String table = (String) map.get("csc_number");
			String sql = sqltemplate.replace("{table}", "cs_history_state_"+table.toLowerCase()).replace("{prefix}", "cshs");
			$.trace(sql);
		}
	}
	
	
	
	/**
	 * 批量添加历史状态Endurance字段
	 */
	public static void createHistoryEnduranceSqls(){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system_remote");
		List<Map> list = dao.executeQuery("select csc_number from cs_car");
		//String sqltemplateEndurance = "alter table {table} add cshs_endurance varchar(32) default NULL after cshs_electric_mileage;";
		String sqltemplateEndurance = "alter table {table} add cshs_endurance varchar(32) default NULL after cshs_electric_mileage;";
		for(Map map:list){
			String table = (String) map.get("csc_number");
			String sql = sqltemplateEndurance.replace("{table}", "cs_history_state_"+table.toLowerCase());
			$.trace(sql);
		}
	}
	
	
	
	
	
	public static String ForMat(String string)
	{		
		if(string==null || string.equals(""))
			return string;
		string=_J(string);
		return string.substring(0,1).toUpperCase() + string.substring(1);
	}
	
	
	public static String forMat(String string)
	{
		if(string==null || string.equals(""))
			return string;
		string=_J(string);
		return string.substring(0,1).toLowerCase() + string.substring(1);
	}
	
	public static String format(String string)
	{
		if(string==null || string.equals(""))
			return string;
		string=_J(string);
		return string.toLowerCase();		
	}
	
	public static String Format(String string)
	{
		if(string==null || string.equals(""))
			return string;
		string=_J(string);
		string=string.toLowerCase();
		return string.substring(0,1).toUpperCase() + string.substring(1);
	}
	
	public static String FORMAT(String string)
	{
		if(string==null || string.equals(""))
			return string;
		string=_J(string);
		return string.toUpperCase();		
	}	
	
	
	/**
	 * 把数据库字段转成java格式的
	 * @param name
	 * @return
	 */
	public static String _J(String name)
	{		
		String upName="";
		Boolean _=false;
		for(int i=0;i<name.length();i++)
		{
			String ch=""+name.charAt(i);
			if(ch.equals("_"))
			{
				_=true;
			}
			else
			{
				if(_){
					upName+=ch.toUpperCase();
				}
				else{
					upName+=ch.toLowerCase();
				}				
				_=false;
			}			
		}
		return upName;
	}
	
	/**
	 * 根据名称取子级前缀
	 * @param name  父名子
	 * @param bParentPrefix 是否包含父级前缀
	 * @return
	 */
	public static String tumb(String name,boolean bParentPrefix)
	{		
		String strPrefix="";
		Boolean _=true;
		for(int i=0;i<name.length();i++)
		{
			String ch=""+name.charAt(i);
			if(ch.equals("_"))
			{
				_=true;
			}
			else
			{
				if(_){
					strPrefix+=ch.toLowerCase();
				}							
				_=false;
			}			
		}
		
		if(bParentPrefix && name.indexOf("_")>0){
			String sParentPrefix=name.substring(0, name.indexOf("_"));
			strPrefix=sParentPrefix+strPrefix.substring(1);
		}
		
		return strPrefix;
	}

}
