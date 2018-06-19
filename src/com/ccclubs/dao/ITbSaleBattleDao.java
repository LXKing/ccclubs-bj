package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleBattle;

/**
 * 销售战役的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbSaleBattleDao
{
	/**
	 * 获取所有销售战役
	 * @return
	 */
	public List<TbSaleBattle> getTbSaleBattleList(Map params,Integer size);
	
	/**
	 * 获取销售战役统计
	 * @return
	 */
	public List getTbSaleBattleStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取销售战役总数
	 * @return
	 */	
	public Long getTbSaleBattleCount(Map params);
	
	/**
	 * 获取销售战役自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleBattleEval(String eval,Map params);
	
	/**
	 * 获取销售战役分页
	 * @return
	 */	
	public Page getTbSaleBattlePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取销售战役
	 * @param params
	 * @return
	 */
	public TbSaleBattle getTbSaleBattle(Map params);	

	/**
	 * 根据ID取销售战役
	 * @param id
	 * @return
	 */
	public TbSaleBattle getTbSaleBattleById(Long id);


	
	

	/**
	 * 保存销售战役
	 * @param tbSaleBattle
	 */
	public TbSaleBattle saveTbSaleBattle(TbSaleBattle tbSaleBattle);

	/**
	 * 更新销售战役
	 * @param tbSaleBattle
	 */
	public void updateTbSaleBattle(TbSaleBattle tbSaleBattle);
	/**
	 * 更新销售战役非空字段
	 * @param tbSaleBattle
	 */
	public void updateTbSaleBattle$NotNull(TbSaleBattle tbSaleBattle);

	/**
	 * 根据ID删除一个销售战役
	 * @param id
	 */
	public void deleteTbSaleBattleById(Long id);
	/**
	 * 根据ID逻辑删除一个销售战役
	 * @param id
	 */
	public void removeTbSaleBattleById(Long id);
	/**
	 * 根据条件更新销售战役
	 * @param values
	 * @param params
	 */
	public void updateTbSaleBattleByConfirm(Map values, Map params);
	/**
	 * 根据条件删除销售战役
	 * @param params
	 */
	public void deleteTbSaleBattleByConfirm(Map params);

}