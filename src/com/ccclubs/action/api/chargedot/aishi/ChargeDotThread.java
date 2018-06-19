package com.ccclubs.action.api.chargedot.aishi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.util.CollectionUtils;

import com.ccclubs.helper.CacheStoreHelper;
import com.ccclubs.helper.redis.DBIndex;
import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.service.admin.ICsElecHistoryService;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

/**
 * 埃士工业电桩状态数据获取
 * 
 * @author zhangjian
 *
 */

@Deprecated
public class ChargeDotThread extends Thread{
	
	final static String CHARSET = "utf-8";
	
	final static String PATH = "http://42.96.191.30:8080/api/";
	
	ICsElecHistoryService csElecHistoryService;
	
	ICsPowerPileService csPowerPileService;
	
	DefaultJRedisClient<String, CsElecHistory> jr = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);
	
	Map<String, CsPowerPile> pileMap = new HashMap<String, CsPowerPile>();

	public void run() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsPowerPile.F.csppStatus, "1");
		List<CsPowerPile> powerPileList = csPowerPileService.getCsPowerPileList(params, 1000);
		
		if(CollectionUtils.isEmpty(powerPileList)){
			
		}else{
			for(CsPowerPile powerPile : powerPileList){
				pileMap.put(powerPile.getCsppNo(), powerPile);
			}
		}
		
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
				getData(csElecHistoryService);
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
	 * 获取和保存数据
	 */
	public void getData(ICsElecHistoryService historyService){
		String result = queryAllState();
		
		JSONObject jsonObj = JSONObject.fromObject(result);
		if(!jsonObj.getBoolean("IsSuccess")){
			System.out.println("ChargeDotThread.queryAllState获取数据失败");
			return;
		}
		
		DateUtil dateUtil = new DateUtil();
		JSONArray jsonArr = JSONArray.fromObject(jsonObj.get("MonDataList"));
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jobj =  jsonArr.getJSONObject(i);
			String did = jobj.getString("Did");
			
			CsPowerPile powerPile =  pileMap.get(did);
			if(powerPile == null){
				continue;
			}
			
			CsElecHistory elecHistory = new CsElecHistory();
			elecHistory.setCsehPowerPile(powerPile.getCsppId());
			elecHistory.setCsehDid(did);
			elecHistory.setCsehStatusText(CsElecHistory.getStatusCodeText(ignoreNull(jobj.getString("Status"))));
			elecHistory.setCsehStatusCode(jobj.getString("Status"));
			elecHistory.setCsehEleAmount(jobj.getString("EleAmount"));
			elecHistory.setCsehTimeLong(ignoreNull(jobj.getString("TimeLong")));
			elecHistory.setCsehErrorCode(ignoreNull(jobj.getString("ErrorCode")));
			elecHistory.setCsehData(jobj.toString());
			
			String collectTimeStr = ignoreNull(jobj.getString("LastCreateTime"));
			if(collectTimeStr !=null){
				Date collectTime = dateUtil.StringtoDate(collectTimeStr, "yyyy-MM-dd'T'HH:mm:ss.SSS");	//yyyy-MM-dd'T'HH:mm:ss.SSSZ
				elecHistory.setCsehCollectTime(collectTime);
			}
			elecHistory.setCsehAddTime(Calendar.getInstance().getTime());
			
			elecHistory.save();
			
			CacheStoreHelper.setElecHistory(elecHistory.getCsehPowerPile().toString(), elecHistory);
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
	
	public final static String AUTH_CUST_ID = "cust0001";
	public final static String AUTH_PASSWORD = "123456";
	public final static String AUTH_COMPANY = "Com00001";
	
	/**
	 * 查询所有状态
	 * @return
	 */
	public String queryAllState(){
		String path = PATH+"/GetMonCdsData";
		JSONObject obj = new JSONObject();
        obj.element("CustId", AUTH_CUST_ID);
        obj.element("Password", AUTH_PASSWORD);
        obj.element("Company", AUTH_COMPANY);
        
        String result = sendJson(path, obj.toString());
		return result;
	}
	
	/**
	 * 根据设备编号查询状态
	 * @param dids
	 * @return
	 */
	public String queryStateByIds(List<String> dids){
		if(dids == null || dids.size() == 0)return "";
		
		String path = PATH+"/GetMonCdsDataByDids";
		JSONObject obj = new JSONObject();
        obj.element("CustId", AUTH_COMPANY);
        obj.element("Password", AUTH_PASSWORD);
        obj.element("Company", AUTH_COMPANY);

        List<Map<String, String>> didsList = new ArrayList<Map<String, String>>();
		for(String did : dids){
			Map<String, String> map = new HashMap<String, String>();
			map.put("did", did);
			didsList.add(map);
		}
		obj.element("Dids", didsList);
		String result = sendJson(path, obj.toString());
		return result;
	}
	
	/**
	 * 发送JSON请求
	 * @param path
	 * @param jsonParam
	 * @return
	 */
	public String sendJson(String path, String jsonParam){
		  try {
	            //创建连接
	            URL url = new URL(path);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
	            connection.connect();

	            //POST请求
	            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	            out.writeBytes(jsonParam);
	            out.flush();
	            out.close();

	            //读取响应
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String lines;
	            StringBuffer sb = new StringBuffer("");
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), CHARSET);
	                sb.append(lines);
	            }
	            reader.close();
	            connection.disconnect();
	            return sb.toString();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		  
		  return null;
	}
	
	public ICsElecHistoryService getCsElecHistoryService() {
		return csElecHistoryService;
	}

	public void setCsElecHistoryService(ICsElecHistoryService csElecHistoryService) {
		this.csElecHistoryService = csElecHistoryService;
	}

	public ICsPowerPileService getCsPowerPileService() {
		return csPowerPileService;
	}

	public void setCsPowerPileService(ICsPowerPileService csPowerPileService) {
		this.csPowerPileService = csPowerPileService;
	}

	public static void main(String[] args) {
		ChargeDotThread dotThread = new ChargeDotThread();
		
		String result = dotThread.queryAllState();
		System.out.println(result);
		
		List<String> dids = new ArrayList<String>();
		dids.add("ASEV01A11404P0000001");
		result = dotThread.queryStateByIds(dids);
		System.out.println(result);
		
//		Date collectTime = new DateUtil().StringtoDate("2014-12-24T08:48:31.123", "yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date collectTime = new DateUtil().StringtoDate("2014-11-28T11:23:00+08:00", "yyyy-MM-dd'T'HH:mm:ss");
		System.out.println(collectTime);
	}
}
