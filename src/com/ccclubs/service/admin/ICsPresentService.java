package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsPresent;

/**
 * 礼品赠品的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPresentService
{
	/**
	 * 获取所有礼品赠品
	 * @return
	 */
	public List<CsPresent> getCsPresentList(Map params,Integer size);
	
	/**
	 * 获取礼品赠品统计
	 * @return
	 */
	public List getCsPresentStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取礼品赠品总数
	 * @return
	 */	
	public Long getCsPresentCount(Map params);
	
	
	/**
	 * 获取礼品赠品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPresentEval(String eval,Map params);

	/**
	 * 获取礼品赠品分页
	 * @return
	 */	
	public Page getCsPresentPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取礼品赠品
	 * @param params
	 * @return
	 */
	public CsPresent getCsPresent(Map params);

	/**
	 * 根据ID取礼品赠品
	 * @param id
	 * @return
	 */
	public CsPresent getCsPresentById(Long id);


	/**
	 * 保存礼品赠品
	 * @param csPresent
	 */
	public CsPresent saveCsPresent(CsPresent csPresent);

	/**
	 * 更新礼品赠品
	 * @param csPresent
	 */
	public void updateCsPresent(CsPresent csPresent);
	/**
	 * 更新礼品赠品非空字段
	 * @param csPresent
	 */
	public void updateCsPresent$NotNull(CsPresent csPresent);

	/**
	 * 根据ID删除一个礼品赠品
	 * @param id
	 */
	public void deleteCsPresentById(Long id);
	/**
	 * 根据ID逻辑删除一个礼品赠品
	 * @param id
	 */
	public void removeCsPresentById(Long id);
	/**
	 * 根据条件更新礼品赠品
	 * @param values
	 * @param params
	 */
	public void updateCsPresentByConfirm(Map values, Map params);
	/**
	 * 根据条件删除礼品赠品
	 * @param params
	 */
	public void deleteCsPresentByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}