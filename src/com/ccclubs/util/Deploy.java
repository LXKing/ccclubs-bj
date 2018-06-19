package com.ccclubs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipInputStream;


/**
 * 项止发布部署程序
 * @author uiu
 */
public class Deploy {
	
	public static String root = new File("").getAbsolutePath()+"\\";//"D:/Servers-4.0/Applications/";
	
	public static final String[] excludes = new String[]{
		"WEB-INF/web.xml",
		"WEB-INF/struts.xml",
		"WEB-INF/struts.properties",
		"WEB-INF/log4j.properties",
		"WEB-INF/applicationContext-import.xml",
		"WEB-INF/thread/applicationContext-Thread.xml",
		"WEB-INF/classes/config.properties",
		"WEB-INF/lib/lazy3q.jar",
		"ROOT/index.html"
	};
	public static String regex = "";
	static{
		for(String exclude:excludes){
			exclude = exclude.replace(".", "\\.").replace("-", "\\-");
			exclude = "([\\s\\S]*?"+exclude+"$)";
			regex+=(regex.equals("")?"":"|")+exclude;
		}
	}
	
	public static final String[] applications=new String[]{"全部应用","extman","admin","api","app","thread","unit","web","weixin","official"};
	public static void main(String...strings) throws Exception{
		if(strings.length==1)
			root = strings[0];
		System.out.println("ccclubs项目部署程序开始....");
		System.out.println("部署之前请先确认您是把ROOT文件夹打包为ROOT.zip并且放在"+root+"文件夹中");
		System.out.println("部署时下面这些文件将不被部署，如有修改请直接到所在应用的文件中修改");
		for(String exclude:excludes)
			System.out.println(exclude);
		System.out.println("请输入您要部署的哪个应用对应的序号，并按下回车确认，放弃请关闭当前窗口");
		System.out.println("0:全部应用,1:extman,2:admin,3:api,4:app,5:thread,6:unit,7:web,8:weixin,9:official");
		System.out.print("请输入编号:");
		BufferedReader dr=new BufferedReader(new InputStreamReader(System.in)); 
		String strLine = dr.readLine();
		if(strLine.equals("0")){
			System.out.println("您选择要部署的应用是[全部应用]...3秒之后开始部署");
			Thread.sleep(3000);
			for(int i=1;i<applications.length;i++)
				executeDeploy(applications[i]);
		}else{
			for(String strItem:strLine.split(",")){
				int type = Integer.parseInt(strItem);
				executeDeploy(applications[type]);
			}
		}
		System.out.println("部署全部完成!");
				
		Thread.sleep(5000000);
	}
	private static void executeDeploy(String appType) {
		File zipFile = new File(root+"ROOT.zip");
		if(!zipFile.exists() || !new File(root+appType+"/").exists()){
			return;
		}
		System.out.println("开始部署应用["+appType+"]");
		int total = 0;//总文件数
		long startTime=System.currentTimeMillis();
		try {
			System.setProperty("sun.zip.encoding", System.getProperty("sun.jnu.encoding"));
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(zipFile));//输入源zip路径
			ZipInputStream countZin=new ZipInputStream(new FileInputStream(zipFile));//输入源zip路径
			BufferedInputStream Bin=new BufferedInputStream(Zin);
			String target=root+appType+"/"; //输出路径（文件夹目录）
			File Fout=null;
			while(countZin.getNextEntry()!=null)total++;
			countZin.close();
			java.util.zip.ZipEntry entry = null;
			int count = 0;
			int last = 0;
			String process = "";
			try{
				while((entry = Zin.getNextEntry())!=null){
					if(!entry.isDirectory()){
						String entryName = entry.getName();
						if(!entryName.startsWith("ROOT")){
							System.out.println("部署被放弃，原因：zip文件中未发现ROOT根文件夹");
							break;
						}
						Fout=new File(target,entryName);
						if(!Fout.exists()){
							(new File(Fout.getParent())).mkdirs();
						}
						if(java.util.regex.Pattern.matches(regex, entryName)){
							continue;
						}
						int scan = ((++count)*10/total);
						if(scan!=last){
							last = scan;
							for(int i=0;i<process.getBytes().length;i++)
								System.out.print("\b");
							process=(scan*10)+"%";
							for(int i=0;i<scan;i++)
								process+="■";
							System.out.print(process);
						}
						FileOutputStream out=new FileOutputStream(Fout);
						BufferedOutputStream Bout=new BufferedOutputStream(out);
						int b;
						while((b=Bin.read())!=-1){
							Bout.write(b);
						}
						Bout.close();
						out.close();
					}
				}
				{
					for(int i=0;i<process.getBytes().length;i++)
						System.out.print("\b");
					process=(10*10)+"%";
					for(int i=0;i<10;i++)
						process+="■";
					System.out.print(process);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			Bin.close();
			Zin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
		System.out.println();
		System.out.println("应用["+appType+"]部署完成!共复制文件"+total+"个,"+"耗费时间： "+(endTime-startTime)+" ms");
	}

}
