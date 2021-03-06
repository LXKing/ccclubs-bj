package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLuckyResultDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLuckyResult;
import com.lazy3q.web.helper.$;

/**
 * 中奖结果的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLuckyResultDao extends SqlMapClientDaoSupport implements ICsLuckyResultDao
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
	private CsLuckyResult custom(CsLuckyResult params){
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
			"cslrName"			
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
	 * 获取所有中奖结果
	 * @return
	 */	
	public List<CsLuckyResult> getCsLuckyResultList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLuckyResult> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLuckyResultList", params);
		
		return list;
	}
	
	
	/**
	 * 获取中奖结果统计
	 * @return
	 */
	public List getCsLuckyResultStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLuckyResultStats", params);
	}

	/**
	 * 获取中奖结果分页
	 * @return
	 */
	public Page getCsLuckyResultPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLuckyResultCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLuckyResultPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取中奖结果数量
	 * @return
	 */	
	public Long getCsLuckyResultCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLuckyResultEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取中奖结果自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLuckyResultEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLuckyResultEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取中奖结果
	 * @param params
	 * @return
	 */
	public CsLuckyResult getCsLuckyResult(Map params)
	{		
		params = custom(params);
			
		List<CsLuckyResult> list=this.getCsLuckyResultList(params,1);
		CsLuckyResult csLuckyResult = list.isEmpty()?null:list.get(0);
		
		return csLuckyResult;
	}
		
	/**
	 * 根据ID取中奖结果
	 * @param id
	 * @return
	 */
	public CsLuckyResult getCsLuckyResultById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLuckyResult csLuckyResult = null;
		if(csLuckyResult!=null)
			return csLuckyResult;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLuckyResult = (CsLuckyResult) this.getSqlMapClientTemplate().queryForObject("getCsLuckyResultById",params);
		if(csLuckyResult!=null)
			MemCache.setValue(CsLuckyResult.class,id, csLuckyResult.getKeyValue());
		return csLuckyResult;
	}
	
	/**
	 * 根据ID取中奖结果(不缓存)
	 * @param id
	 * @return
	 */
	public CsLuckyResult GetCsLuckyResultById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLuckyResult) this.getSqlMapClientTemplate().queryForObject("getCsLuckyResultById",params);
	}
	

	/**
	 * 保存中奖结果
	 * @param csLuckyResult
	 */
	public CsLuckyResult saveCsLuckyResult(CsLuckyResult csLuckyResult)
	{
		csLuckyResult = custom(csLuckyResult);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLuckyResult", csLuckyResult));
		csLuckyResult = this.GetCsLuckyResultById(id);
		MemCache.setValue(CsLuckyResult.class,id, csLuckyResult.getKeyValue());
		return Trigger.on(CsLuckyResult.class,Trigger.SAVE,csLuckyResult);	
	}

	

	/**
	 * 更新中奖结果
	 * @param csLuckyResult
	 */
	public void updateCsLuckyResult(CsLuckyResult csLuckyResult)
	{
		csLuckyResult = custom(csLuckyResult);
		this.getSqlMapClientTemplate().update("updateCsLuckyResult", csLuckyResult);
		csLuckyResult = this.GetCsLuckyResultById(csLuckyResult.getCslrId());
		if(csLuckyResult!=null){
			MemCache.setValue(CsLuckyResult.class,csLuckyResult.getCslrId(), csLuckyResult.getKeyValue());
		}
		Trigger.on(CsLuckyResult.class,Trigger.UPDATE,csLuckyResult);
	}
	/**
	 * 更新中奖结果非空字段
	 * @param csLuckyResult
	 */
	public void updateCsLuckyResult$NotNull(CsLuckyResult csLuckyResult)
	{
		csLuckyResult = custom(csLuckyResult);
		this.getSqlMapClientTemplate().update("updateCsLuckyResult$NotNull", csLuckyResult);
		csLuckyResult = this.GetCsLuckyResultById(csLuckyResult.getCslrId());
		if(csLuckyResult!=null){
			MemCache.setValue(CsLuckyResult.class,csLuckyResult.getCslrId(), csLuckyResult.getKeyValue());
		}
		Trigger.on(CsLuckyResult.class,Trigger.UPDATE,csLuckyResult);
	}

	/**
	 * 根据ID删除一个中奖结果
	 * @param id
	 */
	public void deleteCsLuckyResultById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLuckyResult.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLuckyResultById", params);
		MemCache.deleteValue(CsLuckyResult.class, id);
	}
	/**
	 * 根据ID逻辑删除中奖结果 
	 * @param id
	 * @return
	 */
	public void removeCsLuckyResultById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLuckyResult.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsLuckyResultById", params);
		MemCache.deleteValue(CsLuckyResult.class, id);
	}
	/**
	 * 根据条件更新中奖结果
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyResultByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLuckyResultCount(params)>255){
			MemCache.clearValue(CsLuckyResult.class);
		}else{
			params.put("eval","cslr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLuckyResultEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLuckyResult.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLuckyResultBy", allParams);
		Trigger.on(CsLuckyResult.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除中奖结果
	 * @param params
	 */
	public void deleteCsLuckyResultByConfirm(Map params){
		Trigger.on(CsLuckyResult.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLuckyResultCount(params)>255){
			MemCache.clearValue(CsLuckyResult.class);
		}else{
			params.put("eval","cslr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLuckyResultEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLuckyResult.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLuckyResultBy", params);
	}
	

}