package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitBill;

/**
 * 企业对账的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsUnitBillDao
{
	/**
	 * 获取所有企业对账
	 * @return
	 */
	public List<CsUnitBill> getCsUnitBillList(Map params,Integer size);
	
	/**
	 * 获取企业对账统计
	 * @return
	 */
	public List getCsUnitBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取企业对账总数
	 * @return
	 */	
	public Long getCsUnitBillCount(Map params);
	
	/**
	 * 获取企业对账自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitBillEval(String eval,Map params);
	
	/**
	 * 获取企业对账分页
	 * @return
	 */	
	public Page getCsUnitBillPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取企业对账
	 * @param params
	 * @return
	 */
	public CsUnitBill getCsUnitBill(Map params);	

	/**
	 * 根据ID取企业对账
	 * @param id
	 * @return
	 */
	public CsUnitBill getCsUnitBillById(Long id);


	
	

	/**
	 * 保存企业对账
	 * @param csUnitBill
	 */
	public CsUnitBill saveCsUnitBill(CsUnitBill csUnitBill);

	/**
	 * 更新企业对账
	 * @param csUnitBill
	 */
	public void updateCsUnitBill(CsUnitBill csUnitBill);
	/**
	 * 更新企业对账非空字段
	 * @param csUnitBill
	 */
	public void updateCsUnitBill$NotNull(CsUnitBill csUnitBill);

	/**
	 * 根据ID删除一个企业对账
	 * @param id
	 */
	public void deleteCsUnitBillById(Long id);
	/**
	 * 根据ID逻辑删除一个企业对账
	 * @param id
	 */
	public void removeCsUnitBillById(Long id);
	/**
	 * 根据条件更新企业对账
	 * @param values
	 * @param params
	 */
	public void updateCsUnitBillByConfirm(Map values, Map params);
	/**
	 * 根据条件删除企业对账
	 * @param params
	 */
	public void deleteCsUnitBillByConfirm(Map params);

}