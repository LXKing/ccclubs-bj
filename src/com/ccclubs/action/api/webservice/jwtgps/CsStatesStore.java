package com.ccclubs.action.api.webservice.jwtgps;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ccclubs.model.CsState;

public class CsStatesStore implements Serializable{
	
	private static final long serialVersionUID = 6556871465061102591L;

	private Date lastTime;

	private Map<String, CsState> statesMap;
	
	public void add(CsState state){
		if(statesMap == null)statesMap = new HashMap<String, CsState>();
		if(!statesMap.containsKey(state.getCssNumber())){
			statesMap.put(state.getCssNumber(), state);
		}
	}
	
	public void put(CsState state){
		if(statesMap == null)statesMap = new HashMap<String, CsState>();
		statesMap.put(state.getCssNumber(), state);
	}
	
	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Map<String, CsState> getStatesMap() {
		return statesMap;
	}

	public void setStatesMap(Map<String, CsState> statesMap) {
		this.statesMap = statesMap;
	}

	
}
