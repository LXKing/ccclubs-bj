package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbSaleCustomDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleCustom;
import com.ccclubs.service.admin.ITbSaleCustomService;
import com.lazy3q.web.helper.$;

/**
 * 客户资料的Service实现
 * @author 飞啊飘啊
 */
public class TbSaleCustomService implements ITbSaleCustomService
{
	ITbSaleCustomDao tbSaleCustomDao;
	

	/**
	 * 获取所有客户资料
	 * @return
	 */
	public List<TbSaleCustom> getTbSaleCustomList(Map params,Integer size)
	{
		return tbSaleCustomDao.getTbSaleCustomList(params,size);
	}
	
	/**
	 * 获取客户资料统计
	 * @return
	 */
	public List getTbSaleCustomStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbSaleCustomDao.getTbSaleCustomStats(params,groups,sums);
	}
	
	/**
	 * 获取客户资料总数
	 * @return
	 */
	public Long getTbSaleCustomCount(Map params)
	{
		return tbSaleCustomDao.getTbSaleCustomCount(params);
	}
	
	/**
	 * 获取客户资料自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleCustomEval(String eval,Map params)
	{
		return tbSaleCustomDao.getTbSaleCustomEval(eval,params);
	}

	/**
	 * 获取客户资料分页
	 * @return
	 */
	public Page getTbSaleCustomPage(int page,int size,Map params)
	{
		return tbSaleCustomDao.getTbSaleCustomPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取客户资料
	 * @param params
	 * @return
	 */
	public TbSaleCustom getTbSaleCustom(Map params)
	{
		return tbSaleCustomDao.getTbSaleCustom(params);
	}

	/**
	 * 根据ID取客户资料
	 * @param id
	 * @return
	 */
	public TbSaleCustom getTbSaleCustomById(Long id)
	{
		return tbSaleCustomDao.getTbSaleCustomById(id);
	}

	/**
	 * 保存客户资料
	 * @param tbSaleCustom
	 */
	public TbSaleCustom saveTbSaleCustom(TbSaleCustom tbSaleCustom)
	{
		return tbSaleCustomDao.saveTbSaleCustom(tbSaleCustom);
	}
	
	/**
	 * 更新客户资料
	 * @param tbSaleCustom
	 */
	public void updateTbSaleCustom(TbSaleCustom tbSaleCustom)
	{
		tbSaleCustomDao.updateTbSaleCustom(tbSaleCustom);
	}
	/**
	 * 更新客户资料非空字段
	 * @param tbSaleCustom
	 */
	public void updateTbSaleCustom$NotNull(TbSaleCustom tbSaleCustom)
	{
		tbSaleCustomDao.updateTbSaleCustom$NotNull(tbSaleCustom);
	}
	
	/**
	 * 根据ID删除一个客户资料
	 * @param id
	 */
	public void deleteTbSaleCustomById(Long id)
	{
		tbSaleCustomDao.deleteTbSaleCustomById(id);
	}
	/**
	 * 根据ID逻辑删除一个客户资料
	 * @param id
	 */
	public void removeTbSaleCustomById(Long id)
	{
		tbSaleCustomDao.removeTbSaleCustomById(id);
	}
	
	/**
	 * 根据条件更新客户资料
	 * @param values
	 * @param params
	 */
	public void updateTbSaleCustomByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbSaleCustomBy时无条件");
		tbSaleCustomDao.updateTbSaleCustomByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除客户资料
	 * @param params
	 */
	public void deleteTbSaleCustomByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbSaleCustomBy时无条件");
		tbSaleCustomDao.deleteTbSaleCustomByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbSaleCustomDao getTbSaleCustomDao()
	{
		return tbSaleCustomDao;
	}

	public void setTbSaleCustomDao(ITbSaleCustomDao tbSaleCustomDao)
	{
		this.tbSaleCustomDao = tbSaleCustomDao;
	}
}