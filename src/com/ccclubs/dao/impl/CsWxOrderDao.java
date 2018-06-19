package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsWxOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxOrder;
import com.lazy3q.web.helper.$;

/**
 * 微信指令的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsWxOrderDao extends SqlMapClientDaoSupport implements ICsWxOrderDao
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
	private CsWxOrder custom(CsWxOrder params){
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
			"cswoContent"			
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
	 * 获取所有微信指令
	 * @return
	 */	
	public List<CsWxOrder> getCsWxOrderList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsWxOrder> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cswoTime","cswoTimeStart","cswoTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsWxOrder.M.class, md5);
			List caches = MemCache.getObject(CsWxOrder.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsWxOrder cacheObject = MemCache.getObject(CsWxOrder.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsWxOrder.M.class, md5);
							MemCache.deleteValue(CsWxOrder.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsWxOrder.M.class, md5);
					MemCache.deleteValue(CsWxOrder.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsWxOrderList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsWxOrder csWxOrder:list){
				caches.add(csWxOrder.getCswoId());
				MemCache.setObject(CsWxOrder.class,csWxOrder.getCswoId(), csWxOrder);
			}
			MemCache.setObject(CsWxOrder.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsWxOrder.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取微信指令统计
	 * @return
	 */
	public List getCsWxOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsWxOrderStats", params);
	}

	/**
	 * 获取微信指令分页
	 * @return
	 */
	public Page getCsWxOrderPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsWxOrderCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsWxOrderPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取微信指令数量
	 * @return
	 */	
	public Long getCsWxOrderCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxOrderEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取微信指令自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsWxOrderEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxOrderEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取微信指令
	 * @param params
	 * @return
	 */
	public CsWxOrder getCsWxOrder(Map params)
	{		
		params = custom(params);
			
		List<CsWxOrder> list=this.getCsWxOrderList(params,1);
		CsWxOrder csWxOrder = list.isEmpty()?null:list.get(0);
		
		return csWxOrder;
	}
		
	/**
	 * 根据ID取微信指令
	 * @param id
	 * @return
	 */
	public CsWxOrder getCsWxOrderById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsWxOrder csWxOrder = MemCache.getObject(CsWxOrder.class, id);
		if(csWxOrder!=null)
			return csWxOrder;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csWxOrder = (CsWxOrder) this.getSqlMapClientTemplate().queryForObject("getCsWxOrderById",params);
		if(csWxOrder!=null)
			MemCache.setValue(CsWxOrder.class,id, csWxOrder.getKeyValue());
		if(csWxOrder!=null)
			MemCache.setObject(CsWxOrder.class,id, csWxOrder);
		return csWxOrder;
	}
	
	/**
	 * 根据ID取微信指令(不缓存)
	 * @param id
	 * @return
	 */
	public CsWxOrder GetCsWxOrderById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsWxOrder) this.getSqlMapClientTemplate().queryForObject("getCsWxOrderById",params);
	}
	

	/**
	 * 保存微信指令
	 * @param csWxOrder
	 */
	public CsWxOrder saveCsWxOrder(CsWxOrder csWxOrder)
	{
		csWxOrder = custom(csWxOrder);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsWxOrder", csWxOrder));
		csWxOrder = this.GetCsWxOrderById(id);
		MemCache.setValue(CsWxOrder.class,id, csWxOrder.getKeyValue());
		MemCache.setObject(CsWxOrder.class,id,csWxOrder);
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
		return Trigger.on(CsWxOrder.class,Trigger.SAVE,csWxOrder);	
	}

	

	/**
	 * 更新微信指令
	 * @param csWxOrder
	 */
	public void updateCsWxOrder(CsWxOrder csWxOrder)
	{
		csWxOrder = custom(csWxOrder);
		this.getSqlMapClientTemplate().update("updateCsWxOrder", csWxOrder);
		csWxOrder = this.GetCsWxOrderById(csWxOrder.getCswoId());
		if(csWxOrder!=null){
			MemCache.setValue(CsWxOrder.class,csWxOrder.getCswoId(), csWxOrder.getKeyValue());
			MemCache.setObject(CsWxOrder.class,csWxOrder.getCswoId(),csWxOrder);
		}
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
		Trigger.on(CsWxOrder.class,Trigger.UPDATE,csWxOrder);
	}
	/**
	 * 更新微信指令非空字段
	 * @param csWxOrder
	 */
	public void updateCsWxOrder$NotNull(CsWxOrder csWxOrder)
	{
		csWxOrder = custom(csWxOrder);
		this.getSqlMapClientTemplate().update("updateCsWxOrder$NotNull", csWxOrder);
		csWxOrder = this.GetCsWxOrderById(csWxOrder.getCswoId());
		if(csWxOrder!=null){
			MemCache.setValue(CsWxOrder.class,csWxOrder.getCswoId(), csWxOrder.getKeyValue());
			MemCache.setObject(CsWxOrder.class,csWxOrder.getCswoId(),csWxOrder);
		}
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
		Trigger.on(CsWxOrder.class,Trigger.UPDATE,csWxOrder);
	}

	/**
	 * 根据ID删除一个微信指令
	 * @param id
	 */
	public void deleteCsWxOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsWxOrderById", params);
		MemCache.deleteValue(CsWxOrder.class, id);
		MemCache.deleteObject(CsWxOrder.class, id);
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
	}
	/**
	 * 根据ID逻辑删除微信指令 
	 * @param id
	 * @return
	 */
	public void removeCsWxOrderById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxOrder.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsWxOrderById", params);
		MemCache.deleteValue(CsWxOrder.class, id);
		MemCache.deleteObject(CsWxOrder.class, id);
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
	}
	/**
	 * 根据条件更新微信指令
	 * @param values
	 * @param params
	 */
	public void updateCsWxOrderByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxOrderCount(params)>255){
			MemCache.clearValue(CsWxOrder.class);
			MemCache.clearObject(CsWxOrder.class);
		}else{
			params.put("eval","cswo_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxOrder.class, map.get("eval"));
				MemCache.deleteObject(CsWxOrder.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsWxOrderBy", allParams);
		Trigger.on(CsWxOrder.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除微信指令
	 * @param params
	 */
	public void deleteCsWxOrderByConfirm(Map params){
		Trigger.on(CsWxOrder.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxOrderCount(params)>255){
			MemCache.clearValue(CsWxOrder.class);
			MemCache.clearObject(CsWxOrder.class);
		}else{
			params.put("eval","cswo_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxOrderEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxOrder.class, map.get("eval"));
				MemCache.deleteObject(CsWxOrder.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsWxOrder.M.class);
		MemCache.clearValue(CsWxOrder.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsWxOrderBy", params);
	}
	

}