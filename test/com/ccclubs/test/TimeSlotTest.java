package com.ccclubs.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ccclubs.param.TimeSlot;
import com.lazy3q.web.helper.$;

public class TimeSlotTest {
    public static void main(String[] args) {
        TimeSlot slot = new TimeSlot();
        slot.setStart("17:00:00");
        slot.setRegex("*");
        slot.setName("每分钟");
        slot.setFormula("{M}/1");
        slot.setPriority(1);
        slot.parseGoodsProfile("{params:{startTime:'17:00:00',timeLength:'3600'}}");
        System.out.println(slot.toString());
        
        sortTest();
        
        getTimeSlots(1l, 6l, 1l, 1l, 2l);
    }
    
    public static void sortTest() {
        List<TimeSlot> slots = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TimeSlot slot = new TimeSlot();
            slot.setPriority(i);
            slots.add(slot);
        }
        slots = TimeSlot.sortSlots(slots);
        for (TimeSlot slot : slots) {
            System.out.println(slot.getPriority());
        }
    }
    
    public static Map<String,TimeSlot> getTimeSlots(Long defaultOutletsId, Long outletsId, Long modelId,
            Long userType, Long productId) {
        //获取当前配置计费规则（价格表、规则表、商品表关联查询）
        String sql =
                "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_meas, cr.csr_priority, cg.csg_name, cg.csg_product, cg.csg_profile from cs_price cp "
                        + " left join cs_goods cg on cp.csp_goods=cg.csg_id "
                        + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                        + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets="
                        + outletsId + " and cp.csp_model=" + modelId + " and cp.csp_user_type="
                        + userType + " and cg.csg_status=1 and cg.csg_product =" + productId
                        + " order by cr.csr_priority desc";
        List<Map> rules = $.getDao("ccclubs_system").executeQuery(sql);
        
        //规则列表转换成Map<规则名称, 规则>,key唯一即规则唯一
        Map<String, Map<String, Object>> ruleMap = new HashMap<String, Map<String, Object>>();
        if (rules != null) {
            for (Map<String, Object> rule : rules) {
                ruleMap.put(String.valueOf(rule.get("csr_name")), rule);
            }
        }
        
        //获取默认配置计费规则并增量模式追加至已有规则（价格表、规则表、商品表关联查询）
        if (defaultOutletsId != null) {
            sql = "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_meas, cr.csr_priority, cg.csg_name, cg.csg_product, cg.csg_profile from cs_price cp "
                    + " left join cs_goods cg on cp.csp_goods=cg.csg_id "
                    + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                    + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets="
                    + defaultOutletsId + " and cp.csp_model=" + modelId + " and cp.csp_user_type="
                    + userType + " and cg.csg_status=1 and cg.csg_product =" + productId
                    + " order by cr.csr_priority desc";

            rules = $.getDao("ccclubs_system").executeQuery(sql);
            //增量模式追加至Map<规则名称, 规则>，key唯一即规则唯一
            if (rules != null) {
                for (Map rule : rules) {
                    if (ruleMap.get(String.valueOf(rule.get("csr_name"))) == null) {
                        ruleMap.put(String.valueOf(rule.get("csr_name")), rule);
                    }
                }
            }
        }
        //将计费规则转换成时间槽
        Map<String,TimeSlot> result = new HashMap<>();
        Map<String, Object> rule = null;
        for (String key : ruleMap.keySet()) {
            TimeSlot slot = new TimeSlot();
            rule = ruleMap.get(key);
            // 规则名称（唯一标识）
            if(rule.get("csr_name")!=null) {
                slot.setName(rule.get("csr_name").toString());
            }
            // 日期正则表达式
            if(rule.get("csr_expression")!=null) {
                slot.setRegex(rule.get("csr_expression").toString());
            }
            // 计价公式
            if(rule.get("csr_meas")!=null) {
                slot.setFormula(rule.get("csr_meas").toString());
            }
            // 规则优先级
            if (rule.get("csr_priority")!=null) {
                slot.setPriority(Integer.valueOf(rule.get("csr_priority").toString().trim()));
            }
            // 设置价格表定义的收费标准
            if (rule.get("csp_price")!=null) {
                slot.setPrice(Double.valueOf(rule.get("csp_price").toString().trim()));
            }
            // 解析商品中定义的时间段
            if(rule.get("csg_profile")!=null) {
                slot.parseGoodsProfile(rule.get("csg_profile").toString());
            }
            
            result.put(key, slot);
        }


        return result;
    }
    
}
