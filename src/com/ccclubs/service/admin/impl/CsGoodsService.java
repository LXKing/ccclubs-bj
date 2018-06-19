package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsGoodsDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGoods;
import com.ccclubs.service.admin.ICsGoodsService;
import com.lazy3q.web.helper.$;

/**
 * 商品的Service实现
 * @author 飞啊飘啊
 */
public class CsGoodsService implements ICsGoodsService
{
	ICsGoodsDao csGoodsDao;
	

	/**
	 * 获取所有商品
	 * @return
	 */
	public List<CsGoods> getCsGoodsList(Map params,Integer size)
	{
		return csGoodsDao.getCsGoodsList(params,size);
	}
	
	/**
	 * 获取商品统计
	 * @return
	 */
	public List getCsGoodsStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csGoodsDao.getCsGoodsStats(params,groups,sums);
	}
	
	/**
	 * 获取商品总数
	 * @return
	 */
	public Long getCsGoodsCount(Map params)
	{
		return csGoodsDao.getCsGoodsCount(params);
	}
	
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGoodsEval(String eval,Map params)
	{
		return csGoodsDao.getCsGoodsEval(eval,params);
	}

	/**
	 * 获取商品分页
	 * @return
	 */
	public Page getCsGoodsPage(int page,int size,Map params)
	{
		return csGoodsDao.getCsGoodsPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取商品
	 * @param params
	 * @return
	 */
	public CsGoods getCsGoods(Map params)
	{
		return csGoodsDao.getCsGoods(params);
	}

	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	public CsGoods getCsGoodsById(Long id)
	{
		return csGoodsDao.getCsGoodsById(id);
	}

	/**
	 * 保存商品
	 * @param csGoods
	 */
	public CsGoods saveCsGoods(CsGoods csGoods)
	{
		return csGoodsDao.saveCsGoods(csGoods);
	}
	
	/**
	 * 更新商品
	 * @param csGoods
	 */
	public void updateCsGoods(CsGoods csGoods)
	{
		csGoodsDao.updateCsGoods(csGoods);
	}
	/**
	 * 更新商品非空字段
	 * @param csGoods
	 */
	public void updateCsGoods$NotNull(CsGoods csGoods)
	{
		csGoodsDao.updateCsGoods$NotNull(csGoods);
	}
	
	/**
	 * 根据ID删除一个商品
	 * @param id
	 */
	public void deleteCsGoodsById(Long id)
	{
		csGoodsDao.deleteCsGoodsById(id);
	}
	/**
	 * 根据ID逻辑删除一个商品
	 * @param id
	 */
	public void removeCsGoodsById(Long id)
	{
		csGoodsDao.removeCsGoodsById(id);
	}
	
	/**
	 * 根据条件更新商品
	 * @param values
	 * @param params
	 */
	public void updateCsGoodsByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsGoodsBy时无条件");
		csGoodsDao.updateCsGoodsByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除商品
	 * @param params
	 */
	public void deleteCsGoodsByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsGoodsBy时无条件");
		csGoodsDao.deleteCsGoodsByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsGoodsDao getCsGoodsDao()
	{
		return csGoodsDao;
	}

	public void setCsGoodsDao(ICsGoodsDao csGoodsDao)
	{
		this.csGoodsDao = csGoodsDao;
	}
}