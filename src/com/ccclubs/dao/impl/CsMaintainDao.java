package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMaintainDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMaintain;
import com.lazy3q.web.helper.$;

/**
 * 车辆保养的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMaintainDao extends SqlMapClientDaoSupport implements ICsMaintainDao
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
	private CsMaintain custom(CsMaintain params){
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
			"csmKmHistory"			
			,"csmRemark"			
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
	 * 获取所有车辆保养
	 * @return
	 */	
	public List<CsMaintain> getCsMaintainList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMaintain> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMaintainList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆保养统计
	 * @return
	 */
	public List getCsMaintainStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMaintainStats", params);
	}

	/**
	 * 获取车辆保养分页
	 * @return
	 */
	public Page getCsMaintainPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMaintainCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMaintainPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆保养数量
	 * @return
	 */	
	public Long getCsMaintainCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMaintainEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆保养自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMaintainEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMaintainEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆保养
	 * @param params
	 * @return
	 */
	public CsMaintain getCsMaintain(Map params)
	{		
		params = custom(params);
			
		List<CsMaintain> list=this.getCsMaintainList(params,1);
		CsMaintain csMaintain = list.isEmpty()?null:list.get(0);
		
		return csMaintain;
	}
		
	/**
	 * 根据ID取车辆保养
	 * @param id
	 * @return
	 */
	public CsMaintain getCsMaintainById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMaintain csMaintain = null;
		if(csMaintain!=null)
			return csMaintain;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMaintain = (CsMaintain) this.getSqlMapClientTemplate().queryForObject("getCsMaintainById",params);
		if(csMaintain!=null)
			MemCache.setValue(CsMaintain.class,id, csMaintain.getKeyValue());
		return csMaintain;
	}
	
	/**
	 * 根据ID取车辆保养(不缓存)
	 * @param id
	 * @return
	 */
	public CsMaintain GetCsMaintainById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMaintain) this.getSqlMapClientTemplate().queryForObject("getCsMaintainById",params);
	}
	

	/**
	 * 保存车辆保养
	 * @param csMaintain
	 */
	public CsMaintain saveCsMaintain(CsMaintain csMaintain)
	{
		csMaintain = custom(csMaintain);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMaintain", csMaintain));
		csMaintain = this.GetCsMaintainById(id);
		MemCache.setValue(CsMaintain.class,id, csMaintain.getKeyValue());
		return Trigger.on(CsMaintain.class,Trigger.SAVE,csMaintain);	
	}

	

	/**
	 * 更新车辆保养
	 * @param csMaintain
	 */
	public void updateCsMaintain(CsMaintain csMaintain)
	{
		csMaintain = custom(csMaintain);
		this.getSqlMapClientTemplate().update("updateCsMaintain", csMaintain);
		csMaintain = this.GetCsMaintainById(csMaintain.getCsmId());
		if(csMaintain!=null){
			MemCache.setValue(CsMaintain.class,csMaintain.getCsmId(), csMaintain.getKeyValue());
		}
		Trigger.on(CsMaintain.class,Trigger.UPDATE,csMaintain);
	}
	/**
	 * 更新车辆保养非空字段
	 * @param csMaintain
	 */
	public void updateCsMaintain$NotNull(CsMaintain csMaintain)
	{
		csMaintain = custom(csMaintain);
		this.getSqlMapClientTemplate().update("updateCsMaintain$NotNull", csMaintain);
		csMaintain = this.GetCsMaintainById(csMaintain.getCsmId());
		if(csMaintain!=null){
			MemCache.setValue(CsMaintain.class,csMaintain.getCsmId(), csMaintain.getKeyValue());
		}
		Trigger.on(CsMaintain.class,Trigger.UPDATE,csMaintain);
	}

	/**
	 * 根据ID删除一个车辆保养
	 * @param id
	 */
	public void deleteCsMaintainById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMaintain.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMaintainById", params);
		MemCache.deleteValue(CsMaintain.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆保养 
	 * @param id
	 * @return
	 */
	public void removeCsMaintainById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMaintain.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMaintainById", params);
		MemCache.deleteValue(CsMaintain.class, id);
	}
	/**
	 * 根据条件更新车辆保养
	 * @param values
	 * @param params
	 */
	public void updateCsMaintainByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMaintainCount(params)>255){
			MemCache.clearValue(CsMaintain.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMaintainEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMaintain.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMaintainBy", allParams);
		Trigger.on(CsMaintain.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆保养
	 * @param params
	 */
	public void deleteCsMaintainByConfirm(Map params){
		Trigger.on(CsMaintain.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMaintainCount(params)>255){
			MemCache.clearValue(CsMaintain.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMaintainEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMaintain.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMaintainBy", params);
	}
	

}