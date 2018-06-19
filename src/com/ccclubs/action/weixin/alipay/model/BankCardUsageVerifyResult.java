package com.ccclubs.action.weixin.alipay.model;

/**
 * 银行卡使用情况结果
 * @author wenfeng.cenwf
 * @version 2014年12月11日 下午7:20:52 
 */
public class BankCardUsageVerifyResult extends UsageVerifyResult {
    
    public static final String CHOGNZHI         = BankCardEnum.CHONGZHI.getLabel();

    public static final String TIXIAN           = BankCardEnum.TIXIAN.getLabel();

    public static final String KUAIJIE          = BankCardEnum.KUAIJIE.getLabel();

    public static final String QITA             = BankCardEnum.QITA.getLabel();

    private static final long  serialVersionUID = -3691130842573802531L;

    enum BankCardEnum {

        CHONGZHI("01", "充值卡"),

        TIXIAN("02", "提现卡"),

        KUAIJIE("03", "快捷卡"),

        QITA("04", "其他转账卡");

        private String label;

        private String desc;

        public String getLabel() {
            return label;
        }

        public String getDesc() {
            return desc;
        }

        private BankCardEnum(String label, String desc) {
            this.label = label;
            this.desc = desc;
        }
    }
}
