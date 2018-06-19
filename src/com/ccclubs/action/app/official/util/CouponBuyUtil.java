package com.ccclubs.action.app.official.util;

import java.math.BigDecimal;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.config.Constant;
import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsRecord;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.util.AESCodec;
import com.lazy3q.web.helper.$;

/**
 * 现金券购买计算
 * 
 * @author joel
 *
 */
public class CouponBuyUtil {
	
	public final static String 默认优惠券 = "COUPON";  
	
	public final static String 北京出行充值送活动优惠券 = "ALIPAY_WALLET";  //充值送
	
	/**
	 * 单价
	 */
	private Double price;
	
	/**
	 * 现金券面额
	 */
	private Double amount;
	
	/**
	 * 实际支付价格
	 */
	private Double needPayTotalFee;
	
	/**
	 * 赠送金额
	 */
	private Double presentAmount;
	
	/**
	 * 折扣
	 */
	private Double rebate;
	
	/**
	 * 数量
	 */
	private int count;
	
	/**
	 * 描述
	 */
	private String text;
	
	/**
	 * 购买现金券计算
	 * @param item
	 * @param amount
	 * @return
	 */
	public CouponBuyUtil calculate(CsItem item, int count, Long memberId){
		if(item ==null)return null;
		
		Double presentLimit = null;
		if(item.getCsiFlag().equals(CouponBuyUtil.北京出行充值送活动优惠券)){
			Double presentAmount = getPresentAmountTotalByItem(item, memberId);
			if(presentAmount <0)throw new MessageException(ErrorCode.SYSTEM_ERROR, 4, "计算现金券价格异常");
			
			presentLimit = 300-presentAmount;
		}
		presentLimit = presentLimit == null ? 0 : presentLimit;
		if(presentLimit<0)presentLimit = 0d;
		
		presentLimit = new BigDecimal(presentLimit).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		try {
			 engine.put("price", item.getCsiPrice());
			 engine.put("count", count);
			 engine.put("rebate", item.getCsiRebate());
			 
			 engine.put("text", "");
			 engine.put("amount", "0");
			 engine.put("totalPrice", "0");
			 engine.put("present", "0");
			 engine.put("presentLimit", presentLimit);
			 
			 String depict = item.getCsiRemark();
			 if(StringUtils.isNotEmpty(depict)){
				 depict = decryptScript(depict);
			 }
			 
			 if(StringUtils.isEmpty(depict)){
				 depict = "totalPrice=(price*rebate)*count;amount=totalPrice;present=0;";
			 }
			 engine.eval(depict);
			 this.setAmount(engine.get("amount") == null ? null : Double.valueOf(engine.get("amount").toString()));
			 this.setNeedPayTotalFee(engine.get("totalPrice") == null ? null : Double.valueOf(engine.get("totalPrice").toString()));
			 
			 //如果设置了赠送上限金额，则赠送金额不能大于上限金额
			 Double presentAmount = engine.get("present") == null ? null : Double.valueOf(engine.get("present").toString());
			 if(presentLimit!=null && presentAmount!=null){
				 if(presentAmount>presentLimit)presentAmount = presentLimit;
			 }
			 this.setPresentAmount(presentAmount);
			 
			 this.setCount(count);
			 this.setRebate(item.getCsiRebate());
			 this.setPrice(item.getCsiPrice());
			 
			 String text = engine.get("text") == null ? "" : engine.get("text").toString();
			 text = text.replace("{present}", (presentAmount == null ? "" : presentAmount.toString()));
			 this.setText(text);
		} catch (Exception e) {
			Logger.getRootLogger().error(e.getMessage(),e);
			throw new MessageException(ErrorCode.SYSTEM_ERROR,3,"计算现金券价格异常");
		}
		return this;
	}
	
	/**
	 * 获得赠送金额总数
	 * @param item
	 * @return
	 */
	public Double getPresentAmountTotalByItem(CsItem item, Long memberId){
		ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		
		
		Double total = 0d;
		List<CsRecord> recordList = csRecordService.getCsRecordList($.add(CsRecord.F.csrMember, memberId)
								.add(CsRecord.F.csrRelate, item.getCsiId())
								.add(CsRecord.F.csrMoneyType, 1)		//1:现金券
								.add(CsRecord.F.csrObject, CsItem.class.getSimpleName()+"@"+item.getCsiId()), Integer.MAX_VALUE);
		
		Double sentTotal = csCoinService.getCsCoinEval("sum(csc_count)", $.add(CsCoin.F.cscMember, memberId).add(CsCoin.F.cscFlag, CsItem.class.getSimpleName()+"@"+item.getCsiId()).add(CsCoin.F.cscStatus, 1));
		if(sentTotal == null) sentTotal = 0d;
		if(recordList!=null){
			String remark, a , b;
			for(CsRecord cr : recordList){
				remark = cr.getCsrRemark();
				a = remark.substring(remark.indexOf("帐户支付")+4, remark.indexOf("元"));
				b = remark.substring(remark.indexOf("获得")+2, remark.indexOf("元现金券"));
				Double amountA = Double.valueOf(a);
				Double amountB = Double.valueOf(b);
				Double present = amountB - amountA;
				total += present;
			}
		}
		return total + sentTotal;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getNeedPayTotalFee() {
		return needPayTotalFee;
	}

	public void setNeedPayTotalFee(Double needPayTotalFee) {
		this.needPayTotalFee = needPayTotalFee;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * 获取现金券金额
	 * @return
	 */
	public Double getCouponAmount(){
		return this.amount - this.presentAmount;
	}
	
	/**
	 * 获取红包金额
	 * @return
	 */
	public Double getCoinAmount(){
		return this.presentAmount;
	}



	final static String key = "SmeoLufLwoE2zLw7+VIIKQ==";
	public String encryptScript(String script){
		try {
			return AESCodec.encrypt(script, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String decryptScript(String desContent){
		try {
			return AESCodec.decrypt(desContent, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String script = "totalPrice=price*count;present=price*1*count;if(present>presentLimit){present=presentLimit;}amount=totalPrice+present;if(present>0){text='购买再送{present}元红包';}"; 	//北京出行活动现金券	2017.04.07修改规则
//		String script = "totalPrice=price*count;present=price*2*count;if(present>presentLimit){present=presentLimit;}amount=totalPrice+present;if(present>0){text='购买再送{present}元红包';}"; 	//北京出行活动现金券
//		String script = "totalPrice=price*count;present=(price+=20)*count;if(present>presentLimit){present=presentLimit;}amount=totalPrice+present;if(present>0){text='购买再送{present}元现金券';}"; 	//北京出行活动现金券
		
		try {
			System.out.println(new CouponBuyUtil().encryptScript(script));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//String a = "Rd41RdgYnVaIZvY26Ux1ddcJGK+nsONsXLmML3shS1LSxg94Hxa1ublZ5/r2/0Pt90N1spN1J346AGfszI/pyUlp7z4r8L0qv4jR0jkQDUQ/q49CtUfcgvQXx7TevPT0eQXibOfADwXj6V8JUC156O/w9Ft/y6wbjuH4qGMMnbq3H9Q77oposAshVzHbrXoyGxbGf3PJ12VsiRIMP6kYaS4PP/mCsJ+XQi2fk1rEUC0=";
//		String a = "Rd41RdgYnVaIZvY26Ux1ddcJGK+nsONsXLmML3shS1LSxg94Hxa1ublZ5/r2/0Pt90N1spN1J346AGfszI/pyUlp7z4r8L0qv4jR0jkQDUQ/q49CtUfcgvQXx7TevPT0eQXibOfADwXj6V8JUC156O/w9Ft/y6wbjuH4qGMMnbq3H9Q77oposAshVzHbrXoyGxbGf3PJ12VsiRIMP6kYaS4PP/mCsJ+XQi2fk1rEUC0=";
		String a = "Rd41RdgYnVaIZvY26Ux1ddcJGK+nsONsXLmML3shS1IaEYg/tu1YTqCH6V/VY3Gt90N1spN1J346AGfszI/pyUlp7z4r8L0qv4jR0jkQDUQ/q49CtUfcgvQXx7TevPT0eQXibOfADwXj6V8JUC156O/w9Ft/y6wbjuH4qGMMnbq3H9Q77oposAshVzHbrXoyGxbGf3PJ12VsiRIMP6kYaS4PP/mCsJ+XQi2fk1rEUC0=";
		System.out.println(new CouponBuyUtil().decryptScript(a));
	}
	
}
