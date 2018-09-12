

package com.ccclubs.action.app.official;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.ccclubs.action.app.official.model.JsonFormat;
import com.ccclubs.action.app.official.util.ApiBase;
import com.ccclubs.action.app.official.util.JpushClientHelper;
import com.ccclubs.action.app.official.util.JpushConfig;
import com.ccclubs.action.app.official.util.OauthUtils;
import com.ccclubs.action.web.BaseOrderInfo;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitMt;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.param.OrderParam;
import com.ccclubs.param.OrderParam4Personal;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsArgumentService;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsComplainService;
import com.ccclubs.service.admin.ICsContentService;
import com.ccclubs.service.admin.ICsCreditCardService;
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
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.service.common.impl.SchedulingService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;

public class OrderAction extends BaseAction {

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
    public String submitPersonalOrder(OrderParam4Personal param) {
        // 用户信息校验
        CsMember member = OauthUtils.getOauth(param.getAccess_token());
        if (member == null) {
            return returnError("100", "登录授权无效");
        }
        // 校验驾驶认证和ev卡
        if (member.getVDrive() != (short) 1 && member.getCsmEvcard() != null) {
            if (member.getVDrive() == (short) 2) {
                // 驾驶认证已经上传，请耐心等待
                return returnError("105", "驾驶认证已经上传待审核，请耐心等待");
            } else {
                // 驾驶认证没有上传
                return returnError("103", "驾驶认证未上传");
            }
        }

        // 取车网点
        Long takeOutletsId = param.getTakeOutletsId();
        // 还车网点（AB借还车，仅提前2小时之内预定）
        Long retOutletsId = param.getRetOutletsId();
        // 车辆
        Long carId = param.getCarId();
        // 预计取车时间
        Date takeTime = param.getTakeTime();
        // 预计还车时间
        Date retTime = param.getRetTime();
        // app版本
        String version = param.getAppVersion();
        // 套餐id
        Long mealId = param.getMealId();
        // 免费时长
        Double freeHours = null;
        // 保险类型
        Long insureType = null;

        // 取还车时间有效性校验
        if (takeTime == null) {
            return returnError("102", "请选择预定开始时间");
        }
        // if (takeTime.before(new Date())) {
        // return returnError("104", "预订时间不能早于当前系统时间");
        // }
        if (retTime == null) {
            return returnError("103", "请选择预定结束时间");
        }
        if (!retTime.after(takeTime)) {
            return returnError("104", "结束时间必须晚于开始时间");
        }
        // AB借还车业务校验
        if (takeOutletsId.longValue() != retOutletsId.longValue()) {
            if (takeTime.getTime() - System.currentTimeMillis() > 120 * 60 * 1000) {
                return returnError("108", "异地借还只能提前2小时内预定");
            }
        }
        // 车辆信息校验，数据库交互后移，减少不必要的数据库负载
        if (carId == null) {
            return returnError("101", "没有选择车辆");
        }
        CsCar car = csCarService.getCsCarById(carId);
        if (car == null)
            return returnError("109", "没有选择车辆");
        if (car.getCscStatus() == (short) 0)
            return returnError("110", "选择的车辆已下线");

        try {
            // 支付人
            Long payMemberId = member.getCsmId();
            // 初始化订单id
            Long orderId = null;
            if (mealId!=null) {
                // 套餐下单流程
                CsOrderCluster orderCluster = commonDisposeService.saveMealOrder(member.getCsmId(),
                        member.getCsmId(), car.getCscId(), takeOutletsId, retOutletsId, takeTime,
                        Long.valueOf(mealId), null, insureType, null, freeHours, 0l, From.APP,
                        "鹏龙app" + version);
                orderId = orderCluster.getCsocId();
            } else {
                // 普通下单流程
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
            // if (takeTime.before(new Date())) {
            // return returnError("104", "预订时间不能早于当前系统时间");
            // }
            if (takeOutletsId.longValue() != retOutletsId.longValue()) {
                if (takeTime.getTime() - System.currentTimeMillis() > 120 * 60 * 1000) {
                    return returnError("108", "异地借还只能提前2小时内预定");
                }
            }
            // if(takeTime.before(new Date())){
            // return returnError("105", "取车时间不能早于当前时间");
            // }

            CsUnitPerson person =
                    CsUnitPerson.getCsUnitPerson($.add("csupMember", member.getCsmId()));
            if (person == null)
                return returnError("106", "用户未绑定企业用户");

            // 找到自己所在的组，给组长发消息用
            CsUnitGroup group = CsUnitGroup.getCsUnitGroupById(person.getCsupGroup());
            if (group == null)
                return returnError("107", "用户未绑定分组");

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

            // 订单状态不正确
            if (order.getCsoStatus() != 0)
                return returnError("103", "订单不是预定状态，不能完成取消");

            commonUnitService.executeCancelUnitOrder(unitPerson.getCsupInfo(), unitOrderId, "");
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
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

            // 订单状态不正确
            if (order.getCsoStatus() != 0)
                return returnError("103", "订单状态不正确，无法完成取消操作");

            commonDisposeService.executeCancelOrder(orderId, "会员自主取消订单", From.APP,
                    "鹏龙app" + version);
            return $.SendHtml($.json(JsonFormat.success()), CHARSET);
        } catch (Exception e) {
            return returnError(e);
        }

    }
}
