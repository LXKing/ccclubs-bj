package com.ccclubs.action.app.official;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.app.official.model.JsonFormat;
import com.ccclubs.action.app.official.util.DriverOauthUtils;
import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.action.app.official.util.OauthUtils;
import com.ccclubs.action.app.official.util.SessionMgr;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.CacheStoreHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsCarRes;
import com.ccclubs.model.CsDriver;
import com.ccclubs.model.CsDriverCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsContentService;
import com.ccclubs.service.admin.ICsDriverCarService;
import com.ccclubs.service.admin.ICsDriverService;
import com.ccclubs.service.admin.ICsEvCardService;
import com.ccclubs.service.admin.ICsFavoriteAddressService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.ccclubs.service.admin.ICsUnitMtService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.ccclubs.service.admin.ICsUpdateService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICMBPayService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonPayService;
import com.ccclubs.service.common.ICommonUnitService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.util.UUIDGenerator;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;

@SuppressWarnings({ "unused" })
public class AppDriverAction {

	ICsDriverService csDriverService;
	ICsSpecialCarService csSpecialCarService;
	ISrvPropertyService srvPropertyService;
	ICsMemberService csMemberService;
	ICsOutletsService csOutletsService;
	ICsOrderService csOrderService;
	ICsCarService csCarService;
	ICsGoodsService csGoodsService;
	ICsPriceService csPriceService;
	ICsProductService csProductService;
	ICsUserTypeService csUserTypeService;
	ICommonMoneyService commonMoneyService;
	ICommonDisposeService commonDisposeService;
	ICommonOrderService commonOrderService;
	ICsUpdateService csUpdateService;
	ICsMemberInfoService csMemberInfoService;
	ICsUnitOrderService csUnitOrderService;
	ICsMemberShipService csMemberShipService;
	ICsPowerPileService csPowerPileService;
	ICsEvCardService csEvCardService;
	ICsUnitGroupService csUnitGroupService;
	ICsRefundService csRefundService;
	ICommonUnitService commonUnitService;
	ICsUnitMtService csUnitMtService;
	ICsStateService csStateService;
	ICsFavoriteAddressService csFavoriteAddressService;
	ICsRecordService csRecordService;
	ICsItemService csItemService;
	ICsAlipayRecordService csAlipayRecordService;
	ICommonPayService commonPayService;
	ICsRechargeCardService csRechargeCardService;
	ICsContentService csContentService;
	ICMBPayService cmbPayService;
	ICsDriverCarService csDriverCarService;
	ICsCarModelService csCarModelService;
	
	static Map<String, String> stateMap = new HashMap<String, String>();
	
	final static String CHARSET = "utf-8";
	final static String FORGET_SMS_CODE = "forgetcode"; // 忘记密码的校验码
	final static String FORGET_TOKEN = "forget_token"; // 忘记密码校验通过的状态token
	final static String LATITUDE_TOKEN=" latitude_token";
	final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private String returnError(String code, String message) {
		$.SendHtml($.json(JsonFormat.error(code, message)), CHARSET);
		return null;
	}

	private String returnError(Exception ex) {
		if (ex instanceof MessageException) {
			MessageException e = (MessageException) ex;
			return returnError(e.getCode() + "", e.getMessage());
		}
		ex.printStackTrace();
		Logger.getRootLogger().error(ex.getMessage(),ex);
		return returnError("9999", SYSTEM.ERROR_TIPS);
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			String strUsername = $.getString("username", "");
			String strPass = $.getString("password", "");

			if (SystemHelper.isNullOrEmpty(strUsername)) {
				return returnError("102", "您还没有输入帐号");
			}
			if (SystemHelper.isNullOrEmpty(strPass)) {
				return returnError("103", "您还没有输入密码");
			}

			// String strPassword = RSAUtil.decrypt(strPass);
			String strPassword = strPass;
			CsDriver csDriver = null;
			csDriver = csDriverService.getCsDriver($.Map("csdMobile", strUsername));

			if (csDriver == null) {
				return returnError("104", "您输入的帐号不正确，请重新输入");
			}
			if (csDriver.getCsdStatus() != 1) {
				return returnError("105", "该帐户已经失效，请联系北京出行");
			}

			if (!$.md5(strPassword).equals(csDriver.getCsdPassword().toUpperCase())) {
				return returnError("106", "您输入的密码不正确，请重新输入");
			}

			Map<String, Object> driverData = getDriverInfo(csDriver);
			
			String token = UUIDGenerator.getUUID();
			DriverOauthUtils.saveToken(csDriver.getCsdId().toString(), token);
			
			
			JsonFormat result = JsonFormat.success().setData($.add("access_token", token).add("id", String.valueOf(csDriver.getCsdId())).add("driver", driverData));
			return $.SendHtml($.json(result), SYSTEM.UTF8);
		} catch (Exception ex) {
			return returnError(ex);
		}
	}

	/**
	 * 找回密码-发送短信码
	 * 
	 * @return
	 */
	public String forgetsms() {
		try {
			String mobile = $.getString("mobile", "");
			if ($.empty(mobile))
				return returnError("101", "手机号码不能为空");
			if (!SystemHelper.isMobile(mobile))
				return returnError("102", "手机号码格式错误");

			String VALID_LAST = "forgetsms-" + mobile;
			// 验证短信发送频率
			Date last = SessionMgr.get(mobile, "forgetsms");
			if (last != null) {
				if ((new Date().getTime() - last.getTime()) < SYSTEM.MINUTE)
					return returnError("103", "两次注册短信验证码发送时间间隔不能小于一分钟");
			}
			String strCode = $.zerofill($.rand(9999), 4);
			CsDriver csDriver = CsDriver.where().csdMobile(mobile).get();
			if (csDriver == null) {
				return returnError("104", "手机号码不存在");
			}
			Boolean bOk = UtilHelper.sendTemplateSMS(csDriver.getCsdHost(), "FORGOT_PWD", mobile, CommonMessage.MSG0201, SMSType.提示类短信, $.add("code", strCode));
			if (bOk == true) {
				SessionMgr.put(mobile, FORGET_SMS_CODE, strCode);
				return $.SendHtml($.json(JsonFormat.success()), CHARSET);
			} else {
				return returnError("104", "短信发送失败");
			}
		} catch (Exception ex) {
			return returnError(ex);
		}
	}

	/**
	 * 找回密码-验证手机号
	 * 
	 * @return
	 */
	public String forgetvalid() {
		try {
			String csmMobile = $.getString("mobile", "");
			// String strValidCode = $.getString("txtCode", "");
			String strValidMobCode = $.getString("validCode", "");
			if ($.empty(csmMobile)) {
				return returnError("101", "请输入手机号");
			}
			// if ($.empty(strValidCode)) {
			// // $.SetTips("未输入验证码，不能修改密码");
			// return $.SendHtml("103", "UTF-8");
			// }
			if ($.empty(strValidMobCode)) {
				// $.SetTips("未输入短信校验码，不能修改密码");
				return returnError("104", "未输入短信校验码，不能修改密码");
			}
			if (!SystemHelper.isMobile(csmMobile)) {
				// $.SetTips("手机号码格式错误");
				return returnError("105", "手机号码格式错误");
			}
			if (!$.equals(strValidMobCode, (String) SessionMgr.get(csmMobile, FORGET_SMS_CODE))) {
				return returnError("110", "短信校验码输入错误");
			}
			CsDriver existDriver = csDriverService.getCsDriver($.add(CsDriver.F.csdMobile, csmMobile));
			if (existDriver == null)
				return returnError("111", "手机号不存在，不能修改密码");

			String forget_token = UUIDGenerator.getUUID();
			SessionMgr.put(existDriver.getCsdMobile(), FORGET_TOKEN, forget_token);
			return $.SendHtml($.json(JsonFormat.success().addValue("forget_token", forget_token)), CHARSET);
		} catch (Exception ex) {
			return returnError(ex);
		}
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	public String resetpwd() {
		try {

			// 验证当前手机号
			if ($.getString("forget_token") == null) {
				return returnError("102", "参数缺失");
			}

			String mobile = $.getString("mobile", "");
			String csmPassword = $.getString("txtPassWord", "");
			String rePwd = $.getString("txtRePassWord", "");
			if ($.empty(csmPassword)) {
				return returnError("103", "未输入密码");
			}
			if (csmPassword.length() < 6) {
				return returnError("104", "请输入6到20位有效密码");
			}
			if (!$.equals(csmPassword, rePwd)) {
				return returnError("105", "两次密码输入不一致");
			}
			if (mobile == null) {
				return returnError("106", "手机号码不能为空");
			}
			// 当前重置密码用户
			CsDriver csDriver = csDriverService.getCsDriver($.add(CsDriver.F.csdMobile, mobile));
			if (csDriver == null)
				return returnError("111", "手机号不存在，不能修改密码");

			String token = SessionMgr.get(csDriver.getCsdMobile(), FORGET_TOKEN);
			if (token != null && !$.getString("forget_token").equals(token)) {
				return returnError("101", "非法请求");
			}

			// ----------------------------------------------------------验证通过，重置密码
			csDriverService.updateCsDriver$NotNull(new CsDriver().csdPassword($.md5(rePwd)).csdId(csDriver.getCsdId()));
			// $.SetTips("重置密码成功，进入到成功页面");
			// 清空忘记密码的token
			SessionMgr.remove(csDriver.getCsdMobile(), FORGET_SMS_CODE);
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception ex) {
			return returnError(ex);
		}
	}

	/**
	 * 获取司机当前车辆状态
	 * @return
	 */
	public String driverState(){
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}
			String definex = "( " + CsDriverCar.C.csdcState +" < 2)";

			CsDriverCar csDriverCar = csDriverCarService.getCsDriverCar($.add("definex", definex).add(CsDriverCar.F.csdcDriver, driver.getCsdId()));
			Map<String, Object> data = new HashMap<String, Object>();
			if(csDriverCar != null){
				data.put(CsDriverCar.F.csdcCar, csDriverCar.getCsdcCar());
				data.put(CsDriverCar.F.csdcState,  csDriverCar.getCsdcState());
				data.put(CsDriverCar.F.csdcId, csDriverCar.getCsdcId());
				Map<String, Object> carMap = new HashMap<String, Object>();
				carMap.put(CsCarRes.F.cscrCarNo, csDriverCar.get$csdcCar().getCscrCarNo());
				carMap.put(CsCarRes.F.cscrColor, csDriverCar.get$csdcCar().getCscrColor$());
				carMap.put(CsCarRes.F.cscrCarmodel, csDriverCar.get$csdcCar().getCscrCarmodel$() );
				CsCarModel carModel = csCarModelService.getCsCarModelById(new Long(csDriverCar.get$csdcCar().getCscrCarmodel()));
				carMap.put(CsCarModel.F.cscmImage, carModel.getCscmImage());
				data.put("carMap", carMap);
				Map<String, Object> driverMap =  new HashMap<String, Object>();
				driverMap.put(CsDriver.F.csdName, csDriverCar.get$csdcDriver().getCsdName());
				driverMap.put(CsDriver.F.csdMobile, csDriverCar.get$csdcDriver().getCsdMobile());
				driverMap.put(CsDriver.F.csdPhoto, csDriverCar.get$csdcDriver().getCsdPhoto());
				data.put("driverMap", driverMap);
				
				

				definex = "( " +  CsSpecialCar.C.csscStatus+ " in (3,8,9) )";
				List<CsSpecialCar> specialCarList = csSpecialCarService.getCsSpecialCarList($.add("definex", definex).add(CsSpecialCar.F.csscDrivercar, csDriverCar.getCsdcId())
						.add("asc", CsSpecialCar.C.csscStatus +" desc ," +CsSpecialCar.C.csscStartTime), -1);
				List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
				for (CsSpecialCar csSpecialCar : specialCarList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("csscId", csSpecialCar.getCsscId());
					map.put("csscHost", csSpecialCar.getCsscHost());
					map.put("shName", csSpecialCar.getCsscHost$());
					map.put("csscName", csSpecialCar.getCsscName());
					map.put("csscMobile", csSpecialCar.getCsscMobile());
					map.put("csscCount", csSpecialCar.getCsscCount$());
					map.put("csscCarNumber", csSpecialCar.getCsscCarNumber$());
					map.put("csscDriverColor", csSpecialCar.getCsscDriverColor());
					map.put("csscDriverCarInfo", csSpecialCar.getCsscDriverCarInfo$());
					map.put("csscMsg", csSpecialCar.getCsscMsg$());
					map.put("csscStartPoint", csSpecialCar.getCsscStartPoint$());
					map.put("csscStartAddr", csSpecialCar.getCsscStartAddr$());
					map.put("csscStartTime", csSpecialCar.getCsscStartTime$());
					map.put("csscFinishPoint", csSpecialCar.getCsscFinishPoint$());
					map.put("csscFinishAddr", csSpecialCar.getCsscFinishAddr$());
					map.put("csscFinishTime", csSpecialCar.getCsscFinishTime$());
					map.put("csscDuration", csSpecialCar.getCsscDuration$());
					map.put("csscRealDur", csSpecialCar.getCsscRealDur$());
					map.put("csscKilo", csSpecialCar.getCsscKilo$());
					map.put("csscScope", csSpecialCar.getCsscScope$());
					map.put("csscAssess", csSpecialCar.getCsscAssess$());
					map.put("csscStatus", csSpecialCar.getCsscStatus());
					map.put("csscIsCarpool", csSpecialCar.getCsscIsCarpool$());
					String headerImage = "";
					try{
						headerImage = csSpecialCar.get$csscPerson().get$csupMember().getCsmHeader();
					}catch(Exception e){
						Logger.getRootLogger().error(e.getMessage(),e);
					}
					map.put("csscPersonImg",headerImage);
					if(csSpecialCar.getCsscStartPoint() != null && csSpecialCar.getCsscStartPoint().indexOf(";") != -1){
						map.put("startLongitude", csSpecialCar.getCsscStartPoint().split(";").length>1?csSpecialCar.getCsscStartPoint().split(";")[1]:csSpecialCar.getCsscStartPoint());//经度
						map.put("startLatitude",csSpecialCar.getCsscStartPoint().split(";").length>1? csSpecialCar.getCsscStartPoint().split(";")[0]:csSpecialCar.getCsscStartPoint());//纬度
					}else{
						map.put("startLongitude","0.0");//经度
						map.put("startLatitude","0.0");//纬度
					}
					
					if(csSpecialCar.getCsscFinishPoint() != null && csSpecialCar.getCsscFinishPoint().indexOf(";") != -1){
						map.put("finishLongitude", csSpecialCar.getCsscFinishPoint().split(";").length>1?csSpecialCar.getCsscFinishPoint().split(";")[1]:csSpecialCar.getCsscFinishPoint());//经度
						map.put("finishLatitude", csSpecialCar.getCsscFinishPoint().split(";").length>1?csSpecialCar.getCsscFinishPoint().split(";")[0]:csSpecialCar.getCsscFinishPoint());//纬度
					}else{
						map.put("finishLongitude", "0.0");//经度
						map.put("finishLatitude", "0.0");//纬度
					}
					
					dataList.add(map);
				}
				data.put("orderList", dataList);
			
				
			}
			return $.SendHtml($.json(JsonFormat.success().setData($.$("map", data))), CHARSET);
		} catch (Exception e) {
			// TODO: handle exception
			return returnError(e);
		}
	}
	
	/**
	 * 订单列表
	 * 
	 * @return
	 */
	public String listOrders() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}
			String csscId = $.getString("cssc_id", "");
			if ($.empty(csscId))
				return returnError("101", "请输入订单号");

			return null;
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 开始听单（上线）
	 * 
	 * @return
	 */
	public String onAccept() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			String carId = $.getString("carId", "");
			if ($.empty(carId))
				return returnError("101", "请选择出车车辆");

			CsDriverCar csDriverCar = csDriverCarService.getCsDriverCar($.add("csdcDriver", driver.getCsdId()).add("csdcCar", carId));
			
			if(csDriverCar ==null)
				return returnError("102","无车辆可使用");
			
			if(csDriverCar.getCsdcStatus() != 1)
				return returnError("103",csDriverCar.get$csdcCar().getCscrCarNo()+" 车辆不可用，请联管理员");
			
			if(csDriverCar.getCsdcState() !=2){ // 不是离线状态，不能上线
				return returnError("103","当前是"+csDriverCar.getCsdcState$()+"状态，操作失败！");
			}
			
			csDriverCar.setCsdcState((short)0); 			//上线 
			csDriverCar.setCsdcUpdateTime(new Date());      
			csDriverCar.update();
			
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}
	
	/**
	 * 结束听单（下线）
	 * 
	 * @return
	 */
	public String offAccept() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			String carId = $.getString("carId", "");
			if ($.empty(carId))
				return returnError("101", "请选择出车车辆");

			CsDriverCar csDriverCar = csDriverCarService.getCsDriverCar($.add("csdcDriver", driver.getCsdId()).add("csdcCar", carId));
			
			if(csDriverCar ==null)
				return returnError("102","无车辆可使用");
			
			if(csDriverCar.getCsdcStatus() != 1)
				return returnError("103",csDriverCar.get$csdcCar().getCscrCarNo()+" 车辆不可用，请联管理员");
//			if(csDriverCar.getCsdcState() !=0){ // 不是上线状态，不能上线+
//				return returnError("103","当前是"+csDriverCar.getCsdcState$()+"状态，操作失败！");
//			}
			
			csDriverCar.setCsdcState((short)2); 			//离线
			csDriverCar.setCsdcUpdateTime(new Date());      
			csDriverCar.update();
			
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 接单
	 * 
	 * @return
	 */
	public String ordAccept() {
		try {
			final CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			String carId = $.getString("carId", "");
			if ($.empty(carId))
				return returnError("101", "请选择出车车辆");

			final CsDriverCar csDriverCar = csDriverCarService.getCsDriverCar($.add("csdcDriver", driver.getCsdId()).add("csdcCar", carId));
			if(csDriverCar ==null)
				return returnError("102", "找不到该司机车辆");
			
			if(csDriverCar.getCsdcStatus() != 1)
				return returnError("103", csDriverCar.get$csdcCar().getCscrCarNo()+" 车辆不可用，请联管理员");
			
			if(csDriverCar.getCsdcState() ==2){   // 不是上线状态，不能接单
				return returnError("103", "当前是"+csDriverCar.getCsdcState$()+"状态，操作失败！");
			}
			
			final String csscId = $.getString("specialCarId");
			
			if($.empty(csscId))
				return returnError("104", "请输入接单单号");
			
			final CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar($.Map("csscId", csscId));
			
			if(csSpecialCar == null)
				return returnError("105", "订单号无效");
			
			if(csSpecialCar.getCsscStatus() != 2){
				String msg ="订单已被接，接单失败";
				switch (csSpecialCar.getCsscStatus()) {
				case 5:
					msg ="订单已取消，接单失败";
					break;
				case 6:
					msg = "订单已作废，接单失败";
					break;
				}
				return returnError("106", msg);
			}
			
			csSpecialCarService.executeTransaction(new Function() {
				
				@Override
				public <T> T execute(Object... arg0) {
					
					CsCarRes car = csDriverCar.get$csdcCar();
					
					CsSpecialCar cSpecialCar = new CsSpecialCar(csSpecialCar.getCsscId());
					cSpecialCar.setCsscCarModel(car.getCscrCarmodel() == null ? null : Long.valueOf(car.getCscrCarmodel()));
					cSpecialCar.setCsscCarNumber(csDriverCar.get$csdcCar().getCscrCarNo$());
					cSpecialCar.setCsscDriverName(csDriverCar.get$csdcDriver().getCsdName$());
					cSpecialCar.setCsscDriverPhone(csDriverCar.get$csdcDriver().getCsdMobile());
					cSpecialCar.setCsscDriverImage( csDriverCar.get$csdcDriver().getCsdPhoto$());
					cSpecialCar.setCsscDriverColor(csDriverCar.get$csdcCar().getCscrColor$());
					cSpecialCar.setCsscDrivercar(csDriverCar.getCsdcId());
					cSpecialCar.setCsscStatus((short)3);
					cSpecialCar.update();
					
					//相关司机状态改为“已接单”
					CsDriverCar driverCar = new CsDriverCar(csDriverCar.getCsdcId());
					driverCar.setCsdcState((short)1); 	
					driverCar.update();
					
					//消息通知给用户，订单已经被接单
					List<CsDriver>driverList = CacheStoreHelper.getDriverList(csscId);
					if(driverList == null)
						return null;  
					for (CsDriver csDriver : driverList) {
						if(csDriver.getCsdId$().equals(driver.getCsdId$()))
							continue;
						Map<String, String> extra = new HashMap<String, String>();
						try{
							extra.put("type", "2");														//0实时单，1预约单	2订单被接			
							extra.put("specId", csscId);			//订单ID
							Logger.getLogger("alipay").error("接单订单ID ："+csscId+" 手机号:"+csDriver.getCsdMobile());
							JpushClientHelper.sendMessageWithTag(csDriver.getCsdMobile(),"{\"title\":\"订单已被接单\"}", extra, JpushConfig.OFC_DRIVER_APP);
						}catch(Exception e){
							Logger rentAppLogger = Logger.getLogger("rentApp");
							rentAppLogger.error("submitRentOrder error! data:\t"+$.json(extra), e);
							Logger.getRootLogger().error(e.getMessage(),e);
						}
					
					}
					return null;
				}
			});
			
			String headerImage = "";
			try{
				headerImage = csSpecialCar.get$csscPerson().get$csupMember().getCsmHeader();
			}catch(Exception e){
				Logger.getRootLogger().error(e.getMessage(),e);
			}
			LzMap data = $.add("passagerName", csSpecialCar.getCsscName())
						  .add("passagerMobile", csSpecialCar.getCsscMobile())
						  .add("passagerImage", headerImage);
			
			return $.SendHtml($.json(JsonFormat.success().setData(data)), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 到达出发地点
	 * 
	 * @return
	 */
	public String ordArrive() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			String csscId = $.getString("specialCarId");
			
			if($.empty(csscId))
				return returnError("104","请输入接单单号");
			
			final CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar($.Map("csscId", csscId));
			
			if(csSpecialCar == null)
				return returnError("105","订单号无效");
			
			if(csSpecialCar.getCsscStatus() ==5 || csSpecialCar.getCsscStatus() ==6)
				return returnError("106","订单已取消");
			
			Long count = csSpecialCarService.getCsSpecialCarCount($.Map("csscDrivercar", csSpecialCar.getCsscDrivercar()).add("definex", "(cssc_status in (8,9))"));
			
			if(Integer.parseInt(String.valueOf(count))>0)
				return returnError("107","请先结束其他行进中订单");
			
			//csSpecialCarService.updateCsSpecialCarByConfirm($.add(CsSpecialCar.F.csscDriverArrivaltime, new Date()).add(CsSpecialCar.F.csscStatus, "8"), $.add("csscId", csscId));
			
			csSpecialCar.setCsscDriverArrivaltime(new Date());
			csSpecialCar.setCsscStatus((short)8);
			
			csSpecialCarService.updateCsSpecialCar(csSpecialCar);
			
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 状态上报
	 * 
	 * @param csscId订单ID
	 * @return
	 */
	public String stateReport() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}
			
			String carId = $.getString("carId", "");
			if ($.empty(carId))
				return returnError("101", "请选择出车车辆");
			
			String longitude = $.getString("longitude","");//经度
			
			if($.empty(longitude))
				return returnError("102","经度上传失败");
			
			String latitude = $.getString("latitude","");//纬度

			if($.empty(latitude))
				return returnError("103","维度上传失败");
			
			stateMap.put(carId, longitude+","+latitude);
			
			//TODO
			//SessionMgr.put($.getString("access_token", ""), LATITUDE_TOKEN, longitude+","+latitude);
			
			String csscId = $.getString("csscId", "");
			
			final CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar($.Map("csscId", csscId));
			
			if(csSpecialCar == null)
				return returnError("105","订单号无效");
			
			if(csSpecialCar.getCsscStatus() ==3 || csSpecialCar.getCsscStatus() ==8 || csSpecialCar.getCsscStatus() ==9){
				try{
					Map<String, String> extra = new HashMap<String, String>();
					
					extra.put("specialId", csscId);			//订单ID csSpecialCar.get$csscUnitOrder().get$csuoUnitMember().getCsmMobile()
					Map<String, Object> msgmap = new HashMap<String, Object>();
					msgmap.put("specialId", csscId);
					msgmap.put("longitude",longitude);		//经度
					msgmap.put("latitude", latitude);		//维度 csSpecialCar.get$csscUnitOrder().get$csuoUnitMember().getCsmId$()
					msgmap.put("method", "stateReport");
					JpushClientHelper.sendMessageWithTag(csSpecialCar.get$csscUnitOrder().get$csuoUnitChild().getCsmId$(), $.json(msgmap), extra, JpushConfig.OFC_APP);
				}catch(Exception e){
					Logger.getRootLogger().error(e.getMessage(),e);
				}
			}
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 开始行程
	 * 
	 * @return
	 */
	public String ordBegin() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			String csscId = $.getString("specialCarId");
			
			if($.empty(csscId))
				return returnError("104","请输入接单单号");
			
			final CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar($.Map("csscId", csscId));
			
			if(csSpecialCar == null)
				return returnError("105","订单号无效");
			
			if(csSpecialCar.getCsscStatus() ==5 || csSpecialCar.getCsscStatus() ==6)
				return returnError("106","订单已取消");
			
			csSpecialCar.setCsscStartTime(new Date());
			csSpecialCar.setCsscStatus((short)9);
			csSpecialCarService.updateCsSpecialCar(csSpecialCar);
			
			//csSpecialCarService.updateCsSpecialCarByConfirm($.add(CsSpecialCar.F.csscStartTime, new Date()).add(CsSpecialCar.F.csscStatus, "9"), $.add("csscId", csscId));
			
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 结束行程
	 * @return
	 */
	public String ordEnd() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			final String csscId = $.getString("specialCarId");
			
			if($.empty(csscId))
				return returnError("104","请输入接单单号");
			
			final CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar($.Map("csscId", csscId));
			
			if(csSpecialCar == null)
				return returnError("105","订单号无效");
			
			if(csSpecialCar.getCsscStatus() ==5 || csSpecialCar.getCsscStatus() ==6)
				return returnError("106","订单已取消");
			
			if(csSpecialCar.getCsscStatus()==4 && csSpecialCar.get$csscUnitOrder().getCsuoSettleState()==1)
				return $.SendHtml($.json(JsonFormat.success()), CHARSET);
			
			final Date endTime = new Date();
			final Double mileage = $.getDouble("mileage");
			final String remark = $.getString("remark");
			
			csSpecialCarService.executeTransaction(new Function() {
				
				ICommonUnitService	commonUnitService = $.GetSpringBean("commonUnitService");
				
				@Override
				public <T> T execute(Object... object) {
					
					//结算订单
					CsUnitPerson csup = CsUnitPerson.get(csSpecialCar.getCsscPerson());
					commonUnitService.executeSettleRentOrder(csSpecialCar.getCsscUnitOrder(), csup.getCsupMember(), csup.getCsupMember(), endTime, mileage, remark);
					return null;
				}
			});
			
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 获取当前订单
	 * 
	 * @return
	 */
	public String getCurOrder() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}
			String definex = "( " + CsSpecialCar.C.csscDrivercar + " in (select " + CsDriverCar.C.csdcId + " from cs_driver_car cd where "
					+ CsDriverCar.C.csdcState + " = 1 and " + CsDriverCar.C.csdcDriver + "=" + driver.getCsdId() + ") and " + CsSpecialCar.C.csscStatus
					+ " in (3,8,9))";

			List<CsSpecialCar> specialCarList = csSpecialCarService.getCsSpecialCarList($.add("definex", definex), -1);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			for (CsSpecialCar csSpecialCar : specialCarList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("csscId", csSpecialCar.getCsscId());
				map.put("csscHost", csSpecialCar.getCsscHost());
				map.put("shName", csSpecialCar.getCsscHost$());
				map.put("csscName", csSpecialCar.getCsscName());
				map.put("csscMobile", csSpecialCar.getCsscMobile());
				map.put("csscCount", csSpecialCar.getCsscCount$());
				map.put("csscCarNumber", csSpecialCar.getCsscCarNumber$());
				map.put("csscDriverColor", csSpecialCar.getCsscDriverColor());
				map.put("csscDriverCarInfo", csSpecialCar.getCsscDriverCarInfo$());
				map.put("csscMsg", csSpecialCar.getCsscMsg$());
				map.put("csscStartPoint", csSpecialCar.getCsscStartPoint$());
				map.put("csscStartAddr", csSpecialCar.getCsscStartAddr$());
				map.put("csscStartTime", csSpecialCar.getCsscStartTime$());
				map.put("csscFinishPoint", csSpecialCar.getCsscFinishPoint$());
				map.put("csscFinishAddr", csSpecialCar.getCsscFinishAddr$());
				map.put("csscFinishTime", csSpecialCar.getCsscFinishTime$());
				map.put("csscDuration", csSpecialCar.getCsscDuration$());
				map.put("csscRealDur", csSpecialCar.getCsscRealDur$());
				map.put("csscKilo", csSpecialCar.getCsscKilo$());
				map.put("csscScope", csSpecialCar.getCsscScope$());
				map.put("csscAssess", csSpecialCar.getCsscAssess$());
				map.put("csscStatus", csSpecialCar.getCsscStatus$());
				map.put("csscIsCarpool", csSpecialCar.getCsscIsCarpool$());
				if(csSpecialCar.getCsscStartPoint() != null && csSpecialCar.getCsscStartPoint().indexOf(";") != -1){
					map.put("startLongitude", csSpecialCar.getCsscStartPoint().split(";").length>1?csSpecialCar.getCsscStartPoint().split(";")[1]:csSpecialCar.getCsscStartPoint());//经度
					map.put("startLatitude",csSpecialCar.getCsscStartPoint().split(";").length>1? csSpecialCar.getCsscStartPoint().split(";")[0]:csSpecialCar.getCsscStartPoint());//纬度
				}else{
					map.put("startLongitude","0.0");//经度
					map.put("startLatitude","0.0");//纬度
				}
				
				if(csSpecialCar.getCsscFinishPoint() != null && csSpecialCar.getCsscFinishPoint().indexOf(";") != -1){
					map.put("finishLongitude", csSpecialCar.getCsscFinishPoint().split(";").length>1?csSpecialCar.getCsscFinishPoint().split(";")[1]:csSpecialCar.getCsscFinishPoint());//经度
					map.put("finishLatitude", csSpecialCar.getCsscFinishPoint().split(";").length>1?csSpecialCar.getCsscFinishPoint().split(";")[0]:csSpecialCar.getCsscFinishPoint());//纬度
				}else{
					map.put("finishLongitude", "0.0");//经度
					map.put("finishLatitude", "0.0");//纬度
				}
				dataList.add(map);
			}

			return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", dataList))), CHARSET);
		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 
	 * <li>获取司机个人信息</li> <li>提供司机id，头像，姓名，性别，手机号，业务网点</li>
	 * 
	 * @return
	 */
	public String getDriverInfo() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}
			
			Map<String, Object> data = getDriverInfo(driver);

			return $.SendHtml($.json(JsonFormat.success().setData($.$("map", data))), CHARSET);

		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String modifyPass() {
		try {

			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			String oldPass = $.getString("oldPass", "");
			String newPass = $.getString("newPass", "");
			String newPass2 = $.getString("newPass2", "");

			if (SystemHelper.isNullOrEmpty(oldPass) || SystemHelper.isNullOrEmpty(newPass) || SystemHelper.isNullOrEmpty(newPass2)) {
				return returnError("101", "请输入密码");
			}

			if (!newPass.equals(newPass2)) {
				return returnError("102", "密码不一致");
			}

			CsDriver currentDriver = CsDriver.get(driver.getCsdId());
			if (!$.md5(oldPass).equals(currentDriver.getCsdPassword().toUpperCase())) {
				return returnError("103", "密码不正确");
			}

			currentDriver.setCsdPassword($.md5(newPass));
			currentDriver.update();
			
			return $.SendHtml($.json(JsonFormat.success()), CHARSET);  
		} catch (Exception ex) {
			return returnError(ex);
		}
	}

	/**
	 * 获取司机绑定车辆
	 * 
	 * @return
	 */
	public String getDriverCars() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}
			
			String definex="("+CsDriverCar.C.csdcCar+" not in (select "+CsDriverCar.C.csdcCar+" from cs_driver_car where "+CsDriverCar.C.csdcState+" !=2 and "+CsDriverCar.C.csdcDriver +" != "+driver.getCsdId$()+"))";
			
			@SuppressWarnings("unchecked")
			Page<CsDriverCar> page = csDriverCarService.getCsDriverCarPage($.getInteger("page", 0), 10,
					$.add(CsDriverCar.F.csdcDriver, driver.getCsdId()).add(CsDriverCar.F.csdcStatus, 1).add("definex", definex));

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for (CsDriverCar csDriverCar : page.getResult()) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put(CsDriverCar.F.csdcId, csDriverCar.getCsdcId());
				data.put(CsDriverCar.F.csdcState, csDriverCar.getCsdcState());
				data.put(CsDriverCar.F.csdcCar, csDriverCar.getCsdcCar());
				// Map<String, Object> carResMap = new HashMap<String,
				// Object>();
				CsCarRes carResBean = csDriverCar.get$csdcCar();
				data.put(CsCarRes.F.cscrCarNo, carResBean.getCscrCarNo());
				data.put(CsCarRes.F.cscrCarmodel, carResBean.getCscrCarmodel$());
				data.put(CsCarRes.F.cscrColor, carResBean.getCscrColor());
				CsCarModel carModel = csCarModelService.getCsCarModelById(new Long(csDriverCar.get$csdcCar().getCscrCarmodel()));
				data.put(CsCarModel.F.cscmImage, carModel.getCscmImage());
				// data.put("carRes", carResMap);
				dataList.add(data);
			}

			LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal()).add("count", page.getCount()).add("size", page.getSize());
			return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))), CHARSET);

		} catch (Exception e) {
			return returnError(e);
		}
	}

	/**
	 * 获取司机所有订单
	 * 
	 * @return
	 */
	public String getAllDriverOrders() {
		try {
			CsDriver driver = DriverOauthUtils.getOauth($.getString("access_token", ""));
			if (driver == null) {
				return returnError("100", "登录授权无效");
			}

			ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");

			List<CsDriverCar> listDriverCars = CsDriverCar.getCsDriverCarList(
					$.add(CsDriverCar.F.csdcDriver, driver.getCsdId()).add(CsDriverCar.F.csdcStatus, 1), -1);
			String definex = "";
			for (CsDriverCar csDriverCar : listDriverCars) {
				definex += csDriverCar.getCsdcId() + ",";
			}

			if ($.empty(definex)) {
				definex = "(0)";
			} else {
				definex = "(" + definex.substring(0, definex.length() - 1) + ")";
			}
			@SuppressWarnings("unchecked")
			Page<CsSpecialCar> page = csSpecialCarService.getCsSpecialCarPage($.getInteger("page", 0), 5,
					$.add("definex", CsSpecialCar.C.csscDrivercar + " in " + definex));

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			for (CsSpecialCar data : page.getResult()) {
				Map<String, Object> datamap = new HashMap<String, Object>();
				datamap.put(CsSpecialCar.F.csscId, data.getCsscId());
				
				datamap.put(CsSpecialCar.F.csscStartAddr, data.getCsscStartAddr());
				datamap.put(CsSpecialCar.F.csscFinishAddr, data.getCsscFinishAddr());
				datamap.put(CsSpecialCar.F.csscIsCarpool, data.getCsscIsCarpool());
				datamap.put(CsSpecialCar.F.csscAddTime, data.getCsscAddTime());
				datamap.put(CsSpecialCar.F.csscStartTime, data.getCsscStartTime());
				datamap.put(CsSpecialCar.F.csscFinishTime, data.getCsscFinishTime());
				datamap.put(CsSpecialCar.F.csscThird, data.getCsscThird());
				datamap.put(CsSpecialCar.F.csscRealDur, data.getCsscRealDur()); // 订单时长，分钟为单位
				datamap.put(CsSpecialCar.F.csscKilo, data.getCsscKilo());
				datamap.put(CsSpecialCar.F.csscScope, data.getCsscScope()); // 0:未评分 1:一星 2:二星
															// 3:三星 4:四星 5:五星
				datamap.put(CsSpecialCar.F.csscAssess, data.getCsscAssess());
				datamap.put(CsSpecialCar.F.csscStatus, data.getCsscStatus()); // 0:待审核 1:已审核
																// 2:已下单 3:已接单
																// 4:已完成 5:已取消
																// 6:已作废
				datamap.put(CsSpecialCar.F.csscThirdFee, data.getCsscThirdFee());
				

				datamap.put(CsSpecialCar.F.csscDriverName, data.getCsscDriverName()); // 司机姓名
				datamap.put(CsSpecialCar.F.csscDriverPhone, data.getCsscDriverPhone()); // 司机电话
				datamap.put(CsSpecialCar.F.csscDriverImage, data.getCsscDriverImage()); // 司机车辆头像
				
				
				if(data.getCsscStartPoint() != null && data.getCsscStartPoint().indexOf(";") != -1){
					datamap.put("startLongitude", data.getCsscStartPoint().split(";").length>1?data.getCsscStartPoint().split(";")[1]:data.getCsscStartPoint());//经度
					datamap.put("startLatitude",data.getCsscStartPoint().split(";").length>1? data.getCsscStartPoint().split(";")[0]:data.getCsscStartPoint());//纬度
				}else{
					datamap.put("startLongitude","0.0");//经度
					datamap.put("startLatitude","0.0");//纬度
				}
				
				if(data.getCsscFinishPoint() != null && data.getCsscFinishPoint().indexOf(";") != -1){
					datamap.put("finishLongitude", data.getCsscFinishPoint().split(";").length>1?data.getCsscFinishPoint().split(";")[1]:data.getCsscFinishPoint());//经度
					datamap.put("finishLatitude", data.getCsscFinishPoint().split(";").length>1?data.getCsscFinishPoint().split(";")[0]:data.getCsscFinishPoint());//纬度
				}else{
					datamap.put("finishLongitude", "0.0");//经度
					datamap.put("finishLatitude", "0.0");//纬度
				}
				
				
				
				//车辆信息
				datamap.put(CsSpecialCar.F.csscCarModel, data.getCsscCarModel());//车型
				datamap.put(CsSpecialCar.F.csscCarNumber, data.getCsscCarNumber()); // 司机车辆车牌号
				datamap.put(CsSpecialCar.F.csscDriverColor, data.getCsscDriverColor()); // 司机车辆颜色
				datamap.put(CsSpecialCar.F.csscDriverCarInfo, data.getCsscDriverCarInfo());
				datamap.put(CsCarRes.F.cscrId, data.get$csscDrivercar().getCsdcCar());//车辆ID
				
				
				// 约租车
				CsUnitOrder order = data.get$csscUnitOrder();
				datamap.put(CsSpecialCar.F.csscPreditMileage, data.getCsscPreditMileage()); // 预计公里数
				datamap.put(CsSpecialCar.F.csscEstimateFee, data.getCsscEstimateFee()); // 预计费用
				

				//乘客信息
				datamap.put(CsSpecialCar.F.csscName, data.getCsscName());
				datamap.put(CsSpecialCar.F.csscCount, data.getCsscCount());
				datamap.put(CsSpecialCar.F.csscMobile , data.getCsscMobile()); // 乘车人电话
				String headerImage = "";
				try{
					headerImage = data.get$csscPerson().get$csupMember().getCsmHeader();
				}catch(Exception e){
					Logger.getRootLogger().error(e.getMessage(),e);
				}
				datamap.put("csscPersonImg",headerImage);
				
				dataList.add(datamap);
			}

			LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal()).add("count", page.getCount()).add("size", page.getSize());
			return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))), CHARSET);
		} catch (Exception ex) {
			return returnError(ex);
		}
	}
	//获取司机接口
	public String getSpecialDriverLocale(){
		try {
			CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
			if(member == null){
				return returnError("100", "登录授权无效");
			}
			String csscId = $.getString("specialCarId");
			
			if($.empty(csscId))
				return returnError("104","请输入订单单号");
			
			CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar($.Map("csscId", csscId));
			
			if(csSpecialCar == null)
				return returnError("105","订单号无效");
			
			if(csSpecialCar.getCsscDrivercar() ==null)
				return returnError("106","订单未被接单");
			
			String location = stateMap.get(csSpecialCar.get$csscDrivercar().getCsdcCar$());
			
			String longitude = location != null && location.indexOf(",") != -1?location.split(",")[0]:"0.0"; //经度
			String latitude = location != null && location.indexOf(",") != -1?location.split(",")[1]:"0.0";//维度
			
			return $.SendHtml($.json(JsonFormat.success().setData($.Map("longitude", longitude).add("latitude", latitude))), CHARSET);
		} catch (Exception ex) {
			// TODO: handle exception
			return returnError(ex);
		}
	}
	
	
	public Map<String, Object> getDriverInfo(CsDriver csDriver){
		Map<String, Object> data = new HashMap<String, Object>();

		data.put(CsDriver.F.csdId, csDriver.getCsdId());
		data.put(CsDriver.F.csdPhoto, csDriver.getCsdPhoto());
		data.put(CsDriver.F.csdName, csDriver.getCsdName());
		data.put(CsDriver.F.csdSex, csDriver.getCsdSex$());
		data.put(CsDriver.F.csdMobile, csDriver.getCsdMobile());
		data.put(CsDriver.F.csdOutlets, csDriver.getCsdOutlets$());
		return data;
	}
	

	public ICsDriverService getCsDriverService() {
		return csDriverService;
	}

	public void setCsDriverService(ICsDriverService csDriverService) {
		this.csDriverService = csDriverService;
	}

	public ICsDriverCarService getCsDriverCarService() {
		return csDriverCarService;
	}

	public void setCsDriverCarService(ICsDriverCarService csDriverCarService) {
		this.csDriverCarService = csDriverCarService;
	}

	public ICsSpecialCarService getCsSpecialCarService() {
		return csSpecialCarService;
	}

	public void setCsSpecialCarService(ICsSpecialCarService csSpecialCarService) {
		this.csSpecialCarService = csSpecialCarService;
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

}
