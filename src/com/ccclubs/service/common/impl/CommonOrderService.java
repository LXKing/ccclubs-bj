package com.ccclubs.service.common.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.util.CollectionUtils;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsLimitTime;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsRule;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.param.TimeBlock;
import com.ccclubs.param.TimeSlot;
import com.ccclubs.param.TimeUtil;
import com.ccclubs.param.TimeUtil.RoundMode;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.promotion.Activity2017LadiesDay;
import com.ccclubs.service.common.impl.promotion.ActivityPromotion;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class CommonOrderService extends OrderProvider implements ICommonOrderService {
	
	/**
	 * 根据临时订单到数据库取实际订单，并合并两对象
	 * @param csOrder
	 * @return
	 */
	public CsOrder merge(CsOrder order) {
		if(order.getCsoId()==null)return order;
		CsOrder csOrder = csOrderDao.getCsOrderById(order.getCsoId());
		if(csOrder!=null){//与持久对象合并
			Field[] fields = CsOrder.class.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				try{
					if(field.get(order)!=null)
						field.set(csOrder, field.get(order));	
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}else
			csOrder = order;
		return csOrder;
	}
	
	/**
	 * 查看订单时间的有效性
	 * @param carid 车辆ID
	 * @param start 开始时间
	 * @param finish结束时间
	 * @param norder排除的订单
	 * @return
	 */
	public Boolean isExistOrderByTime(Long carid,Date start, Date finish, Long norder) {
		//检查订单的时间的有效性
		List<CsOrder> orders = csOrderDao.getCsOrderList($.add("csoCar", carid).add("definex", "(cso_status=0||cso_status=1)"), -1);
		for(CsOrder order:orders){
			if(norder!=null && norder.longValue()==order.getCsoId().longValue())continue;			
//			if(!(start.before(order.getCsoStartTime()) || start.after(min(order.getCsoRetTime(),order.getCsoFinishTime()))))
//				return true;
//			if(!(finish.before(order.getCsoStartTime()) || finish.after(min(order.getCsoRetTime(),order.getCsoFinishTime()))))
//				return true;
//			if(start.before(order.getCsoStartTime()) && finish.after(min(order.getCsoRetTime(),order.getCsoFinishTime())))
//				return true;
			
			if(! (start.after(order.getCsoFinishTime()) || finish.before(order.getCsoStartTime())) ){
				return true;
			} 
		}		
		return false;
	}

	public Boolean isExistOrderByTime(Long carid, Date start, Date finish) {
		GregorianCalendar gcStart = new GregorianCalendar();
		gcStart.setTime(start);
		gcStart.add(6, -1);
		String startBefore = SystemHelper.formatDate(gcStart.getTime());
		gcStart.setTime(start);
		gcStart.add(6, 1);
		String startAfter = SystemHelper.formatDate(gcStart.getTime());

		GregorianCalendar gcFinish = new GregorianCalendar();
		gcFinish.setTime(finish);
		gcFinish.add(6, -1);
		String finishBefore = SystemHelper.formatDate(gcFinish.getTime());
		gcFinish.setTime(finish);
		gcFinish.add(6, 1);
		String finishAfter = SystemHelper.formatDate(gcFinish.getTime());

		// 检查订单的时间的有效性
		List<CsOrder> orders = csOrderDao.getCsOrderList($.add("csoCar", carid).add(
				"definex",
				"(cso_status=0||cso_status=1) and  (cso_start_time < '" + finishBefore + "' || cso_start_time < '" + finishAfter + "' )  and  (cso_finish_time > '" + startBefore
						+ "' || cso_finish_time > '" + startAfter + "')"), -1);
		return orders.size() > 0;
	}
	
	/**
	 * 获取订单保证金
	 */
	public Double getOrderMargin(Long mtype,Long model,Long outlets,Boolean bInsure) {				
		CsGoods goods =  getGoodsByFlag(bInsure?SYSTEM.MARGIN:SYSTEM.MARGING,RuleName.每次,mtype);
		if(goods==null){
			//throw new ErrorException("GOODS_ERROR","未配置保证金商品","["+CsUserType.getKeyValue(mtype)+"]["+CsCarModel.getKeyValue(model)+"]["+CsOutlets.getKeyValue(outlets)+"]未配置保证金商品");
			return 0d;
		}
		CsPrice csPrice = csPriceDao.getCsPrice(Lazy.add("cspGoods", goods.getCsgId()).add("cspOutets",outlets).add("cspModel",model).add("cspUserType",mtype));
		if(csPrice==null){//如果未配置价格，则取默认网点价格
			CsFeeTypeSet feeTypeSet = CsFeeTypeSet.Get(
				$.add(CsFeeTypeSet.F.csftsModel, model)
				.add(CsFeeTypeSet.F.csftsHost, CsOutlets.get(outlets).getCsoHost())
			); 
			if(feeTypeSet!=null && feeTypeSet.getCsftsOutlets()!=null)
				csPrice = csPriceDao.getCsPrice(Lazy.add("cspGoods", goods.getCsgId()).add("cspOutets",feeTypeSet.getCsftsOutlets()).add("cspModel",model).add("cspUserType",mtype));
		}
		if(csPrice==null)
			throw new ErrorException("PRICE_ERROR","未配置价格","["+CsUserType.getKeyValue(mtype)+"]["+CsCarModel.getKeyValue(model)+"]["+CsOutlets.getKeyValue(outlets)+"]["+goods.getKeyValue()+"]未配置价格");
		return csPrice.getCspPrice();
	}	
	
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
	public Double getOrderPrice(Long memberid,Long carid,Date start,Date finish,Date end,Double freehour,Double electric,Double fuel,Long feeType,Long insureType,Double longPrice,Long orderid) {
		OrderInfo orderinfo = this.splitOrderDetails(memberid, carid, start, finish, end, freehour, electric, fuel, feeType,insureType,longPrice, orderid);
		return orderinfo.getPrice();
	}
	
	//拆单
	public OrderInfo splitOrderDetails(Long memberid, Long carid, Date start,
			Date finish, Date end, Double freehour, Double electric,
			Double fuel, Long feeType, Long insureType, Double longPrice,
			Long orderid) {		
		return this.splitOrderDetails(memberid, carid, null, null, null,start, finish, end, freehour, electric, fuel, feeType, insureType, longPrice, orderid, false);
	}
	
	@Override
	public OrderInfo splitOrderDetails(Long memberid, Long carid, Date start,
			Date finish, Date end, Double freehour, Double electric,
			Double fuel, Long feeType, Long insureType, Double longPrice,
			Long orderid, boolean isSettle) {
		return this.splitOrderDetails(memberid, carid, null, null, null,start, finish, end, freehour, electric, fuel, feeType, insureType, longPrice, orderid, isSettle);
	}

	public OrderInfo splitOrderDetails(Long memberid, Long carid, Long outlets_get,Long outlets_set, Date start,
			Date finish, Date end, Double freehour, Double electric,
			Double fuel, Long feeType, Long insureType, Double longPrice,
			Long orderid) {		
		return this.splitOrderDetails(memberid, carid, null,outlets_get, outlets_set, start, finish, end, freehour, electric, fuel, feeType, insureType, longPrice, orderid, false);
	}
	//模拟拆单
	public OrderInfo splitOrderDetails(Long csmType, Long cspModel, Long cspOutets,
			Date start, Date finish, Double electric,double fuel, Long insureType) {		
		return this.splitOrderDetails(null, null, cspModel, cspOutets,null, start, finish, null, null, electric, fuel, csmType, insureType, null, null, false);
	}	
	/**
	 * 拆分订单
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
	public OrderInfo splitOrderDetails(Long memberid,Long carid,Long cspModel,Long cspOutets,Long cspOutetsRet,Date start,Date finish,Date end,Double freehour,Double electric,Double fuel,Long feeType,Long insureType,Double longPrice,Long orderid, boolean isSettle) {	
		OrderInfo orderinfo1 = this.splitOrderDetailsEx(memberid, carid, cspModel, cspOutets, cspOutetsRet, start, finish, end, freehour, electric, fuel, feeType, insureType, longPrice, orderid, isSettle);
		return orderinfo1;
	}
	
	
	
	/**
	 * 
	 * @param memberid
	 * @param carid
	 * @param cspModel
	 * @param cspOutets
	 * @param cspOutetsRet
	 * @param start		
	 * @param finish
	 * @param retTime
	 * 
	 * @param freehour
	 * @param electric
	 * @param fuel
	 * 
	 * @param feeType
	 * @param insureType
	 * @param longPrice
	 * @param orderid
	 * @return
	 */
	OrderInfo splitOrderDetailsEx(Long memberid,Long carid,Long cspModel,Long cspOutets,Long cspOutetsRet,Date start,Date finish,Date retTime,Double freehour,Double electric,Double fuel,Long ordFeeType,Long insureType,Double longPrice,Long orderid, boolean isSettle) {
		
		//如果与其它订单时间冲突
//		if(isExistOrderByTime(carid, start, finish, orderid))
//			orderInfo.setConflict(true);	
		
		Date splitStart = new Date();
		System.out.println("########################订单拆分开始#################################");
		
		CsMember csMember = csMemberDao.getCsMemberById(memberid);
		
		//////////////////车辆对象//////////////////
		CsCar csCar = csCarDao.getCsCarById(carid);
		if(csCar!=null){
			cspModel = csCar.getCscModel();
			cspOutets = csCar.getCscOutlets();
		}
		//////////////////原订单//////////////////
		CsOrder oldOrder = null;
		if(orderid!=null){
			oldOrder = csOrderDao.getCsOrderById(orderid);
			if(cspModel == null)cspModel = oldOrder.getCsoModel();
			if(cspOutets == null)cspOutets = oldOrder.getCsoOutlets();
			if(cspOutetsRet == null)cspOutetsRet = oldOrder.getCsoOutletsRet();
			//订单实际开始时间(取车时间与预定开始时间比较，取较小时间)
			if(oldOrder.getCsoTakeTime()!=null) {
				if(start.after(oldOrder.getCsoTakeTime())) {
					start=oldOrder.getCsoTakeTime();
				}
			}
		}
		
		CsCarModel csCarModel = CsCarModel.get(cspModel);
		CsOutlets csOutlets = CsOutlets.get(cspOutets);
		SrvHost srvHost = SrvHost.get(csOutlets.getCsoHost());
		CsOutlets csOutletsRet = csOutlets;
		if(cspOutetsRet!=null)
			csOutletsRet = CsOutlets.get(cspOutetsRet);
		
		
		//根据地区+车型获取策略配置列表
		CsFeeTypeSet csFeeTypeSet 		= CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsHost, srvHost.getShId()).add(CsFeeTypeSet.F.csftsModel, csCarModel.getCscmId()));
		Long userType					= getUserType(ordFeeType, csFeeTypeSet, srvHost, oldOrder, csCarModel);
		CsProduct rentProduct 			= this.getProductByFlag(SYSTEM.RENT);
		
		if(insureType!=null && insureType.intValue()==0)
			insureType=null;
		
		OrderInfo orderInfo  = new OrderInfo();
		List<CsOrderDetail> details = new ArrayList<CsOrderDetail>();
		
		if(electric==null)electric=0d;
		if(fuel==null)fuel=0d;
		if(freehour==null)freehour=0d;
		
		Date rentFinish     = finish;       //算租金的截止时间
		Date timeoutStart 	= null;	 		//超时部分开始时间
		Date realFinish 	= finish;   	//还车时间
		if(retTime!=null ){
			realFinish = retTime;
			rentFinish = retTime;
//			
//			//如果超时还车
//			if(retTime.after(finish)){
//				rentFinish = finish;		//租金计算到预定结束时间
//				
//				//如果还车时间在结束时间之后10分钟，那么结束时间为还车时间 
//				if((retTime.getTime()- finish.getTime()) > (SYSTEM.MINUTE * 10)){
//					realFinish = new Date(retTime.getTime() - SYSTEM.MINUTE * 10);
//					timeoutStart = finish;
//				}
//			}
		}
		
		//订单时长
		orderInfo.setTime(realFinish.getTime() - start.getTime());
		//设置计费类型
		orderInfo.feetype = userType;		
		
		//List<OrderPrice> ordPriceList = getOrderPrices(srvHost.getShId(), cspOutets, cspModel, userType, csFeeTypeSet);
		//PriceModel pm = new PriceModel(ordPriceList);
		
		CsPrice rentPrice = csPriceDao.getCsPrice(Lazy.add("cspGoods", getGoodsByFlag(SYSTEM.RENT,RuleName.每分钟, userType).getCsgId()).add("cspOutets",cspOutets).add("cspModel",cspModel).add("cspUserType",userType));
		if(rentPrice==null){//如果未配置价格，则取默认网点价格 
			if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsOutlets()!=null)
				rentPrice = csPriceDao.getCsPrice(Lazy.add("cspGoods", getGoodsByFlag(SYSTEM.RENT,RuleName.每分钟, userType).getCsgId()).add("cspOutets",csFeeTypeSet.getCsftsOutlets()).add("cspModel",cspModel).add("cspUserType",userType));
		}
		
		Map<Long, Map<String, Map>> prices = getProductRentPrices(csFeeTypeSet, cspOutets, cspModel, userType);
		
		/***************************************拆单开始****************************************/
		boolean isOC = CommonDisposeService.isOrderCluster(oldOrder);
		
		
		List<CsOrderDetail> rentDetails = new ArrayList<CsOrderDetail>();
//		if(!isOC){  //如果不是订单簇，走租金拆单
//			
//			rentDetails = this.getRentOrderDetail(rentProduct, csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), start, rentFinish, start, rentFinish, rentPrice, prices);
//			//租金
//			details.addAll(rentDetails);
//			
//			if(CollectionUtils.isEmpty(rentDetails)){
//				throw new ErrorException("PRICE_ERROR","价格未配置","[租金]["+(csOutlets.getCsoName())+"]["+(csCarModel.getCscmName())+"]["+CsUserType.getKeyValue(userType)+"]未配置价格"); 
//			}
//			
////			promotion = getActivity2017(rentDetails, start, rentFinish, oldOrder);
//		}
		//初始化订单簇
		CsOrderCluster coc = null;
		if (!isOC) { // 如果不是订单簇，走租金拆单
            rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), start, rentFinish);
            // 租金
            details.addAll(rentDetails);

            if (CollectionUtils.isEmpty(rentDetails)) {
                throw new ErrorException("PRICE_ERROR", "价格未配置",
                        "[租金][" + (csOutlets.getCsoName()) + "][" + (csCarModel.getCscmName())
                                + "][" + CsUserType.getKeyValue(userType) + "]未配置价格");
            }
        }else if(oldOrder!=null && StringUtils.isNotEmpty(oldOrder.getCsoSrc())) {
            String orderClusterId = oldOrder.getCsoSrc().replaceAll("\\D", "");
            coc = CsOrderCluster.Get($.add("csocId", orderClusterId));
            //套餐订单时长拆解：套餐时段单独计算，提前取车、超时还车时段分别计算
            if(coc!=null) {
                //提前取车
                if(oldOrder.getCsoTakeTime().getTime()<oldOrder.getCsoStartTime().getTime()) {
                    if(oldOrder.getCsoRetTime().getTime()<=oldOrder.getCsoStartTime().getTime()) {
                        /***************套餐开始之前还车*************/
                        //普通用车费用
                        rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), oldOrder.getCsoTakeTime(), oldOrder.getCsoRetTime());
                        details.addAll(rentDetails);
                    }else if(oldOrder.getCsoRetTime().getTime()>oldOrder.getCsoFinishTime().getTime()) {
                        /***************超时还车*************/
                        //提前取车普通费用
                        rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), oldOrder.getCsoTakeTime(), oldOrder.getCsoStartTime());
                        details.addAll(rentDetails);
                        //超时还车普通费用
                        rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), oldOrder.getCsoFinishTime(), oldOrder.getCsoRetTime());
                        details.addAll(rentDetails); 
                    }else{
                        /***************套餐时间内还车*************/ 
                        //提前取车普通费用
                        rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), oldOrder.getCsoTakeTime(), oldOrder.getCsoStartTime());
                        details.addAll(rentDetails);
                    }
                }
                
                //套餐时间内取车
                if(oldOrder.getCsoTakeTime().getTime()>=oldOrder.getCsoStartTime().getTime()
                        && oldOrder.getCsoTakeTime().getTime()<=oldOrder.getCsoFinishTime().getTime()) {
                    
                    /***************超时还车*************/
                    if(oldOrder.getCsoRetTime().getTime()>oldOrder.getCsoFinishTime().getTime()) {
                        //超时还车普通费用
                        rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), oldOrder.getCsoFinishTime(), oldOrder.getCsoRetTime());
                        details.addAll(rentDetails); 
                    }
                }
                
                //套餐时间后取车,用车普通费用
                if(oldOrder.getCsoTakeTime().getTime()>=oldOrder.getCsoFinishTime().getTime()) {
                    rentDetails = this.buildOrderDetails(rentProduct.getCspId(), csFeeTypeSet, userType, cspOutets, cspModel, srvHost.getShId(), oldOrder.getCsoTakeTime(), oldOrder.getCsoRetTime());
                    details.addAll(rentDetails); 
                }
            }
        }
		
		//免责
		if(insureType!=null){
			//获取免责产品
			CsProduct insureProduct = csProductDao.getCsProductById(insureType);
			if(insureProduct!=null){
				//获取免责产品对应的商品
				CsGoods insureGoods = (insureProduct==null) ? null : csGoodsDao.getCsGoods($.add("csgProduct", insureProduct.getCspId()).add("csgUserType", userType));
				details.addAll(this.getKilometerOrderDetail(insureGoods, start, realFinish, electric + fuel));
			}
		}
		
		//电里程费
//		details.addAll(this.getKilometerOrderDetail(getGoodsByFlag(SYSTEM.KILOM,RuleName.每公里,userType), start, realFinish, electric));
		
		//油里程费
//		details.addAll(this.getKilometerOrderDetail(getGoodsByFlag(SYSTEM.MILEAGE,RuleName.每公里,userType), start, realFinish, fuel));
	
//		if(timeoutStart!=null){
//			CsProduct timeoutProduct = this.getProductByFlag(SYSTEM.TIMEOUT);
//			CsGoods timeoutGoods = getGoodsByFlag(SYSTEM.TIMEOUT,RuleName.每分钟, userType);
//			if(timeoutProduct!=null && timeoutGoods!=null){
//				CsPrice timeoutPrice = csPriceDao.getCsPrice(Lazy.add("cspGoods", timeoutGoods.getCsgId()).add("cspOutets",cspOutets).add("cspModel",cspModel).add("cspUserType",userType));
//				if(timeoutPrice==null){//如果未配置价格，则取默认网点价格 
//					if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsOutlets()!=null)
//						timeoutPrice = csPriceDao.getCsPrice(Lazy.add("cspGoods", timeoutGoods.getCsgId()).add("cspOutets",csFeeTypeSet.getCsftsOutlets()).add("cspModel",cspModel).add("cspUserType",userType));
//				}
//				details.addAll(this.getTimeoutOrderDetail(timeoutProduct, timeoutStart, realFinish, userType, srvHost, timeoutPrice, prices));
//			}
//		}
		
		Collections.sort(details, new Comparator<CsOrderDetail>(){
			@Override
			public int compare(CsOrderDetail a, CsOrderDetail b) {
				return a.getCsodStart().before(b.getCsodStart())? -1 : 1;//a.getCsodStart().compareTo(b.getCsodStart());
			}
		});
		
		Double gRebate = null;
		//套餐外时长费
        double payNormal = 0;
		//设置订单的价格		
		for(CsOrderDetail orderDetail : details){
			orderDetail.setCsodHost(csOutlets.getCsoHost());
			orderDetail.setCsodCar(carid);
			orderDetail.setCsodOrder(orderid);
			CsPrice csPricer = csPriceDao.getCsPrice(Lazy.add("cspGoods", orderDetail.getCsodGoods()).add("cspOutets",cspOutets).add("cspModel",cspModel).add("cspUserType",userType));
			if(csPricer==null){//如果未配置价格，则取默认网点价格
				if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsOutlets()!=null)
					csPricer = csPriceDao.getCsPrice(Lazy.add("cspGoods", orderDetail.getCsodGoods()).add("cspOutets",csFeeTypeSet.getCsftsOutlets()).add("cspModel",cspModel).add("cspUserType",userType));
			}
			if(csPricer==null)
				throw new ErrorException("PRICE_ERROR","价格未配置","["+(orderDetail.get$csodGoods()).getCsgName()+"]["+(csOutlets.getCsoName())+"]["+(csCarModel.getCscmName())+"]["+CsUserType.getKeyValue(userType)+"]未配置价格");
			
			orderDetail.setCsodProduct((orderDetail.get$csodGoods()).getCsgProduct());
			if(csCar!=null){
				orderDetail.setCsodCar(csCar.getCscId());
				orderDetail.setCsodProvid(csCar.getCscProvid());
			}
			orderDetail.setCsodOutlets(cspOutets);
			orderDetail.setCsodModel(cspModel);
			orderDetail.setCsodPrice($(csPricer.getCspPrice()));
			orderDetail.setCsodPriceReal($(csPricer.getCspPrice()));			
			orderDetail.setCsodRemark((StringUtils.isEmpty(orderDetail.getCsodRemark())? "" : orderDetail.getCsodRemark())+"基础价格");
			orderDetail.setCsodPricer(csPricer.getCspId());
			orderDetail.setCsodRebate(1.0d);
			
			if(csMember!=null && orderDetail.get$csodProduct().getCspFlag().equals(SYSTEM.RENT)){	//如果当前详情属于租金项，那么可以享受来自车辆和会员的折扣系数
				Double rebate = 1d;
				/*******************车辆折扣*********************/
				Double carRebate = null;
				if(csCar!=null && csCar.getCscRebate()!=null && csCar.getCscRebate()<1 && csCar.getCscRebate()>0)
					carRebate = csCar.getCscRebate();
				//如果旧订单里已经保存了折扣，则使用之前的折扣
				if(oldOrder!=null && oldOrder.getCsoRebate()!=null && oldOrder.getCsoRebate()<1 && oldOrder.getCsoRebate()>0)
					carRebate = oldOrder.getCsoRebate();
				if(carRebate!=null && carRebate<1 && carRebate>0){
					rebate *= carRebate;
					orderDetail.setCsodRemark(orderDetail.getCsodRemark()+">车辆折扣"+carRebate);
				}
				
				/********************会员折扣*********************/
				if(csMember.getCsmRebate()!=null && csMember.getCsmRebate()<1 && csMember.getCsmRebate()>0){
					rebate *= csMember.getCsmRebate();
					orderDetail.setCsodRemark(orderDetail.getCsodRemark()+">会员折扣"+csMember.getCsmRebate());
				}
				
				/********************会员组织折扣*********************/
				CsMemberGroup memberGroup = csMember.get$csmGroup();
				if(memberGroup!=null && memberGroup.getCsmgRebate()!=null && memberGroup.getCsmgRebate()<1 && memberGroup.getCsmgRebate()>0 ){
					rebate *= memberGroup.getCsmgRebate();
					orderDetail.setCsodRemark(orderDetail.getCsodRemark()+">会员组折扣"+memberGroup.getCsmgRebate());
				}				
				
				//叠加会员折扣和车辆折扣
				orderDetail.setCsodRebate($(orderDetail.getCsodRebate() * rebate));			
				
				if(gRebate==null)gRebate=orderDetail.getCsodRebate();
			}
			orderDetail.setCsodCountReal(orderDetail.getCsodCount());
			orderDetail.setCsodTotal($(orderDetail.getCsodPriceReal()*orderDetail.getCsodCountReal()));//理论金额
			orderDetail.setCsodTotalReal($(orderDetail.getCsodPriceReal()*orderDetail.getCsodCountReal()*orderDetail.getCsodRebate()));//实际金额
		
			//累计套餐外普通时长计费
			if(orderDetail.get$csodProduct().getCspFlag().equals(SYSTEM.RENT)) {
			    payNormal = payNormal + orderDetail.getCsodTotalReal();
			}
		}
		
		//订单簇累计套餐外普通时长计费
        if (coc != null) {
            coc.setCsocPayNormal(payNormal);
            coc.update();
        }
		
		//移除价格为0或者0.01的详情
		for(int i=0;i<details.size();i++){
			CsOrderDetail orderDetail=details.get(i);
			Double p = orderDetail.getCsodPrice();
			if(p==null || p.doubleValue()<0.02 || orderDetail.getCsodCount()<1){
				details.remove(i);
				i--;
			}
		}
		
//		/**********************************************
//		 * 计算订单所需保证金,并且查找套餐中替代价格方案
//		 **********************************************/
//		Double margin = this.getOrderMargin(userType, cspModel, cspOutets, insureType!=null);
//		orderInfo.setMargin($(margin));
//		
//		Map<String,List<CsGift>> giftmap = new HashMap<String,List<CsGift>>();	//优惠方案内容	
//		if(csMember!=null){
//			/**********************************************
//			 * 计算订单免费小时
//			 **********************************************/
//			List<CsOrderDetail> hourDetails = new ArrayList<CsOrderDetail>();		
//			Double memberFreeHours = commonMoneyService.getCanUseFreeHour(memberid,start);//会员所有的免费小时数
//			//如果当前订单不为空，会员可用免费小时则需加上当前订单之前所占用的免费小时数
//			if(orderid!=null)
//				memberFreeHours+=commonMoneyService.getFreezeFreeHour(orderid);
//			//订单中存在的以小时计量的商品总小时数
//			Double oneHours = 0d;
//			//获取属于小时的租金详情
//			for(CsOrderDetail csOrderDetail:details){
//				if(!(csOrderDetail.get$csodProduct()).getCspFlag().equals(SYSTEM.RENT))
//					continue;
//				//总时长除以个数得到每个单位的分钟数，如果约等于60分钟，那么该商品为小时产品			
//				double minute = (csOrderDetail.getCsodEnd().getTime()-csOrderDetail.getCsodStart().getTime())/csOrderDetail.getCsodCount()/SYSTEM.MINUTE;
//				if(minute>58 && minute<63){
//					hourDetails.add(csOrderDetail);
//					oneHours+=csOrderDetail.getCsodCount();
//				}
//			}		
//			final Double finalHour = Math.min(memberFreeHours,Math.min(freehour,oneHours));//最终用到的免费小时数
//			Double detailFreeHour = finalHour;//本次订单扣除的免费小时数	
//			orderInfo.setOnehours(oneHours);
//			if(finalHour>0){
//				/*************依次减掉获取属于小时的租金详情的实际数量**********************/
//				for(CsOrderDetail csOrderDetail:hourDetails){
//					if(detailFreeHour<=0)break;
//					Double count = Math.min(detailFreeHour, csOrderDetail.getCsodCountReal());
//					csOrderDetail.setCsodCountReal($(csOrderDetail.getCsodCountReal()-count));	
//					csOrderDetail.setCsodRemark(csOrderDetail.getCsodRemark()+">使用"+$(count)+"个免费小时");
//					detailFreeHour-=count;
//					csOrderDetail.setCsodTotal($(csOrderDetail.getCsodPriceReal()*csOrderDetail.getCsodCountReal()));//理论金额
//					csOrderDetail.setCsodTotalReal($(csOrderDetail.getCsodPriceReal()*csOrderDetail.getCsodCountReal()*csOrderDetail.getCsodRebate()));//实际金额
//				}
//				orderInfo.setFreehour($(finalHour));
//			}
			
//			/**********************************************
//			 * 查询订单可使用的优惠内容，并做相应的处理
//			 **********************************************/
//			List<CsGift> csGifts = csGiftDao.getCsGiftList(
//					$.add(CsGift.F.csgUntilTimeStart, new Date())
//					.add("asc", CsGift.C.csgId)
//					//.add("csgStatus", 1)=>>大BUG
//					.add("csgMember", memberid), -1);			
//			for(CsGift csGift:csGifts){
//				//判断优惠项是否可用，并且关联的用户套餐是否可用
//				//2013-12-16改套餐优惠项判断关联套餐时只判断不是无效就好了=>csGift.get$csgUserPack().getCsupStatus().shortValue()!=0
//				//2014-2-7 去掉&& csGift.getCsgStatus().shortValue()==1 大BUG
//				if(csGift!=null  && csGift.get$csgUserPack()!=null && csGift.get$csgUserPack().getCsupStatus().shortValue()!=0){
//					String strModels = $.or(csGift.getCsgModel(), "0");
//					String[] models = strModels.split(",");
//					for(String strModel:models){
//						String model = $.trim(strModel).replace("#","");
//						if($.equals(model, "0") && models.length>1)continue;//如果models有多个，则忽略0项
//						String giftKey = csGift.getCsgGoods()+","+(csGift.getCsgOutlets()==null ? 0 : csGift.getCsgOutlets())+","+model;
//						//如果当前订单不为空，会员优惠内容则需加上当前订单之前所占用的优惠内容数
//						if(orderid!=null)
//							csGift.setCsgRemain($(csGift.getCsgRemain())+commonMoneyService.getFreezeGift(orderid,csGift.getCsgId()));
//						if(csGift.getCsgType().intValue()==0 && csGift.getCsgRemain()==0)
//							continue;
//						if(giftmap.get(giftKey)==null)
//							giftmap.put(giftKey, new ArrayList<CsGift>());
//						giftmap.get(giftKey).add(csGift);
//					}
//				}
//			}
//			for(CsOrderDetail csOrderDetail:details){				
//				//获取当前会员订购的套餐项（优惠项）
//				List<CsGift> gifts=giftmap.get(csOrderDetail.getCsodGoods()+","+csOrderDetail.getCsodOutlets()+","+csOrderDetail.getCsodModel());
//				if(gifts==null)
//					gifts=giftmap.get(csOrderDetail.getCsodGoods()+","+csOrderDetail.getCsodOutlets()+","+0);
//				if(gifts==null)
//					gifts=giftmap.get(csOrderDetail.getCsodGoods()+","+0+","+csOrderDetail.getCsodModel());
//				if(gifts==null)
//					gifts=giftmap.get(csOrderDetail.getCsodGoods()+","+0+","+0);
//				if(gifts==null)
//					continue;//不能为空
//				for(CsGift csGift:gifts){
//					//如果定义了优惠项脚本，则先执行优惠项脚本修改优惠项的配置或者验证该优惠项能不能使用
//					if(!$.empty(csGift.getCsgScript())){
//						try{
//							IPackPriceScript script = (IPackPriceScript) Class.forName(csGift.getCsgScript()).newInstance();
//							if(!script.execute(csGift,csOrderDetail))
//								continue;
//						}catch(Exception ex){
//							ex.printStackTrace();
//							new ErrorException("PACK_PRICE_SCRIPT_ERROR","优惠项"+csGift.getCsgName()+"["+csGift.getCsgId()+"]的优惠干预脚本出错",ex.getMessage());	
//						}
//					}
//					
//					//如果之前的循环扫描过该优惠项，那么使用之前用过的优惠项
//					if(csGift.getCsgType().shortValue()==0 && csGift.getCsgRemain()!=null && csGift.getCsgRemain()>0){//如果优惠项余量不为空，则按扣除余量方式
//						Double count = Math.min(csGift.getCsgRemain(), csOrderDetail.getCsodCountReal());
//						if(count>0){
//							csGift.setCsgRemain(csGift.getCsgRemain()-count);//减掉临时优惠项内容
//							csOrderDetail.setCsodCountReal(csOrderDetail.getCsodCountReal()-count);	
//							csOrderDetail.setCsodRemark(csOrderDetail.getCsodRemark()+">套餐项免费数"+count+"");
//							orderInfo.getGifts().add(
//								new CsUseRecord()
//								.csurHost(csOutlets.getCsoHost())
//								.csurCount(count)
//								.csurGift(csGift.getCsgId())
//								.csurCount(count)
//								.csurAddTime(new Date())
//								.csurMember(memberid)
//								.csurOrder(orderid)
//							);	
//						}
//					}else if(csGift.getCsgType().shortValue()==1 && csGift.getCsgPrice()!=null && csGift.getCsgPrice()>=0){//如果优惠项不为空，并且有重新定义当前商品价格,并且未过期，
//						csOrderDetail.setCsodPriceReal(csGift.getCsgPrice());
//						csOrderDetail.setCsodRemark(csOrderDetail.getCsodRemark()+">套餐项价格");
//					}else if(csGift.getCsgType().shortValue()==2 && csGift.getCsgRebate()!=null && csGift.getCsgRebate()>0 && csGift.getCsgRebate()!=1){//如果优惠项不为空，并且有定义当前商品价格折扣,并且未过期，
//						csOrderDetail.setCsodRebate($(csOrderDetail.getCsodRebate()*csGift.getCsgRebate()));
//						csOrderDetail.setCsodRemark(csOrderDetail.getCsodRemark()+">套餐项折扣"+csGift.getCsgRebate());
//					}
//				}
//				//重新修改应付额
//				csOrderDetail.setCsodTotal($(csOrderDetail.getCsodPriceReal()*csOrderDetail.getCsodCountReal()));//理论金额
//				csOrderDetail.setCsodTotalReal($(csOrderDetail.getCsodPriceReal()*csOrderDetail.getCsodCountReal()*csOrderDetail.getCsodRebate()));//实际金额
//			}
//			
//			/**********************************************
//			 * 并且查找套餐中保证金替代价格方案
//			 **********************************************/
//			CsGoods marginGoods =  getGoodsByFlag(insureType!=null?SYSTEM.MARGIN:SYSTEM.MARGING,RuleName.每次,userType);		
//			if(marginGoods!=null){
//				List<CsGift> gifts = giftmap.get(marginGoods.getCsgId()+","+cspOutets+","+cspModel);
//				if(gifts==null)
//					gifts=giftmap.get(marginGoods.getCsgId()+","+cspOutets+","+0);
//				if(gifts==null)
//					gifts=giftmap.get(marginGoods.getCsgId()+","+0+","+cspModel);
//				if(gifts==null)
//					gifts=giftmap.get(marginGoods.getCsgId()+","+0+","+0);
//				if(gifts!=null)
//					orderInfo.setMargin(gifts.get(0).getCsgPrice());
//			}
//		}
		
		//长租的计费类型
		CsUserType monthFeeType=csUserTypeDao.getCsUserType($.add("csutFlag", SYSTEM.USER_TYPE_ENTERPRISE));
		if(monthFeeType!=null && monthFeeType.getCsutId().longValue()==userType.longValue()){
			orderInfo.longorder=true;	
			//如果是长单，并且重定义了长单的价格
			if(oldOrder!=null && oldOrder.getCsoLongPrice()!=null)
				longPrice = oldOrder.getCsoLongPrice();
			if(longPrice!=null){
				//查询订单详情中产品为租金的详情
				for(CsOrderDetail csOrderDetail:details){
					if(csOrderDetail.getCsodProduct().longValue()==rentProduct.getCspId().longValue()){
						csOrderDetail.setCsodPriceReal(longPrice.doubleValue());
						csOrderDetail.setCsodTotalReal($(csOrderDetail.getCsodPriceReal()*csOrderDetail.getCsodCountReal()*csOrderDetail.getCsodRebate()));//实际金额
						csOrderDetail.setCsodRemark(csOrderDetail.getCsodRemark()+">协议包月价格"+longPrice.doubleValue());
					}
				}
			}
		}
		
		Double totalPrice = 0d;
		Double totalFee = 0d;//油电里程费，作为优惠券不能支付油电里程费用的依据
		Double kilom = 0d;//电里程费
		Double mileage= 0d;//油里程费
		Double rent= 0d;//租金费用
		Double insure= 0d;//免责费用  
		Double timeout= 0d;//超时费用  
		Double driver= 0d;//陪驾费用
		for(CsOrderDetail detail:details){
			String flag = (detail.get$csodProduct()).getCspFlag();
			totalPrice+=detail.getCsodTotalReal();
			if(flag.equals(SYSTEM.KILOM)){
				totalFee+=detail.getCsodTotalReal();
				kilom+=detail.getCsodTotalReal();
			}else if(flag.equals(SYSTEM.MILEAGE)){
				totalFee+=detail.getCsodTotalReal();
				mileage+=detail.getCsodTotalReal();
			}else if(flag.equals(SYSTEM.RENT)){
				rent+=detail.getCsodTotalReal();
			}else if(flag.equals(SYSTEM.INSURE)||flag.equals(SYSTEM.SECURE)){
				insure+=detail.getCsodTotalReal();
			}else if(flag.equals(SYSTEM.TIMEOUT)){
				timeout+=detail.getCsodTotalReal();
			}else if(flag.equals(SYSTEM.DRIVE)){
				driver+=detail.getCsodTotalReal();
			}
		}
		
		if(totalFee==0){
			CsGoods kilomGoods = getGoodsByFlag(SYSTEM.KILOM,RuleName.每公里,userType);
			CsGoods mileageGoods = getGoodsByFlag(SYSTEM.MILEAGE,RuleName.每公里,userType);
			if(kilomGoods!=null || mileageGoods!=null){
				//如果油电里程费为0，则计算预计里程费 
				CsPrice kilomPricer = kilomGoods==null ? null :  csPriceDao.getCsPrice(Lazy.add("cspGoods",kilomGoods.getCsgId()).add("cspOutets",cspOutets).add("cspModel",cspModel).add("cspUserType",userType));
				CsPrice mileagePricer = mileageGoods==null ? null : csPriceDao.getCsPrice(Lazy.add("cspGoods",mileageGoods.getCsgId()).add("cspOutets",cspOutets).add("cspModel",cspModel).add("cspUserType",userType));
				
				//如果未配置价格，则取默认网点价格
				if(kilomPricer==null && kilomGoods!=null && csFeeTypeSet!=null && csFeeTypeSet.getCsftsOutlets()!=null)
					kilomPricer = csPriceDao.getCsPrice(Lazy.add("cspGoods",kilomGoods.getCsgId()).add("cspOutets",csFeeTypeSet.getCsftsOutlets()).add("cspModel",cspModel).add("cspUserType",userType));
				if(mileagePricer==null && mileageGoods!=null && csFeeTypeSet!=null && csFeeTypeSet.getCsftsOutlets()!=null)
					mileagePricer = csPriceDao.getCsPrice(Lazy.add("cspGoods",mileageGoods.getCsgId()).add("cspOutets",csFeeTypeSet.getCsftsOutlets()).add("cspModel",cspModel).add("cspUserType",userType));

				Double kilomPrice = kilomPricer==null?0d:kilomPricer.getCspPrice();
				Double mileagePrice = mileagePricer==null?0d:mileagePricer.getCspPrice();
				
//				/**********************************************
//				 * 并且查找套餐中电里程费用替代价格方案
//				 **********************************************/	
//				List<CsGift> gifts = null;
//				if(kilomGoods!=null){
//					giftmap.get(kilomGoods.getCsgId()+","+cspOutets+","+cspModel);
//					if(gifts==null)
//						gifts=giftmap.get(kilomGoods.getCsgId()+","+cspOutets+","+0);
//					if(gifts==null)
//						gifts=giftmap.get(kilomGoods.getCsgId()+","+0+","+cspModel);
//					if(gifts==null)
//						gifts=giftmap.get(kilomGoods.getCsgId()+","+0+","+0);
//					if(gifts!=null)
//						kilomPrice=(gifts.get(0).getCsgPrice());
//				}
//				/**********************************************
//				 * 并且查找套餐中油里程费用替代价格方案
//				 **********************************************/
//				if(mileageGoods!=null){
//					gifts = giftmap.get(mileageGoods.getCsgId()+","+cspOutets+","+cspModel);
//					if(gifts==null)
//						gifts=giftmap.get(mileageGoods.getCsgId()+","+cspOutets+","+0);
//					if(gifts==null)
//						gifts=giftmap.get(mileageGoods.getCsgId()+","+0+","+cspModel);
//					if(gifts==null)
//						gifts=giftmap.get(mileageGoods.getCsgId()+","+0+","+0);
//					if(gifts!=null)
//						mileagePrice=(gifts.get(0).getCsgPrice());
//				}
				
				//预计里程费 = 按每小时15公里乘以max（油里程费+电里程费用）
				Double kmPrice = Math.max(kilomPrice,mileagePrice);
				int allhours = (int) ((finish.getTime()-start.getTime())/SYSTEM.HOUR);
				Integer actualkms = getPredictKms(allhours);
				if($.getLocal("defineactual")!=null){//调用之前设置过 $.setLocal("defineactual", 自定义预计里程数); 则以设置的自定义预计里程数
					actualkms = $.getLocal("defineactual");
					$.setLocal("defineactual", null);
				}
								
				Double rebate = 1.0d;
				if(actualkms<100)
					rebate = 1.0d;
				else if(actualkms<300)//100~299.99>>90%
					rebate=(0.9);
				else if(actualkms<500)//300~499.99>>85%
					rebate=(0.85);
				else if(actualkms<1000)//500~999.99>>80%
					rebate=(0.80);
				else
					rebate=(0.75);//1000~>75%
				
				totalFee = kmPrice * actualkms * rebate;
			}
		}
					
		orderInfo.setDetails(details);
		orderInfo.setPrice($(totalPrice));
		orderInfo.setFeeee(0d);		//orderInfo.setFeeee($(totalFee));  暂时拿掉了里程费，北京出行不计里程费
		orderInfo.setKilom(kilom);
		orderInfo.setMileage(mileage);
		orderInfo.setRent(rent);
		orderInfo.setInsure(insure);
		orderInfo.setTimeout(timeout);
		orderInfo.setDriver(driver);
		
		//======================================提前还车扣除罚金====================================================
//		if(retTime == null)retTime = finish;
//		if(retTime.before(finish)){
//			
//			if(oldOrder==null){
//				throw new MessageException("", 0);
//			}
//			
//			if(oldOrder.getCsoPayNeed() - orderInfo.getPayNeed() >0){
//				Double forfiet = $( (oldOrder.getCsoPayNeed() - orderInfo.getPayNeed()) * 0.1d );
//				
//				CsOrderDetail detail = new CsOrderDetail();
//				detail.setCsodPricer(0l);
//				detail.setCsodCar(carid);
//				detail.setCsodOrder(orderid);
//				detail.setCsodOutlets(oldOrder.getCsoOutlets());
//				detail.setCsodProvid(0l);
//				detail.setCsodModel(oldOrder.getCsoModel());
//				detail.setCsodPrice(0d);
//				detail.setCsodPriceReal(0d);	
//				detail.setCsodRebate(1.0d);
//				detail.setCsodCountReal(1d);
//				detail.setCsodTotal(0d);//理论金额
//				
//				CsGoods goods = getGoodsByFlag(SYSTEM.RENT, RuleName.每分钟, ordFeeType);
//				CsRule csRuleHour = goods.get$csgRule();
//				
//				detail.setCsodHost(oldOrder.getCsoHost());
//				detail.setCsodProduct(goods.getCsgProduct());
//				detail.setCsodPriceReal(forfiet);	//这里单价即为总价
//				detail.setCsodCount(1d);			//1个
//				detail.setCsodTotalReal(forfiet);
//				detail.setCsodAddTime(new Date());
//				detail.setCsodRule(csRuleHour.getCsrId());
//				detail.setCsodGoods(goods.getCsgId());
//				detail.setCsodStart(retTime);
//				detail.setCsodEnd(finish);
//				detail.setCsodRemark("提前还车扣罚租金");
//				orderInfo.getDetails().add(detail);
//				
//				orderInfo.setPrice(orderInfo.getPrice() + forfiet);
//			}
//		}
//		
		ActivityPromotion promotion = new  Activity2017LadiesDay(orderInfo, orderid, rentDetails, oldOrder, start, finish, isSettle);
		promotion.execute();
		
		/***************************************错误信息检测****************************************/	
		if(carid!=null){
			if(isExistOrderByTime(carid, start, finish, orderid))
				orderInfo.addError("当前选定时间已经被其它订单占用");
			String schedulingError = SchedulingService.checkGetRetOutlets(carid,csOutlets.getCsoId(),csOutletsRet.getCsoId(),start,finish);
			if(!$.empty(schedulingError))
				orderInfo.addError(schedulingError);
		}
		if(memberid!=null){
			csMember = CsMember.get(memberid);
			if(commonMoneyService.isRefunding(memberid))
				orderInfo.addError("当前支付会员正在退款，不能下单");
			if(csMember.getCsmStatus().shortValue()!=1)
				orderInfo.addError("当前支付会员帐号已被禁用");
			if(csMember.getCsmVDrive().shortValue()!=1)
				orderInfo.addError("该会员资料未审核通过，不能下单");
			if(csMember.getCsmStatus().shortValue()!=1)
				orderInfo.addError("该会员帐号已被禁用，不能下单");
			if(csMember.get$csmEvcard()==null)
				orderInfo.addError("没有绑定EV卡，不能下单");
			
		}
		
		/**********************************************
		 * 设置当前订单的占用红包
		 **********************************************/
		if(csMember!=null){
			Double memberCoin = commonMoneyService.getCoin(csOutlets.getCsoHost(),memberid,start);//会员所有的可用的红包数
			//如果当前订单不为空，会员可用免费小时则需加上当前订单之前所占用的红包
			if(orderid!=null){
				Double orderCoin = CsUseRecord.getCsUseRecordEval("sum(csur_count)", 
						$.add(CsUseRecord.F.csurMember, memberid)
						.add(CsUseRecord.F.csurCoin+"Not", "not")
						.add(CsUseRecord.F.csurOrder, orderid));
				if(orderCoin!=null){
					memberCoin+=orderCoin;
				}
			}
			
			//订单总所需费用,包括保证金和预计里程
			Double allOrderFee = orderInfo.feeee+orderInfo.price+orderInfo.margin;
			final Double finalCoin = Math.min(allOrderFee,memberCoin);//最终用到的红包数
			orderInfo.setUsecoin(finalCoin);//本次订单扣除的红包
			orderInfo.setCancoin(memberCoin);//可用红包
		}
		
		/***************************************错误信息检测****************************************/	
				
		System.out.println("########################拆分耗时["+(new Date().getTime()-splitStart.getTime())+"毫秒]#################################");
		
		return orderInfo;
	}

	/**
	 * 根据小时数获取预计里程数
	 * @param allhours
	 * @return
	 */
	public Integer getPredictKms(int allhours) {
		Integer kms = SYSTEM.getArgumentValue("预计里程每小时公里数", "PREDICT_HOUR_KMS", new Integer(15));
		Integer max = SYSTEM.getArgumentValue("预计里程天最大公里数", "PREDICT_DAY_MAX_KMS", new Integer(200));
		Integer theorykms = allhours*kms;//理论总公理数=总小时*每小时公里数	
		Integer actualkms = theorykms/(kms*24)*max+Math.min(max, theorykms%(kms*24));//实际总公理数每天最高只能算（预计里程天最大公里数）
		return actualkms;
	}
	
	/**
	 * 获取租金商品详情
	 * @param product
	 * @param feeType
	 * @param hostid
	 * @param ordbegin
	 * @param start
	 * @param finish
	 * @param rentPrice
	 * @return
	 */
	List<CsOrderDetail> getRentOrderDetail(CsProduct product, CsFeeTypeSet csFeeTypeSet, Long userType, Long outletsId, Long modelId, Long hostid, Date ordbegin,Date ordend, Date start, Date finish, CsPrice rentPrice, Map<Long, Map<String, Map>> prices){
		
		//========================计算要计算的这段时间的基础租金=============================================
		DateUtil dateUtil = new DateUtil();
		
		//理论商品列表
		List<CsGoods> theoryGoods = csGoodsDao.getCsGoodsList($.add("csgStatus", 1).add("csgUserType", userType).add("csgProduct", product.getCspId()), -1);
		Map<Long,CsGoods> ruleGoodsMap = new HashMap<Long,CsGoods>();
		String ruleIds = "0";
		for(CsGoods goods:theoryGoods){
			ruleIds+=","+goods.getCsgRule();
			ruleGoodsMap.put(goods.getCsgRule(), goods);
		}
		
//		String SQL = "select cp.*,cr.csr_expression,cr.csr_id,cg.csg_name from cs_price cp left join cs_goods cg on cp.csp_goods=cg.csg_id left join cs_rule cr on cr.csr_id = cg.csg_rule where cr.csr_status=1 and cp.csp_outets="+outletsId+" and cp.csp_model="+modelId+" and cp.csp_user_type="+userType+" and cp.csp_goods in  (SELECT csg_id FROM cs_goods where csg_status=1 and csg_product="+product.getCspId()+") order by cr.csr_priority desc";
//		List<Map> dataList = $.getDao("ccclubs_system").executeQuery(SQL);
//		Map<String, Map> rulePrice = new HashMap<String, Map>();
//		if(dataList!=null){
//			for(Map map : dataList){
//				rulePrice.put(String.valueOf(map.get("csr_id")), map);
//			}
//		}
//		if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsDefault()!=null){
//			SQL = "select cp.*,cr.csr_expression,cr.csr_id,cg.csg_name from cs_price cp left join cs_goods cg on cp.csp_goods=cg.csg_id left join cs_rule cr on cr.csr_id = cg.csg_rule where cr.csr_status=1 and cp.csp_outets="+csFeeTypeSet.getCsftsDefault()+" and cp.csp_model="+modelId+" and cp.csp_user_type="+userType+" and cp.csp_goods in  (SELECT csg_id FROM cs_goods where csg_status=1 and csg_product="+product.getCspId()+") order by cr.csr_priority desc";
//			dataList = $.getDao("ccclubs_system").executeQuery(SQL);
//			if(dataList!=null){
//				for(Map map : dataList){
//					if(rulePrice.get(String.valueOf(map.get("csr_id"))) == null){
//						rulePrice.put(String.valueOf(map.get("csr_id")), map);
//					}
//				}
//			}
//		}
		
//		String timeString = parseTime(start, finish, srvHost, userType, monthFeeType);
		//取对应的规则列表
		List<CsRule> rules = csRuleDao.getCsRuleList(Lazy.add("csrStatus", 1).add("definex", "csr_id in ("+ruleIds+")").add("desc", "csr_priority"),-1);
		
//		/**************************把时间轴转成字符串轴*******************************/
//		Map<Long, Pack> packMaps = new HashMap<Long, Pack>();
//		List<Long> packRules = new ArrayList<Long>();
//		for(Long key : ruleGoodsMap.keySet()){
//			CsGoods goods = ruleGoodsMap.get(key);
//			String json = goods.getCsgProfile();
//			if(StringUtils.isNotEmpty(json)){
//				Map<String, Object> mapParam = (Map)APICallHelper.fromJson(json);
//				if(mapParam.get("params")!=null){
//					packMaps.put(key, Pack.getPack(dateUtil, start, mapParam));
//					packRules.add(key);
//				}
//			}
//		}
//		
//		List<OrderDetails> opList = new ArrayList<CommonOrderService.OrderDetails>();
//		for(Long packRuleId : packRules){
//			opList.add(new OrderDetails(getOrderDetail(product, userType, hostid, ordbegin, start, finish, rentPrice, ruleGoodsMap, dateUtil, rules, rulePrice, timeString, packMaps)));
//			rules.remove(crMaps.get(packRuleId));
//		}
//		
//		Collections.sort(opList, new Comparator<OrderDetails>(){
//			@Override
//			public int compare(OrderDetails o1, OrderDetails o2) {
//				OrderDetails a = (OrderDetails) o1;
//				OrderDetails b = (OrderDetails) o2;
//				return a.getTotal() > (b.getTotal()) ? 1 : 0;
//			}
//		});
		
//		return CollectionUtils.isEmpty(opList) ? null : opList.get(opList.size()-1).getItems();
		return getOrderDetail(userType, hostid, ordbegin, ordend, start, finish, rentPrice, ruleGoodsMap, dateUtil, rules, prices.get(product.getCspId()));
	}
	
	/**
	 * 超时产品
	 * @return
	 */
	public List<CsOrderDetail> getTimeoutOrderDetail(CsProduct product, Date start, Date finish, Long feeType, SrvHost host, CsPrice rentPrice, Map<Long, Map<String, Map>> prices){
		if(product == null)return new ArrayList<CsOrderDetail>();
		//理论商品列表
		List<CsGoods> theoryGoods = csGoodsDao.getCsGoodsList($.add("csgStatus", 1).add("csgUserType", feeType).add("csgProduct", product.getCspId()), -1);
		Map<Long,CsGoods> ruleGoodsMap = new HashMap<Long,CsGoods>();
		String ruleIds = "0";
		for(CsGoods goods:theoryGoods){
			ruleIds+=","+goods.getCsgRule();
			ruleGoodsMap.put(goods.getCsgRule(), goods);
		}
		
		DateUtil dateUtil = new DateUtil();
		//取对应的规则列表
		List<CsRule> rules = csRuleDao.getCsRuleList(Lazy.add("csrStatus", 1).add("definex", "csr_id in ("+ruleIds+")").add("desc", "csr_priority"),-1);
		
		//String timeString = parseTime(start, finish, host, feeType, null);
		
		return getOrderDetail(feeType, host.getShId(), start, finish, start, finish, rentPrice, ruleGoodsMap, dateUtil, rules, prices.get(product.getCspId()));
	}
	
	boolean hasSubPackRule(List<CsRule> rules, Map<Long,CsGoods> ruleGoodsMap, int currentIndex){
		if(rules.size() > currentIndex+1 ){
			return Pack.hasPack(ruleGoodsMap.get(rules.get(currentIndex + 1).getCsrId()).getCsgProfile());
		}
		return false;
	}
	
	/**
	 * 拆分产品到订单项（如果该产品需要拆分）
	 * @param product
	 * @param ordbegin  订单开始时间
	 * @param start		计算开始时间
	 * @param end		计算结束时间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CsOrderDetail> getOrderDetail(Long userType, Long hostid, Date ordbegin, Date ordend, Date start, Date finish, CsPrice minRentPrice, Map<Long,CsGoods> ruleGoodsMap, DateUtil dateUtil, List<CsRule> rules, Map<String, Map> rulePrice){
		List<CsOrderDetail> details = new ArrayList<CsOrderDetail>();
		SrvHost host = SrvHost.get(hostid);
		
		String timeString = parseTime(start, finish, host , userType, null);
		if($.or(timeString, "").equals("*")){
			//不构成10分钟一个单位的长度
			Long minutes = (finish.getTime() - start.getTime()) /SYSTEM.MINUTE ;
			details.add(assembleDetail(minRentPrice.get$cspGoods().getCsgRule(), minRentPrice.get$cspGoods().getCsgId(), start, finish, minutes.intValue(), minRentPrice.getCspPrice()));
			return details;
		}
		
		/**************************把时间轴转成字符串轴*******************************/
//		Map<Long, Pack> packMaps = new HashMap<Long, Pack>();
//		List<Long> packRules = new ArrayList<Long>();
//		Pack tmpPack;
//		for(Long key : ruleGoodsMap.keySet()){
//			tmpPack = null;
//			CsGoods goods = ruleGoodsMap.get(key);
//			String json = goods.getCsgProfile();
//			if(StringUtils.isNotEmpty(json)){
//				Map<String, Object> mapParam = (Map)APICallHelper.fromJson(json);
//				tmpPack = Pack.getPack(dateUtil, start, mapParam);
//				if(tmpPack!=null){
//					packMaps.put(key, tmpPack);
//					packRules.add(key);
//				}
//			}
//		}
		
		stepline:
		for(int i=0; i<rules.size(); i++){
			CsRule csRule = rules.get(i);
			String strRegx = "("+csRule.getCsrExpression()+")" ;//规则表达式
			Pattern pattern = Pattern.compile(strRegx);
			Matcher matcher = pattern.matcher(timeString);
			StringBuffer sb = new StringBuffer();
			while(matcher.find()){
				
				Date begin = new Date(start.getTime()+(matcher.start() / 2 * 10 * SYSTEM.MINUTE));
				Date end = new Date(start.getTime()+(matcher.end() / 2 * 10 * SYSTEM.MINUTE));
				//Logger.getRootLogger().error("bef\t"+csRule.getCsrName()+"->"+dateUtil.dateToString(begin, null)+"->"+dateUtil.dateToString(end, null));
				if(rulePrice.get(csRule.getCsrId$()) == null || rulePrice.get(csRule.getCsrId$()).get("csp_price") == null){
					Logger.getRootLogger().error(csRule.getCsrName()+"价格为空！");
					//throw new ErrorException("PRICE_ERROR","价格未配置","["+csRule.getCsrId()+csRule.getCsrGoods$()+"]["+CsUserType.getKeyValue(userType)+"]未配置价格");
					continue stepline;
				}
				Object priceObj = rulePrice.get(csRule.getCsrId$()).get("csp_price");
				Double price = Double.valueOf(priceObj.toString());
				
				//Pack pack = packMaps.get(csRule.getCsrId());
				Pack pack = Pack.getPack(dateUtil, start, finish, ruleGoodsMap.get(csRule.getCsrId()).getCsgProfile(), host);
				
				if(pack!=null){
					
					//tail of finish
					if(((ordend.getTime()- end.getTime()) /SYSTEM.MINUTE) < 10){
						end = ordend;
					}
					
					pack.setParam(begin, end, minRentPrice.getCspPrice(), price);
					boolean bool = (boolean) eval($.add("$", pack), pack.getRule());
					if(bool){
						begin = pack.getStart();
						end   = pack.getFinish();
						//System.out.println("aft\t"+csRule.getCsrName()+"->"+dateUtil.dateToString(begin, null)+"->"+dateUtil.dateToString(end, null));
						
						if(!pack.isFit()){
							if(hasSubPackRule(rules, ruleGoodsMap, i)){
								
								List<CsRule> _rules = new ArrayList<CsRule>();
								_rules.addAll(rules.subList(i+1, rules.size()));
								
								List<CsOrderDetail> subOrderDetails = getOrderDetail(userType, hostid, ordbegin, ordend, begin, end, minRentPrice, ruleGoodsMap, dateUtil, _rules, rulePrice);
								if(!CollectionUtils.isEmpty(subOrderDetails)){
									if(new OrderDetails(subOrderDetails).getTotal() < price){
										details.addAll(subOrderDetails);
										break stepline;
									}
								}
							}
						}
						
						//头部再拆一遍
						if(begin.after(start)){
							details.addAll(getOrderDetail(userType, hostid, ordbegin, ordend, start, begin, minRentPrice, ruleGoodsMap, dateUtil, rules, rulePrice));
						}
						
						//尾部再拆一遍
						if(finish.after(end)){
							details.addAll(getOrderDetail(userType, hostid, ordbegin, ordend, end, finish, minRentPrice, ruleGoodsMap, dateUtil, rules, rulePrice));
						}
						
						details.add(assembleDetail(csRule.getCsrId(), ruleGoodsMap.get(csRule.getCsrId()).getCsgId(), begin, end, 1, price));
						
						break stepline;
					}else{
						continue stepline;
					}
				}else {
					
					String prof = $.or(ruleGoodsMap.get(csRule.getCsrId()).getCsgProfile(), "");
					if(Pack.hasPack(prof)){
						continue stepline;
					}
				}
				
				//计算数量			
				long minutes = (end.getTime()-begin.getTime())/1000/60;
				//==========================追加最小的计价单位=================================
				if(end.before(finish)){
					minutes += (finish.getTime()-end.getTime())/SYSTEM.MINUTE; 
					end = finish;
				} 
				
				String expression = csRule.getCsrMeas().replace("{M}", ""+minutes);
				Integer count = eval(null, expression);
				
				Double c = Math.floor(count);
				Long remain = minutes - Long.valueOf(c.intValue() * 60);
				
				//=========================剩余的时长按照最小单位【分钟】来计算=========================================
				boolean flag = false;
				if((remain > 0  && remain <60)){
					if(end.before(finish)){
						remain += (finish.getTime()-end.getTime())/SYSTEM.MINUTE; 
						end = finish;
					} 
					
					details.add(assembleDetail(minRentPrice.get$cspGoods().getCsgRule(), minRentPrice.get$cspGoods().getCsgId(), DateUtils.add(end, Calendar.MINUTE, remain.intValue() * -1), end, remain.intValue(), minRentPrice.getCspPrice()));
					end = DateUtils.add(end, Calendar.MINUTE, remain.intValue() * -1);
					
					flag = true;
				}
				
				if(count>0.99){
					details.add(assembleDetail(csRule.getCsrId(), ruleGoodsMap.get(csRule.getCsrId()).getCsgId(), begin, end, count, price));
					
					flag = true;
				}
				
				if(flag){
					String regValue = matcher.group(1);
					matcher.appendReplacement(sb,regValue.replaceAll(".", ""));
				}
			}
			matcher.appendTail(sb);
			timeString = sb.toString();
		}

		return details;
	}
	
	/**
	 * 根据商品、时间或里程获取该订单项
	 * @param csGoods
	 * @param start
	 * @param end
	 * @return
	 */
	public List<CsOrderDetail> getKilometerOrderDetail(CsGoods csGoods, Date start, Date finish, Double kilometer){	
		
		List<CsOrderDetail> details = new ArrayList<CsOrderDetail>();
		if(kilometer == null || kilometer <=0) return details;
		if(csGoods == null) return details;
		
		//取对应的计费规则
		CsRule rule = csRuleDao.getCsRuleById(csGoods.getCsgRule());		
		CsOrderDetail detail = new CsOrderDetail();
		detail.setCsodAddTime(new Date());
		detail.setCsodRule(rule.getCsrId());
		detail.setCsodGoods(csGoods.getCsgId());
		detail.setCsodStart(start);
		detail.setCsodEnd(finish);
		
		/**
		 * 根据规则的运算表达式计算数量
		 */
		String expression = rule.getCsrMeas();//
		if(start!=null && finish!=null){
			long minutes = (finish.getTime()-start.getTime())/1000/60;		
			expression=expression.replace("{M}", ""+minutes);
		}		
		if(kilometer!=null){
			expression=expression.replace("{K}", ""+kilometer);
		}		
		Object count_ = eval(null, expression);
		Double count = count_!=null ? Double.valueOf(count_.toString()) : 0d;
		count = new BigDecimal(count).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		detail.setCsodCount(count);
		
		if(count>0)
			details.add(detail);

		return details;
	}
	
	/**
	 * 限行数据缓存，key格式为 城市ID+"-"+尾号
	 */
	public static Map<String,List> cachetimes = new HashMap();
	public static long lastlimitupdatetime = -1;//最后修改限行的时间
	/**
	 * 获取车辆的限行时间列表
	 * @param carid
	 * @return
	 */
	public List<Restriction> getRestrictions(Long carid, Date start, Date end) {
		if(carid==null||carid.longValue()==0)
			return new ArrayList();
		CsCar csCar = csCarDao.getCsCarById(carid);
		String cscNumber = csCar.getCscNumber();
		Matcher matcher = Pattern.compile("(\\d)([^\\d]+)?$").matcher(cscNumber);		
		if(!matcher.find()){
			new ErrorException("CAR_NUMBER_ERROR","车牌号码格式错误","车牌号码["+cscNumber+"]格式错误,理论上车牌号中应该存在一个数字");
			return new ArrayList();
		}
		
		String lastNumber = matcher.group(1);
		//先到限行表中找一个最后修改的限行,如果与内存中保存的不一样，则把所有限行缓存清空一下
		CsLimitTime lastModify = $.getRequest("lastModify");
		if(lastModify==null){
			lastModify = CsLimitTime.Get($.add("desc", "cslt_update_time"));
			$.setRequest("lastModify", lastModify);
		}
		
		if(lastModify!=null && lastModify.getCsltUpdateTime()!=null && lastModify.getCsltUpdateTime().getTime()!=lastlimitupdatetime){
			lastlimitupdatetime = lastModify.getCsltUpdateTime().getTime();
			cachetimes.clear();
		}
		//查询交通限行表
		List<CsLimitTime> limittimes = cachetimes.get(csCar.getCscHost()+"-"+lastNumber);
		if(limittimes==null){
			cachetimes.put(csCar.getCscHost()+"-"+lastNumber, limittimes = CsLimitTime.list(
				$.add(CsLimitTime.F.csltHost, csCar.getCscHost())//取当前城市
				.add(CsLimitTime.F.csltNum,"%"+lastNumber+"%")//取当前车牌尾号相关的限行数据
			, -1));
		}
		List<Restriction> restrictions=new ArrayList();
		List<Date> dates =  getDateList(start, end);
		for(Date date:dates){
			DateType dateType = this.getDateType(date);
			if(dateType!=DateType.WorkDay)continue;
			int weekIndex = CommonOrderService.getWeekIndex(date);//取周序列
			for(CsLimitTime csLimitTime:limittimes){
				if(csLimitTime.getCsltWeekDay().intValue()!=weekIndex)
					continue;			
				restrictions.add(new Restriction(
					$.date($.date(date, "yyyy-MM-dd "+csLimitTime.getCsltStart()+":00"), "yyyy-MM-dd HH:mm:ss"),
					$.date($.date(date, "yyyy-MM-dd "+csLimitTime.getCsltEnd()+":00"), "yyyy-MM-dd HH:mm:ss")
				));
			}
		}
		return restrictions;
	}
	
	/**
	 * 测试某车辆某时间内是否存在限行
	 * @param carid 车辆ID
	 * @param start 开始时间
	 * @param finish结束时间
	 * @return
	 */
	public Boolean isExistRestrictionByTime(Long carid, Date start, Date finish) {
		//检查订单的时间的有效性
		List<Restriction> restrictions= this.getRestrictions(carid, start, finish);
		for(Restriction restriction:restrictions){
			if(!(start.before(restriction.getStart()) || start.after(restriction.getFinish())))
				return true;
			if(!(finish.before(restriction.getStart()) || finish.after(restriction.getFinish())))
				return true;
			if(start.before(restriction.getStart()) && finish.after(restriction.getFinish()))
				return true;
		}		
		return false;
	}
	
	/**
	 * 获取车辆的限行时间列表
	 * @param carid
	 * @param dayCount,从当天开始往后多少天
	 * @return
	 */
	public List<Restriction> getRestrictions(Long carid, int dayCount) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.HOUR_OF_DAY, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		Date start = calender.getTime();
		calender.add(Calendar.DATE, dayCount);
		Date finish = calender.getTime();		
		return getRestrictions(carid,start,finish);
	}
	
	
	/**
     * 拆封订单计费明细
     * @param productId 产品id
     * @param csFeeTypeSet 计费策略
     * @param userType 计费类型
     * @param outletsId 网点id
     * @param modelId 车型id
     * @param hostId 城市id
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    List<CsOrderDetail> buildOrderDetails(Long productId, CsFeeTypeSet csFeeTypeSet,
            Long userType, Long outletsId, Long modelId, Long hostId, Date start, Date end) {
        //订单明细集合初始化
        List<CsOrderDetail> results = new ArrayList<>(); 
        //获取计费规则
        Map<String, TimeSlot> slotMap = getRules(csFeeTypeSet.getCsftsOutlets(), outletsId, modelId, userType, productId);
        
        //强行校验规则配置、未配置不予下单和结算
        if (slotMap.get(RuleName.每分钟.name()) == null || slotMap.get(RuleName.每小时.name()) == null
                || slotMap.get(RuleName.夜租一.name()) == null
                || slotMap.get(RuleName.夜租二.name()) == null
                || slotMap.get(RuleName.夜租三.name()) == null) {
            throw new MessageException("计费规则配置不全", 1996); 
        }
        
        //计算用车时长天数
        int days = TimeUtil.getDaysBetween(start, end, null);
        if(days>0) {
            //以24小时为结算周期拆分计费时间
            List<TimeBlock> timeBlocks = splitTime(start, null, slotMap);
            //打印时间段
            for (TimeBlock timeBlock : timeBlocks) {
                System.out.println(TimeUtil.format(timeBlock.getStartTime(), TimeUtil.DATE.FORMAT_DEFAULT));
                System.out.println(TimeUtil.format(timeBlock.getEndTime(), TimeUtil.DATE.FORMAT_DEFAULT));
            }
            //计算24小时内最有计费组合
            List<CsOrderDetail> list = buildOrderDetailsIn24Hours(start, null, timeBlocks, slotMap);
            //追加第一天的订单明细
            results.addAll(list);
            
            //追加第二天开始的订单明细，且处理备注和时间
            JSONObject json = new JSONObject();
            for (int i = 1; i < days; i++) {
                for (CsOrderDetail bean : list) {
                    bean.setCsodStart(TimeUtil.addDay(bean.getCsodStart(), 1));
                    bean.setCsodEnd(TimeUtil.addDay(bean.getCsodEnd(), 1));
                    if(bean.getCsodRemark() != null) {
                        json = JSONObject.parseObject(bean.getCsodRemark());
                        if(json!=null) {
                            json.put("time", TimeUtil.format(bean.getCsodStart(), null) + "~" + TimeUtil.format(bean.getCsodEnd(), null));
                            bean.setCsodRemark(json.toJSONString());
                        }
                    }
                }
                results.addAll(list);
            }
            
            //拆分不满24小时的计费时长
            start = TimeUtil.addDay(start, days);
            timeBlocks = splitTime(start, end, slotMap);
            //打印时间段
            for (TimeBlock timeBlock : timeBlocks) {
                System.out.println(TimeUtil.format(timeBlock.getStartTime(), TimeUtil.DATE.FORMAT_DEFAULT));
                System.out.println(TimeUtil.format(timeBlock.getEndTime(), TimeUtil.DATE.FORMAT_DEFAULT));
            }
            
            list = buildOrderDetailsIn24Hours(start, end, timeBlocks, slotMap);
            results.addAll(list);
        }else {
            //拆分不满24小时的计费时长
            List<TimeBlock> timeBlocks = splitTime(start, end, slotMap);
            //打印时间段
            for (TimeBlock timeBlock : timeBlocks) {
                System.out.println(TimeUtil.format(timeBlock.getStartTime(), TimeUtil.DATE.FORMAT_DEFAULT));
                System.out.println(TimeUtil.format(timeBlock.getEndTime(), TimeUtil.DATE.FORMAT_DEFAULT));
            }
            List<CsOrderDetail> list = buildOrderDetailsIn24Hours(start, end, timeBlocks, slotMap); 
            results.addAll(list);
        }

        return results;
    }

    /**
     * 根据计费规则，拆分收费时间段
     * @param start 开始时间
     * @param end 截止时间：如果等于空，截止时间为当前时间后移24小时
     * @param slotMap 计费槽
     * @return
     */
    private List<TimeBlock> splitTime(Date start, Date end, Map<String, TimeSlot> slotMap) {
        //截止时间：如果等于空，截止时间为当前时间后移24小时
        if(end==null) {
            end = TimeUtil.addHour(start, 24);
        }
        TimeSlot nightSlot1 = slotMap.get(RuleName.夜租一.name());
        TimeSlot nightSlot2 = slotMap.get(RuleName.夜租二.name());
        TimeSlot nightSlot3 = slotMap.get(RuleName.夜租三.name());
        // 时间轴：满24小时的时间轴、不满24小时的时间轴
        List<TimeBlock> timeBlocks = new ArrayList<>();
        TimeBlock timeBlock = new TimeBlock();// 时间片段

        Date tempStart = new Date();//时间段拆分临时开始时间
        Date tempEnd = new Date();//时间段拆分临时结束时间
        String date = TimeUtil.format(start, TimeUtil.DATE.FORMAT_yyyy_MM_dd);//截取开始时间的日期
        Date nightStart = TimeUtil.stringToDate(date +" "+ nightSlot1.getStart());//当天夜租一开始时间
        Date nightStartUp = TimeUtil.stringToDate(date +" "+ nightSlot1.getStartUp());//当天夜租一开始时间上限
        Date nightFinish = TimeUtil.addMinute(nightStart, nightSlot1.getDuration());//当天夜租一结束时间
        Date lastNightFinish = TimeUtil.addDay(nightFinish, -1);//前天的夜租三截止时间
        // 起始时间在夜租一开始时间之前，夜租三截止时间之后
        if (start.getTime() < nightStart.getTime()
                && start.getTime() >= lastNightFinish.getTime()) {

            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一开始时间
            tempStart = start;
            tempEnd = nightStart;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一时间段
            tempStart = tempEnd;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
            timeBlocks.add(timeBlock);
            return timeBlocks;
        }

        // 起始时间在夜租一有效时间之内
        if (start.getTime() >= nightStart.getTime()
                && start.getTime() < nightStartUp.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            date = TimeUtil.format(tempStart, TimeUtil.DATE.FORMAT_yyyy_MM_dd);
            tempEnd = TimeUtil.stringToDate(date +" "+ nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end;
            if(tempEnd.getTime()>tempStart.getTime()) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
                timeBlocks.add(timeBlock);
            }
            return timeBlocks;
        }

        /**
         * 计算【费用/夜租二】
         */
        nightStart = TimeUtil.stringToDate(date +" "+ nightSlot2.getStart());//当天夜租二开始时间
        nightStartUp = TimeUtil.stringToDate(date +" "+ nightSlot2.getStartUp());//当天夜租二开始时间上限
        nightFinish = TimeUtil.addMinute(nightStart, nightSlot2.getDuration());//当天夜租二结束时间
        // 起始时间在夜租二有效时间之内
        if (start.getTime() >= nightStart.getTime()
                && start.getTime() < nightStartUp.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT2);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT2);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            date = TimeUtil.format(tempStart, TimeUtil.DATE.FORMAT_yyyy_MM_dd);
            tempEnd = TimeUtil.stringToDate(date +" "+ nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
            timeBlocks.add(timeBlock);
            return timeBlocks;
        }
        
        /**
         * 计算【费用/夜租三】
         */
        nightStart = TimeUtil.stringToDate(date +" "+ nightSlot3.getStart());//当天夜租三开始时间
        nightFinish = TimeUtil.addMinute(nightStart, nightSlot3.getDuration());//当天夜租三开始时间
        // 起始时间在当天的夜租三有效时间之内
        if (start.getTime() >= nightStart.getTime() && start.getTime() < nightFinish.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT3);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT3);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            date = TimeUtil.format(tempStart, TimeUtil.DATE.FORMAT_yyyy_MM_dd); 
            tempEnd = TimeUtil.stringToDate(date +" "+ nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
            timeBlocks.add(timeBlock);
            return timeBlocks;
        }
        
        // 起始时间在昨天夜租三有效时间之内
        nightStart = TimeUtil.addDay(nightStart, -1);//昨天夜租三开始时间
        nightFinish = TimeUtil.addMinute(nightStart, nightSlot3.getDuration());//昨天夜租三开始时间
        if (start.getTime() >= nightStart.getTime() && start.getTime() < nightFinish.getTime()) {
            /**** 以24小时为周期，分割时间 ****/
            // 开始时间~夜租一结束时间
            tempStart = start;
            tempEnd = nightFinish;
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT3);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT3);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一结束时间~夜租一开始时间
            tempStart = tempEnd;
            date = TimeUtil.format(tempStart, TimeUtil.DATE.FORMAT_yyyy_MM_dd); 
            tempEnd = TimeUtil.stringToDate(date +" "+ nightSlot1.getStart());
            if(tempEnd.before(end)) {
                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
            }else {
                timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
                timeBlocks.add(timeBlock);
                return timeBlocks;
            }

            // 夜租一开始时间~24小时周期末
            tempStart = tempEnd;
            tempEnd = end; 
            timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
            timeBlocks.add(timeBlock);
            return timeBlocks;
        }

        //假设时段不跨天
//        nightStart = TimeUtil.stringToDate(date +" "+ nightSlot3.getStart());//当天夜租三开始时间
//        nightFinish = TimeUtil.addMinute(nightStart, nightSlot3.getDuration());//当天夜租三开始时间
//        if(TimeUtil.isSameDay(nightStart, nightFinish)) {
//            // 起始时间在第二天夜租一开始时间之前，夜租三截止时间之后
//            Date nextNightStart = TimeUtil.stringToDate(date +" "+ nightSlot1.getStart());//第二天夜租三开始时间
//            nextNightStart = TimeUtil.addDay(nextNightStart, 1);
//            Date nextNightFinish = TimeUtil.addMinute(nextNightStart, nightSlot1.getDuration());
//            if (start.getTime() >= nightFinish.getTime()
//                    && start.getTime() <= nextNightStart.getTime()) {
//
//                /**** 以24小时为周期，分割时间 ****/
//                // 开始时间~夜租一开始时间
//                tempStart = start;
//                tempEnd = nextNightStart;
//                if(tempEnd.before(end)) {
//                    timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
//                    timeBlocks.add(timeBlock);
//                }else {
//                    timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_DAYTIME);
//                    timeBlocks.add(timeBlock);
//                    return timeBlocks;
//                }
//
//                // 夜租一时间段
//                tempStart = tempEnd;
//                tempEnd = nextNightFinish;
//                if(tempEnd.before(end)) {
//                    timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_NIGHT1);
//                    timeBlocks.add(timeBlock);
//                }else {
//                    timeBlock = new TimeBlock(tempStart, end, TimeBlock.FEE_NIGHT1);
//                    timeBlocks.add(timeBlock);
//                    return timeBlocks;
//                }
//
//                // 夜租一结束时间~24小时周期末
//                tempStart = tempEnd;
//                tempEnd = end; 
//                timeBlock = new TimeBlock(tempStart, tempEnd, TimeBlock.FEE_DAYTIME);
//                timeBlocks.add(timeBlock);
//                return timeBlocks;
//            } 
//        }
        
        return timeBlocks;
    }

    /**
     * 根据计费规则，将订单时长拆分为多个收费项,订单备注先以json存储，供后续处理
     * @param start 开始时间
     * @param end 截止时间：如果等于空，截止时间为当前时间后移24小时,否则截止时间距离开始时间不满24小时
     * @param timeBlocks 时间段集合
     * @param slotMap 计费槽
     * @return
     */
    private List<CsOrderDetail> buildOrderDetailsIn24Hours(Date start, Date end, List<TimeBlock> timeBlocks,
            Map<String, TimeSlot> slotMap) {
        if(end==null) {
            end = TimeUtil.addHour(start, 24);
        }
        
        TimeSlot minuteSlot = slotMap.get(RuleName.每分钟.name());
        TimeSlot hourSlot = slotMap.get(RuleName.每小时.name());
        TimeSlot daySlot = slotMap.get(RuleName.每天.name());
        // 以24小时为结算周期，统计订单时长持续多少个结算周期
        List<CsOrderDetail> details = new ArrayList<>();

        double totalFee = 0;// 总费用
        double dayFee = daySlot.getPrice();// 每天封顶收费
        CsOrderDetail detil = new CsOrderDetail();// 计费明细

        // 时间段遍历结算计费明细
        for (TimeBlock timeBlock : timeBlocks) {
            List<CsOrderDetail> list = new ArrayList<>();
            if (timeBlock.getFeeMode() == TimeBlock.FEE_DAYTIME) {
                // 普通计费时区
                list = buildOrdinaryOrderDetail(timeBlock, minuteSlot, hourSlot);
            } else if (timeBlock.getFeeMode() == TimeBlock.FEE_NIGHT1) {
                // 夜租一计费时区
                list = buildNightOrderDetail(timeBlock, slotMap, RuleName.夜租一);
            } else if (timeBlock.getFeeMode() == TimeBlock.FEE_NIGHT2) {
                // 夜租二计费时区
                list = buildNightOrderDetail(timeBlock, slotMap, RuleName.夜租二);
            } else if (timeBlock.getFeeMode() == TimeBlock.FEE_NIGHT3) {
                // 夜租三计费时区
                list = buildNightOrderDetail(timeBlock, slotMap, RuleName.夜租三);
            }
            // 装载计费明细
            if (list.size() > 0) {
                details.addAll(list);
            }
        }

        // 统计夜租、小时、分钟计费组合收费(方案一)
        JSONObject json = new JSONObject();
        StringBuilder feeDetail = new StringBuilder();
        for (CsOrderDetail detail : details) {
            if (detail != null) {
                totalFee += detail.getCsodPrice() * detail.getCsodCount();
                feeDetail = feeDetail.append(detail.getCsodRemark());
            }
        }
        // 统计小时、分钟计费组合(方案二)
        TimeBlock timeBlock = new TimeBlock(start, end, TimeBlock.FEE_DAYTIME);
        List<CsOrderDetail> list = buildOrdinaryOrderDetail(timeBlock, minuteSlot, hourSlot);
        double tempTotalFee = 0;// 总费用
        // 累计方案二时长费用
        for (CsOrderDetail detail : list) {
            if (detail != null) {
                tempTotalFee += detail.getCsodPrice() * detail.getCsodCount();
            }
        }
        // 方案一和方案二对比，去最优计费方案，并记录当前计费信息
        if(tempTotalFee<=totalFee) {
            feeDetail = new StringBuilder();
            totalFee = tempTotalFee;
            details = list;
            for (CsOrderDetail detail : details) {
                if (detail != null) {
                    feeDetail = feeDetail.append(detail.getCsodRemark());
                }
            }
        }
        
        //记录组合计费明细
        json.put("combination", feeDetail.toString());

        // 24小时封顶收费计算
        if (totalFee > dayFee) {
            detil = buildOrderDetail(daySlot, start, end, 1);
            
            //消费计价明细
            json.put("time", TimeUtil.format(start, null) + "~" + TimeUtil.format(end, null));
            json.put("feeRule", "24小时封顶计费");
            json.put("cheapTip", "24小时封顶计费最优惠");
            json.put("totalFee", dayFee);
            detil.setCsodRemark(json.toJSONString());
            
            // 清楚其他计费记录
            details.clear();
            // 保留当前计费记录
            details.add(detil);
            System.out.println(json.toJSONString());
        }

        return details;
    }

    /**
     * 普通时区计费明细
     * 
     * @param timeBlock
     * @param minuteSlot
     * @param hourSlot
     * @return
     */
    private List<CsOrderDetail> buildOrdinaryOrderDetail(TimeBlock timeBlock, TimeSlot minuteSlot,
            TimeSlot hourSlot) {
        List<CsOrderDetail> details = new ArrayList<>();
        CsOrderDetail detil = new CsOrderDetail();// 计费明细
        // 分钟数
        int minutes = TimeUtil.getMinutesBetween(timeBlock.getStartTime(), timeBlock.getEndTime(),
                RoundMode.Ceiling);
        // 小时数
        int hours = minutes / 60;
        // 不足一小时分钟数
        int normalMinutes = minutes - hours * 60;

        double minuteFee = minuteSlot.getPrice();// 分钟收费单价
        double hourFee = hourSlot.getPrice();// 小时封顶收费
        double tempMuniteFee = minutes * minuteFee;// 分钟计费总价
        double fee = hourFee / 60;// 小时计费单价
        JSONObject json = new JSONObject();//消费计价明细
        //普通时长计费
        if (fee >= minuteFee) {
            // 分钟计费优惠于小时计费
            detil = buildOrderDetail(minuteSlot, timeBlock.getStartTime(), timeBlock.getEndTime(),
                    minutes);
            json.put("time", TimeUtil.format(timeBlock.getStartTime(), null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
            json.put("feeRule", "普通时长计费");
            json.put("cheapTip", "分钟计费优惠于小时计费");
            json.put("hourFee", hourFee);
            json.put("minuteFee", minuteFee);
            json.put("nightFee", "");
            json.put("hours", hours);
            json.put("minutes", minutes);
            json.put("totalFee", tempMuniteFee);
            
            detil.setCsodRemark(json.toJSONString());
            details.add(detil);
            System.out.println(json.toJSONString());
        } else {
            // 小时计费单价优惠于分钟计费：总费用=hours*hourFee+(minutes*minuteFee||hourFee)
            Date temp = TimeUtil.addHour(timeBlock.getStartTime(), hours);
            
            if(hours>=1) {
                detil = buildOrderDetail(hourSlot, timeBlock.getStartTime(), temp, hours);
                
                json.clear();
                json.put("time", TimeUtil.format(timeBlock.getStartTime(), null) + "~" + TimeUtil.format(temp, null));
                json.put("feeRule", "普通时长计费");
                json.put("cheapTip", "小时计费优惠于分钟计费");
                json.put("hourFee", hourFee);
                json.put("minuteFee", minuteFee);
                json.put("hours", hours);
                json.put("totalFee", hourFee * hours);
                
                detil.setCsodRemark(json.toJSONString());
                details.add(detil);
                System.out.println(json.toJSONString());
            }
            // 不满一小时的最低收费
            if(normalMinutes>0) {
                tempMuniteFee = normalMinutes * minuteFee;
                if (tempMuniteFee > hourFee) {
                    //分钟计费满足小时计费封顶
                    detil = buildOrderDetail(hourSlot, temp, timeBlock.getEndTime(), 1);
                    
                    json.clear();
                    json.put("time", TimeUtil.format(temp, null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
                    json.put("feeRule", "普通时长计费");
                    json.put("cheapTip", "时长不满一小时,小时封顶计费优惠于分钟计费");
                    json.put("hourFee", hourFee);
                    json.put("minuteFee", minuteFee);
                    json.put("minutes", normalMinutes);
                    json.put("totalFee", hourFee);
                    
                    detil.setCsodRemark(json.toJSONString());
                    details.add(detil);
                    System.out.println(json.toJSONString());
                } else {
                    //分钟计费不满足小时计费封顶
                    detil = buildOrderDetail(minuteSlot, temp, timeBlock.getEndTime(), normalMinutes);
                    
                    json.clear();
                    json.put("time", TimeUtil.format(temp, null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
                    json.put("feeRule", "普通时长计费");
                    json.put("cheapTip", "时长不满一小时,分钟计费优惠于小时封顶计费");
                    json.put("hourFee", hourFee);
                    json.put("minuteFee", minuteFee);
                    json.put("minutes", normalMinutes);
                    json.put("totalFee", normalMinutes * minuteFee);
                    
                    detil.setCsodRemark(json.toJSONString());
                    details.add(detil);
                    System.out.println(json.toJSONString());
                }
            }
        }

        return details;
    }

    /**
     * 夜租时区计费明细
     * 
     * @param timeBlock
     * @param slotMap
     * @param ruleName
     * @return
     */
    private List<CsOrderDetail> buildNightOrderDetail(TimeBlock timeBlock, Map<String, TimeSlot> slotMap,
            RuleName ruleName) {
        TimeSlot minuteSlot = slotMap.get(RuleName.每分钟.name());
        TimeSlot hourSlot = slotMap.get(RuleName.每小时.name());
        TimeSlot nightSlot = slotMap.get(ruleName.name());
        List<CsOrderDetail> details = new ArrayList<>();
        CsOrderDetail detil = new CsOrderDetail();// 计费明细
        // 分钟数
        int minutes = TimeUtil.getMinutesBetween(timeBlock.getStartTime(), timeBlock.getEndTime(),
                RoundMode.Ceiling);
        // 小时数
        int hours = minutes / 60;
        // 不足一小时分钟数
        int normalMinutes = minutes - hours * 60;

        double minuteFee = minuteSlot.getPrice();// 分钟收费单价
        double hourFee = hourSlot.getPrice();// 小时封顶收费
        double nightFee = nightSlot.getPrice();// 夜租封顶费用
        double tempMuniteFee;// 分钟计费总费用
        double tempHourFee;// 小时计费总费用
        double totalFee;// 总费用
        JSONObject json = new JSONObject();//消费计价明细

        // 分钟计费
        tempMuniteFee = minutes * minuteFee;
        
        //用车不满一小时
        if(hours==0) {
            totalFee = tempMuniteFee>hourFee? hourFee : tempMuniteFee;
        }else {
            // 小时计费
            tempHourFee = hourFee * hours;
            // 小时、分钟组合计费
            totalFee = normalMinutes * minuteFee;
            if(totalFee>hourFee) {
                totalFee = hourFee;
            }
            totalFee = totalFee + tempHourFee; 
        }

        // 分钟计费、小时计费、夜租计费取最优惠组合
        if (totalFee < nightFee || tempMuniteFee < nightFee) {
            //夜租时段:分钟计费最优惠
            if (totalFee >= tempMuniteFee) {
                detil = buildOrderDetail(minuteSlot, timeBlock.getStartTime(),
                        timeBlock.getEndTime(), minutes);
                
                json.put("time", TimeUtil.format(timeBlock.getStartTime(), null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
                json.put("feeRule", ruleName.name()+"时长计费");
                json.put("cheapTip", "分钟计费最优惠");
                json.put("hourFee", hourFee);
                json.put("minuteFee", minuteFee);
                json.put("nightFee", nightFee);
                json.put("minutes", minutes);
                json.put("totalFee", minuteFee * minutes);
                
                detil.setCsodRemark(json.toJSONString());
                details.add(detil);
                System.out.println(json.toJSONString());
            } else {
                //夜租时段:分钟计费、小时计费组合最优惠
                Date temp = TimeUtil.addHour(timeBlock.getStartTime(), hours);
                if(hours>=1) {
                    //小时计费明细
                    detil = buildOrderDetail(hourSlot, timeBlock.getStartTime(), temp, hours);
                    
                    json.clear();
                    json.put("time", TimeUtil.format(timeBlock.getStartTime(), null) + "~" + TimeUtil.format(temp, null));
                    json.put("feeRule", ruleName.name()+"时长计费");
                    if(normalMinutes>0) {
                        json.put("cheapTip", "分钟计费、小时计费组合最优惠");
                    }else {
                        json.put("cheapTip", "小时计费最优惠");
                    }
                    json.put("hourFee", hourFee);
                    json.put("minuteFee", minuteFee);
                    json.put("nightFee", nightFee);
                    json.put("hours", hours);
                    json.put("totalFee", hours * hourFee);
                    
                    detil.setCsodRemark(json.toJSONString());
                    details.add(detil);
                    System.out.println(json.toJSONString());
                }

                //不足一小时的分钟数计费
                if(normalMinutes>0) {
                    totalFee = normalMinutes*minuteFee;
                    if(totalFee>hourFee) {
                        //分钟计费满足小时计费封顶
                        detil = buildOrderDetail(hourSlot, temp, timeBlock.getEndTime(), 1);
                        
                        json.clear();
                        json.put("time", TimeUtil.format(temp, null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
                        json.put("feeRule", ruleName.name()+"时长计费");
                        json.put("cheapTip", "时长不满一小时,小时封顶计费优惠于分钟计费");
                        json.put("hourFee", hourFee);
                        json.put("minuteFee", minuteFee);
                        json.put("nightFee", nightFee);
                        json.put("minutes", normalMinutes);
                        json.put("totalFee", hourFee);
                        
                        detil.setCsodRemark(json.toJSONString());
                        details.add(detil);
                        System.out.println(json.toJSONString());
                    }else {
                        //分钟计费不满足小时计费封顶
                        detil = buildOrderDetail(minuteSlot, temp, timeBlock.getEndTime(), normalMinutes);
                        
                        json.clear();
                        json.put("time", TimeUtil.format(temp, null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
                        json.put("feeRule", ruleName.name()+"时长计费");
                        json.put("cheapTip", "时长不满一小时,分钟计费最优惠");
                        json.put("hourFee", hourFee);
                        json.put("minuteFee", minuteFee);
                        json.put("nightFee", nightFee);
                        json.put("minutes", normalMinutes);
                        json.put("totalFee", normalMinutes*minuteFee);
                        
                        detil.setCsodRemark(json.toJSONString());
                        details.add(detil);
                        System.out.println(json.toJSONString());
                    } 
                }
            }
        } else {
            //夜租封顶计费最优惠
            detil = buildOrderDetail(nightSlot, timeBlock.getStartTime(), timeBlock.getEndTime(),
                    1);
            
            json.clear();
            json.put("time", TimeUtil.format(timeBlock.getStartTime(), null) + "~" + TimeUtil.format(timeBlock.getEndTime(), null));
            json.put("feeRule", ruleName.name()+"时长计费");
            json.put("cheapTip", "夜租封顶计费最优惠");
            json.put("hourFee", hourFee);
            json.put("minuteFee", minuteFee);
            json.put("nightFee", nightFee);
            json.put("minutes", minutes);
            json.put("totalFee", nightFee);
            
            detil.setCsodRemark(json.toJSONString());
            details.add(detil);
            System.out.println(json.toJSONString());
        }

        return details;
    }

    /**
     * 根据车型、网点、用户类型、业务获取收费规则列表。默认收费规则增量追加至当前已有配置规则。收费规则以时间槽形式表示
     * 
     * @param defaultOutletsId 默认网点
     * @param outletsId 当前网点
     * @param modelId 车型
     * @param userType 用户类型
     * @param productId 业务id（产品即业务）
     * @return
     */
    @Override
    public Map<String, TimeSlot> getRules(Long defaultOutletsId, Long outletsId, Long modelId,
            Long userType, Long productId) {
        // 获取当前配置计费规则（价格表、规则表、商品表关联查询）
        String sql =
                "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_meas, cr.csr_priority, cg.csg_name, cg.csg_product, cg.csg_profile from cs_price cp "
                        + " left join cs_goods cg on cp.csp_goods=cg.csg_id and cp.csp_user_type=cg.csg_user_type "
                        + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                        + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets="
                        + outletsId + " and cp.csp_model=" + modelId + " and cp.csp_user_type="
                        + userType + " and cg.csg_status=1 and cg.csg_product =" + productId
                        + " order by cr.csr_priority desc";
        List<Map> rules = $.getDao("ccclubs_system").executeQuery(sql);

        // 规则列表转换成Map<规则名称, 规则>,key唯一即规则唯一
        Map<String, Map<String, Object>> ruleMap = new HashMap<String, Map<String, Object>>();
        if (rules != null) {
            for (Map<String, Object> rule : rules) {
                ruleMap.put(String.valueOf(rule.get("csr_name")), rule);
            }
        }

        // 获取默认配置计费规则并增量模式追加至已有规则（价格表、规则表、商品表关联查询）
        if (defaultOutletsId != null) {
            sql = "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_meas, cr.csr_priority, cg.csg_name, cg.csg_product, cg.csg_profile from cs_price cp "
                    + " left join cs_goods cg on cp.csp_goods=cg.csg_id and cp.csp_user_type=cg.csg_user_type "
                    + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                    + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets="
                    + defaultOutletsId + " and cp.csp_model=" + modelId + " and cp.csp_user_type="
                    + userType + " and cg.csg_status=1 and cg.csg_product =" + productId
                    + " order by cr.csr_priority desc";

            rules = $.getDao("ccclubs_system").executeQuery(sql);
            // 增量模式追加至Map<规则名称, 规则>，key唯一即规则唯一
            if (rules != null) {
                for (Map rule : rules) {
                    if (ruleMap.get(String.valueOf(rule.get("csr_name"))) == null) {
                        ruleMap.put(String.valueOf(rule.get("csr_name")), rule);
                    }
                }
            }
        }
        // 将计费规则转换成时间槽
        Map<String, TimeSlot> result = new HashMap<>();
        Map<String, Object> rule = null;
        for (String key : ruleMap.keySet()) {
            TimeSlot slot = new TimeSlot();
            rule = ruleMap.get(key);
            // 规则id
            if (rule.get("csr_id") != null) {
                slot.setRuleId(Long.valueOf(rule.get("csr_id").toString().trim()));
            }
            // 商品id
            if (rule.get("csp_goods") != null) {
                slot.setGoodsId(Long.valueOf(rule.get("csp_goods").toString().trim()));
            }
            // 规则名称（唯一标识）
            if (rule.get("csr_name") != null) {
                slot.setName(rule.get("csr_name").toString());
            }
            // 日期正则表达式
            if (rule.get("csr_expression") != null) {
                slot.setRegex(rule.get("csr_expression").toString());
            }
            // 计价公式
            if (rule.get("csr_meas") != null) {
                slot.setFormula(rule.get("csr_meas").toString());
            }
            // 规则优先级
            if (rule.get("csr_priority") != null) {
                slot.setPriority(Integer.valueOf(rule.get("csr_priority").toString().trim()));
            }
            // 设置价格表定义的收费标准
            if (rule.get("csp_price") != null) {
                slot.setPrice(Double.valueOf(rule.get("csp_price").toString().trim()));
            }
            // 解析商品中定义的时间段
            if (rule.get("csg_profile") != null) {
                slot.parseGoodsProfile(rule.get("csg_profile").toString());
            }

            result.put(key, slot);
        }


        return result;
    }

    CsOrderDetail buildOrderDetail(TimeSlot slot, Date start, Date end, int count) {
        CsOrderDetail detail = new CsOrderDetail();
        detail.setCsodAddTime(new Date());
        detail.setCsodRule(slot.getRuleId());
        detail.setCsodGoods(slot.getGoodsId());
        detail.setCsodPrice(slot.getPrice());
        detail.setCsodStart(start);
        detail.setCsodEnd(end);
        detail.setCsodCount(Double.valueOf(count));
        return detail;
    }
	
}
