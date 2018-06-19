package com.ccclubs.service.common;

import java.util.Date;

import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.common.impl.UnitOrderInfo;
import com.lazy3q.web.helper.$;

/**
 * 企业公用接口
 * @author uiu
 */
public interface ICommonUnitService {
	
	public final static Double mileagePrice = Double.valueOf($.config("mileagePrice") == null ? "2" : $.config("mileagePrice")); //约租车里程单价
	
	/**
	 * 专车下单
	 * @param personId 人员ID
	 * @param profile 订单摘要
	 * @param carId 车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 类型：1:一般公务 2:紧急公务
	 * @param startPoint
	 * @param finishPoint
	 * @param startAddr
	 * @param finishAddr
	 * @param clientMessage 客户留言
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeAddSpecialOrder(
			Long personId,String profile,
			Date start,Date finish,Short type,
			String startPoint,String finishPoint,
			String startAddr,String finishAddr,
			String clientMessage,
			Long carLevel
		)throws MessageException;
	
	/**
	 * 公务下单
	 * @param personId 人员ID
	 * @param profile 订单摘要
	 * @param carId 车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 类型：1:一般公务 2:紧急公务
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeAddOfficialOrder(
			Long personId,String profile,Long carId,Date start,Date finish,Short type
		)throws MessageException;
	
	
	
	/**
	 * 添加企业订单
	 * @param unid 企业ID
	 * @param groupId 企业组ID
	 * @param memberId 用车帐号ID
	 * @param name 用车人姓名
	 * @param mobile 用车人手机
	 * @param profile 用车用途
	 * @param idcard 身份证号
	 * @param carId 车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 订单类型
	 * @param dayPrice 天租价格
	 * @param hourPrice 小时价格
	 * @param kmPrice 里程价格
	 * @param takeOutlets 取车网点
	 * @param retOutlets 还车网点
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeAddUnitOrder(
			Long unid,Long personId,Long memberId,String name,String mobile,String profile,String idcard,Long carId,Date start,Date finish,Short type,Double dayPrice,Double hourPrice,Double kmPrice, Long takeOutlets, Long retOutlets
		)throws MessageException;
	
	
	/**
	 * 续订企业订单
	 * @param unitId
	 * @param unitOrderId
	 * @param newFinishTime
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeReUnitOrder(Long unitId,Long unitOrderId,Date newFinishTime)throws MessageException;

	/**
	 * 取消企业订单
	 * @param csuoUnitInfo
	 * @param csuoId
	 */
	public void executeCancelUnitOrder(Long unitInfoId, Long unitOrderId,String remark);
	
	
	/**
	 * 订单审核
	 * @param indexOf
	 * @param csuoId
	 * @param csuoUnitInfo
	 * @param csuoRemark
	 */
	public String executeCheckUnitOrder(Boolean bOk, Long csuoId, Long csuoUnitInfo,String csuoRemark);

	/**
	 * 完结企业定单
	 * @param csuoId
	 */
	public void executeComplete(Long id);
	
	//===============================================================================================================
	
	/**
	 * 专车下单
	 * @param personId 人员ID
	 * @param profile 订单摘要
	 * @param carId 车辆ID
	 * @param start 开始时间
	 * @param finish 结束时间
	 * @param type 类型：1:一般公务 2:紧急公务
	 * @param startPoint
	 * @param finishPoint
	 * @param startAddr
	 * @param finishAddr
	 * @param clientMessage 客户留言
	 * @param mobile
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeAddSpecialOrder(
			Long personId,String profile,
			Date start,Date finish,Short type,
			String startPoint,String finishPoint,
			String startAddr,String finishAddr,
			String clientMessage,
			Long carLevel,
			String mobile,
			Long third
		)throws MessageException;
	
	/**
	 * 叫车（约租车）订单【结算】
	 * @param csuoId   		企业订单ID
	 * @param memberId      使用会员
	 * @param payMemberId   支付会员
	 * @param finishTime	实际订单结束时间
	 * @param mileage		里程（单位：公里）
	 * @param remark		结算备注
	 * @param operator		操作人
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeSettleRentOrder(Long csuoId, Long useMemberId, Long payMemberId, Date finishTime, Double mileage, String remark)throws MessageException;
	
	/**
	 * 叫车（约租车）订单【取消】
	 * @param csuoId   		企业订单ID
	 * @param finishTime	实际订单结束时间
	 * @param mileage		公里
	 * @param remark		备注
	 * @return
	 * @throws MessageException
	 */
	public CsUnitOrder executeCancelRentOrder(Long csuoId, Long useMemberId, String remark)throws MessageException;
	
	/**
	 * 执行计算订单信息
	 * @return
	 * @throws MessageException
	 */
	public UnitOrderInfo execulateCalcRentOrder(Long csuoId, Date finishTime, Double mileage, boolean cancel) throws MessageException;
	

}
