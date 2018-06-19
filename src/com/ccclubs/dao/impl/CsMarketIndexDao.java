package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMarketIndexDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketIndex;
import com.lazy3q.web.helper.$;

/**
 * 营销指标的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMarketIndexDao extends SqlMapClientDaoSupport implements ICsMarketIndexDao
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
	private CsMarketIndex custom(CsMarketIndex params){
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
			"csmiName"			
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
	 * 获取所有营销指标
	 * @return
	 */	
	public List<CsMarketIndex> getCsMarketIndexList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMarketIndex> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMarketIndexList", params);
		
		return list;
	}
	
	
	/**
	 * 获取营销指标统计
	 * @return
	 */
	public List getCsMarketIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMarketIndexStats", params);
	}

	/**
	 * 获取营销指标分页
	 * @return
	 */
	public Page getCsMarketIndexPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMarketIndexCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMarketIndexPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取营销指标数量
	 * @return
	 */	
	public Long getCsMarketIndexCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMarketIndexEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取营销指标自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMarketIndexEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMarketIndexEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取营销指标
	 * @param params
	 * @return
	 */
	public CsMarketIndex getCsMarketIndex(Map params)
	{		
		params = custom(params);
			
		List<CsMarketIndex> list=this.getCsMarketIndexList(params,1);
		CsMarketIndex csMarketIndex = list.isEmpty()?null:list.get(0);
		
		return csMarketIndex;
	}
		
	/**
	 * 根据ID取营销指标
	 * @param id
	 * @return
	 */
	public CsMarketIndex getCsMarketIndexById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMarketIndex csMarketIndex = null;
		if(csMarketIndex!=null)
			return csMarketIndex;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMarketIndex = (CsMarketIndex) this.getSqlMapClientTemplate().queryForObject("getCsMarketIndexById",params);
		if(csMarketIndex!=null)
			MemCache.setValue(CsMarketIndex.class,id, csMarketIndex.getKeyValue());
		return csMarketIndex;
	}
	
	/**
	 * 根据ID取营销指标(不缓存)
	 * @param id
	 * @return
	 */
	public CsMarketIndex GetCsMarketIndexById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMarketIndex) this.getSqlMapClientTemplate().queryForObject("getCsMarketIndexById",params);
	}
	

	/**
	 * 保存营销指标
	 * @param csMarketIndex
	 */
	public CsMarketIndex saveCsMarketIndex(CsMarketIndex csMarketIndex)
	{
		csMarketIndex = custom(csMarketIndex);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMarketIndex", csMarketIndex));
		csMarketIndex = this.GetCsMarketIndexById(id);
		MemCache.setValue(CsMarketIndex.class,id, csMarketIndex.getKeyValue());
		return Trigger.on(CsMarketIndex.class,Trigger.SAVE,csMarketIndex);	
	}

	

	/**
	 * 更新营销指标
	 * @param csMarketIndex
	 */
	public void updateCsMarketIndex(CsMarketIndex csMarketIndex)
	{
		csMarketIndex = custom(csMarketIndex);
		this.getSqlMapClientTemplate().update("updateCsMarketIndex", csMarketIndex);
		csMarketIndex = this.GetCsMarketIndexById(csMarketIndex.getCsmiId());
		if(csMarketIndex!=null){
			MemCache.setValue(CsMarketIndex.class,csMarketIndex.getCsmiId(), csMarketIndex.getKeyValue());
		}
		Trigger.on(CsMarketIndex.class,Trigger.UPDATE,csMarketIndex);
	}
	/**
	 * 更新营销指标非空字段
	 * @param csMarketIndex
	 */
	public void updateCsMarketIndex$NotNull(CsMarketIndex csMarketIndex)
	{
		csMarketIndex = custom(csMarketIndex);
		this.getSqlMapClientTemplate().update("updateCsMarketIndex$NotNull", csMarketIndex);
		csMarketIndex = this.GetCsMarketIndexById(csMarketIndex.getCsmiId());
		if(csMarketIndex!=null){
			MemCache.setValue(CsMarketIndex.class,csMarketIndex.getCsmiId(), csMarketIndex.getKeyValue());
		}
		Trigger.on(CsMarketIndex.class,Trigger.UPDATE,csMarketIndex);
	}

	/**
	 * 根据ID删除一个营销指标
	 * @param id
	 */
	public void deleteCsMarketIndexById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMarketIndex.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMarketIndexById", params);
		MemCache.deleteValue(CsMarketIndex.class, id);
	}
	/**
	 * 根据ID逻辑删除营销指标 
	 * @param id
	 * @return
	 */
	public void removeCsMarketIndexById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMarketIndex.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMarketIndexById", params);
		MemCache.deleteValue(CsMarketIndex.class, id);
	}
	/**
	 * 根据条件更新营销指标
	 * @param values
	 * @param params
	 */
	public void updateCsMarketIndexByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMarketIndexCount(params)>255){
			MemCache.clearValue(CsMarketIndex.class);
		}else{
			params.put("eval","csmi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMarketIndexEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMarketIndex.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMarketIndexBy", allParams);
		Trigger.on(CsMarketIndex.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除营销指标
	 * @param params
	 */
	public void deleteCsMarketIndexByConfirm(Map params){
		Trigger.on(CsMarketIndex.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMarketIndexCount(params)>255){
			MemCache.clearValue(CsMarketIndex.class);
		}else{
			params.put("eval","csmi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMarketIndexEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMarketIndex.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMarketIndexBy", params);
	}
	

}