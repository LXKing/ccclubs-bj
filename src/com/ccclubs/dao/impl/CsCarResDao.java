package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarResDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarRes;
import com.lazy3q.web.helper.$;

/**
 * 专车资源的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsCarResDao extends SqlMapClientDaoSupport implements ICsCarResDao
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
	private CsCarRes custom(CsCarRes params){
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
	 * 获取所有专车资源
	 * @return
	 */	
	public List<CsCarRes> getCsCarResList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCarRes> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarResList", params);
		
		return list;
	}
	
	
	/**
	 * 获取专车资源统计
	 * @return
	 */
	public List getCsCarResStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarResStats", params);
	}

	/**
	 * 获取专车资源分页
	 * @return
	 */
	public Page getCsCarResPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarResCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarResPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取专车资源数量
	 * @return
	 */	
	public Long getCsCarResCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarResEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取专车资源自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarResEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarResEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取专车资源
	 * @param params
	 * @return
	 */
	public CsCarRes getCsCarRes(Map params)
	{		
		params = custom(params);
			
		List<CsCarRes> list=this.getCsCarResList(params,1);
		CsCarRes csCarRes = list.isEmpty()?null:list.get(0);
		
		return csCarRes;
	}
		
	/**
	 * 根据ID取专车资源
	 * @param id
	 * @return
	 */
	public CsCarRes getCsCarResById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCarRes csCarRes = null;
		if(csCarRes!=null)
			return csCarRes;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCarRes = (CsCarRes) this.getSqlMapClientTemplate().queryForObject("getCsCarResById",params);
		if(csCarRes!=null)
			MemCache.setValue(CsCarRes.class,id, csCarRes.getKeyValue());
		return csCarRes;
	}
	
	/**
	 * 根据ID取专车资源(不缓存)
	 * @param id
	 * @return
	 */
	public CsCarRes GetCsCarResById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCarRes) this.getSqlMapClientTemplate().queryForObject("getCsCarResById",params);
	}
	

	/**
	 * 保存专车资源
	 * @param csCarRes
	 */
	public CsCarRes saveCsCarRes(CsCarRes csCarRes)
	{
		csCarRes = custom(csCarRes);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCarRes", csCarRes));
		csCarRes = this.GetCsCarResById(id);
		MemCache.setValue(CsCarRes.class,id, csCarRes.getKeyValue());
		return Trigger.on(CsCarRes.class,Trigger.SAVE,csCarRes);	
	}

	

	/**
	 * 更新专车资源
	 * @param csCarRes
	 */
	public void updateCsCarRes(CsCarRes csCarRes)
	{
		csCarRes = custom(csCarRes);
		this.getSqlMapClientTemplate().update("updateCsCarRes", csCarRes);
		csCarRes = this.GetCsCarResById(csCarRes.getCscrId());
		if(csCarRes!=null){
			MemCache.setValue(CsCarRes.class,csCarRes.getCscrId(), csCarRes.getKeyValue());
		}
		Trigger.on(CsCarRes.class,Trigger.UPDATE,csCarRes);
	}
	/**
	 * 更新专车资源非空字段
	 * @param csCarRes
	 */
	public void updateCsCarRes$NotNull(CsCarRes csCarRes)
	{
		csCarRes = custom(csCarRes);
		this.getSqlMapClientTemplate().update("updateCsCarRes$NotNull", csCarRes);
		csCarRes = this.GetCsCarResById(csCarRes.getCscrId());
		if(csCarRes!=null){
			MemCache.setValue(CsCarRes.class,csCarRes.getCscrId(), csCarRes.getKeyValue());
		}
		Trigger.on(CsCarRes.class,Trigger.UPDATE,csCarRes);
	}

	/**
	 * 根据ID删除一个专车资源
	 * @param id
	 */
	public void deleteCsCarResById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarRes.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarResById", params);
		MemCache.deleteValue(CsCarRes.class, id);
	}
	/**
	 * 根据ID逻辑删除专车资源 
	 * @param id
	 * @return
	 */
	public void removeCsCarResById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarRes.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCarResById", params);
		MemCache.deleteValue(CsCarRes.class, id);
	}
	/**
	 * 根据条件更新专车资源
	 * @param values
	 * @param params
	 */
	public void updateCsCarResByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarResCount(params)>255){
			MemCache.clearValue(CsCarRes.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarResEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarRes.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarResBy", allParams);
		Trigger.on(CsCarRes.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除专车资源
	 * @param params
	 */
	public void deleteCsCarResByConfirm(Map params){
		Trigger.on(CsCarRes.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarResCount(params)>255){
			MemCache.clearValue(CsCarRes.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarResEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarRes.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarResBy", params);
	}
	

}