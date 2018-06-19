package com.ccclubs.action.weixin.alipay.model;

/**
 * 用户信息
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午4:20:19 
 */
public class AliTrustUserInfo {

    /** 查询是否身份冒用 */
    public static final String TYPE_IDENTITY_FRAUDULENT = "identity_fraudulent";

    /** 银行卡使用情况 */
    public static final String TYPE_BANKCARD_USAGE      = "bankcard_usage";

    /** 地址使用情况 */
    public static final String TYPE_ADDRESS_USAGE       = "address_usage";

    /** 手机使用情况 */
    public static final String TYPE_MOBILE_USAGE        = "mobile_usage";

    /** ip地址是否可信 */
    public static final String TYPE_IP_CREDIBLE         = "ip_credible";

    /** mac地址是否可信 */
    public static final String TYPE_MAC_CREDIBLE        = "mac_credible";

    /** imei使用情况 */
    public static final String TYPE_IMEI_CREDIBLE       = "imei_credible";

    /** lbs使用情况 */
    public static final String TYPE_LBS_CREDIBLE        = "lbs_credible";

    /** ip是否高危 */
    public static final String TYPE_IP_RISK             = "ip_risk";

    /** mac是否高危 */
    public static final String TYPE_MAC_RISK            = "mac_risk";

    /** imei是否高危 */
    public static final String TYPE_IMEI_RISK           = "imei_risk";

    private String             certNo;

    private String             name;

    private String             mobile;

    private String             bankCard;

    private String             address;

    private String             ip;

    private String             imei;

    private String             mac;

    private String             lbs;

    /** 反欺诈查询类型，可以由以上11种类型叠加而成，多种类型之间以英文","进行分割 */
    private String             types;

    public AliTrustUserInfo() {

    }

    public AliTrustUserInfo(String certNo, String name) {
        this.certNo = certNo;
        this.name = name;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLbs() {
        return lbs;
    }

    public void setLbs(String lbs) {
        this.lbs = lbs;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

}
