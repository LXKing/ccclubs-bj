package com.ccclubs.constants;

/**
 * 
 * @function 短信模板标识枚举
 * @author cjb
 * @createTime 2018年8月2日
 */
public enum SmsFlagEnum {
    
    /************认证相关短信***********/
    实名认证四证通过("REC_AUTH_COMPLETE_FOUR"),
    实名认证三证通过("REC_AUTH_COMPLETE_THREE"),
    身份证认证失败("REC_AUTH_IDCARD_FAIL"),
    驾驶证认证失败("REC_AUTH_DRIVER_FAIL"),
    工作证认证失败("REC_AUTH_WORK_FAIL");
    
    //段姓模板标识
    private String flag;

    private SmsFlagEnum(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    } 

}
