package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOutlets;

/**
 * 网点的Dao接口
 * 
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsOutletsDao {
	/**
	 * 获取所有网点
	 * 
	 * @return
	 */
	public List<CsOutlets> getCsOutletsList(Map params, Integer size);

	/**
	 * 获取网点统计
	 * 
	 * @return
	 */
	public List getCsOutletsStats(Map params, Map<String, Object> groups,
			Map<String, Object> sums);

	/**
	 * 获取网点总数
	 * 
	 * @return
	 */
	public Long getCsOutletsCount(Map params);

	/**
	 * 获取网点自定义求和表达式,比如求和:eval="sum(id)"
	 * 
	 * @return
	 */
	public <T> T getCsOutletsEval(String eval, Map params);

	public <T> T getCsOutletsEvalList(String eval, Map params);

	/**
	 * 获取网点分页
	 * 
	 * @return
	 */
	public Page getCsOutletsPage(int page, int size, Map params);

	/**
	 * 根据查询条件取网点
	 * 
	 * @param params
	 * @return
	 */
	public CsOutlets getCsOutlets(Map params);

	/**
	 * 根据ID取网点
	 * 
	 * @param id
	 * @return
	 */
	public CsOutlets getCsOutletsById(Long id);

	/**
	 * 保存网点
	 * 
	 * @param csOutlets
	 */
	public CsOutlets saveCsOutlets(CsOutlets csOutlets);

	/**
	 * 更新网点
	 * 
	 * @param csOutlets
	 */
	public void updateCsOutlets(CsOutlets csOutlets);

	/**
	 * 更新网点非空字段
	 * 
	 * @param csOutlets
	 */
	public void updateCsOutlets$NotNull(CsOutlets csOutlets);

	/**
	 * 根据ID删除一个网点
	 * 
	 * @param id
	 */
	public void deleteCsOutletsById(Long id);

	/**
	 * 根据ID逻辑删除一个网点
	 * 
	 * @param id
	 */
	public void removeCsOutletsById(Long id);

	/**
	 * 根据条件更新网点
	 * 
	 * @param values
	 * @param params
	 */
	public void updateCsOutletsByConfirm(Map values, Map params);

	/**
	 * 根据条件删除网点
	 * 
	 * @param params
	 */
	public void deleteCsOutletsByConfirm(Map params);

}