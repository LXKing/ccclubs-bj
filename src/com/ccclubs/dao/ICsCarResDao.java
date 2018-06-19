package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarRes;

/**
 * 专车资源的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsCarResDao
{
	/**
	 * 获取所有专车资源
	 * @return
	 */
	public List<CsCarRes> getCsCarResList(Map params,Integer size);
	
	/**
	 * 获取专车资源统计
	 * @return
	 */
	public List getCsCarResStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取专车资源总数
	 * @return
	 */	
	public Long getCsCarResCount(Map params);
	
	/**
	 * 获取专车资源自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarResEval(String eval,Map params);
	
	/**
	 * 获取专车资源分页
	 * @return
	 */	
	public Page getCsCarResPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取专车资源
	 * @param params
	 * @return
	 */
	public CsCarRes getCsCarRes(Map params);	

	/**
	 * 根据ID取专车资源
	 * @param id
	 * @return
	 */
	public CsCarRes getCsCarResById(Long id);


	
	

	/**
	 * 保存专车资源
	 * @param csCarRes
	 */
	public CsCarRes saveCsCarRes(CsCarRes csCarRes);

	/**
	 * 更新专车资源
	 * @param csCarRes
	 */
	public void updateCsCarRes(CsCarRes csCarRes);
	/**
	 * 更新专车资源非空字段
	 * @param csCarRes
	 */
	public void updateCsCarRes$NotNull(CsCarRes csCarRes);

	/**
	 * 根据ID删除一个专车资源
	 * @param id
	 */
	public void deleteCsCarResById(Long id);
	/**
	 * 根据ID逻辑删除一个专车资源
	 * @param id
	 */
	public void removeCsCarResById(Long id);
	/**
	 * 根据条件更新专车资源
	 * @param values
	 * @param params
	 */
	public void updateCsCarResByConfirm(Map values, Map params);
	/**
	 * 根据条件删除专车资源
	 * @param params
	 */
	public void deleteCsCarResByConfirm(Map params);

}