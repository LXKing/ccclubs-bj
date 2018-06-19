package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMemberMemoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberMemo;
import com.ccclubs.service.admin.ICsMemberMemoService;
import com.lazy3q.web.helper.$;

/**
 * 会员备忘的Service实现
 * @author 飞啊飘啊
 */
public class CsMemberMemoService implements ICsMemberMemoService
{
	ICsMemberMemoDao csMemberMemoDao;
	

	/**
	 * 获取所有会员备忘
	 * @return
	 */
	public List<CsMemberMemo> getCsMemberMemoList(Map params,Integer size)
	{
		return csMemberMemoDao.getCsMemberMemoList(params,size);
	}
	
	/**
	 * 获取会员备忘统计
	 * @return
	 */
	public List getCsMemberMemoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberMemoDao.getCsMemberMemoStats(params,groups,sums);
	}
	
	/**
	 * 获取会员备忘总数
	 * @return
	 */
	public Long getCsMemberMemoCount(Map params)
	{
		return csMemberMemoDao.getCsMemberMemoCount(params);
	}
	
	/**
	 * 获取会员备忘自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberMemoEval(String eval,Map params)
	{
		return csMemberMemoDao.getCsMemberMemoEval(eval,params);
	}

	/**
	 * 获取会员备忘分页
	 * @return
	 */
	public Page getCsMemberMemoPage(int page,int size,Map params)
	{
		return csMemberMemoDao.getCsMemberMemoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员备忘
	 * @param params
	 * @return
	 */
	public CsMemberMemo getCsMemberMemo(Map params)
	{
		return csMemberMemoDao.getCsMemberMemo(params);
	}

	/**
	 * 根据ID取会员备忘
	 * @param id
	 * @return
	 */
	public CsMemberMemo getCsMemberMemoById(Long id)
	{
		return csMemberMemoDao.getCsMemberMemoById(id);
	}

	/**
	 * 保存会员备忘
	 * @param csMemberMemo
	 */
	public CsMemberMemo saveCsMemberMemo(CsMemberMemo csMemberMemo)
	{
		return csMemberMemoDao.saveCsMemberMemo(csMemberMemo);
	}
	
	/**
	 * 更新会员备忘
	 * @param csMemberMemo
	 */
	public void updateCsMemberMemo(CsMemberMemo csMemberMemo)
	{
		csMemberMemoDao.updateCsMemberMemo(csMemberMemo);
	}
	/**
	 * 更新会员备忘非空字段
	 * @param csMemberMemo
	 */
	public void updateCsMemberMemo$NotNull(CsMemberMemo csMemberMemo)
	{
		csMemberMemoDao.updateCsMemberMemo$NotNull(csMemberMemo);
	}
	
	/**
	 * 根据ID删除一个会员备忘
	 * @param id
	 */
	public void deleteCsMemberMemoById(Long id)
	{
		csMemberMemoDao.deleteCsMemberMemoById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员备忘
	 * @param id
	 */
	public void removeCsMemberMemoById(Long id)
	{
		csMemberMemoDao.removeCsMemberMemoById(id);
	}
	
	/**
	 * 根据条件更新会员备忘
	 * @param values
	 * @param params
	 */
	public void updateCsMemberMemoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberMemoBy时无条件");
		csMemberMemoDao.updateCsMemberMemoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员备忘
	 * @param params
	 */
	public void deleteCsMemberMemoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberMemoBy时无条件");
		csMemberMemoDao.deleteCsMemberMemoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberMemoDao getCsMemberMemoDao()
	{
		return csMemberMemoDao;
	}

	public void setCsMemberMemoDao(ICsMemberMemoDao csMemberMemoDao)
	{
		this.csMemberMemoDao = csMemberMemoDao;
	}
}