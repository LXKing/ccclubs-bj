package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSpecialModel;

/**
 * 专车车型的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSpecialModelDao
{
	/**
	 * 获取所有专车车型
	 * @return
	 */
	public List<CsSpecialModel> getCsSpecialModelList(Map params,Integer size);
	
	/**
	 * 获取专车车型统计
	 * @return
	 */
	public List getCsSpecialModelStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取专车车型总数
	 * @return
	 */	
	public Long getCsSpecialModelCount(Map params);
	
	/**
	 * 获取专车车型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSpecialModelEval(String eval,Map params);
	
	/**
	 * 获取专车车型分页
	 * @return
	 */	
	public Page getCsSpecialModelPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取专车车型
	 * @param params
	 * @return
	 */
	public CsSpecialModel getCsSpecialModel(Map params);	

	/**
	 * 根据ID取专车车型
	 * @param id
	 * @return
	 */
	public CsSpecialModel getCsSpecialModelById(Long id);


	
	

	/**
	 * 保存专车车型
	 * @param csSpecialModel
	 */
	public CsSpecialModel saveCsSpecialModel(CsSpecialModel csSpecialModel);

	/**
	 * 更新专车车型
	 * @param csSpecialModel
	 */
	public void updateCsSpecialModel(CsSpecialModel csSpecialModel);
	/**
	 * 更新专车车型非空字段
	 * @param csSpecialModel
	 */
	public void updateCsSpecialModel$NotNull(CsSpecialModel csSpecialModel);

	/**
	 * 根据ID删除一个专车车型
	 * @param id
	 */
	public void deleteCsSpecialModelById(Long id);
	/**
	 * 根据ID逻辑删除一个专车车型
	 * @param id
	 */
	public void removeCsSpecialModelById(Long id);
	/**
	 * 根据条件更新专车车型
	 * @param values
	 * @param params
	 */
	public void updateCsSpecialModelByConfirm(Map values, Map params);
	/**
	 * 根据条件删除专车车型
	 * @param params
	 */
	public void deleteCsSpecialModelByConfirm(Map params);

}