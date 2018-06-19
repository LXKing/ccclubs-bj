package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChjSign;

/**
 * 春节套餐报名的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChjSignService
{
	/**
	 * 获取所有春节套餐报名
	 * @return
	 */
	public List<CsChjSign> getCsChjSignList(Map params,Integer size);
	
	/**
	 * 获取春节套餐报名统计
	 * @return
	 */
	public List getCsChjSignStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取春节套餐报名总数
	 * @return
	 */	
	public Long getCsChjSignCount(Map params);
	
	
	/**
	 * 获取春节套餐报名自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChjSignEval(String eval,Map params);

	/**
	 * 获取春节套餐报名分页
	 * @return
	 */	
	public Page getCsChjSignPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取春节套餐报名
	 * @param params
	 * @return
	 */
	public CsChjSign getCsChjSign(Map params);

	/**
	 * 根据ID取春节套餐报名
	 * @param id
	 * @return
	 */
	public CsChjSign getCsChjSignById(Long id);


	/**
	 * 保存春节套餐报名
	 * @param csChjSign
	 */
	public CsChjSign saveCsChjSign(CsChjSign csChjSign);

	/**
	 * 更新春节套餐报名
	 * @param csChjSign
	 */
	public void updateCsChjSign(CsChjSign csChjSign);
	/**
	 * 更新春节套餐报名非空字段
	 * @param csChjSign
	 */
	public void updateCsChjSign$NotNull(CsChjSign csChjSign);

	/**
	 * 根据ID删除一个春节套餐报名
	 * @param id
	 */
	public void deleteCsChjSignById(Long id);
	/**
	 * 根据ID逻辑删除一个春节套餐报名
	 * @param id
	 */
	public void removeCsChjSignById(Long id);
	/**
	 * 根据条件更新春节套餐报名
	 * @param values
	 * @param params
	 */
	public void updateCsChjSignByConfirm(Map values, Map params);
	/**
	 * 根据条件删除春节套餐报名
	 * @param params
	 */
	public void deleteCsChjSignByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}