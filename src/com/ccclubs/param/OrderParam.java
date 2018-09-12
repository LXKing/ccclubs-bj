package com.ccclubs.param;

import java.util.Date;
/**
 * @function 普通下单参数
 * @author cjb
 * @createTime 2018年9月4日
 */
public class OrderParam extends BaseParam{

    private static final long serialVersionUID = 7709263394135699036L;

    // 取车网点
    private Long takeOutletsId;
    // 还车网点（AB借还车，仅提前2小时之内预定）
    private Long retOutletsId;
    // 车辆
    private Long carId;
    // 预计取车时间
    private Date takeTime;
    // 预计还车时间
    private Date retTime;
    // 免费时长
    private Double freeHours;
    // 保险类型
    private Long insureType;
    //长订单价格
    private Double longPrice;
    
    public Long getTakeOutletsId() {
        return takeOutletsId;
    }
    public void setTakeOutletsId(Long takeOutletsId) {
        this.takeOutletsId = takeOutletsId;
    }
    public Long getRetOutletsId() {
        return retOutletsId;
    }
    public void setRetOutletsId(Long retOutletsId) {
        this.retOutletsId = retOutletsId;
    }
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public Date getTakeTime() {
        return takeTime;
    }
    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }
    public Date getRetTime() {
        return retTime;
    }
    public void setRetTime(Date retTime) {
        this.retTime = retTime;
    }
    public Double getFreeHours() {
        return freeHours;
    }
    public void setFreeHours(Double freeHours) {
        this.freeHours = freeHours;
    }
    public Long getInsureType() {
        return insureType;
    }
    public void setInsureType(Long insureType) {
        this.insureType = insureType;
    }
    public Double getLongPrice() {
        return longPrice;
    }
    public void setLongPrice(Double longPrice) {
        this.longPrice = longPrice;
    }
    
}
