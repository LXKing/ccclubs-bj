package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarPhotoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarPhoto;
import com.ccclubs.service.admin.ICsCarPhotoService;
import com.lazy3q.web.helper.$;

/**
 * 车辆照片的Service实现
 * @author 飞啊飘啊
 */
public class CsCarPhotoService implements ICsCarPhotoService
{
	ICsCarPhotoDao csCarPhotoDao;
	

	/**
	 * 获取所有车辆照片
	 * @return
	 */
	public List<CsCarPhoto> getCsCarPhotoList(Map params,Integer size)
	{
		return csCarPhotoDao.getCsCarPhotoList(params,size);
	}
	
	/**
	 * 获取车辆照片统计
	 * @return
	 */
	public List getCsCarPhotoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarPhotoDao.getCsCarPhotoStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆照片总数
	 * @return
	 */
	public Long getCsCarPhotoCount(Map params)
	{
		return csCarPhotoDao.getCsCarPhotoCount(params);
	}
	
	/**
	 * 获取车辆照片自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarPhotoEval(String eval,Map params)
	{
		return csCarPhotoDao.getCsCarPhotoEval(eval,params);
	}

	/**
	 * 获取车辆照片分页
	 * @return
	 */
	public Page getCsCarPhotoPage(int page,int size,Map params)
	{
		return csCarPhotoDao.getCsCarPhotoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆照片
	 * @param params
	 * @return
	 */
	public CsCarPhoto getCsCarPhoto(Map params)
	{
		return csCarPhotoDao.getCsCarPhoto(params);
	}

	/**
	 * 根据ID取车辆照片
	 * @param id
	 * @return
	 */
	public CsCarPhoto getCsCarPhotoById(Long id)
	{
		return csCarPhotoDao.getCsCarPhotoById(id);
	}

	/**
	 * 保存车辆照片
	 * @param csCarPhoto
	 */
	public CsCarPhoto saveCsCarPhoto(CsCarPhoto csCarPhoto)
	{
		return csCarPhotoDao.saveCsCarPhoto(csCarPhoto);
	}
	
	/**
	 * 更新车辆照片
	 * @param csCarPhoto
	 */
	public void updateCsCarPhoto(CsCarPhoto csCarPhoto)
	{
		csCarPhotoDao.updateCsCarPhoto(csCarPhoto);
	}
	/**
	 * 更新车辆照片非空字段
	 * @param csCarPhoto
	 */
	public void updateCsCarPhoto$NotNull(CsCarPhoto csCarPhoto)
	{
		csCarPhotoDao.updateCsCarPhoto$NotNull(csCarPhoto);
	}
	
	/**
	 * 根据ID删除一个车辆照片
	 * @param id
	 */
	public void deleteCsCarPhotoById(Long id)
	{
		csCarPhotoDao.deleteCsCarPhotoById(id);
	}
	
	/**
	 * 根据条件更新车辆照片
	 * @param values
	 * @param params
	 */
	public void updateCsCarPhotoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarPhotoBy时无条件");
		csCarPhotoDao.updateCsCarPhotoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆照片
	 * @param params
	 */
	public void deleteCsCarPhotoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarPhotoBy时无条件");
		csCarPhotoDao.deleteCsCarPhotoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarPhotoDao getCsCarPhotoDao()
	{
		return csCarPhotoDao;
	}

	public void setCsCarPhotoDao(ICsCarPhotoDao csCarPhotoDao)
	{
		this.csCarPhotoDao = csCarPhotoDao;
	}
}