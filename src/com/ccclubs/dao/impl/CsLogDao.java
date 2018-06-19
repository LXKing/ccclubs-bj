package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLog;
import com.lazy3q.web.helper.$;

/**
 * 系统日志的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLogDao extends SqlMapClientDaoSupport implements ICsLogDao
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
	private CsLog custom(CsLog params){
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
			"cslDetail"			
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
	 * 获取所有系统日志
	 * @return
	 */	
	public List<CsLog> getCsLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取系统日志统计
	 * @return
	 */
	public List getCsLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLogStats", params);
	}

	/**
	 * 获取系统日志分页
	 * @return
	 */
	public Page getCsLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统日志数量
	 * @return
	 */	
	public Long getCsLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统日志自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统日志
	 * @param params
	 * @return
	 */
	public CsLog getCsLog(Map params)
	{		
		params = custom(params);
			
		List<CsLog> list=this.getCsLogList(params,1);
		CsLog csLog = list.isEmpty()?null:list.get(0);
		
		return csLog;
	}
		
	/**
	 * 根据ID取系统日志
	 * @param id
	 * @return
	 */
	public CsLog getCsLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLog csLog = null;
		if(csLog!=null)
			return csLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLog = (CsLog) this.getSqlMapClientTemplate().queryForObject("getCsLogById",params);
		return csLog;
	}
	
	/**
	 * 根据ID取系统日志(不缓存)
	 * @param id
	 * @return
	 */
	public CsLog GetCsLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLog) this.getSqlMapClientTemplate().queryForObject("getCsLogById",params);
	}
	

	/**
	 * 保存系统日志
	 * @param csLog
	 */
	public CsLog saveCsLog(CsLog csLog)
	{
		csLog = custom(csLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLog", csLog));
		csLog = this.GetCsLogById(id);
		return Trigger.on(CsLog.class,Trigger.SAVE,csLog);	
	}

	

	/**
	 * 更新系统日志
	 * @param csLog
	 */
	public void updateCsLog(CsLog csLog)
	{
		csLog = custom(csLog);
		this.getSqlMapClientTemplate().update("updateCsLog", csLog);
		csLog = this.GetCsLogById(csLog.getCslId());
		Trigger.on(CsLog.class,Trigger.UPDATE,csLog);
	}
	/**
	 * 更新系统日志非空字段
	 * @param csLog
	 */
	public void updateCsLog$NotNull(CsLog csLog)
	{
		csLog = custom(csLog);
		this.getSqlMapClientTemplate().update("updateCsLog$NotNull", csLog);
		csLog = this.GetCsLogById(csLog.getCslId());
		Trigger.on(CsLog.class,Trigger.UPDATE,csLog);
	}

	/**
	 * 根据ID删除一个系统日志
	 * @param id
	 */
	public void deleteCsLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLogById", params);
	}
	/**
	 * 根据条件更新系统日志
	 * @param values
	 * @param params
	 */
	public void updateCsLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLogBy", allParams);
		Trigger.on(CsLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统日志
	 * @param params
	 */
	public void deleteCsLogByConfirm(Map params){
		Trigger.on(CsLog.class,Trigger.DELETE,params);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLogBy", params);
	}
	

}