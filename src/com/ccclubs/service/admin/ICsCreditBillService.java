package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCreditBill;

/**
 * 信用账单的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCreditBillService
{
	/**
	 * 获取所有信用账单
	 * @return
	 */
	public List<CsCreditBill> getCsCreditBillList(Map params,Integer size);
	
	/**
	 * 获取信用账单统计
	 * @return
	 */
	public List getCsCreditBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取信用账单总数
	 * @return
	 */	
	public Long getCsCreditBillCount(Map params);
	
	
	/**
	 * 获取信用账单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCreditBillEval(String eval,Map params);

	/**
	 * 获取信用账单分页
	 * @return
	 */	
	public Page getCsCreditBillPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取信用账单
	 * @param params
	 * @return
	 */
	public CsCreditBill getCsCreditBill(Map params);

	/**
	 * 根据ID取信用账单
	 * @param id
	 * @return
	 */
	public CsCreditBill getCsCreditBillById(Long id);


	/**
	 * 保存信用账单
	 * @param csCreditBill
	 */
	public CsCreditBill saveCsCreditBill(CsCreditBill csCreditBill);

	/**
	 * 更新信用账单
	 * @param csCreditBill
	 */
	public void updateCsCreditBill(CsCreditBill csCreditBill);
	/**
	 * 更新信用账单非空字段
	 * @param csCreditBill
	 */
	public void updateCsCreditBill$NotNull(CsCreditBill csCreditBill);

	/**
	 * 根据ID删除一个信用账单
	 * @param id
	 */
	public void deleteCsCreditBillById(Long id);
	/**
	 * 根据ID逻辑删除一个信用账单
	 * @param id
	 */
	public void removeCsCreditBillById(Long id);
	/**
	 * 根据条件更新信用账单
	 * @param values
	 * @param params
	 */
	public void updateCsCreditBillByConfirm(Map values, Map params);
	/**
	 * 根据条件删除信用账单
	 * @param params
	 */
	public void deleteCsCreditBillByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}