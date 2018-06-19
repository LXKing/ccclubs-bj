package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsMemberShip;

/**
 * 会员关系的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMemberShipService
{
	/**
	 * 获取所有会员关系
	 * @return
	 */
	public List<CsMemberShip> getCsMemberShipList(Map params,Integer size);
	
	/**
	 * 获取会员关系统计
	 * @return
	 */
	public List getCsMemberShipStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员关系总数
	 * @return
	 */	
	public Long getCsMemberShipCount(Map params);
	
	
	/**
	 * 获取会员关系自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberShipEval(String eval,Map params);

	/**
	 * 获取会员关系分页
	 * @return
	 */	
	public Page getCsMemberShipPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员关系
	 * @param params
	 * @return
	 */
	public CsMemberShip getCsMemberShip(Map params);

	/**
	 * 根据ID取会员关系
	 * @param id
	 * @return
	 */
	public CsMemberShip getCsMemberShipById(Long id);


	/**
	 * 保存会员关系
	 * @param csMemberShip
	 */
	public CsMemberShip saveCsMemberShip(CsMemberShip csMemberShip);

	/**
	 * 更新会员关系
	 * @param csMemberShip
	 */
	public void updateCsMemberShip(CsMemberShip csMemberShip);
	/**
	 * 更新会员关系非空字段
	 * @param csMemberShip
	 */
	public void updateCsMemberShip$NotNull(CsMemberShip csMemberShip);

	/**
	 * 根据ID删除一个会员关系
	 * @param id
	 */
	public void deleteCsMemberShipById(Long id);
	/**
	 * 根据ID逻辑删除一个会员关系
	 * @param id
	 */
	public void removeCsMemberShipById(Long id);
	/**
	 * 根据条件更新会员关系
	 * @param values
	 * @param params
	 */
	public void updateCsMemberShipByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员关系
	 * @param params
	 */
	public void deleteCsMemberShipByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}