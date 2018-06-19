package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOrderClusterDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderCluster;
import com.ccclubs.service.admin.ICsOrderClusterService;
import com.lazy3q.web.helper.$;

/**
 * 订单簇的Service实现
 * @author Joel
 */
public class CsOrderClusterService implements ICsOrderClusterService
{
	ICsOrderClusterDao csOrderClusterDao;
	

	/**
	 * 获取所有订单簇
	 * @return
	 */
	public List<CsOrderCluster> getCsOrderClusterList(Map params,Integer size)
	{
		return csOrderClusterDao.getCsOrderClusterList(params,size);
	}
	
	/**
	 * 获取订单簇统计
	 * @return
	 */
	public List getCsOrderClusterStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOrderClusterDao.getCsOrderClusterStats(params,groups,sums);
	}
	
	/**
	 * 获取订单簇总数
	 * @return
	 */
	public Long getCsOrderClusterCount(Map params)
	{
		return csOrderClusterDao.getCsOrderClusterCount(params);
	}
	
	/**
	 * 获取订单簇自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderClusterEval(String eval,Map params)
	{
		return csOrderClusterDao.getCsOrderClusterEval(eval,params);
	}

	/**
	 * 获取订单簇分页
	 * @return
	 */
	public Page getCsOrderClusterPage(int page,int size,Map params)
	{
		return csOrderClusterDao.getCsOrderClusterPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取订单簇
	 * @param params
	 * @return
	 */
	public CsOrderCluster getCsOrderCluster(Map params)
	{
		return csOrderClusterDao.getCsOrderCluster(params);
	}

	/**
	 * 根据ID取订单簇
	 * @param id
	 * @return
	 */
	public CsOrderCluster getCsOrderClusterById(Long id)
	{
		return csOrderClusterDao.getCsOrderClusterById(id);
	}

	/**
	 * 保存订单簇
	 * @param csOrderCluster
	 */
	public CsOrderCluster saveCsOrderCluster(CsOrderCluster csOrderCluster)
	{
		return csOrderClusterDao.saveCsOrderCluster(csOrderCluster);
	}
	
	/**
	 * 更新订单簇
	 * @param csOrderCluster
	 */
	public void updateCsOrderCluster(CsOrderCluster csOrderCluster)
	{
		csOrderClusterDao.updateCsOrderCluster(csOrderCluster);
	}
	/**
	 * 更新订单簇非空字段
	 * @param csOrderCluster
	 */
	public void updateCsOrderCluster$NotNull(CsOrderCluster csOrderCluster)
	{
		csOrderClusterDao.updateCsOrderCluster$NotNull(csOrderCluster);
	}
	
	/**
	 * 根据ID删除一个订单簇
	 * @param id
	 */
	public void deleteCsOrderClusterById(Long id)
	{
		csOrderClusterDao.deleteCsOrderClusterById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个订单簇
	 * @param id
	 */
	public void removeCsOrderClusterById(Long id)
	{
		csOrderClusterDao.removeCsOrderClusterById(id);
	}
	
	/**
	 * 根据条件更新订单簇
	 * @param values
	 * @param params
	 */
	public void updateCsOrderClusterByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOrderClusterBy时无条件");
		csOrderClusterDao.updateCsOrderClusterByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除订单簇
	 * @param params
	 */
	public void deleteCsOrderClusterByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOrderClusterBy时无条件");
		csOrderClusterDao.deleteCsOrderClusterByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOrderClusterDao getCsOrderClusterDao()
	{
		return csOrderClusterDao;
	}

	public void setCsOrderClusterDao(ICsOrderClusterDao csOrderClusterDao)
	{
		this.csOrderClusterDao = csOrderClusterDao;
	}
}