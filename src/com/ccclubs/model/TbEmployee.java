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

@namespace("oa/hr/employee")
public @caption("公司员工") @table("tb_employee") class TbEmployee implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbe_id")   @primary  @note("  ") Long tbeId;// 主键 非空     
	private @caption("姓名") @column("tbe_name")    @note("  ") String tbeName;// 非空     
	private @caption("部门") @column("tbe_depart")    @relate("$tbeDepart") @RelateClass(TbDepartment.class)  @note("  ") Long tbeDepart;// 非空     
 	private TbDepartment $tbeDepart;//关联对象[公司部门]
	private @caption("微信标识") @column("tbe_weixin_flag")    @note("  ") String tbeWeixinFlag;//     
	private @caption("修改人") @column("tbe_editor")  @hidden   @relate("$tbeEditor") @RelateClass(SrvUser.class)  @note("  ") Long tbeEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $tbeEditor;//关联对象[用户]
	private @caption("备注") @column("tbe_remark")    @note("  ") String tbeRemark;//     
	private @caption("备忘信息") @column("tbe_memo")    @note(" textarea:content text:sign  ") String tbeMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("tbe_data")    @note("  ") String tbeData;//     
	private @caption("修改时间") @column("tbe_update_time")    @note("  ") Date tbeUpdateTime;// 非空     
	private @caption("添加时间") @column("tbe_add_time")    @note("  ") Date tbeAddTime;// 非空     
	private @caption("状态") @column("tbe_status")    @note(" 0:待审核 1:正常 2:无效  ") Short tbeStatus;// 非空 0:待审核 1:正常 2:无效     
	
	//默认构造函数
	public TbEmployee(){
	
	}
	
	//主键构造函数
	public TbEmployee(Long id){
		this.tbeId = id;
	}
	
	/**所有字段构造函数 TbEmployee(tbeName,tbeDepart,tbeWeixinFlag,tbeEditor,tbeRemark,tbeMemo,tbeData,tbeUpdateTime,tbeAddTime,tbeStatus)
	 TbEmployee(
	 	$.getString("tbeName")//姓名 [非空]
	 	,$.getLong("tbeDepart")//部门 [非空]
	 	,$.getString("tbeWeixinFlag")//微信标识
	 	,$.getLong("tbeEditor")//修改人 [非空]
	 	,$.getString("tbeRemark")//备注
	 	,$.getString("tbeMemo")//备忘信息
	 	,$.getString("tbeData")//程序数据
	 	,$.getDate("tbeUpdateTime")//修改时间 [非空]
	 	,$.getDate("tbeAddTime")//添加时间 [非空]
	 	,$.getShort("tbeStatus")//状态 [非空]
	 )
	**/
	public TbEmployee(String tbeName,Long tbeDepart,String tbeWeixinFlag,Long tbeEditor,String tbeRemark,String tbeMemo,String tbeData,Date tbeUpdateTime,Date tbeAddTime,Short tbeStatus){
		this.tbeName=tbeName;
		this.tbeDepart=tbeDepart;
		this.tbeWeixinFlag=tbeWeixinFlag;
		this.tbeEditor=tbeEditor;
		this.tbeRemark=tbeRemark;
		this.tbeMemo=tbeMemo;
		this.tbeData=tbeData;
		this.tbeUpdateTime=tbeUpdateTime;
		this.tbeAddTime=tbeAddTime;
		this.tbeStatus=tbeStatus;
	}
	
	//设置非空字段
	public TbEmployee setNotNull(String tbeName,Long tbeDepart,Long tbeEditor,Date tbeUpdateTime,Date tbeAddTime,Short tbeStatus){
		this.tbeName=tbeName;
		this.tbeDepart=tbeDepart;
		this.tbeEditor=tbeEditor;
		this.tbeUpdateTime=tbeUpdateTime;
		this.tbeAddTime=tbeAddTime;
		this.tbeStatus=tbeStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbEmployee tbeId(Long tbeId){
		this.tbeId = tbeId;
		this.setSeted(F.tbeId);
		return this;
	}
	/** 姓名 [非空]       **/
	public TbEmployee tbeName(String tbeName){
		this.tbeName = tbeName;
		this.setSeted(F.tbeName);
		return this;
	}
	/** 部门 [非空] [TbDepartment]      **/
	public TbEmployee tbeDepart(Long tbeDepart){
		this.tbeDepart = tbeDepart;
		this.setSeted(F.tbeDepart);
		return this;
	}
	/** 微信标识        **/
	public TbEmployee tbeWeixinFlag(String tbeWeixinFlag){
		this.tbeWeixinFlag = tbeWeixinFlag;
		this.setSeted(F.tbeWeixinFlag);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbEmployee tbeEditor(Long tbeEditor){
		this.tbeEditor = tbeEditor;
		this.setSeted(F.tbeEditor);
		return this;
	}
	/** 备注        **/
	public TbEmployee tbeRemark(String tbeRemark){
		this.tbeRemark = tbeRemark;
		this.setSeted(F.tbeRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public TbEmployee tbeMemo(String tbeMemo){
		this.tbeMemo = tbeMemo;
		this.setSeted(F.tbeMemo);
		return this;
	}
	/** 程序数据        **/
	public TbEmployee tbeData(String tbeData){
		this.tbeData = tbeData;
		this.setSeted(F.tbeData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public TbEmployee tbeUpdateTime(Date tbeUpdateTime){
		this.tbeUpdateTime = tbeUpdateTime;
		this.setSeted(F.tbeUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public TbEmployee tbeAddTime(Date tbeAddTime){
		this.tbeAddTime = tbeAddTime;
		this.setSeted(F.tbeAddTime);
		return this;
	}
	/** 状态 [非空]   0:待审核 1:正常 2:无效     **/
	public TbEmployee tbeStatus(Short tbeStatus){
		this.tbeStatus = tbeStatus;
		this.setSeted(F.tbeStatus);
		return this;
	}
	
	
	//克隆对象
	public TbEmployee clone(){
		TbEmployee clone = new TbEmployee();
		clone.tbeName=this.tbeName;
		clone.tbeDepart=this.tbeDepart;
		clone.tbeEditor=this.tbeEditor;
		clone.tbeUpdateTime=this.tbeUpdateTime;
		clone.tbeAddTime=this.tbeAddTime;
		clone.tbeStatus=this.tbeStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取公司员工
	 * @param id
	 * @return
	 */
	public static @api TbEmployee get(Long id){		
		return getTbEmployeeById(id);
	}
	/**
	 * 获取所有公司员工
	 * @return
	 */
	public static @api List<TbEmployee> list(Map params,Integer size){
		return getTbEmployeeList(params,size);
	}
	/**
	 * 获取公司员工分页
	 * @return
	 */
	public static @api Page<TbEmployee> page(int page,int size,Map params){
		return getTbEmployeePage(page, size , params);
	}
	/**
	 * 根据查询条件取公司员工
	 * @param params
	 * @return
	 */
	public static @api TbEmployee Get(Map params){
		return getTbEmployee(params);
	}
	/**
	 * 获取公司员工数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbEmployeeCount(params);
	}
	/**
	 * 获取公司员工数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbEmployeeEval(eval,params);
	}
	
	/**
	 * 根据ID取公司员工
	 * @param id
	 * @return
	 */
	public static @api TbEmployee getTbEmployeeById(Long id){		
		TbEmployee tbEmployee = (TbEmployee) $.GetRequest("TbEmployee$"+id);
		if(tbEmployee!=null)
			return tbEmployee;
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");		
		return tbEmployeeService.getTbEmployeeById(id);
	}
	
	
	/**
	 * 根据ID取公司员工的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbEmployee.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbEmployee tbEmployee = get(id);
		if(tbEmployee!=null){
			String strValue = tbEmployee.getTbeName$();
			if(!"TbeName".equals("TbeName"))
				strValue+="("+tbEmployee.getTbeName$()+")";
			MemCache.setValue(TbEmployee.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbeName$();
		if(!"TbeName".equals("TbeName"))
			keyValue+="("+this.getTbeName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有公司员工
	 * @return
	 */
	public static @api List<TbEmployee> getTbEmployeeList(Map params,Integer size){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		return tbEmployeeService.getTbEmployeeList(params, size);
	}
	
	/**
	 * 获取公司员工分页
	 * @return
	 */
	public static @api Page<TbEmployee> getTbEmployeePage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		return tbEmployeeService.getTbEmployeePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取公司员工
	 * @param params
	 * @return
	 */
	public static @api TbEmployee getTbEmployee(Map params){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		return tbEmployeeService.getTbEmployee(params);
	}
	
	/**
	 * 获取公司员工数
	 * @return
	 */
	public static @api Long getTbEmployeeCount(Map params){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		return tbEmployeeService.getTbEmployeeCount(params);
	}
		
		
	/**
	 * 获取公司员工自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbEmployeeEval(String eval,Map params){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		return tbEmployeeService.getTbEmployeeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbEmployee(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		tbEmployeeService.updateTbEmployeeByConfirm(set, where);
	}
	
	
	/**
	 * 保存公司员工对象
	 * @param params
	 * @return
	 */
	public TbEmployee save(){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		if(this.getTbeId()!=null)
			tbEmployeeService.updateTbEmployee(this);
		else
			return tbEmployeeService.saveTbEmployee(this);
		return this;
	}
	
	
	/**
	 * 更新公司员工对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		tbEmployeeService.updateTbEmployee$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		if($.equals($.config("logic_delete"),"true"))
			tbEmployeeService.removeTbEmployeeById(this.getTbeId());
		else
			tbEmployeeService.deleteTbEmployeeById(this.getTbeId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbEmployeeService tbEmployeeService = $.GetSpringBean("tbEmployeeService");
		return tbEmployeeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbeId(){
		return this.tbeId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbeId$(){
		String strValue="";
		 strValue=$.str(this.getTbeId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbeId(Long tbeId){
		this.tbeId = tbeId;
		this.setSeted(F.tbeId);
	}
	/*******************************姓名**********************************/	
	/**
	* 姓名 [非空]      
	**/
	public String getTbeName(){
		return this.tbeName;
	}
	/**
	* 获取姓名格式化(toString)
	**/
	public String getTbeName$(){
		String strValue="";
		 strValue=$.str(this.getTbeName());
	 	 return strValue;
	}
	/**
	* 姓名 [非空]      
	**/
	public void setTbeName(String tbeName){
		this.tbeName = tbeName;
		this.setSeted(F.tbeName);
	}
	/*******************************部门**********************************/	
	/**
	* 部门 [非空] [TbDepartment]     
	**/
	public Long getTbeDepart(){
		return this.tbeDepart;
	}
	/**
	* 获取部门格式化(toString)
	**/
	public String getTbeDepart$(){
		String strValue="";
		if(this.getTbeDepart()!=null){
				strValue+=$.str(TbDepartment.getKeyValue(this.getTbeDepart()));
		}			
	 	 return strValue;
	}
	/**
	* 部门 [非空] [TbDepartment]     
	**/
	public void setTbeDepart(Long tbeDepart){
		this.tbeDepart = tbeDepart;
		this.setSeted(F.tbeDepart);
	}
	/**
	* 获取关联对象[公司部门]
	**/	 			
 	public TbDepartment get$tbeDepart(){
 		com.ccclubs.model.TbDepartment tbDepartment = (com.ccclubs.model.TbDepartment) $.GetRequest("TbDepartment$"+this.getTbeDepart());
 		if(tbDepartment!=null)
			return tbDepartment;
		if(this.getTbeDepart()!=null){
 			tbDepartment = TbDepartment.get(this.getTbeDepart());
 		}
 		$.SetRequest("TbDepartment$"+this.getTbeDepart(), tbDepartment);
	 	return tbDepartment;
	}
	/*******************************微信标识**********************************/	
	/**
	* 微信标识       
	**/
	public String getTbeWeixinFlag(){
		return this.tbeWeixinFlag;
	}
	/**
	* 获取微信标识格式化(toString)
	**/
	public String getTbeWeixinFlag$(){
		String strValue="";
		 strValue=$.str(this.getTbeWeixinFlag());
	 	 return strValue;
	}
	/**
	* 微信标识       
	**/
	public void setTbeWeixinFlag(String tbeWeixinFlag){
		this.tbeWeixinFlag = tbeWeixinFlag;
		this.setSeted(F.tbeWeixinFlag);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbeEditor(){
		return this.tbeEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getTbeEditor$(){
		String strValue="";
		if(this.getTbeEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getTbeEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbeEditor(Long tbeEditor){
		this.tbeEditor = tbeEditor;
		this.setSeted(F.tbeEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$tbeEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getTbeEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getTbeEditor()!=null){
 			srvUser = SrvUser.get(this.getTbeEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getTbeEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getTbeRemark(){
		return this.tbeRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getTbeRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbeRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setTbeRemark(String tbeRemark){
		this.tbeRemark = tbeRemark;
		this.setSeted(F.tbeRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getTbeMemo(){
		return this.tbeMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getTbeMemo$(){
		String strValue="";
		 strValue=$.str(this.getTbeMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setTbeMemo(String tbeMemo){
		this.tbeMemo = tbeMemo;
		this.setSeted(F.tbeMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getTbeData(){
		return this.tbeData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getTbeData$(){
		String strValue="";
		 strValue=$.str(this.getTbeData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setTbeData(String tbeData){
		this.tbeData = tbeData;
		this.setSeted(F.tbeData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getTbeUpdateTime(){
		return this.tbeUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getTbeUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbeUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setTbeUpdateTime(Date tbeUpdateTime){
		this.tbeUpdateTime = tbeUpdateTime;
		this.setSeted(F.tbeUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getTbeAddTime(){
		return this.tbeAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getTbeAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbeAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setTbeAddTime(Date tbeAddTime){
		this.tbeAddTime = tbeAddTime;
		this.setSeted(F.tbeAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待审核 1:正常 2:无效    
	**/
	public Short getTbeStatus(){
		return this.tbeStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbeStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbeStatus()),"0"))
			strValue=$.str("待审核");		 
		 if($.equals($.str(this.getTbeStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getTbeStatus()),"2"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待审核 1:正常 2:无效    
	**/
	public void setTbeStatus(Short tbeStatus){
		this.tbeStatus = tbeStatus;
		this.setSeted(F.tbeStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbEmployee.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbEmployee.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbEmployee.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbEmployee.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbEmployee.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbEmployee.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbEmployee.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbEmployee.this);
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
		public M tbeId(Object tbeId){this.put("tbeId", tbeId);return this;};
	 	/** and tbe_id is null */
 		public M tbeIdNull(){if(this.get("tbeIdNot")==null)this.put("tbeIdNot", "");this.put("tbeId", null);return this;};
 		/** not .... */
 		public M tbeIdNot(){this.put("tbeIdNot", "not");return this;};
		/** 姓名 [非空]       **/
		public M tbeName(Object tbeName){this.put("tbeName", tbeName);return this;};
	 	/** and tbe_name is null */
 		public M tbeNameNull(){if(this.get("tbeNameNot")==null)this.put("tbeNameNot", "");this.put("tbeName", null);return this;};
 		/** not .... */
 		public M tbeNameNot(){this.put("tbeNameNot", "not");return this;};
		/** 部门 [非空] [TbDepartment]      **/
		public M tbeDepart(Object tbeDepart){this.put("tbeDepart", tbeDepart);return this;};
	 	/** and tbe_depart is null */
 		public M tbeDepartNull(){if(this.get("tbeDepartNot")==null)this.put("tbeDepartNot", "");this.put("tbeDepart", null);return this;};
 		/** not .... */
 		public M tbeDepartNot(){this.put("tbeDepartNot", "not");return this;};
		public M tbeDepart$on(TbDepartment.M value){
			this.put("TbDepartment", value);
			this.put("tbeDepart$on", value);
			return this;
		};	
		/** 微信标识        **/
		public M tbeWeixinFlag(Object tbeWeixinFlag){this.put("tbeWeixinFlag", tbeWeixinFlag);return this;};
	 	/** and tbe_weixin_flag is null */
 		public M tbeWeixinFlagNull(){if(this.get("tbeWeixinFlagNot")==null)this.put("tbeWeixinFlagNot", "");this.put("tbeWeixinFlag", null);return this;};
 		/** not .... */
 		public M tbeWeixinFlagNot(){this.put("tbeWeixinFlagNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbeEditor(Object tbeEditor){this.put("tbeEditor", tbeEditor);return this;};
	 	/** and tbe_editor is null */
 		public M tbeEditorNull(){if(this.get("tbeEditorNot")==null)this.put("tbeEditorNot", "");this.put("tbeEditor", null);return this;};
 		/** not .... */
 		public M tbeEditorNot(){this.put("tbeEditorNot", "not");return this;};
		/** 备注        **/
		public M tbeRemark(Object tbeRemark){this.put("tbeRemark", tbeRemark);return this;};
	 	/** and tbe_remark is null */
 		public M tbeRemarkNull(){if(this.get("tbeRemarkNot")==null)this.put("tbeRemarkNot", "");this.put("tbeRemark", null);return this;};
 		/** not .... */
 		public M tbeRemarkNot(){this.put("tbeRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M tbeMemo(Object tbeMemo){this.put("tbeMemo", tbeMemo);return this;};
	 	/** and tbe_memo is null */
 		public M tbeMemoNull(){if(this.get("tbeMemoNot")==null)this.put("tbeMemoNot", "");this.put("tbeMemo", null);return this;};
 		/** not .... */
 		public M tbeMemoNot(){this.put("tbeMemoNot", "not");return this;};
		/** 程序数据        **/
		public M tbeData(Object tbeData){this.put("tbeData", tbeData);return this;};
	 	/** and tbe_data is null */
 		public M tbeDataNull(){if(this.get("tbeDataNot")==null)this.put("tbeDataNot", "");this.put("tbeData", null);return this;};
 		/** not .... */
 		public M tbeDataNot(){this.put("tbeDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M tbeUpdateTime(Object tbeUpdateTime){this.put("tbeUpdateTime", tbeUpdateTime);return this;};
	 	/** and tbe_update_time is null */
 		public M tbeUpdateTimeNull(){if(this.get("tbeUpdateTimeNot")==null)this.put("tbeUpdateTimeNot", "");this.put("tbeUpdateTime", null);return this;};
 		/** not .... */
 		public M tbeUpdateTimeNot(){this.put("tbeUpdateTimeNot", "not");return this;};
 		/** and tbe_update_time >= ? */
 		public M tbeUpdateTimeStart(Object start){this.put("tbeUpdateTimeStart", start);return this;};			
 		/** and tbe_update_time <= ? */
 		public M tbeUpdateTimeEnd(Object end){this.put("tbeUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M tbeAddTime(Object tbeAddTime){this.put("tbeAddTime", tbeAddTime);return this;};
	 	/** and tbe_add_time is null */
 		public M tbeAddTimeNull(){if(this.get("tbeAddTimeNot")==null)this.put("tbeAddTimeNot", "");this.put("tbeAddTime", null);return this;};
 		/** not .... */
 		public M tbeAddTimeNot(){this.put("tbeAddTimeNot", "not");return this;};
 		/** and tbe_add_time >= ? */
 		public M tbeAddTimeStart(Object start){this.put("tbeAddTimeStart", start);return this;};			
 		/** and tbe_add_time <= ? */
 		public M tbeAddTimeEnd(Object end){this.put("tbeAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:待审核 1:正常 2:无效     **/
		public M tbeStatus(Object tbeStatus){this.put("tbeStatus", tbeStatus);return this;};
	 	/** and tbe_status is null */
 		public M tbeStatusNull(){if(this.get("tbeStatusNot")==null)this.put("tbeStatusNot", "");this.put("tbeStatus", null);return this;};
 		/** not .... */
 		public M tbeStatusNot(){this.put("tbeStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有公司员工 **/
		public @api List<TbEmployee> list(Integer size){
			return getTbEmployeeList(this,size);
		}
		/** 获取公司员工分页 **/
		public @api Page<TbEmployee> page(int page,int size){
			return getTbEmployeePage(page, size , this);
		}
		/** 根据查询条件取公司员工 **/
		public @api TbEmployee get(){
			return getTbEmployee(this);
		}
		/** 获取公司员工数 **/
		public @api Long count(){
			return getTbEmployeeCount(this);
		}
		/** 获取公司员工表达式 **/
		public @api <T> T eval(String strEval){
			return getTbEmployeeEval(strEval,this);
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
			updateTbEmployee(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbeId="tbeId";
		/** 姓名 [非空]       **/
		public final static @type(String.class) @like String tbeName="tbeName";
		/** 部门 [非空] [TbDepartment]      **/
		public final static @type(Long.class)  String tbeDepart="tbeDepart";
		/** 微信标识        **/
		public final static @type(String.class) @like String tbeWeixinFlag="tbeWeixinFlag";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbeEditor="tbeEditor";
		/** 备注        **/
		public final static @type(String.class) @like String tbeRemark="tbeRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String tbeMemo="tbeMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String tbeData="tbeData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String tbeUpdateTime="tbeUpdateTime";
	 	/** and tbe_update_time >= ? */
 		public final static @type(Date.class) String tbeUpdateTimeStart="tbeUpdateTimeStart";
 		/** and tbe_update_time <= ? */
 		public final static @type(Date.class) String tbeUpdateTimeEnd="tbeUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String tbeAddTime="tbeAddTime";
	 	/** and tbe_add_time >= ? */
 		public final static @type(Date.class) String tbeAddTimeStart="tbeAddTimeStart";
 		/** and tbe_add_time <= ? */
 		public final static @type(Date.class) String tbeAddTimeEnd="tbeAddTimeEnd";
		/** 状态 [非空]   0:待审核 1:正常 2:无效     **/
		public final static @type(Short.class)  String tbeStatus="tbeStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbeId="tbe_id";
		/** 姓名 [非空]       **/
		public final static String tbeName="tbe_name";
		/** 部门 [非空] [TbDepartment]      **/
		public final static String tbeDepart="tbe_depart";
		/** 微信标识        **/
		public final static String tbeWeixinFlag="tbe_weixin_flag";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbeEditor="tbe_editor";
		/** 备注        **/
		public final static String tbeRemark="tbe_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String tbeMemo="tbe_memo";
		/** 程序数据        **/
		public final static String tbeData="tbe_data";
		/** 修改时间 [非空]       **/
		public final static String tbeUpdateTime="tbe_update_time";
		/** 添加时间 [非空]       **/
		public final static String tbeAddTime="tbe_add_time";
		/** 状态 [非空]   0:待审核 1:正常 2:无效     **/
		public final static String tbeStatus="tbe_status";
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
				$.Set(name,TbEmployee.getTbEmployee(params));
			else
				$.Set(name,TbEmployee.getTbEmployeeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取公司员工数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbEmployee) $.GetRequest("TbEmployee$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbEmployee.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbEmployee.getTbEmployee(params);
			else
				value = TbEmployee.getTbEmployeeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbEmployee.Get($.add(TbEmployee.F.tbeId,param));
		else if(!$.empty(param.toString()))
			value = TbEmployee.get(Long.valueOf(param.toString()));
		$.SetRequest("TbEmployee$"+param.hashCode(), value);
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
	public void mergeSet(TbEmployee old){
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