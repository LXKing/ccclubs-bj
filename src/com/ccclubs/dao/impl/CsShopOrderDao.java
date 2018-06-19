package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsShopOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsShopOrder;
import com.lazy3q.web.helper.$;

/**
 * 商城订单的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsShopOrderDao extends SqlMapClientDaoSupport implements ICsShopOrderDao
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
	private CsShopOrder custom(CsShopOrder params){
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
			"cssoMemo"			
			,"cssoData"			
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
	 * 获取所有商城订单
	 * @return
	 */	
	public List<CsShopOrder> getCsShopOrderList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsShopOrder> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsShopOrderList", params);
		
		return list;
	}
	
	
	/**
	 * 获取商城订单统计
	 * @return
	 */
	public List getCsShopOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsShopOrderStats", params);
	}

	/**
	 * 获取商城订单分页
	 * @return
	 */
	public Page getCsShopOrderPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsShopOrderCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsShopOrderPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取商城订单数量
	 * @return
	 */	
	public Long getCsShopOrderCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsShopOrderEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取商城订单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsShopOrderEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsShopOrderEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取商城订单
	 * @param params
	 * @return
	 */
	public CsShopOrder getCsShopOrder(Map params)
	{		
		params = custom(params);
			
		List<CsShopOrder> list=this.getCsShopOrderList(params,1);
		CsShopOrder csShopOrder = list.isEmpty()?null:list.get(0);
		
		return csShopOrder;
	}
		
	/**
	 * 根据ID取商城订单
	 * @param id
	 * @return
	 */
	public CsShopOrder getCsShopOrderById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsShopOrder csShopOrder = null;
		if(csShopOrder!=null)
			return csShopOrder;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csShopOrder = (CsShopOrder) this.getSqlMapClientTemplate().queryForObject("getCsShopOrderById",params);
		if(csShopOrder!=null)
			MemCache.setValue(CsShopOrder.class,id, csShopOrder.getKeyValue());
		return csShopOrder;
	}
	
	/**
	 * 根据ID取商城订单(不缓存)
	 * @param id
	 * @return
	 */
	public CsShopOrder GetCsShopOrderById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsShopOrder) this.getSqlMapClientTemplate().queryForObject("getCsShopOrderById",params);
	}
	

	/**
	 * 保存商城订单
	 * @param csShopOrder
	 */
	public CsShopOrder saveCsShopOrder(CsShopOrder csShopOrder)
	{
		csShopOrder = custom(csShopOrder);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsShopOrder", csShopOrder));
		csShopOrder = this.GetCsShopOrderById(id);
		MemCache.setValue(CsShopOrder.class,id, csShopOrder.getKeyValue());
		return Trigger.on(CsShopOrder.class,Trigger.SAVE,csShopOrder);	
	}

	

	/**
	 * 更新商城订单
	 * @param csShopOrder
	 */
	public void updateCsShopOrder(CsShopOrder csShopOrder)
	{
		csShopOrder = custom(csShopOrder);
		this.getSqlMapClientTemplate().update("updateCsShopOrder", csShopOrder);
		csShopOrder = this.GetCsShopOrderById(csShopOrder.getCssoId());
		if(csShopOrder!=null){
			MemCache.setValue(CsShopOrder.class,csShopOrder.getCssoId(), csShopOrder.getKeyValue());
		}
		Trigger.on(CsShopOrder.class,Trigger.UPDATE,csShopOrder);
	}
	/**
	 * 更新商城订单非空字段
	 * @param csShopOrder
	 */
	public void updateCsShopOrder$NotNull(CsShopOrder csShopOrder)
	{
		csShopOrder = custom(csShopOrder);
		this.getSqlMapClientTemplate().update("updateCsShopOrder$NotNull", csShopOrder);
		csShopOrder = this.GetCsShopOrderById(csShopOrder.getCssoId());
		if(csShopOrder!=null){
			MemCache.setValue(CsShopOrder.class,csShopOrder.getCssoId(), csShopOrder.getKeyValue());
		}
		Trigger.on(CsShopOrder.class,Trigger.UPDATE,csShopOrder);
	}

	/**
	 * 根据ID删除一个商城订单
	 * @param id
	 */
	public void deleteCsShopOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsShopOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsShopOrderById", params);
		MemCache.deleteValue(CsShopOrder.class, id);
	}
	/**
	 * 根据ID逻辑删除商城订单 
	 * @param id
	 * @return
	 */
	public void removeCsShopOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsShopOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsShopOrderById", params);
		MemCache.deleteValue(CsShopOrder.class, id);
	}
	/**
	 * 根据条件更新商城订单
	 * @param values
	 * @param params
	 */
	public void updateCsShopOrderByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsShopOrderCount(params)>255){
			MemCache.clearValue(CsShopOrder.class);
		}else{
			params.put("eval","csso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsShopOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsShopOrder.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsShopOrderBy", allParams);
		Trigger.on(CsShopOrder.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除商城订单
	 * @param params
	 */
	public void deleteCsShopOrderByConfirm(Map params){
		Trigger.on(CsShopOrder.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsShopOrderCount(params)>255){
			MemCache.clearValue(CsShopOrder.class);
		}else{
			params.put("eval","csso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsShopOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsShopOrder.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsShopOrderBy", params);
	}
	

}