package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSpecialCar;

/**
 * 专车订单的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsSpecialCarDao
{
	/**
	 * 获取所有专车订单
	 * @return
	 */
	public List<CsSpecialCar> getCsSpecialCarList(Map params,Integer size);
	
	/**
	 * 获取专车订单统计
	 * @return
	 */
	public List getCsSpecialCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取专车订单总数
	 * @return
	 */	
	public Long getCsSpecialCarCount(Map params);
	
	/**
	 * 获取专车订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSpecialCarEval(String eval,Map params);
	
	/**
	 * 获取专车订单分页
	 * @return
	 */	
	public Page getCsSpecialCarPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取专车订单
	 * @param params
	 * @return
	 */
	public CsSpecialCar getCsSpecialCar(Map params);	

	/**
	 * 根据ID取专车订单
	 * @param id
	 * @return
	 */
	public CsSpecialCar getCsSpecialCarById(Long id);


	
	

	/**
	 * 保存专车订单
	 * @param csSpecialCar
	 */
	public CsSpecialCar saveCsSpecialCar(CsSpecialCar csSpecialCar);

	/**
	 * 更新专车订单
	 * @param csSpecialCar
	 */
	public void updateCsSpecialCar(CsSpecialCar csSpecialCar);
	/**
	 * 更新专车订单非空字段
	 * @param csSpecialCar
	 */
	public void updateCsSpecialCar$NotNull(CsSpecialCar csSpecialCar);

	/**
	 * 根据ID删除一个专车订单
	 * @param id
	 */
	public void deleteCsSpecialCarById(Long id);
	/**
	 * 根据ID逻辑删除一个专车订单
	 * @param id
	 */
	public void removeCsSpecialCarById(Long id);
	/**
	 * 根据条件更新专车订单
	 * @param values
	 * @param params
	 */
	public void updateCsSpecialCarByConfirm(Map values, Map params);
	/**
	 * 根据条件删除专车订单
	 * @param params
	 */
	public void deleteCsSpecialCarByConfirm(Map params);

}