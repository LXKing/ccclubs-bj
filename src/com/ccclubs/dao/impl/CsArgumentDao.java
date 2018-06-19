package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsArgumentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArgument;
import com.lazy3q.web.helper.$;

/**
 * 系统参数的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsArgumentDao extends SqlMapClientDaoSupport implements ICsArgumentDao
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
	private CsArgument custom(CsArgument params){
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
			"csaDescribe"			
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
	 * 获取所有系统参数
	 * @return
	 */	
	public List<CsArgument> getCsArgumentList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsArgument> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsArgument.M.class, md5);
			List caches = MemCache.getObject(CsArgument.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsArgument cacheObject = MemCache.getObject(CsArgument.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsArgument.M.class, md5);
							MemCache.deleteValue(CsArgument.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsArgument.M.class, md5);
					MemCache.deleteValue(CsArgument.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsArgumentList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsArgument csArgument:list){
				caches.add(csArgument.getCsaId());
				MemCache.setObject(CsArgument.class,csArgument.getCsaId(), csArgument);
			}
			MemCache.setObject(CsArgument.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsArgument.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取系统参数统计
	 * @return
	 */
	public List getCsArgumentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsArgumentStats", params);
	}

	/**
	 * 获取系统参数分页
	 * @return
	 */
	public Page getCsArgumentPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsArgumentCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsArgumentPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统参数数量
	 * @return
	 */	
	public Long getCsArgumentCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsArgumentEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统参数自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsArgumentEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsArgumentEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统参数
	 * @param params
	 * @return
	 */
	public CsArgument getCsArgument(Map params)
	{		
		params = custom(params);
			
		List<CsArgument> list=this.getCsArgumentList(params,1);
		CsArgument csArgument = list.isEmpty()?null:list.get(0);
		
		return csArgument;
	}
		
	/**
	 * 根据ID取系统参数
	 * @param id
	 * @return
	 */
	public CsArgument getCsArgumentById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsArgument csArgument = MemCache.getObject(CsArgument.class, id);
		if(csArgument!=null)
			return csArgument;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csArgument = (CsArgument) this.getSqlMapClientTemplate().queryForObject("getCsArgumentById",params);
		if(csArgument!=null)
			MemCache.setValue(CsArgument.class,id, csArgument.getKeyValue());
		if(csArgument!=null)
			MemCache.setObject(CsArgument.class,id, csArgument);
		return csArgument;
	}
	
	/**
	 * 根据ID取系统参数(不缓存)
	 * @param id
	 * @return
	 */
	public CsArgument GetCsArgumentById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsArgument) this.getSqlMapClientTemplate().queryForObject("getCsArgumentById",params);
	}
	

	/**
	 * 保存系统参数
	 * @param csArgument
	 */
	public CsArgument saveCsArgument(CsArgument csArgument)
	{
		csArgument = custom(csArgument);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsArgument", csArgument));
		csArgument = this.GetCsArgumentById(id);
		MemCache.setValue(CsArgument.class,id, csArgument.getKeyValue());
		MemCache.setObject(CsArgument.class,id,csArgument);
		MemCache.clearObject(CsArgument.M.class);
		MemCache.clearValue(CsArgument.M.class);
		return Trigger.on(CsArgument.class,Trigger.SAVE,csArgument);	
	}

	

	/**
	 * 更新系统参数
	 * @param csArgument
	 */
	public void updateCsArgument(CsArgument csArgument)
	{
		csArgument = custom(csArgument);
		this.getSqlMapClientTemplate().update("updateCsArgument", csArgument);
		csArgument = this.GetCsArgumentById(csArgument.getCsaId());
		if(csArgument!=null){
			MemCache.setValue(CsArgument.class,csArgument.getCsaId(), csArgument.getKeyValue());
			MemCache.setObject(CsArgument.class,csArgument.getCsaId(),csArgument);
		}
		MemCache.clearObject(CsArgument.M.class);
		MemCache.clearValue(CsArgument.M.class);
		Trigger.on(CsArgument.class,Trigger.UPDATE,csArgument);
	}
	/**
	 * 更新系统参数非空字段
	 * @param csArgument
	 */
	public void updateCsArgument$NotNull(CsArgument csArgument)
	{
		csArgument = custom(csArgument);
		this.getSqlMapClientTemplate().update("updateCsArgument$NotNull", csArgument);
		csArgument = this.GetCsArgumentById(csArgument.getCsaId());
		if(csArgument!=null){
			MemCache.setValue(CsArgument.class,csArgument.getCsaId(), csArgument.getKeyValue());
			MemCache.setObject(CsArgument.class,csArgument.getCsaId(),csArgument);
		}
		MemCache.clearObject(CsArgument.M.class);
		MemCache.clearValue(CsArgument.M.class);
		Trigger.on(CsArgument.class,Trigger.UPDATE,csArgument);
	}

	/**
	 * 根据ID删除一个系统参数
	 * @param id
	 */
	public void deleteCsArgumentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsArgument.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsArgumentById", params);
		MemCache.deleteValue(CsArgument.class, id);
		MemCache.deleteObject(CsArgument.class, id);
		MemCache.clearObject(CsArgument.M.class);
		MemCache.clearValue(CsArgument.M.class);
	}
	/**
	 * 根据条件更新系统参数
	 * @param values
	 * @param params
	 */
	public void updateCsArgumentByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsArgumentCount(params)>255){
			MemCache.clearValue(CsArgument.class);
			MemCache.clearObject(CsArgument.class);
		}else{
			params.put("eval","csa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsArgumentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsArgument.class, map.get("eval"));
				MemCache.deleteObject(CsArgument.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsArgument.M.class);
		MemCache.clearValue(CsArgument.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsArgumentBy", allParams);
		Trigger.on(CsArgument.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统参数
	 * @param params
	 */
	public void deleteCsArgumentByConfirm(Map params){
		Trigger.on(CsArgument.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsArgumentCount(params)>255){
			MemCache.clearValue(CsArgument.class);
			MemCache.clearObject(CsArgument.class);
		}else{
			params.put("eval","csa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsArgumentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsArgument.class, map.get("eval"));
				MemCache.deleteObject(CsArgument.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsArgument.M.class);
		MemCache.clearValue(CsArgument.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsArgumentBy", params);
	}
	

}