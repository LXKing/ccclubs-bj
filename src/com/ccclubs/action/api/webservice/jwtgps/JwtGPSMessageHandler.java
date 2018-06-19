package com.ccclubs.action.api.webservice.jwtgps;

import java.nio.channels.ClosedChannelException;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.ccclubs.action.api.webservice.JwtGPSReportTask;


public class JwtGPSMessageHandler extends SimpleChannelUpstreamHandler  {
	
	JwtGPSReportTask task;
	
	public JwtGPSMessageHandler(){
		
	}
	
	public JwtGPSMessageHandler(JwtGPSReportTask task){
		this.task = task;
	}
	
	 @Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		super.channelDisconnected(ctx, e);
	}

	@Override  
	    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {  
		  System.out.println("========================================已经与Server建立连接。。。。==============================");  
          task.connectedCallback();
////      e.getChannel().write("5B000000460000000010010000046101020F0000000000000003F36A777467707300003132302E32362E3130392E32343800000000000000000000000000000000000022C3563E5D");
	    }  
	  
	    @Override  
	    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {  
	        //e.getChannel().close();  
	        if (e.getMessage() instanceof String) {  
                String message = (String) e.getMessage();  
                System.out.println(message);  
            }  else if(e.getMessage() instanceof BigEndianHeapChannelBuffer){
            	 BigEndianHeapChannelBuffer buffer = (BigEndianHeapChannelBuffer)e.getMessage();
            	 byte[] b = buffer.array();
            	 System.out.println(b);
            	 task.messageCallback();
            } else{
            	System.out.println(e.getMessage());
            }
	    } 
	      
	    @Override  
	    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {  
	    	if(e.getCause() instanceof ClosedChannelException){
	    		task.disConnectCallback();
	    		System.out.println("========================================已经与Server断开连接。。。。==============================");
	    	}
	        e.getCause().printStackTrace();  
	        //e.getChannel().close();  
	    } 
}
