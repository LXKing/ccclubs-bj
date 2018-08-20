package com.ccclubs.action.vc.dto;

import java.io.Serializable;

/**
 * 车辆注册入参
 *
 * @author jianghaiyang
 * @create 2017-07-10
 **/
public class VehicleRegisterInput  implements Serializable {

    /**
     *   真实车牌
     */
    private String csvCarNo;

    /**
     *   车架号(必填)
     */
    private String csvVin;

    /**
     *   发动机号(必填)
     */
    private String csvEngineNo;

    /**
     *   合格证号(必填)
     */
    private String csvCertific;

    /**
     *   车颜色代码0: 白套红橙1: 白套玫瑰金2: 白套苹果绿3: 红橙套黑4: 红套黑5: 黄/黑6: 黄套黑7: 蓝白套8: 苹果绿/白9: 深空黑/红橙10: 紫白套
     *   (必填)
     */
    private Byte csvColorCode;

    /**
     * 内饰颜色
     */
    private String csvInteriorColorCode;

    /**
     *   车型(车型备案型号必填)
     */
    private String csvModel;

    /**
     *   出厂日期(必填)
     */
    private String csvProdDate;

    /**
     *   终端序列号
     */
    private String teNo;

    /**
     *  可充电储能系统编码
     */
    private String csvBataccuCode;

    /**
     * 车辆领域：0:未知,1:个人领域,2:公共领域
     */
    private Byte csvDomain;

    /**
     * 车型代码
     */
    private String csvModelCodeFull;

    /**
     * 车型备案型号
     */
    private String csvModelCodeSimple;

    public VehicleRegisterInput(){}
    public VehicleRegisterInput(String csvCarNo, String csvVin, String csvEngineNo, String csvCertific, Byte csvColorCode, String csvModel, String csvProdDate, String teNo, String csvBataccuCode) {
        this.csvCarNo = csvCarNo;
        this.csvVin = csvVin;
        this.csvEngineNo = csvEngineNo;
        this.csvCertific = csvCertific;
        this.csvColorCode = csvColorCode;
        this.csvModel = csvModel;
        this.csvProdDate = csvProdDate;
        this.teNo = teNo;
        this.csvBataccuCode = csvBataccuCode;
    }

    public VehicleRegisterInput(String csvVin, String csvEngineNo, String csvCertific, Byte csvColorCode, String csvModel, String csvProdDate, String teNo, String csvBataccuCode) {
        this.csvVin = csvVin;
        this.csvEngineNo = csvEngineNo;
        this.csvCertific = csvCertific;
        this.csvColorCode = csvColorCode;
        this.csvModel = csvModel;
        this.csvProdDate = csvProdDate;
        this.teNo = teNo;
        this.csvBataccuCode = csvBataccuCode;
    }

    public VehicleRegisterInput(String csvCarNo, String csvVin, String csvEngineNo, String csvCertific, Byte csvColorCode, String csvInteriorColorCode, String csvModel, String csvProdDate, String teNo, String csvBataccuCode, Byte csvDomain, String csvModelCodeFull, String csvModelCodeSimple) {
        this.csvCarNo = csvCarNo;
        this.csvVin = csvVin;
        this.csvEngineNo = csvEngineNo;
        this.csvCertific = csvCertific;
        this.csvColorCode = csvColorCode;
        this.csvInteriorColorCode = csvInteriorColorCode;
        this.csvModel = csvModel;
        this.csvProdDate = csvProdDate;
        this.teNo = teNo;
        this.csvBataccuCode = csvBataccuCode;
        this.csvDomain = csvDomain;
        this.csvModelCodeFull = csvModelCodeFull;
        this.csvModelCodeSimple = csvModelCodeSimple;
    }

    public String getCsvInteriorColorCode() {
        return csvInteriorColorCode;
    }

    public void setCsvInteriorColorCode(String csvInteriorColorCode) {
        this.csvInteriorColorCode = csvInteriorColorCode;
    }

    public Byte getCsvDomain() {
        return csvDomain;
    }

    public void setCsvDomain(Byte csvDomain) {
        this.csvDomain = csvDomain;
    }

    public String getCsvModelCodeFull() {
        return csvModelCodeFull;
    }

    public void setCsvModelCodeFull(String csvModelCodeFull) {
        this.csvModelCodeFull = csvModelCodeFull;
    }

    public String getCsvModelCodeSimple() {
        return csvModelCodeSimple;
    }

    public void setCsvModelCodeSimple(String csvModelCodeSimple) {
        this.csvModelCodeSimple = csvModelCodeSimple;
    }

    public String getCsvCarNo() {
        return csvCarNo;
    }

    public void setCsvCarNo(String csvCarNo) {
        this.csvCarNo = csvCarNo;
    }

    public String getCsvVin() {
        return csvVin;
    }

    public void setCsvVin(String csvVin) {
        this.csvVin = csvVin;
    }

    public String getCsvEngineNo() {
        return csvEngineNo;
    }

    public void setCsvEngineNo(String csvEngineNo) {
        this.csvEngineNo = csvEngineNo;
    }

    public String getCsvCertific() {
        return csvCertific;
    }

    public void setCsvCertific(String csvCertific) {
        this.csvCertific = csvCertific;
    }

    public Byte getCsvColorCode() {
        return csvColorCode;
    }

    public void setCsvColorCode(Byte csvColorCode) {
        this.csvColorCode = csvColorCode;
    }

    public String getCsvModel() {
        return csvModel;
    }

    public void setCsvModel(String csvModel) {
        this.csvModel = csvModel;
    }

    public String getCsvProdDate() {
        return csvProdDate;
    }

    public void setCsvProdDate(String csvProdDate) {
        this.csvProdDate = csvProdDate;
    }

    public String getTeNo() {
        return teNo;
    }

    public void setTeNo(String teNo) {
        this.teNo = teNo;
    }

    public String getCsvBataccuCode() {
        return csvBataccuCode;
    }

    public void setCsvBataccuCode(String csvBataccuCode) {
        this.csvBataccuCode = csvBataccuCode;
    }
}
