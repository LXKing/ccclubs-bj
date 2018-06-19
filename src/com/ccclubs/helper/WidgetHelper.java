package com.ccclubs.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsIntegralRecord;
import com.ccclubs.model.CsRecord;
import com.ccclubs.service.admin.ICsRecordService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonOrderService.DateType;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.$;

public class WidgetHelper {

	/**
	 * 根据会员id ，获取本月获取积分总和
	 * 
	 * @param member
	 * @return
	 */
	public static Double getPointGets(Long member) {
		Double pointsGets = 0d;
		if (member == null||member.equals(0l))
			return pointsGets;
		ICsRecordService csRecordService = Lazy.GetSpringBean("csRecordService");
		// 本月获取，本月消费
		// 获取当前月的第一天
		Calendar calStrat = Calendar.getInstance();
		calStrat.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calStrat.set(Calendar.HOUR_OF_DAY, 0);
		calStrat.set(Calendar.MINUTE, 0);
		calStrat.set(Calendar.SECOND, 0);
		// 获取当前月的最后一天
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.MONTH, 1);// 月增加1
		calEnd.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		
		pointsGets = CsIntegralRecord.getCsIntegralRecordEval("sum(csr_amount)", 
			$.add(CsIntegralRecord.F.csrMember, member)
			.add(CsIntegralRecord.F.csrAddTime + "Start", calStrat.getTime())
			.add(CsIntegralRecord.F.csrAddTime + "End", calEnd.getTime())
			.add(CsIntegralRecord.F.csrAmountMin, 0)
		);
		
		return Math.abs($.or(pointsGets,0d));
	}

	/**
	 * 根据会员id ，获取本月使用积分总和
	 * 
	 * @param member
	 * @return
	 */
	public static Double getPointUsed(Long member) {
		Double pointsUsed = 0d;
		if (member == null||member.equals(0l))
			return pointsUsed;
		ICsRecordService csRecordService = Lazy.GetSpringBean("csRecordService");
		// 本月获取，本月消费
		// 获取当前月的第一天
		Calendar calStrat = Calendar.getInstance();
		calStrat.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calStrat.set(Calendar.HOUR_OF_DAY, 0);
		calStrat.set(Calendar.MINUTE, 0);
		calStrat.set(Calendar.SECOND, 0);
		// 获取当前月的最后一天
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.MONTH, 1);// 月增加1
		calEnd.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		
		pointsUsed = CsIntegralRecord.getCsIntegralRecordEval("sum(csr_amount)", 
			$.add(CsIntegralRecord.F.csrMember, member)
			.add(CsIntegralRecord.F.csrAddTime + "Start", calStrat.getTime())
			.add(CsIntegralRecord.F.csrAddTime + "End", calEnd.getTime())
			.add(CsIntegralRecord.F.csrAmountMax, 0)
		);
		
		return Math.abs($.or(pointsUsed,0d));
	}

	/**
	 * 获取从第二天开始计算，第 num 个工作日的日期
	 * 
	 * @param num
	 * @return
	 */
	public static Date getWorkDay(int num) {
		// 获取从strartTime的第二天开始计算，是第几个工作日
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, 1);

		ICommonOrderService commonOrderService = Lazy.GetSpringBean("commonOrderService");
		Date start = calendar.getTime();
		Date date = calendar.getTime();
		for (int i = 0; i < num;) {
			DateType dateType = commonOrderService.getDateType(start);
			if (dateType.equals(DateType.HoliDay) || dateType.equals(DateType.CeaseDay)) {
				// 如果是放假
				calendar.add(Calendar.DATE, 1);
				start = calendar.getTime();
			} else {
				i++;
				date = start;
				calendar.add(Calendar.DATE, 1);
				start = calendar.getTime();
				continue;
			}
		}
		return date;
	}

	/**
	 * 从指定日期第二天开始，第 num 个工作日
	 * 
	 * @param num
	 * @param startDate
	 * @return
	 */
	public static Date getWorkDay(int num, Date startDate) {
		// 获取从strartTime的第二天开始计算，是第几个工作日
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, 1);

		ICommonOrderService commonOrderService = Lazy.GetSpringBean("commonOrderService");
		Date start = calendar.getTime();
		Date date = calendar.getTime();
		for (int i = 0; i < num;) {
			DateType dateType = commonOrderService.getDateType(start);
			if (dateType.equals(DateType.HoliDay) || dateType.equals(DateType.CeaseDay)) {
				// 如果是放假
				calendar.add(Calendar.DATE, 1);
				start = calendar.getTime();
			} else {
				i++;
				date = start;
				calendar.add(Calendar.DATE, 1);
				start = calendar.getTime();
				continue;
			}
		}
		return date;
	}
}
