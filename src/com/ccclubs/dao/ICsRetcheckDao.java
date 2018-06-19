package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRetcheck;

/**
 * 还车检查的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsRetcheckDao
{
	/**
	 * 获取所有还车检查
	 * @return
	 */
	public List<CsRetcheck> getCsRetcheckList(Map params,Integer size);
	
	/**
	 * 获取还车检查统计
	 * @return
	 */
	public List getCsRetcheckStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取还车检查总数
	 * @return
	 */	
	public Long getCsRetcheckCount(Map params);
	
	/**
	 * 获取还车检查自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRetcheckEval(String eval,Map params);
	
	/**
	 * 获取还车检查分页
	 * @return
	 */	
	public Page getCsRetcheckPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取还车检查
	 * @param params
	 * @return
	 */
	public CsRetcheck getCsRetcheck(Map params);	

	/**
	 * 根据ID取还车检查
	 * @param id
	 * @return
	 */
	public CsRetcheck getCsRetcheckById(Long id);


	
	

	/**
	 * 保存还车检查
	 * @param csRetcheck
	 */
	public CsRetcheck saveCsRetcheck(CsRetcheck csRetcheck);

	/**
	 * 更新还车检查
	 * @param csRetcheck
	 */
	public void updateCsRetcheck(CsRetcheck csRetcheck);
	/**
	 * 更新还车检查非空字段
	 * @param csRetcheck
	 */
	public void updateCsRetcheck$NotNull(CsRetcheck csRetcheck);

	/**
	 * 根据ID删除一个还车检查
	 * @param id
	 */
	public void deleteCsRetcheckById(Long id);
	/**
	 * 根据ID逻辑删除一个还车检查
	 * @param id
	 */
	public void removeCsRetcheckById(Long id);
	/**
	 * 根据条件更新还车检查
	 * @param values
	 * @param params
	 */
	public void updateCsRetcheckByConfirm(Map values, Map params);
	/**
	 * 根据条件删除还车检查
	 * @param params
	 */
	public void deleteCsRetcheckByConfirm(Map params);

}