package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPropagateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPropagate;
import com.ccclubs.service.admin.ICsPropagateService;
import com.lazy3q.web.helper.$;

/**
 * 传播内容的Service实现
 * @author 飞啊飘啊
 */
public class CsPropagateService implements ICsPropagateService
{
	ICsPropagateDao csPropagateDao;
	

	/**
	 * 获取所有传播内容
	 * @return
	 */
	public List<CsPropagate> getCsPropagateList(Map params,Integer size)
	{
		return csPropagateDao.getCsPropagateList(params,size);
	}
	
	/**
	 * 获取传播内容统计
	 * @return
	 */
	public List getCsPropagateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPropagateDao.getCsPropagateStats(params,groups,sums);
	}
	
	/**
	 * 获取传播内容总数
	 * @return
	 */
	public Long getCsPropagateCount(Map params)
	{
		return csPropagateDao.getCsPropagateCount(params);
	}
	
	/**
	 * 获取传播内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPropagateEval(String eval,Map params)
	{
		return csPropagateDao.getCsPropagateEval(eval,params);
	}

	/**
	 * 获取传播内容分页
	 * @return
	 */
	public Page getCsPropagatePage(int page,int size,Map params)
	{
		return csPropagateDao.getCsPropagatePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取传播内容
	 * @param params
	 * @return
	 */
	public CsPropagate getCsPropagate(Map params)
	{
		return csPropagateDao.getCsPropagate(params);
	}

	/**
	 * 根据ID取传播内容
	 * @param id
	 * @return
	 */
	public CsPropagate getCsPropagateById(Long id)
	{
		return csPropagateDao.getCsPropagateById(id);
	}

	/**
	 * 保存传播内容
	 * @param csPropagate
	 */
	public CsPropagate saveCsPropagate(CsPropagate csPropagate)
	{
		return csPropagateDao.saveCsPropagate(csPropagate);
	}
	
	/**
	 * 更新传播内容
	 * @param csPropagate
	 */
	public void updateCsPropagate(CsPropagate csPropagate)
	{
		csPropagateDao.updateCsPropagate(csPropagate);
	}
	/**
	 * 更新传播内容非空字段
	 * @param csPropagate
	 */
	public void updateCsPropagate$NotNull(CsPropagate csPropagate)
	{
		csPropagateDao.updateCsPropagate$NotNull(csPropagate);
	}
	
	/**
	 * 根据ID删除一个传播内容
	 * @param id
	 */
	public void deleteCsPropagateById(Long id)
	{
		csPropagateDao.deleteCsPropagateById(id);
	}
	/**
	 * 根据ID逻辑删除一个传播内容
	 * @param id
	 */
	public void removeCsPropagateById(Long id)
	{
		csPropagateDao.removeCsPropagateById(id);
	}
	
	/**
	 * 根据条件更新传播内容
	 * @param values
	 * @param params
	 */
	public void updateCsPropagateByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPropagateBy时无条件");
		csPropagateDao.updateCsPropagateByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除传播内容
	 * @param params
	 */
	public void deleteCsPropagateByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPropagateBy时无条件");
		csPropagateDao.deleteCsPropagateByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPropagateDao getCsPropagateDao()
	{
		return csPropagateDao;
	}

	public void setCsPropagateDao(ICsPropagateDao csPropagateDao)
	{
		this.csPropagateDao = csPropagateDao;
	}
}