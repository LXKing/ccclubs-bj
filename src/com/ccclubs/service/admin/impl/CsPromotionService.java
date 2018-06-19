package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPromotionDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPromotion;
import com.ccclubs.service.admin.ICsPromotionService;
import com.lazy3q.web.helper.$;

/**
 * 促销配置的Service实现
 * @author 飞啊飘啊
 */
public class CsPromotionService implements ICsPromotionService
{
	ICsPromotionDao csPromotionDao;
	

	/**
	 * 获取所有促销配置
	 * @return
	 */
	public List<CsPromotion> getCsPromotionList(Map params,Integer size)
	{
		return csPromotionDao.getCsPromotionList(params,size);
	}
	
	/**
	 * 获取促销配置统计
	 * @return
	 */
	public List getCsPromotionStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPromotionDao.getCsPromotionStats(params,groups,sums);
	}
	
	/**
	 * 获取促销配置总数
	 * @return
	 */
	public Long getCsPromotionCount(Map params)
	{
		return csPromotionDao.getCsPromotionCount(params);
	}
	
	/**
	 * 获取促销配置自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPromotionEval(String eval,Map params)
	{
		return csPromotionDao.getCsPromotionEval(eval,params);
	}

	/**
	 * 获取促销配置分页
	 * @return
	 */
	public Page getCsPromotionPage(int page,int size,Map params)
	{
		return csPromotionDao.getCsPromotionPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取促销配置
	 * @param params
	 * @return
	 */
	public CsPromotion getCsPromotion(Map params)
	{
		return csPromotionDao.getCsPromotion(params);
	}

	/**
	 * 根据ID取促销配置
	 * @param id
	 * @return
	 */
	public CsPromotion getCsPromotionById(Long id)
	{
		return csPromotionDao.getCsPromotionById(id);
	}

	/**
	 * 保存促销配置
	 * @param csPromotion
	 */
	public CsPromotion saveCsPromotion(CsPromotion csPromotion)
	{
		return csPromotionDao.saveCsPromotion(csPromotion);
	}
	
	/**
	 * 更新促销配置
	 * @param csPromotion
	 */
	public void updateCsPromotion(CsPromotion csPromotion)
	{
		csPromotionDao.updateCsPromotion(csPromotion);
	}
	/**
	 * 更新促销配置非空字段
	 * @param csPromotion
	 */
	public void updateCsPromotion$NotNull(CsPromotion csPromotion)
	{
		csPromotionDao.updateCsPromotion$NotNull(csPromotion);
	}
	
	/**
	 * 根据ID删除一个促销配置
	 * @param id
	 */
	public void deleteCsPromotionById(Long id)
	{
		csPromotionDao.deleteCsPromotionById(id);
	}
	/**
	 * 根据ID逻辑删除一个促销配置
	 * @param id
	 */
	public void removeCsPromotionById(Long id)
	{
		csPromotionDao.removeCsPromotionById(id);
	}
	
	/**
	 * 根据条件更新促销配置
	 * @param values
	 * @param params
	 */
	public void updateCsPromotionByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPromotionBy时无条件");
		csPromotionDao.updateCsPromotionByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除促销配置
	 * @param params
	 */
	public void deleteCsPromotionByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPromotionBy时无条件");
		csPromotionDao.deleteCsPromotionByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPromotionDao getCsPromotionDao()
	{
		return csPromotionDao;
	}

	public void setCsPromotionDao(ICsPromotionDao csPromotionDao)
	{
		this.csPromotionDao = csPromotionDao;
	}
}