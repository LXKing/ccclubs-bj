package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsError;

/**
 * 错误信息的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsErrorService
{
	/**
	 * 获取所有错误信息
	 * @return
	 */
	public List<CsError> getCsErrorList(Map params,Integer size);
	
	/**
	 * 获取错误信息统计
	 * @return
	 */
	public List getCsErrorStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取错误信息总数
	 * @return
	 */	
	public Long getCsErrorCount(Map params);
	
	
	/**
	 * 获取错误信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsErrorEval(String eval,Map params);

	/**
	 * 获取错误信息分页
	 * @return
	 */	
	public Page getCsErrorPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取错误信息
	 * @param params
	 * @return
	 */
	public CsError getCsError(Map params);

	/**
	 * 根据ID取错误信息
	 * @param id
	 * @return
	 */
	public CsError getCsErrorById(Long id);


	/**
	 * 保存错误信息
	 * @param csError
	 */
	public CsError saveCsError(CsError csError);

	/**
	 * 更新错误信息
	 * @param csError
	 */
	public void updateCsError(CsError csError);
	/**
	 * 更新错误信息非空字段
	 * @param csError
	 */
	public void updateCsError$NotNull(CsError csError);

	/**
	 * 根据ID删除一个错误信息
	 * @param id
	 */
	public void deleteCsErrorById(Long id);
	/**
	 * 根据条件更新错误信息
	 * @param values
	 * @param params
	 */
	public void updateCsErrorByConfirm(Map values, Map params);
	/**
	 * 根据条件删除错误信息
	 * @param params
	 */
	public void deleteCsErrorByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}