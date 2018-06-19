package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMemberSummaryDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberSummary;
import com.lazy3q.web.helper.$;

/**
 * 会员汇总项的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsMemberSummaryDao extends SqlMapClientDaoSupport implements ICsMemberSummaryDao
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
	private CsMemberSummary custom(CsMemberSummary params){
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
		"csmsId","csmsMember","csmsOrdTotal","csmsUnitordTotal","csmsOrdTimelong","csmsUnitordTimelong","csmsOrdMileages","csmsUnitordMileages","csmsOrdAmount","csmsUnitordAmount","csmsAddtime","csmsUpdatetime"
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
	 * 获取所有会员汇总项
	 * @return
	 */	
	public List<CsMemberSummary> getCsMemberSummaryList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMemberSummary> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMemberSummaryList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员汇总项统计
	 * @return
	 */
	public List getCsMemberSummaryStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMemberSummaryStats", params);
	}

	/**
	 * 获取会员汇总项分页
	 * @return
	 */
	public Page getCsMemberSummaryPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMemberSummaryCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMemberSummaryPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员汇总项数量
	 * @return
	 */	
	public Long getCsMemberSummaryCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberSummaryEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员汇总项自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMemberSummaryEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberSummaryEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员汇总项
	 * @param params
	 * @return
	 */
	public CsMemberSummary getCsMemberSummary(Map params)
	{		
		params = custom(params);
			
		List<CsMemberSummary> list=this.getCsMemberSummaryList(params,1);
		CsMemberSummary csMemberSummary = list.isEmpty()?null:list.get(0);
		
		return csMemberSummary;
	}
		
	/**
	 * 根据ID取会员汇总项
	 * @param id
	 * @return
	 */
	public CsMemberSummary getCsMemberSummaryById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMemberSummary csMemberSummary = null;
		if(csMemberSummary!=null)
			return csMemberSummary;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMemberSummary = (CsMemberSummary) this.getSqlMapClientTemplate().queryForObject("getCsMemberSummaryById",params);
		if(csMemberSummary!=null)
			MemCache.setValue(CsMemberSummary.class,id, csMemberSummary.getKeyValue());
		return csMemberSummary;
	}
	
	/**
	 * 根据ID取会员汇总项(不缓存)
	 * @param id
	 * @return
	 */
	public CsMemberSummary GetCsMemberSummaryById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMemberSummary) this.getSqlMapClientTemplate().queryForObject("getCsMemberSummaryById",params);
	}
	

	/**
	 * 保存会员汇总项
	 * @param csMemberSummary
	 */
	public CsMemberSummary saveCsMemberSummary(CsMemberSummary csMemberSummary)
	{
		csMemberSummary = custom(csMemberSummary);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMemberSummary", csMemberSummary));
		csMemberSummary = this.GetCsMemberSummaryById(id);
		MemCache.setValue(CsMemberSummary.class,id, csMemberSummary.getKeyValue());
		return Trigger.on(CsMemberSummary.class,Trigger.SAVE,csMemberSummary);	
	}

	

	/**
	 * 更新会员汇总项
	 * @param csMemberSummary
	 */
	public void updateCsMemberSummary(CsMemberSummary csMemberSummary)
	{
		csMemberSummary = custom(csMemberSummary);
		this.getSqlMapClientTemplate().update("updateCsMemberSummary", csMemberSummary);
		csMemberSummary = this.GetCsMemberSummaryById(csMemberSummary.getCsmsId());
		if(csMemberSummary!=null){
			MemCache.setValue(CsMemberSummary.class,csMemberSummary.getCsmsId(), csMemberSummary.getKeyValue());
		}
		Trigger.on(CsMemberSummary.class,Trigger.UPDATE,csMemberSummary);
	}
	/**
	 * 更新会员汇总项非空字段
	 * @param csMemberSummary
	 */
	public void updateCsMemberSummary$NotNull(CsMemberSummary csMemberSummary)
	{
		csMemberSummary = custom(csMemberSummary);
		this.getSqlMapClientTemplate().update("updateCsMemberSummary$NotNull", csMemberSummary);
		csMemberSummary = this.GetCsMemberSummaryById(csMemberSummary.getCsmsId());
		if(csMemberSummary!=null){
			MemCache.setValue(CsMemberSummary.class,csMemberSummary.getCsmsId(), csMemberSummary.getKeyValue());
		}
		Trigger.on(CsMemberSummary.class,Trigger.UPDATE,csMemberSummary);
	}

	/**
	 * 根据ID删除一个会员汇总项
	 * @param id
	 */
	public void deleteCsMemberSummaryById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberSummary.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMemberSummaryById", params);
		MemCache.deleteValue(CsMemberSummary.class, id);
	}
	/**
	 * 根据ID逻辑删除会员汇总项 
	 * @param id
	 * @return
	 */
	public void removeCsMemberSummaryById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberSummary.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMemberSummaryById", params);
		MemCache.deleteValue(CsMemberSummary.class, id);
	}
	/**
	 * 根据条件更新会员汇总项
	 * @param values
	 * @param params
	 */
	public void updateCsMemberSummaryByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberSummaryCount(params)>255){
			MemCache.clearValue(CsMemberSummary.class);
		}else{
			params.put("eval","csms_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberSummaryEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberSummary.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMemberSummaryBy", allParams);
		Trigger.on(CsMemberSummary.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员汇总项
	 * @param params
	 */
	public void deleteCsMemberSummaryByConfirm(Map params){
		Trigger.on(CsMemberSummary.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberSummaryCount(params)>255){
			MemCache.clearValue(CsMemberSummary.class);
		}else{
			params.put("eval","csms_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberSummaryEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberSummary.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMemberSummaryBy", params);
	}
	

}