package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsServAlarm;

/**
 * 服务监控报警的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsServAlarmService
{
	/**
	 * 获取所有服务监控报警
	 * @return
	 */
	public List<CsServAlarm> getCsServAlarmList(Map params,Integer size);
	
	/**
	 * 获取服务监控报警统计
	 * @return
	 */
	public List getCsServAlarmStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取服务监控报警总数
	 * @return
	 */	
	public Long getCsServAlarmCount(Map params);
	
	
	/**
	 * 获取服务监控报警自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsServAlarmEval(String eval,Map params);

	/**
	 * 获取服务监控报警分页
	 * @return
	 */	
	public Page getCsServAlarmPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取服务监控报警
	 * @param params
	 * @return
	 */
	public CsServAlarm getCsServAlarm(Map params);

	/**
	 * 根据ID取服务监控报警
	 * @param id
	 * @return
	 */
	public CsServAlarm getCsServAlarmById(Long id);


	/**
	 * 保存服务监控报警
	 * @param csServAlarm
	 */
	public CsServAlarm saveCsServAlarm(CsServAlarm csServAlarm);

	/**
	 * 更新服务监控报警
	 * @param csServAlarm
	 */
	public void updateCsServAlarm(CsServAlarm csServAlarm);
	/**
	 * 更新服务监控报警非空字段
	 * @param csServAlarm
	 */
	public void updateCsServAlarm$NotNull(CsServAlarm csServAlarm);

	/**
	 * 根据ID删除一个服务监控报警
	 * @param id
	 */
	public void deleteCsServAlarmById(Long id);
	/**
	 * 根据ID逻辑删除一个服务监控报警
	 * @param id
	 */
	public void removeCsServAlarmById(Long id);
	/**
	 * 根据条件更新服务监控报警
	 * @param values
	 * @param params
	 */
	public void updateCsServAlarmByConfirm(Map values, Map params);
	/**
	 * 根据条件删除服务监控报警
	 * @param params
	 */
	public void deleteCsServAlarmByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}