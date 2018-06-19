package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChargeBill;

/**
 * 充电订单的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsChargeBillService
{
	/**
	 * 获取所有充电订单
	 * @return
	 */
	public List<CsChargeBill> getCsChargeBillList(Map params,Integer size);
	
	/**
	 * 获取充电订单统计
	 * @return
	 */
	public List getCsChargeBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取充电订单总数
	 * @return
	 */	
	public Long getCsChargeBillCount(Map params);
	
	
	/**
	 * 获取充电订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChargeBillEval(String eval,Map params);

	/**
	 * 获取充电订单分页
	 * @return
	 */	
	public Page getCsChargeBillPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取充电订单
	 * @param params
	 * @return
	 */
	public CsChargeBill getCsChargeBill(Map params);

	/**
	 * 根据ID取充电订单
	 * @param id
	 * @return
	 */
	public CsChargeBill getCsChargeBillById(Long id);


	/**
	 * 保存充电订单
	 * @param csChargeBill
	 */
	public CsChargeBill saveCsChargeBill(CsChargeBill csChargeBill);

	/**
	 * 更新充电订单
	 * @param csChargeBill
	 */
	public void updateCsChargeBill(CsChargeBill csChargeBill);
	/**
	 * 更新充电订单非空字段
	 * @param csChargeBill
	 */
	public void updateCsChargeBill$NotNull(CsChargeBill csChargeBill);

	/**
	 * 根据ID删除一个充电订单
	 * @param id
	 */
	public void deleteCsChargeBillById(Long id);
	/**
	 * 根据ID逻辑删除一个充电订单
	 * @param id
	 */
	public void removeCsChargeBillById(Long id);
	/**
	 * 根据条件更新充电订单
	 * @param values
	 * @param params
	 */
	public void updateCsChargeBillByConfirm(Map values, Map params);
	/**
	 * 根据条件删除充电订单
	 * @param params
	 */
	public void deleteCsChargeBillByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}