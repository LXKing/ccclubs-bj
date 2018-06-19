package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvHost;

/**
 * 运营城市的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvHostDao
{
	/**
	 * 获取所有运营城市
	 * @return
	 */
	public List<SrvHost> getSrvHostList(Map params,Integer size);
	
	/**
	 * 获取运营城市统计
	 * @return
	 */
	public List getSrvHostStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取运营城市总数
	 * @return
	 */	
	public Long getSrvHostCount(Map params);
	
	/**
	 * 获取运营城市自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvHostEval(String eval,Map params);
	
	/**
	 * 获取运营城市分页
	 * @return
	 */	
	public Page getSrvHostPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取运营城市
	 * @param params
	 * @return
	 */
	public SrvHost getSrvHost(Map params);	

	/**
	 * 根据ID取运营城市
	 * @param id
	 * @return
	 */
	public SrvHost getSrvHostById(Long id);


	
	

	/**
	 * 保存运营城市
	 * @param srvHost
	 */
	public SrvHost saveSrvHost(SrvHost srvHost);

	/**
	 * 更新运营城市
	 * @param srvHost
	 */
	public void updateSrvHost(SrvHost srvHost);
	/**
	 * 更新运营城市非空字段
	 * @param srvHost
	 */
	public void updateSrvHost$NotNull(SrvHost srvHost);

	/**
	 * 根据ID删除一个运营城市
	 * @param id
	 */
	public void deleteSrvHostById(Long id);
	/**
	 * 根据ID逻辑删除一个运营城市
	 * @param id
	 */
	public void removeSrvHostById(Long id);
	/**
	 * 根据条件更新运营城市
	 * @param values
	 * @param params
	 */
	public void updateSrvHostByConfirm(Map values, Map params);
	/**
	 * 根据条件删除运营城市
	 * @param params
	 */
	public void deleteSrvHostByConfirm(Map params);

}