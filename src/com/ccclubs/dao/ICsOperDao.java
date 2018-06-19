package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOper;

/**
 * 运营商的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsOperDao
{
	/**
	 * 获取所有运营商
	 * @return
	 */
	public List<CsOper> getCsOperList(Map params,Integer size);
	
	/**
	 * 获取运营商统计
	 * @return
	 */
	public List getCsOperStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取运营商总数
	 * @return
	 */	
	public Long getCsOperCount(Map params);
	
	/**
	 * 获取运营商自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOperEval(String eval,Map params);
	
	/**
	 * 获取运营商分页
	 * @return
	 */	
	public Page getCsOperPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取运营商
	 * @param params
	 * @return
	 */
	public CsOper getCsOper(Map params);	

	/**
	 * 根据ID取运营商
	 * @param id
	 * @return
	 */
	public CsOper getCsOperById(Long id);


	
	

	/**
	 * 保存运营商
	 * @param csOper
	 */
	public CsOper saveCsOper(CsOper csOper);

	/**
	 * 更新运营商
	 * @param csOper
	 */
	public void updateCsOper(CsOper csOper);
	/**
	 * 更新运营商非空字段
	 * @param csOper
	 */
	public void updateCsOper$NotNull(CsOper csOper);

	/**
	 * 根据ID删除一个运营商
	 * @param id
	 */
	public void deleteCsOperById(Long id);
	/**
	 * 根据ID逻辑删除一个运营商
	 * @param id
	 */
	public void removeCsOperById(Long id);
	/**
	 * 根据条件更新运营商
	 * @param values
	 * @param params
	 */
	public void updateCsOperByConfirm(Map values, Map params);
	/**
	 * 根据条件删除运营商
	 * @param params
	 */
	public void deleteCsOperByConfirm(Map params);

}