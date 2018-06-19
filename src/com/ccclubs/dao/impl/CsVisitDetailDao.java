package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsVisitDetailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsVisitDetail;
import com.lazy3q.web.helper.$;

/**
 * 访问详情的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsVisitDetailDao extends SqlMapClientDaoSupport implements ICsVisitDetailDao
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
	private CsVisitDetail custom(CsVisitDetail params){
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
	 * 获取所有访问详情
	 * @return
	 */	
	public List<CsVisitDetail> getCsVisitDetailList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsVisitDetail> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsVisitDetailList", params);
		
		return list;
	}
	
	
	/**
	 * 获取访问详情统计
	 * @return
	 */
	public List getCsVisitDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsVisitDetailStats", params);
	}

	/**
	 * 获取访问详情分页
	 * @return
	 */
	public Page getCsVisitDetailPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsVisitDetailCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsVisitDetailPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取访问详情数量
	 * @return
	 */	
	public Long getCsVisitDetailCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsVisitDetailEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取访问详情自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsVisitDetailEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsVisitDetailEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取访问详情
	 * @param params
	 * @return
	 */
	public CsVisitDetail getCsVisitDetail(Map params)
	{		
		params = custom(params);
			
		List<CsVisitDetail> list=this.getCsVisitDetailList(params,1);
		CsVisitDetail csVisitDetail = list.isEmpty()?null:list.get(0);
		
		return csVisitDetail;
	}
		
	/**
	 * 根据ID取访问详情
	 * @param id
	 * @return
	 */
	public CsVisitDetail getCsVisitDetailById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsVisitDetail csVisitDetail = null;
		if(csVisitDetail!=null)
			return csVisitDetail;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csVisitDetail = (CsVisitDetail) this.getSqlMapClientTemplate().queryForObject("getCsVisitDetailById",params);
		if(csVisitDetail!=null)
			MemCache.setValue(CsVisitDetail.class,id, csVisitDetail.getKeyValue());
		return csVisitDetail;
	}
	
	/**
	 * 根据ID取访问详情(不缓存)
	 * @param id
	 * @return
	 */
	public CsVisitDetail GetCsVisitDetailById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsVisitDetail) this.getSqlMapClientTemplate().queryForObject("getCsVisitDetailById",params);
	}
	

	/**
	 * 保存访问详情
	 * @param csVisitDetail
	 */
	public CsVisitDetail saveCsVisitDetail(CsVisitDetail csVisitDetail)
	{
		csVisitDetail = custom(csVisitDetail);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsVisitDetail", csVisitDetail));
		csVisitDetail = this.GetCsVisitDetailById(id);
		MemCache.setValue(CsVisitDetail.class,id, csVisitDetail.getKeyValue());
		return Trigger.on(CsVisitDetail.class,Trigger.SAVE,csVisitDetail);	
	}

	

	/**
	 * 更新访问详情
	 * @param csVisitDetail
	 */
	public void updateCsVisitDetail(CsVisitDetail csVisitDetail)
	{
		csVisitDetail = custom(csVisitDetail);
		this.getSqlMapClientTemplate().update("updateCsVisitDetail", csVisitDetail);
		csVisitDetail = this.GetCsVisitDetailById(csVisitDetail.getCsvdId());
		if(csVisitDetail!=null){
			MemCache.setValue(CsVisitDetail.class,csVisitDetail.getCsvdId(), csVisitDetail.getKeyValue());
		}
		Trigger.on(CsVisitDetail.class,Trigger.UPDATE,csVisitDetail);
	}
	/**
	 * 更新访问详情非空字段
	 * @param csVisitDetail
	 */
	public void updateCsVisitDetail$NotNull(CsVisitDetail csVisitDetail)
	{
		csVisitDetail = custom(csVisitDetail);
		this.getSqlMapClientTemplate().update("updateCsVisitDetail$NotNull", csVisitDetail);
		csVisitDetail = this.GetCsVisitDetailById(csVisitDetail.getCsvdId());
		if(csVisitDetail!=null){
			MemCache.setValue(CsVisitDetail.class,csVisitDetail.getCsvdId(), csVisitDetail.getKeyValue());
		}
		Trigger.on(CsVisitDetail.class,Trigger.UPDATE,csVisitDetail);
	}

	/**
	 * 根据ID删除一个访问详情
	 * @param id
	 */
	public void deleteCsVisitDetailById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsVisitDetail.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsVisitDetailById", params);
		MemCache.deleteValue(CsVisitDetail.class, id);
	}
	/**
	 * 根据ID逻辑删除访问详情 
	 * @param id
	 * @return
	 */
	public void removeCsVisitDetailById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsVisitDetail.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsVisitDetailById", params);
		MemCache.deleteValue(CsVisitDetail.class, id);
	}
	/**
	 * 根据条件更新访问详情
	 * @param values
	 * @param params
	 */
	public void updateCsVisitDetailByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsVisitDetailCount(params)>255){
			MemCache.clearValue(CsVisitDetail.class);
		}else{
			params.put("eval","csvd_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsVisitDetailEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsVisitDetail.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsVisitDetailBy", allParams);
		Trigger.on(CsVisitDetail.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除访问详情
	 * @param params
	 */
	public void deleteCsVisitDetailByConfirm(Map params){
		Trigger.on(CsVisitDetail.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsVisitDetailCount(params)>255){
			MemCache.clearValue(CsVisitDetail.class);
		}else{
			params.put("eval","csvd_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsVisitDetailEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsVisitDetail.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsVisitDetailBy", params);
	}
	

}