package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChannelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChannel;
import com.lazy3q.web.helper.$;

/**
 * 访问渠道的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChannelDao extends SqlMapClientDaoSupport implements ICsChannelDao
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
	private CsChannel custom(CsChannel params){
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
			"cscName"			
			,"cscDesc"			
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
	 * 获取所有访问渠道
	 * @return
	 */	
	public List<CsChannel> getCsChannelList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChannel> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChannelList", params);
		
		return list;
	}
	
	
	/**
	 * 获取访问渠道统计
	 * @return
	 */
	public List getCsChannelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChannelStats", params);
	}

	/**
	 * 获取访问渠道分页
	 * @return
	 */
	public Page getCsChannelPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChannelCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChannelPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取访问渠道数量
	 * @return
	 */	
	public Long getCsChannelCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChannelEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取访问渠道自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChannelEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChannelEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取访问渠道
	 * @param params
	 * @return
	 */
	public CsChannel getCsChannel(Map params)
	{		
		params = custom(params);
			
		List<CsChannel> list=this.getCsChannelList(params,1);
		CsChannel csChannel = list.isEmpty()?null:list.get(0);
		
		return csChannel;
	}
		
	/**
	 * 根据ID取访问渠道
	 * @param id
	 * @return
	 */
	public CsChannel getCsChannelById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChannel csChannel = null;
		if(csChannel!=null)
			return csChannel;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChannel = (CsChannel) this.getSqlMapClientTemplate().queryForObject("getCsChannelById",params);
		if(csChannel!=null)
			MemCache.setValue(CsChannel.class,id, csChannel.getKeyValue());
		return csChannel;
	}
	
	/**
	 * 根据ID取访问渠道(不缓存)
	 * @param id
	 * @return
	 */
	public CsChannel GetCsChannelById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChannel) this.getSqlMapClientTemplate().queryForObject("getCsChannelById",params);
	}
	

	/**
	 * 保存访问渠道
	 * @param csChannel
	 */
	public CsChannel saveCsChannel(CsChannel csChannel)
	{
		csChannel = custom(csChannel);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChannel", csChannel));
		csChannel = this.GetCsChannelById(id);
		MemCache.setValue(CsChannel.class,id, csChannel.getKeyValue());
		return Trigger.on(CsChannel.class,Trigger.SAVE,csChannel);	
	}

	

	/**
	 * 更新访问渠道
	 * @param csChannel
	 */
	public void updateCsChannel(CsChannel csChannel)
	{
		csChannel = custom(csChannel);
		this.getSqlMapClientTemplate().update("updateCsChannel", csChannel);
		csChannel = this.GetCsChannelById(csChannel.getCscId());
		if(csChannel!=null){
			MemCache.setValue(CsChannel.class,csChannel.getCscId(), csChannel.getKeyValue());
		}
		Trigger.on(CsChannel.class,Trigger.UPDATE,csChannel);
	}
	/**
	 * 更新访问渠道非空字段
	 * @param csChannel
	 */
	public void updateCsChannel$NotNull(CsChannel csChannel)
	{
		csChannel = custom(csChannel);
		this.getSqlMapClientTemplate().update("updateCsChannel$NotNull", csChannel);
		csChannel = this.GetCsChannelById(csChannel.getCscId());
		if(csChannel!=null){
			MemCache.setValue(CsChannel.class,csChannel.getCscId(), csChannel.getKeyValue());
		}
		Trigger.on(CsChannel.class,Trigger.UPDATE,csChannel);
	}

	/**
	 * 根据ID删除一个访问渠道
	 * @param id
	 */
	public void deleteCsChannelById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChannel.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChannelById", params);
		MemCache.deleteValue(CsChannel.class, id);
	}
	/**
	 * 根据ID逻辑删除访问渠道 
	 * @param id
	 * @return
	 */
	public void removeCsChannelById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChannel.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChannelById", params);
		MemCache.deleteValue(CsChannel.class, id);
	}
	/**
	 * 根据条件更新访问渠道
	 * @param values
	 * @param params
	 */
	public void updateCsChannelByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChannelCount(params)>255){
			MemCache.clearValue(CsChannel.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChannelEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChannel.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChannelBy", allParams);
		Trigger.on(CsChannel.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除访问渠道
	 * @param params
	 */
	public void deleteCsChannelByConfirm(Map params){
		Trigger.on(CsChannel.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChannelCount(params)>255){
			MemCache.clearValue(CsChannel.class);
		}else{
			params.put("eval","csc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChannelEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChannel.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChannelBy", params);
	}
	

}