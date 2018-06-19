package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.SrvActionScript;

/**
 * 后台事务脚本的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvActionScriptService
{
	/**
	 * 获取所有后台事务脚本
	 * @return
	 */
	public List<SrvActionScript> getSrvActionScriptList(Map params,Integer size);
	
	/**
	 * 获取后台事务脚本统计
	 * @return
	 */
	public List getSrvActionScriptStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取后台事务脚本总数
	 * @return
	 */	
	public Long getSrvActionScriptCount(Map params);
	
	
	/**
	 * 获取后台事务脚本自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvActionScriptEval(String eval,Map params);

	/**
	 * 获取后台事务脚本分页
	 * @return
	 */	
	public Page getSrvActionScriptPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取后台事务脚本
	 * @param params
	 * @return
	 */
	public SrvActionScript getSrvActionScript(Map params);

	/**
	 * 根据ID取后台事务脚本
	 * @param id
	 * @return
	 */
	public SrvActionScript getSrvActionScriptById(Long id);


	/**
	 * 保存后台事务脚本
	 * @param srvActionScript
	 */
	public SrvActionScript saveSrvActionScript(SrvActionScript srvActionScript);

	/**
	 * 更新后台事务脚本
	 * @param srvActionScript
	 */
	public void updateSrvActionScript(SrvActionScript srvActionScript);
	/**
	 * 更新后台事务脚本非空字段
	 * @param srvActionScript
	 */
	public void updateSrvActionScript$NotNull(SrvActionScript srvActionScript);

	/**
	 * 根据ID删除一个后台事务脚本
	 * @param id
	 */
	public void deleteSrvActionScriptById(Long id);
	/**
	 * 根据ID逻辑删除一个后台事务脚本
	 * @param id
	 */
	public void removeSrvActionScriptById(Long id);
	/**
	 * 根据条件更新后台事务脚本
	 * @param values
	 * @param params
	 */
	public void updateSrvActionScriptByConfirm(Map values, Map params);
	/**
	 * 根据条件删除后台事务脚本
	 * @param params
	 */
	public void deleteSrvActionScriptByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}