package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.TbSaleCustom;

/**
 * 客户资料的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbSaleCustomService
{
	/**
	 * 获取所有客户资料
	 * @return
	 */
	public List<TbSaleCustom> getTbSaleCustomList(Map params,Integer size);
	
	/**
	 * 获取客户资料统计
	 * @return
	 */
	public List getTbSaleCustomStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取客户资料总数
	 * @return
	 */	
	public Long getTbSaleCustomCount(Map params);
	
	
	/**
	 * 获取客户资料自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleCustomEval(String eval,Map params);

	/**
	 * 获取客户资料分页
	 * @return
	 */	
	public Page getTbSaleCustomPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取客户资料
	 * @param params
	 * @return
	 */
	public TbSaleCustom getTbSaleCustom(Map params);

	/**
	 * 根据ID取客户资料
	 * @param id
	 * @return
	 */
	public TbSaleCustom getTbSaleCustomById(Long id);


	/**
	 * 保存客户资料
	 * @param tbSaleCustom
	 */
	public TbSaleCustom saveTbSaleCustom(TbSaleCustom tbSaleCustom);

	/**
	 * 更新客户资料
	 * @param tbSaleCustom
	 */
	public void updateTbSaleCustom(TbSaleCustom tbSaleCustom);
	/**
	 * 更新客户资料非空字段
	 * @param tbSaleCustom
	 */
	public void updateTbSaleCustom$NotNull(TbSaleCustom tbSaleCustom);

	/**
	 * 根据ID删除一个客户资料
	 * @param id
	 */
	public void deleteTbSaleCustomById(Long id);
	/**
	 * 根据ID逻辑删除一个客户资料
	 * @param id
	 */
	public void removeTbSaleCustomById(Long id);
	/**
	 * 根据条件更新客户资料
	 * @param values
	 * @param params
	 */
	public void updateTbSaleCustomByConfirm(Map values, Map params);
	/**
	 * 根据条件删除客户资料
	 * @param params
	 */
	public void deleteTbSaleCustomByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}