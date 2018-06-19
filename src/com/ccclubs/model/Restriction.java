package com.ccclubs.model;

import java.util.Date;

public class Restriction {

	Date start;
	Date finish;
	
	/**
	 * @param start
	 * @param finish
	 */
	public Restriction(Date start, Date finish) {
		super();
		this.start = start;
		this.finish = finish;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getFinish() {
		return finish;
	}
	public void setFinish(Date finish) {
		this.finish = finish;
	}
	
}
