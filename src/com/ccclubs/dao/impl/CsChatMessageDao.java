package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsChatMessageDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChatMessage;
import com.lazy3q.web.helper.$;

/**
 * 客服消息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsChatMessageDao extends SqlMapClientDaoSupport implements ICsChatMessageDao
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
	private CsChatMessage custom(CsChatMessage params){
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
			"cscmContent"			
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
	 * 获取所有客服消息
	 * @return
	 */	
	public List<CsChatMessage> getCsChatMessageList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsChatMessage> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsChatMessageList", params);
		
		return list;
	}
	
	
	/**
	 * 获取客服消息统计
	 * @return
	 */
	public List getCsChatMessageStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsChatMessageStats", params);
	}

	/**
	 * 获取客服消息分页
	 * @return
	 */
	public Page getCsChatMessagePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsChatMessageCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsChatMessagePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取客服消息数量
	 * @return
	 */	
	public Long getCsChatMessageCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChatMessageEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取客服消息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsChatMessageEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsChatMessageEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取客服消息
	 * @param params
	 * @return
	 */
	public CsChatMessage getCsChatMessage(Map params)
	{		
		params = custom(params);
			
		List<CsChatMessage> list=this.getCsChatMessageList(params,1);
		CsChatMessage csChatMessage = list.isEmpty()?null:list.get(0);
		
		return csChatMessage;
	}
		
	/**
	 * 根据ID取客服消息
	 * @param id
	 * @return
	 */
	public CsChatMessage getCsChatMessageById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsChatMessage csChatMessage = null;
		if(csChatMessage!=null)
			return csChatMessage;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csChatMessage = (CsChatMessage) this.getSqlMapClientTemplate().queryForObject("getCsChatMessageById",params);
		if(csChatMessage!=null)
			MemCache.setValue(CsChatMessage.class,id, csChatMessage.getKeyValue());
		return csChatMessage;
	}
	
	/**
	 * 根据ID取客服消息(不缓存)
	 * @param id
	 * @return
	 */
	public CsChatMessage GetCsChatMessageById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsChatMessage) this.getSqlMapClientTemplate().queryForObject("getCsChatMessageById",params);
	}
	

	/**
	 * 保存客服消息
	 * @param csChatMessage
	 */
	public CsChatMessage saveCsChatMessage(CsChatMessage csChatMessage)
	{
		csChatMessage = custom(csChatMessage);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsChatMessage", csChatMessage));
		csChatMessage = this.GetCsChatMessageById(id);
		MemCache.setValue(CsChatMessage.class,id, csChatMessage.getKeyValue());
		return Trigger.on(CsChatMessage.class,Trigger.SAVE,csChatMessage);	
	}

	

	/**
	 * 更新客服消息
	 * @param csChatMessage
	 */
	public void updateCsChatMessage(CsChatMessage csChatMessage)
	{
		csChatMessage = custom(csChatMessage);
		this.getSqlMapClientTemplate().update("updateCsChatMessage", csChatMessage);
		csChatMessage = this.GetCsChatMessageById(csChatMessage.getCscmId());
		if(csChatMessage!=null){
			MemCache.setValue(CsChatMessage.class,csChatMessage.getCscmId(), csChatMessage.getKeyValue());
		}
		Trigger.on(CsChatMessage.class,Trigger.UPDATE,csChatMessage);
	}
	/**
	 * 更新客服消息非空字段
	 * @param csChatMessage
	 */
	public void updateCsChatMessage$NotNull(CsChatMessage csChatMessage)
	{
		csChatMessage = custom(csChatMessage);
		this.getSqlMapClientTemplate().update("updateCsChatMessage$NotNull", csChatMessage);
		csChatMessage = this.GetCsChatMessageById(csChatMessage.getCscmId());
		if(csChatMessage!=null){
			MemCache.setValue(CsChatMessage.class,csChatMessage.getCscmId(), csChatMessage.getKeyValue());
		}
		Trigger.on(CsChatMessage.class,Trigger.UPDATE,csChatMessage);
	}

	/**
	 * 根据ID删除一个客服消息
	 * @param id
	 */
	public void deleteCsChatMessageById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChatMessage.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsChatMessageById", params);
		MemCache.deleteValue(CsChatMessage.class, id);
	}
	/**
	 * 根据ID逻辑删除客服消息 
	 * @param id
	 * @return
	 */
	public void removeCsChatMessageById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsChatMessage.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsChatMessageById", params);
		MemCache.deleteValue(CsChatMessage.class, id);
	}
	/**
	 * 根据条件更新客服消息
	 * @param values
	 * @param params
	 */
	public void updateCsChatMessageByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsChatMessageCount(params)>255){
			MemCache.clearValue(CsChatMessage.class);
		}else{
			params.put("eval","cscm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChatMessageEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChatMessage.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsChatMessageBy", allParams);
		Trigger.on(CsChatMessage.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除客服消息
	 * @param params
	 */
	public void deleteCsChatMessageByConfirm(Map params){
		Trigger.on(CsChatMessage.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsChatMessageCount(params)>255){
			MemCache.clearValue(CsChatMessage.class);
		}else{
			params.put("eval","cscm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsChatMessageEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsChatMessage.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsChatMessageBy", params);
	}
	

}