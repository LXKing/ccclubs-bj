package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCashRecordDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCashRecord;
import com.lazy3q.web.helper.$;

/**
 * 提现记录的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCashRecordDao extends SqlMapClientDaoSupport implements ICsCashRecordDao
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
	private CsCashRecord custom(CsCashRecord params){
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
			,"cscrVoucher"			
			,"cscrRemark"			
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
	 * 获取所有提现记录
	 * @return
	 */	
	public List<CsCashRecord> getCsCashRecordList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCashRecord> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCashRecordList", params);
		
		return list;
	}
	
	
	/**
	 * 获取提现记录统计
	 * @return
	 */
	public List getCsCashRecordStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCashRecordStats", params);
	}

	/**
	 * 获取提现记录分页
	 * @return
	 */
	public Page getCsCashRecordPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCashRecordCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCashRecordPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取提现记录数量
	 * @return
	 */	
	public Long getCsCashRecordCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCashRecordEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取提现记录自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCashRecordEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCashRecordEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取提现记录
	 * @param params
	 * @return
	 */
	public CsCashRecord getCsCashRecord(Map params)
	{		
		params = custom(params);
			
		List<CsCashRecord> list=this.getCsCashRecordList(params,1);
		CsCashRecord csCashRecord = list.isEmpty()?null:list.get(0);
		
		return csCashRecord;
	}
		
	/**
	 * 根据ID取提现记录
	 * @param id
	 * @return
	 */
	public CsCashRecord getCsCashRecordById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCashRecord csCashRecord = null;
		if(csCashRecord!=null)
			return csCashRecord;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCashRecord = (CsCashRecord) this.getSqlMapClientTemplate().queryForObject("getCsCashRecordById",params);
		if(csCashRecord!=null)
			MemCache.setValue(CsCashRecord.class,id, csCashRecord.getKeyValue());
		return csCashRecord;
	}
	
	/**
	 * 根据ID取提现记录(不缓存)
	 * @param id
	 * @return
	 */
	public CsCashRecord GetCsCashRecordById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCashRecord) this.getSqlMapClientTemplate().queryForObject("getCsCashRecordById",params);
	}
	

	/**
	 * 保存提现记录
	 * @param csCashRecord
	 */
	public CsCashRecord saveCsCashRecord(CsCashRecord csCashRecord)
	{
		csCashRecord = custom(csCashRecord);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCashRecord", csCashRecord));
		csCashRecord = this.GetCsCashRecordById(id);
		MemCache.setValue(CsCashRecord.class,id, csCashRecord.getKeyValue());
		return Trigger.on(CsCashRecord.class,Trigger.SAVE,csCashRecord);	
	}

	

	/**
	 * 更新提现记录
	 * @param csCashRecord
	 */
	public void updateCsCashRecord(CsCashRecord csCashRecord)
	{
		csCashRecord = custom(csCashRecord);
		this.getSqlMapClientTemplate().update("updateCsCashRecord", csCashRecord);
		csCashRecord = this.GetCsCashRecordById(csCashRecord.getCscrId());
		if(csCashRecord!=null){
			MemCache.setValue(CsCashRecord.class,csCashRecord.getCscrId(), csCashRecord.getKeyValue());
		}
		Trigger.on(CsCashRecord.class,Trigger.UPDATE,csCashRecord);
	}
	/**
	 * 更新提现记录非空字段
	 * @param csCashRecord
	 */
	public void updateCsCashRecord$NotNull(CsCashRecord csCashRecord)
	{
		csCashRecord = custom(csCashRecord);
		this.getSqlMapClientTemplate().update("updateCsCashRecord$NotNull", csCashRecord);
		csCashRecord = this.GetCsCashRecordById(csCashRecord.getCscrId());
		if(csCashRecord!=null){
			MemCache.setValue(CsCashRecord.class,csCashRecord.getCscrId(), csCashRecord.getKeyValue());
		}
		Trigger.on(CsCashRecord.class,Trigger.UPDATE,csCashRecord);
	}

	/**
	 * 根据ID删除一个提现记录
	 * @param id
	 */
	public void deleteCsCashRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCashRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCashRecordById", params);
		MemCache.deleteValue(CsCashRecord.class, id);
	}
	/**
	 * 根据ID逻辑删除提现记录 
	 * @param id
	 * @return
	 */
	public void removeCsCashRecordById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCashRecord.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsCashRecordById", params);
		MemCache.deleteValue(CsCashRecord.class, id);
	}
	/**
	 * 根据条件更新提现记录
	 * @param values
	 * @param params
	 */
	public void updateCsCashRecordByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCashRecordCount(params)>255){
			MemCache.clearValue(CsCashRecord.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCashRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCashRecord.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCashRecordBy", allParams);
		Trigger.on(CsCashRecord.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除提现记录
	 * @param params
	 */
	public void deleteCsCashRecordByConfirm(Map params){
		Trigger.on(CsCashRecord.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCashRecordCount(params)>255){
			MemCache.clearValue(CsCashRecord.class);
		}else{
			params.put("eval","cscr_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCashRecordEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCashRecord.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCashRecordBy", params);
	}
	

}