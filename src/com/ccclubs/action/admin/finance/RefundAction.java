package com.ccclubs.action.admin.finance;

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
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsRefundService;
/**************LAZY3Q_CODE_IMPORT**************/
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsAlipayRefund;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 会员退款的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class RefundAction
{
	ICsRefundService csRefundService;
	
	CsRefund csRefund;
	
	/**
	 * 会员退款管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsRefund.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsRefund.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsRefund.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsRefund.class));
			//有退款权限即可操作状态修改
			boolean hasRefund = false;
			String strlimitsId = $.getString("limitsid");
			if (null != strlimitsId && strlimitsId.trim().length() != 0)
		    {
		      String[] array = strlimitsId.split("-");
		      if (array.length == 2) {
		          Integer limitsId = Integer.valueOf(Integer.parseInt(array[0]));
		          //拥有扩展退款权限128
		          if((limitsId & 128) == 128) {
		              hasRefund = true;
		          }
		      }
		    }
			$.SetRequest("hasRefund", hasRefund);
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsRefund.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsRefund.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","csr_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("csrMember")!=null && objects.get("csrMemberNot")==null){
				objects.remove("HOSTS");
			}
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/finance/refund.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csRefundService.getCsRefundPage(page, size, finalParams);
					}
				}, CsRefund.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csRefundService.getCsRefundPage($.getInteger("page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.setRequest("page", page);	
			
			//重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
			/************LAZY3Q_AFTER_EXECUTE************/
			/************LAZY3Q_AFTER_EXECUTE************/

			
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
	 * 会员退款添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csRefundToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csRefund这个对象为什么不用$.setRequest,因为csRefund有get,set方法，由struts处理的
				csRefund=csRefundService.getCsRefundById(id);
				//确认域标识
				if(SystemHelper.testHost(csRefund.getCsrHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csRefund==null)
				csRefund = new CsRefund();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csRefund);
			/********************LAZY3Q_CODE_EDIT*************************/
			//如果是退款操作，那么先判断是否可以以支付宝退款方式退款
			String sTitle = CTRL.getTitle();
			if($.equals(sTitle, "退款")){
				//获取会员可用余额
				ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
				Double ammount = csRefund.get$csrMember().getCsmMoney();
				//正在退款的金额
				Double returning = CsAlipayRefund.where().csarMember(csRefund.getCsrMember()).csarStatus(0).eval("sum(csar_amount)");
				//可退金额
				$.SetRequest("money", ammount-$.or(returning,0d));
				
				//获取支付宝可退金额
				/*{
					ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
					List<CsAlipayRecord> records = csAlipayRecordService.getCsAlipayRecordList(
						$.add("csarMember", csRefund.getCsrMember())//会员ID
						.add("csarBankType", 0)//类型为支付宝
						//.add("csarRealName", csRefund.getCsrAccountName())//真实姓名
						//.add("csarBuyerAccount", csRefund.getCsrAccountNo())//支付宝帐号
						.add("csarStatus", 1)//成功的充值
						.add("definex", "csar_refund< csar_amount and ((csar_id > 7830 and (csar_pay_method = 'directPay' or csar_pay_method = 'bankPay')) or (csar_pay_method = 'wap01' and csar_id > 20588))")//可退额大于0  (2013年4月30号之后的充值)		
						//.add("definex", "csar_refund < csar_amount and csar_id > 7830 and (csar_pay_method = 'directPay' or csar_pay_method = 'bankPay')")////可退额大于0 (2013年4月30号之后的充值) ，2014年7月1日以后通过手机支付宝充值的	
					, -1);
					Double total = 0d;
					for(CsAlipayRecord csAlipayRecord:records)
						total+=(csAlipayRecord.getCsarAmount()-csAlipayRecord.getCsarRefund());
					$.SetRequest("alipay", total);//支付宝可退额
				}*/
				
				
				Map<String,Double> mapping=new HashMap();
				Short[] types = new Short[]{0,10};
				for(short type:types){//获取每个接口的可退金额
					String definex = "csar_refund < csar_amount";
					//if(type==0)definex+=" and ((csar_id > 7830 and (csar_pay_method = 'directPay' or csar_pay_method = 'bankPay')) or (csar_pay_method = 'wap01' and csar_id > 20588))";
					Double canret = CsAlipayRecord.where().csarMember(csRefund.getCsrMember()).csarBankType(type).csarStatus(1).definex(definex).eval("sum(csar_amount-csar_refund)");
					Double reting = CsAlipayRefund.where().csarMember(csRefund.getCsrMember()).csarBankType(type).csarStatus(0).eval("sum(csar_amount)");
					mapping.put(""+type, $.or(canret,0d)-$.or(reting,0d));
				}
				$.SetRequest("mapping", mapping);//支付宝可退额
				
			}			
			/********************LAZY3Q_CODE_EDIT*************************/

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
	public void on(String type,CsRefund object,CsRefund old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsRefund.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存会员退款
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csRefund.getCsrId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsRefundToken = $.getString("csRefundToken");
			final String sessionCsRefundToken = $.getSession("csRefundToken");
			if($.equals(requestCsRefundToken,sessionCsRefundToken)){
				$.removeSession("csRefundToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csRefundService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csRefund.getCsrId()==null?"":csRefund.getCsrId());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csRefund.setCsrId(Long.valueOf(sItem));
							
								//设置默认值
								if(csRefund.getCsrAmount()==null)
									csRefund.setCsrAmount(0d);
								if(csRefund.getCsrAddTime()==null)
									csRefund.setCsrAddTime(new Date());
								if(csRefund.getCsrCheckTime()==null)
								csRefund.setCsrCheckTime(new Date());
								if(csRefund.getCsrBackTime()==null)
								csRefund.setCsrBackTime(new Date());
								if(csRefund.getCsrEndTime()==null)
								csRefund.setCsrEndTime(new Date());
								if(csRefund.getCsrStatus()==null)
									csRefund.setCsrStatus((short)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csRefund);
								//添加域标识
								if(SystemHelper.testHost(csRefund.getCsrHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csRefund=csRefundService.saveCsRefund(csRefund);			
								
								on("save",csRefund,csRefund);
							}							
							
							csRefundService.updateCsRefund$NotNull(csRefund);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsRefund.class,"add","添加了[会员退款]["+csRefund.getCsrId()+"]",(Long)$.getSession("ccclubs_login_id"), csRefund,csRefund.getCsrId());
							$.SetTips("保存会员退款成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csRefundService.updateCsRefundByConfirm(ActionHelper.getSetValuesFromModel(csRefund), params);
								LoggerHelper.writeLog(CsRefund.class, "update", 
											"批量修改了[会员退款]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csRefund,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csRefund.setCsrId(Long.valueOf(sid));
									CsRefund oldCsRefund = CsRefund.get(Long.valueOf(sid));
									csRefundService.updateCsRefund$NotNull(csRefund);
									LoggerHelper.writeLog(CsRefund.class, "update", 
											"修改了[会员退款]["+oldCsRefund.getCsrId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsRefund, csRefund,false)
											,Long.valueOf(sid));
									on($.getString("method"),csRefund,oldCsRefund);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									
									CsRefund oldCsRefund = CsRefund.get(csRefund.getCsrId());
									csRefundService.updateCsRefund$NotNull(csRefund);
									LoggerHelper.writeLog(CsRefund.class, "update", 
											"修改了[会员退款]["+oldCsRefund.getCsrId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsRefund, csRefund,false)
											,csRefund.getCsrId());
									on($.getString("method"),csRefund,oldCsRefund);									
								}else{
									CsRefund oldCsRefund = csRefundService.getCsRefundById(csRefund.getCsrId());
									//设置默认值
									csRefund.setCsrId(oldCsRefund.getCsrId());	
									csRefund.setCsrMember(oldCsRefund.getCsrMember());	
									csRefund.setCsrChecker(oldCsRefund.getCsrChecker());	
									csRefund.setCsrBacker(oldCsRefund.getCsrBacker());	
									csRefund.setCsrMemo(oldCsRefund.getCsrMemo());	
									csRefund.setCsrAddTime(oldCsRefund.getCsrAddTime());	
									csRefund.setCsrStatus(oldCsRefund.getCsrStatus());	
									
									//设置填充值
									//添加域标识
									if(SystemHelper.testHost(csRefund.getCsrHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsRefund同步csRefund未set过的值
									csRefund.mergeSet(oldCsRefund);
											
									csRefundService.updateCsRefund(csRefund);
									LoggerHelper.writeLog(CsRefund.class, "update", 
											"修改了[会员退款]["+oldCsRefund.getCsrId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsRefund, csRefund,true)
											,csRefund.getCsrId());
									on("update",csRefund,oldCsRefund);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改会员退款成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csRefundToken");//移除令牌	
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_CODE_RETURN************/
			/************LAZY3Q_CODE_RETURN************/

			
			//从ctrl中获取自定义转跳地址,是不是发现ctrl这个东东很神奇？
			String redirectUrl = CTRL==null ? null : CTRL.getRedirect();
			if(!$.empty(redirectUrl))return $.Redirect(redirectUrl);//如果有转跳地址，下面的逻辑就不执行了
			
			//complete是什么呢，jsonp回调函数，jsonp技术建议百度一下
			String complete = $.getString("complete");
			if(!$.empty(complete)){
				$.removeSession("tips");
				return $.SendHtml("<script>"+complete+"("+$.json(csRefund)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("refund_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csRefund.getCsrId());
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
	 * 删除会员退款
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/finance/refund.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csRefundService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
			
					Long pid=$.getLong("id");
					Long[] ids=new Long[]{pid};
					String strIds=$.getString( "ids");
					if(!$.empty(strIds) && !strIds.trim().equals("")){//批量删除
						String[] array = strIds.split(",");
						ids=new Long[array.length];
						for(int i=0;i<array.length;i++)
							ids[i]=Long.valueOf(array[i]);
					}		
					for(Long id:ids){
							CsRefund csRefund = csRefundService.getCsRefundById(id);
							//确认域标识
							if(SystemHelper.testHost(csRefund.getCsrHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csRefundService.removeCsRefundById(id);
							else
							csRefundService.deleteCsRefundById(id);			
							//记录操作日志
							if(csRefund!=null)
								LoggerHelper.writeLog(CsRefund.class,"del","删除了[会员退款]["+csRefund.getCsrId()+"]",(Long)$.getSession("ccclubs_login_id"), csRefund);
							on("delete",csRefund,csRefund);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除会员退款成功");
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
	 * 显示会员退款详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csRefund=csRefundService.getCsRefundById(id);
			if(key!=null && id==null)
				csRefund=CsRefund.Get($.add(CsRefund.F.csrId,key));
			//确认域标识
			if(SystemHelper.testHost(csRefund.getCsrHost()).equals("0")){
				return $.SendHtml("<script>alert('对不起，你没有权限查看该数据');</script>", "UTF-8");
			}
			/************LAZY3Q_CODE_DETAIL************/
			/************LAZY3Q_CODE_DETAIL************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据编号或ID查询会员退款
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsRefund.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(params.get("csrMember")!=null && params.get("csrMemberNot")==null){
				params.remove("HOSTS");
			}
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","csr_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csrId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csr_id like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsRefund> list = csRefundService.getCsRefundPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsRefund csRefund:list){
				Map map = new HashMap();
				map.put("value",csRefund.getCsrId().toString());
				map.put("text", $.js(csRefund.getKeyValue()));
				if(bObject==true)
					map.put("object",csRefund);
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
	/***************LAZY3Q_DEFINE_CODE******************/
	/**
	 * 退款审核
	 */
	public String check(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			if($.getString("submiter","").indexOf("审核通过")>-1){
				csRefundService.updateCsRefund$NotNull(
						csRefund.csrStatus((short)1).csrChecker(LoginHelper.getLoginId()).csrCheckTime(new Date())
					);
				$.SetTips("退款审核成功");				
			}
			if($.getString("submiter","").indexOf("继续等待")>-1){
				csRefundService.updateCsRefund$NotNull(
						csRefund.csrChecker(LoginHelper.getLoginId()).csrCheckTime(new Date())
					);
				$.SetTips("操作成功");
			}	
			//更新会员最后退款时间
			csRefund = csRefundService.getCsRefundById(csRefund.getCsrId());
			ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
			csMemberService.updateCsMemberByConfirm(
				$.add(CsMember.F.csmRefundTime, csRefund.getCsrAddTime()),
				$.add(CsMember.F.csmId, csRefund.getCsrMember()).add("confirm", 1)
			);
			//记录操作日志
			LoggerHelper.writeLog(CsRefund.class,"update","退款审核[退款申请]["+csRefund.getCsrId()+"]",(Long)$.getSession("ccclubs_login_id"), csRefund);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	/**
	 * 完成退款
	 */
	public String finish(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			
			//更新会员最后退款时间
			CsRefund dbRefund = csRefundService.getCsRefundById(csRefund.getCsrId());
			if(dbRefund.get$csrMember().getCsmMoney()>0){
				$.SetTips("会员有未完成退款的余额，\"完成退款\"提交失败");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			new CsRefund(csRefund.getCsrId()).csrStatus((short)2).csrMemo(csRefund.getCsrMemo()).update();
			
			//记录操作日志
			LoggerHelper.writeLog(CsRefund.class,"update","退款审核[退款申请]["+csRefund.getCsrId()+"]",(Long)$.getSession("ccclubs_login_id"), csRefund);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	
	/**
	 * 新增退款
	 */
	public String refund(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等	
			final Long id = $.getLong("id");
			final String memo = $.getString("memo");
			final Double money = $.getDouble("money");
			final Short type = $.getShort("type");
			final CsRefund dbRefund = csRefundService.getCsRefundById(id);
			csRefund = dbRefund;
			final CsMember member = dbRefund.get$csrMember();
			if(CsOrder.count($.add("csoPayMember", member.getCsmId()).add("definex", "cso_credit_card is null and (cso_status=0||cso_status=1||cso_status=2||cso_status=5)"))>0){
				$.SetTips("该会员已经有未完成的订单，不能处理该退款");
				return $.Redirect($.xeh($.getString("editorpoint")));
			}
			if(CsAlipayRefund.where().csarMember(csRefund.getCsrMember()).csarStatus(0).get()!=null){
				$.SetTips("该会员已经有未完成的退款在处理，请等待完成后再处理该退款");
				return $.Redirect($.xeh($.getString("editorpoint")));
			}
			//获取会员可用保证金
			Double account = member.getCsmMoney();
			if(account<money){
				$.SetTips("退款金额超过会员余额，无法退款");
				return $.Redirect($.xeh($.getString("editorpoint")));
			}
			//记录操作日志
			LoggerHelper.writeLog(CsRefund.class,"update","退款处理[会员退款]["+csRefund.getCsrId()+"]",(Long)$.getSession("ccclubs_login_id"), csRefund);
			return csRefundService.executeTransaction(new Function(){
				@Override
				public <T> T execute(Object... arg0) {
					if(type<20){//接口退款方式
						String definex = "csar_refund < csar_amount";
						//if(type==0)definex+=" and ((csar_id > 7830 and (csar_pay_method = 'directPay' or csar_pay_method = 'bankPay')) or (csar_pay_method = 'wap01' and csar_id > 20588))";
						com.ccclubs.model.CsAlipayRecord.M recordWhere = CsAlipayRecord.where().csarMember(csRefund.getCsrMember()).csarBankType(type).csarStatus(1).definex(definex);
						Double canret = recordWhere.eval("sum(csar_amount-csar_refund)");
						Double reting = CsAlipayRefund.where().csarMember(csRefund.getCsrMember()).csarBankType(type).csarStatus(0).eval("sum(csar_amount)");
						Double total = $.or(canret,0d)-$.or(reting,0d);//还可退金额
						if(total<money)
							return (T) $.SendHtml("该退款无法以该退款的方式退款，请联系技术员", "UTF-8");
						//可用的充值记录
						List<CsAlipayRecord> records = recordWhere.list(-1);
						//批次号
						final String csarTradeNo = $.date(new Date(), "yyyyMMddHHmmssSSS")+csRefund.getCsrMember();
						Double refundMoney = money;
						for(CsAlipayRecord csAlipayRecord:records){
							if(refundMoney<=0)break;
							Double amount = $(Math.min(refundMoney, csAlipayRecord.getCsarAmount()-csAlipayRecord.getCsarRefund()));
							if(amount<=0)continue;
							CsAlipayRefund csAlipayRefund = new CsAlipayRefund(
									csRefund.get$csrMember().getCsmHost(),
									type,
									csarTradeNo,
									csRefund.getCsrMember(),
									csAlipayRecord.getCsarRealName(),
									amount,
									csRefund.getCsrId(),
									csAlipayRecord.getCsarId(),
									"",
									new Date(),
									new Date(),
									(short)0
							).save();
							refundMoney-=amount;						
						}
						if(type.shortValue()==0){//转入支付宝退款流程
							return (T) $.Redirect($.GetRequest("basePath")+"admin/finance/alipay/alipay.do?trade="+csarTradeNo);
						}else{//转入招商银行退款流程
							if(!new com.ccclubs.service.common.impl.CMBPayService().executeCMBRefund(csarTradeNo)){
								$.SetTips("招行退款提交失败，错误代码001，请联系技术员");
								return (T) $.Redirect($.xeh($.getString("editorpoint")));
							}
						}
						$.SetTips("新增一笔退款成功，请关注实际退款操作、会员余额、消费记录等操作");
					}else{			
						//获取会员可用余额
						ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
						csRefund = csRefundService.getCsRefundById(csRefund.getCsrId());
						csRefundService.updateCsRefund$NotNull(
								csRefund
								.csrBacker(LoginHelper.getLoginId())//退款人
								.csrBackTime(new Date())//退款时间
								.csrAmount($.or(csRefund.getCsrAmount(),0d)+money)//退款额
						);
						
						Map<String,String> typemap = $.add("0", "支付宝退款")
							.add("10", "招商银行退款")
							.add("21", "银行卡转帐")
							.add("22", "邮局汇款")
							.add("23", "现金退款");
						//修改会员余额
						commonMoneyService.updateMoney(
							csRefund.getCsrMember(), 
							MoneyType.Money,
							-Math.abs(money), 
							RecordType.余额退款,
							typemap.get(""+type), 
							null, csRefund.getCsrId(),
							CsRefund.class
						);
						//批次号
						final String csarTradeNo = $.date(new Date(), "yyyyMMddHHmmssSSS")+csRefund.getCsrMember();
						CsAlipayRefund csAlipayRefund = new CsAlipayRefund(
								csRefund.get$csrMember().getCsmHost(),
								type,
								csarTradeNo,
								csRefund.getCsrMember(),
								csRefund.get$csrMember().getCsmName(),
								money,
								csRefund.getCsrId(),
								0l,
								memo,
								new Date(),
								new Date(),
								(short)1
						).save();
						//更新会员最后退款时间
						csRefund = csRefundService.getCsRefundById(csRefund.getCsrId());
						ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
						csMemberService.updateCsMemberByConfirm(
							$.add(CsMember.F.csmRefundTime, csRefund.getCsrAddTime()),
							$.add(CsMember.F.csmId, csRefund.getCsrMember()).add("confirm", 1)
						);
						$.SetTips("新增一笔退款成功，请关注实际退款操作、会员余额、消费记录等操作");
					}
					return (T) $.Redirect($.xeh($.getString("editorpoint")));
				}

						
			});		
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("editorpoint")));
	}
	public Double $(Double value){
		return new java.math.BigDecimal(value).setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/***************LAZY3Q_DEFINE_CODE******************/


	public ICsRefundService getCsRefundService()
	{
		return csRefundService;
	}

	public void setCsRefundService(ICsRefundService csRefundService)
	{
		this.csRefundService = csRefundService;
	}

	public CsRefund getCsRefund()
	{
		return csRefund;
	}

	public void setCsRefund(CsRefund csRefund)
	{
		this.csRefund = csRefund;
	}
}