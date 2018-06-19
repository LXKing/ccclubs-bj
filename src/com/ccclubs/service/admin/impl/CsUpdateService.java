package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUpdateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUpdate;
import com.ccclubs.service.admin.ICsUpdateService;
import com.lazy3q.web.helper.$;

/**
 * App更新的Service实现
 * @author 飞啊飘啊
 */
public class CsUpdateService implements ICsUpdateService
{
	ICsUpdateDao csUpdateDao;
	

	/**
	 * 获取所有App更新
	 * @return
	 */
	public List<CsUpdate> getCsUpdateList(Map params,Integer size)
	{
		return csUpdateDao.getCsUpdateList(params,size);
	}
	
	/**
	 * 获取App更新统计
	 * @return
	 */
	public List getCsUpdateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUpdateDao.getCsUpdateStats(params,groups,sums);
	}
	
	/**
	 * 获取App更新总数
	 * @return
	 */
	public Long getCsUpdateCount(Map params)
	{
		return csUpdateDao.getCsUpdateCount(params);
	}
	
	/**
	 * 获取App更新自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUpdateEval(String eval,Map params)
	{
		return csUpdateDao.getCsUpdateEval(eval,params);
	}

	/**
	 * 获取App更新分页
	 * @return
	 */
	public Page getCsUpdatePage(int page,int size,Map params)
	{
		return csUpdateDao.getCsUpdatePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取App更新
	 * @param params
	 * @return
	 */
	public CsUpdate getCsUpdate(Map params)
	{
		return csUpdateDao.getCsUpdate(params);
	}

	/**
	 * 根据ID取App更新
	 * @param id
	 * @return
	 */
	public CsUpdate getCsUpdateById(Long id)
	{
		return csUpdateDao.getCsUpdateById(id);
	}

	/**
	 * 保存App更新
	 * @param csUpdate
	 */
	public CsUpdate saveCsUpdate(CsUpdate csUpdate)
	{
		return csUpdateDao.saveCsUpdate(csUpdate);
	}
	
	/**
	 * 更新App更新
	 * @param csUpdate
	 */
	public void updateCsUpdate(CsUpdate csUpdate)
	{
		csUpdateDao.updateCsUpdate(csUpdate);
	}
	/**
	 * 更新App更新非空字段
	 * @param csUpdate
	 */
	public void updateCsUpdate$NotNull(CsUpdate csUpdate)
	{
		csUpdateDao.updateCsUpdate$NotNull(csUpdate);
	}
	
	/**
	 * 根据ID删除一个App更新
	 * @param id
	 */
	public void deleteCsUpdateById(Long id)
	{
		csUpdateDao.deleteCsUpdateById(id);
	}
	/**
	 * 根据ID逻辑删除一个App更新
	 * @param id
	 */
	public void removeCsUpdateById(Long id)
	{
		csUpdateDao.removeCsUpdateById(id);
	}
	
	/**
	 * 根据条件更新App更新
	 * @param values
	 * @param params
	 */
	public void updateCsUpdateByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUpdateBy时无条件");
		csUpdateDao.updateCsUpdateByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除App更新
	 * @param params
	 */
	public void deleteCsUpdateByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUpdateBy时无条件");
		csUpdateDao.deleteCsUpdateByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUpdateDao getCsUpdateDao()
	{
		return csUpdateDao;
	}

	public void setCsUpdateDao(ICsUpdateDao csUpdateDao)
	{
		this.csUpdateDao = csUpdateDao;
	}
}