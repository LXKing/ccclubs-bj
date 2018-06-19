package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMaintain;

/**
 * 车辆保养的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMaintainDao
{
	/**
	 * 获取所有车辆保养
	 * @return
	 */
	public List<CsMaintain> getCsMaintainList(Map params,Integer size);
	
	/**
	 * 获取车辆保养统计
	 * @return
	 */
	public List getCsMaintainStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆保养总数
	 * @return
	 */	
	public Long getCsMaintainCount(Map params);
	
	/**
	 * 获取车辆保养自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMaintainEval(String eval,Map params);
	
	/**
	 * 获取车辆保养分页
	 * @return
	 */	
	public Page getCsMaintainPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆保养
	 * @param params
	 * @return
	 */
	public CsMaintain getCsMaintain(Map params);	

	/**
	 * 根据ID取车辆保养
	 * @param id
	 * @return
	 */
	public CsMaintain getCsMaintainById(Long id);


	
	

	/**
	 * 保存车辆保养
	 * @param csMaintain
	 */
	public CsMaintain saveCsMaintain(CsMaintain csMaintain);

	/**
	 * 更新车辆保养
	 * @param csMaintain
	 */
	public void updateCsMaintain(CsMaintain csMaintain);
	/**
	 * 更新车辆保养非空字段
	 * @param csMaintain
	 */
	public void updateCsMaintain$NotNull(CsMaintain csMaintain);

	/**
	 * 根据ID删除一个车辆保养
	 * @param id
	 */
	public void deleteCsMaintainById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆保养
	 * @param id
	 */
	public void removeCsMaintainById(Long id);
	/**
	 * 根据条件更新车辆保养
	 * @param values
	 * @param params
	 */
	public void updateCsMaintainByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆保养
	 * @param params
	 */
	public void deleteCsMaintainByConfirm(Map params);

}