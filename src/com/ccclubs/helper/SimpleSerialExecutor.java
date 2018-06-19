package com.ccclubs.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * 线程方法串行执行器
 * 
 * @author joel
 *
 */
public class SimpleSerialExecutor {
	
	private String entryKey;
	
	private volatile boolean lock;
	
	/**
	 * 不同的控制块的entryKey不可重复
	 * @param entryKey
	 */
	public SimpleSerialExecutor(String entryKey){
		this.entryKey = entryKey;
	}
	
	private static Map<String, AtomicInteger> localStore = new ConcurrentHashMap<String, AtomicInteger>(); 
	
	/**
	 * 入场
	 */
	public synchronized boolean entry(){
		if(lock)return false;
		if(entryKey == null || entryKey.equals(""))return false;
		AtomicInteger count = localStore.get(entryKey);
		if(count == null){
			count = new AtomicInteger();
			count.set(1);
			localStore.put(entryKey, count);
			lock = true;
			return true;
		}else{
			if(count.get()>=1){
				lock = false;
				return false;
			}else{
				count.incrementAndGet();
				localStore.put(entryKey, count);
				lock = true;
				return true;
			}
		}
	}
	
	/**
	 * 释放
	 * @param key
	 */
	public void release(){
		if(!lock)return ;
		if(entryKey == null || entryKey.equals(""))return ;
		AtomicInteger count = localStore.get(entryKey);
		if(count != null){
			if(count.get() <=1) {
				localStore.remove(entryKey);
			}else{
				count.decrementAndGet();
				localStore.put(entryKey, count);
			}
			lock = false;
		}
	}
	
	public static void main(String[] args) {
		
	}
}
