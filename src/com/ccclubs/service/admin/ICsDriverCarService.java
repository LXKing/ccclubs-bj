package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsDriverCar;

/**
 * 司机车辆的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsDriverCarService
{
	/**
	 * 获取所有司机车辆
	 * @return
	 */
	public List<CsDriverCar> getCsDriverCarList(Map params,Integer size);
	
	/**
	 * 获取司机车辆统计
	 * @return
	 */
	public List getCsDriverCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取司机车辆总数
	 * @return
	 */	
	public Long getCsDriverCarCount(Map params);
	
	
	/**
	 * 获取司机车辆自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsDriverCarEval(String eval,Map params);

	/**
	 * 获取司机车辆分页
	 * @return
	 */	
	public Page getCsDriverCarPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取司机车辆
	 * @param params
	 * @return
	 */
	public CsDriverCar getCsDriverCar(Map params);

	/**
	 * 根据ID取司机车辆
	 * @param id
	 * @return
	 */
	public CsDriverCar getCsDriverCarById(Long id);


	/**
	 * 保存司机车辆
	 * @param csDriverCar
	 */
	public CsDriverCar saveCsDriverCar(CsDriverCar csDriverCar);

	/**
	 * 更新司机车辆
	 * @param csDriverCar
	 */
	public void updateCsDriverCar(CsDriverCar csDriverCar);
	/**
	 * 更新司机车辆非空字段
	 * @param csDriverCar
	 */
	public void updateCsDriverCar$NotNull(CsDriverCar csDriverCar);

	/**
	 * 根据ID删除一个司机车辆
	 * @param id
	 */
	public void deleteCsDriverCarById(Long id);
	/**
	 * 根据ID逻辑删除一个司机车辆
	 * @param id
	 */
	public void removeCsDriverCarById(Long id);
	/**
	 * 根据条件更新司机车辆
	 * @param values
	 * @param params
	 */
	public void updateCsDriverCarByConfirm(Map values, Map params);
	/**
	 * 根据条件删除司机车辆
	 * @param params
	 */
	public void deleteCsDriverCarByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}