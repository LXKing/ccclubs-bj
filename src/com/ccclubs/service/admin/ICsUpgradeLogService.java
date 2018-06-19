package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUpgradeLog;

/**
 * 升级日志的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUpgradeLogService
{
	/**
	 * 获取所有升级日志
	 * @return
	 */
	public List<CsUpgradeLog> getCsUpgradeLogList(Map params,Integer size);
	
	/**
	 * 获取升级日志统计
	 * @return
	 */
	public List getCsUpgradeLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取升级日志总数
	 * @return
	 */	
	public Long getCsUpgradeLogCount(Map params);
	
	
	/**
	 * 获取升级日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUpgradeLogEval(String eval,Map params);

	/**
	 * 获取升级日志分页
	 * @return
	 */	
	public Page getCsUpgradeLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取升级日志
	 * @param params
	 * @return
	 */
	public CsUpgradeLog getCsUpgradeLog(Map params);

	/**
	 * 根据ID取升级日志
	 * @param id
	 * @return
	 */
	public CsUpgradeLog getCsUpgradeLogById(Long id);


	/**
	 * 保存升级日志
	 * @param csUpgradeLog
	 */
	public CsUpgradeLog saveCsUpgradeLog(CsUpgradeLog csUpgradeLog);

	/**
	 * 更新升级日志
	 * @param csUpgradeLog
	 */
	public void updateCsUpgradeLog(CsUpgradeLog csUpgradeLog);
	/**
	 * 更新升级日志非空字段
	 * @param csUpgradeLog
	 */
	public void updateCsUpgradeLog$NotNull(CsUpgradeLog csUpgradeLog);

	/**
	 * 根据ID删除一个升级日志
	 * @param id
	 */
	public void deleteCsUpgradeLogById(Long id);
	/**
	 * 根据ID逻辑删除一个升级日志
	 * @param id
	 */
	public void removeCsUpgradeLogById(Long id);
	/**
	 * 根据条件更新升级日志
	 * @param values
	 * @param params
	 */
	public void updateCsUpgradeLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除升级日志
	 * @param params
	 */
	public void deleteCsUpgradeLogByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}