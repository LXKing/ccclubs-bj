package com.ccclubs.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsOrder;
import com.lazy3q.lang.column;
import com.lazy3q.lang.primary;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class BatchOrderAdder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		StringBuffer sqls=new StringBuffer();
		List<String> errs=new ArrayList();
		
		String[][] data1={
				
		};
		
		sqls.append("/********************情人节体验用车********************/");
		sqls.append("\n");
		for(String[] infos:data1){
			try {
				sqls.append(getSql(
					infos[2],
					infos[1],
					infos[0],
					$.date("2015-02-08 08:00:00", "yyyy-MM-dd HH:mm:ss"),
					$.date("2015-02-15 20:00:00", "yyyy-MM-dd HH:mm:ss"),
					"情人节体验用车"
				));
				sqls.append(";\n");
			} catch (Exception e) {
				errs.add(e.getMessage());
			}
		}
		
		
		
		String[][] data2={
				{"QX1T53","李雁鹏","13811251532"}
		};
		
		sqls.append("/********************春节体验用车********************/");
		sqls.append("\n");
		for(String[] infos:data2){
			try {
				sqls.append(getSql(
					infos[2],
					infos[1],
					infos[0],
					$.date("2015-02-16 08:00:00", "yyyy-MM-dd HH:mm:ss"),
					$.date("2015-02-24 20:00:00", "yyyy-MM-dd HH:mm:ss"),
					"春节体验用车"
				));
				sqls.append(";\n");
			} catch (Exception e) {
				errs.add(e.getMessage());
			}
		}
		
		$.writeFile("f://体验用车.sql", sqls.toString(), $.UTF8);
		
		String strError="";
		for(String error:errs)
			strError+=error+"\n";
		$.writeFile("f://失败.txt", strError, $.UTF8);
		
		$.trace("完成");
		
	}
	
	/**
	 * 添加订单
	 * @param mobile
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public static String getSql(String mobile,String name,String number,Date start,Date end,String csoRemark) throws Exception{
		
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		List<Map> list = dao.executeQuery("select * from cs_member where csm_mobile=? and csm_name like ?", mobile,"%"+name+"%");
		if(list.isEmpty())throw new Exception("无法根据手机号码和姓名找到该会员 "+mobile+" - "+name);
		Map member = list.get(0);
		list = dao.executeQuery("select * from cs_car where csc_number like ?", "%"+number);
		if(list.isEmpty())throw new Exception("车辆"+number+"不存在 "+mobile+" - "+name);
		Map car = list.get(0);
		list = dao.executeQuery("select * from cs_outlets where cso_id=?", car.get("csc_outlets"));
		Map outlets = list.get(0);
		
		CsOrder order = new CsOrder(
		 	2l//城市 [非空]
		 	,(Long)member.get("csm_id")//支付会员 [非空]
		 	,(Long)member.get("csm_id")//使用会员 [非空]
		 	,7l//计费类型 [非空]
		 	,(Long)outlets.get("cso_area")//网点区域 [非空]
		 	,(Long)outlets.get("cso_id")//取车网点 [非空]
		 	,(Long)outlets.get("cso_id")//还车网点 [非空]
		 	,(Long)car.get("csc_model")//预订车型 [非空]
		 	,(Long)car.get("csc_id")//预订车辆 [非空]
		 	,7l//车辆商家 [非空]
		 	,(String)car.get("csc_number")//车牌号码 [非空]
		 	,mobile//手机号码
		 	,"00000000"//EV序列号 [非空]
		 	,false//宣传车？
		 	,start//订单开始时间 [非空]
		 	,end//订单结束时间 [非空]
		 	,false//是否长单
		 	,null//长单价格
		 	,(short)0//用车方式
		 	,start//实际取车时间
		 	,end//实际还车时间
		 	,0d //订单时长
		 	,0d//燃油里程 [非空]
		 	,0d//电里程 [非空]
		 	,null//使用信用卡
		 	,null//信用卡帐单
		 	,11l//保险类型
		 	,0d//使用免费小时
		 	,1d//折扣
		 	,0d//押金/保证金
		 	,0d//预计/里程费
		 	,0d//应付金额
		 	,0d//实付金额
		 	,0d//保证金支付
		 	,0d//现金券支付
		 	,0d//红包支付
		 	,0d//电里程费
		 	,0d//油里程费
		 	,0d//租金费用
		 	,0d//免责费用
		 	,0d//超时费用
		 	,0d//陪驾费用
		 	,(short)1
		 	,1
		 	,(short)10//类型标识
		 	,(short)0//订单来源 [非空]
		 	,null
		 	,start//修改时间 [非空]
		 	,start//添加时间 [非空]
		 	,end//结束时间
		 	,null
		 	,"000000"//授权码 [非空]
		 	,csoRemark//订单备注
		 	,""//历史日志
		 	,0l//操作人 [非空]
		 	,0l//程序掩码 [非空]
		 	,(short)0//下位机版本
		 	,(short)0
		 	,(short)2//数据状态 [非空]
		 	,(short)4//状态 [非空]
		);
		
		String sql="insert into cs_order({columns}) values({values})";
		
		Field[] fields = CsOrder.class.getDeclaredFields();
		String strColumns="";
		String strValues="";
		for(Field field:fields){
			field.setAccessible(true);
			column column = field.getAnnotation(column.class);
			if(column==null)continue;
			if(field.getAnnotation(primary.class)!=null)continue;
			if(!strValues.equals("")){
				strValues+=",";
				strColumns+=",";
			}
			Class<?> type = field.getType();
			Object value=null;
			try {
				value = field.get(order);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			strColumns+=column.value();
			if(value==null)
				strValues+=null;
			else if(type==String.class)
				strValues+="'"+value+"'";
			else if(type==Date.class)
				strValues+="'"+$.date((Date)value, "yyyy-MM-dd HH:mm:ss")+"'";
			else
				strValues+=value;
		}
		
		sql=sql.replace("{columns}", strColumns).replace("{values}", strValues);
		
		return sql;
	}

}
