package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsWxContentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxContent;
import com.lazy3q.web.helper.$;

/**
 * 微信素材的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsWxContentDao extends SqlMapClientDaoSupport implements ICsWxContentDao
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
	private CsWxContent custom(CsWxContent params){
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
			"cswcTitle"			
			,"cswcContnet"			
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
	 * 获取所有微信素材
	 * @return
	 */	
	public List<CsWxContent> getCsWxContentList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsWxContent> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","cswcAddTime","cswcAddTimeStart","cswcAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsWxContent.M.class, md5);
			List caches = MemCache.getObject(CsWxContent.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsWxContent cacheObject = MemCache.getObject(CsWxContent.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsWxContent.M.class, md5);
							MemCache.deleteValue(CsWxContent.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsWxContent.M.class, md5);
					MemCache.deleteValue(CsWxContent.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsWxContentList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsWxContent csWxContent:list){
				caches.add(csWxContent.getCswcId());
				MemCache.setObject(CsWxContent.class,csWxContent.getCswcId(), csWxContent);
			}
			MemCache.setObject(CsWxContent.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsWxContent.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取微信素材统计
	 * @return
	 */
	public List getCsWxContentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsWxContentStats", params);
	}

	/**
	 * 获取微信素材分页
	 * @return
	 */
	public Page getCsWxContentPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsWxContentCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsWxContentPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取微信素材数量
	 * @return
	 */	
	public Long getCsWxContentCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxContentEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取微信素材自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsWxContentEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxContentEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取微信素材
	 * @param params
	 * @return
	 */
	public CsWxContent getCsWxContent(Map params)
	{		
		params = custom(params);
			
		List<CsWxContent> list=this.getCsWxContentList(params,1);
		CsWxContent csWxContent = list.isEmpty()?null:list.get(0);
		
		return csWxContent;
	}
		
	/**
	 * 根据ID取微信素材
	 * @param id
	 * @return
	 */
	public CsWxContent getCsWxContentById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsWxContent csWxContent = MemCache.getObject(CsWxContent.class, id);
		if(csWxContent!=null)
			return csWxContent;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csWxContent = (CsWxContent) this.getSqlMapClientTemplate().queryForObject("getCsWxContentById",params);
		if(csWxContent!=null)
			MemCache.setValue(CsWxContent.class,id, csWxContent.getKeyValue());
		if(csWxContent!=null)
			MemCache.setObject(CsWxContent.class,id, csWxContent);
		return csWxContent;
	}
	
	/**
	 * 根据ID取微信素材(不缓存)
	 * @param id
	 * @return
	 */
	public CsWxContent GetCsWxContentById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsWxContent) this.getSqlMapClientTemplate().queryForObject("getCsWxContentById",params);
	}
	

	/**
	 * 保存微信素材
	 * @param csWxContent
	 */
	public CsWxContent saveCsWxContent(CsWxContent csWxContent)
	{
		csWxContent = custom(csWxContent);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsWxContent", csWxContent));
		csWxContent = this.GetCsWxContentById(id);
		MemCache.setValue(CsWxContent.class,id, csWxContent.getKeyValue());
		MemCache.setObject(CsWxContent.class,id,csWxContent);
		MemCache.clearObject(CsWxContent.M.class);
		MemCache.clearValue(CsWxContent.M.class);
		return Trigger.on(CsWxContent.class,Trigger.SAVE,csWxContent);	
	}

	

	/**
	 * 更新微信素材
	 * @param csWxContent
	 */
	public void updateCsWxContent(CsWxContent csWxContent)
	{
		csWxContent = custom(csWxContent);
		this.getSqlMapClientTemplate().update("updateCsWxContent", csWxContent);
		csWxContent = this.GetCsWxContentById(csWxContent.getCswcId());
		if(csWxContent!=null){
			MemCache.setValue(CsWxContent.class,csWxContent.getCswcId(), csWxContent.getKeyValue());
			MemCache.setObject(CsWxContent.class,csWxContent.getCswcId(),csWxContent);
		}
		MemCache.clearObject(CsWxContent.M.class);
		MemCache.clearValue(CsWxContent.M.class);
		Trigger.on(CsWxContent.class,Trigger.UPDATE,csWxContent);
	}
	/**
	 * 更新微信素材非空字段
	 * @param csWxContent
	 */
	public void updateCsWxContent$NotNull(CsWxContent csWxContent)
	{
		csWxContent = custom(csWxContent);
		this.getSqlMapClientTemplate().update("updateCsWxContent$NotNull", csWxContent);
		csWxContent = this.GetCsWxContentById(csWxContent.getCswcId());
		if(csWxContent!=null){
			MemCache.setValue(CsWxContent.class,csWxContent.getCswcId(), csWxContent.getKeyValue());
			MemCache.setObject(CsWxContent.class,csWxContent.getCswcId(),csWxContent);
		}
		MemCache.clearObject(CsWxContent.M.class);
		MemCache.clearValue(CsWxContent.M.class);
		Trigger.on(CsWxContent.class,Trigger.UPDATE,csWxContent);
	}

	/**
	 * 根据ID删除一个微信素材
	 * @param id
	 */
	public void deleteCsWxContentById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxContent.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsWxContentById", params);
		MemCache.deleteValue(CsWxContent.class, id);
		MemCache.deleteObject(CsWxContent.class, id);
		MemCache.clearObject(CsWxContent.M.class);
		MemCache.clearValue(CsWxContent.M.class);
	}
	/**
	 * 根据条件更新微信素材
	 * @param values
	 * @param params
	 */
	public void updateCsWxContentByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxContentCount(params)>255){
			MemCache.clearValue(CsWxContent.class);
			MemCache.clearObject(CsWxContent.class);
		}else{
			params.put("eval","cswc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxContentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxContent.class, map.get("eval"));
				MemCache.deleteObject(CsWxContent.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsWxContent.M.class);
		MemCache.clearValue(CsWxContent.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsWxContentBy", allParams);
		Trigger.on(CsWxContent.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除微信素材
	 * @param params
	 */
	public void deleteCsWxContentByConfirm(Map params){
		Trigger.on(CsWxContent.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxContentCount(params)>255){
			MemCache.clearValue(CsWxContent.class);
			MemCache.clearObject(CsWxContent.class);
		}else{
			params.put("eval","cswc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxContentEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxContent.class, map.get("eval"));
				MemCache.deleteObject(CsWxContent.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsWxContent.M.class);
		MemCache.clearValue(CsWxContent.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsWxContentBy", params);
	}
	

}