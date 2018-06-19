package com.ccclubs.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.EventHelper;
import com.ccclubs.model.*;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.impl.CommonMoneyService;

/**
 * LAZY3Q_LOCK_FLAG
 * 全局触发器
 * @author uiu
 */
public class Trigger{
	
	public static enum Type{SAVE,UPDATE,DELETE}
	public final static Type SAVE = Type.SAVE;
	public final static Type UPDATE = Type.UPDATE;
	public final static Type DELETE = Type.DELETE;

	/**
	 * 触发器统一入口
	 * @param <T>
	 * @param cls 操作的类型
	 * @param type 动作类型
	 * @param object 操作的对象/参数(有可能是Map类型哦)
	 * 如果发生错误，不抛出，尽量避免影响主业务
	 * @return
	 */
	public static <T> T on(final Class cls,final Type type,final T object){		
		Map<String,String> modifys = $.getLocal("modifys");
		if(modifys!=null)modifys.put(cls.getSimpleName(), type.name());		
		new Thread(){
			public void run(){
				execute(cls,type,object);
			}
		}.start();
		return object;
	}
	
	/**
	 * 异步执行触发器
	 * @param cls
	 * @param type
	 * @param object
	 */
	public static void execute(Class cls,Type type,Object object){
		try{
			if(cls==CsOrder.class){
				if(type==Trigger.SAVE && object.getClass()==CsOrder.class){
					CsOrder order = (CsOrder) object;					
					/*******************************更新会员[累计订单][首次用车][最后使用][累计用车]***********************************/					
					CsMember csMember = order.get$csoUseMember();
					CsMember updateMember = new CsMember().csmId(csMember.getCsmId()).csmLastUse(new Date());
					if(csMember.getCsmFirstUse()==null){
						CsOrder firstOrder = CsOrder.getCsOrder($.add(CsOrder.F.csoUseMember, csMember.getCsmId()).add("asc", CsOrder.C.csoAddTime));
						updateMember.csmFirstUse(firstOrder==null ? new Date() :  firstOrder.getCsoAddTime());
					}
					if(csMember.getCsmAllOrderS()==null){
						Long count = CsOrder.getCsOrderCount($.add(CsOrder.F.csoUseMember, csMember.getCsmId()));
						updateMember.setCsmAllOrderS(count);
					}else{
						updateMember.setCsmAllOrderS(csMember.getCsmAllOrderS()+1);
					}
					List<Map> list = $.getDao(SYSTEM.data_source).executeQuery("select sum(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))/3600 as hours from cs_order where cso_use_member=? and cso_status=4", csMember.getCsmId());
					if(!list.isEmpty() && list.get(0).get("hours")!=null){
						updateMember.setCsmAllUseTime(Double.valueOf(list.get(0).get("hours").toString()));
					}
					updateMember.update();	
					/*******************************更新会员[累计订单][首次用车][最后使用][累计用车]***********************************/
					
					EventHelper.postEvent(
						order.getCsoHost(), 
						EventHelper.EventType.会员下单提醒
						,"好开心，有会员("+order.getCsoMobile()+")下单了", order.getCsoId(),CsOrder.class);
					
				}
				if(type==Trigger.UPDATE && object.getClass()==CsOrder.class){
					CsOrder order = (CsOrder) object;
					com.ccclubs.helper.TimeLineHelper.update(order.getCsoId());
				}
				
			}else if(cls==CsMember.class && type==Trigger.SAVE){
				CsMember csMember = (CsMember) object;
				EventHelper.postEvent(
					csMember.getCsmHost(), 
					EventHelper.EventType.新会员注册提醒
					,"好开心，有新会员("+csMember.getCsmMobile()+")注册", csMember.getCsmId(),CsMember.class);
			}else if(cls==CsMemberInfo.class && type==Trigger.UPDATE){
				CsMemberInfo csMemberInfo = (CsMemberInfo) object;
				CsMemberInfo dbMemberInfo = CsMemberInfo.get(csMemberInfo.getCsmiId());
				CsMember csMember = dbMemberInfo.get$csmiMemberId();
				if(dbMemberInfo!=null && csMember!=null){// && csMember.getCsmHost().longValue()==1
					if(csMember.getCsmVDrive().shortValue()!=1 || csMember.getCsmVReal().shortValue()!=1){
						if(!$.empty(dbMemberInfo.getCsmiCertifyImage()) && 				  
						   !$.empty(dbMemberInfo.getCsmiDriverImage()) &&								  
						   !$.empty(dbMemberInfo.getCsmiDriverNum())
						){
							CommonMoneyService.GiveCoinByPerfectInfo(csMember.getCsmId());
						}
					}
				}
			}else if(cls==CsRefund.class){
				if(type==Trigger.SAVE && object.getClass()==CsRefund.class){
					CsRefund csRefund =  (CsRefund) object;
					EventHelper.postEvent(
						csRefund.getCsrHost(),
						EventHelper.EventType.会员退款提醒
						,"有会员("+csRefund.getCsrMember$()+")刚刚提交了退款", csRefund.getCsrId(),CsRefund.class);
				}
			}else if(cls==CsRecord.class){
				if(type==Trigger.SAVE && object.getClass()==CsRecord.class){
					CsRecord csRecord = (CsRecord) object;
					String csrTypeName = csRecord.get$csrRecordSubject().getCsrsName();
					if($.empty(csrTypeName)){
						//什么也不做
					}else if(csrTypeName.indexOf("充值")>-1){
						/*******************************更新会员[首次充值][累计充值]***********************************/
						CsMember csMember = csRecord.get$csrMember();
						CsMember updateMember = new CsMember().csmId(csMember.getCsmId());
						if(updateMember.getCsmFirstRecharge()==null){
							List<Map> list = $.getDao(SYSTEM.data_source).executeQuery("select csr_add_time from cs_record where csr_member=? and csr_type like '%充值%' order by csr_add_time asc limit 1", csMember.getCsmId());
							if(list.isEmpty() || list.get(0).get("csr_add_time")==null)
								updateMember.setCsmFirstRecharge(new Date());
							else{
								updateMember.setCsmFirstRecharge((Date) list.get(0).get("csr_add_time"));
							}
						}
						if(updateMember.getCsmAllRecharge()==null){
							List<Map> list = $.getDao(SYSTEM.data_source).executeQuery("select sum(csr_amount) as amount from cs_record where csr_member=? and csr_type like '%充值%'", csMember.getCsmId());
							if(list.isEmpty() || list.get(0).get("amount")==null)
								updateMember.setCsmAllRecharge(0d);
							else{
								updateMember.setCsmAllRecharge(Double.valueOf(list.get(0).get("amount").toString()));
							}
						}else{
							updateMember.setCsmAllRecharge(updateMember.getCsmAllRecharge()+csRecord.getCsrAmount());
						}
						updateMember.update();
						EventHelper.postEvent(
							csMember.getCsmHost(), 
							EventHelper.EventType.会员充值提醒
							,"好开心,有会员("+csMember.getCsmMobile()+")刚刚充了值", csMember.getCsmId(),CsMember.class);
						/*******************************更新会员[首次充值][累计充值]***********************************/
					}else if(csrTypeName.indexOf("余额退款")>-1){
						/*******************************更新会员[最后退款]***********************************/
						CsMember csMember = csRecord.get$csrMember();
						CsMember updateMember = new CsMember().csmId(csMember.getCsmId()).csmRefundTime(csRecord.getCsrAddTime());						
						updateMember.update();
						/*******************************更新会员[最后退款]***********************************/
					}
				}			
			}else if(cls==CsTrouble.class){
				if(type==Trigger.SAVE && object.getClass()==CsTrouble.class){
					CsTrouble csTrouble = (CsTrouble) object;
					
					/*******************************更新会员[事故次数]***********************************/
					CsMember csMember = csTrouble.get$cstMember();
					CsMember updateMember = new CsMember().csmId(csMember.getCsmId());
					Long count = CsTrouble.getCsTroubleCount($.add(CsTrouble.F.cstMember, csMember.getCsmId()));
					if(count==null)count=0l;
					updateMember.setCsmAllTroubleS($.or(count,0l));
					updateMember.update();
					/*******************************更新会员[事故次数]***********************************/
				}
			}else if(cls==CsViolat.class){
				if(type==Trigger.SAVE && object.getClass()==CsViolat.class){
					CsViolat csViolat = (CsViolat) object;
					
					/*******************************更新会员[违章次数][未处理违章次数]***********************************/
					CsMember csMember = csViolat.get$csvMember();
					CsMember updateMember = new CsMember().csmId(csMember.getCsmId());
					Long count = CsViolat.getCsViolatCount($.add(CsViolat.F.csvMember, csMember.getCsmId()));
					updateMember.setCsmAllViolatS($.or(count,0l));
					Long uncount = CsViolat.getCsViolatCount($.add(CsViolat.F.csvMember, csMember.getCsmId()).add(CsViolat.F.csvStatus,2).add(CsViolat.F.csvStatus+"Not","not"));
					updateMember.setCsmUnViolatS($.or(uncount,0l));		
					updateMember.update();
					/*******************************更新会员[违章次数][未处理违章次数]***********************************/
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
