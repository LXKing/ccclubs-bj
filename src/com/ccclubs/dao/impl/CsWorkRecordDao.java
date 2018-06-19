package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsWorkRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWorkRecord;
import com.lazy3q.web.helper.$;

/**
 * 后勤工作记录的Dao实现
 * @author Joel
 */
@SuppressWarnings("unchecked")
public class CsWorkRecordDao extends SqlMapClientDaoSupport implements ICsWorkRecordDao
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
	private CsWorkRecord custom(CsWorkRecord params){
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
		"cswrId","cswrHost","cswrOutlets","cswrCar","cswrUser","cswrItem","cswrItemname","cswrOperation","cswrData","cswrStatus","cswrAddTime","cswrUpdateTime"
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
	 * 获取所有后勤工作记录
	 * @return
	 */	
	public List<CsWorkRecord> getCsWorkRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsWorkRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsWorkRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取后勤工作记录统计
	 * @return
	 */
	public List getCsWorkRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsWorkRecordStats", params);
	}

	/**
	 * 获取后勤工作记录分页
	 * @return
	 */
	public Page getCsWorkRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsWorkRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsWorkRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取后勤工作记录数量
	 * @return
	 */	
	public Long getCsWorkRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWorkRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取后勤工作记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsWorkRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsWorkRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取后勤工作记录
	 * @param params
	 * @return
	 */
	public CsWorkRecord getCsWorkRecord(Map params)
	{		
		params = custom(params);
			
		List<CsWorkRecord> list=this.getCsWorkRecordList(params,1);
		CsWorkRecord csWorkRecord = list.isEmpty()?null:list.get(0);
		
		return csWorkRecord;
	}
		
	/**
	 * 根据ID取后勤工作记录
	 * @param id
	 * @return
	 */
	public CsWorkRecord getCsWorkRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsWorkRecord csWorkRecord = null;
		if(csWorkRecord!=null)
			return csWorkRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csWorkRecord = (CsWorkRecord) this.getSqlMapClientTemplate().queryForObject("getCsWorkRecordById",params);
		if(csWorkRecord!=null)
			MemCache.setValue(CsWorkRecord.class,id, csWorkRecord.getKeyValue());
		return csWorkRecord;
	}
	
	/**
	 * 根据ID取后勤工作记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsWorkRecord GetCsWorkRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsWorkRecord) this.getSqlMapClientTemplate().queryForObject("getCsWorkRecordById",params);
	}
	

	/**
	 * 保存后勤工作记录
	 * @param csWorkRecord
	 */
	public CsWorkRecord saveCsWorkRecord(CsWorkRecord csWorkRecord)
	{
		csWorkRecord = custom(csWorkRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsWorkRecord", csWorkRecord));
		csWorkRecord = this.GetCsWorkRecordById(id);
		MemCache.setValue(CsWorkRecord.class,id, csWorkRecord.getKeyValue());
		return Trigger.on(CsWorkRecord.class,Trigger.SAVE,csWorkRecord);	
	}

	

	/**
	 * 更新后勤工作记录
	 * @param csWorkRecord
	 */
	public void updateCsWorkRecord(CsWorkRecord csWorkRecord)
	{
		csWorkRecord = custom(csWorkRecord);
		this.getSqlMapClientTemplate().update("updateCsWorkRecord", csWorkRecord);
		csWorkRecord = this.GetCsWorkRecordById(csWorkRecord.getCswrId());
		if(csWorkRecord!=null){
			MemCache.setValue(CsWorkRecord.class,csWorkRecord.getCswrId(), csWorkRecord.getKeyValue());
		}
		Trigger.on(CsWorkRecord.class,Trigger.UPDATE,csWorkRecord);
	}
	/**
	 * 更新后勤工作记录非空字段
	 * @param csWorkRecord
	 */
	public void updateCsWorkRecord$NotNull(CsWorkRecord csWorkRecord)
	{
		csWorkRecord = custom(csWorkRecord);
		this.getSqlMapClientTemplate().update("updateCsWorkRecord$NotNull", csWorkRecord);
		csWorkRecord = this.GetCsWorkRecordById(csWorkRecord.getCswrId());
		if(csWorkRecord!=null){
			MemCache.setValue(CsWorkRecord.class,csWorkRecord.getCswrId(), csWorkRecord.getKeyValue());
		}
		Trigger.on(CsWorkRecord.class,Trigger.UPDATE,csWorkRecord);
	}

	/**
	 * 根据ID删除一个后勤工作记录
	 * @param id
	 */
	public void deleteCsWorkRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWorkRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsWorkRecordById", params);
		MemCache.deleteValue(CsWorkRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除后勤工作记录 
	 * @param id
	 * @return
	 */
	public void removeCsWorkRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsWorkRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsWorkRecordById", params);
		MemCache.deleteValue(CsWorkRecord.class, id);
	}
	/**
	 * 根据条件更新后勤工作记录
	 * @param values
	 * @param params
	 */
	public void updateCsWorkRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsWorkRecordCount(params)>255){
			MemCache.clearValue(CsWorkRecord.class);
		}else{
			params.put("eval","cswr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWorkRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWorkRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsWorkRecordBy", allParams);
		Trigger.on(CsWorkRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除后勤工作记录
	 * @param params
	 */
	public void deleteCsWorkRecordByConfirm(Map params){
		Trigger.on(CsWorkRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsWorkRecordCount(params)>255){
			MemCache.clearValue(CsWorkRecord.class);
		}else{
			params.put("eval","cswr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsWorkRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsWorkRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsWorkRecordBy", params);
	}
	

}