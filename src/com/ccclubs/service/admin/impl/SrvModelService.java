package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvModelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvModel;
import com.ccclubs.service.admin.ISrvModelService;
import com.lazy3q.web.helper.$;

/**
 * 表单设计的Service实现
 * @author 飞啊飘啊
 */
public class SrvModelService implements ISrvModelService
{
	ISrvModelDao srvModelDao;
	

	/**
	 * 获取所有表单设计
	 * @return
	 */
	public List<SrvModel> getSrvModelList(Map params,Integer size)
	{
		return srvModelDao.getSrvModelList(params,size);
	}
	
	/**
	 * 获取表单设计统计
	 * @return
	 */
	public List getSrvModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvModelDao.getSrvModelStats(params,groups,sums);
	}
	
	/**
	 * 获取表单设计总数
	 * @return
	 */
	public Long getSrvModelCount(Map params)
	{
		return srvModelDao.getSrvModelCount(params);
	}
	
	/**
	 * 获取表单设计自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvModelEval(String eval,Map params)
	{
		return srvModelDao.getSrvModelEval(eval,params);
	}

	/**
	 * 获取表单设计分页
	 * @return
	 */
	public Page getSrvModelPage(int page,int size,Map params)
	{
		return srvModelDao.getSrvModelPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取表单设计
	 * @param params
	 * @return
	 */
	public SrvModel getSrvModel(Map params)
	{
		return srvModelDao.getSrvModel(params);
	}

	/**
	 * 根据ID取表单设计
	 * @param id
	 * @return
	 */
	public SrvModel getSrvModelById(Long id)
	{
		return srvModelDao.getSrvModelById(id);
	}

	/**
	 * 保存表单设计
	 * @param srvModel
	 */
	public SrvModel saveSrvModel(SrvModel srvModel)
	{
		return srvModelDao.saveSrvModel(srvModel);
	}
	
	/**
	 * 更新表单设计
	 * @param srvModel
	 */
	public void updateSrvModel(SrvModel srvModel)
	{
		srvModelDao.updateSrvModel(srvModel);
	}
	/**
	 * 更新表单设计非空字段
	 * @param srvModel
	 */
	public void updateSrvModel$NotNull(SrvModel srvModel)
	{
		srvModelDao.updateSrvModel$NotNull(srvModel);
	}
	
	/**
	 * 根据ID删除一个表单设计
	 * @param id
	 */
	public void deleteSrvModelById(Long id)
	{
		srvModelDao.deleteSrvModelById(id);
	}
	/**
	 * 根据ID逻辑删除一个表单设计
	 * @param id
	 */
	public void removeSrvModelById(Long id)
	{
		srvModelDao.removeSrvModelById(id);
	}
	
	/**
	 * 根据条件更新表单设计
	 * @param values
	 * @param params
	 */
	public void updateSrvModelByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvModelBy时无条件");
		srvModelDao.updateSrvModelByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除表单设计
	 * @param params
	 */
	public void deleteSrvModelByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvModelBy时无条件");
		srvModelDao.deleteSrvModelByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvModelDao getSrvModelDao()
	{
		return srvModelDao;
	}

	public void setSrvModelDao(ISrvModelDao srvModelDao)
	{
		this.srvModelDao = srvModelDao;
	}
}