package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayRefund;

/**
 * 接口退款的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsAlipayRefundDao
{
	/**
	 * 获取所有接口退款
	 * @return
	 */
	public List<CsAlipayRefund> getCsAlipayRefundList(Map params,Integer size);
	
	/**
	 * 获取接口退款统计
	 * @return
	 */
	public List getCsAlipayRefundStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取接口退款总数
	 * @return
	 */	
	public Long getCsAlipayRefundCount(Map params);
	
	/**
	 * 获取接口退款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlipayRefundEval(String eval,Map params);
	
	/**
	 * 获取接口退款分页
	 * @return
	 */	
	public Page getCsAlipayRefundPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取接口退款
	 * @param params
	 * @return
	 */
	public CsAlipayRefund getCsAlipayRefund(Map params);	

	/**
	 * 根据ID取接口退款
	 * @param id
	 * @return
	 */
	public CsAlipayRefund getCsAlipayRefundById(Long id);


	
	

	/**
	 * 保存接口退款
	 * @param csAlipayRefund
	 */
	public CsAlipayRefund saveCsAlipayRefund(CsAlipayRefund csAlipayRefund);

	/**
	 * 更新接口退款
	 * @param csAlipayRefund
	 */
	public void updateCsAlipayRefund(CsAlipayRefund csAlipayRefund);
	/**
	 * 更新接口退款非空字段
	 * @param csAlipayRefund
	 */
	public void updateCsAlipayRefund$NotNull(CsAlipayRefund csAlipayRefund);

	/**
	 * 根据ID删除一个接口退款
	 * @param id
	 */
	public void deleteCsAlipayRefundById(Long id);
	/**
	 * 根据ID逻辑删除一个接口退款
	 * @param id
	 */
	public void removeCsAlipayRefundById(Long id);
	/**
	 * 根据条件更新接口退款
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayRefundByConfirm(Map values, Map params);
	/**
	 * 根据条件删除接口退款
	 * @param params
	 */
	public void deleteCsAlipayRefundByConfirm(Map params);

}