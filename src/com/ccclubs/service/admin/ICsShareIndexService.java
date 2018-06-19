package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsShareIndex;

/**
 * 分享指数的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsShareIndexService
{
	/**
	 * 获取所有分享指数
	 * @return
	 */
	public List<CsShareIndex> getCsShareIndexList(Map params,Integer size);
	
	/**
	 * 获取分享指数统计
	 * @return
	 */
	public List getCsShareIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取分享指数总数
	 * @return
	 */	
	public Long getCsShareIndexCount(Map params);
	
	
	/**
	 * 获取分享指数自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsShareIndexEval(String eval,Map params);

	/**
	 * 获取分享指数分页
	 * @return
	 */	
	public Page getCsShareIndexPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取分享指数
	 * @param params
	 * @return
	 */
	public CsShareIndex getCsShareIndex(Map params);

	/**
	 * 根据ID取分享指数
	 * @param id
	 * @return
	 */
	public CsShareIndex getCsShareIndexById(Long id);


	/**
	 * 保存分享指数
	 * @param csShareIndex
	 */
	public CsShareIndex saveCsShareIndex(CsShareIndex csShareIndex);

	/**
	 * 更新分享指数
	 * @param csShareIndex
	 */
	public void updateCsShareIndex(CsShareIndex csShareIndex);
	/**
	 * 更新分享指数非空字段
	 * @param csShareIndex
	 */
	public void updateCsShareIndex$NotNull(CsShareIndex csShareIndex);

	/**
	 * 根据ID删除一个分享指数
	 * @param id
	 */
	public void deleteCsShareIndexById(Long id);
	/**
	 * 根据ID逻辑删除一个分享指数
	 * @param id
	 */
	public void removeCsShareIndexById(Long id);
	/**
	 * 根据条件更新分享指数
	 * @param values
	 * @param params
	 */
	public void updateCsShareIndexByConfirm(Map values, Map params);
	/**
	 * 根据条件删除分享指数
	 * @param params
	 */
	public void deleteCsShareIndexByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}