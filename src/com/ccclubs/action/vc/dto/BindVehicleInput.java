package com.ccclubs.action.vc.dto;

import java.io.Serializable;

/**
 * 绑定操作输入
 *
 * @author jianghaiyang
 * @create 2017-07-03
 **/
public class BindVehicleInput implements Serializable{
    /**
     * 车辆vin码必填
     */
    private String vin;

    /**
     * 终端序列号必填
     */
    private String teNo;

    private String appId;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTeNo() {
        return teNo;
    }

    public void setTeNo(String teNo) {
        this.teNo = teNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
