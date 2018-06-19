package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsAlipayRefundDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayRefund;
import com.lazy3q.web.helper.$;

/**
 * 接口退款的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsAlipayRefundDao extends SqlMapClientDaoSupport implements ICsAlipayRefundDao
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
	private CsAlipayRefund custom(CsAlipayRefund params){
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
	 * 获取所有接口退款
	 * @return
	 */	
	public List<CsAlipayRefund> getCsAlipayRefundList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsAlipayRefund> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsAlipayRefundList", params);
		
		return list;
	}
	
	
	/**
	 * 获取接口退款统计
	 * @return
	 */
	public List getCsAlipayRefundStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsAlipayRefundStats", params);
	}

	/**
	 * 获取接口退款分页
	 * @return
	 */
	public Page getCsAlipayRefundPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsAlipayRefundCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsAlipayRefundPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取接口退款数量
	 * @return
	 */	
	public Long getCsAlipayRefundCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRefundEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取接口退款自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsAlipayRefundEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRefundEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取接口退款
	 * @param params
	 * @return
	 */
	public CsAlipayRefund getCsAlipayRefund(Map params)
	{		
		params = custom(params);
			
		List<CsAlipayRefund> list=this.getCsAlipayRefundList(params,1);
		CsAlipayRefund csAlipayRefund = list.isEmpty()?null:list.get(0);
		
		return csAlipayRefund;
	}
		
	/**
	 * 根据ID取接口退款
	 * @param id
	 * @return
	 */
	public CsAlipayRefund getCsAlipayRefundById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsAlipayRefund csAlipayRefund = null;
		if(csAlipayRefund!=null)
			return csAlipayRefund;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csAlipayRefund = (CsAlipayRefund) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRefundById",params);
		if(csAlipayRefund!=null)
			MemCache.setValue(CsAlipayRefund.class,id, csAlipayRefund.getKeyValue());
		return csAlipayRefund;
	}
	
	/**
	 * 根据ID取接口退款(不缓存)
	 * @param id
	 * @return
	 */
	public CsAlipayRefund GetCsAlipayRefundById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsAlipayRefund) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRefundById",params);
	}
	

	/**
	 * 保存接口退款
	 * @param csAlipayRefund
	 */
	public CsAlipayRefund saveCsAlipayRefund(CsAlipayRefund csAlipayRefund)
	{
		csAlipayRefund = custom(csAlipayRefund);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsAlipayRefund", csAlipayRefund));
		csAlipayRefund = this.GetCsAlipayRefundById(id);
		MemCache.setValue(CsAlipayRefund.class,id, csAlipayRefund.getKeyValue());
		return Trigger.on(CsAlipayRefund.class,Trigger.SAVE,csAlipayRefund);	
	}

	

	/**
	 * 更新接口退款
	 * @param csAlipayRefund
	 */
	public void updateCsAlipayRefund(CsAlipayRefund csAlipayRefund)
	{
		csAlipayRefund = custom(csAlipayRefund);
		this.getSqlMapClientTemplate().update("updateCsAlipayRefund", csAlipayRefund);
		csAlipayRefund = this.GetCsAlipayRefundById(csAlipayRefund.getCsarId());
		if(csAlipayRefund!=null){
			MemCache.setValue(CsAlipayRefund.class,csAlipayRefund.getCsarId(), csAlipayRefund.getKeyValue());
		}
		Trigger.on(CsAlipayRefund.class,Trigger.UPDATE,csAlipayRefund);
	}
	/**
	 * 更新接口退款非空字段
	 * @param csAlipayRefund
	 */
	public void updateCsAlipayRefund$NotNull(CsAlipayRefund csAlipayRefund)
	{
		csAlipayRefund = custom(csAlipayRefund);
		this.getSqlMapClientTemplate().update("updateCsAlipayRefund$NotNull", csAlipayRefund);
		csAlipayRefund = this.GetCsAlipayRefundById(csAlipayRefund.getCsarId());
		if(csAlipayRefund!=null){
			MemCache.setValue(CsAlipayRefund.class,csAlipayRefund.getCsarId(), csAlipayRefund.getKeyValue());
		}
		Trigger.on(CsAlipayRefund.class,Trigger.UPDATE,csAlipayRefund);
	}

	/**
	 * 根据ID删除一个接口退款
	 * @param id
	 */
	public void deleteCsAlipayRefundById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsAlipayRefund.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsAlipayRefundById", params);
		MemCache.deleteValue(CsAlipayRefund.class, id);
	}
	/**
	 * 根据ID逻辑删除接口退款 
	 * @param id
	 * @return
	 */
	public void removeCsAlipayRefundById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsAlipayRefund.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsAlipayRefundById", params);
		MemCache.deleteValue(CsAlipayRefund.class, id);
	}
	/**
	 * 根据条件更新接口退款
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayRefundByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsAlipayRefundCount(params)>255){
			MemCache.clearValue(CsAlipayRefund.class);
		}else{
			params.put("eval","csar_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAlipayRefundEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsAlipayRefund.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsAlipayRefundBy", allParams);
		Trigger.on(CsAlipayRefund.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除接口退款
	 * @param params
	 */
	public void deleteCsAlipayRefundByConfirm(Map params){
		Trigger.on(CsAlipayRefund.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsAlipayRefundCount(params)>255){
			MemCache.clearValue(CsAlipayRefund.class);
		}else{
			params.put("eval","csar_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAlipayRefundEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsAlipayRefund.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsAlipayRefundBy", params);
	}
	

}