package com.ccclubs.action.thread;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.dao.ICsNoticeDao;
import com.ccclubs.dao.ICsTaskDao;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.EventHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMaintain;
import com.ccclubs.model.CsNotice;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsRefund;
import com.ccclubs.model.CsTask;
import com.ccclubs.model.CsTodo;
import com.ccclubs.model.CsViolat;
import com.ccclubs.dao.ICsCarDao;
import com.ccclubs.dao.ICsMaintainDao;
import com.ccclubs.dao.ICsOutletsDao;
import com.ccclubs.dao.ICsViolatDao;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 数据预警引擎
 * @author uiu
 */
public class TaskThread extends Thread {
	ICsTaskDao csTaskDao;
	ICsNoticeDao csNoticeDao;
	ICsCarDao csCarDao;
	ICsMaintainDao csMaintainDao;
	ICsOutletsDao csOutletsDao;
	ICsViolatDao csViolatDao;
	static Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
	
	@Override
	public void run() {
		while(true){
			int count = 0;
			//MessageHelper.publish(MessageHelper.MessageType.MESSAGE, 0l, 0l, 0l, "test", $.rand(99)+"条最准的心理测试 200条最准的心理测试大全有爱情测试、财富测试", 0l);
			/*MessageHelper.postEvent(
					0l,MessageHelper.EventType.测试事件, 
					"条最准的心理测试 200条最准的心理测试大全有爱情测试、财富测试",0l
				);*/

			//待办事项预警
			try{
				count += scanTodos();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","待办事项预警引擎出错",ex);
			}
			
			
			//系统消息预警
			try{
				count += scanNotices();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","系统消息预警引擎出错",ex);
			}
			
			
			//会员退款预警
			try{
				count += scanRefunds();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","会员退款预警引擎出错",ex);
			}
			
			
			//网点缴费、合约预警
			try{
				count += scanOutlets();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","网点缴费、合约预警引擎出错",ex);
			}
			
			
			//违章通知提醒
			try{
				count += scanViolat();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","违章通知提醒预警引擎出错",ex);
			}
			
			
			
			//保险到期提醒
			try{
				count += scanInsure();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","保险到期提醒预警引擎出错",ex);
			}
			
						
			//保养到期提醒
			try{
				count += scanMaintain();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","保养到期提醒引擎出错",ex);
			}
			
			
			//更新网点车辆数
			try{
				count += updateOutletsCarCounts();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","保养到期提醒引擎出错",ex);
			}
			
			try {
				if(count==0)
					Thread.sleep(5000);
				else
					Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	static Integer lastScanRefunds= -1;
	/**
	 * 退款预警扫描，一小时执行一次
	 * @return
	 */
	private int scanRefunds() {
		
		if(lastScanRefunds == new Date().getHours())
			return 0;
		
		lastScanRefunds = new Date().getHours();
		
		
		List<CsRefund> checkes = CsRefund.list(
				$.add(CsRefund.F.csrStatus, 0)
				.add(CsRefund.F.csrCheckTimeEnd, new Date())
			, -1);
		for(CsRefund csRefund:checkes){
			EventHelper.postEvent(
				csRefund.getCsrHost(),
				EventHelper.EventType.会员退款审核到期预警,
				"会员("+csRefund.getCsrMember$()+")退款审核到期预警",
				csRefund.getCsrId(),
				CsRefund.class
			);
		}
		
		
		List<CsRefund> refunds = CsRefund.list(
				$.add(CsRefund.F.csrStatus, 1)
				.add(CsRefund.F.csrBackTimeEnd, new Date())
			, -1);
		for(CsRefund csRefund:refunds){
			EventHelper.postEvent(
				csRefund.getCsrHost(),
				EventHelper.EventType.会员退款打款到期预警,
				"会员("+csRefund.getCsrMember$()+")退款打款到期预警",
				csRefund.getCsrId(),
				CsRefund.class
			);
		}
		return checkes.size()+refunds.size();
	}


	/**
	 * 扫描系统消息项
	 * @return
	 */
	private int scanNotices() {
		//获取所有已过期待处理的待办事项
		List<CsNotice> list = CsNotice.list($.add(CsNotice.F.csnStatus, 0).add(CsNotice.F.csnAddTimeEnd, new Date()), 20);
		for(CsNotice csNotice:list){
			EventHelper.publish(
				EventHelper.MessageType.NOTICE,
				0l, 0l, csNotice.getCsnReceiver(),
				"NOTICE",
				csNotice.getCsnContent(), 
				csNotice.getCsnId()
			);
			new CsNotice().csnId(csNotice.getCsnId()).csnStatus((short)1).update();
		}
		return list.size();
	}


	/**
	 * 扫描待办事项
	 * @return
	 */
	private int scanTodos() {
		//获取所有已过期待处理的待办事项
		List<CsTodo> list = CsTodo.list($.add(CsTodo.F.cstStatus, 0).add(CsTodo.F.cstAlarmTimeEnd, new Date()), 20);
		for(CsTodo csTodo:list){
			EventHelper.publish(
				EventHelper.MessageType.TODO,
				0l, 0l, csTodo.getCstUser(),
				"NOTICE",
				csTodo.getCstTitle(), 
				csTodo.getCstId()
			);
			new CsTodo().cstId(csTodo.getCstId()).cstStatus((short)1).update();
		}
		return list.size();
	}



	/**
	 * 更新网点车辆数
	 * @return
	 */
	private int updateOutletsCarCounts() {
		//dao.executeUpdate("update cs_outlets o set cso_car_s=(select count(csc_outlets) from cs_car where csc_outlets=o.cso_id)");
		return 0;
	}




	static Integer lastScanInsure= -1;
	/**
	 * 保险到期提醒
	 * @return
	 */
	private int scanInsure() {
		
		if(lastScanInsure == new Date().getHours())
			return 0;
		
		lastScanInsure = new Date().getHours();
		
		int count = 0;		
		
		//交强险到期
		List<CsCar> tis = csCarDao.getCsCarList(
			$.add("definex", "TO_DAYS(csc_ti_unit)-TO_DAYS(now())<10")
				, -1);
		for(CsCar csCar:tis){
			EventHelper.postEvent(
				csCar.getCscHost(),
				EventHelper.EventType.车辆交强险到期预警,
				"车辆"+csCar.getCscNumber()+"交强险到期预警",
				csCar.getCscId(),
				CsCar.class
			);
		}
		count+=tis.size();
		
		
		
		//商业险到期
		List<CsCar> bis = csCarDao.getCsCarList(
			$.add("definex", "TO_DAYS(csc_bi_unit)-TO_DAYS(now())<10")
				, -1);
		for(CsCar csCar:bis){
			EventHelper.postEvent(
					csCar.getCscHost(),
					EventHelper.EventType.车辆商业险到期预警,
					"车辆"+csCar.getCscNumber()+"商业险到期预警", 
					csCar.getCscId(),
					CsCar.class
				);
		}
		count+=bis.size();
		
		
		
		
		//车辆年检到期
		List<CsCar> annuals = csCarDao.getCsCarList(
			$.add("definex", "TO_DAYS(csc_annual)-TO_DAYS(now())<10")
				, -1);
		for(CsCar csCar:annuals){
			EventHelper.postEvent(
				csCar.getCscHost(),
				EventHelper.EventType.车辆年检到期预警, 
				"车辆"+csCar.getCscNumber()+"车辆年检到期预警", 
				csCar.getCscId(),
				CsCar.class
			);
		}
		count+=annuals.size();
		
			
		return count;
	}



	static Integer lastScanViolat= -1;
	/**
	 * 违章通知提醒
	 * @return
	 */
	private int scanViolat() {
		
		if(lastScanViolat == new Date().getHours())
			return 0;
		
		
		lastScanViolat = new Date().getHours();
		
		$.trace("########################违章通知提醒扫描未实现####################################");	
		return 0;
	}




	static Integer lastScanOutlets = -1;
	/**
	 * 网点缴费、合约预警,一天提醒一次
	 * @return
	 */
	private int scanOutlets() {
		
		if(lastScanOutlets == new Date().getHours())
			return 0;
				
		lastScanOutlets = new Date().getHours();
		
		//缴费到期时间提前15天报警
		List<CsOutlets> fees = csOutletsDao.getCsOutletsList(
			$.add("definex", "TO_DAYS(cso_until)-TO_DAYS(now())<15")
				, -1);	
		for(CsOutlets csOutlets:fees){
			EventHelper.postEvent(
				csOutlets.getCsoHost(),
				EventHelper.EventType.网点缴费预警提醒, 
				"网点"+csOutlets.getCsoName()+"["+csOutlets.getCsoId()+"]缴费预警", 
				csOutlets.getCsoId(),
				CsOutlets.class
			);
		}
		
		
		//合约到期时间提前30天报警
		List<CsOutlets> contracts = csOutletsDao.getCsOutletsList(
			$.add("definex", "TO_DAYS(cso_expire)-TO_DAYS(now())<30")
				, -1);	
		for(CsOutlets csOutlets:contracts){
			EventHelper.postEvent(
				csOutlets.getCsoHost(),
				EventHelper.EventType.网点合约到期预警提醒, 
				"网点"+csOutlets.getCsoName()+"["+csOutlets.getCsoId()+"]合约到期预警", 
				csOutlets.getCsoId(),
				CsOutlets.class
			);
		}				
		return fees.size()+contracts.size();
	}
	
	




	static Integer lastScanMaintain = -1;
	/**
	 * 保养到期提醒,一天提醒一次
	 * @return
	 */
	private int scanMaintain() {
		if(lastScanMaintain == new Date().getHours())
			return 0;
		
		lastScanMaintain = new Date().getHours();
		
		//把实际公里大于保养公里的车辆设置为报警
		csMaintainDao.updateCsMaintainByConfirm(
				$.add(CsMaintain.F.csmStatus, 1)
				, $.add(CsMaintain.F.csmStatus, 0).add("definex", "csm_curr_km>csm_expect_km").add("confirm", 1));
		
		List<CsMaintain> warns = csMaintainDao.getCsMaintainList(
			$.add(CsMaintain.F.csmStatus, 1)
				, -1);
		
		for(CsMaintain csMaintain:warns){
			EventHelper.postEvent(
				csMaintain.getCsmHost(),
				EventHelper.EventType.车辆保养预警提醒, 
				"车辆"+csMaintain.get$csmNumber().getCscNumber()+"保养到期预警",
				csMaintain.getCsmId(),
				CsMaintain.class
			);
		}		
		return warns.size();
	}
	

	
	private String format(Map<String,Object> map,String src){		
		for(Map.Entry<String,Object> entry : map.entrySet()){
			if(entry!=null && entry.getValue()!=null)
				src = src.replace("{"+entry.getKey()+"}", entry.getValue().toString());			
		}
		return src;
	}
	

	public Long[] ids(String strIds){
		if($.empty(strIds))return new Long[]{};
		String[] array = strIds.split(",");
		Long ids[] = new Long[array.length];
		for(int i=0;i<array.length;i++)
			ids[i]=Long.parseLong($.trim(array[i]));
		return ids;
	}

	public ICsTaskDao getCsTaskDao() {
		return csTaskDao;
	}

	public void setCsTaskDao(ICsTaskDao csTaskDao) {
		this.csTaskDao = csTaskDao;
	}

	public ICsNoticeDao getCsNoticeDao() {
		return csNoticeDao;
	}

	public void setCsNoticeDao(ICsNoticeDao csNoticeDao) {
		this.csNoticeDao = csNoticeDao;
	}





	public ICsCarDao getCsCarDao() {
		return csCarDao;
	}





	public void setCsCarDao(ICsCarDao csCarDao) {
		this.csCarDao = csCarDao;
	}





	public ICsMaintainDao getCsMaintainDao() {
		return csMaintainDao;
	}





	public void setCsMaintainDao(ICsMaintainDao csMaintainDao) {
		this.csMaintainDao = csMaintainDao;
	}
















	public ICsOutletsDao getCsOutletsDao() {
		return csOutletsDao;
	}
















	public void setCsOutletsDao(ICsOutletsDao csOutletsDao) {
		this.csOutletsDao = csOutletsDao;
	}
















	public ICsViolatDao getCsViolatDao() {
		return csViolatDao;
	}
















	public void setCsViolatDao(ICsViolatDao csViolatDao) {
		this.csViolatDao = csViolatDao;
	}

}
