package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMarketLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketLog;
import com.lazy3q.web.helper.$;

/**
 * 营销跟踪的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMarketLogDao extends SqlMapClientDaoSupport implements ICsMarketLogDao
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
	private CsMarketLog custom(CsMarketLog params){
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
			"csmlTitle"			
			,"csmlProfile"			
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
	 * 获取所有营销跟踪
	 * @return
	 */	
	public List<CsMarketLog> getCsMarketLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMarketLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMarketLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取营销跟踪统计
	 * @return
	 */
	public List getCsMarketLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMarketLogStats", params);
	}

	/**
	 * 获取营销跟踪分页
	 * @return
	 */
	public Page getCsMarketLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMarketLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMarketLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取营销跟踪数量
	 * @return
	 */	
	public Long getCsMarketLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMarketLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取营销跟踪自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMarketLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMarketLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取营销跟踪
	 * @param params
	 * @return
	 */
	public CsMarketLog getCsMarketLog(Map params)
	{		
		params = custom(params);
			
		List<CsMarketLog> list=this.getCsMarketLogList(params,1);
		CsMarketLog csMarketLog = list.isEmpty()?null:list.get(0);
		
		return csMarketLog;
	}
		
	/**
	 * 根据ID取营销跟踪
	 * @param id
	 * @return
	 */
	public CsMarketLog getCsMarketLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMarketLog csMarketLog = null;
		if(csMarketLog!=null)
			return csMarketLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMarketLog = (CsMarketLog) this.getSqlMapClientTemplate().queryForObject("getCsMarketLogById",params);
		if(csMarketLog!=null)
			MemCache.setValue(CsMarketLog.class,id, csMarketLog.getKeyValue());
		return csMarketLog;
	}
	
	/**
	 * 根据ID取营销跟踪(不缓存)
	 * @param id
	 * @return
	 */
	public CsMarketLog GetCsMarketLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMarketLog) this.getSqlMapClientTemplate().queryForObject("getCsMarketLogById",params);
	}
	

	/**
	 * 保存营销跟踪
	 * @param csMarketLog
	 */
	public CsMarketLog saveCsMarketLog(CsMarketLog csMarketLog)
	{
		csMarketLog = custom(csMarketLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMarketLog", csMarketLog));
		csMarketLog = this.GetCsMarketLogById(id);
		MemCache.setValue(CsMarketLog.class,id, csMarketLog.getKeyValue());
		return Trigger.on(CsMarketLog.class,Trigger.SAVE,csMarketLog);	
	}

	

	/**
	 * 更新营销跟踪
	 * @param csMarketLog
	 */
	public void updateCsMarketLog(CsMarketLog csMarketLog)
	{
		csMarketLog = custom(csMarketLog);
		this.getSqlMapClientTemplate().update("updateCsMarketLog", csMarketLog);
		csMarketLog = this.GetCsMarketLogById(csMarketLog.getCsmlId());
		if(csMarketLog!=null){
			MemCache.setValue(CsMarketLog.class,csMarketLog.getCsmlId(), csMarketLog.getKeyValue());
		}
		Trigger.on(CsMarketLog.class,Trigger.UPDATE,csMarketLog);
	}
	/**
	 * 更新营销跟踪非空字段
	 * @param csMarketLog
	 */
	public void updateCsMarketLog$NotNull(CsMarketLog csMarketLog)
	{
		csMarketLog = custom(csMarketLog);
		this.getSqlMapClientTemplate().update("updateCsMarketLog$NotNull", csMarketLog);
		csMarketLog = this.GetCsMarketLogById(csMarketLog.getCsmlId());
		if(csMarketLog!=null){
			MemCache.setValue(CsMarketLog.class,csMarketLog.getCsmlId(), csMarketLog.getKeyValue());
		}
		Trigger.on(CsMarketLog.class,Trigger.UPDATE,csMarketLog);
	}

	/**
	 * 根据ID删除一个营销跟踪
	 * @param id
	 */
	public void deleteCsMarketLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMarketLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMarketLogById", params);
		MemCache.deleteValue(CsMarketLog.class, id);
	}
	/**
	 * 根据ID逻辑删除营销跟踪 
	 * @param id
	 * @return
	 */
	public void removeCsMarketLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMarketLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMarketLogById", params);
		MemCache.deleteValue(CsMarketLog.class, id);
	}
	/**
	 * 根据条件更新营销跟踪
	 * @param values
	 * @param params
	 */
	public void updateCsMarketLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMarketLogCount(params)>255){
			MemCache.clearValue(CsMarketLog.class);
		}else{
			params.put("eval","csml_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMarketLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMarketLog.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMarketLogBy", allParams);
		Trigger.on(CsMarketLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除营销跟踪
	 * @param params
	 */
	public void deleteCsMarketLogByConfirm(Map params){
		Trigger.on(CsMarketLog.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMarketLogCount(params)>255){
			MemCache.clearValue(CsMarketLog.class);
		}else{
			params.put("eval","csml_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMarketLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMarketLog.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMarketLogBy", params);
	}
	

}