package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberMemo;

/**
 * 会员备忘的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMemberMemoDao
{
	/**
	 * 获取所有会员备忘
	 * @return
	 */
	public List<CsMemberMemo> getCsMemberMemoList(Map params,Integer size);
	
	/**
	 * 获取会员备忘统计
	 * @return
	 */
	public List getCsMemberMemoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员备忘总数
	 * @return
	 */	
	public Long getCsMemberMemoCount(Map params);
	
	/**
	 * 获取会员备忘自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberMemoEval(String eval,Map params);
	
	/**
	 * 获取会员备忘分页
	 * @return
	 */	
	public Page getCsMemberMemoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员备忘
	 * @param params
	 * @return
	 */
	public CsMemberMemo getCsMemberMemo(Map params);	

	/**
	 * 根据ID取会员备忘
	 * @param id
	 * @return
	 */
	public CsMemberMemo getCsMemberMemoById(Long id);


	
	

	/**
	 * 保存会员备忘
	 * @param csMemberMemo
	 */
	public CsMemberMemo saveCsMemberMemo(CsMemberMemo csMemberMemo);

	/**
	 * 更新会员备忘
	 * @param csMemberMemo
	 */
	public void updateCsMemberMemo(CsMemberMemo csMemberMemo);
	/**
	 * 更新会员备忘非空字段
	 * @param csMemberMemo
	 */
	public void updateCsMemberMemo$NotNull(CsMemberMemo csMemberMemo);

	/**
	 * 根据ID删除一个会员备忘
	 * @param id
	 */
	public void deleteCsMemberMemoById(Long id);
	/**
	 * 根据ID逻辑删除一个会员备忘
	 * @param id
	 */
	public void removeCsMemberMemoById(Long id);
	/**
	 * 根据条件更新会员备忘
	 * @param values
	 * @param params
	 */
	public void updateCsMemberMemoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员备忘
	 * @param params
	 */
	public void deleteCsMemberMemoByConfirm(Map params);

}