package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsUserPackDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUserPack;
import com.lazy3q.web.helper.$;

/**
 * 用户套餐的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsUserPackDao extends SqlMapClientDaoSupport implements ICsUserPackDao
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
	private CsUserPack custom(CsUserPack params){
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
			"csupRemark"			
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
	 * 获取所有用户套餐
	 * @return
	 */	
	public List<CsUserPack> getCsUserPackList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsUserPack> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsUserPackList", params);
		
		return list;
	}
	
	
	/**
	 * 获取用户套餐统计
	 * @return
	 */
	public List getCsUserPackStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsUserPackStats", params);
	}

	/**
	 * 获取用户套餐分页
	 * @return
	 */
	public Page getCsUserPackPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsUserPackCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsUserPackPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取用户套餐数量
	 * @return
	 */	
	public Long getCsUserPackCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUserPackEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取用户套餐自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsUserPackEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsUserPackEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取用户套餐
	 * @param params
	 * @return
	 */
	public CsUserPack getCsUserPack(Map params)
	{		
		params = custom(params);
			
		List<CsUserPack> list=this.getCsUserPackList(params,1);
		CsUserPack csUserPack = list.isEmpty()?null:list.get(0);
		
		return csUserPack;
	}
		
	/**
	 * 根据ID取用户套餐
	 * @param id
	 * @return
	 */
	public CsUserPack getCsUserPackById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsUserPack csUserPack = null;
		if(csUserPack!=null)
			return csUserPack;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csUserPack = (CsUserPack) this.getSqlMapClientTemplate().queryForObject("getCsUserPackById",params);
		if(csUserPack!=null)
			MemCache.setValue(CsUserPack.class,id, csUserPack.getKeyValue());
		return csUserPack;
	}
	
	/**
	 * 根据ID取用户套餐(不缓存)
	 * @param id
	 * @return
	 */
	public CsUserPack GetCsUserPackById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsUserPack) this.getSqlMapClientTemplate().queryForObject("getCsUserPackById",params);
	}
	

	/**
	 * 保存用户套餐
	 * @param csUserPack
	 */
	public CsUserPack saveCsUserPack(CsUserPack csUserPack)
	{
		csUserPack = custom(csUserPack);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsUserPack", csUserPack));
		csUserPack = this.GetCsUserPackById(id);
		MemCache.setValue(CsUserPack.class,id, csUserPack.getKeyValue());
		return Trigger.on(CsUserPack.class,Trigger.SAVE,csUserPack);	
	}

	

	/**
	 * 更新用户套餐
	 * @param csUserPack
	 */
	public void updateCsUserPack(CsUserPack csUserPack)
	{
		csUserPack = custom(csUserPack);
		this.getSqlMapClientTemplate().update("updateCsUserPack", csUserPack);
		csUserPack = this.GetCsUserPackById(csUserPack.getCsupId());
		if(csUserPack!=null){
			MemCache.setValue(CsUserPack.class,csUserPack.getCsupId(), csUserPack.getKeyValue());
		}
		Trigger.on(CsUserPack.class,Trigger.UPDATE,csUserPack);
	}
	/**
	 * 更新用户套餐非空字段
	 * @param csUserPack
	 */
	public void updateCsUserPack$NotNull(CsUserPack csUserPack)
	{
		csUserPack = custom(csUserPack);
		this.getSqlMapClientTemplate().update("updateCsUserPack$NotNull", csUserPack);
		csUserPack = this.GetCsUserPackById(csUserPack.getCsupId());
		if(csUserPack!=null){
			MemCache.setValue(CsUserPack.class,csUserPack.getCsupId(), csUserPack.getKeyValue());
		}
		Trigger.on(CsUserPack.class,Trigger.UPDATE,csUserPack);
	}

	/**
	 * 根据ID删除一个用户套餐
	 * @param id
	 */
	public void deleteCsUserPackById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUserPack.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsUserPackById", params);
		MemCache.deleteValue(CsUserPack.class, id);
	}
	/**
	 * 根据ID逻辑删除用户套餐 
	 * @param id
	 * @return
	 */
	public void removeCsUserPackById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsUserPack.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsUserPackById", params);
		MemCache.deleteValue(CsUserPack.class, id);
	}
	/**
	 * 根据条件更新用户套餐
	 * @param values
	 * @param params
	 */
	public void updateCsUserPackByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsUserPackCount(params)>255){
			MemCache.clearValue(CsUserPack.class);
		}else{
			params.put("eval","csup_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUserPackEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUserPack.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsUserPackBy", allParams);
		Trigger.on(CsUserPack.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除用户套餐
	 * @param params
	 */
	public void deleteCsUserPackByConfirm(Map params){
		Trigger.on(CsUserPack.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsUserPackCount(params)>255){
			MemCache.clearValue(CsUserPack.class);
		}else{
			params.put("eval","csup_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsUserPackEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsUserPack.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsUserPackBy", params);
	}
	

}