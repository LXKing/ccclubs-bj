package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvModel;

/**
 * 表单设计的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvModelDao
{
	/**
	 * 获取所有表单设计
	 * @return
	 */
	public List<SrvModel> getSrvModelList(Map params,Integer size);
	
	/**
	 * 获取表单设计统计
	 * @return
	 */
	public List getSrvModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取表单设计总数
	 * @return
	 */	
	public Long getSrvModelCount(Map params);
	
	/**
	 * 获取表单设计自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvModelEval(String eval,Map params);
	
	/**
	 * 获取表单设计分页
	 * @return
	 */	
	public Page getSrvModelPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取表单设计
	 * @param params
	 * @return
	 */
	public SrvModel getSrvModel(Map params);	

	/**
	 * 根据ID取表单设计
	 * @param id
	 * @return
	 */
	public SrvModel getSrvModelById(Long id);


	
	

	/**
	 * 保存表单设计
	 * @param srvModel
	 */
	public SrvModel saveSrvModel(SrvModel srvModel);

	/**
	 * 更新表单设计
	 * @param srvModel
	 */
	public void updateSrvModel(SrvModel srvModel);
	/**
	 * 更新表单设计非空字段
	 * @param srvModel
	 */
	public void updateSrvModel$NotNull(SrvModel srvModel);

	/**
	 * 根据ID删除一个表单设计
	 * @param id
	 */
	public void deleteSrvModelById(Long id);
	/**
	 * 根据ID逻辑删除一个表单设计
	 * @param id
	 */
	public void removeSrvModelById(Long id);
	/**
	 * 根据条件更新表单设计
	 * @param values
	 * @param params
	 */
	public void updateSrvModelByConfirm(Map values, Map params);
	/**
	 * 根据条件删除表单设计
	 * @param params
	 */
	public void deleteSrvModelByConfirm(Map params);

}