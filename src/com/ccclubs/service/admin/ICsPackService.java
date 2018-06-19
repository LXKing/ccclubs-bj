package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsPack;

/**
 * 系统套餐的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPackService
{
	/**
	 * 获取所有系统套餐
	 * @return
	 */
	public List<CsPack> getCsPackList(Map params,Integer size);
	
	/**
	 * 获取系统套餐统计
	 * @return
	 */
	public List getCsPackStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统套餐总数
	 * @return
	 */	
	public Long getCsPackCount(Map params);
	
	
	/**
	 * 获取系统套餐自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPackEval(String eval,Map params);

	/**
	 * 获取系统套餐分页
	 * @return
	 */	
	public Page getCsPackPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统套餐
	 * @param params
	 * @return
	 */
	public CsPack getCsPack(Map params);

	/**
	 * 根据ID取系统套餐
	 * @param id
	 * @return
	 */
	public CsPack getCsPackById(Long id);


	/**
	 * 保存系统套餐
	 * @param csPack
	 */
	public CsPack saveCsPack(CsPack csPack);

	/**
	 * 更新系统套餐
	 * @param csPack
	 */
	public void updateCsPack(CsPack csPack);
	/**
	 * 更新系统套餐非空字段
	 * @param csPack
	 */
	public void updateCsPack$NotNull(CsPack csPack);

	/**
	 * 根据ID删除一个系统套餐
	 * @param id
	 */
	public void deleteCsPackById(Long id);
	/**
	 * 根据ID逻辑删除一个系统套餐
	 * @param id
	 */
	public void removeCsPackById(Long id);
	/**
	 * 根据条件更新系统套餐
	 * @param values
	 * @param params
	 */
	public void updateCsPackByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统套餐
	 * @param params
	 */
	public void deleteCsPackByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}