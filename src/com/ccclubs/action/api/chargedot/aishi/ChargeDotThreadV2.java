package com.ccclubs.action.api.chargedot.aishi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
public class ChargeDotThreadV2 extends Thread{
	
	final static String CHARSET = "utf-8";
	
	final static String AUTH_PATH = "http://42.96.191.30:1215/issuer";
	
	final static String DATA_PATH = "http://42.96.191.30:1217/api/";
	
	ICsElecHistoryService csElecHistoryService;
	
	ICsPowerPileService csPowerPileService;
	
	DefaultJRedisClient<String, CsElecHistory> jr = new DefaultJRedisClient<String, CsElecHistory>(DBIndex.APP_API);
	
	static Map<String, CsPowerPile> pileMap = new HashMap<String, CsPowerPile>();
	
	public static String authKey = "";
	
	static{
		
		getAuthKey();
		
		loadPowerPiles();
		
		Timer timer = new Timer();
		long delay = 1000; // 从接收到指令延迟十秒后开始执行
		getAuthTimerTask task = new getAuthTimerTask();
		timer.schedule(task, delay, 1000 * 60 * 10);
	}
	
	public void run() {
		while(true){
//			if($.empty($.config("release.online"))){
//				try {
//					$.trace("非在线模式，放弃执行当前线程"+this.getClass().getName());
//					Thread.sleep(5000l);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				continue;
//			}
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
	
	public static void loadPowerPiles(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsPowerPile.F.csppStatus, "1");
		ICsPowerPileService csPowerPileService = $.GetSpringBean("csPowerPileService");
		List<CsPowerPile> powerPileList = csPowerPileService.getCsPowerPileList(params, -1);
		
		if(CollectionUtils.isEmpty(powerPileList)){
			
		}else{
			for(CsPowerPile powerPile : powerPileList){
				pileMap.put(powerPile.getCsppNo(), powerPile);
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
		JSONArray jsonArr = JSONArray.fromObject(jsonObj.get("list"));
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jobj =  jsonArr.getJSONObject(i);
			String did = jobj.getString("_Did");
			
			CsPowerPile powerPile =  pileMap.get(did);
			if(powerPile == null){
				continue;
			}
			
			CsElecHistory elecHistory = new CsElecHistory();
			elecHistory.setCsehPowerPile(powerPile.getCsppId());
			elecHistory.setCsehDid(did);
			elecHistory.setCsehStatusText(CsElecHistory.getStatusCodeText(ignoreNull(jobj.getString("_Status"))));
			elecHistory.setCsehStatusCode(jobj.getString("_Status"));
			elecHistory.setCsehEleAmount(jobj.getString("_EleAmount"));
			elecHistory.setCsehTimeLong(ignoreNull(jobj.getString("_TimeLong")));
			elecHistory.setCsehErrorCode(ignoreNull(jobj.getString("_ErrorCode")));
			elecHistory.setCsehData(jobj.toString());
			
			String collectTimeStr = ignoreNull(jobj.getString("_LastCreateTime"));
			if(collectTimeStr !=null){
				Date collectTime = dateUtil.StringtoDate(collectTimeStr, "yyyy-MM-dd'T'HH:mm:ss.SSS");	//yyyy-MM-dd'T'HH:mm:ss.SSSZ
				elecHistory.setCsehCollectTime(collectTime);
			}
			//elecHistory.setCsehCollectTime(Calendar.getInstance().getTime());
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
	
	public final static String AUTH_CUST_ID = "006";
	public final static String AUTH_PASSWORD = "123456";
	public final static String AUTH_COMPANY = "ASE0001";
	
	/**
	 * 获取请求授权KEY
	 */
	public static void getAuthKey(){
		String path = AUTH_PATH;
		String urlParam = "client_id="+AUTH_CUST_ID+"&client_secret="+$.md5(AUTH_PASSWORD).toLowerCase()+"&grant_type=client_credentials&scope=http%3A%2F%2Flocalhost%2F";
		String result = postWithFormUrlEncoded(path, urlParam);
		if(result!=null && result.indexOf("access_token")!=-1){
			JSONObject jsonObj = JSONObject.fromObject(result);
			authKey = "Bearer "+jsonObj.getString("access_token");
		}
	}
	
	/**
	 * 查询所有状态
	 * @return
	 */
	public String queryAllState(){
		String path = DATA_PATH+"/MgrGetCdMonDataByCompany";
		String urlParam = "Company="+AUTH_COMPANY;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", authKey);
        String result = postWithFormUrlEncoded(path, urlParam);
		return result;
	}
	
	public static String postWithFormUrlEncoded(String path, String urlParam){
		return sendHttpPost("POST", path, urlParam, null, "application/x-www-form-urlencoded;charset=utf-8", "UTF-8");
	}
	
	public static String postWithFormUrlEncoded(String path, String urlParam, Map<String, String> headers){
		return sendHttpPost("POST", path, urlParam, headers, "application/x-www-form-urlencoded;charset=utf-8", "UTF-8");
	}
	
	/**
	 * 发送Url请求
	 * @param path
	 * @param urlParam
	 * @return
	 */
	public static String sendHttpPost(String methodType, String path, String urlParam, Map<String, String> headers, String contentType, String encode){
		try {
			//创建连接
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(methodType);	
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", contentType);
            
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(urlParam);
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), encode);
                sb.append(lines);
            }
            
            reader.close();
            connection.disconnect();
            return sb.toString();
            
		} catch (Exception e) {
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
	
	/**
	 * 更新授权定时任务
	 * 
	 * @author
	 * 
	 */
	public static class getAuthTimerTask extends TimerTask {

		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			try {
				getAuthKey();
				
				loadPowerPiles();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}


