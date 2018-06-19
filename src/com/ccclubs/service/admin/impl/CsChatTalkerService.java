package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChatTalkerDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChatTalker;
import com.ccclubs.service.admin.ICsChatTalkerService;
import com.lazy3q.web.helper.$;

/**
 * 在线访客的Service实现
 * @author 飞啊飘啊
 */
public class CsChatTalkerService implements ICsChatTalkerService
{
	ICsChatTalkerDao csChatTalkerDao;
	

	/**
	 * 获取所有在线访客
	 * @return
	 */
	public List<CsChatTalker> getCsChatTalkerList(Map params,Integer size)
	{
		return csChatTalkerDao.getCsChatTalkerList(params,size);
	}
	
	/**
	 * 获取在线访客统计
	 * @return
	 */
	public List getCsChatTalkerStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChatTalkerDao.getCsChatTalkerStats(params,groups,sums);
	}
	
	/**
	 * 获取在线访客总数
	 * @return
	 */
	public Long getCsChatTalkerCount(Map params)
	{
		return csChatTalkerDao.getCsChatTalkerCount(params);
	}
	
	/**
	 * 获取在线访客自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChatTalkerEval(String eval,Map params)
	{
		return csChatTalkerDao.getCsChatTalkerEval(eval,params);
	}

	/**
	 * 获取在线访客分页
	 * @return
	 */
	public Page getCsChatTalkerPage(int page,int size,Map params)
	{
		return csChatTalkerDao.getCsChatTalkerPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取在线访客
	 * @param params
	 * @return
	 */
	public CsChatTalker getCsChatTalker(Map params)
	{
		return csChatTalkerDao.getCsChatTalker(params);
	}

	/**
	 * 根据ID取在线访客
	 * @param id
	 * @return
	 */
	public CsChatTalker getCsChatTalkerById(Long id)
	{
		return csChatTalkerDao.getCsChatTalkerById(id);
	}

	/**
	 * 保存在线访客
	 * @param csChatTalker
	 */
	public CsChatTalker saveCsChatTalker(CsChatTalker csChatTalker)
	{
		return csChatTalkerDao.saveCsChatTalker(csChatTalker);
	}
	
	/**
	 * 更新在线访客
	 * @param csChatTalker
	 */
	public void updateCsChatTalker(CsChatTalker csChatTalker)
	{
		csChatTalkerDao.updateCsChatTalker(csChatTalker);
	}
	/**
	 * 更新在线访客非空字段
	 * @param csChatTalker
	 */
	public void updateCsChatTalker$NotNull(CsChatTalker csChatTalker)
	{
		csChatTalkerDao.updateCsChatTalker$NotNull(csChatTalker);
	}
	
	/**
	 * 根据ID删除一个在线访客
	 * @param id
	 */
	public void deleteCsChatTalkerById(Long id)
	{
		csChatTalkerDao.deleteCsChatTalkerById(id);
	}
	/**
	 * 根据ID逻辑删除一个在线访客
	 * @param id
	 */
	public void removeCsChatTalkerById(Long id)
	{
		csChatTalkerDao.removeCsChatTalkerById(id);
	}
	
	/**
	 * 根据条件更新在线访客
	 * @param values
	 * @param params
	 */
	public void updateCsChatTalkerByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChatTalkerBy时无条件");
		csChatTalkerDao.updateCsChatTalkerByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除在线访客
	 * @param params
	 */
	public void deleteCsChatTalkerByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChatTalkerBy时无条件");
		csChatTalkerDao.deleteCsChatTalkerByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChatTalkerDao getCsChatTalkerDao()
	{
		return csChatTalkerDao;
	}

	public void setCsChatTalkerDao(ICsChatTalkerDao csChatTalkerDao)
	{
		this.csChatTalkerDao = csChatTalkerDao;
	}
}