package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRevisitDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRevisit;
import com.lazy3q.web.helper.$;

/**
 * 用户回访的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRevisitDao extends SqlMapClientDaoSupport implements ICsRevisitDao
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
	private CsRevisit custom(CsRevisit params){
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
			"csrRemark"			
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
	 * 获取所有用户回访
	 * @return
	 */	
	public List<CsRevisit> getCsRevisitList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRevisit> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRevisitList", params);
		
		return list;
	}
	
	
	/**
	 * 获取用户回访统计
	 * @return
	 */
	public List getCsRevisitStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRevisitStats", params);
	}

	/**
	 * 获取用户回访分页
	 * @return
	 */
	public Page getCsRevisitPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRevisitCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRevisitPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取用户回访数量
	 * @return
	 */	
	public Long getCsRevisitCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRevisitEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取用户回访自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRevisitEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRevisitEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取用户回访
	 * @param params
	 * @return
	 */
	public CsRevisit getCsRevisit(Map params)
	{		
		params = custom(params);
			
		List<CsRevisit> list=this.getCsRevisitList(params,1);
		CsRevisit csRevisit = list.isEmpty()?null:list.get(0);
		
		return csRevisit;
	}
		
	/**
	 * 根据ID取用户回访
	 * @param id
	 * @return
	 */
	public CsRevisit getCsRevisitById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRevisit csRevisit = null;
		if(csRevisit!=null)
			return csRevisit;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRevisit = (CsRevisit) this.getSqlMapClientTemplate().queryForObject("getCsRevisitById",params);
		if(csRevisit!=null)
			MemCache.setValue(CsRevisit.class,id, csRevisit.getKeyValue());
		return csRevisit;
	}
	
	/**
	 * 根据ID取用户回访(不缓存)
	 * @param id
	 * @return
	 */
	public CsRevisit GetCsRevisitById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRevisit) this.getSqlMapClientTemplate().queryForObject("getCsRevisitById",params);
	}
	

	/**
	 * 保存用户回访
	 * @param csRevisit
	 */
	public CsRevisit saveCsRevisit(CsRevisit csRevisit)
	{
		csRevisit = custom(csRevisit);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRevisit", csRevisit));
		csRevisit = this.GetCsRevisitById(id);
		MemCache.setValue(CsRevisit.class,id, csRevisit.getKeyValue());
		return Trigger.on(CsRevisit.class,Trigger.SAVE,csRevisit);	
	}

	

	/**
	 * 更新用户回访
	 * @param csRevisit
	 */
	public void updateCsRevisit(CsRevisit csRevisit)
	{
		csRevisit = custom(csRevisit);
		this.getSqlMapClientTemplate().update("updateCsRevisit", csRevisit);
		csRevisit = this.GetCsRevisitById(csRevisit.getCsrId());
		if(csRevisit!=null){
			MemCache.setValue(CsRevisit.class,csRevisit.getCsrId(), csRevisit.getKeyValue());
		}
		Trigger.on(CsRevisit.class,Trigger.UPDATE,csRevisit);
	}
	/**
	 * 更新用户回访非空字段
	 * @param csRevisit
	 */
	public void updateCsRevisit$NotNull(CsRevisit csRevisit)
	{
		csRevisit = custom(csRevisit);
		this.getSqlMapClientTemplate().update("updateCsRevisit$NotNull", csRevisit);
		csRevisit = this.GetCsRevisitById(csRevisit.getCsrId());
		if(csRevisit!=null){
			MemCache.setValue(CsRevisit.class,csRevisit.getCsrId(), csRevisit.getKeyValue());
		}
		Trigger.on(CsRevisit.class,Trigger.UPDATE,csRevisit);
	}

	/**
	 * 根据ID删除一个用户回访
	 * @param id
	 */
	public void deleteCsRevisitById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRevisit.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRevisitById", params);
		MemCache.deleteValue(CsRevisit.class, id);
	}
	/**
	 * 根据ID逻辑删除用户回访 
	 * @param id
	 * @return
	 */
	public void removeCsRevisitById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRevisit.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsRevisitById", params);
		MemCache.deleteValue(CsRevisit.class, id);
	}
	/**
	 * 根据条件更新用户回访
	 * @param values
	 * @param params
	 */
	public void updateCsRevisitByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRevisitCount(params)>255){
			MemCache.clearValue(CsRevisit.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRevisitEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRevisit.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRevisitBy", allParams);
		Trigger.on(CsRevisit.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除用户回访
	 * @param params
	 */
	public void deleteCsRevisitByConfirm(Map params){
		Trigger.on(CsRevisit.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRevisitCount(params)>255){
			MemCache.clearValue(CsRevisit.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRevisitEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRevisit.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRevisitBy", params);
	}
	

}