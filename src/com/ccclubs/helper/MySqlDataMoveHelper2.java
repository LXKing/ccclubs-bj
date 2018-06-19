package com.ccclubs.helper;

import java.util.Date;

import com.lazy3q.web.helper.$;

public class MySqlDataMoveHelper2 {

//	public static String dumpExePath = "D:\\Program Files\\mysql-5.6.21-win32\\bin\\";
	public static String dumpExePath = "D:\\soft\\mysql5\\bin\\";
	public static String tempDataPath = "d:\\data\\temp\\";
	
	public static void MySqlDataMove(
		String srcHost,String srcPort,String srcUser,String srcPassword,String srcDatabase,
		String tableName,String where
	) throws Exception,Error{
		
		
		Date start = new Date();
		{
			String sCmd="\"{exepath}mysqldump.exe\" --host={host} --port={port} --user={username} --password={password} --add-locks --add-drop-table --lock-tables "+($.empty(where)?"":"-w \"{where}\"")+" {database} {table}>{path}"+tableName+".sql";
			sCmd = sCmd.replace("{exepath}",dumpExePath);
			sCmd = sCmd.replace("{host}",srcHost);
			sCmd = sCmd.replace("{port}",srcPort);
			sCmd = sCmd.replace("{username}",srcUser);
			sCmd = sCmd.replace("{password}",srcPassword);
			sCmd = sCmd.replace("{database}",srcDatabase);
			sCmd = sCmd.replace("{table}",tableName);
			sCmd = sCmd.replace("{path}",tempDataPath);
			sCmd = sCmd.replace("{where}",$.or(where, ""));
			$.writeFile(tempDataPath+"export.bat", sCmd, $.UTF8);
			$.trace("准备从"+srcHost+"\\"+srcDatabase+"导出表"+tableName);
			$.trace(sCmd);
			final Process process = Runtime.getRuntime().exec(tempDataPath+"export.bat");
			process.waitFor();
			$.trace("导出完成，耗时"+(new Date().getTime()-start.getTime())+"毫秒");
		}
		
	}
	
	
	
	/**
	 * @param args
	 * @throws Error 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception, Error {
		MySqlDataMoveHelper2.MySqlDataMove(
			"localhost", "3306", "root", "", "ccclubs_system", 
			"cs_member", "csm_host=1"
		);
	}

	
	
}
