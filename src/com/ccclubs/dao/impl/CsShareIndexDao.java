package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsShareIndexDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsShareIndex;
import com.lazy3q.web.helper.$;

/**
 * 分享指数的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsShareIndexDao extends SqlMapClientDaoSupport implements ICsShareIndexDao
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
	private CsShareIndex custom(CsShareIndex params){
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
	 * 获取所有分享指数
	 * @return
	 */	
	public List<CsShareIndex> getCsShareIndexList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsShareIndex> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsShareIndexList", params);
		
		return list;
	}
	
	
	/**
	 * 获取分享指数统计
	 * @return
	 */
	public List getCsShareIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsShareIndexStats", params);
	}

	/**
	 * 获取分享指数分页
	 * @return
	 */
	public Page getCsShareIndexPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsShareIndexCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsShareIndexPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取分享指数数量
	 * @return
	 */	
	public Long getCsShareIndexCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsShareIndexEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取分享指数自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsShareIndexEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsShareIndexEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取分享指数
	 * @param params
	 * @return
	 */
	public CsShareIndex getCsShareIndex(Map params)
	{		
		params = custom(params);
			
		List<CsShareIndex> list=this.getCsShareIndexList(params,1);
		CsShareIndex csShareIndex = list.isEmpty()?null:list.get(0);
		
		return csShareIndex;
	}
		
	/**
	 * 根据ID取分享指数
	 * @param id
	 * @return
	 */
	public CsShareIndex getCsShareIndexById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsShareIndex csShareIndex = null;
		if(csShareIndex!=null)
			return csShareIndex;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csShareIndex = (CsShareIndex) this.getSqlMapClientTemplate().queryForObject("getCsShareIndexById",params);
		if(csShareIndex!=null)
			MemCache.setValue(CsShareIndex.class,id, csShareIndex.getKeyValue());
		return csShareIndex;
	}
	
	/**
	 * 根据ID取分享指数(不缓存)
	 * @param id
	 * @return
	 */
	public CsShareIndex GetCsShareIndexById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsShareIndex) this.getSqlMapClientTemplate().queryForObject("getCsShareIndexById",params);
	}
	

	/**
	 * 保存分享指数
	 * @param csShareIndex
	 */
	public CsShareIndex saveCsShareIndex(CsShareIndex csShareIndex)
	{
		csShareIndex = custom(csShareIndex);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsShareIndex", csShareIndex));
		csShareIndex = this.GetCsShareIndexById(id);
		MemCache.setValue(CsShareIndex.class,id, csShareIndex.getKeyValue());
		return Trigger.on(CsShareIndex.class,Trigger.SAVE,csShareIndex);	
	}

	

	/**
	 * 更新分享指数
	 * @param csShareIndex
	 */
	public void updateCsShareIndex(CsShareIndex csShareIndex)
	{
		csShareIndex = custom(csShareIndex);
		this.getSqlMapClientTemplate().update("updateCsShareIndex", csShareIndex);
		csShareIndex = this.GetCsShareIndexById(csShareIndex.getCssiId());
		if(csShareIndex!=null){
			MemCache.setValue(CsShareIndex.class,csShareIndex.getCssiId(), csShareIndex.getKeyValue());
		}
		Trigger.on(CsShareIndex.class,Trigger.UPDATE,csShareIndex);
	}
	/**
	 * 更新分享指数非空字段
	 * @param csShareIndex
	 */
	public void updateCsShareIndex$NotNull(CsShareIndex csShareIndex)
	{
		csShareIndex = custom(csShareIndex);
		this.getSqlMapClientTemplate().update("updateCsShareIndex$NotNull", csShareIndex);
		csShareIndex = this.GetCsShareIndexById(csShareIndex.getCssiId());
		if(csShareIndex!=null){
			MemCache.setValue(CsShareIndex.class,csShareIndex.getCssiId(), csShareIndex.getKeyValue());
		}
		Trigger.on(CsShareIndex.class,Trigger.UPDATE,csShareIndex);
	}

	/**
	 * 根据ID删除一个分享指数
	 * @param id
	 */
	public void deleteCsShareIndexById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsShareIndex.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsShareIndexById", params);
		MemCache.deleteValue(CsShareIndex.class, id);
	}
	/**
	 * 根据ID逻辑删除分享指数 
	 * @param id
	 * @return
	 */
	public void removeCsShareIndexById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsShareIndex.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsShareIndexById", params);
		MemCache.deleteValue(CsShareIndex.class, id);
	}
	/**
	 * 根据条件更新分享指数
	 * @param values
	 * @param params
	 */
	public void updateCsShareIndexByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsShareIndexCount(params)>255){
			MemCache.clearValue(CsShareIndex.class);
		}else{
			params.put("eval","cssi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsShareIndexEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsShareIndex.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsShareIndexBy", allParams);
		Trigger.on(CsShareIndex.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除分享指数
	 * @param params
	 */
	public void deleteCsShareIndexByConfirm(Map params){
		Trigger.on(CsShareIndex.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsShareIndexCount(params)>255){
			MemCache.clearValue(CsShareIndex.class);
		}else{
			params.put("eval","cssi_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsShareIndexEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsShareIndex.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsShareIndexBy", params);
	}
	

}