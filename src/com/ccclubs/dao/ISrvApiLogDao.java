package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApiLog;

/**
 * API日志的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvApiLogDao
{
	/**
	 * 获取所有API日志
	 * @return
	 */
	public List<SrvApiLog> getSrvApiLogList(Map params,Integer size);
	
	/**
	 * 获取API日志统计
	 * @return
	 */
	public List getSrvApiLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取API日志总数
	 * @return
	 */	
	public Long getSrvApiLogCount(Map params);
	
	/**
	 * 获取API日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvApiLogEval(String eval,Map params);
	
	/**
	 * 获取API日志分页
	 * @return
	 */	
	public Page getSrvApiLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取API日志
	 * @param params
	 * @return
	 */
	public SrvApiLog getSrvApiLog(Map params);	

	/**
	 * 根据ID取API日志
	 * @param id
	 * @return
	 */
	public SrvApiLog getSrvApiLogById(Long id);


	
	

	/**
	 * 保存API日志
	 * @param srvApiLog
	 */
	public SrvApiLog saveSrvApiLog(SrvApiLog srvApiLog);

	/**
	 * 更新API日志
	 * @param srvApiLog
	 */
	public void updateSrvApiLog(SrvApiLog srvApiLog);
	/**
	 * 更新API日志非空字段
	 * @param srvApiLog
	 */
	public void updateSrvApiLog$NotNull(SrvApiLog srvApiLog);

	/**
	 * 根据ID删除一个API日志
	 * @param id
	 */
	public void deleteSrvApiLogById(Long id);
	/**
	 * 根据ID逻辑删除一个API日志
	 * @param id
	 */
	public void removeSrvApiLogById(Long id);
	/**
	 * 根据条件更新API日志
	 * @param values
	 * @param params
	 */
	public void updateSrvApiLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除API日志
	 * @param params
	 */
	public void deleteSrvApiLogByConfirm(Map params);

}