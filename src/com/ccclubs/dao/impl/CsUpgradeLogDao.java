package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUpgradeLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUpgradeLog;
import com.lazy3q.web.helper.$;

/**
 * 升级日志的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUpgradeLogDao extends SqlMapClientDaoSupport implements ICsUpgradeLogDao
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
	private CsUpgradeLog custom(CsUpgradeLog params){
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
			"csulTitle"			
			,"csulDescribe"			
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
	 * 获取所有升级日志
	 * @return
	 */	
	public List<CsUpgradeLog> getCsUpgradeLogList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUpgradeLog> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUpgradeLogList", params);
		
		return list;
	}
	
	
	/**
	 * 获取升级日志统计
	 * @return
	 */
	public List getCsUpgradeLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUpgradeLogStats", params);
	}

	/**
	 * 获取升级日志分页
	 * @return
	 */
	public Page getCsUpgradeLogPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUpgradeLogCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUpgradeLogPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取升级日志数量
	 * @return
	 */	
	public Long getCsUpgradeLogCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUpgradeLogEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取升级日志自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUpgradeLogEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUpgradeLogEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取升级日志
	 * @param params
	 * @return
	 */
	public CsUpgradeLog getCsUpgradeLog(Map params)
	{		
		params = custom(params);
			
		List<CsUpgradeLog> list=this.getCsUpgradeLogList(params,1);
		CsUpgradeLog csUpgradeLog = list.isEmpty()?null:list.get(0);
		
		return csUpgradeLog;
	}
		
	/**
	 * 根据ID取升级日志
	 * @param id
	 * @return
	 */
	public CsUpgradeLog getCsUpgradeLogById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUpgradeLog csUpgradeLog = null;
		if(csUpgradeLog!=null)
			return csUpgradeLog;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUpgradeLog = (CsUpgradeLog) this.getSqlMapClientTemplate().queryForObject("getCsUpgradeLogById",params);
		if(csUpgradeLog!=null)
			MemCache.setValue(CsUpgradeLog.class,id, csUpgradeLog.getKeyValue());
		return csUpgradeLog;
	}
	
	/**
	 * 根据ID取升级日志(不缓存)
	 * @param id
	 * @return
	 */
	public CsUpgradeLog GetCsUpgradeLogById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUpgradeLog) this.getSqlMapClientTemplate().queryForObject("getCsUpgradeLogById",params);
	}
	

	/**
	 * 保存升级日志
	 * @param csUpgradeLog
	 */
	public CsUpgradeLog saveCsUpgradeLog(CsUpgradeLog csUpgradeLog)
	{
		csUpgradeLog = custom(csUpgradeLog);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUpgradeLog", csUpgradeLog));
		csUpgradeLog = this.GetCsUpgradeLogById(id);
		MemCache.setValue(CsUpgradeLog.class,id, csUpgradeLog.getKeyValue());
		return Trigger.on(CsUpgradeLog.class,Trigger.SAVE,csUpgradeLog);	
	}

	

	/**
	 * 更新升级日志
	 * @param csUpgradeLog
	 */
	public void updateCsUpgradeLog(CsUpgradeLog csUpgradeLog)
	{
		csUpgradeLog = custom(csUpgradeLog);
		this.getSqlMapClientTemplate().update("updateCsUpgradeLog", csUpgradeLog);
		csUpgradeLog = this.GetCsUpgradeLogById(csUpgradeLog.getCsulId());
		if(csUpgradeLog!=null){
			MemCache.setValue(CsUpgradeLog.class,csUpgradeLog.getCsulId(), csUpgradeLog.getKeyValue());
		}
		Trigger.on(CsUpgradeLog.class,Trigger.UPDATE,csUpgradeLog);
	}
	/**
	 * 更新升级日志非空字段
	 * @param csUpgradeLog
	 */
	public void updateCsUpgradeLog$NotNull(CsUpgradeLog csUpgradeLog)
	{
		csUpgradeLog = custom(csUpgradeLog);
		this.getSqlMapClientTemplate().update("updateCsUpgradeLog$NotNull", csUpgradeLog);
		csUpgradeLog = this.GetCsUpgradeLogById(csUpgradeLog.getCsulId());
		if(csUpgradeLog!=null){
			MemCache.setValue(CsUpgradeLog.class,csUpgradeLog.getCsulId(), csUpgradeLog.getKeyValue());
		}
		Trigger.on(CsUpgradeLog.class,Trigger.UPDATE,csUpgradeLog);
	}

	/**
	 * 根据ID删除一个升级日志
	 * @param id
	 */
	public void deleteCsUpgradeLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUpgradeLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUpgradeLogById", params);
		MemCache.deleteValue(CsUpgradeLog.class, id);
	}
	/**
	 * 根据ID逻辑删除升级日志 
	 * @param id
	 * @return
	 */
	public void removeCsUpgradeLogById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUpgradeLog.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUpgradeLogById", params);
		MemCache.deleteValue(CsUpgradeLog.class, id);
	}
	/**
	 * 根据条件更新升级日志
	 * @param values
	 * @param params
	 */
	public void updateCsUpgradeLogByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUpgradeLogCount(params)>255){
			MemCache.clearValue(CsUpgradeLog.class);
		}else{
			params.put("eval","csul_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUpgradeLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUpgradeLog.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUpgradeLogBy", allParams);
		Trigger.on(CsUpgradeLog.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除升级日志
	 * @param params
	 */
	public void deleteCsUpgradeLogByConfirm(Map params){
		Trigger.on(CsUpgradeLog.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUpgradeLogCount(params)>255){
			MemCache.clearValue(CsUpgradeLog.class);
		}else{
			params.put("eval","csul_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUpgradeLogEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUpgradeLog.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUpgradeLogBy", params);
	}
	

}