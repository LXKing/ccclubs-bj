package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRefundDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRefund;
import com.lazy3q.web.helper.$;

/**
 * 会员退款的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRefundDao extends SqlMapClientDaoSupport implements ICsRefundDao
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
	private CsRefund custom(CsRefund params){
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
			"csrMemo"			
			,"csrRemark"			
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
	 * 获取所有会员退款
	 * @return
	 */	
	public List<CsRefund> getCsRefundList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRefund> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRefundList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员退款统计
	 * @return
	 */
	public List getCsRefundStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRefundStats", params);
	}

	/**
	 * 获取会员退款分页
	 * @return
	 */
	public Page getCsRefundPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRefundCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRefundPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员退款数量
	 * @return
	 */	
	public Long getCsRefundCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRefundEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员退款自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRefundEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRefundEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员退款
	 * @param params
	 * @return
	 */
	public CsRefund getCsRefund(Map params)
	{		
		params = custom(params);
			
		List<CsRefund> list=this.getCsRefundList(params,1);
		CsRefund csRefund = list.isEmpty()?null:list.get(0);
		
		return csRefund;
	}
		
	/**
	 * 根据ID取会员退款
	 * @param id
	 * @return
	 */
	public CsRefund getCsRefundById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRefund csRefund = null;
		if(csRefund!=null)
			return csRefund;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRefund = (CsRefund) this.getSqlMapClientTemplate().queryForObject("getCsRefundById",params);
		if(csRefund!=null)
			MemCache.setValue(CsRefund.class,id, csRefund.getKeyValue());
		return csRefund;
	}
	
	/**
	 * 根据ID取会员退款(不缓存)
	 * @param id
	 * @return
	 */
	public CsRefund GetCsRefundById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRefund) this.getSqlMapClientTemplate().queryForObject("getCsRefundById",params);
	}
	

	/**
	 * 保存会员退款
	 * @param csRefund
	 */
	public CsRefund saveCsRefund(CsRefund csRefund)
	{
		csRefund = custom(csRefund);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRefund", csRefund));
		csRefund = this.GetCsRefundById(id);
		MemCache.setValue(CsRefund.class,id, csRefund.getKeyValue());
		return Trigger.on(CsRefund.class,Trigger.SAVE,csRefund);	
	}

	

	/**
	 * 更新会员退款
	 * @param csRefund
	 */
	public void updateCsRefund(CsRefund csRefund)
	{
		csRefund = custom(csRefund);
		this.getSqlMapClientTemplate().update("updateCsRefund", csRefund);
		csRefund = this.GetCsRefundById(csRefund.getCsrId());
		if(csRefund!=null){
			MemCache.setValue(CsRefund.class,csRefund.getCsrId(), csRefund.getKeyValue());
		}
		Trigger.on(CsRefund.class,Trigger.UPDATE,csRefund);
	}
	/**
	 * 更新会员退款非空字段
	 * @param csRefund
	 */
	public void updateCsRefund$NotNull(CsRefund csRefund)
	{
		csRefund = custom(csRefund);
		this.getSqlMapClientTemplate().update("updateCsRefund$NotNull", csRefund);
		csRefund = this.GetCsRefundById(csRefund.getCsrId());
		if(csRefund!=null){
			MemCache.setValue(CsRefund.class,csRefund.getCsrId(), csRefund.getKeyValue());
		}
		Trigger.on(CsRefund.class,Trigger.UPDATE,csRefund);
	}

	/**
	 * 根据ID删除一个会员退款
	 * @param id
	 */
	public void deleteCsRefundById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRefund.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRefundById", params);
		MemCache.deleteValue(CsRefund.class, id);
	}
	/**
	 * 根据ID逻辑删除会员退款 
	 * @param id
	 * @return
	 */
	public void removeCsRefundById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRefund.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsRefundById", params);
		MemCache.deleteValue(CsRefund.class, id);
	}
	/**
	 * 根据条件更新会员退款
	 * @param values
	 * @param params
	 */
	public void updateCsRefundByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRefundCount(params)>255){
			MemCache.clearValue(CsRefund.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRefundEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRefund.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRefundBy", allParams);
		Trigger.on(CsRefund.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员退款
	 * @param params
	 */
	public void deleteCsRefundByConfirm(Map params){
		Trigger.on(CsRefund.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRefundCount(params)>255){
			MemCache.clearValue(CsRefund.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRefundEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRefund.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRefundBy", params);
	}
	

}