package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsCarPhotoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarPhoto;
import com.lazy3q.web.helper.$;

/**
 * 车辆照片的Dao实现
 * @author 飞啊飘啊
 * 感谢ibatis的持久层框架
 */
@SuppressWarnings("unchecked")
public class CsCarPhotoDao extends SqlMapClientDaoSupport implements ICsCarPhotoDao
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
	private CsCarPhoto custom(CsCarPhoto params){
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
	 * 获取所有车辆照片
	 * @return
	 */	
	public List<CsCarPhoto> getCsCarPhotoList(Map params,Integer size)
	{		
		params = custom(params);
		params.put("SIZE", size);//放入查询条数，作为缓存唯一依据
		String json = $.json(params);
		String md5 = $.md5(json);
		
		List<CsCarPhoto> list = new ArrayList();
				
		
		if(size!=null && size!=-1)
			params.put("limit", size);
		list = this.getSqlMapClientTemplate().queryForList("getCsCarPhotoList", params);
		
		return list;
	}
	
	
	/**
	 * 获取车辆照片统计
	 * @return
	 */
	public List getCsCarPhotoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
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
		return this.getSqlMapClientTemplate().queryForList("getCsCarPhotoStats", params);
	}

	/**
	 * 获取车辆照片分页
	 * @return
	 */
	public Page getCsCarPhotoPage(int page,int size,Map params)
	{
		params = custom(params);
		params=likeKey(params);
		
		if(size<1)size=20;
		
		//获取分页总数
		Long count=getCsCarPhotoCount(params);
		//取分页的队列
		
		params.put("limit", size);
		params.put("offset", (int)Page.test(page,size,count));
		List list=this.getSqlMapClientTemplate().queryForList("getCsCarPhotoPage", params);
		//返回一个包装分页对象
		return new Page(page,size,count,list);
	}
	
	/**
	 * 获取车辆照片数量
	 * @return
	 */	
	public Long getCsCarPhotoCount(Map params)
	{
		params = custom(params);
		params=likeKey(params);
		params.put("eval","count(*)");
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarPhotoEval", params);
		return (Long)result.get("eval");
	}
	
	/**
	 * 获取车辆照片自定义求和表达式,比如求ID平均值:eval="avg(id)"
	 * @return
	 */	
	public <T> T getCsCarPhotoEval(String eval,Map params)
	{
		params = custom(params);
		params=likeKey(params); 
		params.put("eval",eval);
		Map result = (Map) this.getSqlMapClientTemplate().queryForObject("getCsCarPhotoEval", params);
		return (T)result.get("eval");
	}
	
	
	/**
	 * 根据查询条件取车辆照片
	 * @param params
	 * @return
	 */
	public CsCarPhoto getCsCarPhoto(Map params)
	{		
		params = custom(params);
			
		List<CsCarPhoto> list=this.getCsCarPhotoList(params,1);
		CsCarPhoto csCarPhoto = list.isEmpty()?null:list.get(0);
		
		return csCarPhoto;
	}
		
	/**
	 * 根据ID取车辆照片
	 * @param id
	 * @return
	 */
	public CsCarPhoto getCsCarPhotoById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		CsCarPhoto csCarPhoto = null;
		if(csCarPhoto!=null)
			return csCarPhoto;
			
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		csCarPhoto = (CsCarPhoto) this.getSqlMapClientTemplate().queryForObject("getCsCarPhotoById",params);
		if(csCarPhoto!=null)
			MemCache.setValue(CsCarPhoto.class,id, csCarPhoto.getKeyValue());
		return csCarPhoto;
	}
	
	/**
	 * 根据ID取车辆照片(不缓存)
	 * @param id
	 * @return
	 */
	public CsCarPhoto GetCsCarPhotoById(Long id){
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		return  (CsCarPhoto) this.getSqlMapClientTemplate().queryForObject("getCsCarPhotoById",params);
	}
	

	/**
	 * 保存车辆照片
	 * @param csCarPhoto
	 */
	public CsCarPhoto saveCsCarPhoto(CsCarPhoto csCarPhoto)
	{
		csCarPhoto = custom(csCarPhoto);
		Long id=$.parseLong(this.getSqlMapClientTemplate().insert("saveCsCarPhoto", csCarPhoto));
		csCarPhoto = this.GetCsCarPhotoById(id);
		MemCache.setValue(CsCarPhoto.class,id, csCarPhoto.getKeyValue());
		return Trigger.on(CsCarPhoto.class,Trigger.SAVE,csCarPhoto);	
	}

	

	/**
	 * 更新车辆照片
	 * @param csCarPhoto
	 */
	public void updateCsCarPhoto(CsCarPhoto csCarPhoto)
	{
		csCarPhoto = custom(csCarPhoto);
		this.getSqlMapClientTemplate().update("updateCsCarPhoto", csCarPhoto);
		csCarPhoto = this.GetCsCarPhotoById(csCarPhoto.getCscpId());
		if(csCarPhoto!=null){
			MemCache.setValue(CsCarPhoto.class,csCarPhoto.getCscpId(), csCarPhoto.getKeyValue());
		}
		Trigger.on(CsCarPhoto.class,Trigger.UPDATE,csCarPhoto);
	}
	/**
	 * 更新车辆照片非空字段
	 * @param csCarPhoto
	 */
	public void updateCsCarPhoto$NotNull(CsCarPhoto csCarPhoto)
	{
		csCarPhoto = custom(csCarPhoto);
		this.getSqlMapClientTemplate().update("updateCsCarPhoto$NotNull", csCarPhoto);
		csCarPhoto = this.GetCsCarPhotoById(csCarPhoto.getCscpId());
		if(csCarPhoto!=null){
			MemCache.setValue(CsCarPhoto.class,csCarPhoto.getCscpId(), csCarPhoto.getKeyValue());
		}
		Trigger.on(CsCarPhoto.class,Trigger.UPDATE,csCarPhoto);
	}

	/**
	 * 根据ID删除一个车辆照片
	 * @param id
	 */
	public void deleteCsCarPhotoById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		params = custom(params);
		Trigger.on(CsCarPhoto.class,Trigger.DELETE,params);
		this.getSqlMapClientTemplate().delete("deleteCsCarPhotoById", params);
		MemCache.deleteValue(CsCarPhoto.class, id);
	}
	/**
	 * 根据条件更新车辆照片
	 * @param values
	 * @param params
	 */
	public void updateCsCarPhotoByConfirm(Map values, Map params){
		Map allParams = new HashMap();
		for(Map.Entry<String,Object> entry:((Map<String,Object>)values).entrySet())
			allParams.put("V_"+entry.getKey(), entry.getValue());
		for(Map.Entry<String,Object> entry:((Map<String,Object>)params).entrySet())
			allParams.put(entry.getKey(), entry.getValue());
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarPhotoCount(params)>255){
			MemCache.clearValue(CsCarPhoto.class);
		}else{
			params.put("eval","cscp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarPhotoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarPhoto.class, map.get("eval"));
			}
		}
			
		int rows = this.getSqlMapClientTemplate().update("updateCsCarPhotoBy", allParams);
		Trigger.on(CsCarPhoto.class,Trigger.UPDATE,allParams);		
	}
	/**
	 * 根据条件删除车辆照片
	 * @param params
	 */
	public void deleteCsCarPhotoByConfirm(Map params){
		Trigger.on(CsCarPhoto.class,Trigger.DELETE,params);
		//如果更新条数大于255，则清空缓存
		if(this.getCsCarPhotoCount(params)>255){
			MemCache.clearValue(CsCarPhoto.class);
		}else{
			params.put("eval","cscp_id");
			List<Map> result = (List<Map>) this.getSqlMapClientTemplate().queryForList("getCsCarPhotoEval", params);
			for(Map map:result){
				MemCache.deleteValue(CsCarPhoto.class, map.get("eval"));
			}
		}
		
		int rows = this.getSqlMapClientTemplate().update("deleteCsCarPhotoBy", params);
	}
	

}