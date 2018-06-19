package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsOrderCluster;

/**
 * 订单簇的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsOrderClusterService
{
	/**
	 * 获取所有订单簇
	 * @return
	 */
	public List<CsOrderCluster> getCsOrderClusterList(Map params,Integer size);
	
	/**
	 * 获取订单簇统计
	 * @return
	 */
	public List getCsOrderClusterStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取订单簇总数
	 * @return
	 */	
	public Long getCsOrderClusterCount(Map params);
	
	
	/**
	 * 获取订单簇自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderClusterEval(String eval,Map params);

	/**
	 * 获取订单簇分页
	 * @return
	 */	
	public Page getCsOrderClusterPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取订单簇
	 * @param params
	 * @return
	 */
	public CsOrderCluster getCsOrderCluster(Map params);

	/**
	 * 根据ID取订单簇
	 * @param id
	 * @return
	 */
	public CsOrderCluster getCsOrderClusterById(Long id);


	/**
	 * 保存订单簇
	 * @param csOrderCluster
	 */
	public CsOrderCluster saveCsOrderCluster(CsOrderCluster csOrderCluster);

	/**
	 * 更新订单簇
	 * @param csOrderCluster
	 */
	public void updateCsOrderCluster(CsOrderCluster csOrderCluster);
	/**
	 * 更新订单簇非空字段
	 * @param csOrderCluster
	 */
	public void updateCsOrderCluster$NotNull(CsOrderCluster csOrderCluster);

	/**
	 * 根据ID删除一个订单簇
	 * @param id
	 */
	public void deleteCsOrderClusterById(Long id);
	/**
	 * 根据ID逻辑删除一个订单簇
	 * @param id
	 */
	public void removeCsOrderClusterById(Long id);
	/**
	 * 根据条件更新订单簇
	 * @param values
	 * @param params
	 */
	public void updateCsOrderClusterByConfirm(Map values, Map params);
	/**
	 * 根据条件删除订单簇
	 * @param params
	 */
	public void deleteCsOrderClusterByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}