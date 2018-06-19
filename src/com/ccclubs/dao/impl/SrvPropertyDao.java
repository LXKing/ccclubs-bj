package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvPropertyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvProperty;
import com.lazy3q.web.helper.$;

/**
 * 系统属性的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvPropertyDao extends SqlMapClientDaoSupport implements ISrvPropertyDao
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
	private SrvProperty custom(SrvProperty params){
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
			"spText"			
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
	 * 获取所有系统属性
	 * @return
	 */	
	public List<SrvProperty> getSrvPropertyList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvProperty> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(SrvProperty.M.class, md5);
			List caches = MemCache.getObject(SrvProperty.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						SrvProperty cacheObject = MemCache.getObject(SrvProperty.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(SrvProperty.M.class, md5);
							MemCache.deleteValue(SrvProperty.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(SrvProperty.M.class, md5);
					MemCache.deleteValue(SrvProperty.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvPropertyList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(SrvProperty srvProperty:list){
				caches.add(srvProperty.getSpId());
				MemCache.setObject(SrvProperty.class,srvProperty.getSpId(), srvProperty);
			}
			MemCache.setObject(SrvProperty.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(SrvProperty.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取系统属性统计
	 * @return
	 */
	public List getSrvPropertyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvPropertyStats", params);
	}

	/**
	 * 获取系统属性分页
	 * @return
	 */
	public Page getSrvPropertyPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvPropertyCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvPropertyPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统属性数量
	 * @return
	 */	
	public Long getSrvPropertyCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvPropertyEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统属性自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvPropertyEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvPropertyEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统属性
	 * @param params
	 * @return
	 */
	public SrvProperty getSrvProperty(Map params)
	{		
		params = custom(params);
			
		List<SrvProperty> list=this.getSrvPropertyList(params,1);
		SrvProperty srvProperty = list.isEmpty()?null:list.get(0);
		
		return srvProperty;
	}
		
	/**
	 * 根据ID取系统属性
	 * @param id
	 * @return
	 */
	public SrvProperty getSrvPropertyById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvProperty srvProperty = MemCache.getObject(SrvProperty.class, id);
		if(srvProperty!=null)
			return srvProperty;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvProperty = (SrvProperty) this.getSqlMapClientTemplate().queryForObject("getSrvPropertyById",params);
		if(srvProperty!=null)
			MemCache.setValue(SrvProperty.class,id, srvProperty.getKeyValue());
		if(srvProperty!=null)
			MemCache.setObject(SrvProperty.class,id, srvProperty);
		return srvProperty;
	}
	
	/**
	 * 根据ID取系统属性(不缓存)
	 * @param id
	 * @return
	 */
	public SrvProperty GetSrvPropertyById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvProperty) this.getSqlMapClientTemplate().queryForObject("getSrvPropertyById",params);
	}
	

	/**
	 * 保存系统属性
	 * @param srvProperty
	 */
	public SrvProperty saveSrvProperty(SrvProperty srvProperty)
	{
		srvProperty = custom(srvProperty);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvProperty", srvProperty));
		srvProperty = this.GetSrvPropertyById(id);
		MemCache.setValue(SrvProperty.class,id, srvProperty.getKeyValue());
		MemCache.setObject(SrvProperty.class,id,srvProperty);
		MemCache.clearObject(SrvProperty.M.class);
		MemCache.clearValue(SrvProperty.M.class);
		return Trigger.on(SrvProperty.class,Trigger.SAVE,srvProperty);	
	}

	

	/**
	 * 更新系统属性
	 * @param srvProperty
	 */
	public void updateSrvProperty(SrvProperty srvProperty)
	{
		srvProperty = custom(srvProperty);
		this.getSqlMapClientTemplate().update("updateSrvProperty", srvProperty);
		srvProperty = this.GetSrvPropertyById(srvProperty.getSpId());
		if(srvProperty!=null){
			MemCache.setValue(SrvProperty.class,srvProperty.getSpId(), srvProperty.getKeyValue());
			MemCache.setObject(SrvProperty.class,srvProperty.getSpId(),srvProperty);
		}
		MemCache.clearObject(SrvProperty.M.class);
		MemCache.clearValue(SrvProperty.M.class);
		Trigger.on(SrvProperty.class,Trigger.UPDATE,srvProperty);
	}
	/**
	 * 更新系统属性非空字段
	 * @param srvProperty
	 */
	public void updateSrvProperty$NotNull(SrvProperty srvProperty)
	{
		srvProperty = custom(srvProperty);
		this.getSqlMapClientTemplate().update("updateSrvProperty$NotNull", srvProperty);
		srvProperty = this.GetSrvPropertyById(srvProperty.getSpId());
		if(srvProperty!=null){
			MemCache.setValue(SrvProperty.class,srvProperty.getSpId(), srvProperty.getKeyValue());
			MemCache.setObject(SrvProperty.class,srvProperty.getSpId(),srvProperty);
		}
		MemCache.clearObject(SrvProperty.M.class);
		MemCache.clearValue(SrvProperty.M.class);
		Trigger.on(SrvProperty.class,Trigger.UPDATE,srvProperty);
	}

	/**
	 * 根据ID删除一个系统属性
	 * @param id
	 */
	public void deleteSrvPropertyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvProperty.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvPropertyById", params);
		MemCache.deleteValue(SrvProperty.class, id);
		MemCache.deleteObject(SrvProperty.class, id);
		MemCache.clearObject(SrvProperty.M.class);
		MemCache.clearValue(SrvProperty.M.class);
	}
	/**
	 * 根据条件更新系统属性
	 * @param values
	 * @param params
	 */
	public void updateSrvPropertyByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvPropertyCount(params)>255){
			MemCache.clearValue(SrvProperty.class);
			MemCache.clearObject(SrvProperty.class);
		}else{
			params.put("eval","sp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvPropertyEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvProperty.class, map.get("eval"));
				MemCache.deleteObject(SrvProperty.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvProperty.M.class);
		MemCache.clearValue(SrvProperty.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvPropertyBy", allParams);
		Trigger.on(SrvProperty.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统属性
	 * @param params
	 */
	public void deleteSrvPropertyByConfirm(Map params){
		Trigger.on(SrvProperty.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvPropertyCount(params)>255){
			MemCache.clearValue(SrvProperty.class);
			MemCache.clearObject(SrvProperty.class);
		}else{
			params.put("eval","sp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvPropertyEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvProperty.class, map.get("eval"));
				MemCache.deleteObject(SrvProperty.class, map.get("eval"));
			}
		}
		MemCache.clearObject(SrvProperty.M.class);
		MemCache.clearValue(SrvProperty.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvPropertyBy", params);
	}
	

}