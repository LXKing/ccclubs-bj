package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsLongOrderDetailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrderDetail;
import com.lazy3q.web.helper.$;

/**
 * 长单明细的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsLongOrderDetailDao extends SqlMapClientDaoSupport implements ICsLongOrderDetailDao
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
	private CsLongOrderDetail custom(CsLongOrderDetail params){
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
			"cslodRemark"			
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
	 * 获取所有长单明细
	 * @return
	 */	
	public List<CsLongOrderDetail> getCsLongOrderDetailList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsLongOrderDetail> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsLongOrderDetailList", params);
		
		return list;
	}
	
	
	/**
	 * 获取长单明细统计
	 * @return
	 */
	public List getCsLongOrderDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsLongOrderDetailStats", params);
	}

	/**
	 * 获取长单明细分页
	 * @return
	 */
	public Page getCsLongOrderDetailPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsLongOrderDetailCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsLongOrderDetailPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取长单明细数量
	 * @return
	 */	
	public Long getCsLongOrderDetailCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderDetailEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取长单明细自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderDetailEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderDetailEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取长单明细
	 * @param params
	 * @return
	 */
	public CsLongOrderDetail getCsLongOrderDetail(Map params)
	{		
		params = custom(params);
			
		List<CsLongOrderDetail> list=this.getCsLongOrderDetailList(params,1);
		CsLongOrderDetail csLongOrderDetail = list.isEmpty()?null:list.get(0);
		
		return csLongOrderDetail;
	}
		
	/**
	 * 根据ID取长单明细
	 * @param id
	 * @return
	 */
	public CsLongOrderDetail getCsLongOrderDetailById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsLongOrderDetail csLongOrderDetail = null;
		if(csLongOrderDetail!=null)
			return csLongOrderDetail;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csLongOrderDetail = (CsLongOrderDetail) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderDetailById",params);
		if(csLongOrderDetail!=null)
			MemCache.setValue(CsLongOrderDetail.class,id, csLongOrderDetail.getKeyValue());
		return csLongOrderDetail;
	}
	
	/**
	 * 根据ID取长单明细(不缓存)
	 * @param id
	 * @return
	 */
	public CsLongOrderDetail GetCsLongOrderDetailById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsLongOrderDetail) this.getSqlMapClientTemplate().queryForObject("getCsLongOrderDetailById",params);
	}
	

	/**
	 * 保存长单明细
	 * @param csLongOrderDetail
	 */
	public CsLongOrderDetail saveCsLongOrderDetail(CsLongOrderDetail csLongOrderDetail)
	{
		csLongOrderDetail = custom(csLongOrderDetail);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsLongOrderDetail", csLongOrderDetail));
		csLongOrderDetail = this.GetCsLongOrderDetailById(id);
		MemCache.setValue(CsLongOrderDetail.class,id, csLongOrderDetail.getKeyValue());
		return Trigger.on(CsLongOrderDetail.class,Trigger.SAVE,csLongOrderDetail);	
	}

	

	/**
	 * 更新长单明细
	 * @param csLongOrderDetail
	 */
	public void updateCsLongOrderDetail(CsLongOrderDetail csLongOrderDetail)
	{
		csLongOrderDetail = custom(csLongOrderDetail);
		this.getSqlMapClientTemplate().update("updateCsLongOrderDetail", csLongOrderDetail);
		csLongOrderDetail = this.GetCsLongOrderDetailById(csLongOrderDetail.getCslodId());
		if(csLongOrderDetail!=null){
			MemCache.setValue(CsLongOrderDetail.class,csLongOrderDetail.getCslodId(), csLongOrderDetail.getKeyValue());
		}
		Trigger.on(CsLongOrderDetail.class,Trigger.UPDATE,csLongOrderDetail);
	}
	/**
	 * 更新长单明细非空字段
	 * @param csLongOrderDetail
	 */
	public void updateCsLongOrderDetail$NotNull(CsLongOrderDetail csLongOrderDetail)
	{
		csLongOrderDetail = custom(csLongOrderDetail);
		this.getSqlMapClientTemplate().update("updateCsLongOrderDetail$NotNull", csLongOrderDetail);
		csLongOrderDetail = this.GetCsLongOrderDetailById(csLongOrderDetail.getCslodId());
		if(csLongOrderDetail!=null){
			MemCache.setValue(CsLongOrderDetail.class,csLongOrderDetail.getCslodId(), csLongOrderDetail.getKeyValue());
		}
		Trigger.on(CsLongOrderDetail.class,Trigger.UPDATE,csLongOrderDetail);
	}

	/**
	 * 根据ID删除一个长单明细
	 * @param id
	 */
	public void deleteCsLongOrderDetailById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLongOrderDetail.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsLongOrderDetailById", params);
		MemCache.deleteValue(CsLongOrderDetail.class, id);
	}
	/**
	 * 根据ID逻辑删除长单明细 
	 * @param id
	 * @return
	 */
	public void removeCsLongOrderDetailById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsLongOrderDetail.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsLongOrderDetailById", params);
		MemCache.deleteValue(CsLongOrderDetail.class, id);
	}
	/**
	 * 根据条件更新长单明细
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderDetailByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsLongOrderDetailCount(params)>255){
			MemCache.clearValue(CsLongOrderDetail.class);
		}else{
			params.put("eval","cslod_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLongOrderDetailEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLongOrderDetail.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsLongOrderDetailBy", allParams);
		Trigger.on(CsLongOrderDetail.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除长单明细
	 * @param params
	 */
	public void deleteCsLongOrderDetailByConfirm(Map params){
		Trigger.on(CsLongOrderDetail.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsLongOrderDetailCount(params)>255){
			MemCache.clearValue(CsLongOrderDetail.class);
		}else{
			params.put("eval","cslod_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsLongOrderDetailEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsLongOrderDetail.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsLongOrderDetailBy", params);
	}
	

}