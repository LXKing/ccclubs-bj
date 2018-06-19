package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPlaceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPlace;
import com.ccclubs.service.admin.ICsPlaceService;
import com.lazy3q.web.helper.$;

/**
 * 省市地区的Service实现
 * @author 飞啊飘啊
 */
public class CsPlaceService implements ICsPlaceService
{
	ICsPlaceDao csPlaceDao;
	

	/**
	 * 获取所有省市地区
	 * @return
	 */
	public List<CsPlace> getCsPlaceList(Map params,Integer size)
	{
		return csPlaceDao.getCsPlaceList(params,size);
	}
	
	/**
	 * 获取省市地区统计
	 * @return
	 */
	public List getCsPlaceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPlaceDao.getCsPlaceStats(params,groups,sums);
	}
	
	/**
	 * 获取省市地区总数
	 * @return
	 */
	public Long getCsPlaceCount(Map params)
	{
		return csPlaceDao.getCsPlaceCount(params);
	}
	
	/**
	 * 获取省市地区自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPlaceEval(String eval,Map params)
	{
		return csPlaceDao.getCsPlaceEval(eval,params);
	}

	/**
	 * 获取省市地区分页
	 * @return
	 */
	public Page getCsPlacePage(int page,int size,Map params)
	{
		return csPlaceDao.getCsPlacePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取省市地区
	 * @param params
	 * @return
	 */
	public CsPlace getCsPlace(Map params)
	{
		return csPlaceDao.getCsPlace(params);
	}

	/**
	 * 根据ID取省市地区
	 * @param id
	 * @return
	 */
	public CsPlace getCsPlaceById(Long id)
	{
		return csPlaceDao.getCsPlaceById(id);
	}

	/**
	 * 保存省市地区
	 * @param csPlace
	 */
	public CsPlace saveCsPlace(CsPlace csPlace)
	{
		return csPlaceDao.saveCsPlace(csPlace);
	}
	
	/**
	 * 更新省市地区
	 * @param csPlace
	 */
	public void updateCsPlace(CsPlace csPlace)
	{
		csPlaceDao.updateCsPlace(csPlace);
	}
	/**
	 * 更新省市地区非空字段
	 * @param csPlace
	 */
	public void updateCsPlace$NotNull(CsPlace csPlace)
	{
		csPlaceDao.updateCsPlace$NotNull(csPlace);
	}
	
	/**
	 * 根据ID删除一个省市地区
	 * @param id
	 */
	public void deleteCsPlaceById(Long id)
	{
		csPlaceDao.deleteCsPlaceById(id);
		List<CsPlace> list = csPlaceDao.getCsPlaceList($.Map().add("cspParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(CsPlace csPlace:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=csPlace.getCspId();
			}
			csPlaceDao.deleteCsPlaceByConfirm($.Map().add("definex", "csp_id in ("+strIds+")").add("confirm",1));
			list=csPlaceDao.getCsPlaceList($.Map().add("definex", "csp_parent in ("+strIds+")"),-1);
		}
	}
	
	/**
	 * 根据条件更新省市地区
	 * @param values
	 * @param params
	 */
	public void updateCsPlaceByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPlaceBy时无条件");
		csPlaceDao.updateCsPlaceByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除省市地区
	 * @param params
	 */
	public void deleteCsPlaceByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPlaceBy时无条件");
		csPlaceDao.deleteCsPlaceByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPlaceDao getCsPlaceDao()
	{
		return csPlaceDao;
	}

	public void setCsPlaceDao(ICsPlaceDao csPlaceDao)
	{
		this.csPlaceDao = csPlaceDao;
	}
}