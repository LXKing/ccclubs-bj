package com.ccclubs.action.admin.user;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsRefund;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsEvCardService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.helper.JsonHelper;
import com.lazy3q.web.helper.LZ;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.WebHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Ctrl;
import com.opensymphony.xwork2.ActionContext;

public class RegistAction {
	
	ICsMemberService csMemberService;
	ICsMemberInfoService csMemberInfoService;
	ISrvPropertyService srvPropertyService;
	ICsOutletsService csOutletsService;
	CsMember csMember;
	CsMemberInfo csMemberInfo;
	
	File carder;
	File driver;
	String carderFileName;
	String driverFileName;
	
	String cardno;
	String rfid;
	String vcode;
	Boolean coupon;
	
	public String execute(){	
		try{
			//为了让页面能够调用短信接口，在session放入最后发短信标识(当前时间减去一分钟)
			$.setSession(SYSTEM.REGIST_VAILD_LAST, new Date(new Date().getTime()-SYSTEM.MINUTE));
			
			//获取区域列表	
			Long host = SYSTEM.getDefaultHost();
			if(host==null)
				host = SrvHost.Get($.add(SrvHost.F.shFlag, "杭州")).getShId();
			List<CsArea> areas = CsArea.list($.add(CsArea.F.csaHost, host), -1);
			$.SetRequest("areas", areas);
			
			List<CsOutlets> outlets = csOutletsService.getCsOutletsList(LZ.add("csoHost", host).add("csoCanReg", true).add("csoCanOrder", true).add("csoStatus", 1), -1);
			$.SetRequest("outlets", outlets);	
			
			//String strIds = $.or(SYSTEM.getHostConfig(SYSTEM.getDefaultHost()).getCscMarketUsers(),"").replace("#", "");
			//$.SetRequest("sids", strIds);
			
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//查询条件传递到转跳
			$.SetRequest("entrypoint", $.getString("entrypoint"));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	/**
	 * ajax获取ev卡信息
	 * @return
	 */
	public String evcard(){
		try{
			String number = $.getString("number");
			String rfid = $.getString("rfid");
			CsEvCard csEvCard = null;
			if(!$.empty(number)){
				csEvCard = CsEvCard.Get($.add("definex","csec_number like '%"+number+"%'" ));
			}else if(!$.empty(rfid)){
				csEvCard = CsEvCard.Get($.add("definex","csec_rfid like '%"+rfid+"%'" ));
			}
			if(csEvCard==null){
				return $.SendAjax("{success:true}", "UTF-8");
			}
			CsMember member = CsMember.Get($.add("csmEvcard", csEvCard.getCsecId()));
			if(member!=null){
				return $.SendAjax("{success:false,message:'该卡号已被其它会员占用'}", "UTF-8");
			}
			return $.SendAjax($.add("success", true).add("number", csEvCard.getCsecNumber()).add("rfid", csEvCard.getCsecRfid()),"UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("{success:false,message:'系统繁忙，请稍候再试'}", "UTF-8");
		}
	}
	public String mobile(){
		try{
			String mobile = $.getString("mobile");
			CsMember member = CsMember.Get($.add("csmMobile", mobile));
			if(member!=null){
				return $.SendAjax("{success:false,message:'该手机号码已被其它会员占用'}", "UTF-8");
			}
			return $.SendAjax($.add("success", true),"UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("{success:false,message:'系统繁忙，请稍候再试'}", "UTF-8");
		}
	}
	
	
	
	public String submit(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等			
			
			//多个写数据动作合并事务过程
			String strError = csMemberService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){					
					/*if(csMemberService.getCsMember($.add("csmEmail", csMember.getCsmEmail()))!=null){
						return (T)"邮箱号码已被注册，请重新输入";
					}
					
					if(csMemberService.getCsMember($.add("csmUsername", csMember.getCsmUsername()))!=null){
						return (T)"会员帐号已被注册，请重新输入";
					}
					*/
					
					if(csMemberService.getCsMember($.add("csmMobile", csMember.getCsmMobile()))!=null){
						return (T)"手机号码已被注册，请重新输入";
					}
					
					ICsEvCardService csEvCardService=$.getBean("csEvCardService");
					if($.empty(cardno))
						cardno="!@#$%^&*()FGHJ";
					CsEvCard csEvCard = csEvCardService.getCsEvCard($.add("definex","csec_number like '%"+cardno+"%'" ));
					if(csEvCard==null){
						if($.empty(rfid))
							return (T)"请输入卡片识别码";
						if(csEvCardService.getCsEvCard($.add(CsEvCard.F.csecRfid, rfid))!=null){
							return (T)"卡片识别码已存在";
						}
						if(rfid.length()!=8){
							return (T)"卡片识别码必须是八位";
						}
						csEvCard = csEvCardService.saveCsEvCard(
							new CsEvCard()
							.csecAddTime(new Date())
							.csecFlag((short)1)
							.csecNumber(cardno)
							.csecRfid(rfid.toUpperCase())
							.csecStatus((short)1)
						);						
					}else if(csMemberService.getCsMember($.add("csmEvcard", csEvCard.getCsecId()))!=null){						
						return (T)"EV卡号已经被其它会员绑定，请重新输入";
					}
					csMember.setCsmEvcard(csEvCard.getCsecId());
					
					//设置默认值
					csMember.setCsmPassword($.md5(csMember.getCsmPassword()));
					csMember.setCsmMoney(0d);
					csMember.setCsmCoupon(0d);
					csMember.setCsmIntegral(0d);
					csMember.setCsmRebate(1.0d);
					csMember.setCsmVMobile((short)0);
					csMember.setCsmVEmail((short)0);
					csMember.setCsmVReal(csMember.getCsmVDrive());
					csMember.csmVDrive((short)0).csmVReal((short)0);
					csMember.setCsmIsVip((short)0);
					csMember.setCsmAddTime(new Date());
					csMember.setCsmUpdateTime(new Date());
					csMember.setCsmStatus((short)1);					
					csMember.setCsmFrom((short)0);
					csMember.setCsmEvcard(csEvCard==null?null:csEvCard.getCsecId());
					csMember.setCsmAdder(LoginHelper.getLoginId());
					if(csMember.getCsmSaler()==null)
						csMember.setCsmSaler(LoginHelper.getLoginId());
					
					//添加域标识
					csMember.setCsmHost(SYSTEM.getDefaultHost());
					
					//2013-10-31改身份证、会员帐号无需输入
					csMember.setCsmUsername($.uuid());
					
					csMember=csMemberService.saveCsMember(csMember);
					//设置默认值
					csMemberInfo.setCsmiUpdateTime(new Date());
					csMemberInfo.setCsmiAddTime(new Date());	
					csMemberInfo.setCsmiName(csMember.getCsmName());
					csMemberInfo.setCsmiMemberId(csMember.getCsmId());
					csMemberInfo.setCsmiStatus((short)1);
					
					//添加域标识
					csMemberInfo.setCsmiHost(csMember.getCsmHost());
					
					//根据身份证号码取会员生日
					String idcard = $.or(csMemberInfo.getCsmiDriverNum(),csMemberInfo.getCsmiCertifyNum());
					csMemberInfo.setCsmiBirthday(UtilHelper.getBirthdayFromIdCard(idcard));
															
					if($.empty(csMemberInfo.getCsmiCertifyImage())){
						csMemberInfo.setCsmiCertifyImage(getUpload(carder,carderFileName));
					}
					if($.empty(csMemberInfo.getCsmiDriverImage())){
						csMemberInfo.setCsmiDriverImage(getUpload(driver,driverFileName));
					}
					//设置填充值					
					csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
					csMemberService.updateCsMemberByConfirm(
						$.add("csmInfo", csMemberInfo.getCsmiId())
							, $.add("csmId", csMember.getCsmId()).add("confirm", 1));
										
					//记录操作日志
					LoggerHelper.writeLog(CsMember.class,"update","注册了[会员帐号]["+csMember.getCsmName()+"]",(Long)$.getSession("ccclubs_login_id"), csMember);
										
					return null;
				}
			});
			
			
			if($.getSession("EXTFIX")!=null){
				if($.empty(strError)){
					$.SetTips("会员帐号注册成功");
					return $.Redirect("regist.do");
				}else{ 
					$.SetTips(strError);
					return execute();
				}
			}else{
				if($.empty(strError)){
					$.SetTips("会员帐号注册成功,你可以继续注册下一个");
					String redirectUrl = CTRL==null?null:CTRL.getRedirect();//自定义转跳地址
					if(!$.empty(redirectUrl))return $.Redirect(redirectUrl);
					if($.getString("submiter","").indexOf("完成")==-1){
						return $.Redirect("regist.do");
					}	
				}else{
					$.SetTips(strError);
				}
							
			}
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		if($.getSession("EXTFIX")!=null){
			return execute();
		}else{
			return $.Redirect($.xeh($.getString("entrypoint")));
		}
	}	
	
	
	private static String upload_forder=ConfigHelper.GetString("upload.forder");//上传图片保存的路径
	private static String upload_domain=ConfigHelper.GetString("upload.domain");//图片服务的域名地址	
	protected String getUpload(File file,String fileFileName) {		
		try{
			if(file==null || $.empty(fileFileName))
				return null;
			
			Map<String,Object> params = ActionContext.getContext().getParameters();
			String sApp="users";//应用类型							
			
			HttpServletRequest request = ServletActionContext.getRequest();			
			if(WebHelper.empty(upload_forder))
				upload_forder=request.getRealPath("/");
			upload_forder=upload_forder.replaceAll("\\\\", "/");
			if(!upload_forder.endsWith("/"))
				upload_forder+="/";	
			
			String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();				
			String filePah=sApp+"/";
			new File(upload_forder+filePah).mkdirs();
			String fileName = new SimpleDateFormat("yyyyMMddHHmmssS"+WebHelper.rand(999)).format(new Date());
			
			
			Map jsonMap=new HashMap();
			
						
			//保存原始上传的文件
			String uploadFileName=fileName+"."+fileExt;				
			File uploadedFile = new File(upload_forder+filePah, uploadFileName);			
			file.renameTo(uploadedFile);//保存上传的文件
			
			return upload_domain+filePah+uploadFileName;					
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return null;
		}catch(Error e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return null;
		}
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}
	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	public ICsMemberInfoService getCsMemberInfoService() {
		return csMemberInfoService;
	}
	public void setCsMemberInfoService(ICsMemberInfoService csMemberInfoService) {
		this.csMemberInfoService = csMemberInfoService;
	}

	public CsMember getCsMember() {
		return csMember;
	}

	public void setCsMember(CsMember csMember) {
		this.csMember = csMember;
	}

	public CsMemberInfo getCsMemberInfo() {
		return csMemberInfo;
	}

	public void setCsMemberInfo(CsMemberInfo csMemberInfo) {
		this.csMemberInfo = csMemberInfo;
	}

	public File getCarder() {
		return carder;
	}

	public void setCarder(File carder) {
		this.carder = carder;
	}

	public File getDriver() {
		return driver;
	}

	public void setDriver(File driver) {
		this.driver = driver;
	}

	public String getCarderFileName() {
		return carderFileName;
	}

	public void setCarderFileName(String carderFileName) {
		this.carderFileName = carderFileName;
	}

	public String getDriverFileName() {
		return driverFileName;
	}

	public void setDriverFileName(String driverFileName) {
		this.driverFileName = driverFileName;
	}


	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

	public ICsOutletsService getCsOutletsService() {
		return csOutletsService;
	}

	public void setCsOutletsService(ICsOutletsService csOutletsService) {
		this.csOutletsService = csOutletsService;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Boolean getCoupon() {
		return coupon;
	}

	public void setCoupon(Boolean coupon) {
		this.coupon = coupon;
	}
	

}
