package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsIntegralRuleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsIntegralRule;
import com.lazy3q.web.helper.$;

/**
 * 积分规则的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsIntegralRuleDao extends SqlMapClientDaoSupport implements ICsIntegralRuleDao
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
	private CsIntegralRule custom(CsIntegralRule params){
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
			"csirDepict"			
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
	 * 获取所有积分规则
	 * @return
	 */	
	public List<CsIntegralRule> getCsIntegralRuleList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsIntegralRule> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csirUpdateTime","csirUpdateTimeStart","csirUpdateTimeEnd","csirAddTime","csirAddTimeStart","csirAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsIntegralRule.M.class, md5);
			List caches = MemCache.getObject(CsIntegralRule.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsIntegralRule cacheObject = MemCache.getObject(CsIntegralRule.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsIntegralRule.M.class, md5);
							MemCache.deleteValue(CsIntegralRule.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsIntegralRule.M.class, md5);
					MemCache.deleteValue(CsIntegralRule.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsIntegralRuleList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsIntegralRule csIntegralRule:list){
				caches.add(csIntegralRule.getCsirId());
				MemCache.setObject(CsIntegralRule.class,csIntegralRule.getCsirId(), csIntegralRule);
			}
			MemCache.setObject(CsIntegralRule.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsIntegralRule.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取积分规则统计
	 * @return
	 */
	public List getCsIntegralRuleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsIntegralRuleStats", params);
	}

	/**
	 * 获取积分规则分页
	 * @return
	 */
	public Page getCsIntegralRulePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsIntegralRuleCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsIntegralRulePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取积分规则数量
	 * @return
	 */	
	public Long getCsIntegralRuleCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRuleEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取积分规则自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsIntegralRuleEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRuleEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取积分规则
	 * @param params
	 * @return
	 */
	public CsIntegralRule getCsIntegralRule(Map params)
	{		
		params = custom(params);
			
		List<CsIntegralRule> list=this.getCsIntegralRuleList(params,1);
		CsIntegralRule csIntegralRule = list.isEmpty()?null:list.get(0);
		
		return csIntegralRule;
	}
		
	/**
	 * 根据ID取积分规则
	 * @param id
	 * @return
	 */
	public CsIntegralRule getCsIntegralRuleById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsIntegralRule csIntegralRule = MemCache.getObject(CsIntegralRule.class, id);
		if(csIntegralRule!=null)
			return csIntegralRule;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csIntegralRule = (CsIntegralRule) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRuleById",params);
		if(csIntegralRule!=null)
			MemCache.setValue(CsIntegralRule.class,id, csIntegralRule.getKeyValue());
		if(csIntegralRule!=null)
			MemCache.setObject(CsIntegralRule.class,id, csIntegralRule);
		return csIntegralRule;
	}
	
	/**
	 * 根据ID取积分规则(不缓存)
	 * @param id
	 * @return
	 */
	public CsIntegralRule GetCsIntegralRuleById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsIntegralRule) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRuleById",params);
	}
	

	/**
	 * 保存积分规则
	 * @param csIntegralRule
	 */
	public CsIntegralRule saveCsIntegralRule(CsIntegralRule csIntegralRule)
	{
		csIntegralRule = custom(csIntegralRule);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsIntegralRule", csIntegralRule));
		csIntegralRule = this.GetCsIntegralRuleById(id);
		MemCache.setValue(CsIntegralRule.class,id, csIntegralRule.getKeyValue());
		MemCache.setObject(CsIntegralRule.class,id,csIntegralRule);
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
		return Trigger.on(CsIntegralRule.class,Trigger.SAVE,csIntegralRule);	
	}

	

	/**
	 * 更新积分规则
	 * @param csIntegralRule
	 */
	public void updateCsIntegralRule(CsIntegralRule csIntegralRule)
	{
		csIntegralRule = custom(csIntegralRule);
		this.getSqlMapClientTemplate().update("updateCsIntegralRule", csIntegralRule);
		csIntegralRule = this.GetCsIntegralRuleById(csIntegralRule.getCsirId());
		if(csIntegralRule!=null){
			MemCache.setValue(CsIntegralRule.class,csIntegralRule.getCsirId(), csIntegralRule.getKeyValue());
			MemCache.setObject(CsIntegralRule.class,csIntegralRule.getCsirId(),csIntegralRule);
		}
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
		Trigger.on(CsIntegralRule.class,Trigger.UPDATE,csIntegralRule);
	}
	/**
	 * 更新积分规则非空字段
	 * @param csIntegralRule
	 */
	public void updateCsIntegralRule$NotNull(CsIntegralRule csIntegralRule)
	{
		csIntegralRule = custom(csIntegralRule);
		this.getSqlMapClientTemplate().update("updateCsIntegralRule$NotNull", csIntegralRule);
		csIntegralRule = this.GetCsIntegralRuleById(csIntegralRule.getCsirId());
		if(csIntegralRule!=null){
			MemCache.setValue(CsIntegralRule.class,csIntegralRule.getCsirId(), csIntegralRule.getKeyValue());
			MemCache.setObject(CsIntegralRule.class,csIntegralRule.getCsirId(),csIntegralRule);
		}
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
		Trigger.on(CsIntegralRule.class,Trigger.UPDATE,csIntegralRule);
	}

	/**
	 * 根据ID删除一个积分规则
	 * @param id
	 */
	public void deleteCsIntegralRuleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsIntegralRule.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsIntegralRuleById", params);
		MemCache.deleteValue(CsIntegralRule.class, id);
		MemCache.deleteObject(CsIntegralRule.class, id);
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
	}
	/**
	 * 根据ID逻辑删除积分规则 
	 * @param id
	 * @return
	 */
	public void removeCsIntegralRuleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsIntegralRule.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsIntegralRuleById", params);
		MemCache.deleteValue(CsIntegralRule.class, id);
		MemCache.deleteObject(CsIntegralRule.class, id);
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
	}
	/**
	 * 根据条件更新积分规则
	 * @param values
	 * @param params
	 */
	public void updateCsIntegralRuleByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsIntegralRuleCount(params)>255){
			MemCache.clearValue(CsIntegralRule.class);
			MemCache.clearObject(CsIntegralRule.class);
		}else{
			params.put("eval","csir_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsIntegralRuleEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsIntegralRule.class, map.get("eval"));
				MemCache.deleteObject(CsIntegralRule.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsIntegralRuleBy", allParams);
		Trigger.on(CsIntegralRule.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除积分规则
	 * @param params
	 */
	public void deleteCsIntegralRuleByConfirm(Map params){
		Trigger.on(CsIntegralRule.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsIntegralRuleCount(params)>255){
			MemCache.clearValue(CsIntegralRule.class);
			MemCache.clearObject(CsIntegralRule.class);
		}else{
			params.put("eval","csir_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsIntegralRuleEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsIntegralRule.class, map.get("eval"));
				MemCache.deleteObject(CsIntegralRule.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsIntegralRule.M.class);
		MemCache.clearValue(CsIntegralRule.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsIntegralRuleBy", params);
	}
	

}