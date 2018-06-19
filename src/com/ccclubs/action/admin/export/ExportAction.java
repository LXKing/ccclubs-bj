package com.ccclubs.action.admin.export;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.jxl.ExcelOpException;
import com.ccclubs.helper.jxl.ExcelOperator;
import com.ccclubs.helper.jxl.ExcelOperator.ExcelOperatorType;
import com.ccclubs.helper.jxl.ExcelOperator.FileOperatorType;
import com.ccclubs.helper.jxl.ExcelWriteData;
import com.ccclubs.helper.jxl.IExcelOperator;
import com.lazy3q.lang.Feflect;
import com.lazy3q.lang.caption;
import com.lazy3q.util.zip.ZipEntry;
import com.lazy3q.util.zip.ZipOutputStream;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;


public class ExportAction {

	private IExcelOperator operator = new ExcelOperator();
	
	
	public String execute() {
		String path = ServletActionContext.getServletContext().getRealPath("export");
		String className = $.getString("className");
		String[] keys = $.getArray("keys");
		int size = $.getInteger("size",5000);
		try {
			
			
			Class<?> c = Class.forName(className);
			Field[] fields = filedList(c);
			caption capt = c.getAnnotation(caption.class);
			
			//获取导出字段名称
			FieldHelper fieldHelper = new FieldHelper();
			fieldHelper.field = fields;
			fieldHelper = getFieldHelper(c, keys, fieldHelper);
			
			String title = capt.value();
			
			//压缩文件名称
			String fileName = UUID.randomUUID().toString();
			String pathName = path + File.separator + fileName;
			File filePath = new File(pathName);
			
			
			//获取查询条件
			Map<String, Object> objects = ActionHelper.getQueryFormParams(c);
			
			//获取导出数据
			Method pageMethod = c.getDeclaredMethod("page", int.class,int.class, Map.class);
			Page page = (Page) pageMethod.invoke(null, 0, size, objects);
			//拼装导出数据
			ExcelWriteData writeData = new ExcelWriteData();
			Date startTime = new Date();
			writeData.setList(getList(c, fieldHelper, page));
			Logger.getRootLogger().error("time console : "+ $.date(startTime, "yyyy-MM-dd HH:mm:ss")+" - "+ $.date(new Date(), "yyyy-MM-dd HH:mm:ss"));
			writeData.setTitle(fieldHelper.caption);
			

			write(title + "-0.csv", filePath,writeData);
			
			int total = page.getTotal();
			
			for (int i = 1; i < total; i++) {
				page = (Page) pageMethod.invoke(null, i, size, objects);
				startTime = new Date();
				writeData.setList(getList(c, fieldHelper, page));
				Logger.getRootLogger().error("time console : "+ $.date(startTime, "yyyy-MM-dd HH:mm:ss")+" - "+ $.date(new Date(), "yyyy-MM-dd HH:mm:ss"));
				write(title + "-"+i+".csv", filePath,writeData);
			}
			//File zipFile = new File(filePath+".rar");
			//$.zip(zipFile, filePath.listFiles());
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName+".rar", "UTF-8"));  
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream(),"GBK");//压缩包
			byte[] buf = new byte[1024];
			int len;
			for(File file:filePath.listFiles()){
				//把上传的文件压缩到压缩文件		
				ZipEntry ze = new ZipEntry(file.getName());//这是压缩包名里的文件名
				zos.putNextEntry(ze);//写入新的 ZIP 文件条目并将流定位到条目数据的开始处
			    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				while ((len = bis.read(buf)) != -1) {
					zos.write(buf, 0, len);
					zos.flush();
				}
				bis.close();
				file.delete();
			}
			
			zos.flush();
			zos.close();
			filePath.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param fileName 文件名
	 * @param pathName 文件存放目录
	 * @param page 
	 * @param writeData
	 * @throws ExcelOpException
	 * @throws Exception
	 */
	public void write(String fileName,File filePath,ExcelWriteData writeData) throws ExcelOpException, Exception{
		
		if(!filePath .exists()){
			filePath.mkdirs();
		}
		
		File file = new File(filePath+File.separator+fileName);
		if(file.exists()){
			file.delete();
		}else{
			file.createNewFile();
		}
		operator.setFile(file);
		operator.setOperateType(ExcelOperatorType.WRITE);
		operator.setFileOperateType(FileOperatorType.CSV);
		operator.setWriteData(writeData);
		operator.getOperator().execute();
	}
	/**
	 * 获取所有属性
	 * @param c
	 * @return
	 */
	public Field[] filedList(Class<?> c){
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = c.getDeclaredFields();
		for(Field field:fields){
			if(field.getAnnotation(caption.class)!=null){
				fieldList.add(field);
			}
		}
		return  fieldList.toArray(new Field[fieldList.size()]);
	}
	
	/**
	 * 
	 * @param c
	 * @param keys
	 * @param fields
	 * @param fieldHelper
	 * @return
	 */
	public FieldHelper getFieldHelper(Class<?> c,String[] keys,FieldHelper fieldHelper){
		List<String> titleList = new ArrayList<String>();
		List<String> fileNameList = new ArrayList<String>();
		if(keys != null){
			for (String key : keys) {
				try {
					titleList.add(Feflect.getFieldCaption(c, key));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					titleList.add(key.toUpperCase());
				}
			}
		}else{
			for (Field field : fieldHelper.field) {
				try {
					titleList.add(Feflect.getFieldCaption(c, field.getName()));
					fileNameList.add(field.getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					titleList.add(field.getName().toUpperCase());
				}
			}
		}
		fieldHelper.caption = titleList.toArray(new String[titleList.size()]);
		fieldHelper.fieldName = fileNameList.isEmpty() ? keys : fileNameList.toArray(new String[fileNameList.size()]);
		return fieldHelper;
	}
	
	
	public List<Object> getList(Class<?> c,FieldHelper fieldHelper,Page page) throws Exception{
		List<Object> dataList = new ArrayList<Object>();
		
		String fieldName[] = fieldHelper.fieldName;
		List list = page.getResult();
		for(int j=0;j<list.size();j++){
			List<String> valuelist = new ArrayList<String>();
			Object item=list.get(j);
			for(int k=0;k<fieldName.length;k++){
				String value = Feflect.getFieldValue(item,fieldName[k]);
				valuelist.add(value); 
			}
			dataList.add(valuelist);
		}
		return dataList;
	} 
	
	public static class FieldHelper{
		private String caption[];//属性备注
		private String fieldName[];//属性名称
		private Field field[];//属性集合
	}
}

