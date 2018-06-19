package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLongOrderDetailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrderDetail;
import com.ccclubs.service.admin.ICsLongOrderDetailService;
import com.lazy3q.web.helper.$;

/**
 * 长单明细的Service实现
 * @author 飞啊飘啊
 */
public class CsLongOrderDetailService implements ICsLongOrderDetailService
{
	ICsLongOrderDetailDao csLongOrderDetailDao;
	

	/**
	 * 获取所有长单明细
	 * @return
	 */
	public List<CsLongOrderDetail> getCsLongOrderDetailList(Map params,Integer size)
	{
		return csLongOrderDetailDao.getCsLongOrderDetailList(params,size);
	}
	
	/**
	 * 获取长单明细统计
	 * @return
	 */
	public List getCsLongOrderDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLongOrderDetailDao.getCsLongOrderDetailStats(params,groups,sums);
	}
	
	/**
	 * 获取长单明细总数
	 * @return
	 */
	public Long getCsLongOrderDetailCount(Map params)
	{
		return csLongOrderDetailDao.getCsLongOrderDetailCount(params);
	}
	
	/**
	 * 获取长单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderDetailEval(String eval,Map params)
	{
		return csLongOrderDetailDao.getCsLongOrderDetailEval(eval,params);
	}

	/**
	 * 获取长单明细分页
	 * @return
	 */
	public Page getCsLongOrderDetailPage(int page,int size,Map params)
	{
		return csLongOrderDetailDao.getCsLongOrderDetailPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取长单明细
	 * @param params
	 * @return
	 */
	public CsLongOrderDetail getCsLongOrderDetail(Map params)
	{
		return csLongOrderDetailDao.getCsLongOrderDetail(params);
	}

	/**
	 * 根据ID取长单明细
	 * @param id
	 * @return
	 */
	public CsLongOrderDetail getCsLongOrderDetailById(Long id)
	{
		return csLongOrderDetailDao.getCsLongOrderDetailById(id);
	}

	/**
	 * 保存长单明细
	 * @param csLongOrderDetail
	 */
	public CsLongOrderDetail saveCsLongOrderDetail(CsLongOrderDetail csLongOrderDetail)
	{
		return csLongOrderDetailDao.saveCsLongOrderDetail(csLongOrderDetail);
	}
	
	/**
	 * 更新长单明细
	 * @param csLongOrderDetail
	 */
	public void updateCsLongOrderDetail(CsLongOrderDetail csLongOrderDetail)
	{
		csLongOrderDetailDao.updateCsLongOrderDetail(csLongOrderDetail);
	}
	/**
	 * 更新长单明细非空字段
	 * @param csLongOrderDetail
	 */
	public void updateCsLongOrderDetail$NotNull(CsLongOrderDetail csLongOrderDetail)
	{
		csLongOrderDetailDao.updateCsLongOrderDetail$NotNull(csLongOrderDetail);
	}
	
	/**
	 * 根据ID删除一个长单明细
	 * @param id
	 */
	public void deleteCsLongOrderDetailById(Long id)
	{
		csLongOrderDetailDao.deleteCsLongOrderDetailById(id);
	}
	/**
	 * 根据ID逻辑删除一个长单明细
	 * @param id
	 */
	public void removeCsLongOrderDetailById(Long id)
	{
		csLongOrderDetailDao.removeCsLongOrderDetailById(id);
	}
	
	/**
	 * 根据条件更新长单明细
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderDetailByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLongOrderDetailBy时无条件");
		csLongOrderDetailDao.updateCsLongOrderDetailByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除长单明细
	 * @param params
	 */
	public void deleteCsLongOrderDetailByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLongOrderDetailBy时无条件");
		csLongOrderDetailDao.deleteCsLongOrderDetailByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLongOrderDetailDao getCsLongOrderDetailDao()
	{
		return csLongOrderDetailDao;
	}

	public void setCsLongOrderDetailDao(ICsLongOrderDetailDao csLongOrderDetailDao)
	{
		this.csLongOrderDetailDao = csLongOrderDetailDao;
	}
}