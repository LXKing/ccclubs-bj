package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsLongOrderFee;

/**
 * 长单缴费的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLongOrderFeeService
{
	/**
	 * 获取所有长单缴费
	 * @return
	 */
	public List<CsLongOrderFee> getCsLongOrderFeeList(Map params,Integer size);
	
	/**
	 * 获取长单缴费统计
	 * @return
	 */
	public List getCsLongOrderFeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取长单缴费总数
	 * @return
	 */	
	public Long getCsLongOrderFeeCount(Map params);
	
	
	/**
	 * 获取长单缴费自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderFeeEval(String eval,Map params);

	/**
	 * 获取长单缴费分页
	 * @return
	 */	
	public Page getCsLongOrderFeePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取长单缴费
	 * @param params
	 * @return
	 */
	public CsLongOrderFee getCsLongOrderFee(Map params);

	/**
	 * 根据ID取长单缴费
	 * @param id
	 * @return
	 */
	public CsLongOrderFee getCsLongOrderFeeById(Long id);


	/**
	 * 保存长单缴费
	 * @param csLongOrderFee
	 */
	public CsLongOrderFee saveCsLongOrderFee(CsLongOrderFee csLongOrderFee);

	/**
	 * 更新长单缴费
	 * @param csLongOrderFee
	 */
	public void updateCsLongOrderFee(CsLongOrderFee csLongOrderFee);
	/**
	 * 更新长单缴费非空字段
	 * @param csLongOrderFee
	 */
	public void updateCsLongOrderFee$NotNull(CsLongOrderFee csLongOrderFee);

	/**
	 * 根据ID删除一个长单缴费
	 * @param id
	 */
	public void deleteCsLongOrderFeeById(Long id);
	/**
	 * 根据ID逻辑删除一个长单缴费
	 * @param id
	 */
	public void removeCsLongOrderFeeById(Long id);
	/**
	 * 根据条件更新长单缴费
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderFeeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除长单缴费
	 * @param params
	 */
	public void deleteCsLongOrderFeeByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}