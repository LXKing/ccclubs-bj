package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsPowerPile;

/**
 * 充电桩的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPowerPileService
{
	/**
	 * 获取所有充电桩
	 * @return
	 */
	public List<CsPowerPile> getCsPowerPileList(Map params,Integer size);
	
	/**
	 * 获取充电桩统计
	 * @return
	 */
	public List getCsPowerPileStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取充电桩总数
	 * @return
	 */	
	public Long getCsPowerPileCount(Map params);
	
	
	/**
	 * 获取充电桩自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPowerPileEval(String eval,Map params);

	/**
	 * 获取充电桩分页
	 * @return
	 */	
	public Page getCsPowerPilePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public CsPowerPile getCsPowerPile(Map params);

	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public CsPowerPile getCsPowerPileById(Long id);


	/**
	 * 保存充电桩
	 * @param csPowerPile
	 */
	public CsPowerPile saveCsPowerPile(CsPowerPile csPowerPile);

	/**
	 * 更新充电桩
	 * @param csPowerPile
	 */
	public void updateCsPowerPile(CsPowerPile csPowerPile);
	/**
	 * 更新充电桩非空字段
	 * @param csPowerPile
	 */
	public void updateCsPowerPile$NotNull(CsPowerPile csPowerPile);

	/**
	 * 根据ID删除一个充电桩
	 * @param id
	 */
	public void deleteCsPowerPileById(Long id);
	/**
	 * 根据ID逻辑删除一个充电桩
	 * @param id
	 */
	public void removeCsPowerPileById(Long id);
	/**
	 * 根据条件更新充电桩
	 * @param values
	 * @param params
	 */
	public void updateCsPowerPileByConfirm(Map values, Map params);
	/**
	 * 根据条件删除充电桩
	 * @param params
	 */
	public void deleteCsPowerPileByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}