package com.ccclubs.action.api.chargedot.aishi.bio;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.ccclubs.action.api.chargedot.aishi.bio.BlockTnterruptor.Type;

public class DefaultSocketReceiveHandler implements SocketReceiveHandler{

	Queue<Command> commands = null;
	
	@Override
	public SocketReceiveHandler setCommands(Queue<Command> commands) {
		this.commands = commands;
		return this;
	}

	@Override
	public List<Result> read(SocketWrapper socket, String message, Type type) throws Exception {
		
		List<Result> result = new ArrayList<Result>();
		result.addAll(CommandUtil.read(message));
		
		if(type == Type.END){
			return result;
		}
		
		Command input = commands.poll();
		if(input == null){
			return result;
		}else{
			socket.send(input);
			result.addAll(socket.receive());
		}
		return result;
	}
	
}
