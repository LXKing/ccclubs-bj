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

@namespace("shop/cartitem")
public @caption("购物车项") @table("cs_cart_item") class CsCartItem implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csci_id")   @primary  @note("  ") Long csciId;// 主键 非空     
	private @caption("所属会员") @column("csci_member")    @relate("$csciMember") @RelateClass(CsMember.class)  @note("  ") Long csciMember;// 非空     
 	private CsMember $csciMember;//关联对象[会员帐号]
	private @caption("所选商品") @column("csci_item")    @relate("$csciItem") @RelateClass(CsItem.class)  @note("  ") Long csciItem;// 非空     
 	private CsItem $csciItem;//关联对象[商品信息]
	private @caption("所选数量") @column("csci_count")    @note("  ") Integer csciCount;// 非空     
	private @caption("添加时间") @column("csci_add_time")    @note("  ") Date csciAddTime;// 非空     
	
	//默认构造函数
	public CsCartItem(){
	
	}
	
	//主键构造函数
	public CsCartItem(Long id){
		this.csciId = id;
	}
	
	/**所有字段构造函数 CsCartItem(csciMember,csciItem,csciCount,csciAddTime)
	 CsCartItem(
	 	$.getLong("csciMember")//所属会员 [非空]
	 	,$.getLong("csciItem")//所选商品 [非空]
	 	,$.getInteger("csciCount")//所选数量 [非空]
	 	,$.getDate("csciAddTime")//添加时间 [非空]
	 )
	**/
	public CsCartItem(Long csciMember,Long csciItem,Integer csciCount,Date csciAddTime){
		this.csciMember=csciMember;
		this.csciItem=csciItem;
		this.csciCount=csciCount;
		this.csciAddTime=csciAddTime;
	}
	
	//设置非空字段
	public CsCartItem setNotNull(Long csciMember,Long csciItem,Integer csciCount,Date csciAddTime){
		this.csciMember=csciMember;
		this.csciItem=csciItem;
		this.csciCount=csciCount;
		this.csciAddTime=csciAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCartItem csciId(Long csciId){
		this.csciId = csciId;
		this.setSeted(F.csciId);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsCartItem csciMember(Long csciMember){
		this.csciMember = csciMember;
		this.setSeted(F.csciMember);
		return this;
	}
	/** 所选商品 [非空] [CsItem]      **/
	public CsCartItem csciItem(Long csciItem){
		this.csciItem = csciItem;
		this.setSeted(F.csciItem);
		return this;
	}
	/** 所选数量 [非空]       **/
	public CsCartItem csciCount(Integer csciCount){
		this.csciCount = csciCount;
		this.setSeted(F.csciCount);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCartItem csciAddTime(Date csciAddTime){
		this.csciAddTime = csciAddTime;
		this.setSeted(F.csciAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsCartItem clone(){
		CsCartItem clone = new CsCartItem();
		clone.csciMember=this.csciMember;
		clone.csciItem=this.csciItem;
		clone.csciCount=this.csciCount;
		clone.csciAddTime=this.csciAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取购物车项
	 * @param id
	 * @return
	 */
	public static @api CsCartItem get(Long id){		
		return getCsCartItemById(id);
	}
	/**
	 * 获取所有购物车项
	 * @return
	 */
	public static @api List<CsCartItem> list(Map params,Integer size){
		return getCsCartItemList(params,size);
	}
	/**
	 * 获取购物车项分页
	 * @return
	 */
	public static @api Page<CsCartItem> page(int page,int size,Map params){
		return getCsCartItemPage(page, size , params);
	}
	/**
	 * 根据查询条件取购物车项
	 * @param params
	 * @return
	 */
	public static @api CsCartItem Get(Map params){
		return getCsCartItem(params);
	}
	/**
	 * 获取购物车项数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCartItemCount(params);
	}
	/**
	 * 获取购物车项数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCartItemEval(eval,params);
	}
	
	/**
	 * 根据ID取购物车项
	 * @param id
	 * @return
	 */
	public static @api CsCartItem getCsCartItemById(Long id){		
		CsCartItem csCartItem = (CsCartItem) $.GetRequest("CsCartItem$"+id);
		if(csCartItem!=null)
			return csCartItem;
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");		
		return csCartItemService.getCsCartItemById(id);
	}
	
	
	/**
	 * 根据ID取购物车项的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCartItem.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCartItem csCartItem = get(id);
		if(csCartItem!=null){
			String strValue = csCartItem.getCsciId$();
			if(!"CsciId".equals("CsciId"))
				strValue+="("+csCartItem.getCsciId$()+")";
			MemCache.setValue(CsCartItem.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsciId$();
		if(!"CsciId".equals("CsciId"))
			keyValue+="("+this.getCsciId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有购物车项
	 * @return
	 */
	public static @api List<CsCartItem> getCsCartItemList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		return csCartItemService.getCsCartItemList(params, size);
	}
	
	/**
	 * 获取购物车项分页
	 * @return
	 */
	public static @api Page<CsCartItem> getCsCartItemPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		return csCartItemService.getCsCartItemPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取购物车项
	 * @param params
	 * @return
	 */
	public static @api CsCartItem getCsCartItem(Map params){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		return csCartItemService.getCsCartItem(params);
	}
	
	/**
	 * 获取购物车项数
	 * @return
	 */
	public static @api Long getCsCartItemCount(Map params){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		return csCartItemService.getCsCartItemCount(params);
	}
		
		
	/**
	 * 获取购物车项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCartItemEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		return csCartItemService.getCsCartItemEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCartItem(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		csCartItemService.updateCsCartItemByConfirm(set, where);
	}
	
	
	/**
	 * 保存购物车项对象
	 * @param params
	 * @return
	 */
	public CsCartItem save(){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		if(this.getCsciId()!=null)
			csCartItemService.updateCsCartItem(this);
		else
			return csCartItemService.saveCsCartItem(this);
		return this;
	}
	
	
	/**
	 * 更新购物车项对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		csCartItemService.updateCsCartItem$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
			csCartItemService.deleteCsCartItemById(this.getCsciId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCartItemService csCartItemService = $.GetSpringBean("csCartItemService");
		return csCartItemService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsciId(){
		return this.csciId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsciId$(){
		String strValue="";
		 strValue=$.str(this.getCsciId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsciId(Long csciId){
		this.csciId = csciId;
		this.setSeted(F.csciId);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCsciMember(){
		return this.csciMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsciMember$(){
		String strValue="";
		if(this.getCsciMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsciMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCsciMember(Long csciMember){
		this.csciMember = csciMember;
		this.setSeted(F.csciMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csciMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsciMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsciMember()!=null){
 			csMember = CsMember.get(this.getCsciMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsciMember(), csMember);
	 	return csMember;
	}
	/*******************************所选商品**********************************/	
	/**
	* 所选商品 [非空] [CsItem]     
	**/
	public Long getCsciItem(){
		return this.csciItem;
	}
	/**
	* 获取所选商品格式化(toString)
	**/
	public String getCsciItem$(){
		String strValue="";
		if(this.getCsciItem()!=null){
				strValue+=$.str(CsItem.getKeyValue(this.getCsciItem()));
		}			
	 	 return strValue;
	}
	/**
	* 所选商品 [非空] [CsItem]     
	**/
	public void setCsciItem(Long csciItem){
		this.csciItem = csciItem;
		this.setSeted(F.csciItem);
	}
	/**
	* 获取关联对象[商品信息]
	**/	 			
 	public CsItem get$csciItem(){
 		com.ccclubs.model.CsItem csItem = (com.ccclubs.model.CsItem) $.GetRequest("CsItem$"+this.getCsciItem());
 		if(csItem!=null)
			return csItem;
		if(this.getCsciItem()!=null){
 			csItem = CsItem.get(this.getCsciItem());
 		}
 		$.SetRequest("CsItem$"+this.getCsciItem(), csItem);
	 	return csItem;
	}
	/*******************************所选数量**********************************/	
	/**
	* 所选数量 [非空]      
	**/
	public Integer getCsciCount(){
		return this.csciCount;
	}
	/**
	* 获取所选数量格式化(toString)
	**/
	public String getCsciCount$(){
		String strValue="";
		 strValue=$.str(this.getCsciCount());
	 	 return strValue;
	}
	/**
	* 所选数量 [非空]      
	**/
	public void setCsciCount(Integer csciCount){
		this.csciCount = csciCount;
		this.setSeted(F.csciCount);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsciAddTime(){
		return this.csciAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsciAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsciAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsciAddTime(Date csciAddTime){
		this.csciAddTime = csciAddTime;
		this.setSeted(F.csciAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCartItem.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCartItem.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCartItem.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCartItem.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCartItem.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCartItem.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCartItem.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCartItem.this);
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
		public M csciId(Object csciId){this.put("csciId", csciId);return this;};
	 	/** and csci_id is null */
 		public M csciIdNull(){if(this.get("csciIdNot")==null)this.put("csciIdNot", "");this.put("csciId", null);return this;};
 		/** not .... */
 		public M csciIdNot(){this.put("csciIdNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M csciMember(Object csciMember){this.put("csciMember", csciMember);return this;};
	 	/** and csci_member is null */
 		public M csciMemberNull(){if(this.get("csciMemberNot")==null)this.put("csciMemberNot", "");this.put("csciMember", null);return this;};
 		/** not .... */
 		public M csciMemberNot(){this.put("csciMemberNot", "not");return this;};
		public M csciMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csciMember$on", value);
			return this;
		};	
		/** 所选商品 [非空] [CsItem]      **/
		public M csciItem(Object csciItem){this.put("csciItem", csciItem);return this;};
	 	/** and csci_item is null */
 		public M csciItemNull(){if(this.get("csciItemNot")==null)this.put("csciItemNot", "");this.put("csciItem", null);return this;};
 		/** not .... */
 		public M csciItemNot(){this.put("csciItemNot", "not");return this;};
		public M csciItem$on(CsItem.M value){
			this.put("CsItem", value);
			this.put("csciItem$on", value);
			return this;
		};	
		/** 所选数量 [非空]       **/
		public M csciCount(Object csciCount){this.put("csciCount", csciCount);return this;};
	 	/** and csci_count is null */
 		public M csciCountNull(){if(this.get("csciCountNot")==null)this.put("csciCountNot", "");this.put("csciCount", null);return this;};
 		/** not .... */
 		public M csciCountNot(){this.put("csciCountNot", "not");return this;};
		/** and csci_count >= ? */
		public M csciCountMin(Object min){this.put("csciCountMin", min);return this;};
		/** and csci_count <= ? */
		public M csciCountMax(Object max){this.put("csciCountMax", max);return this;};
		/** 添加时间 [非空]       **/
		public M csciAddTime(Object csciAddTime){this.put("csciAddTime", csciAddTime);return this;};
	 	/** and csci_add_time is null */
 		public M csciAddTimeNull(){if(this.get("csciAddTimeNot")==null)this.put("csciAddTimeNot", "");this.put("csciAddTime", null);return this;};
 		/** not .... */
 		public M csciAddTimeNot(){this.put("csciAddTimeNot", "not");return this;};
 		/** and csci_add_time >= ? */
 		public M csciAddTimeStart(Object start){this.put("csciAddTimeStart", start);return this;};			
 		/** and csci_add_time <= ? */
 		public M csciAddTimeEnd(Object end){this.put("csciAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有购物车项 **/
		public @api List<CsCartItem> list(Integer size){
			return getCsCartItemList(this,size);
		}
		/** 获取购物车项分页 **/
		public @api Page<CsCartItem> page(int page,int size){
			return getCsCartItemPage(page, size , this);
		}
		/** 根据查询条件取购物车项 **/
		public @api CsCartItem get(){
			return getCsCartItem(this);
		}
		/** 获取购物车项数 **/
		public @api Long count(){
			return getCsCartItemCount(this);
		}
		/** 获取购物车项表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCartItemEval(strEval,this);
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
			updateCsCartItem(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csciId="csciId";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csciMember="csciMember";
		/** 所选商品 [非空] [CsItem]      **/
		public final static @type(Long.class)  String csciItem="csciItem";
		/** 所选数量 [非空]       **/
		public final static @type(Integer.class)  String csciCount="csciCount";
		/** and csci_count >= ? */
		public final static @type(Integer.class) String csciCountMin="csciCountMin";
		/** and csci_count <= ? */
		public final static @type(Integer.class) String csciCountMax="csciCountMax";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csciAddTime="csciAddTime";
	 	/** and csci_add_time >= ? */
 		public final static @type(Date.class) String csciAddTimeStart="csciAddTimeStart";
 		/** and csci_add_time <= ? */
 		public final static @type(Date.class) String csciAddTimeEnd="csciAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csciId="csci_id";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String csciMember="csci_member";
		/** 所选商品 [非空] [CsItem]      **/
		public final static String csciItem="csci_item";
		/** 所选数量 [非空]       **/
		public final static String csciCount="csci_count";
		/** 添加时间 [非空]       **/
		public final static String csciAddTime="csci_add_time";
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
				$.Set(name,CsCartItem.getCsCartItem(params));
			else
				$.Set(name,CsCartItem.getCsCartItemList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取购物车项数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCartItem) $.GetRequest("CsCartItem$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCartItem.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCartItem.getCsCartItem(params);
			else
				value = CsCartItem.getCsCartItemList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCartItem.Get($.add(CsCartItem.F.csciId,param));
		else if(!$.empty(param.toString()))
			value = CsCartItem.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCartItem$"+param.hashCode(), value);
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
	public void mergeSet(CsCartItem old){
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