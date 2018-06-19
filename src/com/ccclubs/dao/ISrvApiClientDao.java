package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApiClient;

/**
 * API客户端的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvApiClientDao
{
	/**
	 * 获取所有API客户端
	 * @return
	 */
	public List<SrvApiClient> getSrvApiClientList(Map params,Integer size);
	
	/**
	 * 获取API客户端统计
	 * @return
	 */
	public List getSrvApiClientStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取API客户端总数
	 * @return
	 */	
	public Long getSrvApiClientCount(Map params);
	
	/**
	 * 获取API客户端自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvApiClientEval(String eval,Map params);
	
	/**
	 * 获取API客户端分页
	 * @return
	 */	
	public Page getSrvApiClientPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取API客户端
	 * @param params
	 * @return
	 */
	public SrvApiClient getSrvApiClient(Map params);	

	/**
	 * 根据ID取API客户端
	 * @param id
	 * @return
	 */
	public SrvApiClient getSrvApiClientById(Long id);


	
	

	/**
	 * 保存API客户端
	 * @param srvApiClient
	 */
	public SrvApiClient saveSrvApiClient(SrvApiClient srvApiClient);

	/**
	 * 更新API客户端
	 * @param srvApiClient
	 */
	public void updateSrvApiClient(SrvApiClient srvApiClient);
	/**
	 * 更新API客户端非空字段
	 * @param srvApiClient
	 */
	public void updateSrvApiClient$NotNull(SrvApiClient srvApiClient);

	/**
	 * 根据ID删除一个API客户端
	 * @param id
	 */
	public void deleteSrvApiClientById(Long id);
	/**
	 * 根据ID逻辑删除一个API客户端
	 * @param id
	 */
	public void removeSrvApiClientById(Long id);
	/**
	 * 根据条件更新API客户端
	 * @param values
	 * @param params
	 */
	public void updateSrvApiClientByConfirm(Map values, Map params);
	/**
	 * 根据条件删除API客户端
	 * @param params
	 */
	public void deleteSrvApiClientByConfirm(Map params);

}