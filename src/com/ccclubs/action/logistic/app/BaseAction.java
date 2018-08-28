package com.ccclubs.action.logistic.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.app.official.util.OauthUtils;
import com.ccclubs.action.app.official.util.SessionMgr;
import com.ccclubs.action.logistic.vo.CarManage;
import com.ccclubs.action.logistic.vo.Online;
import com.ccclubs.action.logistic.vo.ProjectCheck;
import com.ccclubs.action.logistic.vo.ResponseEnvelope;
import com.ccclubs.action.logistic.vo.User;
import com.ccclubs.action.logistic.vo.Warning;
import com.ccclubs.action.m.WeixinHelper;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarOn;
import com.ccclubs.model.CsCarParts;
import com.ccclubs.model.CsCarSysIssue;
import com.ccclubs.model.CsCleanRecord;
import com.ccclubs.model.CsEvent;
import com.ccclubs.model.CsLog;
import com.ccclubs.model.CsMaintain;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsRetcheck;
import com.ccclubs.model.CsServAlarm;
import com.ccclubs.model.CsState;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.model.TbProblem;
import com.ccclubs.service.admin.ICsCarOnService;
import com.ccclubs.service.admin.ICsCarPartsService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCarSysIssueService;
import com.ccclubs.service.admin.ICsCleanRecordService;
import com.ccclubs.service.admin.ICsEventService;
import com.ccclubs.service.admin.ICsLogService;
import com.ccclubs.service.admin.ICsMaintainService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsRetcheckService;
import com.ccclubs.service.admin.ICsServAlarmService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ISrvProjectService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.ccclubs.service.admin.ISrvUserService;
import com.ccclubs.service.admin.ITbProblemService;
import com.jada.jdbc.Rs2bean;
import com.jada.utils.DateUtil;
import com.jada.utils.JsonUtil;
import com.jada.utils.JsonWriter;
import com.jada.utils.UUIDGenerator;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;
import com.lazy3q.web.helper.WebHelper.LzMap;

public class BaseAction extends OutsideStatisticsUtil{
	private static Logger log = Logger.getLogger(AppAction.class);
	private JsonWriter jsonWriter = new JsonWriter();
	private JsonUtil jsonUtil = new JsonUtil();
	
	
	ISrvPropertyService srvPropertyService;
	ICsCarService csCarService;
	ICsCarOnService csCarOnService;
	ICsMaintainService csMaintainService;
	ITbProblemService tbProblemService;
	
	final static short UPDATE_TYPE = 2;									//后勤app标识
	final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";		//常用日期格式化
	
	/**
	 * 登陆
	 */
	public void login(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			ISrvUserService srvUserService = $.GetSpringBean("srvUserService");
			ISrvUserExpService srvUserExpService = $.GetSpringBean("srvUserExpService");
			
			//参数
			String mobile = $.getString("mobile");
			String password = $.getString("password");
			
			//log.debug("登陆：mobile=" + mobile + ",password:" + password);
			if(mobile == null || mobile.trim().equals("") || password == null || password.trim().equals("")){
				re.setState(false).setCode(201).setMessage("用户名或密码不能为空");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			List<SrvUser> list = srvUserService.getSrvUserList($.add("definex", "su_username='" + mobile + "' or su_mobile='" + mobile + "'"), -1);
			if(list.size()==0 || list.size()>1){
				re.setState(false).setCode(202).setMessage("该账户不存在");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			SrvUser user = list.get(0);
			SrvUserExp userExp = srvUserExpService.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			if(userExp == null){
				re.setState(false).setCode(203).setMessage("该帐号无权限");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			if(userExp.getSueOutlets() == null || userExp.getSueOutlets().length() == 0){
				re.setState(false).setCode(203).setMessage("该帐号无权限");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			if(user.getSuStatus() != 1){
				re.setState(false).setCode(204).setMessage("该帐号已经失效");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			if(!password.equals(user.getSuPassword()) && !$.md5(password).equals(user.getSuPassword())){
				re.setState(false).setCode(205).setMessage("密码输入错误");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String sessionToken = SessionMgr.get(user.getSuId$());
			if(StringUtils.isNotEmpty(sessionToken)){
				if(OauthUtils.getOauth(sessionToken)!=null){
					OauthUtils.removeToken(sessionToken, user.getSuId$());
				}else{
					SessionMgr.del(sessionToken);
				}
			}
			
			//设置最后登陆信息
			user.setSuLastIp(request.getRemoteAddr());
			user.setSuLastTime(new Date());
			srvUserService.updateLastIp(user);
			//传token给客户端
			String token = new UUIDGenerator().getUUID();
			OauthUtils.saveToken(user.getSuId().toString(), token);
			
			LoggerHelper.writeLog(SrvUser.class, "LOGIN", "登入系统", user.getSuId(), "登录系统");
			
			re.setState(true).setCode(200).setMessage("登陆成功").setData(token);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("登陆发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 首页菜单
	 */
	public void index_menu(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");
			String token = $.getString("sessionToken");
			log.debug("首页菜单：token=" + token);
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			//菜单模板根据账号id取
			List<SrvProject> list = srvProjectService.getSrvProjectList($.add(SrvProject.F.spGroup, "yw_app").add("definex",
					"sp_id in (select sl_project_id from srv_limited where sl_actor_id=" + user.getSuId() + ") and sp_parent_id != 0 and sp_status=1 ").add("asc", "sp_level"), -1);
			
			String outletsIds = $.or(SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId())).getSueOutlets(), "");
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			for(SrvProject sp : list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sp_id", sp.getSpId());
				map.put("sp_name", sp.getSpName());
				map.put("sp_level", sp.getSpLevel());
				map.put("sp_logo", sp.getSpLogo());
				map.put("sp_url", sp.getSpUrl());
				if(sp.getSpName().equals("提醒")){
					ICsEventService csEventService = $.GetSpringBean("csEventService");
					Long count = csEventService.getCsEventCount($.add(CsEvent.F.cseStatus, 1).add(CsEvent.F.cseDataId, user.getSuId()));
					map.put("count", count);
				}
				if(sp.getSpName().equals("电桩故障提报")){
					ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
					Long count = tbProblemService.getTbProblemCount($.add("definex", " tbp_outlets in ("+outletsIds+")").add(TbProblem.F.tbpType, "6").add(TbProblem.F.tbpStatus, 0));
					map.put("count", count);
				} 
				res.add(map);
			}
			
			re.setState(true).setCode(200).setMessage("首页菜单").setData(res);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("首页菜单发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 车辆管理
	 */
	public void car_manage(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		DateUtil du = new DateUtil();
		try{
			ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			/** 筛选条件 **/
			String region = $.getString("region", "0");			//区域，0不限
			String outlet = $.getString("outlet", "0");			//网点，0不限
			String online = $.getString("online", "0");				//上下线状态，0不限，1上线，2下线
			String usestatus = $.getString("usestatus", "0");	//使用状态，0不限，1使用中，2空闲
			String carnumber = $.getString("carnumber", "");	//车牌号，支持模糊查询
			log.debug("车辆管理：token=" + token + ",region=" + region + ",outlet=" + outlet + ",online=" + online + ",usestatus=" + usestatus);
			
			SrvUserExp srvUserExp = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			String outletsIds = $.or(srvUserExp.getSueOutlets(), "");
			
			String outletsSQL = "";
			//=======================================================================================
			if(!outlet.equals("0") && outletsIds.indexOf(outlet)!=-1){
				outletsSQL = "=" + outlet;
			}else{
				if(!region.equals("0")){
					outletsSQL = " in (select coo.cso_id from cs_outlets coo where coo.cso_area=" + region + ")";
				}else{
					outletsSQL = " in ("+outletsIds+")";
				}
			}
			
			String whereSQL = " where csc_outlets "+outletsSQL + " AND csc_status >= 0 ";
			if(!$.empty(carnumber))whereSQL += " AND csc_car_no like '%" + carnumber + "%'";
			if(!$.or(online, "").equals("0")) whereSQL += " and csc_status = " + online;
			
			List<Map> list = new ArrayList<Map>();
			String ordebySQL = " order by CAST(cst.css_ev_battery AS SIGNED) asc";
					
			if(usestatus.equals("0") || usestatus.equals("2")){
				String tmpSQL = whereSQL + " and not exists (select 1 from cs_order o where o.cso_car = cc.csc_id and o.cso_status in (1,2,5))";
				String sql = "SELECT cc.csc_id,cc.csc_host,cc.csc_car_no,cc.csc_model,cc.csc_outlets,cc.csc_longitude,cc.csc_latitude,cc.csc_status,cc.csc_images," +
						" cst.css_endurance,cst.css_ev_battery,cst.css_power,cst.css_charging,csm.cscm_name as model_name,csl.cso_name as outlet_name, 2 csc_use from cs_car cc LEFT JOIN cs_state cst on cst.css_number=cc.csc_number " +
						" LEFT JOIN cs_car_model csm on csm.cscm_id = cc.csc_model INNER JOIN cs_outlets csl on csl.cso_id = cc.csc_outlets "+ tmpSQL + ordebySQL;
				log.debug("sql:" + sql);
				list.addAll($.getDao("ccclubs_system").executeQuery(sql));
			}
			
			if(usestatus.equals("0") || usestatus.equals("1")){
				String tmpSQL = whereSQL + " and exists (select 1 from cs_order o where o.cso_car = cc.csc_id and o.cso_status in (1,2,5) and o.cso_outlets_ret "+outletsSQL+")";
				String sql = "SELECT cc.csc_id,cc.csc_host,cc.csc_car_no,cc.csc_model,cc.csc_outlets,cc.csc_longitude,cc.csc_latitude,cc.csc_status,cc.csc_images," +
						" cst.css_endurance,cst.css_ev_battery,cst.css_power,cst.css_charging,csm.cscm_name as model_name,csl.cso_name as outlet_name, 1 csc_use from cs_car cc LEFT JOIN cs_state cst on cst.css_number=cc.csc_number " +
						" LEFT JOIN cs_car_model csm on csm.cscm_id = cc.csc_model INNER JOIN cs_outlets csl on csl.cso_id = cc.csc_outlets "+ tmpSQL + ordebySQL;
				log.debug("sql:" + sql);
				list.addAll($.getDao("ccclubs_system").executeQuery(sql));
			}
			
			List<CarManage> carlist = new ArrayList<CarManage>();
			Map<String, Object> res = new HashMap<String, Object>();
			Rs2bean rs = new Rs2bean();
			for(int i=0; i<list.size(); i++){
				Map map = list.get(i);
				if (map != null) {
					CarManage carm = (CarManage) rs.converttoModel(map, CarManage.class);
					
					//查询使用状态和还入网点，取今天的订单
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MINUTE, 0);
					Date start = calendar.getTime();
					calendar.add(Calendar.DAY_OF_MONTH, 1);
					Date end = calendar.getTime();
					String dateCondition = SystemHelper.formatDate(start, "yyyy-MM-dd");
					String dateConditionEnd = SystemHelper.formatDate(end, "yyyy-MM-dd");
					Map params = $.add(CsOrder.F.csoCar, carm.getCsc_id())
							.add("asc", "(now()-cso_start_time)")
							.add("definex", "  cso_status not in (3,7) and ( cso_start_time BETWEEN STR_TO_DATE('\" + dateCondition + \" 00:00:00','%Y-%m-%d %H:%i:%s') and STR_TO_DATE('\" + dateConditionEnd +\" 23:59:59','%Y-%m-%d %H:%i:%s') or cso_finish_time   BETWEEN STR_TO_DATE('2018-08-28 00:00:00','%Y-%m-%d %H:%i:%s') AND STR_TO_DATE('2018-08-29 23:59:59','%Y-%m-%d %H:%i:%s')  )");
					List<CsOrder> ol = csOrderService.getCsOrderList(params, -1);
					
					if(!ol.isEmpty()){
						log.debug("-----size:"+ol.size());
						
						CsOrder order = ol.get(0);
						//取出该车最近的一个订单
						if(carm.getCsc_use().equals("1")){
							String retOutlet = order.get$csoOutletsRet().getCsoName();
							if($.empty(retOutlet)) retOutlet = order.get$csoOutlets().getCsoName();
							carm.setOutlet_name(retOutlet);
						}
						
						//使用状态不限或者与车辆状态符合，则加入返回列表
						if(usestatus.equals("0") || carm.getCsc_use().equals(usestatus)){
							carlist.add(carm);
						}
						
						//时间轴
						List<Map<String, String>> timelineList = new ArrayList<Map<String,String>>();
						for(int j=0; j<ol.size(); j++){
							CsOrder co = ol.get(j);
							Date finish = co.getCsoRetTime()!=null?co.getCsoRetTime():co.getCsoFinishTime();
							if(co.getCsoFinishTime().after(du.StringtoDate(dateConditionEnd+" 23:59:59", null))){
								finish = du.StringtoDate(dateConditionEnd+" 23:59:59", null);
							}
							//
							if(co.getCsoStartTime().before(du.StringtoDate(dateCondition+" 00:00:00", null)) 
								&&	co.getCsoFinishTime().after(du.StringtoDate(dateCondition+" 00:00:00", null))	
									){
								co.setCsoStartTime(du.StringtoDate(dateCondition+" 00:00:00", null));
							}
							//
							long width = (finish.getTime() - co.getCsoStartTime().getTime()) / 1000 / 60;
							long left = (co.getCsoStartTime().getTime() -  du.StringtoDate(dateCondition + " 00:00:00", null).getTime()) / 1000 / 60;
							if (left < 0 || width < 0){
								continue;
							}
							String[] timeline = "{left_value}%;{widh_value}%;".replace("{left_value}", LZ.digit(left * 100 / 48.0 / 60.0, 2)).replace("{widh_value}", LZ.digit(width * 100 / 48.0 / 60.0, 2)).split(";");
							timelineList.add($.add("left", timeline[0]).add("width", timeline[1]));
						}
						carm.setTimeline(timelineList);
					}else{
						carlist.add(carm);
					}
				}
			}
			
			re.setState(true).setCode(200).setMessage("车辆管理").setData(carlist);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("车辆管理发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 区域
	 */
	public void area(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			String token = $.getString("sessionToken");
			log.debug("区域：token=" + token);
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			SrvUserExp userExp = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			String sql = "select * from cs_area where csa_status=1 and csa_id in " +
					"(select cso_area from cs_outlets where cso_status=1 and cso_id in " +
					"(" + userExp.getSueOutlets() + "))";
			List<Map> list = $.getDao("ccclubs_system").executeQuery(sql);
			
			re.setState(true).setCode(200).setMessage("区域").setData(list);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("区域发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	/**
	 * 区域关联网点
	 */
	public void area_outlet(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			SrvUserExp userExp = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			String area = $.getString("area");
			log.debug("区域关联网点：token=" + token + ",area=" + area);
			List<CsOutlets> list = csOutletsService.getCsOutletsList($.add("definex", " cso_id in (" + userExp.getSueOutlets() + ")")
					.add(CsOutlets.F.csoStatus, 1).add(CsOutlets.F.csoArea, area), -1);
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			for(CsOutlets cot : list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cso_id", cot.getCsoId());
				map.put("cso_address", cot.getCsoAddress());
				map.put("cso_details", cot.getCsoDetails());
				map.put("cso_door_time", cot.getCsoDoorTime());
				map.put("cso_latitude", cot.getCsoLatitude());
				map.put("cso_longitude", cot.getCsoLongitude());
				map.put("cso_name", cot.getCsoName());
				map.put("cso_space", cot.getCsoSpace());
				res.add(map);
			}
			
			re.setState(true).setCode(200).setMessage("区域").setData(res);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("区域发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 车辆详情
	 */
	public void car_detail(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		DateUtil du = new DateUtil();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String carid = $.getString("carid");
			log.debug("车辆详情：token=" + token + ",carid=" + carid);
			if($.empty(carid)){
				re.setState(false).setMessage("车辆id为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Map<String, Object> res = new HashMap<String, Object>();
			
			String sql = "select cc.csc_id,cc.csc_host,cc.csc_car_no,cc.csc_outlets,cc.csc_longitude,cc.csc_latitude,cc.csc_status,cc.csc_images," +
					"cst.css_endurance,cst.css_ev_battery,cst.css_charging,csm.cscm_name as model_name,csl.cso_name as outlet_name from cs_car cc " +
					"LEFT JOIN cs_state cst on cst.css_number=cc.csc_number LEFT JOIN cs_car_model csm on csm.cscm_id = cc.csc_model " +
					"LEFT JOIN cs_outlets csl on csl.cso_id = cc.csc_outlets where cc.csc_id=" + carid;
			
			List<Map> list = $.getDao("ccclubs_system").executeQuery(sql);
			Rs2bean rs = new Rs2bean();
			Map map = list.get(0);
			CarManage carm = null;
			if (map != null) {
				carm = (CarManage) rs.converttoModel(map, CarManage.class);
			}
			res.put("car", carm);
			
			int type = 0;  CsOrder ord = null;
			Map params = $.add(CsOrder.F.csoCar, carm.getCsc_id()).add("desc", "cso_start_time")
					.add("definex", "cso_status in (0,1,2,5) and cso_start_time<now()");
			List<CsOrder> ol = csOrderService.getCsOrderList(params, 2);
			if(!CollectionUtils.isEmpty(ol)){	//当前有订单
				ord = ol.get(0);
				type = 1;
			}else{								//下一个订单
				params = $.add(CsOrder.F.csoCar, carm.getCsc_id()).add("desc", "cso_start_time")
						.add("definex", "cso_status in (0) and now() < cso_start_time");
				ol = csOrderService.getCsOrderList(params, 2);
				if(!CollectionUtils.isEmpty(ol)){	//当前有订单
					ord = ol.get(0);
					type = 2;
				}
			}
			
			if(type == 0){
				carm.setCsc_use("2");
			}else{
				carm.setCsc_use("1");
			}
			
			//上下线记录
			List<CsCarOn> list_on = csCarOnService.getCsCarOnList($.add("desc", "csco_id").add(CsCarOn.F.cscoCar, carm.getCsc_id()), -1);
			List<Online> onlinelist = new ArrayList<Online>();
			if(!CollectionUtils.isEmpty(list_on)){
				CsCarOn caron = list_on.get(0);
				Online on = new Online();
				Online off = new Online(); 
				off.setId(caron.getCscoId().toString());
				off.setOper_time(du.dateToString(caron.getCscoOffTime(), null));
				off.setOper("下线");
				off.setOper_user(caron.getCscoOffUser$());
				off.setRemark(caron.getCscoRemark());
				off.setTag(caron.get$cscoProperty()!=null ? caron.get$cscoProperty().getSpName() : "");
				
				on.setId(caron.getCscoId().toString());
				on.setOper_time(du.dateToString(caron.getCscoOnTime(), null));
				on.setOper("上线");
				on.setOper_user(caron.getCscoOnUser$());
				on.setRemark(caron.getCscoOnRemark());
			
				if(caron.getCscoOnTime()!=null)onlinelist.add(on);
				if(caron.getCscoOffTime()!=null)onlinelist.add(off);
			}
			
			res.put("online", onlinelist);
			
			Map<String, Object> ordermap = new HashMap<String, Object>();
			if(ord !=null){
				ordermap.put("cso_id", ord.getCsoId());
				ordermap.put("cso_start_time", du.dateToString(ord.getCsoStartTime(), null));
				ordermap.put("cso_finish_time", du.dateToString(ord.getCsoFinishTime(), null));
				ordermap.put("order_outlet_name", ord.get$csoOutlets().getCsoName());
				ordermap.put("order_retoutlet_name", ord.get$csoOutletsRet().getCsoName());
			}
			
			ordermap.put("ordtype", type);		//0：无订单，1：当前订单，2：下一个订单
			res.put("order", ordermap);
			
			re.setState(true).setCode(200).setMessage("车辆详情").setData(res);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("车辆详情发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 上下线详情
	 */
	public void online_detail(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		DateUtil du = new DateUtil();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String onlineid = $.getString("onlineid");
			//上下线标识，1为上线，2为下线
			String on = $.getString("on");
			log.debug("上下线详情：token=" + token + ",onlineid=" + onlineid + ",on=" + on);
			if($.empty(onlineid)){
				re.setState(false).setMessage("上下线id为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}

			CsCarOn caron = csCarOnService.getCsCarOnById(Long.parseLong(onlineid));
			Online online = new Online();
			if(on.equals("1")){
				online.setId(onlineid);
				online.setOper("上线");
				online.setOper_time(du.dateToString(caron.getCscoOnTime(), null));
				online.setOper_user(caron.getCscoOnUser$());
				online.setRemark(caron.getCscoOnRemark());
				
			}else if(on.equals("2")){
				online.setId(onlineid);
				online.setOper("下线");
				online.setOper_time(du.dateToString(caron.getCscoOffTime(), null));
				online.setOper_user(caron.getCscoOffUser$());
				online.setRemark(caron.getCscoRemark());
				online.setTag(caron.get$cscoProperty()!=null ? caron.get$cscoProperty().getSpName() : "");
			}
			
			re.setState(true).setCode(200).setMessage("上下线详情").setData(online);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("上下线详情发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 个人中心
	 */
	public void user(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			String token = $.getString("sessionToken");
			log.debug("个人中心：token=" + token);
			ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String sql = "select su.su_id,su.su_username,su.su_real_name,su.su_mobile,su.su_email,sue.sue_outlets as outlets," +
					"srg.sg_name as su_group,srh.sh_name as host from srv_user su left JOIN srv_user_exp sue on sue.sue_user=su.su_id " +
					"LEFT JOIN srv_group srg on srg.sg_id=su.su_group_id LEFT JOIN srv_host srh on srh.sh_id=su.su_host " +
					"where su.su_id=" + user.getSuId();
			List<Map> list = $.getDao("ccclubs_system").executeQuery(sql);
			Rs2bean rs = new Rs2bean();
			Map map = list.get(0);
			User uo = null;
			if (map != null) {
				uo = (User) rs.converttoModel(map, User.class);
			}
			
//			//网点
//			String outlets_all = uo.getOutlets();
//			outlets_all = outlets_all.replaceAll("#", "");
//			outlets_all = outlets_all.replaceAll(" ", "");
//			String[] outlets_str = outlets_all.split(",");
			
			SrvUserExp srvUserExp = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			List<CsOutlets> outletList = srvUserExp.get$sueOutlets();
			String outlets = "";
			for(CsOutlets coo : outletList){
				outlets += coo.getCsoName() + ";";
			}
			uo.setOutlets(outlets);
			
			re.setState(true).setCode(200).setMessage("个人中心").setData(uo);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("个人中心发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 修改密码
	 */
	public void modify_password(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			String token = $.getString("sessionToken");
			log.debug("修改密码：token=" + token);
			ISrvUserService srvUserService = $.GetSpringBean("srvUserService");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String oldpass = $.getString("oldpass");
			String newpass = $.getString("newpass");
			String confirmpass = $.getString("confirmpass");
			if($.empty(oldpass) || $.empty(newpass) || $.empty(confirmpass)){
				re.setState(false).setMessage("密码不能为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			if(newpass.length()<6){
				re.setState(false).setMessage("密码不能少于6位").setCode(203);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			if(!newpass.equals(confirmpass)){
				re.setState(false).setMessage("两次密码不一致").setCode(204);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			user.setSuPassword($.md5(newpass));
			srvUserService.updateSrvUser(user);
			
			re.setState(true).setCode(200).setMessage("密码修改成功");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("修改密码发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 拼装SrvProperty
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getOfflineCase(Map params){
		List<SrvProperty> propertyList = SrvProperty.getSrvPropertyList(params, -1);
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (SrvProperty srvProperty : propertyList) {
			map = new HashMap<String, Object>();
			map.put("tag_id", srvProperty.getSpId$());
			map.put("tag",srvProperty.getSpName$());
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * type:0:远程开门 1:远程关门 2:允许启动 17:远程鸣笛
	 */
	public void send_remote(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String type = $.getString("type");
			if($.empty(type)){
				re.setState(false).setMessage("请选择控制类型").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long carId = $.getLong("csc_id",0l);
			if(carId == 0l){
				re.setState(false).setMessage("请上传车辆ID").setCode(204);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long caseId = $.getLong("case_id", 0L);
//			if(caseId == -1L){
//				re.setState(false).setMessage("请选择控制原因").setCode(205);
//				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
//				return;
//			}
			
			CsCar car = CsCar.get(carId);
			CsState state = CsState.getCsState($.add(CsState.F.cssNumber, car.getCscNumber()));
			if (state != null) {
				if (new Date().getTime() - state.getCssAddTime().getTime() <= 1000 * 60 * 1) {
					CsOrder ord = state.get$cssOrder();
					if (ord!=null && (ord.getCsoStatus()==0 || ord.getCsoStatus()==1 || ord.getCsoStatus()==2 || ord.getCsoStatus()==5)) {
						re.setState(false).setMessage("车辆已有订单，不可远程控制！").setCode(204);
						jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
						return;
					}
				}
			}
			
			WeixinHelper.remoteController(carId, type, user.getSuId(),caseId);
			//外勤车辆整备记录
			String memberLocation = $.getString("memberLocation"); //人员位置 
			updateRemoteControlCount( user, car, type, caseId,memberLocation );

			
			
			re.setState(true).setCode(200).setMessage("远程控制发送成功");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch (Exception e){
			e.printStackTrace();
			log.error("远程开门发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 车辆部位接口
	 */
	public void car_parts(){
		ResponseEnvelope<Map<String, Object>> re = new ResponseEnvelope<Map<String, Object>>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Integer parent = $.getInteger("parent_id",0);
			List<Map<String, Object>> mapList = getCarParts(parent);
			for (Map<String, Object> map : mapList) {
				List<Map<String, Object>> childMapList = getCarParts(Integer.parseInt(map.get("cscp_id").toString()));
				map.put("child", childMapList);
			}
			
			re.setState(true).setCode(200).setMessage("车辆部位接口").setList(mapList);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("车辆部位接口发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 拼装车部件数据
	 * @param parent
	 * @return
	 */
	public List<Map<String, Object>> getCarParts(int parent){
		List<CsCarParts> carParts = CsCarParts.getCsCarPartsList($.add(CsCarParts.F.cscpParentId, parent), -1);
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>(carParts.size());
		Map<String, Object> map;
		for (CsCarParts csCarParts : carParts) {
			map = new HashMap<String, Object>();
			map.put("cscp_id", csCarParts.getCscpId());
			map.put("cscp_name", csCarParts.getCscpName$());
			map.put("cscp_byname", csCarParts.getCscpByname$());
			mapList.add(map);
		}
		return mapList;
	}
	
	/**
	 * 运维人员车况反馈
	 * @return
	 */
	public void put_car_issue(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long carId = $.getLong("csc_id", 0l);
			if(carId == 0){
				re.setState(false).setMessage("车辆ID错误").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long parts_id = $.getLong("parts_id",0l);
			if(parts_id == 0){
				re.setState(false).setMessage("车部件ID错误").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String describe = $.getString("describe");
			if($.empty(describe)){
				re.setState(false).setMessage("问题描述不能为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Integer type = $.getInteger("type",0);
			if(type == 0){
				re.setState(false).setMessage("问题类型不能为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long count = CsCarSysIssue.getCsCarSysIssueCount($.add(CsCarSysIssue.F.cscsiCar, carId).$(CsCarSysIssue.F.cscsiParts,parts_id));
			
			if(count.intValue() > 0){
				re.setState(false).setMessage("部位已反馈过问题，请返回点击车况标签进行修改").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			re.setState(true).setCode(200).setMessage("车况反馈");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("运维人员车况反馈发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	/**
	 * 修改当前里程
	 */
	public void update_maintain(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Long carId = $.getLong("csc_id", 0l);
			if(carId.intValue() == 0){
				re.setState(false).setMessage("车辆ID不能为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			final CsCar csCar = CsCar.get(carId);
			if(csCar == null){
				re.setState(false).setMessage("未查找到车辆").setCode(204);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			
			Double curr_km = $.getDouble("curr_km",0d);
			
			CsMaintain csMaintain = CsMaintain.getCsMaintain($.add(CsMaintain.F.csmNumber, carId).add("desc", "csm_id"));
			if(csMaintain == null){
				csMaintain = new CsMaintain();
				csMaintain.setCsmHost(csCar.getCscHost());
				csMaintain.setCsmType((short)8);
				csMaintain.setCsmFeeType((short)0);
				csMaintain.setCsmAddTime(new Date());
				csMaintain.setCsmStatus((short)0);
				csMaintain.setCsmNumber(carId);
				csMaintain.setCsmModel(csCar.getCscModel());
				csMaintain.setCsmCurrKm(curr_km.intValue());
				csMaintain.setCsmExpectBy((short)0);
			}else{
				csMaintain.setCsmCurrKm(curr_km.intValue());
			}
			if(csMaintain.getCsmCurrKm() != null && csMaintain.getCsmCurrKm() > curr_km.intValue()){
				re.setState(false).setMessage("里程数错误").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			csCar.setCscMaintKm(curr_km.intValue());
			
			csCarService.updateCsCar$NotNull(csCar);
			if(csMaintain.getCsmId() == null){
				csMaintain.save();
			}else{
				csMaintainService.updateCsMaintain$NotNull(csMaintain);
			}
			
			re.setState(true).setCode(200).setMessage("公里数保存成功");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("修改当前里程发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 *个人信息网点 
	 */
	public void person_outlets(){
		ResponseEnvelope<Map<String, Object>> re = new ResponseEnvelope<Map<String, Object>>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			SrvUserExp srvUserExp = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			
			List<CsOutlets> outletList = srvUserExp.get$sueOutlets();
			
			ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>(outletList.size());
			Map<String, Object> map;
			for (CsOutlets outlets : outletList) {
				map = new HashMap<String, Object>();
				map.put("id", outlets.getCsoId());
				map.put("name", outlets.getCsoName$());
				mapList.add(map);
			}
			
			re.setState(true).setCode(200).setMessage("获取个人网点列表").setList(mapList);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("个人信息网点发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	String getPileTypeText(int type){
		if(type == 1){
			return "快充";
		}else if(type == 2){
			return "慢充";
		}
		return "";
	}
	
	/**
	 * 电桩问题列表
	 */
	public void problem_list(){
		ResponseEnvelope<Map<String, Object>> re = new ResponseEnvelope<Map<String, Object>>();
		HttpServletResponse response = ServletActionContext.getResponse();
		DateUtil du = new DateUtil();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			Short type = $.getShort("type", (short)7);
			
			String outletsIds = $.or(SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId())).getSueOutlets(), "");
			List<TbProblem> problemList = tbProblemService.getTbProblemList($.add(TbProblem.F.tbpType, type)
					.add("definex", " tbp_outlets in ("+outletsIds+")")
					.add("desc", "tbp_add_time"), -1);
			
			List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>(problemList.size());
			Map<String, Object> map;
			for (TbProblem p : problemList) {
				if(p != null){
					
				}
				map = new HashMap<String, Object>();
				map.put("tbp_id", p.getTbpId$());
				map.put("tbp_status", p.getTbpStatus());
				map.put("cso_name", p.getTbpRelate$());
				map.put("position", p.getTbpSpecialData$());
				map.put("tbp_profile", p.getTbpProfile$());
				map.put("thp_user", p.getTbpAdder$());
				map.put("add_time", du.dateToString(p.getTbpAddTime(), null));
				map.put("outlet_id", p.getTbpOutlets());
				map.put("outlet", p.get$tbpOutlets()==null?"":p.get$tbpOutlets().getCsoName());
				map.put("user", p.get$tbpEditor()==null?"":p.get$tbpEditor().getSuRealName());
				Long editorId = (p.get$tbpEditor()==null?0L:p.getTbpEditor());
				map.put("canEdit", editorId.longValue() == user.getSuId().longValue() ? 1 : 0);
				
				String data = $.or(p.getTbpSpecialData(), "");
				Map json = APICallHelper.fromJson(data); 
				if(json!=null){
					Object pileTypeObj = $.or(json.get("chargeType"), 0);
					Object problemTypeObj = $.or(json.get("problemType"), 0);
					SrvProperty sp = SrvProperty.get(Double.valueOf(problemTypeObj.toString()).longValue());
					map.put("pileType", pileTypeObj);
					map.put("problemType", problemTypeObj);
					map.put("pileTypeText", data.contains("chargeType")? (getPileTypeText(Double.valueOf(pileTypeObj.toString()).intValue())) :"" );
					map.put("problemTypeText", sp!=null? sp.getSpName() : "");
				}else{
					map.put("pileType", null);
					map.put("problemType", null);
					map.put("pileTypeText", null);
					map.put("problemTypeText", null);
				}
				mapList.add(map);
			}
			re.setState(true).setCode(200).setMessage("获取问题列表").setList(mapList);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统属性发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 电桩问题上报
	 */
	public void submit_problem(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			final SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String problem_id = $.getString("problem_id");
			Long cso_id = $.getLong("cso_id",0l);
			String position = $.getString("position");
			String tbp_profile = $.getString("tbp_profile");
			Short type = $.getShort("type",(short)6);
			Short pileType = $.getShort("pileType", (short)1);
			Long problemType  = $.getLong("problemType");	
			String execute = $.getString("execute");
			ITbProblemService tbProblemService = $.GetSpringBean("tbProblemService");
			
			TbProblem tbProblem = null;
			if($.empty(problem_id)){
				tbProblem = new TbProblem();
			}else{
				tbProblem = tbProblemService.getTbProblemById(Long.parseLong(problem_id));
			}
			if(!$.empty(execute)){
				if(tbProblem != null){
					tbProblem.setTbpStatus(new Short(execute));}
			}else{
				tbProblem.setTbpOutlets(cso_id);
				tbProblem.setTbpAdder(user.getSuId());
				tbProblem.setTbpSpecialData($.json($.add("chargeType", pileType).add("problemType", problemType)));
				tbProblem.setTbpProfile(tbp_profile);
				tbProblem.setTbpHost($.empty(user.getSuHost())?1l:Long.parseLong(user.getSuHost().split(",")[0]));
				tbProblem.setTbpType(type);
				tbProblem.setTbpEditor(user.getSuId());
				tbProblem.setTbpDealer(user.getSuId());
				tbProblem.setTbpAddTime(new Date());
				tbProblem.setTbpUpdateTime(new Date());
				tbProblem.setTbpStatus((short)0);
			}
			tbProblem.save();
			
			re.setState(true).setCode(200).setMessage("电桩问题上报");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("电桩问题上报发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 例行检查
	 */
	public void usual_check(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		DateUtil du = new DateUtil();
		try {
			ICsStateService csStateService = $.GetSpringBean("csStateService");
			ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String carno = $.getString("carno");
			log.debug("例行检查：token=" + token + ",carno=" + carno);
			if($.empty(carno)){
				re.setState(false).setMessage("车牌为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			CsCar car = csCarService.getCsCar($.add(CsCar.F.cscCarNo, carno));
			CarManage cm = new CarManage();
			if(car != null){
				CsState csState = csStateService.getCsState($.add(CsState.F.cssCar, car.getCscId()));
				List<CsOrder> orderlist = csOrderService.getCsOrderList($.add(CsOrder.F.csoCar, car.getCscId()).add("desc", "cso_start_time"), -1);
				CsOrder order = null;
				Map<String, Object> res = new HashMap<String, Object>();
				cm.setCsc_id(car.getCscId().toString());cm.setCsc_host(car.getCscHost().toString());cm.setCsc_outlets(car.getCscOutlets().toString());
				cm.setCsc_car_no(car.getCscCarNo());cm.setCsc_images(car.getCscImages());cm.setCsc_model(car.getCscModel().toString());
				cm.setOutlet_name(car.get$cscOutlets().getCsoName());cm.setCsc_latitude(car.getCscLatitude$());cm.setCsc_longitude(car.getCscLongitude$());
				cm.setCsc_status(car.getCscStatus().toString());cm.setOutlet_name(car.get$cscOutlets().getCsoName());
				if(csState != null){
					cm.setCss_charging(csState.getCssCharging());
					cm.setCss_endurance(csState.getCssEvBattery());
				}
				if(orderlist.size() != 0){
					order = orderlist.get(0);
					res.put("exptime", du.getDistanceTime(new Date(), order.getCsoStartTime()));
				}else{
					res.put("exptime", "");
				}
				res.put("car", cm);
				
				List<CsCarSysIssue> issuelist = csCarSysIssueService.getCsCarSysIssueList($.add(CsCarSysIssue.F.cscsiCar, car.getCscId()), -1);
				List<Map<String, Object>> issueRetList = new ArrayList<Map<String,Object>>();
				for(CsCarSysIssue issue : issuelist){
					Map<String, Object> issue_map = new HashMap<String, Object>();
					issue_map.put("cscsi_id", issue.getCscsiId()); 
					issue_map.put("cscsi_content", issue.getCscsiContent());
					issue_map.put("cscsi_image", issue.getCscsiImage());
					issue_map.put("cscsi_parts_str", issue.getCscsiPartsStr());
					issue_map.put("cscsi_type", issue.getCscsiType());
					issueRetList.add(issue_map);
				}
				
				res.put("issue", issueRetList);
				
				re.setState(true).setCode(200).setMessage("例行检查").setData(res);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
			}else{
				re.setSuccess(true).setState(false).setMessage("车辆为空").setCode(203);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("例行检查发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 保洁项目
	 */
	public void clean(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			List<SrvProperty> list = srvPropertyService.getSrvPropertyList(
					$.add("definex", "sp_parent=(select sp_id FROM srv_property where sp_flag='yw_clean')"), -1);
			List<Map<String, Object>> prolist = new ArrayList<Map<String, Object>>();
			for(SrvProperty sp : list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sp_id", sp.getSpId());
				map.put("sp_name", sp.getSpName());
				prolist.add(map);
			}
			re.setState(true).setCode(200).setMessage("保洁项目").setData(prolist);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保洁项目发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 保洁项目检查提交
	 */
	public void clean_check(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
			ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String carid = $.getString("carid");
			log.debug("保洁项目检查提交：token=" + token + ",carid=" + carid);
			if($.empty(carid)){
				re.setState(false).setMessage("车辆id为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String cleans = $.getString("cleans");
			if($.empty(cleans)){
				re.setState(false).setMessage("参数不正确").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
		
			List<SrvProperty> plist = srvPropertyService.getSrvPropertyList($.add("definex", "sp_id in ("+cleans+") "), -1);
			for(SrvProperty prop : plist){
				CsCleanRecord csCleanRecord = new CsCleanRecord();
				csCleanRecord.setCscrCar(Long.parseLong(carid));
				csCleanRecord.setCscrItemName(prop.getSpName());
				csCleanRecord.setCscrItemId(prop.getSpId());
				csCleanRecord.setCscrAddtime(new Date());
				csCleanRecord.setCscrUpdatetime(new Date());
				csCleanRecord.setCscrSelection((short)1);
				csCleanRecord.setCscrStatus((short)1);
				csCleanRecord.setCscrUser(user.getSuId());
				csCleanRecord.save();
			}
			
			re.setState(true).setCode(200).setMessage("保洁项目检查提交");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保洁项目检查提交发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 还车检查
	 */
	public void return_check(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String carno = $.getString("carno");
			log.debug("还车检查：token=" + token + ",carno=" + carno);
			if($.empty(carno)){
				re.setState(false).setMessage("车牌为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			CsCar car = csCarService.getCsCar($.add(CsCar.F.cscCarNo, carno));
			
			List<SrvProperty> list = srvPropertyService.getSrvPropertyList(
					$.add("definex", "sp_parent=(select sp_id FROM srv_property where sp_flag='yw_return_check')"), -1);
			ICsCarSysIssueService csCarSysIssueService = $.GetSpringBean("csCarSysIssueService");
			List<CsCarSysIssue> issuelist = csCarSysIssueService.getCsCarSysIssueList($.add(CsCarSysIssue.F.cscsiCar, car.getCscId()), -1);
			List<Map<String, Object>> prolist = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> isslist = new ArrayList<Map<String, Object>>();
			
			ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
			for(SrvProperty sp : list){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sp_id", sp.getSpId());
				map.put("sp_name", sp.getSpName());

				CsRetcheck retcheck = csRetcheckService.getCsRetcheck($.add(CsRetcheck.F.csrItemId, sp.getSpId()).add(CsRetcheck.F.csrCar, car.getCscId()).add(CsRetcheck.F.csrState, 0).add(CsRetcheck.F.csrStatus, 1));
				if(retcheck == null){
					map.put("status", 0);
				}else{
					map.put("status", retcheck.getCsrItemState());
				}
				
				prolist.add(map);
			}
			
			//TODO 问题处理状态 
			for(CsCarSysIssue ccs : issuelist){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cscsi_id", ccs.getCscsiId());
				map.put("cscsi_content", ccs.getCscsiContent());
				map.put("cscsi_type", ccs.getCscsiType());
				map.put("cscsi_image", ccs.getCscsiImage());
				map.put("cscsi_parts_str", ccs.getCscsiPartsStr());
				
				map.put("status", ccs.getCscsiStatus());  //1:存在 0:不存在
				isslist.add(map);
			}
			
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("check", prolist);res.put("issue", isslist);
			re.setState(true).setCode(200).setMessage("还车检查").setData(res);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("还车检查发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 检查的项目状态
	 */
	public void check_status(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
			ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
			ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String project = $.getString("project");//yw_return_check,yw_clean
			if($.empty(project)){
				re.setState(false).setMessage("项目为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			List<SrvProperty> plist = srvPropertyService.getSrvPropertyList(
					$.add("definex", "sp_parent=(select sp_id FROM srv_property where sp_flag='" + project + "')"), -1);
			List<ProjectCheck> list = new ArrayList<ProjectCheck>();
			for(SrvProperty sp : plist){
				ProjectCheck pc = new ProjectCheck();
				pc.setId(sp.getSpId().toString());
				pc.setName(sp.getSpName());
				if(project.equals("yw_return_check")){
					CsRetcheck cte = csRetcheckService.getCsRetcheck($.add(CsRetcheck.F.csrItemId, sp.getSpId()));
					pc.setStatus(cte.getCsrItemState().toString());
				}else if(project.equals("yw_clean")){
					CsCleanRecord ccr = csCleanRecordService.getCsCleanRecord($.add(CsCleanRecord.F.cscrItemId, sp.getSpId()));
					pc.setStatus(ccr.getCscrSelection().toString());
				}
				list.add(pc);
			}
			
			re.setState(true).setCode(200).setMessage("还车检查").setData(list);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("检查的项目状态发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 还车检查提交
	 */
	@SuppressWarnings("unchecked")
	public void rercheck_sub(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try { 
			ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
			ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String carid = $.getString("carid");
			log.debug("还车检查：token=" + token + ",carid=" + carid);
			if($.empty(carid)){
				re.setState(false).setMessage("车辆id为空").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			String rets = $.getString("rets");
			String issues_str = $.getString("issues_data");; 
			if($.empty(rets)){
				re.setState(false).setMessage("参数不正确").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			//TODO 批量事物提交	
			Map<String,Object> mapx =  APICallHelper.fromJson("{\"rets\":"+rets+"}");
			Map<String,Object> map_issue =  APICallHelper.fromJson("{\"issues_str\":"+issues_str+"}");
			if(mapx !=null){
				List<Map<String, Object>> dataList = (List)mapx.get("rets");
				if(dataList!=null){
					for(Map<String,Object> map : dataList){
						Double ret = (Double)map.get("sp_id");
						String status = (String)map.get("status");
						
						//更新CsRetcheck
						CsRetcheck retcheck = csRetcheckService.getCsRetcheck($.add(CsRetcheck.F.csrItemId, ret.longValue())
								.add(CsRetcheck.F.csrCar, carid).add(CsRetcheck.F.csrStatus, 1));
						if(retcheck == null){
							SrvProperty prop = SrvProperty.get(ret.longValue());
							CsRetcheck csRetcheck = new CsRetcheck();
							csRetcheck.setCsrItemName(prop.getSpName());
							csRetcheck.setCsrAddtime(new Date());
							csRetcheck.setCsrCar(Long.parseLong(carid));
							csRetcheck.setCsrItemId(ret.longValue());
							csRetcheck.setCsrState((short)0);
							csRetcheck.setCsrUser(user.getSuId());
							csRetcheck.setCsrStatus((short)1);
							csRetcheck.setCsrItemState(new Short(status));
							csRetcheck.save(); 
						}else{
							retcheck.setCsrCar(Long.parseLong(carid));
							retcheck.setCsrItemState(new Short(status));
							retcheck.setCsrUpdatetime(new Date());
							retcheck.setCsrUser(user.getSuId());
							csRetcheckService.updateCsRetcheck(retcheck);
						}
					}
				}
			}else{
				re.setState(true).setCode(201).setMessage("数据格式有误！");
			}
			
			if(map_issue!=null){
				List<Map<String, Object>> dataList = (List)map_issue.get("issues_str");
				if(dataList!=null){
					for(Map<String,Object> map : dataList){
						Double id = (Double)map.get("issue_id");
						String status = (String)map.get("status");
						
						CsCarSysIssue issue = CsCarSysIssue.getCsCarSysIssue($.add(CsCarSysIssue.F.cscsiId, id));
						issue.setCscsiStatus(Short.valueOf(status));
						issue.setCscsiUpdateTime(new Date());
						issue.setCscsiSrvuser(user.getSuId());
						issue.update();
					}
				}
			}
			
			re.setState(true).setCode(200).setMessage("还车检查提交");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("还车检查提交发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 操作记录提交
	 */
	public void log_submit(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ICsLogService csLogService = $.GetSpringBean("csLogService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String module = $.getString("module");//操作模块
			String carid = $.getString("carid");//车辆id
			
			CsLog log = new CsLog();
			log.setCslHost($.empty(user.getSuHost())?1l:Long.parseLong(user.getSuHost().split(",")[0]));log.setCslName(module + "保存");
			log.setCslUser(user.getSuId());log.setCslAddTime(new Date());
			log.setCslClass("AppAction");log.setCslRelate(Long.parseLong(carid));
			csLogService.updateCsLog(log);
			
			re.setState(true).setCode(200).setMessage("操作记录提交");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("操作记录提交发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 部位问题提交
	 */
	public void part_problem_sub(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			String part = $.getString("part");//部位id
			String type = $.getString("type");//1:刮痕 2:凹痕 3:损坏 4:裂痕 5:非正常工作 6:缺失零件
			String carid = $.getString("carid");
			String images = $.getString("images");
			if($.empty(type) || $.empty(part) || $.empty(carid)){
				re.setState(false).setMessage("参数异常").setCode(202);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			CsCarParts ccp = csCarPartsService.getCsCarPartsById(Long.parseLong(part));
			CsCarSysIssue ccsi = new CsCarSysIssue();
			ccsi.setCscsiType(Short.valueOf(type));
			ccsi.setCscsiAddTime(new Date());ccsi.setCscsiCar(Long.parseLong(carid));ccsi.setCscsiImage(images);
			ccsi.setCscsiHost($.empty(user.getSuHost())?1l:Long.parseLong(user.getSuHost().split(",")[0]));ccsi.setCscsiParts(Long.parseLong(part));
			ccsi.setCscsiPartsStr(ccp.getCscpByname());ccsi.setCscsiStatus((short)1);ccsi.setCscsiSrvuser(user.getSuId());
			ccsi.setCscsiUpdateTime(new Date());
			ccsi.save();
			
			re.setState(true).setCode(200).setMessage("部位问题提交");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("部件问题提交发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 报警与提醒
	 */
	public void warning(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			String type = $.getString("type", "");
			type = type.replace("null", "");
			log.debug("[报警与提醒] type:" + type);
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			SrvUserExp srvUserExp = SrvUserExp.getSrvUserExp($.add(SrvUserExp.F.sueUser, user.getSuId()));
			String outletsIds = $.or(srvUserExp.getSueOutlets(), "");
			
			ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
			String definexSQL = " cssa_outlets in ("+outletsIds+")";
			LzMap map = $.add("definex", "cssa_type in (1,2,3,4) and" + definexSQL).add(CsServAlarm.F.cssaStatus, 1);
			if(!$.empty(type) && !type.equals("0")){
				map = $.add(CsServAlarm.F.cssaType, type).add("definex", definexSQL).add(CsServAlarm.F.cssaStatus, 1);
			}
			List<CsServAlarm> list = csServAlarmService.getCsServAlarmList(map, -1);
			
			List<Warning> res = new ArrayList<Warning>();
			for(CsServAlarm ces : list){
				Warning warn = new Warning();
				warn.setCse_add_time(ces.getCssaAddTime$());
				warn.setCse_content(ces.getCssaContent());
				warn.setCse_id(String.valueOf(ces.getCssaId()));
				warn.setCse_status(String.valueOf(ces.getCssaStatus()));
				warn.setCse_type(String.valueOf(ces.getCssaType()));
				warn.setCse_update_time(ces.getCssaUpdateTime$());
				warn.setUser_id(user.getSuId().toString());
				warn.setUser_name(user.getSuRealName());
				warn.setType_name(ces.getCssaType$());
				res.add(warn);
			}
			
			re.setState(true).setCode(200).setMessage("报警与提醒").setData(res);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("报警与提醒发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 报警与提醒类型
	 */
	public void warning_type(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			
			List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
			res.add($.add("id", "0").add("name", "全部提醒"));
			res.add($.add("id", "1").add("name", "车辆掉线提醒"));
			res.add($.add("id", "2").add("name", "小电瓶亏电提醒"));
			res.add($.add("id", "3").add("name", "车辆低电量提醒"));
			res.add($.add("id", "4").add("name", "车辆还入提醒"));
			
//			ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
//			List<CsEventType> list = csEventTypeService.getCsEventTypeList($.add(CsEventType.F.csetAction, "yw_app").add(CsEventType.F.csetStatus, 1), -1);
//			for(CsEventType cset : list){
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("id", cset.getCsetId());
//				map.put("name", cset.getCsetName());
//				res.add(map);
//			}
			
			re.setState(true).setCode(200).setMessage("报警与提醒类型").setData(res);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("报警与提醒类型发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 启动管理
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void startManage(){
		com.ccclubs.util.DateUtil dateUtil = new com.ccclubs.util.DateUtil();
		Map<String,Object> subMap = new HashMap<String, Object>();
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
				String version = $.getString("version", "");
				if($.empty(version)){
					re.setState(false).setMessage("版本号不能为空").setCode(201);
					jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
					return;
				}
				//新升级策略业务逻辑如下2016年4月：
				boolean isNewVersion = false;
				//CsUpdate currentVersioin = CsUpdate.where().csuStatus(1).csuType(UPDATE_TYPE).csuVersion(version).get();
				//Date before = currentVersioin.getCsuAddTime();
				//Date temp = dateUtil.StringtoDate(dateUtil.addDateMinut(dateUtil.dateToString(before, NORMAL_DATE_FORMAT), 10), NORMAL_DATE_FORMAT);
				CsUpdate.M param  =  CsUpdate.where().add("csuStatus", (short)1).add("csuType", UPDATE_TYPE).add("definex", "(csu_version>'"+version+"')").add("asc", "csu_add_time");
				List<CsUpdate> ls = CsUpdate.getCsUpdateList(param, -1);
				CsUpdate csUpdate = null;
				for(CsUpdate c:ls){//找大版本，如果有，直接升级大版本
					String[] v = c.getCsuVersion().split("\\.");    //1.2.0
					if(0 == Integer.valueOf(v[2])){
						csUpdate = c;
						break;
					}
				}
				if(null == csUpdate && ls.size()>0){//如果没有大版本，找最近的小版本
					csUpdate = ls.get(0);
				}
				
				if(null != csUpdate){
					String newVersion = csUpdate.getCsuVersion();
					log.info("========db version="+newVersion+" =======app version="+version);
					//前期例外处理
					if(version.length() == 3){
						subMap.put("isUpdate", true);
						subMap.put("isBigVersion", true);
						subMap.put("url", csUpdate.getCsuFile());
						subMap.put("version", csUpdate.getCsuVersion());
					}
					//==================================================================================>
					//比较两个版本号的大小
					String[] v1 = newVersion.split("\\."); //1.4.0
					String[] v2 = version.split("\\.");    //1.2.0
					if(Integer.valueOf(v1[0]) > Integer.valueOf(v2[0])){//比较最高位，如果true表示有更新
						isNewVersion = true;
					}else if(Integer.valueOf(v1[0])  == Integer.valueOf(v2[0]) 
							&& Integer.valueOf(v1[1]) >  Integer.valueOf(v2[1])){//如果高位相等，比较中间位
						isNewVersion = true;
					}else if(Integer.valueOf(v1[0])  == Integer.valueOf(v2[0]) 
							&& Integer.valueOf(v1[1]) == Integer.valueOf(v2[1])
							&& Integer.valueOf(v1[2]) >  Integer.valueOf(v2[2])){//如果高位和中间位都相等，比较最后一位
						isNewVersion = true;
					}else{
						isNewVersion = false;
					}
					String url = "";
					if(isNewVersion){//有新版本
						subMap.put("isUpdate", true);//有更新
						if(Integer.valueOf(v1[2]) == 0){//有大版本
							subMap.put("isBigVersion", true);
							subMap.put("url", url = csUpdate.getCsuFile());
							subMap.put("version", csUpdate.getCsuVersion());
						}else{//小版本
							subMap.put("isBigVersion", false);
							List list = new ArrayList();
							Map<String,Object> m = new HashMap<String, Object>();
							m.put("url", url = csUpdate.getCsuFile());
							m.put("version", csUpdate.getCsuVersion());
							list.add(m);
							subMap.put("list", list);
						}
						
					}else{
						subMap.put("isUpdate", false);
					}
					subMap.put("url", url);
				}
			}catch (Exception e) {
				log.error("启动管理发生异常：" + e.getMessage());
				re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			re.setState(true).setData(subMap).setCode(200).setMessage("启动管理");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
	}
	
	
	
	
	/**
	 * 审核中心
	 */
	public void verify_center(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			//
			ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
			ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
			//
			String mobile = $.getString("mobile");
			CsMember csMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, mobile));
			
			Map<String, Object> mapData = new HashMap<String, Object>();
			if(csMember!=null) {
				//0:未认证 1:已认证 2:等待认证 3:认证失败
				
				Short	offline=csMember.getCsmVOffline();
				String  offlineString=null;
				if(null==offline||0==offline.intValue()) {
					offlineString="未认证";
				}else if(1==offline.intValue()) {
					offlineString="已认证";
				}else if(2==offline.intValue()) {
					offlineString="等待认证";
				}else if(3==offline.intValue()) {
					offlineString="认证失败";
				}
				mapData.put("offline",offlineString );
				
				CsMemberInfo csMemberInfo=csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiId, csMember.getCsmInfo()));    
				if(csMemberInfo!=null) {
					mapData.put("name", csMemberInfo.getCsmiName());
					mapData.put("certifyNum", csMemberInfo.getCsmiCertifyNum());
					mapData.put("onCertifyImage", csMemberInfo.getCsmiOnCertifyImage());
					mapData.put("certifyImage", csMemberInfo.getCsmiCertifyImage());//反面
					mapData.put("driverImage", csMemberInfo.getCsmiDriverImage());
					mapData.put("checkPhoto", csMemberInfo.getCsmiCheckPhoto());//机审照片
				}
			}
			//
			re.setState(true).setCode(200).setMessage("审核中心").setData(mapData);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("首页菜单发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	/**
	 * 审核提交
	 */
	public void verify_submit(){
		ResponseEnvelope<String> re = new ResponseEnvelope<String>();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String token = $.getString("sessionToken");
			SrvUser user = OauthUtils.getSrvOauth(token);
			if(user == null){
				re.setState(false).setMessage("未登录").setCode(201);
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
			}
			//
			String mobile = $.getString("mobile");
			//0:未认证 1:已认证  3:认证失败
			Short type = $.getShort("type");
			//
			if(type.intValue()!=0&&type.intValue()!=1&&type.intValue()!=3) {
				re.setState(false).setCode(200).setMessage("认证值不在范围内");
				jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
				return;
				
			}
			//
			String checkPhoto = $.getString("checkPhoto");
			//
			ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
			ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
			//
			CsMember csMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, mobile));
			if(csMember!=null) {
				CsMember memberTemp=new CsMember();
				//
				memberTemp.setCsmId(csMember.getCsmId());
				memberTemp.setCsmVOffline(type);
				csMemberService.updateCsMember$NotNull(memberTemp);
				//
				CsMemberInfo csMemberInfo=csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiId, csMember.getCsmInfo()));    
				if(checkPhoto!=null&&StringUtils.isNotBlank(checkPhoto)) {
					if(csMemberInfo.getCsmiCheckPhoto()==null|| !csMemberInfo.getCsmiCheckPhoto().equals(checkPhoto)) {
						csMemberInfo.setCsmiCheckPhoto(checkPhoto);
						csMemberInfo.setCsmiUpdateTime(new Date());
						csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
					}
				}
			}
			
			//
			re.setState(true).setCode(200).setMessage("审核提交成功");
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}catch(Exception e){
			e.printStackTrace();
			log.error("首页菜单发生异常：" + e.getMessage());
			re.setSuccess(false).setState(false).setMessage("系统繁忙，请稍后再试").setCode(9999);
			jsonWriter.outJsonOrJsonp($.getString("callback"), re, response);
		}
	}
	
	
	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}
	
	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}
	
	public ICsCarService getCsCarService() {
		return csCarService;
	}
	
	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}

	public ICsCarOnService getCsCarOnService() {
		return csCarOnService;
	}

	public void setCsCarOnService(ICsCarOnService csCarOnService) {
		this.csCarOnService = csCarOnService;
	}

	public ICsMaintainService getCsMaintainService() {
		return csMaintainService;
	}

	public void setCsMaintainService(ICsMaintainService csMaintainService) {
		this.csMaintainService = csMaintainService;
	}

	public ITbProblemService getTbProblemService() {
		return tbProblemService;
	}

	public void setTbProblemService(ITbProblemService tbProblemService) {
		this.tbProblemService = tbProblemService;
	}
	
}
