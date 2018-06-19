package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsBugDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsBug;
import com.lazy3q.web.helper.$;

/**
 * 系统BUG的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsBugDao extends SqlMapClientDaoSupport implements ICsBugDao
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
	private CsBug custom(CsBug params){
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
			"csbTitle"			
			,"csbDescript"			
			,"csbRemark"			
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
	 * 获取所有系统BUG
	 * @return
	 */	
	public List<CsBug> getCsBugList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsBug> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsBugList", params);
		
		return list;
	}
	
	
	/**
	 * 获取系统BUG统计
	 * @return
	 */
	public List getCsBugStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsBugStats", params);
	}

	/**
	 * 获取系统BUG分页
	 * @return
	 */
	public Page getCsBugPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsBugCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsBugPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取系统BUG数量
	 * @return
	 */	
	public Long getCsBugCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsBugEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取系统BUG自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsBugEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsBugEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取系统BUG
	 * @param params
	 * @return
	 */
	public CsBug getCsBug(Map params)
	{		
		params = custom(params);
			
		List<CsBug> list=this.getCsBugList(params,1);
		CsBug csBug = list.isEmpty()?null:list.get(0);
		
		return csBug;
	}
		
	/**
	 * 根据ID取系统BUG
	 * @param id
	 * @return
	 */
	public CsBug getCsBugById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsBug csBug = null;
		if(csBug!=null)
			return csBug;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csBug = (CsBug) this.getSqlMapClientTemplate().queryForObject("getCsBugById",params);
		if(csBug!=null)
			MemCache.setValue(CsBug.class,id, csBug.getKeyValue());
		return csBug;
	}
	
	/**
	 * 根据ID取系统BUG(不缓存)
	 * @param id
	 * @return
	 */
	public CsBug GetCsBugById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsBug) this.getSqlMapClientTemplate().queryForObject("getCsBugById",params);
	}
	

	/**
	 * 保存系统BUG
	 * @param csBug
	 */
	public CsBug saveCsBug(CsBug csBug)
	{
		csBug = custom(csBug);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsBug", csBug));
		csBug = this.GetCsBugById(id);
		MemCache.setValue(CsBug.class,id, csBug.getKeyValue());
		return Trigger.on(CsBug.class,Trigger.SAVE,csBug);	
	}

	

	/**
	 * 更新系统BUG
	 * @param csBug
	 */
	public void updateCsBug(CsBug csBug)
	{
		csBug = custom(csBug);
		this.getSqlMapClientTemplate().update("updateCsBug", csBug);
		csBug = this.GetCsBugById(csBug.getCsbId());
		if(csBug!=null){
			MemCache.setValue(CsBug.class,csBug.getCsbId(), csBug.getKeyValue());
		}
		Trigger.on(CsBug.class,Trigger.UPDATE,csBug);
	}
	/**
	 * 更新系统BUG非空字段
	 * @param csBug
	 */
	public void updateCsBug$NotNull(CsBug csBug)
	{
		csBug = custom(csBug);
		this.getSqlMapClientTemplate().update("updateCsBug$NotNull", csBug);
		csBug = this.GetCsBugById(csBug.getCsbId());
		if(csBug!=null){
			MemCache.setValue(CsBug.class,csBug.getCsbId(), csBug.getKeyValue());
		}
		Trigger.on(CsBug.class,Trigger.UPDATE,csBug);
	}

	/**
	 * 根据ID删除一个系统BUG
	 * @param id
	 */
	public void deleteCsBugById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsBug.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsBugById", params);
		MemCache.deleteValue(CsBug.class, id);
	}
	/**
	 * 根据ID逻辑删除系统BUG 
	 * @param id
	 * @return
	 */
	public void removeCsBugById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsBug.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsBugById", params);
		MemCache.deleteValue(CsBug.class, id);
	}
	/**
	 * 根据条件更新系统BUG
	 * @param values
	 * @param params
	 */
	public void updateCsBugByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsBugCount(params)>255){
			MemCache.clearValue(CsBug.class);
		}else{
			params.put("eval","csb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsBugEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsBug.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsBugBy", allParams);
		Trigger.on(CsBug.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除系统BUG
	 * @param params
	 */
	public void deleteCsBugByConfirm(Map params){
		Trigger.on(CsBug.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsBugCount(params)>255){
			MemCache.clearValue(CsBug.class);
		}else{
			params.put("eval","csb_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsBugEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsBug.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsBugBy", params);
	}
	

}