package com.ccclubs.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.SrvTransaction;
import com.lazy3q.web.helper.$;

public class CcclubsTransactionInterceptor extends TransactionInterceptor {
	
	private static Boolean transactionKeep = false;
	private static Date last=null;
	private static Boolean getTransactionKeep(){
		if(last==null || (new Date().getTime()-last.getTime())>$.TIME.MINUTE.time*10){
			last = new Date();
			transactionKeep = SYSTEM.getArgumentValue("是否记录事务日志", "transactionKeep", false);
		}
		return transactionKeep;
	}

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		if(getTransactionKeep()){
			if("executeTransaction".equals(arg0.getMethod().getName())){
				$.setLocal("executeTransactionObject",arg0.getArguments()[0].getClass().getName());
			}
		}
		return super.invoke(arg0);
	}

	@Override
	protected TransactionInfo prepareTransactionInfo(TransactionAttribute txAttr, String joinpointIdentification,TransactionStatus status) {
		if(getTransactionKeep()){
			String[] array = joinpointIdentification.split("\\.");
			String m = array[array.length-1];
			if(m.startsWith("update")||m.startsWith("save")||m.startsWith("execute")||m.startsWith("remove")||m.startsWith("delete")){
				List<String> points=$.getLocal("points");
				Map<String,Boolean> modifys=$.getLocal("modifys");
				if(status.isNewTransaction()){
					if(points==null)points=new ArrayList();
					if(modifys==null)modifys = new HashMap();
					points.clear();
					modifys.clear();
					String executeTransactionObject = $.getLocal("executeTransactionObject");
					if(!$.empty(executeTransactionObject))
						points.add(executeTransactionObject);
					else
						points.add(joinpointIdentification);
				}				
				if(!(points.size()>0 && points.get(points.size()-1).equals(m)))
					points.add(m);
				if(status.isNewTransaction()){
					$.setLocal("points", points);
					$.setLocal("modifys", modifys);
				}
			}
		}
		return super.prepareTransactionInfo(txAttr, joinpointIdentification, status);
	}

	@Override
	protected void commitTransactionAfterReturning(TransactionInfo txInfo) {
		if(getTransactionKeep()){
			String[] array = txInfo.getJoinpointIdentification().split("\\.");
			String m = array[array.length-1];
			if(m.startsWith("update")||m.startsWith("save")||m.startsWith("execute")||m.startsWith("remove")||m.startsWith("delete")){
				if(txInfo.getTransactionStatus().isNewTransaction()){
					List<String> points=$.getLocal("points");
					Map<String,String> modifys = $.getLocal("modifys");
					if(modifys!=null && modifys.size()>1){
						final String entryPoint = points.remove(0);
						final String callDetails = $.json(points);
						final String modifyModels = $.json(modifys);
						if(callDetails.indexOf("SrvTransaction")==-1 && modifyModels.indexOf("SrvTransaction")==-1){
							new Thread(){
								public void run(){
									SrvTransaction oldTransaction = SrvTransaction.where().stEntry(entryPoint).get();
									if(oldTransaction==null){
										new SrvTransaction(
											entryPoint//调用入口 [非空]
										 	,callDetails//调用详情 [非空]
										 	,modifyModels//修改的对象列表
										).save();
									}else if(!$.equals(modifyModels, oldTransaction.getStModels()) || !$.equals(callDetails, oldTransaction.getStDetails())){
										oldTransaction.stModels(modifyModels).stDetails(callDetails).update();
									}
								}					
							}.start();
						}
					}
					$.setLocal("points", null);
					$.setLocal("modifys", null);
				}
			}
			$.setLocal("executeTransactionObject", null);
		}
		super.commitTransactionAfterReturning(txInfo);
	}

}
