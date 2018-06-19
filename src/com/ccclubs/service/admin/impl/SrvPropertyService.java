package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvPropertyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.lazy3q.web.helper.$;

/**
 * 系统属性的Service实现
 * @author 飞啊飘啊
 */
public class SrvPropertyService implements ISrvPropertyService
{
	ISrvPropertyDao srvPropertyDao;
	

	/**
	 * 获取所有系统属性
	 * @return
	 */
	public List<SrvProperty> getSrvPropertyList(Map params,Integer size)
	{
		return srvPropertyDao.getSrvPropertyList(params,size);
	}
	
	/**
	 * 获取系统属性统计
	 * @return
	 */
	public List getSrvPropertyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvPropertyDao.getSrvPropertyStats(params,groups,sums);
	}
	
	/**
	 * 获取系统属性总数
	 * @return
	 */
	public Long getSrvPropertyCount(Map params)
	{
		return srvPropertyDao.getSrvPropertyCount(params);
	}
	
	/**
	 * 获取系统属性自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvPropertyEval(String eval,Map params)
	{
		return srvPropertyDao.getSrvPropertyEval(eval,params);
	}

	/**
	 * 获取系统属性分页
	 * @return
	 */
	public Page getSrvPropertyPage(int page,int size,Map params)
	{
		return srvPropertyDao.getSrvPropertyPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统属性
	 * @param params
	 * @return
	 */
	public SrvProperty getSrvProperty(Map params)
	{
		return srvPropertyDao.getSrvProperty(params);
	}

	/**
	 * 根据ID取系统属性
	 * @param id
	 * @return
	 */
	public SrvProperty getSrvPropertyById(Long id)
	{
		return srvPropertyDao.getSrvPropertyById(id);
	}

	/**
	 * 保存系统属性
	 * @param srvProperty
	 */
	public SrvProperty saveSrvProperty(SrvProperty srvProperty)
	{
		return srvPropertyDao.saveSrvProperty(srvProperty);
	}
	
	/**
	 * 更新系统属性
	 * @param srvProperty
	 */
	public void updateSrvProperty(SrvProperty srvProperty)
	{
		srvPropertyDao.updateSrvProperty(srvProperty);
	}
	/**
	 * 更新系统属性非空字段
	 * @param srvProperty
	 */
	public void updateSrvProperty$NotNull(SrvProperty srvProperty)
	{
		srvPropertyDao.updateSrvProperty$NotNull(srvProperty);
	}
	
	/**
	 * 根据ID删除一个系统属性
	 * @param id
	 */
	public void deleteSrvPropertyById(Long id)
	{
		srvPropertyDao.deleteSrvPropertyById(id);
		List<SrvProperty> list = srvPropertyDao.getSrvPropertyList($.Map().add("spParent", id),-1);
		while(!list.isEmpty()){
			String strIds="";
			for(SrvProperty srvProperty:list){
				if(!strIds.equals(""))
					strIds+=",";
				strIds+=srvProperty.getSpId();
			}
			srvPropertyDao.deleteSrvPropertyByConfirm($.Map().add("definex", "sp_id in ("+strIds+")").add("confirm",1));
			list=srvPropertyDao.getSrvPropertyList($.Map().add("definex", "sp_parent in ("+strIds+")"),-1);
		}
	}
	
	/**
	 * 根据条件更新系统属性
	 * @param values
	 * @param params
	 */
	public void updateSrvPropertyByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvPropertyBy时无条件");
		srvPropertyDao.updateSrvPropertyByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统属性
	 * @param params
	 */
	public void deleteSrvPropertyByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvPropertyBy时无条件");
		srvPropertyDao.deleteSrvPropertyByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvPropertyDao getSrvPropertyDao()
	{
		return srvPropertyDao;
	}

	public void setSrvPropertyDao(ISrvPropertyDao srvPropertyDao)
	{
		this.srvPropertyDao = srvPropertyDao;
	}
}