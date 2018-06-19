package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsArtifRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArtifRecord;
import com.lazy3q.web.helper.$;

/**
 * 充值扣款的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsArtifRecordDao extends SqlMapClientDaoSupport implements ICsArtifRecordDao
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
	private CsArtifRecord custom(CsArtifRecord params){
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
			"csarTitle"			
			,"csarRemark"			
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
	 * 获取所有充值扣款
	 * @return
	 */	
	public List<CsArtifRecord> getCsArtifRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsArtifRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsArtifRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取充值扣款统计
	 * @return
	 */
	public List getCsArtifRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsArtifRecordStats", params);
	}

	/**
	 * 获取充值扣款分页
	 * @return
	 */
	public Page getCsArtifRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsArtifRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsArtifRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取充值扣款数量
	 * @return
	 */	
	public Long getCsArtifRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsArtifRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取充值扣款自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsArtifRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsArtifRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取充值扣款
	 * @param params
	 * @return
	 */
	public CsArtifRecord getCsArtifRecord(Map params)
	{		
		params = custom(params);
			
		List<CsArtifRecord> list=this.getCsArtifRecordList(params,1);
		CsArtifRecord csArtifRecord = list.isEmpty()?null:list.get(0);
		
		return csArtifRecord;
	}
		
	/**
	 * 根据ID取充值扣款
	 * @param id
	 * @return
	 */
	public CsArtifRecord getCsArtifRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsArtifRecord csArtifRecord = null;
		if(csArtifRecord!=null)
			return csArtifRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csArtifRecord = (CsArtifRecord) this.getSqlMapClientTemplate().queryForObject("getCsArtifRecordById",params);
		if(csArtifRecord!=null)
			MemCache.setValue(CsArtifRecord.class,id, csArtifRecord.getKeyValue());
		return csArtifRecord;
	}
	
	/**
	 * 根据ID取充值扣款(不缓存)
	 * @param id
	 * @return
	 */
	public CsArtifRecord GetCsArtifRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsArtifRecord) this.getSqlMapClientTemplate().queryForObject("getCsArtifRecordById",params);
	}
	

	/**
	 * 保存充值扣款
	 * @param csArtifRecord
	 */
	public CsArtifRecord saveCsArtifRecord(CsArtifRecord csArtifRecord)
	{
		csArtifRecord = custom(csArtifRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsArtifRecord", csArtifRecord));
		csArtifRecord = this.GetCsArtifRecordById(id);
		MemCache.setValue(CsArtifRecord.class,id, csArtifRecord.getKeyValue());
		return Trigger.on(CsArtifRecord.class,Trigger.SAVE,csArtifRecord);	
	}

	

	/**
	 * 更新充值扣款
	 * @param csArtifRecord
	 */
	public void updateCsArtifRecord(CsArtifRecord csArtifRecord)
	{
		csArtifRecord = custom(csArtifRecord);
		this.getSqlMapClientTemplate().update("updateCsArtifRecord", csArtifRecord);
		csArtifRecord = this.GetCsArtifRecordById(csArtifRecord.getCsarId());
		if(csArtifRecord!=null){
			MemCache.setValue(CsArtifRecord.class,csArtifRecord.getCsarId(), csArtifRecord.getKeyValue());
		}
		Trigger.on(CsArtifRecord.class,Trigger.UPDATE,csArtifRecord);
	}
	/**
	 * 更新充值扣款非空字段
	 * @param csArtifRecord
	 */
	public void updateCsArtifRecord$NotNull(CsArtifRecord csArtifRecord)
	{
		csArtifRecord = custom(csArtifRecord);
		this.getSqlMapClientTemplate().update("updateCsArtifRecord$NotNull", csArtifRecord);
		csArtifRecord = this.GetCsArtifRecordById(csArtifRecord.getCsarId());
		if(csArtifRecord!=null){
			MemCache.setValue(CsArtifRecord.class,csArtifRecord.getCsarId(), csArtifRecord.getKeyValue());
		}
		Trigger.on(CsArtifRecord.class,Trigger.UPDATE,csArtifRecord);
	}

	/**
	 * 根据ID删除一个充值扣款
	 * @param id
	 */
	public void deleteCsArtifRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsArtifRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsArtifRecordById", params);
		MemCache.deleteValue(CsArtifRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除充值扣款 
	 * @param id
	 * @return
	 */
	public void removeCsArtifRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsArtifRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsArtifRecordById", params);
		MemCache.deleteValue(CsArtifRecord.class, id);
	}
	/**
	 * 根据条件更新充值扣款
	 * @param values
	 * @param params
	 */
	public void updateCsArtifRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsArtifRecordCount(params)>255){
			MemCache.clearValue(CsArtifRecord.class);
		}else{
			params.put("eval","csar_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsArtifRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsArtifRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsArtifRecordBy", allParams);
		Trigger.on(CsArtifRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除充值扣款
	 * @param params
	 */
	public void deleteCsArtifRecordByConfirm(Map params){
		Trigger.on(CsArtifRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsArtifRecordCount(params)>255){
			MemCache.clearValue(CsArtifRecord.class);
		}else{
			params.put("eval","csar_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsArtifRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsArtifRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsArtifRecordBy", params);
	}
	

}