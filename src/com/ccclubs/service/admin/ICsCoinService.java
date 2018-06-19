package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCoin;

/**
 * 红包的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsCoinService
{
	/**
	 * 获取所有红包
	 * @return
	 */
	public List<CsCoin> getCsCoinList(Map params,Integer size);
	
	/**
	 * 获取红包统计
	 * @return
	 */
	public List getCsCoinStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取红包总数
	 * @return
	 */	
	public Long getCsCoinCount(Map params);
	
	
	/**
	 * 获取红包自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCoinEval(String eval,Map params);

	/**
	 * 获取红包分页
	 * @return
	 */	
	public Page getCsCoinPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取红包
	 * @param params
	 * @return
	 */
	public CsCoin getCsCoin(Map params);

	/**
	 * 根据ID取红包
	 * @param id
	 * @return
	 */
	public CsCoin getCsCoinById(Long id);


	/**
	 * 保存红包
	 * @param csCoin
	 */
	public CsCoin saveCsCoin(CsCoin csCoin);

	/**
	 * 更新红包
	 * @param csCoin
	 */
	public void updateCsCoin(CsCoin csCoin);
	/**
	 * 更新红包非空字段
	 * @param csCoin
	 */
	public void updateCsCoin$NotNull(CsCoin csCoin);

	/**
	 * 根据ID删除一个红包
	 * @param id
	 */
	public void deleteCsCoinById(Long id);
	/**
	 * 根据ID逻辑删除一个红包
	 * @param id
	 */
	public void removeCsCoinById(Long id);
	/**
	 * 根据条件更新红包
	 * @param values
	 * @param params
	 */
	public void updateCsCoinByConfirm(Map values, Map params);
	/**
	 * 根据条件删除红包
	 * @param params
	 */
	public void deleteCsCoinByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}