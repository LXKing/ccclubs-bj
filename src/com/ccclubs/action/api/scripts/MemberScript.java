package com.ccclubs.action.api.scripts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.api.model.Result;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;

/**
 * 会员操作
 * 
 * @author 
 *
 */
public class MemberScript extends BaseScript{
	
	/**
	 * 保存用户基本信息
	 * @param userName
	 * @param userHeadImg
	 * @param gender
	 * @param email
	 * @return
	 */
	public static Result saveUserBasicInfo(final String userName, final String userHeadImgUrl, final Short gender, final String email){
		Result rs = new Result();
		final ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		final ICsMemberInfoService	csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		
		//非法请求
		if(getOauth() == null){
			return new Result(false, 100); 
		}
		
		final CsMember csMember = CsMember.get(getOauth());
		//账户信息无效
		if(csMember == null){
			return new Result(false, 101); 
		}
		
		if(userName == null && userHeadImgUrl == null
				&& gender == null && email == null){
			rs.setResult(true);
			return rs; 
		}
		
		try{
			//用户名已存在
			if(userName!=null){
				CsMember tmp = csMemberService.getCsMember($.add(CsMember.F.csmUsername, userName));
				if( tmp != null && !tmp.getCsmId().equals(csMember.getCsmId())){
					return new Result(false, 102); 
				}
			}
			
			final Long memberId = csMember.getCsmId();
			final Long hostId = csMember.getCsmHost();
		
			csMemberInfoService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
					
					// 更新会员的扩展信息
					CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, memberId));
					if (csMemberInfo == null) {
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(hostId,memberId, new Date(), new Date(), (short) 1);
						csMemberInfo.setCsmiMemberId(memberId);
						csMemberInfo.setCsmiSex(gender);
						CsMemberInfo memberInfoTemp = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
						csMember.setCsmInfo(memberInfoTemp.getCsmiId());
					}else{
						csMemberInfo.setCsmiSex(gender);
						csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
						if(csMember.getCsmInfo() == null){
							csMember.setCsmInfo(csMemberInfo.getCsmiId());
						}
					}
					
					csMember.setCsmUsername(userName);
					csMember.setCsmHeader(userHeadImgUrl);
					csMember.setCsmEmail(email);
					csMemberService.updateCsMember$NotNull(csMember);
					
					return null;
				}
			});
		}catch(Exception e){
			e.printStackTrace(); 
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
		rs.setResult(true);
		return rs;
	}
	
	/**
	 * 保存驾驶人证件信息
	 * @param realName
	 * @param certifyType
	 * @param certifyNubmer
	 * @param certifyImageUrl
	 * @param driverNum
	 * @param driverImageUrl
	 * @return
	 */
	public static Result saveDriverInfo(final String realName, 
										final Short certifyType,
										final String certifyNubmer,
										final String certifyImageUrl,
										final String driverNum, 
										final String driverImageUrl){
		Result rs = new Result();
		
		final ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		final ICsMemberInfoService	csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		
		if(getOauth() == null){		//非法请求
			return new Result(false, 100); 
		}
		
		final CsMember csMember = CsMember.get(getOauth());
		if(csMember == null){	//账户信息无效
			return new Result(false, 101); 
		}

		if(realName == null && certifyType == null
				&& certifyNubmer == null && certifyImageUrl == null
				&& driverNum == null && driverImageUrl == null){
			rs.setResult(true);
			return rs; 
		}
		
		try{
			if(driverNum!=null && !driverNum.equals("")){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(CsMemberInfo.F.csmiDriverNum, driverNum);
				CsMemberInfo existMemberInfo = csMemberInfoService.getCsMemberInfo(params);
				//驾驶证号码已存在
				if (existMemberInfo != null && !existMemberInfo.getCsmiMemberId().equals(getOauth()))	
					return new Result(false, 104);
			}
				
			final Long memberId = csMember.getCsmId();
			final Long hostId = csMember.getCsmHost();
	
			csMemberInfoService.executeTransaction(new Function() {
				
				@Override
				public <T> T execute(Object... arg0) {
					// 更新会员的扩展信息
					CsMemberInfo csMemberInfo = csMemberInfoService.getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, memberId));
					if (csMemberInfo == null) {
						csMemberInfo = new CsMemberInfo();
						csMemberInfo.setNotNull(hostId,memberId, new Date(), new Date(), (short) 1);
						csMemberInfo.setCsmiMemberId(memberId);
						csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
					}
	
					CsMemberInfo oldMemberInfo = csMemberInfoService.getCsMemberInfoById(csMemberInfo.getCsmiId());
					// 设置扩展信息：关联的会员帐号，驾照照片路径，驾照号码，真实姓名，更新时间
					csMemberInfo.csmiMemberId(getOauth())
								.csmiCertifyImage(certifyImageUrl)
								.csmiDriverImage(driverImageUrl)
								.csmiDriverNum(driverNum)
								.csmiName(realName)
								.csmiUpdateTime(new Date());
					
					csMemberInfo.setCsmiCertifyNum(certifyNubmer);
					csMemberInfo.setCsmiCertifyType(certifyType);
					
					Date birthday=SystemHelper.getBirthday(driverNum);
					if (birthday!=null) {
						csMemberInfo.csmiBirthday(birthday);
					}
					csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
	
					CsMember csMember = new CsMember().csmId(memberId).csmInfo(csMemberInfo.getCsmiId()).csmUpdateTime(new Date());
					// 比较修改的内容，决定会员的审核状态,如果任意其中一个被修改过，则更新其对应的状态
					if (!$.equals(realName, oldMemberInfo.getCsmiName()))
						csMember.setCsmVReal((short) 2);
					if (!$.equals(driverImageUrl, oldMemberInfo.getCsmiDriverImage())|| !$.equals(certifyImageUrl, oldMemberInfo.getCsmiCertifyImage()) || !$.equals(driverNum, oldMemberInfo.getCsmiDriverNum()))
						csMember.setCsmVDrive((short) 2);
	
					// 真实姓名同步到会员表中
					csMember.setCsmName(csMemberInfo.getCsmiName());
					csMemberService.updateCsMember$NotNull(csMember);
	
					return null;
				}
			});
		}catch(Exception e){
			e.printStackTrace(); 
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
		
		rs.setResult(true);
		return rs;
	}
	
	/**
	 * 保存会员联系信息
	 * @param postAddress
	 * @param company
	 * @param people
	 * @param peoplePhoneNumber
	 * @param relation
	 * @return
	 */
	public static Result saveContact(final String postAddress, 
										final String company,
										final String people,
										final String peoplePhoneNumber,
										final String relation){
		Result rs = new Result();
		
		final ICsMemberInfoService	csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		
		if(getOauth() == null){		//非法请求
			return new Result(false, 100); 
		}
		
		final CsMember csMember = CsMember.get(getOauth());
		if(csMember == null){	//账户信息无效
			return new Result(false, 101); 
		}
		
		if(postAddress == null && company == null && people == null && peoplePhoneNumber == null && relation == null){
			rs.setResult(true);
			return rs; 
		}
		
		try{
			
			// 设置扩展信息：关联的会员帐号，邮寄地址，更新时间
			final  CsMemberInfo newCsMemberInfo = new CsMemberInfo();
			newCsMemberInfo.csmiAddress(postAddress).csmiUpdateTime(new Date());
			newCsMemberInfo.setCsmiCompany(company);
			newCsMemberInfo.setCsmiPerson(people);
			newCsMemberInfo.setCsmiContact(peoplePhoneNumber);
			newCsMemberInfo.setCsmiRelation(relation);
			newCsMemberInfo.setCsmiId(csMember.getCsmInfo());
			
			csMemberInfoService.executeTransaction(new Function() {
				@Override
				public <T> T execute(Object... arg0) {
			
					// 更新会员的扩展信息
					if(csMember.getCsmInfo()!=null && csMember.getCsmInfo() > 0){
						newCsMemberInfo.setNotNull(csMember.getCsmHost(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						csMemberInfoService.updateCsMemberInfo$NotNull(newCsMemberInfo);
					}else{
						newCsMemberInfo.setNotNull(csMember.getCsmHost(),csMember.getCsmId(), new Date(), new Date(), (short) 1);
						CsMemberInfo csMemberInfoTmp = csMemberInfoService.saveCsMemberInfo(newCsMemberInfo);
						
						csMember.setCsmInfo(csMemberInfoTmp.getCsmiId());
						csMember.update();
					}
					return null;
				}
			});
			
			// 设置扩展信息：关联的会员帐号，邮寄地址，更新时间
			rs.setResult(true);
			
		}catch(Exception e){
			e.printStackTrace(); 
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
		
		return rs;
	}
	
	/**
	 * 获取会员账户余额
	 */
	public static Result getAccountAmount(){
		Result rs = new Result();
		if(getOauth() == null){		//非法请求
			return new Result(false, 100); 
		}
		
		ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
		
		CsMember csMember = CsMember.get(getOauth());
		if(csMember == null){	//账户信息无效
			return new Result(false, 101); 
		}
		
		try{
			DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();
			df.setMaximumFractionDigits(2);
			
			//可用余额
			Double canUseMoney = $.or(commonMoneyService.getUsableAmount(getOauth()), 0).doubleValue();
			
			Double memberMoney = $.or(csMember.getCsmMoney(), 0).doubleValue(); 
			
			//充值余额
			Double remainCharge = memberMoney >=  canUseMoney ? canUseMoney : memberMoney;
			
			//现金券余额
			Double remainCoupon = memberMoney >=  canUseMoney? 0 : canUseMoney - memberMoney;
			
			//冻结资金
			Double freezeMoney = memberMoney + $.or(csMember.getCsmCoupon(), 0).doubleValue() - canUseMoney;
			
			//当前积分
			Double integral = $.or(csMember.getCsmIntegral(), 0).doubleValue();
			
			//红包
			Map<String, String> hostmap = new HashMap<String, String>();
			hostmap.put("1", "杭州");
			hostmap.put("2", "北京");
			hostmap.put("3", "常州");
			hostmap.put("10", "贵阳");
			List<Map<String, Object>> coinList = new ArrayList<Map<String,Object>>();
			for(String host : hostmap.keySet()){
				Double coin = commonMoneyService.getCoin(Long.valueOf(host), getOauth(), Calendar.getInstance().getTime());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("host", host); 
				data.put("name", hostmap.get(host));
				data.put("coin", df.format($.or(coin, 0).doubleValue()));
				coinList.add(data);
			}
			
			// 可用免费小时
			Double canUseFreeHour = commonMoneyService.getCanUseFreeHour(getOauth(), new Date());
			
			Map<String, Object> datamap = new HashMap<String, Object>();
			
			datamap.put("canUseMoney", df.format(canUseMoney));
			datamap.put("remainCharge", df.format(remainCharge));
			datamap.put("remainCoupon", df.format(remainCoupon));
			datamap.put("freezeMoney", df.format(freezeMoney));
			datamap.put("integral", df.format(integral));
			datamap.put("canUseFreeHour", canUseFreeHour);
			datamap.put("coinList", coinList);
			rs.setResult(true).setData(datamap);
		}catch(Exception e){
			e.printStackTrace(); 
			Logger.getRootLogger().error(e.getMessage(),e);
			return new Result(false, 9999);
		}
		
		return rs;
	}
	
}
