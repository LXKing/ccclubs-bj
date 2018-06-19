package com.ccclubs.helper.im4java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;

import com.lazy3q.web.helper.ConfigHelper;

public class IM4Editor {
	
	/** 
     * ImageMagick的路径 
     */
    public static String imageMagickPath = ConfigHelper.GetString("imageMagickPath");
    public static Boolean isWindows=false;
    static{
    	String os = System.getProperty("os.name"); 
    	if(os!=null && os.toLowerCase().indexOf("windows")>-1)
    		isWindows=true;
    }
    
  //加水印
	public static void watermark(String filename, String watermark_file,String newname) {		
		try {
			CompositeCmd composite = new CompositeCmd(); 
			IMOperation op = new IMOperation();
			op.gravity("southeast");
			op.addImage();
			op.addImage();
			op.addImage();
			composite.run(op, watermark_file, filename, newname);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}
	}
	
	
	/**
	 * 图片裁切
	 * @param filename
	 * @param cx
	 * @param cy
	 * @return
	 */
	public static Boolean cut(String filename,Integer left,Integer top,Integer width,Integer height,String newname) {
		try {
			 IMOperation op = new IMOperation(); 			 
			 op.addImage(filename);

			 /**
	         * width：裁剪的宽度
	         * height：裁剪的高度
	         * x：裁剪的横坐标
	         * y：裁剪的挫坐标
	         */
	         op.crop(width, height, left, top);
			 
			 op.addImage(newname);   
			 ConvertCmd convert = new ConvertCmd();
			 convert.setSearchPath(imageMagickPath); 
			 convert.run(op);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return false;
		}
		return true;
	}
    
	
	
	
	/**
	 * 限制图片最大高度
	 * @param filename
	 * @param cx
	 * @param cy
	 * @return
	 */
	public static Boolean limit(String filename,Integer cx,Integer cy,String newname) {
		try {
			Size size = size(filename);
			System.out.println("[源:"+size.getWidth()+","+size.getHeight()+"][目标:"+cx+","+cy+"]");
			if((size.getWidth().intValue() == cx.intValue() || cx.intValue()==0) 
					&& 
					(size.getHeight().intValue() == cy.intValue() || cy.intValue()==0)){
				System.out.println("放弃智能创建缩略图,直接做文件拷贝");
				
				java.io.File srcFile=new java.io.File(filename);
		   		java.io.File destFile=new java.io.File(newname);
		   		FileInputStream in=new FileInputStream(srcFile);
		   		FileOutputStream out=new FileOutputStream(destFile);
		   		byte[] bytes=new byte[1024];
		   		int c;
		   		while((c=in.read(bytes))!=-1)
		   			out.write(bytes,0,c);
		   		in.close();
		   		out.close();
				
				return true;
			}
			
			 IMOperation op = new IMOperation(); 			 
			 op.addImage(filename);
			 op.resize(cx, cy,">");
			 op.addImage(newname);   
			 ConvertCmd convert = new ConvertCmd();
			 convert.setSearchPath(imageMagickPath); 
			 convert.run(op);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return false;
		}
		return true;
	}
    
	
	/**
	 * 智能创建缩略图
	 * @param filename
	 * @param cx
	 * @param cy
	 * @return
	 */
	public static Boolean thumb(String filename,Integer cx,Integer cy,String newname) {
		try {
			Size size = size(filename);
			System.out.println("[源:"+size.getWidth()+","+size.getHeight()+"][目标:"+cx+","+cy+"]");
			if((size.getWidth().intValue() == cx.intValue() || cx.intValue()==0) 
					&& 
					(size.getHeight().intValue() == cy.intValue() || cy.intValue()==0)){
				System.out.println("放弃智能创建缩略图,直接做文件拷贝");
				
				java.io.File srcFile=new java.io.File(filename);
		   		java.io.File destFile=new java.io.File(newname);
		   		FileInputStream in=new FileInputStream(srcFile);
		   		FileOutputStream out=new FileOutputStream(destFile);
		   		byte[] bytes=new byte[1024];
		   		int c;
		   		while((c=in.read(bytes))!=-1)
		   			out.write(bytes,0,c);
		   		in.close();
		   		out.close();
				
				return true;
			}
			
			 IMOperation op = new IMOperation(); 			 
			 op.addImage(filename);
			 op.resize(cx, cy, '^').gravity("center").extent(cx, cy);
			 op.addImage(newname);   
			 ConvertCmd convert = new ConvertCmd();
			 convert.setSearchPath(imageMagickPath); 
			 convert.run(op);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return false;
		}
		return true;
	}
	
	
	public static void convert(String filename,String newname,Double quality){
		try {
			 IMOperation op = new IMOperation();
			 op.addImage(filename);
			 if(quality==null || quality<0.1 || quality>100)
				 quality=100d;
			 op.quality(quality);
			 op.colorspace("RGB");
			 op.addImage(newname);  
			 ConvertCmd convert = new ConvertCmd();
			 if(isWindows)
				 convert.setSearchPath(imageMagickPath);
			 convert.run(op);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}
	}
	
	
	public static Size size(String filename){
		try {
			 IMOperation op = new IMOperation();
			 op.format("%[fx:w],%[fx:h]");
			 op.addImage(filename);	
			 IdentifyCmd cmd = new IdentifyCmd();
			 ArrayListOutputConsumer aloc = new ArrayListOutputConsumer();   
			 cmd.setOutputConsumer(aloc);
			 if(isWindows)
				 cmd.setSearchPath(imageMagickPath);
			 cmd.run(op);
			 ArrayList<String> output = aloc.getOutput();
			 String swh = output.get(0);
			 String[] array = swh.split(",");
			 Size size=new Size();
			 size.setWidth(Integer.parseInt(array[0]));
			 size.setHeight(Integer.parseInt(array[1]));
			 return size;
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return null;
		}
	}
	
	public static void copy(String filename,String newname){
		try {
			 IMOperation op = new IMOperation();
			 op.addImage(filename);
			 op.clone(newname);
			 op.addImage(newname);  
			 ConvertCmd convert = new ConvertCmd();
			 if(isWindows)
				 convert.setSearchPath(imageMagickPath);
			 convert.run(op);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
		}		
	}
		
	public static void main(String...strings)
	{
		//IM4Editor.copy("f:/b.jpg", "f:/2345678.gif");
		//System.out.println(size("f:/b.jpg").getWidth());
		//Date start=new Date();
		//IM4Editor.limit("f:/b.jpg", 200, -1, "f:/bbbbbbbbbbbbbb.png");
		IM4Editor.thumb("f:/b.jpg", 64,64,"f:/2345678.gif");
	}

}
