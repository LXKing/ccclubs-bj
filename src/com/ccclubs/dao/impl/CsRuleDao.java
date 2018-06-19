package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRuleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRule;
import com.lazy3q.web.helper.$;

/**
 * 规则的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRuleDao extends SqlMapClientDaoSupport implements ICsRuleDao
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
	private CsRule custom(CsRule params){
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
			"csrProfile"			
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
	 * 获取所有规则
	 * @return
	 */	
	public List<CsRule> getCsRuleList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRule> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsRule.M.class, md5);
			List caches = MemCache.getObject(CsRule.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsRule cacheObject = MemCache.getObject(CsRule.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsRule.M.class, md5);
							MemCache.deleteValue(CsRule.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsRule.M.class, md5);
					MemCache.deleteValue(CsRule.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRuleList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsRule csRule:list){
				caches.add(csRule.getCsrId());
				MemCache.setObject(CsRule.class,csRule.getCsrId(), csRule);
			}
			MemCache.setObject(CsRule.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsRule.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取规则统计
	 * @return
	 */
	public List getCsRuleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRuleStats", params);
	}

	/**
	 * 获取规则分页
	 * @return
	 */
	public Page getCsRulePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRuleCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRulePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取规则数量
	 * @return
	 */	
	public Long getCsRuleCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRuleEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取规则自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRuleEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRuleEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取规则
	 * @param params
	 * @return
	 */
	public CsRule getCsRule(Map params)
	{		
		params = custom(params);
			
		List<CsRule> list=this.getCsRuleList(params,1);
		CsRule csRule = list.isEmpty()?null:list.get(0);
		
		return csRule;
	}
		
	/**
	 * 根据ID取规则
	 * @param id
	 * @return
	 */
	public CsRule getCsRuleById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRule csRule = MemCache.getObject(CsRule.class, id);
		if(csRule!=null)
			return csRule;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRule = (CsRule) this.getSqlMapClientTemplate().queryForObject("getCsRuleById",params);
		if(csRule!=null)
			MemCache.setValue(CsRule.class,id, csRule.getKeyValue());
		if(csRule!=null)
			MemCache.setObject(CsRule.class,id, csRule);
		return csRule;
	}
	
	/**
	 * 根据ID取规则(不缓存)
	 * @param id
	 * @return
	 */
	public CsRule GetCsRuleById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRule) this.getSqlMapClientTemplate().queryForObject("getCsRuleById",params);
	}
	

	/**
	 * 保存规则
	 * @param csRule
	 */
	public CsRule saveCsRule(CsRule csRule)
	{
		csRule = custom(csRule);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRule", csRule));
		csRule = this.GetCsRuleById(id);
		MemCache.setValue(CsRule.class,id, csRule.getKeyValue());
		MemCache.setObject(CsRule.class,id,csRule);
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
		return Trigger.on(CsRule.class,Trigger.SAVE,csRule);	
	}

	

	/**
	 * 更新规则
	 * @param csRule
	 */
	public void updateCsRule(CsRule csRule)
	{
		csRule = custom(csRule);
		this.getSqlMapClientTemplate().update("updateCsRule", csRule);
		csRule = this.GetCsRuleById(csRule.getCsrId());
		if(csRule!=null){
			MemCache.setValue(CsRule.class,csRule.getCsrId(), csRule.getKeyValue());
			MemCache.setObject(CsRule.class,csRule.getCsrId(),csRule);
		}
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
		Trigger.on(CsRule.class,Trigger.UPDATE,csRule);
	}
	/**
	 * 更新规则非空字段
	 * @param csRule
	 */
	public void updateCsRule$NotNull(CsRule csRule)
	{
		csRule = custom(csRule);
		this.getSqlMapClientTemplate().update("updateCsRule$NotNull", csRule);
		csRule = this.GetCsRuleById(csRule.getCsrId());
		if(csRule!=null){
			MemCache.setValue(CsRule.class,csRule.getCsrId(), csRule.getKeyValue());
			MemCache.setObject(CsRule.class,csRule.getCsrId(),csRule);
		}
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
		Trigger.on(CsRule.class,Trigger.UPDATE,csRule);
	}

	/**
	 * 根据ID删除一个规则
	 * @param id
	 */
	public void deleteCsRuleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRule.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRuleById", params);
		MemCache.deleteValue(CsRule.class, id);
		MemCache.deleteObject(CsRule.class, id);
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
	}
	/**
	 * 根据ID逻辑删除规则 
	 * @param id
	 * @return
	 */
	public void removeCsRuleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRule.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsRuleById", params);
		MemCache.deleteValue(CsRule.class, id);
		MemCache.deleteObject(CsRule.class, id);
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
	}
	/**
	 * 根据条件更新规则
	 * @param values
	 * @param params
	 */
	public void updateCsRuleByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRuleCount(params)>255){
			MemCache.clearValue(CsRule.class);
			MemCache.clearObject(CsRule.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRuleEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRule.class, map.get("eval"));
				MemCache.deleteObject(CsRule.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRuleBy", allParams);
		Trigger.on(CsRule.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除规则
	 * @param params
	 */
	public void deleteCsRuleByConfirm(Map params){
		Trigger.on(CsRule.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRuleCount(params)>255){
			MemCache.clearValue(CsRule.class);
			MemCache.clearObject(CsRule.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRuleEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRule.class, map.get("eval"));
				MemCache.deleteObject(CsRule.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsRule.M.class);
		MemCache.clearValue(CsRule.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRuleBy", params);
	}
	

}