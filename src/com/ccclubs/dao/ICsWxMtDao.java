package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMt;

/**
 * 微信下行的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsWxMtDao
{
	/**
	 * 获取所有微信下行
	 * @return
	 */
	public List<CsWxMt> getCsWxMtList(Map params,Integer size);
	
	/**
	 * 获取微信下行统计
	 * @return
	 */
	public List getCsWxMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取微信下行总数
	 * @return
	 */	
	public Long getCsWxMtCount(Map params);
	
	/**
	 * 获取微信下行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxMtEval(String eval,Map params);
	
	/**
	 * 获取微信下行分页
	 * @return
	 */	
	public Page getCsWxMtPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取微信下行
	 * @param params
	 * @return
	 */
	public CsWxMt getCsWxMt(Map params);	

	/**
	 * 根据ID取微信下行
	 * @param id
	 * @return
	 */
	public CsWxMt getCsWxMtById(Long id);


	
	

	/**
	 * 保存微信下行
	 * @param csWxMt
	 */
	public CsWxMt saveCsWxMt(CsWxMt csWxMt);

	/**
	 * 更新微信下行
	 * @param csWxMt
	 */
	public void updateCsWxMt(CsWxMt csWxMt);
	/**
	 * 更新微信下行非空字段
	 * @param csWxMt
	 */
	public void updateCsWxMt$NotNull(CsWxMt csWxMt);

	/**
	 * 根据ID删除一个微信下行
	 * @param id
	 */
	public void deleteCsWxMtById(Long id);
	/**
	 * 根据ID逻辑删除一个微信下行
	 * @param id
	 */
	public void removeCsWxMtById(Long id);
	/**
	 * 根据条件更新微信下行
	 * @param values
	 * @param params
	 */
	public void updateCsWxMtByConfirm(Map values, Map params);
	/**
	 * 根据条件删除微信下行
	 * @param params
	 */
	public void deleteCsWxMtByConfirm(Map params);

}