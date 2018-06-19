package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsAreaDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArea;
import com.ccclubs.service.admin.ICsAreaService;
import com.lazy3q.web.helper.$;

/**
 * 网点区域的Service实现
 * @author 飞啊飘啊
 */
public class CsAreaService implements ICsAreaService
{
	ICsAreaDao csAreaDao;
	

	/**
	 * 获取所有网点区域
	 * @return
	 */
	public List<CsArea> getCsAreaList(Map params,Integer size)
	{
		return csAreaDao.getCsAreaList(params,size);
	}
	
	/**
	 * 获取网点区域统计
	 * @return
	 */
	public List getCsAreaStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csAreaDao.getCsAreaStats(params,groups,sums);
	}
	
	/**
	 * 获取网点区域总数
	 * @return
	 */
	public Long getCsAreaCount(Map params)
	{
		return csAreaDao.getCsAreaCount(params);
	}
	
	/**
	 * 获取网点区域自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAreaEval(String eval,Map params)
	{
		return csAreaDao.getCsAreaEval(eval,params);
	}

	/**
	 * 获取网点区域分页
	 * @return
	 */
	public Page getCsAreaPage(int page,int size,Map params)
	{
		return csAreaDao.getCsAreaPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取网点区域
	 * @param params
	 * @return
	 */
	public CsArea getCsArea(Map params)
	{
		return csAreaDao.getCsArea(params);
	}

	/**
	 * 根据ID取网点区域
	 * @param id
	 * @return
	 */
	public CsArea getCsAreaById(Long id)
	{
		return csAreaDao.getCsAreaById(id);
	}

	/**
	 * 保存网点区域
	 * @param csArea
	 */
	public CsArea saveCsArea(CsArea csArea)
	{
		return csAreaDao.saveCsArea(csArea);
	}
	
	/**
	 * 更新网点区域
	 * @param csArea
	 */
	public void updateCsArea(CsArea csArea)
	{
		csAreaDao.updateCsArea(csArea);
	}
	/**
	 * 更新网点区域非空字段
	 * @param csArea
	 */
	public void updateCsArea$NotNull(CsArea csArea)
	{
		csAreaDao.updateCsArea$NotNull(csArea);
	}
	
	/**
	 * 根据ID删除一个网点区域
	 * @param id
	 */
	public void deleteCsAreaById(Long id)
	{
		csAreaDao.deleteCsAreaById(id);
	}
	/**
	 * 根据ID逻辑删除一个网点区域
	 * @param id
	 */
	public void removeCsAreaById(Long id)
	{
		csAreaDao.removeCsAreaById(id);
	}
	
	/**
	 * 根据条件更新网点区域
	 * @param values
	 * @param params
	 */
	public void updateCsAreaByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsAreaBy时无条件");
		csAreaDao.updateCsAreaByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除网点区域
	 * @param params
	 */
	public void deleteCsAreaByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsAreaBy时无条件");
		csAreaDao.deleteCsAreaByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsAreaDao getCsAreaDao()
	{
		return csAreaDao;
	}

	public void setCsAreaDao(ICsAreaDao csAreaDao)
	{
		this.csAreaDao = csAreaDao;
	}
}