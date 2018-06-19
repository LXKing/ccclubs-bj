package com.ccclubs.action.thread;

import java.util.Date;

import com.ccclubs.model.CsCreditBill;
import com.lazy3q.web.helper.$;

public class CreditBillThread extends Thread{
	public void run(){
		while(true){
			if(new Date().getHours()>8){
				int count = 0;
				try{
					count = scanCreditBill();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				if(count>0){
					try{
						Thread.sleep(3000l);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					try{
						Thread.sleep(5000l);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}else{
				try{
					Thread.sleep(30000l);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	private int scanCreditBill() {
		CsCreditBill bill = CsCreditBill.where().cscbStatus((short)0)
		.cscbAddTimeEnd(new Date(new Date().getTime()-$.TIME.HOUR.time*2))
		.cscbProcess("#1#").add("cscbProcessNot", "not").get();
		if(bill==null){
			return 0;
		}
		//new CsCreditBill(bill.getCscbId()).cscbProcess("#1#"+($.empty(bill.getCscbProcess())?(""):(","+bill.getCscbProcess()))).update();
		$.trace("您的用车订单["+bill.getCscbOrder()+"]芝麻信用帐单(金额:{money}元)快要过期，请及时付款，以免对您的信用记录产生影响");
		/*UtilHelper.sendTemplateSms(bill.getCscbHost(),"CREDIT_BILL_TIMEOUT",
			bill.get$cscbOrder().getCsoMobile(),
			"您的用车订单[{order}]芝麻信用帐单(金额:{money}元)快要过期，请及时付款，以免对您的信用记录产生影响", 
			SMSType.通知类短信,
			$.add("order",""+bill.getCscbOrder()).add("money", ""+bill.getCscbValue())
		);*/
		return 1;
	}
}
