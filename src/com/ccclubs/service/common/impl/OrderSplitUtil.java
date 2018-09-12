package com.ccclubs.service.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.param.TimeBlock;
import com.ccclubs.param.TimeSlot;
import com.ccclubs.param.TimeUtil;
import com.ccclubs.param.TimeUtil.RoundMode;
import com.lazy3q.web.helper.$;

public class OrderSplitUtil extends CommonOrderService {

    /**
     * 拆封订单计费明细
     * @param productId 产品id
     * @param csFeeTypeSet 计费策略
     * @param userType 计费类型
     * @param outletsId 网点id
     * @param modelId 车型id
     * @param hostId 城市id
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    List<CsOrderDetail> buildOrderDetails(Long productId, CsFeeTypeSet csFeeTypeSet,
            Long userType, Long outletsId, Long modelId, Long hostId, Date start, Date end) {
        //订单明细集合初始化
        List<CsOrderDetail> results = new ArrayList<>(); 
        //获取计费规则
        Map<String, TimeSlot> slotMap = getRules(csFeeTypeSet.getCsftsDefault(), outletsId, modelId, userType, productId);
        
        int days = TimeUtil.getDaysBetween(start, end, null);
        if(days>0) {
            //以24小时为结算周期拆分计费时间
            List<TimeBlock> timeBlocks = splitTime(start, null, slotMap);
            //计算24小时内最有计费组合
            List<CsOrderDetail> list = buildOrderDetailsIn24Hours(start, null, timeBlocks, slotMap);
            for (int i = 0; i < days; i++) {
                for (CsOrderDetail bean : list) {
                    bean.setCsodStart(TimeUtil.addDay(bean.getCsodStart(), 1));
                    bean.setCsodEnd(TimeUtil.addDay(bean.getCsodEnd(), 1));
                }
                results.addAll(list);
            }
            
            //拆分不满24小时的计费时长
            start = TimeUtil.addDay(start, days);
            timeBlocks = splitTime(start, end, slotMap);
            list = buildOrderDetailsIn24Hours(start, end, timeBlocks, slotMap);
            results.addAll(list);
        }else {
            //拆分不满24小时的计费时长
            List<TimeBlock> timeBlocks = splitTime(start, end, slotMap);
            List<CsOrderDetail> list = buildOrderDetailsIn24Hours(start, end, timeBlocks, slotMap); 
            results.addAll(list);
        }

        return results;
    }

    /**
     * 根据计费规则，拆分收费时间段
     * @param start 开始时间
     * @param end 截止时间：如果等于空，截止时间为当前时间后移24小时
     * @param slotMap 计费槽
     * @return
     */
    private List<TimeBlock> splitTime(Date start, Date end, Map<String, TimeSlot> slotMap) {
        //截止时间：如果等于空，截止时间为当前时间后移24小时
        if(end==null) {
            end = TimeUtil.addHour(start, 24);
        }
        TimeSlot nightSlot1 = slotMap.get(RuleName.夜租一);
        TimeSlot nightSlot2 = slotMap.get(RuleName.夜租二);
        TimeSlot nightSlot3 = slotMap.get(RuleName.夜租三);
        // 时间轴：满24小时的时间轴、不满24小时的时间轴
        List<TimeBlock> timeBlocks = new ArrayList<>();
        TimeBlock timeBlock = new TimeBlock();// 时间片段

        Date tempStart = new Date();
        Date tempEnd = new Date();
        String date = TimeUtil.format(start, TimeUtil.DATE.FORMAT_yyyy_MM_dd);
        Date nightStart = TimeUtil.stringToDate(date + nightSlot1.getStart());
        Date nightStartUp = TimeUtil.stringToDate(date + nightSlot1.getStartUp());
        Date nightFinish = TimeUtil.addMinute(nightStart, nightSlot1.getDuration());
        nightFinish = TimeUtil.addDay(nightFinish, -1);//前天的夜租三截止时间
        // 起始时间在夜租一开始时间之前，夜租三截止时间之后
        if (start.getTime() <= nightStart.getTime()
                && start.getTime() >= nightFinish.getTime()) {

            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一开始时间
            tempStart = start;
            tempEnd = nightStart;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一时间段
            tempStart = tempEnd;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
            timeBlocks.add(timeBlock);
        }

        // 起始时间在夜租一有效时间之内
        if (start.getTime() >= nightStart.getTime()
                && start.getTime() < nightStartUp.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            tempEnd = TimeUtil.stringToDate(nightFinish + nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
            timeBlocks.add(timeBlock);
        }

        /**
         * 计算【费用/夜租二】
         */
        nightStart = TimeUtil.stringToDate(date + nightSlot2.getStart());
        nightStartUp = TimeUtil.stringToDate(date + nightSlot2.getStartUp());
        nightFinish = TimeUtil.addMinute(nightStart, nightSlot2.getDuration());
        // 起始时间在夜租二有效时间之内
        if (start.getTime() >= nightStart.getTime()
                && start.getTime() < nightStartUp.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT2);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT2);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            tempEnd = TimeUtil.stringToDate(nightFinish + nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
            timeBlocks.add(timeBlock);
        }

        /**
         * 计算【费用/夜租三】
         */
        nightStart = TimeUtil.stringToDate(date + nightSlot3.getStart());
        nightFinish = TimeUtil.addMinute(nightStart, nightSlot3.getDuration());
        // 起始时间在夜租三有效时间之内
        if (start.getTime() >= nightStart.getTime()
                && start.getTime() < nightFinish.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT3);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT3);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            tempEnd = TimeUtil.stringToDate(nightFinish + nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
            timeBlocks.add(timeBlock);
        }
        return timeBlocks;
    }

    /**
     * 根据计费规则，将订单时长拆分为多个收费项
     * @param start 开始时间
     * @param end 截止时间：如果等于空，截止时间为当前时间后移24小时
     * @param timeBlocks 时间段集合
     * @param slotMap 计费槽
     * @return
     */
    private List<CsOrderDetail> buildOrderDetailsIn24Hours(Date start, Date end, List<TimeBlock> timeBlocks,
            Map<String, TimeSlot> slotMap) {
        TimeSlot minuteSlot = slotMap.get(RuleName.每分钟);
        TimeSlot hourSlot = slotMap.get(RuleName.每小时);
        TimeSlot daySlot = slotMap.get(RuleName.每天);
        // 以24小时为结算周期，统计订单时长持续多少个结算周期
        List<CsOrderDetail> details = new ArrayList<>();

        double totalFee = 0;// 总费用
        double dayFee = daySlot.getPrice();// 每天封顶收费
        CsOrderDetail detil = new CsOrderDetail();// 计费明细

        // 时间段遍历结算计费明细
        for (TimeBlock timeBlock : timeBlocks) {
            List<CsOrderDetail> list = new ArrayList<>();
            if (timeBlock.getFeeMode() == TimeBlock.FEE_DAYTIME) {
                // 普通计费时区
                list = buildOrdinaryOrderDetail(timeBlock, minuteSlot, hourSlot);
            } else if (timeBlock.getFeeMode() == TimeBlock.FEE_NIGHT1) {
                // 夜租一计费时区
                list = buildNightOrderDetail(timeBlock, slotMap, RuleName.夜租一);
            } else if (timeBlock.getFeeMode() == TimeBlock.FEE_NIGHT2) {
                // 夜租二计费时区
                list = buildNightOrderDetail(timeBlock, slotMap, RuleName.夜租二);
            } else if (timeBlock.getFeeMode() == TimeBlock.FEE_NIGHT3) {
                // 夜租三计费时区
                list = buildNightOrderDetail(timeBlock, slotMap, RuleName.夜租三);
            }
            // 装载计费明细
            if (list.size() > 0) {
                details.addAll(list);
            }
        }

        // 统计24小时累计收费
        for (CsOrderDetail detail : details) {
            if (detail != null) {
                totalFee += detail.getCsodPrice() * detail.getCsodCount();
            }
        }

        // 24小时封顶收费计算
        if (totalFee > dayFee) {
            if(end==null) {
                end = TimeUtil.addHour(start, 24);
            }
            detil = buildOrderDetail(daySlot, start, end, 1);
            detil.setCsodRemark("按天计费：按天封顶计费最优惠");
            // 清楚其他计费记录
            details.clear();
            // 保留当前计费记录
            details.add(detil);
        }

        return details;
    }

    /**
     * 普通时区计费明细
     * 
     * @param timeBlock
     * @param minuteSlot
     * @param hourSlot
     * @return
     */
    private List<CsOrderDetail> buildOrdinaryOrderDetail(TimeBlock timeBlock, TimeSlot minuteSlot,
            TimeSlot hourSlot) {
        List<CsOrderDetail> details = new ArrayList<>();
        CsOrderDetail detil = new CsOrderDetail();// 计费明细
        // 分钟数
        int minutes = TimeUtil.getMinutesBetween(timeBlock.getStartTime(), timeBlock.getEndTime(),
                RoundMode.Ceiling);
        // 小时数
        int hours = minutes / 60;
        // 不足一小时分钟数
        int normalMinutes = minutes - hours * 60;

        double minuteFee = minuteSlot.getPrice();// 分钟收费单价
        double hourFee = hourSlot.getPrice();// 小时封顶收费
        double tempMuniteFee = minutes * minuteFee;// 分钟计费总价
        double fee = hourFee / 60;// 小时计费单价

        if (fee > minuteFee) {
            detil = buildOrderDetail(minuteSlot, timeBlock.getStartTime(), timeBlock.getEndTime(),
                    minutes);
            detil.setCsodRemark("普通时长计费：分钟计费优惠于小时计费");
            details.add(detil);
        } else {
            // 小时计费单价优惠于分钟计费时：总费用=hours*hourFee+(minutes*minuteFee||hourFee)
            Date temp = TimeUtil.addHour(timeBlock.getStartTime(), hours);
            detil = buildOrderDetail(hourSlot, timeBlock.getStartTime(), temp, 1);
            detil.setCsodRemark("普通时长计费：小时计费优惠于分钟计费");
            // 不满一小时的最低收费
            tempMuniteFee = normalMinutes * minuteFee;
            if (tempMuniteFee > hourFee) {
                tempMuniteFee = hourFee;
                detil = buildOrderDetail(hourSlot, temp, timeBlock.getEndTime(), 1);
                detil.setCsodRemark("普通时长计费：小时计费优惠于分钟计费");
                details.add(detil);
            } else {
                detil = buildOrderDetail(minuteSlot, temp, timeBlock.getEndTime(), normalMinutes);
                detil.setCsodRemark("普通时长计费：分钟计费不满足小时计费封顶条件");
                details.add(detil);
            }
        }

        return details;
    }

    /**
     * 夜租时区计费明细
     * 
     * @param timeBlock
     * @param slotMap
     * @param ruleName
     * @return
     */
    private List<CsOrderDetail> buildNightOrderDetail(TimeBlock timeBlock, Map<String, TimeSlot> slotMap,
            RuleName ruleName) {
        TimeSlot minuteSlot = slotMap.get(RuleName.每分钟.ordinal());
        TimeSlot hourSlot = slotMap.get(RuleName.每小时.ordinal());
        TimeSlot nightSlot = slotMap.get(ruleName.ordinal());
        List<CsOrderDetail> details = new ArrayList<>();
        CsOrderDetail detil = new CsOrderDetail();// 计费明细
        // 分钟数
        int minutes = TimeUtil.getMinutesBetween(timeBlock.getStartTime(), timeBlock.getEndTime(),
                RoundMode.Ceiling);
        // 小时数
        int hours = minutes / 60;
        // 不足一小时分钟数
        int normalMinutes = minutes - hours * 60;

        double minuteFee = minuteSlot.getPrice();// 分钟收费单价
        double hourFee = hourSlot.getPrice();// 小时封顶收费
        double nightFee = nightSlot.getPrice();// 夜租封顶费用
        double tempMuniteFee;// 费用临时寄存器
        double tempHourFee;// 费用临时寄存器
        double totalFee;// 费用临时寄存器

        // 分钟计费
        tempMuniteFee = minutes * minuteFee;
        // 小时计费
        tempHourFee = hourFee * hours;
        // 小时、分钟组合计费
        totalFee = normalMinutes * minuteFee;
        totalFee = totalFee + tempHourFee;

        // 分钟计费、小时计费、夜租计费取最优惠组合
        if (totalFee < nightFee || tempMuniteFee < nightFee) {
            if (totalFee >= tempMuniteFee) {
                detil = buildOrderDetail(minuteSlot, timeBlock.getStartTime(),
                        timeBlock.getEndTime(), minutes);
                detil.setCsodRemark("夜租一时长计费：分钟计费最优惠。");
                details.add(detil);
            } else {
                Date temp = TimeUtil.addHour(timeBlock.getStartTime(), hours);
                detil = buildOrderDetail(hourSlot, timeBlock.getStartTime(), temp, hours);
                detil.setCsodRemark("夜租一时长计费：分钟计费、小时计费组合最优惠。");
                details.add(detil);

                detil = buildOrderDetail(minuteSlot, temp, timeBlock.getEndTime(), normalMinutes);
                detil.setCsodRemark("夜租一时长计费：分钟计费、小时计费组合最优惠。");
                details.add(detil);
            }
        } else {
            detil = buildOrderDetail(nightSlot, timeBlock.getStartTime(), timeBlock.getEndTime(),
                    1);
            detil.setCsodRemark("夜租一时长计费：夜租一封顶计费最优惠");
            details.add(detil);
        }

        return details;
    }



    /**
     * 根据车型、网点、用户类型、业务获取收费规则列表。默认收费规则增量追加至当前已有配置规则。收费规则以时间槽形式表示
     * 
     * @param defaultOutletsId 默认网点
     * @param outletsId 当前网点
     * @param modelId 车型
     * @param userType 用户类型
     * @param productId 业务id（产品即业务）
     * @return
     */
    private Map<String, TimeSlot> getRules(Long defaultOutletsId, Long outletsId, Long modelId,
            Long userType, Long productId) {
        // 获取当前配置计费规则（价格表、规则表、商品表关联查询）
        String sql =
                "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_meas, cr.csr_priority, cg.csg_name, cg.csg_product, cg.csg_profile from cs_price cp "
                        + " left join cs_goods cg on cp.csp_goods=cg.csg_id "
                        + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                        + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets="
                        + outletsId + " and cp.csp_model=" + modelId + " and cp.csp_user_type="
                        + userType + " and cg.csg_status=1 and cg.csg_product =" + productId
                        + " order by cr.csr_priority desc";
        List<Map> rules = $.getDao("ccclubs_system").executeQuery(sql);

        // 规则列表转换成Map<规则名称, 规则>,key唯一即规则唯一
        Map<String, Map<String, Object>> ruleMap = new HashMap<String, Map<String, Object>>();
        if (rules != null) {
            for (Map<String, Object> rule : rules) {
                ruleMap.put(String.valueOf(rule.get("csr_name")), rule);
            }
        }

        // 获取默认配置计费规则并增量模式追加至已有规则（价格表、规则表、商品表关联查询）
        if (defaultOutletsId != null) {
            sql = "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_meas, cr.csr_priority, cg.csg_name, cg.csg_product, cg.csg_profile from cs_price cp "
                    + " left join cs_goods cg on cp.csp_goods=cg.csg_id "
                    + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                    + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets="
                    + defaultOutletsId + " and cp.csp_model=" + modelId + " and cp.csp_user_type="
                    + userType + " and cg.csg_status=1 and cg.csg_product =" + productId
                    + " order by cr.csr_priority desc";

            rules = $.getDao("ccclubs_system").executeQuery(sql);
            // 增量模式追加至Map<规则名称, 规则>，key唯一即规则唯一
            if (rules != null) {
                for (Map rule : rules) {
                    if (ruleMap.get(String.valueOf(rule.get("csr_name"))) == null) {
                        ruleMap.put(String.valueOf(rule.get("csr_name")), rule);
                    }
                }
            }
        }
        // 将计费规则转换成时间槽
        Map<String, TimeSlot> result = new HashMap<>();
        Map<String, Object> rule = null;
        for (String key : ruleMap.keySet()) {
            TimeSlot slot = new TimeSlot();
            rule = ruleMap.get(key);
            // 规则名称（唯一标识）
            if (rule.get("csr_name") != null) {
                slot.setName(rule.get("csr_name").toString());
            }
            // 日期正则表达式
            if (rule.get("csr_expression") != null) {
                slot.setRegex(rule.get("csr_expression").toString());
            }
            // 计价公式
            if (rule.get("csr_meas") != null) {
                slot.setFormula(rule.get("csr_meas").toString());
            }
            // 规则优先级
            if (rule.get("csr_priority") != null) {
                slot.setPriority(Integer.valueOf(rule.get("csr_priority").toString().trim()));
            }
            // 设置价格表定义的收费标准
            if (rule.get("csp_price") != null) {
                slot.setPrice(Double.valueOf(rule.get("csp_price").toString().trim()));
            }
            // 解析商品中定义的时间段
            if (rule.get("csg_profile") != null) {
                slot.parseGoodsProfile(rule.get("csg_profile").toString());
            }

            result.put(key, slot);
        }


        return result;
    }

    CsOrderDetail buildOrderDetail(TimeSlot slot, Date start, Date end, int count) {
        CsOrderDetail detail = new CsOrderDetail();
        detail.setCsodAddTime(new Date());
        detail.setCsodRule(slot.getRuleId());
        detail.setCsodGoods(slot.getGoodsId());
        detail.setCsodPrice(slot.getPrice());
        detail.setCsodStart(start);
        detail.setCsodEnd(end);
        detail.setCsodCount(Double.valueOf(count));
        return detail;
    }

}
