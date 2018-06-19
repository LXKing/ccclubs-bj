package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChannelIndexDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChannelIndex;
import com.lazy3q.web.helper.$;

/**
 * 渠道指标的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChannelIndexDao extends SqlMapClientDaoSupport implements ICsChannelIndexDao
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
	private CsChannelIndex custom(CsChannelIndex params){
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
			"csciName"			
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
	 * 获取所有渠道指标
	 * @return
	 */	
	public List<CsChannelIndex> getCsChannelIndexList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChannelIndex> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChannelIndexList", params);
		
		return list;
	}
	
	
	/**
	 * 获取渠道指标统计
	 * @return
	 */
	public List getCsChannelIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChannelIndexStats", params);
	}

	/**
	 * 获取渠道指标分页
	 * @return
	 */
	public Page getCsChannelIndexPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChannelIndexCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChannelIndexPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取渠道指标数量
	 * @return
	 */	
	public Long getCsChannelIndexCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChannelIndexEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取渠道指标自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChannelIndexEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChannelIndexEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取渠道指标
	 * @param params
	 * @return
	 */
	public CsChannelIndex getCsChannelIndex(Map params)
	{		
		params = custom(params);
			
		List<CsChannelIndex> list=this.getCsChannelIndexList(params,1);
		CsChannelIndex csChannelIndex = list.isEmpty()?null:list.get(0);
		
		return csChannelIndex;
	}
		
	/**
	 * 根据ID取渠道指标
	 * @param id
	 * @return
	 */
	public CsChannelIndex getCsChannelIndexById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChannelIndex csChannelIndex = null;
		if(csChannelIndex!=null)
			return csChannelIndex;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChannelIndex = (CsChannelIndex) this.getSqlMapClientTemplate().queryForObject("getCsChannelIndexById",params);
		if(csChannelIndex!=null)
			MemCache.setValue(CsChannelIndex.class,id, csChannelIndex.getKeyValue());
		return csChannelIndex;
	}
	
	/**
	 * 根据ID取渠道指标(不缓存)
	 * @param id
	 * @return
	 */
	public CsChannelIndex GetCsChannelIndexById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChannelIndex) this.getSqlMapClientTemplate().queryForObject("getCsChannelIndexById",params);
	}
	

	/**
	 * 保存渠道指标
	 * @param csChannelIndex
	 */
	public CsChannelIndex saveCsChannelIndex(CsChannelIndex csChannelIndex)
	{
		csChannelIndex = custom(csChannelIndex);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChannelIndex", csChannelIndex));
		csChannelIndex = this.GetCsChannelIndexById(id);
		MemCache.setValue(CsChannelIndex.class,id, csChannelIndex.getKeyValue());
		return Trigger.on(CsChannelIndex.class,Trigger.SAVE,csChannelIndex);	
	}

	

	/**
	 * 更新渠道指标
	 * @param csChannelIndex
	 */
	public void updateCsChannelIndex(CsChannelIndex csChannelIndex)
	{
		csChannelIndex = custom(csChannelIndex);
		this.getSqlMapClientTemplate().update("updateCsChannelIndex", csChannelIndex);
		csChannelIndex = this.GetCsChannelIndexById(csChannelIndex.getCsciId());
		if(csChannelIndex!=null){
			MemCache.setValue(CsChannelIndex.class,csChannelIndex.getCsciId(), csChannelIndex.getKeyValue());
		}
		Trigger.on(CsChannelIndex.class,Trigger.UPDATE,csChannelIndex);
	}
	/**
	 * 更新渠道指标非空字段
	 * @param csChannelIndex
	 */
	public void updateCsChannelIndex$NotNull(CsChannelIndex csChannelIndex)
	{
		csChannelIndex = custom(csChannelIndex);
		this.getSqlMapClientTemplate().update("updateCsChannelIndex$NotNull", csChannelIndex);
		csChannelIndex = this.GetCsChannelIndexById(csChannelIndex.getCsciId());
		if(csChannelIndex!=null){
			MemCache.setValue(CsChannelIndex.class,csChannelIndex.getCsciId(), csChannelIndex.getKeyValue());
		}
		Trigger.on(CsChannelIndex.class,Trigger.UPDATE,csChannelIndex);
	}

	/**
	 * 根据ID删除一个渠道指标
	 * @param id
	 */
	public void deleteCsChannelIndexById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChannelIndex.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChannelIndexById", params);
		MemCache.deleteValue(CsChannelIndex.class, id);
	}
	/**
	 * 根据ID逻辑删除渠道指标 
	 * @param id
	 * @return
	 */
	public void removeCsChannelIndexById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChannelIndex.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChannelIndexById", params);
		MemCache.deleteValue(CsChannelIndex.class, id);
	}
	/**
	 * 根据条件更新渠道指标
	 * @param values
	 * @param params
	 */
	public void updateCsChannelIndexByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChannelIndexCount(params)>255){
			MemCache.clearValue(CsChannelIndex.class);
		}else{
			params.put("eval","csci_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChannelIndexEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChannelIndex.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChannelIndexBy", allParams);
		Trigger.on(CsChannelIndex.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除渠道指标
	 * @param params
	 */
	public void deleteCsChannelIndexByConfirm(Map params){
		Trigger.on(CsChannelIndex.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChannelIndexCount(params)>255){
			MemCache.clearValue(CsChannelIndex.class);
		}else{
			params.put("eval","csci_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChannelIndexEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChannelIndex.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChannelIndexBy", params);
	}
	

}