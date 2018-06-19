package com.ccclubs.action.api.webservice.jwtgps;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ccclubs.model.CsCar;

public class CscarStore implements Serializable{
	
	private static final long serialVersionUID = -3163269408664035692L;

	private Date lastTime;
	
	private Map<Long, CsCar> carsMap;
	
	public void add(CsCar car){
		if(carsMap == null)carsMap = new HashMap<Long, CsCar>();
		if(!carsMap.containsKey(car.getCscId())){
			carsMap.put(car.getCscId(), car);
		}
	}
	
	public void put(CsCar car){
		if(carsMap == null)carsMap = new HashMap<Long, CsCar>();
		carsMap.put(car.getCscId(), car);
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Map<Long, CsCar> getCarsMap() {
		return carsMap;
	}

	public void setCarsMap(Map<Long, CsCar> carsMap) {
		this.carsMap = carsMap;
	}
	
	

}
