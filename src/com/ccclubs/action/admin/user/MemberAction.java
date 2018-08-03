package com.ccclubs.action.admin.user;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.node.Export;
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;
import edu.emory.mathcs.backport.java.util.Collections;


/**
 * 会员帐号的Action
 * 
 * @author 飞啊飘啊 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class MemberAction {
    ICsMemberService csMemberService;
    ICsUnitPersonService csUnitPersonService;

    CsMember csMember;
    Lazy3qDaoSupport dao = $.getDao("ccclubs_system");

    /**
     * 会员帐号管理首页
     * 
     * @return
     */
    public String execute() {
        try {
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.")
                    .matcher($.getServletRequest().getServletPath());
            String alias =
                    (String) (matcher.find() ? $.setRequest("alias", matcher.group(1)) : null);// 别名入口

            $.setRequest("modelClass", CsMember.class);// 把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            $.setRequest("entrypoint", $.hex($.getUrlPath() + "?" + $.querys("UTF-8")));// 列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
            // 处理页面的视图数据，比如显示哪些列，自定义显示列等
            ActionHelper.doPageViewFields(CsMember.class);
            // 根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
            Map<String, Object> objects = ActionHelper.getQueryFormParams(CsMember.class);
            $.setRequest("PARAMS", ActionHelper.getQueryFormParams(CsMember.class));

            // 取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
            String strAsc = CsMember.C.get($.forMat($.getString("asc")));// 升序字段
            objects.put("asc", strAsc);// 放入查询条件
            String strDesc = CsMember.C.get($.forMat($.getString("desc")));// 降序字段
            objects.put("desc", strDesc);// 放入查询条件
            if ($.empty(strAsc) && $.empty(strDesc))// 如果未传入排序字段
                objects.put("desc", "csm_id");// 那么，默认以主键降序，传给Dao层

            // 把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
            Set<String> keys = objects.keySet();
            for (String key : keys)
                $.setRequest(key, objects.get(key));

            // 把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
            $.setRequest("objects", objects);
            // 强制限制域
            objects.put("HOSTS", SystemHelper.testHost(null));

            // 反强制限制域
            if (objects.get("csmRefer") != null && objects.get("csmReferNot") == null) {
                objects.remove("HOSTS");
            }

            // canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
            $.setRequest("canQuery", $.getBoolean("canQuery", true));
            if ($.getBoolean("export", false) == true)// 如果传入了一个数据导出的参数
            {
                // 获取当前用户的权限，看当前用户有没有删除权限
                SystemHelper.getSrvLimit("admin/user/member.do");
                if ($.getRequest("canExport") == null
                        || ((Boolean) $.getRequest("canExport")) == false) {
                    throw new RuntimeException("您没有权限导出该数据");
                }
                final Map<String, Object> finalParams = objects;
                // 调用lazy3q.jar中的导出功能
                // 参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
                $.export($.getInteger("size", 1000), 100, $.getInteger("page", 0), new Export() {// new
                                                                                                 // Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
                    @Override // 重载实现Export的getPage方法，Export会分多次调用这个方法
                    public Page getPage(Integer page, int size) {// 这时的page和size是Export转化过后的当前页，每页查询数
                        return csMemberService.getCsMemberPage(page, size, finalParams);
                    }
                }, CsMember.class, $.getArray("keys"));
                return null;// 数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
            }
            /************ LAZY3Q_CODE_EXECUTE ************/
            String strReferer = $.getServletRequest().getHeader("Referer");
            Boolean entryOnce = strReferer == null || strReferer.indexOf("right.do") > -1;
            if (!$.empty(alias) && (alias.startsWith("dyna") || alias.startsWith("public"))) {
                String[] array = $.getString("showKey", "").split(",");
                // 把request传过来的查询参数放回request
                keys = objects.keySet();
                for (String key : keys)
                    $.setRequest(key, objects.get(key));
                if ($.equals(alias, "dyna"))
                    objects.put("csmTracker", LoginHelper.getLoginId());
                $.setRequest("canEdit", false);
                $.setRequest("canDel", false);
                $.setRequest("canAdd", false);
                $.setRequest("canExp", false);
                $.setRequest("handles", "回访会员,标注会员,备忘标记");
                if (!$.empty($.getString("handles")))
                    $.setRequest("handles", $.getString("handles").split(","));
                if (CTRL == null) {
                    LzMap fields = $.add(CsMember.F.csmId, $.add("listable", true))
                            .add(CsMember.F.csmHost, $.add("listable", true).add("queryable", true))
                            .add(CsMember.F.csmMobile,
                                    $.add("listable", true).add("queryable", true))
                            .add(CsMember.F.csmName, $.add("listable", true).add("queryable", true))
                            .add(CsMember.F.csmTag, $.add("listable", true).add("queryable", true))
                            .add(CsMember.F.csmMark, $.add("listable", true).add("queryable", true))
                            .add(CsMember.F.csmVisitFlag,
                                    $.add("listable", true).add("queryable", true))
                            .add(CsMember.F.csmRemark, $.add("listable", true));
                    for (String showKey : array)
                        fields.add(showKey, $.add("listable", true));
                    LzMap map =
                            $.add("listable", false).add("queryable", false).add("fields", fields);
                    if (!$.getBoolean("all", false))
                        $.setRequest("CTRL", $.CTRL(map));// 可配置控制器
                }
                if ($.empty((String) $.getRequest("title")) && $.equals(alias, "dyna"))
                    $.setRequest("title", "我的会员客户");
                $.setRequest("hideQuery", true);
            }
            /************ LAZY3Q_CODE_EXECUTE ************/


            // 调用Service获取分页
            Page page = csMemberService.getCsMemberPage($.getInteger("page", 0),
                    $.getInteger("size", 16), objects);
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
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return "index";
    }

    /**
     * 会员帐号统计
     * 
     * @return
     */
    public String stats() {
        try {
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            $.setRequest("entrypoint", $.hex($.getUrlPath() + "?" + $.querys("UTF-8")));// 列表地址参数状态串,查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
            $.setRequest("modelClass", CsMember.class);// 把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用

            if ($.getBoolean("export", false) == true) {// 如果传入了一个数据导出的参数
                javax.servlet.http.HttpServletResponse response =
                        com.lazy3q.xwork.WebContext.getServletResponse();
                response.setCharacterEncoding("UTF-8");
                java.io.PrintWriter out = response.getWriter();
                response.setContentType("application/x-octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + java.net.URLEncoder.encode("会员帐号统计导出", "UTF-8") + ".xls");
                out.println($.getString("html"));
                out.println("");
                out.flush();
                return null;// 写入给输出流了，直接返回null,下面的逻辑就不执行了，
            }

            // 处理页面的视图数据，比如显示哪些列，自定义显示列等
            ActionHelper.doPageViewFields(CsMember.class);

            // 根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
            Map<String, Object> objects = ActionHelper.getQueryFormParams(CsMember.class);

            // 把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
            Set<String> keys = objects.keySet();
            for (String key : keys)
                $.setRequest(key, objects.get(key));

            // 把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
            $.setRequest("objects", objects);
            // 强制限制域
            objects.put("HOSTS", SystemHelper.testHost(null));

            // 反强制限制域
            if (objects.get("csmRefer") != null && objects.get("csmReferNot") == null) {
                objects.remove("HOSTS");
            }

            Map<String, Object> groups = new HashMap();// 要分组的字段,Dao层会处理，有空去看看
            Map<String, Object> sums = new HashMap();// 要统计的字段,Dao层会处理，有空去看看


            // 获取要分组的字段，放入Map中
            String[] groupNames = $.getArray("groupBy");
            $.setRequest("groupBy", groupNames);
            List<String> timeTypes = new java.util.ArrayList();
            if (groupNames != null) {
                for (String groupName : groupNames) {
                    String timeType = $.getString(groupName + "TimeType");
                    if ($.empty(timeType))
                        groups.put(CsMember.C.get(groupName), "{key}");
                    else {
                        groups.put(CsMember.C.get(groupName),
                                "DATE_FORMAT({key},'" + timeType.split(",")[0] + "')");
                        $.setRequest("haveTimeType", true);
                        timeTypes.add(groupName);
                        if (timeType.startsWith("%Y"))
                            objects.put("asc", CsMember.C.get(groupName));
                    }
                    $.setRequest(groupName + "Group", true);
                }
                $.setRequest("groupCount", groupNames.length);
            } else {
                $.setRequest("groupCount", 0);
            }
            $.setRequest("timeTypes", timeTypes);


            // 获取要统计的字段，放入Map中
            String[] sumNames = $.getArray("sumBy");
            $.setRequest("sumBy", sumNames);
            if (sumNames != null) {
                for (int i = 0; i < sumNames.length; i++) {
                    String sumName = sumNames[i];
                    if (sumName.indexOf(":") == -1) {
                        sums.put(CsMember.C.get(sumName), "sum({key})");
                        $.setRequest(sumName + "Sum", true);
                    } else {// 如果统计字段中带有逗号，那么则认为是自定义统计
                        sums.put(sumName.split(":")[0], sumName.split(":")[1]);
                        $.setRequest(sumName.split(":")[0] + "Sum", true);
                    }
                }
                $.setRequest("sumCount", sumNames.length);
            } else {
                $.setRequest("sumCount", 0);
            }
            /************ LAZY3Q_CODE_STATS ************/
            /************ LAZY3Q_CODE_STATS ************/


            // 调用Service获取统计数据
            List list = csMemberService.getCsMemberStats(objects, groups, sums);
            $.setRequest("list", list);

            // 重置请求参数，主要是把里面的百分号去掉一下
            ActionHelper.resetQueryFormParams(objects);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return "stats";
    }



    /**
     * 会员帐号添加/编辑页面
     * 
     * @return
     */
    public String edit() {
        try {
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            $.setRequest("editorpoint", $.hex($.getUrlPath() + "?" + $.querys("UTF-8")));// 编辑页状态串(包含进入编辑页面时的地址和参数)
            $.setRequest("entrypoint", $.getString("entrypoint"));// 列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
            $.setRequest("ids", $.getString("ids"));// 批量编辑的数据ID，多个ID以,号分隔
            $.setSession("csMemberToken", $.uuid());// 防重复提交Token
            // 当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
            Long id = $.getLong("id");
            if (id != null) {
                // 把当前要修改的数据查出来,注意了，csMember这个对象为什么不用$.setRequest,因为csMember有get,set方法，由struts处理的
                csMember = csMemberService.getCsMemberById(id);
                // 确认域标识
                if (SystemHelper.testHost(csMember.getCsmHost()).equals("0")) {
                    return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
                }
            }
            if (csMember == null)
                csMember = new CsMember();
            else {
                CsUnitPerson unitPerson = CsUnitPerson
                        .getCsUnitPerson($.add(CsUnitPerson.F.csupMember, csMember.getCsmId()));
                if (unitPerson != null) {
                    $.setRequest("unitPerson", unitPerson);
                    List<CsMember> payMembers = unitPerson.get$csupInfo().get$csuiMember();
                    $.setRequest("payMembers", payMembers);
                    CsMemberShip memberShip =
                            CsMemberShip.getCsMemberShip($.add(CsMemberShip.F.csmsTargeter, id));
                    if (memberShip != null) {
                        $.setRequest("payMember", memberShip.getCsmsPayer());
                    }
                }
            }
            // 根据自定义配置ctrl中配置的默认值信息设置默认值
            if (CTRL != null)
                CTRL.setObjectDefaultValue(csMember);
            /************ LAZY3Q_CODE_EDIT ************/
            /************ LAZY3Q_CODE_EDIT ************/
            
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return "edit";
    }


    /**
     * 数据事件触发
     * 
     * @param type 动作类型
     * @param object 操作的对象 如果发生错误，不抛出，尽量避免影响主业务
     * @return
     */
    public void on(String type, CsMember object, CsMember old) {
        try {
            // 执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
            ActionHelper.executeActionScript(CsMember.class, type, object, old);
            /************ LAZY3Q_ACTION_ON ************/
            /************ LAZY3Q_ACTION_ON ************/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    /**
     * 保存会员帐号
     * 
     * @return
     */
    public String save() {
        try {
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            // 主键为空，表示插入,否则表示更新
            final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS"))
                    && csMember.getCsmId() == null);
            // Token技术建议百度下，就是为了防止重复提交
            final String requestCsMemberToken = $.getString("csMemberToken");
            final String sessionCsMemberToken = $.getSession("csMemberToken");
            if ($.equals(requestCsMemberToken, sessionCsMemberToken)) {
                $.removeSession("csMemberToken");// 移除令牌
                // 多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
                csMemberService.executeTransaction(new Function() {
                    public <T> T execute(Object... arg0) {
                        /*************************** LAZY3Q_BEFOR_SAVE ***************************/
                        Map<Long, Boolean> checkedmap = new HashMap();// 保存之前的会员审核状态
                        if (!bIsAdd) {
                            String ids = $.getString("ids");
                            if (ids != null && !ids.trim().equals("")) {// 批量更新
                                String[] array = ids.split(",");
                                for (String sid : array) {
                                    Long id = Long.valueOf(sid);
                                    CsMember member = csMemberService.getCsMemberById(id);
                                    checkedmap.put(member.getCsmId(), member.getCsmVDrive() != null
                                            && member.getCsmVDrive().shortValue() == 1);
                                }
                            } else {
                                Long id = csMember.getCsmId();
                                CsMember member = csMemberService.getCsMemberById(id);
                                checkedmap.put(member.getCsmId(), member.getCsmVDrive() != null
                                        && member.getCsmVDrive().shortValue() == 1);
                            }
                        }
                        /*************************** LAZY3Q_BEFOR_SAVE ***************************/

                        if (bIsAdd) {
                            // 批量插入处理
                            String vValue = ""
                                    + (csMember.getCsmName() == null ? "" : csMember.getCsmName());
                            String sArray[] = {"" + vValue};
                            if (vValue.startsWith("[*]")) {
                                vValue = vValue.replace("[*]", "");
                                sArray = vValue.split("[\\s|\u3000]+");
                            }
                            for (String sItem : sArray) {
                                if (!$.empty(sItem))
                                    csMember.setCsmName(String.valueOf(sItem));

                                // 设置默认值
                                if (csMember.getCsmPassword() == null)
                                    csMember.setCsmPassword($.md5("123456"));
                                if (csMember.getCsmMoney() == null)
                                    csMember.setCsmMoney(0d);
                                if (csMember.getCsmCoupon() == null)
                                    csMember.setCsmCoupon(0d);
                                if (csMember.getCsmIntegral() == null)
                                    csMember.setCsmIntegral(0d);
                                if (csMember.getCsmGrow() == null)
                                    csMember.setCsmGrow(0);
                                if (csMember.getCsmGrade() == null)
                                    csMember.setCsmGrade(0);
                                if (csMember.getCsmRebate() == null)
                                    csMember.setCsmRebate(1.0d);
                                if (csMember.getCsmNotRevenue() == null)
                                    csMember.setCsmNotRevenue((short) 1);
                                if (csMember.getCsmIsVip() == null)
                                    csMember.setCsmIsVip((short) 0);
                                if (csMember.getCsmUpdateTime() == null)
                                    csMember.setCsmUpdateTime(new Date());
                                if (csMember.getCsmAddTime() == null)
                                    csMember.setCsmAddTime(new Date());
                                if (csMember.getCsmAdder() == null)
                                    csMember.setCsmAdder(LoginHelper.getLoginId());
                                if (csMember.getCsmReferType() == null)
                                    csMember.setCsmReferType((short) 0);
                                if (csMember.getCsmFrom() == null)
                                    csMember.setCsmFrom((short) 0);
                                if (csMember.getCsmVMobile() == null)
                                    csMember.setCsmVMobile((short) 0);
                                if (csMember.getCsmVEmail() == null)
                                    csMember.setCsmVEmail((short) 0);
                                if (csMember.getCsmVReal() == null)
                                    csMember.setCsmVReal((short) 0);
                                if (csMember.getCsmVDrive() == null)
                                    csMember.setCsmVDrive((short) 0);
                                if (csMember.getCsmStatus() == null)
                                    csMember.setCsmStatus((short) 1);

                                // 根据自定义的默认值信息设置默认值
                                if (CTRL != null)
                                    CTRL.setObjectDefaultValue(csMember);
                                // 添加域标识
                                if (SystemHelper.testHost(csMember.getCsmHost()).equals("0")) {
                                    throw new RuntimeException("非法指定域值");
                                }

                                csMember = csMemberService.saveCsMember(csMember);

                                on("save", csMember, csMember);
                            }

                            csMemberService.updateCsMember$NotNull(csMember);
                            /************ LAZY3Q_CODE_ADD ************/
                            /************ LAZY3Q_CODE_ADD ************/


                            // 记录操作日志
                            LoggerHelper.writeLog(CsMember.class, "add",
                                    "添加了[会员帐号][" + csMember.getCsmName() + "]",
                                    (Long) $.getSession("ccclubs_login_id"), csMember,
                                    csMember.getCsmId());
                            $.SetTips("保存会员帐号成功");
                        } else {
                            String PARAMS = $.getString("PARAMS");
                            String ids = $.getString("ids");
                            if (!$.empty(PARAMS)) {// 根据查询条件批量更新
                                Map params = $.eval(PARAMS);
                                params.put("confirm", 1);
                                params.put("definex", "1=1");
                                csMemberService.updateCsMemberByConfirm(
                                        ActionHelper.getSetValuesFromModel(csMember), params);
                                LoggerHelper.writeLog(CsMember.class, "update", "批量修改了[会员帐号]",
                                        (Long) $.getSession("ccclubs_login_id"),
                                        LoggerHelper.getUpdateDescription(null, csMember, false),
                                        null);
                            } else if (!$.empty(ids)) {// 根据ids批量更新
                                String[] array = ids.split(",");
                                for (String sid : array) {
                                    csMember.setCsmId(Long.valueOf(sid));
                                    CsMember oldCsMember = CsMember.get(Long.valueOf(sid));
                                    csMemberService.updateCsMember$NotNull(csMember);
                                    LoggerHelper.writeLog(CsMember.class, "update",
                                            "修改了[会员帐号][" + oldCsMember.getCsmName() + "]",
                                            (Long) $.getSession("ccclubs_login_id"),
                                            LoggerHelper.getUpdateDescription(oldCsMember, csMember,
                                                    false),
                                            Long.valueOf(sid));
                                    on($.getString("method"), csMember, oldCsMember);
                                }
                            } else {
                                if ($.empty($.getString("edittype"))) {
                                    // 设置填充值
                                    csMember.setCsmUpdateTime(new Date());

                                    CsMember oldCsMember = CsMember.get(csMember.getCsmId());
                                    csMemberService.updateCsMember$NotNull(csMember);
                                    LoggerHelper.writeLog(CsMember.class, "update",
                                            "修改了[会员帐号][" + oldCsMember.getCsmName() + "]",
                                            (Long) $.getSession("ccclubs_login_id"),
                                            LoggerHelper.getUpdateDescription(oldCsMember, csMember,
                                                    false),
                                            csMember.getCsmId());
                                    on($.getString("method"), csMember, oldCsMember);
                                } else {
                                    CsMember oldCsMember =
                                            csMemberService.getCsMemberById(csMember.getCsmId());
                                    // 设置默认值
                                    csMember.setCsmHost(oldCsMember.getCsmHost());
                                    csMember.setCsmUsername(oldCsMember.getCsmUsername());
                                    csMember.setCsmPassword(oldCsMember.getCsmPassword());
                                    csMember.setCsmMoney(oldCsMember.getCsmMoney());
                                    csMember.setCsmCoupon(oldCsMember.getCsmCoupon());
                                    csMember.setCsmIntegral(oldCsMember.getCsmIntegral());
                                    csMember.setCsmGrow(oldCsMember.getCsmGrow());
                                    csMember.setCsmGrade(oldCsMember.getCsmGrade());
                                    csMember.setCsmMobile(oldCsMember.getCsmMobile());
                                    csMember.setCsmUpdateTime(oldCsMember.getCsmUpdateTime());
                                    csMember.setCsmAddTime(oldCsMember.getCsmAddTime());
                                    csMember.setCsmLastTime(oldCsMember.getCsmLastTime());
                                    csMember.setCsmLastIp(oldCsMember.getCsmLastIp());
                                    csMember.setCsmLoginS(oldCsMember.getCsmLoginS());
                                    csMember.setCsmFirstUse(oldCsMember.getCsmFirstUse());
                                    csMember.setCsmLastUse(oldCsMember.getCsmLastUse());
                                    csMember.setCsmAllRecharge(oldCsMember.getCsmAllRecharge());
                                    csMember.setCsmAllOrderS(oldCsMember.getCsmAllOrderS());
                                    csMember.setCsmAllUseTime(oldCsMember.getCsmAllUseTime());
                                    csMember.setCsmAllViolatS(oldCsMember.getCsmAllViolatS());
                                    csMember.setCsmUnViolatS(oldCsMember.getCsmUnViolatS());
                                    csMember.setCsmAllTroubleS(oldCsMember.getCsmAllTroubleS());
                                    csMember.setCsmFirstRecharge(oldCsMember.getCsmFirstRecharge());
                                    csMember.setCsmRefundTime(oldCsMember.getCsmRefundTime());
                                    csMember.setCsmRegistIp(oldCsMember.getCsmRegistIp());
                                    csMember.setCsmAdder(oldCsMember.getCsmAdder());
                                    csMember.setCsmTracker(oldCsMember.getCsmTracker());
                                    csMember.setCsmRefer(oldCsMember.getCsmRefer());
                                    csMember.setCsmReferType(oldCsMember.getCsmReferType());
                                    csMember.setCsmFrom(oldCsMember.getCsmFrom());
                                    csMember.setCsmSrc(oldCsMember.getCsmSrc());
                                    csMember.setCsmChannel(oldCsMember.getCsmChannel());
                                    csMember.setCsmMarketPlan(oldCsMember.getCsmMarketPlan());
                                    csMember.setCsmActivity(oldCsMember.getCsmActivity());
                                    csMember.setCsmSaler(oldCsMember.getCsmSaler());
                                    csMember.setCsmMask(oldCsMember.getCsmMask());

                                    // 设置填充值
                                    if (csMember.getCsmUpdateTime() == null)
                                        csMember.setCsmUpdateTime(new Date());
                                    // 添加域标识
                                    if (SystemHelper.testHost(csMember.getCsmHost()).equals("0")) {
                                        throw new RuntimeException("非法指定域值");
                                    }

                                    // 从oldCsMember同步csMember未set过的值
                                    csMember.mergeSet(oldCsMember);

                                    csMemberService.updateCsMember(csMember);
                                    LoggerHelper.writeLog(CsMember.class, "update",
                                            "修改了[会员帐号][" + oldCsMember.getCsmName() + "]",
                                            (Long) $.getSession("ccclubs_login_id"),
                                            LoggerHelper.getUpdateDescription(oldCsMember, csMember,
                                                    true),
                                            csMember.getCsmId());
                                    on("update", csMember, oldCsMember);
                                }
                            }
                            /************ LAZY3Q_CODE_UPDATE ************/
                            /************ LAZY3Q_CODE_UPDATE ************/

                            // 记录操作日志
                            $.SetTips("修改会员帐号成功");
                        }
                        /************************** LAZY3Q_CODE_SAVE ****************************/
                        Set<Long> keySet = checkedmap.keySet();
                        for (Long key : keySet) {
                            Boolean checked = checkedmap.get(key);
                            // 如果之前的状态为未认证，则判断当前状态是否认证通过，如通过加积分
                            if (checked == false) {
                                CsMember member = csMemberService.getCsMemberById(key);
                                if (member.getCsmVDrive() != null
                                        && member.getCsmVDrive().shortValue() == 1) {
                                    com.ccclubs.service.common.ICommonMoneyService commonMoneyService =
                                            $.getBean("commonMoneyService");
                                    com.ccclubs.service.admin.ICsRecordService csRecordService =
                                            $.getBean("csRecordService");
                                    if (csRecordService
                                            .getCsRecord($.add("csrType", IntegralType.注册送积分.name())
                                                    .add("csrMember", key)) == null)
                                        commonMoneyService.updateIntegralByRule(key, 1d,
                                                IntegralType.注册送积分, "资料审核通过得积分", null);
                                }
                            }
                        }
                        /************************** LAZY3Q_CODE_SAVE ****************************/


                        return null;
                    }
                });

            } else {
                $.removeSession("csMemberToken");// 移除令牌
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
                        "<script>" + complete + "(" + $.json(csMember) + ")" + "</script>",
                        "UTF-8");
            }

            // 如果点保存，则回到编辑页，如果点确定，则回到列表页
            // 现在知道editorpoint和entrypoint的意义了吗
            // 它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
            if ($.getString("submiter", "").indexOf("保存") > -1) {// 则回到编辑页
                if (bIsAdd)
                    return $.Redirect("member_edit.do?entrypoint=" + $.getString("entrypoint")
                            + "&id=" + csMember.getCsmId());
                else
                    return $.Redirect($.xeh($.getString("editorpoint")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        // 回到列表页
        return $.Redirect($.empty($.getString("entrypoint")) ? $.xeh($.getString("editorpoint"))
                : $.xeh($.getString("entrypoint")));
    }


    /**
     * 删除会员帐号
     * 
     * @return
     */
    public String del() {
        try {
            // 获取当前用户的权限，看当前用户有没有删除权限
            SystemHelper.getSrvLimit("admin/user/member.do");
            /************ LAZY3Q_CAN_DEL ************/
            /************ LAZY3Q_CAN_DEL ************/

            if ($.getRequest("canDel") == null || ((Boolean) $.getRequest("canDel")) == false) {
                throw new RuntimeException("您没有权限删除该数据");
            }
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            // 多个删除数据动作合并事务过程
            csMemberService.executeTransaction(new Function() {
                public <T> T execute(Object... arg0) {

                    Long pid = $.getLong("id");
                    Long[] ids = new Long[] {pid};
                    String strIds = $.getString("ids");
                    if (!$.empty(strIds) && !strIds.trim().equals("")) {// 批量删除
                        String[] array = strIds.split(",");
                        ids = new Long[array.length];
                        for (int i = 0; i < array.length; i++)
                            ids[i] = Long.valueOf(array[i]);
                    }
                    for (Long id : ids) {
                        CsMember csMember = csMemberService.getCsMemberById(id);
                        // 确认域标识
                        if (SystemHelper.testHost(csMember.getCsmHost()).equals("0")) {
                            throw new RuntimeException("当前用户没有权限删除该数据");
                        }
                        if (ConfigHelper.GetBoolean("logic_delete") == true) {
                            csMember.setCsmMobile(
                                    csMember.getCsmMobile$() + "-" + csMember.getCsmId$());
                            csMemberService.updateCsMember(csMember);
                            csMemberService.removeCsMemberById(id);
                        } else
                            csMemberService.deleteCsMemberById(id);
                        // 记录操作日志
                        if (csMember != null)
                            LoggerHelper.writeLog(CsMember.class, "del",
                                    "删除了[会员帐号][" + csMember.getCsmName() + "]",
                                    (Long) $.getSession("ccclubs_login_id"), csMember);
                        on("delete", csMember, csMember);
                    }
                    /************ LAZY3Q_CODE_DEL ************/
                    /************ LAZY3Q_CODE_DEL ************/


                    return null;
                }
            });

            $.SetTips("删除会员帐号成功");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            if (e.getMessage().indexOf("没有权限删除该数据") > -1)
                return $.SendHtml("<script>alert('对不起，你没有权限删除该数据');</script>", "UTF-8");
            $.SetTips("系统繁忙,请稍候再试");
        }
        return $.Redirect($.xeh($.getString("entrypoint")));
    }

    /**
     * 显示会员帐号详情
     * 
     * @return
     */
    public String details() {
        try {
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            Long id = $.getLong("id");
            Long key = $.getLong("key");
            if (id != null)
                csMember = csMemberService.getCsMemberById(id);
            if (key != null && id == null)
                csMember = CsMember.Get($.add(CsMember.F.csmId, key));
            // 确认域标识
            if (SystemHelper.testHost(csMember.getCsmHost()).equals("0")) {
                return $.SendHtml("<script>alert('对不起，你没有权限查看该数据');</script>", "UTF-8");
            }
            /************ LAZY3Q_CODE_DETAIL ************/
            /************ LAZY3Q_CODE_DETAIL ************/
            List<Map> map = dao.executeQuery(
                    "select sum(cso_pay_coupon) as cso_pay_coupon ,sum(cso_pay_coin) as cso_pay_coin from cs_order where cso_status in (3,4) and cso_pay_member = "
                            + csMember.getCsmId());



            String artifReqcordSQL = "SELECT "
                    + " SUM(	IF ( r.csar_money_type = 1 && s.csrs_is_user_out = 1, r.csar_count, 0 ) ) AS backConsuMeCouponCount "
                    + // 后台现金券消费
                    " FROM " + " cs_artif_record r, cs_record_subject s " + " WHERE "
                    + " r.csar_record_subject = s.csrs_id AND " + " r.csar_status = 1 and "
                    + " r.csar_member =  " + csMember.getCsmId() + " ";
            List<Map> artif_record = dao.executeQuery(artifReqcordSQL);

            String recordSql = "SELECT "
                    + " sum(if(r.csr_record_subject = 16,csr_amount,0)) as backConsuMeCouponCount"
                    + " from cs_record r ,cs_record_subject s " + " where "
                    + " r.csr_record_subject = s.csrs_id and " + " csr_member =  "
                    + csMember.getCsmId() + "  ";
            List<Map> record = dao.executeQuery(recordSql);

            Double order_pay_coupon = map.get(0).get("cso_pay_coupon") != null
                    ? ((Double) map.get(0).get("cso_pay_coupon"))
                    : 0;
            Double artif_pay_coupon = artif_record.get(0).get("backConsuMeCouponCount") != null
                    ? ((Double) artif_record.get(0).get("backConsuMeCouponCount"))
                    : 0;
            Double record_pay_coupon = record.get(0).get("backConsuMeCouponCount") != null
                    ? ((Double) record.get(0).get("backConsuMeCouponCount"))
                    : 0;

            $.SetRequest("pay_coin",
                    map.get(0).get("cso_pay_coin") != null
                            ? ((Double) map.get(0).get("cso_pay_coin"))
                            : 0);
            $.SetRequest("pay_coupon", order_pay_coupon + artif_pay_coupon + record_pay_coupon);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return "edit";
    }


    /**
     * 根据真实姓名或ID查询会员帐号 返回ajax数据
     * 
     * @return
     */
    public String query() {
        try {
            // 根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
            Map<String, Object> params = ActionHelper.getQueryFormParams(CsMember.class);
            // 强制限制域
            params.put("HOSTS", SystemHelper.testHost(null));

            // 反强制限制域
            if (params.get("csmRefer") != null && params.get("csmReferNot") == null) {
                params.remove("HOSTS");
            }

            // 取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
            String strAsc = $.getString("asc");// 升序字段
            params.put("asc", strAsc);// 放入查询条件
            String strDesc = $.getString("desc");// 降序字段
            params.put("desc", strDesc);// 放入查询条件
            if ($.empty(strAsc) && $.empty(strDesc))// 如果未传入排序字段
                params.put("desc", "csm_id");// 那么，默认以主键降序，传给Dao层

            String strTerm = $.getString("value");// 智能搜索时的参数，一般都是主键后面那个字段
            if (!$.empty(strTerm)) {
                if (strTerm.toLowerCase().startsWith("id:"))// 如果查询条件以id:开头，则按ID查询
                    params.put("csmId", strTerm.toLowerCase().replaceFirst("id:", ""));
                else// 按标识查询，模糊查询请带%
                {
                    String strDefinex = "";
                    strDefinex += " or csm_mobile like '" + strTerm.replaceAll("'", "''") + "%'";
                    strDefinex += " or csm_name like '" + strTerm.replaceAll("'", "''") + "%'";
                    strDefinex = "(2=1 " + strDefinex + ")";
                    params.put("definex", strDefinex);
                }
            }
            /************ LAZY3Q_CODE_QUERY ************/

            // 查询会员时取消域限制
            params.remove("HOSTS");
            params.remove("csmHost");

            if (!$.empty(strTerm)) {
                if (strTerm.toLowerCase().startsWith("id")) {// 如果查询条件以id:开头，则按ID查询
                    if (strTerm.length() < 6)
                        return $.SendAjax("[]", "UTF-8");
                    params.put("csmId", strTerm.toLowerCase().replaceFirst("id:", ""));
                } else {// 按标识查询，模糊查询请带%
                    String strDefinex = "";
                    if (java.util.regex.Pattern.matches("[\\d]+", strTerm.replace("%", ""))) {
                        if (strTerm.length() < 6)
                            return $.SendAjax("[]", "UTF-8");
                        strDefinex += " csm_mobile like '" + strTerm.replaceAll("'", "''") + "%'";
                    } else {
                        if (strTerm.length() < 2)
                            return $.SendAjax("[]", "UTF-8");
                        strDefinex += " csm_name like '" + strTerm.replaceAll("'", "''") + "%'";
                    }
                    params.put("definex", strDefinex);
                }
            }
            /************ LAZY3Q_CODE_QUERY ************/


            // 是否需要整个数据对象
            Boolean bObject = $.getBoolean("object", false);

            List<CsMember> list = csMemberService
                    .getCsMemberPage(0, $.getInteger("size", 10), params).getResult();

            /**
             * OK!取到数据拼成放入Map中，[{value,text,object:{...}},...] value:数据ID，text:数据标识,object,整个对象
             **/
            List<Map> result = new java.util.ArrayList();
            for (CsMember csMember : list) {
                Map map = new HashMap();
                map.put("value", csMember.getCsmId().toString());
                map.put("text", $.js(csMember.getKeyValue()));
                if (bObject == true)
                    map.put("object", csMember);
                result.add(map);
            }
            /************ LAZY3Q_AFTER_QUERY ************/
            result.clear();
            for (CsMember csMember : list) {
                Map map = new HashMap();
                map.put("value", csMember.getCsmId().toString());
                map.put("text", $.js(csMember.getCsmHost$() + "-" + csMember.getKeyValue()));
                if (bObject == true)
                    map.put("object", csMember);
                result.add(map);
            }
            /************ LAZY3Q_AFTER_QUERY ************/


            // $.SendAjax这个函数，第一个参数不是字符串，会自动把第一个对象转成json格式的字符串
            return $.SendAjax(result, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            return $.SendAjax("[]", "UTF-8");
        }
    }

    /*************** LAZY3Q_DEFINE_CODE ******************/
    /**
     * 自定义会员查询
     */
    public String inquire() {
        return query();
    }

    /**
     * 用户充值扣款对话框
     * 
     * @return
     */
    public String recharger() {
        try {
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            Map params = ActionContext.getContext().getParameters();
            $.SetRequest("editorpoint", $.hex($.getUrlPath() + "?" + $.querys("UTF-8")));
            $.SetRequest("entrypoint", $.getString(params, "entrypoint"));
            $.SetRequest("way", $.getString("way"));
            $.SetRequest("type", $.getShort("type"));
            String ids = $.getString("ids");
            if ($.empty(ids))
                ids = $.getString("id");
            List<CsMember> members = csMemberService
                    .getCsMemberList($.add(CsMember.F.csmHost, LoginHelper.getLogin().getSuHost())
                            .add("definex", "csm_id in (" + ids + ")"), -1);
            $.SetRequest("members", members);
            $.SetRequest("define", "member_recharge.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return "define";
    }

    /**
     * 用户充减积分
     * 
     * @return
     */
    public String recharge() {
        try {
            final Ctrl CTRL = $.setRequest("CTRL", $.CTRL($.getString("ctrl")));// 可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
            String message = csMemberService.executeTransaction(new Function() {
                public <T> T execute(Object... arg0) {
                    String[] ids = $.getArray("ids");
                    String way = $.getString("way");// 充值 扣款
                    if (ids == null || $.empty(way))
                        return (T) "系统错误，请联系管理员";
                    Double amount = $.getDouble("amount");
                    for (String sid : ids) {
                        Long id = Long.parseLong(sid);
                        if (amount == null || amount < 0)
                            return (T) (way + "数量必须是一个比0大的数");
                        if (!way.equals("充积分"))
                            amount = -amount;

                        SYSTEM.IntegralType recordType = way.equals("充积分") ? SYSTEM.IntegralType.加积分
                                : SYSTEM.IntegralType.减积分;

                        ICommonMoneyService commonMoneyService =
                                $.GetSpringBean("commonMoneyService");

                        commonMoneyService.updateIntegral(id, amount, recordType,
                                $.getString("remark"), null);
                    }
                    return (T) (way + "成功");
                }
            });
            $.SetTips(message);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return $.Redirect($.empty($.getString("entrypoint")) ? $.xeh($.getString("editorpoint"))
                : $.xeh($.getString("entrypoint")));
    }

    /**
     * 获取会员的可用余额
     * 
     * @return
     */
    public String account() {
        try {
            Long id = $.getLong("id");
            ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
            CsMember member = CsMember.get(id);
            Double canUseMoney = commonMoneyService.getUsableAmount(id);
            Double canFreeHour = commonMoneyService.getCanUseFreeHour(id, new Date());
            Double canCoin = commonMoneyService.getCoin(member.getCsmHost(), id, new Date());
            return $.SendAjax($.add("money", canUseMoney).add("freehour", canFreeHour)
                    .add("coin", canCoin).add("integral", member.getCsmIntegral()), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
        }
        return $.SendAjax(null, "UTF-8");
    }

    /**
     * 重置会员密码
     * 
     * @return
     */
    public String reset() {
        try {
            Long id = $.getLong("id");
            String password = $.getString("password");
            if (id == null)
                return $.SendAjax($.add("success", false).add("message", "参数错误"), "UTF-8");
            if (!com.ccclubs.helper.SystemHelper.isPassword(password))
                return $.SendAjax($.add("success", false).add("message", "密码格式错误，必须是6到12位字母或数字"),
                        "UTF-8");
            csMemberService.updateCsMemberByConfirm($.add("csmPassword", $.md5(password)),
                    $.add("csmId", id).add("csmHost", LoginHelper.getLogin().getSuHost())
                            .add("confirm", 1));
            CsMember oldCsMember = CsMember.get(id);
            LoggerHelper.writeLog(CsMember.class, "update",
                    "重置会员密码[会员帐号][" + oldCsMember.getCsmMobile() + "]", LoginHelper.getLoginId(),
                    "重置会员密码", oldCsMember.getCsmId());
            return $.SendAjax($.add("success", true).add("message", "重置密码成功"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            return $.SendAjax($.add("success", false).add("message", "系统繁忙，请稍候再试"), "UTF-8");
        }
    }

    /**
     * 快速会员资料审核
     * 
     * @return
     */
    public String check() {
        try {
            return CsMember.execute(new Function() {
                @Override
                public <T> T execute(Object... arg0) {
                    Long id = $.getLong("id");
                    Boolean bPass = $.getBoolean("pass");
                    if (id == null || bPass == null)
                        return (T) $.SendAjax($.add("success", false).add("message", "参数错误"),
                                "UTF-8");
                    CsMember oldCsMember = CsMember.get(id);
                    if (bPass) {
                        csMember = new CsMember().csmId(id).csmVDrive((short) 1)
                                .csmUpdateTime(new Date()).csmVReal((short) 1);
                        csMember.update();
                        com.ccclubs.service.common.ICommonMoneyService commonMoneyService =
                                $.getBean("commonMoneyService");
                        com.ccclubs.service.admin.ICsRecordService csRecordService =
                                $.getBean("csRecordService");
                        if (csRecordService.getCsRecord($.add("csrType", IntegralType.注册送积分.name())
                                .add("csrMember", id)) == null)
                            commonMoneyService.updateIntegralByRule(id, 1d, IntegralType.注册送积分,
                                    "资料审核通过得积分", null);
                    } else {
                        csMember =
                                new CsMember().csmId(id).csmVDrive((short) 3).csmVReal((short) 3);
                        csMember.update();
                    }
                    LoggerHelper.writeLog(CsMember.class, "update",
                            "快速审核会员资料[会员帐号][" + oldCsMember.getCsmMobile() + "]",
                            LoginHelper.getLoginId(),
                            LoggerHelper.getUpdateDescription(oldCsMember, csMember, false),
                            csMember.getCsmId());
                    return (T) $.SendAjax($.add("success", true).add("message", "资料审核成功"), "UTF-8");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            return $.SendAjax($.add("success", false).add("message", "系统繁忙，请稍候再试"), "UTF-8");
        }
    }

    /**
     * 跟踪会员：把该会员标记为我的会员
     * 
     * @return
     */
    public String tracke() {
        try {
            Long id = $.getLong("id");
            CsMember csMember = CsMember.get(id);
            if (csMember == null) {
                $.SetTips("会员不存在");
            } else if (csMember.getCsmTracker() != null) {
                $.SetTips("该会员已有跟踪人员，请稍候再试");
            } else {
                new CsMember().csmId(id).csmTracker(LoginHelper.getLoginId()).update();
                $.SetTips("添加会员到我的会员客户成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return $.Redirect($.empty($.getString("entrypoint")) ? $.xeh($.getString("editorpoint"))
                : $.xeh($.getString("entrypoint")));
    }

    /**
     * 快速审核会员
     * 
     * @return
     */
    public String fastCheck() {
        try {
            if ($.getBoolean("jsp", false) == true) {
                $.setRequest("define", "member_fast.jsp");
                return "define";
            }


            CsMember uncheckMember = CsMember.execute(new Function() {
                @Override
                public <T> T execute(Object... arg0) {
                    Long id = $.getLong("id");
                    if (id != null) {
                        CsMember dbMember = CsMember.get(id);
                        csMember = new CsMember(id);
                        csMember.setCsmVDrive($.getShort("csmVDrive"));
                        csMember.setCsmVReal($.getShort("csmVReal"));
                        csMember.csmUpdateTime(new Date());
                        if (csMember.getVDrive().shortValue() == 1
                                && csMember.getVReal().shortValue() == 1) {
                            com.ccclubs.service.common.ICommonMoneyService commonMoneyService =
                                    $.getBean("commonMoneyService");
                            com.ccclubs.service.admin.ICsRecordService csRecordService =
                                    $.getBean("csRecordService");
                            if (csRecordService
                                    .getCsRecord($.add("csrType", IntegralType.注册送积分.name())
                                            .add("csrMember", csMember.getCsmId())) == null)
                                commonMoneyService.updateIntegralByRule(csMember.getCsmId(), 1d,
                                        IntegralType.注册送积分, "资料审核通过得积分", null);
                        }
                        csMember.update();
                        String message = $.getString("message");
                        if (!$.empty(message)) {
                            com.ccclubs.helper.UtilHelper.sendSms(dbMember.getCsmHost(),
                                    dbMember.getCsmMobile(), message,
                                    com.ccclubs.service.common.ICommonUtilService.SMSType.提示类短信);
                        }
                        CsMember oldCsMember = CsMember.get(id);
                        LoggerHelper.writeLog(CsMember.class, "update",
                                "审核了[会员帐号][" + csMember.getKeyValue() + "]",
                                LoginHelper.getLoginId(),
                                LoggerHelper.getUpdateDescription(oldCsMember, csMember, false)
                                        + " - " + message,
                                csMember.getCsmId());
                        $.setRequest("submit", "ok");
                    }

                    return (T) CsMember.where().add("asc", "rand()")
                            .add("HOSTS", SystemHelper.testHost(null)).csmVDrive((short) 2)
                            .csmVReal((short) 2).get();
                }
            });

            if (uncheckMember == null)
                return $.SendAjax($.add("success", true), $.UTF8);

            Map member = new HashMap();
            member.put("id", uncheckMember.getCsmId());
            member.put("mobile", uncheckMember.getCsmMobile());
            member.put("name", uncheckMember.getCsmName());
            if (uncheckMember.get$csmInfo() != null) {
                member.put("number", uncheckMember.get$csmInfo().getCsmiDriverNum());
                member.put("idcard", uncheckMember.get$csmInfo().getCsmiCertifyImage());
                member.put("driver", uncheckMember.get$csmInfo().getCsmiDriverImage());
                member.put("address", uncheckMember.get$csmInfo().getCsmiAddress());
            }

            return $.SendAjax($.add("success", true).add("member", member).add("submit",
                    $.getRequest("submit")), $.UTF8);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
            return $.SendAjax("{}", "UTF-8");
        }
    }


    /**
     * 表单方式审核会员
     * 
     * @return
     */
    public String verify() {
        try {
            CsMember.execute(new Function() {
                @Override
                public <T> T execute(Object... arg0) {
                    if (csMember.getCsmId() == null)
                        throw new RuntimeException("表单方式审核会员出错，未发现会员ID");
                    String remark = csMember.getCsmRemark();
                    CsMember oldCsMember = CsMember.get(csMember.getCsmId());

                    /**
                     * 会员审核流程：
                     * 1、用户认证通过，赠送积分；2、认证进度提醒短信；
                     * 3、身份证、驾驶证、工作证认证状态变更失败发送短信提示；
                     * 4、工作证认证成功；绑定企业和部门、创建企业用户记录；
                     */
                    csMemberService.verify(oldCsMember, csMember);
                    
                    //会员支付账号变更
                    String payMember = $.getString("payMember");
                    if (!$.empty(payMember)) {
                        CsMemberShip.where().csmsTargeter(csMember.getCsmId()).set()
                                .csmsPayer(payMember).update();
                    }
                    //会员企业部门信息变更
                    Long unitInfo = $.getLong("unitInfo");
                    Long unitGroup = $.getLong("unitGroup");
                    if (unitInfo != null && unitGroup != null) {
                        CsUnitPerson.where().csupMember(csMember.getCsmId()).set()
                                .csupInfo(unitInfo).csupGroup(unitGroup).update();
                    }
                    
                    //日志输出
                    LoggerHelper.writeLog(CsMember.class, "update",
                            "审核了[会员帐号][" + oldCsMember.getCsmMobile() + "]"
                                    + ($.empty(payMember) ? "" : "修改支付会员"),
                            LoginHelper.getLoginId(),
                            LoggerHelper.getUpdateDescription(oldCsMember, csMember, false) + " - "
                                    + remark,
                            csMember.getCsmId());
                    $.SetTips("审核表单提交成功！");
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return $.Redirect($.empty($.getString("entrypoint")) ? $.xeh($.getString("editorpoint"))
                : $.xeh($.getString("entrypoint")));
    }

    /**
     * 批量充值 //会员ID|订单ID|积分充值数|充值描述
     * 
     * @return
     */
    public String batch() {
        try {
            String message = csMemberService.executeTransaction(new Function() {
                public <T> T execute(Object... arg0) {
                    Workbook book = null;
                    try {
                        book = Workbook.getWorkbook(batch);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return (T) "文件类型或格式错误";
                    }
                    ICsOrderService csOrderService = $.getBean("csOrderService");
                    ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
                    Sheet sheet = book.getSheet(0);
                    for (int i = 1; i < sheet.getRows(); i++) {
                        Long memberId = getLong(sheet.getCell(0, i).getContents());
                        Long orderId = getLong(sheet.getCell(1, i).getContents());
                        if (memberId == null && orderId != null) {
                            CsOrder csOrder = csOrderService.getCsOrder($.add("csoId", orderId));
                            if (csOrder != null)
                                memberId = csOrder.getCsoUseMember();
                        }
                        if (memberId == null)
                            continue;
                        Double dIntegral = getDouble(sheet.getCell(2, i).getContents());
                        String sRemark = sheet.getCell(3, i).getContents();

                        if (dIntegral != null)
                            commonMoneyService.updateIntegral(memberId, dIntegral,
                                    dIntegral > 0 ? IntegralType.加积分 : IntegralType.减积分, sRemark,
                                    orderId);
                    }

                    book.close();
                    return (T) "批量充值成功";
                }
            });
            $.SetTips(message);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            $.SetTips("系统繁忙,请稍候再试");
        }
        return $.Redirect("member.do");
    }

    public Double getDouble(String sDouble) {
        if ($.trim(sDouble).equals(""))
            return null;
        Double d = null;
        try {
            d = Double.parseDouble(sDouble);
        } catch (Exception ex) {
            return null;
        }
        return d;
    }

    public Long getLong(String sLong) {
        if ($.trim(sLong).equals(""))
            return null;
        Long l = null;
        try {
            l = Long.parseLong(sLong);
        } catch (Exception ex) {
            return null;
        }
        return l;
    }

    File batch;
    String batchFileName;

    public File getBatch() {
        return batch;
    }

    public void setBatch(File batch) {
        this.batch = batch;
    }

    public String getBatchFileName() {
        return batchFileName;
    }

    public void setBatchFileName(String batchFileName) {
        this.batchFileName = batchFileName;
    }

    /*************** LAZY3Q_DEFINE_CODE ******************/


    public ICsMemberService getCsMemberService() {
        return csMemberService;
    }

    public void setCsMemberService(ICsMemberService csMemberService) {
        this.csMemberService = csMemberService;
    }

    public CsMember getCsMember() {
        return csMember;
    }

    public void setCsMember(CsMember csMember) {
        this.csMember = csMember;
    }
}
