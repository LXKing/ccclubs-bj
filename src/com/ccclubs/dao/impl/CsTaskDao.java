package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsTaskDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTask;
import com.lazy3q.web.helper.$;

/**
 * 数据任务的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsTaskDao extends SqlMapClientDaoSupport implements ICsTaskDao
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
	private CsTask custom(CsTask params){
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
	 * 获取所有数据任务
	 * @return
	 */	
	public List<CsTask> getCsTaskList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsTask> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsTaskList", params);
		
		return list;
	}
	
	
	/**
	 * 获取数据任务统计
	 * @return
	 */
	public List getCsTaskStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsTaskStats", params);
	}

	/**
	 * 获取数据任务分页
	 * @return
	 */
	public Page getCsTaskPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsTaskCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsTaskPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取数据任务数量
	 * @return
	 */	
	public Long getCsTaskCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsTaskEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取数据任务自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsTaskEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsTaskEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取数据任务
	 * @param params
	 * @return
	 */
	public CsTask getCsTask(Map params)
	{		
		params = custom(params);
			
		List<CsTask> list=this.getCsTaskList(params,1);
		CsTask csTask = list.isEmpty()?null:list.get(0);
		
		return csTask;
	}
		
	/**
	 * 根据ID取数据任务
	 * @param id
	 * @return
	 */
	public CsTask getCsTaskById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsTask csTask = null;
		if(csTask!=null)
			return csTask;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csTask = (CsTask) this.getSqlMapClientTemplate().queryForObject("getCsTaskById",params);
		if(csTask!=null)
			MemCache.setValue(CsTask.class,id, csTask.getKeyValue());
		return csTask;
	}
	
	/**
	 * 根据ID取数据任务(不缓存)
	 * @param id
	 * @return
	 */
	public CsTask GetCsTaskById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsTask) this.getSqlMapClientTemplate().queryForObject("getCsTaskById",params);
	}
	

	/**
	 * 保存数据任务
	 * @param csTask
	 */
	public CsTask saveCsTask(CsTask csTask)
	{
		csTask = custom(csTask);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsTask", csTask));
		csTask = this.GetCsTaskById(id);
		MemCache.setValue(CsTask.class,id, csTask.getKeyValue());
		return Trigger.on(CsTask.class,Trigger.SAVE,csTask);	
	}

	

	/**
	 * 更新数据任务
	 * @param csTask
	 */
	public void updateCsTask(CsTask csTask)
	{
		csTask = custom(csTask);
		this.getSqlMapClientTemplate().update("updateCsTask", csTask);
		csTask = this.GetCsTaskById(csTask.getCstId());
		if(csTask!=null){
			MemCache.setValue(CsTask.class,csTask.getCstId(), csTask.getKeyValue());
		}
		Trigger.on(CsTask.class,Trigger.UPDATE,csTask);
	}
	/**
	 * 更新数据任务非空字段
	 * @param csTask
	 */
	public void updateCsTask$NotNull(CsTask csTask)
	{
		csTask = custom(csTask);
		this.getSqlMapClientTemplate().update("updateCsTask$NotNull", csTask);
		csTask = this.GetCsTaskById(csTask.getCstId());
		if(csTask!=null){
			MemCache.setValue(CsTask.class,csTask.getCstId(), csTask.getKeyValue());
		}
		Trigger.on(CsTask.class,Trigger.UPDATE,csTask);
	}

	/**
	 * 根据ID删除一个数据任务
	 * @param id
	 */
	public void deleteCsTaskById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsTask.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsTaskById", params);
		MemCache.deleteValue(CsTask.class, id);
	}
	/**
	 * 根据条件更新数据任务
	 * @param values
	 * @param params
	 */
	public void updateCsTaskByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsTaskCount(params)>255){
			MemCache.clearValue(CsTask.class);
		}else{
			params.put("eval","cst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsTaskEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsTask.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsTaskBy", allParams);
		Trigger.on(CsTask.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除数据任务
	 * @param params
	 */
	public void deleteCsTaskByConfirm(Map params){
		Trigger.on(CsTask.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsTaskCount(params)>255){
			MemCache.clearValue(CsTask.class);
		}else{
			params.put("eval","cst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsTaskEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsTask.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsTaskBy", params);
	}
	

}