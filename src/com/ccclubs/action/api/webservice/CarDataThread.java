package com.ccclubs.action.api.webservice;

import java.net.URL;
import java.util.List;

import org.codehaus.xfire.client.Client;

import com.ccclubs.action.api.webservice.impl.CarStateService;
import com.ccclubs.action.api.webservice.struct.MOData;
import com.lazy3q.web.helper.$;

public class CarDataThread extends Thread{
	
	ICarStateService carStateService;
	
	public void getData(){
		try {
			Client client = new Client(new URL("http://218.205.165.105/webin.asmx?wsdl"));
			Object[] object = client.invoke("QueryCarData", new Object[]{"","",""});
			String packetData = (String) object[0];
			$.trace("获取到数据包",packetData.length());
			List<MOData> datas = CarStateService.parseMOData(packetData);
			carStateService.saveMOData(datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public void run() {
		while(true){
			if($.empty($.config("release.online"))){
				try {
					$.trace("非在线模式，放弃执行当前线程"+this.getClass().getName());
					Thread.sleep(5000l);
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			try {
				getData();//获取数据
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(60000l);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public ICarStateService getCarStateService() {
		return carStateService;
	}

	public void setCarStateService(ICarStateService carStateService) {
		this.carStateService = carStateService;
	}
}
