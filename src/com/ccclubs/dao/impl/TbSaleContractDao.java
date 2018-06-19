package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ITbSaleContractDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleContract;
import com.lazy3q.web.helper.$;

/**
 * 签约合同的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class TbSaleContractDao extends SqlMapClientDaoSupport implements ITbSaleContractDao
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
	private TbSaleContract custom(TbSaleContract params){
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
			,"tbscNo"			
			,"tbscFile"			
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
	 * 获取所有签约合同
	 * @return
	 */	
	public List<TbSaleContract> getTbSaleContractList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<TbSaleContract> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getTbSaleContractList", params);
		
		return list;
	}
	
	
	/**
	 * 获取签约合同统计
	 * @return
	 */
	public List getTbSaleContractStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getTbSaleContractStats", params);
	}

	/**
	 * 获取签约合同分页
	 * @return
	 */
	public Page getTbSaleContractPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getTbSaleContractCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getTbSaleContractPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取签约合同数量
	 * @return
	 */	
	public Long getTbSaleContractCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleContractEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取签约合同自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getTbSaleContractEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getTbSaleContractEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取签约合同
	 * @param params
	 * @return
	 */
	public TbSaleContract getTbSaleContract(Map params)
	{		
		params = custom(params);
			
		List<TbSaleContract> list=this.getTbSaleContractList(params,1);
		TbSaleContract tbSaleContract = list.isEmpty()?null:list.get(0);
		
		return tbSaleContract;
	}
		
	/**
	 * 根据ID取签约合同
	 * @param id
	 * @return
	 */
	public TbSaleContract getTbSaleContractById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		TbSaleContract tbSaleContract = null;
		if(tbSaleContract!=null)
			return tbSaleContract;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		tbSaleContract = (TbSaleContract) this.getSqlMapClientTemplate().queryForObject("getTbSaleContractById",params);
		if(tbSaleContract!=null)
			MemCache.setValue(TbSaleContract.class,id, tbSaleContract.getKeyValue());
		return tbSaleContract;
	}
	
	/**
	 * 根据ID取签约合同(不缓存)
	 * @param id
	 * @return
	 */
	public TbSaleContract GetTbSaleContractById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (TbSaleContract) this.getSqlMapClientTemplate().queryForObject("getTbSaleContractById",params);
	}
	

	/**
	 * 保存签约合同
	 * @param tbSaleContract
	 */
	public TbSaleContract saveTbSaleContract(TbSaleContract tbSaleContract)
	{
		tbSaleContract = custom(tbSaleContract);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveTbSaleContract", tbSaleContract));
		tbSaleContract = this.GetTbSaleContractById(id);
		MemCache.setValue(TbSaleContract.class,id, tbSaleContract.getKeyValue());
		return Trigger.on(TbSaleContract.class,Trigger.SAVE,tbSaleContract);	
	}

	

	/**
	 * 更新签约合同
	 * @param tbSaleContract
	 */
	public void updateTbSaleContract(TbSaleContract tbSaleContract)
	{
		tbSaleContract = custom(tbSaleContract);
		this.getSqlMapClientTemplate().update("updateTbSaleContract", tbSaleContract);
		tbSaleContract = this.GetTbSaleContractById(tbSaleContract.getTbscId());
		if(tbSaleContract!=null){
			MemCache.setValue(TbSaleContract.class,tbSaleContract.getTbscId(), tbSaleContract.getKeyValue());
		}
		Trigger.on(TbSaleContract.class,Trigger.UPDATE,tbSaleContract);
	}
	/**
	 * 更新签约合同非空字段
	 * @param tbSaleContract
	 */
	public void updateTbSaleContract$NotNull(TbSaleContract tbSaleContract)
	{
		tbSaleContract = custom(tbSaleContract);
		this.getSqlMapClientTemplate().update("updateTbSaleContract$NotNull", tbSaleContract);
		tbSaleContract = this.GetTbSaleContractById(tbSaleContract.getTbscId());
		if(tbSaleContract!=null){
			MemCache.setValue(TbSaleContract.class,tbSaleContract.getTbscId(), tbSaleContract.getKeyValue());
		}
		Trigger.on(TbSaleContract.class,Trigger.UPDATE,tbSaleContract);
	}

	/**
	 * 根据ID删除一个签约合同
	 * @param id
	 */
	public void deleteTbSaleContractById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleContract.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteTbSaleContractById", params);
		MemCache.deleteValue(TbSaleContract.class, id);
	}
	/**
	 * 根据ID逻辑删除签约合同 
	 * @param id
	 * @return
	 */
	public void removeTbSaleContractById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(TbSaleContract.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeTbSaleContractById", params);
		MemCache.deleteValue(TbSaleContract.class, id);
	}
	/**
	 * 根据条件更新签约合同
	 * @param values
	 * @param params
	 */
	public void updateTbSaleContractByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleContractCount(params)>255){
			MemCache.clearValue(TbSaleContract.class);
		}else{
			params.put("eval","tbsc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleContractEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleContract.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateTbSaleContractBy", allParams);
		Trigger.on(TbSaleContract.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除签约合同
	 * @param params
	 */
	public void deleteTbSaleContractByConfirm(Map params){
		Trigger.on(TbSaleContract.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getTbSaleContractCount(params)>255){
			MemCache.clearValue(TbSaleContract.class);
		}else{
			params.put("eval","tbsc_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getTbSaleContractEval", params);
			for(Map map:result){
				MemCache.deleteValue(TbSaleContract.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteTbSaleContractBy", params);
	}
	

}