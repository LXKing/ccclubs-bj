package com.ccclubs.action.api.chargedot;

import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ccclubs.action.api.chargedot.aishi.bio.BlockTnterruptor;
import com.ccclubs.action.api.chargedot.aishi.bio.Command;
import com.ccclubs.action.api.chargedot.aishi.bio.CommandUtil;
import com.ccclubs.action.api.chargedot.aishi.bio.DataChannel;
import com.ccclubs.action.api.chargedot.aishi.bio.DefaultDataChannel;
import com.ccclubs.action.api.chargedot.aishi.bio.DefaultSocketClient;
import com.ccclubs.action.api.chargedot.aishi.bio.DefaultSocketReceiveHandler;
import com.ccclubs.action.api.chargedot.aishi.bio.RespType;
import com.ccclubs.action.api.chargedot.aishi.bio.Result;
import com.ccclubs.action.api.chargedot.aishi.bio.SocketReceiveHandler;
import com.ccclubs.action.app.official.util.ApiBase.Type;

public class BioSocketTest {
	
	public static void exec(String did){
		long start = System.currentTimeMillis();
		System.out.println("begin ================================" +start);
		
		String cid = "cust0001";
		String password = "123456";
		String user = "qinguojun";
		
		final DefaultSocketClient client = new DefaultSocketClient("42.96.191.30", 8002);
		try{
			CommandUtil cmd = new CommandUtil();
			String loginCmd = cmd.LoginCmd(cid, password);
			String joinCmd = cmd.JoinCmd(did, cid, user);
			String timingCmd = cmd.TimingCmd(); 
			
			Queue<Command> command = new LinkedList<Command>();  
			command.add(new Command(RespType.LOGIN, loginCmd));
			command.add(new Command(RespType.JOIN, joinCmd));
			command.add(new Command(RespType.TIMING, timingCmd));
			
			SocketReceiveHandler handler = new DefaultSocketReceiveHandler();
			handler.setCommands(command);
			client.setReadHandler(handler);
			DataChannel channel = new DefaultDataChannel(client);
			
			/**
			 * 登陆-》绑定-》充电
			 */
			channel.addBlockTnterruptor(new BlockTnterruptor() {
				
				@Override
				public Type interrupt(String all, String current) {
					System.out.println(current);
					List<Result> result = CommandUtil.read(current);
					if(CommandUtil.isEnd(result, RespType.TIMING)){
						return Type.END;
					}else{
						for(Result r : result){
							if(r.getCmdType() == RespType.LOGIN){
								return Type.INTERRUPT;
							}
							if(r.getCmdType() == RespType.JOIN){
								return Type.INTERRUPT;
							}
						}
					}
					return Type.BLOCK;
				}
				
			});
			
			/**
			 * 登陆-》绑定
			 */
			channel.addBlockTnterruptor(new BlockTnterruptor() {
				
				@Override
				public Type interrupt(String all, String current) {
					System.out.println(current);
					List<Result> result = CommandUtil.read(current);
					if(CommandUtil.isEnd(result, RespType.JOIN)){
						return Type.END;
					}else{
						for(Result r : result){
							if(r.getCmdType() == RespType.LOGIN){
								return Type.INTERRUPT;
							}
						}
					}
					return Type.BLOCK;
				}
				
			});
			
			client.setDataChannel(channel);
			client.init();
			client.send(command.poll());
			List<Result> results = client.receive();
			for(Result r : results){
				System.out.println(r.getText()+":\t"+r.getResult());
			}
			
			long end = System.currentTimeMillis();
			System.out.println("end ================================" +end);
			System.out.println(end-start);
		}catch(Exception e){
			if(e instanceof SocketTimeoutException){
				System.out.println("dddd");
			}else{
				e.printStackTrace();
			}
		}finally{
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SocketTimeoutException {
//		exec("ASEV01A11404P0000056");	//20301
		exec("TEST01A11404P0000104");	
	}
}
