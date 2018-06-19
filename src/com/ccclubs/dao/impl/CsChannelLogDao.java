package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChannelLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChannelLog;
import com.lazy3q.web.helper.$;

/**
 * 渠道跟踪维护的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChannelLogDao extends SqlMapClientDaoSupport implements ICsChannelLogDao
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
	private CsChannelLog custom(CsChannelLog params){
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
			"csclTitle"			
			,"csclProfile"			
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
	 * 获取所有渠道跟踪维护
	 * @return
	 */	
	public List<CsChannelLog> getCsChannelLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChannelLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChannelLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取渠道跟踪维护统计
	 * @return
	 */
	public List getCsChannelLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChannelLogStats", params);
	}

	/**
	 * 获取渠道跟踪维护分页
	 * @return
	 */
	public Page getCsChannelLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChannelLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChannelLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取渠道跟踪维护数量
	 * @return
	 */	
	public Long getCsChannelLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChannelLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取渠道跟踪维护自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChannelLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChannelLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取渠道跟踪维护
	 * @param params
	 * @return
	 */
	public CsChannelLog getCsChannelLog(Map params)
	{		
		params = custom(params);
			
		List<CsChannelLog> list=this.getCsChannelLogList(params,1);
		CsChannelLog csChannelLog = list.isEmpty()?null:list.get(0);
		
		return csChannelLog;
	}
		
	/**
	 * 根据ID取渠道跟踪维护
	 * @param id
	 * @return
	 */
	public CsChannelLog getCsChannelLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChannelLog csChannelLog = null;
		if(csChannelLog!=null)
			return csChannelLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChannelLog = (CsChannelLog) this.getSqlMapClientTemplate().queryForObject("getCsChannelLogById",params);
		if(csChannelLog!=null)
			MemCache.setValue(CsChannelLog.class,id, csChannelLog.getKeyValue());
		return csChannelLog;
	}
	
	/**
	 * 根据ID取渠道跟踪维护(不缓存)
	 * @param id
	 * @return
	 */
	public CsChannelLog GetCsChannelLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChannelLog) this.getSqlMapClientTemplate().queryForObject("getCsChannelLogById",params);
	}
	

	/**
	 * 保存渠道跟踪维护
	 * @param csChannelLog
	 */
	public CsChannelLog saveCsChannelLog(CsChannelLog csChannelLog)
	{
		csChannelLog = custom(csChannelLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChannelLog", csChannelLog));
		csChannelLog = this.GetCsChannelLogById(id);
		MemCache.setValue(CsChannelLog.class,id, csChannelLog.getKeyValue());
		return Trigger.on(CsChannelLog.class,Trigger.SAVE,csChannelLog);	
	}

	

	/**
	 * 更新渠道跟踪维护
	 * @param csChannelLog
	 */
	public void updateCsChannelLog(CsChannelLog csChannelLog)
	{
		csChannelLog = custom(csChannelLog);
		this.getSqlMapClientTemplate().update("updateCsChannelLog", csChannelLog);
		csChannelLog = this.GetCsChannelLogById(csChannelLog.getCsclId());
		if(csChannelLog!=null){
			MemCache.setValue(CsChannelLog.class,csChannelLog.getCsclId(), csChannelLog.getKeyValue());
		}
		Trigger.on(CsChannelLog.class,Trigger.UPDATE,csChannelLog);
	}
	/**
	 * 更新渠道跟踪维护非空字段
	 * @param csChannelLog
	 */
	public void updateCsChannelLog$NotNull(CsChannelLog csChannelLog)
	{
		csChannelLog = custom(csChannelLog);
		this.getSqlMapClientTemplate().update("updateCsChannelLog$NotNull", csChannelLog);
		csChannelLog = this.GetCsChannelLogById(csChannelLog.getCsclId());
		if(csChannelLog!=null){
			MemCache.setValue(CsChannelLog.class,csChannelLog.getCsclId(), csChannelLog.getKeyValue());
		}
		Trigger.on(CsChannelLog.class,Trigger.UPDATE,csChannelLog);
	}

	/**
	 * 根据ID删除一个渠道跟踪维护
	 * @param id
	 */
	public void deleteCsChannelLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChannelLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChannelLogById", params);
		MemCache.deleteValue(CsChannelLog.class, id);
	}
	/**
	 * 根据ID逻辑删除渠道跟踪维护 
	 * @param id
	 * @return
	 */
	public void removeCsChannelLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChannelLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChannelLogById", params);
		MemCache.deleteValue(CsChannelLog.class, id);
	}
	/**
	 * 根据条件更新渠道跟踪维护
	 * @param values
	 * @param params
	 */
	public void updateCsChannelLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChannelLogCount(params)>255){
			MemCache.clearValue(CsChannelLog.class);
		}else{
			params.put("eval","cscl_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChannelLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChannelLog.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChannelLogBy", allParams);
		Trigger.on(CsChannelLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除渠道跟踪维护
	 * @param params
	 */
	public void deleteCsChannelLogByConfirm(Map params){
		Trigger.on(CsChannelLog.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChannelLogCount(params)>255){
			MemCache.clearValue(CsChannelLog.class);
		}else{
			params.put("eval","cscl_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChannelLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChannelLog.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChannelLogBy", params);
	}
	

}