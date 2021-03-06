package com.ccclubs.action.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.config.SYSTEM;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.JsonHelper;
import com.lazy3q.web.helper.WebHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.opensymphony.xwork2.ActionContext;

public class UploadAction {
	
	
	private static String upload_forder=ConfigHelper.GetString("upload.forder");//上传图片保存的路径
	private static String upload_domain=ConfigHelper.GetString("upload.domain");//图片服务的域名地址
	private static Map<String,String> types=$
		.add("member_edit", "header")
		.add("memberinfo_edit", "users")
		.add("regist", "users")
		.add("carmodel_edit", "cartype")
		.add("outlets_edit", "outlets")
		.add("update_edit", "app")
		.add("carphoto_edit", "photo")
		.add("car_edit", "car")
		.add("trouble_edit", "trouble")
		.add("help_edit", "help")
		.add("help_edit", "help")
		.add("cashrecord_edit", "finance")
		.add("longorderfee_edit", "finance")
		.add("longorder_edit", "finance")
		.add("wxcontent_edit", "weixin");
	
	private File file;
	private String fileFileName;
	
	public String execute(){
		
		try{
			Map<String,Object> params = ActionContext.getContext().getParameters();
			String sApp=$.getString(params, "app");//应用类型							
			String remote=$.getString(params, "remote");
			String callback = $.getString(params,"callback");
			if(!$.empty(remote))
				remote=remote+(remote.indexOf("?")>-1 ? "&" : "?") +"1="+ 1;
			if($.empty(sApp)||(sApp=types.get(sApp))==null){
				LzMap retMap = $.add("error", 1).add("message", "上传时未指定app参数");
				if(!WebHelper.empty(remote))
					return WebHelper.Redirect(remote+"&json="+WebHelper.hex(JsonHelper.json(retMap)));
				else
					return WebHelper.SendAjax(retMap, "UTF-8");				
			}
			
			HttpServletRequest request = ServletActionContext.getRequest();			
			if(WebHelper.empty(upload_forder))
				upload_forder=request.getRealPath("/");
			upload_forder=upload_forder.replaceAll("\\\\", "/");
			if(!upload_forder.endsWith("/"))
				upload_forder+="/";			
			if(WebHelper.empty(upload_domain)){
				String port="";
				if(request.getServerPort()!=80)
					port=":"+request.getServerPort();
				upload_domain = request.getScheme()+"://"+request.getServerName()+port+request.getContextPath()+"/";
			}
					
			
			String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();				
			String filePah=sApp+"/";
			new File(upload_forder+filePah).mkdirs();
			String fileName = new SimpleDateFormat("yyyyMMddHHmmssS"+WebHelper.rand(999)).format(new Date());
			
			
			Map jsonMap=new HashMap();
			
						
			//保存原始上传的文件
			String uploadFileName=fileName+"."+fileExt;				
			File uploadedFile = new File(upload_forder+filePah, uploadFileName);			
			file.renameTo(uploadedFile);//保存上传的文件
			jsonMap.put("url", upload_domain+filePah+uploadFileName);//上传的文件
			$.trace("文件上传成功",uploadedFile.getAbsolutePath());
			
			//保存的文件路径名
			String strSavePaths=uploadedFile.getAbsolutePath();			
				
			jsonMap.put("message", "文件上传成功");
			jsonMap.put("error", 0);
			if(!WebHelper.empty(remote))
				WebHelper.Redirect(remote+"&json="+WebHelper.hex(JsonHelper.json(jsonMap)));
			else if(!$.empty(callback))
				WebHelper.SendJsonp(callback, jsonMap, "UTF-8");
			else
				WebHelper.SendAjax(jsonMap, "UTF-8");			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			WebHelper.SendAjax($.Map().add("message", SYSTEM.ERROR_TIPS), "UTF-8");
		}catch(Error e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			WebHelper.SendAjax($.Map().add("message", SYSTEM.ERROR_TIPS), "UTF-8");
		}
		
		return null;
	}
	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}



	public File getImgFile() {
		return file;
	}


	public void setImgFile(File imgFile) {
		this.file = imgFile;
	}


	public String getImgFileFileName() {
		return fileFileName;
	}


	public void setImgFileFileName(String imgFileFileName) {
		this.fileFileName = imgFileFileName;
	}
}
