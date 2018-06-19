package com.ccclubs.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitFee;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsUnitFeeService;
import com.lazy3q.web.helper.$;

public class UnitHelper {
	
	/**
	 * 根据使用ID企业获取支付ID
	 * @param unitid
	 * @param useid
	 * @return
	 */
	public static Long getPayIdByUseId(Long unitid,Long useid){
		CsUnitInfo csUnitInfo = CsUnitInfo.get(unitid);
		CsMemberShip csMemberShip = CsMemberShip.Get($.add(CsMemberShip.F.csmsTargeter, useid).add("definex", "csms_payer in ("+csUnitInfo.getCsuiMember()+")"));
		if(csMemberShip!=null)
			return csMemberShip.getCsmsPayer();
		return useid;
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取企业代理订单价格信息
	 * @return
	 */
	public static UnitOrderInfo getUnitOrderInfo(Long unitId,Long car,Date start,Date finish,Date ret,Integer freehour,Double km,Double freeKm){		
		
		CsCar csCar = CsCar.get(car);
		CsUnitFee csUnitFee = csCar==null ? null : CsUnitFee.where().csufModel(csCar.getCscModel()).csufUnitInfo(unitId).get();
		
		Date end = ret;
		if(end==null)end = finish;
		if(end.before(finish))end = finish;		
		double time = end.getTime()-start.getTime();	
		
		//减去免费小时
		if(freehour==null)freehour=0;
		time-=freehour*SYSTEM.HOUR;
		if(time<0)
			time = 0;		
				
		Double day = 0d;
		Double hour = 0d;
		//如果天租价格大于0，则取天数
		if(csUnitFee!=null && csUnitFee.getCsufDayPrice()>0){
			day = Math.floor(time/SYSTEM.DAY);
			//时长减去已用天数
			time = time - day*SYSTEM.DAY;
		}
		//如果小时价格大于0，则取小时数
		if(csUnitFee!=null && csUnitFee.getCsufHourPrice()>0)
			hour = time/SYSTEM.HOUR;//小数
		else//否则把零散的小时追加到天上
			day+=time/SYSTEM.DAY;//小数
		
		day = $(day);
		hour = $(hour);
		
		if(km==null)
			km = 0d;
		if(freeKm==null)
			freeKm = 0d;		
		km -= freeKm;
		if(km<0)
			km = 0d;
		
		Double total = 0d;
		if(csUnitFee!=null)
			total = $(day*csUnitFee.getCsufDayPrice()+hour*csUnitFee.getCsufHourPrice()+km*csUnitFee.getCsufMileage());
				
		return new UnitOrderInfo(day, hour,
				csUnitFee==null?null:csUnitFee.getCsufDayPrice(), 
				csUnitFee==null?null:csUnitFee.getCsufHourPrice(), 
				csUnitFee==null?null:csUnitFee.getCsufMileage()
				, km, total);
	}
	public static Double $(Double value){
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 企业订单信息
	 * @author uiu
	 *
	 */
	public static class UnitOrderInfo{
		Double day;
		Double hour;
		Double dayPrice;
		Double hourPrice;
		Double mileage;
		Double km;
		Double total;
		/**
		 * @param day
		 * @param hour
		 * @param dayPrice
		 * @param hourPrice
		 * @param mileage
		 * @param km
		 * @param total
		 */
		public UnitOrderInfo(Double day, Double hour, Double dayPrice,
				Double hourPrice, Double mileage, Double km, Double total) {
			super();
			this.day = day;
			this.hour = hour;
			this.dayPrice = dayPrice;
			this.hourPrice = hourPrice;
			this.mileage = mileage;
			this.km = km;
			this.total = total;
		}
		public Double getDay() {
			return day;
		}
		public void setDay(Double day) {
			this.day = day;
		}
		public Double getHour() {
			return hour;
		}
		public void setHour(Double hour) {
			this.hour = hour;
		}
		public Double getDayPrice() {
			return dayPrice;
		}
		public void setDayPrice(Double dayPrice) {
			this.dayPrice = dayPrice;
		}
		public Double getHourPrice() {
			return hourPrice;
		}
		public void setHourPrice(Double hourPrice) {
			this.hourPrice = hourPrice;
		}
		public Double getMileage() {
			return mileage;
		}
		public void setMileage(Double mileage) {
			this.mileage = mileage;
		}
		public Double getKm() {
			return km;
		}
		public void setKm(Double km) {
			this.km = km;
		}
		public Double getTotal() {
			return total;
		}
		public void setTotal(Double total) {
			this.total = total;
		}
	}
	
	

}
