package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsProductDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsProduct;
import com.ccclubs.service.admin.ICsProductService;
import com.lazy3q.web.helper.$;

/**
 * 产品的Service实现
 * @author 飞啊飘啊
 */
public class CsProductService implements ICsProductService
{
	ICsProductDao csProductDao;
	

	/**
	 * 获取所有产品
	 * @return
	 */
	public List<CsProduct> getCsProductList(Map params,Integer size)
	{
		return csProductDao.getCsProductList(params,size);
	}
	
	/**
	 * 获取产品统计
	 * @return
	 */
	public List getCsProductStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csProductDao.getCsProductStats(params,groups,sums);
	}
	
	/**
	 * 获取产品总数
	 * @return
	 */
	public Long getCsProductCount(Map params)
	{
		return csProductDao.getCsProductCount(params);
	}
	
	/**
	 * 获取产品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsProductEval(String eval,Map params)
	{
		return csProductDao.getCsProductEval(eval,params);
	}

	/**
	 * 获取产品分页
	 * @return
	 */
	public Page getCsProductPage(int page,int size,Map params)
	{
		return csProductDao.getCsProductPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取产品
	 * @param params
	 * @return
	 */
	public CsProduct getCsProduct(Map params)
	{
		return csProductDao.getCsProduct(params);
	}

	/**
	 * 根据ID取产品
	 * @param id
	 * @return
	 */
	public CsProduct getCsProductById(Long id)
	{
		return csProductDao.getCsProductById(id);
	}

	/**
	 * 保存产品
	 * @param csProduct
	 */
	public CsProduct saveCsProduct(CsProduct csProduct)
	{
		return csProductDao.saveCsProduct(csProduct);
	}
	
	/**
	 * 更新产品
	 * @param csProduct
	 */
	public void updateCsProduct(CsProduct csProduct)
	{
		csProductDao.updateCsProduct(csProduct);
	}
	/**
	 * 更新产品非空字段
	 * @param csProduct
	 */
	public void updateCsProduct$NotNull(CsProduct csProduct)
	{
		csProductDao.updateCsProduct$NotNull(csProduct);
	}
	
	/**
	 * 根据ID删除一个产品
	 * @param id
	 */
	public void deleteCsProductById(Long id)
	{
		csProductDao.deleteCsProductById(id);
	}
	/**
	 * 根据ID逻辑删除一个产品
	 * @param id
	 */
	public void removeCsProductById(Long id)
	{
		csProductDao.removeCsProductById(id);
	}
	
	/**
	 * 根据条件更新产品
	 * @param values
	 * @param params
	 */
	public void updateCsProductByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsProductBy时无条件");
		csProductDao.updateCsProductByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除产品
	 * @param params
	 */
	public void deleteCsProductByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsProductBy时无条件");
		csProductDao.deleteCsProductByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsProductDao getCsProductDao()
	{
		return csProductDao;
	}

	public void setCsProductDao(ICsProductDao csProductDao)
	{
		this.csProductDao = csProductDao;
	}
}