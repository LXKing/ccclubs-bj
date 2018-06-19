package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvQuery;

/**
 * 表单查询的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvQueryDao
{
	/**
	 * 获取所有表单查询
	 * @return
	 */
	public List<SrvQuery> getSrvQueryList(Map params,Integer size);
	
	/**
	 * 获取表单查询统计
	 * @return
	 */
	public List getSrvQueryStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取表单查询总数
	 * @return
	 */	
	public Long getSrvQueryCount(Map params);
	
	/**
	 * 获取表单查询自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvQueryEval(String eval,Map params);
	
	/**
	 * 获取表单查询分页
	 * @return
	 */	
	public Page getSrvQueryPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取表单查询
	 * @param params
	 * @return
	 */
	public SrvQuery getSrvQuery(Map params);	

	/**
	 * 根据ID取表单查询
	 * @param id
	 * @return
	 */
	public SrvQuery getSrvQueryById(Long id);


	
	

	/**
	 * 保存表单查询
	 * @param srvQuery
	 */
	public SrvQuery saveSrvQuery(SrvQuery srvQuery);

	/**
	 * 更新表单查询
	 * @param srvQuery
	 */
	public void updateSrvQuery(SrvQuery srvQuery);
	/**
	 * 更新表单查询非空字段
	 * @param srvQuery
	 */
	public void updateSrvQuery$NotNull(SrvQuery srvQuery);

	/**
	 * 根据ID删除一个表单查询
	 * @param id
	 */
	public void deleteSrvQueryById(Long id);
	/**
	 * 根据ID逻辑删除一个表单查询
	 * @param id
	 */
	public void removeSrvQueryById(Long id);
	/**
	 * 根据条件更新表单查询
	 * @param values
	 * @param params
	 */
	public void updateSrvQueryByConfirm(Map values, Map params);
	/**
	 * 根据条件删除表单查询
	 * @param params
	 */
	public void deleteSrvQueryByConfirm(Map params);

}