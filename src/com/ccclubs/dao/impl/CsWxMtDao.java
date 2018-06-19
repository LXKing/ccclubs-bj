package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsWxMtDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMt;
import com.lazy3q.web.helper.$;

/**
 * 微信下行的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsWxMtDao extends SqlMapClientDaoSupport implements ICsWxMtDao
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
	private CsWxMt custom(CsWxMt params){
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
			"cswmContent"			
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
	 * 获取所有微信下行
	 * @return
	 */	
	public List<CsWxMt> getCsWxMtList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsWxMt> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsWxMtList", params);
		
		return list;
	}
	
	
	/**
	 * 获取微信下行统计
	 * @return
	 */
	public List getCsWxMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsWxMtStats", params);
	}

	/**
	 * 获取微信下行分页
	 * @return
	 */
	public Page getCsWxMtPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsWxMtCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsWxMtPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取微信下行数量
	 * @return
	 */	
	public Long getCsWxMtCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxMtEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取微信下行自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsWxMtEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWxMtEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取微信下行
	 * @param params
	 * @return
	 */
	public CsWxMt getCsWxMt(Map params)
	{		
		params = custom(params);
			
		List<CsWxMt> list=this.getCsWxMtList(params,1);
		CsWxMt csWxMt = list.isEmpty()?null:list.get(0);
		
		return csWxMt;
	}
		
	/**
	 * 根据ID取微信下行
	 * @param id
	 * @return
	 */
	public CsWxMt getCsWxMtById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsWxMt csWxMt = null;
		if(csWxMt!=null)
			return csWxMt;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csWxMt = (CsWxMt) this.getSqlMapClientTemplate().queryForObject("getCsWxMtById",params);
		if(csWxMt!=null)
			MemCache.setValue(CsWxMt.class,id, csWxMt.getKeyValue());
		return csWxMt;
	}
	
	/**
	 * 根据ID取微信下行(不缓存)
	 * @param id
	 * @return
	 */
	public CsWxMt GetCsWxMtById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsWxMt) this.getSqlMapClientTemplate().queryForObject("getCsWxMtById",params);
	}
	

	/**
	 * 保存微信下行
	 * @param csWxMt
	 */
	public CsWxMt saveCsWxMt(CsWxMt csWxMt)
	{
		csWxMt = custom(csWxMt);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsWxMt", csWxMt));
		csWxMt = this.GetCsWxMtById(id);
		MemCache.setValue(CsWxMt.class,id, csWxMt.getKeyValue());
		return Trigger.on(CsWxMt.class,Trigger.SAVE,csWxMt);	
	}

	

	/**
	 * 更新微信下行
	 * @param csWxMt
	 */
	public void updateCsWxMt(CsWxMt csWxMt)
	{
		csWxMt = custom(csWxMt);
		this.getSqlMapClientTemplate().update("updateCsWxMt", csWxMt);
		csWxMt = this.GetCsWxMtById(csWxMt.getCswmId());
		if(csWxMt!=null){
			MemCache.setValue(CsWxMt.class,csWxMt.getCswmId(), csWxMt.getKeyValue());
		}
		Trigger.on(CsWxMt.class,Trigger.UPDATE,csWxMt);
	}
	/**
	 * 更新微信下行非空字段
	 * @param csWxMt
	 */
	public void updateCsWxMt$NotNull(CsWxMt csWxMt)
	{
		csWxMt = custom(csWxMt);
		this.getSqlMapClientTemplate().update("updateCsWxMt$NotNull", csWxMt);
		csWxMt = this.GetCsWxMtById(csWxMt.getCswmId());
		if(csWxMt!=null){
			MemCache.setValue(CsWxMt.class,csWxMt.getCswmId(), csWxMt.getKeyValue());
		}
		Trigger.on(CsWxMt.class,Trigger.UPDATE,csWxMt);
	}

	/**
	 * 根据ID删除一个微信下行
	 * @param id
	 */
	public void deleteCsWxMtById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxMt.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsWxMtById", params);
		MemCache.deleteValue(CsWxMt.class, id);
	}
	/**
	 * 根据ID逻辑删除微信下行 
	 * @param id
	 * @return
	 */
	public void removeCsWxMtById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWxMt.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsWxMtById", params);
		MemCache.deleteValue(CsWxMt.class, id);
	}
	/**
	 * 根据条件更新微信下行
	 * @param values
	 * @param params
	 */
	public void updateCsWxMtByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxMtCount(params)>255){
			MemCache.clearValue(CsWxMt.class);
		}else{
			params.put("eval","cswm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxMtEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxMt.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsWxMtBy", allParams);
		Trigger.on(CsWxMt.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除微信下行
	 * @param params
	 */
	public void deleteCsWxMtByConfirm(Map params){
		Trigger.on(CsWxMt.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsWxMtCount(params)>255){
			MemCache.clearValue(CsWxMt.class);
		}else{
			params.put("eval","cswm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWxMtEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWxMt.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsWxMtBy", params);
	}
	

}