package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsAddress;

/**
 * 会员地址的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsAddressService
{
	/**
	 * 获取所有会员地址
	 * @return
	 */
	public List<CsAddress> getCsAddressList(Map params,Integer size);
	
	/**
	 * 获取会员地址统计
	 * @return
	 */
	public List getCsAddressStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员地址总数
	 * @return
	 */	
	public Long getCsAddressCount(Map params);
	
	
	/**
	 * 获取会员地址自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAddressEval(String eval,Map params);

	/**
	 * 获取会员地址分页
	 * @return
	 */	
	public Page getCsAddressPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员地址
	 * @param params
	 * @return
	 */
	public CsAddress getCsAddress(Map params);

	/**
	 * 根据ID取会员地址
	 * @param id
	 * @return
	 */
	public CsAddress getCsAddressById(Long id);


	/**
	 * 保存会员地址
	 * @param csAddress
	 */
	public CsAddress saveCsAddress(CsAddress csAddress);

	/**
	 * 更新会员地址
	 * @param csAddress
	 */
	public void updateCsAddress(CsAddress csAddress);
	/**
	 * 更新会员地址非空字段
	 * @param csAddress
	 */
	public void updateCsAddress$NotNull(CsAddress csAddress);

	/**
	 * 根据ID删除一个会员地址
	 * @param id
	 */
	public void deleteCsAddressById(Long id);
	/**
	 * 根据ID逻辑删除一个会员地址
	 * @param id
	 */
	public void removeCsAddressById(Long id);
	/**
	 * 根据条件更新会员地址
	 * @param values
	 * @param params
	 */
	public void updateCsAddressByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员地址
	 * @param params
	 */
	public void deleteCsAddressByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}