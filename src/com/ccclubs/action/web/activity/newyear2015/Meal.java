package com.ccclubs.action.web.activity.newyear2015;

import com.ccclubs.model.CsPack;

/**
 * 套餐
 * 
 * @author joel
 *
 */
public class Meal {
	
	public Meal(){
		
	}
	
	public Meal(int count, int remain){
		this.count = count;
		this.remain = remain;
	}
	
	/**
	 * 总量
	 */
	private int count;	
	
	/**
	 * 余量
	 */
	private int remain;
	
	private CsPack pack;

	public int getCount() {
		return count;
	}

	public Meal setCount(int count) {
		this.count = count;
		return this;
	}

	public int getRemain() {
		return remain;
	}

	public Meal setRemain(int remain) {
		this.remain = remain;
		return this;
	}

	public CsPack getPack() {
		return pack;
	}

	public void setPack(CsPack pack) {
		this.pack = pack;
	}

}
