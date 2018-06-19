package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbPassCheckDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbPassCheck;
import com.ccclubs.service.admin.ITbPassCheckService;
import com.lazy3q.web.helper.$;

/**
 * 上下班签到的Service实现
 * @author 飞啊飘啊
 */
public class TbPassCheckService implements ITbPassCheckService
{
	ITbPassCheckDao tbPassCheckDao;
	

	/**
	 * 获取所有上下班签到
	 * @return
	 */
	public List<TbPassCheck> getTbPassCheckList(Map params,Integer size)
	{
		return tbPassCheckDao.getTbPassCheckList(params,size);
	}
	
	/**
	 * 获取上下班签到统计
	 * @return
	 */
	public List getTbPassCheckStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbPassCheckDao.getTbPassCheckStats(params,groups,sums);
	}
	
	/**
	 * 获取上下班签到总数
	 * @return
	 */
	public Long getTbPassCheckCount(Map params)
	{
		return tbPassCheckDao.getTbPassCheckCount(params);
	}
	
	/**
	 * 获取上下班签到自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbPassCheckEval(String eval,Map params)
	{
		return tbPassCheckDao.getTbPassCheckEval(eval,params);
	}

	/**
	 * 获取上下班签到分页
	 * @return
	 */
	public Page getTbPassCheckPage(int page,int size,Map params)
	{
		return tbPassCheckDao.getTbPassCheckPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取上下班签到
	 * @param params
	 * @return
	 */
	public TbPassCheck getTbPassCheck(Map params)
	{
		return tbPassCheckDao.getTbPassCheck(params);
	}

	/**
	 * 根据ID取上下班签到
	 * @param id
	 * @return
	 */
	public TbPassCheck getTbPassCheckById(Long id)
	{
		return tbPassCheckDao.getTbPassCheckById(id);
	}

	/**
	 * 保存上下班签到
	 * @param tbPassCheck
	 */
	public TbPassCheck saveTbPassCheck(TbPassCheck tbPassCheck)
	{
		return tbPassCheckDao.saveTbPassCheck(tbPassCheck);
	}
	
	/**
	 * 更新上下班签到
	 * @param tbPassCheck
	 */
	public void updateTbPassCheck(TbPassCheck tbPassCheck)
	{
		tbPassCheckDao.updateTbPassCheck(tbPassCheck);
	}
	/**
	 * 更新上下班签到非空字段
	 * @param tbPassCheck
	 */
	public void updateTbPassCheck$NotNull(TbPassCheck tbPassCheck)
	{
		tbPassCheckDao.updateTbPassCheck$NotNull(tbPassCheck);
	}
	
	/**
	 * 根据ID删除一个上下班签到
	 * @param id
	 */
	public void deleteTbPassCheckById(Long id)
	{
		tbPassCheckDao.deleteTbPassCheckById(id);
	}
	/**
	 * 根据ID逻辑删除一个上下班签到
	 * @param id
	 */
	public void removeTbPassCheckById(Long id)
	{
		tbPassCheckDao.removeTbPassCheckById(id);
	}
	
	/**
	 * 根据条件更新上下班签到
	 * @param values
	 * @param params
	 */
	public void updateTbPassCheckByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbPassCheckBy时无条件");
		tbPassCheckDao.updateTbPassCheckByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除上下班签到
	 * @param params
	 */
	public void deleteTbPassCheckByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbPassCheckBy时无条件");
		tbPassCheckDao.deleteTbPassCheckByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbPassCheckDao getTbPassCheckDao()
	{
		return tbPassCheckDao;
	}

	public void setTbPassCheckDao(ITbPassCheckDao tbPassCheckDao)
	{
		this.tbPassCheckDao = tbPassCheckDao;
	}
}