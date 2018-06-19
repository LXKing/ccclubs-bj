package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsAlipayLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayLog;
import com.lazy3q.web.helper.$;

/**
 * 接口日志的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsAlipayLogDao extends SqlMapClientDaoSupport implements ICsAlipayLogDao
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
	private CsAlipayLog custom(CsAlipayLog params){
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
			"csalLog"			
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
	 * 获取所有接口日志
	 * @return
	 */	
	public List<CsAlipayLog> getCsAlipayLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsAlipayLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsAlipayLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取接口日志统计
	 * @return
	 */
	public List getCsAlipayLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsAlipayLogStats", params);
	}

	/**
	 * 获取接口日志分页
	 * @return
	 */
	public Page getCsAlipayLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsAlipayLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsAlipayLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取接口日志数量
	 * @return
	 */	
	public Long getCsAlipayLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAlipayLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取接口日志自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsAlipayLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAlipayLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取接口日志
	 * @param params
	 * @return
	 */
	public CsAlipayLog getCsAlipayLog(Map params)
	{		
		params = custom(params);
			
		List<CsAlipayLog> list=this.getCsAlipayLogList(params,1);
		CsAlipayLog csAlipayLog = list.isEmpty()?null:list.get(0);
		
		return csAlipayLog;
	}
		
	/**
	 * 根据ID取接口日志
	 * @param id
	 * @return
	 */
	public CsAlipayLog getCsAlipayLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsAlipayLog csAlipayLog = null;
		if(csAlipayLog!=null)
			return csAlipayLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csAlipayLog = (CsAlipayLog) this.getSqlMapClientTemplate().queryForObject("getCsAlipayLogById",params);
		if(csAlipayLog!=null)
			MemCache.setValue(CsAlipayLog.class,id, csAlipayLog.getKeyValue());
		return csAlipayLog;
	}
	
	/**
	 * 根据ID取接口日志(不缓存)
	 * @param id
	 * @return
	 */
	public CsAlipayLog GetCsAlipayLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsAlipayLog) this.getSqlMapClientTemplate().queryForObject("getCsAlipayLogById",params);
	}
	

	/**
	 * 保存接口日志
	 * @param csAlipayLog
	 */
	public CsAlipayLog saveCsAlipayLog(CsAlipayLog csAlipayLog)
	{
		csAlipayLog = custom(csAlipayLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsAlipayLog", csAlipayLog));
		csAlipayLog = this.GetCsAlipayLogById(id);
		MemCache.setValue(CsAlipayLog.class,id, csAlipayLog.getKeyValue());
		return Trigger.on(CsAlipayLog.class,Trigger.SAVE,csAlipayLog);	
	}

	

	/**
	 * 更新接口日志
	 * @param csAlipayLog
	 */
	public void updateCsAlipayLog(CsAlipayLog csAlipayLog)
	{
		csAlipayLog = custom(csAlipayLog);
		this.getSqlMapClientTemplate().update("updateCsAlipayLog", csAlipayLog);
		csAlipayLog = this.GetCsAlipayLogById(csAlipayLog.getCsalId());
		if(csAlipayLog!=null){
			MemCache.setValue(CsAlipayLog.class,csAlipayLog.getCsalId(), csAlipayLog.getKeyValue());
		}
		Trigger.on(CsAlipayLog.class,Trigger.UPDATE,csAlipayLog);
	}
	/**
	 * 更新接口日志非空字段
	 * @param csAlipayLog
	 */
	public void updateCsAlipayLog$NotNull(CsAlipayLog csAlipayLog)
	{
		csAlipayLog = custom(csAlipayLog);
		this.getSqlMapClientTemplate().update("updateCsAlipayLog$NotNull", csAlipayLog);
		csAlipayLog = this.GetCsAlipayLogById(csAlipayLog.getCsalId());
		if(csAlipayLog!=null){
			MemCache.setValue(CsAlipayLog.class,csAlipayLog.getCsalId(), csAlipayLog.getKeyValue());
		}
		Trigger.on(CsAlipayLog.class,Trigger.UPDATE,csAlipayLog);
	}

	/**
	 * 根据ID删除一个接口日志
	 * @param id
	 */
	public void deleteCsAlipayLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsAlipayLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsAlipayLogById", params);
		MemCache.deleteValue(CsAlipayLog.class, id);
	}
	/**
	 * 根据ID逻辑删除接口日志 
	 * @param id
	 * @return
	 */
	public void removeCsAlipayLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsAlipayLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsAlipayLogById", params);
		MemCache.deleteValue(CsAlipayLog.class, id);
	}
	/**
	 * 根据条件更新接口日志
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsAlipayLogCount(params)>255){
			MemCache.clearValue(CsAlipayLog.class);
		}else{
			params.put("eval","csal_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAlipayLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsAlipayLog.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsAlipayLogBy", allParams);
		Trigger.on(CsAlipayLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除接口日志
	 * @param params
	 */
	public void deleteCsAlipayLogByConfirm(Map params){
		Trigger.on(CsAlipayLog.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsAlipayLogCount(params)>255){
			MemCache.clearValue(CsAlipayLog.class);
		}else{
			params.put("eval","csal_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAlipayLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsAlipayLog.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsAlipayLogBy", params);
	}
	

}