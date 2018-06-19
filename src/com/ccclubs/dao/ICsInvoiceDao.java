package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsInvoice;

/**
 * 发票记录的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsInvoiceDao
{
	/**
	 * 获取所有发票记录
	 * @return
	 */
	public List<CsInvoice> getCsInvoiceList(Map params,Integer size);
	
	/**
	 * 获取发票记录统计
	 * @return
	 */
	public List getCsInvoiceStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取发票记录总数
	 * @return
	 */	
	public Long getCsInvoiceCount(Map params);
	
	/**
	 * 获取发票记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsInvoiceEval(String eval,Map params);
	
	/**
	 * 获取发票记录分页
	 * @return
	 */	
	public Page getCsInvoicePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取发票记录
	 * @param params
	 * @return
	 */
	public CsInvoice getCsInvoice(Map params);	

	/**
	 * 根据ID取发票记录
	 * @param id
	 * @return
	 */
	public CsInvoice getCsInvoiceById(Long id);


	
	

	/**
	 * 保存发票记录
	 * @param csInvoice
	 */
	public CsInvoice saveCsInvoice(CsInvoice csInvoice);

	/**
	 * 更新发票记录
	 * @param csInvoice
	 */
	public void updateCsInvoice(CsInvoice csInvoice);
	/**
	 * 更新发票记录非空字段
	 * @param csInvoice
	 */
	public void updateCsInvoice$NotNull(CsInvoice csInvoice);

	/**
	 * 根据ID删除一个发票记录
	 * @param id
	 */
	public void deleteCsInvoiceById(Long id);
	/**
	 * 根据ID逻辑删除一个发票记录
	 * @param id
	 */
	public void removeCsInvoiceById(Long id);
	/**
	 * 根据条件更新发票记录
	 * @param values
	 * @param params
	 */
	public void updateCsInvoiceByConfirm(Map values, Map params);
	/**
	 * 根据条件删除发票记录
	 * @param params
	 */
	public void deleteCsInvoiceByConfirm(Map params);

}