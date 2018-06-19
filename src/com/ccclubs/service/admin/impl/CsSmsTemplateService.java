package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSmsTemplateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSmsTemplate;
import com.ccclubs.service.admin.ICsSmsTemplateService;
import com.lazy3q.web.helper.$;

/**
 * 短信模版的Service实现
 * @author 飞啊飘啊
 */
public class CsSmsTemplateService implements ICsSmsTemplateService
{
	ICsSmsTemplateDao csSmsTemplateDao;
	

	/**
	 * 获取所有短信模版
	 * @return
	 */
	public List<CsSmsTemplate> getCsSmsTemplateList(Map params,Integer size)
	{
		return csSmsTemplateDao.getCsSmsTemplateList(params,size);
	}
	
	/**
	 * 获取短信模版统计
	 * @return
	 */
	public List getCsSmsTemplateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSmsTemplateDao.getCsSmsTemplateStats(params,groups,sums);
	}
	
	/**
	 * 获取短信模版总数
	 * @return
	 */
	public Long getCsSmsTemplateCount(Map params)
	{
		return csSmsTemplateDao.getCsSmsTemplateCount(params);
	}
	
	/**
	 * 获取短信模版自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSmsTemplateEval(String eval,Map params)
	{
		return csSmsTemplateDao.getCsSmsTemplateEval(eval,params);
	}

	/**
	 * 获取短信模版分页
	 * @return
	 */
	public Page getCsSmsTemplatePage(int page,int size,Map params)
	{
		return csSmsTemplateDao.getCsSmsTemplatePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取短信模版
	 * @param params
	 * @return
	 */
	public CsSmsTemplate getCsSmsTemplate(Map params)
	{
		return csSmsTemplateDao.getCsSmsTemplate(params);
	}

	/**
	 * 根据ID取短信模版
	 * @param id
	 * @return
	 */
	public CsSmsTemplate getCsSmsTemplateById(Long id)
	{
		return csSmsTemplateDao.getCsSmsTemplateById(id);
	}

	/**
	 * 保存短信模版
	 * @param csSmsTemplate
	 */
	public CsSmsTemplate saveCsSmsTemplate(CsSmsTemplate csSmsTemplate)
	{
		return csSmsTemplateDao.saveCsSmsTemplate(csSmsTemplate);
	}
	
	/**
	 * 更新短信模版
	 * @param csSmsTemplate
	 */
	public void updateCsSmsTemplate(CsSmsTemplate csSmsTemplate)
	{
		csSmsTemplateDao.updateCsSmsTemplate(csSmsTemplate);
	}
	/**
	 * 更新短信模版非空字段
	 * @param csSmsTemplate
	 */
	public void updateCsSmsTemplate$NotNull(CsSmsTemplate csSmsTemplate)
	{
		csSmsTemplateDao.updateCsSmsTemplate$NotNull(csSmsTemplate);
	}
	
	/**
	 * 根据ID删除一个短信模版
	 * @param id
	 */
	public void deleteCsSmsTemplateById(Long id)
	{
		csSmsTemplateDao.deleteCsSmsTemplateById(id);
	}
	/**
	 * 根据ID逻辑删除一个短信模版
	 * @param id
	 */
	public void removeCsSmsTemplateById(Long id)
	{
		csSmsTemplateDao.removeCsSmsTemplateById(id);
	}
	
	/**
	 * 根据条件更新短信模版
	 * @param values
	 * @param params
	 */
	public void updateCsSmsTemplateByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSmsTemplateBy时无条件");
		csSmsTemplateDao.updateCsSmsTemplateByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除短信模版
	 * @param params
	 */
	public void deleteCsSmsTemplateByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSmsTemplateBy时无条件");
		csSmsTemplateDao.deleteCsSmsTemplateByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSmsTemplateDao getCsSmsTemplateDao()
	{
		return csSmsTemplateDao;
	}

	public void setCsSmsTemplateDao(ICsSmsTemplateDao csSmsTemplateDao)
	{
		this.csSmsTemplateDao = csSmsTemplateDao;
	}
}