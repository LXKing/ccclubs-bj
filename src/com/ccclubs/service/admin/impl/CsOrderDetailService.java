package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOrderDetailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.service.admin.ICsOrderDetailService;
import com.lazy3q.web.helper.$;

/**
 * 订单明细的Service实现
 * @author 飞啊飘啊
 */
public class CsOrderDetailService implements ICsOrderDetailService
{
	ICsOrderDetailDao csOrderDetailDao;
	

	/**
	 * 获取所有订单明细
	 * @return
	 */
	public List<CsOrderDetail> getCsOrderDetailList(Map params,Integer size)
	{
		return csOrderDetailDao.getCsOrderDetailList(params,size);
	}
	
	/**
	 * 获取订单明细统计
	 * @return
	 */
	public List getCsOrderDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOrderDetailDao.getCsOrderDetailStats(params,groups,sums);
	}
	
	/**
	 * 获取订单明细总数
	 * @return
	 */
	public Long getCsOrderDetailCount(Map params)
	{
		return csOrderDetailDao.getCsOrderDetailCount(params);
	}
	
	/**
	 * 获取订单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderDetailEval(String eval,Map params)
	{
		return csOrderDetailDao.getCsOrderDetailEval(eval,params);
	}

	/**
	 * 获取订单明细分页
	 * @return
	 */
	public Page getCsOrderDetailPage(int page,int size,Map params)
	{
		return csOrderDetailDao.getCsOrderDetailPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public CsOrderDetail getCsOrderDetail(Map params)
	{
		return csOrderDetailDao.getCsOrderDetail(params);
	}

	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public CsOrderDetail getCsOrderDetailById(Long id)
	{
		return csOrderDetailDao.getCsOrderDetailById(id);
	}

	/**
	 * 保存订单明细
	 * @param csOrderDetail
	 */
	public CsOrderDetail saveCsOrderDetail(CsOrderDetail csOrderDetail)
	{
		return csOrderDetailDao.saveCsOrderDetail(csOrderDetail);
	}
	
	/**
	 * 更新订单明细
	 * @param csOrderDetail
	 */
	public void updateCsOrderDetail(CsOrderDetail csOrderDetail)
	{
		csOrderDetailDao.updateCsOrderDetail(csOrderDetail);
	}
	/**
	 * 更新订单明细非空字段
	 * @param csOrderDetail
	 */
	public void updateCsOrderDetail$NotNull(CsOrderDetail csOrderDetail)
	{
		csOrderDetailDao.updateCsOrderDetail$NotNull(csOrderDetail);
	}
	
	/**
	 * 根据ID删除一个订单明细
	 * @param id
	 */
	public void deleteCsOrderDetailById(Long id)
	{
		csOrderDetailDao.deleteCsOrderDetailById(id);
	}
	
	/**
	 * 根据条件更新订单明细
	 * @param values
	 * @param params
	 */
	public void updateCsOrderDetailByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOrderDetailBy时无条件");
		csOrderDetailDao.updateCsOrderDetailByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除订单明细
	 * @param params
	 */
	public void deleteCsOrderDetailByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOrderDetailBy时无条件");
		csOrderDetailDao.deleteCsOrderDetailByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOrderDetailDao getCsOrderDetailDao()
	{
		return csOrderDetailDao;
	}

	public void setCsOrderDetailDao(ICsOrderDetailDao csOrderDetailDao)
	{
		this.csOrderDetailDao = csOrderDetailDao;
	}
}