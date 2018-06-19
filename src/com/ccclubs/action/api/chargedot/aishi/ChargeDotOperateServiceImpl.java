package com.ccclubs.action.api.chargedot.aishi;

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

public class ChargeDotOperateServiceImpl implements ChargeDotOperateService{
	
	final	String	host 	= 		"42.96.191.30";
	final	int		port 	= 		8002;
	
	public ChargeDotOperateServiceImpl(){
		
	}
	
	public List<Result> send(Queue<Command> commands, BlockTnterruptor interruptor){
		final DefaultSocketClient client = new DefaultSocketClient(host, port);
		try{
			SocketReceiveHandler handler = new DefaultSocketReceiveHandler();
			handler.setCommands(commands);
			
			DataChannel channel = new DefaultDataChannel(client);
			channel.addBlockTnterruptor(interruptor);
			
			client.setReadHandler(handler);
			client.setDataChannel(channel);
			client.init();
			client.send(commands.poll());
			
			List<Result> results = client.receive();
			for(Result r : results){
				System.out.println(r.getText()+":\t"+r.getResult());
			}
			
			return results;
			
		}catch(Exception e){
			if(e instanceof SocketTimeoutException){
				
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
		return null;
	}
	
	@Override
	public Result login(String cid, String password, String user)
			throws Exception {
		
		CommandUtil cmd = new CommandUtil();
		Command login = new Command(RespType.LOGIN, cmd.LoginCmd(cid, password));
		Queue<Command> commands = new LinkedList<Command>();  
		commands.add(login);
		
		BlockTnterruptor loginInterruptor = new BlockTnterruptor() {
			@Override
			public Type interrupt(String all, String current) {
				System.out.println(current);
				List<Result> result = CommandUtil.read(current);
				
				//先判断结束标识
				if(CommandUtil.isEnd(result, RespType.LOGIN)){
					return Type.END;
				}
				
				//再判断终端标识
				//TODO
				
				return Type.BLOCK;
				
			}
		};
		
		List<Result> result = send(commands, loginInterruptor);
		if(result!=null){
			for(Result r : result){
				if(r.getText().equals(CommandUtil.LOGIN_SUCCESS_TEXT)){
					return r;
				}
			}
		}
		
		return null;
	}

	@Override
	public Result timing(String cid, String password, String user, String did) {
		CommandUtil cmd = new CommandUtil();
		
		Queue<Command> commands = new LinkedList<Command>();  
		commands.add(new Command(RespType.LOGIN, cmd.LoginCmd(cid, password)));
		commands.add(new Command(RespType.JOIN, cmd.JoinCmd(did, cid, user)));
		commands.add(new Command(RespType.TIMING, cmd.TimingCmd()));
		
		BlockTnterruptor loginInterruptor = new BlockTnterruptor() {
			@Override
			public Type interrupt(String all, String current) {
				System.out.println(current);
				List<Result> result = CommandUtil.read(current);
				
				//先判断结束标识
				if(CommandUtil.isEnd(result, RespType.TIMING)){
					return Type.END;
				}
				
				//再判断中断标识
				if(CommandUtil.isEnd(result, RespType.LOGIN)){
					return Type.INTERRUPT;
				}
				if(CommandUtil.isEnd(result, RespType.JOIN)){
					return Type.INTERRUPT;
				}
				
				return Type.BLOCK;
				
			}
		};
		
		List<Result> result = send(commands, loginInterruptor);
		if(result!=null){
			for(Result r : result){
				if(r.getCmdType() == RespType.TIMING){
					return r;
				}
			}
		}
		return null;
	}

	@Override
	public Result cancelTiming(String cid, String password, String user, String did) {
		return null;
	}

}
