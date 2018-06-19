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

@namespace("oa/hr/passcheck")
public @caption("上下班签到") @table("tb_pass_check") class TbPassCheck implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbpc_id")   @primary  @note("  ") Long tbpcId;// 主键 非空     
	private @caption("城市") @column("tbpc_host")    @relate("$tbpcHost") @RelateClass(SrvHost.class)  @note("  ") Long tbpcHost;// 非空     
 	private SrvHost $tbpcHost;//关联对象[运营城市]
	private @caption("签到描述") @column("tbpc_title")    @note("  ") String tbpcTitle;// 非空     
	private @caption("员工") @column("tbpc_employee")    @relate("$tbpcEmployee") @RelateClass(TbEmployee.class)  @note("  ") Long tbpcEmployee;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private TbEmployee $tbpcEmployee;//关联对象[公司员工]
	private @caption("签入位置") @column("tbpc_in_pos")    @note("  ") String tbpcInPos;//     
	private @caption("签入距离") @column("tbpc_in_distance")    @note("  ") Integer tbpcInDistance;//(单位：米)     
	private @caption("签入时间") @column("tbpc_in_time")    @note("  ") Date tbpcInTime;//     
	private @caption("签出位置") @column("tbpc_out_pos")    @note("  ") String tbpcOutPos;//     
	private @caption("签出距离") @column("tbpc_out_distance")    @note("  ") Integer tbpcOutDistance;//(单位：米)     
	private @caption("签出时间") @column("tbpc_out_time")    @note("  ") Date tbpcOutTime;//     
	private @caption("作业时长") @column("tbpc_time_long")    @note("  ") Integer tbpcTimeLong;//(单位：分)     
	private @caption("备注信息") @column("tbpc_remark")    @note("  ") String tbpcRemark;//     
	private @caption("状态") @column("tbpc_status")    @note(" 1:正常 0:无效  ") Short tbpcStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public TbPassCheck(){
	
	}
	
	//主键构造函数
	public TbPassCheck(Long id){
		this.tbpcId = id;
	}
	
	/**所有字段构造函数 TbPassCheck(tbpcHost,tbpcTitle,tbpcEmployee,tbpcInPos,tbpcInDistance,tbpcInTime,tbpcOutPos,tbpcOutDistance,tbpcOutTime,tbpcTimeLong,tbpcRemark,tbpcStatus)
	 TbPassCheck(
	 	$.getLong("tbpcHost")//城市 [非空]
	 	,$.getString("tbpcTitle")//签到描述 [非空]
	 	,$.getLong("tbpcEmployee")//员工 [非空]
	 	,$.getString("tbpcInPos")//签入位置
	 	,$.getInteger("tbpcInDistance")//签入距离
	 	,$.getDate("tbpcInTime")//签入时间
	 	,$.getString("tbpcOutPos")//签出位置
	 	,$.getInteger("tbpcOutDistance")//签出距离
	 	,$.getDate("tbpcOutTime")//签出时间
	 	,$.getInteger("tbpcTimeLong")//作业时长
	 	,$.getString("tbpcRemark")//备注信息
	 	,$.getShort("tbpcStatus")//状态 [非空]
	 )
	**/
	public TbPassCheck(Long tbpcHost,String tbpcTitle,Long tbpcEmployee,String tbpcInPos,Integer tbpcInDistance,Date tbpcInTime,String tbpcOutPos,Integer tbpcOutDistance,Date tbpcOutTime,Integer tbpcTimeLong,String tbpcRemark,Short tbpcStatus){
		this.tbpcHost=tbpcHost;
		this.tbpcTitle=tbpcTitle;
		this.tbpcEmployee=tbpcEmployee;
		this.tbpcInPos=tbpcInPos;
		this.tbpcInDistance=tbpcInDistance;
		this.tbpcInTime=tbpcInTime;
		this.tbpcOutPos=tbpcOutPos;
		this.tbpcOutDistance=tbpcOutDistance;
		this.tbpcOutTime=tbpcOutTime;
		this.tbpcTimeLong=tbpcTimeLong;
		this.tbpcRemark=tbpcRemark;
		this.tbpcStatus=tbpcStatus;
	}
	
	//设置非空字段
	public TbPassCheck setNotNull(Long tbpcHost,String tbpcTitle,Long tbpcEmployee,Short tbpcStatus){
		this.tbpcHost=tbpcHost;
		this.tbpcTitle=tbpcTitle;
		this.tbpcEmployee=tbpcEmployee;
		this.tbpcStatus=tbpcStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbPassCheck tbpcId(Long tbpcId){
		this.tbpcId = tbpcId;
		this.setSeted(F.tbpcId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbPassCheck tbpcHost(Long tbpcHost){
		this.tbpcHost = tbpcHost;
		this.setSeted(F.tbpcHost);
		return this;
	}
	/** 签到描述 [非空]       **/
	public TbPassCheck tbpcTitle(String tbpcTitle){
		this.tbpcTitle = tbpcTitle;
		this.setSeted(F.tbpcTitle);
		return this;
	}
	/** 员工 [非空] [TbEmployee]     ${basePath}${proname}/permissions/user_tree.do **/
	public TbPassCheck tbpcEmployee(Long tbpcEmployee){
		this.tbpcEmployee = tbpcEmployee;
		this.setSeted(F.tbpcEmployee);
		return this;
	}
	/** 签入位置        **/
	public TbPassCheck tbpcInPos(String tbpcInPos){
		this.tbpcInPos = tbpcInPos;
		this.setSeted(F.tbpcInPos);
		return this;
	}
	/** 签入距离        **/
	public TbPassCheck tbpcInDistance(Integer tbpcInDistance){
		this.tbpcInDistance = tbpcInDistance;
		this.setSeted(F.tbpcInDistance);
		return this;
	}
	/** 签入时间        **/
	public TbPassCheck tbpcInTime(Date tbpcInTime){
		this.tbpcInTime = tbpcInTime;
		this.setSeted(F.tbpcInTime);
		return this;
	}
	/** 签出位置        **/
	public TbPassCheck tbpcOutPos(String tbpcOutPos){
		this.tbpcOutPos = tbpcOutPos;
		this.setSeted(F.tbpcOutPos);
		return this;
	}
	/** 签出距离        **/
	public TbPassCheck tbpcOutDistance(Integer tbpcOutDistance){
		this.tbpcOutDistance = tbpcOutDistance;
		this.setSeted(F.tbpcOutDistance);
		return this;
	}
	/** 签出时间        **/
	public TbPassCheck tbpcOutTime(Date tbpcOutTime){
		this.tbpcOutTime = tbpcOutTime;
		this.setSeted(F.tbpcOutTime);
		return this;
	}
	/** 作业时长        **/
	public TbPassCheck tbpcTimeLong(Integer tbpcTimeLong){
		this.tbpcTimeLong = tbpcTimeLong;
		this.setSeted(F.tbpcTimeLong);
		return this;
	}
	/** 备注信息        **/
	public TbPassCheck tbpcRemark(String tbpcRemark){
		this.tbpcRemark = tbpcRemark;
		this.setSeted(F.tbpcRemark);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public TbPassCheck tbpcStatus(Short tbpcStatus){
		this.tbpcStatus = tbpcStatus;
		this.setSeted(F.tbpcStatus);
		return this;
	}
	
	
	//克隆对象
	public TbPassCheck clone(){
		TbPassCheck clone = new TbPassCheck();
		clone.tbpcHost=this.tbpcHost;
		clone.tbpcTitle=this.tbpcTitle;
		clone.tbpcEmployee=this.tbpcEmployee;
		clone.tbpcStatus=this.tbpcStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取上下班签到
	 * @param id
	 * @return
	 */
	public static @api TbPassCheck get(Long id){		
		return getTbPassCheckById(id);
	}
	/**
	 * 获取所有上下班签到
	 * @return
	 */
	public static @api List<TbPassCheck> list(Map params,Integer size){
		return getTbPassCheckList(params,size);
	}
	/**
	 * 获取上下班签到分页
	 * @return
	 */
	public static @api Page<TbPassCheck> page(int page,int size,Map params){
		return getTbPassCheckPage(page, size , params);
	}
	/**
	 * 根据查询条件取上下班签到
	 * @param params
	 * @return
	 */
	public static @api TbPassCheck Get(Map params){
		return getTbPassCheck(params);
	}
	/**
	 * 获取上下班签到数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbPassCheckCount(params);
	}
	/**
	 * 获取上下班签到数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbPassCheckEval(eval,params);
	}
	
	/**
	 * 根据ID取上下班签到
	 * @param id
	 * @return
	 */
	public static @api TbPassCheck getTbPassCheckById(Long id){		
		TbPassCheck tbPassCheck = (TbPassCheck) $.GetRequest("TbPassCheck$"+id);
		if(tbPassCheck!=null)
			return tbPassCheck;
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");		
		return tbPassCheckService.getTbPassCheckById(id);
	}
	
	
	/**
	 * 根据ID取上下班签到的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbPassCheck.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbPassCheck tbPassCheck = get(id);
		if(tbPassCheck!=null){
			String strValue = tbPassCheck.getTbpcTitle$();
			if(!"TbpcTitle".equals("TbpcTitle"))
				strValue+="("+tbPassCheck.getTbpcTitle$()+")";
			MemCache.setValue(TbPassCheck.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbpcTitle$();
		if(!"TbpcTitle".equals("TbpcTitle"))
			keyValue+="("+this.getTbpcTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有上下班签到
	 * @return
	 */
	public static @api List<TbPassCheck> getTbPassCheckList(Map params,Integer size){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		return tbPassCheckService.getTbPassCheckList(params, size);
	}
	
	/**
	 * 获取上下班签到分页
	 * @return
	 */
	public static @api Page<TbPassCheck> getTbPassCheckPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		return tbPassCheckService.getTbPassCheckPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取上下班签到
	 * @param params
	 * @return
	 */
	public static @api TbPassCheck getTbPassCheck(Map params){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		return tbPassCheckService.getTbPassCheck(params);
	}
	
	/**
	 * 获取上下班签到数
	 * @return
	 */
	public static @api Long getTbPassCheckCount(Map params){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		return tbPassCheckService.getTbPassCheckCount(params);
	}
		
		
	/**
	 * 获取上下班签到自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbPassCheckEval(String eval,Map params){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		return tbPassCheckService.getTbPassCheckEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbPassCheck(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		tbPassCheckService.updateTbPassCheckByConfirm(set, where);
	}
	
	
	/**
	 * 保存上下班签到对象
	 * @param params
	 * @return
	 */
	public TbPassCheck save(){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		if(this.getTbpcId()!=null)
			tbPassCheckService.updateTbPassCheck(this);
		else
			return tbPassCheckService.saveTbPassCheck(this);
		return this;
	}
	
	
	/**
	 * 更新上下班签到对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		tbPassCheckService.updateTbPassCheck$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		if($.equals($.config("logic_delete"),"true"))
			tbPassCheckService.removeTbPassCheckById(this.getTbpcId());
		else
			tbPassCheckService.deleteTbPassCheckById(this.getTbpcId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbPassCheckService tbPassCheckService = $.GetSpringBean("tbPassCheckService");
		return tbPassCheckService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbpcId(){
		return this.tbpcId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbpcId$(){
		String strValue="";
		 strValue=$.str(this.getTbpcId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbpcId(Long tbpcId){
		this.tbpcId = tbpcId;
		this.setSeted(F.tbpcId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbpcHost(){
		return this.tbpcHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbpcHost$(){
		String strValue="";
		if(this.getTbpcHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbpcHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbpcHost(Long tbpcHost){
		this.tbpcHost = tbpcHost;
		this.setSeted(F.tbpcHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbpcHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbpcHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbpcHost()!=null){
 			srvHost = SrvHost.get(this.getTbpcHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbpcHost(), srvHost);
	 	return srvHost;
	}
	/*******************************签到描述**********************************/	
	/**
	* 签到描述 [非空]      
	**/
	public String getTbpcTitle(){
		return this.tbpcTitle;
	}
	/**
	* 获取签到描述格式化(toString)
	**/
	public String getTbpcTitle$(){
		String strValue="";
		 strValue=$.str(this.getTbpcTitle());
	 	 return strValue;
	}
	/**
	* 签到描述 [非空]      
	**/
	public void setTbpcTitle(String tbpcTitle){
		this.tbpcTitle = tbpcTitle;
		this.setSeted(F.tbpcTitle);
	}
	/*******************************员工**********************************/	
	/**
	* 员工 [非空] [TbEmployee]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getTbpcEmployee(){
		return this.tbpcEmployee;
	}
	/**
	* 获取员工格式化(toString)
	**/
	public String getTbpcEmployee$(){
		String strValue="";
		if(this.getTbpcEmployee()!=null){
				strValue+=$.str(TbEmployee.getKeyValue(this.getTbpcEmployee()));
		}			
	 	 return strValue;
	}
	/**
	* 员工 [非空] [TbEmployee]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setTbpcEmployee(Long tbpcEmployee){
		this.tbpcEmployee = tbpcEmployee;
		this.setSeted(F.tbpcEmployee);
	}
	/**
	* 获取关联对象[公司员工]
	**/	 			
 	public TbEmployee get$tbpcEmployee(){
 		com.ccclubs.model.TbEmployee tbEmployee = (com.ccclubs.model.TbEmployee) $.GetRequest("TbEmployee$"+this.getTbpcEmployee());
 		if(tbEmployee!=null)
			return tbEmployee;
		if(this.getTbpcEmployee()!=null){
 			tbEmployee = TbEmployee.get(this.getTbpcEmployee());
 		}
 		$.SetRequest("TbEmployee$"+this.getTbpcEmployee(), tbEmployee);
	 	return tbEmployee;
	}
	/*******************************签入位置**********************************/	
	/**
	* 签入位置       
	**/
	public String getTbpcInPos(){
		return this.tbpcInPos;
	}
	/**
	* 获取签入位置格式化(toString)
	**/
	public String getTbpcInPos$(){
		String strValue="";
		 strValue=$.str(this.getTbpcInPos());
	 	 return strValue;
	}
	/**
	* 签入位置       
	**/
	public void setTbpcInPos(String tbpcInPos){
		this.tbpcInPos = tbpcInPos;
		this.setSeted(F.tbpcInPos);
	}
	/*******************************签入距离**********************************/	
	/**
	* 签入距离       
	**/
	public Integer getTbpcInDistance(){
		return this.tbpcInDistance;
	}
	/**
	* 获取签入距离格式化(toString)
	**/
	public String getTbpcInDistance$(){
		String strValue="";
		 strValue=$.str(this.getTbpcInDistance());
	 	 return strValue;
	}
	/**
	* 签入距离       
	**/
	public void setTbpcInDistance(Integer tbpcInDistance){
		this.tbpcInDistance = tbpcInDistance;
		this.setSeted(F.tbpcInDistance);
	}
	/*******************************签入时间**********************************/	
	/**
	* 签入时间       
	**/
	public Date getTbpcInTime(){
		return this.tbpcInTime;
	}
	/**
	* 获取签入时间格式化(toString)
	**/
	public String getTbpcInTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbpcInTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 签入时间       
	**/
	public void setTbpcInTime(Date tbpcInTime){
		this.tbpcInTime = tbpcInTime;
		this.setSeted(F.tbpcInTime);
	}
	/*******************************签出位置**********************************/	
	/**
	* 签出位置       
	**/
	public String getTbpcOutPos(){
		return this.tbpcOutPos;
	}
	/**
	* 获取签出位置格式化(toString)
	**/
	public String getTbpcOutPos$(){
		String strValue="";
		 strValue=$.str(this.getTbpcOutPos());
	 	 return strValue;
	}
	/**
	* 签出位置       
	**/
	public void setTbpcOutPos(String tbpcOutPos){
		this.tbpcOutPos = tbpcOutPos;
		this.setSeted(F.tbpcOutPos);
	}
	/*******************************签出距离**********************************/	
	/**
	* 签出距离       
	**/
	public Integer getTbpcOutDistance(){
		return this.tbpcOutDistance;
	}
	/**
	* 获取签出距离格式化(toString)
	**/
	public String getTbpcOutDistance$(){
		String strValue="";
		 strValue=$.str(this.getTbpcOutDistance());
	 	 return strValue;
	}
	/**
	* 签出距离       
	**/
	public void setTbpcOutDistance(Integer tbpcOutDistance){
		this.tbpcOutDistance = tbpcOutDistance;
		this.setSeted(F.tbpcOutDistance);
	}
	/*******************************签出时间**********************************/	
	/**
	* 签出时间       
	**/
	public Date getTbpcOutTime(){
		return this.tbpcOutTime;
	}
	/**
	* 获取签出时间格式化(toString)
	**/
	public String getTbpcOutTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getTbpcOutTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 签出时间       
	**/
	public void setTbpcOutTime(Date tbpcOutTime){
		this.tbpcOutTime = tbpcOutTime;
		this.setSeted(F.tbpcOutTime);
	}
	/*******************************作业时长**********************************/	
	/**
	* 作业时长       
	**/
	public Integer getTbpcTimeLong(){
		return this.tbpcTimeLong;
	}
	/**
	* 获取作业时长格式化(toString)
	**/
	public String getTbpcTimeLong$(){
		String strValue="";
		 strValue=$.str(this.getTbpcTimeLong());
	 	 return strValue;
	}
	/**
	* 作业时长       
	**/
	public void setTbpcTimeLong(Integer tbpcTimeLong){
		this.tbpcTimeLong = tbpcTimeLong;
		this.setSeted(F.tbpcTimeLong);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getTbpcRemark(){
		return this.tbpcRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getTbpcRemark$(){
		String strValue="";
		 strValue=$.str(this.getTbpcRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setTbpcRemark(String tbpcRemark){
		this.tbpcRemark = tbpcRemark;
		this.setSeted(F.tbpcRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getTbpcStatus(){
		return this.tbpcStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbpcStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbpcStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getTbpcStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setTbpcStatus(Short tbpcStatus){
		this.tbpcStatus = tbpcStatus;
		this.setSeted(F.tbpcStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbPassCheck.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbPassCheck.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbPassCheck.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbPassCheck.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbPassCheck.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbPassCheck.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbPassCheck.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbPassCheck.this);
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
		public M tbpcId(Object tbpcId){this.put("tbpcId", tbpcId);return this;};
	 	/** and tbpc_id is null */
 		public M tbpcIdNull(){if(this.get("tbpcIdNot")==null)this.put("tbpcIdNot", "");this.put("tbpcId", null);return this;};
 		/** not .... */
 		public M tbpcIdNot(){this.put("tbpcIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbpcHost(Object tbpcHost){this.put("tbpcHost", tbpcHost);return this;};
	 	/** and tbpc_host is null */
 		public M tbpcHostNull(){if(this.get("tbpcHostNot")==null)this.put("tbpcHostNot", "");this.put("tbpcHost", null);return this;};
 		/** not .... */
 		public M tbpcHostNot(){this.put("tbpcHostNot", "not");return this;};
		/** 签到描述 [非空]       **/
		public M tbpcTitle(Object tbpcTitle){this.put("tbpcTitle", tbpcTitle);return this;};
	 	/** and tbpc_title is null */
 		public M tbpcTitleNull(){if(this.get("tbpcTitleNot")==null)this.put("tbpcTitleNot", "");this.put("tbpcTitle", null);return this;};
 		/** not .... */
 		public M tbpcTitleNot(){this.put("tbpcTitleNot", "not");return this;};
		/** 员工 [非空] [TbEmployee]     ${basePath}${proname}/permissions/user_tree.do **/
		public M tbpcEmployee(Object tbpcEmployee){this.put("tbpcEmployee", tbpcEmployee);return this;};
	 	/** and tbpc_employee is null */
 		public M tbpcEmployeeNull(){if(this.get("tbpcEmployeeNot")==null)this.put("tbpcEmployeeNot", "");this.put("tbpcEmployee", null);return this;};
 		/** not .... */
 		public M tbpcEmployeeNot(){this.put("tbpcEmployeeNot", "not");return this;};
		public M tbpcEmployee$on(TbEmployee.M value){
			this.put("TbEmployee", value);
			this.put("tbpcEmployee$on", value);
			return this;
		};	
		/** 签入位置        **/
		public M tbpcInPos(Object tbpcInPos){this.put("tbpcInPos", tbpcInPos);return this;};
	 	/** and tbpc_in_pos is null */
 		public M tbpcInPosNull(){if(this.get("tbpcInPosNot")==null)this.put("tbpcInPosNot", "");this.put("tbpcInPos", null);return this;};
 		/** not .... */
 		public M tbpcInPosNot(){this.put("tbpcInPosNot", "not");return this;};
		/** 签入距离        **/
		public M tbpcInDistance(Object tbpcInDistance){this.put("tbpcInDistance", tbpcInDistance);return this;};
	 	/** and tbpc_in_distance is null */
 		public M tbpcInDistanceNull(){if(this.get("tbpcInDistanceNot")==null)this.put("tbpcInDistanceNot", "");this.put("tbpcInDistance", null);return this;};
 		/** not .... */
 		public M tbpcInDistanceNot(){this.put("tbpcInDistanceNot", "not");return this;};
		/** and tbpc_in_distance >= ? */
		public M tbpcInDistanceMin(Object min){this.put("tbpcInDistanceMin", min);return this;};
		/** and tbpc_in_distance <= ? */
		public M tbpcInDistanceMax(Object max){this.put("tbpcInDistanceMax", max);return this;};
		/** 签入时间        **/
		public M tbpcInTime(Object tbpcInTime){this.put("tbpcInTime", tbpcInTime);return this;};
	 	/** and tbpc_in_time is null */
 		public M tbpcInTimeNull(){if(this.get("tbpcInTimeNot")==null)this.put("tbpcInTimeNot", "");this.put("tbpcInTime", null);return this;};
 		/** not .... */
 		public M tbpcInTimeNot(){this.put("tbpcInTimeNot", "not");return this;};
 		/** and tbpc_in_time >= ? */
 		public M tbpcInTimeStart(Object start){this.put("tbpcInTimeStart", start);return this;};			
 		/** and tbpc_in_time <= ? */
 		public M tbpcInTimeEnd(Object end){this.put("tbpcInTimeEnd", end);return this;};
		/** 签出位置        **/
		public M tbpcOutPos(Object tbpcOutPos){this.put("tbpcOutPos", tbpcOutPos);return this;};
	 	/** and tbpc_out_pos is null */
 		public M tbpcOutPosNull(){if(this.get("tbpcOutPosNot")==null)this.put("tbpcOutPosNot", "");this.put("tbpcOutPos", null);return this;};
 		/** not .... */
 		public M tbpcOutPosNot(){this.put("tbpcOutPosNot", "not");return this;};
		/** 签出距离        **/
		public M tbpcOutDistance(Object tbpcOutDistance){this.put("tbpcOutDistance", tbpcOutDistance);return this;};
	 	/** and tbpc_out_distance is null */
 		public M tbpcOutDistanceNull(){if(this.get("tbpcOutDistanceNot")==null)this.put("tbpcOutDistanceNot", "");this.put("tbpcOutDistance", null);return this;};
 		/** not .... */
 		public M tbpcOutDistanceNot(){this.put("tbpcOutDistanceNot", "not");return this;};
		/** and tbpc_out_distance >= ? */
		public M tbpcOutDistanceMin(Object min){this.put("tbpcOutDistanceMin", min);return this;};
		/** and tbpc_out_distance <= ? */
		public M tbpcOutDistanceMax(Object max){this.put("tbpcOutDistanceMax", max);return this;};
		/** 签出时间        **/
		public M tbpcOutTime(Object tbpcOutTime){this.put("tbpcOutTime", tbpcOutTime);return this;};
	 	/** and tbpc_out_time is null */
 		public M tbpcOutTimeNull(){if(this.get("tbpcOutTimeNot")==null)this.put("tbpcOutTimeNot", "");this.put("tbpcOutTime", null);return this;};
 		/** not .... */
 		public M tbpcOutTimeNot(){this.put("tbpcOutTimeNot", "not");return this;};
 		/** and tbpc_out_time >= ? */
 		public M tbpcOutTimeStart(Object start){this.put("tbpcOutTimeStart", start);return this;};			
 		/** and tbpc_out_time <= ? */
 		public M tbpcOutTimeEnd(Object end){this.put("tbpcOutTimeEnd", end);return this;};
		/** 作业时长        **/
		public M tbpcTimeLong(Object tbpcTimeLong){this.put("tbpcTimeLong", tbpcTimeLong);return this;};
	 	/** and tbpc_time_long is null */
 		public M tbpcTimeLongNull(){if(this.get("tbpcTimeLongNot")==null)this.put("tbpcTimeLongNot", "");this.put("tbpcTimeLong", null);return this;};
 		/** not .... */
 		public M tbpcTimeLongNot(){this.put("tbpcTimeLongNot", "not");return this;};
		/** and tbpc_time_long >= ? */
		public M tbpcTimeLongMin(Object min){this.put("tbpcTimeLongMin", min);return this;};
		/** and tbpc_time_long <= ? */
		public M tbpcTimeLongMax(Object max){this.put("tbpcTimeLongMax", max);return this;};
		/** 备注信息        **/
		public M tbpcRemark(Object tbpcRemark){this.put("tbpcRemark", tbpcRemark);return this;};
	 	/** and tbpc_remark is null */
 		public M tbpcRemarkNull(){if(this.get("tbpcRemarkNot")==null)this.put("tbpcRemarkNot", "");this.put("tbpcRemark", null);return this;};
 		/** not .... */
 		public M tbpcRemarkNot(){this.put("tbpcRemarkNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M tbpcStatus(Object tbpcStatus){this.put("tbpcStatus", tbpcStatus);return this;};
	 	/** and tbpc_status is null */
 		public M tbpcStatusNull(){if(this.get("tbpcStatusNot")==null)this.put("tbpcStatusNot", "");this.put("tbpcStatus", null);return this;};
 		/** not .... */
 		public M tbpcStatusNot(){this.put("tbpcStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有上下班签到 **/
		public @api List<TbPassCheck> list(Integer size){
			return getTbPassCheckList(this,size);
		}
		/** 获取上下班签到分页 **/
		public @api Page<TbPassCheck> page(int page,int size){
			return getTbPassCheckPage(page, size , this);
		}
		/** 根据查询条件取上下班签到 **/
		public @api TbPassCheck get(){
			return getTbPassCheck(this);
		}
		/** 获取上下班签到数 **/
		public @api Long count(){
			return getTbPassCheckCount(this);
		}
		/** 获取上下班签到表达式 **/
		public @api <T> T eval(String strEval){
			return getTbPassCheckEval(strEval,this);
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
			updateTbPassCheck(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbpcId="tbpcId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbpcHost="tbpcHost";
		/** 签到描述 [非空]       **/
		public final static @type(String.class) @like String tbpcTitle="tbpcTitle";
		/** 员工 [非空] [TbEmployee]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String tbpcEmployee="tbpcEmployee";
		/** 签入位置        **/
		public final static @type(String.class) @like String tbpcInPos="tbpcInPos";
		/** 签入距离        **/
		public final static @type(Integer.class)  String tbpcInDistance="tbpcInDistance";
		/** and tbpc_in_distance >= ? */
		public final static @type(Integer.class) String tbpcInDistanceMin="tbpcInDistanceMin";
		/** and tbpc_in_distance <= ? */
		public final static @type(Integer.class) String tbpcInDistanceMax="tbpcInDistanceMax";
		/** 签入时间        **/
		public final static @type(Date.class)  String tbpcInTime="tbpcInTime";
	 	/** and tbpc_in_time >= ? */
 		public final static @type(Date.class) String tbpcInTimeStart="tbpcInTimeStart";
 		/** and tbpc_in_time <= ? */
 		public final static @type(Date.class) String tbpcInTimeEnd="tbpcInTimeEnd";
		/** 签出位置        **/
		public final static @type(String.class) @like String tbpcOutPos="tbpcOutPos";
		/** 签出距离        **/
		public final static @type(Integer.class)  String tbpcOutDistance="tbpcOutDistance";
		/** and tbpc_out_distance >= ? */
		public final static @type(Integer.class) String tbpcOutDistanceMin="tbpcOutDistanceMin";
		/** and tbpc_out_distance <= ? */
		public final static @type(Integer.class) String tbpcOutDistanceMax="tbpcOutDistanceMax";
		/** 签出时间        **/
		public final static @type(Date.class)  String tbpcOutTime="tbpcOutTime";
	 	/** and tbpc_out_time >= ? */
 		public final static @type(Date.class) String tbpcOutTimeStart="tbpcOutTimeStart";
 		/** and tbpc_out_time <= ? */
 		public final static @type(Date.class) String tbpcOutTimeEnd="tbpcOutTimeEnd";
		/** 作业时长        **/
		public final static @type(Integer.class)  String tbpcTimeLong="tbpcTimeLong";
		/** and tbpc_time_long >= ? */
		public final static @type(Integer.class) String tbpcTimeLongMin="tbpcTimeLongMin";
		/** and tbpc_time_long <= ? */
		public final static @type(Integer.class) String tbpcTimeLongMax="tbpcTimeLongMax";
		/** 备注信息        **/
		public final static @type(String.class) @like String tbpcRemark="tbpcRemark";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String tbpcStatus="tbpcStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbpcId="tbpc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbpcHost="tbpc_host";
		/** 签到描述 [非空]       **/
		public final static String tbpcTitle="tbpc_title";
		/** 员工 [非空] [TbEmployee]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String tbpcEmployee="tbpc_employee";
		/** 签入位置        **/
		public final static String tbpcInPos="tbpc_in_pos";
		/** 签入距离        **/
		public final static String tbpcInDistance="tbpc_in_distance";
		/** 签入时间        **/
		public final static String tbpcInTime="tbpc_in_time";
		/** 签出位置        **/
		public final static String tbpcOutPos="tbpc_out_pos";
		/** 签出距离        **/
		public final static String tbpcOutDistance="tbpc_out_distance";
		/** 签出时间        **/
		public final static String tbpcOutTime="tbpc_out_time";
		/** 作业时长        **/
		public final static String tbpcTimeLong="tbpc_time_long";
		/** 备注信息        **/
		public final static String tbpcRemark="tbpc_remark";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String tbpcStatus="tbpc_status";
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
				$.Set(name,TbPassCheck.getTbPassCheck(params));
			else
				$.Set(name,TbPassCheck.getTbPassCheckList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取上下班签到数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbPassCheck) $.GetRequest("TbPassCheck$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbPassCheck.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbPassCheck.getTbPassCheck(params);
			else
				value = TbPassCheck.getTbPassCheckList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbPassCheck.Get($.add(TbPassCheck.F.tbpcId,param));
		else if(!$.empty(param.toString()))
			value = TbPassCheck.get(Long.valueOf(param.toString()));
		$.SetRequest("TbPassCheck$"+param.hashCode(), value);
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
	public void mergeSet(TbPassCheck old){
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