package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChatTalkerDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChatTalker;
import com.lazy3q.web.helper.$;

/**
 * 在线访客的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChatTalkerDao extends SqlMapClientDaoSupport implements ICsChatTalkerDao
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
	private CsChatTalker custom(CsChatTalker params){
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
			"csctRemark"			
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
	 * 获取所有在线访客
	 * @return
	 */	
	public List<CsChatTalker> getCsChatTalkerList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChatTalker> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChatTalkerList", params);
		
		return list;
	}
	
	
	/**
	 * 获取在线访客统计
	 * @return
	 */
	public List getCsChatTalkerStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChatTalkerStats", params);
	}

	/**
	 * 获取在线访客分页
	 * @return
	 */
	public Page getCsChatTalkerPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChatTalkerCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChatTalkerPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取在线访客数量
	 * @return
	 */	
	public Long getCsChatTalkerCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChatTalkerEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取在线访客自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChatTalkerEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChatTalkerEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取在线访客
	 * @param params
	 * @return
	 */
	public CsChatTalker getCsChatTalker(Map params)
	{		
		params = custom(params);
			
		List<CsChatTalker> list=this.getCsChatTalkerList(params,1);
		CsChatTalker csChatTalker = list.isEmpty()?null:list.get(0);
		
		return csChatTalker;
	}
		
	/**
	 * 根据ID取在线访客
	 * @param id
	 * @return
	 */
	public CsChatTalker getCsChatTalkerById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChatTalker csChatTalker = null;
		if(csChatTalker!=null)
			return csChatTalker;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChatTalker = (CsChatTalker) this.getSqlMapClientTemplate().queryForObject("getCsChatTalkerById",params);
		if(csChatTalker!=null)
			MemCache.setValue(CsChatTalker.class,id, csChatTalker.getKeyValue());
		return csChatTalker;
	}
	
	/**
	 * 根据ID取在线访客(不缓存)
	 * @param id
	 * @return
	 */
	public CsChatTalker GetCsChatTalkerById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChatTalker) this.getSqlMapClientTemplate().queryForObject("getCsChatTalkerById",params);
	}
	

	/**
	 * 保存在线访客
	 * @param csChatTalker
	 */
	public CsChatTalker saveCsChatTalker(CsChatTalker csChatTalker)
	{
		csChatTalker = custom(csChatTalker);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChatTalker", csChatTalker));
		csChatTalker = this.GetCsChatTalkerById(id);
		MemCache.setValue(CsChatTalker.class,id, csChatTalker.getKeyValue());
		return Trigger.on(CsChatTalker.class,Trigger.SAVE,csChatTalker);	
	}

	

	/**
	 * 更新在线访客
	 * @param csChatTalker
	 */
	public void updateCsChatTalker(CsChatTalker csChatTalker)
	{
		csChatTalker = custom(csChatTalker);
		this.getSqlMapClientTemplate().update("updateCsChatTalker", csChatTalker);
		csChatTalker = this.GetCsChatTalkerById(csChatTalker.getCsctId());
		if(csChatTalker!=null){
			MemCache.setValue(CsChatTalker.class,csChatTalker.getCsctId(), csChatTalker.getKeyValue());
		}
		Trigger.on(CsChatTalker.class,Trigger.UPDATE,csChatTalker);
	}
	/**
	 * 更新在线访客非空字段
	 * @param csChatTalker
	 */
	public void updateCsChatTalker$NotNull(CsChatTalker csChatTalker)
	{
		csChatTalker = custom(csChatTalker);
		this.getSqlMapClientTemplate().update("updateCsChatTalker$NotNull", csChatTalker);
		csChatTalker = this.GetCsChatTalkerById(csChatTalker.getCsctId());
		if(csChatTalker!=null){
			MemCache.setValue(CsChatTalker.class,csChatTalker.getCsctId(), csChatTalker.getKeyValue());
		}
		Trigger.on(CsChatTalker.class,Trigger.UPDATE,csChatTalker);
	}

	/**
	 * 根据ID删除一个在线访客
	 * @param id
	 */
	public void deleteCsChatTalkerById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChatTalker.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChatTalkerById", params);
		MemCache.deleteValue(CsChatTalker.class, id);
	}
	/**
	 * 根据ID逻辑删除在线访客 
	 * @param id
	 * @return
	 */
	public void removeCsChatTalkerById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChatTalker.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChatTalkerById", params);
		MemCache.deleteValue(CsChatTalker.class, id);
	}
	/**
	 * 根据条件更新在线访客
	 * @param values
	 * @param params
	 */
	public void updateCsChatTalkerByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChatTalkerCount(params)>255){
			MemCache.clearValue(CsChatTalker.class);
		}else{
			params.put("eval","csct_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChatTalkerEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChatTalker.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChatTalkerBy", allParams);
		Trigger.on(CsChatTalker.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除在线访客
	 * @param params
	 */
	public void deleteCsChatTalkerByConfirm(Map params){
		Trigger.on(CsChatTalker.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChatTalkerCount(params)>255){
			MemCache.clearValue(CsChatTalker.class);
		}else{
			params.put("eval","csct_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChatTalkerEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChatTalker.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChatTalkerBy", params);
	}
	

}