package com.ccclubs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class DataMove {
	
	public static String dumpExePath = "D:\\Program Files\\MySQL\\MySQL Server 5.0\\bin\\";
	public static String path = "d:\\temp\\";
	public static String host="localhost";
	public static String port="3316";
	public static String username="root";
	public static String password="ccclubs";
	public static String database="ccclubs_system";
	public static String table="cs_history_state";
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> list = dao.executeQuery("select csc_number from cs_car");
		for(Map map:list){
			String sNumber = map.get("csc_number").toString();
			$.trace("数据迁移",sNumber);
			mysqlexport(sNumber);
			mysqlimport(sNumber);
			$.trace("成功");
		}	
		
	}
	
	/**
	 * 按车牌号码导出数据
	 * @param 
	 * @throws Exception 
	 */
	public static void mysqlexport(String number) throws Exception{
		number = number.toLowerCase();
		String sCmd="\"{exepath}mysqldump.exe\" --host={host} --port={port} --user={username} --password={password} --add-locks --add-drop-table --lock-tables -w \"{where}\" {database} {table}>{path}{table}.sql";
		sCmd = sCmd.replace("{exepath}",dumpExePath);
		sCmd = sCmd.replace("{host}",host);
		sCmd = sCmd.replace("{port}",port);
		sCmd = sCmd.replace("{username}",username);
		sCmd = sCmd.replace("{password}",password);
		sCmd = sCmd.replace("{database}",database);
		sCmd = sCmd.replace("{table}",table);
		sCmd = sCmd.replace("{path}",path);
		sCmd = sCmd.replace("{where}","cshs_number='{number}'");
		sCmd = sCmd.replace("{number}",number);
		//$.trace(sCmd);
			
		try{
			$.writeFile("d://temp/export.bat", sCmd, $.UTF8);
			final Process process = Runtime.getRuntime().exec("d:\\temp\\export.bat");
			process.waitFor();
		}catch(Exception ex){
			ex.printStackTrace();
		}catch(Error er){
			er.printStackTrace();
		}		
	}
	
	
	
	/**
	 * 数据导入
	 * @param 
	 * @throws Exception 
	 */
	public static void mysqlimport(String number) throws Exception{
		try{
			number = number.toLowerCase();
			
			$.getDao("ccclubs_system_history").execute("drop table if exists cs_history_state");
			$.getDao("ccclubs_system_history").execute("CREATE table if not exists cs_history_state LIKE cs_history_state_template");	
					
			String sCmd="\"{exepath}mysql.exe\" --host={host} --port={port} --user={username} --password={password} {database}_history<{path}{table}.sql";
			sCmd = sCmd.replace("{exepath}",dumpExePath);
			sCmd = sCmd.replace("{host}",host);
			sCmd = sCmd.replace("{port}",port);
			sCmd = sCmd.replace("{username}",username);
			sCmd = sCmd.replace("{password}",password);
			sCmd = sCmd.replace("{database}",database);
			sCmd = sCmd.replace("{table}",table);
			sCmd = sCmd.replace("{path}",path);
			sCmd = sCmd.replace("{where}","cshs_number='{number}'");
			sCmd = sCmd.replace("{number}",number);
		//$.trace(sCmd);			
		
			$.writeFile("d://temp/import.bat", sCmd, $.UTF8);
			final Process process = Runtime.getRuntime().exec("d:\\temp\\import.bat");
			process.waitFor();
		}catch(Exception ex){
			ex.printStackTrace();
		}catch(Error er){
			er.printStackTrace();
		}	
		
		
		$.getDao("ccclubs_system_history").execute("drop table if exists cs_history_state_"+number);
		$.getDao("ccclubs_system_history").execute("RENAME TABLE cs_history_state TO cs_history_state_"+number);		
		
	}
	
	
	
	

}
