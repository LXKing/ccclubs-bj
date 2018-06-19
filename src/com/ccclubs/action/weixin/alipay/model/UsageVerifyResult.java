package com.ccclubs.action.weixin.alipay.model;

/**
 * 
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:19:31 
 */
public class UsageVerifyResult extends BasicVerifyResult {

    private static final long  serialVersionUID = -2353948814284323942L;

    public static final String IS_NOT_SELF_USE  = "0.0";                //非本人使用
    public static final String IS_SELF_USE      = "10.0";               //本人使用
    public static final String UNKNOWN          = "-1.0";               //未知

    /** 
     * 是否曾经使用过
     *  */
    private String             isUsed;

    /**  
     * 首次使用时间
     * */
    private String             startTime;

    /**  
     * 最近使用时间
     * */
    private String             lastTime;

    /**  
     * 多个结果之间以英文","分隔
     * 客体为银行卡时：01充值卡；02提现卡；03快捷卡；04其他转账卡
     * 客体为手机时：  11账户绑定手机；12快捷签约手机；13收货手机；14充值手机
     * 客体为地址时：  21收货地址
     * */
    private String             sceneType;

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String firstUseDate) {
        this.startTime = firstUseDate;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastUseDate) {
        this.lastTime = lastUseDate;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }
}
