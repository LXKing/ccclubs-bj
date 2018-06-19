package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsSaleSurveyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleSurvey;
import com.lazy3q.web.helper.$;

/**
 * 市场调查的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsSaleSurveyDao extends SqlMapClientDaoSupport implements ICsSaleSurveyDao
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
	private CsSaleSurvey custom(CsSaleSurvey params){
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
			"csssSubject"			
			,"csssDetail"			
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
	 * 获取所有市场调查
	 * @return
	 */	
	public List<CsSaleSurvey> getCsSaleSurveyList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsSaleSurvey> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsSaleSurveyList", params);
		
		return list;
	}
	
	
	/**
	 * 获取市场调查统计
	 * @return
	 */
	public List getCsSaleSurveyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsSaleSurveyStats", params);
	}

	/**
	 * 获取市场调查分页
	 * @return
	 */
	public Page getCsSaleSurveyPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsSaleSurveyCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsSaleSurveyPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取市场调查数量
	 * @return
	 */	
	public Long getCsSaleSurveyCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSaleSurveyEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取市场调查自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsSaleSurveyEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsSaleSurveyEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取市场调查
	 * @param params
	 * @return
	 */
	public CsSaleSurvey getCsSaleSurvey(Map params)
	{		
		params = custom(params);
			
		List<CsSaleSurvey> list=this.getCsSaleSurveyList(params,1);
		CsSaleSurvey csSaleSurvey = list.isEmpty()?null:list.get(0);
		
		return csSaleSurvey;
	}
		
	/**
	 * 根据ID取市场调查
	 * @param id
	 * @return
	 */
	public CsSaleSurvey getCsSaleSurveyById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsSaleSurvey csSaleSurvey = null;
		if(csSaleSurvey!=null)
			return csSaleSurvey;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csSaleSurvey = (CsSaleSurvey) this.getSqlMapClientTemplate().queryForObject("getCsSaleSurveyById",params);
		if(csSaleSurvey!=null)
			MemCache.setValue(CsSaleSurvey.class,id, csSaleSurvey.getKeyValue());
		return csSaleSurvey;
	}
	
	/**
	 * 根据ID取市场调查(不缓存)
	 * @param id
	 * @return
	 */
	public CsSaleSurvey GetCsSaleSurveyById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsSaleSurvey) this.getSqlMapClientTemplate().queryForObject("getCsSaleSurveyById",params);
	}
	

	/**
	 * 保存市场调查
	 * @param csSaleSurvey
	 */
	public CsSaleSurvey saveCsSaleSurvey(CsSaleSurvey csSaleSurvey)
	{
		csSaleSurvey = custom(csSaleSurvey);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsSaleSurvey", csSaleSurvey));
		csSaleSurvey = this.GetCsSaleSurveyById(id);
		MemCache.setValue(CsSaleSurvey.class,id, csSaleSurvey.getKeyValue());
		return Trigger.on(CsSaleSurvey.class,Trigger.SAVE,csSaleSurvey);	
	}

	

	/**
	 * 更新市场调查
	 * @param csSaleSurvey
	 */
	public void updateCsSaleSurvey(CsSaleSurvey csSaleSurvey)
	{
		csSaleSurvey = custom(csSaleSurvey);
		this.getSqlMapClientTemplate().update("updateCsSaleSurvey", csSaleSurvey);
		csSaleSurvey = this.GetCsSaleSurveyById(csSaleSurvey.getCsssId());
		if(csSaleSurvey!=null){
			MemCache.setValue(CsSaleSurvey.class,csSaleSurvey.getCsssId(), csSaleSurvey.getKeyValue());
		}
		Trigger.on(CsSaleSurvey.class,Trigger.UPDATE,csSaleSurvey);
	}
	/**
	 * 更新市场调查非空字段
	 * @param csSaleSurvey
	 */
	public void updateCsSaleSurvey$NotNull(CsSaleSurvey csSaleSurvey)
	{
		csSaleSurvey = custom(csSaleSurvey);
		this.getSqlMapClientTemplate().update("updateCsSaleSurvey$NotNull", csSaleSurvey);
		csSaleSurvey = this.GetCsSaleSurveyById(csSaleSurvey.getCsssId());
		if(csSaleSurvey!=null){
			MemCache.setValue(CsSaleSurvey.class,csSaleSurvey.getCsssId(), csSaleSurvey.getKeyValue());
		}
		Trigger.on(CsSaleSurvey.class,Trigger.UPDATE,csSaleSurvey);
	}

	/**
	 * 根据ID删除一个市场调查
	 * @param id
	 */
	public void deleteCsSaleSurveyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSaleSurvey.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsSaleSurveyById", params);
		MemCache.deleteValue(CsSaleSurvey.class, id);
	}
	/**
	 * 根据ID逻辑删除市场调查 
	 * @param id
	 * @return
	 */
	public void removeCsSaleSurveyById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsSaleSurvey.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsSaleSurveyById", params);
		MemCache.deleteValue(CsSaleSurvey.class, id);
	}
	/**
	 * 根据条件更新市场调查
	 * @param values
	 * @param params
	 */
	public void updateCsSaleSurveyByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsSaleSurveyCount(params)>255){
			MemCache.clearValue(CsSaleSurvey.class);
		}else{
			params.put("eval","csss_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSaleSurveyEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSaleSurvey.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsSaleSurveyBy", allParams);
		Trigger.on(CsSaleSurvey.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除市场调查
	 * @param params
	 */
	public void deleteCsSaleSurveyByConfirm(Map params){
		Trigger.on(CsSaleSurvey.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsSaleSurveyCount(params)>255){
			MemCache.clearValue(CsSaleSurvey.class);
		}else{
			params.put("eval","csss_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsSaleSurveyEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsSaleSurvey.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsSaleSurveyBy", params);
	}
	

}