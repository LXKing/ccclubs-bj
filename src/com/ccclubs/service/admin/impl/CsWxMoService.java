package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsWxMoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMo;
import com.ccclubs.service.admin.ICsWxMoService;
import com.lazy3q.web.helper.$;

/**
 * 微信上行的Service实现
 * @author 飞啊飘啊
 */
public class CsWxMoService implements ICsWxMoService
{
	ICsWxMoDao csWxMoDao;
	

	/**
	 * 获取所有微信上行
	 * @return
	 */
	public List<CsWxMo> getCsWxMoList(Map params,Integer size)
	{
		return csWxMoDao.getCsWxMoList(params,size);
	}
	
	/**
	 * 获取微信上行统计
	 * @return
	 */
	public List getCsWxMoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csWxMoDao.getCsWxMoStats(params,groups,sums);
	}
	
	/**
	 * 获取微信上行总数
	 * @return
	 */
	public Long getCsWxMoCount(Map params)
	{
		return csWxMoDao.getCsWxMoCount(params);
	}
	
	/**
	 * 获取微信上行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxMoEval(String eval,Map params)
	{
		return csWxMoDao.getCsWxMoEval(eval,params);
	}

	/**
	 * 获取微信上行分页
	 * @return
	 */
	public Page getCsWxMoPage(int page,int size,Map params)
	{
		return csWxMoDao.getCsWxMoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取微信上行
	 * @param params
	 * @return
	 */
	public CsWxMo getCsWxMo(Map params)
	{
		return csWxMoDao.getCsWxMo(params);
	}

	/**
	 * 根据ID取微信上行
	 * @param id
	 * @return
	 */
	public CsWxMo getCsWxMoById(Long id)
	{
		return csWxMoDao.getCsWxMoById(id);
	}

	/**
	 * 保存微信上行
	 * @param csWxMo
	 */
	public CsWxMo saveCsWxMo(CsWxMo csWxMo)
	{
		return csWxMoDao.saveCsWxMo(csWxMo);
	}
	
	/**
	 * 更新微信上行
	 * @param csWxMo
	 */
	public void updateCsWxMo(CsWxMo csWxMo)
	{
		csWxMoDao.updateCsWxMo(csWxMo);
	}
	/**
	 * 更新微信上行非空字段
	 * @param csWxMo
	 */
	public void updateCsWxMo$NotNull(CsWxMo csWxMo)
	{
		csWxMoDao.updateCsWxMo$NotNull(csWxMo);
	}
	
	/**
	 * 根据ID删除一个微信上行
	 * @param id
	 */
	public void deleteCsWxMoById(Long id)
	{
		csWxMoDao.deleteCsWxMoById(id);
	}
	/**
	 * 根据ID逻辑删除一个微信上行
	 * @param id
	 */
	public void removeCsWxMoById(Long id)
	{
		csWxMoDao.removeCsWxMoById(id);
	}
	
	/**
	 * 根据条件更新微信上行
	 * @param values
	 * @param params
	 */
	public void updateCsWxMoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsWxMoBy时无条件");
		csWxMoDao.updateCsWxMoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除微信上行
	 * @param params
	 */
	public void deleteCsWxMoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsWxMoBy时无条件");
		csWxMoDao.deleteCsWxMoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsWxMoDao getCsWxMoDao()
	{
		return csWxMoDao;
	}

	public void setCsWxMoDao(ICsWxMoDao csWxMoDao)
	{
		this.csWxMoDao = csWxMoDao;
	}
}