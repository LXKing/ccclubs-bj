package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayLog;

/**
 * 接口日志的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsAlipayLogDao
{
	/**
	 * 获取所有接口日志
	 * @return
	 */
	public List<CsAlipayLog> getCsAlipayLogList(Map params,Integer size);
	
	/**
	 * 获取接口日志统计
	 * @return
	 */
	public List getCsAlipayLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取接口日志总数
	 * @return
	 */	
	public Long getCsAlipayLogCount(Map params);
	
	/**
	 * 获取接口日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlipayLogEval(String eval,Map params);
	
	/**
	 * 获取接口日志分页
	 * @return
	 */	
	public Page getCsAlipayLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取接口日志
	 * @param params
	 * @return
	 */
	public CsAlipayLog getCsAlipayLog(Map params);	

	/**
	 * 根据ID取接口日志
	 * @param id
	 * @return
	 */
	public CsAlipayLog getCsAlipayLogById(Long id);


	
	

	/**
	 * 保存接口日志
	 * @param csAlipayLog
	 */
	public CsAlipayLog saveCsAlipayLog(CsAlipayLog csAlipayLog);

	/**
	 * 更新接口日志
	 * @param csAlipayLog
	 */
	public void updateCsAlipayLog(CsAlipayLog csAlipayLog);
	/**
	 * 更新接口日志非空字段
	 * @param csAlipayLog
	 */
	public void updateCsAlipayLog$NotNull(CsAlipayLog csAlipayLog);

	/**
	 * 根据ID删除一个接口日志
	 * @param id
	 */
	public void deleteCsAlipayLogById(Long id);
	/**
	 * 根据ID逻辑删除一个接口日志
	 * @param id
	 */
	public void removeCsAlipayLogById(Long id);
	/**
	 * 根据条件更新接口日志
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除接口日志
	 * @param params
	 */
	public void deleteCsAlipayLogByConfirm(Map params);

}