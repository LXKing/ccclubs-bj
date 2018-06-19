package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFavoriteAddress;

/**
 * 会员常用地址的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsFavoriteAddressDao
{
	/**
	 * 获取所有会员常用地址
	 * @return
	 */
	public List<CsFavoriteAddress> getCsFavoriteAddressList(Map params,Integer size);
	
	/**
	 * 获取会员常用地址统计
	 * @return
	 */
	public List getCsFavoriteAddressStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员常用地址总数
	 * @return
	 */	
	public Long getCsFavoriteAddressCount(Map params);
	
	/**
	 * 获取会员常用地址自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsFavoriteAddressEval(String eval,Map params);
	
	/**
	 * 获取会员常用地址分页
	 * @return
	 */	
	public Page getCsFavoriteAddressPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员常用地址
	 * @param params
	 * @return
	 */
	public CsFavoriteAddress getCsFavoriteAddress(Map params);	

	/**
	 * 根据ID取会员常用地址
	 * @param id
	 * @return
	 */
	public CsFavoriteAddress getCsFavoriteAddressById(Long id);


	
	

	/**
	 * 保存会员常用地址
	 * @param csMember
	 */
	public CsFavoriteAddress saveCsFavoriteAddress(CsFavoriteAddress csMember);

	/**
	 * 更新会员常用地址
	 * @param csMember
	 */
	public void updateCsFavoriteAddress(CsFavoriteAddress csMember);
	/**
	 * 更新会员常用地址非空字段
	 * @param csMember
	 */
	public void updateCsFavoriteAddress$NotNull(CsFavoriteAddress csMember);

	/**
	 * 根据ID删除一个会员常用地址
	 * @param id
	 */
	public void deleteCsFavoriteAddressById(Long id);
	/**
	 * 根据ID逻辑删除一个会员常用地址
	 * @param id
	 */
	public void removeCsFavoriteAddressById(Long id);
	/**
	 * 根据条件更新会员常用地址
	 * @param values
	 * @param params
	 */
	public void updateCsFavoriteAddressByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员常用地址
	 * @param params
	 */
	public void deleteCsFavoriteAddressByConfirm(Map params);

}