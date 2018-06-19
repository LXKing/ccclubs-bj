package com.ccclubs.action.app.official.event;

import com.ccclubs.util.mq.ICarStatus;

public interface Event {
	
	public void trace(ICarStatus carStatus);
	
	public void addListener();
}
