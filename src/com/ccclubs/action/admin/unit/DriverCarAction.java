package com.ccclubs.action.admin.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsDriver;
import com.ccclubs.model.CsDriverCar;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.service.admin.ICsDriverCarService;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.util.DateUtil;
import com.ctc.wstx.util.DataUtil;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.node.Export;
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.util.Page;

/**
 * 专车司机Action
 * @author ccclubs
 *
 */
@SuppressWarnings({"unused","unchecked","rawtypes"})
public class DriverCarAction {
	ICsDriverCarService csDriverCarService;
	CsDriverCar csDriverCar;

	public String execute() {
		// TODO Auto-generated method stub

		try {
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(
					"\\.([\\S\\s]+)\\.").matcher(
					$.getServletRequest().getServletPath());
			String alias = (String) (matcher.find() ? $.setRequest("alias",
					matcher.group(1)) : null);// 别名入口

			$.setRequest("modelClass", CsDriverCar.class);// 把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",
					$.hex($.getUrlPath() + "?" + $.querys("UTF-8")));// 列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			// 处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsDriverCar.class);
			// 根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String, Object> objects = ActionHelper
					.getQueryFormParams(CsDriverCar.class);
			$.setRequest("PARAMS",
					ActionHelper.getQueryFormParams(CsDriverCar.class));

			// 取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc = CsDriverCar.C.get($.forMat($.getString("asc")));// 升序字段
			objects.put("asc", strAsc);// 放入查询条件
			String strDesc = CsDriverCar.C.get($.forMat($.getString("desc")));// 降序字段
			objects.put("desc", strDesc);// 放入查询条件
			if ($.empty(strAsc) && $.empty(strDesc))// 如果未传入排序字段
				objects.put("desc", "csdc_id");// 那么，默认以主键降序，传给Dao层

			// 把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for (String key : keys)
				$.setRequest(key, objects.get(key));

			// 把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects", objects);
			// 强制限制域
			objects.put("HOSTS", SystemHelper.testHost(null));

			// 反强制限制域
			if (objects.get("$csdHost") != null
					&& objects.get("$csdHost") == null) {
				objects.remove("HOSTS");
			}
			StringBuffer buffer = new StringBuffer();
			buffer.append("(1=1 ");
			// 手机号模糊查询
			if ($.getRequest("csdMobile")  != null && !$.getRequest("csdMobile").equals("") ) {
				buffer.append("and  csdc_driver in  (  select csd_id from cs_driver where csd_mobile "
						+ ($.getRequest("csdMobileYesNot") == null || $.getRequest("csdMobileYesNot").equals("") ? "" : "not")
						+ "  like '%"
						+ $.getRequest("csdMobile").toString()
								.replaceAll("'", "''") + "%' )");
			}
			// 司机姓名模糊查询
			if ($.getRequest("csdName") != null && !$.getRequest("csdName").equals("")) {
				buffer.append("and  csdc_driver in  (  select csd_id from cs_driver where csd_name "
						+ ($.getRequest("csdNameYesNot") == null||$.getRequest("csdNameYesNot").equals("")  ? "" : "not")
						+ " like '%"
						+ $.getRequest("csdName").toString()
								.replaceAll("'", "''") + "%' )");
			}
		
			// 驾驶证号模糊查询
			if ($.getRequest("cscrCarNo") != null && !$.getRequest("cscrCarNo").equals("")) {
				buffer.append("and  csdc_car in  (  select cscr_id from cs_car_res where cscr_car_no "
						+ ($.getRequest("cscrCarNoYesNot") == null||$.getRequest("cscrCarNoYesNot").equals("")  ? ""
								: "not")
						+ " like '%"
						+ $.getRequest("cscrCarNo").toString()
								.replaceAll("'", "''") + "%' )");
			}
			
			if($.getRequest("csdc_driver_query")!= null && $.getRequest("csdc_driver_query").equals("true")){
				//buffer.append(" and csdc_driver not in  (SELECT csdc_driver from cs_driver_car  where csdc_state = 1 ) ");
				
				String csscId = $.getString("csscId","");
				if($.empty(csscId)){
					$.SetTips("获取订单失败");
					$.SetRequest("define", "drivercar_select.jsp");
					return "define";
				}
				
				String finishTime = $.getString("csscFinishTime","");
				
				if($.empty(finishTime)){
					$.SetTips("派单结束时间错误");
					$.SetRequest("define", "drivercar_select.jsp");
					return "define";
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("csscId", csscId);
				if(!$.getString("appoint").equals("change"))
					map.put("definex", "(cssc_status <3)");
				
				CsSpecialCar csSpecialCar = csSpecialCarService.getCsSpecialCar(map);
				if(csSpecialCar==null){
					$.SetTips("订单已被派出");
					$.SetRequest("define", "drivercar_select.jsp");
					return "define";
				}
				DateUtil dataUtil = new DateUtil();
				
				if(!dataUtil.StringtoDate(finishTime, null).after(csSpecialCar.getCsscStartTime())){
					$.SetTips("订单结束时间小于订单开始时间");
					$.SetRequest("define", "drivercar_select.jsp");
					return "define";
				}

				String definex = "(select  "+CsDriverCar.C.csdcDriver+" from cs_driver_car cdc LEFT JOIN cs_special_car cssc on cdc.csdc_id = cssc.cssc_drivercar " +
						"where cssc.cssc_status in (3,8,9) and ( ('"+csSpecialCar.getCsscStartTime$()+"'<=cssc.cssc_finish_time and cssc.cssc_start_time <= '"+csSpecialCar.getCsscStartTime$()+"') " +//当前订单在 其他订单时间之间
										"or ('"+finishTime+"'>=cssc.cssc_start_time and cssc.cssc_start_time >= '"+csSpecialCar.getCsscStartTime$()+"') " +//其他订单时间 在 当前订单时间之间
														")" +
														")";
				buffer.append(" and csdc_driver not in  "+definex+" and csdc_state != 2 and csdc_status =1");

			}
			
			objects.put("definex", buffer.append(")").toString());

			// canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery", true));
			if ($.getBoolean("export", false) == true)// 如果传入了一个数据导出的参数
			{
				// 获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/unit/drivercar.do");
				if ($.getRequest("canExport") == null
						|| ((Boolean) $.getRequest("canExport")) == false) {
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				// 调用lazy3q.jar中的导出功能
				// 参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size", 1000), 100,
						$.getInteger("page", 0), new Export() {// new
							// Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
							@Override
							// 重载实现Export的getPage方法，Export会分多次调用这个方法
							public Page getPage(Integer page, int size) {// 这时的page和size是Export转化过后的当前页，每页查询数
								return csDriverCarService.getCsDriverCarPage(page,
										size, finalParams);
							}
						}, CsDriverCar.class, $.getArray("keys"));
				return null;// 数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************ LAZY3Q_CODE_EXECUTE ************/
			/************ LAZY3Q_CODE_EXECUTE ************/

			// 调用Service获取分页
			Page page = csDriverCarService.getCsDriverCarPage(
					$.getInteger("page", 0), $.getInteger("size", 16), objects);
			// 把分页对象放入request
			$.setRequest("page", page);

			// 重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
			/************ LAZY3Q_AFTER_EXECUTE ************/
			/************ LAZY3Q_AFTER_EXECUTE ************/

			// 把当前查询到的条数放入request
			objects.put("count", page.getCount());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";

	}

	
	/**
	 * 司机人员添加/编辑
	 * 
	 * @return
	 */
	public String edit() {

		try {
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",
					$.hex($.getUrlPath() + "?" + $.querys("UTF-8")));// 编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));// 列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids", $.getString("ids"));// 批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csDriverCarToken", $.uuid());// 防重复提交Token
			// 当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id = $.getLong("id");
			if (id != null) {
				// 把当前要修改的数据查出来,注意了，CsDriver这个对象为什么不用$.setRequest,因为CsDriver有get,set方法，由struts处理的
				csDriverCar = csDriverCarService.getCsDriverCarById(id);
				// 确认域标识
				if (SystemHelper.testHost(csDriverCar.getCsdcHost()).equals("0")) {
					return $.SendHtml(
							"<script>alert('对不起，你没有权限编辑该数据');</script>",
							"UTF-8");
				}
			}
			if (csDriverCar == null)
				csDriverCar = new CsDriverCar();
			// 根据自定义配置ctrl中配置的默认值信息设置默认值
			if (CTRL != null)
				CTRL.setObjectDefaultValue(csDriverCar);
			/************ LAZY3Q_CODE_EDIT ************/
			/************ LAZY3Q_CODE_EDIT ************/

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";

	}
	
	public String save() {

		try {
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			// 主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids"))
					&& $.empty($.getString("PARAMS")) && csDriverCar.getCsdcId() == null);
			// Token技术建议百度下，就是为了防止重复提交
			final String requestCsDriverCarToken = $.getString("csDriverToken");
			final String sessionCsDriverCarToken = $.getSession("csDriverToken");
			if ($.equals(requestCsDriverCarToken, sessionCsDriverCarToken)) {
				$.removeSession("csDriverCarToken");// 移除令牌
				// 多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csDriverCarService.executeTransaction(new Function() {
					public <T> T execute(Object... arg0) {
						/************ LAZY3Q_BEFOR_SAVE ************/
						/************ LAZY3Q_BEFOR_SAVE ************/
						if (bIsAdd) {
							
							// 设置默认值
							if (csDriverCar.getCsdcUpdateTime() == null)
								csDriverCar.setCsdcUpdateTime(new Date());
							if (csDriverCar.getCsdcAddTime() == null)
								csDriverCar.setCsdcAddTime(new Date());
							// 根据自定义的默认值信息设置默认值
							if (CTRL != null)
								CTRL.setObjectDefaultValue(csDriverCar);
							// 添加域标识
							if (SystemHelper
									.testHost(csDriverCar.getCsdcHost())
									.equals("0")) {
								throw new RuntimeException("非法指定域值");
							}

							csDriverCar = csDriverCarService
									.saveCsDriverCar(csDriverCar);

							on("save", csDriverCar, csDriverCar);
						

							csDriverCarService.updateCsDriverCar$NotNull(csDriverCar);
							/************ LAZY3Q_CODE_ADD ************/
							/************ LAZY3Q_CODE_ADD ************/

							// 记录操作日志
							LoggerHelper.writeLog(CsDriver.class, "add",
									"添加了[司机专车人员][" + csDriverCar.getCsdcDriver()
											+ "]",
									(Long) $.getSession("ccclubs_login_id"),
									csDriverCar, csDriverCar.getCsdcId());
							$.SetTips("保存司机人员成功");
						} else {
							String PARAMS = $.getString("PARAMS");
							String ids = $.getString("ids");
							if (!$.empty(PARAMS)) {// 根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex", "1=1");
								csDriverCarService.updateCsDriverCarByConfirm(
										ActionHelper
												.getSetValuesFromModel(csDriverCar),
										params);
								LoggerHelper.writeLog(
										CsDriver.class,
										"update",
										"批量修改了[用车人员]",
										(Long) $.getSession("ccclubs_login_id"),
										LoggerHelper.getUpdateDescription(null,
												csDriverCar, false), null);
							} else if (!$.empty(ids)) {// 根据ids批量更新
								String[] array = ids.split(",");
								for (String sid : array) {
									csDriverCar.setCsdcId(Long.valueOf(sid));
									CsDriverCar oldCsDriverCar = CsDriverCar.get(Long
											.valueOf(sid));
									csDriverCarService
											.updateCsDriverCar$NotNull(csDriverCar);
									LoggerHelper.writeLog(
											CsDriver.class,
											"update",
											"修改了[司机人员]["
													+ oldCsDriverCar.getCsdcId()
													+ "]",
											(Long) $.getSession("ccclubs_login_id"),
											LoggerHelper.getUpdateDescription(
													oldCsDriverCar, csDriverCar,
													false), Long.valueOf(sid));
									on($.getString("method"), csDriverCar,
											oldCsDriverCar);
								}
							} else {
								if ($.empty($.getString("edittype"))) {
									// 设置填充值
									csDriverCar.setCsdcUpdateTime(new Date());

									CsDriverCar oldCsDriverCar = CsDriverCar
											.get(csDriverCar.getCsdcId());
									csDriverCarService
											.updateCsDriverCar$NotNull(csDriverCar);
									LoggerHelper.writeLog(
											CsDriver.class,
											"update",
											"修改了[司机人员]["
													+ oldCsDriverCar.getCsdcId()
													+ "]",
											(Long) $.getSession("ccclubs_login_id"),
											LoggerHelper.getUpdateDescription(
													oldCsDriverCar, csDriverCar,
													false), csDriverCar.getCsdcId());
									on($.getString("method"), csDriverCar,
											oldCsDriverCar);
								} else {
									CsDriverCar oldCsDriver = csDriverCarService
											.getCsDriverCarById(csDriverCar
													.getCsdcId());
									// 设置默认值

									csDriverCar.setCsdcUpdateTime(new Date());
									csDriverCar.setCsdcAddTime(oldCsDriver
											.getCsdcAddTime());

									// 添加域标识
									if (SystemHelper.testHost(
											csDriverCar.getCsdcHost()).equals("0")) {
										throw new RuntimeException("非法指定域值");
									}

									// 从oldCsDriver同步csDriver未set过的值
									csDriverCar.mergeSet(oldCsDriver);

									csDriverCarService.updateCsDriverCar(csDriverCar);
									LoggerHelper.writeLog(
											CsDriver.class,
											"update",
											"修改了[用车人员]["
													+ oldCsDriver.getCsdcId()
													+ "]",
											(Long) $.getSession("ccclubs_login_id"),
											LoggerHelper
													.getUpdateDescription(
															oldCsDriver,
															csDriverCar, true),
											csDriverCar.getCsdcId());
									on("update", csDriverCar, oldCsDriver);
								}
							}
							/************ LAZY3Q_CODE_UPDATE ************/
							/************ LAZY3Q_CODE_UPDATE ************/

							// 记录操作日志
							$.SetTips("修改用车人员成功");
						}
						/************ LAZY3Q_CODE_SAVE ************/
						/************ LAZY3Q_CODE_SAVE ************/

						return null;
					}
				});

			} else {
				$.removeSession("csDriverToken");// 移除令牌
				$.SetTips("对不起，请勿重复提交");
			}
			/************ LAZY3Q_CODE_RETURN ************/
			/************ LAZY3Q_CODE_RETURN ************/

			// 从ctrl中获取自定义转跳地址,是不是发现ctrl这个东东很神奇？
			String redirectUrl = CTRL == null ? null : CTRL.getRedirect();
			if (!$.empty(redirectUrl))
				return $.Redirect(redirectUrl);// 如果有转跳地址，下面的逻辑就不执行了

			// complete是什么呢，jsonp回调函数，jsonp技术建议百度一下
			String complete = $.getString("complete");
			if (!$.empty(complete)) {
				$.removeSession("tips");
				return $.SendHtml(
						"<script>" + complete + "(" + $.json(csDriverCar) + ")"
								+ "</script>", "UTF-8");
			}

			// 如果点保存，则回到编辑页，如果点确定，则回到列表页
			// 现在知道editorpoint和entrypoint的意义了吗
			// 它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if ($.getString("submiter", "").indexOf("保存") > -1) {// 则回到编辑页
				if (bIsAdd)
					return $.Redirect("drivercar_edit.do?entrypoint="
							+ $.getString("entrypoint") + "&id="
							+ csDriverCar.getCsdcId());
				else
					return $.Redirect($.xeh($.getString("editorpoint")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		// 回到列表页
		return $.Redirect($.empty($.getString("entrypoint")) ? $.xeh($
				.getString("editorpoint")) : $.xeh($.getString("entrypoint")));

	}

	/**
	 * 数据事件触发
	 * 
	 * @param type
	 *            动作类型
	 * @param object
	 *            操作的对象 如果发生错误，不抛出，尽量避免影响主业务
	 * @return
	 */
	public void on(String type, CsDriverCar object, CsDriverCar old) {
		try {
			// 执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsDriverCar.class, type, object, old);
			/************ LAZY3Q_ACTION_ON ************/
			/************ LAZY3Q_ACTION_ON ************/

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 司机信息详情
	 * @return
	 */
	public String details() {

		try {
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id = $.getLong("id");
			Long key = $.getLong("key");
			if (id != null)
				csDriverCar = csDriverCarService.getCsDriverCarById(id);
			if (key != null && id == null)
				csDriverCar = CsDriverCar.Get($.add(CsDriver.F.csdId,
						key));
			// 确认域标识
			if (SystemHelper.testHost(csDriverCar.getCsdcHost()).equals("0")) {
				return $.SendHtml("<script>alert('对不起，你没有权限查看该数据');</script>",
						"UTF-8");
			}
			/************ LAZY3Q_CODE_DETAIL ************/
			/************ LAZY3Q_CODE_DETAIL ************/

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";

	}
	
	/**
	 * 删除用车人员
	 * 
	 * @return
	 */
	public String del() {
		try {
			// 获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/unit/drivercar.do");
			/************ LAZY3Q_CAN_DEL ************/
			/************ LAZY3Q_CAN_DEL ************/

			if ($.getRequest("canDel") == null
					|| ((Boolean) $.getRequest("canDel")) == false) {
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			// 多个删除数据动作合并事务过程
			csDriverCarService.executeTransaction(new Function() {
				public <T> T execute(Object... arg0) {

					Long pid = $.getLong("id");
					Long[] ids = new Long[] { pid };
					String strIds = $.getString("ids");
					if (!$.empty(strIds) && !strIds.trim().equals("")) {// 批量删除
						String[] array = strIds.split(",");
						ids = new Long[array.length];
						for (int i = 0; i < array.length; i++)
							ids[i] = Long.valueOf(array[i]);
					}
					for (Long id : ids) {
						CsDriverCar csDriverCar = csDriverCarService.getCsDriverCarById(id);
						// 确认域标识
						if (SystemHelper.testHost(csDriverCar.getCsdcHost())
								.equals("0")) {
							throw new RuntimeException("当前用户没有权限删除该数据");
						}
						if (ConfigHelper.GetBoolean("logic_delete") == true)
							csDriverCarService.removeCsDriverCarById(id);
						else
							csDriverCarService.deleteCsDriverCarById(id);
						// 记录操作日志
						if (csDriverCar != null)
							LoggerHelper.writeLog(CsDriverCar.class, "del",
									"删除了[用车人员][" + csDriverCar.getCsdcId() + "]",
									(Long) $.getSession("ccclubs_login_id"),
									csDriverCar);
						on("delete", csDriverCar,csDriverCar);
					}
					/************ LAZY3Q_CODE_DEL ************/
					/************ LAZY3Q_CODE_DEL ************/

					return null;
				}
			});

			$.SetTips("删除专车司机成功");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			if (e.getMessage().indexOf("没有权限删除该数据") > -1)
				return $.SendHtml("<script>alert('对不起，你没有权限删除该数据');</script>",
						"UTF-8");
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}

	
	ICsSpecialCarService csSpecialCarService;
	
	/***************LAZY3Q_DEFINE_CODE******************/
	/**
	 * 短信模版管理首页
	 * @return
	 */	
	public String select()
	{	
		
		execute();
		$.SetRequest("define", "drivercar_select.jsp");
		return "define";
	}	
	/***************LAZY3Q_DEFINE_CODE******************/
	
	
	public ICsDriverCarService getCsDriverCarService() {
		return csDriverCarService;
	}

	public void setCsDriverCarService(ICsDriverCarService csDriverCarService) {
		this.csDriverCarService = csDriverCarService;
	}

	public CsDriverCar getCsDriverCar() {
		return csDriverCar;
	}

	public void setCsDriverCar(CsDriverCar csDriverCar) {
		this.csDriverCar = csDriverCar;
	}


	public ICsSpecialCarService getCsSpecialCarService() {
		return csSpecialCarService;
	}


	public void setCsSpecialCarService(ICsSpecialCarService csSpecialCarService) {
		this.csSpecialCarService = csSpecialCarService;
	}

}
