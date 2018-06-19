package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChargePileDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChargePile;
import com.lazy3q.web.helper.$;

/**
 * 充电桩的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChargePileDao extends SqlMapClientDaoSupport implements ICsChargePileDao
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
	private CsChargePile custom(CsChargePile params){
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
			"csmMarket"			
			,"csmTag"			
			,"csmMark"			
			,"csmVisitFlag"			
			,"csmRemark"			
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
	public List<CsChargePile> getCsChargePileList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChargePile> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChargePileList", params);
		
		return list;
	}
	
	
	/**
	 * 获取充电桩统计
	 * @return
	 */
	public List getCsChargePileStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChargePileStats", params);
	}

	/**
	 * 获取充电桩分页
	 * @return
	 */
	public Page getCsChargePilePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChargePileCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChargePilePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取充电桩数量
	 * @return
	 */	
	public Long getCsChargePileCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChargePileEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取充电桩自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChargePileEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChargePileEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public CsChargePile getCsChargePile(Map params)
	{		
		params = custom(params);
			
		List<CsChargePile> list=this.getCsChargePileList(params,1);
		CsChargePile csChargePile = list.isEmpty()?null:list.get(0);
		
		return csChargePile;
	}
		
	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public CsChargePile getCsChargePileById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChargePile csChargePile = null;
		if(csChargePile!=null)
			return csChargePile;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChargePile = (CsChargePile) this.getSqlMapClientTemplate().queryForObject("getCsChargePileById",params);
		if(csChargePile!=null)
			MemCache.setValue(CsChargePile.class,id, csChargePile.getKeyValue());
		return csChargePile;
	}
	
	/**
	 * 根据ID取充电桩(不缓存)
	 * @param id
	 * @return
	 */
	public CsChargePile GetCsChargePileById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChargePile) this.getSqlMapClientTemplate().queryForObject("getCsChargePileById",params);
	}
	

	/**
	 * 保存充电桩
	 * @param csChargePile
	 */
	public CsChargePile saveCsChargePile(CsChargePile csChargePile)
	{
		csChargePile = custom(csChargePile);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChargePile", csChargePile));
		csChargePile = this.GetCsChargePileById(id);
		MemCache.setValue(CsChargePile.class,id, csChargePile.getKeyValue());
		return Trigger.on(CsChargePile.class,Trigger.SAVE,csChargePile);	
	}

	

	/**
	 * 更新充电桩
	 * @param csChargePile
	 */
	public void updateCsChargePile(CsChargePile csChargePile)
	{
		csChargePile = custom(csChargePile);
		this.getSqlMapClientTemplate().update("updateCsChargePile", csChargePile);
		csChargePile = this.GetCsChargePileById(csChargePile.getCsCpId());
		if(csChargePile!=null){
			MemCache.setValue(CsChargePile.class,csChargePile.getCsCpId(), csChargePile.getKeyValue());
		}
		Trigger.on(CsChargePile.class,Trigger.UPDATE,csChargePile);
	}
	/**
	 * 更新充电桩非空字段
	 * @param csChargePile
	 */
	public void updateCsChargePile$NotNull(CsChargePile csChargePile)
	{
		csChargePile = custom(csChargePile);
		this.getSqlMapClientTemplate().update("updateCsChargePile$NotNull", csChargePile);
		csChargePile = this.GetCsChargePileById(csChargePile.getCsCpId());
		if(csChargePile!=null){
			MemCache.setValue(CsChargePile.class,csChargePile.getCsCpId(), csChargePile.getKeyValue());
		}
		Trigger.on(CsChargePile.class,Trigger.UPDATE,csChargePile);
	}

	/**
	 * 根据ID删除一个充电桩
	 * @param id
	 */
	public void deleteCsChargePileById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChargePile.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChargePileById", params);
		MemCache.deleteValue(CsChargePile.class, id);
	}
	/**
	 * 根据ID逻辑删除充电桩 
	 * @param id
	 * @return
	 */
	public void removeCsChargePileById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChargePile.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChargePileById", params);
		MemCache.deleteValue(CsChargePile.class, id);
	}
	/**
	 * 根据条件更新充电桩
	 * @param values
	 * @param params
	 */
	public void updateCsChargePileByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChargePileCount(params)>255){
			MemCache.clearValue(CsChargePile.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChargePileEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChargePile.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChargePileBy", allParams);
		Trigger.on(CsChargePile.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除充电桩
	 * @param params
	 */
	public void deleteCsChargePileByConfirm(Map params){
		Trigger.on(CsChargePile.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChargePileCount(params)>255){
			MemCache.clearValue(CsChargePile.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChargePileEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChargePile.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChargePileBy", params);
	}
	

}