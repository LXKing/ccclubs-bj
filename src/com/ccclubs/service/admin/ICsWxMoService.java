package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsWxMo;

/**
 * 微信上行的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsWxMoService
{
	/**
	 * 获取所有微信上行
	 * @return
	 */
	public List<CsWxMo> getCsWxMoList(Map params,Integer size);
	
	/**
	 * 获取微信上行统计
	 * @return
	 */
	public List getCsWxMoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取微信上行总数
	 * @return
	 */	
	public Long getCsWxMoCount(Map params);
	
	
	/**
	 * 获取微信上行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxMoEval(String eval,Map params);

	/**
	 * 获取微信上行分页
	 * @return
	 */	
	public Page getCsWxMoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取微信上行
	 * @param params
	 * @return
	 */
	public CsWxMo getCsWxMo(Map params);

	/**
	 * 根据ID取微信上行
	 * @param id
	 * @return
	 */
	public CsWxMo getCsWxMoById(Long id);


	/**
	 * 保存微信上行
	 * @param csWxMo
	 */
	public CsWxMo saveCsWxMo(CsWxMo csWxMo);

	/**
	 * 更新微信上行
	 * @param csWxMo
	 */
	public void updateCsWxMo(CsWxMo csWxMo);
	/**
	 * 更新微信上行非空字段
	 * @param csWxMo
	 */
	public void updateCsWxMo$NotNull(CsWxMo csWxMo);

	/**
	 * 根据ID删除一个微信上行
	 * @param id
	 */
	public void deleteCsWxMoById(Long id);
	/**
	 * 根据ID逻辑删除一个微信上行
	 * @param id
	 */
	public void removeCsWxMoById(Long id);
	/**
	 * 根据条件更新微信上行
	 * @param values
	 * @param params
	 */
	public void updateCsWxMoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除微信上行
	 * @param params
	 */
	public void deleteCsWxMoByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}