package com.ccclubs.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ccclubs.action.api.webservice.jwtgps.CscarStore;
import com.ccclubs.model.CsCar;

public class FileUtil {
	
	public void saveFile(String fileName, String data) {

       SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

       Date date = new Date();

       String formateDate = format.format(date);

       String remotefilename = formateDate + ".txt";

       try {

           OutputStream os = new FileOutputStream(fileName);
           
           os.write(data.getBytes());

           os.close();

       } catch (IOException ex) {

        }
    }


	public String readFile(String remoteFile) {

	       String log = "";

	       try {

	           File file = new File(remoteFile);

	           FileInputStream is = new FileInputStream(remoteFile);

	           int size = (int) file.length();

	           byte[] bytes = getBytes(is,size);

	           String content = new String(bytes, "UTF-8");

	           log = content;

	           is.close();

	       } catch (IOException ex) {

	        }

	       return log;

	    }

	    private byte[] getBytes(InputStream inputStream,int size) {

	       byte[] bytes = new byte[size];

	       try {

	           int readBytes = inputStream.read(bytes);

	           return bytes;

	       } catch (Exception e) {

	           e.printStackTrace();

	       }

	       return null;

	    }
	    
	    public static void writeObjectToFile(Object obj, String filePath)
	    {
	        File file =new File(filePath);
	        if(!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        FileOutputStream out;
	        try {
	            out = new FileOutputStream(file);
	            ObjectOutputStream objOut=new ObjectOutputStream(out);
	            objOut.writeObject(obj);
	            objOut.flush();
	            objOut.close();
	            System.out.println("write object success!"+filePath);
	        } catch (IOException e) {
	            System.out.println("write object failed"+filePath);
	            e.printStackTrace();
	        }
	    }
	    
	    public static Object readObjectFromFile(String filePath)
	    {
	        Object temp=null;
	        File file =new File(filePath);
	        FileInputStream in;
	        try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn=new ObjectInputStream(in);
	            temp=objIn.readObject();
	            objIn.close();
	            System.out.println("read object success!"+filePath);
	        } catch (IOException e) {
	            System.out.println("read object failed"+filePath);
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return temp;
	    }
	    
	    
	    public static void main(String[] args) {
	    	try {
				String path = FileUtil.class.getResource("/").toURI().getPath() + "dat/cscarstore.dat";
				System.out.println(path);
				
				CscarStore cs = new CscarStore();
				cs.setLastTime(new Date());
				
				CsCar ca = new CsCar();
				ca.setCscId(1001L);
				ca.setCscNumber("测试的好");
				
				cs.add(ca);
				
				FileUtil.writeObjectToFile(cs, path);
				
				Object o = FileUtil.readObjectFromFile(path);
				System.out.println(o);
			} catch (URISyntaxException e) {
				
			}
		}
}
