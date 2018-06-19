package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsLog;

/**
 * 系统日志的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLogService
{
	/**
	 * 获取所有系统日志
	 * @return
	 */
	public List<CsLog> getCsLogList(Map params,Integer size);
	
	/**
	 * 获取系统日志统计
	 * @return
	 */
	public List getCsLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统日志总数
	 * @return
	 */	
	public Long getCsLogCount(Map params);
	
	
	/**
	 * 获取系统日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLogEval(String eval,Map params);

	/**
	 * 获取系统日志分页
	 * @return
	 */	
	public Page getCsLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统日志
	 * @param params
	 * @return
	 */
	public CsLog getCsLog(Map params);

	/**
	 * 根据ID取系统日志
	 * @param id
	 * @return
	 */
	public CsLog getCsLogById(Long id);


	/**
	 * 保存系统日志
	 * @param csLog
	 */
	public CsLog saveCsLog(CsLog csLog);

	/**
	 * 更新系统日志
	 * @param csLog
	 */
	public void updateCsLog(CsLog csLog);
	/**
	 * 更新系统日志非空字段
	 * @param csLog
	 */
	public void updateCsLog$NotNull(CsLog csLog);

	/**
	 * 根据ID删除一个系统日志
	 * @param id
	 */
	public void deleteCsLogById(Long id);
	/**
	 * 根据条件更新系统日志
	 * @param values
	 * @param params
	 */
	public void updateCsLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统日志
	 * @param params
	 */
	public void deleteCsLogByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}