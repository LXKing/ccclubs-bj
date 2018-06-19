package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRefund;

/**
 * 会员退款的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRefundDao
{
	/**
	 * 获取所有会员退款
	 * @return
	 */
	public List<CsRefund> getCsRefundList(Map params,Integer size);
	
	/**
	 * 获取会员退款统计
	 * @return
	 */
	public List getCsRefundStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员退款总数
	 * @return
	 */	
	public Long getCsRefundCount(Map params);
	
	/**
	 * 获取会员退款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRefundEval(String eval,Map params);
	
	/**
	 * 获取会员退款分页
	 * @return
	 */	
	public Page getCsRefundPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员退款
	 * @param params
	 * @return
	 */
	public CsRefund getCsRefund(Map params);	

	/**
	 * 根据ID取会员退款
	 * @param id
	 * @return
	 */
	public CsRefund getCsRefundById(Long id);


	
	

	/**
	 * 保存会员退款
	 * @param csRefund
	 */
	public CsRefund saveCsRefund(CsRefund csRefund);

	/**
	 * 更新会员退款
	 * @param csRefund
	 */
	public void updateCsRefund(CsRefund csRefund);
	/**
	 * 更新会员退款非空字段
	 * @param csRefund
	 */
	public void updateCsRefund$NotNull(CsRefund csRefund);

	/**
	 * 根据ID删除一个会员退款
	 * @param id
	 */
	public void deleteCsRefundById(Long id);
	/**
	 * 根据ID逻辑删除一个会员退款
	 * @param id
	 */
	public void removeCsRefundById(Long id);
	/**
	 * 根据条件更新会员退款
	 * @param values
	 * @param params
	 */
	public void updateCsRefundByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员退款
	 * @param params
	 */
	public void deleteCsRefundByConfirm(Map params);

}