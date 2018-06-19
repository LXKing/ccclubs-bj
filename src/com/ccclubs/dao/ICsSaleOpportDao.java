package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleOpport;

/**
 * 销售机会的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSaleOpportDao
{
	/**
	 * 获取所有销售机会
	 * @return
	 */
	public List<CsSaleOpport> getCsSaleOpportList(Map params,Integer size);
	
	/**
	 * 获取销售机会统计
	 * @return
	 */
	public List getCsSaleOpportStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取销售机会总数
	 * @return
	 */	
	public Long getCsSaleOpportCount(Map params);
	
	/**
	 * 获取销售机会自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSaleOpportEval(String eval,Map params);
	
	/**
	 * 获取销售机会分页
	 * @return
	 */	
	public Page getCsSaleOpportPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取销售机会
	 * @param params
	 * @return
	 */
	public CsSaleOpport getCsSaleOpport(Map params);	

	/**
	 * 根据ID取销售机会
	 * @param id
	 * @return
	 */
	public CsSaleOpport getCsSaleOpportById(Long id);


	
	

	/**
	 * 保存销售机会
	 * @param csSaleOpport
	 */
	public CsSaleOpport saveCsSaleOpport(CsSaleOpport csSaleOpport);

	/**
	 * 更新销售机会
	 * @param csSaleOpport
	 */
	public void updateCsSaleOpport(CsSaleOpport csSaleOpport);
	/**
	 * 更新销售机会非空字段
	 * @param csSaleOpport
	 */
	public void updateCsSaleOpport$NotNull(CsSaleOpport csSaleOpport);

	/**
	 * 根据ID删除一个销售机会
	 * @param id
	 */
	public void deleteCsSaleOpportById(Long id);
	/**
	 * 根据ID逻辑删除一个销售机会
	 * @param id
	 */
	public void removeCsSaleOpportById(Long id);
	/**
	 * 根据条件更新销售机会
	 * @param values
	 * @param params
	 */
	public void updateCsSaleOpportByConfirm(Map values, Map params);
	/**
	 * 根据条件删除销售机会
	 * @param params
	 */
	public void deleteCsSaleOpportByConfirm(Map params);

}