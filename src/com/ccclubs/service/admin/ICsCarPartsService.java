package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCarParts;

/**
 * 车辆部件的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsCarPartsService
{
	/**
	 * 获取所有车辆部件
	 * @return
	 */
	public List<CsCarParts> getCsCarPartsList(Map params,Integer size);
	
	/**
	 * 获取车辆部件统计
	 * @return
	 */
	public List getCsCarPartsStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆部件总数
	 * @return
	 */	
	public Long getCsCarPartsCount(Map params);
	
	
	/**
	 * 获取车辆部件自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarPartsEval(String eval,Map params);

	/**
	 * 获取车辆部件分页
	 * @return
	 */	
	public Page getCsCarPartsPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆部件
	 * @param params
	 * @return
	 */
	public CsCarParts getCsCarParts(Map params);

	/**
	 * 根据ID取车辆部件
	 * @param id
	 * @return
	 */
	public CsCarParts getCsCarPartsById(Long id);


	/**
	 * 保存车辆部件
	 * @param csCarParts
	 */
	public CsCarParts saveCsCarParts(CsCarParts csCarParts);

	/**
	 * 更新车辆部件
	 * @param csCarParts
	 */
	public void updateCsCarParts(CsCarParts csCarParts);
	/**
	 * 更新车辆部件非空字段
	 * @param csCarParts
	 */
	public void updateCsCarParts$NotNull(CsCarParts csCarParts);

	/**
	 * 根据ID删除一个车辆部件
	 * @param id
	 */
	public void deleteCsCarPartsById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆部件
	 * @param id
	 */
	public void removeCsCarPartsById(Long id);
	/**
	 * 根据条件更新车辆部件
	 * @param values
	 * @param params
	 */
	public void updateCsCarPartsByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆部件
	 * @param params
	 */
	public void deleteCsCarPartsByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}