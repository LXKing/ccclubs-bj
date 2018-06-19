package com.ccclubs.action.api.webservice.jwtgps;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.ccclubs.action.api.webservice.JwtGPSReportTask;
import com.ccclubs.util.ByteUtil;

/**
 * 
 * 北交委车辆GPS数据对接
 * 
 * @author joel
 *
 */
public class JwtGPSClient extends Thread{
	
	JwtGPSReportTask task;
	
	public JwtGPSClient(){
		
	}
	
	public JwtGPSClient(JwtGPSReportTask task){
		this.task = task;
	}
	
	private static final Logger logger = Logger.getLogger(JwtGPSClient.class);
//	public static String HOST = "103.44.145.243";
//	public static int PORT = 10871;
	public static String HOST = "219.232.194.133";
	public static int PORT = 10000;

    public static Channel channel = null;
    
    /**
     * 初始化
     */
    public void init(){
    	getChannel();
    }
    
    @Override
	public void run() {
		super.run();
		init();
	}

	public void getChannel(){
		ChannelFactory factory = new NioClientSocketChannelFactory(  
				Executors.newCachedThreadPool(),  
				Executors.newCachedThreadPool());  
		ClientBootstrap bootstrap = new ClientBootstrap(factory);  
		 try { 
		        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {  
		            public ChannelPipeline getPipeline() {  
		                ChannelPipeline pipeline = Channels.pipeline();  
//		                pipeline.addLast("encode",new StringEncoder());  
//		                pipeline.addLast("decode",new StringDecoder());  
		                pipeline.addLast("handler",new JwtGPSMessageHandler(task));  
		                return pipeline;  
		            }  
		        });  
		        bootstrap.setOption("tcpNoDelay" , true);  
		        bootstrap.setOption("keepAlive", true);  
		        
		        // Start the connection attempt.  
		        ChannelFuture future = bootstrap.connect(new InetSocketAddress(HOST, PORT));  
		        // Wait until the connection is closed or the connection attempt fails.  
		        channel = future.getChannel();
		        channel.getCloseFuture().awaitUninterruptibly();
//		        bootstrap.releaseExternalResources();  
		        // Shut down thread pools to exit.  
			}finally{
				task.disConnectCallback();
				logger.warn("===============================================连接异常===============================================");
				bootstrap.shutdown();
				Thread.currentThread().interrupted();
			} 
    }

    public static void sendMsg(byte[] msg) {
    	try{
	        if (channel!= null) {
	        	channel.write(ChannelBuffers.wrappedBuffer(msg)); 
	        } else {
	            logger.warn("消息发送失败,连接尚未建立!");
	        }
    	}catch(Exception e){
    		
    	}
    }
    
    public static String decToHex(int dec) {
        String hex = "";
        while(dec != 0) {
            String h = Integer.toString(dec & 0xff, 16);
            if((h.length() & 0x01) == 1)
                h = '0' + h;
            hex = hex + h;
            dec = dec >> 8;
        }
        return hex;
    }
    
    public static void main(String[] args) {
    	Integer a = Integer.reverseBytes(120262554);
    	System.out.println(a);
    	 ByteBuffer buffer =  ByteBuffer.wrap(ByteUtil.getBytes(120262554, false));
    	 
    	 int sumTemp = buffer.order(ByteOrder.LITTLE_ENDIAN).getInt();
    	 System.out.println(sumTemp);
    	System.out.println(decToHex(120262554));
    	int lat = 120150868;
    	Double v = lat * Math.pow(10, -6);
    	System.out.println(v.doubleValue());
    }
}
