package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbPassCheckDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbPassCheck;
import com.lazy3q.web.helper.$;

/**
 * 上下班签到的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbPassCheckDao extends SqlMapClientDaoSupport implements ITbPassCheckDao
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
	private TbPassCheck custom(TbPassCheck params){
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
			"tbpcTitle"			
			,"tbpcRemark"			
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
	 * 获取所有上下班签到
	 * @return
	 */	
	public List<TbPassCheck> getTbPassCheckList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbPassCheck> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbPassCheckList", params);
		
		return list;
	}
	
	
	/**
	 * 获取上下班签到统计
	 * @return
	 */
	public List getTbPassCheckStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbPassCheckStats", params);
	}

	/**
	 * 获取上下班签到分页
	 * @return
	 */
	public Page getTbPassCheckPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbPassCheckCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbPassCheckPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取上下班签到数量
	 * @return
	 */	
	public Long getTbPassCheckCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbPassCheckEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取上下班签到自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbPassCheckEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbPassCheckEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取上下班签到
	 * @param params
	 * @return
	 */
	public TbPassCheck getTbPassCheck(Map params)
	{		
		params = custom(params);
			
		List<TbPassCheck> list=this.getTbPassCheckList(params,1);
		TbPassCheck tbPassCheck = list.isEmpty()?null:list.get(0);
		
		return tbPassCheck;
	}
		
	/**
	 * 根据ID取上下班签到
	 * @param id
	 * @return
	 */
	public TbPassCheck getTbPassCheckById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbPassCheck tbPassCheck = null;
		if(tbPassCheck!=null)
			return tbPassCheck;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbPassCheck = (TbPassCheck) this.getSqlMapClientTemplate().queryForObject("getTbPassCheckById",params);
		if(tbPassCheck!=null)
			MemCache.setValue(TbPassCheck.class,id, tbPassCheck.getKeyValue());
		return tbPassCheck;
	}
	
	/**
	 * 根据ID取上下班签到(不缓存)
	 * @param id
	 * @return
	 */
	public TbPassCheck GetTbPassCheckById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbPassCheck) this.getSqlMapClientTemplate().queryForObject("getTbPassCheckById",params);
	}
	

	/**
	 * 保存上下班签到
	 * @param tbPassCheck
	 */
	public TbPassCheck saveTbPassCheck(TbPassCheck tbPassCheck)
	{
		tbPassCheck = custom(tbPassCheck);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbPassCheck", tbPassCheck));
		tbPassCheck = this.GetTbPassCheckById(id);
		MemCache.setValue(TbPassCheck.class,id, tbPassCheck.getKeyValue());
		return Trigger.on(TbPassCheck.class,Trigger.SAVE,tbPassCheck);	
	}

	

	/**
	 * 更新上下班签到
	 * @param tbPassCheck
	 */
	public void updateTbPassCheck(TbPassCheck tbPassCheck)
	{
		tbPassCheck = custom(tbPassCheck);
		this.getSqlMapClientTemplate().update("updateTbPassCheck", tbPassCheck);
		tbPassCheck = this.GetTbPassCheckById(tbPassCheck.getTbpcId());
		if(tbPassCheck!=null){
			MemCache.setValue(TbPassCheck.class,tbPassCheck.getTbpcId(), tbPassCheck.getKeyValue());
		}
		Trigger.on(TbPassCheck.class,Trigger.UPDATE,tbPassCheck);
	}
	/**
	 * 更新上下班签到非空字段
	 * @param tbPassCheck
	 */
	public void updateTbPassCheck$NotNull(TbPassCheck tbPassCheck)
	{
		tbPassCheck = custom(tbPassCheck);
		this.getSqlMapClientTemplate().update("updateTbPassCheck$NotNull", tbPassCheck);
		tbPassCheck = this.GetTbPassCheckById(tbPassCheck.getTbpcId());
		if(tbPassCheck!=null){
			MemCache.setValue(TbPassCheck.class,tbPassCheck.getTbpcId(), tbPassCheck.getKeyValue());
		}
		Trigger.on(TbPassCheck.class,Trigger.UPDATE,tbPassCheck);
	}

	/**
	 * 根据ID删除一个上下班签到
	 * @param id
	 */
	public void deleteTbPassCheckById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbPassCheck.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbPassCheckById", params);
		MemCache.deleteValue(TbPassCheck.class, id);
	}
	/**
	 * 根据ID逻辑删除上下班签到 
	 * @param id
	 * @return
	 */
	public void removeTbPassCheckById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbPassCheck.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbPassCheckById", params);
		MemCache.deleteValue(TbPassCheck.class, id);
	}
	/**
	 * 根据条件更新上下班签到
	 * @param values
	 * @param params
	 */
	public void updateTbPassCheckByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbPassCheckCount(params)>255){
			MemCache.clearValue(TbPassCheck.class);
		}else{
			params.put("eval","tbpc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbPassCheckEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbPassCheck.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbPassCheckBy", allParams);
		Trigger.on(TbPassCheck.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除上下班签到
	 * @param params
	 */
	public void deleteTbPassCheckByConfirm(Map params){
		Trigger.on(TbPassCheck.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbPassCheckCount(params)>255){
			MemCache.clearValue(TbPassCheck.class);
		}else{
			params.put("eval","tbpc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbPassCheckEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbPassCheck.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbPassCheckBy", params);
	}
	

}