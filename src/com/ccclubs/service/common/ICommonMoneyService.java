package com.ccclubs.service.common;

import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM.GrowRecordType;
import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.entity.Invoice;
import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsItem;
import com.ccclubs.model.CsRecord;

public interface ICommonMoneyService {
	
	/**
	 * 默认三个月有效期
	 */
	public final static short validity = 3;
	
	public enum MoneyType{Money,Coupon};
	
	/**
	 * 修改会员帐户信息
	 * 
	 * @param memberId会员ID
	 * @param moneyType帐户类型
	 * @param amount改变额
	 * @param recordType
	 *            类型描述
	 * @param remark
	 *            备注信息
	 * @param order
	 *            关联订单
	 * @param relate
	 *            其它关联对象ID
	 */
	public CsRecord updateMoney(Long memberId,MoneyType moneyType,Double amount,RecordType recordType,String remark,Long order,Long relate,Class relateClass)throws MessageException;
	public CsRecord updateMoney(Long hostid,Long memberId,MoneyType moneyType,Double amount,RecordType recordType,String remark,Long order,Long relate,Class relateClass)throws MessageException;
	
	/**
	 * 充值现金券
	 * @param memberId
	 * @param amount
	 * @return
	 */
	public int chargeCoupon(Long memberId, CsItem item, int count)throws MessageException;
	
	/**
	 * 充值卡兑换（含现金券、免费小时、红包）
	 * @param csRechargeCard
	 * 			兑换现金券实例
	 * @param member
	 * 			会员
	 * @param remark
	 * 			消费记录描述
	 * @return	200：兑换成功，201:用户不存在，202：兑换现金券实例不存在，203：只有出库的才能兑换
	 */
	public int executeRechangeCard(String csrcNumber, String csrcPassword, Long member, String remark)throws MessageException;
	
	
	/**
	 * 完善资料送88红包
	 * @param member
	 */
	public void giveCoinByPerfectInfo(Long member);
	
	/**
	 * 获取会员可用额(余额+现金券+红包)
	 * 
	 * @param csoPayMember
	 *            会员ID
	 * @return
	 */
	public Double getUsableAmount(Long memberid);	
	
	/**
	 * 获取会员可用余额和现金券和信用值
	 * 
	 * @param csoPayMember
	 *            会员ID
	 * @return
	 */
	public Double getUsableMoneyAndCouponAndCredit(Long memberid);
		
	
	/**
	 * 获取会员可用信用额
	 * 
	 * @param csoPayMember
	 *            会员ID
	 * @return
	 */
	public Double getUsableCredit(Long memberid);
	
	
	/**
	 * 给会员加积分
	 * 
	 * @param memberId
	 *            会员ID
	 * @param arg
	 *            积分参数，一般情况下传1就好了，像消费订单时传入的是订单额
	 * @param flag
	 *            标识，RecordType里面有就用RecordType里面的，没有就在RecordType里面创建一个
	 * @param remark
	 *            描述
	 * @param order
	 * @param relate
	 */
	public void updateIntegral(Long memberId,Double amount, IntegralType flag,String remark,Long relate);
	public void updateIntegral(Long hostid,Long memberId,Double amount, IntegralType flag,String remark,Long relate);
	public void updateIntegralForWeixin(Long hostid,Long memberId,Double amount, IntegralType flag,String remark,Long relate);
	public void updateIntegralByRule(Long memberId,Double arg, IntegralType flag,String remark,Long relate);
	public void updateIntegralByRule(Long hostid,Long memberId,Double arg, IntegralType flag,String remark,Long relate);
	
	
	
	/**
	 * 更新会员成长值，并自动保存成长值记录
	 * @param memberId 会员ID
	 * @param growRecordType 类型枚举
	 * @param relate 关联ID
	 * @param args 参考数值
	 */
	public void updateGrow(Long csmId, GrowRecordType growRecordType, Long relate, Number...args);
	
	
	/**
	 * 信用支付订单
	 * @param csoHost
	 * @param csmId
	 * @param payReal
	 * @param descript
	 * @param csoId
	 */
	public Long payCredit(Long hostid, Long memberid,Long creditCard,double payReal,String descript, Long orderid,Long violatid,Long troubleid);	
	
	
	/**
	 * 使用帐户余额还信用卡帐单（目前只支持保证金还帐单）
	 * @param creditBill
	 */
	public String repayCreditBillByAccount(Long creditBill,String remark);
	
	/**
	 * 撤销信用卡帐单
	 * @param cscbId
	 * @param cscbRemark
	 * @return
	 */
	public String cancelCreditBill(Long cscbId, String cscbRemark);
	
	/**
	 * 设置信用卡帐单为已付
	 * @param cscbId
	 * @param cscbPaySerial
	 * @param cscbRemark
	 * @param bManual
	 * @return
	 */
	public String setCreditBillPayed(Long cscbId, String cscbPaySerial,String cscbRemark,Boolean bManual);
	
	/**
	 * 设置信用卡帐单为坏帐
	 * @param cscbId
	 * @param cscbRemark
	 * @return
	 */
	public String setCreditBillBad(Long cscbId, String cscbRemark);
	
	/**
	 * 获取会员在某个城市的红包
	 * @param host
	 * @param memberid
	 * @param date
	 * @return
	 */
	public Double getCoin(Long host,Long memberid,Date date);
	
	
	/**
	 * 获取会员可用的红包
	 * @param csoPayMember
	 * @param csoStartTime
	 * @return
	 */
	public List<CsCoin> getCanUseCoinList(Long host,Long memberid, Date date);
	
	
	/**
	 * 获取会员所有的免费小时
	 * 
	 * @param memberid
	 *            会员ID
	 * @param date
	 *            时间点:理论上应该是当前时间，实际上有可能是某个订单的开始时间
	 * @return
	 */
	public Double getCanUseFreeHour(Long memberid,Date date);
	
	
	/**
	 * 获取会员所有的免费小时列表
	 * 
	 * @param memberid
	 * @param date
	 *            时间点:理论上应该是当前时间，实际上有可能是某个订单的开始时间
	 * @return
	 */
	public List<CsFreeHour> getCanUseFreeHourList(Long memberid,Date date);

	/**
	 * 获取被某个订单冻结的免费小时数
	 * 
	 * @param orderid
	 *            订单ID
	 * @return
	 */
	public Double getFreezeFreeHour(Long orderid);

	/**
	 * 获取被某个订单冻结的优惠项数
	 * 
	 * @param orderid
	 *            订单ID
	 * @param giftid
	 *            优惠项ID
	 * @return
	 */
	public Double getFreezeGift(Long orderid,Long giftid);	
	
	/**
	 * 查看会员是否有未完成的订单
	 */
	public Boolean haveUnFinishOrder(Long memberid);
	
	/**
	 * 查看会员是否正在退款
	 * 
	 * @param memberid
	 * @return
	 */
	public Boolean isRefunding(Long memberid);
	
	/**
	 * 获取会员发票信息
	 * @param memberid
	 * @return
	 */
	public Invoice getInvoiceInfo(Long memberid);
	
	
	
	
}
