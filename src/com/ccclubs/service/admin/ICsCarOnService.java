package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCarOn;

/**
 * 车辆上线记录的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCarOnService
{
	/**
	 * 获取所有车辆上线记录
	 * @return
	 */
	public List<CsCarOn> getCsCarOnList(Map params,Integer size);
	
	/**
	 * 获取车辆上线记录统计
	 * @return
	 */
	public List getCsCarOnStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆上线记录总数
	 * @return
	 */	
	public Long getCsCarOnCount(Map params);
	
	
	/**
	 * 获取车辆上线记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarOnEval(String eval,Map params);

	/**
	 * 获取车辆上线记录分页
	 * @return
	 */	
	public Page getCsCarOnPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆上线记录
	 * @param params
	 * @return
	 */
	public CsCarOn getCsCarOn(Map params);

	/**
	 * 根据ID取车辆上线记录
	 * @param id
	 * @return
	 */
	public CsCarOn getCsCarOnById(Long id);


	/**
	 * 保存车辆上线记录
	 * @param csCarOn
	 */
	public CsCarOn saveCsCarOn(CsCarOn csCarOn);

	/**
	 * 更新车辆上线记录
	 * @param csCarOn
	 */
	public void updateCsCarOn(CsCarOn csCarOn);
	/**
	 * 更新车辆上线记录非空字段
	 * @param csCarOn
	 */
	public void updateCsCarOn$NotNull(CsCarOn csCarOn);

	/**
	 * 根据ID删除一个车辆上线记录
	 * @param id
	 */
	public void deleteCsCarOnById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆上线记录
	 * @param id
	 */
	public void removeCsCarOnById(Long id);
	/**
	 * 根据条件更新车辆上线记录
	 * @param values
	 * @param params
	 */
	public void updateCsCarOnByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆上线记录
	 * @param params
	 */
	public void deleteCsCarOnByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}