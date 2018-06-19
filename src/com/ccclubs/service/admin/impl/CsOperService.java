package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOperDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOper;
import com.ccclubs.service.admin.ICsOperService;
import com.lazy3q.web.helper.$;

/**
 * 运营商的Service实现
 * @author 飞啊飘啊
 */
public class CsOperService implements ICsOperService
{
	ICsOperDao csOperDao;
	

	/**
	 * 获取所有运营商
	 * @return
	 */
	public List<CsOper> getCsOperList(Map params,Integer size)
	{
		return csOperDao.getCsOperList(params,size);
	}
	
	/**
	 * 获取运营商统计
	 * @return
	 */
	public List getCsOperStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOperDao.getCsOperStats(params,groups,sums);
	}
	
	/**
	 * 获取运营商总数
	 * @return
	 */
	public Long getCsOperCount(Map params)
	{
		return csOperDao.getCsOperCount(params);
	}
	
	/**
	 * 获取运营商自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOperEval(String eval,Map params)
	{
		return csOperDao.getCsOperEval(eval,params);
	}

	/**
	 * 获取运营商分页
	 * @return
	 */
	public Page getCsOperPage(int page,int size,Map params)
	{
		return csOperDao.getCsOperPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取运营商
	 * @param params
	 * @return
	 */
	public CsOper getCsOper(Map params)
	{
		return csOperDao.getCsOper(params);
	}

	/**
	 * 根据ID取运营商
	 * @param id
	 * @return
	 */
	public CsOper getCsOperById(Long id)
	{
		return csOperDao.getCsOperById(id);
	}

	/**
	 * 保存运营商
	 * @param csOper
	 */
	public CsOper saveCsOper(CsOper csOper)
	{
		return csOperDao.saveCsOper(csOper);
	}
	
	/**
	 * 更新运营商
	 * @param csOper
	 */
	public void updateCsOper(CsOper csOper)
	{
		csOperDao.updateCsOper(csOper);
	}
	/**
	 * 更新运营商非空字段
	 * @param csOper
	 */
	public void updateCsOper$NotNull(CsOper csOper)
	{
		csOperDao.updateCsOper$NotNull(csOper);
	}
	
	/**
	 * 根据ID删除一个运营商
	 * @param id
	 */
	public void deleteCsOperById(Long id)
	{
		csOperDao.deleteCsOperById(id);
	}
	/**
	 * 根据ID逻辑删除一个运营商
	 * @param id
	 */
	public void removeCsOperById(Long id)
	{
		csOperDao.removeCsOperById(id);
	}
	
	/**
	 * 根据条件更新运营商
	 * @param values
	 * @param params
	 */
	public void updateCsOperByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOperBy时无条件");
		csOperDao.updateCsOperByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除运营商
	 * @param params
	 */
	public void deleteCsOperByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOperBy时无条件");
		csOperDao.deleteCsOperByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOperDao getCsOperDao()
	{
		return csOperDao;
	}

	public void setCsOperDao(ICsOperDao csOperDao)
	{
		this.csOperDao = csOperDao;
	}
}