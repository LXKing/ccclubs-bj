package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsAddressDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAddress;
import com.ccclubs.service.admin.ICsAddressService;
import com.lazy3q.web.helper.$;

/**
 * 会员地址的Service实现
 * @author 飞啊飘啊
 */
public class CsAddressService implements ICsAddressService
{
	ICsAddressDao csAddressDao;
	

	/**
	 * 获取所有会员地址
	 * @return
	 */
	public List<CsAddress> getCsAddressList(Map params,Integer size)
	{
		return csAddressDao.getCsAddressList(params,size);
	}
	
	/**
	 * 获取会员地址统计
	 * @return
	 */
	public List getCsAddressStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csAddressDao.getCsAddressStats(params,groups,sums);
	}
	
	/**
	 * 获取会员地址总数
	 * @return
	 */
	public Long getCsAddressCount(Map params)
	{
		return csAddressDao.getCsAddressCount(params);
	}
	
	/**
	 * 获取会员地址自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAddressEval(String eval,Map params)
	{
		return csAddressDao.getCsAddressEval(eval,params);
	}

	/**
	 * 获取会员地址分页
	 * @return
	 */
	public Page getCsAddressPage(int page,int size,Map params)
	{
		return csAddressDao.getCsAddressPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员地址
	 * @param params
	 * @return
	 */
	public CsAddress getCsAddress(Map params)
	{
		return csAddressDao.getCsAddress(params);
	}

	/**
	 * 根据ID取会员地址
	 * @param id
	 * @return
	 */
	public CsAddress getCsAddressById(Long id)
	{
		return csAddressDao.getCsAddressById(id);
	}

	/**
	 * 保存会员地址
	 * @param csAddress
	 */
	public CsAddress saveCsAddress(CsAddress csAddress)
	{
		return csAddressDao.saveCsAddress(csAddress);
	}
	
	/**
	 * 更新会员地址
	 * @param csAddress
	 */
	public void updateCsAddress(CsAddress csAddress)
	{
		csAddressDao.updateCsAddress(csAddress);
	}
	/**
	 * 更新会员地址非空字段
	 * @param csAddress
	 */
	public void updateCsAddress$NotNull(CsAddress csAddress)
	{
		csAddressDao.updateCsAddress$NotNull(csAddress);
	}
	
	/**
	 * 根据ID删除一个会员地址
	 * @param id
	 */
	public void deleteCsAddressById(Long id)
	{
		csAddressDao.deleteCsAddressById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员地址
	 * @param id
	 */
	public void removeCsAddressById(Long id)
	{
		csAddressDao.removeCsAddressById(id);
	}
	
	/**
	 * 根据条件更新会员地址
	 * @param values
	 * @param params
	 */
	public void updateCsAddressByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsAddressBy时无条件");
		csAddressDao.updateCsAddressByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员地址
	 * @param params
	 */
	public void deleteCsAddressByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsAddressBy时无条件");
		csAddressDao.deleteCsAddressByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsAddressDao getCsAddressDao()
	{
		return csAddressDao;
	}

	public void setCsAddressDao(ICsAddressDao csAddressDao)
	{
		this.csAddressDao = csAddressDao;
	}
}