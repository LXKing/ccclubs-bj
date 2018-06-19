package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChargePile;

/**
 * 充电桩的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChargePileService
{
	/**
	 * 获取所有充电桩
	 * @return
	 */
	public List<CsChargePile> getCsChargePileList(Map params,Integer size);
	
	/**
	 * 获取充电桩统计
	 * @return
	 */
	public List getCsChargePileStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取充电桩总数
	 * @return
	 */	
	public Long getCsChargePileCount(Map params);
	
	
	/**
	 * 获取充电桩自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChargePileEval(String eval,Map params);

	/**
	 * 获取充电桩分页
	 * @return
	 */	
	public Page getCsChargePilePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public CsChargePile getCsChargePile(Map params);

	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public CsChargePile getCsChargePileById(Long id);


	/**
	 * 保存充电桩
	 * @param csChargePile
	 */
	public CsChargePile saveCsChargePile(CsChargePile csChargePile);

	/**
	 * 更新充电桩
	 * @param csChargePile
	 */
	public void updateCsChargePile(CsChargePile csChargePile);
	/**
	 * 更新充电桩非空字段
	 * @param csChargePile
	 */
	public void updateCsChargePile$NotNull(CsChargePile csChargePile);

	/**
	 * 根据ID删除一个充电桩
	 * @param id
	 */
	public void deleteCsChargePileById(Long id);
	/**
	 * 根据条件更新充电桩
	 * @param values
	 * @param params
	 */
	public void updateCsChargePileByConfirm(Map values, Map params);
	/**
	 * 根据条件删除充电桩
	 * @param params
	 */
	public void deleteCsChargePileByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}