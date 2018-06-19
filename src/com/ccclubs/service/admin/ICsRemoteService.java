package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsRemote;

/**
 * 远程控制的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRemoteService
{
	/**
	 * 获取所有远程控制
	 * @return
	 */
	public List<CsRemote> getCsRemoteList(Map params,Integer size);
	
	/**
	 * 获取远程控制统计
	 * @return
	 */
	public List getCsRemoteStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取远程控制总数
	 * @return
	 */	
	public Long getCsRemoteCount(Map params);
	
	
	/**
	 * 获取远程控制自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRemoteEval(String eval,Map params);

	/**
	 * 获取远程控制分页
	 * @return
	 */	
	public Page getCsRemotePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取远程控制
	 * @param params
	 * @return
	 */
	public CsRemote getCsRemote(Map params);

	/**
	 * 根据ID取远程控制
	 * @param id
	 * @return
	 */
	public CsRemote getCsRemoteById(Long id);


	/**
	 * 保存远程控制
	 * @param csRemote
	 */
	public CsRemote saveCsRemote(CsRemote csRemote);

	/**
	 * 更新远程控制
	 * @param csRemote
	 */
	public void updateCsRemote(CsRemote csRemote);
	/**
	 * 更新远程控制非空字段
	 * @param csRemote
	 */
	public void updateCsRemote$NotNull(CsRemote csRemote);

	/**
	 * 根据ID删除一个远程控制
	 * @param id
	 */
	public void deleteCsRemoteById(Long id);
	/**
	 * 根据条件更新远程控制
	 * @param values
	 * @param params
	 */
	public void updateCsRemoteByConfirm(Map values, Map params);
	/**
	 * 根据条件删除远程控制
	 * @param params
	 */
	public void deleteCsRemoteByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}