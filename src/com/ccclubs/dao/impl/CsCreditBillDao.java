package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCreditBillDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditBill;
import com.lazy3q.web.helper.$;

/**
 * 信用账单的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCreditBillDao extends SqlMapClientDaoSupport implements ICsCreditBillDao
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
	private CsCreditBill custom(CsCreditBill params){
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
			"cscbTitle"			
			,"cscbRemark"			
			,"cscbProcess"			
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
	 * 获取所有信用账单
	 * @return
	 */	
	public List<CsCreditBill> getCsCreditBillList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCreditBill> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCreditBillList", params);
		
		return list;
	}
	
	
	/**
	 * 获取信用账单统计
	 * @return
	 */
	public List getCsCreditBillStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCreditBillStats", params);
	}

	/**
	 * 获取信用账单分页
	 * @return
	 */
	public Page getCsCreditBillPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCreditBillCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCreditBillPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取信用账单数量
	 * @return
	 */	
	public Long getCsCreditBillCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCreditBillEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取信用账单自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCreditBillEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCreditBillEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取信用账单
	 * @param params
	 * @return
	 */
	public CsCreditBill getCsCreditBill(Map params)
	{		
		params = custom(params);
			
		List<CsCreditBill> list=this.getCsCreditBillList(params,1);
		CsCreditBill csCreditBill = list.isEmpty()?null:list.get(0);
		
		return csCreditBill;
	}
		
	/**
	 * 根据ID取信用账单
	 * @param id
	 * @return
	 */
	public CsCreditBill getCsCreditBillById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCreditBill csCreditBill = null;
		if(csCreditBill!=null)
			return csCreditBill;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCreditBill = (CsCreditBill) this.getSqlMapClientTemplate().queryForObject("getCsCreditBillById",params);
		if(csCreditBill!=null)
			MemCache.setValue(CsCreditBill.class,id, csCreditBill.getKeyValue());
		return csCreditBill;
	}
	
	/**
	 * 根据ID取信用账单(不缓存)
	 * @param id
	 * @return
	 */
	public CsCreditBill GetCsCreditBillById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCreditBill) this.getSqlMapClientTemplate().queryForObject("getCsCreditBillById",params);
	}
	

	/**
	 * 保存信用账单
	 * @param csCreditBill
	 */
	public CsCreditBill saveCsCreditBill(CsCreditBill csCreditBill)
	{
		csCreditBill = custom(csCreditBill);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCreditBill", csCreditBill));
		csCreditBill = this.GetCsCreditBillById(id);
		MemCache.setValue(CsCreditBill.class,id, csCreditBill.getKeyValue());
		return Trigger.on(CsCreditBill.class,Trigger.SAVE,csCreditBill);	
	}

	

	/**
	 * 更新信用账单
	 * @param csCreditBill
	 */
	public void updateCsCreditBill(CsCreditBill csCreditBill)
	{
		csCreditBill = custom(csCreditBill);
		this.getSqlMapClientTemplate().update("updateCsCreditBill", csCreditBill);
		csCreditBill = this.GetCsCreditBillById(csCreditBill.getCscbId());
		if(csCreditBill!=null){
			MemCache.setValue(CsCreditBill.class,csCreditBill.getCscbId(), csCreditBill.getKeyValue());
		}
		Trigger.on(CsCreditBill.class,Trigger.UPDATE,csCreditBill);
	}
	/**
	 * 更新信用账单非空字段
	 * @param csCreditBill
	 */
	public void updateCsCreditBill$NotNull(CsCreditBill csCreditBill)
	{
		csCreditBill = custom(csCreditBill);
		this.getSqlMapClientTemplate().update("updateCsCreditBill$NotNull", csCreditBill);
		csCreditBill = this.GetCsCreditBillById(csCreditBill.getCscbId());
		if(csCreditBill!=null){
			MemCache.setValue(CsCreditBill.class,csCreditBill.getCscbId(), csCreditBill.getKeyValue());
		}
		Trigger.on(CsCreditBill.class,Trigger.UPDATE,csCreditBill);
	}

	/**
	 * 根据ID删除一个信用账单
	 * @param id
	 */
	public void deleteCsCreditBillById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCreditBill.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCreditBillById", params);
		MemCache.deleteValue(CsCreditBill.class, id);
	}
	/**
	 * 根据ID逻辑删除信用账单 
	 * @param id
	 * @return
	 */
	public void removeCsCreditBillById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCreditBill.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCreditBillById", params);
		MemCache.deleteValue(CsCreditBill.class, id);
	}
	/**
	 * 根据条件更新信用账单
	 * @param values
	 * @param params
	 */
	public void updateCsCreditBillByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCreditBillCount(params)>255){
			MemCache.clearValue(CsCreditBill.class);
		}else{
			params.put("eval","cscb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCreditBillEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCreditBill.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCreditBillBy", allParams);
		Trigger.on(CsCreditBill.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除信用账单
	 * @param params
	 */
	public void deleteCsCreditBillByConfirm(Map params){
		Trigger.on(CsCreditBill.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCreditBillCount(params)>255){
			MemCache.clearValue(CsCreditBill.class);
		}else{
			params.put("eval","cscb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCreditBillEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCreditBill.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCreditBillBy", params);
	}
	

}