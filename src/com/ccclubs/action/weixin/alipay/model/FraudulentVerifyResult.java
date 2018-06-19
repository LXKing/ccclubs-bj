package com.ccclubs.action.weixin.alipay.model;

public class FraudulentVerifyResult extends BasicVerifyResult {

    private static final long  serialVersionUID  = 8160215152539741202L;

    public static final String IS_NOT_Fraudulent = "0.0";               //非冒用
    public static final String IS_FRAUDULENT     = "10.0";              //冒用
    public static final String NO_DATA           = "-2.0";              //暂无数据
    public static final String UNKNOWN           = "-1.0";              //未知

    /**  
     * 身份冒用标签：01：模型判断身份冒用；02：涉嫌批量认证；03：盗卡案件；04：证件被他人占用；05：无冒用
     * 多个标签之间以英文","分隔
     * */
    private String             fraudulentLabel;

    public String getFraudulentLabel() {
        return fraudulentLabel;
    }

    public void setFraudulentLabel(String fraudulentLabel) {
        this.fraudulentLabel = fraudulentLabel;
    }

    enum FraudulentEnum {

        MAOYONG("01", "模型判断身份冒用"),

        PILIANGRENZHENG("02", "涉嫌批量认证"),

        DAOKA("03", "盗卡案件"),

        ZHANYONG("04", "证件被他人占用"),

        WUMAOYONG("05", "无冒用");

        private String label; //标签

        private String desc; //描述

        public String getLabel() {
            return label;
        }

        public String getDesc() {
            return desc;
        }

        private FraudulentEnum(String label, String desc) {
            this.label = label;
            this.desc = desc;
        }

    }
}
