package com.ccclubs.action.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.UUID;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.node.Export;
import com.lazy3q.lang.*;
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;
import com.lazy3q.web.util.Tree;
import com.lazy3q.util.Function;
import com.lazy3q.util.Meet;

import com.opensymphony.xwork2.ActionContext;
		   
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsUnitGroupService;
/************LAZY3Q_CODE_IMPORT************/
/************LAZY3Q_CODE_IMPORT************/


/**
 * 分组信息的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class GroupAction
{
	ICsUnitGroupService csUnitGroupService;
	
	CsUnitGroup csUnitGroup;
	
	/**
	 * 分组信息管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsUnitGroup.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsUnitGroup.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsUnitGroup.class);
			
			objects.put(CsUnitGroup.F.csugInfo, UnitLoginHelper.getLogin().getCsuuUnitInfo());
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsUnitGroup.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsUnitGroup.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","csug_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			
						
			//调用Service获取分页
			Page page = csUnitGroupService.getCsUnitGroupPage($.getInteger("page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.setRequest("page", page);	
			
			//重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
			
			//把当前查询到的条数放入request
			objects.put("count",page.getCount());
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	
	
	/**
	 * 分组信息添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csUnitGroupToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csUnitGroup这个对象为什么不用$.setRequest,因为csUnitGroup有get,set方法，由struts处理的
				csUnitGroup=csUnitGroupService.getCsUnitGroupById(id);
				//确认域标识
				if(csUnitGroup.getCsugInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue()){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csUnitGroup==null)
				csUnitGroup = new CsUnitGroup();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csUnitGroup);
			/************LAZY3Q_CODE_EDIT************/
			/************LAZY3Q_CODE_EDIT************/

		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	/**
	 * 数据事件触发
	 * @param type 动作类型
	 * @param object 操作的对象
	 * 如果发生错误，不抛出，尽量避免影响主业务
	 * @return
	 */
	public void on(String type,CsUnitGroup object,CsUnitGroup old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsUnitGroup.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存分组信息
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && csUnitGroup.getCsugId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsUnitGroupToken = $.getString("csUnitGroupToken");
			final String sessionCsUnitGroupToken = $.getSession("csUnitGroupToken");
			if($.equals(requestCsUnitGroupToken,sessionCsUnitGroupToken)){
				$.removeSession("csUnitGroupToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csUnitGroupService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						if(bIsAdd){
							//设置默认值
							if(csUnitGroup.getCsugUpdateTime()==null)
								csUnitGroup.setCsugUpdateTime(new Date());
							if(csUnitGroup.getCsugAddTime()==null)
								csUnitGroup.setCsugAddTime(new Date());
							if(csUnitGroup.getCsugStatus()==null)
								csUnitGroup.setCsugStatus((short)1);
							
							csUnitGroup.setCsugHost(UnitLoginHelper.getLogin().getCsuuHost());
							csUnitGroup.setCsugInfo(UnitLoginHelper.getLogin().getCsuuUnitInfo());
							
							//确认数据修改权限
							if(csUnitGroup.getCsugInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue()){
								throw new RuntimeException("您没有权限修改数据");
							}
							
							csUnitGroup=csUnitGroupService.saveCsUnitGroup(csUnitGroup);			
							
							on("save",csUnitGroup,csUnitGroup);
							
							csUnitGroupService.updateCsUnitGroup$NotNull(csUnitGroup);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/
							
							//记录操作日志
							LoggerHelper.writeLog(CsUnitGroup.class,"add","添加了[分组信息]["+csUnitGroup.getCsugName()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitGroup,csUnitGroup.getCsugId());
							$.SetTips("保存分组信息成功");
						}else{
							if($.empty($.getString("edittype"))){
								//设置填充值
								csUnitGroup.setCsugUpdateTime(new Date());
								CsUnitGroup oldCsUnitGroup = CsUnitGroup.get(csUnitGroup.getCsugId());
								
								//确认数据修改权限
								if(oldCsUnitGroup.getCsugInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue()){
									throw new RuntimeException("您没有权限修改数据");
								}
								
								csUnitGroupService.updateCsUnitGroup$NotNull(csUnitGroup);
								LoggerHelper.writeLog(CsUnitGroup.class, "update",
										"修改了[分组信息]["+oldCsUnitGroup.getCsugName()+"]",
										(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsUnitGroup, csUnitGroup,false)
										,csUnitGroup.getCsugId());
								on($.getString("method"),csUnitGroup,oldCsUnitGroup);									
							}else{
								CsUnitGroup oldCsUnitGroup = csUnitGroupService.getCsUnitGroupById(csUnitGroup.getCsugId());
								//设置默认值
								csUnitGroup.setCsugRemark(oldCsUnitGroup.getCsugRemark());	
								csUnitGroup.setCsugData(oldCsUnitGroup.getCsugData());	
								csUnitGroup.setCsugUpdateTime(oldCsUnitGroup.getCsugUpdateTime());	
								csUnitGroup.setCsugAddTime(oldCsUnitGroup.getCsugAddTime());	
								csUnitGroup.setCsugStatus(oldCsUnitGroup.getCsugStatus());	
								
								//设置填充值
								if(csUnitGroup.getCsugUpdateTime()==null)
									csUnitGroup.setCsugUpdateTime(new Date());
								
								//确认数据修改权限
								if(oldCsUnitGroup.getCsugInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue()){
									throw new RuntimeException("您没有权限修改数据");
								}
								
								//从oldCsUnitGroup同步csUnitGroup未set过的值
								csUnitGroup.mergeSet(oldCsUnitGroup);
										
								csUnitGroupService.updateCsUnitGroup(csUnitGroup);
								LoggerHelper.writeLog(CsUnitGroup.class, "update", 
										"修改了[分组信息]["+oldCsUnitGroup.getCsugName()+"]",
										(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsUnitGroup, csUnitGroup,true)
										,csUnitGroup.getCsugId());
								on("update",csUnitGroup,oldCsUnitGroup);
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改分组信息成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csUnitGroupToken");//移除令牌	
				$.SetTips("对不起，请勿重复提交");
			}
			
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("group_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csUnitGroup.getCsugId());
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
	 * 删除分组信息
	 * @return
	 */
	public String del()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csUnitGroupService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
					Long id=$.getLong("id");
					CsUnitGroup csUnitGroup = csUnitGroupService.getCsUnitGroupById(id);
					//确认数据修改权限
					if(csUnitGroup.getCsugInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue()){
						throw new RuntimeException("您没有权限删除数据");
					}
					if(ConfigHelper.GetBoolean("logic_delete")==true)
						csUnitGroupService.removeCsUnitGroupById(id);
					else
						csUnitGroupService.deleteCsUnitGroupById(id);		
					//记录操作日志
					if(csUnitGroup!=null)
						LoggerHelper.writeLog(CsUnitGroup.class,"del","删除了[分组信息]["+csUnitGroup.getCsugName()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitGroup);
					on("delete",csUnitGroup,csUnitGroup);
					return null;
				}
			});
			
			$.SetTips("删除分组信息成功");
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
	 * 显示分组信息详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csUnitGroup=csUnitGroupService.getCsUnitGroupById(id);
			if(key!=null && id==null)
				csUnitGroup=CsUnitGroup.Get($.add(CsUnitGroup.F.csugId,key));
			//确认数据修改权限
			if(csUnitGroup.getCsugInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue()){
				throw new RuntimeException("您没有权限查看数据");
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据组名称或ID查询分组信息
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsUnitGroup.class);
			
			params.put(CsUnitGroup.F.csugInfo, UnitLoginHelper.getLogin().getCsuuUnitInfo());
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","csug_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csugId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csug_name like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsUnitGroup> list = csUnitGroupService.getCsUnitGroupList(params, $.getInteger("size",10));
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsUnitGroup csUnitGroup:list){
				Map map = new HashMap();
				map.put("value",csUnitGroup.getCsugId().toString());
				map.put("text", $.js(csUnitGroup.getKeyValue()));
				if(bObject==true)
					map.put("object",csUnitGroup);
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


	public ICsUnitGroupService getCsUnitGroupService()
	{
		return csUnitGroupService;
	}

	public void setCsUnitGroupService(ICsUnitGroupService csUnitGroupService)
	{
		this.csUnitGroupService = csUnitGroupService;
	}

	public CsUnitGroup getCsUnitGroup()
	{
		return csUnitGroup;
	}

	public void setCsUnitGroup(CsUnitGroup csUnitGroup)
	{
		this.csUnitGroup = csUnitGroup;
	}
}