package com.ccclubs.param;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.ccclubs.helper.APICallHelper;

/**
 * @function 时间段计费信息
 * @author cjb
 * @createTime 2018年9月6日
 */
public class TimeSlot {
    //商品id
    private Long ruleId;
    //商品id
    private Long goodsId;
    // 规则名称（唯一标识）
    private String name;
    // 匹配规则
    private String regex;
    // 生效起始节点
    private String start;
    // 生效截止节点
    private String startUp;
    // 计价公式
    private String formula;
    // 优先级
    private Integer priority;
    // 时长：分钟
    private Integer duration;
    // 价格/单位
    private Double price;

    public void parseGoodsProfile(String json) {
        if (StringUtils.isEmpty(json))
            return;
        Map<String, Object> param = (Map<String, Object>) APICallHelper.fromJson(json);
        if (param == null || (Map<String, String>) param.get("params") == null)
            return;
        Map<String, String> params = (Map<String, String>) param.get("params");

        //生效起始节点
        if (StringUtils.isNotEmpty(params.get("startTime"))) {
            this.start = params.get("startTime").trim();
        }
        //生效截止节点
        if (StringUtils.isNotEmpty(params.get("startTimeUp"))) {
            this.startUp = params.get("startTimeUp").trim();
        }
        //计费时长
        if (StringUtils.isNotEmpty(params.get("timeLength"))) {
            this.duration = Integer.valueOf(params.get("timeLength").trim());
        }
    }
    
    /**
     * 优先级降序
     * @param slots
     * @return
     */
    public static List<TimeSlot> sortSlots(List<TimeSlot> slots) {
        Collections.sort(slots, new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot o1, TimeSlot o2) {
                if (o1.getPriority() > o2.getPriority().intValue()) {
                    return -1;
                } else if (o1.getPriority() < o2.getPriority().intValue()) {
                    return 1;
                }
                return 0;
            }
        });
        
        return slots;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStartUp() {
        return startUp;
    }

    public void setStartUp(String startUp) {
        this.startUp = startUp;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TimeSlot [ruleId=" + ruleId + ", goodsId=" + goodsId + ", name=" + name + ", regex="
                + regex + ", start=" + start + ", startUp=" + startUp + ", formula=" + formula
                + ", priority=" + priority + ", duration=" + duration + ", price=" + price + "]";
    }

}
