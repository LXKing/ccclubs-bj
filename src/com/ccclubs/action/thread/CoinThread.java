package com.ccclubs.action.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsUnitMt;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsUnitMtService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;
/**
 * 红包过期时间检测(2周 1周 到期提醒)
 * @author yanzhao
 *
 */
public class CoinThread {

	private Timer timer;
	private TravelDate travelDate;
	private UpdateThread updateThread;
	
	ExecutorService executorService = Executors.newScheduledThreadPool(10); 
	
	private final String TITLE = "红包到期提醒";
	
	ICsCoinService csCoinService;
	ICsUnitMtService csUnitMtService;
	
	final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public void init(){

		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 15); // 凌晨0点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		// 如果第一次执行定时任务的时间 小于当前的时间
		// 此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			updateThread = new UpdateThread($.date(date, "yyyy-MM-dd"));
			executorService.execute(updateThread);
			date = this.addDay(date, 1);
		}
		travelDate = new TravelDate($.date(date, "yyyy-MM-dd"));
		timer = new Timer();
		timer.schedule(travelDate, date,1000*60*60*24);
	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public class UpdateThread extends Thread {
		private String endTime;

		public UpdateThread(String endTime) {
			this.endTime = endTime;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//updateCoin(endTime);
			coinRemain(endTime); 
		}

	}

	public class TravelDate extends TimerTask {

		private String endTime;

		public TravelDate(String endTime) {
			this.endTime = endTime;
		}

		@Override
		public void run() {
			coinRemain(endTime);
		}
	}

	public class ThreadSMS extends Thread{
		private CsMember member;
		private CsCoin csCoin;
		
		
		public ThreadSMS(CsMember member,CsCoin csCoin) {
			super();
			this.member = member;
			this.csCoin = csCoin;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("flag", "coin-audit");
			extra.put("data",$.json($.add("coinId", csCoin.getCscId$())));
			JpushClientHelper.sendAlertWithTag(member.getCsmId$(), TITLE, "您有红包即将在"+$.date(csCoin.getCscEnd(), "yyyy年MM月dd号")+"到期，请及时使用！", extra, JpushConfig.OFC_APP);
		}
	}
	
	public void coinRemain(String endTime){
		
		LzMap params = $.add("definex", CsCoin.C.cscStatus + " = 1 and (TIMESTAMPDIFF(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),csc_end) = 14 or TIMESTAMPDIFF(DAY,DATE_FORMAT(NOW(),'%Y-%m-%d'),csc_end) = 7) and csc_remain > 0 ");
		Long count = csCoinService.getCsCoinCount(params);
		List<CsCoin> coinList = csCoinService.getCsCoinList(params,count.intValue());
		ThreadSMS threadSMS;
		for (CsCoin csCoin : coinList) {
			saveUnitMq (csCoin);
			CsMember member = csCoin.get$cscMember();
			try {
				threadSMS = new ThreadSMS(member,csCoin);
				executorService.execute(threadSMS);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void saveUnitMq (CsCoin csCoin){
		CsMember member = csCoin.get$cscMember();
		CsUnitMt csUnitMt = new CsUnitMt();
		csUnitMt.setCsumAddTime(new Date());
		csUnitMt.setCsumHost( csCoin.getCscHost());
		CsUnitPerson person = CsUnitPerson.getCsUnitPerson($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
		if(person != null){
			csUnitMt.setCsumInfo(person.getCsupInfo());
			csUnitMt.setCsumPerson(person.getCsupId());
		}else{
			csUnitMt.setCsumId(1L);
			csUnitMt.setCsumPerson(0l);
		}
		csUnitMt.setCsumTitle(TITLE);
		csUnitMt.setCsumContent("您有红包即将在"+$.date(csCoin.getCscEnd(), "yyyy年MM月dd号")+"到期，请及时使用！");
		csUnitMt.setCsumType(2);
		csUnitMt.setCsumData("CsCoin@"+csCoin.getCscId$());
		csUnitMt.setCsumAddTime(new Date());
		csUnitMt.setCsumUpdateTime(new Date());
		csUnitMt.setCsumStatus((short)0);
		csUnitMtService.saveCsUnitMt(csUnitMt);
		
	}
	
	public void updateCoin(String endTime) {
		
		List<CsCoin> coinList = csCoinService.getCsCoinList($.add("definex", CsCoin.C.cscStatus + " != -1 and " + CsCoin.C.cscStatus + " != 2 and (" + CsCoin.C.cscEnd + " < '" + endTime + "' or " + CsCoin.C.cscEnd + " = '" + endTime + "')"), -1);
		for (CsCoin csCoin : coinList) {
			csCoin.setCscStatus((short)2);
			csCoin.setCscUpdateTime(new Date());
			csCoinService.updateCsCoin(csCoin);
		}
		
	}

	public ICsCoinService getCsCoinService() {
		return csCoinService;
	}

	public void setCsCoinService(ICsCoinService csCoinService) {
		this.csCoinService = csCoinService;
	}

	public ICsUnitMtService getCsUnitMtService() {
		return csUnitMtService;
	}

	public void setCsUnitMtService(ICsUnitMtService csUnitMtService) {
		this.csUnitMtService = csUnitMtService;
	}
	
}
