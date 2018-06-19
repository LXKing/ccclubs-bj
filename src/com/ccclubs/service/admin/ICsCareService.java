package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCare;

/**
 * 客户关怀的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCareService
{
	/**
	 * 获取所有客户关怀
	 * @return
	 */
	public List<CsCare> getCsCareList(Map params,Integer size);
	
	/**
	 * 获取客户关怀统计
	 * @return
	 */
	public List getCsCareStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取客户关怀总数
	 * @return
	 */	
	public Long getCsCareCount(Map params);
	
	
	/**
	 * 获取客户关怀自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCareEval(String eval,Map params);

	/**
	 * 获取客户关怀分页
	 * @return
	 */	
	public Page getCsCarePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取客户关怀
	 * @param params
	 * @return
	 */
	public CsCare getCsCare(Map params);

	/**
	 * 根据ID取客户关怀
	 * @param id
	 * @return
	 */
	public CsCare getCsCareById(Long id);


	/**
	 * 保存客户关怀
	 * @param csCare
	 */
	public CsCare saveCsCare(CsCare csCare);

	/**
	 * 更新客户关怀
	 * @param csCare
	 */
	public void updateCsCare(CsCare csCare);
	/**
	 * 更新客户关怀非空字段
	 * @param csCare
	 */
	public void updateCsCare$NotNull(CsCare csCare);

	/**
	 * 根据ID删除一个客户关怀
	 * @param id
	 */
	public void deleteCsCareById(Long id);
	/**
	 * 根据ID逻辑删除一个客户关怀
	 * @param id
	 */
	public void removeCsCareById(Long id);
	/**
	 * 根据条件更新客户关怀
	 * @param values
	 * @param params
	 */
	public void updateCsCareByConfirm(Map values, Map params);
	/**
	 * 根据条件删除客户关怀
	 * @param params
	 */
	public void deleteCsCareByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}