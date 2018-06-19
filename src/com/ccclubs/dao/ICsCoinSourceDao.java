package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCoinSource;

/**
 * 红包来源的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsCoinSourceDao
{
	/**
	 * 获取所有红包来源
	 * @return
	 */
	public List<CsCoinSource> getCsCoinSourceList(Map params,Integer size);
	
	/**
	 * 获取红包来源统计
	 * @return
	 */
	public List getCsCoinSourceStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取红包来源总数
	 * @return
	 */	
	public Long getCsCoinSourceCount(Map params);
	
	/**
	 * 获取红包来源自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCoinSourceEval(String eval,Map params);
	
	/**
	 * 获取红包来源分页
	 * @return
	 */	
	public Page getCsCoinSourcePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取红包来源
	 * @param params
	 * @return
	 */
	public CsCoinSource getCsCoinSource(Map params);	

	/**
	 * 根据ID取红包来源
	 * @param id
	 * @return
	 */
	public CsCoinSource getCsCoinSourceById(Long id);


	
	

	/**
	 * 保存红包来源
	 * @param csCoinSource
	 */
	public CsCoinSource saveCsCoinSource(CsCoinSource csCoinSource);

	/**
	 * 更新红包来源
	 * @param csCoinSource
	 */
	public void updateCsCoinSource(CsCoinSource csCoinSource);
	/**
	 * 更新红包来源非空字段
	 * @param csCoinSource
	 */
	public void updateCsCoinSource$NotNull(CsCoinSource csCoinSource);

	/**
	 * 根据ID删除一个红包来源
	 * @param id
	 */
	public void deleteCsCoinSourceById(Long id);
	/**
	 * 根据ID逻辑删除一个红包来源
	 * @param id
	 */
	public void removeCsCoinSourceById(Long id);
	/**
	 * 根据条件更新红包来源
	 * @param values
	 * @param params
	 */
	public void updateCsCoinSourceByConfirm(Map values, Map params);
	/**
	 * 根据条件删除红包来源
	 * @param params
	 */
	public void deleteCsCoinSourceByConfirm(Map params);

}