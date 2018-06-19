package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCreditRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditRecord;
import com.lazy3q.web.helper.$;

/**
 * 信用记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCreditRecordDao extends SqlMapClientDaoSupport implements ICsCreditRecordDao
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
	private CsCreditRecord custom(CsCreditRecord params){
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
			"cscrTitle"			
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
	 * 获取所有信用记录
	 * @return
	 */	
	public List<CsCreditRecord> getCsCreditRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCreditRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCreditRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取信用记录统计
	 * @return
	 */
	public List getCsCreditRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCreditRecordStats", params);
	}

	/**
	 * 获取信用记录分页
	 * @return
	 */
	public Page getCsCreditRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCreditRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCreditRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取信用记录数量
	 * @return
	 */	
	public Long getCsCreditRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCreditRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取信用记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCreditRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCreditRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取信用记录
	 * @param params
	 * @return
	 */
	public CsCreditRecord getCsCreditRecord(Map params)
	{		
		params = custom(params);
			
		List<CsCreditRecord> list=this.getCsCreditRecordList(params,1);
		CsCreditRecord csCreditRecord = list.isEmpty()?null:list.get(0);
		
		return csCreditRecord;
	}
		
	/**
	 * 根据ID取信用记录
	 * @param id
	 * @return
	 */
	public CsCreditRecord getCsCreditRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCreditRecord csCreditRecord = null;
		if(csCreditRecord!=null)
			return csCreditRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCreditRecord = (CsCreditRecord) this.getSqlMapClientTemplate().queryForObject("getCsCreditRecordById",params);
		if(csCreditRecord!=null)
			MemCache.setValue(CsCreditRecord.class,id, csCreditRecord.getKeyValue());
		return csCreditRecord;
	}
	
	/**
	 * 根据ID取信用记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsCreditRecord GetCsCreditRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCreditRecord) this.getSqlMapClientTemplate().queryForObject("getCsCreditRecordById",params);
	}
	

	/**
	 * 保存信用记录
	 * @param csCreditRecord
	 */
	public CsCreditRecord saveCsCreditRecord(CsCreditRecord csCreditRecord)
	{
		csCreditRecord = custom(csCreditRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCreditRecord", csCreditRecord));
		csCreditRecord = this.GetCsCreditRecordById(id);
		MemCache.setValue(CsCreditRecord.class,id, csCreditRecord.getKeyValue());
		return Trigger.on(CsCreditRecord.class,Trigger.SAVE,csCreditRecord);	
	}

	

	/**
	 * 更新信用记录
	 * @param csCreditRecord
	 */
	public void updateCsCreditRecord(CsCreditRecord csCreditRecord)
	{
		csCreditRecord = custom(csCreditRecord);
		this.getSqlMapClientTemplate().update("updateCsCreditRecord", csCreditRecord);
		csCreditRecord = this.GetCsCreditRecordById(csCreditRecord.getCscrId());
		if(csCreditRecord!=null){
			MemCache.setValue(CsCreditRecord.class,csCreditRecord.getCscrId(), csCreditRecord.getKeyValue());
		}
		Trigger.on(CsCreditRecord.class,Trigger.UPDATE,csCreditRecord);
	}
	/**
	 * 更新信用记录非空字段
	 * @param csCreditRecord
	 */
	public void updateCsCreditRecord$NotNull(CsCreditRecord csCreditRecord)
	{
		csCreditRecord = custom(csCreditRecord);
		this.getSqlMapClientTemplate().update("updateCsCreditRecord$NotNull", csCreditRecord);
		csCreditRecord = this.GetCsCreditRecordById(csCreditRecord.getCscrId());
		if(csCreditRecord!=null){
			MemCache.setValue(CsCreditRecord.class,csCreditRecord.getCscrId(), csCreditRecord.getKeyValue());
		}
		Trigger.on(CsCreditRecord.class,Trigger.UPDATE,csCreditRecord);
	}

	/**
	 * 根据ID删除一个信用记录
	 * @param id
	 */
	public void deleteCsCreditRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCreditRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCreditRecordById", params);
		MemCache.deleteValue(CsCreditRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除信用记录 
	 * @param id
	 * @return
	 */
	public void removeCsCreditRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCreditRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCreditRecordById", params);
		MemCache.deleteValue(CsCreditRecord.class, id);
	}
	/**
	 * 根据条件更新信用记录
	 * @param values
	 * @param params
	 */
	public void updateCsCreditRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCreditRecordCount(params)>255){
			MemCache.clearValue(CsCreditRecord.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCreditRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCreditRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCreditRecordBy", allParams);
		Trigger.on(CsCreditRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除信用记录
	 * @param params
	 */
	public void deleteCsCreditRecordByConfirm(Map params){
		Trigger.on(CsCreditRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCreditRecordCount(params)>255){
			MemCache.clearValue(CsCreditRecord.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCreditRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCreditRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCreditRecordBy", params);
	}
	

}