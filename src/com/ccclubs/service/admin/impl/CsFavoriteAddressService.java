package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsFavoriteAddressDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFavoriteAddress;
import com.ccclubs.service.admin.ICsFavoriteAddressService;
import com.lazy3q.web.helper.$;

/**
 * 会员常用地址的Service实现
 * @author 飞啊飘啊
 */
public class CsFavoriteAddressService implements ICsFavoriteAddressService
{
	ICsFavoriteAddressDao csFavoriteAddressDao;
	

	/**
	 * 获取所有会员常用地址
	 * @return
	 */
	public List<CsFavoriteAddress> getCsFavoriteAddressList(Map params,Integer size)
	{
		return csFavoriteAddressDao.getCsFavoriteAddressList(params,size);
	}
	
	/**
	 * 获取会员常用地址统计
	 * @return
	 */
	public List getCsFavoriteAddressStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csFavoriteAddressDao.getCsFavoriteAddressStats(params,groups,sums);
	}
	
	/**
	 * 获取会员常用地址总数
	 * @return
	 */
	public Long getCsFavoriteAddressCount(Map params)
	{
		return csFavoriteAddressDao.getCsFavoriteAddressCount(params);
	}
	
	/**
	 * 获取会员常用地址自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsFavoriteAddressEval(String eval,Map params)
	{
		return csFavoriteAddressDao.getCsFavoriteAddressEval(eval,params);
	}

	/**
	 * 获取会员常用地址分页
	 * @return
	 */
	public Page getCsFavoriteAddressPage(int page,int size,Map params)
	{
		return csFavoriteAddressDao.getCsFavoriteAddressPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员常用地址
	 * @param params
	 * @return
	 */
	public CsFavoriteAddress getCsFavoriteAddress(Map params)
	{
		return csFavoriteAddressDao.getCsFavoriteAddress(params);
	}

	/**
	 * 根据ID取会员常用地址
	 * @param id
	 * @return
	 */
	public CsFavoriteAddress getCsFavoriteAddressById(Long id)
	{
		return csFavoriteAddressDao.getCsFavoriteAddressById(id);
	}

	/**
	 * 保存会员常用地址
	 * @param csFavoriteAddress
	 */
	public CsFavoriteAddress saveCsFavoriteAddress(CsFavoriteAddress csFavoriteAddress)
	{
		return csFavoriteAddressDao.saveCsFavoriteAddress(csFavoriteAddress);
	}
	
	/**
	 * 更新会员常用地址
	 * @param csFavoriteAddress
	 */
	public void updateCsFavoriteAddress(CsFavoriteAddress csFavoriteAddress)
	{
		csFavoriteAddressDao.updateCsFavoriteAddress(csFavoriteAddress);
	}
	/**
	 * 更新会员常用地址非空字段
	 * @param csFavoriteAddress
	 */
	public void updateCsFavoriteAddress$NotNull(CsFavoriteAddress csFavoriteAddress)
	{
		csFavoriteAddressDao.updateCsFavoriteAddress$NotNull(csFavoriteAddress);
	}
	
	/**
	 * 根据ID删除一个会员常用地址
	 * @param id
	 */
	public void deleteCsFavoriteAddressById(Long id)
	{
		csFavoriteAddressDao.deleteCsFavoriteAddressById(id);
	}
	
	/**
	 * 根据条件更新会员常用地址
	 * @param values
	 * @param params
	 */
	public void updateCsFavoriteAddressByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsFavoriteAddressBy时无条件");
		csFavoriteAddressDao.updateCsFavoriteAddressByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员常用地址
	 * @param params
	 */
	public void deleteCsFavoriteAddressByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsFavoriteAddressBy时无条件");
		csFavoriteAddressDao.deleteCsFavoriteAddressByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsFavoriteAddressDao getCsFavoriteAddressDao()
	{
		return csFavoriteAddressDao;
	}

	public void setCsFavoriteAddressDao(ICsFavoriteAddressDao csFavoriteAddressDao)
	{
		this.csFavoriteAddressDao = csFavoriteAddressDao;
	}
}