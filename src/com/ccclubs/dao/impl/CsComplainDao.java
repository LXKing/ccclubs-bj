package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsComplainDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsComplain;
import com.lazy3q.web.helper.$;

/**
 * 意见反馈的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsComplainDao extends SqlMapClientDaoSupport implements ICsComplainDao
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
	private CsComplain custom(CsComplain params){
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
			"cscDealer","cscUpdateTime","cscAddTime","cscObject"
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
	 * 获取所有意见反馈
	 * @return
	 */	
	public List<CsComplain> getCsComplainList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsComplain> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsComplainList", params);
		
		return list;
	}
	
	
	/**
	 * 获取意见反馈统计
	 * @return
	 */
	public List getCsComplainStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsComplainStats", params);
	}

	/**
	 * 获取意见反馈分页
	 * @return
	 */
	public Page getCsComplainPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsComplainCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsComplainPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取意见反馈数量
	 * @return
	 */	
	public Long getCsComplainCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsComplainEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取意见反馈自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsComplainEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsComplainEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取意见反馈
	 * @param params
	 * @return
	 */
	public CsComplain getCsComplain(Map params)
	{		
		params = custom(params);
			
		List<CsComplain> list=this.getCsComplainList(params,1);
		CsComplain csComplain = list.isEmpty()?null:list.get(0);
		
		return csComplain;
	}
		
	/**
	 * 根据ID取意见反馈
	 * @param id
	 * @return
	 */
	public CsComplain getCsComplainById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsComplain csComplain = null;
		if(csComplain!=null)
			return csComplain;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csComplain = (CsComplain) this.getSqlMapClientTemplate().queryForObject("getCsComplainById",params);
		if(csComplain!=null)
			MemCache.setValue(CsComplain.class,id, csComplain.getKeyValue());
		return csComplain;
	}
	
	/**
	 * 根据ID取意见反馈(不缓存)
	 * @param id
	 * @return
	 */
	public CsComplain GetCsComplainById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsComplain) this.getSqlMapClientTemplate().queryForObject("getCsComplainById",params);
	}
	

	/**
	 * 保存意见反馈
	 * @param csComplain
	 */
	public CsComplain saveCsComplain(CsComplain csComplain)
	{
		csComplain = custom(csComplain);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsComplain", csComplain));
		csComplain = this.GetCsComplainById(id);
		MemCache.setValue(CsComplain.class,id, csComplain.getKeyValue());
		return Trigger.on(CsComplain.class,Trigger.SAVE,csComplain);	
	}

	

	/**
	 * 更新意见反馈
	 * @param csComplain
	 */
	public void updateCsComplain(CsComplain csComplain)
	{
		csComplain = custom(csComplain);
		this.getSqlMapClientTemplate().update("updateCsComplain", csComplain);
		csComplain = this.GetCsComplainById(csComplain.getCscId());
		if(csComplain!=null){
			MemCache.setValue(CsComplain.class,csComplain.getCscId(), csComplain.getKeyValue());
		}
		Trigger.on(CsComplain.class,Trigger.UPDATE,csComplain);
	}
	/**
	 * 更新意见反馈非空字段
	 * @param csComplain
	 */
	public void updateCsComplain$NotNull(CsComplain csComplain)
	{
		csComplain = custom(csComplain);
		this.getSqlMapClientTemplate().update("updateCsComplain$NotNull", csComplain);
		csComplain = this.GetCsComplainById(csComplain.getCscId());
		if(csComplain!=null){
			MemCache.setValue(CsComplain.class,csComplain.getCscId(), csComplain.getKeyValue());
		}
		Trigger.on(CsComplain.class,Trigger.UPDATE,csComplain);
	}

	/**
	 * 根据ID删除一个意见反馈
	 * @param id
	 */
	public void deleteCsComplainById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsComplain.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsComplainById", params);
		MemCache.deleteValue(CsComplain.class, id);
	}
	/**
	 * 根据ID逻辑删除意见反馈 
	 * @param id
	 * @return
	 */
	public void removeCsComplainById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsComplain.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsComplainById", params);
		MemCache.deleteValue(CsComplain.class, id);
	}
	/**
	 * 根据条件更新意见反馈
	 * @param values
	 * @param params
	 */
	public void updateCsComplainByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsComplainCount(params)>255){
			MemCache.clearValue(CsComplain.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsComplainEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsComplain.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsComplainBy", allParams);
		Trigger.on(CsComplain.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除意见反馈
	 * @param params
	 */
	public void deleteCsComplainByConfirm(Map params){
		Trigger.on(CsComplain.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsComplainCount(params)>255){
			MemCache.clearValue(CsComplain.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsComplainEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsComplain.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsComplainBy", params);
	}
	

}