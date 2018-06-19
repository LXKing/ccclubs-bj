package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUnitBillDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitBill;
import com.lazy3q.web.helper.$;

/**
 * 企业对账的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsUnitBillDao extends SqlMapClientDaoSupport implements ICsUnitBillDao
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
	private CsUnitBill custom(CsUnitBill params){
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
			"csubSummary"			
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
	 * 获取所有企业对账
	 * @return
	 */	
	public List<CsUnitBill> getCsUnitBillList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUnitBill> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUnitBillList", params);
		
		return list;
	}
	
	
	/**
	 * 获取企业对账统计
	 * @return
	 */
	public List getCsUnitBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUnitBillStats", params);
	}

	/**
	 * 获取企业对账分页
	 * @return
	 */
	public Page getCsUnitBillPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUnitBillCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUnitBillPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取企业对账数量
	 * @return
	 */	
	public Long getCsUnitBillCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitBillEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取企业对账自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUnitBillEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUnitBillEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取企业对账
	 * @param params
	 * @return
	 */
	public CsUnitBill getCsUnitBill(Map params)
	{		
		params = custom(params);
			
		List<CsUnitBill> list=this.getCsUnitBillList(params,1);
		CsUnitBill csUnitBill = list.isEmpty()?null:list.get(0);
		
		return csUnitBill;
	}
		
	/**
	 * 根据ID取企业对账
	 * @param id
	 * @return
	 */
	public CsUnitBill getCsUnitBillById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUnitBill csUnitBill = null;
		if(csUnitBill!=null)
			return csUnitBill;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUnitBill = (CsUnitBill) this.getSqlMapClientTemplate().queryForObject("getCsUnitBillById",params);
		if(csUnitBill!=null)
			MemCache.setValue(CsUnitBill.class,id, csUnitBill.getKeyValue());
		return csUnitBill;
	}
	
	/**
	 * 根据ID取企业对账(不缓存)
	 * @param id
	 * @return
	 */
	public CsUnitBill GetCsUnitBillById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUnitBill) this.getSqlMapClientTemplate().queryForObject("getCsUnitBillById",params);
	}
	

	/**
	 * 保存企业对账
	 * @param csUnitBill
	 */
	public CsUnitBill saveCsUnitBill(CsUnitBill csUnitBill)
	{
		csUnitBill = custom(csUnitBill);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUnitBill", csUnitBill));
		csUnitBill = this.GetCsUnitBillById(id);
		MemCache.setValue(CsUnitBill.class,id, csUnitBill.getKeyValue());
		return Trigger.on(CsUnitBill.class,Trigger.SAVE,csUnitBill);	
	}

	

	/**
	 * 更新企业对账
	 * @param csUnitBill
	 */
	public void updateCsUnitBill(CsUnitBill csUnitBill)
	{
		csUnitBill = custom(csUnitBill);
		this.getSqlMapClientTemplate().update("updateCsUnitBill", csUnitBill);
		csUnitBill = this.GetCsUnitBillById(csUnitBill.getCsubId());
		if(csUnitBill!=null){
			MemCache.setValue(CsUnitBill.class,csUnitBill.getCsubId(), csUnitBill.getKeyValue());
		}
		Trigger.on(CsUnitBill.class,Trigger.UPDATE,csUnitBill);
	}
	/**
	 * 更新企业对账非空字段
	 * @param csUnitBill
	 */
	public void updateCsUnitBill$NotNull(CsUnitBill csUnitBill)
	{
		csUnitBill = custom(csUnitBill);
		this.getSqlMapClientTemplate().update("updateCsUnitBill$NotNull", csUnitBill);
		csUnitBill = this.GetCsUnitBillById(csUnitBill.getCsubId());
		if(csUnitBill!=null){
			MemCache.setValue(CsUnitBill.class,csUnitBill.getCsubId(), csUnitBill.getKeyValue());
		}
		Trigger.on(CsUnitBill.class,Trigger.UPDATE,csUnitBill);
	}

	/**
	 * 根据ID删除一个企业对账
	 * @param id
	 */
	public void deleteCsUnitBillById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitBill.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUnitBillById", params);
		MemCache.deleteValue(CsUnitBill.class, id);
	}
	/**
	 * 根据ID逻辑删除企业对账 
	 * @param id
	 * @return
	 */
	public void removeCsUnitBillById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUnitBill.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUnitBillById", params);
		MemCache.deleteValue(CsUnitBill.class, id);
	}
	/**
	 * 根据条件更新企业对账
	 * @param values
	 * @param params
	 */
	public void updateCsUnitBillByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitBillCount(params)>255){
			MemCache.clearValue(CsUnitBill.class);
		}else{
			params.put("eval","csub_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitBillEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitBill.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUnitBillBy", allParams);
		Trigger.on(CsUnitBill.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除企业对账
	 * @param params
	 */
	public void deleteCsUnitBillByConfirm(Map params){
		Trigger.on(CsUnitBill.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUnitBillCount(params)>255){
			MemCache.clearValue(CsUnitBill.class);
		}else{
			params.put("eval","csub_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUnitBillEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUnitBill.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUnitBillBy", params);
	}
	

}