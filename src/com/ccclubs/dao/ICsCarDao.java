package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCar;

/**
 * 车辆的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCarDao
{
	/**
	 * 获取所有车辆
	 * @return
	 */
	public List<CsCar> getCsCarList(Map params,Integer size);
	
	/**
	 * 获取车辆统计
	 * @return
	 */
	public List getCsCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆总数
	 * @return
	 */	
	public Long getCsCarCount(Map params);
	
	/**
	 * 获取车辆自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarEval(String eval,Map params);
	
	/**
	 * 获取车辆分页
	 * @return
	 */	
	public Page getCsCarPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆
	 * @param params
	 * @return
	 */
	public CsCar getCsCar(Map params);	

	/**
	 * 根据ID取车辆
	 * @param id
	 * @return
	 */
	public CsCar getCsCarById(Long id);


	
	

	/**
	 * 保存车辆
	 * @param csCar
	 */
	public CsCar saveCsCar(CsCar csCar);

	/**
	 * 更新车辆
	 * @param csCar
	 */
	public void updateCsCar(CsCar csCar);
	/**
	 * 更新车辆非空字段
	 * @param csCar
	 */
	public void updateCsCar$NotNull(CsCar csCar);

	/**
	 * 根据ID删除一个车辆
	 * @param id
	 */
	public void deleteCsCarById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆
	 * @param id
	 */
	public void removeCsCarById(Long id);
	/**
	 * 根据条件更新车辆
	 * @param values
	 * @param params
	 */
	public void updateCsCarByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆
	 * @param params
	 */
	public void deleteCsCarByConfirm(Map params);

}