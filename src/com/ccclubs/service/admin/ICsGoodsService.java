package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsGoods;

/**
 * 商品的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsGoodsService
{
	/**
	 * 获取所有商品
	 * @return
	 */
	public List<CsGoods> getCsGoodsList(Map params,Integer size);
	
	/**
	 * 获取商品统计
	 * @return
	 */
	public List getCsGoodsStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取商品总数
	 * @return
	 */	
	public Long getCsGoodsCount(Map params);
	
	
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGoodsEval(String eval,Map params);

	/**
	 * 获取商品分页
	 * @return
	 */	
	public Page getCsGoodsPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取商品
	 * @param params
	 * @return
	 */
	public CsGoods getCsGoods(Map params);

	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	public CsGoods getCsGoodsById(Long id);


	/**
	 * 保存商品
	 * @param csGoods
	 */
	public CsGoods saveCsGoods(CsGoods csGoods);

	/**
	 * 更新商品
	 * @param csGoods
	 */
	public void updateCsGoods(CsGoods csGoods);
	/**
	 * 更新商品非空字段
	 * @param csGoods
	 */
	public void updateCsGoods$NotNull(CsGoods csGoods);

	/**
	 * 根据ID删除一个商品
	 * @param id
	 */
	public void deleteCsGoodsById(Long id);
	/**
	 * 根据ID逻辑删除一个商品
	 * @param id
	 */
	public void removeCsGoodsById(Long id);
	/**
	 * 根据条件更新商品
	 * @param values
	 * @param params
	 */
	public void updateCsGoodsByConfirm(Map values, Map params);
	/**
	 * 根据条件删除商品
	 * @param params
	 */
	public void deleteCsGoodsByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}