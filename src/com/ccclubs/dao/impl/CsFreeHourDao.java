package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsFreeHourDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFreeHour;
import com.lazy3q.web.helper.$;

/**
 * 免费小时的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsFreeHourDao extends SqlMapClientDaoSupport implements ICsFreeHourDao
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
	private CsFreeHour custom(CsFreeHour params){
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
			"csfhRemark"			
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
	 * 获取所有免费小时
	 * @return
	 */	
	public List<CsFreeHour> getCsFreeHourList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsFreeHour> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsFreeHourList", params);
		
		return list;
	}
	
	
	/**
	 * 获取免费小时统计
	 * @return
	 */
	public List getCsFreeHourStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsFreeHourStats", params);
	}

	/**
	 * 获取免费小时分页
	 * @return
	 */
	public Page getCsFreeHourPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsFreeHourCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsFreeHourPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取免费小时数量
	 * @return
	 */	
	public Long getCsFreeHourCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsFreeHourEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取免费小时自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsFreeHourEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsFreeHourEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取免费小时
	 * @param params
	 * @return
	 */
	public CsFreeHour getCsFreeHour(Map params)
	{		
		params = custom(params);
			
		List<CsFreeHour> list=this.getCsFreeHourList(params,1);
		CsFreeHour csFreeHour = list.isEmpty()?null:list.get(0);
		
		return csFreeHour;
	}
		
	/**
	 * 根据ID取免费小时
	 * @param id
	 * @return
	 */
	public CsFreeHour getCsFreeHourById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsFreeHour csFreeHour = null;
		if(csFreeHour!=null)
			return csFreeHour;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csFreeHour = (CsFreeHour) this.getSqlMapClientTemplate().queryForObject("getCsFreeHourById",params);
		if(csFreeHour!=null)
			MemCache.setValue(CsFreeHour.class,id, csFreeHour.getKeyValue());
		return csFreeHour;
	}
	
	/**
	 * 根据ID取免费小时(不缓存)
	 * @param id
	 * @return
	 */
	public CsFreeHour GetCsFreeHourById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsFreeHour) this.getSqlMapClientTemplate().queryForObject("getCsFreeHourById",params);
	}
	

	/**
	 * 保存免费小时
	 * @param csFreeHour
	 */
	public CsFreeHour saveCsFreeHour(CsFreeHour csFreeHour)
	{
		csFreeHour = custom(csFreeHour);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsFreeHour", csFreeHour));
		csFreeHour = this.GetCsFreeHourById(id);
		MemCache.setValue(CsFreeHour.class,id, csFreeHour.getKeyValue());
		return Trigger.on(CsFreeHour.class,Trigger.SAVE,csFreeHour);	
	}

	

	/**
	 * 更新免费小时
	 * @param csFreeHour
	 */
	public void updateCsFreeHour(CsFreeHour csFreeHour)
	{
		csFreeHour = custom(csFreeHour);
		this.getSqlMapClientTemplate().update("updateCsFreeHour", csFreeHour);
		csFreeHour = this.GetCsFreeHourById(csFreeHour.getCsfhId());
		if(csFreeHour!=null){
			MemCache.setValue(CsFreeHour.class,csFreeHour.getCsfhId(), csFreeHour.getKeyValue());
		}
		Trigger.on(CsFreeHour.class,Trigger.UPDATE,csFreeHour);
	}
	/**
	 * 更新免费小时非空字段
	 * @param csFreeHour
	 */
	public void updateCsFreeHour$NotNull(CsFreeHour csFreeHour)
	{
		csFreeHour = custom(csFreeHour);
		this.getSqlMapClientTemplate().update("updateCsFreeHour$NotNull", csFreeHour);
		csFreeHour = this.GetCsFreeHourById(csFreeHour.getCsfhId());
		if(csFreeHour!=null){
			MemCache.setValue(CsFreeHour.class,csFreeHour.getCsfhId(), csFreeHour.getKeyValue());
		}
		Trigger.on(CsFreeHour.class,Trigger.UPDATE,csFreeHour);
	}

	/**
	 * 根据ID删除一个免费小时
	 * @param id
	 */
	public void deleteCsFreeHourById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsFreeHour.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsFreeHourById", params);
		MemCache.deleteValue(CsFreeHour.class, id);
	}
	/**
	 * 根据ID逻辑删除免费小时 
	 * @param id
	 * @return
	 */
	public void removeCsFreeHourById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsFreeHour.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsFreeHourById", params);
		MemCache.deleteValue(CsFreeHour.class, id);
	}
	/**
	 * 根据条件更新免费小时
	 * @param values
	 * @param params
	 */
	public void updateCsFreeHourByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsFreeHourCount(params)>255){
			MemCache.clearValue(CsFreeHour.class);
		}else{
			params.put("eval","csfh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsFreeHourEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsFreeHour.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsFreeHourBy", allParams);
		Trigger.on(CsFreeHour.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除免费小时
	 * @param params
	 */
	public void deleteCsFreeHourByConfirm(Map params){
		Trigger.on(CsFreeHour.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsFreeHourCount(params)>255){
			MemCache.clearValue(CsFreeHour.class);
		}else{
			params.put("eval","csfh_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsFreeHourEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsFreeHour.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsFreeHourBy", params);
	}
	

}