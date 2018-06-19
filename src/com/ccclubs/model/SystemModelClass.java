package com.ccclubs.model;

import com.lazy3q.lang.caption;
import com.lazy3q.lang.namespace;
import com.lazy3q.web.helper.$;

public enum SystemModelClass{
	CsLongOrderFee(CsLongOrderFee.class),
	CsLongOrderDetail(CsLongOrderDetail.class),
	CsLongOrder(CsLongOrder.class),
	CsSpecialModel(CsSpecialModel.class),
	CsSpecialCar(CsSpecialCar.class),
	CsUnitMt(CsUnitMt.class),
	CsPlace(CsPlace.class),
	CsUnitPerson(CsUnitPerson.class),
	CsUnitGroup(CsUnitGroup.class),
	TbPassCheck(TbPassCheck.class),
	TbEmployee(TbEmployee.class),
	TbDepartment(TbDepartment.class),
	CsCashRecord(CsCashRecord.class),
	SrvHelp(SrvHelp.class),
	CsShopOrderItem(CsShopOrderItem.class),
	CsShopOrder(CsShopOrder.class),
	CsCartItem(CsCartItem.class),
	CsGrowType(CsGrowType.class),
	TbSaleContract(TbSaleContract.class),
	TbSaleQuote(TbSaleQuote.class),
	TbSaleCustom(TbSaleCustom.class),
	TbCustomGroup(TbCustomGroup.class),
	TbSaleBattle(TbSaleBattle.class),
	CsStrategy(CsStrategy.class),
	SrvLock(SrvLock.class),
	SrvTransaction(SrvTransaction.class),
	TbAbAffirm(TbAbAffirm.class),
	TbSolve(TbSolve.class),
	CsScheduling(CsScheduling.class),
	CsOper(CsOper.class),
	CsOperRecord(CsOperRecord.class),
	CsArtifRecord(CsArtifRecord.class),
	CsRecordSubject(CsRecordSubject.class),
	TbProblem(TbProblem.class),
	CsElecHistory(CsElecHistory.class),
	CsHistoryState(CsHistoryState.class),
	CsOutsideQr(CsOutsideQr.class),
	CsUnitBill(CsUnitBill.class),
	CsUnitAirport(CsUnitAirport.class),
	CsUnitFee(CsUnitFee.class),
	CsUnitOrder(CsUnitOrder.class),
	CsUnitUser(CsUnitUser.class),
	CsUnitInfo(CsUnitInfo.class),
	CsContent(CsContent.class),
	CsState(CsState.class),
	CsAlarm(CsAlarm.class),
	CsRemote(CsRemote.class),
	CsUpdate(CsUpdate.class),
	CsMaintain(CsMaintain.class),
	CsRepairAdr(CsRepairAdr.class),
	CsRepair(CsRepair.class),
	CsOrderLog(CsOrderLog.class),
	CsOrderDetail(CsOrderDetail.class),
	CsOrder(CsOrder.class),
	CsViolat(CsViolat.class),
	CsTrouble(CsTrouble.class),
	CsCarOn(CsCarOn.class),
	CsProvid(CsProvid.class),
	CsRechargeCard(CsRechargeCard.class),
	CsEvCard(CsEvCard.class),
	CsPowerPile(CsPowerPile.class),
	CsOutlets(CsOutlets.class),
	CsCar(CsCar.class),
	CsCarModel(CsCarModel.class),
	CsItem(CsItem.class),
	CsPackInfo(CsPackInfo.class),
	CsPack(CsPack.class),
	CsPrice(CsPrice.class),
	CsGoods(CsGoods.class),
	CsProduct(CsProduct.class),
	CsRule(CsRule.class),
	CsCreditBill(CsCreditBill.class),
	CsCreditRecord(CsCreditRecord.class),
	CsCreditCard(CsCreditCard.class),
	CsAlipayLog(CsAlipayLog.class),
	CsAlipayRefund(CsAlipayRefund.class),
	CsAlipayRecord(CsAlipayRecord.class),
	CsInvoice(CsInvoice.class),
	CsRecord(CsRecord.class),
	CsRefund(CsRefund.class),
	CsQuestAnswer(CsQuestAnswer.class),
	CsQuestTemplate(CsQuestTemplate.class),
	CsGrowRecord(CsGrowRecord.class),
	CsIntegralRecord(CsIntegralRecord.class),
	CsUseRecord(CsUseRecord.class),
	CsCoin(CsCoin.class),
	CsFreeHour(CsFreeHour.class),
	CsUserPack(CsUserPack.class),
	CsGift(CsGift.class),
	CsUserType(CsUserType.class),
	CsMemberMemo(CsMemberMemo.class),
	CsMessage(CsMessage.class),
	CsAddress(CsAddress.class),
	CsMemberGroup(CsMemberGroup.class),
	CsMemberShip(CsMemberShip.class),
	CsMemberInfo(CsMemberInfo.class),
	CsMember(CsMember.class),
	CsChatMessage(CsChatMessage.class),
	CsChatTalker(CsChatTalker.class),
	CsCallIn(CsCallIn.class),
	CsServe(CsServe.class),
	CsCare(CsCare.class),
	CsRevisit(CsRevisit.class),
	CsComplain(CsComplain.class),
	CsPropagate(CsPropagate.class),
	CsVisitIp(CsVisitIp.class),
	CsVisitDetail(CsVisitDetail.class),
	CsChannelIndex(CsChannelIndex.class),
	CsChannelLog(CsChannelLog.class),
	CsChannel(CsChannel.class),
	CsLuckyResult(CsLuckyResult.class),
	CsLuckyItem(CsLuckyItem.class),
	CsLucky(CsLucky.class),
	CsPromotion(CsPromotion.class),
	CsActivityIn(CsActivityIn.class),
	CsActivity(CsActivity.class),
	CsChjSign(CsChjSign.class),
	CsShareIndex(CsShareIndex.class),
	CsPresent(CsPresent.class),
	CsMarketIndex(CsMarketIndex.class),
	CsMarketLog(CsMarketLog.class),
	CsMarketPlan(CsMarketPlan.class),
	CsSaleOpport(CsSaleOpport.class),
	CsSaleSurvey(CsSaleSurvey.class),
	CsSaleEvent(CsSaleEvent.class),
	CsWxMo(CsWxMo.class),
	CsWxMt(CsWxMt.class),
	CsWxContent(CsWxContent.class),
	CsWxMenu(CsWxMenu.class),
	CsWxOrder(CsWxOrder.class),
	CsCarPhoto(CsCarPhoto.class),
	CsUpgradeLog(CsUpgradeLog.class),
	CsBug(CsBug.class),
	CsError(CsError.class),
	CsEventHeed(CsEventHeed.class),
	CsEvent(CsEvent.class),
	CsEventType(CsEventType.class),
	CsTodo(CsTodo.class),
	CsNotice(CsNotice.class),
	CsLog(CsLog.class),
	CsEmail(CsEmail.class),
	CsSmsMt(CsSmsMt.class),
	SrvOauth(SrvOauth.class),
	SrvApiLog(SrvApiLog.class),
	CsGasStation(CsGasStation.class),
	CsLimitTime(CsLimitTime.class),
	CsArea(CsArea.class),
	CsTask(CsTask.class),
	CsConfig(CsConfig.class),
	CsArgument(CsArgument.class),
	CsFeeTypeSet(CsFeeTypeSet.class),
	CsIntegralRule(CsIntegralRule.class),
	CsSmsTemplate(CsSmsTemplate.class),
	CsHoliday(CsHoliday.class),
	CsRecordType(CsRecordType.class),
	SrvApiClient(SrvApiClient.class),
	SrvApi(SrvApi.class),
	SrvActionScript(SrvActionScript.class),
	SrvQuery(SrvQuery.class),
	SrvProperty(SrvProperty.class),
	SrvModel(SrvModel.class),
	SrvWorkflow(SrvWorkflow.class),
	SrvHost(SrvHost.class),
	SrvUserExp(SrvUserExp.class),
	SrvUser(SrvUser.class),
	SrvGroup(SrvGroup.class),
	SrvProject(SrvProject.class)
	;private Class value;
	private SystemModelClass(Class value) {
		this.value = value;
	}
	public String getName(){
		return this.name();
	}
	public Class getValue(){
		return this.value;
	}
	public String getCaption(){
		caption caption = (caption) this.value.getAnnotation(caption.class);
		return caption==null?this.name():caption.value();
	}
	public String getNamespace(){
		namespace namespace = (namespace) this.value.getAnnotation(namespace.class);
		return namespace.value();
	}
	public static SystemModelClass[] getModels(String modelNames){
		if($.empty(modelNames))
			return SystemModelClass.values();
		String[] names = modelNames.split(",");
		SystemModelClass[] models = new SystemModelClass[names.length];
		for(int i=0;i<names.length;i++){
			models[i]=SystemModelClass.valueOf(names[i]);
		}
		return models;
	}
	public static SystemModelClass getModel(String modelName){
		return SystemModelClass.valueOf(modelName);
	}
	public static <T> T getGenericObject(String classAtValue){
		if($.empty(classAtValue))
 			return null;
 		try{
	 		String ClassName = classAtValue.split("@")[0];
	 		Long id = Long.parseLong(classAtValue.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T) cls.getMethod("get", Long.class).invoke(null, id);
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	public static String getGenericValue(String classAtValue){
 		try{	
 			Object object = getGenericObject(classAtValue);
 			if(object==null)return null;
 			String strValue="";
	 		caption caption = object.getClass().getAnnotation(caption.class);
	 		if(caption!=null)
	 			strValue+="["+caption.value()+"]";
	 		try {
				strValue+=object.getClass().getMethod("getKeyValue").invoke(object);
				return strValue;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
}
