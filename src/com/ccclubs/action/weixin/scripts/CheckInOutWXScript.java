package com.ccclubs.action.weixin.scripts;

import java.util.Date;
import java.util.List;

import com.lazy3q.web.helper.$;
import com.ccclubs.action.weixin.IWXScript;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsWxContent;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.model.TbEmployee;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.ccclubs.service.admin.ISrvUserService;
import com.ccclubs.service.common.ICommonMoneyService;

/**
 * 员工登记 
 * @author 刘代进
 */
public class CheckInOutWXScript implements IWXScript {

	public String getContent(String openId, String content) {
		try {
			TbEmployee tbEmployee = TbEmployee.where().tbeWeixinFlag(openId).get();
			if(tbEmployee==null){
				tbEmployee = new TbEmployee(
					content.replace("hr:", "").replace("HR:", "")//姓名 [非空]
				 	,0l//部门 [非空]
				 	,openId//微信标识
				 	,0l//修改人 [非空]
				 	,null//备注
				 	,null//备忘信息
				 	,null//程序数据
				 	,new Date()//修改时间 [非空]
				 	,new Date()//添加时间 [非空]
				 	,(short)0//状态 [非空]
				 ).save();
				 return "登记成功,等待人事审核";
			}else{
				return "您已登记,"+(tbEmployee.getTbeStatus().shortValue()==1?"可以直接扫码签到啦~":"请等待人事审核");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "系统错误";
		}
	}

	public List<CsWxContent> getContents(String openId, String content) {
		return null;
	}

	public ReturnType getReturnType() {
		return ReturnType.TEXT;
	}

}
