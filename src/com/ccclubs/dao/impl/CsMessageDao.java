package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMessageDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMessage;
import com.lazy3q.web.helper.$;

/**
 * 会员消息的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMessageDao extends SqlMapClientDaoSupport implements ICsMessageDao
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
	private CsMessage custom(CsMessage params){
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
			"csmTitle"			
			,"csmContent"			
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
	 * 获取所有会员消息
	 * @return
	 */	
	public List<CsMessage> getCsMessageList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMessage> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMessageList", params);
		
		return list;
	}
	
	
	/**
	 * 获取会员消息统计
	 * @return
	 */
	public List getCsMessageStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMessageStats", params);
	}

	/**
	 * 获取会员消息分页
	 * @return
	 */
	public Page getCsMessagePage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMessageCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsMessagePage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员消息数量
	 * @return
	 */	
	public Long getCsMessageCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMessageEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员消息自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMessageEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMessageEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员消息
	 * @param params
	 * @return
	 */
	public CsMessage getCsMessage(Map params)
	{		
		params = custom(params);
			
		List<CsMessage> list=this.getCsMessageList(params,1);
		CsMessage csMessage = list.isEmpty()?null:list.get(0);
		
		return csMessage;
	}
		
	/**
	 * 根据ID取会员消息
	 * @param id
	 * @return
	 */
	public CsMessage getCsMessageById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMessage csMessage = null;
		if(csMessage!=null)
			return csMessage;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMessage = (CsMessage) this.getSqlMapClientTemplate().queryForObject("getCsMessageById",params);
		if(csMessage!=null)
			MemCache.setValue(CsMessage.class,id, csMessage.getKeyValue());
		return csMessage;
	}
	
	/**
	 * 根据ID取会员消息(不缓存)
	 * @param id
	 * @return
	 */
	public CsMessage GetCsMessageById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsMessage) this.getSqlMapClientTemplate().queryForObject("getCsMessageById",params);
	}
	

	/**
	 * 保存会员消息
	 * @param csMessage
	 */
	public CsMessage saveCsMessage(CsMessage csMessage)
	{
		csMessage = custom(csMessage);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMessage", csMessage));
		csMessage = this.GetCsMessageById(id);
		MemCache.setValue(CsMessage.class,id, csMessage.getKeyValue());
		return Trigger.on(CsMessage.class,Trigger.SAVE,csMessage);	
	}

	

	/**
	 * 更新会员消息
	 * @param csMessage
	 */
	public void updateCsMessage(CsMessage csMessage)
	{
		csMessage = custom(csMessage);
		this.getSqlMapClientTemplate().update("updateCsMessage", csMessage);
		csMessage = this.GetCsMessageById(csMessage.getCsmId());
		if(csMessage!=null){
			MemCache.setValue(CsMessage.class,csMessage.getCsmId(), csMessage.getKeyValue());
		}
		Trigger.on(CsMessage.class,Trigger.UPDATE,csMessage);
	}
	/**
	 * 更新会员消息非空字段
	 * @param csMessage
	 */
	public void updateCsMessage$NotNull(CsMessage csMessage)
	{
		csMessage = custom(csMessage);
		this.getSqlMapClientTemplate().update("updateCsMessage$NotNull", csMessage);
		csMessage = this.GetCsMessageById(csMessage.getCsmId());
		if(csMessage!=null){
			MemCache.setValue(CsMessage.class,csMessage.getCsmId(), csMessage.getKeyValue());
		}
		Trigger.on(CsMessage.class,Trigger.UPDATE,csMessage);
	}

	/**
	 * 根据ID删除一个会员消息
	 * @param id
	 */
	public void deleteCsMessageById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMessage.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMessageById", params);
		MemCache.deleteValue(CsMessage.class, id);
	}
	/**
	 * 根据ID逻辑删除会员消息 
	 * @param id
	 * @return
	 */
	public void removeCsMessageById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMessage.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMessageById", params);
		MemCache.deleteValue(CsMessage.class, id);
	}
	/**
	 * 根据条件更新会员消息
	 * @param values
	 * @param params
	 */
	public void updateCsMessageByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMessageCount(params)>255){
			MemCache.clearValue(CsMessage.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMessageEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMessage.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMessageBy", allParams);
		Trigger.on(CsMessage.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员消息
	 * @param params
	 */
	public void deleteCsMessageByConfirm(Map params){
		Trigger.on(CsMessage.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMessageCount(params)>255){
			MemCache.clearValue(CsMessage.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMessageEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMessage.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMessageBy", params);
	}
	

}