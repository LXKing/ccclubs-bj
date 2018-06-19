package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberInfo;

/**
 * 会员信息的Dao接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsMemberInfoDao
{
	/**
	 * 获取所有会员信息
	 * @return
	 */
	public List<CsMemberInfo> getCsMemberInfoList(Map params,Integer size);
	
	/**
	 * 获取会员信息统计
	 * @return
	 */
	public List getCsMemberInfoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员信息总数
	 * @return
	 */	
	public Long getCsMemberInfoCount(Map params);
	
	/**
	 * 获取会员信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberInfoEval(String eval,Map params);
	
	/**
	 * 获取会员信息分页
	 * @return
	 */	
	public Page getCsMemberInfoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员信息
	 * @param params
	 * @return
	 */
	public CsMemberInfo getCsMemberInfo(Map params);	

	/**
	 * 根据ID取会员信息
	 * @param id
	 * @return
	 */
	public CsMemberInfo getCsMemberInfoById(Long id);


	
	

	/**
	 * 保存会员信息
	 * @param csMemberInfo
	 */
	public CsMemberInfo saveCsMemberInfo(CsMemberInfo csMemberInfo);

	/**
	 * 更新会员信息
	 * @param csMemberInfo
	 */
	public void updateCsMemberInfo(CsMemberInfo csMemberInfo);
	/**
	 * 更新会员信息非空字段
	 * @param csMemberInfo
	 */
	public void updateCsMemberInfo$NotNull(CsMemberInfo csMemberInfo);

	/**
	 * 根据ID删除一个会员信息
	 * @param id
	 */
	public void deleteCsMemberInfoById(Long id);
	/**
	 * 根据ID逻辑删除一个会员信息
	 * @param id
	 */
	public void removeCsMemberInfoById(Long id);
	/**
	 * 根据条件更新会员信息
	 * @param values
	 * @param params
	 */
	public void updateCsMemberInfoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员信息
	 * @param params
	 */
	public void deleteCsMemberInfoByConfirm(Map params);

}