package com.ccclubs.service.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.Restriction;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.web.util.Page;

public interface ICommonOrderService {
	
	enum DateType {
		HoliDay, WorkDay, CeaseDay
	}

	/**
	 * 根据临时订单到数据库取实际订单，并合并两对象
	 * 
	 * @param csOrder
	 * @return
	 */
	public CsOrder merge(CsOrder csOrder);

	/**
	 * 获取订单价格
	 * @param[非空] memberid会员ID
	 * @param[非空] carid车辆ID
	 * @param[非空] start开始时间
	 * @param[非空] finish结束时间
	 * @param[可空] end还车时间
	 * @param[可空] freehour最多使用免费小时数
	 * @param[可空] electric电里程
	 * @param[可空] fuel油里程
	 * @param[可空] insureType保险类型
	 * @param[可空] orderid订单ID
	 * @return
	 */
	public Double getOrderPrice(Long memberid, Long carid, Date start, Date finish, Date end, Double freehour, Double electric, Double fuel,Long feeType, Long insureType,Double longPrice, Long orderid);

	/**
	 * 拆分订单
	 * 
	 * @param[非空] memberid会员ID
	 * @param[非空] carid车辆ID
	 * @param outlets_get 取车网点
	 * @param outlets_ret 还车网点
	 * @param[非空] start开始时间
	 * @param[非空] finish结束时间
	 * @param[可空] end还车时间
	 * @param[可空] freehour最多使用免费小时数
	 * @param[可空] electric电里程
	 * @param[可空] fuel油里程
	 * @param[可空] insureType保险类型
	 * @param[可空] orderid订单ID
	 * @return
	 */
	public OrderInfo splitOrderDetails(Long memberid, Long carid, Date start, Date finish, Date end, Double freehour, Double electric, Double fuel,Long feeType, Long insureType, Double longPrice, Long orderid);
	public OrderInfo splitOrderDetails(Long memberid, Long carid, Date start, Date finish, Date end, Double freehour, Double electric, Double fuel,Long feeType, Long insureType, Double longPrice, Long orderid, boolean isSettle);
	public OrderInfo splitOrderDetails(Long memberid, Long carid, Long outlets_get, Long outlets_ret, Date start, Date finish, Date end, Double freehour, Double electric, Double fuel,Long feeType, Long insureType, Double longPrice, Long orderid);
	
	/**
	 * 模拟拆单
	 * @param csmType
	 * @param cspModel
	 * @param cspOutets
	 * @param start
	 * @param finish
	 * @param fuel
	 * @param insureType
	 * @return
	 */
	public OrderInfo splitOrderDetails(Long csmType, Long cspModel, Long cspOutets,Date start, Date finish, Double electric, double fuel, Long insureType);

	
	
	/**
	 * 根据小时数获取预计里程数
	 * @param allhours
	 * @return
	 */
	public Integer getPredictKms(int allhours);
	
	
	/**
	 * 查看订单时间的有效性
	 * 
	 * @param carid
	 *            车辆ID
	 * @param start
	 *            开始时间
	 * @param finish结束时间
	 * @param norder排除的订单
	 * @return
	 */
	public Boolean isExistOrderByTime(Long carid, Date start, Date finish, Long norder);


	/**
	 * 测试某车辆某时间内是否存在限行
	 * 
	 * @param carid
	 *            车辆ID
	 * @param start
	 *            开始时间
	 * @param finish结束时间
	 * @return
	 */
	public Boolean isExistRestrictionByTime(Long carid, Date start, Date finish);

	/**
	 * 获取车辆的限行时间列表
	 * 
	 * @param carid
	 * @param start
	 *            开始时间
	 * @param finish结束时间
	 * @return
	 */
	public List<Restriction> getRestrictions(Long carid, Date start, Date end);

	/**
	 * 获取车辆的限行时间列表
	 * 
	 * @param carid
	 * @param dayCount,从当天开始往后多少天
	 * @return
	 */
	public List<Restriction> getRestrictions(Long carid, int dayCount);

	/**
	 * 根据时间获取日期类型
	 * 
	 * @param date
	 * @return
	 */
	public DateType getDateType(Date date);
	
	
	/**
	 * 根据标识获取唯一商品
	 * @param flag
	 * @return
	 */
	public CsGoods getGoodsByFlag(String productFlag, RuleName ruleName,Long userType);
	

	/**
	 * 根据标识取产品
	 * @param flag
	 * @return
	 */
	public CsProduct getProductByFlag(String flag);

	

}
