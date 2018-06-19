package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitOrder;
import com.lazy3q.web.helper.$;

/**
 * 企业订单的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUnitOrderDao extends SqlMapClientDaoSupport implements ICsUnitOrderDao
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
	private CsUnitOrder custom(CsUnitOrder params){
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
			"csuoNumber"			
			,"csuoProfile"			
			,"csuoRemark"			
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
	 * 获取所有企业订单
	 * @return
	 */	
	public List<CsUnitOrder> getCsUnitOrderList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitOrder> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitOrderList", params);
		
		return list;
	}
	
	
	/**
	 * 获取企业订单统计
	 * @return
	 */
	public List getCsUnitOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitOrderStats", params);
	}

	/**
	 * 获取企业订单分页
	 * @return
	 */
	public Page getCsUnitOrderPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitOrderCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitOrderPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取企业订单数量
	 * @return
	 */	
	public Long getCsUnitOrderCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitOrderEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取企业订单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitOrderEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitOrderEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取企业订单
	 * @param params
	 * @return
	 */
	public CsUnitOrder getCsUnitOrder(Map params)
	{		
		params = custom(params);
			
		List<CsUnitOrder> list=this.getCsUnitOrderList(params,1);
		CsUnitOrder csUnitOrder = list.isEmpty()?null:list.get(0);
		
		return csUnitOrder;
	}
		
	/**
	 * 根据ID取企业订单
	 * @param id
	 * @return
	 */
	public CsUnitOrder getCsUnitOrderById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitOrder csUnitOrder = null;
		if(csUnitOrder!=null)
			return csUnitOrder;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitOrder = (CsUnitOrder) this.getSqlMapClientTemplate().queryForObject("getCsUnitOrderById",params);
		if(csUnitOrder!=null)
			MemCache.setValue(CsUnitOrder.class,id, csUnitOrder.getKeyValue());
		return csUnitOrder;
	}
	
	/**
	 * 根据ID取企业订单(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitOrder GetCsUnitOrderById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitOrder) this.getSqlMapClientTemplate().queryForObject("getCsUnitOrderById",params);
	}
	

	/**
	 * 保存企业订单
	 * @param csUnitOrder
	 */
	public CsUnitOrder saveCsUnitOrder(CsUnitOrder csUnitOrder)
	{
		csUnitOrder = custom(csUnitOrder);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitOrder", csUnitOrder));
		csUnitOrder = this.GetCsUnitOrderById(id);
		MemCache.setValue(CsUnitOrder.class,id, csUnitOrder.getKeyValue());
		return Trigger.on(CsUnitOrder.class,Trigger.SAVE,csUnitOrder);	
	}

	

	/**
	 * 更新企业订单
	 * @param csUnitOrder
	 */
	public void updateCsUnitOrder(CsUnitOrder csUnitOrder)
	{
		csUnitOrder = custom(csUnitOrder);
		this.getSqlMapClientTemplate().update("updateCsUnitOrder", csUnitOrder);
		csUnitOrder = this.GetCsUnitOrderById(csUnitOrder.getCsuoId());
		if(csUnitOrder!=null){
			MemCache.setValue(CsUnitOrder.class,csUnitOrder.getCsuoId(), csUnitOrder.getKeyValue());
		}
		Trigger.on(CsUnitOrder.class,Trigger.UPDATE,csUnitOrder);
	}
	/**
	 * 更新企业订单非空字段
	 * @param csUnitOrder
	 */
	public void updateCsUnitOrder$NotNull(CsUnitOrder csUnitOrder)
	{
		csUnitOrder = custom(csUnitOrder);
		this.getSqlMapClientTemplate().update("updateCsUnitOrder$NotNull", csUnitOrder);
		csUnitOrder = this.GetCsUnitOrderById(csUnitOrder.getCsuoId());
		if(csUnitOrder!=null){
			MemCache.setValue(CsUnitOrder.class,csUnitOrder.getCsuoId(), csUnitOrder.getKeyValue());
		}
		Trigger.on(CsUnitOrder.class,Trigger.UPDATE,csUnitOrder);
	}

	/**
	 * 根据ID删除一个企业订单
	 * @param id
	 */
	public void deleteCsUnitOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitOrderById", params);
		MemCache.deleteValue(CsUnitOrder.class, id);
	}
	/**
	 * 根据ID逻辑删除企业订单 
	 * @param id
	 * @return
	 */
	public void removeCsUnitOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitOrderById", params);
		MemCache.deleteValue(CsUnitOrder.class, id);
	}
	/**
	 * 根据条件更新企业订单
	 * @param values
	 * @param params
	 */
	public void updateCsUnitOrderByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitOrderCount(params)>255){
			MemCache.clearValue(CsUnitOrder.class);
		}else{
			params.put("eval","csuo_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitOrder.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitOrderBy", allParams);
		Trigger.on(CsUnitOrder.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除企业订单
	 * @param params
	 */
	public void deleteCsUnitOrderByConfirm(Map params){
		Trigger.on(CsUnitOrder.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitOrderCount(params)>255){
			MemCache.clearValue(CsUnitOrder.class);
		}else{
			params.put("eval","csuo_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitOrder.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitOrderBy", params);
	}
	

}