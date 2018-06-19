package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbSaleCustomDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleCustom;
import com.lazy3q.web.helper.$;

/**
 * 客户资料的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbSaleCustomDao extends SqlMapClientDaoSupport implements ITbSaleCustomDao
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
	private TbSaleCustom custom(TbSaleCustom params){
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
			"tbscName"			
			,"tbscProfile"			
			,"tbscRemark"			
			,"tbscMemo"			
			,"tbscData"			
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
	 * 获取所有客户资料
	 * @return
	 */	
	public List<TbSaleCustom> getTbSaleCustomList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbSaleCustom> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbSaleCustomList", params);
		
		return list;
	}
	
	
	/**
	 * 获取客户资料统计
	 * @return
	 */
	public List getTbSaleCustomStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbSaleCustomStats", params);
	}

	/**
	 * 获取客户资料分页
	 * @return
	 */
	public Page getTbSaleCustomPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbSaleCustomCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbSaleCustomPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取客户资料数量
	 * @return
	 */	
	public Long getTbSaleCustomCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleCustomEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取客户资料自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbSaleCustomEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleCustomEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取客户资料
	 * @param params
	 * @return
	 */
	public TbSaleCustom getTbSaleCustom(Map params)
	{		
		params = custom(params);
			
		List<TbSaleCustom> list=this.getTbSaleCustomList(params,1);
		TbSaleCustom tbSaleCustom = list.isEmpty()?null:list.get(0);
		
		return tbSaleCustom;
	}
		
	/**
	 * 根据ID取客户资料
	 * @param id
	 * @return
	 */
	public TbSaleCustom getTbSaleCustomById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbSaleCustom tbSaleCustom = null;
		if(tbSaleCustom!=null)
			return tbSaleCustom;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbSaleCustom = (TbSaleCustom) this.getSqlMapClientTemplate().queryForObject("getTbSaleCustomById",params);
		if(tbSaleCustom!=null)
			MemCache.setValue(TbSaleCustom.class,id, tbSaleCustom.getKeyValue());
		return tbSaleCustom;
	}
	
	/**
	 * 根据ID取客户资料(不缓存)
	 * @param id
	 * @return
	 */
	public TbSaleCustom GetTbSaleCustomById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbSaleCustom) this.getSqlMapClientTemplate().queryForObject("getTbSaleCustomById",params);
	}
	

	/**
	 * 保存客户资料
	 * @param tbSaleCustom
	 */
	public TbSaleCustom saveTbSaleCustom(TbSaleCustom tbSaleCustom)
	{
		tbSaleCustom = custom(tbSaleCustom);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbSaleCustom", tbSaleCustom));
		tbSaleCustom = this.GetTbSaleCustomById(id);
		MemCache.setValue(TbSaleCustom.class,id, tbSaleCustom.getKeyValue());
		return Trigger.on(TbSaleCustom.class,Trigger.SAVE,tbSaleCustom);	
	}

	

	/**
	 * 更新客户资料
	 * @param tbSaleCustom
	 */
	public void updateTbSaleCustom(TbSaleCustom tbSaleCustom)
	{
		tbSaleCustom = custom(tbSaleCustom);
		this.getSqlMapClientTemplate().update("updateTbSaleCustom", tbSaleCustom);
		tbSaleCustom = this.GetTbSaleCustomById(tbSaleCustom.getTbscId());
		if(tbSaleCustom!=null){
			MemCache.setValue(TbSaleCustom.class,tbSaleCustom.getTbscId(), tbSaleCustom.getKeyValue());
		}
		Trigger.on(TbSaleCustom.class,Trigger.UPDATE,tbSaleCustom);
	}
	/**
	 * 更新客户资料非空字段
	 * @param tbSaleCustom
	 */
	public void updateTbSaleCustom$NotNull(TbSaleCustom tbSaleCustom)
	{
		tbSaleCustom = custom(tbSaleCustom);
		this.getSqlMapClientTemplate().update("updateTbSaleCustom$NotNull", tbSaleCustom);
		tbSaleCustom = this.GetTbSaleCustomById(tbSaleCustom.getTbscId());
		if(tbSaleCustom!=null){
			MemCache.setValue(TbSaleCustom.class,tbSaleCustom.getTbscId(), tbSaleCustom.getKeyValue());
		}
		Trigger.on(TbSaleCustom.class,Trigger.UPDATE,tbSaleCustom);
	}

	/**
	 * 根据ID删除一个客户资料
	 * @param id
	 */
	public void deleteTbSaleCustomById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleCustom.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbSaleCustomById", params);
		MemCache.deleteValue(TbSaleCustom.class, id);
	}
	/**
	 * 根据ID逻辑删除客户资料 
	 * @param id
	 * @return
	 */
	public void removeTbSaleCustomById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleCustom.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbSaleCustomById", params);
		MemCache.deleteValue(TbSaleCustom.class, id);
	}
	/**
	 * 根据条件更新客户资料
	 * @param values
	 * @param params
	 */
	public void updateTbSaleCustomByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleCustomCount(params)>255){
			MemCache.clearValue(TbSaleCustom.class);
		}else{
			params.put("eval","tbsc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleCustomEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleCustom.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbSaleCustomBy", allParams);
		Trigger.on(TbSaleCustom.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除客户资料
	 * @param params
	 */
	public void deleteTbSaleCustomByConfirm(Map params){
		Trigger.on(TbSaleCustom.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleCustomCount(params)>255){
			MemCache.clearValue(TbSaleCustom.class);
		}else{
			params.put("eval","tbsc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleCustomEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleCustom.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbSaleCustomBy", params);
	}
	

}