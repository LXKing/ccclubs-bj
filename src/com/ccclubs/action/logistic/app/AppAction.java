package com.ccclubs.action.logistic.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.app.official.util.OauthUtils;
import com.ccclubs.action.logistic.vo.JobView;
import com.ccclubs.action.logistic.vo.ResponseEnvelope;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsWorkRecord;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.model.TbProblem;
import com.ccclubs.service.admin.ICsWorkRecordService;
import com.ccclubs.service.admin.impl.CsWorkRecordService;
import com.ccclubs.service.common.impl.SchedulingService;
import com.jada.utils.DateUtil;
import com.jada.utils.JsonWriter;
import com.lazy3q.web.helper.$;


/**
 * 后勤端APP基本接口
 * @author wangx
 *
 */
public class AppAction extends BaseAction{
	private static Logger log = Logger.getLogger(AppAction.class);
	private JsonWriter jsonWriter = new JsonWriter();

	/***
	 * 车辆上下线操作
	 */
	public void submit_online(){

		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			//下线原因
			Long property = $.getLong("tag_id", 0l);
			//上下线标识，1为上线，2为下线
			String on = $.getString("on");
			//备注
			String remark = $.getString("remark");
			
			if($.empty(on)){
				re.setState(false).setMessage("请选择车辆上线状态").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			if(on.equals("2")){
				if(property.intValue() == 0){
					re.setState(false).setMessage("请选择下线标签").setCode(203);
					jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
					return;
				}
			}
			if($.empty(remark)){
				re.setState(false).setMessage("请输入上下线备注").setCode(204);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			Long carId = $.getLong("csc_id",0l);
			if(carId.intValue() == 0){
				re.setState(false).setMessage("车辆id为空").setCode(205);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String result = SchedulingService.onlineCar(carId, on.equals("2")?(short)2:(short)1, remark, null, property, user.getSuId());
			re.setState(true).setCode(200).setMessage("车辆上线下操作："+result);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("上下线详情发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	//0:远程开门 1:远程关门 2:允许启动 17:远程鸣笛
	public List<Map<String, Object>> getRemoteType(){
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1= new HashMap<String, Object>();
		map1.put("tag_id", "0");
		map1.put("tag", "远程开门");
		mapList.add(map1);
		
		Map<String, Object> map2= new HashMap<String, Object>();
		map2.put("tag_id", "1");
		map2.put("tag", "远程关门");
		mapList.add(map2);
		
		Map<String, Object> map3= new HashMap<String, Object>();
		map3.put("tag_id", "2");
		map3.put("tag", "允许启动");
		mapList.add(map3);
		
		Map<String, Object> map4= new HashMap<String, Object>();
		map4.put("tag_id", "17");
		map4.put("tag", "远程鸣笛");
		mapList.add(map4);
		
		return mapList;
	}
	
	/**
	 * 获取属性接口
	 * @flag 上线原因：CAR_ON_LINE_LABEL 下线原因：CAR_OFF_LINE_LABEL 远程控制原因：REMOTE_CASE 远程控制类型：REMOTE_TYPE 电桩故障原因：ELECPILE_PROB_TYPE 车辆整备项目：CAR_PREPARE_ITEMS
	 */
	public void property_list(){
		ResponseEnvelope<Map<String, Object>> re = new ResponseEnvelope<Map<String, Object>>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String flags = $.getString("flag");
			if($.empty(flags)){
				re.setState(false).setMessage("请输入FLAG值").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String[] f = flags.split(",");
			
			Map<String, Object> map = new HashMap<String, Object>();
			for (String flag : f) {
				if($.empty(flag)){
					continue;
				}
				
				if(flag.equals("REMOTE_TYPE")){
					map.put(flag, getRemoteType());
					continue;
				}
				
				SrvProperty property = srvPropertyService.getSrvProperty($.add(SrvProperty.F.spFlag, flag));
				List<Map<String, Object>> caseList = getOfflineCase($.add(SrvProperty.F.spParent,property.getSpId()));
				map.put(flag, caseList);
			}
			re.setState(true).setCode(200).setMessage("系统属性").setData(map);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取属性接口发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 车辆整备列表
	 */
	public void job_list(){
		ResponseEnvelope<JobView> re = new ResponseEnvelope<JobView>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
			List<CsWorkRecord> recordList = csWorkRecordService.getCsWorkRecordList($.add(CsWorkRecord.F.cswrUser, user.getSuId())
					.add("definex", "cswr_add_time>DATE_SUB(CURDATE(), INTERVAL 1 WEEK)"), -1);
			
			Map<String, List<Map<String, Object>>> mapdata = new HashMap<String, List<Map<String,Object>>>();
			
			if(!CollectionUtils.isEmpty(recordList)){
				
				Map<String, Object> map ;
				for (CsWorkRecord p : recordList) {
					String addTime = $.date(p.getCswrAddTime(),"yyyy-MM-dd");
					List<Map<String, Object>> dataList = mapdata.get(addTime);
					if(dataList == null) dataList = new ArrayList<Map<String,Object>>();
					
					map = new HashMap<String, Object>();
					map.put("recordId", p.getCswrId$());
					map.put("outletsName", p.getCswrOutlets$());
					map.put("carId", p.getCswrCar());
					map.put("carNumber", p.getCswrCar$());
					map.put("itemId", $.or(p.getCswrItem(), 0));   //控制原因
					map.put("itemName", p.getCswrItemname());
					map.put("status", p.getCswrStatus());
					map.put("statusText", p.getCswrStatus$());
					
					dataList.add(map);
					mapdata.put(addTime, dataList);
				}
			}
			
			List<JobView> retList = new ArrayList<JobView>();
			for(String key : mapdata.keySet()){
				JobView view = new JobView();
				view.setDatestr(key);
				view.setDatas(mapdata.get(key));
				retList.add(view);
			}
			
			Collections.sort(retList, new Comparator<JobView>(){
				
				@Override
				public int compare(JobView a, JobView b) {
					return a.getDate().before(b.getDate()) ? 1 : -1;
				}
				
			});
			
			re.setState(true).setCode(200).setMessage("获取工作记录列表").setList(retList);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统属性发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 车辆整备提交
	 */
	public void job_submit(){
		ResponseEnvelope<Map<String, Object>> re = new ResponseEnvelope<Map<String, Object>>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long recordId 	= $.getLong("record");
			Short status   	= $.getShort("status", (short)1); 				//状态1:未确认 2:已确认 
			if(recordId == null){
				Long itemId = $.getLong("itemId", 0L);   						//整备项目
				Long carId = $.getLong("carId");							//车辆
				Long outletsId = $.getLong("outletsId", 0L);				//网点
				String itemName = $.getString("itemName", "");   			//项目名称
				String operations = $.getString("operations", "");			//远程操作项目
				
				if(itemId == null || carId == null || outletsId == null){
					re.setState(false).setMessage("参数不正确").setCode(202);
					jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
					return;
				}
				
				SrvUserExp sup = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
				if(sup.getSueOutlets().indexOf(outletsId.toString()) == -1){
					re.setState(false).setMessage("该网点不属于您管辖的网点").setCode(202);
					jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
					return;
				}
				
				CsOutlets outlets = CsOutlets.get(outletsId);
				
				CsWorkRecord record = new CsWorkRecord();
				record.setCswrCar(carId);
				record.setCswrHost(outlets.getCsoHost());
				record.setCswrUser(user.getSuId());
				record.setCswrOutlets(outletsId);
				record.setCswrOperation(operations);
				record.setCswrItem(itemId);
				record.setCswrItemname(itemName);
				record.setCswrAddTime(new Date());
				record.setCswrUpdateTime(new Date());
				record.setCswrStatus(status);
				record.save();
				
				/**
				 * 车辆整备完成
				 * 2:已完成整备
				 */
				if(status==2){
					OutsideStatisticsUtil.finish(user, carId, itemId);
				}
				
			}else{
				CsWorkRecord record = CsWorkRecord.get(recordId);
				if(record == null){
					re.setState(false).setMessage("没有该记录").setCode(203);
					jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
					return;
				}
				
				record.setCswrUpdateTime(new Date());
				record.setCswrStatus(status);
				record.update();
				/**
				 * 车辆整备完成
				 * 2:已完成整备
				 */
				if(status==2){
					OutsideStatisticsUtil.finish(user, record.getCswrCar(), record.getCswrItem());
				}
				
			}
			re.setState(true).setCode(200).setMessage("车辆整备已保存");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("车辆整备提交接口发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
}
