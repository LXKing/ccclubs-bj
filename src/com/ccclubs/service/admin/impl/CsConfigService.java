package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsConfigDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsConfig;
import com.ccclubs.service.admin.ICsConfigService;
import com.lazy3q.web.helper.$;

/**
 * 系统配置(城市)的Service实现
 * @author 飞啊飘啊
 */
public class CsConfigService implements ICsConfigService
{
	ICsConfigDao csConfigDao;
	

	/**
	 * 获取所有系统配置(城市)
	 * @return
	 */
	public List<CsConfig> getCsConfigList(Map params,Integer size)
	{
		return csConfigDao.getCsConfigList(params,size);
	}
	
	/**
	 * 获取系统配置(城市)统计
	 * @return
	 */
	public List getCsConfigStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csConfigDao.getCsConfigStats(params,groups,sums);
	}
	
	/**
	 * 获取系统配置(城市)总数
	 * @return
	 */
	public Long getCsConfigCount(Map params)
	{
		return csConfigDao.getCsConfigCount(params);
	}
	
	/**
	 * 获取系统配置(城市)自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsConfigEval(String eval,Map params)
	{
		return csConfigDao.getCsConfigEval(eval,params);
	}

	/**
	 * 获取系统配置(城市)分页
	 * @return
	 */
	public Page getCsConfigPage(int page,int size,Map params)
	{
		return csConfigDao.getCsConfigPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统配置(城市)
	 * @param params
	 * @return
	 */
	public CsConfig getCsConfig(Map params)
	{
		return csConfigDao.getCsConfig(params);
	}

	/**
	 * 根据ID取系统配置(城市)
	 * @param id
	 * @return
	 */
	public CsConfig getCsConfigById(Long id)
	{
		return csConfigDao.getCsConfigById(id);
	}

	/**
	 * 保存系统配置(城市)
	 * @param csConfig
	 */
	public CsConfig saveCsConfig(CsConfig csConfig)
	{
		return csConfigDao.saveCsConfig(csConfig);
	}
	
	/**
	 * 更新系统配置(城市)
	 * @param csConfig
	 */
	public void updateCsConfig(CsConfig csConfig)
	{
		csConfigDao.updateCsConfig(csConfig);
	}
	/**
	 * 更新系统配置(城市)非空字段
	 * @param csConfig
	 */
	public void updateCsConfig$NotNull(CsConfig csConfig)
	{
		csConfigDao.updateCsConfig$NotNull(csConfig);
	}
	
	/**
	 * 根据ID删除一个系统配置(城市)
	 * @param id
	 */
	public void deleteCsConfigById(Long id)
	{
		csConfigDao.deleteCsConfigById(id);
	}
	
	/**
	 * 根据条件更新系统配置(城市)
	 * @param values
	 * @param params
	 */
	public void updateCsConfigByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsConfigBy时无条件");
		csConfigDao.updateCsConfigByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统配置(城市)
	 * @param params
	 */
	public void deleteCsConfigByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsConfigBy时无条件");
		csConfigDao.deleteCsConfigByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsConfigDao getCsConfigDao()
	{
		return csConfigDao;
	}

	public void setCsConfigDao(ICsConfigDao csConfigDao)
	{
		this.csConfigDao = csConfigDao;
	}
}