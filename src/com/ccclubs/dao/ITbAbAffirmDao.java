package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbAbAffirm;

/**
 * 异借确认的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbAbAffirmDao
{
	/**
	 * 获取所有异借确认
	 * @return
	 */
	public List<TbAbAffirm> getTbAbAffirmList(Map params,Integer size);
	
	/**
	 * 获取异借确认统计
	 * @return
	 */
	public List getTbAbAffirmStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取异借确认总数
	 * @return
	 */	
	public Long getTbAbAffirmCount(Map params);
	
	/**
	 * 获取异借确认自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbAbAffirmEval(String eval,Map params);
	
	/**
	 * 获取异借确认分页
	 * @return
	 */	
	public Page getTbAbAffirmPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取异借确认
	 * @param params
	 * @return
	 */
	public TbAbAffirm getTbAbAffirm(Map params);	

	/**
	 * 根据ID取异借确认
	 * @param id
	 * @return
	 */
	public TbAbAffirm getTbAbAffirmById(Long id);


	
	

	/**
	 * 保存异借确认
	 * @param tbAbAffirm
	 */
	public TbAbAffirm saveTbAbAffirm(TbAbAffirm tbAbAffirm);

	/**
	 * 更新异借确认
	 * @param tbAbAffirm
	 */
	public void updateTbAbAffirm(TbAbAffirm tbAbAffirm);
	/**
	 * 更新异借确认非空字段
	 * @param tbAbAffirm
	 */
	public void updateTbAbAffirm$NotNull(TbAbAffirm tbAbAffirm);

	/**
	 * 根据ID删除一个异借确认
	 * @param id
	 */
	public void deleteTbAbAffirmById(Long id);
	/**
	 * 根据ID逻辑删除一个异借确认
	 * @param id
	 */
	public void removeTbAbAffirmById(Long id);
	/**
	 * 根据条件更新异借确认
	 * @param values
	 * @param params
	 */
	public void updateTbAbAffirmByConfirm(Map values, Map params);
	/**
	 * 根据条件删除异借确认
	 * @param params
	 */
	public void deleteTbAbAffirmByConfirm(Map params);

}