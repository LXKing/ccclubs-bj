package com.ccclubs.action.vc.dto;

import java.io.Serializable;

/**
 * 下发订单-有授权信息
 *
 * @author jianghaiyang
 * @create 2017-08-01
 **/
public class IssueAuthOrderInput implements Serializable {
    
    private static final long serialVersionUID = -2320166799844658843L;

    /**
     * 车辆vin码必填
     */
    private String vin;

    /**
     * 订单号必填
     */
    private Long orderId;
    
    /**
     * 订单开始时间(yyyy-MM-dd HH:mm:ss)必填
     */
    private String startTime;

    /**
     * 订单结束时间(yyyy-MM-dd HH:mm:ss)必填
     */
    private String endTime;
    
    /**
     * RFID号(8位)必填
     */
    private String rfid;

    /**
     * 授权码(6位)必填
     */
    private Integer authCode;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }
}
