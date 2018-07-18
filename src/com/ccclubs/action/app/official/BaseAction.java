package com.ccclubs.action.app.official;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.lazy3q.web.helper.$;

public class BaseAction {

	/**
	 * 获取单位信息
	 * @param unitList
	 * @return
	 */
	public List<Map<String, Object>> assembleUnitInfo(List<CsUnitInfo> unitList) {
		List<Map<String, Object>> unitInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (CsUnitInfo csUnitInfo : unitList) {
			map = new HashMap<String, Object>();
			map.put("unitId", csUnitInfo.getCsuiId());
			map.put("unitName", csUnitInfo.getCsuiName$());
			unitInfoList.add(map);
		}
		return unitInfoList;
	}
	
	/**
	 * 获取默认网点 （产品需求）
	 * @return
	 */
	public Map<String, Object> defaultArea() {
		Map<String, Object> defaultArea = new HashMap<String, Object>();
		defaultArea.put("areaId","0" );
		defaultArea.put("areaName", "默认网点");
		List<CsUnitInfo> unitList = CsUnitInfo.getCsUnitInfoList($.add(CsUnitInfo.F.csuiName, "北京市政务中心"), -1);
		defaultArea.put("unitList", assembleUnitInfo(unitList));
		return defaultArea;
	}
	
	/**
	 * 设置 密码 注册来源 手机号 邀请码
	 * @param csMember
	 * @param csmPassword
	 * @param from
	 * @param csmMobile
	 * @param inviteCode
	 * @return
	 */
	public CsMember getRegisterMember(CsMember csMember,String csmPassword,short from,String csmMobile,String inviteCode){
		csMember.setCsmHost(SystemHelper.getDefaultSrvHost().getShId() );
		String username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
		//设置会员的相关信息，对于部分字段设置默认值，保存会员的相关信息到cs_member
		csMember.setCsmUsername(username);
		csMember.setCsmPassword($.md5(csmPassword));
		csMember.setCsmFrom(from);
		csMember.setCsmStatus((short)1);
		csMember.setCsmAddTime(new Date());
		csMember.setCsmUpdateTime(csMember.getCsmAddTime());
		csMember.setCsmMoney(0d);
		csMember.setCsmCoupon(0d);
		csMember.setCsmGrow(0);
		csMember.setCsmGrade(null);
		csMember.setCsmIntegral(0d);
		csMember.setCsmRebate(1d);//会员折扣，例如如果值为0.75表示75折，默认为1，没有折扣
		csMember.setCsmVReal((short)0);
		csMember.setCsmVDrive((short)0);
		csMember.setCsmMobile(csmMobile);
		csMember.setCsmTemp(inviteCode);
		return csMember;
	}
	/**
	 * 设置真实姓名 城市 单位名称
	 * @param csMember
	 * @param realName
	 * @param host
	 * @param unitName
	 * @return
	 */
	public CsMember updateMember(CsMember csMember,String realName,Long host,String unitName){
		csMember.setCsmName(realName);
		csMember.setCsmHost(host);
		csMember.setCsmUpdateTime(new Date());
		csMember.setCsmRemark("{\"remark\":\""+csMember.getCsmRemark()+"\",\"unitName\":\""+unitName+"\"}");
		return csMember;
	}

	/**
	 * 修改会员身份认证、驾驶证认证状态
	 * @param member
	 */
	public CsMember updateAutoState(CsMember member,short rValue,short dValue){
		member.setCsmVReal(rValue);
		member.setCsmVDrive(dValue);
		return member;
	}
	
	/**
	 * 修改会员实名认证状态
	 * */
	public CsMember updateAutoState(CsMember member,Short vDriveState,Short vIdCardState,Short vWorkState){
	    
	    member.setCsmVReal((short)2);
	    if(vDriveState!=null) {
	        member.setCsmVDrive(vDriveState);
	    }
	    if(vIdCardState!=null) {
            member.setCsmVIdcard(vIdCardState);
        }
	    if(vWorkState!=null) {
            member.setCsmVWork(vWorkState);
        }
        
        return member;
    }
	
	/**
	 * 会员详细信息
	 * @param csMemberInfo
	 * @param host
	 * @param realName
	 * @param csMember
	 * @param sex
	 * @return
	 */
	public CsMemberInfo getMemberInfo (CsMemberInfo csMemberInfo,Long host,String realName,CsMember csMember,short sex){
		csMemberInfo.setCsmiHost(host);
		csMemberInfo.setCsmiName(realName);
		csMemberInfo.setCsmiMemberId(csMember.getCsmId());
		csMemberInfo.setCsmiAddTime(new Date());
		csMemberInfo.setCsmiUpdateTime(new Date());
		csMemberInfo.setCsmiStatus((short)1);
		csMemberInfo.setCsmiSex(sex);
		return csMemberInfo;
	}
	
	/**
	 * 修改会员信息 身份证 驾驶证 
	 * @param member
	 * @param certifyImg
	 * @param certifyNum
	 * @param driverImage
	 * @param onCertifyImg
	 */
	public CsMemberInfo updateMemberInfo(CsMember member,String certifyImg,String certifyNum,String driverImage,String onCertifyImg){
		CsMemberInfo csMemberInfo = CsMemberInfo.Get($.add(CsMemberInfo.F.csmiMemberId, member.getCsmId()));
		csMemberInfo.setCsmiCertifyType((short)1);
		csMemberInfo.setCsmiCertifyNum(certifyNum);
		csMemberInfo.setCsmiCertifyImage(certifyImg);
		csMemberInfo.setCsmiDriverImage(driverImage);
		csMemberInfo.setCsmiOnCertifyImage(onCertifyImg);
		return csMemberInfo;
	}
	
	public CsMemberInfo updateMemberInfoCertifyImage(CsMember member,String certifyImg,String certifyNum,String realName,String onCertifyImg){
        CsMemberInfo csMemberInfo = CsMemberInfo.Get($.add(CsMemberInfo.F.csmiMemberId, member.getCsmId()));
        csMemberInfo.setCsmiCertifyType((short)1);
        csMemberInfo.setCsmiCertifyNum(certifyNum);
        csMemberInfo.setCsmiCertifyImage(certifyImg);
        csMemberInfo.setCsmiName(realName);
        csMemberInfo.setCsmiOnCertifyImage(onCertifyImg);
        return csMemberInfo;
    }
	
	public CsMemberInfo updateMemberInfoDriverImage(CsMember member,String driverImage){
        CsMemberInfo csMemberInfo = CsMemberInfo.Get($.add(CsMemberInfo.F.csmiMemberId, member.getCsmId()));
       
        csMemberInfo.setCsmiDriverImage(driverImage);
        return csMemberInfo;
    }
	
	public CsMemberInfo updateMemberInfoWorkImage(CsMember member,String proofOfEmployment,String company,String department){
        CsMemberInfo csMemberInfo = CsMemberInfo.Get($.add(CsMemberInfo.F.csmiMemberId, member.getCsmId()));
    
        csMemberInfo.setCsmiProofOfEmployment(proofOfEmployment);
        csMemberInfo.setCsmiCompany(company);
        csMemberInfo.setCsmiDepartment(department);
        
        return csMemberInfo;
    }
	
	/**
	 * 会员关系表信息
	 * @param csMemberShip
	 * @param host
	 * @param payMember
	 * @param targeterMember
	 * @return
	 */
	public CsMemberShip getMemberShip(CsMemberShip csMemberShip,Long host,Long payMember,Long targeterMember){
		csMemberShip.setCsmsHost(host);
		csMemberShip.setCsmsPayer(payMember);
		csMemberShip.setCsmsTargeter(targeterMember);
		csMemberShip.setCsmsAddTime(new Date());
		csMemberShip.setCsmsStatus((short)1);
		return csMemberShip;
	}
	
	public CsUnitPerson getUnitPerson(CsUnitPerson csUnitPerson,long host ,Long infoId,Long memberId,Long groupId){
		csUnitPerson.setCsupHost(host);
		csUnitPerson.setCsupInfo(infoId);
		csUnitPerson.setCsupMember(memberId);
		csUnitPerson.setCsupAddTime(new Date());
		csUnitPerson.setCsupUpdateTime(new Date());
		csUnitPerson.setCsupName("");
		csUnitPerson.setCsupGroup(groupId);
		csUnitPerson.setCsupStatus((short)1);
		return csUnitPerson;
	}
	
}
