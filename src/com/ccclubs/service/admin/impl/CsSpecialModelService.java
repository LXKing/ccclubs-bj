package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSpecialModelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSpecialModel;
import com.ccclubs.service.admin.ICsSpecialModelService;
import com.lazy3q.web.helper.$;

/**
 * 专车车型的Service实现
 * @author 飞啊飘啊
 */
public class CsSpecialModelService implements ICsSpecialModelService
{
	ICsSpecialModelDao csSpecialModelDao;
	

	/**
	 * 获取所有专车车型
	 * @return
	 */
	public List<CsSpecialModel> getCsSpecialModelList(Map params,Integer size)
	{
		return csSpecialModelDao.getCsSpecialModelList(params,size);
	}
	
	/**
	 * 获取专车车型统计
	 * @return
	 */
	public List getCsSpecialModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSpecialModelDao.getCsSpecialModelStats(params,groups,sums);
	}
	
	/**
	 * 获取专车车型总数
	 * @return
	 */
	public Long getCsSpecialModelCount(Map params)
	{
		return csSpecialModelDao.getCsSpecialModelCount(params);
	}
	
	/**
	 * 获取专车车型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSpecialModelEval(String eval,Map params)
	{
		return csSpecialModelDao.getCsSpecialModelEval(eval,params);
	}

	/**
	 * 获取专车车型分页
	 * @return
	 */
	public Page getCsSpecialModelPage(int page,int size,Map params)
	{
		return csSpecialModelDao.getCsSpecialModelPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取专车车型
	 * @param params
	 * @return
	 */
	public CsSpecialModel getCsSpecialModel(Map params)
	{
		return csSpecialModelDao.getCsSpecialModel(params);
	}

	/**
	 * 根据ID取专车车型
	 * @param id
	 * @return
	 */
	public CsSpecialModel getCsSpecialModelById(Long id)
	{
		return csSpecialModelDao.getCsSpecialModelById(id);
	}

	/**
	 * 保存专车车型
	 * @param csSpecialModel
	 */
	public CsSpecialModel saveCsSpecialModel(CsSpecialModel csSpecialModel)
	{
		return csSpecialModelDao.saveCsSpecialModel(csSpecialModel);
	}
	
	/**
	 * 更新专车车型
	 * @param csSpecialModel
	 */
	public void updateCsSpecialModel(CsSpecialModel csSpecialModel)
	{
		csSpecialModelDao.updateCsSpecialModel(csSpecialModel);
	}
	/**
	 * 更新专车车型非空字段
	 * @param csSpecialModel
	 */
	public void updateCsSpecialModel$NotNull(CsSpecialModel csSpecialModel)
	{
		csSpecialModelDao.updateCsSpecialModel$NotNull(csSpecialModel);
	}
	
	/**
	 * 根据ID删除一个专车车型
	 * @param id
	 */
	public void deleteCsSpecialModelById(Long id)
	{
		csSpecialModelDao.deleteCsSpecialModelById(id);
	}
	/**
	 * 根据ID逻辑删除一个专车车型
	 * @param id
	 */
	public void removeCsSpecialModelById(Long id)
	{
		csSpecialModelDao.removeCsSpecialModelById(id);
	}
	
	/**
	 * 根据条件更新专车车型
	 * @param values
	 * @param params
	 */
	public void updateCsSpecialModelByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSpecialModelBy时无条件");
		csSpecialModelDao.updateCsSpecialModelByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除专车车型
	 * @param params
	 */
	public void deleteCsSpecialModelByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSpecialModelBy时无条件");
		csSpecialModelDao.deleteCsSpecialModelByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSpecialModelDao getCsSpecialModelDao()
	{
		return csSpecialModelDao;
	}

	public void setCsSpecialModelDao(ICsSpecialModelDao csSpecialModelDao)
	{
		this.csSpecialModelDao = csSpecialModelDao;
	}
}