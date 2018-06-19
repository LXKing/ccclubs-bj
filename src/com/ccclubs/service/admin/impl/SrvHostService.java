package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvHostDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ISrvHostService;
import com.lazy3q.web.helper.$;

/**
 * 运营城市的Service实现
 * @author 飞啊飘啊
 */
public class SrvHostService implements ISrvHostService
{
	ISrvHostDao srvHostDao;
	

	/**
	 * 获取所有运营城市
	 * @return
	 */
	public List<SrvHost> getSrvHostList(Map params,Integer size)
	{
		return srvHostDao.getSrvHostList(params,size);
	}
	
	/**
	 * 获取运营城市统计
	 * @return
	 */
	public List getSrvHostStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvHostDao.getSrvHostStats(params,groups,sums);
	}
	
	/**
	 * 获取运营城市总数
	 * @return
	 */
	public Long getSrvHostCount(Map params)
	{
		return srvHostDao.getSrvHostCount(params);
	}
	
	/**
	 * 获取运营城市自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvHostEval(String eval,Map params)
	{
		return srvHostDao.getSrvHostEval(eval,params);
	}

	/**
	 * 获取运营城市分页
	 * @return
	 */
	public Page getSrvHostPage(int page,int size,Map params)
	{
		return srvHostDao.getSrvHostPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取运营城市
	 * @param params
	 * @return
	 */
	public SrvHost getSrvHost(Map params)
	{
		return srvHostDao.getSrvHost(params);
	}

	/**
	 * 根据ID取运营城市
	 * @param id
	 * @return
	 */
	public SrvHost getSrvHostById(Long id)
	{
		return srvHostDao.getSrvHostById(id);
	}

	/**
	 * 保存运营城市
	 * @param srvHost
	 */
	public SrvHost saveSrvHost(SrvHost srvHost)
	{
		return srvHostDao.saveSrvHost(srvHost);
	}
	
	/**
	 * 更新运营城市
	 * @param srvHost
	 */
	public void updateSrvHost(SrvHost srvHost)
	{
		srvHostDao.updateSrvHost(srvHost);
	}
	/**
	 * 更新运营城市非空字段
	 * @param srvHost
	 */
	public void updateSrvHost$NotNull(SrvHost srvHost)
	{
		srvHostDao.updateSrvHost$NotNull(srvHost);
	}
	
	/**
	 * 根据ID删除一个运营城市
	 * @param id
	 */
	public void deleteSrvHostById(Long id)
	{
		srvHostDao.deleteSrvHostById(id);
	}
	/**
	 * 根据ID逻辑删除一个运营城市
	 * @param id
	 */
	public void removeSrvHostById(Long id)
	{
		srvHostDao.removeSrvHostById(id);
	}
	
	/**
	 * 根据条件更新运营城市
	 * @param values
	 * @param params
	 */
	public void updateSrvHostByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvHostBy时无条件");
		srvHostDao.updateSrvHostByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除运营城市
	 * @param params
	 */
	public void deleteSrvHostByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvHostBy时无条件");
		srvHostDao.deleteSrvHostByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvHostDao getSrvHostDao()
	{
		return srvHostDao;
	}

	public void setSrvHostDao(ISrvHostDao srvHostDao)
	{
		this.srvHostDao = srvHostDao;
	}
}