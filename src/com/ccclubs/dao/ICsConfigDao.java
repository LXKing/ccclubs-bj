package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsConfig;

/**
 * 系统配置(城市)的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsConfigDao
{
	/**
	 * 获取所有系统配置(城市)
	 * @return
	 */
	public List<CsConfig> getCsConfigList(Map params,Integer size);
	
	/**
	 * 获取系统配置(城市)统计
	 * @return
	 */
	public List getCsConfigStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统配置(城市)总数
	 * @return
	 */	
	public Long getCsConfigCount(Map params);
	
	/**
	 * 获取系统配置(城市)自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsConfigEval(String eval,Map params);
	
	/**
	 * 获取系统配置(城市)分页
	 * @return
	 */	
	public Page getCsConfigPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统配置(城市)
	 * @param params
	 * @return
	 */
	public CsConfig getCsConfig(Map params);	

	/**
	 * 根据ID取系统配置(城市)
	 * @param id
	 * @return
	 */
	public CsConfig getCsConfigById(Long id);


	
	

	/**
	 * 保存系统配置(城市)
	 * @param csConfig
	 */
	public CsConfig saveCsConfig(CsConfig csConfig);

	/**
	 * 更新系统配置(城市)
	 * @param csConfig
	 */
	public void updateCsConfig(CsConfig csConfig);
	/**
	 * 更新系统配置(城市)非空字段
	 * @param csConfig
	 */
	public void updateCsConfig$NotNull(CsConfig csConfig);

	/**
	 * 根据ID删除一个系统配置(城市)
	 * @param id
	 */
	public void deleteCsConfigById(Long id);
	/**
	 * 根据条件更新系统配置(城市)
	 * @param values
	 * @param params
	 */
	public void updateCsConfigByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统配置(城市)
	 * @param params
	 */
	public void deleteCsConfigByConfirm(Map params);

}