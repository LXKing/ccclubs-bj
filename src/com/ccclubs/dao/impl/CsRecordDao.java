package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRecord;
import com.lazy3q.web.helper.$;

/**
 * 消费记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsRecordDao extends SqlMapClientDaoSupport implements ICsRecordDao
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
	private CsRecord custom(CsRecord params){
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
	 * 获取所有消费记录
	 * @return
	 */	
	public List<CsRecord> getCsRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取消费记录统计
	 * @return
	 */
	public List getCsRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsRecordStats", params);
	}

	/**
	 * 获取消费记录分页
	 * @return
	 */
	public Page getCsRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取消费记录数量
	 * @return
	 */	
	public Long getCsRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取消费记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取消费记录
	 * @param params
	 * @return
	 */
	public CsRecord getCsRecord(Map params)
	{		
		params = custom(params);
			
		List<CsRecord> list=this.getCsRecordList(params,1);
		CsRecord csRecord = list.isEmpty()?null:list.get(0);
		
		return csRecord;
	}
		
	/**
	 * 根据ID取消费记录
	 * @param id
	 * @return
	 */
	public CsRecord getCsRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsRecord csRecord = null;
		if(csRecord!=null)
			return csRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csRecord = (CsRecord) this.getSqlMapClientTemplate().queryForObject("getCsRecordById",params);
		if(csRecord!=null)
			MemCache.setValue(CsRecord.class,id, csRecord.getKeyValue());
		return csRecord;
	}
	
	/**
	 * 根据ID取消费记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsRecord GetCsRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsRecord) this.getSqlMapClientTemplate().queryForObject("getCsRecordById",params);
	}
	

	/**
	 * 保存消费记录
	 * @param csRecord
	 */
	public CsRecord saveCsRecord(CsRecord csRecord)
	{
		csRecord = custom(csRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsRecord", csRecord));
		csRecord = this.GetCsRecordById(id);
		MemCache.setValue(CsRecord.class,id, csRecord.getKeyValue());
		return Trigger.on(CsRecord.class,Trigger.SAVE,csRecord);	
	}

	

	/**
	 * 更新消费记录
	 * @param csRecord
	 */
	public void updateCsRecord(CsRecord csRecord)
	{
		csRecord = custom(csRecord);
		this.getSqlMapClientTemplate().update("updateCsRecord", csRecord);
		csRecord = this.GetCsRecordById(csRecord.getCsrId());
		if(csRecord!=null){
			MemCache.setValue(CsRecord.class,csRecord.getCsrId(), csRecord.getKeyValue());
		}
		Trigger.on(CsRecord.class,Trigger.UPDATE,csRecord);
	}
	/**
	 * 更新消费记录非空字段
	 * @param csRecord
	 */
	public void updateCsRecord$NotNull(CsRecord csRecord)
	{
		csRecord = custom(csRecord);
		this.getSqlMapClientTemplate().update("updateCsRecord$NotNull", csRecord);
		csRecord = this.GetCsRecordById(csRecord.getCsrId());
		if(csRecord!=null){
			MemCache.setValue(CsRecord.class,csRecord.getCsrId(), csRecord.getKeyValue());
		}
		Trigger.on(CsRecord.class,Trigger.UPDATE,csRecord);
	}

	/**
	 * 根据ID删除一个消费记录
	 * @param id
	 */
	public void deleteCsRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsRecordById", params);
		MemCache.deleteValue(CsRecord.class, id);
	}
	/**
	 * 根据条件更新消费记录
	 * @param values
	 * @param params
	 */
	public void updateCsRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsRecordCount(params)>255){
			MemCache.clearValue(CsRecord.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsRecordBy", allParams);
		Trigger.on(CsRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除消费记录
	 * @param params
	 */
	public void deleteCsRecordByConfirm(Map params){
		Trigger.on(CsRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsRecordCount(params)>255){
			MemCache.clearValue(CsRecord.class);
		}else{
			params.put("eval","csr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsRecordBy", params);
	}
	

}