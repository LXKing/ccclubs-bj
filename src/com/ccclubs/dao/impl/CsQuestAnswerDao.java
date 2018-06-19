package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsQuestAnswerDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsQuestAnswer;
import com.lazy3q.web.helper.$;

/**
 * 问题及选项的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsQuestAnswerDao extends SqlMapClientDaoSupport implements ICsQuestAnswerDao
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
	private CsQuestAnswer custom(CsQuestAnswer params){
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
			"csqaTitle"			
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
	 * 获取所有问题及选项
	 * @return
	 */	
	public List<CsQuestAnswer> getCsQuestAnswerList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsQuestAnswer> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsQuestAnswerList", params);
		
		return list;
	}
	
	
	/**
	 * 获取问题及选项统计
	 * @return
	 */
	public List getCsQuestAnswerStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsQuestAnswerStats", params);
	}

	/**
	 * 获取问题及选项分页
	 * @return
	 */
	public Page getCsQuestAnswerPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsQuestAnswerCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsQuestAnswerPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取问题及选项数量
	 * @return
	 */	
	public Long getCsQuestAnswerCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsQuestAnswerEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取问题及选项自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsQuestAnswerEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsQuestAnswerEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取问题及选项
	 * @param params
	 * @return
	 */
	public CsQuestAnswer getCsQuestAnswer(Map params)
	{		
		params = custom(params);
			
		List<CsQuestAnswer> list=this.getCsQuestAnswerList(params,1);
		CsQuestAnswer csQuestAnswer = list.isEmpty()?null:list.get(0);
		
		return csQuestAnswer;
	}
		
	/**
	 * 根据ID取问题及选项
	 * @param id
	 * @return
	 */
	public CsQuestAnswer getCsQuestAnswerById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsQuestAnswer csQuestAnswer = null;
		if(csQuestAnswer!=null)
			return csQuestAnswer;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csQuestAnswer = (CsQuestAnswer) this.getSqlMapClientTemplate().queryForObject("getCsQuestAnswerById",params);
		if(csQuestAnswer!=null)
			MemCache.setValue(CsQuestAnswer.class,id, csQuestAnswer.getKeyValue());
		return csQuestAnswer;
	}
	
	/**
	 * 根据ID取问题及选项(不缓存)
	 * @param id
	 * @return
	 */
	public CsQuestAnswer GetCsQuestAnswerById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsQuestAnswer) this.getSqlMapClientTemplate().queryForObject("getCsQuestAnswerById",params);
	}
	

	/**
	 * 保存问题及选项
	 * @param csQuestAnswer
	 */
	public CsQuestAnswer saveCsQuestAnswer(CsQuestAnswer csQuestAnswer)
	{
		csQuestAnswer = custom(csQuestAnswer);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsQuestAnswer", csQuestAnswer));
		csQuestAnswer = this.GetCsQuestAnswerById(id);
		MemCache.setValue(CsQuestAnswer.class,id, csQuestAnswer.getKeyValue());
		return Trigger.on(CsQuestAnswer.class,Trigger.SAVE,csQuestAnswer);	
	}

	

	/**
	 * 更新问题及选项
	 * @param csQuestAnswer
	 */
	public void updateCsQuestAnswer(CsQuestAnswer csQuestAnswer)
	{
		csQuestAnswer = custom(csQuestAnswer);
		this.getSqlMapClientTemplate().update("updateCsQuestAnswer", csQuestAnswer);
		csQuestAnswer = this.GetCsQuestAnswerById(csQuestAnswer.getCsqaId());
		if(csQuestAnswer!=null){
			MemCache.setValue(CsQuestAnswer.class,csQuestAnswer.getCsqaId(), csQuestAnswer.getKeyValue());
		}
		Trigger.on(CsQuestAnswer.class,Trigger.UPDATE,csQuestAnswer);
	}
	/**
	 * 更新问题及选项非空字段
	 * @param csQuestAnswer
	 */
	public void updateCsQuestAnswer$NotNull(CsQuestAnswer csQuestAnswer)
	{
		csQuestAnswer = custom(csQuestAnswer);
		this.getSqlMapClientTemplate().update("updateCsQuestAnswer$NotNull", csQuestAnswer);
		csQuestAnswer = this.GetCsQuestAnswerById(csQuestAnswer.getCsqaId());
		if(csQuestAnswer!=null){
			MemCache.setValue(CsQuestAnswer.class,csQuestAnswer.getCsqaId(), csQuestAnswer.getKeyValue());
		}
		Trigger.on(CsQuestAnswer.class,Trigger.UPDATE,csQuestAnswer);
	}

	/**
	 * 根据ID删除一个问题及选项
	 * @param id
	 */
	public void deleteCsQuestAnswerById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsQuestAnswer.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsQuestAnswerById", params);
		MemCache.deleteValue(CsQuestAnswer.class, id);
	}
	/**
	 * 根据条件更新问题及选项
	 * @param values
	 * @param params
	 */
	public void updateCsQuestAnswerByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsQuestAnswerCount(params)>255){
			MemCache.clearValue(CsQuestAnswer.class);
		}else{
			params.put("eval","csqa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsQuestAnswerEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsQuestAnswer.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsQuestAnswerBy", allParams);
		Trigger.on(CsQuestAnswer.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除问题及选项
	 * @param params
	 */
	public void deleteCsQuestAnswerByConfirm(Map params){
		Trigger.on(CsQuestAnswer.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsQuestAnswerCount(params)>255){
			MemCache.clearValue(CsQuestAnswer.class);
		}else{
			params.put("eval","csqa_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsQuestAnswerEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsQuestAnswer.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsQuestAnswerBy", params);
	}
	

}