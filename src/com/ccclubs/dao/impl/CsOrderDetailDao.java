package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOrderDetailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderDetail;
import com.lazy3q.web.helper.$;

/**
 * 订单明细的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOrderDetailDao extends SqlMapClientDaoSupport implements ICsOrderDetailDao
{
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/


	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private Map custom(Map params){
		/************LAZY3Q_DAO_CUSTOM************/
		/************LAZY3Q_DAO_CUSTOM************/

		return params;
	}
	
	/**
	 * 自定义加工处理params,可以添加表别名、联表查询等操作
	 * 可在此动态设置数据源，以达到分库分表的目的
	 * @param params
	 * @return
	 */
	private CsOrderDetail custom(CsOrderDetail params){
		/************LAZY3Q_CUSTOM_MODEL************/
		/************LAZY3Q_CUSTOM_MODEL************/

		return params;
	}
	/************LAZY3Q_DEFINE_FUNCTION************/
	/************LAZY3Q_DEFINE_FUNCTION************/

	
	/**
	 * 为某些需要加like的key加%
	 * @param params
	 * @return
	 */
	private Map likeKey(Map params){
		//为模糊匹配的varchar类型字段加上%号
		String[] likes={
			"csodRemark"			
		};
		for(String key:likes){
			String value=(String) params.get(key);
			if(value==null || value.trim().equals(""))
				params.remove(key);
			else
				params.put(key, "%"+value.replaceAll("%", "")+"%");
		}
		return params;
	}
	

	/**
	 * 获取所有订单明细
	 * @return
	 */	
	public List<CsOrderDetail> getCsOrderDetailList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOrderDetail> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOrderDetailList", params);
		
		return list;
	}
	
	
	/**
	 * 获取订单明细统计
	 * @return
	 */
	public List getCsOrderDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		params = custom(params);
		params=likeKey(params);
		
		String groupSql="";
		for(Map.Entry<String, Object> item : groups.entrySet())
			if(!$.empty(item.getKey()))
				groupSql+=(groupSql.equals("")?"":",")+item.getValue().toString().replace("{key}",item.getKey());
		if(groups.get("definex")!=null)
			groupSql+=(groupSql.equals("")?"":",")+groups.get("definex");
		if(!groupSql.equals(""))
			groupSql = "group by "+groupSql;
		if(params.get("desc")!=null)
			groupSql+=" order by {key} desc ".replace("{key}",params.get("desc").toString());
		else if(params.get("asc")!=null)
			groupSql+=" order by {key} asc ".replace("{key}",params.get("asc").toString());
		if(!groupSql.equals(""))
			groupSql+=" limit 512 ";
			
		String selectSql="count(*) as 'count'";
		for(Map.Entry<String, Object> item : sums.entrySet())
			if(!$.empty(item.getKey()))
				selectSql+=(selectSql.equals("")?"":",")+item.getValue().toString().replace("{key}",item.getKey())+(item.getValue().toString().indexOf(" as ")==-1 ? (" as "+item.getKey()) : "");
		for(Map.Entry<String, Object> item : groups.entrySet())
			if(!$.empty(item.getKey()))
				selectSql+=(selectSql.equals("")?"":",")+item.getKey()+" as '"+item.getKey()+"'";
		if(sums.get("definex")!=null)
			selectSql+=(selectSql.equals("")?"":",")+sums.get("definex");
		
		params.put("select",selectSql);
		params.put("group",groupSql);
		return this.getSqlMapClientTemplate().queryForList("getCsOrderDetailStats", params);
	}

	/**
	 * 获取订单明细分页
	 * @return
	 */
	public Page getCsOrderDetailPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOrderDetailCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOrderDetailPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取订单明细数量
	 * @return
	 */	
	public Long getCsOrderDetailCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderDetailEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取订单明细自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOrderDetailEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderDetailEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public CsOrderDetail getCsOrderDetail(Map params)
	{		
		params = custom(params);
			
		List<CsOrderDetail> list=this.getCsOrderDetailList(params,1);
		CsOrderDetail csOrderDetail = list.isEmpty()?null:list.get(0);
		
		return csOrderDetail;
	}
		
	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public CsOrderDetail getCsOrderDetailById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOrderDetail csOrderDetail = null;
		if(csOrderDetail!=null)
			return csOrderDetail;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOrderDetail = (CsOrderDetail) this.getSqlMapClientTemplate().queryForObject("getCsOrderDetailById",params);
		return csOrderDetail;
	}
	
	/**
	 * 根据ID取订单明细(不缓存)
	 * @param id
	 * @return
	 */
	public CsOrderDetail GetCsOrderDetailById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOrderDetail) this.getSqlMapClientTemplate().queryForObject("getCsOrderDetailById",params);
	}
	

	/**
	 * 保存订单明细
	 * @param csOrderDetail
	 */
	public CsOrderDetail saveCsOrderDetail(CsOrderDetail csOrderDetail)
	{
		csOrderDetail = custom(csOrderDetail);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOrderDetail", csOrderDetail));
		csOrderDetail = this.GetCsOrderDetailById(id);
		return Trigger.on(CsOrderDetail.class,Trigger.SAVE,csOrderDetail);	
	}

	

	/**
	 * 更新订单明细
	 * @param csOrderDetail
	 */
	public void updateCsOrderDetail(CsOrderDetail csOrderDetail)
	{
		csOrderDetail = custom(csOrderDetail);
		this.getSqlMapClientTemplate().update("updateCsOrderDetail", csOrderDetail);
		csOrderDetail = this.GetCsOrderDetailById(csOrderDetail.getCsodId());
		Trigger.on(CsOrderDetail.class,Trigger.UPDATE,csOrderDetail);
	}
	/**
	 * 更新订单明细非空字段
	 * @param csOrderDetail
	 */
	public void updateCsOrderDetail$NotNull(CsOrderDetail csOrderDetail)
	{
		csOrderDetail = custom(csOrderDetail);
		this.getSqlMapClientTemplate().update("updateCsOrderDetail$NotNull", csOrderDetail);
		csOrderDetail = this.GetCsOrderDetailById(csOrderDetail.getCsodId());
		Trigger.on(CsOrderDetail.class,Trigger.UPDATE,csOrderDetail);
	}

	/**
	 * 根据ID删除一个订单明细
	 * @param id
	 */
	public void deleteCsOrderDetailById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOrderDetail.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOrderDetailById", params);
	}
	/**
	 * 根据条件更新订单明细
	 * @param values
	 * @param params
	 */
	public void updateCsOrderDetailByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOrderDetailBy", allParams);
		Trigger.on(CsOrderDetail.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除订单明细
	 * @param params
	 */
	public void deleteCsOrderDetailByConfirm(Map params){
		Trigger.on(CsOrderDetail.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOrderDetailBy", params);
	}
	

}