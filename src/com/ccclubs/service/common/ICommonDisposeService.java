package com.ccclubs.service.common;

import java.util.Date;

import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;

public interface ICommonDisposeService {
	
	//是否需要发送短信
	public static final String DONOTSENDSMS = "DONOTSENDSMS";
		
	/**
	 * 生成订单
	 * @param payMemberId 支付会员ID
	 * @param useMemberId 使用会员ID
	 * @param[可空] creditCard 信用卡ID
	 * @param carId 预订车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param feeType 计费类型
	 * @param insureType 保险类型
	 * @param longPrice 长租价格
	 * @param freeHours 免费小时
	 * @param defineMargin 保证金
	 * @param editor 添加人
	 * @param from 订单来源
	 * @param src 来源信息：类名@id
	 * @return
	 */
	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId,
			Long carId, Date start, Date finish,Long feeType, Long insureType,Double longPrice,
			Double freeHours,Double defineMargin,Long editor,From from,String src)throws MessageException;
	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId,String mobile,
			Long carId, Date start, Date finish,Long feeType, Long insureType,Double longPrice,
			Double freeHours,Double defineMargin,Long editor,From from,String src)throws MessageException;
	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId,
			Long carId,Long outlets_get_id,Long outlets_ret_id, Date start, Date finish,Long feeType, Long insureType,Double longPrice,
			Double freeHours,Double defineMargin,Long editor,From from,String src)throws MessageException;
	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId,Long creditCard,
			Long carId, Date start, Date finish,Long feeType, Long insureType,Double longPrice,
			Double freeHours,Double defineMargin,Long editor,From from,String src)throws MessageException;
	public CsOrder executeSaveOrder(Long payMemberId, Long useMemberId,Long creditCard,
			Long carId,Long outlets_get_id,Long outlets_ret_id, Date start, Date finish,Long feeType, Long insureType,Double longPrice,
			Double freeHours,Double defineMargin,Long editor,From from,String src)throws MessageException;
	
	public CsOrderCluster saveMealOrder(Long payMemberId, Long useMemberId, Long carId, Long outlets_get_id,Long outlets_ret_id, Date start, Long mealId, Long feeType, Long insureType, Double longPrice, Double freeHours, Long editor, From from, String src) throws MessageException;
		
	/**
	 * 续订订单
	 * @param[非空] orderId
	 * @param[非空] finish
	 * @param[可空] freeHours
	 */
	public CsOrder executeReOrder(Long orderId,Date finish,Double freeHours,From from,String src)throws MessageException;
	
	
	/**
	 * 取消订单
	 * @param[非空] orderId
	 */
	public CsOrder executeCancelOrder(Long orderId,String remark,From from,String src) throws MessageException;
	
	/**
	 * 取消订单簇
	 * @param orderId
	 * @param remark
	 * @param from
	 * @param src
	 * @throws MessageException
	 */
	public void executeCancelOrderCluster(Long orderId, String remark, From from, String src) throws MessageException;
	
	
	/**
	 * 结算订单
	 * @param[非空] orderId
	 * @param[可空] retTime
	 * @param[可空] csoFuel
	 * @param[可空] csoElectric
	 */
	public CsOrder executeSettleOrder(Long orderId,Date retTime,Double csoFuel,Double csoElectric,From from,String src)throws MessageException;


	/**
	 * 撤销订单
	 * @param csoId
	 * @param csoRemark
	 */
	public void executeUndoOrder(Long csoId, String csoRemark,From from,String src);
	
	/**
	 * 回滚订单
	 * @param csoId
	 * @param csoRemark
	 */
	public void executeRollbackOrder(Long csoId, String csoRemark,From from,String src);
	
}
