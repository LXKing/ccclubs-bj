package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSmsMt;

/**
 * 短信发送的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSmsMtDao
{
	/**
	 * 获取所有短信发送
	 * @return
	 */
	public List<CsSmsMt> getCsSmsMtList(Map params,Integer size);
	
	/**
	 * 获取短信发送统计
	 * @return
	 */
	public List getCsSmsMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取短信发送总数
	 * @return
	 */	
	public Long getCsSmsMtCount(Map params);
	
	/**
	 * 获取短信发送自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSmsMtEval(String eval,Map params);
	
	/**
	 * 获取短信发送分页
	 * @return
	 */	
	public Page getCsSmsMtPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取短信发送
	 * @param params
	 * @return
	 */
	public CsSmsMt getCsSmsMt(Map params);	

	/**
	 * 根据ID取短信发送
	 * @param id
	 * @return
	 */
	public CsSmsMt getCsSmsMtById(Long id);


	
	

	/**
	 * 保存短信发送
	 * @param csSmsMt
	 */
	public CsSmsMt saveCsSmsMt(CsSmsMt csSmsMt);

	/**
	 * 更新短信发送
	 * @param csSmsMt
	 */
	public void updateCsSmsMt(CsSmsMt csSmsMt);
	/**
	 * 更新短信发送非空字段
	 * @param csSmsMt
	 */
	public void updateCsSmsMt$NotNull(CsSmsMt csSmsMt);

	/**
	 * 根据ID删除一个短信发送
	 * @param id
	 */
	public void deleteCsSmsMtById(Long id);
	/**
	 * 根据ID逻辑删除一个短信发送
	 * @param id
	 */
	public void removeCsSmsMtById(Long id);
	/**
	 * 根据条件更新短信发送
	 * @param values
	 * @param params
	 */
	public void updateCsSmsMtByConfirm(Map values, Map params);
	/**
	 * 根据条件删除短信发送
	 * @param params
	 */
	public void deleteCsSmsMtByConfirm(Map params);

}