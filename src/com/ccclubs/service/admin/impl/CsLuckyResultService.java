package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLuckyResultDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLuckyResult;
import com.ccclubs.service.admin.ICsLuckyResultService;
import com.lazy3q.web.helper.$;

/**
 * 中奖结果的Service实现
 * @author 飞啊飘啊
 */
public class CsLuckyResultService implements ICsLuckyResultService
{
	ICsLuckyResultDao csLuckyResultDao;
	

	/**
	 * 获取所有中奖结果
	 * @return
	 */
	public List<CsLuckyResult> getCsLuckyResultList(Map params,Integer size)
	{
		return csLuckyResultDao.getCsLuckyResultList(params,size);
	}
	
	/**
	 * 获取中奖结果统计
	 * @return
	 */
	public List getCsLuckyResultStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLuckyResultDao.getCsLuckyResultStats(params,groups,sums);
	}
	
	/**
	 * 获取中奖结果总数
	 * @return
	 */
	public Long getCsLuckyResultCount(Map params)
	{
		return csLuckyResultDao.getCsLuckyResultCount(params);
	}
	
	/**
	 * 获取中奖结果自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLuckyResultEval(String eval,Map params)
	{
		return csLuckyResultDao.getCsLuckyResultEval(eval,params);
	}

	/**
	 * 获取中奖结果分页
	 * @return
	 */
	public Page getCsLuckyResultPage(int page,int size,Map params)
	{
		return csLuckyResultDao.getCsLuckyResultPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取中奖结果
	 * @param params
	 * @return
	 */
	public CsLuckyResult getCsLuckyResult(Map params)
	{
		return csLuckyResultDao.getCsLuckyResult(params);
	}

	/**
	 * 根据ID取中奖结果
	 * @param id
	 * @return
	 */
	public CsLuckyResult getCsLuckyResultById(Long id)
	{
		return csLuckyResultDao.getCsLuckyResultById(id);
	}

	/**
	 * 保存中奖结果
	 * @param csLuckyResult
	 */
	public CsLuckyResult saveCsLuckyResult(CsLuckyResult csLuckyResult)
	{
		return csLuckyResultDao.saveCsLuckyResult(csLuckyResult);
	}
	
	/**
	 * 更新中奖结果
	 * @param csLuckyResult
	 */
	public void updateCsLuckyResult(CsLuckyResult csLuckyResult)
	{
		csLuckyResultDao.updateCsLuckyResult(csLuckyResult);
	}
	/**
	 * 更新中奖结果非空字段
	 * @param csLuckyResult
	 */
	public void updateCsLuckyResult$NotNull(CsLuckyResult csLuckyResult)
	{
		csLuckyResultDao.updateCsLuckyResult$NotNull(csLuckyResult);
	}
	
	/**
	 * 根据ID删除一个中奖结果
	 * @param id
	 */
	public void deleteCsLuckyResultById(Long id)
	{
		csLuckyResultDao.deleteCsLuckyResultById(id);
	}
	/**
	 * 根据ID逻辑删除一个中奖结果
	 * @param id
	 */
	public void removeCsLuckyResultById(Long id)
	{
		csLuckyResultDao.removeCsLuckyResultById(id);
	}
	
	/**
	 * 根据条件更新中奖结果
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyResultByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLuckyResultBy时无条件");
		csLuckyResultDao.updateCsLuckyResultByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除中奖结果
	 * @param params
	 */
	public void deleteCsLuckyResultByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLuckyResultBy时无条件");
		csLuckyResultDao.deleteCsLuckyResultByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLuckyResultDao getCsLuckyResultDao()
	{
		return csLuckyResultDao;
	}

	public void setCsLuckyResultDao(ICsLuckyResultDao csLuckyResultDao)
	{
		this.csLuckyResultDao = csLuckyResultDao;
	}
}