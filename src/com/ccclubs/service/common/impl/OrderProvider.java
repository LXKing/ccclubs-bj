package com.ccclubs.service.common.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import bsh.EvalError;
import bsh.Interpreter;

import com.ccclubs.action.web.activity.newyear2015.Activity;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.dao.ICsCarDao;
import com.ccclubs.dao.ICsFreeHourDao;
import com.ccclubs.dao.ICsGiftDao;
import com.ccclubs.dao.ICsGoodsDao;
import com.ccclubs.dao.ICsHolidayDao;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.dao.ICsOrderDao;
import com.ccclubs.dao.ICsOrderDetailDao;
import com.ccclubs.dao.ICsOutletsDao;
import com.ccclubs.dao.ICsPriceDao;
import com.ccclubs.dao.ICsProductDao;
import com.ccclubs.dao.ICsRuleDao;
import com.ccclubs.dao.ICsUseRecordDao;
import com.ccclubs.dao.ICsUserTypeDao;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsHoliday;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.model.CsProduct;
import com.ccclubs.model.CsRule;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonOrderService.DateType;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class OrderProvider {
	
	ICsRuleDao csRuleDao;
	ICsGoodsDao csGoodsDao;
	ICsProductDao csProductDao;
	ICsPriceDao csPriceDao;
	ICsMemberDao csMemberDao;
	ICsUserTypeDao csUserTypeDao;
	ICsGiftDao csGiftDao;	
	ICsOrderDao csOrderDao;
	ICsOrderDetailDao csOrderDetailDao;
	ICsHolidayDao csHolidayDao;	
	ICsFreeHourDao csFreeHourDao;
	ICsUseRecordDao csUseRecordDao;
	ICsCarDao csCarDao;
	ICsOutletsDao csOutletsDao;
	ICommonMoneyService commonMoneyService;
	
	static Map<String,Boolean> holidays = null;
	static Map<String,Boolean> workdays = null;
	static Map<String,Short>   weekdays = null;
	
	public static ICommonOrderService bean(){
		return Lazy.GetSpringBean("commonOrderService");
	}
	
	public Double $(Double value){
		if(value==null || value.doubleValue()==0.01)return 0d;
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	<T> T eval(Map<String, Object> param, String expression){
		if(StringUtils.isEmpty(expression))return null;
		Interpreter interpreter = new Interpreter();
		try {  
			if(param!=null)
			for(String key : param.keySet()){
				interpreter.set(key, param.get(key));
			}
			Object object = interpreter.eval(expression);
			//System.out.println(expression+"->"+object.toString());
			if(object!=null){
				return (T)object;
			}
		} catch (EvalError e) {
			Logger.getRootLogger().error("表达式执行异常["+expression+"]", e);
			e.printStackTrace();  
		} 
		return null;
	}
	
	CsOrderDetail assembleDetail(Long ruleId, Long goodsId, Date start, Date end, int count, Double price){
		CsOrderDetail detail = new CsOrderDetail();
		detail.setCsodAddTime(new Date());
		detail.setCsodRule(ruleId);
		detail.setCsodGoods(goodsId);
		detail.setCsodStart(start);
		detail.setCsodEnd(end);
		detail.setCsodCount(Double.valueOf(count));
		detail.setCsodPrice(price);
		return detail;
	}
	
	
	
	public Map<Long, Map<String, Map>> getProductRentPrices(CsFeeTypeSet csFeeTypeSet, Long outletsId, Long modelId, Long userType){
		String SQL = "select cp.*,cr.csr_expression,cr.csr_id,cg.csg_name,cg.csg_product from cs_price cp left join cs_goods cg on cp.csp_goods=cg.csg_id left join cs_rule cr on cr.csr_id = cg.csg_rule where cr.csr_status=1 and cp.csp_outets="+outletsId+" and cp.csp_model="+modelId+" and cp.csp_user_type="+userType+" and cg.csg_status=1  order by cr.csr_priority desc";
		List<Map> dataList = $.getDao("ccclubs_system").executeQuery(SQL);
		
		Map<Long, Map<String, Map>> rulePrices = new HashMap<Long, Map<String, Map>>();
		
		Map<String, Map> rulePrice = new HashMap<String, Map>();
		if(dataList!=null){
			for(Map map : dataList){
				rulePrice.put(String.valueOf(map.get("csp_id")), map);
			}
		}
		if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsDefault()!=null){
			SQL = "select cp.*,cr.csr_expression,cr.csr_id,cg.csg_name,cg.csg_product from cs_price cp left join cs_goods cg on cp.csp_goods=cg.csg_id left join cs_rule cr on cr.csr_id = cg.csg_rule where cr.csr_status=1 and cp.csp_outets="+csFeeTypeSet.getCsftsDefault()+" and cp.csp_model="+modelId+" and cp.csp_user_type="+userType+" and cg.csg_status=1  order by cr.csr_priority desc";
			dataList = $.getDao("ccclubs_system").executeQuery(SQL);
			if(dataList!=null){
				for(Map map : dataList){
					if(rulePrice.get(String.valueOf(map.get("csp_id"))) == null){
						rulePrice.put(String.valueOf(map.get("csp_id")), map);
					}
				}
			}
		}
		
		for(String map : rulePrice.keySet()){
			Map<String, Map> rentRulePrice = rulePrices.get((Long)rulePrice.get(map).get("csg_product"));
			
			if(rentRulePrice == null ) rentRulePrice = new HashMap<String, Map>();
			rentRulePrice.put(String.valueOf((Long)rulePrice.get(map).get("csr_id")), rulePrice.get(map));
			
			rulePrices.put((Long)rulePrice.get(map).get("csg_product"), rentRulePrice);
		}
		
		return rulePrices;
	}
	
	public String parseTime(Date start, Date finish, SrvHost srvHost, Long feeType, CsUserType monthFeeType){
		String timeString = "";
		
		if((finish.getTime() - start.getTime()) /SYSTEM.MINUTE < 10){
			return "*";
		}
		
		Calendar calender = Calendar.getInstance();
		calender.setTime(start);
		if(monthFeeType!=null && monthFeeType.getCsutId().longValue() == feeType.longValue()){//长租类型
			while(finish.after(calender.getTime())){//从开始时间处以每递增1天遍历时间			
				timeString+="Y";	
				calender.add(Calendar.DATE, 1);
			}
		}else{
			while(finish.after(calender.getTime())){//从开始时间处以每递增1小时遍历时间		
				
				if((finish.getTime() - calender.getTime().getTime()) / SYSTEM.MINUTE < 10){
					break;
				}
				
				Double hourIndex = getHourIndex(calender.getTime()); // calender.get(Calendar.HOUR_OF_DAY);
				
				String type = getHourType(calender.getTime(), srvHost);
				char c = 0;
				if(hourIndex < $.or(srvHost.getShOnHour(), 9d) || hourIndex >= $.or(srvHost.getShOffHour(), 17d)){
					c = 'N';
				}else{
					c = 'D';
				}
				
				timeString+=type+c;	
				calender.add(Calendar.MINUTE, 10);
			}
		}
		return timeString;
	}
	
	/**
	 * 根据标识获取产品
	 * @param flag
	 * @return
	 */
	public CsProduct getProductByFlag(String flag) {
		return csProductDao.getCsProduct(Lazy.add("cspFlag", flag).add("cspStatus", 1));
	}
	
	/**
	 * 根据标识获取规则
	 * @param ruleFlag
	 * @return
	 */
	private CsRule getRuleByName(String ruleName) {
		return csRuleDao.getCsRule(Lazy.add("csrName", ruleName).add("csrStatus", 1));
	}
	
	/**
	 * 根据标识获取唯一商品
	 * @param flag
	 * @return
	 */
	public CsGoods getGoodsByFlag(String productFlag, RuleName ruleName, Long userType) {
		CsProduct product = this.getProductByFlag(productFlag);
		CsRule rule = this.getRuleByName(ruleName.name());
		CsGoods goods = null;
		if(product!=null){
			goods = csGoodsDao.getCsGoods(
				Lazy.add("csgProduct", product.getCspId())
				.add("csgRule", rule.getCsrId())
				.add("csgUserType", userType)
			);
		}else
			System.out.println("系统未配置标识为["+productFlag+"]["+ruleName+"]的产品");
		return goods;
	}	
	
	Long getUserType(Long feeType, CsFeeTypeSet csFeeTypeSet, SrvHost srvHost, CsOrder oldOrder, CsCarModel csCarModel){
		//////////////////计费策略校验//////////////////
		if(oldOrder!=null)//默认以原订单计费类型
			feeType = oldOrder.getCsoFeeType();
		if(feeType==null || feeType.longValue()==0l)
			feeType = null;
		//再没有则以地区默认策略
		//if(feeType==null)
			//feeType = srvHost.getShFeeType();
		
		//检查会员的计费类型是否是当前地区所接受的计费类型
		if(csFeeTypeSet!=null && csFeeTypeSet.getCsftsFeeType().indexOf("#"+feeType+"#")==-1)
			feeType = csFeeTypeSet.getCsftsDefault();
		
		return feeType;
	}
	
	public List<OrderPrice> getOrderPrices(Long hostId, Long outletsId, Long carModel, Long userType, CsFeeTypeSet feeTypeSet){
		
		if(feeTypeSet.getCsftsFeeType().indexOf(userType.toString()) == -1){
			throw new MessageException("不可用该计费类型", 0);
		}
		
		String TplSQL = "select cp.csp_id price_id, cp.csp_price price, cp.csp_goods goods_id, cg.csg_name goods_name,cg.csg_profile goods_profile,ct.csp_id prod_id,ct.csp_name prod_name,cr.csr_id rule_id,cr.csr_expression rule_expression from cs_price cp left join cs_goods cg on cp.csp_goods=cg.csg_id left join cs_product ct on ct.csp_id = cg.csg_product left join cs_rule cr on cr.csr_id = cg.csg_rule where cg.csg_status=1 and cp.csp_outets={outletsId} and cp.csp_model={carModel} and cp.csp_user_type={userType}  order by cr.csr_priority desc";
		String SQL = TplSQL.replaceAll("{outletsId}", outletsId.toString());
		SQL = SQL.replaceAll("{carModel}", carModel.toString());
		SQL = SQL.replaceAll("{userType}", userType.toString());
		List<Map> dataList = $.getDao("ccclubs_system").executeQuery(SQL);
		if(CollectionUtils.isEmpty(dataList)){
			if(feeTypeSet!=null && feeTypeSet.getCsftsOutlets()!=null){
				SQL = TplSQL.replaceAll("{outletsId}", feeTypeSet.getCsftsOutlets().toString());
				SQL = SQL.replaceAll("{carModel}", carModel.toString());
				SQL = SQL.replaceAll("{userType}", userType.toString());
				dataList = $.getDao("ccclubs_system").executeQuery(SQL);
			}
		}
		
		List<OrderPrice> resultList = new ArrayList<OrderProvider.OrderPrice>();
		if(!CollectionUtils.isEmpty(dataList)){
			for(Map map : dataList){
				OrderPrice data = new OrderPrice();
				try {
					BeanUtils.populate(data, map);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				resultList.add(data);
			}
		}
		return resultList;
	}
	
	/***************************日期处理工具********************************/
	/**
	 * 获取日期的类型
	 * @param date
	 * @return
	 */
	public DateType getDateType(Date date){
		//从数据库中读取节假日
		if(holidays==null || workdays==null || weekdays==null){
			holidays = new HashMap();
			workdays = new HashMap();
			weekdays = new HashMap();
			List<CsHoliday> list = csHolidayDao.getCsHolidayList(Lazy.Map(), -1);
			for(CsHoliday csHoliday:list){
				if(csHoliday.getCshType().intValue()==0){//放假
					holidays.put(Lazy.date(csHoliday.getCshDate(), "yyyy-MM-dd"), true);
				}else{//补班
					workdays.put(Lazy.date(csHoliday.getCshDate(), "yyyy-MM-dd"), true);
					weekdays.put(Lazy.date(csHoliday.getCshDate(), "yyyy-MM-dd"), csHoliday.getCshIndex());
				}				
			}			
		}		
		String sDate = Lazy.date(date, "yyyy-MM-dd");		
		if(holidays.get(sDate)!=null)//数据库指定节假日
			return DateType.HoliDay;		
		if(workdays.get(sDate)!=null)//数据库指定工作日
			return DateType.WorkDay;		
		int weekIndex = getWeekIndex(date);		
		if(weekIndex==1||weekIndex==7)
			return DateType.CeaseDay;
		else
			return DateType.WorkDay;
	}
	
	/**
	 * 获取小时类型 c为双休日 w为工作日 h为节假日
	 * @param csUserType 
	 * @param date
	 * @return
	 */
	private String getHourType(Date time, SrvHost srvHost){
		Long ONEDAY = 1000l*60l*60l*24l;
		DateType today = getDateType(time);
		DateType yestoday = getDateType(new Date(time.getTime()-ONEDAY));
		DateType tomorrow = getDateType(new Date(time.getTime()+ONEDAY));

		if(today==DateType.HoliDay)
			return "h";
		else if(today==DateType.CeaseDay)
			return "c";
		else{
			Double hourIndex = getHourIndex(time);
			if(hourIndex < $.or(srvHost.getShOnHour(),9d)){
				if(yestoday==DateType.HoliDay)return "h";
				if(yestoday==DateType.CeaseDay)return "c";
				return "w";
			}else if(hourIndex >= $.or(srvHost.getShOffHour(),17d)){
				if(tomorrow==DateType.HoliDay)return "h";
				if(tomorrow==DateType.CeaseDay)return "c";
				return "w";
			}else{
				return "w";
			}
		}
	}	
	
	static List<Date> getDateList(Date start, Date finish) {		
		List<Date> dates = new ArrayList();
		Calendar calender = Calendar.getInstance();
		calender.setTime(start);
		while(!finish.before(calender.getTime())){//从开始时间处以每递增1天遍历日期		
			dates.add(calender.getTime());
			calender.add(Calendar.DATE, 1);
		}				
		return dates;
	}
	
	static Double getHourIndex(Date time){
		Calendar calender = Calendar.getInstance();
		calender.setTime(time);
		return calender.get(Calendar.HOUR_OF_DAY)+(calender.get(Calendar.MINUTE)*0.01);
	}
	
	static int getWeekIndex(Date time){		
		String sDate = Lazy.date(time, "yyyy-MM-dd");		
		Short weekIndex = weekdays.get(sDate);
		if(weekIndex!=null)
			return weekIndex;		
		Calendar calender = Calendar.getInstance();
		calender.setTime(time);
		return calender.get(Calendar.DAY_OF_WEEK);
	}
	
	public Date min(Date a, Date b){
		if(a==null)
			return b;
		if(a.before(b))
			return a;
		else
			return b;
	}
	
	/**
	 * 设置秒为0
	 * @return
	 */
	public Date resetSecond(Date time){
		return $.date($.date(time, "yyyy-MM-dd HH:mm:00"),"yyyy-MM-dd HH:mm:00");
	}
	/***************************日期处理工具********************************/
	
	class PriceModel {
		
		CsGoods goods;
		
		CsUserType csUserType;
		
		Long outletsId;
		
		Long carModel;
		
		SrvHost host;
		
		List<OrderPrice> ordPriceList;
		
		/*
		 * 产品标识 + 规则名称
		 */
		Map<String, OrderPrice> prodFlag_ruleName__goods = new HashMap<String, OrderProvider.OrderPrice>();
		
		/*
		 * 产品标识
		 */
		Map<String, List<OrderPrice>> prodFlag__goods = new HashMap<String, List<OrderPrice>>();
		
		public PriceModel(List<OrderPrice> ordPriceList){
			this.ordPriceList = ordPriceList;
			
			if(!CollectionUtils.isEmpty(ordPriceList)){
				for(OrderPrice ordp : ordPriceList){
					prodFlag_ruleName__goods.put(ordp.getProd_flag() + ordp.getRule_name(), ordp);
					
					List<OrderPrice> rulegoodsList = prodFlag__goods.get(ordp.getProd_flag());
					if(rulegoodsList == null) rulegoodsList = new ArrayList<OrderProvider.OrderPrice>();
					rulegoodsList.add(ordp);
					
					prodFlag__goods.put(ordp.getProd_flag(), rulegoodsList);
				}
			}
		}
		
		public List<OrderPrice> getByProductFlag(String productFlag){
			return prodFlag__goods.get(productFlag);
		}
		
		public OrderPrice getByProductFlagAndRuleName(String productFlag, RuleName ruleName){
			return prodFlag_ruleName__goods.get(productFlag + ruleName.name());
		}
	}
	
	class OrderPrice{
		private Long	 	price_id;
		private Double 	 	price;
		private Long   	 	goods_id;
		private String 		goods_name;
		private String 		goods_profile;
		private Long 		prod_id;
		private String      prod_flag;
		private String 		prod_name;
		private Long 		rule_id;
		private String      rule_name;
		private String 		rule_expression;
		private String      rule_means;
		
		public Long getPrice_id() {
			return price_id;
		}
		public void setPrice_id(Long price_id) {
			this.price_id = price_id;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Long getGoods_id() {
			return goods_id;
		}
		public void setGoods_id(Long goods_id) {
			this.goods_id = goods_id;
		}
		public String getGoods_name() {
			return goods_name;
		}
		public void setGoods_name(String goods_name) {
			this.goods_name = goods_name;
		}
		public String getGoods_profile() {
			return goods_profile;
		}
		public void setGoods_profile(String goods_profile) {
			this.goods_profile = goods_profile;
		}
		public Long getProd_id() {
			return prod_id;
		}
		public void setProd_id(Long prod_id) {
			this.prod_id = prod_id;
		}
		public String getProd_name() {
			return prod_name;
		}
		public void setProd_name(String prod_name) {
			this.prod_name = prod_name;
		}
		public Long getRule_id() {
			return rule_id;
		}
		public void setRule_id(Long rule_id) {
			this.rule_id = rule_id;
		}
		public String getProd_flag() {
			return prod_flag;
		}
		public void setProd_flag(String prod_flag) {
			this.prod_flag = prod_flag;
		}
		public String getRule_expression() {
			return rule_expression;
		}
		public void setRule_expression(String rule_expression) {
			this.rule_expression = rule_expression;
		}
		public String getRule_name() {
			return rule_name;
		}
		public void setRule_name(String rule_name) {
			this.rule_name = rule_name;
		}
		public String getRule_means() {
			return rule_means;
		}
		public void setRule_means(String rule_means) {
			this.rule_means = rule_means;
		}
	}
	
	class OrderDetails{
		
		List<CsOrderDetail> items;
		
		Double total = 0d;
		
		public OrderDetails(List<CsOrderDetail> items){
			this.items = items;
		}
		
		/**
		 * 汇总所有订单项的价格集合
		 * @return
		 */
		OrderDetails calc(){
			total = 0d;
			if(!CollectionUtils.isEmpty(items)){
				for(CsOrderDetail ordetail : items){
					total += ordetail.getCsodCount() * ordetail.getCsodPrice();
				}
			}
			return this;
		}

		public Double getTotal() {
			if(total == 0d && !CollectionUtils.isEmpty(items)){
				calc();
			}
			return total;
		}

		public List<CsOrderDetail> getItems() {
			return items;
		}

		public void setItems(List<CsOrderDetail> items) {
			this.items = items;
		}

	}

	public ICsRuleDao getCsRuleDao() {
		return csRuleDao;
	}
	public void setCsRuleDao(ICsRuleDao csRuleDao) {
		this.csRuleDao = csRuleDao;
	}
	public ICsGoodsDao getCsGoodsDao() {
		return csGoodsDao;
	}
	public void setCsGoodsDao(ICsGoodsDao csGoodsDao) {
		this.csGoodsDao = csGoodsDao;
	}
	public ICsProductDao getCsProductDao() {
		return csProductDao;
	}
	public void setCsProductDao(ICsProductDao csProductDao) {
		this.csProductDao = csProductDao;
	}
	public ICsPriceDao getCsPriceDao() {
		return csPriceDao;
	}
	public void setCsPriceDao(ICsPriceDao csPriceDao) {
		this.csPriceDao = csPriceDao;
	}
	public ICsMemberDao getCsMemberDao() {
		return csMemberDao;
	}
	public void setCsMemberDao(ICsMemberDao csMemberDao) {
		this.csMemberDao = csMemberDao;
	}
	public ICsGiftDao getCsGiftDao() {
		return csGiftDao;
	}
	public void setCsGiftDao(ICsGiftDao csGiftDao) {
		this.csGiftDao = csGiftDao;
	}
	public ICsOrderDao getCsOrderDao() {
		return csOrderDao;
	}
	public void setCsOrderDao(ICsOrderDao csOrderDao) {
		this.csOrderDao = csOrderDao;
	}
	public ICsOrderDetailDao getCsOrderDetailDao() {
		return csOrderDetailDao;
	}
	public void setCsOrderDetailDao(ICsOrderDetailDao csOrderDetailDao) {
		this.csOrderDetailDao = csOrderDetailDao;
	}
	public ICsHolidayDao getCsHolidayDao() {
		return csHolidayDao;
	}
	public void setCsHolidayDao(ICsHolidayDao csHolidayDao) {
		this.csHolidayDao = csHolidayDao;
	}
	public ICsFreeHourDao getCsFreeHourDao() {
		return csFreeHourDao;
	}
	public void setCsFreeHourDao(ICsFreeHourDao csFreeHourDao) {
		this.csFreeHourDao = csFreeHourDao;
	}
	public ICsUseRecordDao getCsUseRecordDao() {
		return csUseRecordDao;
	}
	public void setCsUseRecordDao(ICsUseRecordDao csUseRecordDao) {
		this.csUseRecordDao = csUseRecordDao;
	}
	public ICsCarDao getCsCarDao() {
		return csCarDao;
	}
	public void setCsCarDao(ICsCarDao csCarDao) {
		this.csCarDao = csCarDao;
	}
	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}
	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}
	public ICsOutletsDao getCsOutletsDao() {
		return csOutletsDao;
	}
	public void setCsOutletsDao(ICsOutletsDao csOutletsDao) {
		this.csOutletsDao = csOutletsDao;
	}
	public ICsUserTypeDao getCsUserTypeDao() {
		return csUserTypeDao;
	}
	public void setCsUserTypeDao(ICsUserTypeDao csUserTypeDao) {
		this.csUserTypeDao = csUserTypeDao;
	}	
}
