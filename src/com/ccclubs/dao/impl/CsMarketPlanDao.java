package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMarketPlanDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketPlan;
import com.lazy3q.web.helper.$;

/**
 * 营销方案的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMarketPlanDao extends SqlMapClientDaoSupport implements ICsMarketPlanDao
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
	private CsMarketPlan custom(CsMarketPlan params){
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
			"csmpName"			
			,"csmpProfile"			
			,"csmpRemark"			
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
	 * 获取所有营销方案
	 * @return
	 */	
	public List<CsMarketPlan> getCsMarketPlanList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMarketPlan> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMarketPlanList", params);
		
		return list;
	}
	
	
	/**
	 * 获取营销方案统计
	 * @return
	 */
	public List getCsMarketPlanStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMarketPlanStats", params);
	}

	/**
	 * 获取营销方案分页
	 * @return
	 */
	public Page getCsMarketPlanPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMarketPlanCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMarketPlanPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取营销方案数量
	 * @return
	 */	
	public Long getCsMarketPlanCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMarketPlanEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取营销方案自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMarketPlanEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMarketPlanEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取营销方案
	 * @param params
	 * @return
	 */
	public CsMarketPlan getCsMarketPlan(Map params)
	{		
		params = custom(params);
			
		List<CsMarketPlan> list=this.getCsMarketPlanList(params,1);
		CsMarketPlan csMarketPlan = list.isEmpty()?null:list.get(0);
		
		return csMarketPlan;
	}
		
	/**
	 * 根据ID取营销方案
	 * @param id
	 * @return
	 */
	public CsMarketPlan getCsMarketPlanById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMarketPlan csMarketPlan = null;
		if(csMarketPlan!=null)
			return csMarketPlan;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMarketPlan = (CsMarketPlan) this.getSqlMapClientTemplate().queryForObject("getCsMarketPlanById",params);
		if(csMarketPlan!=null)
			MemCache.setValue(CsMarketPlan.class,id, csMarketPlan.getKeyValue());
		return csMarketPlan;
	}
	
	/**
	 * 根据ID取营销方案(不缓存)
	 * @param id
	 * @return
	 */
	public CsMarketPlan GetCsMarketPlanById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMarketPlan) this.getSqlMapClientTemplate().queryForObject("getCsMarketPlanById",params);
	}
	

	/**
	 * 保存营销方案
	 * @param csMarketPlan
	 */
	public CsMarketPlan saveCsMarketPlan(CsMarketPlan csMarketPlan)
	{
		csMarketPlan = custom(csMarketPlan);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMarketPlan", csMarketPlan));
		csMarketPlan = this.GetCsMarketPlanById(id);
		MemCache.setValue(CsMarketPlan.class,id, csMarketPlan.getKeyValue());
		return Trigger.on(CsMarketPlan.class,Trigger.SAVE,csMarketPlan);	
	}

	

	/**
	 * 更新营销方案
	 * @param csMarketPlan
	 */
	public void updateCsMarketPlan(CsMarketPlan csMarketPlan)
	{
		csMarketPlan = custom(csMarketPlan);
		this.getSqlMapClientTemplate().update("updateCsMarketPlan", csMarketPlan);
		csMarketPlan = this.GetCsMarketPlanById(csMarketPlan.getCsmpId());
		if(csMarketPlan!=null){
			MemCache.setValue(CsMarketPlan.class,csMarketPlan.getCsmpId(), csMarketPlan.getKeyValue());
		}
		Trigger.on(CsMarketPlan.class,Trigger.UPDATE,csMarketPlan);
	}
	/**
	 * 更新营销方案非空字段
	 * @param csMarketPlan
	 */
	public void updateCsMarketPlan$NotNull(CsMarketPlan csMarketPlan)
	{
		csMarketPlan = custom(csMarketPlan);
		this.getSqlMapClientTemplate().update("updateCsMarketPlan$NotNull", csMarketPlan);
		csMarketPlan = this.GetCsMarketPlanById(csMarketPlan.getCsmpId());
		if(csMarketPlan!=null){
			MemCache.setValue(CsMarketPlan.class,csMarketPlan.getCsmpId(), csMarketPlan.getKeyValue());
		}
		Trigger.on(CsMarketPlan.class,Trigger.UPDATE,csMarketPlan);
	}

	/**
	 * 根据ID删除一个营销方案
	 * @param id
	 */
	public void deleteCsMarketPlanById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMarketPlan.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMarketPlanById", params);
		MemCache.deleteValue(CsMarketPlan.class, id);
	}
	/**
	 * 根据ID逻辑删除营销方案 
	 * @param id
	 * @return
	 */
	public void removeCsMarketPlanById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMarketPlan.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMarketPlanById", params);
		MemCache.deleteValue(CsMarketPlan.class, id);
	}
	/**
	 * 根据条件更新营销方案
	 * @param values
	 * @param params
	 */
	public void updateCsMarketPlanByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMarketPlanCount(params)>255){
			MemCache.clearValue(CsMarketPlan.class);
		}else{
			params.put("eval","csmp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMarketPlanEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMarketPlan.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMarketPlanBy", allParams);
		Trigger.on(CsMarketPlan.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除营销方案
	 * @param params
	 */
	public void deleteCsMarketPlanByConfirm(Map params){
		Trigger.on(CsMarketPlan.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMarketPlanCount(params)>255){
			MemCache.clearValue(CsMarketPlan.class);
		}else{
			params.put("eval","csmp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMarketPlanEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMarketPlan.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMarketPlanBy", params);
	}
	

}