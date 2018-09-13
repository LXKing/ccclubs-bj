

package com.ccclubs.action.app.official;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.ccclubs.action.api.scripts.CarScript;
import com.ccclubs.action.api.scripts.TimelineScript;
import com.ccclubs.action.app.official.dto.AppTip;
import com.ccclubs.action.app.official.meal.MealExpress;
import com.ccclubs.action.app.official.meal.MealHelper;
import com.ccclubs.action.app.official.model.JsonFormat;
import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.action.app.official.util.CouponBuyUtil;
import com.ccclubs.action.app.official.util.DidiApi;
import com.ccclubs.action.app.official.util.Env;
import com.ccclubs.action.app.official.util.EventBarrier;
import com.ccclubs.action.app.official.util.EventLock;
import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.action.app.official.util.LocateConverter;
import com.ccclubs.action.app.official.util.OauthUtils;
import com.ccclubs.action.app.official.util.Reason;
import com.ccclubs.action.app.official.util.SessionMgr;
import com.ccclubs.action.app.official.util.YidaoApi;
import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.action.weixin.WeixinHelper;
import com.ccclubs.config.ArgumentKey;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.constants.MemberRecStatus;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.helper.CacheStoreHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.helper.SystemHelper.Prices;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WidgetHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsArgument;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsComplain;
import com.ccclubs.model.CsContent;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.model.CsDriver;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsFavoriteAddress;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsMemberSummary;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.CsRefund;
import com.ccclubs.model.CsRule;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsState;
import com.ccclubs.model.CsUnderlineMember;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitMt;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.CsViolat;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsArgumentService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsComplainService;
import com.ccclubs.service.admin.ICsContentService;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.ccclubs.service.admin.ICsDriverService;
import com.ccclubs.service.admin.ICsEvCardService;
import com.ccclubs.service.admin.ICsFavoriteAddressService;
import com.ccclubs.service.admin.ICsGoodsService;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsMemberInfoService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.ccclubs.service.admin.ICsMemberSummaryService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.ccclubs.service.admin.ICsPriceService;
import com.ccclubs.service.admin.ICsProductService;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.service.admin.ICsUnderlineMemberService;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.ccclubs.service.admin.ICsUnitMtService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.ccclubs.service.admin.ICsUpdateService;
import com.ccclubs.service.admin.ICsUseRecordService;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICMBPayService;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonPayService;
import com.ccclubs.service.common.ICommonUnitService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.service.common.impl.CommonDisposeService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.service.common.impl.SchedulingService;
import com.ccclubs.service.common.impl.UnitOrderInfo;
import com.ccclubs.util.CoordtransformUtil;
import com.ccclubs.util.DateUtil;
import com.ccclubs.util.UUIDGenerator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;

import net.sf.json.JSONObject;

public class DefaultAction extends BaseAction {

    ISrvPropertyService srvPropertyService;
    ICsMemberService csMemberService;
    ICsOutletsService csOutletsService;
    ICsOrderService csOrderService;
    ICsCarService csCarService;
    ICsGoodsService csGoodsService;
    ICsPriceService csPriceService;
    ICsProductService csProductService;
    ICsUserTypeService csUserTypeService;
    ICommonMoneyService commonMoneyService;
    ICommonDisposeService commonDisposeService;
    ICommonOrderService commonOrderService;
    ICsUpdateService csUpdateService;
    ICsMemberInfoService csMemberInfoService;
    ICsUnitOrderService csUnitOrderService;
    ICsMemberShipService csMemberShipService;
    ICsPowerPileService csPowerPileService;
    ICsEvCardService csEvCardService;
    ICsUnitGroupService csUnitGroupService;
    ICsRefundService csRefundService;
    ICommonUnitService commonUnitService;
    ICsUnitMtService csUnitMtService;
    ICsStateService csStateService;
    ICsFavoriteAddressService csFavoriteAddressService;
    ICsRecordService csRecordService;
    ICsItemService csItemService;
    ICsAlipayRecordService csAlipayRecordService;
    ICommonPayService commonPayService;
    ICsRechargeCardService csRechargeCardService;
    ICsContentService csContentService;
    ICMBPayService cmbPayService;
    ICsArgumentService csArgumentService;
    ICsComplainService csComplainService;
    ICsMemberSummaryService csMemberSummaryService;
    ICsCoinService csCoinService;
    ICsUseRecordService csUseRecordService;
    ICsUnitPersonService csUnitPersonService;
    ICsCreditCardService csCreditCardService;
    ICsUnderlineMemberService csUnderlineMemberService;
    final static String CHARSET = "utf-8";
    final static String FORGET_SMS_CODE = "forgetcode"; // 忘记密码的校验码
    final static String FORGET_TOKEN = "forget_token"; // 忘记密码校验通过的状态token

    final static String RESET_SMS_CODE = "resetcode";// 重置密码的校验码
    final static String RESET_TOKEN = "reset_token";// 重置密码通过的token

    final static String REGISTER_SMS_CODE = "registercode";// 注册账号的校验码
    final static String REGISTER_TOKEN = "register_token";// 注册账号通过的token

    final static String NORMAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    final static String LOGIN_CODE = "MEMBER_LOGIN_CODE";

    Logger rentAppLogger = Logger.getLogger("rentApp");

    private String returnError(String code, String message) {
        $.SendHtml($.json(JsonFormat.error(code, message)), CHARSET);
        return null;
    }

    private String returnError(Exception ex) {
        if (ex instanceof MessageException) {
            MessageException e = (MessageException) ex;
            return returnError(e.getCode() + "", e.getMessage());
        }
        if (ex instanceof com.ccclubs.exception.ErrorException) {
            com.ccclubs.exception.ErrorException e = (com.ccclubs.exception.ErrorException) ex;
            return returnError("0", e.getMessage());
        }

        ex.printStackTrace();
        Logger.getRootLogger().error(ex.getMessage(), ex);
        return returnError("9999", SYSTEM.ERROR_TIPS);
    }


    /**
     * 线下认证
     * 
     * @return
     */
    public String getUnderlineMember() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            //
            Map<String, Object> params = new HashMap<>();
            params.put("asc", "cum_area");
            Page<CsUnderlineMember> page = csUnderlineMemberService
                    .getCsUnderlineMemberPage($.getInteger("page", 0), 20, params);
            //
            Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();

            List<Object> dataList = new ArrayList<>();
            Map<String, Object> tempMap = null;
            List<Map<String, Object>> tempList = null;
            for (CsUnderlineMember data : page.getResult()) {
                String area = data.getCumArea$();
                //
                tempMap = new HashMap<>();
                tempMap.put("user", data.getCumUser());// 对接人
                tempMap.put("mobile", data.getCumMobile());
                // tempMap.put("area", data.getCumArea$());
                //
                if (dataMap.containsKey(area)) {
                    dataMap.get(area).add(tempMap);
                } else {
                    tempList = new ArrayList<>();
                    tempList.add(tempMap);
                    dataMap.put(area, tempList);
                }
            }
            // 数据组装
            for (String str : dataMap.keySet()) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("area", str);
                temp.put("list", dataMap.get(str));
                dataList.add(temp);
            }


            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }

    private String appVersionLogin(HttpServletRequest request) {
        CsArgument ca = CsArgument.getCsArgument($.add(CsArgument.F.csaFlag, "APP_VERSION_LOGIN_FLAG"));
        if(null != ca) {
            //是否禁止非配置版本登录的开关
            if("true".equals(ca.getCsaValue$())) {
                //可登录版本列表
                CsArgument cv = CsArgument.getCsArgument($.add(CsArgument.F.csaFlag, "APP_VERSION_ACCESS_LIST"));
                String av = null == cv || StringUtils.isBlank(cv.getCsaValue$()) ? "" : cv.getCsaValue$();
                //不可登录版本提示信息
                CsArgument tip = CsArgument.getCsArgument($.add(CsArgument.F.csaFlag, "APP_VERSION_LOGIN_MSG"));
                String at = null == tip || StringUtils.isBlank(tip.getCsaValue$()) ? "版本过低，被禁止登录，请升级程序" : tip.getCsaValue$();
                if(StringUtils.isNotBlank(av)) {
                    String[] an = av.split(",");
                    //获取移动端内部版本号
                    String appInnerVersion = request.getHeader("appInnerVersion");
                    appInnerVersion = StringUtils.isBlank(appInnerVersion)  ? "" : appInnerVersion;
                    rentAppLogger.info("appInnerVersion:"+appInnerVersion);
                    boolean flag = false;
                    if(StringUtils.isNotBlank(appInnerVersion) && null != an) {
                        for(String aa : an) {
                            if(appInnerVersion.equals(aa)) {
                                flag = true;
                            }
                        }
                    }
                    if(!flag) {
                        return returnError("102", at);
                    }
                }
            }
        }
        return null;
    }
    /**
     * 登录
     * 
     * @return
     */
    public String login() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            
            String strUsername = $.getString("username", "");
            String strPass = $.getString("password", "");
            int type = $.getInteger("type", 0);// 默认密码登录；1-验证码登录

            if (SystemHelper.isNullOrEmpty(strUsername)) {
                return returnError("102", "您还没有输入帐号");
            }
            if (SystemHelper.isNullOrEmpty(strPass)) {
                if (type == 0) {
                    return returnError("103", "您还没有输入密码");
                } else if (type == 1) {
                    return returnError("103", "您还没有输入验证码");
                }
            }
            
            String ret = this.appVersionLogin(request);
            if(null != ret) {
                return ret;
            }

            // String strPassword = RSAUtil.decrypt(strPass);
            String strPassword = strPass;
            CsMember user = null;

            if (SystemHelper.isEmail(strUsername))// 邮箱方式登录
                user = csMemberService.getCsMember($.Map("csmEmail", strUsername));
            else if (SystemHelper.isMobile(strUsername))// 手机方式登录
                user = csMemberService.getCsMember($.Map("csmMobile", strUsername));
            else
                // 帐号方式登录
                user = csMemberService.getCsMember($.Map("csmUsername", strUsername));

            if (user == null) {
                return returnError("104", "您输入的帐号不正确，请重新输入");
            }
            if (user.getCsmStatus() != 1) {
                return returnError("105", "该帐户已经失效，请联系客服");
            }

            if (type == 0) {
                if (!$.md5(strPassword).equals(user.getCsmPassword().toUpperCase()))
                    return returnError("106", "您输入的密码不正确，请重新输入");
            } else if (type == 1) {
                if (SessionMgr.get(strUsername, LOGIN_CODE) == null) {
                    return returnError("106", "您输入的验证码已失效，请重新获取");
                } else if (!$.equals(strPass, (String) SessionMgr.get(strUsername, LOGIN_CODE))) {
                    return returnError("106", "您输入的验证码不正确，请重新输入");
                }
            }

            // 校验企业用户信息
            // CsUnitPerson person =
            // CsUnitPerson.getCsUnitPerson($.add("csupMember", user.getCsmId()));
            // if (person == null)
            // return returnError("107", "您的注册还未完成，请继续完善信息");

            String sessionToken = SessionMgr.get(user.getCsmId$());
            if (StringUtils.isNotEmpty(sessionToken)) {
                if (OauthUtils.getOauth(sessionToken) != null) {
                    OauthUtils.removeToken(sessionToken, user.getCsmId$());
                } else {
                    SessionMgr.del(sessionToken);
                }
            }

            String csmLastIp = user.getCsmLastIp();
            java.util.Date csmLastTime = user.getCsmLastTime();
            // 更新最后登录IP，最后登录时间，登录次数 +1
            if (user.getCsmLoginS() == null)
                csMemberService.updateCsMember$NotNull(new CsMember()
                        .csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date())
                        .csmLoginS(1).csmId(user.getCsmId()));
            else {
                csMemberService.updateCsMember$NotNull(new CsMember()
                        .csmLastIp(request.getRemoteAddr()).csmLastTime(new java.util.Date())
                        .csmLoginS(user.getCsmLoginS() + 1).csmId(user.getCsmId()));
            }

            user.setCsmLastIp(csmLastIp);
            user.setCsmLastTime(csmLastTime);

            String token = UUIDGenerator.getUUID();
            OauthUtils.saveToken(user.getCsmId().toString(), token);
            JsonFormat result = JsonFormat.success().setData(
                    $.add("access_token", token).add("id", String.valueOf(user.getCsmId())));
            return $.SendHtml($.json(result), SYSTEM.UTF8);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 找回密码-发送短信码
     * 
     * @return
     */
    public String forgetsms() {
        try {
            String mobile = $.getString("mobile", "");
            if ($.empty(mobile))
                return returnError("101", "手机号码不能为空");
            if (!SystemHelper.isMobile(mobile))
                return returnError("102", "手机号码格式错误");

            int type = $.getInteger("type", 0);// 验证码类型
            String smRateKey = "forgetsms";// 短信发送时间key
            String flag = "FORGOT_PWD";// 短信模块标识 0-忘记密码； 1-重置密码；2-注册；3-登录
            String defaultTemplate = CommonMessage.MSG0201;// 短信默认模块 201 忘记密码 202重置密码 203 注册密码
            String key = FORGET_SMS_CODE;// 短信校验码保存key值
            switch (type) {
                case 1:
                    smRateKey = "resetsms";
                    flag = "RESET_PWD";
                    defaultTemplate = CommonMessage.MSG0202;
                    key = RESET_SMS_CODE;
                    break;

                case 2:
                    smRateKey = "registersms";
                    flag = "REGISTER_PWD";
                    defaultTemplate = CommonMessage.MSG0203;
                    key = REGISTER_SMS_CODE;
                    break;
                case 3:
                    smRateKey = "loginsms";
                    flag = LOGIN_CODE;
                    key = LOGIN_CODE;
                    break;
            }

            // 验证短信发送频率
            Date last = SessionMgr.get(mobile, smRateKey);
            if (last != null) {
                if ((new Date().getTime() - last.getTime()) < SYSTEM.MINUTE)
                    return returnError("103", "您已获取短信验证码，请查看短信，或一分钟后重新获取");
            }
            String strCode = $.zerofill($.rand(9999), 4);
            CsMember csMember = CsMember.where().csmMobile(mobile).get();

            if (csMember == null && type != 2) {
                return returnError("104", "您还未注册");
            }

            if (csMember != null && type == 2) {
                return returnError("105", "手机号已被注册，请直接登录");
            }

            Boolean bOk = UtilHelper.sendTemplateSMS(type == 2 ? 1l : csMember.getCsmHost(), flag,
                    mobile, defaultTemplate, SMSType.提示类短信, $.add("code", strCode));
            if (bOk == true) {
                if (type == 3) {
                    SessionMgr.put(mobile, key, strCode, 5 * 60);
                    SessionMgr.put(mobile, smRateKey, new Date(), 5 * 60);
                } else {
                    SessionMgr.put(mobile, key, strCode);
                    SessionMgr.put(mobile, smRateKey, new Date());
                }
                return $.SendHtml($.json(JsonFormat.success()), CHARSET);
            } else {
                return returnError("104", "短信发送失败");
            }
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 找回密码-验证手机号
     * 
     * @return
     */
    public String forgetvalid() {
        try {
            int type = $.getInteger("type", 0);
            String csmMobile = $.getString("mobile", "");
            String strValidMobCode = $.getString("validCode", "");
            if ($.empty(csmMobile)) {
                return returnError("101", "请输入手机号");
            }
            if ($.empty(strValidMobCode)) {
                return returnError("104", "未输入短信校验码，不能修改密码");
            }
            if (!SystemHelper.isMobile(csmMobile)) {
                return returnError("105", "手机号码格式错误");
            }

            if (type == 0 && !$.equals(strValidMobCode,
                    (String) SessionMgr.get(csmMobile, FORGET_SMS_CODE))) {
                return returnError("110", "短信校验码输入错误");
            } else if (type == 1 && !$.equals(strValidMobCode,
                    (String) SessionMgr.get(csmMobile, RESET_SMS_CODE))) {
                return returnError("110", "短信校验码输入错误");
            } else if (type == 2 && !$.equals(strValidMobCode,
                    (String) SessionMgr.get(csmMobile, REGISTER_SMS_CODE))) {
                return returnError("110", "短信校验码输入错误");
            }

            CsMember existMember =
                    csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));

            String token = UUIDGenerator.getUUID();
            String key = FORGET_TOKEN;
            switch (type) {
                case 1:
                    key = RESET_TOKEN;
                    if (existMember == null)
                        return returnError("111", "手机号不存在，不能修改密码");
                    break;
                case 2:
                    key = REGISTER_TOKEN;
                    break;
            }
            SessionMgr.put(csmMobile, key, token);
            return $.SendHtml($.json(JsonFormat.success().addValue(FORGET_TOKEN, token)), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 重置密码
     * 
     * @return
     */
    public String resetpwd() {
        try {

            // 验证当前手机号
            if ($.getString("forget_token") == null) {
                return returnError("102", "参数缺失");
            }

            String mobile = $.getString("mobile", "");
            String csmPassword = $.getString("txtPassWord", "");
            String rePwd = $.getString("txtRePassWord", "");
            if ($.empty(csmPassword)) {
                return returnError("103", "未输入密码");
            }
            if (csmPassword.length() < 6) {
                return returnError("104", "请输入6到20位有效密码");
            }
            if (!$.equals(csmPassword, rePwd)) {
                return returnError("105", "两次密码输入不一致");
            }
            if (mobile == null) {
                return returnError("106", "手机号码不能为空");
            }
            // 当前重置密码用户
            CsMember csMember = csMemberService.getCsMember($.add(CsMember.F.csmMobile, mobile));
            if (csMember == null)
                return returnError("111", "手机号不存在，不能修改密码");

            String token = SessionMgr.get(csMember.getCsmMobile(), FORGET_TOKEN);
            if (token != null && !$.getString("forget_token").equals(token)) {
                return returnError("101", "非法请求");
            }

            // ----------------------------------------------------------验证通过，重置密码
            csMemberService.updateCsMember$NotNull(
                    new CsMember().csmPassword($.md5(rePwd)).csmId(csMember.getCsmId()));
            // $.SetTips("重置密码成功，进入到成功页面");
            // 清空忘记密码的token
            SessionMgr.remove(csMember.getCsmMobile(), FORGET_SMS_CODE);
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 退出登录
     * 
     * @return
     */
    public String logout() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            OauthUtils.removeToken($.getString("access_token"), member.getCsmId$());
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }


    // 注册手机号校验
    public String register() {
        try {

            final short from = $.getShort("from");
            // 默认0：跳转登录页；1-跳过登录
            final short type = $.getShort("type", (short) 0);

            final String csmMobile = $.getString("mobile", "");
            if ($.empty(csmMobile)) {
                return returnError("101", "请输入手机号");
            }

            if (!SystemHelper.isMobile(csmMobile)) {
                return returnError("103", "手机号码格式错误");
            }

            String strValidMobCode = $.getString("validCode", "");
            if (from != 2) {
                if ($.empty(strValidMobCode)) {
                    if (type == 0) {
                        return returnError("102", "未输入短信校验码，不能注册账号");
                    } else if (type == 1) {
                        return returnError("102", "请先通过短信验证码校验");
                    }
                } else {
                    if (type == 0 && !$.equals(strValidMobCode,
                            (String) SessionMgr.get(csmMobile, REGISTER_SMS_CODE))) {
                        return returnError("104", "短信校验码输入错误");
                    } else if (type == 1 && !$.equals(strValidMobCode,
                            (String) SessionMgr.get(csmMobile, REGISTER_TOKEN))) {
                        return returnError("104", "请先通过短信验证码校验");
                    }
                }
            }

            final String csmPassword = $.getString("txtPassWord", "");
            final String rePwd = $.getString("txtRePassWord", "");

            if ($.empty(csmPassword)) {
                return returnError("105", "未输入密码");
            }
            if (csmPassword.length() < 6) {
                return returnError("106", "请输入6到20位有效密码");
            }
            if (!$.equals(csmPassword, rePwd)) {
                return returnError("109", "两次密码输入不一致");
            }

            // -------------------只有微信端注册 才上传一下内容-------------

            final String realName = $.getString("realName");// 真实姓名
            final short sex = $.getShort("sex", (short) 0);
            int unitId = $.getInteger("unitId", 0);

            if (from == 2 && unitId == 0) {
                return returnError("101", "请选择单位");
            }

            final CsUnitInfo csUnitInfo = CsUnitInfo.get(Long.parseLong(String.valueOf(unitId)));
            if (csUnitInfo == null && from == 2) {
                return returnError("104", "单位不存在，请联系管理员");
            }
            final String unitName = $.getString("unitName");

            // -----------------只有微信端注册 才上传以上内容-----------------

            CsMember existMember =
                    csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));
            if (existMember != null) {
                return returnError("108", "手机号已被注册,请去登录");
            }
            final String inviteCode = $.getString("inviteCode");

            /**** 开始创建会员相关信息 *****/
            // 初始化会员账号
            CsMember member = csMemberService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... arg0) {
                    // TODO Auto-generated method stub
                    CsMember csMember = getRegisterMember(new CsMember(), csmPassword, from,
                            csmMobile, inviteCode);
                    Long csmHost = csMember.getCsmHost();
                    csMember = csMemberService.saveCsMember(csMember);

                    // 保存会员信息相关信息到cs_member_info
                    CsMemberInfo csMemberInfo = getMemberInfo(new CsMemberInfo(),
                            null != csmHost ? csMember.getCsmHost() : 1, null, csMember, sex);
                    csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);

                    // 保存会员信息相关信息到cs_member_info成功后，生成对应的csmiId,生成完成后反写到cs_member表中的csm_Info字段中
                    csMember.setCsmInfo(csMemberInfo.getCsmiId());
                    csMemberService.updateCsMember$NotNull(csMember);

                    if (from == 2) {
                        Long host = csUnitInfo.getCsuiHost();
                        // 企业，车市信息更新到会员
                        CsMember member = updateMember(csMember, realName, host, unitName);
                        csMemberService.updateCsMember$NotNull(member);
                        csMemberInfo.setCsmiHost(host);
                        csMemberInfo.setCsmiName(realName);
                        csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);

                        // 保存会员关系到cs_member_ship表中
                        CsMemberShip csMemberShip =
                                getMemberShip(new CsMemberShip(), host, -1l, member.getCsmId());
                        csMemberShipService.saveCsMemberShip(csMemberShip);

                        // 保存人员信息到cs_unit_person
                        CsUnitPerson csUnitPerson = getUnitPerson(new CsUnitPerson(), host,
                                csUnitInfo.getCsuiId(), member.getCsmId(), -1l);
                        csUnitPersonService.saveCsUnitPerson(csUnitPerson);
                        UtilHelper.sendTemplateSMS(csUnitPerson.getCsupHost(), "REGIST_CODE",
                                $.str(csmMobile), "欢迎使用北京出行，你的系统登录名为{mobile}，密码为{code}。",
                                SMSType.通知类短信,
                                $.add("mobile", $.str(csmMobile)).add("code", csmPassword));

                    }

                    return (T) csMember;
                }
            });

            // TODO Auto-generated method stub
            SessionMgr.remove(csmMobile, REGISTER_SMS_CODE);
            SessionMgr.remove(csmMobile, "registersms");

            if (type == 1) {
                // 注册成功，后台登录
                String token = UUIDGenerator.getUUID();
                OauthUtils.saveToken(member.getCsmId().toString(), token);
                JsonFormat result = JsonFormat.success().setData(
                        $.add("access_token", token).add("id", String.valueOf(member.getCsmId())));
                return $.SendHtml($.json(result), SYSTEM.UTF8);
            } else {
                // 用户手动登录
                return $.SendHtml($.json(JsonFormat.success()), CHARSET);
            }
        } catch (Exception ex) {
            // TODO: handle exception
            return returnError(ex);
        }
    }


    // 保存员工部门信息
    public String putMemberInfo() {
        try {
            String csmMobile = $.getString("mobile", "");
            if ($.empty(csmMobile)) {
                return returnError("100", "请输入手机号");
            }

            final CsMember csMember =
                    csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));
            if (csMember == null) {
                return returnError("106", "注册失败，重新注册");
            }

            CsUnitPerson existUnitPerson = CsUnitPerson
                    .getCsUnitPerson($.add(CsUnitPerson.F.csupMember, csMember.getCsmId()));
            if (existUnitPerson != null) {
                return returnError("107", "资料已完善，请直接登录");
            }

            final String realName = $.getString("realName");// 真实姓名
            final short sex = $.getShort("sex");
            int unitId = $.getInteger("unitId");
            if (unitId == 0) {
                return returnError("101", "请选择单位");
            }

            final CsUnitInfo csUnitInfo = CsUnitInfo.get(Long.parseLong(String.valueOf(unitId)));
            if (csUnitInfo == null) {
                return returnError("104", "单位不存在，请联系管理员");
            }
            final String unitName = $.getString("unitName");

            final long host = csUnitInfo.getCsuiHost();

            csUnitPersonService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... arg0) {
                    // TODO Auto-generated method stub

                    CsMember member = updateMember(csMember, realName, host, unitName);
                    csMemberService.updateCsMember$NotNull(member);

                    // 保存会员信息相关信息到cs_member_info
                    CsMemberInfo csMemberInfo = CsMemberInfo
                            .getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, member.getCsmId()));
                    if (csMemberInfo == null) {
                        csMemberInfo =
                                getMemberInfo(new CsMemberInfo(), host, realName, csMember, sex);
                        csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
                    } else {
                        csMemberInfo = getMemberInfo(csMemberInfo, host, realName, csMember, sex);
                        csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
                    }

                    // 保存会员信息相关信息到cs_member_info成功后，生成对应的csmiId,生成完成后反写到cs_member表中的csm_Info字段中
                    csMember.setCsmInfo(csMemberInfo.getCsmiId());
                    csMemberService.updateCsMember$NotNull(csMember);

                    // 保存会员关系到cs_member_ship表中
                    CsMemberShip csMemberShip = CsMemberShip
                            .getCsMemberShip($.add(CsMemberShip.F.csmsTargeter, member.getCsmId()));
                    if (csMemberShip == null) {
                        csMemberShip =
                                getMemberShip(new CsMemberShip(), host, -1l, member.getCsmId());
                        csMemberShipService.saveCsMemberShip(csMemberShip);
                    } else {

                    }


                    // 保存人员信息到cs_unit_person
                    CsUnitPerson csUnitPerson = CsUnitPerson
                            .getCsUnitPerson($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
                    if (csUnitPerson == null) {
                        csUnitPerson = getUnitPerson(new CsUnitPerson(), host,
                                csUnitInfo.getCsuiId(), member.getCsmId(), -1l);
                        csUnitPersonService.saveCsUnitPerson(csUnitPerson);
                    } else {
                        csUnitPerson = getUnitPerson(csUnitPerson, host, csUnitInfo.getCsuiId(),
                                member.getCsmId(), -1l);
                        csUnitPersonService.updateCsUnitPerson$NotNull(csUnitPerson);
                    }



                    return null;
                }
            });

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);

        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 身份证认证
     * 
     */
    public String authCertify() {
        try {
            final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            if (member.getVReal() == 1) {
                return returnError("100", "已认证通过，请勿重复认证");
            }

            final String certifyImage = $.getString("certifyImage");// 身份证正面（国徽面）
            final String certifyNum = $.getString("certifyNum");// 身份证号码
            final String onCertifyImage = $.getString("onCertifyImage");// 身份证反面（人像面）
            final String realName = $.getString("realName");// 真实姓名
            if ($.empty(certifyImage)) {
                return returnError("101", "身份证国徽面图片路径未上传，请上传图片。");
            }
            if ($.empty(certifyNum)) {
                return returnError("101", "身份证号码未填写，请填写。");
            } else if (!SystemHelper.isIdCardNo(certifyNum)) {
                return returnError("101", "身份证号码格式不正确。");
            }
            if ($.empty(onCertifyImage)) {
                return returnError("101", "身份证人像面图片路径未上传，请上传图片。");
            }
            if ($.empty(realName)) {
                return returnError("101", "真实姓名未填写，请填写。");
            }

            csMemberInfoService.executeTransaction(new Function() {
                @Override
                public <T> T execute(Object... arg0) {

                    csMemberInfoService.updateMemberInfoCertifyImage(member, certifyImage,
                            certifyNum, realName, onCertifyImage);

                    member.setCsmName(realName);
                    csMemberService
                            .updateCsMember$NotNull(updateAutoState(member, null, (short) 2, null));
                    return null;
                }
            });



            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 驾驶证认证
     * 
     */
    public String authDriverLicense() {
        try {
            final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            if (member.getVDrive() == 1) {
                return returnError("100", "已认证通过，请勿重复认证");
            }

            final String driverImage = $.getString("driverImage");
            if ($.empty(driverImage)) {
                return returnError("101", "驾驶证图片路径未上传，请上传图片。");
            }

            csMemberInfoService.executeTransaction(new Function() {
                @Override
                public <T> T execute(Object... arg0) {
                    csMemberInfoService.updateMemberInfoDriverImage(member, driverImage);

                    csMemberService
                            .updateCsMember$NotNull(updateAutoState(member, (short) 2, null, null));
                    return null;
                }
            });


            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 工作证名认证
     * 
     */
    public String authWork() {
        try {
            final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            if (member.getVWork() == 1) {
                return returnError("100", "已认证通过，请勿重复认证");
            }

            final String company = $.getString("company");// 企业
            final String department = $.getString("department");// 部门
            final String proofOfEmployment = $.getString("proofOfEmployment");// 图片路径
            if ($.empty(company)) {
                return returnError("101", "企业未填写，请填写。");
            }
            if ($.empty(department)) {
                return returnError("101", "部门未填写，请填写。");
            }
            /*if ($.empty(proofOfEmployment)) {
                return returnError("101", "图片路径未上传，请上传图片。");
            }*/

            csMemberInfoService.executeTransaction(new Function() {
                @Override
                public <T> T execute(Object... arg0) {

                    csMemberInfoService.updateMemberInfoWorkImage(member, proofOfEmployment,
                            company, department);

                    csMemberService
                            .updateCsMember$NotNull(updateAutoState(member, null, null, (short) 2));

                    return null;
                }
            });

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }


    /**
     * 实名认证 身份证 驾驶证
     * 
     * @return
     */
    public String authInfo() {
        try {
            String csmMobile = $.getString("mobile", "");
            if ($.empty(csmMobile)) {
                return returnError("100", "请输入手机号");
            }

            final CsMember member =
                    csMemberService.getCsMember($.add(CsMember.F.csmMobile, csmMobile));
            if (member == null) {
                return returnError("102", "用户尚未注册，请注册");
            }
            final String realName = $.getString("realName");// 真实姓名
            final String certifyNum = $.getString("certifyNum");
            final String certifyImg = $.getString("certifyImage");// 身份证反面
            final String onCertifyImg = $.getString("onCertifyImage");// 身份正面
            final String driverImage = $.getString("driverImage");

            final String cardNo = $.getString("cardNo");
            final String cardImage = $.getString("cardImage");

            if (!$.empty(cardNo)) {
                Long count = CsCreditCard.getCsCreditCardCount(
                        $.add(CsCreditCard.F.csccNo, cardNo).add(CsCreditCard.F.csccType, 1)
                                .add("definex", "( cscc_member != " + member.getCsmId() + " )"));
                if (count != null && count.intValue() > 0) {
                    return returnError("103", "信用卡卡号已被其他人员绑定");
                }
            }

            csMemberInfoService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... arg0) {
                    // TODO Auto-generated method stub
                    if ($.empty(certifyImg)) {
                        csMemberInfoService.updateMemberInfo(member, certifyImg, certifyNum,
                                driverImage, onCertifyImg);

                        member.setCsmName(realName);
                        csMemberService.updateCsMember$NotNull(
                                updateAutoState(member, (short) 2, (short) 2));
                    }
                    if ($.empty(cardNo)) {
                        updateCreditCard(member, cardNo, cardImage);
                    }
                    return null;
                }
            });

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 信用卡认证 信用卡照片
     * 
     * @return
     */
    public String authCreditCard() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            String cardNo = $.getString("cardNo");
            if ($.empty(cardNo)) {
                return returnError("101", "信用卡卡号不能为空");
            }

            String cardImage = $.getString("cardImage");
            if ($.empty(cardImage)) {
                return returnError("102", "信用卡照片不能为空");
            }

            Long count = CsCreditCard.getCsCreditCardCount(
                    $.add(CsCreditCard.F.csccNo, cardNo).add(CsCreditCard.F.csccType, 1)
                            .add("definex", "( cscc_member != " + member.getCsmId() + " )"));

            if (count != null && count.intValue() > 0) {
                return returnError("103", "信用卡卡号已被其他人员绑定");
            }
            updateCreditCard(member, cardNo, cardImage);

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 修改信信用卡信息
     * 
     * @param member
     * @return
     */
    public void updateCreditCard(CsMember member, String cardNo, String cardImage) {
        CsCreditCard creditCard = CsCreditCard.getCsCreditCard($
                .add(CsCreditCard.F.csccMember, member.getCsmId()).add(CsCreditCard.F.csccType, 1));
        if (creditCard == null) {
            creditCard = new CsCreditCard();
        }
        creditCard.setCsccNo(cardNo);
        creditCard.setCsccImage(cardImage);
        creditCard.setCsccMember(member.getCsmId());
        creditCard.setCsccLimit(0d);
        creditCard.setCsccScope(0);
        creditCard.setCsccAddTime(new Date());
        creditCard.setCsccUpdateTime(new Date());
        creditCard.setCsccStatus((short) 2);
        creditCard.setCsccType((short) 1);

        if ($.empty(creditCard.getCsccId$())) {
            csCreditCardService.saveCsCreditCard(creditCard);
        } else {
            csCreditCardService.updateCsCreditCard(creditCard);
        }
    }


    /**
     * 企业部门
     * 
     * @return
     */
    public String getAreasUnit() {
        try {
            List<CsArea> areaList = CsArea.getCsAreaList($.add(CsArea.F.csaStatus, 1), -1);

            List<Map<String, Object>> cldList = new ArrayList<Map<String, Object>>();

            // 设置默认网点
            cldList.add(defaultArea());
            for (CsArea area : areaList) {
                List<CsUnitInfo> unitInfoList =
                        CsUnitInfo.getCsUnitInfoList($.add(CsUnitInfo.F.csuiArea, area.getCsaId())
                                .add(CsUnitInfo.F.csuiStatus, 1), 1000);
                cldList.add($.add("areaId", area.getCsaId()).add("areaName", area.getCsaName$())
                        .add("unitList", assembleUnitInfo(unitInfoList)));
            }

            return $.SendHtml($.json(JsonFormat.success().setData($.$("list", cldList))), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }

    /**
     * 企业部门
     * 
     * @return
     */
    public String getUnitGroup() {
        try {

            Long unitId = $.getLong("unitId");
            if (unitId == null) {
                return returnError("100", "无企业参数");
            }
            /*
             * List<CsArea> areaList = CsArea.getCsAreaList($.add(CsArea.F.csaStatus, 1), -1);
             * 
             * List<Map<String, Object>> cldList = new ArrayList<Map<String, Object>>();
             * 
             * // 设置默认网点 cldList.add(defaultArea()); for (CsArea area : areaList) { List<CsUnitInfo>
             * unitInfoList = CsUnitInfo.getCsUnitInfoList($.add(CsUnitInfo.F.csuiArea,
             * area.getCsaId()) .add(CsUnitInfo.F.csuiStatus, 1), 1000); cldList.add($.add("areaId",
             * area.getCsaId()).add("areaName", area.getCsaName$()) .add("unitList",
             * assembleUnitInfo(unitInfoList)));
             * 
             * }
             */
            List<CsUnitGroup> groupList =
                    CsUnitGroup.getCsUnitGroupList($.add(CsUnitGroup.F.csugInfo, unitId).add(CsUnitGroup.F.csugStatus, 1), -1);
            List<Map<String, Object>> groupNameList = new ArrayList<>();
            if(null!=groupList) {
                for (CsUnitGroup csUnitGroup : groupList) {
                    groupNameList.add($.add("name", csUnitGroup.getCsugName$()));
                     
                 }
            }
            
            return $.SendHtml($.json(JsonFormat.success().setData($.$("list", groupNameList))),
                    CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }

    /**
     * 根据单位名称
     * 
     * @return
     */
    public String getQueryUnit() {
        try {
            String unitName = $.getString("unitName"); // 企业单位名称

            LzMap param = $.Map();
            if (StringUtils.isNotEmpty(unitName)) { // 按单位名称模糊匹配
                param.add(CsUnitInfo.F.csuiName, unitName);
            }

            Long unitInfoCount = CsUnitInfo.getCsUnitInfoCount(param);
            List<CsUnitInfo> unitInfoList =
                    CsUnitInfo.getCsUnitInfoList(param, unitInfoCount.intValue());
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.$("list", assembleUnitInfo(unitInfoList)))),
                    CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }



    // =============================================================================================================
    /**
     * 获取网点信息
     * 
     * @return
     */
    public String getOutletsInfo() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long id = $.getLong("outletsId");
            CsOutlets outlets = null;
            if (id == null) {
                CsUnitPerson person =
                        CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
                if (person == null)
                    return returnError("101", "用户未绑定企业用户");
                CsUnitInfo unitInfo = person.get$csupInfo();
                if (unitInfo == null)
                    return returnError("102", "用户未绑定单位");
                List<CsOutlets> outletsList = unitInfo.get$csuiOutlets();
                if (CollectionUtils.isEmpty(outletsList)) {
                    return returnError("103", "用户单位没有可用网点");
                } else {
                    outlets = outletsList.get(0);
                }

            } else {
                outlets = csOutletsService.getCsOutletsById(id);
            }

            return $.SendHtml($.json(JsonFormat.success().setData($.$("data", outlets))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取车辆价格详情列表
     * 
     * @return
     */
    public String getCarPrices() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long id = $.getLong("carId");
            if (id == null) {
                return returnError("101", "参数无效");
            }
            CsCar car = CsCar.get(id);
            CsCarModel model = car.get$cscModel();

            Prices prices = SystemHelper.getPrices(car.getCscHost(), car.getCscModel(),
                    car.getCscOutlets());
            LzMap data = $.add("hourPrice", prices.get("租金.工作日白天小时|租金.白天小时|租金.每小时"));
            data.add("dayPrice", prices.get("租金.工作日一天|租金.每天"));
            data.add("workdayRangePrice", prices.get("租金.工作日白天包段|租金.白天包段"));
            data.add("workdaynightRangePrice", prices.get("租金.工作日晚上包段|租金.晚上包段"));
            data.add("overTime", prices.get("超时费用.每小时")); // 超时费用
            data.add("secure", prices.get("按小时免责.每小时")); // 按小时免责
            data.add("insure", prices.get("按里程免责.每公里")); // 按里程免责

            if (model.getCscmType() == (short) 0) {
                data.add("mileageFee", prices.get("油里程费.每公里"));
            } else if (model.getCscmType() == (short) 1) {
                data.add("mileageFee", prices.get("电里程费.每公里"));
            }

            return $.SendHtml($.json(JsonFormat.success().setData($.$("map", data))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }

    }

    /**
     * 订单预定检查
     * 
     * @carId 车辆ID
     * @start 开始时间
     * @finish 结束时间
     * @insureType 保险类型
     * @freehour 免费小时
     * @return
     */
    public String checkOrder() {
        CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }

        Long carId = $.getLong("carId");
        Date takeTime = $.getDate("takeTime");
        Date retTime = $.getDate("retTime");
        int type = $.getInteger("type"); // 0:个人 1:单位

        if (carId == null) {
            return returnError("101", "没有选择车辆");
        }
        if (takeTime == null) {
            return returnError("102", "请选择预定开始时间");
        }
        if (retTime == null) {
            return returnError("103", "请选择预定结束时间");
        }
        if (retTime.before(takeTime)) {
            return returnError("104", "结束时间不能早于开始时间");
        }
        CsCar car = csCarService.getCsCarById(carId);
        if (car == null)
            return returnError("109", "没有选择车辆");
        if (car.getCscStatus() == (short) 0)
            return returnError("110", "选择的车辆已下线");

        // 设置定单基本信息
        BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
        baseOrderInfo.setCsoFinish(retTime);
        baseOrderInfo.setCsoStart(takeTime);
        baseOrderInfo.setCsoCar(carId);
        baseOrderInfo.setCsoOutlets(car.getCscOutlets());
        baseOrderInfo.setCsoOutletsRet(car.getCscOutlets());

        LzMap values = $.Map();
        OrderInfo orderInfo = null;
        Long insureType = null;
        Double freehour = null;
        // 获取小时数
        // int allhours = (int) ((baseOrderInfo.getCsoFinish().getTime() -
        // baseOrderInfo.getCsoStart().getTime()) / SYSTEM.HOUR);

        try {
            if (type == 1) { // 单位
                CsUnitPerson person =
                        CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
                if (person == null)
                    return returnError("106", "用户未绑定企业用户");
                CsUnitInfo unitInfo = person.get$csupInfo();
                if (unitInfo == null)
                    return returnError("106", "用户未绑定企业用户");
                orderInfo = commonOrderService.splitOrderDetails(member.getCsmId(),
                        baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(),
                        baseOrderInfo.getCsoFinish(), null, freehour, null, null,
                        unitInfo.getCsuiFeeType(), insureType, null, null);
            } else { // 个人
                orderInfo = commonOrderService.splitOrderDetails(member.getCsmId(),
                        baseOrderInfo.getCsoCar(), baseOrderInfo.getCsoStart(),
                        baseOrderInfo.getCsoFinish(), null, freehour, null, null, null, insureType,
                        null, null);
            }
            if (orderInfo != null) {
                values.put("price", orderInfo.getPrice()); // 总消费金额
            } else {
                values.put("price", 0); // 总消费金额
            }
            return $.SendHtml($.json(JsonFormat.success().setData(values)), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }

    /**
     * 订单提交
     * 
     * @return
     */
    public String submitPersonalOrder() {

        CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }
        if(MemberRecStatus.REC_PASS != member.getVstatus()) {
            StringBuilder sb = new StringBuilder();
            if(MemberRecStatus.REC_PASS != member.getVDrive()) {
                sb.append("驾驶证认证未通过,");
            }
            if(MemberRecStatus.REC_PASS != member.getVReal()) {
                sb.append("实名认证未通过,");
            }
            if(MemberRecStatus.REC_PASS != member.getVWork()) {
                sb.append("工作认证未通过,");
            }
            if(MemberRecStatus.REC_PASS != member.getVOffline()) {
                sb.append("线下认证未通过,");
            }
            sb.append("不能下单");
            return returnError("111", sb.toString());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        String ret = this.appVersionLogin(request);
        if(null != ret) {
            return ret;  
        }

        Long takeOutletsId = $.getLong("takeOutletsId");
        Long retOutletsId = $.getLong("retOutletsId", takeOutletsId);

        Long carId = $.getLong("carId");
        Date takeTime = $.getDate("takeTime");
        Date retTime = $.getDate("retTime");
        Long paid = $.getLong("paid"); // 代付会员(可选)
        String remark = $.getString("remark");
        String version = $.getString("appVersion");

        // 套餐ID
        String mealId = $.getString("mealId", "0");

        Double freeHours = null;
        Long insureType = null;

        if (carId == null) {
            return returnError("101", "没有选择车辆");
        }
        if (takeTime == null) {
            return returnError("102", "请选择预定开始时间");
        }
        if(takeTime.getTime()-new Date().getTime()<30*60*1000){
       	 	return returnError("105", "取车时间需提前30分钟预订车辆");
        }
        if (retTime == null) {
            return returnError("103", "请选择预定结束时间");
        }
        if (!retTime.after(takeTime)) {
            return returnError("104", "结束时间必须晚于开始时间");
        }
        if (takeOutletsId.longValue() != retOutletsId.longValue()) {
            if (takeTime.getTime() - System.currentTimeMillis() > 120 * 60 * 1000) {
                return returnError("108", "异地借还只能提前2小时内预定");
            }
        }
        CsCar car = csCarService.getCsCarById(carId);
        if (car == null)
            return returnError("109", "没有选择车辆");
        if (car.getCscStatus() == (short) 0||car.getCscStatus() == (short) 2) {
            return returnError("110", "选择的车辆已下线");
        }   
        try {

            /** ********驾驶人********* */
//            // 驾驶认证没有审核通过
//            if (member.getCsmVDrive() != null && member.getCsmVDrive() != (short) 1
//                    && member.getCsmEvcard() != null) {
//                if (member.getCsmVDrive() == (short) 2) {
//                    // 驾驶认证已经上传，请耐心等待
//                    return returnError("105", "驾驶认证已经上传待审核，请耐心等待");
//                } else {
//                    // 驾驶认证没有上传
//                    return returnError("103", "驾驶认证未上传");
//                }
//            }

            /** ********支付人********* */
            Long payMemberId = null;
            payMemberId = member.getCsmId();
            //
            Long orderId = null;
            if (!mealId.trim().equals("0")) {
                CsOrderCluster orderCluster = commonDisposeService.saveMealOrder(member.getCsmId(),
                        member.getCsmId(), car.getCscId(), takeOutletsId, retOutletsId, takeTime,
                        Long.valueOf(mealId), null, insureType, null, freeHours, 0l, From.APP,
                        "鹏龙app" + version);
                orderId = orderCluster.getCsocId();
            } else {
                CsOrder csOrder = commonDisposeService.executeSaveOrder(payMemberId,
                        member.getCsmId(), car.getCscId(), takeOutletsId, retOutletsId, takeTime,
                        retTime, null, insureType, null, freeHours, null, 0l, From.APP,
                        "鹏龙app" + version);
                orderId = csOrder.getCsoId();
            }
           
            // 更新默认取车点
            if (member.getCsmOutlets() == null) {
                CsMember newMember = new CsMember();
                newMember.setCsmOutlets(takeOutletsId);
                newMember.setCsmId(member.getCsmId());
                csMemberService.updateCsMember$NotNull(newMember);
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.Map("orderId", orderId))),
                    CHARSET);
        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("104", oe.getMessage());
        } catch (Exception e) {
            return returnError(e);
        }
    }

    // =============================================================================================================

    /**
     * 获取所在单位网点的充电桩
     * 
     * @return
     */
    public String getPowerpiles() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("101", "用户未绑定企业用户");
            CsUnitInfo unitInfo = person.get$csupInfo();
            if (unitInfo == null)
                return returnError("102", "用户未绑定单位");
            CsOutlets outlets = null;

            List<CsOutlets> outletsList = unitInfo.get$csuiOutlets();
            if (CollectionUtils.isEmpty(outletsList)) {
                return returnError("103", "用户单位没有可用网点");
            } else {
                outlets = outletsList.get(0);
            }

            List<CsPowerPile> data = csPowerPileService
                    .getCsPowerPileList($.add("csppOutlets", outlets.getCsoId()), -1);
            return $.SendHtml($.json(JsonFormat.success().setData($.add("list", data))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    // =============================================================================================================

    /**
     * 获取取车网点
     * 
     * @return
     */
    public String getTakeOutlets() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("101", "用户未绑定企业用户");
            CsUnitInfo unitInfo = person.get$csupInfo();
            if (unitInfo == null)
                return returnError("102", "用户未绑定单位");
            List<CsOutlets> outletsList =
                    CsOutlets.getCsOutletsList($.add(CsOutlets.F.csoCanOrder, 1)
                            .add("definex", "cso_id in ("
                                    + $.str(unitInfo.getCsuiOutlets()).replace("#", "") + ")")
                            .add("asc", "cso_priority"), -1);
            if (CollectionUtils.isEmpty(outletsList)) {
                return returnError("103", "用户单位没有可用网点");
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", outletsList))),
                    CHARSET);

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取还车网点
     * 
     * @return
     */
    public String getRetOutlets() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long takeOutletsId = $.getLong("takeOutletsId");
            if (takeOutletsId == null) {
                return returnError("101", "请选择取车网点");
            }

            CsOutlets outlets = CsOutlets.get(takeOutletsId);
            if (outlets == null) {
                return returnError("102", "无效的取车网点");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("101", "用户未绑定企业用户");
            CsUnitInfo unitInfo = person.get$csupInfo();
            if (unitInfo == null)
                return returnError("102", "用户未绑定单位");
            List<CsOutlets> canUseOutletsList = unitInfo.get$csuiOutlets();
            if (CollectionUtils.isEmpty(canUseOutletsList)) {
                return returnError("103", "用户单位没有可用网点");
            }

            String ids = "";
            for (int i = 0; i < canUseOutletsList.size(); i++) {
                ids += (i == 0 ? "" : ",") + canUseOutletsList.get(i).getCsoId();
            }

            Map<String, Object> params = new HashMap<String, Object>();
            params.put(CsOutlets.F.csoHost, outlets.getCsoHost());
            params.put(CsOutlets.F.csoStatus, "1");
            params.put(CsOutlets.F.csoCanOrder, "1");

            if (outlets.getCsoCanA()) {
                // 可用车位数大于0
                params.put("definex", "(" + CsOutlets.C.csoId + " = " + outlets.getCsoId()
                        + " OR (cso_id in (" + ids + ") AND " + CsOutlets.C.csoPositionS + " - "
                        + CsOutlets.C.csoCarS + " > 0 AND " + CsOutlets.C.csoCanB + " = 1))");
            } else {
                params.put(CsOutlets.F.csoId, takeOutletsId);
            }


            List<CsOutlets> outletsList = csOutletsService.getCsOutletsList(params, -1);
            return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", outletsList))),
                    CHARSET);

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取单位网点可用车辆
     * 
     * @return
     */
    public String getUnitCars() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long takeOutletsId = $.getLong("takeOutletsId");
            Long retOutletsId = $.getLong("retOutletsId");

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("101", "用户未绑定企业用户");
            CsUnitInfo unitInfo = person.get$csupInfo();
            if (unitInfo == null)
                return returnError("102", "用户未绑定单位");
            List<CsOutlets> outletsList = unitInfo.get$csuiOutlets();
            if (CollectionUtils.isEmpty(outletsList))
                return returnError("103", "用户单位没有可用网点");

            if (takeOutletsId == null || takeOutletsId == 0L)
                return returnError("103", "请先选择取车网点");

            // 套餐ID
            CsItem item=null;
            Long mealId = $.getLong("mealId", 0L);
            Page<CsCar> page = new Page<CsCar>(1, 5, 0, new ArrayList<CsCar>());
            if (mealId > 0) {

                Date takeTime = $.getDate("takeTime");
                Date retTime = $.getDate("retTime");

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Map<String, Object> params = $.Map().add("cscStatus", 1);
                StringBuffer definex = new StringBuffer();

                 item = CsItem.get(mealId);
                if (item == null) {
                    return returnError("104", "套餐无效");
                }

                if (takeTime.after(new Date())) {
                    List<CsOrder> orderList =
                            MealHelper.calcMealOrders(item.getCsiDepict(), takeTime);
                    // ==============================================
                    definex.append("(");
                    definex.append("not exists(");
                    definex.append("select 1 from cs_order co where co.cso_car = csc_id and ((");
                    for (int i = 0; i < orderList.size(); i++) {
                        CsOrder order = orderList.get(i);
                        String startTime = fmt.format(order.getCsoStartTime());
                        String finishTime = fmt.format(order.getCsoFinishTime());
                        definex.append("           ( cso_start_time <= '" + startTime
                                + "' and cso_finish_time > '" + startTime + "')");
                        definex.append("        or (cso_start_time > '" + startTime
                                + "' and cso_start_time <'" + finishTime + "' )");
                        if (i != orderList.size() - 1)
                            definex.append(" or ");
                    }
                    definex.append("        ) and cso_status in (0,1,5))");
                    definex.append(")");

                    definex.append(" and csc_outlets = " + takeOutletsId);
                    definex.append(" AND csc_status IN(1,3) )");
                    // ==============================================

                    params.put("definex", definex);
                    params.put("join", " left join cs_state o on csc_id = o.css_car ");
                    params.put("desc", " CAST(o.css_endurance AS SIGNED) ");
                    page = csCarService.getCsCarPage($.getInteger("page", 0), 5, params);
                }
            } else {
                // 兼容旧的方式(不选择取车/还车网点)
                boolean a2bModel = true; // 是否异地还车
                if (retOutletsId == null) {
                    retOutletsId = takeOutletsId;
                    a2bModel = false;
                } else if (takeOutletsId.equals(retOutletsId)) {
                    a2bModel = false;
                }

                Date takeTime = $.getDate("takeTime");
                Date retTime = $.getDate("retTime");

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String startTime = fmt.format(takeTime);
                String finishTime = fmt.format(retTime);

                Map<String, Object> params = $.Map().add("cscStatus", 1);
                StringBuffer definex = new StringBuffer();

                // ==============================================
                definex.append("(");
                definex.append("not exists(");
                definex.append("select 1 from cs_order co where co.cso_car = csc_id and ((");
                definex.append("           ( cso_start_time <= '" + startTime
                        + "' and cso_finish_time > '" + startTime + "')");
                definex.append("        or (cso_start_time > '" + startTime
                        + "' and cso_start_time <'" + finishTime + "' )");
                if (a2bModel) {
                    definex.append("or (cso_start_time > '" + startTime + "' )");
                }
                definex.append("        ) and cso_status in (0,1,5))");
                definex.append(")");

                definex.append(" and csc_outlets = " + takeOutletsId);
                definex.append(" AND csc_status IN(1,3) )");
                // ==============================================

                params.put("definex", definex);
                params.put("join", " left join cs_state o on csc_id = o.css_car ");
                params.put("desc", " CAST(o.css_endurance AS SIGNED) ");
                page = csCarService.getCsCarPage($.getInteger("page", 0), 5, params);
            }

            CarScript.loadCarFeatures(page);

            // 读取时间线
            Integer days = 7;
            java.text.SimpleDateFormat formatter =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
            calendar.set(java.util.Calendar.MINUTE, 59);
            calendar.set(java.util.Calendar.SECOND, 59);
            calendar.add(java.util.Calendar.DAY_OF_MONTH, (days == null || days == 0) ? 1 : days);
            String dt_end = formatter.format(calendar.getTime());
            TimelineScript.readTimeLine(page, null, dt_end);

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            for (CsCar car : page.getResult()) {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("id", car.getCscId());
                data.put("number", car.getCscNumber());
                data.put("color", car.getCscColor());
                data.put("carno", car.getCscCarNo());
                data.put("images", car.getCscImages());

                data.put("outletsId", car.get$cscOutlets().getCsoId());
                data.put("outletsName", car.get$cscOutlets().getCsoName());
                data.put("outletsLatitude", car.get$cscOutlets().getCsoLatitude());
                data.put("outletsLongitude", car.get$cscOutlets().getCsoLongitude());
                //
                //套餐价格
                if(item!=null) {
                	data.put("mealPrice", item.getCsiPrice());
                }
                //
                
                String carImg = car.getCscPreview();
                if (carImg == null || carImg.equals("")) {
                    CsCarModel carmodel = car.get$cscModel();
                    if (carmodel != null)
                        carImg = carmodel.getCscmImage();
                }
                data.put("previewimg", carImg);

                data.put("endurance", car.getValues().get("endurance"));
                data.put("speed", car.getValues().get("speed"));
                data.put("power", car.getValues().get("power"));
                data.put("ischarging", car.getValues().get("ischarging"));
                data.put("evBattery", car.getValues().get("evBattery"));
                data.put("oil", car.getValues().get("oil"));

                data.put("orders", car.getValues().get("orders"));
                data.put("restrs", car.getValues().get("restrs"));

                // 加载车型数据
                Map<String, Object> carmodel = new HashMap<String, Object>();
                CsCarModel cm = car.get$cscModel();
                carmodel.put("id", cm.getCscmId()); // 车型ID
                carmodel.put("name", cm.getCscmName()); // 车型名称
                carmodel.put("image", cm.getCscmImage()); // 车型图片
                carmodel.put("type", cm.getCscmType()); // 车型
                carmodel.put("seatCount", cm.getCscmSeatS()); // 座位数
                carmodel.put("fileType", cm.getCscmFile()); // 档位类型
                carmodel.put("volume", cm.getCscmVolume()); // 排量
                data.put("carmodel", carmodel);

                //天租金
                data.put("feePerDay", car.getValues().get("dayPrice"));
                //分钟租金
                data.put("feePerMin", car.getValues().get("feePerMin"));
        		//
                dataList.add(data);
            }
            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取分时租赁需求
     * 
     * @return
     */
    public String getUnitUseOptions() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            LzMap data = $.Map("1001", "机要任务");
            data.add("1002", "应急勤务");
            data.add("1003", "文件交换");
            data.add("1004", "跨区交通");
            return $.SendHtml($.json(JsonFormat.success().setData($.$("map", data))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 提交分时租赁订单
     * 
     * @return
     */
    public String submitUnitOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            if(MemberRecStatus.REC_PASS != member.getVstatus()) {
                StringBuilder sb = new StringBuilder();
                if(MemberRecStatus.REC_PASS != member.getVDrive()) {
                    sb.append("驾驶证认证未通过,");
                }
                if(MemberRecStatus.REC_PASS != member.getVReal()) {
                    sb.append("实名认证未通过,");
                }
                if(MemberRecStatus.REC_PASS != member.getVWork()) {
                    sb.append("工作认证未通过,");
                }
                if(MemberRecStatus.REC_PASS != member.getVOffline()) {
                    sb.append("线下认证未通过,");
                }
                sb.append("不能下单");
                return returnError("110", sb.toString());
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            String ret = this.appVersionLogin(request);
            if(null != ret) {
                return ret;  
            }
            String carId = $.getString("carId");
            Date takeTime = $.getDate("takeTime");
            Date retTime = $.getDate("retTime");
            String profile = $.getString("profile");
            Short type = $.getShort("type"); // 1:分时租赁 2:紧急公务

            Long takeOutletsId = $.getLong("takeOutletsId");
            Long retOutletsId = $.getLong("retOutletsId");

            if (StringUtils.isEmpty(carId)) {
                return returnError("101", "没有选择车辆");
            }
            if (takeTime == null) {
                return returnError("102", "请选择预定开始时间");
            }
            if (retTime == null) {
                return returnError("103", "请选择预定结束时间");
            }
            if (!retTime.after(takeTime)) {
                return returnError("104", "结束时间必须晚于开始时间");
            }
            
            if (takeOutletsId.longValue() != retOutletsId.longValue()) {
                if (takeTime.getTime() - System.currentTimeMillis() > 120 * 60 * 1000) {
                    return returnError("108", "异地借还只能提前2小时内预定");
                }
            }
            if(takeTime.getTime()-new Date().getTime()<30*60*1000){
            	 return returnError("105", "取车时间需提前30分钟预订车辆");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("106", "用户未绑定企业用户");

            // 找到自己所在的组，给组长发消息用
            CsUnitGroup group = CsUnitGroup.getCsUnitGroupById(person.getCsupGroup());
            if (group == null)
                return returnError("107", "用户未绑定分组");
           //
            CsCar car = csCarService.getCsCarById(Long.valueOf(carId));
            if (car == null) {
            	return returnError("101", "没有选择车辆");
            }
            //
            if (car.getCscStatus() == (short) 0||car.getCscStatus() == (short) 2) {
            	return returnError("109", "车辆已下线，请更换车辆");
            }
            
            CsUnitPerson auditPerson = CsUnitPerson.get(group.getCsugPerson());

            // CsUnitOrder unitOrder =
            // commonUnitService.executeAddOfficialOrder(person.getCsupId(),
            // URLDecoder.decode(profile,"UTF-8"), Long.valueOf(carId),
            // takeTime, retTime, type);
            CsUnitOrder unitOrder = commonUnitService.executeAddUnitOrder(person.getCsupInfo(),
                    person.getCsupId(), member.getCsmId(), member.getCsmName(),
                    member.getCsmMobile(), URLDecoder.decode(profile, "UTF-8"),
                    member.get$csmInfo().getCsmiCertifyNum(), Long.valueOf(carId), takeTime,
                    retTime, type, null, null, null, takeOutletsId, retOutletsId);
            /*
             * Map<String, Object> price_Estimate = new HashMap<String, Object>(); Double
             * fee_total_estimate = 0d; if(unitOrder.get$csuoSpecial().getCsscThird() == 1){
             * price_Estimate = DidiApi.getEstimateOrderPrice(unitOrder.get$csuoSpecial());
             * fee_total_estimate = Double.parseDouble(price_Estimate.get("price").toString());
             * }else if(unitOrder.get$csuoSpecial().getCsscThird() == 2){ price_Estimate =
             * YidaoApi.getOrderEstimatedPrice(unitOrder.get$csuoSpecial()); fee_total_estimate =
             * Double.parseDouble(price_Estimate.get("total_fee").toString()); }
             * unitOrder.setCsuoPayNeed(fee_total_estimate); unitOrder.update();
             */
            try {
                Map<String, String> extra = new HashMap<String, String>();
                extra.put("flag", "order-audit");
                extra.put("data", $.json($.add("unitOrderId", unitOrder.getCsuoId())));

                SimpleDateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
                if (unitOrder.getCsuoState() != 2)
                    JpushClientHelper.sendAlertWithTag(auditPerson.getCsupMember().toString(),
                            "待办提醒",
                            "您有一笔" + ApiBase.RENT_CAR + "订单申请待审批" + formatter.format(new Date()),
                            extra, JpushConfig.OFC_APP);

                // 如果是紧急公务，给审核人发送短信
                if (type.intValue() == 2) {
                    UtilHelper.sendTemplateSMS(person.getCsupHost(), "CALLCAR_AUDIT_NOTICE",
                            auditPerson.get$csupMember().getCsmMobile(),
                            "您有一笔" + ApiBase.RENT_CAR + "订单(订单号:{ORDER_ID})申请等待审批，请及时处理。",
                            SMSType.通知类短信, $.add("ORDER_ID", unitOrder.getCsuoOrder() + ""));
                }
            } catch (Exception e) {
                Logger.getRootLogger().error(e.getMessage(), e);
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.add("unitOrderId", ""))),
                    CHARSET);
        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("104", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    // =============================================================================================================
    /**
     * 获取个人信息
     * 
     * @return
     */
    public String getMemberInfo() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            // if (person == null)
            // return returnError("101", "用户未绑定企业用户");
            CsUnitInfo unitInfo = null;
            CsUnitGroup group = null;
            if (person != null) {
                unitInfo = person.get$csupInfo();
                if (person.getCsupGroup() != null) {
                    group = CsUnitGroup.getCsUnitGroup(
                            $.add("csugId", person.getCsupGroup()).add("csugStatus", 1));
                }
            }

            int checkflag;
            if (group != null)
                checkflag = 1;
            else
                checkflag = 0;

            Double remain =
                    commonMoneyService.getCoin(member.getCsmHost(), member.getCsmId(), new Date());

            CsCreditCard csCreditCard = csCreditCardService
                    .getCsCreditCard($.add(CsCreditCard.F.csccMember, member.getCsmId())
                            .add(CsCreditCard.F.csccType, 1));

            CsEvCard csEvCard = member.get$csmEvcard();
            CsMemberInfo memberInfo = member.get$csmInfo();

            LzMap data = new LzMap();
            data.put("id", member.getCsmId());
            data.put("host", member.getCsmHost());
            data.put("cityname", member.get$csmHost().getShName());
            data.put("name", member.getCsmName());
            data.put("headerImg", member.getCsmHeader());
            data.put("mobile", member.getCsmMobile());
            data.put("money", member.getCsmMoney());
            data.put("vdrive", member.getCsmVDrive());// 驾驶证认证
            data.put("vemail", member.getCsmVEmail());
            data.put("vreal", member.getCsmVReal());// 身份证认证
            data.put("vmobile", member.getCsmVMobile());
            data.put("vstatus", member.getVstatus());// 认证状态
            data.put("vwork", member.getCsmVWork());// 工作认证
            data.put("voffline", member.getCsmVOffline());// 线下认证

            data.put("coinRemain", remain);
            data.put("checkflag", checkflag);
            // 设置企业用户信息
            if (person != null) {
                data.put("personId", person.getCsupId());
                data.put("memberNum", person.getCsupName());
                data.put("csupflag",
                        (person.getCsupFlag() != null && person.getCsupFlag().indexOf("1") > 0) ? 1
                                : 0);
                data.put("isapproved",
                        (person.getCsupFlag() != null && person.getCsupFlag().indexOf("3") > 0) ? 1
                                : 0);
                data.put("rentflag", person.getCsupFlag());
            }
            // memberInfo企业部门信息优先设置
            if (memberInfo != null) {
                data.put("unitName", memberInfo.getCsmiCompany$());
                data.put("deptName", memberInfo.getCsmiDepartment$());
            }
            // 设置企业信息
            if (unitInfo != null && member.getCsmVWork() == 1) {
                data.put("unitInfoId", unitInfo.getCsuiId());
                data.put("unitName", unitInfo.getCsuiName$());
            }
            // 设置企业部门信息
            if (group != null && member.getCsmVWork() == 1) {
                data.put("deptId", group.getCsugId());
                data.put("deptName", group.getCsugName$());
            }

            if (csCreditCard == null) {// 信用卡
                data.put("cardNo", "");
                data.put("cardImage", "");
                data.put("cardStatus", "0");
            } else {
                data.put("cardStatus", csCreditCard.getCsccStatus());
                data.put("cardNo", csCreditCard.getCsccNo$());
                data.put("cardImage", csCreditCard.getCsccImage$());
            }
            if (memberInfo != null) {
                data.put("certifyType", memberInfo.getCsmiCertifyType());
                data.put("certifyNum", StringUtils.trimToEmpty(memberInfo.getCsmiCertifyNum()));
                data.put("certifyImage", memberInfo.getCsmiCertifyImage());
                data.put("driverNum", memberInfo.getCsmiDriverNum());
                data.put("driverImage", memberInfo.getCsmiDriverImage());
                data.put("onCertifyImage", memberInfo.getCsmiOnCertifyImage());
                data.put("sex", memberInfo.getCsmiSex());
                data.put("address", memberInfo.getCsmiAddress());
                data.put("company", memberInfo.getCsmiCompany());
                data.put("person", memberInfo.getCsmiPerson());
                data.put("contact", memberInfo.getCsmiContact());
                data.put("relation", memberInfo.getCsmiRelation());
                data.put("status", memberInfo.getCsmiStatus());
                // 工作证照片
                data.put("workImage", memberInfo.getCsmiProofOfEmployment());
            } else {
                data.put("certifyType", null);
                data.put("certifyNum", null);
                data.put("certifyImage", null);
                data.put("driverNum", null);
                data.put("driverImage", null);
                data.put("onCertifyImage", null);
                data.put("sex", null);
                data.put("address", null);
                data.put("company", null);
                data.put("person", null);
                data.put("contact", null);
                data.put("relation", null);
                data.put("status", null);
                // 工作证照片
                data.put("workImage", null);
            }
            data.put("isRefunding", commonMoneyService.isRefunding(member.getCsmId()));// 正在退款
            if (csEvCard != null) {
                data.put("evcard", csEvCard.getCsecNumber$());
            } else {
                data.put("evcard", member.getCsmEvcard());
            }
            data.put("coupon", member.getCsmCoupon());
            return $.SendHtml($.json(JsonFormat.success().setData($.$("map", data))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取认证状态
     * 
     * @return
     */
    public String getAuthStatus() {
        CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }
        Map<String, Object> data = new HashMap<>();
        data.put("id", member.getCsmId());
        data.put("vreal", member.getVReal());
        data.put("vdrive", member.getVDrive());
        data.put("vwork", member.getVWork());
        data.put("voffline", member.getVOffline());
        return $.SendHtml($.json(JsonFormat.success().setData($.$("map", data))), CHARSET);
    }

    /**
     * 获取用户可用余额
     * 
     * @return
     */
    public String getUserAmount() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
            df.setMaximumFractionDigits(2);

            // 可用余额
            Double canUseMoney =
                    $.or(commonMoneyService.getUsableAmount(member.getCsmId()), 0).doubleValue();

            // 充值余额
            Double remainCharge = $.or(member.getCsmMoney(), 0).doubleValue();

            // 现金券余额
            Double remainCoupon = $.or(member.getCsmCoupon(), 0).doubleValue();

            // 冻结资金
            Double freezeMoney =
                    remainCharge + $.or(member.getCsmCoupon(), 0).doubleValue() - canUseMoney;

            // 当前积分
            Double integral = $.or(member.getCsmIntegral(), 0).doubleValue();

            // 红包
            Map<String, String> hostmap = new HashMap<String, String>();
            hostmap.put("1", "杭州");
            hostmap.put("2", "北京");
            List<Map<String, Object>> coinList = new ArrayList<Map<String, Object>>();
            for (String host : hostmap.keySet()) {
                Double coin = commonMoneyService.getCoin(Long.valueOf(host), member.getCsmId(),
                        Calendar.getInstance().getTime());
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("host", host);
                data.put("name", hostmap.get(host));
                data.put("coin", df.format($.or(coin, 0).doubleValue()));
                coinList.add(data);
            }

            // 可用免费小时
            Double canUseFreeHour =
                    commonMoneyService.getCanUseFreeHour(member.getCsmId(), new Date());

            Map<String, Object> datamap = new HashMap<String, Object>();

            datamap.put("canUseMoney", df.format(canUseMoney));
            datamap.put("remainCharge", df.format(remainCharge));
            datamap.put("remainCoupon", df.format(remainCoupon));
            datamap.put("freezeMoney", df.format(freezeMoney));
            datamap.put("integral", df.format(integral));
            datamap.put("canUseFreeHour", canUseFreeHour);
            datamap.put("coinList", coinList);

            return $.SendHtml($.json(JsonFormat.success().setData($.$("map", datamap))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 申请退款
     * 
     * @return
     */
    public String applyRefund() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            if (commonMoneyService.haveUnFinishOrder(member.getCsmId())) {
                return returnError("104", "您有未完成的订单，不能完成退款");
            }
            if (commonMoneyService.isRefunding(member.getCsmId())) {
                return returnError("105", "您的退款正在处理中");
            }

            // 可用余额0元不给退款
            Double canUseMoney =
                    $.or(commonMoneyService.getUsableAmount(member.getCsmId()), 0).doubleValue();
            if (canUseMoney <= 0 || (member.getCsmMoney() != null && member.getCsmMoney() <= 0)) {
                return returnError("106", "您没有可用的退款余额");
            }

            CsRefund csRefundResult = null;
            List<CsOrder> csOrderList =
                    csOrderService.getCsOrderList(
                            $.add(CsOrder.F.csoPayMember, member.getCsmId())
                                    .add(CsOrder.F.csoStatus, 4).add("desc", CsOrder.C.csoRetTime),
                            -1);
            // 没有定单
            if (csOrderList.size() == 0) {
                // 没有用车记录，3个工作日退款
                csRefundResult = doRefund(member);
                return $.SendHtml($.json(
                        JsonFormat.success().setData($.add("map", putRefund(csRefundResult)))),
                        CHARSET);
            } else {
                if (csOrderList.get(0).getCsoRetTime() != null) {
                    Date now = new Date();
                    if (now.after(WidgetHelper.getWorkDay(7, csOrderList.get(0).getCsoRetTime()))) {
                        // 7个工作日没用车了，相当于没有车记录，3个工作日退款
                        csRefundResult = doRefund(member);
                        return $.SendHtml(
                                $.json(JsonFormat.success()
                                        .setData($.add("map", putRefund(csRefundResult)))),
                                CHARSET);
                    } else {
                        CsRefund newCsRefund = new CsRefund();
                        newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d,
                                "如果不能自动退款，请联系会员确认退款帐号", member.getCsmName(), member.getCsmMobile(),
                                new Date(), WidgetHelper.getWorkDay(1),
                                WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime()),
                                WidgetHelper.getWorkDay(9, csOrderList.get(0).getCsoRetTime()),
                                (short) 0);
                        newCsRefund.csrMobile(member.getCsmMobile());
                        csRefundResult = csRefundService.saveCsRefund(newCsRefund);
                        return $.SendHtml(
                                $.json(JsonFormat.success()
                                        .setData($.add("map", putRefund(csRefundResult)))),
                                CHARSET);
                    }
                } else {
                    // 没有车记录，3个工作日退款
                    csRefundResult = doRefund(member);
                    return $.SendHtml($.json(
                            JsonFormat.success().setData($.add("map", putRefund(csRefundResult)))),
                            CHARSET);
                }
            }
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    private LzMap putRefund(CsRefund csRefund) {
        LzMap result = new LzMap();
        if (csRefund != null) {
            result.put("refundId", csRefund.getCsrId());
            result.put("amount", csRefund.getCsrAmount());
            result.put("addTime", csRefund.getCsrAddTime());
            result.put("auditTime", csRefund.getCsrCheckTime());
            result.put("backTime", csRefund.getCsrBackTime());
            result.put("finishTime", csRefund.getCsrEndTime());
            result.put("status", csRefund.getCsrStatus());
        }
        return result;
    }

    /**
     * 退款
     * 
     * @param member
     */
    private CsRefund doRefund(CsMember member) {
        CsRefund newCsRefund = new CsRefund();
        newCsRefund.setNotNull(member.getCsmHost(), member.getCsmId(), 0d, "如果不能自动退款，请联系会员确认退款帐号",
                member.getCsmName(), member.getCsmMobile(), new Date(), WidgetHelper.getWorkDay(1),
                WidgetHelper.getWorkDay(4), WidgetHelper.getWorkDay(4), (short) 0);
        newCsRefund.csrMobile(member.getCsmMobile());
        return csRefundService.saveCsRefund(newCsRefund);
    }

    /**
     * 取消申请退款
     * 
     * @return
     */
    public String cancelRefund() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            if (!commonMoneyService.isRefunding(member.getCsmId())) {
                return returnError("102", "没有可取消的退款");
            }

            final CsRefund csRefund = csRefundService.getCsRefund(
                    $.add("csrMember", member.getCsmId()).add("definex", "csr_status<1"));
            if (csRefund == null)
                return returnError("102", "退款正在处理中");

            csRefundService.updateCsRefund$NotNull(
                    new CsRefund().csrId(csRefund.getCsrId()).csrStatus((short) 4));

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 保存个人基本信息
     * 
     * @return
     */
    public String saveBasicInfo() {
        final CsMember csMember = OauthUtils.getOauth($.getString("access_token", ""));
        if (csMember == null) {
            return returnError("100", "登录授权无效");
        }

        final String userName = $.getString("userName");
        final String userHeadImgUrl = $.getString("userHeadImgUrl");
        final Short gender = $.getShort("gender");
        final String email = $.getString("email");

        if (userName == null && userHeadImgUrl == null && gender == null && email == null) {
            return returnError("102", "参数缺失");
        }

        try {
            // 用户名已存在
            if (userName != null) {
                CsMember tmp = csMemberService.getCsMember($.add(CsMember.F.csmUsername, userName));
                if (tmp != null && !tmp.getCsmId().equals(csMember.getCsmId())) {
                    return returnError("103", "用户名已存在");
                }
            }

            final Long memberId = csMember.getCsmId();
            final Long hostId = csMember.getCsmHost();

            csMemberInfoService.executeTransaction(new Function() {
                @Override
                public <T> T execute(Object... arg0) {

                    // 更新会员的扩展信息
                    CsMemberInfo csMemberInfo = csMemberInfoService
                            .getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, memberId));
                    if (csMemberInfo == null) {
                        csMemberInfo = new CsMemberInfo();
                        csMemberInfo.setNotNull(hostId, memberId, new Date(), new Date(),
                                (short) 1);
                        csMemberInfo.setCsmiMemberId(memberId);
                        csMemberInfo.setCsmiSex(gender);
                        CsMemberInfo memberInfoTemp =
                                csMemberInfoService.saveCsMemberInfo(csMemberInfo);
                        csMember.setCsmInfo(memberInfoTemp.getCsmiId());
                    } else {
                        csMemberInfo.setCsmiSex(gender);
                        csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);
                        if (csMember.getCsmInfo() == null) {
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
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 保存个人驾照信息
     * 
     * @return
     */
    public String saveDriverInfo() {
        final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }

        final String realName = $.getString("realName");
        final Short certifyType = $.getShort("certifyType");
        final String certifyNubmer = $.getString("certifyNubmer");
        final String certifyImageUrl = $.getString("certifyImageUrl");
        final String driverImageUrl = $.getString("driverImageUrl");
        final String driverNum = $.getString("driverNum");

        if (realName == null && certifyType == null && certifyNubmer == null
                && certifyImageUrl == null && driverNum == null && driverImageUrl == null) {
            return returnError("102", "参数缺失");
        }

        try {
            if (driverNum != null && !driverNum.equals("")) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(CsMemberInfo.F.csmiDriverNum, driverNum);
                CsMemberInfo existMemberInfo = csMemberInfoService.getCsMemberInfo(params);
                // 驾驶证号码已存在
                if (existMemberInfo != null
                        && !existMemberInfo.getCsmiMemberId().equals(member.getCsmId()))
                    return returnError("104", "驾驶证号码已存在");
            }

            final Long memberId = member.getCsmId();
            final Long hostId = member.getCsmHost();

            csMemberInfoService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... arg0) {
                    // 更新会员的扩展信息
                    CsMemberInfo csMemberInfo = csMemberInfoService
                            .getCsMemberInfo($.add(CsMemberInfo.F.csmiMemberId, memberId));
                    if (csMemberInfo == null) {
                        csMemberInfo = new CsMemberInfo();
                        csMemberInfo.setNotNull(hostId, memberId, new Date(), new Date(),
                                (short) 1);
                        csMemberInfo.setCsmiMemberId(memberId);
                        csMemberInfo = csMemberInfoService.saveCsMemberInfo(csMemberInfo);
                    }

                    CsMemberInfo oldMemberInfo =
                            csMemberInfoService.getCsMemberInfoById(csMemberInfo.getCsmiId());
                    // 设置扩展信息：关联的会员帐号，驾照照片路径，驾照号码，真实姓名，更新时间
                    csMemberInfo.csmiMemberId(memberId).csmiCertifyImage(certifyImageUrl)
                            .csmiDriverImage(driverImageUrl).csmiDriverNum(driverNum)
                            .csmiName(realName).csmiUpdateTime(new Date());

                    csMemberInfo.setCsmiCertifyNum(certifyNubmer);
                    csMemberInfo.setCsmiCertifyType(certifyType);

                    Date birthday = SystemHelper.getBirthday(driverNum);
                    if (birthday != null) {
                        csMemberInfo.csmiBirthday(birthday);
                    }
                    csMemberInfoService.updateCsMemberInfo$NotNull(csMemberInfo);

                    CsMember csMember = new CsMember().csmId(memberId)
                            .csmInfo(csMemberInfo.getCsmiId()).csmUpdateTime(new Date());
                    // 比较修改的内容，决定会员的审核状态,如果任意其中一个被修改过，则更新其对应的状态
                    if (!$.equals(realName, oldMemberInfo.getCsmiName()))
                        csMember.setCsmVReal((short) 2);
                    if (!$.equals(driverImageUrl, oldMemberInfo.getCsmiDriverImage())
                            || !$.equals(certifyImageUrl, oldMemberInfo.getCsmiCertifyImage())
                            || !$.equals(driverNum, oldMemberInfo.getCsmiDriverNum()))
                        csMember.setCsmVDrive((short) 2);

                    // 真实姓名同步到会员表中
                    csMember.setCsmName(csMemberInfo.getCsmiName());
                    csMemberService.updateCsMember$NotNull(csMember);

                    return null;
                }
            });

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }

    /**
     * 保存联系人信息
     * 
     * @return
     */
    public String saveContact() {

        final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }

        final String postAddress = $.getString("postAddress");
        final String company = $.getString("company");
        final String people = $.getString("people");
        final String peoplePhoneNumber = $.getString("peoplePhoneNumber");
        final String relation = $.getString("relation");

        if (postAddress == null && company == null && people == null && peoplePhoneNumber == null
                && relation == null) {
            return returnError("102", "参数缺失");
        }

        try {

            // 设置扩展信息：关联的会员帐号，邮寄地址，更新时间
            final CsMemberInfo newCsMemberInfo = new CsMemberInfo();
            newCsMemberInfo.csmiAddress(postAddress).csmiUpdateTime(new Date());
            newCsMemberInfo.setCsmiCompany(company);
            newCsMemberInfo.setCsmiPerson(people);
            newCsMemberInfo.setCsmiContact(peoplePhoneNumber);
            newCsMemberInfo.setCsmiRelation(relation);
            newCsMemberInfo.setCsmiId(member.getCsmInfo());

            csMemberInfoService.executeTransaction(new Function() {
                @Override
                public <T> T execute(Object... arg0) {

                    // 更新会员的扩展信息
                    if (member.getCsmInfo() != null && member.getCsmInfo() > 0) {
                        newCsMemberInfo.setNotNull(member.getCsmHost(), member.getCsmId(),
                                new Date(), new Date(), (short) 1);
                        csMemberInfoService.updateCsMemberInfo$NotNull(newCsMemberInfo);
                    } else {
                        newCsMemberInfo.setNotNull(member.getCsmHost(), member.getCsmId(),
                                new Date(), new Date(), (short) 1);
                        CsMemberInfo csMemberInfoTmp =
                                csMemberInfoService.saveCsMemberInfo(newCsMemberInfo);

                        member.setCsmInfo(csMemberInfoTmp.getCsmiId());
                        member.update();
                    }
                    return null;
                }
            });
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);

        } catch (Exception e) {
            return returnError(e);
        }
    }

    /**
     * 自助绑卡
     * 
     * @return
     */
    public String bindEvCard() {

        final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }

        final String cardNumber = $.getString("cardNumber");
        if (StringUtils.isEmpty(cardNumber)) {
            return returnError("101", "卡号不能为空");
        }

        CsEvCard evCard = csEvCardService.getCsEvCard($.add(CsEvCard.F.csecNumber, cardNumber));
        if (evCard == null) {
            return returnError("102", "卡号无效");
        }

        try {
            member.setCsmEvcard(evCard.getCsecId());
            member.update();
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }

    // =============================================================================================================
    /**
     * 获取审核信息列表 待审批 续订审批 审批通过 审批未通过
     * 
     * @return
     */
    public String getAuditInfo() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            // 审批
            String auditStatus = $.getString("auditStatus");

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null) {
                return returnError("101", "会员不是企业账号");
            }

            // 找到（自己是审核人的那个）组
            CsUnitGroup csupg = csUnitGroupService
                    .getCsUnitGroup($.add(CsUnitGroup.F.csugPerson, person.getCsupId()));
            if (csupg == null) {
                return returnError("102", "会员没有分配所属组");
            }

            Page<CsUnitOrder> page = csUnitOrderService.getCsUnitOrderPage($.getInteger("page", 0),
                    5,
                    $.add(CsUnitOrder.F.csuoUnitGroup, csupg.getCsugId())
                            .add("definex",
                                    "(" + CsUnitOrder.C.csuoSpecial + " is null or "
                                            + CsUnitOrder.C.csuoSpecial + "=0 )")
                            .add(CsUnitOrder.F.csuoState,
                                    auditStatus == null ? null : Short.valueOf(auditStatus)));

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

            for (CsUnitOrder data : page.getResult()) {
                Map<String, Object> datamap = getUnitOrderMap(data);

                CsOutlets outlets = null;
                CsOrder order = data.get$csuoOrder();
                if (order != null)
                    outlets = order.get$csoOutlets();

                if (outlets != null) {
                    datamap.put("startOutletsName", outlets.getCsoName());
                } else {
                    datamap.put("startOutletsName", "");
                }

                if (data.getCsuoSpecial() == null || data.getCsuoSpecial() <= 0) {
                    datamap.put("carmodelImage", "");
                    datamap.put("carmodelName", "");
                } else {
                    CsCar car = CsCar.get(data.getCsuoCar());
                    CsCarModel model = null;
                    if (car != null) {
                        model = CsCarModel.get(car.getCscModel());
                    }
                    if (model != null) {
                        datamap.put("carmodelImage", model.getCscmImage());
                        datamap.put("carmodelName", model.getCscmName());
                    } else {
                        datamap.put("carmodelImage", "");
                        datamap.put("carmodelName", "");
                    }
                }

                dataList.add(datamap);
            }

            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());

            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取会员可用订单列表 -远程控制列表
     * 
     * @return
     */
    public String getPersonOrders() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null) {
                return returnError("101", "会员不是企业账号");
            }

            // 查询出用户“等待审核、审核通过”的企业订单
            List<CsUnitOrder> allResult = csUnitOrderService
                    .getCsUnitOrderList($.add(CsUnitOrder.F.csuoPerson, person.getCsupId())
                            .add("definex", "(csuo_state in (1,2))"), -1);

            Map<Long, CsUnitOrder> unitOrdersmap = new HashMap<Long, CsUnitOrder>();
            for (int i = 0; i < allResult.size(); i++) {
                unitOrdersmap.put(allResult.get(i).getCsuoOrder(), allResult.get(i));
            }

            // 查询出用户“可远程控制”的订单
            List<CsOrder> orderList =
                    csOrderService.getCsOrderList($.add(CsOrder.F.csoUseMember, member.getCsmId())
                            .add("definex", "(cso_status in (0,1))"), -1);
            List<Map<String, Object>> dataList = assemUserOrders(orderList);
            List<Map<String, Object>> delList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> mapdata : dataList) {
                Long orderId = Long.valueOf(mapdata.get("orderId").toString());
                CsUnitOrder unitOrder = unitOrdersmap.get(orderId);
                if (unitOrder != null) {
                    mapdata.put("state", unitOrder.getCsuoState());
                    mapdata.put("id", unitOrder.getCsuoId());

                    // 如果不是审核通过的企业订单，过滤掉
                    if (unitOrder.getCsuoState().intValue() != 2) {
                        delList.add(mapdata);
                    }
                }
            }
            dataList.removeAll(delList);

            LzMap pagemap = $.$("index", 0).add("total", 0).add("count", 0).add("size", 0);
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 是否叫车订单
     * 
     * @param data
     * @return
     */
    boolean isCallCarOrder(CsUnitOrder data) {
        return data != null && data.getCsuoSpecial() != null
                && data.getCsuoSpecial().intValue() > 0;
    }

    private Map<String, Object> getUnitOrderMap(CsUnitOrder data) {
        Map<String, Object> mapdata = new HashMap<String, Object>();
        mapdata.put("id", data.getCsuoId());
        mapdata.put("host", data.getCsuoHost());
        mapdata.put("payMember", data.getCsuoUnitMember());
        mapdata.put("unitChild", data.getCsuoUnitChild());

        CsCar car = data.get$csuoCar();
        if (car != null) {
            CsCarModel carmodel = car.get$cscModel();
            if (carmodel != null) {
                mapdata.put("car", car.getCscId());
                mapdata.put("carmodel", carmodel.getCscmId());
                mapdata.put("carnumber", car.getCscNumber());
                mapdata.put("carno", car.getCscCarNo());
                mapdata.put("carmodelname", carmodel.getCscmName());
            }
        } else {
            mapdata.put("specialcarmodel", data.get$csuoSpecial().getCsscCarModel());
        }

        mapdata.put("special", data.getCsuoSpecial());
        if (data.getCsuoSpecial() != null && data.getCsuoSpecial() > 0L) {
            CsSpecialCar special = data.get$csuoSpecial();
            // mapdata.put("startAddr", special.getCsscStartAddr());
            // mapdata.put("finishAddr", special.getCsscFinishAddr());

            mapdata.put("csoAddress", special.getCsscStartAddr()); // 地址
            mapdata.put("csoLongitude", null); // 经度
            mapdata.put("csoLatitude", null); // 纬度
            mapdata.put("csoName", ""); // 网点名称
        } else {
            // mapdata.put("startAddr", "");
            // mapdata.put("finishAddr", "");

            mapdata.put("csoName", data.get$csuoCar().get$cscOutlets().getCsoName()); // 网点名称
            mapdata.put("csoAddress", data.get$csuoCar().get$cscOutlets().getCsoAddress()); // 地址
            mapdata.put("csoLongitude", data.get$csuoCar().get$cscOutlets().getCsoLongitude()); // 经度
            mapdata.put("csoLatitude", data.get$csuoCar().get$cscOutlets().getCsoLatitude()); // 纬度
        }

        mapdata.put("authCode", data.get$csuoOrder().getCsoCode()); // 授权码
        mapdata.put("bltName", "HELLO123"); // 蓝牙名称
        mapdata.put("bltMacAddr", "e2556c37364d"); // 蓝牙地址
        mapdata.put("unitName", data.get$csuoUnitGroup().getCsugName());
        mapdata.put("type", data.getCsuoType());
        mapdata.put("person", data.getCsuoPerson());
        mapdata.put("userName", data.getCsuoUsername());
        mapdata.put("alias", data.getCsuoAlias());
        mapdata.put("mobile", data.getCsuoMobile());
        mapdata.put("profile", data.getCsuoProfile());
        mapdata.put("idcard", StringUtils.trimToEmpty(data.getCsuoIdcard()));
        mapdata.put("dayPrice", data.getCsuoDayPrice());
        mapdata.put("hourPrice", data.getCsuoHourPrice());
        mapdata.put("kmPrice", data.getCsuoKmPrice());
        mapdata.put("startTime", data.getCsuoStartTime());
        mapdata.put("finishTime", data.getCsuoFinishTime());
        mapdata.put("retTime", data.getCsuoRetTime());
        mapdata.put("freeHour", data.getCsuoFreeHour());
        mapdata.put("mileage", data.getCsuoMileage());
        mapdata.put("freeKm", data.getCsuoFreeKm());
        mapdata.put("payNeed", data.get$csuoOrder().getCsoPayNeed());
        mapdata.put("payReal", data.get$csuoOrder().getCsoPayReal());
        mapdata.put("remark", data.getCsuoRemark());
        mapdata.put("addTime", data.getCsuoAddTime());
        mapdata.put("updateTime", data.getCsuoUpdateTime());
        mapdata.put("settlement", data.getCsuoSettlement());
        mapdata.put("statement", data.getCsuoStatement());
        mapdata.put("checkTime", data.getCsuoCheckTime());
        mapdata.put("checker", data.getCsuoChecker());
        mapdata.put("state", data.getCsuoState());
        mapdata.put("orderId", data.getCsuoOrder()); // 系统订单id
        mapdata.put("total_fee", data.getCsuoPayNeed());// 预估费用
        // mapdata.put("orderFee", data.get$csuoOrder().getCsoPayRent());// 时租费用
        mapdata.put("orderFee",
                data.get$csuoOrder().getCsoPayReal() + data.get$csuoOrder().getCsoPayCoin()
                        - $.or(data.get$csuoOrder().getCsoPayTimeout(), 0d));
        mapdata.put("timeoutFee", data.get$csuoOrder().getCsoPayTimeout());// 超时费用
        return mapdata;
    }

    /**
     * 获取（公务）订单，不能删除
     * 
     * @return
     */
    @Deprecated
    public String getOrderById() {
        return getUnitOrderById();
    }

    /**
     * 根据id获取公务用车订单（unitorder.id）
     * 
     * @return
     */
    public String getUnitOrderById() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long unitOrderId = $.getLong("orderId");
            if (unitOrderId == null) {
                return returnError("101", "没有该订单");
            }

            // 已下单的订单
            CsUnitOrder unitOrder = csUnitOrderService.getCsUnitOrderById(unitOrderId);
            if (unitOrder == null) {
                return returnError("101", "没有该订单");
            }

            // 参考getUnitOrders返回
            Map<String, Object> map = getUnitOrderMap(unitOrder);
            CsOrder order = unitOrder.get$csuoOrder();
            if (order != null) {
                CsOutlets outletsRet = order.get$csoOutletsRet();
                if (outletsRet != null) {
                    map.put("finishOutletsName", outletsRet.getCsoName());
                    map.put("finishOutletsLat", outletsRet.getCsoLatitude());
                    map.put("finishOutletsLng", outletsRet.getCsoLongitude());
                    map.put("finishOutletsAddr", outletsRet.getCsoAddress());
                }

                CsComplain csComplain = csComplainService.getCsComplain(
                        $.add(CsComplain.F.cscObject, "CsOrder@" + order.getCsoId()));

                map.put("isComment", csComplain == null ? false : true);
                map.put("starLevel", csComplain == null ? 0 : csComplain.getCscLevel());
                map.put("starLevel1", csComplain == null ? 0 : csComplain.getCscLevel1());
                map.put("content", csComplain == null ? " " : csComplain.getCscContent());

                if (order.getCsoStatus() != 0 && order.getCsoStatus() != 1) {
                    map.put("endurance", null);
                } else {
                    CsState state = csStateService.getCsState(
                            $.add(CsState.F.cssNumber, order.get$csoCar().getCscNumber()));
                    if (state != null) {
                        map.put("endurance", state.getCssEndurance());
                    } else {
                        map.put("endurance", null);
                    }
                }
            } else {
                map.put("endurance", null);
            }

            map.put("orderStatus", order == null ? null : order.getCsoStatus());

            return $.SendHtml($.json(JsonFormat.success().setData($.add("map", map))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 根据id获取个人用车订单（order.id）
     * 
     * @return
     */
    public String getPersonalOrderById() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long orderId = $.getLong("orderId");
            if (orderId == null) {
                return returnError("101", "没有该订单");
            }

            // 已下单的订单
            CsOrder order = csOrderService.getCsOrderById(orderId);
            if (order == null) {
                return returnError("101", "没有该订单");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            CsCar car = order.get$csoCar();
            CsCarModel carmodel = car.get$cscModel();
            CsOutlets outlets = order.get$csoOutlets();
            CsOutlets outletsRet = order.get$csoOutletsRet();

            map.put("car", order.getCsoCar());
            map.put("startTime", order.getCsoStartTime());

            String carImg = car.getCscPreview();
            if (carImg == null || carImg.equals("")) {
                carImg = carmodel.getCscmImage();
            }

            map.put("carImg", carImg);
            map.put("carmodel", carmodel.getCscmId());
            map.put("carmodelname", carmodel.getCscmName());
            map.put("carno", car.getCscCarNo());

            map.put("csoAddress", outlets.getCsoAddress());
            map.put("csoName", outlets.getCsoName());
            map.put("csoLatitude", outlets.getCsoLatitude());
            map.put("csoLongitude", outlets.getCsoLongitude());
            map.put("csoFlag", order.getCsoFlag()); // 订单标识

            map.put("finishOutletsName", outletsRet.getCsoName());
            map.put("finishOutletsLat", outletsRet.getCsoLatitude());
            map.put("finishOutletsLng", outletsRet.getCsoLongitude());
            map.put("finishOutletsAddr", outletsRet.getCsoAddress());

            map.put("finishAddr", order.getCsoOutletsRet());
            map.put("finishTime", order.getCsoFinishTime());
            map.put("host", order.getCsoHost());
            map.put("id", null); // offical订单号
            map.put("orderId", order.getCsoId());
            map.put("authCode", order.getCsoCode()); // 授权码
            map.put("bltName", "HELLO123"); // 蓝牙名称
            map.put("bltMacAddr", "e2556c37364d"); // 蓝牙地址
            map.put("payMember", order.getCsoPayMember());
            map.put("person", order.getCsoUseMember());
            map.put("retTime", order.getCsoRetTime());
            map.put("special", 0); // 是否是特急专送
            map.put("startAddr", null); // 特急专送订单出发地
            map.put("state", null); // 审批状态
            map.put("type", null); // 订单类型
            map.put("unitChild", null); // 使用账号
            map.put("userName", member.getCsmName()); // 用车人姓名

            CsState state =
                    csStateService.getCsState($.add(CsState.F.cssNumber, car.getCscNumber()));
            if (state != null) {
                map.put("endurance", state.getCssEndurance());
            } else {
                map.put("endurance", null);
            }

            // 如果是订单簇，返回套餐价格明细
            if (CommonDisposeService.isOrderCluster(order)) {
                CsOrderCluster coc = CsOrderCluster.Get($.add(CsOrderCluster.F.csocId,
                        $.or(order.getCsoSrc(), "").replace("CsOrderCluster@", "")));
                if (coc != null) {
                    map.put("payNeed", order.getCsoPayNeed() + coc.getCsocPayNeed());
                    map.put("payReal",
                            order.getCsoPayReal() + coc.getCsocPayReal() + order.getCsoPayCoin());

                    map.put("orderFee", $.or(order.getCsoPayRent(), 0d));// 租金费用
                    map.put("mealpFee", coc.getCsocPayReal());
                    map.put("timeoutFee", order.getCsoPayTimeout());// 超时费用
                }
            } else {
                map.put("payNeed", order.getCsoPayNeed());
                map.put("payReal", order.getCsoPayReal() + order.getCsoPayCoin());
                map.put("timeoutFee", order.getCsoPayTimeout());// 超时费用
                map.put("orderFee", (order.getCsoPayReal() + order.getCsoPayCoin()
                        - $.or(order.getCsoPayTimeout(), 0d)));
                // map.put("orderFee",(order.getCsoPayReal()+order.getCsoPayCoin()) > 0 ?
                // $.or(order.getCsoPayRent(), 0d) : 0);//租金费用
            }

            CsComplain csComplain = csComplainService
                    .getCsComplain($.add(CsComplain.F.cscObject, "CsOrder@" + order.getCsoId()));

            map.put("isComment", csComplain == null ? false : true);
            map.put("starLevel", csComplain == null ? 0 : csComplain.getCscLevel());
            map.put("starLevel1", csComplain == null ? 0 : csComplain.getCscLevel1());
            map.put("content", csComplain == null ? " " : csComplain.getCscContent());

            map.put("orderStatus", order.getCsoStatus());
            map.put("profile", "");

            return $.SendHtml($.json(JsonFormat.success().setData($.add("map", map))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取会员当前订单
     * 
     * @return
     */
    public String getCurrentOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null) {
                return returnError("101", "会员不是企业账号");
            }

            // 已下单的订单
            CsUnitOrder order = csUnitOrderService.getCsUnitOrder($.add(CsUnitOrder.F.csuoState, 2)
                    .add(CsUnitOrder.F.csuoPerson, person.getCsupId()));
            if (order == null) {
                return returnError("102", "您当前没有可用订单");
            }

            LzMap map = new LzMap();
            map.put("orderId", order.getCsuoOrder());
            map.put("unitOrderId", order.getCsuoId());
            map.put("car", order.getCsuoCar());
            CsCar car = order.get$csuoCar();
            CsCarModel carmodel = car.get$cscModel();
            map.put("carno", car.getCscCarNo());
            map.put("carnumber", car.getCscNumber());
            map.put("carmodelname", carmodel.getCscmName());

            return $.SendHtml($.json(JsonFormat.success().setData($.$("map", map))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 审核提交
     * 
     * @return
     */
    public String submitAudit() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            String orderId = $.getString("orderId");
            Short auditState = $.getShort("auditState");
            if (StringUtils.isEmpty(orderId)) {
                return returnError("101", "订单号无效");
            }

            CsUnitOrder order = csUnitOrderService.getCsUnitOrderById(Long.valueOf(orderId));
            if (order == null) {
                return returnError("101", "订单号无效");
            }

            if (order.getCsuoState().intValue() != 1) {
                return returnError("102", "该订单已被审核过啦");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));

            order.setCsuoCheckTime(new Date());
            order.setCsuoUpdateTime(new Date());
            order.setCsuoChecker("CsUnitPerson@" + unitPerson.getCsupId());
            // order.setCsuoState(auditState);
            order.update();

            try {
                CsUnitPerson useUnitPerson = CsUnitPerson.get(order.getCsuoPerson());

                CsUnitMt mt = new CsUnitMt(member.getCsmHost(), order.getCsuoUnitInfo(),
                        order.getCsuoPerson(), null, null, 1, "CsUnitOrder@" + order.getCsuoId(),
                        null, new Date(), new Date(), Short.valueOf("0"));

                commonUnitService.executeCheckUnitOrder(auditState.intValue() == 2 ? true : false,
                        Long.valueOf(orderId), unitPerson.getCsupInfo(), "");

                // CsSpecialCar specialCar =
                // CsSpecialCar.Get($.add(CsSpecialCar.F.csscUnitOrder,
                // orderId));
                // if(specialCar !=null){
                // callCar(specialCar);
                // }else{
                // Logger.getRootLogger().error("特急专送订单"+orderId+"没有找到叫车信息！");
                // }

                if (auditState == 2) { // 通过
                    if (isCallCarOrder(order)) {
                        mt.setCsumTitle(ApiBase.CALL_CAR + "订单审核通过");
                        mt.setCsumContent("您的" + ApiBase.CALL_CAR + "订单审批通过。");
                    } else {
                        mt.setCsumTitle(ApiBase.RENT_CAR + "订单审核通过");
                        mt.setCsumContent(
                                "您的" + ApiBase.RENT_CAR + "订单" + order.getCsuoOrder() + "审批通过。");
                    }
                } else if (auditState == 3) {// 不通过
                    if (isCallCarOrder(order)) {
                        mt.setCsumTitle(ApiBase.CALL_CAR + "订单审核未通过");
                        mt.setCsumContent("您的" + ApiBase.CALL_CAR + "订单审批未通过。如需使用，请重新申请。");
                    } else {
                        mt.setCsumTitle(ApiBase.RENT_CAR + "订单审核未通过");
                        mt.setCsumContent("您的" + ApiBase.RENT_CAR + "订单" + order.getCsuoOrder()
                                + "审批未通过。如需使用，请重新申请。");
                    }

                }
                mt = mt.save();

                try {
                    Map<String, String> extra = new HashMap<String, String>();
                    extra.put("flag", "order-message");
                    extra.put("data", $.json($.add("messageId", mt.getCsumId()).add("unitOrderId",
                            order.getCsuoId())));
                    JpushClientHelper.sendAlertWithTag(useUnitPerson.getCsupMember().toString(),
                            mt.getCsumTitle(), mt.getCsumContent(), extra, JpushConfig.OFC_APP);
                } catch (Exception e) {
                    Logger.getRootLogger().error(e.getMessage(), e);
                }
            } catch (Exception e) {
                Logger.getRootLogger().error(e.getMessage(), e);
            }

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    // =============================================================================================================
    /**
     * 获取会员公务车订单列表
     * 
     * @return
     */
    public String getUnitOrders() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Integer type = $.getInteger("type"); // 个人:0 公务:1

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            //判断用户当天取消的订单次数
	        Long cancelPersonOrderCount=  csOrderService.getCsOrderCount($.add("csoStatus",3).add("csoUseMember", member.getCsmId())
	        		  .add("definex", "cso_start_time>="+ new DateUtil().dateToString(new Date(), "yyyy-MM-dd") ));
	        
            //
            
            if (type == null)
                type = 1;
            if (type == 1) {
                Page<CsUnitOrder> page = csUnitOrderService.getCsUnitOrderPage(
                        $.getInteger("page", 0), 5,
                        $.add(CsUnitOrder.F.csuoPerson, unitPerson.getCsupId()).add("definex",
                                "( (" + CsUnitOrder.C.csuoSpecial + " is null or "
                                        + CsUnitOrder.C.csuoSpecial + "=0 ))"));
                List<Map<String, Object>> dataList = loadUnitOrderFeatures(page.getResult(), true);
                LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                        .add("count", page.getCount()).add("size", page.getSize());
                return $.SendHtml($.json(
                        JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap).add("cancelCount", cancelPersonOrderCount))),
                        CHARSET);
            } else {
                Page<CsOrder> page = csOrderService.getCsOrderPage($.getInteger("page", 0), 5,
                        $.add(CsOrder.F.csoUseMember, member.getCsmId()).add("definex",
                                "(not exists(select 1 from cs_unit_order uo where uo.csuo_order = cso_id) ) "));
                LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                        .add("count", page.getCount()).add("size", page.getSize());
                return $.SendHtml($.json(JsonFormat.success().setData(
                        $.Map("list", assemUserOrders(page.getResult())).add("page", pagemap).add("cancelCount", cancelPersonOrderCount))),
                        CHARSET);
            }

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 订单检索接口 根据车牌号或者订单号检索
     * 
     * @return
     */
    public String queryOrders() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            String queryParam = $.getString("query_param");
            if ($.empty(queryParam)) {
                return returnError("101", "请输入订单号或车牌号");
            }
            LzMap lzMap = $
                    .add(CsOrder.F.csoUseMember, member.getCsmId()).add("definex", "(cso_id like '%"
                            + queryParam + "%' or cso_car_number like '%" + queryParam + "%')")
                    .add("desc", CsOrder.C.csoId);
            List<CsOrder> page = csOrderService.getCsOrderList(lzMap, -1);
            List<Map<String, Object>> dataList = assemUserOrders(page);
            for (Map<String, Object> mapdata : dataList) {
                CsUnitOrder unitOrder = csUnitOrderService
                        .getCsUnitOrder($.add(CsUnitOrder.F.csuoOrder, mapdata.get("orderId")));
                if (unitOrder != null) {
                    mapdata.put("state", unitOrder.getCsuoState());
                    mapdata.put("id", unitOrder.getCsuoId());
                    mapdata.put("profile", unitOrder.getCsuoProfile$());
                } else {
                    mapdata.put("state", 0);
                    mapdata.put("id", 0);
                    mapdata.put("profile", "");
                }
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", dataList))),
                    CHARSET);
        } catch (Exception ex) {
            // TODO: handle exception
            return returnError(ex);
        }
    }


    // =============================================================================================================

    /**
     * carmodelname car carImg startOutletsName csoAddress csoLongitude csoLatitude csoName csoFlag
     * finishOutletsName finishOutletsLat finishOutletsLng finishOutletsAddr orderId orderStatus
     * startTime finishTime carno carnumber endurance payNeed payReal orderFee mealpFee timeoutFee
     * 
     * @param orderList
     * @return
     */
    public List<Map<String, Object>> assemUserOrders(List<CsOrder> orderList) {

        // ================================================================
        Map<Long, CsCar> carmap = new HashMap<Long, CsCar>();
        Map<Long, CsCarModel> carmodelmap = new HashMap<Long, CsCarModel>();
        for (CsOrder data : orderList) {
            if (data != null && data.getCsoCar() != null) {
                if (carmap.get(data.getCsoCar()) == null) {
                    carmap.put(data.getCsoCar(), CsCar.get(data.getCsoCar()));
                }
            }
        }

        for (Long carId : carmap.keySet()) {
            Long carModelId = carmap.get(carId).getCscModel();
            CsCarModel carmodel = carmodelmap.get(carModelId);
            if (carmodel == null) {
                carmodelmap.put(carModelId, CsCarModel.get(carModelId));
            }
        }
        // ================================================================

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for (CsOrder data : orderList) {
            Map<String, Object> datamap = new HashMap<String, Object>(); // getUnitOrderMap(data);

            CsCar car = carmap.get(data.getCsoCar());
            String carImg = car.getCscPreview();
            if (car != null && carmodelmap.get(car.getCscModel()) != null) {
                datamap.put("carmodelname", carmodelmap.get(car.getCscModel()).getCscmName());
                if (carImg == null || carImg.equals("")) {
                    carImg = carmodelmap.get(car.getCscModel()).getCscmImage();
                }
            }

            datamap.put("car", car.getCscId());
            datamap.put("carImg", carImg);

            CsOutlets outlets = data.get$csoOutlets();
            if (outlets == null)
                outlets = new CsOutlets();
            datamap.put("startOutletsName", outlets.getCsoName());

            datamap.put("csoAddress", outlets.getCsoAddress());// 地址
            datamap.put("csoLongitude", outlets.getCsoLongitude());// 经度
            datamap.put("csoLatitude", outlets.getCsoLatitude());// 纬度
            datamap.put("csoName", outlets.getCsoName());// 网点名称
            datamap.put("csoFlag", data.getCsoFlag()); // 订单标识

            CsOutlets retoutlets = data.get$csoOutletsRet();
            if (retoutlets != null) {
                datamap.put("finishOutletsName", retoutlets.getCsoName());
                datamap.put("finishOutletsLat", retoutlets.getCsoLatitude());
                datamap.put("finishOutletsLng", retoutlets.getCsoLongitude());
                datamap.put("finishOutletsAddr", retoutlets.getCsoAddress());
            } else {
                datamap.put("finishOutletsName", "");
                datamap.put("finishOutletsLat", "");
                datamap.put("finishOutletsLng", "");
                datamap.put("finishOutletsAddr", "");
            }
            datamap.put("orderId", data.getCsoId());
            datamap.put("authCode", data.getCsoCode()); // 授权码
            datamap.put("bltName", "HELLO123"); // 蓝牙名称
            datamap.put("bltMacAddr", "e2556c37364d"); // 蓝牙地址
            datamap.put("orderStatus", data.getCsoStatus());
            datamap.put("startTime", data.getCsoStartTime());
            datamap.put("finishTime", data.getCsoFinishTime());
            datamap.put("carno", car.getCscCarNo());
            datamap.put("carnumber", car.getCscNumber());

            CsState state =
                    csStateService.getCsState($.add(CsState.F.cssNumber, car.getCscNumber()));
            if (state != null) {
                datamap.put("endurance", state.getCssEndurance());
            } else {
                datamap.put("endurance", null);
            }

            // 如果是订单簇，返回套餐价格明细
            if (CommonDisposeService.isOrderCluster(data)) {
                CsOrderCluster coc = CsOrderCluster.Get($.add(CsOrderCluster.F.csocId,
                        $.or(data.getCsoSrc(), "").replace("CsOrderCluster@", "")));
                if (coc != null) {
                    datamap.put("payNeed", data.getCsoPayNeed() + coc.getCsocPayNeed());
                    datamap.put("payReal",
                            data.getCsoPayReal() + coc.getCsocPayReal() + data.getCsoPayCoin());

                    datamap.put("orderFee", $.or(data.getCsoPayRent(), 0d));// 租金费用
                    datamap.put("mealpFee", coc.getCsocPayReal());
                    datamap.put("timeoutFee", data.getCsoPayTimeout());// 超时费用
                }
            } else {
                datamap.put("payNeed", data.getCsoPayNeed());
                datamap.put("payReal", data.getCsoPayReal() + data.getCsoPayCoin());
                datamap.put("timeoutFee", data.getCsoPayTimeout());// 超时费用
                datamap.put("orderFee", (data.getCsoPayReal() + data.getCsoPayCoin()
                        - $.or(data.getCsoPayTimeout(), 0d)));
                // datamap.put("orderFee",(data.getCsoPayReal()+data.getCsoPayCoin()) > 0 ?
                // $.or(data.getCsoPayRent(), 0d) : 0);//租金费用
            }

            CsComplain csComplain = csComplainService
                    .getCsComplain($.add(CsComplain.F.cscObject, "CsOrder@" + data.getCsoId()));

            datamap.put("isComment", csComplain == null ? false : true);
            datamap.put("starLevel", csComplain == null ? 0 : csComplain.getCscLevel());
            datamap.put("starLevel1", csComplain == null ? 0 : csComplain.getCscLevel1());
            datamap.put("content", csComplain == null ? " " : csComplain.getCscContent());

            dataList.add(datamap);
        }
        return dataList;
    }

    /**
     * 获取个人用车订单列表
     * 
     * @return
     */
    public String getUserOrders() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Page<CsOrder> page = csOrderService.getCsOrderPage($.getInteger("page", 0), 5,
                    $.add(CsOrder.F.csoUseMember, member.getCsmId()));

            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());

            return $.SendHtml(
                    $.json(JsonFormat.success().setData(
                            $.Map("list", assemUserOrders(page.getResult())).add("page", pagemap))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 
     * startOutletsName finishOutletsName finishOutletsLat finishOutletsLng finishOutletsAddr
     * carmodelImage //车型图片 previewimg //车辆主图 carImg //车辆图片，区别是可能会取车型图片 orderStatus endurance
     * 
     * 加载公务车订单附加属性
     * 
     * @param result
     */
    public List<Map<String, Object>> loadUnitOrderFeatures(List<CsUnitOrder> result,
            boolean loadStateData) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<Long, CsCar> carmap = new HashMap<Long, CsCar>();
        Map<Long, CsCarModel> carmodelmap = new HashMap<Long, CsCarModel>();
        for (CsUnitOrder data : result) {
            if (data != null && data.getCsuoCar() != null) {
                CsCar car = carmap.get(data.getCsuoCar());
                if (car == null) {
                    carmap.put(data.getCsuoCar(), CsCar.get(data.getCsuoCar()));
                }
            }
        }

        for (Long carId : carmap.keySet()) {
            Long carModelId = carmap.get(carId).getCscModel();
            CsCarModel carmodel = carmodelmap.get(carModelId);
            if (carmodel == null) {
                carmodelmap.put(carModelId, CsCarModel.get(carModelId));
            }
        }

        for (CsUnitOrder data : result) {
            if (data == null)
                continue;

            CsOutlets outlets = null;
            CsOutlets retoutlets = null;
            CsOrder order = data.get$csuoOrder();
            if (order != null) {
                outlets = order.get$csoOutlets();
                retoutlets = CsOutlets.get(order.getCsoOutletsRet());
            }

            Map<String, Object> datamap = getUnitOrderMap(data);
            if (outlets != null) {
                datamap.put("startOutletsName", outlets.getCsoName());
            } else {
                datamap.put("startOutletsName", "");
            }
            if (retoutlets != null) {
                datamap.put("finishOutletsName", retoutlets.getCsoName());
                datamap.put("finishOutletsLat", retoutlets.getCsoLatitude());
                datamap.put("finishOutletsLng", retoutlets.getCsoLongitude());
                datamap.put("finishOutletsAddr", retoutlets.getCsoAddress());
            } else {
                datamap.put("finishOutletsName", "");
                datamap.put("finishOutletsLat", "");
                datamap.put("finishOutletsLng", "");
                datamap.put("finishOutletsAddr", "");
            }

            CsCar car = carmap.get(data.getCsuoCar());
            String carImg = car.getCscPreview();
            if (car != null && carmodelmap.get(car.getCscModel()) != null) {
                datamap.put("carmodelImage", carmodelmap.get(car.getCscModel()).getCscmImage());

                if (carImg == null || carImg.equals("")) {
                    carImg = carmodelmap.get(car.getCscModel()).getCscmImage();
                }
                datamap.put("previewimg", car.getCscPreview());
            } else {
                datamap.put("carmodelImage", "");
            }

            datamap.put("carImg", carImg);

            if (data.getCsuoOrder() != null && data.getCsuoOrder() > 0) {
                CsOrder csOrder = data.get$csuoOrder();
                if (csOrder != null) {
                    datamap.put("orderStatus", csOrder.getCsoStatus());
                } else {
                    datamap.put("orderStatus", "-1");
                }
            } else {
                datamap.put("orderStatus", "-1");
            }

            CsOrder csOrder = data.get$csuoOrder();

            if (csOrder == null) {
                datamap.put("isComment", false);
                datamap.put("starLevel", 0);
                datamap.put("starLevel1", 0);
                datamap.put("content", " ");
                if (loadStateData) {
                    datamap.put("endurance", null);
                }
            } else {
                CsComplain csComplain = csComplainService.getCsComplain(
                        $.add(CsComplain.F.cscObject, "CsOrder@" + csOrder.getCsoId()));
                datamap.put("isComment", csComplain == null ? false : true);
                datamap.put("starLevel", csComplain == null ? 0 : csComplain.getCscLevel());
                datamap.put("starLevel1", csComplain == null ? 0 : csComplain.getCscLevel1());
                datamap.put("content", csComplain == null ? " " : csComplain.getCscContent());
                if (loadStateData) {
                    // 订单已预订使用中
                    if ((csOrder.getCsoStatus() == 0 || csOrder.getCsoStatus() == 1)
                            && car != null) {
                        CsState state = csStateService
                                .getCsState($.add(CsState.F.cssNumber, car.getCscNumber()));
                        if (state != null) {
                            datamap.put("endurance", state.getCssEndurance());
                        } else {
                            datamap.put("endurance", null);
                        }
                    } else {
                        datamap.put("endurance", null);
                    }
                }
            }



            dataList.add(datamap);
        }
        return dataList;
    }

    /**
     * 获取公务专车订单列表
     * 
     * @return
     */
    public String getSpecials() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            Page<CsSpecialCar> page =
                    csSpecialCarService.getCsSpecialCarPage($.getInteger("page", 0), 5,
                            $.add(CsSpecialCar.F.csscPerson, unitPerson.getCsupId().toString()));

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            for (CsSpecialCar data : page.getResult()) {
                Map<String, Object> datamap = new HashMap<String, Object>();
                datamap.put("specialId", data.getCsscId());
                datamap.put("fromAddr", data.getCsscStartAddr());
                datamap.put("toAddr", data.getCsscFinishAddr());
                datamap.put("driverName", data.getCsscDriverName());
                datamap.put("driverPhone", data.getCsscDriverPhone());
                datamap.put("driverImage", data.getCsscDriverImage());
                datamap.put("carNumber", data.getCsscCarNumber());
                datamap.put("carColor", data.getCsscDriverColor());
                datamap.put("carTypeInfo", data.getCsscDriverCarInfo());
                datamap.put("addTime", data.getCsscAddTime());
                datamap.put("startTime", data.getCsscStartTime());
                datamap.put("finishTime", data.getCsscFinishTime());
                datamap.put("third", data.getCsscThird());
                datamap.put("duration", data.getCsscRealDur()); // 订单时长，分钟为单位
                datamap.put("kilo", data.getCsscKilo());
                datamap.put("score", data.getCsscScope()); // 0:未评分 1:一星 2:二星
                                                           // 3:三星 4:四星 5:五星
                datamap.put("assess", data.getCsscAssess());
                datamap.put("status", data.getCsscStatus()); // 0:待审核 1:已审核
                                                             // 2:已下单 3:已接单
                                                             // 4:已完成 5:已取消
                                                             // 6:已作废
                datamap.put("thirdFee", data.getCsscThirdFee());
                datamap.put("carModel", data.getCsscCarModel());

                datamap.put("driverName", data.getCsscDriverName()); // 司机姓名
                datamap.put("driverMobile", data.getCsscDriverPhone()); // 司机电话
                datamap.put("driverCarColor", data.getCsscDriverColor()); // 司机车辆颜色
                datamap.put("driverImage", data.getCsscDriverImage()); // 司机车辆头像
                datamap.put("driverCarNumber", data.getCsscCarNumber()); // 司机车辆车牌号

                // 约租车
                CsUnitOrder order = data.get$csscUnitOrder();
                datamap.put("mobile", data.getCsscMobile()); // 乘车人电话
                datamap.put("mileage", data.getCsscPreditMileage()); // 预计公里数
                datamap.put("payNeed", data.getCsscEstimateFee()); // 预计费用

                String msg = data.getCsscMsg();
                JSONObject val = null;
                try {
                    val = JSONObject.fromObject(msg);
                } catch (Exception e) {
                }
                if (val != null) {
                    datamap.put("share", Integer.valueOf(val.get("share").toString()));
                    datamap.put("profile", val.get("profile"));
                } else {
                    datamap.put("share", 0); // 是否接受拼车
                    datamap.put("profile", ""); // 需求
                }
                datamap.put("mobile", data.getCsscMobile()); // 乘车人电话

                dataList.add(datamap);
            }

            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());

            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取公务专车订单
     * 
     * @return
     */
    public String getSpecial() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            Long specialCarId = $.getLong("specialCarId");
            if (specialCarId == null) {
                return returnError("102", "参数无效");
            }
            CsSpecialCar data = csSpecialCarService.getCsSpecialCarById(specialCarId);
            if (data == null) {
                return returnError("103", "订单无效");
            }

            Map<String, Object> datamap = new HashMap<String, Object>();
            datamap.put("specialId", data.getCsscId());
            datamap.put("fromAddr", data.getCsscStartAddr());
            datamap.put("toAddr", data.getCsscFinishAddr());
            datamap.put("driverName", data.getCsscDriverName());
            datamap.put("driverPhone", data.getCsscDriverPhone());
            datamap.put("driverImage", data.getCsscDriverImage());
            datamap.put("carNumber", data.getCsscCarNumber());
            datamap.put("carColor", data.getCsscDriverColor());
            datamap.put("carTypeInfo", data.getCsscDriverCarInfo());
            datamap.put("addTime", data.getCsscAddTime());
            datamap.put("startTime", data.getCsscStartTime());
            datamap.put("finishTime", data.getCsscFinishTime());
            datamap.put("third", data.getCsscThird());
            datamap.put("duration", data.getCsscRealDur()); // 订单时长，分钟为单位
            datamap.put("kilo", data.getCsscKilo());
            datamap.put("score", data.getCsscScope()); // 0:未评分 1:一星 2:二星 3:三星
                                                       // 4:四星 5:五星
            datamap.put("assess", data.getCsscAssess());
            datamap.put("status", data.getCsscStatus()); // 0:待审核 1:已审核 2:已下单
                                                         // 3:已接单 4:已完成 5:已取消
                                                         // 6:已作废

            return $.SendHtml($.json(JsonFormat.success().setData($.$("map", datamap))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 提交评价
     * 
     * @return
     */
    public String submitAssess() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            Long specialId = $.getLong("specialId");
            Integer score = $.getInteger("score", null);
            String access = $.getString("access");
            if (specialId == null) {
                return returnError("102", "订单无效");
            }

            CsSpecialCar specialCar = CsSpecialCar.get(specialId);
            if (specialCar == null) {
                return returnError("102", "订单无效");
            }

            if (score != null)
                specialCar.setCsscScope((short) score.intValue());
            specialCar.setCsscAssess(access);
            specialCar.setCsscUpdateTime(new Date());
            specialCar.update();
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 取消公务专车订单
     * 
     * @return
     */
    public String cancelSpecialOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            Long specialId = $.getLong("specialId");
            boolean force = $.getBoolean("force", false);
            String reasonId = $.getString("reasonId");
            String otherReason = $.getString("otherReason");

            if (specialId == null) {
                return returnError("101", "参数无效");
            }

            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
            CsSpecialCar specialCar = csSpecialCarService.getCsSpecialCarById(specialId);
            if (specialCar == null) {
                return returnError("102", "订单无效");
            }

            if ((specialCar.getCsscStatus().intValue() > 3)) {
                return returnError("102", "订单无效");
            }

            commonUnitService.executeCancelUnitOrder(unitPerson.getCsupInfo(),
                    specialCar.getCsscUnitOrder(), "");
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 续订公务车订单
     * 
     * @return
     */
    public String renewUnitOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("106", "用户未绑定企业用户");

            String unitOrderId = $.getString("unitOrderId");
            Date newFinishTime = $.getDate("newFinishTime");
            if (newFinishTime == null) {
                try {
                    newFinishTime = new DateUtil().StringtoDate($.getString("newFinishTime"),
                            "yyyy MM-dd HH:mm:ss");
                } catch (Exception e) {
                }
            }

            if (unitOrderId == null)
                // 新的还车时间不能为空
                return returnError("101", "订单号不能为空");

            // 获取当前企业订单
            CsUnitOrder csUnitOrder = CsUnitOrder.get(Long.valueOf(unitOrderId));
            if (csUnitOrder == null)
                return returnError("101", "该订单不存在");

            if (newFinishTime == null)
                // 新的还车时间不能为空
                return returnError("103", "还车时间不能为空");

            if (newFinishTime.before(csUnitOrder.getCsuoFinishTime())) {
                return returnError("103", "续订订单结束时间必须晚于当前订单结束时间");
            }

            CsUnitOrder unitOrder = commonUnitService.executeReUnitOrder(person.getCsupInfo(),
                    Long.valueOf(unitOrderId), newFinishTime);
            if (unitOrder != null) {
                return $.SendHtml($.json(
                        JsonFormat.success().setData($.$("unitOrderId", unitOrder.getCsuoId()))),
                        CHARSET);
            } else {
                return returnError("110", "续订申请失败");
            }

        } catch (MessageException e) {
            e.printStackTrace();
            Logger.getRootLogger().error(e.getMessage(), e);
            return returnError("104", e.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 个人订单续订
     * 
     * @return
     */
    public String renewPersonalOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long order = $.getLong("orderId", 0l);
            Date newFinishDate = $.getDate("newFinishDate");

            CsOrder csOrder = csOrderService.getCsOrderById(order);
            // 不存在定单
            if (csOrder == null)
                return returnError("101", "登录授权无效");

            // 支付会员对不上
            if (!csOrder.getCsoPayMember().equals(member.getCsmId()))
                return returnError("102", "会员信息不匹配");

            if (newFinishDate == null)
                // 新的还车时间不能为空
                return returnError("103", "还车时间不能为空");

            Double freehour = csOrder.getCsoFreehour();
            CsOrder reOrder = commonDisposeService.executeReOrder(order, newFinishDate, freehour,
                    From.APP, null);
            if (reOrder != null) {
                return $.SendHtml(
                        $.json(JsonFormat.success().setData($.$("orderId", reOrder.getCsoId()))),
                        CHARSET);
            } else {
                return returnError("110", "续订申请失败");
            }

        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("104", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取消息
     * 
     * @return
     */
    public String getMessages() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("101", "用户未绑定企业用户");

            Short readStatus = $.getShort("readStatus"); // 1:已读 0:未读
            Integer csumType = $.getInteger("type", 0); // 1:待审核消息 2系统消息 0全部消息

            Map<String, Object> params = $.Map(CsUnitMt.F.csumStatus, readStatus)
                    .add(CsUnitMt.F.csumPerson, person.getCsupId());
            if (csumType != 0) {
                params.put(CsUnitMt.F.csumType, csumType);
            }

            Page<CsUnitMt> page =
                    csUnitMtService.getCsUnitMtPage($.getInteger("page", 0), 10, params);

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            for (CsUnitMt data : page.getResult()) {
                String udata = data.getCsumData();
                if (udata != null && udata.indexOf("CsUnitOrder") != -1) {
                    udata = udata.substring(udata.indexOf("CsUnitOrder") + "CsUnitOrder@".length(),
                            udata.length());
                }

                Map<String, Object> mapdata = new HashMap<String, Object>();
                mapdata.put("id", data.getCsumId());
                mapdata.put("title", data.getCsumTitle());
                mapdata.put("content", data.getCsumContent());
                mapdata.put("readTime", data.getCsumReadTime());
                mapdata.put("addTime", data.getCsumAddTime());
                mapdata.put("updateTime", data.getCsumUpdateTime());
                mapdata.put("readStatus", data.getCsumStatus());
                mapdata.put("unitOrderId", udata);
                dataList.add(mapdata);
            }
            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());

            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 读取消息
     * 
     * @return
     */
    public String readMessages() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            Long id = $.getLong("id");
            if (id == null) {
                return returnError("101", "消息无效");
            }

            CsUnitMt csUnitMt = csUnitMtService.getCsUnitMtById(id);
            if (csUnitMt == null) {
                return returnError("101", "消息无效");
            }

            csUnitMt.setCsumStatus(Short.valueOf("1"));
            csUnitMt.update();

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取违章列表
     * 
     * @return
     */
    public String getViolates() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 远程操作
     * 
     * @param carId
     * @param conType 1取车/开门、2关门、3鸣笛、4远程还车
     * @return
     */
    public String remoteOperate() {
        try {

            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Reason event = Reason.单用户远程操作间隔十秒钟.set(member.getCsmId());
            if (!EventBarrier.has(event)) {
                EventBarrier.put(event, 5);
            } else {
                return returnError("110", "您操作过于频繁，请5秒钟后重试！");
            }

            String orderIdStr = $.getString("orderId");

            if (orderIdStr == null || orderIdStr.equals("")) {
                return returnError("101", "订单号不可为空");
            }

            String conType = $.getString("type");

            Long orderId = Long.valueOf(orderIdStr);

            if (orderId == null) {
                return returnError("101", "订单号不可为空");
            }

            CsOrder csOrder = csOrderService.getCsOrderById(orderId);
            // 不存在定单或订单无效
            if (csOrder == null)
                return returnError("102", "订单无效");

            // 支付会员对不上
            if (!csOrder.getCsoUseMember().equals(member.getCsmId())) {
                return returnError("103", "订单与会员信息不匹配");
            }

            CsState state = CsState.getCsState($.add(CsState.F.cssCar, csOrder.getCsoCar()));
            if (state != null) {
                if (new Date().getTime() - state.getCssAddTime().getTime() <= 1000 * 60 * 10) {
                    if (state.getCssOrder() != null && state.getCssOrder().intValue() > 0
                            && state.getCssOrder().intValue() != orderId.intValue()) {
                        return returnError("103", "前用户尚未还车，暂不能操作");
                    }
                }
            }

            // 公务订单判断审核状态
            CsUnitOrder unitOrder =
                    CsUnitOrder.getCsUnitOrder($.add(CsUnitOrder.F.csuoOrder, csOrder.getCsoId()));
            if (unitOrder != null) {
                if (unitOrder.getCsuoState().intValue() != 2) {
                    return returnError("108", "订单未通过审核");
                }
            }
//            CsArgument takeCarTime = csArgumentService
//                    .getCsArgument($.add(CsArgument.F.csaFlag, ArgumentKey.TAKE_CAR_TIME));
//
//            // 判断订单开始时间：订单前10分钟才可以取车
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(csOrder.getCsoStartTime());
//            calendar.add(Calendar.MINUTE,
//                    takeCarTime == null ? -20 : Integer.parseInt(takeCarTime.getCsaValue()));
//            long temp = calendar.getTimeInMillis() - new Date().getTime();
//            if (temp > 0) {
//                // 订单尚未开始
//                return returnError("104", "您的订单还未开始，请等待");
//            }

            // 仅允许北京+电动车可操作
            // if (car.get$cscModel().getCscmType$().equals("电动车") &&
            // car.getCscHost() == 2) {
            if (!(csOrder.getCsoStatus().equals(new Short("0"))
                    || csOrder.getCsoStatus().equals(new Short("1")))) {
                // System.currentTimeMillis()>csOrder.getCsoFinishTime().getTime()
                // 订单状态不为0和1
                return returnError("105", "订单状态不正确");
            }
            // } else{
            // 该车型不支持该操作
            // return new Result(false, 105);
            // }

            String msg = "";
            if (conType.equals("1")) { // 取车/开门
                if (csOrder.getCsoStatus().equals(new Short("0"))) { // 已预订状态-远程取车
                    if (csOrder.getCsoState().intValue() != 2) {
                        // 非法操作
                        return returnError("106", "您的订单车辆还未接收，请过几分钟再试！");
                    } else {
                        WeixinHelper.remoteController(csOrder.getCsoCar(), "6", member.getCsmId());
                   
                    }
                } else if (csOrder.getCsoStatus().equals(new Short("1"))) {// 使用中状态-远程开门
                    WeixinHelper.remoteController(csOrder.getCsoCar(), "0", member.getCsmId());
                } else {
                    // 非法操作
                    return returnError("106", "非法的请求");
                }
                msg = "指令发送成功，开门执行中。";
            } else if (conType.equals("2")) {// 关门
                if (csOrder.getCsoStatus().equals(new Short("0"))
                        || csOrder.getCsoStatus().equals(new Short("1"))) {
                    WeixinHelper.remoteController(csOrder.getCsoCar(), "1", member.getCsmId());
                } else {
                    // 非法操作
                    return returnError("106", "非法的请求");
                }
                msg = "指令发送成功，锁车执行中。";
            } else if (conType.equals("3")) {// 鸣笛
                if (csOrder.getCsoStatus().equals(new Short("0"))
                        || csOrder.getCsoStatus().equals(new Short("1"))) {
                    WeixinHelper.remoteController(csOrder.getCsoCar(), "17", member.getCsmId());
                } else {
                    // 非法操作
                    return returnError("106", "非法的请求");
                }
                msg = "指令发送成功，鸣笛执行中。";
            } else if (conType.equals("4")) {// 远程还车
                if (csOrder.getCsoStatus().equals(new Short("0"))
                        || csOrder.getCsoStatus().equals(new Short("1"))) {

                    String remark = csOrder.get$csoOutletsRet().getCsoProfile();

                    String destance = null;
                    if (remark != null) {
                        try {
                            JsonElement element = new JsonParser().parse(remark);
                            if (element.isJsonObject()) {
                                JsonObject jsonObject = element.getAsJsonObject();
                                if (jsonObject.has("range")) {
                                    destance = jsonObject.get("range").getAsString();
                                }
                            }
                        } catch (Exception e) {
                            Logger.getRootLogger().error(e);
                        }
                    }

                    if (state != null) {
                        Double[] lng_lat = CoordtransformUtil.WGS84ToGCJ02(
                                Double.parseDouble(state.getCssLongitude()),
                                Double.parseDouble(state.getCssLatitude()));
                        double distance = getDistance(lng_lat[0], lng_lat[1],
                                csOrder.get$csoOutletsRet().getCsoLongitude(),
                                csOrder.get$csoOutletsRet().getCsoLatitude());
                        if (destance != null && !destance.equals("") && !destance.equals("0")
                                && distance > Integer.parseInt(destance))
                            return returnError("106", "请将车辆还入网点还车范围内，再点击还车");
                    }


                    if (csOrder.getCsoStartTime().getTime() - new Date().getTime() > 0) {
                        return returnError("107", "请在订单开始后还车");
                    }

                    WeixinHelper.remoteController(csOrder.getCsoCar(), "7", member.getCsmId());

                } else {
                    // 非法操作
                    return returnError("106", "非法的请求");
                }
                msg = "指令发送成功，还车执行中。";
            }

            return $.SendHtml($.json(JsonFormat.success().setText(msg)), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }

    }

    /**
     * 取消订单
     * 
     * @return
     */
    public String cancelOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long unitOrderId = $.getLong("unitOrderId");
            if (unitOrderId == null) {
                return returnError("101", "订单不存在");
            }

            CsUnitOrder unitOrder = CsUnitOrder.get(unitOrderId);
            if (unitOrder == null) {
                return returnError("101", "订单不存在");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("102", "会员不是企业账户");
            }

            CsOrder order = csOrderService.getCsOrderById(unitOrder.getCsuoOrder());
            // 验证当前订单是不是当前用户的
            if (order == null) {
                return returnError("101", "订单不存在");
            }
            //订单开始后不能取消订单，只能选择提前还车
            if(order.getCsoStatus()==1) {
            	 return returnError("104", "订单开始后不能取消订单	，只能选择提前还车");
            }
            // 订单状态不正确
            if (order.getCsoStatus() != 0) {
                return returnError("103", "订单不是预定状态，不能完成取消");
            }
            //判断用户当天取消的订单次数
	        Long cancelCount=  csOrderService.getCsOrderCount($.add("csoStatus",3).add("csoUseMember", member.getCsmId())
	        		  .add("definex", "cso_start_time>="+ new DateUtil().dateToString(new Date(), "yyyy-MM-dd") ));
	        if(cancelCount==null||cancelCount<=3) {
	        	 //可取消订单
	        	 commonUnitService.executeCancelUnitOrder(unitPerson.getCsupInfo(), unitOrderId, "");
	        }
           
            return $.SendHtml($.json(JsonFormat.success().setData($.add("cancelOrderCount", cancelCount))), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }

    }

    /**
     * 取消订单申请
     * 
     * @return
     */
    public String cancelUnitOrderSubmit() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long unitOrderId = $.getLong("unitOrderId");
            if (unitOrderId == null) {
                return returnError("101", "订单不存在");
            }

            final CsUnitOrder unitOrder = CsUnitOrder.get(unitOrderId);
            if (unitOrder == null) {
                return returnError("101", "订单不存在");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("102", "会员不是企业账户");
            }

            final CsOrder order = csOrderService.getCsOrderById(unitOrder.getCsuoOrder());
            // 验证当前订单是不是当前用户的
            if (order == null) {
                return returnError("101", "订单不存在");
            }

            // 订单状态不正确
            if (order.getCsoStatus() != 0)
                return returnError("103", "订单不是预定状态，不能完成取消");
            if (unitOrder.getCsuoState().intValue() != 1) {
                return returnError("104", "订单不是待审核状态，不能完成取消");
            }

            csOrderService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... arg0) {
                    unitOrder.setCsuoRemark("公务车企业订单未审核前用户取消");
                    unitOrder.setCsuoUpdateTime(new Date());
                    unitOrder.setCsuoState((short) 4);
                    unitOrder.update();

                    order.setCsoUpdateTime(new Date());
                    order.csoSerial(order.getCsoUseMember() + "-" + order.getCsoCar() + "-"
                            + order.getCsoStartTime().getTime() + "-" + order.getCsoStatus() + "-"
                            + Calendar.getInstance().get(Calendar.MINUTE));
                    order.setCsoRemark("公务车企业订单未审核前用户取消");
                    order.setCsoStatus((short) 7);
                    order.update();

                    // 撤销定单的订单调度
                    SchedulingService.cancelScheduling(order.getCsoId());

                    SchedulingService.onlineCar(order.getCsoCar(), (short) 1, "异借还车订单取消车辆自动上线",
                            null);

                    return null;
                }
            });

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }

    }

    // =============================================================================================================

    /**
     * 第三方公务车预约
     * 
     * @return
     */
    public String thirdPartyOrder() {
        try {

            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Date startTime = $.getDate("startTime");
            Date finishTime = $.getDate("finishTime", startTime);
            String fromLatitude = $.getString("fromLatitude"); // 纬度
            String fromLongitude = $.getString("fromLongitude"); // 经度
            String toLatitude = $.getString("toLatitude"); // 纬度
            String toLongitude = $.getString("toLongitude"); // 经度
            String fromAddr = $.getString("fromAddr"); // 出发地点
            String toAddr = $.getString("toAddr"); // 到达地点
            // Integer headcount = $.getInteger("headCount", 1); //乘车人数
            String profile = $.getString("profile"); // 用车需求
            Short type = $.getShort("type", (short) 1); // 类型：1:一般公务 2:紧急公务
            Long carLevel = $.getLong("carLevel"); // 预定车型：前台传递的参数为1或者2或者3，其中1代表舒适型，2代表商务型,
            String message = $.getString("message"); // 留言

            Long startOutlets = $.getLong("startOutlets"); // 出发网点

            // if (startTime.getTime() - System.currentTimeMillis() < 30 * 60 *
            // 1000) {
            // return returnError("108", "预约时间请提前30分钟以上");
            // }

            String fromLocate =
                    LocateConverter.execute(fromLatitude + "," + fromLongitude, 5, null);
            fromLatitude = fromLocate.split(",")[0];
            fromLongitude = fromLocate.split(",")[1];

            String toLocate = LocateConverter.execute(toLatitude + "," + toLongitude, 5, null);
            toLatitude = toLocate.split(",")[0];
            toLongitude = toLocate.split(",")[1];

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("106", "用户未绑定企业用户");

            // 找到自己所在的组，给组长发消息用
            CsUnitGroup group = CsUnitGroup.getCsUnitGroupById(person.getCsupGroup());
            if (group == null)
                return returnError("107", "用户未绑定分组");

            CsUnitPerson auditPerson = CsUnitPerson.get(group.getCsugPerson());

            CsUnitOrder unitOrder = commonUnitService.executeAddSpecialOrder(person.getCsupId(),
                    profile, startTime, finishTime, type, fromLatitude + ";" + fromLongitude,
                    toLatitude + ";" + toLongitude, fromAddr, toAddr, message, carLevel);

            // 获取预估价格
            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
            CsSpecialCar csSpecialCar =
                    csSpecialCarService.getCsSpecialCarById(unitOrder.getCsuoSpecial());
            Map<String, Object> price_Estimate = new HashMap<String, Object>();
            Double fee_total_estimate = 0d;
            if (unitOrder.get$csuoSpecial().getCsscThird() == 1) {
                price_Estimate = DidiApi.getEstimateOrderPrice(csSpecialCar);
                if (price_Estimate != null) {
                    Object code = price_Estimate.get("errno");
                    Object msg = price_Estimate.get("errmsg");
                    if (Double.valueOf(code.toString()).intValue() == 0 && msg.equals("SUCCESS")) {
                        Map<String, Object> price_new =
                                (Map<String, Object>) price_Estimate.get("data");
                        String price = ((Map<String, Object>) price_new
                                .get(DidiApi.carModelMapping(csSpecialCar))).get("price")
                                        .toString();
                        fee_total_estimate = Double.parseDouble(price);
                    }
                }
            } else if (unitOrder.get$csuoSpecial().getCsscThird() == 2) {
                price_Estimate = YidaoApi.getOrderEstimatedPrice(csSpecialCar);
                if (price_Estimate != null) {
                    Object code = price_Estimate.get("code");
                    if (Double.valueOf(code.toString()).intValue() == 200) {
                        String total_fee = ((Map<String, Object>) price_Estimate.get("result"))
                                .get("total_fee").toString();
                        fee_total_estimate = Double.parseDouble(total_fee);
                    }
                }
            }
            unitOrder.setCsuoPayNeed(fee_total_estimate);
            unitOrder.update();

            try {
                SimpleDateFormat formatter = new SimpleDateFormat(NORMAL_DATE_FORMAT);
                Map<String, String> extra = new HashMap<String, String>();
                extra.put("flag", "order-audit");
                extra.put("data", $.json($.add("unitOrderId", unitOrder.getCsuoId())));
                JpushClientHelper.sendAlertWithTag(auditPerson.getCsupMember().toString(), "待办提醒",
                        "您有一笔" + ApiBase.CALL_CAR + "申请待审批" + formatter.format(new Date()), extra,
                        JpushConfig.OFC_APP);
            } catch (Exception e) {
                Logger.getRootLogger().error(e.getMessage(), e);
            }

            return $.SendHtml($.json(JsonFormat.success().setData($.$("unitOrderId", ""))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取特急专送订单位置信息
     * 
     * @return
     */
    public String getSpecialOrderLocale() {
        try {

            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");

            String special_id = $.getString("specialId"); // csSpecialCar.id
            if (StringUtils.isEmpty(special_id))
                return returnError("101", "参数无效");

            CsSpecialCar specialCar =
                    csSpecialCarService.getCsSpecialCarById(Long.valueOf(special_id));
            if (specialCar == null) {
                return returnError("101", "订单号无效");
            }

            LzMap retValue = new LzMap();
            if (specialCar.getCsscThird() != null) {
                if (specialCar.getCsscThird().intValue() == 1) { // 1:滴滴专车
                    Map<String, Object> map = DidiApi.getOrderDetail(member.getCsmMobile(),
                            specialCar.getCsscThirdNo());
                    if (map != null) {
                        Object code = map.get("errno");
                        Object msg = map.get("errmsg");
                        Map data = (Map) map.get("data");
                        if (Double.valueOf(code.toString()).intValue() == 0
                                && msg.equals("SUCCESS")) {
                            Map<String, Object> order = (Map) data.get("order");
                            // retValue.put("status", order.get("status"));
                            retValue.put("clng", order.get("dlng")); // 当前经度
                            retValue.put("clat", order.get("dlat")); // 当前纬度
                        }
                    }

                } else if (specialCar.getCsscThird().intValue() == 2) { // 2:易到专车
                    Map<String, Object> map = YidaoApi.getOrderLocale(specialCar.getCsscThirdNo());
                    if (map != null) {
                        Object code = map.get("code");
                        Object msg = map.get("msg");
                        Map data = (Map) map.get("result");
                        if (Double.valueOf(code.toString()).intValue() == 200
                                && msg.equals("success")) {
                            retValue.put("clng", data.get("lng")); // 当前经度
                            retValue.put("clat", data.get("lat")); // 当前纬度
                        }
                    }
                }
            }

            return $.SendHtml($.json(JsonFormat.success().setData(retValue)), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    // =============================================================================================================
    /**
     * 取消个人订单
     * 
     * @return
     */
    public String cancelPersonalOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            String version = $.getString("appVersion");
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long orderId = $.getLong("orderId");
            if (orderId == null) {
                return returnError("101", "订单不存在");
            }

            ICsOrderService csOrderService = $.getBean("csOrderService");
            ICommonDisposeService commonDisposeService = $.GetSpringBean("commonDisposeService");

            CsOrder order = csOrderService.getCsOrderById(orderId);
            // 验证当前订单是不是当前用户的
            if (order == null || !order.getCsoPayMember().equals(member.getCsmId())) {
                return returnError("102", "订单信息不符，无法完成取消操作");
            }
            //订单开始后不能取消订单，只能选择提前还车
            if(order.getCsoStatus()==1) {
            	 return returnError("104", "订单开始后不能取消订单	，只能选择提前还车");
            }
            
            // 订单状态不正确
            if (order.getCsoStatus() != 0)
                return returnError("103", "订单状态不正确，无法完成取消操作");

            //判断用户当天取消的订单次数
	        Long cancelCount=  csOrderService.getCsOrderCount($.add("csoStatus",3).add("csoUseMember", member.getCsmId())
	        		  .add("definex", "cso_start_time>="+ new DateUtil().dateToString(new Date(), "yyyy-MM-dd") ));
	        if(cancelCount==null||cancelCount<=3) {
	        	 //可取消订单
	            commonDisposeService.executeCancelOrder(orderId, "会员自主取消订单", From.APP,
	                    "鹏龙app" + version);
	        }
            return $.SendHtml($.json(JsonFormat.success().setData($.add("cancelOrderCount", cancelCount))), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }

    }

    // =============================================================================================================

    /**
     * 修改密码
     * 
     * @return
     */
    public String modifyPass() {
        try {

            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            String oldPass = $.getString("oldPass", "");
            String newPass = $.getString("newPass", "");
            String newPass2 = $.getString("newPass2", "");

            if (SystemHelper.isNullOrEmpty(oldPass) || SystemHelper.isNullOrEmpty(newPass)
                    || SystemHelper.isNullOrEmpty(newPass2)) {
                return returnError("101", "您还没有输入密码");
            }

            if (!newPass.equals(newPass2)) {
                return returnError("102", "两次输入的密码不一致");
            }

            CsMember user = csMemberService.getCsMemberById(member.getCsmId());
            if (!$.md5(oldPass).equals(user.getCsmPassword().toUpperCase())) {
                return returnError("103", "您输入的密码不正确，请重新输入");
            }

            user.setCsmPassword($.md5(newPass));
            user.setCsmLastTime(new Date());
            user.update();

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 检查更新
     * 
     * @return
     */
    public String checkAppVersion() {
        try {
            CsUpdate csUpdate = csUpdateService.getCsUpdate($.add("csuType", 2));
            if (csUpdate != null) {
                return $.SendHtml($.json(
                        JsonFormat.success().setData($.$("newVersion", csUpdate.getCsuVersion())
                                .add("newFile", csUpdate.getCsuFile()))),
                        CHARSET);
            } else {
                return $.SendHtml($.json(JsonFormat.success()), CHARSET);
            }
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取运行城市 1、北京 2、杭州
     */
    public void getHost() {
        SrvHost host = SrvHost.get(1L);
        LzMap retValue = new LzMap();
        retValue.put("host", host.getShId());
        retValue.put("name", Env.isOnline() ? "北京" : "杭州");
        $.SendHtml($.json(JsonFormat.success().setData(retValue)), CHARSET);
    }

    /**
     * 保存用户常用地址,
     */
    public String saveFavoriteAddress() {
        try {
            // 首先判断常用地址是否已经超过20个，如果超过则不予保存
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(CsFavoriteAddress.F.csfaMember, member.getCsmId());
            params.put("define", "csfaStatus = 1");
            List<CsFavoriteAddress> list = new ArrayList<CsFavoriteAddress>();
            list = csFavoriteAddressService.getCsFavoriteAddressList(params, 21);
            if (list.size() > 20)
                return returnError("900", "常用地址超过20个，请删除后再添加新地址。");
            CsFavoriteAddress csFavoriteAddress = new CsFavoriteAddress();
            csFavoriteAddress.setCsfaAddress($.getString("csfaAddress"));
            csFavoriteAddress.setCsfaLatitude($.getDouble("csfaLatitude"));
            csFavoriteAddress.setCsfaLongitude($.getDouble("csfaLongitude"));
            csFavoriteAddress.setCsfaMember(member.getCsmId());
            csFavoriteAddress.setCsfaType($.getShort("csfaType"));
            csFavoriteAddress.setCsfaStatus((short) 1);
            csFavoriteAddress.setCsfaAddTime(new Date());
            csFavoriteAddress.setCsfaUpdateTime(new Date());
            csFavoriteAddress.setCsfaDescription($.getString("csfaDescription"));
            // csFavoriteAddress.save();
            csFavoriteAddressService.saveCsFavoriteAddress(csFavoriteAddress);
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return returnError("903", "地址保存失败，请稍后再试！");
        }
    }

    public String getFavoriteAddress() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(CsFavoriteAddress.F.csfaMember, member.getCsmId());
            params.put("definex", "csfa_status = 1");
            List<CsFavoriteAddress> list = new ArrayList<CsFavoriteAddress>();
            list = csFavoriteAddressService.getCsFavoriteAddressList(params, 20);
            // Page page =
            // csFavoriteAddressService.getCsFavoriteAddressPage($.getInteger("page",
            // 0), 20, params);
            if (list == null || list.size() == 0)
                return returnError("902", "无可用常用地址，请先保存地址！");
            else
                return $.SendHtml($.json(JsonFormat.success().setData($.$("list", list))), CHARSET);

            // LzMap pagemap = $.$("index", page.getIndex()).add("total",
            // page.getTotal()).add("count", page.getCount()).add("size",
            // page.getSize());
            // return
            // $.SendHtml($.json(JsonFormat.success().setData($.Map("list",
            // dataList).add("page", pagemap))), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return returnError("905", "获取地址失败，请稍后再试！");
        }
    }

    public String deleteFavoriteAddressById() {
        // CsMember member = OauthUtils.getOauth($.getString("access_token",
        // ""));
        try {
            Long csfaId = $.getLong("csfaId");

            csFavoriteAddressService.deleteCsFavoriteAddressById(csfaId);
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return returnError("901", "地址删除失败，请稍后再试！");
        }
    }

    /**
     * 计算地球上任意两点(经纬度)距离
     * 
     * @param long1 第一点经度
     * @param lat1 第一点纬度
     * @param long2 第二点经度
     * @param lat2 第二点纬度
     * @return 返回距离 单位：米
     */
    public static double getDistance(double long1, double lat1, double long2, double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    /**
     * 寻车导航 规则：取车前导航到车辆所在网点。 取车后导航到车辆实际位置
     * 
     * @return
     */
    public String locateCar() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long carId = $.getLong("carId");
            if (carId == null) {
                return returnError("101", "参数无效");
            }

            CsCar car = CsCar.get(carId);
            if (car == null) {
                return returnError("101", "参数无效");
            }

            Long orderId = $.getLong("orderId");
            if (orderId != null) {
                // 根据车辆使用人、车辆、已预订or使用中状态获取车辆当前订单
                // CsOrder order =
                // csOrderService.getCsOrder($.add(CsOrder.F.csoCar,
                // carId).add(CsOrder.F.csoUseMember,
                // member.getCsmId()).add("definex", "cso_status in (0,1)"));
                CsOrder order = CsOrder.get(orderId);
                if (order != null) {
                    if (order.getCsoStatus().intValue() == 1) { // 如果订单已取车，获取车辆当前实时位置
                        // TODO 改成走缓存
                        CsState state = CsState.getCsState($.add(CsState.F.cssCar, carId));
                        if (state != null) {

                            // 如果数据是2个小时以内的
                            if (new Date().getTime() - state.getCssAddTime().getTime() < 1000 * 60
                                    * 60 * 2) {


                                Double[] longlan = CoordtransformUtil.WGS84ToGCJ02(
                                        Double.parseDouble(state.getCssLatitude()),
                                        Double.parseDouble(state.getCssLongitude()));

                                //
                                return $.SendHtml($.json(JsonFormat.success().setData($
                                        .add("latitude", longlan[1]).add("longitude", longlan[0]))),
                                        CHARSET);
                            }
                        }
                    }
                }
            }

            return $.SendHtml(
                    $.json(JsonFormat.success().setData($.add("latitude", car.getCscLatitude())
                            .add("longitude", car.getCscLongitude()))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * APP端获取支付url
     * 
     * @return
     */
    public String getPayUrl() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Double amount = $.getDouble("amount");
            String app = $.getString("app");
            if (amount == null) {
                return returnError("101", "参数无效");
            }

            String noticeUrl = "http://" + $.config("appHost") + "/app/official/alipayNotify.ashx";
            ICommonPayService commonPayService = $.GetSpringBean("commonPayService");
            String result = commonPayService.executeGetAppAlipayString(member.getCsmId(), amount,
                    noticeUrl, app);
            return $.SendHtml($.json(JsonFormat.success().setData($.add("urlData", result))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * APP端发起的支付回调
     * 
     * @return
     */
    public String callbackPayment() {
        // 非法请求
        CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }

        String resultInfo = $.getString("resultInfo");
        // 参数异常
        if (resultInfo == null) {
            return returnError("101", "参数无效");
        }

        int iSignContentEnd = resultInfo.indexOf("&sign_type=");
        String signContent = resultInfo.substring(0, iSignContentEnd);
        Map<String, String> paramMap = parseUrl(resultInfo);

        String outTradeNo = paramMap.get("out_trade_no");

        String lockReason = Reason.支付宝充值回调.name() + outTradeNo;
        EventLock eventLock = new EventLock();
        try {
            if (eventLock.tryLock(lockReason, 3000)) {
                boolean result =
                        commonPayService.executeSynAppAlipayCallback(paramMap, signContent, true);
                return $.SendHtml($.json(JsonFormat.success().setSuccess(result)), CHARSET);
            } else {
                return returnError("102", "您的操作过于频繁，请稍后再试!");
            }
        } catch (Exception ex) {
            return returnError(ex);
        } finally {
            eventLock.unlock(lockReason);
        }
    }

    public static Map<String, String> parseUrl(String url) {
        Map<String, String> result = new HashMap<String, String>();
        if (url == null)
            return result;
        String[] paramArr = url.split("[&]");
        for (String kvstr : paramArr) {
            for (int i = 0; i < kvstr.length(); i++) {
                if (kvstr.charAt(i) == '=' && (i + 1 != kvstr.length())
                        && kvstr.charAt(i + 1) == '"' && kvstr.charAt(kvstr.length() - 1) == '"'
                        && i + 2 != kvstr.length()) {
                    result.put(kvstr.substring(0, i), kvstr.substring(i + 2, kvstr.length() - 1));
                }
            }
        }

        return result;
    }

    /**
     * 执行支付宝同步回调
     * 
     * @return
     */
    public boolean doAlipayNotify() {
        // 获取支付宝POST过来反馈信息

        HttpServletRequest request = ServletActionContext.getRequest();
        // 商户订单号
        String out_trade_no = request.getParameter("out_trade_no");

        String lockReason = Reason.支付宝充值回调.name() + out_trade_no;
        EventLock eventLock = new EventLock();
        try {
            Map<String, String> params = new HashMap<String, String>();
            if (eventLock.tryLock(lockReason, 3000)) {
                Map requestParams = request.getParameterMap();
                for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                    String name = (String) iter.next();
                    String[] values = (String[]) requestParams.get(name);
                    String valueStr = "";
                    for (int i = 0; i < values.length; i++) {
                        valueStr = (i == values.length - 1) ? valueStr + values[i]
                                : valueStr + values[i] + ",";
                    }
                    // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                    // valueStr = new String(valueStr.getBytes("ISO-8859-1"),
                    // "gbk");
                    params.put(name, valueStr);
                }

                ICommonPayService commonPayService = $.GetSpringBean("commonPayService");
                boolean result = commonPayService.executeSynAppAlipayCallback(params, null, false);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLock.unlock(lockReason);
        }
        return false;
    }

    /**
     * 支付宝发起的支付通知
     * 
     * @return
     */
    public String alipayNotify() {
        PrintWriter out = null;
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            out = response.getWriter();

            boolean result = doAlipayNotify();
            if (result) {
                out.println("success"); // 请不要修改或删除
            } else {
                out.println("fail");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            if (out != null)
                out.println("fail");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取正在退款的数据
     * 
     * @return
     */
    public String getRefund() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsRefund csRefund = csRefundService.getCsRefund(
                    $.add(CsRefund.F.csrMember, member.getCsmId()).add("definex", "csr_status<2"));
            Map<String, Object> result = new HashMap<String, Object>();
            if (csRefund != null) {
                result.put("refundId", csRefund.getCsrId());
                result.put("amount", csRefund.getCsrAmount());
                result.put("addTime", csRefund.getCsrAddTime());
                result.put("auditTime", csRefund.getCsrCheckTime());
                result.put("backTime", csRefund.getCsrBackTime());
                result.put("finishTime", csRefund.getCsrEndTime());
                result.put("status", csRefund.getCsrStatus());
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.add("data", result))), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取会员消费明细列表
     * 
     * @return
     */
    public String getUserRecord() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            Integer type = $.getInteger("type"); // 0:保证金 1:现金券
            LzMap param = $.add(CsRecord.F.csrMember, member.getCsmId());
            if (type != null) {
                param.add(CsRecord.F.csrMoneyType, type);
            }

            Page<CsRecord> page =
                    csRecordService.getCsRecordPage($.getInteger("page", 0), 10, param);
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            for (CsRecord data : page.getResult()) {
                Map<String, Object> datamap = new HashMap<String, Object>();
                datamap.put("id", data.getCsrId());
                datamap.put("subjectName", data.get$csrRecordSubject().getCsrsName());
                datamap.put("recordAmount", data.getCsrAmount());
                datamap.put("remain", data.getCsrRemain());
                datamap.put("addTime", data.getCsrAddTime());
                datamap.put("type", data.getCsrMoneyType());
                dataList.add(datamap);
            }

            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 是否已购买过“某”宝贝
     * 
     * @return
     */
    boolean isPurchasedItem(CsItem item, Long memberId) {
        return false;

        // CsRecord record =
        // csRecordService.getCsRecord($.add(CsRecord.F.csrMember, memberId)
        // .add(CsRecord.F.csrRelate, item.getCsiId())
        // .add(CsRecord.F.csrObject,
        // CsItem.class.getSimpleName()+"@"+item.getCsiId())
        // .add("definex", "(csr_amount>0)"));
        // if(record!=null && record.getCsrAmount()>0){
        // return true;
        // }
        //
        // CsAlipayRecord alipayRecord =
        // csAlipayRecordService.getCsAlipayRecord($.add(CsAlipayRecord.F.csarMember,
        // memberId)
        // .add(CsAlipayRecord.F.csarBuyItem, item.getCsiId())
        // .add(CsAlipayRecord.F.csarStatus, 1));
        // if(alipayRecord!=null){
        // return true;
        // }
        //
        // return false;
    }

    /**
     * 获取现金券产品
     * 
     * @return
     */
    public String getPrefCoupon() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            int count = $.or($.getInteger("count"), 1);

            CsArgument csArgument = csArgumentService
                    .getCsArgument($.add(CsArgument.F.csaFlag, "coupon_activity_end_time"));
            String endTime = csArgument == null ? "2018-12-31 23:59:59" : csArgument.getCsaValue();

            CsItem item = null;
            // 判断是否有资格购买“北京出行”的活动现金券
            if (Calendar.getInstance().getTime()
                    .before(new DateUtil().StringtoDate(endTime, NORMAL_DATE_FORMAT))) {
                item = csItemService.getCsItem($.add(CsItem.F.csiFlag, CouponBuyUtil.北京出行充值送活动优惠券)
                        .add(CsItem.F.csiStatus, 1));
                if (item != null) {
                    // 判断用户是否是第一次购买
                    if (isPurchasedItem(item, member.getCsmId())) {
                        item = null;
                    }
                }
            }

            if (item == null) {
                item = csItemService.getCsItem(
                        $.add(CsItem.F.csiFlag, CouponBuyUtil.默认优惠券).add(CsItem.F.csiStatus, 1));
            }

            if (item == null) {
                return returnError("101", "没有可用的现金券产品");
            }

            CouponBuyUtil couponBuy = new CouponBuyUtil();
            couponBuy = couponBuy.calculate(item, count, member.getCsmId());

            LzMap data = $.$("itemName", item.getCsiTitle()).add("itemId", item.getCsiId())
                    .add("price", couponBuy.getPrice())
                    // .add("amount", couponBuy.getAmount())
                    .add("amount", 5000).add("totalFee", couponBuy.getNeedPayTotalFee())
                    .add("count", couponBuy.getCount()).add("title", couponBuy.getText())
                    .add("descript", item.getCsiDepict())
                    .add("present", couponBuy.getPresentAmount());
            return $.SendHtml($.json(JsonFormat.success().setData($.Map("map", data))), CHARSET);
        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("105", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 购买现金券产品
     * 
     * @return
     */
    public String submitBuyCoupon() {
        final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
        if (member == null) {
            return returnError("100", "登录授权无效");
        }
        String lockReason = Reason.购买现金券产品.name() + member.getCsmId();
        EventLock eventLock = new EventLock();
        try {

            if (eventLock.tryLock(lockReason, 3000)) {

                Long itemId = $.getLong("itemId");
                final int count = $.or($.getInteger("count"), 1);
                int payType = $.or($.getInteger("payType"), 1); // 1账户余额，2支付宝，3招商银行
                String app = $.getString("app", "plApp");
                if (itemId == null) {
                    return returnError("101", "要购买的宝贝参数不正确");
                }

                CsItem bitem = csItemService
                        .getCsItem($.add(CsItem.F.csiId, itemId).add(CsItem.F.csiStatus, 1));
                if (bitem == null) {
                    return returnError("102", "您购买的现金券已失效");
                }

                // 安全校验：判断一下如果已经购买过“北京出行充值送活动优惠券”，则使用购买默认优惠券
                if (bitem.getCsiFlag().equals(CouponBuyUtil.北京出行充值送活动优惠券)
                        && isPurchasedItem(bitem, member.getCsmId())) {
                    bitem = csItemService.getCsItem($.add(CsItem.F.csiFlag, CouponBuyUtil.默认优惠券)
                            .add(CsItem.F.csiStatus, 1));
                }

                CouponBuyUtil couponBuy = new CouponBuyUtil();
                couponBuy = couponBuy.calculate(bitem, count, member.getCsmId());
                final CsItem item = bitem;
                final double money = couponBuy.getNeedPayTotalFee();
                final double coupon = couponBuy.getAmount();
                if (payType == 1) {
                    // 购买现金券 添加事务
                    csRecordService.executeTransaction(new Function() {

                        @Override
                        public <T> T execute(Object... arg0) {
                            // TODO Auto-generated method stub
                            commonMoneyService.chargeCoupon(member.getCsmId(), item, count);
                            return null;
                        }
                    });
                    return $.SendHtml($.json(JsonFormat.success()), CHARSET);
                } else if (payType == 2) { // 支付宝，参考getPayUrl，支付完成回调详见：
                                           // CommonPayService.updatePaySuccess
                    String noticeUrl =
                            "http://" + $.config("appHost") + "/app/official/alipayNotify.ashx";
                    String out_trade_no =
                            $.date(new Date(), "yyyyMMddHHmmssSSS") + member.getCsmId();
                    String result = commonPayService.executeGetAppAlipayString(member.getCsmId(),
                            money, noticeUrl, app, out_trade_no);
                    CsAlipayRecord csAlipayRecord = csAlipayRecordService
                            .getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
                    csAlipayRecord.csarBuyItem(item.getCsiId());
                    csAlipayRecord.csarBuyCount(couponBuy.getCount());
                    csAlipayRecord.update();

                    return $.SendHtml(
                            $.json(JsonFormat.success().setData($.Map("urlData", result))),
                            CHARSET);
                } else if (payType == 3) { // 招商银行，参考getCmbPayUrl，支付完成回调详见：CMBPayService.updateCMBPayResponse
                    String noticeUrl = $.config("service.domain") + "service/cmb.notice.do";
                    String out_trade_no =
                            $.date(new Date(), "yyyyMMddHHmmssSSS") + member.getCsmId();
                    String result = cmbPayService.executeCMBPayMobileUrl(member.getCsmId(), money,
                            null, app, out_trade_no);
                    CsAlipayRecord csAlipayRecord = csAlipayRecordService
                            .getCsAlipayRecord($.add("csarTradeNo", out_trade_no));
                    csAlipayRecord.csarBuyItem(item.getCsiId());
                    csAlipayRecord.csarBuyCount(couponBuy.getCount());
                    csAlipayRecord.update();

                    return $.SendHtml(
                            $.json(JsonFormat.success().setData(
                                    $.add("urlData", result).add("callbackUrl", noticeUrl))),
                            CHARSET);
                } else {
                    return returnError("104", "您选择的支付方式不正确");
                }
            } else {
                return returnError("105", "您的操作过于频繁，请稍后再试！");
            }

        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("105", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        } finally {
            eventLock.unlock(lockReason);
        }
    }

    /**
     * 兑换现金券
     * 
     * @return
     */
    public String exchangeCoupon() {
        try {
            final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            String couponNumber = $.getString("couponNumber");
            String code = $.getString("validCode");

            if (couponNumber == null || code == null) {
                return returnError("101", "参数不正确");
            }

            // 开始兑换
            int result = commonMoneyService.executeRechangeCard(couponNumber, code,
                    member.getCsmId(), "恭喜您，兑换充值卡成功");
            if (result == 104) {
                return returnError("104", "您输入的卡号或密码不正确！");
            } else if (result == 106) {
                return returnError("106", "该卡的所属人不是您，不能完成兑换！");
            } else if (result == 203) {
                return returnError("203", "该卡状态无效，不能完成兑换！");
            } else if (result == 204) {
                return returnError("204", "不能重复兑换！");
            } else if (result == 205) {
                return returnError("205", "该卡已过有效期，不能完成兑换！");
            } else if (result == 200) {
                return $.SendHtml($.json(JsonFormat.success().setText("兑换成功！")), CHARSET);
            } else {
                return returnError("201", "兑换失败！");
            }

        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("105", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 用户协议接口
     * 
     * @return
     */
    public String getAppProtocol() {
        try {

            int type = $.getInteger("type", 0); // 0:所有，1:banner信息，2:用户协议，3:帮助，4：配置参数
            String ROOT_FLAG = "PLAPP_CONFIG"; // app配置根目录
            String LICENSE_FLAG = "LICENSE"; // 许可协议
            CsContent root = csContentService.getCsContent($.add(CsContent.F.cscFlag, ROOT_FLAG));
            LzMap data = new LzMap();
            if (root == null) {
                return $.SendHtml($.json(JsonFormat.success().setData(data)), CHARSET);
            }
            CsContent content = csContentService.getCsContent($
                    .add(CsContent.F.cscFlag, LICENSE_FLAG)
                    .add(CsContent.F.cscParent, root.getCscId()).add(CsContent.F.cscStatus, 1));
            if (content != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("title", content.getCscTitle());
                map.put("content", content.getCscContent());
                data.put("licenagmt", map);
            }
            return $.SendHtml($.json(JsonFormat.success().setData(data)), CHARSET);
        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("105", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }

    }

    /**
     * 非强制更新版本
     */

    String getVersion(String version) {
        if (version != null && version.indexOf("#") != -1) {
            return version.split("#")[0];
        }
        return version;
    }


    /**
     * 指定强制更新版本
     */

    String getUpdateVersions(String version) {
        if (version != null && version.indexOf("#") != -1) {
            return version.split("#")[1];
        }
        return null;
    }

    /**
     * 批量强制更新版本
     * 
     * @param appVersion 客户端上传的版本号
     * @param version 配置的版本号
     * 
     */

    boolean getForceUpdateVersion(String appVersion, String version) {
        if (version != null && version.indexOf("#") != -1) {
            Integer versionInt = Integer.valueOf(version.split("#")[2]);
            Integer appVersionInt = Integer.valueOf(appVersion);
            if (null != versionInt && null != appVersionInt) {
                return versionInt > appVersionInt;
            }

        }
        return false;
    }

    /**
     * id in ids
     * 
     * @param ids
     * @param id
     * @param prefix
     * @return
     */
    public static boolean in(Object id, String ids, String prefix) {
        if (ids != null && id != null) {
            String[] idsArr = ids.split(prefix == null ? "," : prefix);
            for (String v : idsArr) {
                if (v.trim().equals(id.toString().trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取应用配置信息
     * 
     * @return
     */
    public String getAppConfig() {
        try {
            // final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            // if (member == null) {
            // return returnError("100", "登录授权无效");
            // }

            int type = $.getInteger("type", 0); // 0:所有，1:banner信息，2:用户协议，3:帮助，4：配置参数
            if (type > 6)
                type = 0;

            final String ROOT_FLAG = "PLAPP_CONFIG"; // app配置根目录
            final String BANNER_FLAG = "BANNER"; // 首页BANNER
            final String LICENSE_FLAG = "LICENSE"; // 许可协议
            final String HELP_FLAG = "HELP"; // 帮助
            final String COIN_FLAG = "COIN_HELP";
            CsContent root = csContentService.getCsContent($.add(CsContent.F.cscFlag, ROOT_FLAG));
            LzMap data = new LzMap();
            if (root == null) {
                return $.SendHtml($.json(JsonFormat.success().setData(data)), CHARSET);
            }

            // 首页BANNER
            if (type == 0 || type == 1) {
                List<CsContent> contentList =
                        csContentService.getCsContentList($.add(CsContent.F.cscFlag, BANNER_FLAG)
                                .add(CsContent.F.cscParent, root.getCscId())
                                .add(CsContent.F.cscStatus, 1).add("asc", "csc_order"), 50);
                if (contentList != null) {
                    List<Map<String, Object>> bannerList = new ArrayList<Map<String, Object>>();
                    for (CsContent cont : contentList) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("title", cont.getCscTitle());
                        map.put("picUrl", cont.getCscImages());
                        map.put("linkUrl", cont.getCscContent());
                        bannerList.add(map);
                    }
                    data.put("banners", bannerList);
                }
            }

            // 许可协议
            if (type == 0 || type == 2) {
                CsContent content = csContentService.getCsContent($
                        .add(CsContent.F.cscFlag, LICENSE_FLAG)
                        .add(CsContent.F.cscParent, root.getCscId()).add(CsContent.F.cscStatus, 1));
                if (content != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", content.getCscTitle());
                    map.put("content", content.getCscContent());
                    data.put("licenagmt", map);
                }
            }

            // 帮助
            if (type == 0 || type == 3) {
                CsContent content = csContentService.getCsContent($
                        .add(CsContent.F.cscFlag, HELP_FLAG)
                        .add(CsContent.F.cscParent, root.getCscId()).add(CsContent.F.cscStatus, 1));
                if (content != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", content.getCscTitle());
                    map.put("content", content.getCscContent());
                    data.put("help", map);
                }
            }

            // 红包帮助
            if (type == 0 || type == 5) {
                CsContent content = csContentService.getCsContent($
                        .add(CsContent.F.cscFlag, COIN_FLAG)
                        .add(CsContent.F.cscParent, root.getCscId()).add(CsContent.F.cscStatus, 1));
                if (content != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", content.getCscTitle$());
                    map.put("content", content.getCscContent());
                    data.put("coinHelp", map);
                }
            }

            // 配置参数
            if (type == 0 || type == 4) {

                ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
                int orderTimeMin = 60;
                int appTimerSep = 10;
                int renewOrderTimeMin = 20;
                int preOrderTimeMin = 120;
                try {
                    CsArgument orderTimeMinArg = csArgumentService
                            .getCsArgument($.add(CsArgument.F.csaFlag, "orderTimeMin"));
                    CsArgument appTimerSepArg = csArgumentService
                            .getCsArgument($.add(CsArgument.F.csaFlag, "appTimerSep"));
                    CsArgument renewOrderTimeMinArg = csArgumentService
                            .getCsArgument($.add(CsArgument.F.csaFlag, "renewOrderTimeMin"));
                    CsArgument preOrderTimeMinArg = csArgumentService
                            .getCsArgument($.add(CsArgument.F.csaFlag, "preOrderTimeMin"));

                    orderTimeMin =
                            (orderTimeMinArg != null && orderTimeMinArg.getCsaValue() != null)
                                    ? Integer.valueOf(orderTimeMinArg.getCsaValue())
                                    : orderTimeMin;
                    appTimerSep = (appTimerSepArg != null && appTimerSepArg.getCsaValue() != null)
                            ? Integer.valueOf(appTimerSepArg.getCsaValue())
                            : appTimerSep;
                    renewOrderTimeMin = (renewOrderTimeMinArg != null
                            && renewOrderTimeMinArg.getCsaValue() != null)
                                    ? Integer.valueOf(renewOrderTimeMinArg.getCsaValue())
                                    : renewOrderTimeMin;
                    preOrderTimeMin =
                            (preOrderTimeMinArg != null && preOrderTimeMinArg.getCsaValue() != null)
                                    ? Integer.valueOf(preOrderTimeMinArg.getCsaValue())
                                    : preOrderTimeMin;
                } catch (Exception e) {
                    Logger.getRootLogger().error(e);
                }

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("orderTimeMin", orderTimeMin); // 起租时长 ，默认：60分钟
                map.put("appTimerSep", appTimerSep); // 时间控件间隔，默认：10分钟
                map.put("renewOrderTimeMin", renewOrderTimeMin); // 最短续订时间，默认：20分钟
                map.put("preOrderTimeMin", preOrderTimeMin); // 异地借还可提前预定时间，默认：120分钟
                data.put("args", map);
            }

            // 获取app更新配置
            if (type == 0 || type == 6 || type == 4) {
                Integer appType = $.getInteger("appType", 1);
                String appVersion = $.getString("version", "1000");
                Map<String, Object> map = new HashMap<String, Object>();
                int upgradeFlag = 0;
                if (appType == 1) { // 1安卓
                    CsUpdate update = csUpdateService
                            .getCsUpdate($.add(CsUpdate.F.csuType, 0).add(CsUpdate.F.csuStatus, 1));
                    if (update != null) {
                        String versionList = getUpdateVersions(update.getCsuVersion$());
                        if (versionList != null && in(appVersion, versionList, ",")) { // 匹配需要强制更新的版本如：2.3.0,2.4.0
                            upgradeFlag = 1;
                        }
                        if (getForceUpdateVersion(appVersion, update.getCsuVersion$())) {
                            upgradeFlag = 1;
                        }
                        map.put("version", getVersion(update.getCsuVersion$()));
                        map.put("fileUrl", update.getCsuFile());
                        map.put("content", update.getCsuDescript()); // 1强制更新 0不更新
                    } else {

                        map.put("content", "");
                    }
                } else {// IOS

                    CsUpdate update = csUpdateService
                            .getCsUpdate($.add(CsUpdate.F.csuType, 1).add(CsUpdate.F.csuStatus, 1));
                    if (update != null) {
                        String versionList = getUpdateVersions(update.getCsuVersion$());
                        if (versionList != null && in(appVersion, versionList, ",")) { // 匹配需要强制更新的版本如：2.3.0,2.4.0
                            upgradeFlag = 1;
                        }
                        if (getForceUpdateVersion(appVersion, update.getCsuVersion$())) {
                            upgradeFlag = 1;
                        }
                    }

                    map.put("content", update != null ? update.getCsuDescript() : "");
                }
                map.put("upgradeFlag", upgradeFlag);
                data.put("update", map);
            }

            //
            // CsUpdate update = csUpdateService.getCsUpdate($.add(CsUpdate.F.csuType, 0));
            // if(update!=null){
            // LzMap map = $.add("version", update.getCsuVersion()).add("fileUrl",
            // update.getCsuFile());
            // data.put("update", map);
            // }

            return $.SendHtml($.json(JsonFormat.success().setData(data)), CHARSET);

        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("105", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    public String getAndroidApp() {
        try {
            CsUpdate update = csUpdateService.getCsUpdate($.add(CsUpdate.F.csuType, 0));
            if (update != null) {
                ServletActionContext.getResponse().sendRedirect(update.getCsuFile());
            } else {
                return returnError("100", "文件地址丢失！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * APP端获取支付招行url
     * 
     * @return
     */
    public String getCmbPayUrl() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Double amount = $.getDouble("amount");
            String app = $.getString("app", "");
            if (amount == null) {
                return returnError("101", "参数无效");
            }

            String noticeUrl = $.config("service.domain") + "service/cmb.notice.do";
            String result =
                    cmbPayService.executeCMBPayMobileUrl(member.getCsmId(), amount, null, app);
            return $.SendHtml(
                    $.json(JsonFormat.success()
                            .setData($.add("urlData", result).add("callbackUrl", noticeUrl))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    // ===========================================约租车=====================================================
    final static Double mileagePrice = ICommonUnitService.mileagePrice; // 里程单价

    /**
     * 获取约租车目的地址
     * 
     * @return
     */
    public String getRentGoalAddress() {
        try {
            return $.SendHtml(AddressList.json, CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取
     * 
     * @return
     */
    public String getRentConfig() {
        try {
            return $.SendHtml(
                    $.json(JsonFormat.success().setData($.$("mileagePrice", mileagePrice))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 约租车提交订单
     * 
     * @return
     */
    public String submitRentOrder() {
        try {

            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            final Date startTime = $.getDate("startTime");
            final Date finishTime = $.getDate("finishTime", startTime);
            final String fromLatitude = $.getString("fromLatitude"); // 纬度
            final String fromLongitude = $.getString("fromLongitude"); // 经度
            final String toLatitude = $.getString("toLatitude"); // 纬度
            final String toLongitude = $.getString("toLongitude"); // 经度
            final String fromAddr = $.getString("fromAddr"); // 出发地点
            final String toAddr = $.getString("toAddr"); // 到达地点
            final String profile = $.getString("profile"); // 用车需求
            final Short type = $.getShort("type", (short) 1); // 类型：1:一般公务
                                                              // 2:紧急公务
            final Long carLevel = $.getLong("carLevel"); // 预定车型：前台传递的参数为1或者2或者3，其中1代表舒适型，2代表商务型,
            final Double milage = $.getDouble("milage"); // 预估里程
            final Short isShare = $.getShort("share"); // 是否拼单
            final Double amount = mileagePrice * milage * (isShare.intValue() == 1 ? 0.5 : 1); // 预估金额
            final String mobile = $.getString("mobile"); // 乘车人联系电话
            final String message = $.json($.Map("share", isShare).add("profile", profile));

            final CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("106", "用户未绑定企业用户");

            // 找到自己所在的组，给组长发消息用
            CsUnitGroup group = CsUnitGroup.getCsUnitGroupById(person.getCsupGroup());
            if (group == null)
                return returnError("107", "用户未绑定分组");

            CsUnitPerson auditPerson = CsUnitPerson.get(group.getCsugPerson());

            CsUnitOrder unitOrder = csStateService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... object) {
                    final CsUnitOrder unitOrder = commonUnitService.executeAddSpecialOrder(
                            person.getCsupId(), profile, startTime, finishTime, type,
                            fromLatitude + ";" + fromLongitude, toLatitude + ";" + toLongitude,
                            fromAddr, toAddr, message, carLevel, mobile, null);
                    unitOrder.setCsuoMileage(milage);
                    unitOrder.setCsuoState((short) 2); // 直接审核通过
                    unitOrder.update();

                    new CsSpecialCar(unitOrder.getCsuoSpecial()).csscStatus((short) 2) // 状态是“已下单”
                            .csscEstimateFee(amount) // 预估费用
                            .csscPreditMileage(milage) // 预估里程
                            .csscIsCarpool(isShare) // 是否拼车
                            .csscDemand(profile) // 用车需求
                            .update();

                    StringBuffer sbf = new StringBuffer();
                    sbf.append("订单从" + fromAddr + "到" + toAddr);

                    final String startTimeStr =
                            new DateUtil().dateToString(startTime, NORMAL_DATE_FORMAT);
                    final String finishTimeStr =
                            new DateUtil().dateToString(finishTime, NORMAL_DATE_FORMAT);

                    // 找到所有上线状态（并且订单时间上没有冲突）司机，发送订单信息
                    ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
                    StringBuffer sql = new StringBuffer();
                    // sql.append(" csd_id not in ");
                    // sql.append("(");
                    // sql.append(" select csdc_driver from cs_driver_car cdc ");
                    // sql.append(" where ");
                    // // sql.append(" and (");
                    // //
                    // sql.append(" (cssc.cssc_start_time <='").append(startTimeStr).append("' and
                    // cssc.cssc_finish_time >'").append(startTimeStr).append("')");
                    // // sql.append(" or ");
                    // //
                    // sql.append(" (cssc.cssc_start_time >='").append(startTimeStr).append("' and
                    // cssc.cssc_finish_time >'").append(startTimeStr).append("')");
                    // // sql.append(" )");
                    // sql.append(" csdc_state !=2 and csdc_status=1 ");
                    // sql.append(")");

                    sql.append(
                            " exists ( select 1 from cs_driver_car where csdc_driver=csd_id and csdc_state !=2 and csdc_status=1 ) ");

                    final List<CsDriver> driverList =
                            csDriverService.getCsDriverList($.add("definex", sql.toString()), -1);

                    ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
                    singleThreadPool.execute(new Runnable() {

                        @Override
                        public void run() {
                            CacheStoreHelper.setDriverList(
                                    String.valueOf(unitOrder.getCsuoSpecial()), driverList);
                            for (CsDriver driver : driverList) {
                                Map<String, String> extra = new HashMap<String, String>();
                                try {
                                    extra.put("type", "1"); // 0实时单，1预约单
                                    extra.put("startTtime", String.valueOf(startTime.getTime())); // 开始时间
                                    extra.put("finishTime", String.valueOf(finishTime.getTime())); // 结束时间
                                    extra.put("fromAddr", fromAddr); // 出发地址
                                    extra.put("toAddr", toAddr); // 目的地
                                    extra.put("fromLat", fromLatitude); // 出发地纬度
                                    extra.put("fromLng", fromLongitude); // 出发地经度
                                    extra.put("toLng", toLongitude); // 目的地经度
                                    extra.put("toLat", toLatitude); // 目的地纬度
                                    extra.put("milage", String.valueOf(milage)); // 预估里程
                                    extra.put("isCarPool", String.valueOf(isShare)); // 是否拼车，1拼车0不拼车
                                    extra.put("profile", String.valueOf(isShare)); // 乘车需求
                                    extra.put("specId", String.valueOf(unitOrder.getCsuoSpecial())); // 订单ID
                                    // extra.put("distance",
                                    // LocateConverter.getDistance(fromLongitude,
                                    // fromLatitude, toLongitude,
                                    // toLatitude));
                                    Logger.getLogger("alipay")
                                            .error("订单ID ：" + unitOrder.getCsuoSpecial() + " 手机号:"
                                                    + driver.getCsdMobile());
                                    JpushClientHelper.sendMessageWithTag(driver.getCsdMobile(),
                                            message, extra, JpushConfig.OFC_DRIVER_APP);
                                } catch (Exception e) {
                                    rentAppLogger.error(
                                            "submitRentOrder error! data:\t" + $.json(extra), e);
                                    Logger.getRootLogger().error(e.getMessage(), e);
                                }
                            }
                        }
                    });

                    return (T) unitOrder;
                }
            });

            return $.SendHtml($.json(JsonFormat.success().setData($.$("unitOrderId", ""))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 计算取消约租车订单
     * 
     * @return
     */
    public String calcCancelRentOrder() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            Long specialId = $.getLong("specialId");
            if (specialId == null) {
                return returnError("101", "参数无效");
            }

            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
            final CsSpecialCar specialCar = csSpecialCarService.getCsSpecialCarById(specialId);
            if (specialCar == null) {
                return returnError("102", "订单无效");
            }

            if ((specialCar.getCsscStatus().intValue() > 3)
                    && (specialCar.getCsscStatus().intValue() != 8)) {
                return returnError("102", "订单无效");
            }

            UnitOrderInfo unitOrderInfo = commonUnitService
                    .execulateCalcRentOrder(specialCar.getCsscUnitOrder(), new Date(), null, true);
            Double totalFee = 0d;
            if (unitOrderInfo != null) {
                totalFee = unitOrderInfo.getPayNeed();
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.add("totalFee", totalFee))),
                    CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 取消约租车订单
     * 
     * @return
     */
    public String cancelRentOrder() {
        try {
            final CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsUnitPerson unitPerson =
                    CsUnitPerson.Get($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
            if (unitPerson == null) {
                return returnError("101", "会员不是企业账户");
            }

            final Long specialId = $.getLong("specialId");
            final String remark = $.getString("remark");
            if (specialId == null) {
                return returnError("101", "参数无效");
            }

            ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
            final CsSpecialCar specialCar = csSpecialCarService.getCsSpecialCarById(specialId);
            if (specialCar == null) {
                return returnError("102", "订单无效");
            }

            if ((specialCar.getCsscStatus().intValue() > 3)
                    && (specialCar.getCsscStatus().intValue() != 8)) {
                return returnError("102", "订单无效");
            }

            csSpecialCarService.executeTransaction(new Function() {

                @Override
                public <T> T execute(Object... object) {
                    // TODO Auto-generated method stub
                    commonUnitService.executeCancelRentOrder(specialCar.getCsscUnitOrder(),
                            member.getCsmId(), remark);
                    // 已接单订单取消，推送给指定司机通知
                    if (specialCar.getCsscStatus().intValue() == 3
                            || specialCar.getCsscStatus().intValue() == 8) {
                        Map<String, String> extra = new HashMap<String, String>();
                        try {
                            JpushClientHelper.sendAlertWithTag(
                                    specialCar.get$csscDrivercar().get$csdcDriver().getCsdMobile$(),
                                    "订单取消", specialCar.getCsscAddTime$()
                                            + specialCar.getCsscStartAddr$() + "订单已取消，请重新接单",
                                    JpushConfig.OFC_DRIVER_APP);
                        } catch (Exception e) {
                            rentAppLogger.error("submitRentOrder error! data:\t" + $.json(extra),
                                    e);
                            Logger.getRootLogger().error(e.getMessage(), e);
                        }
                        return null;
                    }
                    // 未必接单订单取消，推送给所有司机
                    List<CsDriver> driverList =
                            CacheStoreHelper.getDriverList(String.valueOf(specialId));
                    if (driverList == null)
                        return null;
                    for (CsDriver csDriver : driverList) {
                        Map<String, String> extra = new HashMap<String, String>();
                        try {
                            extra.put("type", "3"); // 0实时单，1预约单 2订单被接 3订单取消
                            extra.put("specId", String.valueOf(specialId)); // 订单ID
                            Logger.getLogger("alipay").error(
                                    "取消订单ID ：" + specialId + " 手机号:" + csDriver.getCsdMobile());
                            JpushClientHelper.sendMessageWithTag(csDriver.getCsdMobile(),
                                    "{\"title\":\"订单取消\"}", extra, JpushConfig.OFC_DRIVER_APP);
                        } catch (Exception e) {
                            rentAppLogger.error("submitRentOrder error! data:\t" + $.json(extra),
                                    e);
                            Logger.getRootLogger().error(e.getMessage(), e);
                        }

                    }
                    return null;
                }
            });
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 获取套餐产品列表
     * 
     * @return
     */
    public String getMeals() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            List<CsItem> itemList =
                    csItemService.getCsItemList($.add(CsItem.F.csiFlag, "201604MEAL")
                            .add(CsItem.F.csiStatus, 1).add("asc", "csi_data_number"), -1);

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("101", "用户未绑定企业用户");

            Date date = $.getDate("start");

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            for (CsItem item : itemList) {
                MealExpress me = MealHelper.parseExpress(item.getCsiDepict());
                if (StringUtils.isNotEmpty(me.getTag())) {
                    if ($.or(person.getCsupFlag(), "").indexOf(me.getTag()) == -1) {
                        continue;
                    }
                }

                System.out.println(item.getCsiTitle());
                List<CsOrder> orderList = MealHelper.calcMealOrders(item.getCsiDepict(), date);
                List<Map<String, Object>> ordersList = new ArrayList<Map<String, Object>>();
                // for(CsOrder o : orderList){
                // Map<String, Object> map = new HashMap<String, Object>();
                // map.put("start", o.getCsoStartTime().getTime());
                // map.put("finish", o.getCsoStartTime().getTime());
                // ordersList.add(map);
                // }

                Calendar cal = Calendar.getInstance();
                Calendar finishcal = Calendar.getInstance();
                cal.setTime(orderList.get(0).getCsoStartTime());
                finishcal.setTime(orderList.get(orderList.size() - 1).getCsoFinishTime());
                for (int i = 0; i < 30; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (i > 0) {
                        cal.add(Calendar.HOUR_OF_DAY, me.getSep());
                        finishcal.add(Calendar.HOUR_OF_DAY, me.getSep());
                    }
                    map.put("start", cal.getTime());
                    map.put("finish", finishcal.getTime());

                    System.out.println("开始时间:"
                            + new DateUtil().dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss")
                            + "\t" + "结束时间:" + new DateUtil().dateToString(finishcal.getTime(),
                                    "yyyy-MM-dd HH:mm:ss"));
                    ordersList.add(map);
                }

                LzMap data = $.$("itemName", item.getCsiTitle()).add("itemId", item.getCsiId())
                        .add("price", item.getCsiPrice()).add("title", item.getCsiTitle())
                        .add("descript", item.getCsiRemark()).add("orders", ordersList)
                        .add("time1", me.getFeature("time1")).add("time2", me.getFeature("time2"));

                dataList.add(data);
            }

            return $.SendHtml($.json(JsonFormat.success().setData($.add("list", dataList))),
                    CHARSET);
        } catch (com.ccclubs.exception.MessageException oe) {
            oe.printStackTrace();
            Logger.getRootLogger().error(oe);
            return returnError("105", oe.getMessage());
        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 他人预订
     * 
     * @param
     * @author just
     * @return
     */
    public String otherBook() {

        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Long takeOutletsId = $.getLong("takeOutletsId");

            if (takeOutletsId == null) {
                return returnError("101", "取车网点无效");
            }

            Date takeTime = $.getDate("takeTime");

            if (takeTime == null) {
                return returnError("102", "请选择预定开始时间");
            }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("103", "用户未绑定企业用户");
            CsUnitInfo unitInfo = person.get$csupInfo();
            if (unitInfo == null)
                return returnError("104", "用户未绑定单位");
            List<CsOutlets> outletsList = unitInfo.get$csuiOutlets();
            if (CollectionUtils.isEmpty(outletsList))
                return returnError("105", "用户单位没有可用网点");

            CsArgument argument = csArgumentService
                    .getCsArgument($.add(CsArgument.F.csaFlag, ArgumentKey.OTHER_BOOK_TIME));

            java.text.SimpleDateFormat formatter =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(takeTime);
            calendar.add(java.util.Calendar.MINUTE,
                    argument != null ? Integer.parseInt(argument.getCsaValue()) : -15);
            String dt_end = formatter.format(calendar.getTime());

            Page<CsOrder> page = csOrderService.getCsOrderPage($.getInteger("page", 0), 5, $.add(
                    "definex",
                    "( (csuo.csuo_state is null || csuo.csuo_state = 2) and cso_use_member != '"
                            + member.getCsmId$() + "' and cso_start_time BETWEEN  '" + dt_end
                            + "'  and  '" + $.date(takeTime, "yyyy-MM-dd") + " 23:59:59'  )")
                    .add(CsOrder.F.csoOutlets, takeOutletsId).add(CsOrder.F.csoStatus, 0)
                    .add("asc", "cso_start_time")
                    .add("join", "LEFT JOIN cs_unit_order csuo on cso_id = csuo.csuo_order"));
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

            for (CsOrder order : page.getResult()) {
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("id", order.getCsoId());

                // 车辆信息
                data.put("carno", order.get$csoCar().getCscCarNo());
                data.put("carmodelImage", order.get$csoCar().get$cscModel().getCscmImage());
                data.put("carmodelname", order.get$csoCar().get$cscModel().getCscmName$());

                // 网点信息
                data.put("outletsId", order.get$csoOutlets().getCsoId());
                data.put("csoName", order.get$csoOutlets().getCsoName());

                // 租车人信息
                data.put("userName", order.get$csoUseMember().getCsmName$());
                data.put("personMoblie", order.get$csoUseMember().getCsmMobile$());
                data.put("groupName", person.get$csupGroup().getCsugName());
                // 订单信息时间
                data.put("startTime", order.getCsoStartTime());

                dataList.add(data);
            }
            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);

        } catch (Exception ex) {
            return returnError(ex);
        }
    }

    /**
     * 提交产品建议 订单评论
     * 
     * @return
     */
    public String putComplain() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            CsComplain complain = new CsComplain();

            // 1 订单 2 帮助与反馈
            String source = $.getString("source") == null ? "2" : $.getString("source");

            Long orderId = $.getLong("orderId");

            if (source.equals("1") && orderId == null) {
                return returnError("101", "订单号不能为空");
            }

            String starLevel = $.getString("starlevel");
            String starLevel1 = $.getString("starlevel1");

            if (source.equals("1") && starLevel == null) {
                return returnError("102", "星级评分不能为空");
            }

            if (source.equals("1") && starLevel1 == null) {
                return returnError("102", "星级评分不能为空");
            }

            // type 3产品建议 4驾驶体验
            String type = $.getString("type");

            String content = $.getString("context");// 内容

            complain.setCscObject(
                    orderId == null || orderId.equals("") ? "" : "CsOrder@" + orderId);// 关联订单
            complain.setCscLevel(
                    starLevel == null || starLevel.equals("") ? 0 : Short.parseShort(starLevel));// 星级
            complain.setCscLevel1(
                    starLevel1 == null || starLevel1.equals("") ? 0 : Short.parseShort(starLevel1));
            complain.setCscHost(member.getCsmHost());
            complain.setCscAddTime(new Date());
            complain.setCscType((Short) (type == null ? 3 : Short.parseShort(type)));
            complain.setCscContent(content);
            complain.setCscTitle("");
            complain.setCscAccept((short) 0);
            complain.setCscStatus((short) 1);
            complain.setCscMember(member.getCsmId());
            complain.setCscUpdateTime(new Date());
            complain.setCscSource(source);
            csComplainService.saveCsComplain(complain);

            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 获取用户统计信息
     * 
     * @return
     */
    public String getMemberSumm() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            CsMemberSummary csMemberSummary = csMemberSummaryService
                    .getCsMemberSummary($.add(CsMemberSummary.F.csmsMember, member.getCsmId()));

            LzMap data = new LzMap();
            data.add("join_time", member.getCsmAddTime());

            data.add("ord_total", csMemberSummary == null ? 0 : csMemberSummary.getCsmsOrdTotal());
            data.add("unitord_total",
                    csMemberSummary == null ? 0 : csMemberSummary.getCsmsUnitordTotal());
            data.add("order_total",
                    csMemberSummary == null ? 0
                            : csMemberSummary.getCsmsOrdTotal() + (csMemberSummary == null ? 0
                                    : csMemberSummary.getCsmsUnitordTotal()));

            Double d_ord_time_long = Double.parseDouble(csMemberSummary == null ? "0"
                    : csMemberSummary.getCsmsOrdTimelong().toString());
            BigDecimal bigDecimal = new BigDecimal(d_ord_time_long / 60);
            data.add("ord_time_long", bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP));

            Double d_unitord_time = Double.parseDouble(csMemberSummary == null ? "0"
                    : csMemberSummary.getCsmsUnitordTimelong().toString());
            bigDecimal = new BigDecimal(d_unitord_time / 60);
            data.add("unitord_time", bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP));

            bigDecimal = new BigDecimal((d_ord_time_long + d_unitord_time) / 60);
            data.add("time_total", bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP));

            data.add("ord_mileage",
                    csMemberSummary == null ? 0 : csMemberSummary.getCsmsOrdMileages());
            data.add("unitord_mileage",
                    csMemberSummary == null ? 0 : csMemberSummary.getCsmsUnitordMileages());
            data.add("mileage_total",
                    csMemberSummary == null ? 0
                            : csMemberSummary.getCsmsOrdMileages()
                                    + csMemberSummary.getCsmsUnitordMileages());

            data.add("ord_amount",
                    csMemberSummary == null ? 0 : csMemberSummary.getCsmsOrdAmount());
            data.add("unitord_amount",
                    csMemberSummary == null ? 0 : csMemberSummary.getCsmsUnitordAmount());
            data.add("amount_total", csMemberSummary == null ? 0
                    : csMemberSummary.getCsmsOrdAmount() + csMemberSummary.getCsmsUnitordAmount());

            return $.SendHtml($.json(JsonFormat.success().setData($.$("member_summary", data))),
                    CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 红包列表 type 1正常 2过期
     * 
     * @return
     */
    public String getCoinList() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            String type = $.getString("type");

            if (type == null || type.equals("")) {
                return returnError("101", "红包类型不能为空");
            }

            List<CsCoin> coinList;
            if (type.equals("1")) {
                coinList = csCoinService.getCsCoinList($.add(CsCoin.F.cscStatus, 1)
                        .add("definex", "( 1=1 and " + CsCoin.C.cscEnd + " >= '"
                                + $.date(new Date(), "yyyy-MM-dd") + "' and csc_remain > 0)")
                        .add(CsCoin.F.cscMember, member.getCsmId())
                        .add(CsCoin.F.cscHost, member.getCsmHost()).add("asc", CsCoin.C.cscEnd),
                        -1);
            } else {
                coinList = csCoinService.getCsCoinList(
                        $.add("definex", "( 1=1 and " + CsCoin.C.cscEnd + " <= '"
                                + $.date(new Date(), "yyyy-MM-dd") + "' and  csc_remain > 0)")
                                .add(CsCoin.F.cscMember, member.getCsmId())
                                .add(CsCoin.F.cscHost, member.getCsmHost())
                                .add("asc", CsCoin.C.cscEnd),
                        -1);
            }

            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            Map<String, Object> data;
            for (CsCoin csCoin : coinList) {
                data = new HashMap<String, Object>();
                data.put("type", type);
                data.put("coinId", csCoin.getCscId());
                data.put("cscRemain", csCoin.getCscRemain());// 可用余额
                data.put("cscStartTime", csCoin.getCscAddTime());// 开始时间
                data.put("cscEndTime", csCoin.getCscEnd());// 结束时间
                dataList.add(data);
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.add("list", dataList))),
                    CHARSET);
        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 红包消费记录
     */
    public String getCoinRecord() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            Page<CsUseRecord> page = csUseRecordService.getCsUseRecordPage($.getInteger("page", 0),
                    10, $.add(CsUseRecord.F.csurMember, member.getCsmId())
                            .add(CsUseRecord.F.csurHost, member.getCsmHost()));
            List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
            Map<String, Object> data;
            for (CsUseRecord csUseRecord : page.getResult()) {
                data = new HashMap<String, Object>();
                data.put("orderId", csUseRecord.getCsurOrder());
                data.put("profile", csUseRecord.getCsurProfile());// 科目类型
                data.put("addTime", csUseRecord.getCsurAddTime());
                data.put("count", csUseRecord.getCsurCount());
                dataList.add(data);
            }

            LzMap pagemap = $.$("index", page.getIndex()).add("total", page.getTotal())
                    .add("count", page.getCount()).add("size", page.getSize());
            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", dataList).add("page", pagemap))),
                    CHARSET);

        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 违章查询
     * 
     * @return
     */
    public String getViolat() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }
            // type=2 表示 已交款，已处理
            // type=4 表示 除 已交款，已处理 其他
            Integer type = $.getInteger("status", 2);
            LzMap params =
                    $.add(CsViolat.F.csvMember, member.getCsmId()).add("desc", "csv_happen_time");
            if (type == 2) {
                params.add(CsViolat.F.csvStatus, type);
            } else {
                params.add("definex", "( csv_status != 2 )");
            }

            Double money = 0d;
            Integer score = 0;


            List<CsViolat> violatList = CsViolat.getCsViolatList(params, -1);
            List<Map<String, Object>> mapList =
                    new ArrayList<Map<String, Object>>(violatList.size());
            for (CsViolat csViolat : violatList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("money", $(csViolat.getCsvMoney()));
                money += $(csViolat.getCsvMoney());
                map.put("score", csViolat.getCsvScore());
                score += csViolat.getCsvScore();
                map.put("time", csViolat.getCsvHappenTime());
                map.put("status", csViolat.getCsvStatus());
                map.put("orderId", csViolat.getCsvOrder());
                if (!$.empty(csViolat.getCsvDecipt$())
                        && csViolat.getCsvDecipt$().indexOf("-") != -1) {
                    String address = csViolat.getCsvDecipt().substring(0,
                            csViolat.getCsvDecipt().lastIndexOf("-"));
                    String decipt = csViolat.getCsvDecipt().substring(
                            csViolat.getCsvDecipt().lastIndexOf("-") + 1,
                            csViolat.getCsvDecipt().length());
                    map.put("address", address.trim());
                    map.put("decipt", decipt.trim());
                } else {
                    map.put("address", csViolat.getCsvDecipt$());
                    map.put("decipt", csViolat.getCsvDecipt$());
                }
                mapList.add(map);
            }

            LzMap pagemap =
                    $.add("count", mapList.size()).add("money", $(money)).add("score", score);

            return $.SendHtml($.json(
                    JsonFormat.success().setData($.Map("list", mapList).add("summary", pagemap))),
                    CHARSET);


        } catch (Exception e) {
            // TODO: handle exception
            return returnError(e);
        }
    }

    /**
     * 首页提示
     * 
     * @return
     */

    public String getHomeTip() {
        try {
            CsMember member = OauthUtils.getOauth($.getString("access_token", ""));
            if (member == null) {
                return returnError("100", "登录授权无效");
            }

            // 查询验证信息
            short vstatus = member.getVstatus();
            List<AppTip> tips = new ArrayList<>();

            if (0 == vstatus) {
                // 用户未认证时
                AppTip appTip = new AppTip();
                appTip.setMessage("您还未认证，点击前往认证");
                appTip.setType(AppTip.Type.AUTH_REAL.ordinal());
                appTip.setObjectId(null);
                tips.add(appTip);
            }
            return $.SendHtml($.json(JsonFormat.success().setData($.Map("list", tips))), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }
    }
    
    /**
     * 获取应用配置信息
     * 
     * @return
     */
    public String getContent() {
        try {
            String flag = $.getString("flag", "没你要的数据，哈哈哈");
            Long carId = $.getLong("carId");
            LzMap data = new LzMap();
            
            CsContent content = csContentService.getCsContent($
                    .add(CsContent.F.cscFlag, flag).add(CsContent.F.cscStatus, 1));
           
            if (content != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                String title= content.getCscTitle();
                String explainContent=content.getCscContent();
                //
                if(carId!=null) {
                    CsCar car = csCarService.getCsCarById(carId);
                	//
                    Long host=car.getCscHost();
                    Long model= car.getCscModel();
                    Long outlets=car.getCscOutlets();
                     //
                    CsFeeTypeSet csFeeTypeSet = CsFeeTypeSet.where().csftsHost(host).csftsModel(model).get();
             		
             		List<CsPrice> list = CsPrice.where().cspUserType(csFeeTypeSet.getCsftsDefault()).cspModel(model).cspOutets($.or(outlets,csFeeTypeSet.getCsftsOutlets())).list(-1);
             		if(list.isEmpty())
             			list = CsPrice.where().cspUserType(csFeeTypeSet.getCsftsDefault()).cspModel(model).cspOutets(csFeeTypeSet.getCsftsOutlets()).list(-1);
             		for(CsPrice csPrice:list){
             			CsGoods goods = csPrice.get$cspGoods();
             			if("分钟租金".equals(goods.getCsgName())) {
             				explainContent=explainContent.replace("{code1}", csPrice.getCspPrice()+"");           ;
            			}else if("租金一天".equals(goods.getCsgName())) {
            				explainContent=explainContent.replace("{code2}", csPrice.getCspPrice()+"");    
            			}else if("夜租一".equals(goods.getCsgName())) {
            				String paramsJSON=goods.getCsgProfile();
            				//
            				Map<String, Object> mapParam = (Map)APICallHelper.fromJson(paramsJSON);
            				if(mapParam == null || (Map)mapParam.get("params") == null) continue;
            				Map<String, Object> params = (Map)mapParam.get("params");
            				//
            				String startTime = params.get("startTime") == null ? "" : params.get("startTime").toString();
            				String startTimeUp = params.get("startTimeUp") == null ? "" : params.get("startTimeUp").toString();
            				startTime=startTime.split(":")[0];
            				startTimeUp=startTimeUp.split(":")[0];
            				explainContent=explainContent.replace("{code31}",startTime).replace("{code32}",startTimeUp);
            			
            				//
            				explainContent=explainContent.replace("{code3}", csPrice.getCspPrice()+"");    
            			}else if("夜租二".equals(goods.getCsgName())) {
            				String paramsJSON=goods.getCsgProfile();
            				//
            				Map<String, Object> mapParam = (Map)APICallHelper.fromJson(paramsJSON);
            				if(mapParam == null || (Map)mapParam.get("params") == null) continue;
            				Map<String, Object> params = (Map)mapParam.get("params");
            				//
            				String startTime = params.get("startTime") == null ? "" : params.get("startTime").toString();
            				String startTimeUp = params.get("startTimeUp") == null ? "" : params.get("startTimeUp").toString();
            				startTime=startTime.split(":")[0];
            				startTimeUp=startTimeUp.split(":")[0];
            				
            				explainContent=explainContent.replace("{code41}",startTime).replace("{code42}",startTimeUp);
            				//
            				explainContent=explainContent.replace("{code4}", csPrice.getCspPrice()+"");   
            			}else if("夜租三".equals(goods.getCsgName())) {
            				String paramsJSON=goods.getCsgProfile();
            				//
            				Map<String, Object> mapParam = (Map)APICallHelper.fromJson(paramsJSON);
            				if(mapParam == null || (Map)mapParam.get("params") == null) continue;
            				Map<String, Object> params = (Map)mapParam.get("params");
            				//
            				String startTime = params.get("startTime") == null ? "" : params.get("startTime").toString();
            				
            				startTime=startTime.split(":")[0];
            				explainContent=explainContent.replace("{code51}",startTime);
            				//
            				explainContent=explainContent.replace("{code5}", csPrice.getCspPrice()+"");    
            			}
             		}
                }
               data.put("title",title);
               data.put("content", explainContent);
       
            }

            return $.SendHtml($.json(JsonFormat.success().setData(data)), CHARSET);
        } catch (Exception ex) {
            return $.SendHtml($.json(JsonFormat.error("-1", "网络繁忙")), CHARSET);
        }
    }

    public Double $(Double value) {
        if (value == null)
            return 0d;
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void ctrl() {
        boolean isTest = $.getBoolean("test");
        ApiBase.isTest = isTest;
        $.SendHtml("success", "utf-8");
    }

    public void useYidao() {
        ApiBase.useYidao = $.getInteger("a").intValue() == 1 ? true : false;
    }

    public void useState() {
        ApiBase.useState = $.getInteger("a").intValue() == 1 ? true : false;
    }

    public ISrvPropertyService getSrvPropertyService() {
        return srvPropertyService;
    }

    public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
        this.srvPropertyService = srvPropertyService;
    }

    public ICsMemberService getCsMemberService() {
        return csMemberService;
    }

    public void setCsMemberService(ICsMemberService csMemberService) {
        this.csMemberService = csMemberService;
    }

    public ICsOutletsService getCsOutletsService() {
        return csOutletsService;
    }

    public void setCsOutletsService(ICsOutletsService csOutletsService) {
        this.csOutletsService = csOutletsService;
    }

    public ICommonMoneyService getCommonMoneyService() {
        return commonMoneyService;
    }

    public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
        this.commonMoneyService = commonMoneyService;
    }

    public ICommonDisposeService getCommonDisposeService() {
        return commonDisposeService;
    }

    public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
        this.commonDisposeService = commonDisposeService;
    }

    public ICommonOrderService getCommonOrderService() {
        return commonOrderService;
    }

    public void setCommonOrderService(ICommonOrderService commonOrderService) {
        this.commonOrderService = commonOrderService;
    }

    public ICsOrderService getCsOrderService() {
        return csOrderService;
    }

    public void setCsOrderService(ICsOrderService csOrderService) {
        this.csOrderService = csOrderService;
    }

    public ICsCarService getCsCarService() {
        return csCarService;
    }

    public void setCsCarService(ICsCarService csCarService) {
        this.csCarService = csCarService;
    }

    public ICsGoodsService getCsGoodsService() {
        return csGoodsService;
    }

    public void setCsGoodsService(ICsGoodsService csGoodsService) {
        this.csGoodsService = csGoodsService;
    }

    public ICsPriceService getCsPriceService() {
        return csPriceService;
    }

    public void setCsPriceService(ICsPriceService csPriceService) {
        this.csPriceService = csPriceService;
    }

    public ICsUserTypeService getCsUserTypeService() {
        return csUserTypeService;
    }

    public void setCsUserTypeService(ICsUserTypeService csUserTypeService) {
        this.csUserTypeService = csUserTypeService;
    }

    public ICsProductService getCsProductService() {
        return csProductService;
    }

    public void setCsProductService(ICsProductService csProductService) {
        this.csProductService = csProductService;
    }

    public ICsUpdateService getCsUpdateService() {
        return csUpdateService;
    }

    public void setCsUpdateService(ICsUpdateService csUpdateService) {
        this.csUpdateService = csUpdateService;
    }

    public ICsMemberInfoService getCsMemberInfoService() {
        return csMemberInfoService;
    }

    public void setCsMemberInfoService(ICsMemberInfoService csMemberInfoService) {
        this.csMemberInfoService = csMemberInfoService;
    }

    public ICsUnitOrderService getCsUnitOrderService() {
        return csUnitOrderService;
    }

    public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
        this.csUnitOrderService = csUnitOrderService;
    }

    public ICsPowerPileService getCsPowerPileService() {
        return csPowerPileService;
    }

    public void setCsPowerPileService(ICsPowerPileService csPowerPileService) {
        this.csPowerPileService = csPowerPileService;
    }

    public ICsMemberShipService getCsMemberShipService() {
        return csMemberShipService;
    }

    public void setCsMemberShipService(ICsMemberShipService csMemberShipService) {
        this.csMemberShipService = csMemberShipService;
    }

    public ICsEvCardService getCsEvCardService() {
        return csEvCardService;
    }

    public void setCsEvCardService(ICsEvCardService csEvCardService) {
        this.csEvCardService = csEvCardService;
    }

    public ICsUnitGroupService getCsUnitGroupService() {
        return csUnitGroupService;
    }

    public void setCsUnitGroupService(ICsUnitGroupService csUnitGroupService) {
        this.csUnitGroupService = csUnitGroupService;
    }

    public ICsRefundService getCsRefundService() {
        return csRefundService;
    }

    public void setCsRefundService(ICsRefundService csRefundService) {
        this.csRefundService = csRefundService;
    }

    public ICommonUnitService getCommonUnitService() {
        return commonUnitService;
    }

    public void setCommonUnitService(ICommonUnitService commonUnitService) {
        this.commonUnitService = commonUnitService;
    }

    public ICsUnitMtService getCsUnitMtService() {
        return csUnitMtService;
    }

    public void setCsUnitMtService(ICsUnitMtService csUnitMtService) {
        this.csUnitMtService = csUnitMtService;
    }

    public ICsStateService getCsStateService() {
        return csStateService;
    }

    public void setCsStateService(ICsStateService csStateService) {
        this.csStateService = csStateService;
    }

    public ICsFavoriteAddressService getCsFavoriteAddressService() {
        return csFavoriteAddressService;
    }

    public void setCsFavoriteAddressService(ICsFavoriteAddressService csFavoriteAddressService) {
        this.csFavoriteAddressService = csFavoriteAddressService;
    }

    public ICsRecordService getCsRecordService() {
        return csRecordService;
    }

    public void setCsRecordService(ICsRecordService csRecordService) {
        this.csRecordService = csRecordService;
    }

    public ICsItemService getCsItemService() {
        return csItemService;
    }

    public void setCsItemService(ICsItemService csItemService) {
        this.csItemService = csItemService;
    }

    public ICsAlipayRecordService getCsAlipayRecordService() {
        return csAlipayRecordService;
    }

    public void setCsAlipayRecordService(ICsAlipayRecordService csAlipayRecordService) {
        this.csAlipayRecordService = csAlipayRecordService;
    }

    public ICommonPayService getCommonPayService() {
        return commonPayService;
    }

    public void setCommonPayService(ICommonPayService commonPayService) {
        this.commonPayService = commonPayService;
    }

    public ICsRechargeCardService getCsRechargeCardService() {
        return csRechargeCardService;
    }

    public void setCsRechargeCardService(ICsRechargeCardService csRechargeCardService) {
        this.csRechargeCardService = csRechargeCardService;
    }

    public ICsContentService getCsContentService() {
        return csContentService;
    }

    public void setCsContentService(ICsContentService csContentService) {
        this.csContentService = csContentService;
    }

    public ICMBPayService getCmbPayService() {
        return cmbPayService;
    }

    public void setCmbPayService(ICMBPayService cmbPayService) {
        this.cmbPayService = cmbPayService;
    }

    public ICsArgumentService getCsArgumentService() {
        return csArgumentService;
    }

    public void setCsArgumentService(ICsArgumentService csArgumentService) {
        this.csArgumentService = csArgumentService;
    }

    public ICsComplainService getCsComplainService() {
        return csComplainService;
    }

    public void setCsComplainService(ICsComplainService csComplainService) {
        this.csComplainService = csComplainService;
    }

    public ICsMemberSummaryService getCsMemberSummaryService() {
        return csMemberSummaryService;
    }

    public void setCsMemberSummaryService(ICsMemberSummaryService csMemberSummaryService) {
        this.csMemberSummaryService = csMemberSummaryService;
    }

    public ICsCoinService getCsCoinService() {
        return csCoinService;
    }

    public void setCsCoinService(ICsCoinService csCoinService) {
        this.csCoinService = csCoinService;
    }

    public ICsUseRecordService getCsUseRecordService() {
        return csUseRecordService;
    }

    public void setCsUseRecordService(ICsUseRecordService csUseRecordService) {
        this.csUseRecordService = csUseRecordService;
    }

    public ICsUnitPersonService getCsUnitPersonService() {
        return csUnitPersonService;
    }

    public void setCsUnitPersonService(ICsUnitPersonService csUnitPersonService) {
        this.csUnitPersonService = csUnitPersonService;
    }

    public ICsCreditCardService getCsCreditCardService() {
        return csCreditCardService;
    }

    public void setCsCreditCardService(ICsCreditCardService csCreditCardService) {
        this.csCreditCardService = csCreditCardService;
    }

    public ICsUnderlineMemberService getCsUnderlineMemberService() {
        return csUnderlineMemberService;
    }

    public void setCsUnderlineMemberService(ICsUnderlineMemberService csUnderlineMemberService) {
        this.csUnderlineMemberService = csUnderlineMemberService;
    }



}
