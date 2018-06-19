package com.ccclubs.action.weixin.admin;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.action.weixin.WeixinTokenHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOutsideQr;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsState;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.model.TbEmployee;
import com.ccclubs.model.TbPassCheck;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsOutsideQrService;
import com.ccclubs.service.admin.ICsRemoteService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.ccclubs.service.admin.ISrvUserService;
import com.lazy3q.web.helper.$;

/**
 * 上下班签到
 * @author qsxiaogang http://m.ccclubs.com/weixin/admin/check.html?type=0
 */
public class CheckAction {
	
	private static String	sApp		= "wxoutsideqr";
	
	public String execute() {
		Short type = $.getShort("type");
		if ($.empty(WeixinHelper.getOpenId())) {// 微信授权登录
			$.Redirect($.GetRequest("basePath") + "weixin/oauth2.html?redirect=weixin/admin/check.html?type=" + type);
			return null;
		}
		String openid = WeixinHelper.getOpenId();
		TbEmployee tbEmployee = TbEmployee.where().tbeWeixinFlag(openid).get();
		if(tbEmployee==null || tbEmployee.getTbeStatus().shortValue()!=1){
			$.SetTips("您的帐号当前无效，请联系人事");
			return "check";
		}
		$.setRequest("tbEmployee", tbEmployee);
		$.setRequest("now", new Date());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String, String> ret = WeixinTokenHelper.getSign(WeixinTokenHelper.getCompleteUrl(request));
		$.SetRequest("map", ret);
		
		return "check";
	}
	
	
	public String submit(){
		Short type = $.getShort("type");
		if(type==null){
			return $.SendAjax($.add("success", false).add("message","参数错误，请退出重新再试"), $.UTF8);
		}		
		String Latitude = $.getString("Latitude");
		String Longitude = $.getString("Longitude");
		String position = ($.empty(Latitude)||$.empty(Longitude))?null:(Latitude+","+Longitude);
		if($.empty(position)){
			return $.SendAjax($.add("success", false).add("message","无法获位置信息，请联系技术"), $.UTF8);
		}
		String title = $.date(new Date(), "yyyy-MM-dd E")+"签到";
		String openid = WeixinHelper.getOpenId();
		TbEmployee tbEmployee = TbEmployee.where().tbeWeixinFlag(openid).get();
		if(tbEmployee==null || tbEmployee.getTbeStatus().shortValue()!=1){
			return $.SendAjax($.add("success", false).add("message","您的帐号当前无效，请联系人事"), $.UTF8);
		}
		TbPassCheck tbPassCheck = TbPassCheck.where().tbpcTitle(title).tbpcEmployee(tbEmployee.getTbeId()).get();
		if(tbPassCheck==null)
			tbPassCheck = new TbPassCheck().setNotNull(1l, title, tbEmployee.getTbeId(), (short) 1).save();
		if(type.shortValue()==0){//上班
			tbPassCheck.tbpcInTime(new Date()).tbpcInPos(position);
		}else{//下班
			tbPassCheck.tbpcOutTime(new Date()).tbpcOutPos(position);
		}
		SrvHost srvHost = SrvHost.get(tbPassCheck.getTbpcHost());
		//计算距离
		if(!$.empty(srvHost.getShLocation())){
			if(!$.empty(tbPassCheck.getTbpcInPos())){
				tbPassCheck.setTbpcInDistance(getDistance(tbPassCheck.getTbpcInPos(),srvHost.getShLocation()));				
			}
			if(!$.empty(tbPassCheck.getTbpcOutPos())){
				tbPassCheck.setTbpcOutDistance(getDistance(tbPassCheck.getTbpcOutPos(),srvHost.getShLocation()));
			}
		}
		//计算上下班时间
		if(tbPassCheck.getTbpcInTime()!=null && tbPassCheck.getTbpcOutTime()!=null){
			tbPassCheck.setTbpcTimeLong(((int)(tbPassCheck.getTbpcOutTime().getTime()-tbPassCheck.getTbpcInTime().getTime()))/((int)($.TIME.MINUTE.time)));
		}
		tbPassCheck.update();
		return $.SendAjax($.add("success", true).add("message", (type.shortValue()==0?"签入":"签出")+"成功"), $.UTF8);
	}
	
	
	

	/**
	 * 获取两坐标位置距离
	 * @param pos1
	 * @param pos2
	 * @return
	 */
	private Integer getDistance(String pos1, String pos2) {
		return getShortestDistance(
				Double.parseDouble(pos1.split(",")[1]),
				Double.parseDouble(pos1.split(",")[0]),
				Double.parseDouble(pos2.split(",")[1]),
				Double.parseDouble(pos2.split(",")[0])
		);
	}

	/**
	 * 计算两点间的距离
	 */
	private final double	EARTH_RADIUS	= 6378.137; // 地球半径

	private double rad(double d) {
		return d * Math.PI / 180.0; // 计算弧长
	}

	// lng1 第一个点经度，lat1第一点纬度；lng2第二点经度，lat2第二点纬度
	private int getShortestDistance(double lng1, double lat1, double lng2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = s * 1000; // 换算成米
		return SystemHelper.toInt(s);

	}
}
