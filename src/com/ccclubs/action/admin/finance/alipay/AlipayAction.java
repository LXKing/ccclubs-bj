package com.ccclubs.action.admin.finance.alipay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsAlipayRefund;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsAlipayRecordService;
import com.ccclubs.service.admin.ICsAlipayRefundService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsRefundService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;

/**
 * 支付宝退款
 * @author uiu
 */
public class AlipayAction {
	
	ICsMemberService csMemberService;
	ICommonMoneyService commonMoneyService;
	ICsRefundService csRefundService;
	ICsAlipayRecordService csAlipayRecordService;
	ICsAlipayRefundService csAlipayRefundService;

	CsRefund csRefund = null;
	
	/**
	 * 支付宝退款
	 * @return
	 */
	public String execute(){
		try{
			//批次号
			final String csarTradeNo = $.getString("trade");
			final StringBuffer detailData=new StringBuffer();//提交到支付宝的退款明细数据
			final List<CsAlipayRefund> refunds = CsAlipayRefund.where().csarTradeNo(csarTradeNo).list(-1);//退款记录
			csRefundService.executeTransaction(new Function(){
				@Override
				public <T> T execute(Object... arg0) {
					for(CsAlipayRefund csAlipayRefund:refunds){
						if(detailData.length()>0)
							detailData.append("#");
						detailData.append(csAlipayRefund.get$csarRecord().getCsarAlipayNo());
						detailData.append("^");
						detailData.append(csAlipayRefund.getCsarAmount());
						detailData.append("^");
						detailData.append("北京出行分时租赁退款");						
					}
					return null;
				}
			});
			
			////////////////////////////////////请求参数//////////////////////////////////////

			//服务器异步通知页面路径
			//String notify_url = $.config("domain")+"my/alipay-Notify.html";
			String notify_url = $.config("service.domain")+"service/alipay.refundNotify.do";
			//需http://格式的完整路径，不允许加?id=123这类自定义参数

			//卖家支付宝帐户
			String seller_email = AlipayConfig.seller_email;
			//必填

			//退款当天日期
			String refund_date = $.date(new Date(), "yyyy-MM-dd HH:mm:ss");
			//必填，格式：年[4位]-月[2位]-日[2位] 小时[2位 24小时制]:分[2位]:秒[2位]，如：2007-10-01 13:13:13

			//批次号
			String batch_no = csarTradeNo;
			//必填，格式：当天日期[8位]+序列号[3至24位]，如：201008010000001

			//退款笔数
			String batch_num = ""+refunds.size();
			//必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
			
			//退款详细数据
			String detail_data = detailData.toString();
			//必填，具体格式请参见接口技术文档			
			
			//////////////////////////////////////////////////////////////////////////////////
			
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
	        sParaTemp.put("partner", AlipayConfig.partner);
	        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("seller_email", seller_email);
			sParaTemp.put("seller_user_id", AlipayConfig.partner);
			sParaTemp.put("refund_date", refund_date);
			sParaTemp.put("batch_no", batch_no);
			sParaTemp.put("batch_num", batch_num);
			sParaTemp.put("detail_data", detail_data);
			
			
			Logger.getLogger("alipay").info(sParaTemp);			
			
			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");

			return $.SendHtml(sHtmlText, SYSTEM.UTF8);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml(SYSTEM.ERROR_TIPS, "UTF-8");
		}		
	}

	public Double $(Double value){
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsRefundService getCsRefundService() {
		return csRefundService;
	}

	public void setCsRefundService(ICsRefundService csRefundService) {
		this.csRefundService = csRefundService;
	}

	public ICsAlipayRecordService getCsAlipayRecordService() {
		return csAlipayRecordService;
	}

	public void setCsAlipayRecordService(
			ICsAlipayRecordService csAlipayRecordService) {
		this.csAlipayRecordService = csAlipayRecordService;
	}

	public ICsAlipayRefundService getCsAlipayRefundService() {
		return csAlipayRefundService;
	}

	public void setCsAlipayRefundService(
			ICsAlipayRefundService csAlipayRefundService) {
		this.csAlipayRefundService = csAlipayRefundService;
	}


}
