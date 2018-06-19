package com.ccclubs.action.weixin.alipay.model;

/**
 * 地址使用情况
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:21:57 
 */
public class AddressUsageVerifyResult extends UsageVerifyResult {

    public static final String SHOUHUO          = AddressEnum.SHOUHUO.getLabel();

    private static final long  serialVersionUID = 6620941803844882929L;

    enum AddressEnum {

        SHOUHUO("21", "收货地址");

        private String label;

        private String desc;

        public String getLabel() {
            return label;
        }

        public String getDesc() {
            return desc;
        }

        private AddressEnum(String label, String desc) {
            this.label = label;
            this.desc = desc;
        }

    }
}
