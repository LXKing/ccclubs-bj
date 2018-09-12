package com.ccclubs.service.common.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import com.ccclubs.action.app.official.meal.MealExpress;
import com.ccclubs.action.app.official.meal.MealHelper;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.dao.ICsCarDao;
import com.ccclubs.dao.ICsFreeHourDao;
import com.ccclubs.dao.ICsGiftDao;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.dao.ICsOrderDao;
import com.ccclubs.dao.ICsOrderDetailDao;
import com.ccclubs.dao.ICsUseRecordDao;
import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.WeixinHelper.WxTemplateMsgType;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsRule;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvLock;
import com.ccclubs.param.OrderParam4Personal;
import com.ccclubs.service.admin.ICsArgumentService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.service.common.impl.promotion.Activity2017LadiesDay;
import com.ccclubs.service.common.impl.promotion.ActivityPromotion;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class OrderFacade extends CommonDisposeService {

    ICsOrderDao csOrderDao;
    ICsFreeHourDao csFreeHourDao;
    ICsUseRecordDao csUseRecordDao;
    ICsGiftDao csGiftDao;
    ICsCarDao csCarDao;
    ICsMemberDao csMemberDao;
    ICsOrderDetailDao csOrderDetailDao;
    ICommonOrderService commonOrderService;
    ICommonMoneyService commonMoneyService;
    ICsArgumentService csArgumentService;

    public static ICommonDisposeService getBean() {
        return $.getBean("commonDisposeService");
    }

    public CsOrderCluster saveMealOrder(OrderParam4Personal param, Long memberId, Long payMemberId,
            From from, String src) {
        SrvLock.lock(SrvLock.LockType.订单事务锁, "套餐添加");

        /*************** 下单参数拆箱 **************/
        // 套餐id
        Long mealId = param.getMealId();
        // 车辆id
        Long carId = param.getCarId();
        // 预计取车时间
        Date takeTime = param.getTakeTime();
        // 取车网点
        Long takeOutletsId = param.getTakeOutletsId();
        // 还车网点
        Long retOutletsId = param.getRetOutletsId();
        // 免费时长
        Double freeHours = param.getFreeHours();
        // 保险类型
        Long insureType = param.getInsureType();
        // 长订单价格
        Double longPrice = param.getLongPrice();
        // 设置支付人
        if (payMemberId == null)
            payMemberId = memberId;
        // 编辑者
        long editor = 0;
        // 收费方式
        Long feeType = null;

        CsOrderLog csOrderLog = new CsOrderLog(// 添加订单日志
                "订单添加"// 操作项目 [非空]
                , 0l// 所属订单 [非空]
                , $.or(LoginHelper.getLoginId(), 0l)// 操作人 [非空]
                , LoginHelper.getLoginId() != null ? null : memberId, (short) from.ordinal(), src,
                $.getString("csOrder.csoRemark")// 备注信息
                , new Date()// 添加时间 [非空]
        ).save();

        // 下单业务关联数据初始化
        CsCar csCar = csCarDao.getCsCarById(carId);
        CsMember member = CsMember.get(payMemberId);
        CsItem item = CsItem.get(mealId);

        // 下单前校验
        checkBeforeOrder(item, member, payMemberId, takeTime);

        // 套餐计费描述封装
        MealExpress me = MealHelper.parseExpress(item.getCsiDepict());
        // 保证金
        Double margin = me.getMargin();
        // 套餐订单总金额：套件价+冻结保证金
        Double totalFee = item.getCsiPrice() + margin;
        // 账户可用余额
        Double canMoney = commonMoneyService.getUsableAmount(payMemberId);
        // 检查余额是否足够
        if (canMoney < totalFee) {
            throw new MessageException(ErrorCode.ORDER_MONEY_LESS, "会员可用余额不足");
        }

        // 即行券
        Double payCoupon = 0d;
        // 现金
        Double payMoney = 0d;
        // 用户扣费：即行券满足付费要求则用即行券支付，否则用即行券+现金支付
        if (member.getCsmCoupon() >= totalFee) {
            payCoupon = totalFee;// 即行券额数
        } else {
            payCoupon = member.getCsmCoupon();
            payMoney = totalFee - payCoupon;// 现金额数
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(takeTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        List<CsOrder> orderList = MealHelper.calcMealOrders(item.getCsiDepict(), cal.getTime());
        // 订单计费开始时间
        takeTime = orderList.get(0).getCsoStartTime();

        /**
         * 套餐订单总金额：套件价+冻结保证金
         */
        CsOrderCluster coc = new CsOrderCluster();
        //绑定用户信息
        coc = bindMemberInfo4OrderCluster(coc, member);
        //绑定套餐信息
        coc = bindMealInfo4OrderCluster(coc, item, margin);
        //绑定支付费用信息
        coc = bindPayInfo4OrderCluster(coc, payMoney, payCoupon);
        //绑定订单时长信息
        coc = bindDurationInfo4OrderCluster(coc, takeTime, orderList.get(orderList.size()-1).getCsoFinishTime());
        //订单族数据组装完毕并入库
        coc = coc.save();

        /***************************** 扣款 ********************************/
        String remark = "订单簇预定：" + coc.getCsocId$() + ",套餐ID" + item.getCsiId() + ",套餐名称"
                + item.getCsiTitle() + ",套餐金额" + item.getCsiPrice() + ",保证金" + margin;
        if (payMoney > 0) {
            //扣除现金
            commonMoneyService.updateMoney(coc.getCsocHost(), member.getCsmId(),
                    ICommonMoneyService.MoneyType.Money, -payMoney, SYSTEM.RecordType.订单消费,
                    remark, coc.getCsocId(), coc.getCsocId(), CsOrderCluster.class);
        }
        if (payCoupon > 0) {
            //扣除即行券
            commonMoneyService.updateMoney(coc.getCsocHost(), member.getCsmId(),
                    ICommonMoneyService.MoneyType.Coupon, -payCoupon, SYSTEM.RecordType.订单消费,
                    remark, coc.getCsocId(), coc.getCsocId(), CsOrderCluster.class);
        }
        /***************************** 扣款 ********************************/
        
        ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
        //子订单id拼接结果
        String sub_order_ids = "";
        for (int i = 0; i < orderList.size(); i++) {

            CsOrder csOrder = orderList.get(i);
            csOrder = initDefaultValue4Order(csOrder);
            csOrder = bindMemberInfo4Order(csOrder, member);
            csOrder = bindCarInfo4Order(csOrder, csCar, takeOutletsId, retOutletsId);
            csOrder = bindOtherInfo(csOrder, param); 
            
            csOrder.setCsoFrom((short) from.ordinal());
            csOrder.setCsoRemark("使用套餐【" + item.getCsiTitle() + "】预定");
            csOrder.setCsoSrc(CsOrderCluster.class.getSimpleName() + "@" + coc.getCsocId());
            csOrder.setCsoUseIndex(CsOrder.where().csoUseMember(memberId).csoStatus((short) 4).count().intValue());
            // 订单序列号=会员ID+车辆ID+订单开始时间
            csOrder.setCsoSerial(csOrder.getCsoUseMember() + "-" + csOrder.getCsoCar() + "-"
                    + csOrder.getCsoStartTime().getTime());
            
            buildOrder(csOrder, param);
            csOrder = csOrderDao.saveCsOrder(csOrder);

            // 更新日志
            csOrderLog.csolOrder(csOrder.getCsoId()).update();
            //创建订单成功，发送通知消息
            doSaveOrderSuccess(csOrder);

            sub_order_ids += "," + csOrder.getCsoId();
        }

        // 更新宝贝已售数
        item.setCsiSale(item.getCsiSale() == null ? 0 : item.getCsiSale() + 1);
        item.update();

        //更新子订单信息
        coc.setCsocSubOrders(sub_order_ids);
        coc.update();

        return coc;
    }

    /**
     * 下单前校验
     * 
     * @param item 套餐
     * @param member 会员：校验驾照、ev卡、账户状态
     * @param payMemberId 支付人
     * @param takeTime 预定取车时间
     */
    public void checkBeforeOrder(CsItem item, CsMember member, Long payMemberId, Date takeTime) {

        // 校验会员信息
        if (member.getCsmStatus().shortValue() != 1)
            throw new MessageException(ErrorCode.ORDER_MEMBER_DISABLE, "该会员帐号已被禁用，不能下单");
        if (member.getVDrive().shortValue() != 1)
            throw new MessageException(ErrorCode.ORDER_MEMBERINFO_ERROR, "该会员资料未审核通过，不能下单");
        if (member.get$csmEvcard() == null)
            throw new MessageException(ErrorCode.ORDER_NO_EV_CARD, "没有绑定EV卡，不能下单");

        // 校验套餐
        if (item == null)
            throw new MessageException(ErrorCode.PACKAGE_CANOT_BUY, "套餐不存在");

        // 检查支付人是否有未完成的退款
        if (commonMoneyService.isRefunding(payMemberId))
            throw new MessageException(ErrorCode.ORDER_NO_REFUND, "当前支付会员正在退款，不能下单");
        // 支付人信用检测
        if (CsCreditBill.Get($.add(CsCreditBill.F.cscbMember, payMemberId)
                .add(CsCreditBill.F.cscbStatus, 4)) != null) {
            throw new MessageException(ErrorCode.ORDER_CREDIT_BAD_PAY, "会员有信用帐单坏帐，不能租车");
        }

        // 校验预定取车时间
        if (takeTime.before(new Date())) {
            throw new MessageException(ErrorCode.ORDER_TIME_ERROR, "不能预订比当前时间早的订单");
        }
    }
    /**
     * 绑定用户信息
     * @param coc 订单族
     * @param member 会员
     * @return
     */
    private CsOrderCluster bindMemberInfo4OrderCluster(CsOrderCluster coc, CsMember member) {
        //订单用户信息
        coc.setCsocHost(member.getCsmHost());
        coc.setCsocPayMember(member.getCsmId());
        coc.setCsocMobile(member.getCsmMobile());
        return coc;
    }
    /**
     * 绑定套餐信息
     * @param coc 订单族
     * @param item 套餐
     * @param margin 保证金
     * @return
     */
    private CsOrderCluster bindMealInfo4OrderCluster(CsOrderCluster coc, CsItem item, Double margin) {
        //套餐费用
        coc.setCsocOutType((short) 1); // 套餐类型
        coc.setCsocOutId(item.getCsiId()); //套餐id
        coc.setCsocPrice(item.getCsiPrice());
        coc.setCsocPayNeed(item.getCsiPrice());
        coc.setCsocPayReal(item.getCsiPrice());
        coc.setCsocPayRent(item.getCsiPrice());
        coc.setCsocPayCoin(0d);
        coc.setCsocSubOrders("");
        coc.setCsocPayDetails($.json($.add("express", item.getCsiDepict()).add(
                new DateUtil().dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"),
                "套餐下单支付套餐费用" + item.getCsiPrice() + ",冻结保证金" + margin)));
        coc.setCsocStatus((short) 0);//预定
        return coc;
    }
    /**
     * 绑定支付费用信息
     * @param coc 订单族
     * @param payMoney 现金
     * @param payCoupon 即行券
     * @return
     */
    private CsOrderCluster bindPayInfo4OrderCluster(CsOrderCluster coc, Double payMoney, Double payCoupon) {
        //用户支付费用
        coc.setCsocPayMoney(payMoney);
        coc.setCsocPayCoupon(payCoupon);
        return coc;
    }
    /**
     * 绑定订单时长信息
     * @param coc 订单族
     * @param startTime 开始时间
     * @param finishTime 截止时间
     * @return
     */
    private CsOrderCluster bindDurationInfo4OrderCluster(CsOrderCluster coc, Date startTime, Date finishTime) {
        //订单时间信息
        coc.setCsocStartTime(startTime);
        coc.setCsocFinishTime(finishTime);
        coc.setCsocTotalDuration($((finishTime.getTime() - startTime.getTime()) * 1d / SYSTEM.HOUR));
        coc.setCsocAddTime(new Date());
        coc.setCsocUpdateTime(new Date());
        return coc;
    }
    
    private CsOrder buildOrder(CsOrder csOrder, OrderParam4Personal param){
        Date start = csOrder.getCsoStartTime(); 
        Date finish = csOrder.getCsoFinishTime();
        
        /***************************** 校验 ********************************/
        // 分析取还车网点
        String schedulingError = SchedulingService.checkGetRetOutlets(param.getCarId(),
                csOrder.getCsoOutlets(), csOrder.getCsoOutletsRet(), start, finish);
        if (!$.empty(schedulingError))
            throw new MessageException(ErrorCode.ORDER_A_B_OUTLETS_ERROR, schedulingError);

        // 分析时段是否有订单
        if (commonOrderService.isExistOrderByTime(param.getCarId(), start, finish, null))
            throw new MessageException(ErrorCode.ORDER_CONFLICT, "当前订单时间已经被其它订单占用");
        /**************************** 校验 *********************************/

     // 获取小时数
        int allhours = (int) ((finish.getTime() - start.getTime()) / SYSTEM.HOUR);
        $.setLocal("defineactual", commonOrderService.getPredictKms(allhours) * 1);

        // 拆分订单
        OrderInfo orderinfo = commonOrderService.splitOrderDetails(csOrder.getCsoPayMember(), param.getCarId(), start,
                finish, null, param.getFreeHours(), null, null, null, param.getInsureType(), param.getLongPrice(), null);

        /***************************** 更改订单金额 ********************************/
        orderinfo.setPrice(0d);
        orderinfo.setMargin(0d); // 保证金
        orderinfo.setFeeee(0d); // 里程费
        orderinfo.setUsecoin(0d); // 不能用红包

        /***************************** 更改订单金额 ********************************/
        csOrder.setCsoUseType((short) (csOrder.getCsoOutlets().longValue() == csOrder
                .getCsoOutletsRet().longValue() ? 0 : 1)); // 原借原还
        csOrder.setCsoFeeType(orderinfo.feetype); // 计费类型
        csOrder.setCsoPayNeed(orderinfo.price); // 设置订单需付额
        csOrder.setCsoMarginNeed(orderinfo.margin); // 设置订单需保证金额
        csOrder.setCsoPredict(orderinfo.feeee); // 设置订单预计里程费
        csOrder.setCsoDuration(orderinfo.getTime().doubleValue() / SYSTEM.HOUR.doubleValue()); // 订单时长
        csOrder.setCsoPayCoin(orderinfo.usecoin); // 设置红包支付
        
        return csOrder;
        
    }
    
    /**
     * 设置订单默认值
     * @param csOrder
     * @return
     */
    private CsOrder initDefaultValue4Order(CsOrder csOrder){
        csOrder.setCsoFlag((short) 3); // 订单标识设置成“订单簇”
        csOrder.setCsoCreditCard(null);
        csOrder.setCsoEditor(0l);
        csOrder.setCsoMask(0l);
        csOrder.setCsoPayInsure(0d);
        csOrder.setCsoPayTimeout(0d);
        csOrder.setCsoFuel(0d);
        csOrder.setCsoElectric(0d);
        csOrder.setCsoPayReal(0d);
        csOrder.setCsoAlarm((short) 0);
        csOrder.setCsoState((short) 0);
        csOrder.setCsoStatus((short) 0);
        csOrder.setCsoUpdateTime(new Date());
        csOrder.setCsoAddTime(new Date());
        csOrder.setCsoCode($.zerofill($.rand(999999), 6).replaceAll("0", "1"));
        return csOrder;
    }
    
    /**
     * 绑定用户信息
     * @param csOrder
     * @param memberId
     * @return
     */
    private CsOrder bindMemberInfo4Order(CsOrder csOrder, CsMember member){
        csOrder.setCsoPayMember(member.getCsmId());
        csOrder.setCsoUseMember(member.getCsmId());
        csOrder.setCsoMobile(member.getCsmMobile());// 手机号码
        csOrder.setCsoEvRfid(member.get$csmEvcard().getCsecRfid());// ev卡号
        Short notRevenue = $.or(member.getCsmNotRevenue(), (short) 0);
        csOrder.setCsoIncome((short) (notRevenue.shortValue() == 0 ? 1 : 0));
        return csOrder;
    }
    
    /**
     * 绑定车辆相关信息
     * @param csOrder
     * @param csCar
     * @param takeOutletsId
     * @param retOutletsId
     * @return
     */
    private CsOrder bindCarInfo4Order(CsOrder csOrder, CsCar csCar, Long takeOutletsId, Long retOutletsId){
        csOrder.setCsoCar(csCar.getCscId());
        csOrder.setCsoArea((csCar.get$cscOutlets()).getCsoArea());
        csOrder.setCsoDeviceVesion(csCar.getCscDeviceVesion());
        csOrder.setCsoHost(csCar.getCscHost());
        csOrder.setCsoIsAdCar(csCar.getCscIsAd());// 保存车辆是否宣传车
        csOrder.setCsoRebate(csCar.getCscRebate());// 保存车辆的折扣状态
        csOrder.setCsoModel(csCar.getCscModel());//车型
        csOrder.setCsoProvid(csCar.getCscProvid());// 所属商家
        csOrder.setCsoCarNumber(csCar.getCscNumber());// 车牌号
        csOrder.setCsoOutlets($.or(takeOutletsId, csCar.getCscOutlets()));// 取车网点
        csOrder.setCsoOutletsRet($.or(retOutletsId, csCar.getCscOutlets()));// 还车网点
        return csOrder;
    }
    
    private CsOrder bindOtherInfo(CsOrder csOrder, OrderParam4Personal param) {
        csOrder.setCsoInsureType(param.getInsureType());
        csOrder.setCsoFreehour(param.getFreeHours());
        csOrder.setCsoLongPrice(param.getLongPrice());
        return csOrder;
    }
    
    private void doSaveOrderSuccess(CsOrder csOrder) {
        // 插入调度记录数据
        SchedulingService.scheduling(csOrder.getCsoCar(), csOrder.getCsoOutlets(), csOrder.getCsoOutletsRet(),
                csOrder.getCsoStartTime(), csOrder.getCsoFinishTime(),
                com.ccclubs.service.common.impl.SchedulingService.DispatchType.定单调度,
                csOrder.getCsoId(), "订单自动调度");

        Boolean bUnSendSms = false;
        try {
            bUnSendSms = $.getBoolean("checkbox", false);
        } catch (Exception ex) {
            bUnSendSms = false;
        }
        if ($.getLocal(ICommonDisposeService.DONOTSENDSMS) == null && bUnSendSms == false) {
            // 发送短信
            UtilHelper.sendTemplateSMS(csOrder.getCsoHost(), "ADD_ORDER",
                    csOrder.getCsoMobile(),
                    "您的订单时间{start}到{finish}，取车点{outlets}，车牌号{number}，授权码{code}[车纷享]",
                    SMSType.通知类短信,
                    $.add("start", $.date(csOrder.getCsoStartTime(), "MM月dd日HH时mm分"))
                            .add("finish", $.date(csOrder.getCsoFinishTime(), "MM月dd日HH时mm分"))
                            .add("outlets", (csOrder.get$csoOutlets()).getCsoName())
                            .add("number", (csOrder.get$csoCar()).getCscNumber())
                            .add("code", csOrder.getCsoCode()));
            // 发送微信消息
            com.ccclubs.helper.WeixinHelper.sendWxTemplateMessage(csOrder.getCsoUseMember(),
                    WxTemplateMsgType.订单预订, CsOrder.class, csOrder.getCsoId());
        }
        $.setLocal(ICommonDisposeService.DONOTSENDSMS, null);
        com.ccclubs.helper.TimeLineHelper.update(csOrder.getCsoId());
    }

}
