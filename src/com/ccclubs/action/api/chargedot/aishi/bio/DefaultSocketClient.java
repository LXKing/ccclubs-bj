package com.ccclubs.action.api.chargedot.aishi.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * BIO模型的socket客户端
 * 
 * @author zhangjian
 *
 */
public class DefaultSocketClient extends Thread implements SocketWrapper{

	/**
	 * 超时时间默认15秒钟
	 */
	int timeout = 6000 * 50;
	
	Socket socket = null;
	DataChannel channel = null;
	Lock lock = new ReentrantLock();
	SocketReceiveHandler readHandler = null;
	InetSocketAddress addr = null;
	public volatile boolean initialized = false;
	public Command command = new Command();
	
	public DefaultSocketClient(){
		
	}
	

	public DefaultSocketClient(String hostname, int port){
		this.addr = new InetSocketAddress(hostname, port);
	}
	
	public DefaultSocketClient(String hostname, int port, SocketReceiveHandler readHandler){
		this(hostname, port);
		this.readHandler = readHandler;
	}
	
	@Override
	public SocketWrapper setTimeout(int time) {
		this.timeout = time;
		return this;
	}
	
	@Override
	public Command getCommand() {
		return command;
	}

	@Override
	public SocketWrapper setAddr(InetSocketAddress addr) {
		this.addr = addr;
		return this;
	}

	@Override
	public SocketWrapper setReadHandler(SocketReceiveHandler readHandler) {
		this.readHandler = readHandler;
		return this;
	}
	
	@Override
	public SocketReceiveHandler getReadHandler() {
		return readHandler;
	}
	
	@Override
	public SocketWrapper setDataChannel(DataChannel channel) {
		this.channel = channel;
		return this;
	}

	/**
	 * 获取已连接的socket
	 */
	@Override
	public Socket getSocket() throws Exception{
		try {
			lock.lock();
			if(socket!=null){
				if(socket.isConnected()){
					return socket;
				}else{
					//如果socket是重连接，需要销毁流
					if(channel!=null){
						channel.destroy();
					}
					socket.connect(addr);
				}
			}else{
				socket = new Socket();
				socket.connect(addr);
				if (socket.isConnected()) {
					System.out.println("is connected");
				}
			}
			socket.setSoTimeout(timeout);
			if(!socket.isConnected()){
				throw new RuntimeException("getConnSocket failed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		return socket;
	}
	
	@Override
	public synchronized void init() throws Exception{
		if(!initialized){
			Socket socket = getSocket(); 
			if(socket.isConnected()){
				if(this.channel==null){
					channel = new DefaultDataChannel(this);
				}else{
					channel.init(this);
				}
			}
			initialized = true;
		}
	}
	
	@Override
	public void close() throws Exception{
		if(channel!=null){
			channel.destroy();
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 提供异步接口
	 */
	@Override
	public void run() {
		try{
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送数据
	 * @param message
	 * @throws Exception 
	 */
	@Override
	public DefaultSocketClient send(Command cmd) throws Exception {
		if(cmd!=null && cmd.getCmd().length()>0){
			command = cmd;
			channel.writeAndFlush(cmd.getCmd());
		}
		return this;
	}

	/**
	 * 读取
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<Result> receive() throws Exception{
		if(channel!=null){
			return channel.read();
		}
		return null;
	}

	@Override
	public List<Result> receive(boolean await) throws Exception {
		if(channel!=null){
			return channel.read(await);
		}
		return null;
	}
	
}
