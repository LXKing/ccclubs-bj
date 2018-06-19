package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("systematic/carphoto")
public @caption("车辆照片") @table("cs_car_photo") class CsCarPhoto implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscp_id")   @primary  @note("  ") Long cscpId;// 主键 非空     
	private @caption("城市") @column("cscp_host")    @relate("$cscpHost") @RelateClass(SrvHost.class)  @note("  ") Long cscpHost;// 非空     
 	private SrvHost $cscpHost;//关联对象[运营城市]
	private @caption("所属车辆") @column("cscp_car")    @relate("$cscpCar") @RelateClass(CsCar.class)  @note("  ") Long cscpCar;// 非空     
 	private CsCar $cscpCar;//关联对象[车辆]
	private @caption("照片日期") @column("cscp_date")    @note("  ") Date cscpDate;// 非空     
	private @caption("照片列表") @column("cscp_images")    @note("  ") String cscpImages;// 非空     
	private @caption("上传人员") @column("cscp_editor")    @relate("$cscpEditor") @RelateClass(SrvUser.class)  @note("  ") Long cscpEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cscpEditor;//关联对象[用户]
	private @caption("备注信息") @column("cscp_remark")    @note("  ") String cscpRemark;//     
	private @caption("添加时间") @column("cscp_add_time")    @note("  ") Date cscpAddTime;// 非空     
	
	//默认构造函数
	public CsCarPhoto(){
	
	}
	
	//主键构造函数
	public CsCarPhoto(Long id){
		this.cscpId = id;
	}
	
	/**所有字段构造函数 CsCarPhoto(cscpHost,cscpCar,cscpDate,cscpImages,cscpEditor,cscpRemark,cscpAddTime)
	 CsCarPhoto(
	 	$.getLong("cscpHost")//城市 [非空]
	 	,$.getLong("cscpCar")//所属车辆 [非空]
	 	,$.getDate("cscpDate")//照片日期 [非空]
	 	,$.getString("cscpImages")//照片列表 [非空]
	 	,$.getLong("cscpEditor")//上传人员 [非空]
	 	,$.getString("cscpRemark")//备注信息
	 	,$.getDate("cscpAddTime")//添加时间 [非空]
	 )
	**/
	public CsCarPhoto(Long cscpHost,Long cscpCar,Date cscpDate,String cscpImages,Long cscpEditor,String cscpRemark,Date cscpAddTime){
		this.cscpHost=cscpHost;
		this.cscpCar=cscpCar;
		this.cscpDate=cscpDate;
		this.cscpImages=cscpImages;
		this.cscpEditor=cscpEditor;
		this.cscpRemark=cscpRemark;
		this.cscpAddTime=cscpAddTime;
	}
	
	//设置非空字段
	public CsCarPhoto setNotNull(Long cscpHost,Long cscpCar,Date cscpDate,String cscpImages,Long cscpEditor,Date cscpAddTime){
		this.cscpHost=cscpHost;
		this.cscpCar=cscpCar;
		this.cscpDate=cscpDate;
		this.cscpImages=cscpImages;
		this.cscpEditor=cscpEditor;
		this.cscpAddTime=cscpAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCarPhoto cscpId(Long cscpId){
		this.cscpId = cscpId;
		this.setSeted(F.cscpId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsCarPhoto cscpHost(Long cscpHost){
		this.cscpHost = cscpHost;
		this.setSeted(F.cscpHost);
		return this;
	}
	/** 所属车辆 [非空] [CsCar]      **/
	public CsCarPhoto cscpCar(Long cscpCar){
		this.cscpCar = cscpCar;
		this.setSeted(F.cscpCar);
		return this;
	}
	/** 照片日期 [非空]       **/
	public CsCarPhoto cscpDate(Date cscpDate){
		this.cscpDate = cscpDate;
		this.setSeted(F.cscpDate);
		return this;
	}
	/** 照片列表 [非空]       **/
	public CsCarPhoto cscpImages(String cscpImages){
		this.cscpImages = cscpImages;
		this.setSeted(F.cscpImages);
		return this;
	}
	/** 上传人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCarPhoto cscpEditor(Long cscpEditor){
		this.cscpEditor = cscpEditor;
		this.setSeted(F.cscpEditor);
		return this;
	}
	/** 备注信息        **/
	public CsCarPhoto cscpRemark(String cscpRemark){
		this.cscpRemark = cscpRemark;
		this.setSeted(F.cscpRemark);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCarPhoto cscpAddTime(Date cscpAddTime){
		this.cscpAddTime = cscpAddTime;
		this.setSeted(F.cscpAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsCarPhoto clone(){
		CsCarPhoto clone = new CsCarPhoto();
		clone.cscpHost=this.cscpHost;
		clone.cscpCar=this.cscpCar;
		clone.cscpDate=this.cscpDate;
		clone.cscpImages=this.cscpImages;
		clone.cscpEditor=this.cscpEditor;
		clone.cscpAddTime=this.cscpAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆照片
	 * @param id
	 * @return
	 */
	public static @api CsCarPhoto get(Long id){		
		return getCsCarPhotoById(id);
	}
	/**
	 * 获取所有车辆照片
	 * @return
	 */
	public static @api List<CsCarPhoto> list(Map params,Integer size){
		return getCsCarPhotoList(params,size);
	}
	/**
	 * 获取车辆照片分页
	 * @return
	 */
	public static @api Page<CsCarPhoto> page(int page,int size,Map params){
		return getCsCarPhotoPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆照片
	 * @param params
	 * @return
	 */
	public static @api CsCarPhoto Get(Map params){
		return getCsCarPhoto(params);
	}
	/**
	 * 获取车辆照片数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarPhotoCount(params);
	}
	/**
	 * 获取车辆照片数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarPhotoEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆照片
	 * @param id
	 * @return
	 */
	public static @api CsCarPhoto getCsCarPhotoById(Long id){		
		CsCarPhoto csCarPhoto = (CsCarPhoto) $.GetRequest("CsCarPhoto$"+id);
		if(csCarPhoto!=null)
			return csCarPhoto;
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");		
		return csCarPhotoService.getCsCarPhotoById(id);
	}
	
	
	/**
	 * 根据ID取车辆照片的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCarPhoto.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCarPhoto csCarPhoto = get(id);
		if(csCarPhoto!=null){
			String strValue = csCarPhoto.getCscpId$();
			if(!"CscpId".equals("CscpId"))
				strValue+="("+csCarPhoto.getCscpId$()+")";
			MemCache.setValue(CsCarPhoto.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscpId$();
		if(!"CscpId".equals("CscpId"))
			keyValue+="("+this.getCscpId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆照片
	 * @return
	 */
	public static @api List<CsCarPhoto> getCsCarPhotoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		return csCarPhotoService.getCsCarPhotoList(params, size);
	}
	
	/**
	 * 获取车辆照片分页
	 * @return
	 */
	public static @api Page<CsCarPhoto> getCsCarPhotoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		return csCarPhotoService.getCsCarPhotoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆照片
	 * @param params
	 * @return
	 */
	public static @api CsCarPhoto getCsCarPhoto(Map params){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		return csCarPhotoService.getCsCarPhoto(params);
	}
	
	/**
	 * 获取车辆照片数
	 * @return
	 */
	public static @api Long getCsCarPhotoCount(Map params){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		return csCarPhotoService.getCsCarPhotoCount(params);
	}
		
		
	/**
	 * 获取车辆照片自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarPhotoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		return csCarPhotoService.getCsCarPhotoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCarPhoto(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		csCarPhotoService.updateCsCarPhotoByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆照片对象
	 * @param params
	 * @return
	 */
	public CsCarPhoto save(){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		if(this.getCscpId()!=null)
			csCarPhotoService.updateCsCarPhoto(this);
		else
			return csCarPhotoService.saveCsCarPhoto(this);
		return this;
	}
	
	
	/**
	 * 更新车辆照片对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		csCarPhotoService.updateCsCarPhoto$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
			csCarPhotoService.deleteCsCarPhotoById(this.getCscpId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarPhotoService csCarPhotoService = $.GetSpringBean("csCarPhotoService");
		return csCarPhotoService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscpId(){
		return this.cscpId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscpId$(){
		String strValue="";
		 strValue=$.str(this.getCscpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscpId(Long cscpId){
		this.cscpId = cscpId;
		this.setSeted(F.cscpId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscpHost(){
		return this.cscpHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscpHost$(){
		String strValue="";
		if(this.getCscpHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscpHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscpHost(Long cscpHost){
		this.cscpHost = cscpHost;
		this.setSeted(F.cscpHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscpHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscpHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscpHost()!=null){
 			srvHost = SrvHost.get(this.getCscpHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscpHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属车辆**********************************/	
	/**
	* 所属车辆 [非空] [CsCar]     
	**/
	public Long getCscpCar(){
		return this.cscpCar;
	}
	/**
	* 获取所属车辆格式化(toString)
	**/
	public String getCscpCar$(){
		String strValue="";
		if(this.getCscpCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCscpCar()));
		}			
	 	 return strValue;
	}
	/**
	* 所属车辆 [非空] [CsCar]     
	**/
	public void setCscpCar(Long cscpCar){
		this.cscpCar = cscpCar;
		this.setSeted(F.cscpCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$cscpCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCscpCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCscpCar()!=null){
 			csCar = CsCar.get(this.getCscpCar());
 		}
 		$.SetRequest("CsCar$"+this.getCscpCar(), csCar);
	 	return csCar;
	}
	/*******************************照片日期**********************************/	
	/**
	* 照片日期 [非空]      
	**/
	public Date getCscpDate(){
		return this.cscpDate;
	}
	/**
	* 获取照片日期格式化(toString)
	**/
	public String getCscpDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscpDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 照片日期 [非空]      
	**/
	public void setCscpDate(Date cscpDate){
		this.cscpDate = cscpDate;
		this.setSeted(F.cscpDate);
	}
	/*******************************照片列表**********************************/	
	/**
	* 照片列表 [非空]      
	**/
	public String getCscpImages(){
		return this.cscpImages;
	}
	/**
	* 获取照片列表格式化(toString)
	**/
	public String getCscpImages$(){
		String strValue="";
		 strValue=$.str(this.getCscpImages());
	 	 return strValue;
	}
	/**
	* 照片列表 [非空]      
	**/
	public void setCscpImages(String cscpImages){
		this.cscpImages = cscpImages;
		this.setSeted(F.cscpImages);
	}
	/*******************************上传人员**********************************/	
	/**
	* 上传人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCscpEditor(){
		return this.cscpEditor;
	}
	/**
	* 获取上传人员格式化(toString)
	**/
	public String getCscpEditor$(){
		String strValue="";
		if(this.getCscpEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscpEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 上传人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCscpEditor(Long cscpEditor){
		this.cscpEditor = cscpEditor;
		this.setSeted(F.cscpEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cscpEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscpEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscpEditor()!=null){
 			srvUser = SrvUser.get(this.getCscpEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCscpEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCscpRemark(){
		return this.cscpRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCscpRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscpRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCscpRemark(String cscpRemark){
		this.cscpRemark = cscpRemark;
		this.setSeted(F.cscpRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscpAddTime(){
		return this.cscpAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscpAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscpAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscpAddTime(Date cscpAddTime){
		this.cscpAddTime = cscpAddTime;
		this.setSeted(F.cscpAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCarPhoto.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarPhoto.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarPhoto.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCarPhoto.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCarPhoto.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarPhoto.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarPhoto.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCarPhoto.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 获取查询参数链式串
	 * @return
	 */
	public static M where(){ return new M(0); }
	public static M m(){ return new M(0); }
	public static M set(){ return new M(1); }
	public static class M extends HashMap{
		int type = 0;
		M linked = null;
		public M(int type){this.type = type;};
		public M where(){
			this.linked=new M(0);
			this.linked.linked=this;
			return this.linked;
		};
		public M set(){
			this.linked=new M(1);
			this.linked.linked=this;
			return this.linked;
		};
		/** 编号 [非空]       **/
		public M cscpId(Object cscpId){this.put("cscpId", cscpId);return this;};
	 	/** and cscp_id is null */
 		public M cscpIdNull(){if(this.get("cscpIdNot")==null)this.put("cscpIdNot", "");this.put("cscpId", null);return this;};
 		/** not .... */
 		public M cscpIdNot(){this.put("cscpIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscpHost(Object cscpHost){this.put("cscpHost", cscpHost);return this;};
	 	/** and cscp_host is null */
 		public M cscpHostNull(){if(this.get("cscpHostNot")==null)this.put("cscpHostNot", "");this.put("cscpHost", null);return this;};
 		/** not .... */
 		public M cscpHostNot(){this.put("cscpHostNot", "not");return this;};
		/** 所属车辆 [非空] [CsCar]      **/
		public M cscpCar(Object cscpCar){this.put("cscpCar", cscpCar);return this;};
	 	/** and cscp_car is null */
 		public M cscpCarNull(){if(this.get("cscpCarNot")==null)this.put("cscpCarNot", "");this.put("cscpCar", null);return this;};
 		/** not .... */
 		public M cscpCarNot(){this.put("cscpCarNot", "not");return this;};
		public M cscpCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cscpCar$on", value);
			return this;
		};	
		/** 照片日期 [非空]       **/
		public M cscpDate(Object cscpDate){this.put("cscpDate", cscpDate);return this;};
	 	/** and cscp_date is null */
 		public M cscpDateNull(){if(this.get("cscpDateNot")==null)this.put("cscpDateNot", "");this.put("cscpDate", null);return this;};
 		/** not .... */
 		public M cscpDateNot(){this.put("cscpDateNot", "not");return this;};
 		/** and cscp_date >= ? */
 		public M cscpDateStart(Object start){this.put("cscpDateStart", start);return this;};			
 		/** and cscp_date <= ? */
 		public M cscpDateEnd(Object end){this.put("cscpDateEnd", end);return this;};
		/** 照片列表 [非空]       **/
		public M cscpImages(Object cscpImages){this.put("cscpImages", cscpImages);return this;};
	 	/** and cscp_images is null */
 		public M cscpImagesNull(){if(this.get("cscpImagesNot")==null)this.put("cscpImagesNot", "");this.put("cscpImages", null);return this;};
 		/** not .... */
 		public M cscpImagesNot(){this.put("cscpImagesNot", "not");return this;};
		/** 上传人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cscpEditor(Object cscpEditor){this.put("cscpEditor", cscpEditor);return this;};
	 	/** and cscp_editor is null */
 		public M cscpEditorNull(){if(this.get("cscpEditorNot")==null)this.put("cscpEditorNot", "");this.put("cscpEditor", null);return this;};
 		/** not .... */
 		public M cscpEditorNot(){this.put("cscpEditorNot", "not");return this;};
		/** 备注信息        **/
		public M cscpRemark(Object cscpRemark){this.put("cscpRemark", cscpRemark);return this;};
	 	/** and cscp_remark is null */
 		public M cscpRemarkNull(){if(this.get("cscpRemarkNot")==null)this.put("cscpRemarkNot", "");this.put("cscpRemark", null);return this;};
 		/** not .... */
 		public M cscpRemarkNot(){this.put("cscpRemarkNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M cscpAddTime(Object cscpAddTime){this.put("cscpAddTime", cscpAddTime);return this;};
	 	/** and cscp_add_time is null */
 		public M cscpAddTimeNull(){if(this.get("cscpAddTimeNot")==null)this.put("cscpAddTimeNot", "");this.put("cscpAddTime", null);return this;};
 		/** not .... */
 		public M cscpAddTimeNot(){this.put("cscpAddTimeNot", "not");return this;};
 		/** and cscp_add_time >= ? */
 		public M cscpAddTimeStart(Object start){this.put("cscpAddTimeStart", start);return this;};			
 		/** and cscp_add_time <= ? */
 		public M cscpAddTimeEnd(Object end){this.put("cscpAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆照片 **/
		public @api List<CsCarPhoto> list(Integer size){
			return getCsCarPhotoList(this,size);
		}
		/** 获取车辆照片分页 **/
		public @api Page<CsCarPhoto> page(int page,int size){
			return getCsCarPhotoPage(page, size , this);
		}
		/** 根据查询条件取车辆照片 **/
		public @api CsCarPhoto get(){
			return getCsCarPhoto(this);
		}
		/** 获取车辆照片数 **/
		public @api Long count(){
			return getCsCarPhotoCount(this);
		}
		/** 获取车辆照片表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarPhotoEval(strEval,this);
		}
		/** 根据条件更新 **/
		public @api void update(){
			M where = null;
			M set = null;
			if(this.type==0){
				if(this.linked==null)throw new RuntimeException("未发set");
				where = this;
				set = this.linked;
			}else{
				if(this.linked==null)throw new RuntimeException("未发where条件");
				set = this;
				where = this.linked;
			}
			updateCsCarPhoto(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscpId="cscpId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscpHost="cscpHost";
		/** 所属车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String cscpCar="cscpCar";
		/** 照片日期 [非空]       **/
		public final static @type(Date.class)  String cscpDate="cscpDate";
	 	/** and cscp_date >= ? */
 		public final static @type(Date.class) String cscpDateStart="cscpDateStart";
 		/** and cscp_date <= ? */
 		public final static @type(Date.class) String cscpDateEnd="cscpDateEnd";
		/** 照片列表 [非空]       **/
		public final static @type(String.class)  String cscpImages="cscpImages";
		/** 上传人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cscpEditor="cscpEditor";
		/** 备注信息        **/
		public final static @type(String.class) @like String cscpRemark="cscpRemark";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscpAddTime="cscpAddTime";
	 	/** and cscp_add_time >= ? */
 		public final static @type(Date.class) String cscpAddTimeStart="cscpAddTimeStart";
 		/** and cscp_add_time <= ? */
 		public final static @type(Date.class) String cscpAddTimeEnd="cscpAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscpId="cscp_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscpHost="cscp_host";
		/** 所属车辆 [非空] [CsCar]      **/
		public final static String cscpCar="cscp_car";
		/** 照片日期 [非空]       **/
		public final static String cscpDate="cscp_date";
		/** 照片列表 [非空]       **/
		public final static String cscpImages="cscp_images";
		/** 上传人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cscpEditor="cscp_editor";
		/** 备注信息        **/
		public final static String cscpRemark="cscp_remark";
		/** 添加时间 [非空]       **/
		public final static String cscpAddTime="cscp_add_time";
	 	public static String get(String name){
			try {
				Field field = C.class.getField(name);
				if(field!=null)
					return (String)field.get(null);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			return null;
		}
	}
	
	
	
	/**
	 * 通过页面标签获取对象
	 * @author uiu
	 */
	public static class Tag extends BodyTag{
		String name;
		Integer size;
		@Override
		public void execute(String strContent) {
			Map params = $.eval(strContent);
			if(size==null)
				$.Set(name,CsCarPhoto.getCsCarPhoto(params));
			else
				$.Set(name,CsCarPhoto.getCsCarPhotoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆照片数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCarPhoto) $.GetRequest("CsCarPhoto$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCarPhoto.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCarPhoto.getCsCarPhoto(params);
			else
				value = CsCarPhoto.getCsCarPhotoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCarPhoto.Get($.add(CsCarPhoto.F.cscpId,param));
		else if(!$.empty(param.toString()))
			value = CsCarPhoto.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCarPhoto$"+param.hashCode(), value);
		return value;
	}
	
	
	
	
	/**字段设置标记队列**/
	private Map<String,Boolean> seted=new HashMap();
	public Boolean getSeted(String key){
		return seted.get(key);
	}
	public void setSeted(String key){
		seted.put(key,true);
	}
	/**同步未set的字段**/
	public void mergeSet(CsCarPhoto old){
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field field:fields){
				String key = field.getName();
				if(seted.get(key)==null && field.getAnnotation(column.class)!=null){
					field.setAccessible(true);
					field.set(this, field.get(old));					
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
		
	/**自定义变量存储**/
	private Map values=new HashMap();
	public Map getValues(){
		return values;
	}
	public void setValues(String name,Object value){
		values.put(name,value);
	}
	
	/**分表时的表名后缀**/
	private String suffix;
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}