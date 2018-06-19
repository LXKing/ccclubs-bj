package com.ccclubs.action.api.chargedot.CMDT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.model.CsChargeBill;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsChargeBillService;
import com.ccclubs.service.admin.ICsMemberService;
import com.lazy3q.web.helper.$;

public class CMDTChargingPileService {
	
	public final static String PROVIDER = "CMDT";
	
	final ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
	final ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
	
	/**
	 * 获取并写入充电订单
	 */
	public void getAndWriteChargingBill(){
		CMDTResp resp = CMDTChargingPileApi.getChargingBills();
		if(resp!=null){
			List<CMDTBill> dataList = resp.getData();
			if(dataList!=null){
				for(CMDTBill data : dataList){
					CsChargeBill bill = csChargeBillService.getCsChargeBill($.add(CsChargeBill.F.cscbProvider, PROVIDER)
							.add(CsChargeBill.F.cscbStatus, "1").add(CsChargeBill.F.cscbSerialnumber, data.getSerialNumber()));
					if(bill!=null)continue;
					
					bill = new CsChargeBill();
					CsEvCard evcard = CsEvCard.getCsEvCard($.add(CsEvCard.F.csecNumber, data.getCardNumber()));
					if(evcard!=null){
						CsMember member = csMemberService.getCsMember($.add(CsMember.F.csmEvcard, evcard.getCsecId()));
						if(member!=null){
							bill.setCscbCustId(member.getCsmId().toString());
						}
					}
					
					bill.setCscbStatus("1");
					bill.setCscbSerialnumber(data.getSerialNumber());
					bill.setCscbCustCardno(data.getCardNumber());
					bill.setCscbPileCode(data.getChargPileNumber());
					bill.setCscbStartEnergy(data.getChargStartEnergy());
					bill.setCscbFinishEnergy(data.getChargEndEnergy());
					bill.setCscbStartTime(new Date(data.getChargBeginTime()));
					bill.setCscbFinishTime(new Date(data.getChargEndTime()));
					bill.setCscbTotalElecfee(data.getChargFee());
					bill.setCscbTotalPower(Double.valueOf(data.getChargAmount()));
					bill.setCscbProvider(PROVIDER);
					bill.setCscbAddTime(new Date());
					bill.setCscbUpdateTime(new Date());
					
					Map<String, Object> extdata = new HashMap<String, Object>();
					extdata.put("cardId", data.getCardId());
					bill.setCscbData($.json(extdata));
					
					bill.save();
				}
			}
		}
	}
	
	
}
