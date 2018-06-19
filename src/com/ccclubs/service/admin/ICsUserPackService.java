package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUserPack;

/**
 * 用户套餐的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUserPackService
{
	/**
	 * 获取所有用户套餐
	 * @return
	 */
	public List<CsUserPack> getCsUserPackList(Map params,Integer size);
	
	/**
	 * 获取用户套餐统计
	 * @return
	 */
	public List getCsUserPackStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取用户套餐总数
	 * @return
	 */	
	public Long getCsUserPackCount(Map params);
	
	
	/**
	 * 获取用户套餐自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUserPackEval(String eval,Map params);

	/**
	 * 获取用户套餐分页
	 * @return
	 */	
	public Page getCsUserPackPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取用户套餐
	 * @param params
	 * @return
	 */
	public CsUserPack getCsUserPack(Map params);

	/**
	 * 根据ID取用户套餐
	 * @param id
	 * @return
	 */
	public CsUserPack getCsUserPackById(Long id);


	/**
	 * 保存用户套餐
	 * @param csUserPack
	 */
	public CsUserPack saveCsUserPack(CsUserPack csUserPack);

	/**
	 * 更新用户套餐
	 * @param csUserPack
	 */
	public void updateCsUserPack(CsUserPack csUserPack);
	/**
	 * 更新用户套餐非空字段
	 * @param csUserPack
	 */
	public void updateCsUserPack$NotNull(CsUserPack csUserPack);

	/**
	 * 根据ID删除一个用户套餐
	 * @param id
	 */
	public void deleteCsUserPackById(Long id);
	/**
	 * 根据ID逻辑删除一个用户套餐
	 * @param id
	 */
	public void removeCsUserPackById(Long id);
	/**
	 * 根据条件更新用户套餐
	 * @param values
	 * @param params
	 */
	public void updateCsUserPackByConfirm(Map values, Map params);
	/**
	 * 根据条件删除用户套餐
	 * @param params
	 */
	public void deleteCsUserPackByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}