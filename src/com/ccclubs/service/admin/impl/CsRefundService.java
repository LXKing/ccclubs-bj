package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRefundDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRefund;
import com.ccclubs.service.admin.ICsRefundService;
import com.lazy3q.web.helper.$;

/**
 * 会员退款的Service实现
 * @author 飞啊飘啊
 */
public class CsRefundService implements ICsRefundService
{
	ICsRefundDao csRefundDao;
	

	/**
	 * 获取所有会员退款
	 * @return
	 */
	public List<CsRefund> getCsRefundList(Map params,Integer size)
	{
		return csRefundDao.getCsRefundList(params,size);
	}
	
	/**
	 * 获取会员退款统计
	 * @return
	 */
	public List getCsRefundStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRefundDao.getCsRefundStats(params,groups,sums);
	}
	
	/**
	 * 获取会员退款总数
	 * @return
	 */
	public Long getCsRefundCount(Map params)
	{
		return csRefundDao.getCsRefundCount(params);
	}
	
	/**
	 * 获取会员退款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRefundEval(String eval,Map params)
	{
		return csRefundDao.getCsRefundEval(eval,params);
	}

	/**
	 * 获取会员退款分页
	 * @return
	 */
	public Page getCsRefundPage(int page,int size,Map params)
	{
		return csRefundDao.getCsRefundPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员退款
	 * @param params
	 * @return
	 */
	public CsRefund getCsRefund(Map params)
	{
		return csRefundDao.getCsRefund(params);
	}

	/**
	 * 根据ID取会员退款
	 * @param id
	 * @return
	 */
	public CsRefund getCsRefundById(Long id)
	{
		return csRefundDao.getCsRefundById(id);
	}

	/**
	 * 保存会员退款
	 * @param csRefund
	 */
	public CsRefund saveCsRefund(CsRefund csRefund)
	{
		return csRefundDao.saveCsRefund(csRefund);
	}
	
	/**
	 * 更新会员退款
	 * @param csRefund
	 */
	public void updateCsRefund(CsRefund csRefund)
	{
		csRefundDao.updateCsRefund(csRefund);
	}
	/**
	 * 更新会员退款非空字段
	 * @param csRefund
	 */
	public void updateCsRefund$NotNull(CsRefund csRefund)
	{
		csRefundDao.updateCsRefund$NotNull(csRefund);
	}
	
	/**
	 * 根据ID删除一个会员退款
	 * @param id
	 */
	public void deleteCsRefundById(Long id)
	{
		csRefundDao.deleteCsRefundById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员退款
	 * @param id
	 */
	public void removeCsRefundById(Long id)
	{
		csRefundDao.removeCsRefundById(id);
	}
	
	/**
	 * 根据条件更新会员退款
	 * @param values
	 * @param params
	 */
	public void updateCsRefundByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRefundBy时无条件");
		csRefundDao.updateCsRefundByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员退款
	 * @param params
	 */
	public void deleteCsRefundByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRefundBy时无条件");
		csRefundDao.deleteCsRefundByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRefundDao getCsRefundDao()
	{
		return csRefundDao;
	}

	public void setCsRefundDao(ICsRefundDao csRefundDao)
	{
		this.csRefundDao = csRefundDao;
	}
}