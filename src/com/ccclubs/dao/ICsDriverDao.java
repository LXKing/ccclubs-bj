package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsDriver;

/**
 * 专车司机的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsDriverDao
{
	/**
	 * 获取所有专车司机
	 * @return
	 */
	public List<CsDriver> getCsDriverList(Map params,Integer size);
	
	/**
	 * 获取专车司机统计
	 * @return
	 */
	public List getCsDriverStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取专车司机总数
	 * @return
	 */	
	public Long getCsDriverCount(Map params);
	
	/**
	 * 获取专车司机自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsDriverEval(String eval,Map params);
	
	/**
	 * 获取专车司机分页
	 * @return
	 */	
	public Page getCsDriverPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取专车司机
	 * @param params
	 * @return
	 */
	public CsDriver getCsDriver(Map params);	

	/**
	 * 根据ID取专车司机
	 * @param id
	 * @return
	 */
	public CsDriver getCsDriverById(Long id);


	
	

	/**
	 * 保存专车司机
	 * @param csDriver
	 */
	public CsDriver saveCsDriver(CsDriver csDriver);

	/**
	 * 更新专车司机
	 * @param csDriver
	 */
	public void updateCsDriver(CsDriver csDriver);
	/**
	 * 更新专车司机非空字段
	 * @param csDriver
	 */
	public void updateCsDriver$NotNull(CsDriver csDriver);

	/**
	 * 根据ID删除一个专车司机
	 * @param id
	 */
	public void deleteCsDriverById(Long id);
	/**
	 * 根据ID逻辑删除一个专车司机
	 * @param id
	 */
	public void removeCsDriverById(Long id);
	/**
	 * 根据条件更新专车司机
	 * @param values
	 * @param params
	 */
	public void updateCsDriverByConfirm(Map values, Map params);
	/**
	 * 根据条件删除专车司机
	 * @param params
	 */
	public void deleteCsDriverByConfirm(Map params);

}