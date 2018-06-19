package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLucky;

/**
 * 抽奖活动的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLuckyDao
{
	/**
	 * 获取所有抽奖活动
	 * @return
	 */
	public List<CsLucky> getCsLuckyList(Map params,Integer size);
	
	/**
	 * 获取抽奖活动统计
	 * @return
	 */
	public List getCsLuckyStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取抽奖活动总数
	 * @return
	 */	
	public Long getCsLuckyCount(Map params);
	
	/**
	 * 获取抽奖活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLuckyEval(String eval,Map params);
	
	/**
	 * 获取抽奖活动分页
	 * @return
	 */	
	public Page getCsLuckyPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取抽奖活动
	 * @param params
	 * @return
	 */
	public CsLucky getCsLucky(Map params);	

	/**
	 * 根据ID取抽奖活动
	 * @param id
	 * @return
	 */
	public CsLucky getCsLuckyById(Long id);


	
	

	/**
	 * 保存抽奖活动
	 * @param csLucky
	 */
	public CsLucky saveCsLucky(CsLucky csLucky);

	/**
	 * 更新抽奖活动
	 * @param csLucky
	 */
	public void updateCsLucky(CsLucky csLucky);
	/**
	 * 更新抽奖活动非空字段
	 * @param csLucky
	 */
	public void updateCsLucky$NotNull(CsLucky csLucky);

	/**
	 * 根据ID删除一个抽奖活动
	 * @param id
	 */
	public void deleteCsLuckyById(Long id);
	/**
	 * 根据ID逻辑删除一个抽奖活动
	 * @param id
	 */
	public void removeCsLuckyById(Long id);
	/**
	 * 根据条件更新抽奖活动
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyByConfirm(Map values, Map params);
	/**
	 * 根据条件删除抽奖活动
	 * @param params
	 */
	public void deleteCsLuckyByConfirm(Map params);

}