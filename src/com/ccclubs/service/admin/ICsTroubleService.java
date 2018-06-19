package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsTrouble;

/**
 * 车辆事故的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsTroubleService
{
	/**
	 * 获取所有车辆事故
	 * @return
	 */
	public List<CsTrouble> getCsTroubleList(Map params,Integer size);
	
	/**
	 * 获取车辆事故统计
	 * @return
	 */
	public List getCsTroubleStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆事故总数
	 * @return
	 */	
	public Long getCsTroubleCount(Map params);
	
	
	/**
	 * 获取车辆事故自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsTroubleEval(String eval,Map params);

	/**
	 * 获取车辆事故分页
	 * @return
	 */	
	public Page getCsTroublePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆事故
	 * @param params
	 * @return
	 */
	public CsTrouble getCsTrouble(Map params);

	/**
	 * 根据ID取车辆事故
	 * @param id
	 * @return
	 */
	public CsTrouble getCsTroubleById(Long id);


	/**
	 * 保存车辆事故
	 * @param csTrouble
	 */
	public CsTrouble saveCsTrouble(CsTrouble csTrouble);

	/**
	 * 更新车辆事故
	 * @param csTrouble
	 */
	public void updateCsTrouble(CsTrouble csTrouble);
	/**
	 * 更新车辆事故非空字段
	 * @param csTrouble
	 */
	public void updateCsTrouble$NotNull(CsTrouble csTrouble);

	/**
	 * 根据ID删除一个车辆事故
	 * @param id
	 */
	public void deleteCsTroubleById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆事故
	 * @param id
	 */
	public void removeCsTroubleById(Long id);
	/**
	 * 根据条件更新车辆事故
	 * @param values
	 * @param params
	 */
	public void updateCsTroubleByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆事故
	 * @param params
	 */
	public void deleteCsTroubleByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}