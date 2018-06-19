package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsActivityDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsActivity;
import com.lazy3q.web.helper.$;

/**
 * 营销活动的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsActivityDao extends SqlMapClientDaoSupport implements ICsActivityDao
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
	private CsActivity custom(CsActivity params){
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
			"csaTitle"			
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
	 * 获取所有营销活动
	 * @return
	 */	
	public List<CsActivity> getCsActivityList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsActivity> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsActivityList", params);
		
		return list;
	}
	
	
	/**
	 * 获取营销活动统计
	 * @return
	 */
	public List getCsActivityStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsActivityStats", params);
	}

	/**
	 * 获取营销活动分页
	 * @return
	 */
	public Page getCsActivityPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsActivityCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsActivityPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取营销活动数量
	 * @return
	 */	
	public Long getCsActivityCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsActivityEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取营销活动自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsActivityEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsActivityEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取营销活动
	 * @param params
	 * @return
	 */
	public CsActivity getCsActivity(Map params)
	{		
		params = custom(params);
			
		List<CsActivity> list=this.getCsActivityList(params,1);
		CsActivity csActivity = list.isEmpty()?null:list.get(0);
		
		return csActivity;
	}
		
	/**
	 * 根据ID取营销活动
	 * @param id
	 * @return
	 */
	public CsActivity getCsActivityById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsActivity csActivity = null;
		if(csActivity!=null)
			return csActivity;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csActivity = (CsActivity) this.getSqlMapClientTemplate().queryForObject("getCsActivityById",params);
		if(csActivity!=null)
			MemCache.setValue(CsActivity.class,id, csActivity.getKeyValue());
		return csActivity;
	}
	
	/**
	 * 根据ID取营销活动(不缓存)
	 * @param id
	 * @return
	 */
	public CsActivity GetCsActivityById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsActivity) this.getSqlMapClientTemplate().queryForObject("getCsActivityById",params);
	}
	

	/**
	 * 保存营销活动
	 * @param csActivity
	 */
	public CsActivity saveCsActivity(CsActivity csActivity)
	{
		csActivity = custom(csActivity);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsActivity", csActivity));
		csActivity = this.GetCsActivityById(id);
		MemCache.setValue(CsActivity.class,id, csActivity.getKeyValue());
		return Trigger.on(CsActivity.class,Trigger.SAVE,csActivity);	
	}

	

	/**
	 * 更新营销活动
	 * @param csActivity
	 */
	public void updateCsActivity(CsActivity csActivity)
	{
		csActivity = custom(csActivity);
		this.getSqlMapClientTemplate().update("updateCsActivity", csActivity);
		csActivity = this.GetCsActivityById(csActivity.getCsaId());
		if(csActivity!=null){
			MemCache.setValue(CsActivity.class,csActivity.getCsaId(), csActivity.getKeyValue());
		}
		Trigger.on(CsActivity.class,Trigger.UPDATE,csActivity);
	}
	/**
	 * 更新营销活动非空字段
	 * @param csActivity
	 */
	public void updateCsActivity$NotNull(CsActivity csActivity)
	{
		csActivity = custom(csActivity);
		this.getSqlMapClientTemplate().update("updateCsActivity$NotNull", csActivity);
		csActivity = this.GetCsActivityById(csActivity.getCsaId());
		if(csActivity!=null){
			MemCache.setValue(CsActivity.class,csActivity.getCsaId(), csActivity.getKeyValue());
		}
		Trigger.on(CsActivity.class,Trigger.UPDATE,csActivity);
	}

	/**
	 * 根据ID删除一个营销活动
	 * @param id
	 */
	public void deleteCsActivityById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsActivity.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsActivityById", params);
		MemCache.deleteValue(CsActivity.class, id);
	}
	/**
	 * 根据ID逻辑删除营销活动 
	 * @param id
	 * @return
	 */
	public void removeCsActivityById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsActivity.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsActivityById", params);
		MemCache.deleteValue(CsActivity.class, id);
	}
	/**
	 * 根据条件更新营销活动
	 * @param values
	 * @param params
	 */
	public void updateCsActivityByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsActivityCount(params)>255){
			MemCache.clearValue(CsActivity.class);
		}else{
			params.put("eval","csa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsActivityEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsActivity.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsActivityBy", allParams);
		Trigger.on(CsActivity.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除营销活动
	 * @param params
	 */
	public void deleteCsActivityByConfirm(Map params){
		Trigger.on(CsActivity.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsActivityCount(params)>255){
			MemCache.clearValue(CsActivity.class);
		}else{
			params.put("eval","csa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsActivityEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsActivity.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsActivityBy", params);
	}
	

}