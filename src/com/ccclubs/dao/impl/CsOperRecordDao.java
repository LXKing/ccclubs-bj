package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsOperRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOperRecord;
import com.lazy3q.web.helper.$;

/**
 * 收支记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsOperRecordDao extends SqlMapClientDaoSupport implements ICsOperRecordDao
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
	private CsOperRecord custom(CsOperRecord params){
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
			"csorTitle"			
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
	 * 获取所有收支记录
	 * @return
	 */	
	public List<CsOperRecord> getCsOperRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsOperRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsOperRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取收支记录统计
	 * @return
	 */
	public List getCsOperRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsOperRecordStats", params);
	}

	/**
	 * 获取收支记录分页
	 * @return
	 */
	public Page getCsOperRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsOperRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsOperRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取收支记录数量
	 * @return
	 */	
	public Long getCsOperRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOperRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取收支记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsOperRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsOperRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取收支记录
	 * @param params
	 * @return
	 */
	public CsOperRecord getCsOperRecord(Map params)
	{		
		params = custom(params);
			
		List<CsOperRecord> list=this.getCsOperRecordList(params,1);
		CsOperRecord csOperRecord = list.isEmpty()?null:list.get(0);
		
		return csOperRecord;
	}
		
	/**
	 * 根据ID取收支记录
	 * @param id
	 * @return
	 */
	public CsOperRecord getCsOperRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsOperRecord csOperRecord = null;
		if(csOperRecord!=null)
			return csOperRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csOperRecord = (CsOperRecord) this.getSqlMapClientTemplate().queryForObject("getCsOperRecordById",params);
		if(csOperRecord!=null)
			MemCache.setValue(CsOperRecord.class,id, csOperRecord.getKeyValue());
		return csOperRecord;
	}
	
	/**
	 * 根据ID取收支记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsOperRecord GetCsOperRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsOperRecord) this.getSqlMapClientTemplate().queryForObject("getCsOperRecordById",params);
	}
	

	/**
	 * 保存收支记录
	 * @param csOperRecord
	 */
	public CsOperRecord saveCsOperRecord(CsOperRecord csOperRecord)
	{
		csOperRecord = custom(csOperRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsOperRecord", csOperRecord));
		csOperRecord = this.GetCsOperRecordById(id);
		MemCache.setValue(CsOperRecord.class,id, csOperRecord.getKeyValue());
		return Trigger.on(CsOperRecord.class,Trigger.SAVE,csOperRecord);	
	}

	

	/**
	 * 更新收支记录
	 * @param csOperRecord
	 */
	public void updateCsOperRecord(CsOperRecord csOperRecord)
	{
		csOperRecord = custom(csOperRecord);
		this.getSqlMapClientTemplate().update("updateCsOperRecord", csOperRecord);
		csOperRecord = this.GetCsOperRecordById(csOperRecord.getCsorId());
		if(csOperRecord!=null){
			MemCache.setValue(CsOperRecord.class,csOperRecord.getCsorId(), csOperRecord.getKeyValue());
		}
		Trigger.on(CsOperRecord.class,Trigger.UPDATE,csOperRecord);
	}
	/**
	 * 更新收支记录非空字段
	 * @param csOperRecord
	 */
	public void updateCsOperRecord$NotNull(CsOperRecord csOperRecord)
	{
		csOperRecord = custom(csOperRecord);
		this.getSqlMapClientTemplate().update("updateCsOperRecord$NotNull", csOperRecord);
		csOperRecord = this.GetCsOperRecordById(csOperRecord.getCsorId());
		if(csOperRecord!=null){
			MemCache.setValue(CsOperRecord.class,csOperRecord.getCsorId(), csOperRecord.getKeyValue());
		}
		Trigger.on(CsOperRecord.class,Trigger.UPDATE,csOperRecord);
	}

	/**
	 * 根据ID删除一个收支记录
	 * @param id
	 */
	public void deleteCsOperRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOperRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsOperRecordById", params);
		MemCache.deleteValue(CsOperRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除收支记录 
	 * @param id
	 * @return
	 */
	public void removeCsOperRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsOperRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsOperRecordById", params);
		MemCache.deleteValue(CsOperRecord.class, id);
	}
	/**
	 * 根据条件更新收支记录
	 * @param values
	 * @param params
	 */
	public void updateCsOperRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsOperRecordCount(params)>255){
			MemCache.clearValue(CsOperRecord.class);
		}else{
			params.put("eval","csor_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOperRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOperRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsOperRecordBy", allParams);
		Trigger.on(CsOperRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除收支记录
	 * @param params
	 */
	public void deleteCsOperRecordByConfirm(Map params){
		Trigger.on(CsOperRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsOperRecordCount(params)>255){
			MemCache.clearValue(CsOperRecord.class);
		}else{
			params.put("eval","csor_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsOperRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsOperRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsOperRecordBy", params);
	}
	

}