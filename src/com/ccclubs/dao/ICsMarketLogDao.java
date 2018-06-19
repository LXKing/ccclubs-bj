package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketLog;

/**
 * 营销跟踪的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMarketLogDao
{
	/**
	 * 获取所有营销跟踪
	 * @return
	 */
	public List<CsMarketLog> getCsMarketLogList(Map params,Integer size);
	
	/**
	 * 获取营销跟踪统计
	 * @return
	 */
	public List getCsMarketLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取营销跟踪总数
	 * @return
	 */	
	public Long getCsMarketLogCount(Map params);
	
	/**
	 * 获取营销跟踪自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMarketLogEval(String eval,Map params);
	
	/**
	 * 获取营销跟踪分页
	 * @return
	 */	
	public Page getCsMarketLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取营销跟踪
	 * @param params
	 * @return
	 */
	public CsMarketLog getCsMarketLog(Map params);	

	/**
	 * 根据ID取营销跟踪
	 * @param id
	 * @return
	 */
	public CsMarketLog getCsMarketLogById(Long id);


	
	

	/**
	 * 保存营销跟踪
	 * @param csMarketLog
	 */
	public CsMarketLog saveCsMarketLog(CsMarketLog csMarketLog);

	/**
	 * 更新营销跟踪
	 * @param csMarketLog
	 */
	public void updateCsMarketLog(CsMarketLog csMarketLog);
	/**
	 * 更新营销跟踪非空字段
	 * @param csMarketLog
	 */
	public void updateCsMarketLog$NotNull(CsMarketLog csMarketLog);

	/**
	 * 根据ID删除一个营销跟踪
	 * @param id
	 */
	public void deleteCsMarketLogById(Long id);
	/**
	 * 根据ID逻辑删除一个营销跟踪
	 * @param id
	 */
	public void removeCsMarketLogById(Long id);
	/**
	 * 根据条件更新营销跟踪
	 * @param values
	 * @param params
	 */
	public void updateCsMarketLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除营销跟踪
	 * @param params
	 */
	public void deleteCsMarketLogByConfirm(Map params);

}