package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChannelLog;

/**
 * 渠道跟踪维护的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChannelLogService
{
	/**
	 * 获取所有渠道跟踪维护
	 * @return
	 */
	public List<CsChannelLog> getCsChannelLogList(Map params,Integer size);
	
	/**
	 * 获取渠道跟踪维护统计
	 * @return
	 */
	public List getCsChannelLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取渠道跟踪维护总数
	 * @return
	 */	
	public Long getCsChannelLogCount(Map params);
	
	
	/**
	 * 获取渠道跟踪维护自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChannelLogEval(String eval,Map params);

	/**
	 * 获取渠道跟踪维护分页
	 * @return
	 */	
	public Page getCsChannelLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取渠道跟踪维护
	 * @param params
	 * @return
	 */
	public CsChannelLog getCsChannelLog(Map params);

	/**
	 * 根据ID取渠道跟踪维护
	 * @param id
	 * @return
	 */
	public CsChannelLog getCsChannelLogById(Long id);


	/**
	 * 保存渠道跟踪维护
	 * @param csChannelLog
	 */
	public CsChannelLog saveCsChannelLog(CsChannelLog csChannelLog);

	/**
	 * 更新渠道跟踪维护
	 * @param csChannelLog
	 */
	public void updateCsChannelLog(CsChannelLog csChannelLog);
	/**
	 * 更新渠道跟踪维护非空字段
	 * @param csChannelLog
	 */
	public void updateCsChannelLog$NotNull(CsChannelLog csChannelLog);

	/**
	 * 根据ID删除一个渠道跟踪维护
	 * @param id
	 */
	public void deleteCsChannelLogById(Long id);
	/**
	 * 根据ID逻辑删除一个渠道跟踪维护
	 * @param id
	 */
	public void removeCsChannelLogById(Long id);
	/**
	 * 根据条件更新渠道跟踪维护
	 * @param values
	 * @param params
	 */
	public void updateCsChannelLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除渠道跟踪维护
	 * @param params
	 */
	public void deleteCsChannelLogByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}