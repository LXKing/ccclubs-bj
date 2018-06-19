package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsIntegralRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsIntegralRecord;
import com.lazy3q.web.helper.$;

/**
 * 积分记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsIntegralRecordDao extends SqlMapClientDaoSupport implements ICsIntegralRecordDao
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
	private CsIntegralRecord custom(CsIntegralRecord params){
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
			"csrRemark"			
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
	 * 获取所有积分记录
	 * @return
	 */	
	public List<CsIntegralRecord> getCsIntegralRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsIntegralRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsIntegralRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取积分记录统计
	 * @return
	 */
	public List getCsIntegralRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsIntegralRecordStats", params);
	}

	/**
	 * 获取积分记录分页
	 * @return
	 */
	public Page getCsIntegralRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsIntegralRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsIntegralRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取积分记录数量
	 * @return
	 */	
	public Long getCsIntegralRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取积分记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsIntegralRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取积分记录
	 * @param params
	 * @return
	 */
	public CsIntegralRecord getCsIntegralRecord(Map params)
	{		
		params = custom(params);
			
		List<CsIntegralRecord> list=this.getCsIntegralRecordList(params,1);
		CsIntegralRecord csIntegralRecord = list.isEmpty()?null:list.get(0);
		
		return csIntegralRecord;
	}
		
	/**
	 * 根据ID取积分记录
	 * @param id
	 * @return
	 */
	public CsIntegralRecord getCsIntegralRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsIntegralRecord csIntegralRecord = null;
		if(csIntegralRecord!=null)
			return csIntegralRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csIntegralRecord = (CsIntegralRecord) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRecordById",params);
		if(csIntegralRecord!=null)
			MemCache.setValue(CsIntegralRecord.class,id, csIntegralRecord.getKeyValue());
		return csIntegralRecord;
	}
	
	/**
	 * 根据ID取积分记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsIntegralRecord GetCsIntegralRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsIntegralRecord) this.getSqlMapClientTemplate().queryForObject("getCsIntegralRecordById",params);
	}
	

	/**
	 * 保存积分记录
	 * @param csIntegralRecord
	 */
	public CsIntegralRecord saveCsIntegralRecord(CsIntegralRecord csIntegralRecord)
	{
		csIntegralRecord = custom(csIntegralRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsIntegralRecord", csIntegralRecord));
		csIntegralRecord = this.GetCsIntegralRecordById(id);
		MemCache.setValue(CsIntegralRecord.class,id, csIntegralRecord.getKeyValue());
		return Trigger.on(CsIntegralRecord.class,Trigger.SAVE,csIntegralRecord);	
	}

	

	/**
	 * 更新积分记录
	 * @param csIntegralRecord
	 */
	public void updateCsIntegralRecord(CsIntegralRecord csIntegralRecord)
	{
		csIntegralRecord = custom(csIntegralRecord);
		this.getSqlMapClientTemplate().update("updateCsIntegralRecord", csIntegralRecord);
		csIntegralRecord = this.GetCsIntegralRecordById(csIntegralRecord.getCsrId());
		if(csIntegralRecord!=null){
			MemCache.setValue(CsIntegralRecord.class,csIntegralRecord.getCsrId(), csIntegralRecord.getKeyValue());
		}
		Trigger.on(CsIntegralRecord.class,Trigger.UPDATE,csIntegralRecord);
	}
	/**
	 * 更新积分记录非空字段
	 * @param csIntegralRecord
	 */
	public void updateCsIntegralRecord$NotNull(CsIntegralRecord csIntegralRecord)
	{
		csIntegralRecord = custom(csIntegralRecord);
		this.getSqlMapClientTemplate().update("updateCsIntegralRecord$NotNull", csIntegralRecord);
		csIntegralRecord = this.GetCsIntegralRecordById(csIntegralRecord.getCsrId());
		if(csIntegralRecord!=null){
			MemCache.setValue(CsIntegralRecord.class,csIntegralRecord.getCsrId(), csIntegralRecord.getKeyValue());
		}
		Trigger.on(CsIntegralRecord.class,Trigger.UPDATE,csIntegralRecord);
	}

	/**
	 * 根据ID删除一个积分记录
	 * @param id
	 */
	public void deleteCsIntegralRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsIntegralRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsIntegralRecordById", params);
		MemCache.deleteValue(CsIntegralRecord.class, id);
	}
	/**
	 * 根据条件更新积分记录
	 * @param values
	 * @param params
	 */
	public void updateCsIntegralRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsIntegralRecordCount(params)>255){
			MemCache.clearValue(CsIntegralRecord.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsIntegralRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsIntegralRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsIntegralRecordBy", allParams);
		Trigger.on(CsIntegralRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除积分记录
	 * @param params
	 */
	public void deleteCsIntegralRecordByConfirm(Map params){
		Trigger.on(CsIntegralRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsIntegralRecordCount(params)>255){
			MemCache.clearValue(CsIntegralRecord.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsIntegralRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsIntegralRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsIntegralRecordBy", params);
	}
	

}