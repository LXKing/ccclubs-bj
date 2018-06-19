package com.ccclubs.action.weixin;

import java.io.File;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ccclubs.util.UploadUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper;
import com.opensymphony.xwork2.ActionContext;

public class UploadAction {

	private File	file;
	private String	fileFileName;

	public String execute() {

		try {
			Map<String, Object> params = ActionContext.getContext().getParameters();
			String sApp = $.getString(params, "app");// 应用类型

			String result = UploadUtil.getInstance().uploadFile(file, "file", fileFileName, "http://pic.ccclubs.com/commonUpload.do", $.Map().add("app", sApp));

			$.trace("uploadAction result :" + result);

			if ($.empty(result)) {
				return $.SendAjax($.Map().add("code", 201).add("message", "系统繁忙，请稍候再试"), "UTF-8");
			}
			return WebHelper.SendAjax(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.Map().add("code", 201).add("message", "系统繁忙，请稍候再试"), "UTF-8");
		}
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
