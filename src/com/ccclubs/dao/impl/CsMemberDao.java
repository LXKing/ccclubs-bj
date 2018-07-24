package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.dao.ICsMemberInfoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberInfo;
import com.lazy3q.web.helper.$;

/**
 * 会员帐号的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsMemberDao extends SqlMapClientDaoSupport implements ICsMemberDao
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
	private CsMember custom(CsMember params){
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
			"csmMarket"			
			,"csmTag"			
			,"csmMark"			
			,"csmVisitFlag"			
			,"csmRemark"			
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
	
	ICsMemberInfoDao csMemberInfoDao;
	
	public ICsMemberInfoDao getCsMemberInfoDao() {
        return csMemberInfoDao;
    }

    public void setCsMemberInfoDao(ICsMemberInfoDao csMemberInfoDao) {
        this.csMemberInfoDao = csMemberInfoDao;
    }

    /**
	 * 获取所有会员帐号
	 * @return
	 */	
	public List<CsMember> getCsMemberList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsMember> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsMemberList", params);
		if(null!=list&&list.size()>0)
		for(int i=0 ;i<list.size();i++) {
		    CsMemberInfo csMemberInfo = csMemberInfoDao.getCsMemberInfoById(list.get(i).getCsmInfo());
		    if(null != csMemberInfo) {
		        list.get(i).setCsmRemark(list.get(i).getCsmRemark()+"    公司："+csMemberInfo.getCsmiCompany$()+"\n单位："+csMemberInfo.getCsmiDepartment());
		    }
		}
		
		return list;
	}
	
	
	/**
	 * 获取会员帐号统计
	 * @return
	 */
	public List getCsMemberStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsMemberStats", params);
	}

	/**
	 * 获取会员帐号分页
	 * @return
	 */
	public Page getCsMemberPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsMemberCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List<CsMember> list=this.getSqlMapClientTemplate().queryForList("getCsMemberPage", params);
		if(null!=list&&list.size()>0)
	        for(int i=0 ;i<list.size();i++) {
	            CsMemberInfo csMemberInfo = csMemberInfoDao.getCsMemberInfoById(list.get(i).getCsmInfo());
	            if(null != csMemberInfo) {
	                list.get(i).setCsmRemark(list.get(i).getCsmRemark()+"    公司："+csMemberInfo.getCsmiCompany$()+"\n单位："+csMemberInfo.getCsmiDepartment());
	            }
	        }
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取会员帐号数量
	 * @return
	 */	
	public Long getCsMemberCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取会员帐号自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsMemberEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsMemberEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取会员帐号
	 * @param params
	 * @return
	 */
	public CsMember getCsMember(Map params)
	{		
		params = custom(params);
			
		List<CsMember> list=this.getCsMemberList(params,1);
		CsMember csMember = list.isEmpty()?null:list.get(0);
		
		return csMember;
	}
		
	/**
	 * 根据ID取会员帐号
	 * @param id
	 * @return
	 */
	public CsMember getCsMemberById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsMember csMember = null;
		if(csMember!=null)
			return csMember;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csMember = (CsMember) this.getSqlMapClientTemplate().queryForObject("getCsMemberById",params);
		if(csMember!=null) {
		    MemCache.setValue(CsMember.class,id, csMember.getKeyValue());
		    CsMemberInfo csMemberInfo = csMemberInfoDao.getCsMemberInfoById(csMember.getCsmInfo());
            if(null != csMemberInfo) {
                csMember.setCsmRemark(csMember.getCsmRemark()+"    公司："+csMemberInfo.getCsmiCompany$()+"\n单位："+csMemberInfo.getCsmiDepartment());
            }
		}
			
		return csMember;
	}
	
	/**
	 * 根据ID取会员帐号(不缓存)
	 * @param id
	 * @return
	 */
	public CsMember GetCsMemberById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		CsMember csMember=(CsMember) this.getSqlMapClientTemplate().queryForObject("getCsMemberById",params);
		if(csMember!=null) {
            CsMemberInfo csMemberInfo = csMemberInfoDao.getCsMemberInfoById(csMember.getCsmInfo());
            if(null != csMemberInfo) {
                csMember.setCsmRemark(csMember.getCsmRemark()+"    公司："+csMemberInfo.getCsmiCompany$()+"\n单位："+csMemberInfo.getCsmiDepartment());
            }
        }
		return   csMember;
	}
	

	/**
	 * 保存会员帐号
	 * @param csMember
	 */
	public CsMember saveCsMember(CsMember csMember)
	{
		csMember = custom(csMember);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsMember", csMember));
		csMember = this.GetCsMemberById(id);
		MemCache.setValue(CsMember.class,id, csMember.getKeyValue());
		return Trigger.on(CsMember.class,Trigger.SAVE,csMember);	
	}

	

	/**
	 * 更新会员帐号
	 * @param csMember
	 */
	public void updateCsMember(CsMember csMember)
	{
		csMember = custom(csMember);
		this.getSqlMapClientTemplate().update("updateCsMember", csMember);
		csMember = this.GetCsMemberById(csMember.getCsmId());
		if(csMember!=null){
			MemCache.setValue(CsMember.class,csMember.getCsmId(), csMember.getKeyValue());
		}
		Trigger.on(CsMember.class,Trigger.UPDATE,csMember);
	}
	/**
	 * 更新会员帐号非空字段
	 * @param csMember
	 */
	public void updateCsMember$NotNull(CsMember csMember)
	{
		csMember = custom(csMember);
		this.getSqlMapClientTemplate().update("updateCsMember$NotNull", csMember);
		csMember = this.GetCsMemberById(csMember.getCsmId());
		if(csMember!=null){
			MemCache.setValue(CsMember.class,csMember.getCsmId(), csMember.getKeyValue());
		}
		Trigger.on(CsMember.class,Trigger.UPDATE,csMember);
	}

	/**
	 * 根据ID删除一个会员帐号
	 * @param id
	 */
	public void deleteCsMemberById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMember.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsMemberById", params);
		MemCache.deleteValue(CsMember.class, id);
	}
	/**
	 * 根据ID逻辑删除会员帐号 
	 * @param id
	 * @return
	 */
	public void removeCsMemberById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsMember.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().update("removeCsMemberById", params);
		MemCache.deleteValue(CsMember.class, id);
	}
	/**
	 * 根据条件更新会员帐号
	 * @param values
	 * @param params
	 */
	public void updateCsMemberByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberCount(params)>255){
			MemCache.clearValue(CsMember.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMember.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsMemberBy", allParams);
		Trigger.on(CsMember.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除会员帐号
	 * @param params
	 */
	public void deleteCsMemberByConfirm(Map params){
		Trigger.on(CsMember.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsMemberCount(params)>255){
			MemCache.clearValue(CsMember.class);
		}else{
			params.put("eval","csm_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsMemberEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsMember.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsMemberBy", params);
	}
	

}