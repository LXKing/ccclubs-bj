package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsQuestTemplateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsQuestTemplate;
import com.ccclubs.service.admin.ICsQuestTemplateService;
import com.lazy3q.web.helper.$;

/**
 * 问卷模板的Service实现
 * @author 飞啊飘啊
 */
public class CsQuestTemplateService implements ICsQuestTemplateService
{
	ICsQuestTemplateDao csQuestTemplateDao;
	

	/**
	 * 获取所有问卷模板
	 * @return
	 */
	public List<CsQuestTemplate> getCsQuestTemplateList(Map params,Integer size)
	{
		return csQuestTemplateDao.getCsQuestTemplateList(params,size);
	}
	
	/**
	 * 获取问卷模板统计
	 * @return
	 */
	public List getCsQuestTemplateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csQuestTemplateDao.getCsQuestTemplateStats(params,groups,sums);
	}
	
	/**
	 * 获取问卷模板总数
	 * @return
	 */
	public Long getCsQuestTemplateCount(Map params)
	{
		return csQuestTemplateDao.getCsQuestTemplateCount(params);
	}
	
	/**
	 * 获取问卷模板自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsQuestTemplateEval(String eval,Map params)
	{
		return csQuestTemplateDao.getCsQuestTemplateEval(eval,params);
	}

	/**
	 * 获取问卷模板分页
	 * @return
	 */
	public Page getCsQuestTemplatePage(int page,int size,Map params)
	{
		return csQuestTemplateDao.getCsQuestTemplatePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取问卷模板
	 * @param params
	 * @return
	 */
	public CsQuestTemplate getCsQuestTemplate(Map params)
	{
		return csQuestTemplateDao.getCsQuestTemplate(params);
	}

	/**
	 * 根据ID取问卷模板
	 * @param id
	 * @return
	 */
	public CsQuestTemplate getCsQuestTemplateById(Long id)
	{
		return csQuestTemplateDao.getCsQuestTemplateById(id);
	}

	/**
	 * 保存问卷模板
	 * @param csQuestTemplate
	 */
	public CsQuestTemplate saveCsQuestTemplate(CsQuestTemplate csQuestTemplate)
	{
		return csQuestTemplateDao.saveCsQuestTemplate(csQuestTemplate);
	}
	
	/**
	 * 更新问卷模板
	 * @param csQuestTemplate
	 */
	public void updateCsQuestTemplate(CsQuestTemplate csQuestTemplate)
	{
		csQuestTemplateDao.updateCsQuestTemplate(csQuestTemplate);
	}
	/**
	 * 更新问卷模板非空字段
	 * @param csQuestTemplate
	 */
	public void updateCsQuestTemplate$NotNull(CsQuestTemplate csQuestTemplate)
	{
		csQuestTemplateDao.updateCsQuestTemplate$NotNull(csQuestTemplate);
	}
	
	/**
	 * 根据ID删除一个问卷模板
	 * @param id
	 */
	public void deleteCsQuestTemplateById(Long id)
	{
		csQuestTemplateDao.deleteCsQuestTemplateById(id);
	}
	/**
	 * 根据ID逻辑删除一个问卷模板
	 * @param id
	 */
	public void removeCsQuestTemplateById(Long id)
	{
		csQuestTemplateDao.removeCsQuestTemplateById(id);
	}
	
	/**
	 * 根据条件更新问卷模板
	 * @param values
	 * @param params
	 */
	public void updateCsQuestTemplateByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsQuestTemplateBy时无条件");
		csQuestTemplateDao.updateCsQuestTemplateByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除问卷模板
	 * @param params
	 */
	public void deleteCsQuestTemplateByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsQuestTemplateBy时无条件");
		csQuestTemplateDao.deleteCsQuestTemplateByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsQuestTemplateDao getCsQuestTemplateDao()
	{
		return csQuestTemplateDao;
	}

	public void setCsQuestTemplateDao(ICsQuestTemplateDao csQuestTemplateDao)
	{
		this.csQuestTemplateDao = csQuestTemplateDao;
	}
}