package com.ccclubs.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.action.app.official.util.CouponBuyUtil;
import com.ccclubs.config.CommonMessage;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.helper.SimpleSerialExecutor;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsAlipayRecord;
import com.ccclubs.model.CsAlipayRefund;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsItemService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.common.ICMBPayService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;

public class CMBPayService implements ICMBPayService {

	//招行手机银行网上充值页地址
	private final static String cmb_mobile_pay_url="https://netpay.cmbchina.com/netpayment/BaseHttp.dll?prepaywap?";
	//支付商户开户分行号
	private final static String BranchID="0010";				//
	//支付商户号
	private final static String CoNo="008602";					//测试：000818
	//支付密钥
	private final static String payKey="G2S1Npg6B438K587";		//测试：abcCBA1234554321
	//退款密钥
	private final static String refundKey="G2S1Npg6B438K587";	//测试：abcCBA1234554321
	//登录密钥
	private final static String loginKey="985631";	//测试：abcCBA1234554321
	//我方充值通知页面
	private final String NoticeUrl = $.config("service.domain")+"service/cmb.notice.do";
	//我方同步回调页面
	private final String CallbackUrl = $.config("service.domain")+"service/cmb.callback.do";
	
	public static void main(String...strings){
		String url = new CMBPayService().executeCMBPayMobileUrl(10000000l, 10d, null, null);
		$.trace(url);
	}
	
	@Override
	public String executeCMBPayMobileUrl(Long member, Double money,
			String callbackUrl, String flag, String out_trade_no) {
		CsMember csMember=CsMember.get(member);
		if(StringUtils.isEmpty(out_trade_no)){
			out_trade_no = $.date(new Date(), "yyyyMMddHHmmssSSS") + member;
		}
		
		//在接口充值记录表中插入一条记录
		CsAlipayRecord csAlipayRecord = new CsAlipayRecord();
		csAlipayRecord.setNotNull(csMember.getCsmHost(), (short)10, out_trade_no, member, csMember.getCsmName(), money, new Date(), new Date(), (short) 0);
		csAlipayRecord.csarRefund(0d).csarPayMethod(flag);
		csAlipayRecord.csarThirdBack(callbackUrl);
		csAlipayRecord.setCsarBlank("CMB");
		csAlipayRecord = csAlipayRecord.save();
		
		//##################################本地调试用##################################
		//csAlipayRecord.setNotNull(22l, (short)10,$.date(new Date(), "yyyyMMddHHmmssSSS") + member, member, "徐刚", money, new Date(), new Date(), (short) 0);
		//csAlipayRecord.setCsarId(111l);//本地调试
		//##################################本地调试用##################################
		
		Map<String,String> params = new HashMap();
				
		params.put("BillNo", $.zerofill(csAlipayRecord.getCsarId(), 10));//我方订单号
		params.put("Amount", money.toString());//充值额
		params.put("Date", $.date(new Date(), "yyyyMMdd"));//充值日期
		params.put("BranchID", BranchID);//支付商户开户分行号，请咨询开户的招商银行分支机构；
		params.put("CoNo", CoNo);//支付商户号，6位长数字，由银行在商户开户时确定；
		params.put("MerchantUrl", NoticeUrl);//回调地址
		params.put("MerchantPara", "");//回调参数
		params.put("PayeeID", member.toString());//回调参数
		params.put("AppType", "C");//招商银行手机银行客户端固定为C，目前不支持其它参数值。
		
		//如果调用者配置了三方回调，则先回调到接口充值同步回调地址，再转跳到三方回调
		if($.empty(callbackUrl)){
//			params.put("MerchantRetUrl", CallbackUrl);
		}else{
			params.put("MerchantRetUrl", callbackUrl);
		}
		
		String strMerchantCode = cmb.MerchantCode.genMerchantCode(
				payKey, 
				params.get("Date"), 
				params.get("BranchID"),
				params.get("CoNo"),
				params.get("BillNo"), 
				params.get("Amount"),
				"", 
				params.get("MerchantUrl"), 
				params.get("PayeeID"),
				"", 
				"",
				"54011600", 
				"");
		
		params.put("MerchantCode", strMerchantCode);//商户校验码
		
		String strParams = "";
		for(String key:params.keySet()){
			if(!strParams.equals(""))strParams+="&";
			strParams+=key+"="+params.get(key);
		}
		
		return cmb_mobile_pay_url+strParams;
	}

	@Override
	public String executeCMBPayMobileUrl(Long member, Double money,String callbackUrl, String flag) {
		return executeCMBPayMobileUrl(member, money, callbackUrl, flag, null);
	}
	
	@Override
	public Long updateCMBPayResponse() {
		try {
			    final String BillNo = $.getString("BillNo");
				String Succeed = $.getString("Succeed");
				String CoNo = $.getString("CoNo");
				String Amount = $.getString("Amount");
				String Date = $.getString("Date");
				String MerchantPara = $.getString("MerchantPara");
				String Msg = $.getString("Msg");
				String Signature = $.getString("Signature");
				
				//银行流水号
				final String bank_trade_no = Msg.substring(18, 38);
				
				final CsAlipayRecord csAlipayRecord = CsAlipayRecord.where().csarId(BillNo).get();
				if (csAlipayRecord == null) {
					Logger.getRootLogger().error("招行充值返回错误 - 无法找到相关的充值记录 ");
					return null;
				} else if(csAlipayRecord.getCsarStatus().shortValue() != 0) {
					Logger.getRootLogger().error("招行充值返回放弃处理 - " + $.json(csAlipayRecord));
					return null;
				}
				
				Boolean bValid = false;
				cmb.netpayment.Security pay = null;
				try {
					pay = new cmb.netpayment.Security(this.getClass().getResource("/").getPath()+"/cmb.public.key");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				String rbg = "Succeed="+Succeed+"&CoNo="+CoNo+"&BillNo="+BillNo+"&Amount="+Amount+"&Date="+Date+"&MerchantPara="+MerchantPara+"&Msg="+Msg+"&Signature="+Signature;
				bValid = pay.checkInfoFromBank(rbg);
				if(bValid){
					CsAlipayRecord.execute(new Function() {
						@Override
						public <T> T execute(Object... arg0) {
							Logger.getRootLogger().info("招行充值返回 - "+$.getQueryString($.UTF8));
							if (csAlipayRecord == null) {
								Logger.getRootLogger().error("招行充值返回错误 - 无法找到相关的充值记录 ");
								return null;
							} else if (csAlipayRecord.getCsarStatus().shortValue() == 0) {
								// 修改充值记录为已成功
								new CsAlipayRecord()
								.csarAlipayNo(bank_trade_no)// 招行交易号
								.csarStatus((short) 1)// 状态为成功
								.csarId(csAlipayRecord.getCsarId())// 原充值记录ID
								.csarUpdateTime(new Date())// 更新时间为当前时间
								.update();
								// 修改会员余额帐户
								String remark = "";
								if (csAlipayRecord.getCsarPayMethod().equals("directPay")) {
									remark = CommonMessage.MSG0309.replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
								} else {
									remark = CommonMessage.MSG0313.replace("{bank}", SystemHelper.convertBank(csAlipayRecord.getCsarBlank())).replace("{money}", LZ.digit(csAlipayRecord.getCsarAmount(), 2));
								}
								CommonMoneyService.getBean().updateMoney(csAlipayRecord.getCsarMember(), MoneyType.Money, csAlipayRecord.getCsarAmount(), RecordType.招行充值, remark, null, csAlipayRecord.getCsarId(), CsAlipayRecord.class);
								
								//================================充值宝贝后续处理===============================================
								if(csAlipayRecord.getCsarBuyItem()!=null){
									final ICsItemService csItemService = $.GetSpringBean("csItemService");
									final CsItem item = csItemService.getCsItem($.add(CsItem.F.csiId, csAlipayRecord.getCsarBuyItem()));
									final ICommonMoneyService	commonMoneyService = $.GetSpringBean("commonMoneyService");
									if(item ==null){
										Logger.getRootLogger().error("招商银行充值返回[" + csAlipayRecord.getCsarTradeNo() + "][" + BillNo + "]充值宝贝找不到记录 - " + $.json(csAlipayRecord));
										return null;
									}
									commonMoneyService.chargeCoupon(csAlipayRecord.getCsarMember(), item, csAlipayRecord.getCsarBuyCount());
								}
								//================================充值宝贝后续处理===============================================
		
								return null;
							} else {// 放弃处理
								Logger.getRootLogger().error("招行充值返回放弃处理 - " + $.json(csAlipayRecord));
								return null;
							}
						}
					});
				}else{
					throw new RuntimeException("招行充值处理回调校验失败，信息："+rbg);
				}
			
				return csAlipayRecord.getCsarId();
		}finally{
			
		}
	}

	
	public Boolean executeCMBRefund(final String csarTradeNo) {
		cmb.netpayment.Settle settle=new cmb.netpayment.Settle();
		settle.SetOptions("payment.ebank.cmbchina.com");
		int iRet = settle.LoginC(BranchID,CoNo,loginKey);
		if (iRet != 0){
			System.out.println("招行退款时登录失败 - "+settle.GetLastErr(iRet));
			return false;
		}
		try{
			List<CsAlipayRefund> refunds = CsAlipayRefund.where().csarTradeNo(csarTradeNo).csarStatus(0).list(-1);
			for(CsAlipayRefund csAlipayRefund:refunds){
				/**
				 *  RefundOrder 
				 * 	参数说明：
				 * 	pszDate	定单的交易日期。格式为8位数字。例如“20050810”
				 * 	pszBillNo	订单号
				 * 	pszAmt	退款金额。应该带有2位小数
				 * 	pszDesc	退款说明。长度不应该超过100字节
				 * 	pszKey	商户密钥。
				 * 	建议把商户密钥作为一个配置文件保存，在调用直联退款之前，读取出来。
				 */
				iRet = settle.RefundOrder($.date(csAlipayRefund.get$csarRecord().getCsarAddTime(), "yyyyMMdd"),$.zerofill(csAlipayRefund.get$csarRecord().getCsarId(), 10),$.digit(csAlipayRefund.getCsarAmount(),2),"直接退款",refundKey);
			    if(iRet == 0){
			    	csAlipayRefund.csarStatus((short)2).csarAlipayNo(settle.GetLastErr(iRet)).update();
					CommonMoneyService.getBean().updateMoney(csAlipayRefund.getCsarMember(), MoneyType.Money, -Math.abs(csAlipayRefund.getCsarAmount()), RecordType.余额退款, "接口退款", null, csAlipayRefund.getCsarRefund(), CsAlipayRefund.class);
					// 对应的支付宝充值记录上修改已退金额数
					CsAlipayRecord.where().csarId(csAlipayRefund.getCsarRecord()).set().definex("csar_refund=csar_refund+" + csAlipayRefund.getCsarAmount()).update();
					// 对应的支付宝退款记录修改为成功状态
					csAlipayRefund.csarStatus((short)1).csarUpdateTime(new Date()).update();
					// 对应的退款记录修改已退金额数
					CsRefund.where().csrId(csAlipayRefund.getCsarRefund()).set().definex("csr_amount=csr_amount+" + csAlipayRefund.getCsarAmount()).csrStatus(2).update();
			    } else {
			    	csAlipayRefund.csarStatus((short)2).update();
			    	CsRefund.where().csrId(csAlipayRefund.getCsarRefund()).set().csrStatus(3).csrRemark("退款失败，错误码："+settle.GetLastErr(0)).update();
			    	Logger.getRootLogger().error("退款失败，错误码："+settle.GetLastErr(0));
			    	return false;
			    }
			}
		}finally{
			settle.Logout();
		}
		return true;
	}		
}
