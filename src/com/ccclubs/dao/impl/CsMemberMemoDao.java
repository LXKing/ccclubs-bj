package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMemberMemoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberMemo;
import com.lazy3q.web.helper.$;

/**
 * 会员备忘的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMemberMemoDao extends SqlMapClientDaoSupport implements ICsMemberMemoDao
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
	private CsMemberMemo custom(CsMemberMemo params){
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
			"csmmContent"			
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
	 * 获取所有会员备忘
	 * @return
	 */	
	public List<CsMemberMemo> getCsMemberMemoList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMemberMemo> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMemberMemoList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员备忘统计
	 * @return
	 */
	public List getCsMemberMemoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMemberMemoStats", params);
	}

	/**
	 * 获取会员备忘分页
	 * @return
	 */
	public Page getCsMemberMemoPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMemberMemoCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMemberMemoPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员备忘数量
	 * @return
	 */	
	public Long getCsMemberMemoCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberMemoEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员备忘自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMemberMemoEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberMemoEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员备忘
	 * @param params
	 * @return
	 */
	public CsMemberMemo getCsMemberMemo(Map params)
	{		
		params = custom(params);
			
		List<CsMemberMemo> list=this.getCsMemberMemoList(params,1);
		CsMemberMemo csMemberMemo = list.isEmpty()?null:list.get(0);
		
		return csMemberMemo;
	}
		
	/**
	 * 根据ID取会员备忘
	 * @param id
	 * @return
	 */
	public CsMemberMemo getCsMemberMemoById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMemberMemo csMemberMemo = null;
		if(csMemberMemo!=null)
			return csMemberMemo;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMemberMemo = (CsMemberMemo) this.getSqlMapClientTemplate().queryForObject("getCsMemberMemoById",params);
		if(csMemberMemo!=null)
			MemCache.setValue(CsMemberMemo.class,id, csMemberMemo.getKeyValue());
		return csMemberMemo;
	}
	
	/**
	 * 根据ID取会员备忘(不缓存)
	 * @param id
	 * @return
	 */
	public CsMemberMemo GetCsMemberMemoById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMemberMemo) this.getSqlMapClientTemplate().queryForObject("getCsMemberMemoById",params);
	}
	

	/**
	 * 保存会员备忘
	 * @param csMemberMemo
	 */
	public CsMemberMemo saveCsMemberMemo(CsMemberMemo csMemberMemo)
	{
		csMemberMemo = custom(csMemberMemo);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMemberMemo", csMemberMemo));
		csMemberMemo = this.GetCsMemberMemoById(id);
		MemCache.setValue(CsMemberMemo.class,id, csMemberMemo.getKeyValue());
		return Trigger.on(CsMemberMemo.class,Trigger.SAVE,csMemberMemo);	
	}

	

	/**
	 * 更新会员备忘
	 * @param csMemberMemo
	 */
	public void updateCsMemberMemo(CsMemberMemo csMemberMemo)
	{
		csMemberMemo = custom(csMemberMemo);
		this.getSqlMapClientTemplate().update("updateCsMemberMemo", csMemberMemo);
		csMemberMemo = this.GetCsMemberMemoById(csMemberMemo.getCsmmId());
		if(csMemberMemo!=null){
			MemCache.setValue(CsMemberMemo.class,csMemberMemo.getCsmmId(), csMemberMemo.getKeyValue());
		}
		Trigger.on(CsMemberMemo.class,Trigger.UPDATE,csMemberMemo);
	}
	/**
	 * 更新会员备忘非空字段
	 * @param csMemberMemo
	 */
	public void updateCsMemberMemo$NotNull(CsMemberMemo csMemberMemo)
	{
		csMemberMemo = custom(csMemberMemo);
		this.getSqlMapClientTemplate().update("updateCsMemberMemo$NotNull", csMemberMemo);
		csMemberMemo = this.GetCsMemberMemoById(csMemberMemo.getCsmmId());
		if(csMemberMemo!=null){
			MemCache.setValue(CsMemberMemo.class,csMemberMemo.getCsmmId(), csMemberMemo.getKeyValue());
		}
		Trigger.on(CsMemberMemo.class,Trigger.UPDATE,csMemberMemo);
	}

	/**
	 * 根据ID删除一个会员备忘
	 * @param id
	 */
	public void deleteCsMemberMemoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberMemo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMemberMemoById", params);
		MemCache.deleteValue(CsMemberMemo.class, id);
	}
	/**
	 * 根据ID逻辑删除会员备忘 
	 * @param id
	 * @return
	 */
	public void removeCsMemberMemoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMemberMemo.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMemberMemoById", params);
		MemCache.deleteValue(CsMemberMemo.class, id);
	}
	/**
	 * 根据条件更新会员备忘
	 * @param values
	 * @param params
	 */
	public void updateCsMemberMemoByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberMemoCount(params)>255){
			MemCache.clearValue(CsMemberMemo.class);
		}else{
			params.put("eval","csmm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberMemoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberMemo.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMemberMemoBy", allParams);
		Trigger.on(CsMemberMemo.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员备忘
	 * @param params
	 */
	public void deleteCsMemberMemoByConfirm(Map params){
		Trigger.on(CsMemberMemo.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberMemoCount(params)>255){
			MemCache.clearValue(CsMemberMemo.class);
		}else{
			params.put("eval","csmm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberMemoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMemberMemo.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMemberMemoBy", params);
	}
	

}