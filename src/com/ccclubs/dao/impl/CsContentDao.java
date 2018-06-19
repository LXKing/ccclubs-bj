package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsContentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsContent;
import com.lazy3q.web.helper.$;

/**
 * 静态内容的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsContentDao extends SqlMapClientDaoSupport implements ICsContentDao
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
	private CsContent custom(CsContent params){
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
			,"cscIntroduction"			
			,"cscKeywords"			
			,"cscContent"			
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
	 * 获取所有静态内容
	 * @return
	 */	
	public List<CsContent> getCsContentList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsContent> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsContentList", params);
		
		return list;
	}
	
	
	/**
	 * 获取静态内容统计
	 * @return
	 */
	public List getCsContentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsContentStats", params);
	}

	/**
	 * 获取静态内容分页
	 * @return
	 */
	public Page getCsContentPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsContentCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsContentPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取静态内容数量
	 * @return
	 */	
	public Long getCsContentCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsContentEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取静态内容自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsContentEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsContentEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取静态内容
	 * @param params
	 * @return
	 */
	public CsContent getCsContent(Map params)
	{		
		params = custom(params);
			
		List<CsContent> list=this.getCsContentList(params,1);
		CsContent csContent = list.isEmpty()?null:list.get(0);
		
		return csContent;
	}
		
	/**
	 * 根据ID取静态内容
	 * @param id
	 * @return
	 */
	public CsContent getCsContentById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsContent csContent = null;
		if(csContent!=null)
			return csContent;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csContent = (CsContent) this.getSqlMapClientTemplate().queryForObject("getCsContentById",params);
		if(csContent!=null)
			MemCache.setValue(CsContent.class,id, csContent.getKeyValue());
		return csContent;
	}
	
	/**
	 * 根据ID取静态内容(不缓存)
	 * @param id
	 * @return
	 */
	public CsContent GetCsContentById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsContent) this.getSqlMapClientTemplate().queryForObject("getCsContentById",params);
	}
	

	/**
	 * 保存静态内容
	 * @param csContent
	 */
	public CsContent saveCsContent(CsContent csContent)
	{
		csContent = custom(csContent);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsContent", csContent));
		csContent = this.GetCsContentById(id);
		MemCache.setValue(CsContent.class,id, csContent.getKeyValue());
		return Trigger.on(CsContent.class,Trigger.SAVE,csContent);	
	}

	

	/**
	 * 更新静态内容
	 * @param csContent
	 */
	public void updateCsContent(CsContent csContent)
	{
		csContent = custom(csContent);
		this.getSqlMapClientTemplate().update("updateCsContent", csContent);
		csContent = this.GetCsContentById(csContent.getCscId());
		if(csContent!=null){
			MemCache.setValue(CsContent.class,csContent.getCscId(), csContent.getKeyValue());
		}
		Trigger.on(CsContent.class,Trigger.UPDATE,csContent);
	}
	/**
	 * 更新静态内容非空字段
	 * @param csContent
	 */
	public void updateCsContent$NotNull(CsContent csContent)
	{
		csContent = custom(csContent);
		this.getSqlMapClientTemplate().update("updateCsContent$NotNull", csContent);
		csContent = this.GetCsContentById(csContent.getCscId());
		if(csContent!=null){
			MemCache.setValue(CsContent.class,csContent.getCscId(), csContent.getKeyValue());
		}
		Trigger.on(CsContent.class,Trigger.UPDATE,csContent);
	}

	/**
	 * 根据ID删除一个静态内容
	 * @param id
	 */
	public void deleteCsContentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsContent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsContentById", params);
		MemCache.deleteValue(CsContent.class, id);
	}
	/**
	 * 根据ID逻辑删除静态内容 
	 * @param id
	 * @return
	 */
	public void removeCsContentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsContent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsContentById", params);
		MemCache.deleteValue(CsContent.class, id);
	}
	/**
	 * 根据条件更新静态内容
	 * @param values
	 * @param params
	 */
	public void updateCsContentByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsContentCount(params)>255){
			MemCache.clearValue(CsContent.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsContentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsContent.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsContentBy", allParams);
		Trigger.on(CsContent.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除静态内容
	 * @param params
	 */
	public void deleteCsContentByConfirm(Map params){
		Trigger.on(CsContent.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsContentCount(params)>255){
			MemCache.clearValue(CsContent.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsContentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsContent.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsContentBy", params);
	}
	

}