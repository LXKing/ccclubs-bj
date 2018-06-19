package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarModel;

/**
 * 车型的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCarModelDao
{
	/**
	 * 获取所有车型
	 * @return
	 */
	public List<CsCarModel> getCsCarModelList(Map params,Integer size);
	
	/**
	 * 获取车型统计
	 * @return
	 */
	public List getCsCarModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车型总数
	 * @return
	 */	
	public Long getCsCarModelCount(Map params);
	
	/**
	 * 获取车型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarModelEval(String eval,Map params);
	
	/**
	 * 获取车型分页
	 * @return
	 */	
	public Page getCsCarModelPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车型
	 * @param params
	 * @return
	 */
	public CsCarModel getCsCarModel(Map params);	

	/**
	 * 根据ID取车型
	 * @param id
	 * @return
	 */
	public CsCarModel getCsCarModelById(Long id);


	
	

	/**
	 * 保存车型
	 * @param csCarModel
	 */
	public CsCarModel saveCsCarModel(CsCarModel csCarModel);

	/**
	 * 更新车型
	 * @param csCarModel
	 */
	public void updateCsCarModel(CsCarModel csCarModel);
	/**
	 * 更新车型非空字段
	 * @param csCarModel
	 */
	public void updateCsCarModel$NotNull(CsCarModel csCarModel);

	/**
	 * 根据ID删除一个车型
	 * @param id
	 */
	public void deleteCsCarModelById(Long id);
	/**
	 * 根据ID逻辑删除一个车型
	 * @param id
	 */
	public void removeCsCarModelById(Long id);
	/**
	 * 根据条件更新车型
	 * @param values
	 * @param params
	 */
	public void updateCsCarModelByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车型
	 * @param params
	 */
	public void deleteCsCarModelByConfirm(Map params);

}