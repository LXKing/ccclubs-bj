package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsFeeTypeSetDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFeeTypeSet;
import com.lazy3q.web.helper.$;

/**
 * 计费策略的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsFeeTypeSetDao extends SqlMapClientDaoSupport implements ICsFeeTypeSetDao
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
	private CsFeeTypeSet custom(CsFeeTypeSet params){
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
			"csftsFeeType"			
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
	 * 获取所有计费策略
	 * @return
	 */	
	public List<CsFeeTypeSet> getCsFeeTypeSetList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsFeeTypeSet> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsFeeTypeSet.M.class, md5);
			List caches = MemCache.getObject(CsFeeTypeSet.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsFeeTypeSet cacheObject = MemCache.getObject(CsFeeTypeSet.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsFeeTypeSet.M.class, md5);
							MemCache.deleteValue(CsFeeTypeSet.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsFeeTypeSet.M.class, md5);
					MemCache.deleteValue(CsFeeTypeSet.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsFeeTypeSetList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsFeeTypeSet csFeeTypeSet:list){
				caches.add(csFeeTypeSet.getCsftsId());
				MemCache.setObject(CsFeeTypeSet.class,csFeeTypeSet.getCsftsId(), csFeeTypeSet);
			}
			MemCache.setObject(CsFeeTypeSet.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsFeeTypeSet.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取计费策略统计
	 * @return
	 */
	public List getCsFeeTypeSetStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsFeeTypeSetStats", params);
	}

	/**
	 * 获取计费策略分页
	 * @return
	 */
	public Page getCsFeeTypeSetPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsFeeTypeSetCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsFeeTypeSetPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取计费策略数量
	 * @return
	 */	
	public Long getCsFeeTypeSetCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsFeeTypeSetEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取计费策略自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsFeeTypeSetEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsFeeTypeSetEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取计费策略
	 * @param params
	 * @return
	 */
	public CsFeeTypeSet getCsFeeTypeSet(Map params)
	{		
		params = custom(params);
			
		List<CsFeeTypeSet> list=this.getCsFeeTypeSetList(params,1);
		CsFeeTypeSet csFeeTypeSet = list.isEmpty()?null:list.get(0);
		
		return csFeeTypeSet;
	}
		
	/**
	 * 根据ID取计费策略
	 * @param id
	 * @return
	 */
	public CsFeeTypeSet getCsFeeTypeSetById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsFeeTypeSet csFeeTypeSet = MemCache.getObject(CsFeeTypeSet.class, id);
		if(csFeeTypeSet!=null)
			return csFeeTypeSet;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csFeeTypeSet = (CsFeeTypeSet) this.getSqlMapClientTemplate().queryForObject("getCsFeeTypeSetById",params);
		if(csFeeTypeSet!=null)
			MemCache.setValue(CsFeeTypeSet.class,id, csFeeTypeSet.getKeyValue());
		if(csFeeTypeSet!=null)
			MemCache.setObject(CsFeeTypeSet.class,id, csFeeTypeSet);
		return csFeeTypeSet;
	}
	
	/**
	 * 根据ID取计费策略(不缓存)
	 * @param id
	 * @return
	 */
	public CsFeeTypeSet GetCsFeeTypeSetById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsFeeTypeSet) this.getSqlMapClientTemplate().queryForObject("getCsFeeTypeSetById",params);
	}
	

	/**
	 * 保存计费策略
	 * @param csFeeTypeSet
	 */
	public CsFeeTypeSet saveCsFeeTypeSet(CsFeeTypeSet csFeeTypeSet)
	{
		csFeeTypeSet = custom(csFeeTypeSet);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsFeeTypeSet", csFeeTypeSet));
		csFeeTypeSet = this.GetCsFeeTypeSetById(id);
		MemCache.setValue(CsFeeTypeSet.class,id, csFeeTypeSet.getKeyValue());
		MemCache.setObject(CsFeeTypeSet.class,id,csFeeTypeSet);
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
		return Trigger.on(CsFeeTypeSet.class,Trigger.SAVE,csFeeTypeSet);	
	}

	

	/**
	 * 更新计费策略
	 * @param csFeeTypeSet
	 */
	public void updateCsFeeTypeSet(CsFeeTypeSet csFeeTypeSet)
	{
		csFeeTypeSet = custom(csFeeTypeSet);
		this.getSqlMapClientTemplate().update("updateCsFeeTypeSet", csFeeTypeSet);
		csFeeTypeSet = this.GetCsFeeTypeSetById(csFeeTypeSet.getCsftsId());
		if(csFeeTypeSet!=null){
			MemCache.setValue(CsFeeTypeSet.class,csFeeTypeSet.getCsftsId(), csFeeTypeSet.getKeyValue());
			MemCache.setObject(CsFeeTypeSet.class,csFeeTypeSet.getCsftsId(),csFeeTypeSet);
		}
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
		Trigger.on(CsFeeTypeSet.class,Trigger.UPDATE,csFeeTypeSet);
	}
	/**
	 * 更新计费策略非空字段
	 * @param csFeeTypeSet
	 */
	public void updateCsFeeTypeSet$NotNull(CsFeeTypeSet csFeeTypeSet)
	{
		csFeeTypeSet = custom(csFeeTypeSet);
		this.getSqlMapClientTemplate().update("updateCsFeeTypeSet$NotNull", csFeeTypeSet);
		csFeeTypeSet = this.GetCsFeeTypeSetById(csFeeTypeSet.getCsftsId());
		if(csFeeTypeSet!=null){
			MemCache.setValue(CsFeeTypeSet.class,csFeeTypeSet.getCsftsId(), csFeeTypeSet.getKeyValue());
			MemCache.setObject(CsFeeTypeSet.class,csFeeTypeSet.getCsftsId(),csFeeTypeSet);
		}
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
		Trigger.on(CsFeeTypeSet.class,Trigger.UPDATE,csFeeTypeSet);
	}

	/**
	 * 根据ID删除一个计费策略
	 * @param id
	 */
	public void deleteCsFeeTypeSetById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsFeeTypeSet.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsFeeTypeSetById", params);
		MemCache.deleteValue(CsFeeTypeSet.class, id);
		MemCache.deleteObject(CsFeeTypeSet.class, id);
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
	}
	/**
	 * 根据ID逻辑删除计费策略 
	 * @param id
	 * @return
	 */
	public void removeCsFeeTypeSetById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsFeeTypeSet.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsFeeTypeSetById", params);
		MemCache.deleteValue(CsFeeTypeSet.class, id);
		MemCache.deleteObject(CsFeeTypeSet.class, id);
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
	}
	/**
	 * 根据条件更新计费策略
	 * @param values
	 * @param params
	 */
	public void updateCsFeeTypeSetByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsFeeTypeSetCount(params)>255){
			MemCache.clearValue(CsFeeTypeSet.class);
			MemCache.clearObject(CsFeeTypeSet.class);
		}else{
			params.put("eval","csfts_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsFeeTypeSetEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsFeeTypeSet.class, map.get("eval"));
				MemCache.deleteObject(CsFeeTypeSet.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsFeeTypeSetBy", allParams);
		Trigger.on(CsFeeTypeSet.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除计费策略
	 * @param params
	 */
	public void deleteCsFeeTypeSetByConfirm(Map params){
		Trigger.on(CsFeeTypeSet.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsFeeTypeSetCount(params)>255){
			MemCache.clearValue(CsFeeTypeSet.class);
			MemCache.clearObject(CsFeeTypeSet.class);
		}else{
			params.put("eval","csfts_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsFeeTypeSetEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsFeeTypeSet.class, map.get("eval"));
				MemCache.deleteObject(CsFeeTypeSet.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsFeeTypeSet.M.class);
		MemCache.clearValue(CsFeeTypeSet.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsFeeTypeSetBy", params);
	}
	

}