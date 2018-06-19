package com.ccclubs.action.api.chargedot.teld;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ccclubs.model.CsChargePile;
import com.ccclubs.model.CsChargeStation;
import com.ccclubs.service.admin.ICsChargePileService;
import com.ccclubs.service.admin.ICsChargeStationService;
import com.lazy3q.web.helper.$;

/**
 * 同步电桩数据
 *
 */
public class SyncPile implements Job{
	
	final ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
	final ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
	final static String provider = "teld";
	public static Map<String, CsChargeStation> stations = new ConcurrentHashMap<String, CsChargeStation>(); 
	public static Map<String, CsChargePile> piles = new ConcurrentHashMap<String, CsChargePile>(); 
	
	final static Logger log = Logger.getRootLogger();
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TeldApiResp resp;
		try{
			resp = TeldApi.queryStas();
			if(resp.success()){
				Map<String, Object> re = resp.getResultValue();
				List<?> l = (List<?>)re.get("staList");
				for(int i=0; i<l.size(); i++){
					
					try{
						Map map = (Map)l.get(i);
						
						CsChargeStation station = csChargeStationService.getCsChargeStation($.add(CsChargeStation.F.cscsStaCode, map.get("staCode").toString())
								.add(CsChargeStation.F.cscsProvider, provider));
						if(station == null)
							station = new CsChargeStation();
						
						station.setCscsStaCode(map.get("staCode").toString());
						station.setCscsStaName(map.get("staName").toString());
						station.setCscsType(map.get("staType").toString());
						station.setCscsOpState(map.get("staOpstate").toString());
						station.setCscsProvince(map.get("province").toString());
						station.setCscsCity(map.get("city").toString());
						station.setCscsArea(map.get("region").toString());
						station.setCscsAddress(map.get("staAddress").toString());
						station.setCscsLng(map.get("lng").toString());
						station.setCscsLat(map.get("lat").toString());
						station.setCscsPrice(map.get("price").toString());
						station.setCscsProvider(provider);
						station.setCscsAddTime(new Date());
						station.setCscsUpdateTime(new Date());
						
						Map<String, Object> otherData = new HashMap<String, Object>();
						otherData.put("acNum", map.get("acNum"));
						otherData.put("dcNum", map.get("dcNum"));
						otherData.put("acableNum", map.get("acableNum"));
						otherData.put("dcableNum", map.get("dcableNum"));
						station.setCscsData($.json(otherData));
						
						if(station.getCscsId() == null){
							station.save();
						}else{
							station.update();
						}
						stations.put(station.getCscsStaCode(), station);
					}catch(Exception e){
						log.error(e);
						e.printStackTrace();
					}
				}
				
				syncPile();
			}
			
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 同步电桩 
	 */
	public void syncPile(){
		if(stations.isEmpty())return;
		for(Map.Entry<String, CsChargeStation> entry : stations.entrySet()){
			TeldApiResp resp;
			try{
				resp = TeldApi.getStaDetail(entry.getKey());
				if(resp.success()){
					Map<String, Object> re = resp.getResultValue();
					List<?> l = (List<?>)re.get("pileList");
					for(int i=0; i<l.size(); i++){
						
						try{
							Map map = (Map)l.get(i);
							CsChargePile pile = csChargePileService.getCsChargePile($.add(CsChargePile.F.cscpCode, map.get("pileCode").toString())
									.add(CsChargePile.F.cscpProvider, provider));
							if(pile == null)
								pile = new CsChargePile();
							
							pile.setCsCpStation(entry.getValue().getCscsId());
							pile.setCsCpCode(map.get("pileCode").toString());
							pile.setCsCpName(map.get("pileName").toString());
							pile.setCsCpType(map.get("pileType").toString());
							pile.setCsCpState(map.get("pileState").toString());
							pile.setCsCpVoltage(map.get("voltage").toString());
							pile.setCsCpGalvanic(map.get("galvanic").toString());
							pile.setCsCpPower(map.get("power").toString());
							pile.setCsCpProvider(provider);
							pile.setCsCpAddTime(new Date());
							pile.setCsCpUpdateTime(new Date());
							
							if(pile.getCsCpId()==null){
								pile.save();
							}else{
								pile.update();
							}
							
							piles.put(pile.getCsCpCode(), pile);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
					stations.remove(entry.getKey());
				}
			}catch(Exception e){
				log.error(e);
				e.printStackTrace();
			}
		}
	}
}
