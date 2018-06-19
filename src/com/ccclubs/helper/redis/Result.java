package com.ccclubs.helper.redis;

public class Result<T> {
	
	public Result(){
		
	}
	
	public Result(T value){
		this.value = value;
	}
	
	private T value;
	
	public T getValue(){
		return value;
	}
	
	public T getValue(T temp){
		if(value == null){
			return temp;
		}
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
