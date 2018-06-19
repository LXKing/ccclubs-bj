package com.ccclubs.action.weixin.alipay.model;

import java.io.Serializable;

public class VerifyResult implements Serializable {

    private static final long         serialVersionUID = -6860428779855628149L;

    /** 身份冒用 */
    private FraudulentVerifyResult    identityFraudulent;

    /** 银行卡使用情况 */
    private BankCardUsageVerifyResult bankCardUsage;

    /** 地址使用情况 */
    private AddressUsageVerifyResult  addressMatched;

    /** 手机号使用情况 */
    private MobileUsageVerifyResult   mobileMatched;

    /** IP地址是否可信 */
    private CredibleResult            ipCredible;

    /** MAC地址是否可信 */
    private CredibleResult            macCredible;

    /** IMEI是否可信 */
    private CredibleResult            imeiCredible;

    /** LBS是否可信 */
    private CredibleResult            lbsCredible;

    /** IP地址是否高危 */
    private RiskVerifyResult          ipRisk;

    /** MAC地址是否高危 */
    private RiskVerifyResult          macRisk;

    /** IMEI是否高危 */
    private RiskVerifyResult          imeiRisk;

    public FraudulentVerifyResult getIdentityFraudulent() {
        return identityFraudulent;
    }

    public void setIdentityFraudulent(FraudulentVerifyResult identityFraudulent) {
        this.identityFraudulent = identityFraudulent;
    }

    public BankCardUsageVerifyResult getBankCardUsage() {
        return bankCardUsage;
    }

    public void setBankCardUsage(BankCardUsageVerifyResult bankCardUsage) {
        this.bankCardUsage = bankCardUsage;
    }

    public AddressUsageVerifyResult getAddressMatched() {
        return addressMatched;
    }

    public void setAddressMatched(AddressUsageVerifyResult addressUsage) {
        this.addressMatched = addressUsage;
    }

    public MobileUsageVerifyResult getMobileMatched() {
        return mobileMatched;
    }

    public void setMobileMatched(MobileUsageVerifyResult mobileUsage) {
        this.mobileMatched = mobileUsage;
    }

    public CredibleResult getIpCredible() {
        return ipCredible;
    }

    public void setIpCredible(CredibleResult ipCredible) {
        this.ipCredible = ipCredible;
    }

    public CredibleResult getMacCredible() {
        return macCredible;
    }

    public void setMacCredible(CredibleResult macCredible) {
        this.macCredible = macCredible;
    }

    public CredibleResult getImeiCredible() {
        return imeiCredible;
    }

    public void setImeiCredible(CredibleResult imeiCredible) {
        this.imeiCredible = imeiCredible;
    }

    public CredibleResult getLbsCredible() {
        return lbsCredible;
    }

    public void setLbsCredible(CredibleResult lbsCredible) {
        this.lbsCredible = lbsCredible;
    }

    public RiskVerifyResult getIpRisk() {
        return ipRisk;
    }

    public void setIpRisk(RiskVerifyResult ipRisk) {
        this.ipRisk = ipRisk;
    }

    public RiskVerifyResult getMacRisk() {
        return macRisk;
    }

    public void setMacRisk(RiskVerifyResult macRisk) {
        this.macRisk = macRisk;
    }

    public RiskVerifyResult getImeiRisk() {
        return imeiRisk;
    }

    public void setImeiRisk(RiskVerifyResult imeiRisk) {
        this.imeiRisk = imeiRisk;
    }
}
