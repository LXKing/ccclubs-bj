package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSaleOpportDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleOpport;
import com.lazy3q.web.helper.$;

/**
 * 销售机会的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSaleOpportDao extends SqlMapClientDaoSupport implements ICsSaleOpportDao
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
	private CsSaleOpport custom(CsSaleOpport params){
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
			"cssoTitle"			
			,"cssoTag"			
			,"cssoProfile"			
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
	 * 获取所有销售机会
	 * @return
	 */	
	public List<CsSaleOpport> getCsSaleOpportList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSaleOpport> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSaleOpportList", params);
		
		return list;
	}
	
	
	/**
	 * 获取销售机会统计
	 * @return
	 */
	public List getCsSaleOpportStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSaleOpportStats", params);
	}

	/**
	 * 获取销售机会分页
	 * @return
	 */
	public Page getCsSaleOpportPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSaleOpportCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSaleOpportPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取销售机会数量
	 * @return
	 */	
	public Long getCsSaleOpportCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSaleOpportEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取销售机会自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSaleOpportEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSaleOpportEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取销售机会
	 * @param params
	 * @return
	 */
	public CsSaleOpport getCsSaleOpport(Map params)
	{		
		params = custom(params);
			
		List<CsSaleOpport> list=this.getCsSaleOpportList(params,1);
		CsSaleOpport csSaleOpport = list.isEmpty()?null:list.get(0);
		
		return csSaleOpport;
	}
		
	/**
	 * 根据ID取销售机会
	 * @param id
	 * @return
	 */
	public CsSaleOpport getCsSaleOpportById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSaleOpport csSaleOpport = null;
		if(csSaleOpport!=null)
			return csSaleOpport;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSaleOpport = (CsSaleOpport) this.getSqlMapClientTemplate().queryForObject("getCsSaleOpportById",params);
		if(csSaleOpport!=null)
			MemCache.setValue(CsSaleOpport.class,id, csSaleOpport.getKeyValue());
		return csSaleOpport;
	}
	
	/**
	 * 根据ID取销售机会(不缓存)
	 * @param id
	 * @return
	 */
	public CsSaleOpport GetCsSaleOpportById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSaleOpport) this.getSqlMapClientTemplate().queryForObject("getCsSaleOpportById",params);
	}
	

	/**
	 * 保存销售机会
	 * @param csSaleOpport
	 */
	public CsSaleOpport saveCsSaleOpport(CsSaleOpport csSaleOpport)
	{
		csSaleOpport = custom(csSaleOpport);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSaleOpport", csSaleOpport));
		csSaleOpport = this.GetCsSaleOpportById(id);
		MemCache.setValue(CsSaleOpport.class,id, csSaleOpport.getKeyValue());
		return Trigger.on(CsSaleOpport.class,Trigger.SAVE,csSaleOpport);	
	}

	

	/**
	 * 更新销售机会
	 * @param csSaleOpport
	 */
	public void updateCsSaleOpport(CsSaleOpport csSaleOpport)
	{
		csSaleOpport = custom(csSaleOpport);
		this.getSqlMapClientTemplate().update("updateCsSaleOpport", csSaleOpport);
		csSaleOpport = this.GetCsSaleOpportById(csSaleOpport.getCssoId());
		if(csSaleOpport!=null){
			MemCache.setValue(CsSaleOpport.class,csSaleOpport.getCssoId(), csSaleOpport.getKeyValue());
		}
		Trigger.on(CsSaleOpport.class,Trigger.UPDATE,csSaleOpport);
	}
	/**
	 * 更新销售机会非空字段
	 * @param csSaleOpport
	 */
	public void updateCsSaleOpport$NotNull(CsSaleOpport csSaleOpport)
	{
		csSaleOpport = custom(csSaleOpport);
		this.getSqlMapClientTemplate().update("updateCsSaleOpport$NotNull", csSaleOpport);
		csSaleOpport = this.GetCsSaleOpportById(csSaleOpport.getCssoId());
		if(csSaleOpport!=null){
			MemCache.setValue(CsSaleOpport.class,csSaleOpport.getCssoId(), csSaleOpport.getKeyValue());
		}
		Trigger.on(CsSaleOpport.class,Trigger.UPDATE,csSaleOpport);
	}

	/**
	 * 根据ID删除一个销售机会
	 * @param id
	 */
	public void deleteCsSaleOpportById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSaleOpport.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSaleOpportById", params);
		MemCache.deleteValue(CsSaleOpport.class, id);
	}
	/**
	 * 根据ID逻辑删除销售机会 
	 * @param id
	 * @return
	 */
	public void removeCsSaleOpportById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSaleOpport.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSaleOpportById", params);
		MemCache.deleteValue(CsSaleOpport.class, id);
	}
	/**
	 * 根据条件更新销售机会
	 * @param values
	 * @param params
	 */
	public void updateCsSaleOpportByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSaleOpportCount(params)>255){
			MemCache.clearValue(CsSaleOpport.class);
		}else{
			params.put("eval","csso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSaleOpportEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSaleOpport.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSaleOpportBy", allParams);
		Trigger.on(CsSaleOpport.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除销售机会
	 * @param params
	 */
	public void deleteCsSaleOpportByConfirm(Map params){
		Trigger.on(CsSaleOpport.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSaleOpportCount(params)>255){
			MemCache.clearValue(CsSaleOpport.class);
		}else{
			params.put("eval","csso_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSaleOpportEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSaleOpport.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSaleOpportBy", params);
	}
	

}