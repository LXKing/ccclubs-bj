package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCreditCardDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditCard;
import com.ccclubs.service.admin.ICsCreditCardService;
import com.lazy3q.web.helper.$;

/**
 * 信用卡的Service实现
 * @author 飞啊飘啊
 */
public class CsCreditCardService implements ICsCreditCardService
{
	ICsCreditCardDao csCreditCardDao;
	

	/**
	 * 获取所有信用卡
	 * @return
	 */
	public List<CsCreditCard> getCsCreditCardList(Map params,Integer size)
	{
		return csCreditCardDao.getCsCreditCardList(params,size);
	}
	
	/**
	 * 获取信用卡统计
	 * @return
	 */
	public List getCsCreditCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCreditCardDao.getCsCreditCardStats(params,groups,sums);
	}
	
	/**
	 * 获取信用卡总数
	 * @return
	 */
	public Long getCsCreditCardCount(Map params)
	{
		return csCreditCardDao.getCsCreditCardCount(params);
	}
	
	/**
	 * 获取信用卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCreditCardEval(String eval,Map params)
	{
		return csCreditCardDao.getCsCreditCardEval(eval,params);
	}

	/**
	 * 获取信用卡分页
	 * @return
	 */
	public Page getCsCreditCardPage(int page,int size,Map params)
	{
		return csCreditCardDao.getCsCreditCardPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取信用卡
	 * @param params
	 * @return
	 */
	public CsCreditCard getCsCreditCard(Map params)
	{
		return csCreditCardDao.getCsCreditCard(params);
	}

	/**
	 * 根据ID取信用卡
	 * @param id
	 * @return
	 */
	public CsCreditCard getCsCreditCardById(Long id)
	{
		return csCreditCardDao.getCsCreditCardById(id);
	}

	/**
	 * 保存信用卡
	 * @param csCreditCard
	 */
	public CsCreditCard saveCsCreditCard(CsCreditCard csCreditCard)
	{
		return csCreditCardDao.saveCsCreditCard(csCreditCard);
	}
	
	/**
	 * 更新信用卡
	 * @param csCreditCard
	 */
	public void updateCsCreditCard(CsCreditCard csCreditCard)
	{
		csCreditCardDao.updateCsCreditCard(csCreditCard);
	}
	/**
	 * 更新信用卡非空字段
	 * @param csCreditCard
	 */
	public void updateCsCreditCard$NotNull(CsCreditCard csCreditCard)
	{
		csCreditCardDao.updateCsCreditCard$NotNull(csCreditCard);
	}
	
	/**
	 * 根据ID删除一个信用卡
	 * @param id
	 */
	public void deleteCsCreditCardById(Long id)
	{
		csCreditCardDao.deleteCsCreditCardById(id);
	}
	/**
	 * 根据ID逻辑删除一个信用卡
	 * @param id
	 */
	public void removeCsCreditCardById(Long id)
	{
		csCreditCardDao.removeCsCreditCardById(id);
	}
	
	/**
	 * 根据条件更新信用卡
	 * @param values
	 * @param params
	 */
	public void updateCsCreditCardByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCreditCardBy时无条件");
		csCreditCardDao.updateCsCreditCardByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除信用卡
	 * @param params
	 */
	public void deleteCsCreditCardByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCreditCardBy时无条件");
		csCreditCardDao.deleteCsCreditCardByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCreditCardDao getCsCreditCardDao()
	{
		return csCreditCardDao;
	}

	public void setCsCreditCardDao(ICsCreditCardDao csCreditCardDao)
	{
		this.csCreditCardDao = csCreditCardDao;
	}
}