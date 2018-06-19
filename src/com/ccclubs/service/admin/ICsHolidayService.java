package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsHoliday;

/**
 * 节假日的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsHolidayService
{
	/**
	 * 获取所有节假日
	 * @return
	 */
	public List<CsHoliday> getCsHolidayList(Map params,Integer size);
	
	/**
	 * 获取节假日统计
	 * @return
	 */
	public List getCsHolidayStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取节假日总数
	 * @return
	 */	
	public Long getCsHolidayCount(Map params);
	
	
	/**
	 * 获取节假日自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsHolidayEval(String eval,Map params);

	/**
	 * 获取节假日分页
	 * @return
	 */	
	public Page getCsHolidayPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取节假日
	 * @param params
	 * @return
	 */
	public CsHoliday getCsHoliday(Map params);

	/**
	 * 根据ID取节假日
	 * @param id
	 * @return
	 */
	public CsHoliday getCsHolidayById(Long id);


	/**
	 * 保存节假日
	 * @param csHoliday
	 */
	public CsHoliday saveCsHoliday(CsHoliday csHoliday);

	/**
	 * 更新节假日
	 * @param csHoliday
	 */
	public void updateCsHoliday(CsHoliday csHoliday);
	/**
	 * 更新节假日非空字段
	 * @param csHoliday
	 */
	public void updateCsHoliday$NotNull(CsHoliday csHoliday);

	/**
	 * 根据ID删除一个节假日
	 * @param id
	 */
	public void deleteCsHolidayById(Long id);
	/**
	 * 根据条件更新节假日
	 * @param values
	 * @param params
	 */
	public void updateCsHolidayByConfirm(Map values, Map params);
	/**
	 * 根据条件删除节假日
	 * @param params
	 */
	public void deleteCsHolidayByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}