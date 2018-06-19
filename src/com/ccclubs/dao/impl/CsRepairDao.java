package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRepairDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRepair;
import com.lazy3q.web.helper.$;

/**
 * 车辆维修的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRepairDao extends SqlMapClientDaoSupport implements ICsRepairDao
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
	private CsRepair custom(CsRepair params){
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
			"csrProfile"			
			,"csrDescript"			
			,"csrRepairNo"			
			,"csrMemo"			
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
	 * 获取所有车辆维修
	 * @return
	 */	
	public List<CsRepair> getCsRepairList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRepair> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRepairList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆维修统计
	 * @return
	 */
	public List getCsRepairStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRepairStats", params);
	}

	/**
	 * 获取车辆维修分页
	 * @return
	 */
	public Page getCsRepairPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRepairCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRepairPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆维修数量
	 * @return
	 */	
	public Long getCsRepairCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRepairEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆维修自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRepairEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRepairEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆维修
	 * @param params
	 * @return
	 */
	public CsRepair getCsRepair(Map params)
	{		
		params = custom(params);
			
		List<CsRepair> list=this.getCsRepairList(params,1);
		CsRepair csRepair = list.isEmpty()?null:list.get(0);
		
		return csRepair;
	}
		
	/**
	 * 根据ID取车辆维修
	 * @param id
	 * @return
	 */
	public CsRepair getCsRepairById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRepair csRepair = null;
		if(csRepair!=null)
			return csRepair;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRepair = (CsRepair) this.getSqlMapClientTemplate().queryForObject("getCsRepairById",params);
		if(csRepair!=null)
			MemCache.setValue(CsRepair.class,id, csRepair.getKeyValue());
		return csRepair;
	}
	
	/**
	 * 根据ID取车辆维修(不缓存)
	 * @param id
	 * @return
	 */
	public CsRepair GetCsRepairById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRepair) this.getSqlMapClientTemplate().queryForObject("getCsRepairById",params);
	}
	

	/**
	 * 保存车辆维修
	 * @param csRepair
	 */
	public CsRepair saveCsRepair(CsRepair csRepair)
	{
		csRepair = custom(csRepair);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRepair", csRepair));
		csRepair = this.GetCsRepairById(id);
		MemCache.setValue(CsRepair.class,id, csRepair.getKeyValue());
		return Trigger.on(CsRepair.class,Trigger.SAVE,csRepair);	
	}

	

	/**
	 * 更新车辆维修
	 * @param csRepair
	 */
	public void updateCsRepair(CsRepair csRepair)
	{
		csRepair = custom(csRepair);
		this.getSqlMapClientTemplate().update("updateCsRepair", csRepair);
		csRepair = this.GetCsRepairById(csRepair.getCsrId());
		if(csRepair!=null){
			MemCache.setValue(CsRepair.class,csRepair.getCsrId(), csRepair.getKeyValue());
		}
		Trigger.on(CsRepair.class,Trigger.UPDATE,csRepair);
	}
	/**
	 * 更新车辆维修非空字段
	 * @param csRepair
	 */
	public void updateCsRepair$NotNull(CsRepair csRepair)
	{
		csRepair = custom(csRepair);
		this.getSqlMapClientTemplate().update("updateCsRepair$NotNull", csRepair);
		csRepair = this.GetCsRepairById(csRepair.getCsrId());
		if(csRepair!=null){
			MemCache.setValue(CsRepair.class,csRepair.getCsrId(), csRepair.getKeyValue());
		}
		Trigger.on(CsRepair.class,Trigger.UPDATE,csRepair);
	}

	/**
	 * 根据ID删除一个车辆维修
	 * @param id
	 */
	public void deleteCsRepairById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRepair.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRepairById", params);
		MemCache.deleteValue(CsRepair.class, id);
	}
	/**
	 * 根据ID逻辑删除车辆维修 
	 * @param id
	 * @return
	 */
	public void removeCsRepairById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRepair.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsRepairById", params);
		MemCache.deleteValue(CsRepair.class, id);
	}
	/**
	 * 根据条件更新车辆维修
	 * @param values
	 * @param params
	 */
	public void updateCsRepairByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRepairCount(params)>255){
			MemCache.clearValue(CsRepair.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRepairEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRepair.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRepairBy", allParams);
		Trigger.on(CsRepair.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆维修
	 * @param params
	 */
	public void deleteCsRepairByConfirm(Map params){
		Trigger.on(CsRepair.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRepairCount(params)>255){
			MemCache.clearValue(CsRepair.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRepairEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRepair.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRepairBy", params);
	}
	

}