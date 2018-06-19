package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCareDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCare;
import com.lazy3q.web.helper.$;

/**
 * 客户关怀的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCareDao extends SqlMapClientDaoSupport implements ICsCareDao
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
	private CsCare custom(CsCare params){
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
			"cscTitle"			
			,"cscDesc"			
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
	 * 获取所有客户关怀
	 * @return
	 */	
	public List<CsCare> getCsCareList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCare> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCareList", params);
		
		return list;
	}
	
	
	/**
	 * 获取客户关怀统计
	 * @return
	 */
	public List getCsCareStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCareStats", params);
	}

	/**
	 * 获取客户关怀分页
	 * @return
	 */
	public Page getCsCarePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCareCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取客户关怀数量
	 * @return
	 */	
	public Long getCsCareCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCareEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取客户关怀自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCareEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCareEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取客户关怀
	 * @param params
	 * @return
	 */
	public CsCare getCsCare(Map params)
	{		
		params = custom(params);
			
		List<CsCare> list=this.getCsCareList(params,1);
		CsCare csCare = list.isEmpty()?null:list.get(0);
		
		return csCare;
	}
		
	/**
	 * 根据ID取客户关怀
	 * @param id
	 * @return
	 */
	public CsCare getCsCareById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCare csCare = null;
		if(csCare!=null)
			return csCare;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCare = (CsCare) this.getSqlMapClientTemplate().queryForObject("getCsCareById",params);
		if(csCare!=null)
			MemCache.setValue(CsCare.class,id, csCare.getKeyValue());
		return csCare;
	}
	
	/**
	 * 根据ID取客户关怀(不缓存)
	 * @param id
	 * @return
	 */
	public CsCare GetCsCareById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCare) this.getSqlMapClientTemplate().queryForObject("getCsCareById",params);
	}
	

	/**
	 * 保存客户关怀
	 * @param csCare
	 */
	public CsCare saveCsCare(CsCare csCare)
	{
		csCare = custom(csCare);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCare", csCare));
		csCare = this.GetCsCareById(id);
		MemCache.setValue(CsCare.class,id, csCare.getKeyValue());
		return Trigger.on(CsCare.class,Trigger.SAVE,csCare);	
	}

	

	/**
	 * 更新客户关怀
	 * @param csCare
	 */
	public void updateCsCare(CsCare csCare)
	{
		csCare = custom(csCare);
		this.getSqlMapClientTemplate().update("updateCsCare", csCare);
		csCare = this.GetCsCareById(csCare.getCscId());
		if(csCare!=null){
			MemCache.setValue(CsCare.class,csCare.getCscId(), csCare.getKeyValue());
		}
		Trigger.on(CsCare.class,Trigger.UPDATE,csCare);
	}
	/**
	 * 更新客户关怀非空字段
	 * @param csCare
	 */
	public void updateCsCare$NotNull(CsCare csCare)
	{
		csCare = custom(csCare);
		this.getSqlMapClientTemplate().update("updateCsCare$NotNull", csCare);
		csCare = this.GetCsCareById(csCare.getCscId());
		if(csCare!=null){
			MemCache.setValue(CsCare.class,csCare.getCscId(), csCare.getKeyValue());
		}
		Trigger.on(CsCare.class,Trigger.UPDATE,csCare);
	}

	/**
	 * 根据ID删除一个客户关怀
	 * @param id
	 */
	public void deleteCsCareById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCare.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCareById", params);
		MemCache.deleteValue(CsCare.class, id);
	}
	/**
	 * 根据ID逻辑删除客户关怀 
	 * @param id
	 * @return
	 */
	public void removeCsCareById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCare.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCareById", params);
		MemCache.deleteValue(CsCare.class, id);
	}
	/**
	 * 根据条件更新客户关怀
	 * @param values
	 * @param params
	 */
	public void updateCsCareByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCareCount(params)>255){
			MemCache.clearValue(CsCare.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCareEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCare.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCareBy", allParams);
		Trigger.on(CsCare.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除客户关怀
	 * @param params
	 */
	public void deleteCsCareByConfirm(Map params){
		Trigger.on(CsCare.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCareCount(params)>255){
			MemCache.clearValue(CsCare.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCareEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCare.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCareBy", params);
	}
	

}