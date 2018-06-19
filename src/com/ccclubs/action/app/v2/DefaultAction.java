package com.ccclubs.action.app.v2;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ccclubs.action.app.v2.AppJsonFrame;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.model.CsConfig;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.model.SrvHost;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

public class DefaultAction {
	
	
	
	/**
	 * 获取我附近的车
	 * @return
	 */
	public String init(){
		try {
			AppJsonFrame ajf = new AppJsonFrame();
			
			CsUpdate csUpdate = CsUpdate.where().csuType((short)0).get();
			if (csUpdate == null)
				new ErrorException("APP_NOFOUND_ERROR", "未上传最新的APP程序", "请在后台上传最新的APP程序");
			else
				ajf.data.add("version", csUpdate.getCsuVersion()).add("update", csUpdate.getCsuFile());

			SrvHost srvHost = null;
			//根据传入的位置获取最近的城市
			String strLocation = $.getString("location");
			if($.empty(strLocation)||strLocation.split(",").length!=2){//如果未传入位置，则取杭州
				srvHost = SrvHost.where().shFlag("杭州").get();
			}else{
				String pow = "(pow("+strLocation.split(",")[0]+"-SUBSTRING_INDEX(sh_location, ',', -1),2)+pow("+strLocation.split(",")[1]+"-SUBSTRING_INDEX(sh_location, ',', 1),2))";
				SrvHost.where().shState((short)1).shStatus((short)1).add("asc",pow).get();
			}
			CsConfig csConfig = CsConfig.where().cscHost(srvHost.getShId()).get();
			ajf.addData("service", csConfig.getCscSvrNumber());

			return LZ.SendAjax(ajf, SYSTEM.UTF8);
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(AppJsonFrame.global(AppJsonFrame.ERROR.SYSTEM_ERROR, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
	}
	
	
	
	
	/**
	 * 获取我附近的车
	 * @return
	 */
	public String near(){
		try {
			String uid = $.getString("uid");
			Integer index = $.getInteger("index");
			String strLocation = $.getString("location");
			
			
			
			
			
		
		
		} catch (Exception ex) {
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return LZ.SendAjax(AppJsonFrame.global(AppJsonFrame.ERROR.SYSTEM_ERROR, SYSTEM.ERROR_TIPS), SYSTEM.UTF8);
		}
		
		return null;
	}
	
	

}
