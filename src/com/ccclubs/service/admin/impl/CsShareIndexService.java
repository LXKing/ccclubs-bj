package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsShareIndexDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsShareIndex;
import com.ccclubs.service.admin.ICsShareIndexService;
import com.lazy3q.web.helper.$;

/**
 * 分享指数的Service实现
 * @author 飞啊飘啊
 */
public class CsShareIndexService implements ICsShareIndexService
{
	ICsShareIndexDao csShareIndexDao;
	

	/**
	 * 获取所有分享指数
	 * @return
	 */
	public List<CsShareIndex> getCsShareIndexList(Map params,Integer size)
	{
		return csShareIndexDao.getCsShareIndexList(params,size);
	}
	
	/**
	 * 获取分享指数统计
	 * @return
	 */
	public List getCsShareIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csShareIndexDao.getCsShareIndexStats(params,groups,sums);
	}
	
	/**
	 * 获取分享指数总数
	 * @return
	 */
	public Long getCsShareIndexCount(Map params)
	{
		return csShareIndexDao.getCsShareIndexCount(params);
	}
	
	/**
	 * 获取分享指数自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsShareIndexEval(String eval,Map params)
	{
		return csShareIndexDao.getCsShareIndexEval(eval,params);
	}

	/**
	 * 获取分享指数分页
	 * @return
	 */
	public Page getCsShareIndexPage(int page,int size,Map params)
	{
		return csShareIndexDao.getCsShareIndexPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取分享指数
	 * @param params
	 * @return
	 */
	public CsShareIndex getCsShareIndex(Map params)
	{
		return csShareIndexDao.getCsShareIndex(params);
	}

	/**
	 * 根据ID取分享指数
	 * @param id
	 * @return
	 */
	public CsShareIndex getCsShareIndexById(Long id)
	{
		return csShareIndexDao.getCsShareIndexById(id);
	}

	/**
	 * 保存分享指数
	 * @param csShareIndex
	 */
	public CsShareIndex saveCsShareIndex(CsShareIndex csShareIndex)
	{
		return csShareIndexDao.saveCsShareIndex(csShareIndex);
	}
	
	/**
	 * 更新分享指数
	 * @param csShareIndex
	 */
	public void updateCsShareIndex(CsShareIndex csShareIndex)
	{
		csShareIndexDao.updateCsShareIndex(csShareIndex);
	}
	/**
	 * 更新分享指数非空字段
	 * @param csShareIndex
	 */
	public void updateCsShareIndex$NotNull(CsShareIndex csShareIndex)
	{
		csShareIndexDao.updateCsShareIndex$NotNull(csShareIndex);
	}
	
	/**
	 * 根据ID删除一个分享指数
	 * @param id
	 */
	public void deleteCsShareIndexById(Long id)
	{
		csShareIndexDao.deleteCsShareIndexById(id);
	}
	/**
	 * 根据ID逻辑删除一个分享指数
	 * @param id
	 */
	public void removeCsShareIndexById(Long id)
	{
		csShareIndexDao.removeCsShareIndexById(id);
	}
	
	/**
	 * 根据条件更新分享指数
	 * @param values
	 * @param params
	 */
	public void updateCsShareIndexByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsShareIndexBy时无条件");
		csShareIndexDao.updateCsShareIndexByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除分享指数
	 * @param params
	 */
	public void deleteCsShareIndexByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsShareIndexBy时无条件");
		csShareIndexDao.deleteCsShareIndexByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsShareIndexDao getCsShareIndexDao()
	{
		return csShareIndexDao;
	}

	public void setCsShareIndexDao(ICsShareIndexDao csShareIndexDao)
	{
		this.csShareIndexDao = csShareIndexDao;
	}
}