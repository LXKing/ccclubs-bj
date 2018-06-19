package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSmsTemplateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSmsTemplate;
import com.lazy3q.web.helper.$;

/**
 * 短信模版的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSmsTemplateDao extends SqlMapClientDaoSupport implements ICsSmsTemplateDao
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
	private CsSmsTemplate custom(CsSmsTemplate params){
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
			"csstContent"			
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
	 * 获取所有短信模版
	 * @return
	 */	
	public List<CsSmsTemplate> getCsSmsTemplateList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSmsTemplate> list = new ArrayList();
		Boolean bInReject = false;
		//如果查询参数中有下列字段中任意之一，则不作缓存
		for(String reject:new String[]{"In-Reject","csstUpdateTime","csstUpdateTimeStart","csstUpdateTimeEnd","csstAddTime","csstAddTimeStart","csstAddTimeEnd"}){
			if(params.get(reject)!=null){
				bInReject = true;
				break;
			}
		}
		if(!bInReject){
			String sParam = MemCache.getValue(CsSmsTemplate.M.class, md5);
			List caches = MemCache.getObject(CsSmsTemplate.M.class, md5);
			if(caches!=null){
				if($.equals(sParam, json)){
					for(Object item:caches){
						CsSmsTemplate cacheObject = MemCache.getObject(CsSmsTemplate.class, Long.parseLong(""+item));
						if(cacheObject==null){//如果缓存对象为空，表示对象有修改过，则放弃当前列表缓存
							list = new ArrayList();
							MemCache.deleteObject(CsSmsTemplate.M.class, md5);
							MemCache.deleteValue(CsSmsTemplate.M.class, md5);
							break;
						}else{
							list.add(cacheObject);
						}
					}
					return list;
				}else{
					$.trace("缓存冲突",sParam,json);
					MemCache.deleteObject(CsSmsTemplate.M.class, md5);
					MemCache.deleteValue(CsSmsTemplate.M.class, md5);
				}
			}
		}
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSmsTemplateList", params);
		if(!bInReject && size<1024){
			List caches = new ArrayList();
			for(CsSmsTemplate csSmsTemplate:list){
				caches.add(csSmsTemplate.getCsstId());
				MemCache.setObject(CsSmsTemplate.class,csSmsTemplate.getCsstId(), csSmsTemplate);
			}
			MemCache.setObject(CsSmsTemplate.M.class, md5, caches, MemCache.defaultSeconds());
			MemCache.setValue(CsSmsTemplate.M.class, md5, json, MemCache.defaultSeconds());
		}		
		
		return list;
	}
	
	
	/**
	 * 获取短信模版统计
	 * @return
	 */
	public List getCsSmsTemplateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSmsTemplateStats", params);
	}

	/**
	 * 获取短信模版分页
	 * @return
	 */
	public Page getCsSmsTemplatePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSmsTemplateCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSmsTemplatePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取短信模版数量
	 * @return
	 */	
	public Long getCsSmsTemplateCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSmsTemplateEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取短信模版自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSmsTemplateEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSmsTemplateEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取短信模版
	 * @param params
	 * @return
	 */
	public CsSmsTemplate getCsSmsTemplate(Map params)
	{		
		params = custom(params);
			
		List<CsSmsTemplate> list=this.getCsSmsTemplateList(params,1);
		CsSmsTemplate csSmsTemplate = list.isEmpty()?null:list.get(0);
		
		return csSmsTemplate;
	}
		
	/**
	 * 根据ID取短信模版
	 * @param id
	 * @return
	 */
	public CsSmsTemplate getCsSmsTemplateById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSmsTemplate csSmsTemplate = MemCache.getObject(CsSmsTemplate.class, id);
		if(csSmsTemplate!=null)
			return csSmsTemplate;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSmsTemplate = (CsSmsTemplate) this.getSqlMapClientTemplate().queryForObject("getCsSmsTemplateById",params);
		if(csSmsTemplate!=null)
			MemCache.setValue(CsSmsTemplate.class,id, csSmsTemplate.getKeyValue());
		if(csSmsTemplate!=null)
			MemCache.setObject(CsSmsTemplate.class,id, csSmsTemplate);
		return csSmsTemplate;
	}
	
	/**
	 * 根据ID取短信模版(不缓存)
	 * @param id
	 * @return
	 */
	public CsSmsTemplate GetCsSmsTemplateById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSmsTemplate) this.getSqlMapClientTemplate().queryForObject("getCsSmsTemplateById",params);
	}
	

	/**
	 * 保存短信模版
	 * @param csSmsTemplate
	 */
	public CsSmsTemplate saveCsSmsTemplate(CsSmsTemplate csSmsTemplate)
	{
		csSmsTemplate = custom(csSmsTemplate);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSmsTemplate", csSmsTemplate));
		csSmsTemplate = this.GetCsSmsTemplateById(id);
		MemCache.setValue(CsSmsTemplate.class,id, csSmsTemplate.getKeyValue());
		MemCache.setObject(CsSmsTemplate.class,id,csSmsTemplate);
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
		return Trigger.on(CsSmsTemplate.class,Trigger.SAVE,csSmsTemplate);	
	}

	

	/**
	 * 更新短信模版
	 * @param csSmsTemplate
	 */
	public void updateCsSmsTemplate(CsSmsTemplate csSmsTemplate)
	{
		csSmsTemplate = custom(csSmsTemplate);
		this.getSqlMapClientTemplate().update("updateCsSmsTemplate", csSmsTemplate);
		csSmsTemplate = this.GetCsSmsTemplateById(csSmsTemplate.getCsstId());
		if(csSmsTemplate!=null){
			MemCache.setValue(CsSmsTemplate.class,csSmsTemplate.getCsstId(), csSmsTemplate.getKeyValue());
			MemCache.setObject(CsSmsTemplate.class,csSmsTemplate.getCsstId(),csSmsTemplate);
		}
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
		Trigger.on(CsSmsTemplate.class,Trigger.UPDATE,csSmsTemplate);
	}
	/**
	 * 更新短信模版非空字段
	 * @param csSmsTemplate
	 */
	public void updateCsSmsTemplate$NotNull(CsSmsTemplate csSmsTemplate)
	{
		csSmsTemplate = custom(csSmsTemplate);
		this.getSqlMapClientTemplate().update("updateCsSmsTemplate$NotNull", csSmsTemplate);
		csSmsTemplate = this.GetCsSmsTemplateById(csSmsTemplate.getCsstId());
		if(csSmsTemplate!=null){
			MemCache.setValue(CsSmsTemplate.class,csSmsTemplate.getCsstId(), csSmsTemplate.getKeyValue());
			MemCache.setObject(CsSmsTemplate.class,csSmsTemplate.getCsstId(),csSmsTemplate);
		}
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
		Trigger.on(CsSmsTemplate.class,Trigger.UPDATE,csSmsTemplate);
	}

	/**
	 * 根据ID删除一个短信模版
	 * @param id
	 */
	public void deleteCsSmsTemplateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSmsTemplate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSmsTemplateById", params);
		MemCache.deleteValue(CsSmsTemplate.class, id);
		MemCache.deleteObject(CsSmsTemplate.class, id);
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
	}
	/**
	 * 根据ID逻辑删除短信模版 
	 * @param id
	 * @return
	 */
	public void removeCsSmsTemplateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSmsTemplate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSmsTemplateById", params);
		MemCache.deleteValue(CsSmsTemplate.class, id);
		MemCache.deleteObject(CsSmsTemplate.class, id);
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
	}
	/**
	 * 根据条件更新短信模版
	 * @param values
	 * @param params
	 */
	public void updateCsSmsTemplateByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSmsTemplateCount(params)>255){
			MemCache.clearValue(CsSmsTemplate.class);
			MemCache.clearObject(CsSmsTemplate.class);
		}else{
			params.put("eval","csst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSmsTemplateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSmsTemplate.class, map.get("eval"));
				MemCache.deleteObject(CsSmsTemplate.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSmsTemplateBy", allParams);
		Trigger.on(CsSmsTemplate.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除短信模版
	 * @param params
	 */
	public void deleteCsSmsTemplateByConfirm(Map params){
		Trigger.on(CsSmsTemplate.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSmsTemplateCount(params)>255){
			MemCache.clearValue(CsSmsTemplate.class);
			MemCache.clearObject(CsSmsTemplate.class);
		}else{
			params.put("eval","csst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSmsTemplateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSmsTemplate.class, map.get("eval"));
				MemCache.deleteObject(CsSmsTemplate.class, map.get("eval"));
			}
		}
		MemCache.clearObject(CsSmsTemplate.M.class);
		MemCache.clearValue(CsSmsTemplate.M.class);
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSmsTemplateBy", params);
	}
	

}