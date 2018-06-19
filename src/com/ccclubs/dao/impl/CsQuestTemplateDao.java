package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsQuestTemplateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsQuestTemplate;
import com.lazy3q.web.helper.$;

/**
 * 问卷模板的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsQuestTemplateDao extends SqlMapClientDaoSupport implements ICsQuestTemplateDao
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
	private CsQuestTemplate custom(CsQuestTemplate params){
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
			"csqtContent"			
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
	 * 获取所有问卷模板
	 * @return
	 */	
	public List<CsQuestTemplate> getCsQuestTemplateList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsQuestTemplate> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsQuestTemplateList", params);
		
		return list;
	}
	
	
	/**
	 * 获取问卷模板统计
	 * @return
	 */
	public List getCsQuestTemplateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsQuestTemplateStats", params);
	}

	/**
	 * 获取问卷模板分页
	 * @return
	 */
	public Page getCsQuestTemplatePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsQuestTemplateCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsQuestTemplatePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取问卷模板数量
	 * @return
	 */	
	public Long getCsQuestTemplateCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsQuestTemplateEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取问卷模板自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsQuestTemplateEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsQuestTemplateEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取问卷模板
	 * @param params
	 * @return
	 */
	public CsQuestTemplate getCsQuestTemplate(Map params)
	{		
		params = custom(params);
			
		List<CsQuestTemplate> list=this.getCsQuestTemplateList(params,1);
		CsQuestTemplate csQuestTemplate = list.isEmpty()?null:list.get(0);
		
		return csQuestTemplate;
	}
		
	/**
	 * 根据ID取问卷模板
	 * @param id
	 * @return
	 */
	public CsQuestTemplate getCsQuestTemplateById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsQuestTemplate csQuestTemplate = null;
		if(csQuestTemplate!=null)
			return csQuestTemplate;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csQuestTemplate = (CsQuestTemplate) this.getSqlMapClientTemplate().queryForObject("getCsQuestTemplateById",params);
		if(csQuestTemplate!=null)
			MemCache.setValue(CsQuestTemplate.class,id, csQuestTemplate.getKeyValue());
		return csQuestTemplate;
	}
	
	/**
	 * 根据ID取问卷模板(不缓存)
	 * @param id
	 * @return
	 */
	public CsQuestTemplate GetCsQuestTemplateById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsQuestTemplate) this.getSqlMapClientTemplate().queryForObject("getCsQuestTemplateById",params);
	}
	

	/**
	 * 保存问卷模板
	 * @param csQuestTemplate
	 */
	public CsQuestTemplate saveCsQuestTemplate(CsQuestTemplate csQuestTemplate)
	{
		csQuestTemplate = custom(csQuestTemplate);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsQuestTemplate", csQuestTemplate));
		csQuestTemplate = this.GetCsQuestTemplateById(id);
		MemCache.setValue(CsQuestTemplate.class,id, csQuestTemplate.getKeyValue());
		return Trigger.on(CsQuestTemplate.class,Trigger.SAVE,csQuestTemplate);	
	}

	

	/**
	 * 更新问卷模板
	 * @param csQuestTemplate
	 */
	public void updateCsQuestTemplate(CsQuestTemplate csQuestTemplate)
	{
		csQuestTemplate = custom(csQuestTemplate);
		this.getSqlMapClientTemplate().update("updateCsQuestTemplate", csQuestTemplate);
		csQuestTemplate = this.GetCsQuestTemplateById(csQuestTemplate.getCsqtId());
		if(csQuestTemplate!=null){
			MemCache.setValue(CsQuestTemplate.class,csQuestTemplate.getCsqtId(), csQuestTemplate.getKeyValue());
		}
		Trigger.on(CsQuestTemplate.class,Trigger.UPDATE,csQuestTemplate);
	}
	/**
	 * 更新问卷模板非空字段
	 * @param csQuestTemplate
	 */
	public void updateCsQuestTemplate$NotNull(CsQuestTemplate csQuestTemplate)
	{
		csQuestTemplate = custom(csQuestTemplate);
		this.getSqlMapClientTemplate().update("updateCsQuestTemplate$NotNull", csQuestTemplate);
		csQuestTemplate = this.GetCsQuestTemplateById(csQuestTemplate.getCsqtId());
		if(csQuestTemplate!=null){
			MemCache.setValue(CsQuestTemplate.class,csQuestTemplate.getCsqtId(), csQuestTemplate.getKeyValue());
		}
		Trigger.on(CsQuestTemplate.class,Trigger.UPDATE,csQuestTemplate);
	}

	/**
	 * 根据ID删除一个问卷模板
	 * @param id
	 */
	public void deleteCsQuestTemplateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsQuestTemplate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsQuestTemplateById", params);
		MemCache.deleteValue(CsQuestTemplate.class, id);
	}
	/**
	 * 根据ID逻辑删除问卷模板 
	 * @param id
	 * @return
	 */
	public void removeCsQuestTemplateById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsQuestTemplate.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsQuestTemplateById", params);
		MemCache.deleteValue(CsQuestTemplate.class, id);
	}
	/**
	 * 根据条件更新问卷模板
	 * @param values
	 * @param params
	 */
	public void updateCsQuestTemplateByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsQuestTemplateCount(params)>255){
			MemCache.clearValue(CsQuestTemplate.class);
		}else{
			params.put("eval","csqt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsQuestTemplateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsQuestTemplate.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsQuestTemplateBy", allParams);
		Trigger.on(CsQuestTemplate.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除问卷模板
	 * @param params
	 */
	public void deleteCsQuestTemplateByConfirm(Map params){
		Trigger.on(CsQuestTemplate.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsQuestTemplateCount(params)>255){
			MemCache.clearValue(CsQuestTemplate.class);
		}else{
			params.put("eval","csqt_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsQuestTemplateEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsQuestTemplate.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsQuestTemplateBy", params);
	}
	

}