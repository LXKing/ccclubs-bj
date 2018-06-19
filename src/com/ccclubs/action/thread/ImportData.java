package com.ccclubs.action.thread;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ccclubs.helper.jxl.ExcelOpException;
import com.ccclubs.helper.jxl.ExcelOperator;
import com.ccclubs.helper.jxl.ExcelOperator.ExcelOperatorType;
import com.ccclubs.helper.jxl.ExcelOperator.FileOperatorType;
import com.ccclubs.helper.jxl.ExcelWriteData;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.CsUseRecord;
import com.lazy3q.web.helper.$;

public class ImportData extends Thread{
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File file = new File("D:\\data");
		
		if(!file.exists()){
			file.mkdirs();
		}
		
		Logger.getRootLogger().info("开始导出数据了");
		long previousMemberId = 0l;
		
		List<Object> dateList = new ArrayList<Object>();
		
		ExcelOperator operator = new ExcelOperator();
		operator.setFileOperateType(FileOperatorType.EXCEL);
		operator.setOperateType(ExcelOperatorType.WRITE);
		operator.setFile(new File(file.toString()+"/"+$.date(new Date(), "yyyyMMddHHmmss")+".xls"));
		Object object[] =null;
		while(true){
			Logger.getRootLogger().debug("程序跑呀跑呀");
			CsMember csMember = CsMember.Get($.add("asc", CsMember.C.csmId).add("definex", "csm_id > "+ previousMemberId));
			if(csMember == null){
				Logger.getRootLogger().info("导出数据结束");
				break;
			}
			Logger.getRootLogger().info("当前ID"+csMember.getCsmId() );
			previousMemberId = csMember.getCsmId();
			//先判断此人是否消费
			Long isRecord = CsRecord.getCsRecordCount($.add(CsRecord.F.csrMember, previousMemberId).add("definex",CsRecord.C.csrAddTime +" > '2017-01-01 00:00:00'"));
			
			object = new Object[6];
			
			object[1]=(csMember.getCsmMobile());
			object[0]=(csMember.getCsmId());
			object[2]=(csMember.getCsmName());
			if(isRecord.intValue() > 0){
				Double money = $.or(csMember.getCsmMoney(),0d);
				Double coupon = $.or(csMember.getCsmCoupon(),0d);
				Double recordMoney = CsRecord.getCsRecordEval("sum("+CsRecord.C.csrAmount+")", $.add(CsRecord.F.csrMember, previousMemberId).add("definex",CsRecord.C.csrAddTime +" > '2017-01-01 00:00:00'").add(CsRecord.F.csrMoneyType, 0));
				object[3]=$(money-$.or(recordMoney,0d));
				Double recordCoupon = CsRecord.getCsRecordEval("sum("+CsRecord.C.csrAmount+")", $.add(CsRecord.F.csrMember, previousMemberId).add("definex",CsRecord.C.csrAddTime +" > '2017-01-01 00:00:00'").add(CsRecord.F.csrMoneyType, 1));
				object[4]=$(coupon-$.or(recordCoupon,0d));
				
				
			}else{
				object[3]=(csMember.getCsmMoney());
				object[4]=(csMember.getCsmCoupon());
			}
			
			Long isUseRecord = CsUseRecord.getCsUseRecordCount($.add(CsUseRecord.F.csurMember, csMember.getCsmId()).add("definex",CsUseRecord.C.csurAddTime +" > '2017-01-01 00:00:00'"));

			if(isUseRecord.intValue() > 0){
				Double recordCoin = CsUseRecord.getCsUseRecordEval("sum("+CsUseRecord.C.csurCount+")", $.add(CsUseRecord.F.csurMember, csMember.getCsmId()).add("join", " JOIN cs_coin c on csur_coin = c.csc_id ").add("definex", " c.csc_add_time < '2017-01-01 00:00:00' and csur_add_time > '2017-01-01 00:00:00' "));
				Double cscRemain  = CsCoin.getCsCoinEval("sum("+CsCoin.C.cscRemain+") ", $.add(CsCoin.F.cscMember, csMember.getCsmId()).add("definex",CsCoin.C.cscAddTime +" < '2017-01-01 00:00:00' and "+CsCoin.C.cscEnd+" >'2017-01-01 00:00:00'" ));
				object[5] = $(($.or(cscRemain, 0d))+($.or(recordCoin, 0d)));
			}else{
				Double cscRemain  = CsCoin.getCsCoinEval("sum("+CsCoin.C.cscRemain+") ", $.add(CsCoin.F.cscMember, csMember.getCsmId()).add("definex",CsCoin.C.cscAddTime +" < '2017-01-01 00:00:00' and "+CsCoin.C.cscEnd+" >'2017-01-01 00:00:00'" ));
				object[5]=($.or(cscRemain, 0d));
			}
			
			dateList.add(object);
		}
		ExcelWriteData writeData = new ExcelWriteData();
		writeData.setTitle(new String[]{"ID","手机号","姓名","余额","现金券","红包"});
		writeData.setList(dateList);
		operator.setWriteData(writeData);
		try {
			operator.execute();
		} catch (ExcelOpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("孩子 导出完成");
		Logger.getRootLogger().info("孩子 导出完成");
		
		
		
	}
	
	
	public Double $(Double value) {
		if (value == null)
			return 0d;
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
