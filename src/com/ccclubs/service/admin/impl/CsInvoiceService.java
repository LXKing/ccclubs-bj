package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsInvoiceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsInvoice;
import com.ccclubs.service.admin.ICsInvoiceService;
import com.lazy3q.web.helper.$;

/**
 * 发票记录的Service实现
 * @author 飞啊飘啊
 */
public class CsInvoiceService implements ICsInvoiceService
{
	ICsInvoiceDao csInvoiceDao;
	

	/**
	 * 获取所有发票记录
	 * @return
	 */
	public List<CsInvoice> getCsInvoiceList(Map params,Integer size)
	{
		return csInvoiceDao.getCsInvoiceList(params,size);
	}
	
	/**
	 * 获取发票记录统计
	 * @return
	 */
	public List getCsInvoiceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csInvoiceDao.getCsInvoiceStats(params,groups,sums);
	}
	
	/**
	 * 获取发票记录总数
	 * @return
	 */
	public Long getCsInvoiceCount(Map params)
	{
		return csInvoiceDao.getCsInvoiceCount(params);
	}
	
	/**
	 * 获取发票记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsInvoiceEval(String eval,Map params)
	{
		return csInvoiceDao.getCsInvoiceEval(eval,params);
	}

	/**
	 * 获取发票记录分页
	 * @return
	 */
	public Page getCsInvoicePage(int page,int size,Map params)
	{
		return csInvoiceDao.getCsInvoicePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取发票记录
	 * @param params
	 * @return
	 */
	public CsInvoice getCsInvoice(Map params)
	{
		return csInvoiceDao.getCsInvoice(params);
	}

	/**
	 * 根据ID取发票记录
	 * @param id
	 * @return
	 */
	public CsInvoice getCsInvoiceById(Long id)
	{
		return csInvoiceDao.getCsInvoiceById(id);
	}

	/**
	 * 保存发票记录
	 * @param csInvoice
	 */
	public CsInvoice saveCsInvoice(CsInvoice csInvoice)
	{
		return csInvoiceDao.saveCsInvoice(csInvoice);
	}
	
	/**
	 * 更新发票记录
	 * @param csInvoice
	 */
	public void updateCsInvoice(CsInvoice csInvoice)
	{
		csInvoiceDao.updateCsInvoice(csInvoice);
	}
	/**
	 * 更新发票记录非空字段
	 * @param csInvoice
	 */
	public void updateCsInvoice$NotNull(CsInvoice csInvoice)
	{
		csInvoiceDao.updateCsInvoice$NotNull(csInvoice);
	}
	
	/**
	 * 根据ID删除一个发票记录
	 * @param id
	 */
	public void deleteCsInvoiceById(Long id)
	{
		csInvoiceDao.deleteCsInvoiceById(id);
	}
	/**
	 * 根据ID逻辑删除一个发票记录
	 * @param id
	 */
	public void removeCsInvoiceById(Long id)
	{
		csInvoiceDao.removeCsInvoiceById(id);
	}
	
	/**
	 * 根据条件更新发票记录
	 * @param values
	 * @param params
	 */
	public void updateCsInvoiceByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsInvoiceBy时无条件");
		csInvoiceDao.updateCsInvoiceByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除发票记录
	 * @param params
	 */
	public void deleteCsInvoiceByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsInvoiceBy时无条件");
		csInvoiceDao.deleteCsInvoiceByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsInvoiceDao getCsInvoiceDao()
	{
		return csInvoiceDao;
	}

	public void setCsInvoiceDao(ICsInvoiceDao csInvoiceDao)
	{
		this.csInvoiceDao = csInvoiceDao;
	}
}