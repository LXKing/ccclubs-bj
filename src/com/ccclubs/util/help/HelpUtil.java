package com.ccclubs.util.help;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.ccclubs.model.SrvHelp;
import com.ccclubs.model.SrvHelp.M;
import com.lazy3q.web.helper.*;

import freemarker.template.Configuration;

public class HelpUtil {

	public static void export() {
		try {
			javax.servlet.http.HttpServletResponse response = com.lazy3q.xwork.WebContext.getServletResponse();
			response.setCharacterEncoding("UTF-8"); 
			OutputStream os = response.getOutputStream();
			response.setContentType("application/x-octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("系统帮助手册","UTF-8")+".zip");
			
			System.setProperty("sun.zip.encoding", System.getProperty("sun.jnu.encoding"));
			ZipInputStream Zin=new ZipInputStream(HelpUtil.class.getResourceAsStream("help.zip"));			
			ZipOutputStream Zout = new ZipOutputStream(os);
			try{
				
				
				//把源zip的移到导出的zip中
				{
					java.util.zip.ZipEntry entry = null;
					while((entry = Zin.getNextEntry())!=null){
						if(entry.isDirectory()){ 
							ZipEntry zipEntry = new ZipEntry(entry.getName());
							Zout.putNextEntry(zipEntry);
						}else{
							ZipEntry zipEntry = new ZipEntry(entry.getName());
							zipEntry.setSize(entry.getSize());
							Zout.putNextEntry(zipEntry);
							int b;
							while((b=Zin.read())!=-1){
								Zout.write(b);
							}
						}
					}
				}
				
				List<SrvHelp> helpers = SrvHelp.where().shStatus((short)1).list(-1);
				//把帮助目录压入文件夹
				{
					//String listhtml = FremarkerHelper.executeFremarker($.readFile(HelpUtil.class.getResourceAsStream("list.fm"), "UTF-8"),$.add("list", helpers));
					String listhtml = FremarkerHelper.executeFremarker($.readFile(HelpUtil.class.getResourceAsStream("list.fm"), "UTF-8"),$.add("list", $.eval($.json(helpers))));
					Zout.putNextEntry(new ZipEntry("html/list.html"));
					Zout.write(listhtml.getBytes("utf-8"));
				}
				
				//把帮助子文件压入文件夹
				for(SrvHelp srvHelp:helpers){
					//String html = FremarkerHelper.executeFremarker($.readFile(HelpUtil.class.getResourceAsStream("view.fm"), "UTF-8"),$.add("srvHelp", srvHelp));
					String html = FremarkerHelper.executeFremarker($.readFile(HelpUtil.class.getResourceAsStream("view.fm"), "UTF-8"),$.add("srvHelp", $.eval($.json(srvHelp))));
					ZipEntry entry = new ZipEntry("html/"+srvHelp.getShId()+".html");
					entry.setSize(html.getBytes("utf-8").length);
					Zout.putNextEntry(entry);
					Zout.write(html.getBytes("utf-8"));
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				Zout.close();
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	
	/**
	 * 根据url地址下载文件到字节中
	 * @param url
	 * @param filePathName
	 * @return
	 */
	public static byte[] download(String url) {
		InputStream is=null;	
		try {
			URL Url = new URL(url);
			URLConnection conn = Url.openConnection();
			conn.connect();
			is = conn.getInputStream();
			int fileSize = conn.getContentLength();// 根据响应获取文件大小
			if (fileSize <= 0) // 获取内容长度为0
				return null;		
			if (is == null)
				return null;
			byte buf[] = new byte[fileSize];
			int numread;
			while ((numread = is.read(buf)) != -1);
			is.close();
			return buf;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}finally{
			if(is!=null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		}
	}
	
	
	public static void main(String...strings) throws Exception{
		byte[] bytes = download("http://admin.ccclubs.com/admin/login.do");
		$.trace(new String(bytes,"utf-8"));
	}
}
