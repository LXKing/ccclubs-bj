package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRepairAdrDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRepairAdr;
import com.lazy3q.web.helper.$;

/**
 * 维修地点的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRepairAdrDao extends SqlMapClientDaoSupport implements ICsRepairAdrDao
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
	private CsRepairAdr custom(CsRepairAdr params){
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
			"csraAddress"			
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
	 * 获取所有维修地点
	 * @return
	 */	
	public List<CsRepairAdr> getCsRepairAdrList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRepairAdr> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRepairAdrList", params);
		
		return list;
	}
	
	
	/**
	 * 获取维修地点统计
	 * @return
	 */
	public List getCsRepairAdrStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRepairAdrStats", params);
	}

	/**
	 * 获取维修地点分页
	 * @return
	 */
	public Page getCsRepairAdrPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRepairAdrCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRepairAdrPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取维修地点数量
	 * @return
	 */	
	public Long getCsRepairAdrCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRepairAdrEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取维修地点自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRepairAdrEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRepairAdrEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取维修地点
	 * @param params
	 * @return
	 */
	public CsRepairAdr getCsRepairAdr(Map params)
	{		
		params = custom(params);
			
		List<CsRepairAdr> list=this.getCsRepairAdrList(params,1);
		CsRepairAdr csRepairAdr = list.isEmpty()?null:list.get(0);
		
		return csRepairAdr;
	}
		
	/**
	 * 根据ID取维修地点
	 * @param id
	 * @return
	 */
	public CsRepairAdr getCsRepairAdrById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRepairAdr csRepairAdr = null;
		if(csRepairAdr!=null)
			return csRepairAdr;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRepairAdr = (CsRepairAdr) this.getSqlMapClientTemplate().queryForObject("getCsRepairAdrById",params);
		if(csRepairAdr!=null)
			MemCache.setValue(CsRepairAdr.class,id, csRepairAdr.getKeyValue());
		return csRepairAdr;
	}
	
	/**
	 * 根据ID取维修地点(不缓存)
	 * @param id
	 * @return
	 */
	public CsRepairAdr GetCsRepairAdrById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRepairAdr) this.getSqlMapClientTemplate().queryForObject("getCsRepairAdrById",params);
	}
	

	/**
	 * 保存维修地点
	 * @param csRepairAdr
	 */
	public CsRepairAdr saveCsRepairAdr(CsRepairAdr csRepairAdr)
	{
		csRepairAdr = custom(csRepairAdr);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRepairAdr", csRepairAdr));
		csRepairAdr = this.GetCsRepairAdrById(id);
		MemCache.setValue(CsRepairAdr.class,id, csRepairAdr.getKeyValue());
		return Trigger.on(CsRepairAdr.class,Trigger.SAVE,csRepairAdr);	
	}

	

	/**
	 * 更新维修地点
	 * @param csRepairAdr
	 */
	public void updateCsRepairAdr(CsRepairAdr csRepairAdr)
	{
		csRepairAdr = custom(csRepairAdr);
		this.getSqlMapClientTemplate().update("updateCsRepairAdr", csRepairAdr);
		csRepairAdr = this.GetCsRepairAdrById(csRepairAdr.getCsraId());
		if(csRepairAdr!=null){
			MemCache.setValue(CsRepairAdr.class,csRepairAdr.getCsraId(), csRepairAdr.getKeyValue());
		}
		Trigger.on(CsRepairAdr.class,Trigger.UPDATE,csRepairAdr);
	}
	/**
	 * 更新维修地点非空字段
	 * @param csRepairAdr
	 */
	public void updateCsRepairAdr$NotNull(CsRepairAdr csRepairAdr)
	{
		csRepairAdr = custom(csRepairAdr);
		this.getSqlMapClientTemplate().update("updateCsRepairAdr$NotNull", csRepairAdr);
		csRepairAdr = this.GetCsRepairAdrById(csRepairAdr.getCsraId());
		if(csRepairAdr!=null){
			MemCache.setValue(CsRepairAdr.class,csRepairAdr.getCsraId(), csRepairAdr.getKeyValue());
		}
		Trigger.on(CsRepairAdr.class,Trigger.UPDATE,csRepairAdr);
	}

	/**
	 * 根据ID删除一个维修地点
	 * @param id
	 */
	public void deleteCsRepairAdrById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRepairAdr.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRepairAdrById", params);
		MemCache.deleteValue(CsRepairAdr.class, id);
	}
	/**
	 * 根据条件更新维修地点
	 * @param values
	 * @param params
	 */
	public void updateCsRepairAdrByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRepairAdrCount(params)>255){
			MemCache.clearValue(CsRepairAdr.class);
		}else{
			params.put("eval","csra_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRepairAdrEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRepairAdr.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRepairAdrBy", allParams);
		Trigger.on(CsRepairAdr.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除维修地点
	 * @param params
	 */
	public void deleteCsRepairAdrByConfirm(Map params){
		Trigger.on(CsRepairAdr.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRepairAdrCount(params)>255){
			MemCache.clearValue(CsRepairAdr.class);
		}else{
			params.put("eval","csra_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRepairAdrEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRepairAdr.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRepairAdrBy", params);
	}
	

}