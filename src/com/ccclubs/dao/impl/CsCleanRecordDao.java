package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCleanRecordDao;
import com.ccclubs.model.CsCleanRecord;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

/**
 * 保洁记录的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsCleanRecordDao extends SqlMapClientDaoSupport implements ICsCleanRecordDao
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
	private CsCleanRecord custom(CsCleanRecord params){
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
		"cscrId","cscrItemId","cscrCar","cscrItemName","cscrUser","cscrSelection","cscrRemark","cscrAddtime","cscrUpdatetime","cscrStatus"
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
	 * 获取所有保洁记录
	 * @return
	 */	
	public List<CsCleanRecord> getCsCleanRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCleanRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCleanRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取保洁记录统计
	 * @return
	 */
	public List getCsCleanRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCleanRecordStats", params);
	}

	/**
	 * 获取保洁记录分页
	 * @return
	 */
	public Page getCsCleanRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCleanRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCleanRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取保洁记录数量
	 * @return
	 */	
	public Long getCsCleanRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCleanRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取保洁记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCleanRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCleanRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取保洁记录
	 * @param params
	 * @return
	 */
	public CsCleanRecord getCsCleanRecord(Map params)
	{		
		params = custom(params);
			
		List<CsCleanRecord> list=this.getCsCleanRecordList(params,1);
		CsCleanRecord csCleanRecord = list.isEmpty()?null:list.get(0);
		
		return csCleanRecord;
	}
		
	/**
	 * 根据ID取保洁记录
	 * @param id
	 * @return
	 */
	public CsCleanRecord getCsCleanRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCleanRecord csCleanRecord = null;
		if(csCleanRecord!=null)
			return csCleanRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCleanRecord = (CsCleanRecord) this.getSqlMapClientTemplate().queryForObject("getCsCleanRecordById",params);
		if(csCleanRecord!=null)
			MemCache.setValue(CsCleanRecord.class,id, csCleanRecord.getKeyValue());
		return csCleanRecord;
	}
	
	/**
	 * 根据ID取保洁记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsCleanRecord GetCsCleanRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCleanRecord) this.getSqlMapClientTemplate().queryForObject("getCsCleanRecordById",params);
	}
	

	/**
	 * 保存保洁记录
	 * @param csCleanRecord
	 */
	public CsCleanRecord saveCsCleanRecord(CsCleanRecord csCleanRecord)
	{
		csCleanRecord = custom(csCleanRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCleanRecord", csCleanRecord));
		csCleanRecord = this.GetCsCleanRecordById(id);
		MemCache.setValue(CsCleanRecord.class,id, csCleanRecord.getKeyValue());
		return Trigger.on(CsCleanRecord.class,Trigger.SAVE,csCleanRecord);	
	}

	

	/**
	 * 更新保洁记录
	 * @param csCleanRecord
	 */
	public void updateCsCleanRecord(CsCleanRecord csCleanRecord)
	{
		csCleanRecord = custom(csCleanRecord);
		this.getSqlMapClientTemplate().update("updateCsCleanRecord", csCleanRecord);
		csCleanRecord = this.GetCsCleanRecordById(csCleanRecord.getCscrId());
		if(csCleanRecord!=null){
			MemCache.setValue(CsCleanRecord.class,csCleanRecord.getCscrId(), csCleanRecord.getKeyValue());
		}
		Trigger.on(CsCleanRecord.class,Trigger.UPDATE,csCleanRecord);
	}
	/**
	 * 更新保洁记录非空字段
	 * @param csCleanRecord
	 */
	public void updateCsCleanRecord$NotNull(CsCleanRecord csCleanRecord)
	{
		csCleanRecord = custom(csCleanRecord);
		this.getSqlMapClientTemplate().update("updateCsCleanRecord$NotNull", csCleanRecord);
		csCleanRecord = this.GetCsCleanRecordById(csCleanRecord.getCscrId());
		if(csCleanRecord!=null){
			MemCache.setValue(CsCleanRecord.class,csCleanRecord.getCscrId(), csCleanRecord.getKeyValue());
		}
		Trigger.on(CsCleanRecord.class,Trigger.UPDATE,csCleanRecord);
	}

	/**
	 * 根据ID删除一个保洁记录
	 * @param id
	 */
	public void deleteCsCleanRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCleanRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCleanRecordById", params);
		MemCache.deleteValue(CsCleanRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除保洁记录 
	 * @param id
	 * @return
	 */
	public void removeCsCleanRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCleanRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCleanRecordById", params);
		MemCache.deleteValue(CsCleanRecord.class, id);
	}
	/**
	 * 根据条件更新保洁记录
	 * @param values
	 * @param params
	 */
	public void updateCsCleanRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCleanRecordCount(params)>255){
			MemCache.clearValue(CsCleanRecord.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCleanRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCleanRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCleanRecordBy", allParams);
		Trigger.on(CsCleanRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除保洁记录
	 * @param params
	 */
	public void deleteCsCleanRecordByConfirm(Map params){
		Trigger.on(CsCleanRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCleanRecordCount(params)>255){
			MemCache.clearValue(CsCleanRecord.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCleanRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCleanRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCleanRecordBy", params);
	}
	

}