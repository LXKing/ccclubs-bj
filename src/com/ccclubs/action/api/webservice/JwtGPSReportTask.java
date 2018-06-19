package com.ccclubs.action.api.webservice;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.api.webservice.jwtgps.CarInfoReq;
import com.ccclubs.action.api.webservice.jwtgps.CarLocationReq;
import com.ccclubs.action.api.webservice.jwtgps.CsStatesStore;
import com.ccclubs.action.api.webservice.jwtgps.CscarStore;
import com.ccclubs.action.api.webservice.jwtgps.JwtGPSClient;
import com.ccclubs.action.api.webservice.jwtgps.LoginReq;
import com.ccclubs.action.api.webservice.jwtgps.RepayCarLocationReq;
import com.ccclubs.helper.FileUtil;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsState;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsStateService;
import com.lazy3q.web.helper.$;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicInteger;

public class JwtGPSReportTask {
	/**
	 * 
	 * 任务状态!!!
	 * 
	 * 0：默认状态，尚未初始化 ==》 
	 * 1：已完成初始化(发送登录) ==》 
	 * 2：定时任务已启动，上传任务执行中...(等待登录返回)==》
	 * 3：上报任务正常执行状态 (登录已成功)==》
	 * 4：通信异常，重新连接==》
	 * 5：连接成功并发送登录                    
	 * 
	 */
	protected AtomicInteger state		    = new AtomicInteger(STATE_DEFAULT);							
	private AtomicBoolean reportRunning     = new AtomicBoolean(false);
	final static int STATE_DEFAULT 	 		= 0;
	final static int STATE_CONNECTED 		= 1;
	final static int STATE_INITIALIZED  	= 2;
	final static int STATE_LOGINOK			= 3;
	final static int STATE_RECONNECT  		= 4;
	final static int STATE_RELOGIN   		= 5;
	
	final static int port = 8899;
	
	static JwtGPSClient client = null;
	public static CscarStore carStore = null;
	public static CsStatesStore stateStore = null;
	public static Map<String, CsCar> cars = new HashMap<String, CsCar>();
	ICsCarService			csCarService;
	ICsStateService			csStateService;
	static String			carStoreFilePath = null;
	static  String 			stateStoreFilePath = null;
	
	
	public void init(){
		synchronized(JwtGPSReportTask.class){
			if(getState().get()>0){
				return;
			}
	//		if($.empty($.config("release.online"))){
	//			$.trace("非在线模式，放弃执行同步任务："+this.getClass().getName());
	//			return;
	//		}
			
			
			try {
				//stateStoreFilePath = FileUtil.class.getResource("/").toURI().getPath() + "dat/csstatestore.dat";
				carStoreFilePath = $.config("kv.cache.path") + "cscarstore.dat";
				stateStoreFilePath = $.config("kv.cache.path") + "csstatestore.dat";
				File file = new File(carStoreFilePath);
				if(!file.exists()){
					file.createNewFile();
				}
				file = new File(stateStoreFilePath);
				if(!file.exists()){
					file.createNewFile();
				}
				carStore = (CscarStore)FileUtil.readObjectFromFile(carStoreFilePath);
				stateStore = (CsStatesStore) FileUtil.readObjectFromFile(stateStoreFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			client = new JwtGPSClient(this);
			client.setName("jwtGpsClient-Thread"+(new Date()).getTime());
			client.start();
		}
	}
	
	Long lastSyncTime = 0L;
	
	public void start(){
		getState().set(STATE_CONNECTED);
		
		login();
		
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		while(!isReady() && step.get() < 6){
//			
//			Long currentTime = Calendar.getInstance().getTime().getTime();
//			if(lastSyncTime == 0L)lastSyncTime = currentTime;
//			//离最后更新结束后的时间戳大于10秒钟，才去执行更新
//			if(currentTime - lastSyncTime < 1000 * 60){
//				try {
//					Thread.sleep(1000 * 1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				continue;
//			}
//			
//			login();
//			step.incrementAndGet();
//		}
		
		Timer timer = new Timer();
		long delay = 10000; 								//从接收到指令延迟十秒后开始执行
		SyncCarsTimerTask task = new SyncCarsTimerTask(this);
		timer.schedule(task, delay, 1000 * 60 * 60 * 1);	//一小时刷新一下本地记录
		
		Timer timer_2 = new Timer();
		SyncStatesTimerTask task_2 = new SyncStatesTimerTask(this);
		timer_2.schedule(task_2, delay, 1000 * 10);		    //10秒钟
		
		Timer timer_3 = new Timer();
		LoginTimerTask task_3 = new LoginTimerTask(this);
		timer_3.schedule(task_3, delay, 1000 * 60);		   //1分钟
		
		getState().set(STATE_INITIALIZED);
	}
	
	public void reloadCars(){
		List<CsCar> carsList = csCarService.getCsCarList($.add(CsCar.F.cscHost, 1), Integer.MAX_VALUE);
		if(!CollectionUtils.isEmpty(carsList)){
			for(CsCar car : carsList){
				cars.put(car.getCscNumber(), car);
			}
		}
	}
	
	/**
	 * 同步车辆实时状态数据
	 * 把最新的数据与之前的拿出来做对比，如果有新增的数据则上报
	 */
	public void syncStates(){ 
		List<CsState> statesList = csStateService.getCsStateList($.add(CsState.F.cssHost, 1), Integer.MAX_VALUE);
		List<CsState> delList = new ArrayList<CsState>();
		if(!CollectionUtils.isEmpty(statesList)){
			if(stateStore!=null){
				Map<String, CsState> statesMap = stateStore.getStatesMap();
				if(statesMap!=null){
					for(CsState csState : statesList){
						CsState oldState = statesMap.get(csState.getCssNumber());
						if(oldState!=null){
							if(csState.getCssAddTime().equals(oldState.getCssAddTime())){
								delList.add(csState);
							}
						}
					}
				}
			}
			
			statesList.removeAll(delList);
			reqFilterStateAll(statesList);
			//进行同步
			for(CsState state : statesList){
				CarLocationReq locReq = new CarLocationReq();
				client.sendMsg(locReq.getHexString(port, state));
				
				stateStore.put(state);
			}
			if(!CollectionUtils.isEmpty(statesList)){
				writeStatesDataToFile();
			}
		}
	}
	
	/**
	 * 同步车辆注册数据
	 * 把最新的数据与之前的拿出来做对比，如果有新增的数据则上报
	 */
	public void syncCars(){
		List<CsCar> carsList = csCarService.getCsCarList($.add(CsCar.F.cscHost, 1), Integer.MAX_VALUE);
		List<CsCar> delList = new ArrayList<CsCar>();
		if(!CollectionUtils.isEmpty(carsList)){
			if(carStore!=null){
				Map<Long, CsCar> carsMap = carStore.getCarsMap();
				if(carsMap!=null){
					for(int i =0; i<carsList.size(); i++){
						CsCar car = carsList.get(i);
						CsCar oldCar = carsMap.get(car.getCscId());
						if(oldCar!=null){
							if(car.getCscAddTime().getTime() == oldCar.getCscAddTime().getTime()){
								delList.add(car);
							}
						}
					}
				}
			}
			
			carsList.removeAll(delList);
			
			//进行组装
			for(CsCar car : carsList){
				if (reqFilterCar(car, "BJ")) {
					CarInfoReq carInfoReq = new CarInfoReq();
					client.sendMsg(carInfoReq.getHexString(port, car));
				}
				
				//再回写给本地
				cars.put(car.getCscNumber(), car);
				carStore.put(car);
			}
			
			if(!CollectionUtils.isEmpty(carsList)){
				writeCarsDataToFile();
			}
		}
	}
	
	void writeCarsDataToFile(){
		try{
			FileUtil.writeObjectToFile(carStore, carStoreFilePath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void writeStatesDataToFile(){
		try{
			FileUtil.writeObjectToFile(stateStore, stateStoreFilePath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始启动，上报所有的车辆信息
	 */
	public void reportAllCars(){
		List<CsCar> carsList = csCarService.getCsCarList($.add(CsCar.F.cscHost, 1), Integer.MAX_VALUE);
		if(!CollectionUtils.isEmpty(carsList)){
			if(carStore == null)carStore=new CscarStore();
			for(CsCar car : carsList){
				if (reqFilterCar(car, "BJ")) {
					CarInfoReq carInfoReq = new CarInfoReq();
					client.sendMsg(carInfoReq.getHexString(port, car));
					carStore.put(car);
				}
				cars.put(car.getCscNumber(), car);	// 有疑问	过滤掉的数据是否要回写到本地？？
			}
		}
		
		writeCarsDataToFile();
	}
	
	/**
	 * 初始启动，补报车辆GPS定位信息
	 */
	public void repayCarsStates(){
		List<CsState> statesList = csStateService.getCsStateList($.add(CsState.F.cssHost, 1), Integer.MAX_VALUE);
		List<CsState> delList = new ArrayList<CsState>();
		if(!CollectionUtils.isEmpty(statesList)){
			if(stateStore!=null){
				Map<String, CsState> statesMap = stateStore.getStatesMap();
				if(statesMap!=null){
					for(int i =0; i<statesList.size(); i++){
						CsState state = statesList.get(i);
						CsState oldState = statesMap.get(state.getCssNumber());
						if(oldState!=null){
							if(state.getCssAddTime().getTime() == oldState.getCssAddTime().getTime()){
								delList.add(state);
							}
						}
					}
				}
			}else{
				stateStore = new CsStatesStore();
			}
			
			statesList.removeAll(delList);
			
			Map<String, List<CsState>> carStatesMap = new HashMap<String, List<CsState>>();
			for(CsState s : statesList){
				List<CsState> sList = carStatesMap.get(s.getCssNumber());
				if(sList == null) sList = new ArrayList<CsState>();
				sList.add(s);
				carStatesMap.put(s.getCssNumber(), sList);
				stateStore.put(s);
			}
			
			//进行同步
			for(String carKey : carStatesMap.keySet()){
				List<CsState> stateList = carStatesMap.get(carKey);
				reqFilterStateAll(stateList);	// 过滤没有经纬度数据
				RepayCarLocationReq locReq = new RepayCarLocationReq();
				client.sendMsg(locReq.getHexString(port, stateList));
			}
			
			writeStatesDataToFile();
		}
	}
	
//	// 过滤没有经纬度数据
//	public static boolean reqFilterState(CsState req) {
//		
//		return StringUtils.isEmpty(req.getCssLongitude()) || StringUtils.isEmpty(req.getCssLatitude())? true : false;
//	}
	
	// 过滤车牌不匹配数据
	public static boolean reqFilterCar(CsCar req, String param) {
		if(req.getCscNumber() == null) return false;
		return req.getCscNumber().contains(param)? true : false;
	}
	
	// 过滤所有没有经纬度数据
	public static void reqFilterStateAll(List<CsState> lists) {
		for (Iterator<CsState> it = lists.iterator(); it.hasNext();) {
			CsState state = it.next();
			if(StringUtils.isEmpty(state.getCssLongitude()) || StringUtils.isEmpty(state.getCssLatitude()) 
			|| Double.valueOf(state.getCssLongitude()).intValue() ==0 || Double.valueOf(state.getCssLatitude()).intValue() ==0){ 
				it.remove();
			}
		}
		
		/*// CsState老式过滤
		if (lists != null) {
			List<CsState> delList = new ArrayList<CsState>();
			for (CsState state : lists) {
				if(StringUtils.isEmpty(state.getCssLongitude()) || StringUtils.isEmpty(state.getCssLatitude())){
					delList.add(state);
				}
			}
			
			if (delList != null) {
				lists.removeAll(delList);
			}
		}
		 */
		
	}
	
	/**
	 * 发起登录
	 * @return
	 */
	public void login(){
		LoginReq login = new LoginReq();
		client.sendMsg(login.getHexString(port));
	}
	
	/**
	 * 服务器连接成功回调
	 */
	public void connectedCallback(){
		if(getState().get() == STATE_DEFAULT){
			start();
		}else if(getState().get() == STATE_RECONNECT){
			getState().set(STATE_RELOGIN);
			login();
		}
	}
	
	/**
	 * 服务器断开连接回调
	 */
	public void disConnectCallback(){
		getState().set(STATE_RECONNECT);
		//TODO 发起重新连接
		client = new JwtGPSClient(this);
		client.setName("jwtGpsClient-Thread"+(new Date()).getTime());
		client.start();
	}
	
	/**
	 * 登录消息成功回调
	 */
	public void messageCallback(){
		
		//如果是初始登录或重连返回的，重新上传补报
		if(getState().get() ==STATE_INITIALIZED || getState().get() == STATE_RELOGIN){
			
			//启动连接并登录成功时  上传所有的车辆信息
			reportAllCars();
			
			//启动连接并登录成功时   补报所有的车辆定位信息
			repayCarsStates();
		}
		
		getState().set(STATE_LOGINOK);
	}

	public ICsCarService getCsCarService() {
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICsStateService getCsStateService() {
		return csStateService;
	}

	public void setCsStateService(ICsStateService csStateService) {
		this.csStateService = csStateService;
	}

	public boolean isReportRunning() {
		return reportRunning.get();
	}

	public boolean setReportRunning(boolean reportRunning) {
		return this.reportRunning.compareAndSet(!reportRunning, reportRunning);
	}

	public AtomicInteger getState() {
		return state;
	}
	
}

/**
 * 更新定时任务
 * 
 * @author
 * 
 */
class SyncCarsTimerTask extends TimerTask {
	
	JwtGPSReportTask syncTask;
	
	public SyncCarsTimerTask(JwtGPSReportTask syncTask){
		this.syncTask = syncTask;
	}
	
	/**
	 * 此计时器任务要执行的操作。
	 */
	public void run() {
		boolean flag = false;
		try {
			if(syncTask.setReportRunning(true)){
				flag = true;
				if(syncTask.getState().get() == 3){
					syncTask.syncCars();
				}
				
				syncTask.reloadCars();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally{
			if(flag){
				syncTask.setReportRunning(false);
			}
		}
	}
}

/**
 * 车辆GPS定位信息任务
 * 
 * @author
 * 
 */
class SyncStatesTimerTask extends TimerTask {
	
	JwtGPSReportTask syncTask;
	
	public SyncStatesTimerTask(JwtGPSReportTask syncTask){
		this.syncTask = syncTask;
	}
	
	/**
	 * 此计时器任务要执行的操作。
	 */
	public void run() {
		boolean flag = false;
		try {
			if(syncTask.setReportRunning(true)){
				flag = true;
				if(syncTask.getState().get() == 3){
					syncTask.syncStates();
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally{
			if(flag){
				syncTask.setReportRunning(false);
			}
		}
	}
}

/**
 * 尝试登录定时任务
 * 
 * @author
 * 
 */
class LoginTimerTask extends TimerTask {
	
	JwtGPSReportTask syncTask;
	
	public LoginTimerTask(JwtGPSReportTask syncTask){
		this.syncTask = syncTask;
	}

	@Override
	public void run() {
		boolean flag = false;
		try{
			//如果登录还未返回，重新发送
			if(syncTask.setReportRunning(true)){
				flag = true;
				if(syncTask.getState().get()<3){
					syncTask.login();
				}
			}
		} finally{
			if(flag){
				syncTask.setReportRunning(false);
			}
		}
		
	}
	
}
