package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOutsideQrDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOutsideQr;
import com.ccclubs.service.admin.ICsOutsideQrService;
import com.lazy3q.web.helper.$;

/**
 * 巡检作业的Service实现
 * @author 飞啊飘啊
 */
public class CsOutsideQrService implements ICsOutsideQrService
{
	ICsOutsideQrDao csOutsideQrDao;
	

	/**
	 * 获取所有巡检作业
	 * @return
	 */
	public List<CsOutsideQr> getCsOutsideQrList(Map params,Integer size)
	{
		return csOutsideQrDao.getCsOutsideQrList(params,size);
	}
	
	/**
	 * 获取巡检作业统计
	 * @return
	 */
	public List getCsOutsideQrStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOutsideQrDao.getCsOutsideQrStats(params,groups,sums);
	}
	
	/**
	 * 获取巡检作业总数
	 * @return
	 */
	public Long getCsOutsideQrCount(Map params)
	{
		return csOutsideQrDao.getCsOutsideQrCount(params);
	}
	
	/**
	 * 获取巡检作业自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOutsideQrEval(String eval,Map params)
	{
		return csOutsideQrDao.getCsOutsideQrEval(eval,params);
	}

	/**
	 * 获取巡检作业分页
	 * @return
	 */
	public Page getCsOutsideQrPage(int page,int size,Map params)
	{
		return csOutsideQrDao.getCsOutsideQrPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取巡检作业
	 * @param params
	 * @return
	 */
	public CsOutsideQr getCsOutsideQr(Map params)
	{
		return csOutsideQrDao.getCsOutsideQr(params);
	}

	/**
	 * 根据ID取巡检作业
	 * @param id
	 * @return
	 */
	public CsOutsideQr getCsOutsideQrById(Long id)
	{
		return csOutsideQrDao.getCsOutsideQrById(id);
	}

	/**
	 * 保存巡检作业
	 * @param csOutsideQr
	 */
	public CsOutsideQr saveCsOutsideQr(CsOutsideQr csOutsideQr)
	{
		return csOutsideQrDao.saveCsOutsideQr(csOutsideQr);
	}
	
	/**
	 * 更新巡检作业
	 * @param csOutsideQr
	 */
	public void updateCsOutsideQr(CsOutsideQr csOutsideQr)
	{
		csOutsideQrDao.updateCsOutsideQr(csOutsideQr);
	}
	/**
	 * 更新巡检作业非空字段
	 * @param csOutsideQr
	 */
	public void updateCsOutsideQr$NotNull(CsOutsideQr csOutsideQr)
	{
		csOutsideQrDao.updateCsOutsideQr$NotNull(csOutsideQr);
	}
	
	/**
	 * 根据ID删除一个巡检作业
	 * @param id
	 */
	public void deleteCsOutsideQrById(Long id)
	{
		csOutsideQrDao.deleteCsOutsideQrById(id);
	}
	/**
	 * 根据ID逻辑删除一个巡检作业
	 * @param id
	 */
	public void removeCsOutsideQrById(Long id)
	{
		csOutsideQrDao.removeCsOutsideQrById(id);
	}
	
	/**
	 * 根据条件更新巡检作业
	 * @param values
	 * @param params
	 */
	public void updateCsOutsideQrByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOutsideQrBy时无条件");
		csOutsideQrDao.updateCsOutsideQrByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除巡检作业
	 * @param params
	 */
	public void deleteCsOutsideQrByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOutsideQrBy时无条件");
		csOutsideQrDao.deleteCsOutsideQrByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOutsideQrDao getCsOutsideQrDao()
	{
		return csOutsideQrDao;
	}

	public void setCsOutsideQrDao(ICsOutsideQrDao csOutsideQrDao)
	{
		this.csOutsideQrDao = csOutsideQrDao;
	}
}