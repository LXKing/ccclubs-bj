package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarSysIssueDao;
import com.ccclubs.model.CsCarSysIssue;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

/**
 * 系统车况报告的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsCarSysIssueDao extends SqlMapClientDaoSupport implements ICsCarSysIssueDao
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
	private CsCarSysIssue custom(CsCarSysIssue params){
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
		"cscsiContent","cscsiKeywords","cscsiRemark"
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
	 * 获取所有系统车况报告
	 * @return
	 */	
	public List<CsCarSysIssue> getCsCarSysIssueList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCarSysIssue> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarSysIssueList", params);
		
		return list;
	}
	
	
	/**
	 * 获取系统车况报告统计
	 * @return
	 */
	public List getCsCarSysIssueStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarSysIssueStats", params);
	}

	/**
	 * 获取系统车况报告分页
	 * @return
	 */
	public Page getCsCarSysIssuePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarSysIssueCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarSysIssuePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统车况报告数量
	 * @return
	 */	
	public Long getCsCarSysIssueCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarSysIssueEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统车况报告自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarSysIssueEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarSysIssueEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统车况报告
	 * @param params
	 * @return
	 */
	public CsCarSysIssue getCsCarSysIssue(Map params)
	{		
		params = custom(params);
			
		List<CsCarSysIssue> list=this.getCsCarSysIssueList(params,1);
		CsCarSysIssue csCarSysIssue = list.isEmpty()?null:list.get(0);
		
		return csCarSysIssue;
	}
		
	/**
	 * 根据ID取系统车况报告
	 * @param id
	 * @return
	 */
	public CsCarSysIssue getCsCarSysIssueById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCarSysIssue csCarSysIssue = null;
		if(csCarSysIssue!=null)
			return csCarSysIssue;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCarSysIssue = (CsCarSysIssue) this.getSqlMapClientTemplate().queryForObject("getCsCarSysIssueById",params);
		if(csCarSysIssue!=null)
			MemCache.setValue(CsCarSysIssue.class,id, csCarSysIssue.getKeyValue());
		return csCarSysIssue;
	}
	
	/**
	 * 根据ID取系统车况报告(不缓存)
	 * @param id
	 * @return
	 */
	public CsCarSysIssue GetCsCarSysIssueById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCarSysIssue) this.getSqlMapClientTemplate().queryForObject("getCsCarSysIssueById",params);
	}
	

	/**
	 * 保存系统车况报告
	 * @param csCarSysIssue
	 */
	public CsCarSysIssue saveCsCarSysIssue(CsCarSysIssue csCarSysIssue)
	{
		csCarSysIssue = custom(csCarSysIssue);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCarSysIssue", csCarSysIssue));
		csCarSysIssue = this.GetCsCarSysIssueById(id);
		MemCache.setValue(CsCarSysIssue.class,id, csCarSysIssue.getKeyValue());
		return Trigger.on(CsCarSysIssue.class,Trigger.SAVE,csCarSysIssue);	
	}

	

	/**
	 * 更新系统车况报告
	 * @param csCarSysIssue
	 */
	public void updateCsCarSysIssue(CsCarSysIssue csCarSysIssue)
	{
		csCarSysIssue = custom(csCarSysIssue);
		this.getSqlMapClientTemplate().update("updateCsCarSysIssue", csCarSysIssue);
		csCarSysIssue = this.GetCsCarSysIssueById(csCarSysIssue.getCscsiId());
		if(csCarSysIssue!=null){
			MemCache.setValue(CsCarSysIssue.class,csCarSysIssue.getCscsiId(), csCarSysIssue.getKeyValue());
		}
		Trigger.on(CsCarSysIssue.class,Trigger.UPDATE,csCarSysIssue);
	}
	/**
	 * 更新系统车况报告非空字段
	 * @param csCarSysIssue
	 */
	public void updateCsCarSysIssue$NotNull(CsCarSysIssue csCarSysIssue)
	{
		csCarSysIssue = custom(csCarSysIssue);
		this.getSqlMapClientTemplate().update("updateCsCarSysIssue$NotNull", csCarSysIssue);
		csCarSysIssue = this.GetCsCarSysIssueById(csCarSysIssue.getCscsiId());
		if(csCarSysIssue!=null){
			MemCache.setValue(CsCarSysIssue.class,csCarSysIssue.getCscsiId(), csCarSysIssue.getKeyValue());
		}
		Trigger.on(CsCarSysIssue.class,Trigger.UPDATE,csCarSysIssue);
	}

	/**
	 * 根据ID删除一个系统车况报告
	 * @param id
	 */
	public void deleteCsCarSysIssueById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarSysIssue.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarSysIssueById", params);
		MemCache.deleteValue(CsCarSysIssue.class, id);
	}
	/**
	 * 根据ID逻辑删除系统车况报告 
	 * @param id
	 * @return
	 */
	public void removeCsCarSysIssueById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarSysIssue.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCarSysIssueById", params);
		MemCache.deleteValue(CsCarSysIssue.class, id);
	}
	/**
	 * 根据条件更新系统车况报告
	 * @param values
	 * @param params
	 */
	public void updateCsCarSysIssueByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarSysIssueCount(params)>255){
			MemCache.clearValue(CsCarSysIssue.class);
		}else{
			params.put("eval","cscsi_Id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarSysIssueEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarSysIssue.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarSysIssueBy", allParams);
		Trigger.on(CsCarSysIssue.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统车况报告
	 * @param params
	 */
	public void deleteCsCarSysIssueByConfirm(Map params){
		Trigger.on(CsCarSysIssue.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarSysIssueCount(params)>255){
			MemCache.clearValue(CsCarSysIssue.class);
		}else{
			params.put("eval","cscsi_Id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarSysIssueEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarSysIssue.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarSysIssueBy", params);
	}
	

}