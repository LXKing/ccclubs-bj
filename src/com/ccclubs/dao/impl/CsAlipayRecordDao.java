package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsAlipayRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayRecord;
import com.lazy3q.web.helper.$;

/**
 * 接口充值的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsAlipayRecordDao extends SqlMapClientDaoSupport implements ICsAlipayRecordDao
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
	private CsAlipayRecord custom(CsAlipayRecord params){
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
	 * 获取所有接口充值
	 * @return
	 */	
	public List<CsAlipayRecord> getCsAlipayRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsAlipayRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsAlipayRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取接口充值统计
	 * @return
	 */
	public List getCsAlipayRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsAlipayRecordStats", params);
	}

	/**
	 * 获取接口充值分页
	 * @return
	 */
	public Page getCsAlipayRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsAlipayRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsAlipayRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取接口充值数量
	 * @return
	 */	
	public Long getCsAlipayRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取接口充值自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsAlipayRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取接口充值
	 * @param params
	 * @return
	 */
	public CsAlipayRecord getCsAlipayRecord(Map params)
	{		
		params = custom(params);
			
		List<CsAlipayRecord> list=this.getCsAlipayRecordList(params,1);
		CsAlipayRecord csAlipayRecord = list.isEmpty()?null:list.get(0);
		
		return csAlipayRecord;
	}
		
	/**
	 * 根据ID取接口充值
	 * @param id
	 * @return
	 */
	public CsAlipayRecord getCsAlipayRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsAlipayRecord csAlipayRecord = null;
		if(csAlipayRecord!=null)
			return csAlipayRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csAlipayRecord = (CsAlipayRecord) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRecordById",params);
		if(csAlipayRecord!=null)
			MemCache.setValue(CsAlipayRecord.class,id, csAlipayRecord.getKeyValue());
		return csAlipayRecord;
	}
	
	/**
	 * 根据ID取接口充值(不缓存)
	 * @param id
	 * @return
	 */
	public CsAlipayRecord GetCsAlipayRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsAlipayRecord) this.getSqlMapClientTemplate().queryForObject("getCsAlipayRecordById",params);
	}
	

	/**
	 * 保存接口充值
	 * @param csAlipayRecord
	 */
	public CsAlipayRecord saveCsAlipayRecord(CsAlipayRecord csAlipayRecord)
	{
		csAlipayRecord = custom(csAlipayRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsAlipayRecord", csAlipayRecord));
		csAlipayRecord = this.GetCsAlipayRecordById(id);
		MemCache.setValue(CsAlipayRecord.class,id, csAlipayRecord.getKeyValue());
		return Trigger.on(CsAlipayRecord.class,Trigger.SAVE,csAlipayRecord);	
	}

	

	/**
	 * 更新接口充值
	 * @param csAlipayRecord
	 */
	public void updateCsAlipayRecord(CsAlipayRecord csAlipayRecord)
	{
		csAlipayRecord = custom(csAlipayRecord);
		this.getSqlMapClientTemplate().update("updateCsAlipayRecord", csAlipayRecord);
		csAlipayRecord = this.GetCsAlipayRecordById(csAlipayRecord.getCsarId());
		if(csAlipayRecord!=null){
			MemCache.setValue(CsAlipayRecord.class,csAlipayRecord.getCsarId(), csAlipayRecord.getKeyValue());
		}
		Trigger.on(CsAlipayRecord.class,Trigger.UPDATE,csAlipayRecord);
	}
	/**
	 * 更新接口充值非空字段
	 * @param csAlipayRecord
	 */
	public void updateCsAlipayRecord$NotNull(CsAlipayRecord csAlipayRecord)
	{
		csAlipayRecord = custom(csAlipayRecord);
		this.getSqlMapClientTemplate().update("updateCsAlipayRecord$NotNull", csAlipayRecord);
		csAlipayRecord = this.GetCsAlipayRecordById(csAlipayRecord.getCsarId());
		if(csAlipayRecord!=null){
			MemCache.setValue(CsAlipayRecord.class,csAlipayRecord.getCsarId(), csAlipayRecord.getKeyValue());
		}
		Trigger.on(CsAlipayRecord.class,Trigger.UPDATE,csAlipayRecord);
	}

	/**
	 * 根据ID删除一个接口充值
	 * @param id
	 */
	public void deleteCsAlipayRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsAlipayRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsAlipayRecordById", params);
		MemCache.deleteValue(CsAlipayRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除接口充值 
	 * @param id
	 * @return
	 */
	public void removeCsAlipayRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsAlipayRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsAlipayRecordById", params);
		MemCache.deleteValue(CsAlipayRecord.class, id);
	}
	/**
	 * 根据条件更新接口充值
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsAlipayRecordCount(params)>255){
			MemCache.clearValue(CsAlipayRecord.class);
		}else{
			params.put("eval","csar_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAlipayRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsAlipayRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsAlipayRecordBy", allParams);
		Trigger.on(CsAlipayRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除接口充值
	 * @param params
	 */
	public void deleteCsAlipayRecordByConfirm(Map params){
		Trigger.on(CsAlipayRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsAlipayRecordCount(params)>255){
			MemCache.clearValue(CsAlipayRecord.class);
		}else{
			params.put("eval","csar_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsAlipayRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsAlipayRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsAlipayRecordBy", params);
	}
	

}