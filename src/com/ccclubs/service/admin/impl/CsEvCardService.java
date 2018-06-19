package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsEvCardDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.service.admin.ICsEvCardService;
import com.lazy3q.web.helper.$;

/**
 * 会员卡的Service实现
 * @author 飞啊飘啊
 */
public class CsEvCardService implements ICsEvCardService
{
	ICsEvCardDao csEvCardDao;
	

	/**
	 * 获取所有会员卡
	 * @return
	 */
	public List<CsEvCard> getCsEvCardList(Map params,Integer size)
	{
		return csEvCardDao.getCsEvCardList(params,size);
	}
	
	/**
	 * 获取会员卡统计
	 * @return
	 */
	public List getCsEvCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csEvCardDao.getCsEvCardStats(params,groups,sums);
	}
	
	/**
	 * 获取会员卡总数
	 * @return
	 */
	public Long getCsEvCardCount(Map params)
	{
		return csEvCardDao.getCsEvCardCount(params);
	}
	
	/**
	 * 获取会员卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEvCardEval(String eval,Map params)
	{
		return csEvCardDao.getCsEvCardEval(eval,params);
	}

	/**
	 * 获取会员卡分页
	 * @return
	 */
	public Page getCsEvCardPage(int page,int size,Map params)
	{
		return csEvCardDao.getCsEvCardPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员卡
	 * @param params
	 * @return
	 */
	public CsEvCard getCsEvCard(Map params)
	{
		return csEvCardDao.getCsEvCard(params);
	}

	/**
	 * 根据ID取会员卡
	 * @param id
	 * @return
	 */
	public CsEvCard getCsEvCardById(Long id)
	{
		return csEvCardDao.getCsEvCardById(id);
	}

	/**
	 * 保存会员卡
	 * @param csEvCard
	 */
	public CsEvCard saveCsEvCard(CsEvCard csEvCard)
	{
		return csEvCardDao.saveCsEvCard(csEvCard);
	}
	
	/**
	 * 更新会员卡
	 * @param csEvCard
	 */
	public void updateCsEvCard(CsEvCard csEvCard)
	{
		csEvCardDao.updateCsEvCard(csEvCard);
	}
	/**
	 * 更新会员卡非空字段
	 * @param csEvCard
	 */
	public void updateCsEvCard$NotNull(CsEvCard csEvCard)
	{
		csEvCardDao.updateCsEvCard$NotNull(csEvCard);
	}
	
	/**
	 * 根据ID删除一个会员卡
	 * @param id
	 */
	public void deleteCsEvCardById(Long id)
	{
		csEvCardDao.deleteCsEvCardById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员卡
	 * @param id
	 */
	public void removeCsEvCardById(Long id)
	{
		csEvCardDao.removeCsEvCardById(id);
	}
	
	/**
	 * 根据条件更新会员卡
	 * @param values
	 * @param params
	 */
	public void updateCsEvCardByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsEvCardBy时无条件");
		csEvCardDao.updateCsEvCardByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员卡
	 * @param params
	 */
	public void deleteCsEvCardByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsEvCardBy时无条件");
		csEvCardDao.deleteCsEvCardByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsEvCardDao getCsEvCardDao()
	{
		return csEvCardDao;
	}

	public void setCsEvCardDao(ICsEvCardDao csEvCardDao)
	{
		this.csEvCardDao = csEvCardDao;
	}
}