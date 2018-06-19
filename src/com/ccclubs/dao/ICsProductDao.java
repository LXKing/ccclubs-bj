package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsProduct;

/**
 * 产品的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsProductDao
{
	/**
	 * 获取所有产品
	 * @return
	 */
	public List<CsProduct> getCsProductList(Map params,Integer size);
	
	/**
	 * 获取产品统计
	 * @return
	 */
	public List getCsProductStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取产品总数
	 * @return
	 */	
	public Long getCsProductCount(Map params);
	
	/**
	 * 获取产品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsProductEval(String eval,Map params);
	
	/**
	 * 获取产品分页
	 * @return
	 */	
	public Page getCsProductPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取产品
	 * @param params
	 * @return
	 */
	public CsProduct getCsProduct(Map params);	

	/**
	 * 根据ID取产品
	 * @param id
	 * @return
	 */
	public CsProduct getCsProductById(Long id);


	
	

	/**
	 * 保存产品
	 * @param csProduct
	 */
	public CsProduct saveCsProduct(CsProduct csProduct);

	/**
	 * 更新产品
	 * @param csProduct
	 */
	public void updateCsProduct(CsProduct csProduct);
	/**
	 * 更新产品非空字段
	 * @param csProduct
	 */
	public void updateCsProduct$NotNull(CsProduct csProduct);

	/**
	 * 根据ID删除一个产品
	 * @param id
	 */
	public void deleteCsProductById(Long id);
	/**
	 * 根据ID逻辑删除一个产品
	 * @param id
	 */
	public void removeCsProductById(Long id);
	/**
	 * 根据条件更新产品
	 * @param values
	 * @param params
	 */
	public void updateCsProductByConfirm(Map values, Map params);
	/**
	 * 根据条件删除产品
	 * @param params
	 */
	public void deleteCsProductByConfirm(Map params);

}