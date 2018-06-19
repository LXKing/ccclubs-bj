package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOutsideQr;

/**
 * 巡检作业的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsOutsideQrDao
{
	/**
	 * 获取所有巡检作业
	 * @return
	 */
	public List<CsOutsideQr> getCsOutsideQrList(Map params,Integer size);
	
	/**
	 * 获取巡检作业统计
	 * @return
	 */
	public List getCsOutsideQrStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取巡检作业总数
	 * @return
	 */	
	public Long getCsOutsideQrCount(Map params);
	
	/**
	 * 获取巡检作业自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOutsideQrEval(String eval,Map params);
	
	/**
	 * 获取巡检作业分页
	 * @return
	 */	
	public Page getCsOutsideQrPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取巡检作业
	 * @param params
	 * @return
	 */
	public CsOutsideQr getCsOutsideQr(Map params);	

	/**
	 * 根据ID取巡检作业
	 * @param id
	 * @return
	 */
	public CsOutsideQr getCsOutsideQrById(Long id);


	
	

	/**
	 * 保存巡检作业
	 * @param csOutsideQr
	 */
	public CsOutsideQr saveCsOutsideQr(CsOutsideQr csOutsideQr);

	/**
	 * 更新巡检作业
	 * @param csOutsideQr
	 */
	public void updateCsOutsideQr(CsOutsideQr csOutsideQr);
	/**
	 * 更新巡检作业非空字段
	 * @param csOutsideQr
	 */
	public void updateCsOutsideQr$NotNull(CsOutsideQr csOutsideQr);

	/**
	 * 根据ID删除一个巡检作业
	 * @param id
	 */
	public void deleteCsOutsideQrById(Long id);
	/**
	 * 根据ID逻辑删除一个巡检作业
	 * @param id
	 */
	public void removeCsOutsideQrById(Long id);
	/**
	 * 根据条件更新巡检作业
	 * @param values
	 * @param params
	 */
	public void updateCsOutsideQrByConfirm(Map values, Map params);
	/**
	 * 根据条件删除巡检作业
	 * @param params
	 */
	public void deleteCsOutsideQrByConfirm(Map params);

}