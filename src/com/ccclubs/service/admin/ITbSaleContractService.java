package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.TbSaleContract;

/**
 * 签约合同的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbSaleContractService
{
	/**
	 * 获取所有签约合同
	 * @return
	 */
	public List<TbSaleContract> getTbSaleContractList(Map params,Integer size);
	
	/**
	 * 获取签约合同统计
	 * @return
	 */
	public List getTbSaleContractStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取签约合同总数
	 * @return
	 */	
	public Long getTbSaleContractCount(Map params);
	
	
	/**
	 * 获取签约合同自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleContractEval(String eval,Map params);

	/**
	 * 获取签约合同分页
	 * @return
	 */	
	public Page getTbSaleContractPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取签约合同
	 * @param params
	 * @return
	 */
	public TbSaleContract getTbSaleContract(Map params);

	/**
	 * 根据ID取签约合同
	 * @param id
	 * @return
	 */
	public TbSaleContract getTbSaleContractById(Long id);


	/**
	 * 保存签约合同
	 * @param tbSaleContract
	 */
	public TbSaleContract saveTbSaleContract(TbSaleContract tbSaleContract);

	/**
	 * 更新签约合同
	 * @param tbSaleContract
	 */
	public void updateTbSaleContract(TbSaleContract tbSaleContract);
	/**
	 * 更新签约合同非空字段
	 * @param tbSaleContract
	 */
	public void updateTbSaleContract$NotNull(TbSaleContract tbSaleContract);

	/**
	 * 根据ID删除一个签约合同
	 * @param id
	 */
	public void deleteTbSaleContractById(Long id);
	/**
	 * 根据ID逻辑删除一个签约合同
	 * @param id
	 */
	public void removeTbSaleContractById(Long id);
	/**
	 * 根据条件更新签约合同
	 * @param values
	 * @param params
	 */
	public void updateTbSaleContractByConfirm(Map values, Map params);
	/**
	 * 根据条件删除签约合同
	 * @param params
	 */
	public void deleteTbSaleContractByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}