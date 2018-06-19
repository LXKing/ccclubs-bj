package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberSummary;

/**
 * 会员汇总项的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsMemberSummaryDao
{
	/**
	 * 获取所有会员汇总项
	 * @return
	 */
	public List<CsMemberSummary> getCsMemberSummaryList(Map params,Integer size);
	
	/**
	 * 获取会员汇总项统计
	 * @return
	 */
	public List getCsMemberSummaryStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员汇总项总数
	 * @return
	 */	
	public Long getCsMemberSummaryCount(Map params);
	
	/**
	 * 获取会员汇总项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberSummaryEval(String eval,Map params);
	
	/**
	 * 获取会员汇总项分页
	 * @return
	 */	
	public Page getCsMemberSummaryPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员汇总项
	 * @param params
	 * @return
	 */
	public CsMemberSummary getCsMemberSummary(Map params);	

	/**
	 * 根据ID取会员汇总项
	 * @param id
	 * @return
	 */
	public CsMemberSummary getCsMemberSummaryById(Long id);


	
	

	/**
	 * 保存会员汇总项
	 * @param csMemberSummary
	 */
	public CsMemberSummary saveCsMemberSummary(CsMemberSummary csMemberSummary);

	/**
	 * 更新会员汇总项
	 * @param csMemberSummary
	 */
	public void updateCsMemberSummary(CsMemberSummary csMemberSummary);
	/**
	 * 更新会员汇总项非空字段
	 * @param csMemberSummary
	 */
	public void updateCsMemberSummary$NotNull(CsMemberSummary csMemberSummary);

	/**
	 * 根据ID删除一个会员汇总项
	 * @param id
	 */
	public void deleteCsMemberSummaryById(Long id);
	/**
	 * 根据ID逻辑删除一个会员汇总项
	 * @param id
	 */
	public void removeCsMemberSummaryById(Long id);
	/**
	 * 根据条件更新会员汇总项
	 * @param values
	 * @param params
	 */
	public void updateCsMemberSummaryByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员汇总项
	 * @param params
	 */
	public void deleteCsMemberSummaryByConfirm(Map params);

}