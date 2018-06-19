package com.ccclubs.action.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.manage.WeixinHelper;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsMaintain;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsOutsideQr;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ICsAreaService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsMaintainService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.util.Ctrl;


public class AjaxAction {
	
	
	ICsCarService csCarService ;
	CsCar csCar;
	ICsMaintainService csMaintainService;
	CsMaintain csMaintain;
	/*
	 * 车牌选择
	 * 
	 * @Ajax
	 * 
	 */
	HashMap<String, Object> Params = new HashMap<String, Object>();
	public String selectcard(){
		try {
			String cardnumber="";
			SrvUser user =$.getSession("ccclubs_login");
			cardnumber=$.getString("cardnumber");
			if(cardnumber!=null&&!cardnumber.equals("")){
				CsCar car = CsCar.where().cscCarNo(cardnumber).add("HOSTS",user.getSuHost()).get();//查询该车所有信息
				if(car!=null){
					cardmode(car.getCscModel());
					Params.put("car",car);
					return $.SendAjax(Params, "UTF-8");
				}else{
					return $.SendAjax("", "UTF-8");
				}
			}else{
				return $.SendAjax("", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendAjax("系统错误", "UTF-8");
		}
	};
	/*
	 * 查询车辆类型
	 */
	public String cardmode(Long s){
		try {
			CsCarModel cscarmodel;
			cscarmodel = CsCarModel.getCsCarModel($.add(CsCarModel.F.cscmId, s));//查询该车类型信息
			Params.put("cscarmodel",cscarmodel);
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendAjax("系统错误", "UTF-8");
		}
		return "";
	}
	/*
	 * 手机开关门
	 * 
	 * @return
	 */
	public String orderoperate(){
		try {
			short conType=-1;
			if($.getString("operation").equals("开门")){
				conType=0;
			}else if($.getString("operation").equals("关门")){
				conType=1;
			}else if($.getString("operation").equals("鸣笛")){
				conType=17;
			}else if($.getString("operation").equals("允许启动")){
				conType=2;
			}else{
				return $.SendAjax("请正确按键", "UTF-8");
			}
			long carId=-1;
			carId = Integer.valueOf($.getString("cscId")).intValue();
			System.out.println(carId);
			WeixinHelper.remoteController(carId, ""+conType+"", 0);
			return $.SendAjax("操作成功", "UTF-8");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return $.SendAjax("系统错误", "UTF-8");
		}
	}
	/*
	 * 查询人员是否存在并判断是否绑定会员卡
	 * 
	 * @return
	 * 
	 *1表示已经绑定过了
	 *2表示没有查到这个会员 
	 * 
	 */
	public String selectuser(){
		try {
			String usertle=$.getString("usertle");
			CsMember csMember=CsMember.getCsMember($.add(CsMember.F.csmMobile, usertle));
			if(csMember!=null){
				if(csMember.getCsmEvcard()==null||csMember.getCsmEvcard().equals("")){
					return $.SendAjax(csMember, "UTF-8");
				}else{
					return $.SendAjax("1", "UTF-8");
				}
				
			}else{
				return $.SendAjax("2", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendAjax("系统错误请重试", "UTF-8");
		}
		
	}
	/*
	 * 查询人员是否存在并判断是否绑定会员卡
	 * 
	 * @return
	 * 
	 *1表示已经绑定过了
	 *2表示没有查到这个会员 
	 * 
	 */
	public String usermember(){
		try {
			String usertle=$.getString("usertle");
			CsMember csMember=CsMember.getCsMember($.add(CsMember.F.csmMobile, usertle));
			if(csMember!=null){
				return $.SendAjax(csMember, "UTF-8");
			}else{
				return $.SendAjax("2", "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return $.SendAjax("系统错误请重试", "UTF-8");
		}
		
	}
	/*
	 * 车辆上下线
	 * 
	 * @return
	 * 
	 */
	public String carstatus(){
		try{
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCarToken = $.getString("csCarToken");
			final String sessionCsCarToken = $.getSession("csCarToken");
			if($.equals(requestCsCarToken,sessionCsCarToken)){
				$.removeSession("csCarToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCarService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
							SrvUser user =$.getSession("ccclubs_login");//备注信息
							String password = $.getString("cscOnPsd");
							Short status;
							if($.getString("usertle").equals("1")){
								status = 1;
							}else if($.getString("usertle").equals("2")){
								status = 2;
							}else{
								status = 3;
							}
							if(!$.empty(password))//密码用md5加密
								password = $.md5(password);
							
							
							//ids->id
							
							List<Long> num=new java.util.ArrayList<Long>();
							if($.empty($.getString("ids"))){
								num.add(csCar.getCscId());
							}else{
								for(String sid:$.getString("ids").split(","))
									num.add(Long.valueOf(sid));
							}
							
							List<String> results = new java.util.ArrayList<String>();
							for(Long id:num){
								String sResult = com.ccclubs.service.common.impl.SchedulingService.onlineCar(id, status, user.getSuRealName(), password);
								results.add(sResult);
							}
							String strTips = "处理成功：";
							for(String tip:results){
								strTips+=tip;
							}
							Params.put("status", strTips);
							$.SendAjax(Params, "UTF-8");
						return null;
					}
				});
			}else{
				Params.put("status", "对不起，请勿重复提交");
				$.SendAjax(Params, "UTF-8");
			}
			
			return null;
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			Params.put("status", "系统繁忙,请稍候再试");
			$.SendAjax(Params, "UTF-8");
		}
		//回到列表页
		return null;
	}
	/**
	 * 地区加载
	 * @ return
	 */
	@SuppressWarnings("unchecked")
	public String outles(){
		String areaId = $.getString("area_id");
		List<CsOutlets> csOutlets=CsOutlets.getCsOutletsList($.add(CsOutlets.F.csoArea, areaId), -1);
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		for(CsOutlets csOutlet:csOutlets){
			@SuppressWarnings("rawtypes")
			Map map = new HashMap();
			map.put("csoId", csOutlet.getCsoId());
			map.put("csoName", csOutlet.getCsoName());
			list.add(map);
		}
		return $.SendAjax(list, "UTF-8");
	}
	
	
	/**
	 * 车辆调度管理
	 * @return
	 */
	public String dispatch(){
		try{
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCarToken = $.getString("csCarToken");
			final String sessionCsCarToken = $.getSession("csCarToken");
			if($.equals(requestCsCarToken,sessionCsCarToken)){
				$.removeSession("csCarToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCarService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						SrvUser user =$.getSession("ccclubs_login");//备注信息
						String remark=user.getSuRealName();
						Long targetOutlets  = Long.parseLong($.getString("outlets"));//目标网点
						Long cscId =Long.parseLong($.getString("cscId"));
						//插入调度记录数据
						if(com.ccclubs.model.CsScheduling.where().cssStatus((short)0).cssCar(cscId).cssTimeStart(new Date()).count()>0){
							Params.put("status", "当前车辆后面可能有定单，不能调度");
							$.SendAjax(Params, "UTF-8");
						}else{
							com.ccclubs.service.common.impl.SchedulingService.scheduling(
									cscId,targetOutlets, targetOutlets, new Date(), new Date(), 
								com.ccclubs.service.common.impl.SchedulingService.DispatchType.干预调度
								, null, remark 
							);
							//更新车辆网点
							new CsCar(cscId).cscOutlets(targetOutlets).update();
							Params.put("status", "车辆调度成功");
							$.SendAjax(Params, "UTF-8");
						}
						return null;
					}
				});
			}else{
				Params.put("status", "对不起，请勿重复提交");
				$.SendAjax(Params, "UTF-8");
			}
			
			return $.SendAjax($.getString("entrypoint"), "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			Params.put("status", "系统繁忙,请稍候再试");
			$.SendAjax(Params, "UTF-8");
		}
		//回到列表页
		return null;
	}
	/*
	 * 
	 * 更新车辆公里数
	 * 
	 * @ return
	 * 
	 */
	
	public String maintain(){
		try{
			String number = $.getString("carNo");
			Integer km = $.getInteger("currKm");
						
			if(km==null)
				return $.SendAjax($.add("success", false).add("message", "输入当前公里数"), "UTF-8");
			if($.empty(number))
				return $.SendAjax($.add("success", false).add("message", "请输入车牌号码"), "UTF-8");
			if(number.length()<5)
				return $.SendAjax($.add("success", false).add("message", "车牌号码不能小于5位"), "UTF-8");
			
			ICsCarService csCarService=$.GetSpringBean("csCarService");
			CsCar csCar = csCarService.getCsCar($.add("definex","csc_car_no like '%"+number+"%'"));
			if(csCar==null)
				return $.SendAjax($.add("success", false).add("message", "车牌号码错误"), "UTF-8");
			CsMaintain csMaintain = csMaintainService.getCsMaintain($.add(CsMaintain.F.csmNumber, csCar.getCscId()));
			if(csMaintain==null){
				return $.SendAjax($.add("success", false).add("message", "当前车辆还没有保养基础数据，提交失败"), "UTF-8");
			}
			String  strHistory = csMaintain.getCsmKmHistory();
			if(strHistory==null)
				strHistory = "";
			if($.empty(strHistory) && csMaintain.getCsmCurrKm()!=null){
				strHistory = $.date(new Date(), "yyyy-MM-dd")+"保存初始公里("+csMaintain.getCsmCurrKm()+")<br/>";
			}
			String name=$.getString("playcarname");
			strHistory+=$.date(new Date(), "yyyy-MM-dd")+name+"添加当前公里数("+km+")<br/>";

			csMaintainService.updateCsMaintainByConfirm(
				$.add(CsMaintain.F.csmCurrKm, km).add(CsMaintain.F.csmKmHistory, strHistory),
				$.add(CsMaintain.F.csmId, csMaintain.getCsmId()).add("confirm", 1)
			);
			return $.SendAjax($.add("success", true).add("message","车辆保养数据提交成功"), "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请联系管理员"), "UTF-8");
		}
	}
	
	/*
	 * 巡视车辆签到
	 * 
	 * @return
	 * 
	 */
	public String patrolcheck(){
		try {
			//获取区域列表	
			Long host = SYSTEM.getDefaultHost();
			if(host==null)
				host = SrvHost.Get($.add(SrvHost.F.shFlag, "杭州")).getShId();
			Long csoqAdder = $.getLong("csoqAdder");//操作人
			String csoqCar = $.getString("csoqCar");//车辆
			Long csoqType = $.getLong("csoqType");//作业类型
			String csoqUserPosStart = $.getString("csoqUserPosStart");//人员位置
			String csoqUsePosEnd=$.getString("csoqUsePosEnd");//人员位置
			//车辆查询
			ICsCarService csCarService = $.getBean("csCarService");
			CsCar carInfo = csCarService.getCsCar($.add(CsCar.F.cscNumber, csoqCar));
			if(carInfo==null||carInfo.equals("")){
				Params.put("status", "查无此车，请重试");
				$.SendAjax(Params, "UTF-8");
			}else{
				double radLat1 = rad(Double.valueOf(csoqUserPosStart));
				double radLat2 = rad(carInfo.getCscLatitude());
				double a = radLat1 - radLat2;
				double b = rad(Double.valueOf(csoqUsePosEnd)) - rad(carInfo.getCscLongitude());
				double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
				s = s * EARTH_RADIUS;
				s = s * 1000; // 换算成米
				CsOutsideQr csOutsideQr=new CsOutsideQr();
				csOutsideQr.setNotNull(host, csoqAdder, carInfo.getCscId(), csoqType, csoqUserPosStart, Double.toString(carInfo.getCscLatitude()), (int) s, new Date(), (short)1);
				csOutsideQr.setCsoqUsePosEnd(csoqUsePosEnd);
				csOutsideQr.setCsoqCarPosEnd(Double.toString(carInfo.getCscLongitude()));
				csOutsideQr.save();
				Params.put("status", "巡车成功！");
				$.SendAjax(Params, "UTF-8");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			Params.put("status", "系统错误请联系管理员！");
			$.SendAjax(Params, "UTF-8");
			return null;
		}
	}
	/**
	 * 计算两点间的距离
	 */
	private final double	EARTH_RADIUS	= 6378.137; // 地球半径

	private double rad(double d) {
		return d * Math.PI / 180.0; // 计算弧长
	}
	/***************** set get 方法 *************/
	public ICsCarService getCsCarService() {
		return csCarService;
	}
	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}
	public CsCar getCsCar() {
		return csCar;
	}
	public ICsMaintainService getCsMaintainService() {
		return csMaintainService;
	}
	public void setCsMaintainService(ICsMaintainService csMaintainService) {
		this.csMaintainService = csMaintainService;
	}
	public CsMaintain getCsMaintain() {
		return csMaintain;
	}
	public void setCsMaintain(CsMaintain csMaintain) {
		this.csMaintain = csMaintain;
	}
}
