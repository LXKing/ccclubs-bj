package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSaleSurveyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleSurvey;
import com.ccclubs.service.admin.ICsSaleSurveyService;
import com.lazy3q.web.helper.$;

/**
 * 市场调查的Service实现
 * @author 飞啊飘啊
 */
public class CsSaleSurveyService implements ICsSaleSurveyService
{
	ICsSaleSurveyDao csSaleSurveyDao;
	

	/**
	 * 获取所有市场调查
	 * @return
	 */
	public List<CsSaleSurvey> getCsSaleSurveyList(Map params,Integer size)
	{
		return csSaleSurveyDao.getCsSaleSurveyList(params,size);
	}
	
	/**
	 * 获取市场调查统计
	 * @return
	 */
	public List getCsSaleSurveyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSaleSurveyDao.getCsSaleSurveyStats(params,groups,sums);
	}
	
	/**
	 * 获取市场调查总数
	 * @return
	 */
	public Long getCsSaleSurveyCount(Map params)
	{
		return csSaleSurveyDao.getCsSaleSurveyCount(params);
	}
	
	/**
	 * 获取市场调查自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSaleSurveyEval(String eval,Map params)
	{
		return csSaleSurveyDao.getCsSaleSurveyEval(eval,params);
	}

	/**
	 * 获取市场调查分页
	 * @return
	 */
	public Page getCsSaleSurveyPage(int page,int size,Map params)
	{
		return csSaleSurveyDao.getCsSaleSurveyPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取市场调查
	 * @param params
	 * @return
	 */
	public CsSaleSurvey getCsSaleSurvey(Map params)
	{
		return csSaleSurveyDao.getCsSaleSurvey(params);
	}

	/**
	 * 根据ID取市场调查
	 * @param id
	 * @return
	 */
	public CsSaleSurvey getCsSaleSurveyById(Long id)
	{
		return csSaleSurveyDao.getCsSaleSurveyById(id);
	}

	/**
	 * 保存市场调查
	 * @param csSaleSurvey
	 */
	public CsSaleSurvey saveCsSaleSurvey(CsSaleSurvey csSaleSurvey)
	{
		return csSaleSurveyDao.saveCsSaleSurvey(csSaleSurvey);
	}
	
	/**
	 * 更新市场调查
	 * @param csSaleSurvey
	 */
	public void updateCsSaleSurvey(CsSaleSurvey csSaleSurvey)
	{
		csSaleSurveyDao.updateCsSaleSurvey(csSaleSurvey);
	}
	/**
	 * 更新市场调查非空字段
	 * @param csSaleSurvey
	 */
	public void updateCsSaleSurvey$NotNull(CsSaleSurvey csSaleSurvey)
	{
		csSaleSurveyDao.updateCsSaleSurvey$NotNull(csSaleSurvey);
	}
	
	/**
	 * 根据ID删除一个市场调查
	 * @param id
	 */
	public void deleteCsSaleSurveyById(Long id)
	{
		csSaleSurveyDao.deleteCsSaleSurveyById(id);
	}
	/**
	 * 根据ID逻辑删除一个市场调查
	 * @param id
	 */
	public void removeCsSaleSurveyById(Long id)
	{
		csSaleSurveyDao.removeCsSaleSurveyById(id);
	}
	
	/**
	 * 根据条件更新市场调查
	 * @param values
	 * @param params
	 */
	public void updateCsSaleSurveyByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSaleSurveyBy时无条件");
		csSaleSurveyDao.updateCsSaleSurveyByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除市场调查
	 * @param params
	 */
	public void deleteCsSaleSurveyByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSaleSurveyBy时无条件");
		csSaleSurveyDao.deleteCsSaleSurveyByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSaleSurveyDao getCsSaleSurveyDao()
	{
		return csSaleSurveyDao;
	}

	public void setCsSaleSurveyDao(ICsSaleSurveyDao csSaleSurveyDao)
	{
		this.csSaleSurveyDao = csSaleSurveyDao;
	}
}