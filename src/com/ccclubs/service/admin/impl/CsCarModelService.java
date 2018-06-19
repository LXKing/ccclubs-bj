package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarModelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.service.admin.ICsCarModelService;
import com.lazy3q.web.helper.$;

/**
 * 车型的Service实现
 * @author 飞啊飘啊
 */
public class CsCarModelService implements ICsCarModelService
{
	ICsCarModelDao csCarModelDao;
	

	/**
	 * 获取所有车型
	 * @return
	 */
	public List<CsCarModel> getCsCarModelList(Map params,Integer size)
	{
		return csCarModelDao.getCsCarModelList(params,size);
	}
	
	/**
	 * 获取车型统计
	 * @return
	 */
	public List getCsCarModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarModelDao.getCsCarModelStats(params,groups,sums);
	}
	
	/**
	 * 获取车型总数
	 * @return
	 */
	public Long getCsCarModelCount(Map params)
	{
		return csCarModelDao.getCsCarModelCount(params);
	}
	
	/**
	 * 获取车型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarModelEval(String eval,Map params)
	{
		return csCarModelDao.getCsCarModelEval(eval,params);
	}

	/**
	 * 获取车型分页
	 * @return
	 */
	public Page getCsCarModelPage(int page,int size,Map params)
	{
		return csCarModelDao.getCsCarModelPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车型
	 * @param params
	 * @return
	 */
	public CsCarModel getCsCarModel(Map params)
	{
		return csCarModelDao.getCsCarModel(params);
	}

	/**
	 * 根据ID取车型
	 * @param id
	 * @return
	 */
	public CsCarModel getCsCarModelById(Long id)
	{
		return csCarModelDao.getCsCarModelById(id);
	}

	/**
	 * 保存车型
	 * @param csCarModel
	 */
	public CsCarModel saveCsCarModel(CsCarModel csCarModel)
	{
		return csCarModelDao.saveCsCarModel(csCarModel);
	}
	
	/**
	 * 更新车型
	 * @param csCarModel
	 */
	public void updateCsCarModel(CsCarModel csCarModel)
	{
		csCarModelDao.updateCsCarModel(csCarModel);
	}
	/**
	 * 更新车型非空字段
	 * @param csCarModel
	 */
	public void updateCsCarModel$NotNull(CsCarModel csCarModel)
	{
		csCarModelDao.updateCsCarModel$NotNull(csCarModel);
	}
	
	/**
	 * 根据ID删除一个车型
	 * @param id
	 */
	public void deleteCsCarModelById(Long id)
	{
		csCarModelDao.deleteCsCarModelById(id);
	}
	/**
	 * 根据ID逻辑删除一个车型
	 * @param id
	 */
	public void removeCsCarModelById(Long id)
	{
		csCarModelDao.removeCsCarModelById(id);
	}
	
	/**
	 * 根据条件更新车型
	 * @param values
	 * @param params
	 */
	public void updateCsCarModelByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarModelBy时无条件");
		csCarModelDao.updateCsCarModelByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车型
	 * @param params
	 */
	public void deleteCsCarModelByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarModelBy时无条件");
		csCarModelDao.deleteCsCarModelByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarModelDao getCsCarModelDao()
	{
		return csCarModelDao;
	}

	public void setCsCarModelDao(ICsCarModelDao csCarModelDao)
	{
		this.csCarModelDao = csCarModelDao;
	}
}