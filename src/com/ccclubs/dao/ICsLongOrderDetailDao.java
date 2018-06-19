package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrderDetail;

/**
 * 长单明细的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLongOrderDetailDao
{
	/**
	 * 获取所有长单明细
	 * @return
	 */
	public List<CsLongOrderDetail> getCsLongOrderDetailList(Map params,Integer size);
	
	/**
	 * 获取长单明细统计
	 * @return
	 */
	public List getCsLongOrderDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取长单明细总数
	 * @return
	 */	
	public Long getCsLongOrderDetailCount(Map params);
	
	/**
	 * 获取长单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderDetailEval(String eval,Map params);
	
	/**
	 * 获取长单明细分页
	 * @return
	 */	
	public Page getCsLongOrderDetailPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取长单明细
	 * @param params
	 * @return
	 */
	public CsLongOrderDetail getCsLongOrderDetail(Map params);	

	/**
	 * 根据ID取长单明细
	 * @param id
	 * @return
	 */
	public CsLongOrderDetail getCsLongOrderDetailById(Long id);


	
	

	/**
	 * 保存长单明细
	 * @param csLongOrderDetail
	 */
	public CsLongOrderDetail saveCsLongOrderDetail(CsLongOrderDetail csLongOrderDetail);

	/**
	 * 更新长单明细
	 * @param csLongOrderDetail
	 */
	public void updateCsLongOrderDetail(CsLongOrderDetail csLongOrderDetail);
	/**
	 * 更新长单明细非空字段
	 * @param csLongOrderDetail
	 */
	public void updateCsLongOrderDetail$NotNull(CsLongOrderDetail csLongOrderDetail);

	/**
	 * 根据ID删除一个长单明细
	 * @param id
	 */
	public void deleteCsLongOrderDetailById(Long id);
	/**
	 * 根据ID逻辑删除一个长单明细
	 * @param id
	 */
	public void removeCsLongOrderDetailById(Long id);
	/**
	 * 根据条件更新长单明细
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderDetailByConfirm(Map values, Map params);
	/**
	 * 根据条件删除长单明细
	 * @param params
	 */
	public void deleteCsLongOrderDetailByConfirm(Map params);

}