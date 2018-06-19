package com.ccclubs.action.admin.analysis;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.entity.MemberConsuMe;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.service.admin.ICsAnalysisService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.node.Export;
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.util.Page;
@SuppressWarnings({"unchecked","unused"})
public class MemberConsuMeAction {
	ICsMemberService csMemberService;
	ICommonMoneyService commonMoneyService;
	MemberConsuMe memberConsuMe;
	ICsAnalysisService csAnalysisService;
	
	private CsUnitInfo unitInfo;
	private CsUnitGroup unitGroup;
	private CsMember csMember;
	
	
	/**
	 * 会员帐号管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsMember.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsMember.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsMember.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsMember.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsMember.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsMember.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","csm_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("csmRefer")!=null && objects.get("csmReferNot")==null){
				objects.remove("HOSTS");
			}
			
			
			
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			
			/************LAZY3Q_CODE_EXECUTE************/
			
			/************LAZY3Q_CODE_EXECUTE************/
			
			//调用Service获取分页
			
			if($.getString("csmId") != null && !$.empty($.getString("csmId")) ){
				csMember = CsMember.get(Long.parseLong($.getString("csmId")));
			}
			
			if($.getString("csuiId") != null && !$.empty($.getString("csuiId"))){
				unitInfo = CsUnitInfo.get(Long.parseLong($.getString("csuiId")));
			}
			
			if($.getString("csugId") != null && !$.empty($.getString("csugId"))){
				unitGroup = CsUnitGroup.get(Long.parseLong($.getString("csugId")));
			}
			
			
			final Page<MemberConsuMe>  consumePage = csAnalysisService.getMemberConsuMePage($.getInteger("page", 0), $.getInteger("size",16), objects);

			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/user/member.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return consumePage;
					}
				}, MemberConsuMe.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			} 
			
			
			
			
			
			//把分页对象放入request
			$.setRequest("page", consumePage);	
			
			//重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
			/************LAZY3Q_AFTER_EXECUTE************/
			/************LAZY3Q_AFTER_EXECUTE************/

			
			//把当前查询到的条数放入request
			objects.put("count",consumePage.getCount());
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	
	
	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public MemberConsuMe getMemberConsuMe() {
		return memberConsuMe;
	}

	public void setMemberConsuMe(MemberConsuMe memberConsuMe) {
		this.memberConsuMe = memberConsuMe;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public CsUnitInfo getUnitInfo() {
		return unitInfo;
	}

	public void setUnitInfo(CsUnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	public CsUnitGroup getUnitGroup() {
		return unitGroup;
	}

	public void setUnitGroup(CsUnitGroup unitGroup) {
		this.unitGroup = unitGroup;
	}



	public ICsAnalysisService getCsAnalysisService() {
		return csAnalysisService;
	}

	public void setCsAnalysisService(ICsAnalysisService csAnalysisService) {
		this.csAnalysisService = csAnalysisService;
	}



	public CsMember getCsMember() {
		return csMember;
	}



	public void setCsMember(CsMember csMember) {
		this.csMember = csMember;
	}

}
