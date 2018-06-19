package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsPromotionDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPromotion;
import com.lazy3q.web.helper.$;

/**
 * 促销配置的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsPromotionDao extends SqlMapClientDaoSupport implements ICsPromotionDao
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
	private CsPromotion custom(CsPromotion params){
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
			"cspName"			
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
	 * 获取所有促销配置
	 * @return
	 */	
	public List<CsPromotion> getCsPromotionList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsPromotion> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsPromotionList", params);
		
		return list;
	}
	
	
	/**
	 * 获取促销配置统计
	 * @return
	 */
	public List getCsPromotionStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsPromotionStats", params);
	}

	/**
	 * 获取促销配置分页
	 * @return
	 */
	public Page getCsPromotionPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsPromotionCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsPromotionPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取促销配置数量
	 * @return
	 */	
	public Long getCsPromotionCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPromotionEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取促销配置自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsPromotionEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsPromotionEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取促销配置
	 * @param params
	 * @return
	 */
	public CsPromotion getCsPromotion(Map params)
	{		
		params = custom(params);
			
		List<CsPromotion> list=this.getCsPromotionList(params,1);
		CsPromotion csPromotion = list.isEmpty()?null:list.get(0);
		
		return csPromotion;
	}
		
	/**
	 * 根据ID取促销配置
	 * @param id
	 * @return
	 */
	public CsPromotion getCsPromotionById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsPromotion csPromotion = null;
		if(csPromotion!=null)
			return csPromotion;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csPromotion = (CsPromotion) this.getSqlMapClientTemplate().queryForObject("getCsPromotionById",params);
		if(csPromotion!=null)
			MemCache.setValue(CsPromotion.class,id, csPromotion.getKeyValue());
		return csPromotion;
	}
	
	/**
	 * 根据ID取促销配置(不缓存)
	 * @param id
	 * @return
	 */
	public CsPromotion GetCsPromotionById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsPromotion) this.getSqlMapClientTemplate().queryForObject("getCsPromotionById",params);
	}
	

	/**
	 * 保存促销配置
	 * @param csPromotion
	 */
	public CsPromotion saveCsPromotion(CsPromotion csPromotion)
	{
		csPromotion = custom(csPromotion);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsPromotion", csPromotion));
		csPromotion = this.GetCsPromotionById(id);
		MemCache.setValue(CsPromotion.class,id, csPromotion.getKeyValue());
		return Trigger.on(CsPromotion.class,Trigger.SAVE,csPromotion);	
	}

	

	/**
	 * 更新促销配置
	 * @param csPromotion
	 */
	public void updateCsPromotion(CsPromotion csPromotion)
	{
		csPromotion = custom(csPromotion);
		this.getSqlMapClientTemplate().update("updateCsPromotion", csPromotion);
		csPromotion = this.GetCsPromotionById(csPromotion.getCspId());
		if(csPromotion!=null){
			MemCache.setValue(CsPromotion.class,csPromotion.getCspId(), csPromotion.getKeyValue());
		}
		Trigger.on(CsPromotion.class,Trigger.UPDATE,csPromotion);
	}
	/**
	 * 更新促销配置非空字段
	 * @param csPromotion
	 */
	public void updateCsPromotion$NotNull(CsPromotion csPromotion)
	{
		csPromotion = custom(csPromotion);
		this.getSqlMapClientTemplate().update("updateCsPromotion$NotNull", csPromotion);
		csPromotion = this.GetCsPromotionById(csPromotion.getCspId());
		if(csPromotion!=null){
			MemCache.setValue(CsPromotion.class,csPromotion.getCspId(), csPromotion.getKeyValue());
		}
		Trigger.on(CsPromotion.class,Trigger.UPDATE,csPromotion);
	}

	/**
	 * 根据ID删除一个促销配置
	 * @param id
	 */
	public void deleteCsPromotionById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPromotion.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsPromotionById", params);
		MemCache.deleteValue(CsPromotion.class, id);
	}
	/**
	 * 根据ID逻辑删除促销配置 
	 * @param id
	 * @return
	 */
	public void removeCsPromotionById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsPromotion.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsPromotionById", params);
		MemCache.deleteValue(CsPromotion.class, id);
	}
	/**
	 * 根据条件更新促销配置
	 * @param values
	 * @param params
	 */
	public void updateCsPromotionByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsPromotionCount(params)>255){
			MemCache.clearValue(CsPromotion.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPromotionEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPromotion.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsPromotionBy", allParams);
		Trigger.on(CsPromotion.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除促销配置
	 * @param params
	 */
	public void deleteCsPromotionByConfirm(Map params){
		Trigger.on(CsPromotion.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsPromotionCount(params)>255){
			MemCache.clearValue(CsPromotion.class);
		}else{
			params.put("eval","csp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsPromotionEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsPromotion.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsPromotionBy", params);
	}
	

}