package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRevisit;

/**
 * 用户回访的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRevisitDao
{
	/**
	 * 获取所有用户回访
	 * @return
	 */
	public List<CsRevisit> getCsRevisitList(Map params,Integer size);
	
	/**
	 * 获取用户回访统计
	 * @return
	 */
	public List getCsRevisitStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取用户回访总数
	 * @return
	 */	
	public Long getCsRevisitCount(Map params);
	
	/**
	 * 获取用户回访自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRevisitEval(String eval,Map params);
	
	/**
	 * 获取用户回访分页
	 * @return
	 */	
	public Page getCsRevisitPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取用户回访
	 * @param params
	 * @return
	 */
	public CsRevisit getCsRevisit(Map params);	

	/**
	 * 根据ID取用户回访
	 * @param id
	 * @return
	 */
	public CsRevisit getCsRevisitById(Long id);


	
	

	/**
	 * 保存用户回访
	 * @param csRevisit
	 */
	public CsRevisit saveCsRevisit(CsRevisit csRevisit);

	/**
	 * 更新用户回访
	 * @param csRevisit
	 */
	public void updateCsRevisit(CsRevisit csRevisit);
	/**
	 * 更新用户回访非空字段
	 * @param csRevisit
	 */
	public void updateCsRevisit$NotNull(CsRevisit csRevisit);

	/**
	 * 根据ID删除一个用户回访
	 * @param id
	 */
	public void deleteCsRevisitById(Long id);
	/**
	 * 根据ID逻辑删除一个用户回访
	 * @param id
	 */
	public void removeCsRevisitById(Long id);
	/**
	 * 根据条件更新用户回访
	 * @param values
	 * @param params
	 */
	public void updateCsRevisitByConfirm(Map values, Map params);
	/**
	 * 根据条件删除用户回访
	 * @param params
	 */
	public void deleteCsRevisitByConfirm(Map params);

}