package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsTroubleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTrouble;
import com.lazy3q.web.helper.$;

/**
 * 车辆事故的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsTroubleDao extends SqlMapClientDaoSupport implements ICsTroubleDao
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
	private CsTrouble custom(CsTrouble params){
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
			"cstTitle"			
			,"cstDamage"			
			,"cstRepairPart"			
			,"cstDecipt"			
			,"cstRemark"			
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
	 * 获取所有车辆事故
	 * @return
	 */	
	public List<CsTrouble> getCsTroubleList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsTrouble> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsTroubleList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆事故统计
	 * @return
	 */
	public List getCsTroubleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsTroubleStats", params);
	}

	/**
	 * 获取车辆事故分页
	 * @return
	 */
	public Page getCsTroublePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsTroubleCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsTroublePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆事故数量
	 * @return
	 */	
	public Long getCsTroubleCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsTroubleEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆事故自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsTroubleEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsTroubleEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆事故
	 * @param params
	 * @return
	 */
	public CsTrouble getCsTrouble(Map params)
	{		
		params = custom(params);
			
		List<CsTrouble> list=this.getCsTroubleList(params,1);
		CsTrouble csTrouble = list.isEmpty()?null:list.get(0);
		
		return csTrouble;
	}
		
	/**
	 * 根据ID取车辆事故
	 * @param id
	 * @return
	 */
	public CsTrouble getCsTroubleById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsTrouble csTrouble = null;
		if(csTrouble!=null)
			return csTrouble;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csTrouble = (CsTrouble) this.getSqlMapClientTemplate().queryForObject("getCsTroubleById",params);
		if(csTrouble!=null)
			MemCache.setValue(CsTrouble.class,id, csTrouble.getKeyValue());
		return csTrouble;
	}
	
	/**
	 * 根据ID取车辆事故(不缓存)
	 * @param id
	 * @return
	 */
	public CsTrouble GetCsTroubleById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsTrouble) this.getSqlMapClientTemplate().queryForObject("getCsTroubleById",params);
	}
	

	/**
	 * 保存车辆事故
	 * @param csTrouble
	 */
	public CsTrouble saveCsTrouble(CsTrouble csTrouble)
	{
		csTrouble = custom(csTrouble);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsTrouble", csTrouble));
		csTrouble = this.GetCsTroubleById(id);
		MemCache.setValue(CsTrouble.class,id, csTrouble.getKeyValue());
		return Trigger.on(CsTrouble.class,Trigger.SAVE,csTrouble);	
	}

	

	/**
	 * 更新车辆事故
	 * @param csTrouble
	 */
	public void updateCsTrouble(CsTrouble csTrouble)
	{
		csTrouble = custom(csTrouble);
		this.getSqlMapClientTemplate().update("updateCsTrouble", csTrouble);
		csTrouble = this.GetCsTroubleById(csTrouble.getCstId());
		if(csTrouble!=null){
			MemCache.setValue(CsTrouble.class,csTrouble.getCstId(), csTrouble.getKeyValue());
		}
		Trigger.on(CsTrouble.class,Trigger.UPDATE,csTrouble);
	}
	/**
	 * 更新车辆事故非空字段
	 * @param csTrouble
	 */
	public void updateCsTrouble$NotNull(CsTrouble csTrouble)
	{
		csTrouble = custom(csTrouble);
		this.getSqlMapClientTemplate().update("updateCsTrouble$NotNull", csTrouble);
		csTrouble = this.GetCsTroubleById(csTrouble.getCstId());
		if(csTrouble!=null){
			MemCache.setValue(CsTrouble.class,csTrouble.getCstId(), csTrouble.getKeyValue());
		}
		Trigger.on(CsTrouble.class,Trigger.UPDATE,csTrouble);
	}

	/**
	 * 根据ID删除一个车辆事故
	 * @param id
	 */
	public void deleteCsTroubleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsTrouble.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsTroubleById", params);
		MemCache.deleteValue(CsTrouble.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆事故 
	 * @param id
	 * @return
	 */
	public void removeCsTroubleById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsTrouble.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsTroubleById", params);
		MemCache.deleteValue(CsTrouble.class, id);
	}
	/**
	 * 根据条件更新车辆事故
	 * @param values
	 * @param params
	 */
	public void updateCsTroubleByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsTroubleCount(params)>255){
			MemCache.clearValue(CsTrouble.class);
		}else{
			params.put("eval","cst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsTroubleEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsTrouble.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsTroubleBy", allParams);
		Trigger.on(CsTrouble.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆事故
	 * @param params
	 */
	public void deleteCsTroubleByConfirm(Map params){
		Trigger.on(CsTrouble.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsTroubleCount(params)>255){
			MemCache.clearValue(CsTrouble.class);
		}else{
			params.put("eval","cst_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsTroubleEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsTrouble.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsTroubleBy", params);
	}
	

}