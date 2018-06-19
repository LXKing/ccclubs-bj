package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.SrvApi;

/**
 * API接口的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvApiService
{
	/**
	 * 获取所有API接口
	 * @return
	 */
	public List<SrvApi> getSrvApiList(Map params,Integer size);
	
	/**
	 * 获取API接口统计
	 * @return
	 */
	public List getSrvApiStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取API接口总数
	 * @return
	 */	
	public Long getSrvApiCount(Map params);
	
	
	/**
	 * 获取API接口自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvApiEval(String eval,Map params);

	/**
	 * 获取API接口分页
	 * @return
	 */	
	public Page getSrvApiPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取API接口
	 * @param params
	 * @return
	 */
	public SrvApi getSrvApi(Map params);

	/**
	 * 根据ID取API接口
	 * @param id
	 * @return
	 */
	public SrvApi getSrvApiById(Long id);


	/**
	 * 保存API接口
	 * @param srvApi
	 */
	public SrvApi saveSrvApi(SrvApi srvApi);

	/**
	 * 更新API接口
	 * @param srvApi
	 */
	public void updateSrvApi(SrvApi srvApi);
	/**
	 * 更新API接口非空字段
	 * @param srvApi
	 */
	public void updateSrvApi$NotNull(SrvApi srvApi);

	/**
	 * 根据ID删除一个API接口
	 * @param id
	 */
	public void deleteSrvApiById(Long id);
	/**
	 * 根据ID逻辑删除一个API接口
	 * @param id
	 */
	public void removeSrvApiById(Long id);
	/**
	 * 根据条件更新API接口
	 * @param values
	 * @param params
	 */
	public void updateSrvApiByConfirm(Map values, Map params);
	/**
	 * 根据条件删除API接口
	 * @param params
	 */
	public void deleteSrvApiByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}