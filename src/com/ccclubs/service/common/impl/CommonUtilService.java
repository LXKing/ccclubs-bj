package com.ccclubs.service.common.impl;

import java.util.Date;
import java.util.Map;

import com.ccclubs.dao.ICsUserTypeDao;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsSmsMt;
import com.ccclubs.model.CsSmsTemplate;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUserType;
import com.ccclubs.service.admin.ICsSmsMtService;
import com.ccclubs.service.admin.ICsSmsTemplateService;
import com.ccclubs.service.common.ICommonUtilService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.Lazy;

public class CommonUtilService implements ICommonUtilService {

	
	ICsSmsTemplateService csSmsTemplateService;	
	ICsSmsMtService csSmsMtService;
	ICsUserTypeDao csUserTypeDao;
	
	/**
	 * 发送模版短信
	 * @param flag 标识 
	 * @param mobile 手机号码
	 * @param params 参数列表
	 */
	public Boolean sendTemplateSms(final Long host,final String flag,final  String mobile,final String defaultTemplate,final  SMSType smsType,final Map<String,String> params) {
		
		new Thread(){
			public void run(){
				CsSmsTemplate csSmsTemplate = CsSmsTemplate.where().csstFlag(flag).csstHost(host).get();
				if(csSmsTemplate==null)
					csSmsTemplate = CsSmsTemplate.where().csstFlag(flag).csstHost(0l).get();
				if(csSmsTemplate==null){
					csSmsTemplate = csSmsTemplateService.saveCsSmsTemplate(
						new CsSmsTemplate()
						.csstFlag(flag)
						.setNotNull(0l,"未命名模版", defaultTemplate, (long)smsType.ordinal(), new Date(), new Date(), (short)1)
					);
				}
				String csstContent = csSmsTemplate.getCsstContent();				
				for (Map.Entry<String, String> entry : params.entrySet()) {  
					csstContent = csstContent.replace("{"+entry.getKey()+"}", entry.getValue()==null ? "" : entry.getValue());
		        }
				csSmsMtService.saveCsSmsMt(
						new CsSmsMt()
						.cssmHost(host==null?0l:host)
						.cssmAddTime(new Date())
						.cssmUpdateTime(new Date())
						.cssmContent(csstContent)
						.cssmType(csSmsTemplate.getCsstType().shortValue())
						.cssmEditor(LoginHelper.getLoginId())
						.cssmMobile(mobile)
						.cssmStatus((short) 1)
				);
			}			
		}.start();
		
		return true;
	}
	/**
	 * 发送普通短信 
	 * @param mobile 手机号码
	 * @param content 短信内容
	 */
	public void sendSms(final Long host,final String mobile,final  String content,final  SMSType smsType) {
		
		new Thread(){
			public void run(){		
				csSmsMtService.saveCsSmsMt(
						new CsSmsMt()
						.cssmHost(host==null?0l:host)
						.cssmAddTime(new Date())
						.cssmContent(content)
						.cssmType((short)(smsType.ordinal()+1))
						.cssmEditor(LoginHelper.getLoginId())
						.cssmMobile(mobile)
						.cssmStatus((short) 1)
				);
			}			
		}.start();
	}
	
	/**
	 * 根据标识获取会员类型
	 */
	public CsUserType getUserType(String strFlag) {
		return csUserTypeDao.getCsUserType(Lazy.add("csutFlag", strFlag));
	}
	
	public ICsSmsMtService getCsSmsMtService() {
		return csSmsMtService;
	}

	public void setCsSmsMtService(ICsSmsMtService csSmsMtService) {
		this.csSmsMtService = csSmsMtService;
	}



	public ICsSmsTemplateService getCsSmsTemplateService() {
		return csSmsTemplateService;
	}

	public void setCsSmsTemplateService(ICsSmsTemplateService csSmsTemplateService) {
		this.csSmsTemplateService = csSmsTemplateService;
	}	
	public ICsUserTypeDao getCsUserTypeDao() {
		return csUserTypeDao;
	}
	public void setCsUserTypeDao(ICsUserTypeDao csUserTypeDao) {
		this.csUserTypeDao = csUserTypeDao;
	}

}
