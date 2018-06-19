package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvWorkflowDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvWorkflow;
import com.lazy3q.web.helper.$;

/**
 * 工作流的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class SrvWorkflowDao extends SqlMapClientDaoSupport implements ISrvWorkflowDao
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
	private SrvWorkflow custom(SrvWorkflow params){
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
			"swProfile"			
			,"swPaint"			
			,"swConfig"			
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
	 * 获取所有工作流
	 * @return
	 */	
	public List<SrvWorkflow> getSrvWorkflowList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<SrvWorkflow> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getSrvWorkflowList", params);
		
		return list;
	}
	
	
	/**
	 * 获取工作流统计
	 * @return
	 */
	public List getSrvWorkflowStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getSrvWorkflowStats", params);
	}

	/**
	 * 获取工作流分页
	 * @return
	 */
	public Page getSrvWorkflowPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getSrvWorkflowCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getSrvWorkflowPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取工作流数量
	 * @return
	 */	
	public Long getSrvWorkflowCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvWorkflowEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取工作流自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getSrvWorkflowEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getSrvWorkflowEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取工作流
	 * @param params
	 * @return
	 */
	public SrvWorkflow getSrvWorkflow(Map params)
	{		
		params = custom(params);
			
		List<SrvWorkflow> list=this.getSrvWorkflowList(params,1);
		SrvWorkflow srvWorkflow = list.isEmpty()?null:list.get(0);
		
		return srvWorkflow;
	}
		
	/**
	 * 根据ID取工作流
	 * @param id
	 * @return
	 */
	public SrvWorkflow getSrvWorkflowById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvWorkflow srvWorkflow = null;
		if(srvWorkflow!=null)
			return srvWorkflow;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		srvWorkflow = (SrvWorkflow) this.getSqlMapClientTemplate().queryForObject("getSrvWorkflowById",params);
		if(srvWorkflow!=null)
			MemCache.setValue(SrvWorkflow.class,id, srvWorkflow.getKeyValue());
		return srvWorkflow;
	}
	
	/**
	 * 根据ID取工作流(不缓存)
	 * @param id
	 * @return
	 */
	public SrvWorkflow GetSrvWorkflowById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (SrvWorkflow) this.getSqlMapClientTemplate().queryForObject("getSrvWorkflowById",params);
	}
	

	/**
	 * 保存工作流
	 * @param srvWorkflow
	 */
	public SrvWorkflow saveSrvWorkflow(SrvWorkflow srvWorkflow)
	{
		srvWorkflow = custom(srvWorkflow);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveSrvWorkflow", srvWorkflow));
		srvWorkflow = this.GetSrvWorkflowById(id);
		MemCache.setValue(SrvWorkflow.class,id, srvWorkflow.getKeyValue());
		return Trigger.on(SrvWorkflow.class,Trigger.SAVE,srvWorkflow);	
	}

	

	/**
	 * 更新工作流
	 * @param srvWorkflow
	 */
	public void updateSrvWorkflow(SrvWorkflow srvWorkflow)
	{
		srvWorkflow = custom(srvWorkflow);
		this.getSqlMapClientTemplate().update("updateSrvWorkflow", srvWorkflow);
		srvWorkflow = this.GetSrvWorkflowById(srvWorkflow.getSwId());
		if(srvWorkflow!=null){
			MemCache.setValue(SrvWorkflow.class,srvWorkflow.getSwId(), srvWorkflow.getKeyValue());
		}
		Trigger.on(SrvWorkflow.class,Trigger.UPDATE,srvWorkflow);
	}
	/**
	 * 更新工作流非空字段
	 * @param srvWorkflow
	 */
	public void updateSrvWorkflow$NotNull(SrvWorkflow srvWorkflow)
	{
		srvWorkflow = custom(srvWorkflow);
		this.getSqlMapClientTemplate().update("updateSrvWorkflow$NotNull", srvWorkflow);
		srvWorkflow = this.GetSrvWorkflowById(srvWorkflow.getSwId());
		if(srvWorkflow!=null){
			MemCache.setValue(SrvWorkflow.class,srvWorkflow.getSwId(), srvWorkflow.getKeyValue());
		}
		Trigger.on(SrvWorkflow.class,Trigger.UPDATE,srvWorkflow);
	}

	/**
	 * 根据ID删除一个工作流
	 * @param id
	 */
	public void deleteSrvWorkflowById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvWorkflow.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteSrvWorkflowById", params);
		MemCache.deleteValue(SrvWorkflow.class, id);
	}
	/**
	 * 根据ID逻辑删除工作流 
	 * @param id
	 * @return
	 */
	public void removeSrvWorkflowById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(SrvWorkflow.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeSrvWorkflowById", params);
		MemCache.deleteValue(SrvWorkflow.class, id);
	}
	/**
	 * 根据条件更新工作流
	 * @param values
	 * @param params
	 */
	public void updateSrvWorkflowByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getSrvWorkflowCount(params)>255){
			MemCache.clearValue(SrvWorkflow.class);
		}else{
			params.put("eval","sw_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvWorkflowEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvWorkflow.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateSrvWorkflowBy", allParams);
		Trigger.on(SrvWorkflow.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除工作流
	 * @param params
	 */
	public void deleteSrvWorkflowByConfirm(Map params){
		Trigger.on(SrvWorkflow.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getSrvWorkflowCount(params)>255){
			MemCache.clearValue(SrvWorkflow.class);
		}else{
			params.put("eval","sw_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getSrvWorkflowEval", params);
			for(Map map:result){
				MemCache.deleteValue(SrvWorkflow.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteSrvWorkflowBy", params);
	}
	

}