package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarOnDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarOn;
import com.lazy3q.web.helper.$;

/**
 * 车辆上线记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCarOnDao extends SqlMapClientDaoSupport implements ICsCarOnDao
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
	private CsCarOn custom(CsCarOn params){
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
			"cscoRemark"			
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
	 * 获取所有车辆上线记录
	 * @return
	 */	
	public List<CsCarOn> getCsCarOnList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCarOn> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarOnList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆上线记录统计
	 * @return
	 */
	public List getCsCarOnStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarOnStats", params);
	}

	/**
	 * 获取车辆上线记录分页
	 * @return
	 */
	public Page getCsCarOnPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarOnCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarOnPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆上线记录数量
	 * @return
	 */	
	public Long getCsCarOnCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarOnEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆上线记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarOnEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarOnEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆上线记录
	 * @param params
	 * @return
	 */
	public CsCarOn getCsCarOn(Map params)
	{		
		params = custom(params);
			
		List<CsCarOn> list=this.getCsCarOnList(params,1);
		CsCarOn csCarOn = list.isEmpty()?null:list.get(0);
		
		return csCarOn;
	}
		
	/**
	 * 根据ID取车辆上线记录
	 * @param id
	 * @return
	 */
	public CsCarOn getCsCarOnById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCarOn csCarOn = null;
		if(csCarOn!=null)
			return csCarOn;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCarOn = (CsCarOn) this.getSqlMapClientTemplate().queryForObject("getCsCarOnById",params);
		if(csCarOn!=null)
			MemCache.setValue(CsCarOn.class,id, csCarOn.getKeyValue());
		return csCarOn;
	}
	
	/**
	 * 根据ID取车辆上线记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsCarOn GetCsCarOnById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCarOn) this.getSqlMapClientTemplate().queryForObject("getCsCarOnById",params);
	}
	

	/**
	 * 保存车辆上线记录
	 * @param csCarOn
	 */
	public CsCarOn saveCsCarOn(CsCarOn csCarOn)
	{
		csCarOn = custom(csCarOn);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCarOn", csCarOn));
		csCarOn = this.GetCsCarOnById(id);
		MemCache.setValue(CsCarOn.class,id, csCarOn.getKeyValue());
		return Trigger.on(CsCarOn.class,Trigger.SAVE,csCarOn);	
	}

	

	/**
	 * 更新车辆上线记录
	 * @param csCarOn
	 */
	public void updateCsCarOn(CsCarOn csCarOn)
	{
		csCarOn = custom(csCarOn);
		this.getSqlMapClientTemplate().update("updateCsCarOn", csCarOn);
		csCarOn = this.GetCsCarOnById(csCarOn.getCscoId());
		if(csCarOn!=null){
			MemCache.setValue(CsCarOn.class,csCarOn.getCscoId(), csCarOn.getKeyValue());
		}
		Trigger.on(CsCarOn.class,Trigger.UPDATE,csCarOn);
	}
	/**
	 * 更新车辆上线记录非空字段
	 * @param csCarOn
	 */
	public void updateCsCarOn$NotNull(CsCarOn csCarOn)
	{
		csCarOn = custom(csCarOn);
		this.getSqlMapClientTemplate().update("updateCsCarOn$NotNull", csCarOn);
		csCarOn = this.GetCsCarOnById(csCarOn.getCscoId());
		if(csCarOn!=null){
			MemCache.setValue(CsCarOn.class,csCarOn.getCscoId(), csCarOn.getKeyValue());
		}
		Trigger.on(CsCarOn.class,Trigger.UPDATE,csCarOn);
	}

	/**
	 * 根据ID删除一个车辆上线记录
	 * @param id
	 */
	public void deleteCsCarOnById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarOn.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarOnById", params);
		MemCache.deleteValue(CsCarOn.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆上线记录 
	 * @param id
	 * @return
	 */
	public void removeCsCarOnById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarOn.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCarOnById", params);
		MemCache.deleteValue(CsCarOn.class, id);
	}
	/**
	 * 根据条件更新车辆上线记录
	 * @param values
	 * @param params
	 */
	public void updateCsCarOnByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarOnCount(params)>255){
			MemCache.clearValue(CsCarOn.class);
		}else{
			params.put("eval","csco_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarOnEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarOn.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarOnBy", allParams);
		Trigger.on(CsCarOn.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆上线记录
	 * @param params
	 */
	public void deleteCsCarOnByConfirm(Map params){
		Trigger.on(CsCarOn.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarOnCount(params)>255){
			MemCache.clearValue(CsCarOn.class);
		}else{
			params.put("eval","csco_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarOnEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarOn.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarOnBy", params);
	}
	

}