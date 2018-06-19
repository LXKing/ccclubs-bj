package com.ccclubs.action.weixin.alipay.model;

/**
 * 手机使用情况
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:26:49 
 */
public class MobileUsageVerifyResult extends UsageVerifyResult {

    private static final long  serialVersionUID = 3426605251384947804L;

    public static final String BANGDING         = MobileTypeEnum.BANGDING.getLabel();
    public static final String KUAIJIE          = MobileTypeEnum.KUAIJIE.getLabel();
    public static final String SHOUHUO          = MobileTypeEnum.SHOUHUO.getLabel();
    public static final String CHOGNZHI         = MobileTypeEnum.CHOGNZHI.getLabel();

    enum MobileTypeEnum {

        BANGDING("11", "账户绑定手机"),

        KUAIJIE("12", "快捷签约手机"),

        SHOUHUO("13", "收货手机"),

        CHOGNZHI("14", "充值手机");

        private String label;

        private String desc;

        public String getLabel() {
            return label;
        }

        public String getDesc() {
            return desc;
        }

        private MobileTypeEnum(String label, String desc) {
            this.label = label;
            this.desc = desc;
        }
    }

}
