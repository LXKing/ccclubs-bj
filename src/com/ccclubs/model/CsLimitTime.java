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

@namespace("configurator/limittime")
public @caption("交通限行") @table("cs_limit_time") class CsLimitTime implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cslt_id")   @primary  @note("  ") Long csltId;// 主键 非空     
	private @caption("城市") @column("cslt_host")    @relate("$csltHost") @RelateClass(SrvHost.class)  @note("  ") Long csltHost;// 非空     
 	private SrvHost $csltHost;//关联对象[运营城市]
	private @caption("星期几？") @column("cslt_week_day")    @note(" 1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  ") Short csltWeekDay;// 非空 1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六     
	private @caption("限行尾号") @column("cslt_num")    @note("  限行的车牌尾号，123表未以1或2或3的数字尾号") String csltNum;// 非空  限行的车牌尾号，123表未以1或2或3的数字尾号   
	private @caption("开始时间") @column("cslt_start")    @note("  ") String csltStart;// 非空     
	private @caption("结束时间") @column("cslt_end")    @note("  ") String csltEnd;// 非空     
	private @caption("更新时间") @column("cslt_update_time")  @hidden   @note("  ") Date csltUpdateTime;//     
	
	//默认构造函数
	public CsLimitTime(){
	
	}
	
	//主键构造函数
	public CsLimitTime(Long id){
		this.csltId = id;
	}
	
	/**所有字段构造函数 CsLimitTime(csltHost,csltWeekDay,csltNum,csltStart,csltEnd,csltUpdateTime)
	 CsLimitTime(
	 	$.getLong("csltHost")//城市 [非空]
	 	,$.getShort("csltWeekDay")//星期几？ [非空]
	 	,$.getString("csltNum")//限行尾号 [非空]
	 	,$.getString("csltStart")//开始时间 [非空]
	 	,$.getString("csltEnd")//结束时间 [非空]
	 	,$.getDate("csltUpdateTime")//更新时间
	 )
	**/
	public CsLimitTime(Long csltHost,Short csltWeekDay,String csltNum,String csltStart,String csltEnd,Date csltUpdateTime){
		this.csltHost=csltHost;
		this.csltWeekDay=csltWeekDay;
		this.csltNum=csltNum;
		this.csltStart=csltStart;
		this.csltEnd=csltEnd;
		this.csltUpdateTime=csltUpdateTime;
	}
	
	//设置非空字段
	public CsLimitTime setNotNull(Long csltHost,Short csltWeekDay,String csltNum,String csltStart,String csltEnd){
		this.csltHost=csltHost;
		this.csltWeekDay=csltWeekDay;
		this.csltNum=csltNum;
		this.csltStart=csltStart;
		this.csltEnd=csltEnd;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLimitTime csltId(Long csltId){
		this.csltId = csltId;
		this.setSeted(F.csltId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsLimitTime csltHost(Long csltHost){
		this.csltHost = csltHost;
		this.setSeted(F.csltHost);
		return this;
	}
	/** 星期几？ [非空]   1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六     **/
	public CsLimitTime csltWeekDay(Short csltWeekDay){
		this.csltWeekDay = csltWeekDay;
		this.setSeted(F.csltWeekDay);
		return this;
	}
	/** 限行尾号 [非空]    限行的车牌尾号，123表未以1或2或3的数字尾号   **/
	public CsLimitTime csltNum(String csltNum){
		this.csltNum = csltNum;
		this.setSeted(F.csltNum);
		return this;
	}
	/** 开始时间 [非空]       **/
	public CsLimitTime csltStart(String csltStart){
		this.csltStart = csltStart;
		this.setSeted(F.csltStart);
		return this;
	}
	/** 结束时间 [非空]       **/
	public CsLimitTime csltEnd(String csltEnd){
		this.csltEnd = csltEnd;
		this.setSeted(F.csltEnd);
		return this;
	}
	/** 更新时间        **/
	public CsLimitTime csltUpdateTime(Date csltUpdateTime){
		this.csltUpdateTime = csltUpdateTime;
		this.setSeted(F.csltUpdateTime);
		return this;
	}
	
	
	//克隆对象
	public CsLimitTime clone(){
		CsLimitTime clone = new CsLimitTime();
		clone.csltHost=this.csltHost;
		clone.csltWeekDay=this.csltWeekDay;
		clone.csltNum=this.csltNum;
		clone.csltStart=this.csltStart;
		clone.csltEnd=this.csltEnd;
		return clone;
	}
	
	
	/**
	 * 根据ID取交通限行
	 * @param id
	 * @return
	 */
	public static @api CsLimitTime get(Long id){		
		return getCsLimitTimeById(id);
	}
	/**
	 * 获取所有交通限行
	 * @return
	 */
	public static @api List<CsLimitTime> list(Map params,Integer size){
		return getCsLimitTimeList(params,size);
	}
	/**
	 * 获取交通限行分页
	 * @return
	 */
	public static @api Page<CsLimitTime> page(int page,int size,Map params){
		return getCsLimitTimePage(page, size , params);
	}
	/**
	 * 根据查询条件取交通限行
	 * @param params
	 * @return
	 */
	public static @api CsLimitTime Get(Map params){
		return getCsLimitTime(params);
	}
	/**
	 * 获取交通限行数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLimitTimeCount(params);
	}
	/**
	 * 获取交通限行数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLimitTimeEval(eval,params);
	}
	
	/**
	 * 根据ID取交通限行
	 * @param id
	 * @return
	 */
	public static @api CsLimitTime getCsLimitTimeById(Long id){		
		CsLimitTime csLimitTime = (CsLimitTime) $.GetRequest("CsLimitTime$"+id);
		if(csLimitTime!=null)
			return csLimitTime;
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");		
		return csLimitTimeService.getCsLimitTimeById(id);
	}
	
	
	/**
	 * 根据ID取交通限行的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLimitTime.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLimitTime csLimitTime = get(id);
		if(csLimitTime!=null){
			String strValue = csLimitTime.getCsltId$();
			if(!"CsltId".equals("CsltId"))
				strValue+="("+csLimitTime.getCsltId$()+")";
			MemCache.setValue(CsLimitTime.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsltId$();
		if(!"CsltId".equals("CsltId"))
			keyValue+="("+this.getCsltId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有交通限行
	 * @return
	 */
	public static @api List<CsLimitTime> getCsLimitTimeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		return csLimitTimeService.getCsLimitTimeList(params, size);
	}
	
	/**
	 * 获取交通限行分页
	 * @return
	 */
	public static @api Page<CsLimitTime> getCsLimitTimePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		return csLimitTimeService.getCsLimitTimePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取交通限行
	 * @param params
	 * @return
	 */
	public static @api CsLimitTime getCsLimitTime(Map params){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		return csLimitTimeService.getCsLimitTime(params);
	}
	
	/**
	 * 获取交通限行数
	 * @return
	 */
	public static @api Long getCsLimitTimeCount(Map params){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		return csLimitTimeService.getCsLimitTimeCount(params);
	}
		
		
	/**
	 * 获取交通限行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLimitTimeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		return csLimitTimeService.getCsLimitTimeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLimitTime(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		csLimitTimeService.updateCsLimitTimeByConfirm(set, where);
	}
	
	
	/**
	 * 保存交通限行对象
	 * @param params
	 * @return
	 */
	public CsLimitTime save(){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		if(this.getCsltId()!=null)
			csLimitTimeService.updateCsLimitTime(this);
		else
			return csLimitTimeService.saveCsLimitTime(this);
		return this;
	}
	
	
	/**
	 * 更新交通限行对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		csLimitTimeService.updateCsLimitTime$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
			csLimitTimeService.deleteCsLimitTimeById(this.getCsltId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLimitTimeService csLimitTimeService = $.GetSpringBean("csLimitTimeService");
		return csLimitTimeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsltId(){
		return this.csltId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsltId$(){
		String strValue="";
		 strValue=$.str(this.getCsltId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsltId(Long csltId){
		this.csltId = csltId;
		this.setSeted(F.csltId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsltHost(){
		return this.csltHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsltHost$(){
		String strValue="";
		if(this.getCsltHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsltHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsltHost(Long csltHost){
		this.csltHost = csltHost;
		this.setSeted(F.csltHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csltHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsltHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsltHost()!=null){
 			srvHost = SrvHost.get(this.getCsltHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsltHost(), srvHost);
	 	return srvHost;
	}
	/*******************************星期几？**********************************/	
	/**
	* 星期几？ [非空]   1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六    
	**/
	public Short getCsltWeekDay(){
		return this.csltWeekDay;
	}
	/**
	* 获取星期几？格式化(toString)
	**/
	public String getCsltWeekDay$(){
		String strValue="";
		 if($.equals($.str(this.getCsltWeekDay()),"1"))
			strValue=$.str("星期天");		 
		 if($.equals($.str(this.getCsltWeekDay()),"2"))
			strValue=$.str("星期一");		 
		 if($.equals($.str(this.getCsltWeekDay()),"3"))
			strValue=$.str("星期二");		 
		 if($.equals($.str(this.getCsltWeekDay()),"4"))
			strValue=$.str("星期三");		 
		 if($.equals($.str(this.getCsltWeekDay()),"5"))
			strValue=$.str("星期四");		 
		 if($.equals($.str(this.getCsltWeekDay()),"6"))
			strValue=$.str("星期五");		 
		 if($.equals($.str(this.getCsltWeekDay()),"7"))
			strValue=$.str("星期六");		 
	 	 return strValue;
	}
	/**
	* 星期几？ [非空]   1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六    
	**/
	public void setCsltWeekDay(Short csltWeekDay){
		this.csltWeekDay = csltWeekDay;
		this.setSeted(F.csltWeekDay);
	}
	/*******************************限行尾号**********************************/	
	/**
	* 限行尾号 [非空]    限行的车牌尾号，123表未以1或2或3的数字尾号  
	**/
	public String getCsltNum(){
		return this.csltNum;
	}
	/**
	* 获取限行尾号格式化(toString)
	**/
	public String getCsltNum$(){
		String strValue="";
		 strValue=$.str(this.getCsltNum());
	 	 return strValue;
	}
	/**
	* 限行尾号 [非空]    限行的车牌尾号，123表未以1或2或3的数字尾号  
	**/
	public void setCsltNum(String csltNum){
		this.csltNum = csltNum;
		this.setSeted(F.csltNum);
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间 [非空]      
	**/
	public String getCsltStart(){
		return this.csltStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsltStart$(){
		String strValue="";
		 strValue=$.str(this.getCsltStart());
	 	 return strValue;
	}
	/**
	* 开始时间 [非空]      
	**/
	public void setCsltStart(String csltStart){
		this.csltStart = csltStart;
		this.setSeted(F.csltStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public String getCsltEnd(){
		return this.csltEnd;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsltEnd$(){
		String strValue="";
		 strValue=$.str(this.getCsltEnd());
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]      
	**/
	public void setCsltEnd(String csltEnd){
		this.csltEnd = csltEnd;
		this.setSeted(F.csltEnd);
	}
	/*******************************更新时间**********************************/	
	/**
	* 更新时间       
	**/
	public Date getCsltUpdateTime(){
		return this.csltUpdateTime;
	}
	/**
	* 获取更新时间格式化(toString)
	**/
	public String getCsltUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsltUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更新时间       
	**/
	public void setCsltUpdateTime(Date csltUpdateTime){
		this.csltUpdateTime = csltUpdateTime;
		this.setSeted(F.csltUpdateTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLimitTime.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLimitTime.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLimitTime.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLimitTime.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLimitTime.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLimitTime.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLimitTime.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLimitTime.this);
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
		public M csltId(Object csltId){this.put("csltId", csltId);return this;};
	 	/** and cslt_id is null */
 		public M csltIdNull(){if(this.get("csltIdNot")==null)this.put("csltIdNot", "");this.put("csltId", null);return this;};
 		/** not .... */
 		public M csltIdNot(){this.put("csltIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csltHost(Object csltHost){this.put("csltHost", csltHost);return this;};
	 	/** and cslt_host is null */
 		public M csltHostNull(){if(this.get("csltHostNot")==null)this.put("csltHostNot", "");this.put("csltHost", null);return this;};
 		/** not .... */
 		public M csltHostNot(){this.put("csltHostNot", "not");return this;};
		/** 星期几？ [非空]   1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六     **/
		public M csltWeekDay(Object csltWeekDay){this.put("csltWeekDay", csltWeekDay);return this;};
	 	/** and cslt_week_day is null */
 		public M csltWeekDayNull(){if(this.get("csltWeekDayNot")==null)this.put("csltWeekDayNot", "");this.put("csltWeekDay", null);return this;};
 		/** not .... */
 		public M csltWeekDayNot(){this.put("csltWeekDayNot", "not");return this;};
		/** 限行尾号 [非空]    限行的车牌尾号，123表未以1或2或3的数字尾号   **/
		public M csltNum(Object csltNum){this.put("csltNum", csltNum);return this;};
	 	/** and cslt_num is null */
 		public M csltNumNull(){if(this.get("csltNumNot")==null)this.put("csltNumNot", "");this.put("csltNum", null);return this;};
 		/** not .... */
 		public M csltNumNot(){this.put("csltNumNot", "not");return this;};
		/** 开始时间 [非空]       **/
		public M csltStart(Object csltStart){this.put("csltStart", csltStart);return this;};
	 	/** and cslt_start is null */
 		public M csltStartNull(){if(this.get("csltStartNot")==null)this.put("csltStartNot", "");this.put("csltStart", null);return this;};
 		/** not .... */
 		public M csltStartNot(){this.put("csltStartNot", "not");return this;};
		/** 结束时间 [非空]       **/
		public M csltEnd(Object csltEnd){this.put("csltEnd", csltEnd);return this;};
	 	/** and cslt_end is null */
 		public M csltEndNull(){if(this.get("csltEndNot")==null)this.put("csltEndNot", "");this.put("csltEnd", null);return this;};
 		/** not .... */
 		public M csltEndNot(){this.put("csltEndNot", "not");return this;};
		/** 更新时间        **/
		public M csltUpdateTime(Object csltUpdateTime){this.put("csltUpdateTime", csltUpdateTime);return this;};
	 	/** and cslt_update_time is null */
 		public M csltUpdateTimeNull(){if(this.get("csltUpdateTimeNot")==null)this.put("csltUpdateTimeNot", "");this.put("csltUpdateTime", null);return this;};
 		/** not .... */
 		public M csltUpdateTimeNot(){this.put("csltUpdateTimeNot", "not");return this;};
 		/** and cslt_update_time >= ? */
 		public M csltUpdateTimeStart(Object start){this.put("csltUpdateTimeStart", start);return this;};			
 		/** and cslt_update_time <= ? */
 		public M csltUpdateTimeEnd(Object end){this.put("csltUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有交通限行 **/
		public @api List<CsLimitTime> list(Integer size){
			return getCsLimitTimeList(this,size);
		}
		/** 获取交通限行分页 **/
		public @api Page<CsLimitTime> page(int page,int size){
			return getCsLimitTimePage(page, size , this);
		}
		/** 根据查询条件取交通限行 **/
		public @api CsLimitTime get(){
			return getCsLimitTime(this);
		}
		/** 获取交通限行数 **/
		public @api Long count(){
			return getCsLimitTimeCount(this);
		}
		/** 获取交通限行表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLimitTimeEval(strEval,this);
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
			updateCsLimitTime(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csltId="csltId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csltHost="csltHost";
		/** 星期几？ [非空]   1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六     **/
		public final static @type(Short.class)  String csltWeekDay="csltWeekDay";
		/** 限行尾号 [非空]    限行的车牌尾号，123表未以1或2或3的数字尾号   **/
		public final static @type(String.class) @like String csltNum="csltNum";
		/** 开始时间 [非空]       **/
		public final static @type(String.class) @like String csltStart="csltStart";
		/** 结束时间 [非空]       **/
		public final static @type(String.class) @like String csltEnd="csltEnd";
		/** 更新时间        **/
		public final static @type(Date.class)  String csltUpdateTime="csltUpdateTime";
	 	/** and cslt_update_time >= ? */
 		public final static @type(Date.class) String csltUpdateTimeStart="csltUpdateTimeStart";
 		/** and cslt_update_time <= ? */
 		public final static @type(Date.class) String csltUpdateTimeEnd="csltUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csltId="cslt_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csltHost="cslt_host";
		/** 星期几？ [非空]   1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六     **/
		public final static String csltWeekDay="cslt_week_day";
		/** 限行尾号 [非空]    限行的车牌尾号，123表未以1或2或3的数字尾号   **/
		public final static String csltNum="cslt_num";
		/** 开始时间 [非空]       **/
		public final static String csltStart="cslt_start";
		/** 结束时间 [非空]       **/
		public final static String csltEnd="cslt_end";
		/** 更新时间        **/
		public final static String csltUpdateTime="cslt_update_time";
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
				$.Set(name,CsLimitTime.getCsLimitTime(params));
			else
				$.Set(name,CsLimitTime.getCsLimitTimeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取交通限行数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLimitTime) $.GetRequest("CsLimitTime$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLimitTime.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLimitTime.getCsLimitTime(params);
			else
				value = CsLimitTime.getCsLimitTimeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLimitTime.Get($.add(CsLimitTime.F.csltId,param));
		else if(!$.empty(param.toString()))
			value = CsLimitTime.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLimitTime$"+param.hashCode(), value);
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
	public void mergeSet(CsLimitTime old){
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