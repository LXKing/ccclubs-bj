package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbSaleBattleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleBattle;
import com.ccclubs.service.admin.ITbSaleBattleService;
import com.lazy3q.web.helper.$;

/**
 * 销售战役的Service实现
 * @author 飞啊飘啊
 */
public class TbSaleBattleService implements ITbSaleBattleService
{
	ITbSaleBattleDao tbSaleBattleDao;
	

	/**
	 * 获取所有销售战役
	 * @return
	 */
	public List<TbSaleBattle> getTbSaleBattleList(Map params,Integer size)
	{
		return tbSaleBattleDao.getTbSaleBattleList(params,size);
	}
	
	/**
	 * 获取销售战役统计
	 * @return
	 */
	public List getTbSaleBattleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbSaleBattleDao.getTbSaleBattleStats(params,groups,sums);
	}
	
	/**
	 * 获取销售战役总数
	 * @return
	 */
	public Long getTbSaleBattleCount(Map params)
	{
		return tbSaleBattleDao.getTbSaleBattleCount(params);
	}
	
	/**
	 * 获取销售战役自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleBattleEval(String eval,Map params)
	{
		return tbSaleBattleDao.getTbSaleBattleEval(eval,params);
	}

	/**
	 * 获取销售战役分页
	 * @return
	 */
	public Page getTbSaleBattlePage(int page,int size,Map params)
	{
		return tbSaleBattleDao.getTbSaleBattlePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取销售战役
	 * @param params
	 * @return
	 */
	public TbSaleBattle getTbSaleBattle(Map params)
	{
		return tbSaleBattleDao.getTbSaleBattle(params);
	}

	/**
	 * 根据ID取销售战役
	 * @param id
	 * @return
	 */
	public TbSaleBattle getTbSaleBattleById(Long id)
	{
		return tbSaleBattleDao.getTbSaleBattleById(id);
	}

	/**
	 * 保存销售战役
	 * @param tbSaleBattle
	 */
	public TbSaleBattle saveTbSaleBattle(TbSaleBattle tbSaleBattle)
	{
		return tbSaleBattleDao.saveTbSaleBattle(tbSaleBattle);
	}
	
	/**
	 * 更新销售战役
	 * @param tbSaleBattle
	 */
	public void updateTbSaleBattle(TbSaleBattle tbSaleBattle)
	{
		tbSaleBattleDao.updateTbSaleBattle(tbSaleBattle);
	}
	/**
	 * 更新销售战役非空字段
	 * @param tbSaleBattle
	 */
	public void updateTbSaleBattle$NotNull(TbSaleBattle tbSaleBattle)
	{
		tbSaleBattleDao.updateTbSaleBattle$NotNull(tbSaleBattle);
	}
	
	/**
	 * 根据ID删除一个销售战役
	 * @param id
	 */
	public void deleteTbSaleBattleById(Long id)
	{
		tbSaleBattleDao.deleteTbSaleBattleById(id);
	}
	/**
	 * 根据ID逻辑删除一个销售战役
	 * @param id
	 */
	public void removeTbSaleBattleById(Long id)
	{
		tbSaleBattleDao.removeTbSaleBattleById(id);
	}
	
	/**
	 * 根据条件更新销售战役
	 * @param values
	 * @param params
	 */
	public void updateTbSaleBattleByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbSaleBattleBy时无条件");
		tbSaleBattleDao.updateTbSaleBattleByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除销售战役
	 * @param params
	 */
	public void deleteTbSaleBattleByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbSaleBattleBy时无条件");
		tbSaleBattleDao.deleteTbSaleBattleByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbSaleBattleDao getTbSaleBattleDao()
	{
		return tbSaleBattleDao;
	}

	public void setTbSaleBattleDao(ITbSaleBattleDao tbSaleBattleDao)
	{
		this.tbSaleBattleDao = tbSaleBattleDao;
	}
}