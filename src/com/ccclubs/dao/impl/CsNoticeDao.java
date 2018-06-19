package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsNoticeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsNotice;
import com.lazy3q.web.helper.$;

/**
 * 系统通知的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsNoticeDao extends SqlMapClientDaoSupport implements ICsNoticeDao
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
	private CsNotice custom(CsNotice params){
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
			"csnContent"			
			,"csnRemark"			
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
	 * 获取所有系统通知
	 * @return
	 */	
	public List<CsNotice> getCsNoticeList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsNotice> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsNoticeList", params);
		
		return list;
	}
	
	
	/**
	 * 获取系统通知统计
	 * @return
	 */
	public List getCsNoticeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsNoticeStats", params);
	}

	/**
	 * 获取系统通知分页
	 * @return
	 */
	public Page getCsNoticePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsNoticeCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsNoticePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统通知数量
	 * @return
	 */	
	public Long getCsNoticeCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsNoticeEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统通知自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsNoticeEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsNoticeEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统通知
	 * @param params
	 * @return
	 */
	public CsNotice getCsNotice(Map params)
	{		
		params = custom(params);
			
		List<CsNotice> list=this.getCsNoticeList(params,1);
		CsNotice csNotice = list.isEmpty()?null:list.get(0);
		
		return csNotice;
	}
		
	/**
	 * 根据ID取系统通知
	 * @param id
	 * @return
	 */
	public CsNotice getCsNoticeById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsNotice csNotice = null;
		if(csNotice!=null)
			return csNotice;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csNotice = (CsNotice) this.getSqlMapClientTemplate().queryForObject("getCsNoticeById",params);
		if(csNotice!=null)
			MemCache.setValue(CsNotice.class,id, csNotice.getKeyValue());
		return csNotice;
	}
	
	/**
	 * 根据ID取系统通知(不缓存)
	 * @param id
	 * @return
	 */
	public CsNotice GetCsNoticeById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsNotice) this.getSqlMapClientTemplate().queryForObject("getCsNoticeById",params);
	}
	

	/**
	 * 保存系统通知
	 * @param csNotice
	 */
	public CsNotice saveCsNotice(CsNotice csNotice)
	{
		csNotice = custom(csNotice);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsNotice", csNotice));
		csNotice = this.GetCsNoticeById(id);
		MemCache.setValue(CsNotice.class,id, csNotice.getKeyValue());
		return Trigger.on(CsNotice.class,Trigger.SAVE,csNotice);	
	}

	

	/**
	 * 更新系统通知
	 * @param csNotice
	 */
	public void updateCsNotice(CsNotice csNotice)
	{
		csNotice = custom(csNotice);
		this.getSqlMapClientTemplate().update("updateCsNotice", csNotice);
		csNotice = this.GetCsNoticeById(csNotice.getCsnId());
		if(csNotice!=null){
			MemCache.setValue(CsNotice.class,csNotice.getCsnId(), csNotice.getKeyValue());
		}
		Trigger.on(CsNotice.class,Trigger.UPDATE,csNotice);
	}
	/**
	 * 更新系统通知非空字段
	 * @param csNotice
	 */
	public void updateCsNotice$NotNull(CsNotice csNotice)
	{
		csNotice = custom(csNotice);
		this.getSqlMapClientTemplate().update("updateCsNotice$NotNull", csNotice);
		csNotice = this.GetCsNoticeById(csNotice.getCsnId());
		if(csNotice!=null){
			MemCache.setValue(CsNotice.class,csNotice.getCsnId(), csNotice.getKeyValue());
		}
		Trigger.on(CsNotice.class,Trigger.UPDATE,csNotice);
	}

	/**
	 * 根据ID删除一个系统通知
	 * @param id
	 */
	public void deleteCsNoticeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsNotice.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsNoticeById", params);
		MemCache.deleteValue(CsNotice.class, id);
	}
	/**
	 * 根据ID逻辑删除系统通知 
	 * @param id
	 * @return
	 */
	public void removeCsNoticeById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsNotice.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsNoticeById", params);
		MemCache.deleteValue(CsNotice.class, id);
	}
	/**
	 * 根据条件更新系统通知
	 * @param values
	 * @param params
	 */
	public void updateCsNoticeByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsNoticeCount(params)>255){
			MemCache.clearValue(CsNotice.class);
		}else{
			params.put("eval","csn_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsNoticeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsNotice.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsNoticeBy", allParams);
		Trigger.on(CsNotice.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统通知
	 * @param params
	 */
	public void deleteCsNoticeByConfirm(Map params){
		Trigger.on(CsNotice.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsNoticeCount(params)>255){
			MemCache.clearValue(CsNotice.class);
		}else{
			params.put("eval","csn_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsNoticeEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsNotice.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsNoticeBy", params);
	}
	

}