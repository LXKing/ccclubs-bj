package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbSaleContractDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleContract;
import com.ccclubs.service.admin.ITbSaleContractService;
import com.lazy3q.web.helper.$;

/**
 * 签约合同的Service实现
 * @author 飞啊飘啊
 */
public class TbSaleContractService implements ITbSaleContractService
{
	ITbSaleContractDao tbSaleContractDao;
	

	/**
	 * 获取所有签约合同
	 * @return
	 */
	public List<TbSaleContract> getTbSaleContractList(Map params,Integer size)
	{
		return tbSaleContractDao.getTbSaleContractList(params,size);
	}
	
	/**
	 * 获取签约合同统计
	 * @return
	 */
	public List getTbSaleContractStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbSaleContractDao.getTbSaleContractStats(params,groups,sums);
	}
	
	/**
	 * 获取签约合同总数
	 * @return
	 */
	public Long getTbSaleContractCount(Map params)
	{
		return tbSaleContractDao.getTbSaleContractCount(params);
	}
	
	/**
	 * 获取签约合同自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleContractEval(String eval,Map params)
	{
		return tbSaleContractDao.getTbSaleContractEval(eval,params);
	}

	/**
	 * 获取签约合同分页
	 * @return
	 */
	public Page getTbSaleContractPage(int page,int size,Map params)
	{
		return tbSaleContractDao.getTbSaleContractPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取签约合同
	 * @param params
	 * @return
	 */
	public TbSaleContract getTbSaleContract(Map params)
	{
		return tbSaleContractDao.getTbSaleContract(params);
	}

	/**
	 * 根据ID取签约合同
	 * @param id
	 * @return
	 */
	public TbSaleContract getTbSaleContractById(Long id)
	{
		return tbSaleContractDao.getTbSaleContractById(id);
	}

	/**
	 * 保存签约合同
	 * @param tbSaleContract
	 */
	public TbSaleContract saveTbSaleContract(TbSaleContract tbSaleContract)
	{
		return tbSaleContractDao.saveTbSaleContract(tbSaleContract);
	}
	
	/**
	 * 更新签约合同
	 * @param tbSaleContract
	 */
	public void updateTbSaleContract(TbSaleContract tbSaleContract)
	{
		tbSaleContractDao.updateTbSaleContract(tbSaleContract);
	}
	/**
	 * 更新签约合同非空字段
	 * @param tbSaleContract
	 */
	public void updateTbSaleContract$NotNull(TbSaleContract tbSaleContract)
	{
		tbSaleContractDao.updateTbSaleContract$NotNull(tbSaleContract);
	}
	
	/**
	 * 根据ID删除一个签约合同
	 * @param id
	 */
	public void deleteTbSaleContractById(Long id)
	{
		tbSaleContractDao.deleteTbSaleContractById(id);
	}
	/**
	 * 根据ID逻辑删除一个签约合同
	 * @param id
	 */
	public void removeTbSaleContractById(Long id)
	{
		tbSaleContractDao.removeTbSaleContractById(id);
	}
	
	/**
	 * 根据条件更新签约合同
	 * @param values
	 * @param params
	 */
	public void updateTbSaleContractByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbSaleContractBy时无条件");
		tbSaleContractDao.updateTbSaleContractByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除签约合同
	 * @param params
	 */
	public void deleteTbSaleContractByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbSaleContractBy时无条件");
		tbSaleContractDao.deleteTbSaleContractByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbSaleContractDao getTbSaleContractDao()
	{
		return tbSaleContractDao;
	}

	public void setTbSaleContractDao(ITbSaleContractDao tbSaleContractDao)
	{
		this.tbSaleContractDao = tbSaleContractDao;
	}
}