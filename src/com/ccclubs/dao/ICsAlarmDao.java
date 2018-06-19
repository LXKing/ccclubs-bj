package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlarm;

/**
 * 车辆警报的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsAlarmDao
{
	/**
	 * 获取所有车辆警报
	 * @return
	 */
	public List<CsAlarm> getCsAlarmList(Map params,Integer size);
	
	/**
	 * 获取车辆警报统计
	 * @return
	 */
	public List getCsAlarmStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆警报总数
	 * @return
	 */	
	public Long getCsAlarmCount(Map params);
	
	/**
	 * 获取车辆警报自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlarmEval(String eval,Map params);
	
	/**
	 * 获取车辆警报分页
	 * @return
	 */	
	public Page getCsAlarmPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆警报
	 * @param params
	 * @return
	 */
	public CsAlarm getCsAlarm(Map params);	

	/**
	 * 根据ID取车辆警报
	 * @param id
	 * @return
	 */
	public CsAlarm getCsAlarmById(Long id);


	
	

	/**
	 * 保存车辆警报
	 * @param csAlarm
	 */
	public CsAlarm saveCsAlarm(CsAlarm csAlarm);

	/**
	 * 更新车辆警报
	 * @param csAlarm
	 */
	public void updateCsAlarm(CsAlarm csAlarm);
	/**
	 * 更新车辆警报非空字段
	 * @param csAlarm
	 */
	public void updateCsAlarm$NotNull(CsAlarm csAlarm);

	/**
	 * 根据ID删除一个车辆警报
	 * @param id
	 */
	public void deleteCsAlarmById(Long id);
	/**
	 * 根据条件更新车辆警报
	 * @param values
	 * @param params
	 */
	public void updateCsAlarmByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆警报
	 * @param params
	 */
	public void deleteCsAlarmByConfirm(Map params);

}