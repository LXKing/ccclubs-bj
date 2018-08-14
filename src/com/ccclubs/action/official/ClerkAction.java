package com.ccclubs.action.official;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.Constant;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class ClerkAction {
	
	public ICsUnitPersonService csUnitPersonService;
	public ICsUnitGroupService csUnitGroupService;
	public ICsMemberService csMemberService;
	public ICsMemberInfoService csMemberInfoService;
	public ICsMemberShipService csMemberShipService;
	public CsUnitPerson csUnitPerson;
	public CsMemberInfo csMemberInfo;
	public CsMember csMember;
	public CsMemberShip csMemberShip;
	
	public String execute(){
		try{
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//查询条件传递到转跳
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsUnitPerson.class);//把表单参数转成查询需要的参数格式
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			
			if(unitInfo.getCsuiId() != null){
				params.put(CsUnitPerson.F.csupInfo, unitInfo.getCsuiId());
			}
			Page<CsUnitPerson> page = csUnitPersonService.getCsUnitPersonPage($.getInteger("page",0), 16, params);
			
			//获取所有的部门信息
			Map<String,Object> param=new HashMap();
			
			param.put("csugInfo", UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
			param.put("definex", "(csug_status>=0)");
			List<CsUnitGroup> csUnitGroups = csUnitGroupService.getCsUnitGroupList(param, -1);
			
			$.setRequest("csUnitGroups", csUnitGroups);
			
			$.SetRequest("csUnitPerson", page.getResult());
			$.SetRequest("page", page);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().equals(e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "index";
	}
	
	
	public String edit(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitPerson);//进入之前的预处理
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("id",$.getString(params, "id"));
			
			//获取所有的部门信息
			Map<String,Object> param=new HashMap();
			
			param.put("csugInfo", UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
			param.put("definex", "(csug_status>=0)");
			List<CsUnitGroup> csUnitGroups = csUnitGroupService.getCsUnitGroupList(param, 50);
			$.setRequest("csUnitGroups", csUnitGroups);
			
			List<CsMember> payMembers = UnitLoginHelper.getLogin().get$csuuUnitInfo().get$csuiMember();
			$.setRequest("payMembers", payMembers);
			//根据页面获取的id,获取对应的人员信息，如果id为空,
			Long id=$.getLong(params, "id");
			if(id != null){
				csUnitPerson = csUnitPersonService.getCsUnitPersonById(id);
				csMember = csUnitPerson.get$csupMember();
				csMemberInfo = csMember.get$csmInfo();
				$.setRequest("canVisible",true);
	
			} 
			if(csUnitPerson == null){
				csUnitPerson = new CsUnitPerson();
				csMember = new CsMember();
				csMemberInfo = new CsMemberInfo();
				 $.setRequest("canVisible",false);
				 
			}
			
			ControllerHelper.setObjectDefaultValue(csUnitPerson,controller);
			ControllerHelper.setObjectDefaultValue(csMember,controller);
			ControllerHelper.setObjectDefaultValue(csMemberInfo,controller);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().equals(e);
			$.SetTips("");
		}
		
		return "edit";
	}
	
	
	public String save(){
		try{
			final CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitPerson);//进入之前的预处理	
			//String string = $.getString("entrypoint");
			$.SetRequest("controller",controller);
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("id")) && csUnitPerson.getCsupId()==null);
			//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
			csUnitPersonService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
					
					if(bIsAdd){
						//添加人员信息时首先现在会员表csm_member中插入会员信息记录
						String password = $.zerofill($.rand(999999), 6);//自动生成6位随机密码
						Long host = UnitLoginHelper.getLogin().getCsuuHost();//城市
						csMember.setCsmHost(host);
						String username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
						Map<String,Object> param = new HashMap();
						param.put(CsMember.F.csmUsername, username);
						//param.put("definex", "(csuo_status=1)");
						// 用户名去重
						while (csMemberService.getCsMember(param) != null) {
							username = SystemHelper.randomUsername(Constant.USERNAME_LENGTH);
							param.remove(CsMember.F.csmUsername);
							param.put(CsMember.F.csmUsername, username);
						}
						if(csMember.getCsmMobile$() != null && !csMember.getCsmMobile$().trim().equals("")){
							CsMember cMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, csMember.getCsmMobile$()));
							if(cMember != null){
								$.SetTips("手机号已存在，保存失败");
								return null;
							}
						}
						
						
						//设置会员的相关信息，对于部分字段设置默认值，保存会员的相关信息到cs_member
						csMember.setCsmUsername(username);
						csMember.setCsmPassword($.md5(password));
						//csMember.setCsmName(csUnitPerson.getCsupName());
						csMember.setCsmAdder(UnitLoginHelper.getLogin().getCsuuId());
						csMember.setCsmStatus(csUnitPerson.getCsupStatus());
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
						csMember.setCsmFrom((short)5);
						csMember = csMemberService.saveCsMember(csMember);
						
						//保存会员信息相关信息到cs_member_info
						//CsMemberInfo csMemberInfo = new CsMemberInfo();
						csMemberInfo.setCsmiHost(host);
						csMemberInfo.setCsmiName(csUnitPerson.getCsupName());
						csMemberInfo.setCsmiMemberId(csMember.getCsmId());
						csMemberInfo.setCsmiAddTime(csMember.getCsmAddTime());
						csMemberInfo.setCsmiUpdateTime(csMember.getCsmAddTime());
						csMemberInfo.setCsmiStatus((short)1);
						
						csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
						
						
						
						//保存会员信息相关信息到cs_member_info成功后，生成对应的csmiId,生成完成后反写到cs_member表中的csm_Info字段中
						csMember.setCsmInfo(csMemberInfo.getCsmiId());
						csMemberService.updateCsMember$NotNull(csMember);
						
						//保存会员关系到cs_member_ship表中
						csMemberShip.setCsmsHost(host);
						//csMemberShip.setCsmsPayer(UnitLoginHelper.getLogin().getCsuuUnitInfo());
						csMemberShip.setCsmsTargeter(csMember.getCsmId());
						csMemberShip.setCsmsAddTime(csMember.getCsmAddTime());
						csMemberShip.setCsmsStatus(csUnitPerson.getCsupStatus());
						csMemberShipService.saveCsMemberShip(csMemberShip);
												
						
						//保存人员信息到cs_unit_person
						csUnitPerson.setCsupHost(UnitLoginHelper.getLogin().getCsuuHost());
						csUnitPerson.setCsupInfo(UnitLoginHelper.getLogin().getCsuuUnitInfo());
						csUnitPerson.setCsupMember(csMember.getCsmId());
						csUnitPerson.setCsupAddTime(csMember.getCsmAddTime());
						csUnitPerson.setCsupUpdateTime(csUnitPerson.getCsupAddTime());
						ControllerHelper.setObjectDefaultValue(csUnitPerson,controller);
						
						if(csUnitPersonService.saveCsUnitPerson(csUnitPerson) != null){
							LoggerHelper.writeLog(CsUnitUser.class,"add","添加了[人员信息]["+csUnitPerson.getCsupName()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitPerson);
							$.SetTips("保存人员信息成功");
							
                            UtilHelper.sendTemplateSMS(csUnitPerson.getCsupHost(),"REGIST_CODE",csMember.getCsmMobile$(),"欢迎使用北京出行，你的系统登录名为{mobile}，密码为{code}。"
                                    ,SMSType.通知类短信,$.add("mobile", csMember.getCsmMobile$()).add("code", password));
						}else{
							$.SetTips("系统繁忙，请稍后再试！");
						}
					}else{
						if(csUnitPerson.getCsupFlag()==null)csUnitPerson.setCsupFlag("");
						//csUnitPerson.setCsupPassword($.md5(String.valueOf((int)(Math.random()*1000000))));
						csUnitPerson.setCsupId($.getLong("id"));
						csUnitPerson.setCsupUpdateTime(new Date());
						
						csUnitPersonService.updateCsUnitPerson$NotNull(csUnitPerson);
						csUnitPerson= csUnitPersonService.getCsUnitPersonById(Long.valueOf($.getString("id")));
						csMember.setCsmId(csUnitPerson.get$csupMember().getCsmId());
						//csMember.setCsmName(csUnitPerson.getCsupName());
						csMember.setCsmStatus(csUnitPerson.getCsupStatus());
						csMember.setCsmUpdateTime(csUnitPerson.getCsupUpdateTime());
						csMemberService.updateCsMember$NotNull(csMember);
						
						csMemberInfo.setCsmiId(csMemberService.getCsMemberById(csMember.getCsmId()).get$csmInfo().getCsmiId());
						csMemberInfo.setCsmiName(csMember.getCsmName());
						csMemberInfo.setCsmiUpdateTime(csUnitPerson.getCsupUpdateTime());
						csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);						
						$.SetTips("修改人员信息成功");	
					}
					
					return null;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint"))); 
	}
	
	public String del(){
		try{
			Long pid=$.getLong("id");
			Long[] ids= new Long[]{pid};
			String strIds=$.getString( "ids");
			if(!$.empty(strIds) && !strIds.trim().equals("")){//批量删除
				String[] array = strIds.split(",");
				ids=new Long[array.length];
				for(int i=0;i<array.length;i++)
					ids[i]=Long.valueOf(array[i]);
			}		
			for(Long id:ids){
				//首先根据id获取csUnitPerson对象，根据csUnitPerson关联的会员进行会员删除（cs_member），根据会员关联的会员信息删除会员信息(cs_member_info),根据付款会员和使用会员查询到会员关系记录，并进行删除。
				csUnitPerson = csUnitPersonService.getCsUnitPersonById(id);
				
				
				
				Map <String,Object> params = new HashMap();
				params.put(CsMemberShip.F.csmsHost, UnitLoginHelper.getLogin().getCsuuHost());
				params.put(CsMemberShip.F.csmsTargeter,csUnitPerson.get$csupMember().getCsmId());
				params.put("definex", "(csms_status>=0)");
				csMemberShip = csMemberShipService.getCsMemberShip(params);
				csMemberShipService.removeCsMemberShipById(csMemberShip.getCsmsId());
				csMemberInfoService.removeCsMemberInfoById(csUnitPerson.get$csupMember().getCsmInfo());
				csMemberService.removeCsMemberById(csUnitPerson.getCsupMember());
				csUnitPersonService.removeCsUnitPersonById(id);
					//记录操作日志
				if(csUnitPerson != null)
				LoggerHelper.writeLog(CsUnitPerson.class,"del","删除了[人员信息]["+csUnitPerson.getCsupName()+"]",(Long)$.getSession("ccclubs_login_id"), UnitLoginHelper.getLogin());
				//删除人员的同时，如果该员工为部门领导，同时删除部门上面的审核人员信息
				List<CsUnitGroup> csUnitGroups = null;
				Map<String,String> param = new HashMap();
				param.put(CsUnitGroup.F.csugPerson, String.valueOf(id));
				param.put("definex", "csug_status > 0");
				csUnitGroups = csUnitGroupService.getCsUnitGroupList(param, 50);
				if(csUnitGroups != null && csUnitGroups.size() > 0){
					for(CsUnitGroup csUnitGroup:csUnitGroups){
//						CsUnitGroup csUnitGroup_new =  new CsUnitGroup();
//						csUnitGroup_new.setCsugId(csUnitGroup.getCsugId());
//						csUnitGroup_new.setCsugPerson(null);
//						csUnitGroup_new.update();
						csUnitGroup.setCsugPerson(null);
						csUnitGroupService.updateCsUnitGroup(csUnitGroup);
					}
				}
			}
			$.SetTips("人员删除成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsUnitPerson.class);
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			//强制限制域
			//params.put("HOSTS",SystemHelper.testHost(null));
			//params.put("desc","csm_id");//默认以主键降序，传给Dao层
			//String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			String csuoState = $.getString("csuoState");
			String csmMobile = $.getString("csmMobile");
			String csupName = $.getString("csupName");
			Integer csUnitGroup = $.getInteger("csUnitGroup");
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//查询条件传递到转跳
			Map<String,Object> param_dept=new HashMap();
			param_dept.put("csugInfo", UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
			param_dept.put("definex", "(csug_status>=0)");
			List<CsUnitGroup> csUnitGroups = csUnitGroupService.getCsUnitGroupList(param_dept, -1);
			if(csmMobile != null && !"".equals(csmMobile)){
				//if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
				Map<String,Object> param_csm = new HashMap<String,Object>();
				param_csm.put(CsMember.F.csmStatus, csuoState);
				String strDefinex = "";
				strDefinex+="csm_mobile like '%"+csmMobile+"%'";
				//param.put(CsMember.F.csmMobile, csmMobile);
				param_csm.put(CsMember.F.csmGroup, csUnitGroup);
				param_csm.put(CsMember.F.csmHost,unitInfo.getCsuiHost());
				param_csm.put("definex", strDefinex);
				List<CsMember> csMemberList = csMemberService.getCsMemberList(param_csm, -1);
				//CsMember csMember = csMemberService.getCsMember(param);
				String ids = "";
				String id = "";
				if(csMemberList != null && csMemberList.size() > 0){
					 Iterator<CsMember> iterator = csMemberList.iterator();
					 if(csMemberList.size() == 1)
						 ids += iterator.next().getCsmId();
					 else{
						 while(iterator.hasNext()){
								CsMember csm = iterator.next();
								ids += "'" + csm.getCsmId() + "',";
								id = "'" + csm.getCsmId() + "'";
							}
						 ids = ids.replace(id+",", id);
					 }
						String definex = "csup_member in ("+ids+")";
						Map<String,Object> param_csup = new HashMap<String,Object>();
						param_csup.put(CsUnitPerson.F.csupHost, unitInfo.getCsuiHost());
						param_csup.put(CsUnitPerson.F.csupInfo, unitInfo.getCsuiId());
						param_csup.put("definex",definex);
						Page<CsUnitPerson> page = csUnitPersonService.getCsUnitPersonPage($.getInteger("page",0), 16, param_csup);																							
						$.SetRequest("csUnitPerson", page.getResult());
						$.SetRequest("page", page);
				}
				
			}else{
				params.put(CsUnitPerson.F.csupGroup, csUnitGroup);
				params.put(CsUnitPerson.F.csupStatus, csuoState);
				params.put(CsUnitPerson.F.csupHost, unitInfo.getCsuiHost());
				params.put(CsUnitPerson.F.csupInfo, unitInfo.getCsuiId());
				String strDefinex = "";
				if(csupName != null && !"".equals(csupName))
				strDefinex+="csup_name like '%"+csupName+"%'";
				//strDefinex+=" or csm_name like '%"+strTerm.replaceAll("'", "''")+"%'";
				params.put("definex",strDefinex);
				Page<CsUnitPerson> page = csUnitPersonService.getCsUnitPersonPage($.getInteger("page",0), 16, params);								
				$.SetRequest("csUnitPerson", page.getResult());
				$.SetRequest("page", page);
			}
			$.setRequest("csUnitGroups", csUnitGroups);
			$.setRequest("groupId", csUnitGroup);
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			
			//$.SendAjax这个函数，第一个参数不是字符串，会自动把第一个对象转成json格式的字符串
			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
			return $.SendAjax("[]", "UTF-8");
		}
		return "index";
	}
	
	public String resetPassword(){
		try{
			Long id=$.getLong( "id");
			csUnitPerson = csUnitPersonService.getCsUnitPersonById(id);
			csMember = csUnitPerson.get$csupMember();
			String password = $.zerofill($.rand(999999), 6);
			csMember.setCsmPassword($.md5(password));
			csMemberService.updateCsMember(csMember);
			
			UtilHelper.sendTemplateSMS(csUnitPerson.getCsupHost(),"RESET_CODE",csMember.getCsmMobile(),CommonMessage.MSG0902,SMSType.通知类短信,$.add("code", password));
			$.SetTips("密码重置成功，已通过短信发送给员工！");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	public String member_inquire(){
		try{
			
			Long id=$.getLong( "id");
			csUnitPerson = csUnitPersonService.getCsUnitPersonById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String details(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitPerson);//进入之前的预处理
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("id",$.getString(params, "id"));
			
			//获取所有的部门信息
			Map<String,Object> param=new HashMap();
			
			param.put("csugInfo", UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
			param.put("definex", "(csug_status>=0)");
			List<CsUnitGroup> csUnitGroups = csUnitGroupService.getCsUnitGroupList(param, 50);
			$.setRequest("csUnitGroups", csUnitGroups);
			
			List<CsMember> payMembers = UnitLoginHelper.getLogin().get$csuuUnitInfo().get$csuiMember();
			$.setRequest("payMembers", payMembers);
			
			Long id=$.getLong( "id");
			csUnitPerson = csUnitPersonService.getCsUnitPersonById(id);
			csMember = csUnitPerson.get$csupMember();
			csMemberInfo = csMember.get$csmInfo();
			ControllerHelper.setObjectDefaultValue(csUnitPerson,controller);
			ControllerHelper.setObjectDefaultValue(csMember,controller);
			ControllerHelper.setObjectDefaultValue(csMemberInfo,controller);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "details";
	}

	public ICsUnitPersonService getCsUnitPersonService() {
		return csUnitPersonService;
	}

	public void setCsUnitPersonService(ICsUnitPersonService csUnitPersonService) {
		this.csUnitPersonService = csUnitPersonService;
	}

	public CsUnitPerson getCsUnitPerson() {
		return csUnitPerson;
	}

	public void setCsUnitPerson(CsUnitPerson csUnitPerson) {
		this.csUnitPerson = csUnitPerson;
	}

	public ICsUnitGroupService getCsUnitGroupService() {
		return csUnitGroupService;
	}

	public void setCsUnitGroupService(ICsUnitGroupService csUnitGroupService) {
		this.csUnitGroupService = csUnitGroupService;
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

	public ICsMemberShipService getCsMemberShipService() {
		return csMemberShipService;
	}

	public void setCsMemberShipService(ICsMemberShipService csMemberShipService) {
		this.csMemberShipService = csMemberShipService;
	}

	public CsMemberInfo getCsMemberInfo() {
		return csMemberInfo;
	}

	public void setCsMemberInfo(CsMemberInfo csMemberInfo) {
		this.csMemberInfo = csMemberInfo;
	}

	public CsMember getCsMember() {
		return csMember;
	}

	public void setCsMember(CsMember csMember) {
		this.csMember = csMember;
	}

	public CsMemberShip getCsMemberShip() {
		return csMemberShip;
	}

	public void setCsMemberShip(CsMemberShip csMemberShip) {
		this.csMemberShip = csMemberShip;
	}
	
	
	
}
