package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarPhoto;

/**
 * 车辆照片的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCarPhotoDao
{
	/**
	 * 获取所有车辆照片
	 * @return
	 */
	public List<CsCarPhoto> getCsCarPhotoList(Map params,Integer size);
	
	/**
	 * 获取车辆照片统计
	 * @return
	 */
	public List getCsCarPhotoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆照片总数
	 * @return
	 */	
	public Long getCsCarPhotoCount(Map params);
	
	/**
	 * 获取车辆照片自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarPhotoEval(String eval,Map params);
	
	/**
	 * 获取车辆照片分页
	 * @return
	 */	
	public Page getCsCarPhotoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆照片
	 * @param params
	 * @return
	 */
	public CsCarPhoto getCsCarPhoto(Map params);	

	/**
	 * 根据ID取车辆照片
	 * @param id
	 * @return
	 */
	public CsCarPhoto getCsCarPhotoById(Long id);


	
	

	/**
	 * 保存车辆照片
	 * @param csCarPhoto
	 */
	public CsCarPhoto saveCsCarPhoto(CsCarPhoto csCarPhoto);

	/**
	 * 更新车辆照片
	 * @param csCarPhoto
	 */
	public void updateCsCarPhoto(CsCarPhoto csCarPhoto);
	/**
	 * 更新车辆照片非空字段
	 * @param csCarPhoto
	 */
	public void updateCsCarPhoto$NotNull(CsCarPhoto csCarPhoto);

	/**
	 * 根据ID删除一个车辆照片
	 * @param id
	 */
	public void deleteCsCarPhotoById(Long id);
	/**
	 * 根据条件更新车辆照片
	 * @param values
	 * @param params
	 */
	public void updateCsCarPhotoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆照片
	 * @param params
	 */
	public void deleteCsCarPhotoByConfirm(Map params);

}