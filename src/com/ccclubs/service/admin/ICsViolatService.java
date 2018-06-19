package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsViolat;

/**
 * 车辆违章的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsViolatService
{
	/**
	 * 获取所有车辆违章
	 * @return
	 */
	public List<CsViolat> getCsViolatList(Map params,Integer size);
	
	/**
	 * 获取车辆违章统计
	 * @return
	 */
	public List getCsViolatStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆违章总数
	 * @return
	 */	
	public Long getCsViolatCount(Map params);
	
	
	/**
	 * 获取车辆违章自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsViolatEval(String eval,Map params);

	/**
	 * 获取车辆违章分页
	 * @return
	 */	
	public Page getCsViolatPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆违章
	 * @param params
	 * @return
	 */
	public CsViolat getCsViolat(Map params);

	/**
	 * 根据ID取车辆违章
	 * @param id
	 * @return
	 */
	public CsViolat getCsViolatById(Long id);


	/**
	 * 保存车辆违章
	 * @param csViolat
	 */
	public CsViolat saveCsViolat(CsViolat csViolat);

	/**
	 * 更新车辆违章
	 * @param csViolat
	 */
	public void updateCsViolat(CsViolat csViolat);
	/**
	 * 更新车辆违章非空字段
	 * @param csViolat
	 */
	public void updateCsViolat$NotNull(CsViolat csViolat);

	/**
	 * 根据ID删除一个车辆违章
	 * @param id
	 */
	public void deleteCsViolatById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆违章
	 * @param id
	 */
	public void removeCsViolatById(Long id);
	/**
	 * 根据条件更新车辆违章
	 * @param values
	 * @param params
	 */
	public void updateCsViolatByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆违章
	 * @param params
	 */
	public void deleteCsViolatByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}