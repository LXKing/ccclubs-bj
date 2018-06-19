package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPowerPileDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPowerPile;
import com.lazy3q.web.helper.$;

/**
 * 充电桩的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPowerPileDao extends SqlMapClientDaoSupport implements ICsPowerPileDao
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
	private CsPowerPile custom(CsPowerPile params){
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
			"csppPerson"			
			,"csppTel"			
			,"csppRemark"			
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
	 * 获取所有充电桩
	 * @return
	 */	
	public List<CsPowerPile> getCsPowerPileList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPowerPile> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPowerPileList", params);
		
		return list;
	}
	
	
	/**
	 * 获取充电桩统计
	 * @return
	 */
	public List getCsPowerPileStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPowerPileStats", params);
	}

	/**
	 * 获取充电桩分页
	 * @return
	 */
	public Page getCsPowerPilePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPowerPileCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPowerPilePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取充电桩数量
	 * @return
	 */	
	public Long getCsPowerPileCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPowerPileEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取充电桩自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPowerPileEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPowerPileEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public CsPowerPile getCsPowerPile(Map params)
	{		
		params = custom(params);
			
		List<CsPowerPile> list=this.getCsPowerPileList(params,1);
		CsPowerPile csPowerPile = list.isEmpty()?null:list.get(0);
		
		return csPowerPile;
	}
		
	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public CsPowerPile getCsPowerPileById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPowerPile csPowerPile = null;
		if(csPowerPile!=null)
			return csPowerPile;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPowerPile = (CsPowerPile) this.getSqlMapClientTemplate().queryForObject("getCsPowerPileById",params);
		if(csPowerPile!=null)
			MemCache.setValue(CsPowerPile.class,id, csPowerPile.getKeyValue());
		return csPowerPile;
	}
	
	/**
	 * 根据ID取充电桩(不缓存)
	 * @param id
	 * @return
	 */
	public CsPowerPile GetCsPowerPileById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPowerPile) this.getSqlMapClientTemplate().queryForObject("getCsPowerPileById",params);
	}
	

	/**
	 * 保存充电桩
	 * @param csPowerPile
	 */
	public CsPowerPile saveCsPowerPile(CsPowerPile csPowerPile)
	{
		csPowerPile = custom(csPowerPile);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPowerPile", csPowerPile));
		csPowerPile = this.GetCsPowerPileById(id);
		MemCache.setValue(CsPowerPile.class,id, csPowerPile.getKeyValue());
		return Trigger.on(CsPowerPile.class,Trigger.SAVE,csPowerPile);	
	}

	

	/**
	 * 更新充电桩
	 * @param csPowerPile
	 */
	public void updateCsPowerPile(CsPowerPile csPowerPile)
	{
		csPowerPile = custom(csPowerPile);
		this.getSqlMapClientTemplate().update("updateCsPowerPile", csPowerPile);
		csPowerPile = this.GetCsPowerPileById(csPowerPile.getCsppId());
		if(csPowerPile!=null){
			MemCache.setValue(CsPowerPile.class,csPowerPile.getCsppId(), csPowerPile.getKeyValue());
		}
		Trigger.on(CsPowerPile.class,Trigger.UPDATE,csPowerPile);
	}
	/**
	 * 更新充电桩非空字段
	 * @param csPowerPile
	 */
	public void updateCsPowerPile$NotNull(CsPowerPile csPowerPile)
	{
		csPowerPile = custom(csPowerPile);
		this.getSqlMapClientTemplate().update("updateCsPowerPile$NotNull", csPowerPile);
		csPowerPile = this.GetCsPowerPileById(csPowerPile.getCsppId());
		if(csPowerPile!=null){
			MemCache.setValue(CsPowerPile.class,csPowerPile.getCsppId(), csPowerPile.getKeyValue());
		}
		Trigger.on(CsPowerPile.class,Trigger.UPDATE,csPowerPile);
	}

	/**
	 * 根据ID删除一个充电桩
	 * @param id
	 */
	public void deleteCsPowerPileById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPowerPile.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPowerPileById", params);
		MemCache.deleteValue(CsPowerPile.class, id);
	}
	/**
	 * 根据ID逻辑删除充电桩 
	 * @param id
	 * @return
	 */
	public void removeCsPowerPileById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPowerPile.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsPowerPileById", params);
		MemCache.deleteValue(CsPowerPile.class, id);
	}
	/**
	 * 根据条件更新充电桩
	 * @param values
	 * @param params
	 */
	public void updateCsPowerPileByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPowerPileCount(params)>255){
			MemCache.clearValue(CsPowerPile.class);
		}else{
			params.put("eval","cspp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPowerPileEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPowerPile.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPowerPileBy", allParams);
		Trigger.on(CsPowerPile.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除充电桩
	 * @param params
	 */
	public void deleteCsPowerPileByConfirm(Map params){
		Trigger.on(CsPowerPile.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPowerPileCount(params)>255){
			MemCache.clearValue(CsPowerPile.class);
		}else{
			params.put("eval","cspp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPowerPileEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPowerPile.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPowerPileBy", params);
	}
	

}