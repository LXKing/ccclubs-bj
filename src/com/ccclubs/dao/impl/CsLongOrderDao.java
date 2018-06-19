package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLongOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrder;
import com.lazy3q.web.helper.$;

/**
 * 长租订单的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLongOrderDao extends SqlMapClientDaoSupport implements ICsLongOrderDao
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
	private CsLongOrder custom(CsLongOrder params){
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
			"csloPackProfile"			
			,"csloRemark"			
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
	 * 获取所有长租订单
	 * @return
	 */	
	public List<CsLongOrder> getCsLongOrderList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLongOrder> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLongOrderList", params);
		
		return list;
	}
	
	
	/**
	 * 获取长租订单统计
	 * @return
	 */
	public List getCsLongOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLongOrderStats", params);
	}

	/**
	 * 获取长租订单分页
	 * @return
	 */
	public Page getCsLongOrderPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLongOrderCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLongOrderPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取长租订单数量
	 * @return
	 */	
	public Long getCsLongOrderCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取长租订单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取长租订单
	 * @param params
	 * @return
	 */
	public CsLongOrder getCsLongOrder(Map params)
	{		
		params = custom(params);
			
		List<CsLongOrder> list=this.getCsLongOrderList(params,1);
		CsLongOrder csLongOrder = list.isEmpty()?null:list.get(0);
		
		return csLongOrder;
	}
		
	/**
	 * 根据ID取长租订单
	 * @param id
	 * @return
	 */
	public CsLongOrder getCsLongOrderById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLongOrder csLongOrder = null;
		if(csLongOrder!=null)
			return csLongOrder;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLongOrder = (CsLongOrder) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderById",params);
		if(csLongOrder!=null)
			MemCache.setValue(CsLongOrder.class,id, csLongOrder.getKeyValue());
		return csLongOrder;
	}
	
	/**
	 * 根据ID取长租订单(不缓存)
	 * @param id
	 * @return
	 */
	public CsLongOrder GetCsLongOrderById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLongOrder) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderById",params);
	}
	

	/**
	 * 保存长租订单
	 * @param csLongOrder
	 */
	public CsLongOrder saveCsLongOrder(CsLongOrder csLongOrder)
	{
		csLongOrder = custom(csLongOrder);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLongOrder", csLongOrder));
		csLongOrder = this.GetCsLongOrderById(id);
		MemCache.setValue(CsLongOrder.class,id, csLongOrder.getKeyValue());
		return Trigger.on(CsLongOrder.class,Trigger.SAVE,csLongOrder);	
	}

	

	/**
	 * 更新长租订单
	 * @param csLongOrder
	 */
	public void updateCsLongOrder(CsLongOrder csLongOrder)
	{
		csLongOrder = custom(csLongOrder);
		this.getSqlMapClientTemplate().update("updateCsLongOrder", csLongOrder);
		csLongOrder = this.GetCsLongOrderById(csLongOrder.getCsloId());
		if(csLongOrder!=null){
			MemCache.setValue(CsLongOrder.class,csLongOrder.getCsloId(), csLongOrder.getKeyValue());
		}
		Trigger.on(CsLongOrder.class,Trigger.UPDATE,csLongOrder);
	}
	/**
	 * 更新长租订单非空字段
	 * @param csLongOrder
	 */
	public void updateCsLongOrder$NotNull(CsLongOrder csLongOrder)
	{
		csLongOrder = custom(csLongOrder);
		this.getSqlMapClientTemplate().update("updateCsLongOrder$NotNull", csLongOrder);
		csLongOrder = this.GetCsLongOrderById(csLongOrder.getCsloId());
		if(csLongOrder!=null){
			MemCache.setValue(CsLongOrder.class,csLongOrder.getCsloId(), csLongOrder.getKeyValue());
		}
		Trigger.on(CsLongOrder.class,Trigger.UPDATE,csLongOrder);
	}

	/**
	 * 根据ID删除一个长租订单
	 * @param id
	 */
	public void deleteCsLongOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLongOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLongOrderById", params);
		MemCache.deleteValue(CsLongOrder.class, id);
	}
	/**
	 * 根据ID逻辑删除长租订单 
	 * @param id
	 * @return
	 */
	public void removeCsLongOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLongOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsLongOrderById", params);
		MemCache.deleteValue(CsLongOrder.class, id);
	}
	/**
	 * 根据条件更新长租订单
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLongOrderCount(params)>255){
			MemCache.clearValue(CsLongOrder.class);
		}else{
			params.put("eval","cslo_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLongOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLongOrder.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLongOrderBy", allParams);
		Trigger.on(CsLongOrder.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除长租订单
	 * @param params
	 */
	public void deleteCsLongOrderByConfirm(Map params){
		Trigger.on(CsLongOrder.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLongOrderCount(params)>255){
			MemCache.clearValue(CsLongOrder.class);
		}else{
			params.put("eval","cslo_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLongOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLongOrder.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLongOrderBy", params);
	}
	

}