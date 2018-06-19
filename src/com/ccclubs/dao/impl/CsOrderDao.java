package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrder;
import com.lazy3q.web.helper.$;

/**
 * 系统订单的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOrderDao extends SqlMapClientDaoSupport implements ICsOrderDao
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
	private CsOrder custom(CsOrder params){
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
			"csoRemark"			
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
	 * 获取所有系统订单
	 * @return
	 */	
	public List<CsOrder> getCsOrderList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOrder> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOrderList", params);
		
		return list;
	}
	
	
	/**
	 * 获取系统订单统计
	 * @return
	 */
	public List getCsOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsOrderStats", params);
	}

	/**
	 * 获取系统订单分页
	 * @return
	 */
	public Page getCsOrderPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOrderCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOrderPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统订单数量
	 * @return
	 */	
	public Long getCsOrderCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统订单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOrderEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOrderEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统订单
	 * @param params
	 * @return
	 */
	public CsOrder getCsOrder(Map params)
	{		
		params = custom(params);
			
		List<CsOrder> list=this.getCsOrderList(params,1);
		CsOrder csOrder = list.isEmpty()?null:list.get(0);
		
		return csOrder;
	}
		
	/**
	 * 根据ID取系统订单
	 * @param id
	 * @return
	 */
	public CsOrder getCsOrderById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOrder csOrder = null;
		if(csOrder!=null)
			return csOrder;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOrder = (CsOrder) this.getSqlMapClientTemplate().queryForObject("getCsOrderById",params);
		if(csOrder!=null)
			MemCache.setValue(CsOrder.class,id, csOrder.getKeyValue());
		return csOrder;
	}
	
	/**
	 * 根据ID取系统订单(不缓存)
	 * @param id
	 * @return
	 */
	public CsOrder GetCsOrderById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOrder) this.getSqlMapClientTemplate().queryForObject("getCsOrderById",params);
	}
	

	/**
	 * 保存系统订单
	 * @param csOrder
	 */
	public CsOrder saveCsOrder(CsOrder csOrder)
	{
		csOrder = custom(csOrder);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOrder", csOrder));
		csOrder = this.GetCsOrderById(id);
		MemCache.setValue(CsOrder.class,id, csOrder.getKeyValue());
		return Trigger.on(CsOrder.class,Trigger.SAVE,csOrder);	
	}

	

	/**
	 * 更新系统订单
	 * @param csOrder
	 */
	public void updateCsOrder(CsOrder csOrder)
	{
		csOrder = custom(csOrder);
		this.getSqlMapClientTemplate().update("updateCsOrder", csOrder);
		csOrder = this.GetCsOrderById(csOrder.getCsoId());
		if(csOrder!=null){
			MemCache.setValue(CsOrder.class,csOrder.getCsoId(), csOrder.getKeyValue());
		}
		Trigger.on(CsOrder.class,Trigger.UPDATE,csOrder);
	}
	/**
	 * 更新系统订单非空字段
	 * @param csOrder
	 */
	public void updateCsOrder$NotNull(CsOrder csOrder)
	{
		csOrder = custom(csOrder);
		this.getSqlMapClientTemplate().update("updateCsOrder$NotNull", csOrder);
		csOrder = this.GetCsOrderById(csOrder.getCsoId());
		if(csOrder!=null){
			MemCache.setValue(CsOrder.class,csOrder.getCsoId(), csOrder.getKeyValue());
		}
		Trigger.on(CsOrder.class,Trigger.UPDATE,csOrder);
	}

	/**
	 * 根据ID删除一个系统订单
	 * @param id
	 */
	public void deleteCsOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOrderById", params);
		MemCache.deleteValue(CsOrder.class, id);
	}
	/**
	 * 根据ID逻辑删除系统订单 
	 * @param id
	 * @return
	 */
	public void removeCsOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsOrderById", params);
		MemCache.deleteValue(CsOrder.class, id);
	}
	/**
	 * 根据条件更新系统订单
	 * @param values
	 * @param params
	 */
	public void updateCsOrderByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsOrderCount(params)>255){
			MemCache.clearValue(CsOrder.class);
		}else{
			params.put("eval","cso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOrder.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOrderBy", allParams);
		Trigger.on(CsOrder.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统订单
	 * @param params
	 */
	public void deleteCsOrderByConfirm(Map params){
		Trigger.on(CsOrder.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsOrderCount(params)>255){
			MemCache.clearValue(CsOrder.class);
		}else{
			params.put("eval","cso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOrder.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOrderBy", params);
	}
	

}