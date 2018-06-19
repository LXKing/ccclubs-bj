package com.ccclubs.action.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UnitHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.util.Page;


/**
 * 人员信息的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class PersonAction
{
	ICsUnitPersonService csUnitPersonService;
	
	CsUnitPerson csUnitPerson;
	
	/**
	 * 人员信息管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = new HashMap();
			String strDefinex = " csm_id in ("+UnitLoginHelper.getUseIds()+") ";
			Page<CsMember> page = CsMember.where()
			.add("definex",strDefinex)
			.csmMobile($.getString("csmMobile"))
			.csmName($.getString("csmName"))
			.page($.getInteger("page",0), $.getInteger("size",20));
			
			$.setRequest("page", page);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	
	
	
	
	/**
	 * 用车人员添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csUnitPersonToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long member=$.getLong("member");
			if(member!=null){
				if(!$.idin(UnitLoginHelper.getUseIds(), member))
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				CsMember csMember = CsMember.get(member);
				$.setRequest("csMember", csMember);
				csUnitPerson=CsUnitPerson.where().csupInfo(UnitLoginHelper.getLogin().getCsuuUnitInfo()).csupMember(member).get();
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	
	/**
	 * 保存用车人员
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && csUnitPerson.getCsupId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsUnitPersonToken = $.getString("csUnitPersonToken");
			final String sessionCsUnitPersonToken = $.getSession("csUnitPersonToken");
			if($.equals(requestCsUnitPersonToken,sessionCsUnitPersonToken)){
				$.removeSession("csUnitPersonToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csUnitPersonService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						
						CsMember csMember = CsMember.get(csUnitPerson.getCsupMember());
						if(csMember==null){
							//如果会员不存在，则注册一个会员
							csMember = new CsMember();
							String csmName = $.getString("csmName");
							String csmMobile = $.getString("csmMobile");
							String csmPassword = $.zerofill($.rand(999999), 6);
							csMember.setNotNull(
								UnitLoginHelper.getLogin().getCsuuHost(),
								$.uuid(), 
								$.md5(csmPassword),
								0d,0d,0d,0,0,1.0d, 
								new Date(),(short)1
							);
							csMember.csmVDrive((short)0).csmVReal((short)0);
							csMember.csmMobile(csmMobile).csmName(csmName);
							csMember = csMember.save();
							
							//同时添加会员信息
							CsMemberInfo csMemberInfo = new CsMemberInfo();
							csMemberInfo.setNotNull(
								csMember.getCsmHost(), 
								csMember.getCsmId(), 
								new Date(), new Date(), (short)1
							);
							csMemberInfo.setCsmiName(csmName);
							csMemberInfo.csmiCertifyType($.getShort("csmiCertifyType"));
							csMemberInfo.csmiCertifyImage($.getString("csmiCertifyImage"));
							csMemberInfo.csmiCertifyNum($.getString("csmiCertifyNum"));
							csMemberInfo.csmiDriverImage($.getString("csmiDriverImage"));
							csMemberInfo.csmiDriverNum($.getString("csmiDriverNum"));
							csMemberInfo = csMemberInfo.save();
							
							//关联会员与会员信息
							new CsMember(csMember.getCsmId()).csmInfo(csMemberInfo.getCsmiId()).update();
							
							//绑定会员关系
							new CsMemberShip(
								csMember.getCsmHost()//城市 [非空]
							 	,$.getLong("csmsPayer")//付款帐号 [非空]
							 	,csMember.getCsmId()//使用帐号 [非空]
							 	,new Date()//添加时间 [非空]
							 	,null//备注
							 	,(short)1//状态 [非空]
							 ).save();
							
							//创建企业用车帐号
							new CsUnitPerson(
								csMember.getCsmHost()//城市 [非空]
							 	,UnitLoginHelper.getLogin().getCsuuUnitInfo()//所属单位 [非空]
							 	,csMember.getCsmName()//姓名 [非空]
							 	,csUnitPerson.getCsupGroup()//所属分组 [非空]
							 	,csMember.getCsmId()//帐号信息 [非空]
							 	,null//备注
							 	,null//备忘信息
							 	,new Date()//修改时间 [非空]
							 	,new Date()//添加时间 [非空]
							 	,csUnitPerson.getCsupStatus()//状态 [非空]
							 	,null
							 ).save();
							
							UtilHelper.sendTemplateSMS(csMember.getCsmHost(),"REGISTER_PASSWORD",
								csMember.getCsmMobile(), 
								"您在公务用车平台的登录密码为{password}，请妥善保管，并及时修改密码", 
								SMSType.通知类短信,
								$.add("password",csmPassword)
							);
							
							$.SetTips("创建用车帐号成功！");
							
						}else{
							if(csUnitPerson.getCsupId()==null){
								//创建企业用车帐号
								new CsUnitPerson(
									csMember.getCsmHost()//城市 [非空]
								 	,UnitLoginHelper.getLogin().getCsuuUnitInfo()//所属单位 [非空]
								 	,csMember.getCsmName()//姓名 [非空]
								 	,csUnitPerson.getCsupGroup()//所属分组 [非空]
								 	,csMember.getCsmId()//帐号信息 [非空]
								 	,null//备注
								 	,null//备忘信息
								 	,new Date()//修改时间 [非空]
								 	,new Date()//添加时间 [非空]
								 	,csUnitPerson.getCsupStatus()//状态 [非空]
								 	,null
								 ).save();
							}else{
								csUnitPerson.update();
							}
							
							//更新会员证件信息
							if(csMember.get$csmInfo()==null){
								CsMemberInfo csMemberInfo = new CsMemberInfo();
								csMemberInfo.setNotNull(
									csMember.getCsmHost(), 
									csMember.getCsmId(), 
									new Date(), new Date(), (short)1
								);
								csMemberInfo.csmiCertifyType($.getShort("csmiCertifyType"));
								csMemberInfo.csmiCertifyImage($.getString("csmiCertifyImage"));
								csMemberInfo.csmiCertifyNum($.getString("csmiCertifyNum"));
								csMemberInfo.csmiDriverImage($.getString("csmiDriverImage"));
								csMemberInfo.csmiDriverNum($.getString("csmiDriverNum"));
								csMemberInfo = csMemberInfo.save();
							}else{
								CsMemberInfo csMemberInfo = new CsMemberInfo(csMember.getCsmInfo());
								csMemberInfo.csmiCertifyType($.getShort("csmiCertifyType"));
								csMemberInfo.csmiCertifyImage($.getString("csmiCertifyImage"));
								csMemberInfo.csmiCertifyNum($.getString("csmiCertifyNum"));
								csMemberInfo.csmiDriverImage($.getString("csmiDriverImage"));
								csMemberInfo.csmiDriverNum($.getString("csmiDriverNum"));
								csMemberInfo.update();
							}
								
							//重设支付帐号
							Long csmsPayer = $.getLong("csmsPayer");
							CsMemberShip csMemberShip = CsMemberShip.where().csmsTargeter(csMember.getCsmId())
							.add("definex", "csms_payer in ("+UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember()+")").get();
							if(csMemberShip.getCsmsPayer().longValue()!=csmsPayer.longValue()){
								csMemberShip.setCsmsPayer(csmsPayer);
								csMemberShip.update();
							}
							
							$.SetTips("修改用车帐号成功！");
						}
						return null;
					}
				});
				
			}else{
				$.removeSession("csUnitPersonToken");//移除令牌	
				$.SetTips("对不起，请勿重复提交");
			}
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("person.edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csUnitPerson.getCsupMember());
				else
					return $.Redirect($.xeh($.getString("editorpoint")));
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}

	
	
	
	
	
	
	/**
	 * 删除用车人员
	 * @return
	 */
	public String del()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csUnitPersonService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
					Long id = $.getLong("id");
					if(!$.idin(UnitLoginHelper.getUseIds(),id))
						throw new RuntimeException("当前用户没有权限删除该数据");
					CsMemberShip csMemberShip = CsMemberShip.where().csmsTargeter(id).add("definex", "csms_payer in ("+UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember()+")").get();
					if(csMemberShip!=null)
						csMemberShip.delete();
					csUnitPerson = CsUnitPerson.where().csupMember(id).csupInfo(UnitLoginHelper.getLogin().getCsuuUnitInfo()).get();
					if(csUnitPerson!=null)
						csUnitPerson.delete();
					$.SetTips("删除用车帐号成功");
					return null;
				}
			});
			
			$.SetTips("删除用车人员成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			if(e.getMessage().indexOf("没有权限删除该数据")>-1)
				return $.SendHtml("<script>alert('对不起，你没有权限删除该数据');</script>", "UTF-8");
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}	
	
	
	
	
	
	

	/**
	 * 根据姓名或ID查询人员信息
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsUnitPerson.class);
			
			params.put(CsUnitPerson.F.csupInfo, UnitLoginHelper.getLogin().getCsuuUnitInfo());
			
			//反强制限制域
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","csup_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csupId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csup_name like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsUnitPerson> list = csUnitPersonService.getCsUnitPersonList(params, $.getInteger("size",10));
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsUnitPerson csUnitPerson:list){
				Map map = new HashMap();
				map.put("value",csUnitPerson.getCsupId().toString());
				map.put("text", $.js(csUnitPerson.getKeyValue()));
				if(bObject==true)
					map.put("object",csUnitPerson);
				result.add(map);
			}
			/************LAZY3Q_AFTER_QUERY************/
			/************LAZY3Q_AFTER_QUERY************/

			
			//$.SendAjax这个函数，第一个参数不是字符串，会自动把第一个对象转成json格式的字符串
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/


	public ICsUnitPersonService getCsUnitPersonService()
	{
		return csUnitPersonService;
	}

	public void setCsUnitPersonService(ICsUnitPersonService csUnitPersonService)
	{
		this.csUnitPersonService = csUnitPersonService;
	}

	public CsUnitPerson getCsUnitPerson()
	{
		return csUnitPerson;
	}

	public void setCsUnitPerson(CsUnitPerson csUnitPerson)
	{
		this.csUnitPerson = csUnitPerson;
	}
}