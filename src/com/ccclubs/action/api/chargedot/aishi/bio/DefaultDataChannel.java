package com.ccclubs.action.api.chargedot.aishi.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.ccclubs.action.api.chargedot.aishi.bio.BlockTnterruptor.Type;

/**
 * 数据管道
 * @author zhangjian
 *
 */
public class DefaultDataChannel implements DataChannel {
	
	private PrintWriter writer = null;
	private OutputStream outStream = null;
	
	private InputStream inStream = null;
//	private DataInputStream dataInputStream = null;
	SocketWrapper socketWrapper = null;
	
	public volatile boolean initialized = false;

	public List<BlockTnterruptor>	interruptors;
	
	public DefaultDataChannel(SocketWrapper socketWrapper) throws Exception{
		this.init(socketWrapper);
	}
	
	@Override
	public void addBlockTnterruptor(BlockTnterruptor interruptor) {
		if(interruptors == null) interruptors = new ArrayList<BlockTnterruptor>();
		interruptors.add(interruptor);
	}

	@Override
	public void writeAndFlush(String message) throws Exception {
		writer.print(message);
		writer.flush();
	}

	@Override
	public void write(String message) {
		writer.print(message);
	}

	@Override
	public void destroy() {
		try {
			if(outStream!=null){
				outStream.close();
			}
			if(writer!=null){
				writer.close();
			}
			if(inStream!=null){
				inStream.close();
			}
//			if(dataInputStream!=null){
//				dataInputStream.close();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Result> read() throws Exception{
		return read(false);
	}

	@Override
	public List<Result> read(boolean await) throws Exception {
		int bufferSize = 10240;
		byte[] buffer = new byte[bufferSize]; // 缓冲区的大小
		StringBuilder sb = new StringBuilder();
		int receiveBytes = 0;
		
//			while((receiveBytes = dataInputStream.read(buffer))!=-1){
//				String reciveData = new String(buffer,0, receiveBytes, "GB2312");
//				sb.append(reciveData);
//			}
		
		 Type r = null; 
		 String clientMessage = "";  
         while((receiveBytes=inStream.read(buffer))!=-1) {  
             clientMessage = new String(buffer, 0, receiveBytes);
             sb.append(clientMessage);
             
             if(!await){
            	 boolean interrupted = false;
            	 
	             if(interruptors!=null){
	            	 for(BlockTnterruptor interrupt : interruptors){
	            		 r = interrupt.interrupt(sb.toString(), clientMessage);
	            		 if(r != Type.BLOCK){
	            			 interrupted = true;
	            			 break;
	            		 }
	            	 }
	             }
	             
	             if(interrupted){
	            	 break;
	             }
	             
//	             //这里使用较大的缓冲，来判断是否当次是否已经读取完毕
//	             if(receiveBytes < bufferSize){
//	            	 break;
//	             }
             }
         }
         
		/**
		 * C++传递过来的中文字，需要转化一下。C++默认使用GBK。
		 * GB2312是GBK的子集，只有简体中文。因为数据库用GB2312，所以这里直接转为GB2312
		 **/
		if(socketWrapper.getReadHandler()!=null){
			return socketWrapper.getReadHandler().read(socketWrapper, sb.toString(), r);
		}else{
			return CommandUtil.read(sb.toString());
		}
	}

	@Override
	public synchronized void init(SocketWrapper socketWrapper) throws Exception {
		if(!this.initialized){
			this.socketWrapper = socketWrapper;
			Socket socket = socketWrapper.getSocket();
			try {
				outStream = socket.getOutputStream();
				writer = new PrintWriter(outStream);
				
				inStream = socket.getInputStream();
//				dataInputStream = new DataInputStream(inStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
			initialized = true;
		}
	}

}
