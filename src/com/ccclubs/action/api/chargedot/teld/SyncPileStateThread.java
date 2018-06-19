package com.ccclubs.action.api.chargedot.teld;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.ccclubs.model.CsChargePile;
import com.ccclubs.service.admin.ICsChargePileService;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 特来电电桩状态同步
 * 
 * @author zhangjian
 *
 */
public class SyncPileStateThread extends Thread{
	
	final static ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
	Lazy3qDaoSupport dao = $.getDao("ccclubs_elec_history");
	static Map<String, Boolean> tabs = new HashMap<String, Boolean>();
	
	public void run() {
		
		try{
			init();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		while(true){
			try {
				getData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(60000 * 2);	//两分钟去拉去一次数据
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsChargePile.F.cscpProvider, SyncPile.provider);
		List<CsChargePile> pileList = csChargePileService.getCsChargePileList(params, -1);
		
		if(!CollectionUtils.isEmpty(pileList)){
			for(CsChargePile pile : pileList){
				SyncPile.piles.put(pile.getCsCpCode(), pile);
				
				createTable(pile.getCsCpId().toString());
			}
		}
	}
	
	public void createTable(String pileId){
		dao.execute("CREATE table if not exists cs_charge_pile_"+pileId.toLowerCase()+" LIKE cs_charge_pile");
		tabs.put(pileId.toLowerCase(),true);
	}
	
	/**
	 * 获取和保存数据
	 */
	static String strKeys = "cscp_id,cscp_station,cscp_code,cscp_name,cscp_type,cscp_state,cscp_voltage,cscp_galvanic,cscp_power,cscp_provider,cscp_data,cscp_add_time,cscp_update_time";
	static String keys[]=strKeys.split(",");
	static String sqlTemplate = "INSERT INTO cs_charge_pile_{number}("+strKeys+") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	public void getData(){
		
		Map<String, CsChargePile> piles = SyncPile.piles;
		for(CsChargePile pileEntry : piles.values()){
			TeldApiResp resp = TeldApi.getPileDetail(pileEntry.getCsCpCode());
			if(resp.success()){
				Object[] sqlParams=new Object[keys.length];
				try{
					Map<String, Object> map = resp.getResultValue();
					CsChargePile pile = new CsChargePile();
					pile.setCsCpId(pileEntry.getCsCpId());
					pile.setCsCpStation(pileEntry.getCsCpStation());
					pile.setCsCpCode(map.get("pileCode").toString());
					pile.setCsCpName(map.get("pileName").toString());
					pile.setCsCpType(map.get("pileType").toString());
					pile.setCsCpState(map.get("pileState").toString());
					pile.setCsCpVoltage(map.get("voltage").toString());
					pile.setCsCpGalvanic(map.get("galvanic").toString());
					pile.setCsCpPower(map.get("power").toString());
					pile.setCsCpProvider(SyncPile.provider);
					pile.setCsCpUpdateTime(new Date());
					pile.update();
					
					pile.setCsCpAddTime(new Date());
					
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("cscp_id", null);
					params.put("cscp_station", pile.getCsCpStation());
					params.put("cscp_code", pileEntry.getCsCpCode());
					params.put("cscp_name", pile.getCsCpName());
					params.put("cscp_type", pile.getCsCpType());
					params.put("cscp_state", pile.getCsCpState());
					params.put("cscp_voltage", pile.getCsCpVoltage());
					params.put("cscp_galvanic", pile.getCsCpGalvanic());
					params.put("cscp_power", pile.getCsCpPower());
					params.put("cscp_provider", pile.getCsCpProvider());
					params.put("cscp_data", pile.getCsCpData());
					params.put("cscp_add_time", pile.getCsCpAddTime());
					params.put("cscp_update_time", pile.getCsCpUpdateTime());
					
					for(int i=0;i<keys.length;i++){
						String key=keys[i];
						sqlParams[i] = params.get(key);
					}
					
					if(tabs.get(pileEntry.getCsCpId().toString())==null){
						createTable(pileEntry.getCsCpId().toString());
					}
					dao.execute(sqlTemplate.replace("{number}", pileEntry.getCsCpId().toString()), sqlParams);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * "null"字符处理
	 * @param data
	 * @return
	 */
	public String ignoreNull(String data){
		if(data !=null && data.equals("null")){
			return null;
		}
		return data;
	}

}


