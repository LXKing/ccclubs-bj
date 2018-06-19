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

@namespace("user/pack")
public @caption("用户套餐") @table("cs_user_pack") class CsUserPack implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csup_id")   @primary  @note("  ") Long csupId;// 主键 非空     
	private @caption("城市") @column("csup_host")    @relate("$csupHost") @RelateClass(SrvHost.class)  @note("  ") Long csupHost;// 非空     
 	private SrvHost $csupHost;//关联对象[运营城市]
	private @caption("所属系统套餐") @column("csup_pack")    @relate("$csupPack") @RelateClass(CsPack.class)  @note("  ") Long csupPack;// 非空     
 	private CsPack $csupPack;//关联对象[系统套餐]
	private @caption("所属会员") @column("csup_member")    @relate("$csupMember") @RelateClass(CsMember.class)  @note("  ") Long csupMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csupMember;//关联对象[会员帐号]
	//private @caption("套餐内容") @RelateClass(CsGift.class) List<CsGift> csupGifts;//反向关联的优惠内容列表
	private @caption("备注") @column("csup_remark")    @note("  ") String csupRemark;//     
	private @caption("有效期至") @column("csup_until_time")    @note("  ") Date csupUntilTime;// 非空     
	private @caption("修改时间") @column("csup_update_time")    @note("  ") Date csupUpdateTime;// 非空     
	private @caption("添加时间") @column("csup_add_time")    @note("  ") Date csupAddTime;// 非空     
	private @caption("续订状态") @column("csup_state")  @hidden   @note(" 0:不续订 1:待续订 2:已续订  ") Short csupState;// 非空 0:不续订 1:待续订 2:已续订     
	private @caption("套餐状态") @column("csup_status")    @note(" 1:有效 0:无效 2:过期 3:用完  ") Short csupStatus;// 非空 1:有效 0:无效 2:过期 3:用完     
	
	//默认构造函数
	public CsUserPack(){
	
	}
	
	//主键构造函数
	public CsUserPack(Long id){
		this.csupId = id;
	}
	
	/**所有字段构造函数 CsUserPack(csupHost,csupPack,csupMember,csupRemark,csupUntilTime,csupUpdateTime,csupAddTime,csupState,csupStatus)
	 CsUserPack(
	 	$.getLong("csupHost")//城市 [非空]
	 	,$.getLong("csupPack")//所属系统套餐 [非空]
	 	,$.getLong("csupMember")//所属会员 [非空]
	 	,$.getString("csupRemark")//备注
	 	,$.getDate("csupUntilTime")//有效期至 [非空]
	 	,$.getDate("csupUpdateTime")//修改时间 [非空]
	 	,$.getDate("csupAddTime")//添加时间 [非空]
	 	,$.getShort("csupState")//续订状态 [非空]
	 	,$.getShort("csupStatus")//套餐状态 [非空]
	 )
	**/
	public CsUserPack(Long csupHost,Long csupPack,Long csupMember,String csupRemark,Date csupUntilTime,Date csupUpdateTime,Date csupAddTime,Short csupState,Short csupStatus){
		this.csupHost=csupHost;
		this.csupPack=csupPack;
		this.csupMember=csupMember;
		this.csupRemark=csupRemark;
		this.csupUntilTime=csupUntilTime;
		this.csupUpdateTime=csupUpdateTime;
		this.csupAddTime=csupAddTime;
		this.csupState=csupState;
		this.csupStatus=csupStatus;
	}
	
	//设置非空字段
	public CsUserPack setNotNull(Long csupHost,Long csupPack,Long csupMember,Date csupUntilTime,Date csupUpdateTime,Date csupAddTime,Short csupState,Short csupStatus){
		this.csupHost=csupHost;
		this.csupPack=csupPack;
		this.csupMember=csupMember;
		this.csupUntilTime=csupUntilTime;
		this.csupUpdateTime=csupUpdateTime;
		this.csupAddTime=csupAddTime;
		this.csupState=csupState;
		this.csupStatus=csupStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUserPack csupId(Long csupId){
		this.csupId = csupId;
		this.setSeted(F.csupId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUserPack csupHost(Long csupHost){
		this.csupHost = csupHost;
		this.setSeted(F.csupHost);
		return this;
	}
	/** 所属系统套餐 [非空] [CsPack]      **/
	public CsUserPack csupPack(Long csupPack){
		this.csupPack = csupPack;
		this.setSeted(F.csupPack);
		return this;
	}
	/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsUserPack csupMember(Long csupMember){
		this.csupMember = csupMember;
		this.setSeted(F.csupMember);
		return this;
	}
	/** 备注        **/
	public CsUserPack csupRemark(String csupRemark){
		this.csupRemark = csupRemark;
		this.setSeted(F.csupRemark);
		return this;
	}
	/** 有效期至 [非空]       **/
	public CsUserPack csupUntilTime(Date csupUntilTime){
		this.csupUntilTime = csupUntilTime;
		this.setSeted(F.csupUntilTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUserPack csupUpdateTime(Date csupUpdateTime){
		this.csupUpdateTime = csupUpdateTime;
		this.setSeted(F.csupUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUserPack csupAddTime(Date csupAddTime){
		this.csupAddTime = csupAddTime;
		this.setSeted(F.csupAddTime);
		return this;
	}
	/** 续订状态 [非空]   0:不续订 1:待续订 2:已续订     **/
	public CsUserPack csupState(Short csupState){
		this.csupState = csupState;
		this.setSeted(F.csupState);
		return this;
	}
	/** 套餐状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
	public CsUserPack csupStatus(Short csupStatus){
		this.csupStatus = csupStatus;
		this.setSeted(F.csupStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUserPack clone(){
		CsUserPack clone = new CsUserPack();
		clone.csupHost=this.csupHost;
		clone.csupPack=this.csupPack;
		clone.csupMember=this.csupMember;
		clone.csupUntilTime=this.csupUntilTime;
		clone.csupUpdateTime=this.csupUpdateTime;
		clone.csupAddTime=this.csupAddTime;
		clone.csupState=this.csupState;
		clone.csupStatus=this.csupStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取用户套餐
	 * @param id
	 * @return
	 */
	public static @api CsUserPack get(Long id){		
		return getCsUserPackById(id);
	}
	/**
	 * 获取所有用户套餐
	 * @return
	 */
	public static @api List<CsUserPack> list(Map params,Integer size){
		return getCsUserPackList(params,size);
	}
	/**
	 * 获取用户套餐分页
	 * @return
	 */
	public static @api Page<CsUserPack> page(int page,int size,Map params){
		return getCsUserPackPage(page, size , params);
	}
	/**
	 * 根据查询条件取用户套餐
	 * @param params
	 * @return
	 */
	public static @api CsUserPack Get(Map params){
		return getCsUserPack(params);
	}
	/**
	 * 获取用户套餐数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUserPackCount(params);
	}
	/**
	 * 获取用户套餐数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUserPackEval(eval,params);
	}
	
	/**
	 * 根据ID取用户套餐
	 * @param id
	 * @return
	 */
	public static @api CsUserPack getCsUserPackById(Long id){		
		CsUserPack csUserPack = (CsUserPack) $.GetRequest("CsUserPack$"+id);
		if(csUserPack!=null)
			return csUserPack;
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");		
		return csUserPackService.getCsUserPackById(id);
	}
	
	
	/**
	 * 根据ID取用户套餐的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUserPack.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUserPack csUserPack = get(id);
		if(csUserPack!=null){
			String strValue = csUserPack.getCsupId$();
			if(!"CsupId".equals("CsupId"))
				strValue+="("+csUserPack.getCsupId$()+")";
			MemCache.setValue(CsUserPack.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsupId$();
		if(!"CsupId".equals("CsupId"))
			keyValue+="("+this.getCsupId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有用户套餐
	 * @return
	 */
	public static @api List<CsUserPack> getCsUserPackList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		return csUserPackService.getCsUserPackList(params, size);
	}
	
	/**
	 * 获取用户套餐分页
	 * @return
	 */
	public static @api Page<CsUserPack> getCsUserPackPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		return csUserPackService.getCsUserPackPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取用户套餐
	 * @param params
	 * @return
	 */
	public static @api CsUserPack getCsUserPack(Map params){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		return csUserPackService.getCsUserPack(params);
	}
	
	/**
	 * 获取用户套餐数
	 * @return
	 */
	public static @api Long getCsUserPackCount(Map params){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		return csUserPackService.getCsUserPackCount(params);
	}
		
		
	/**
	 * 获取用户套餐自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUserPackEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		return csUserPackService.getCsUserPackEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUserPack(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		csUserPackService.updateCsUserPackByConfirm(set, where);
	}
	
	
	/**
	 * 保存用户套餐对象
	 * @param params
	 * @return
	 */
	public CsUserPack save(){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		if(this.getCsupId()!=null)
			csUserPackService.updateCsUserPack(this);
		else
			return csUserPackService.saveCsUserPack(this);
		return this;
	}
	
	
	/**
	 * 更新用户套餐对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		csUserPackService.updateCsUserPack$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		if($.equals($.config("logic_delete"),"true"))
			csUserPackService.removeCsUserPackById(this.getCsupId());
		else
			csUserPackService.deleteCsUserPackById(this.getCsupId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		return csUserPackService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsupId(){
		return this.csupId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsupId$(){
		String strValue="";
		 strValue=$.str(this.getCsupId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsupId(Long csupId){
		this.csupId = csupId;
		this.setSeted(F.csupId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsupHost(){
		return this.csupHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsupHost$(){
		String strValue="";
		if(this.getCsupHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsupHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsupHost(Long csupHost){
		this.csupHost = csupHost;
		this.setSeted(F.csupHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csupHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsupHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsupHost()!=null){
 			srvHost = SrvHost.get(this.getCsupHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsupHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属系统套餐**********************************/	
	/**
	* 所属系统套餐 [非空] [CsPack]     
	**/
	public Long getCsupPack(){
		return this.csupPack;
	}
	/**
	* 获取所属系统套餐格式化(toString)
	**/
	public String getCsupPack$(){
		String strValue="";
		if(this.getCsupPack()!=null){
				strValue+=$.str(CsPack.getKeyValue(this.getCsupPack()));
		}			
	 	 return strValue;
	}
	/**
	* 所属系统套餐 [非空] [CsPack]     
	**/
	public void setCsupPack(Long csupPack){
		this.csupPack = csupPack;
		this.setSeted(F.csupPack);
	}
	/**
	* 获取关联对象[系统套餐]
	**/	 			
 	public CsPack get$csupPack(){
 		com.ccclubs.model.CsPack csPack = (com.ccclubs.model.CsPack) $.GetRequest("CsPack$"+this.getCsupPack());
 		if(csPack!=null)
			return csPack;
		if(this.getCsupPack()!=null){
 			csPack = CsPack.get(this.getCsupPack());
 		}
 		$.SetRequest("CsPack$"+this.getCsupPack(), csPack);
	 	return csPack;
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsupMember(){
		return this.csupMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsupMember$(){
		String strValue="";
		if(this.getCsupMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsupMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsupMember(Long csupMember){
		this.csupMember = csupMember;
		this.setSeted(F.csupMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csupMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsupMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsupMember()!=null){
 			csMember = CsMember.get(this.getCsupMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsupMember(), csMember);
	 	return csMember;
	}
	/*******************************反向关联的优惠内容列表**********************************/	
	/**
	* 获取与当前对象反向关联的优惠内容列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsGift> getCsupGifts(){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		Map params = new HashMap();
		params.put("csgUserPack",this.getCsupId());
		return csGiftService.getCsGiftList(params,-1);
	}
	/**
	* 获取套餐内容格式化(toString)
	**/
	public String getCsupGifts$(){
		return "[...]";
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsupRemark(){
		return this.csupRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsupRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsupRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsupRemark(String csupRemark){
		this.csupRemark = csupRemark;
		this.setSeted(F.csupRemark);
	}
	/*******************************有效期至**********************************/	
	/**
	* 有效期至 [非空]      
	**/
	public Date getCsupUntilTime(){
		return this.csupUntilTime;
	}
	/**
	* 获取有效期至格式化(toString)
	**/
	public String getCsupUntilTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsupUntilTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 有效期至 [非空]      
	**/
	public void setCsupUntilTime(Date csupUntilTime){
		this.csupUntilTime = csupUntilTime;
		this.setSeted(F.csupUntilTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsupUpdateTime(){
		return this.csupUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsupUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsupUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsupUpdateTime(Date csupUpdateTime){
		this.csupUpdateTime = csupUpdateTime;
		this.setSeted(F.csupUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsupAddTime(){
		return this.csupAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsupAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsupAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsupAddTime(Date csupAddTime){
		this.csupAddTime = csupAddTime;
		this.setSeted(F.csupAddTime);
	}
	/*******************************续订状态**********************************/	
	/**
	* 续订状态 [非空]   0:不续订 1:待续订 2:已续订    
	**/
	public Short getCsupState(){
		return this.csupState;
	}
	/**
	* 获取续订状态格式化(toString)
	**/
	public String getCsupState$(){
		String strValue="";
		 if($.equals($.str(this.getCsupState()),"0"))
			strValue=$.str("不续订");		 
		 if($.equals($.str(this.getCsupState()),"1"))
			strValue=$.str("待续订");		 
		 if($.equals($.str(this.getCsupState()),"2"))
			strValue=$.str("已续订");		 
	 	 return strValue;
	}
	/**
	* 续订状态 [非空]   0:不续订 1:待续订 2:已续订    
	**/
	public void setCsupState(Short csupState){
		this.csupState = csupState;
		this.setSeted(F.csupState);
	}
	/*******************************套餐状态**********************************/	
	/**
	* 套餐状态 [非空]   1:有效 0:无效 2:过期 3:用完    
	**/
	public Short getCsupStatus(){
		return this.csupStatus;
	}
	/**
	* 获取套餐状态格式化(toString)
	**/
	public String getCsupStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsupStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getCsupStatus()),"0"))
			strValue=$.str("无效");		 
		 if($.equals($.str(this.getCsupStatus()),"2"))
			strValue=$.str("过期");		 
		 if($.equals($.str(this.getCsupStatus()),"3"))
			strValue=$.str("用完");		 
	 	 return strValue;
	}
	/**
	* 套餐状态 [非空]   1:有效 0:无效 2:过期 3:用完    
	**/
	public void setCsupStatus(Short csupStatus){
		this.csupStatus = csupStatus;
		this.setSeted(F.csupStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUserPack.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUserPack.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUserPack.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUserPack.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUserPack.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUserPack.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUserPack.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUserPack.this);
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
		public M csupId(Object csupId){this.put("csupId", csupId);return this;};
	 	/** and csup_id is null */
 		public M csupIdNull(){if(this.get("csupIdNot")==null)this.put("csupIdNot", "");this.put("csupId", null);return this;};
 		/** not .... */
 		public M csupIdNot(){this.put("csupIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csupHost(Object csupHost){this.put("csupHost", csupHost);return this;};
	 	/** and csup_host is null */
 		public M csupHostNull(){if(this.get("csupHostNot")==null)this.put("csupHostNot", "");this.put("csupHost", null);return this;};
 		/** not .... */
 		public M csupHostNot(){this.put("csupHostNot", "not");return this;};
		/** 所属系统套餐 [非空] [CsPack]      **/
		public M csupPack(Object csupPack){this.put("csupPack", csupPack);return this;};
	 	/** and csup_pack is null */
 		public M csupPackNull(){if(this.get("csupPackNot")==null)this.put("csupPackNot", "");this.put("csupPack", null);return this;};
 		/** not .... */
 		public M csupPackNot(){this.put("csupPackNot", "not");return this;};
		public M csupPack$on(CsPack.M value){
			this.put("CsPack", value);
			this.put("csupPack$on", value);
			return this;
		};	
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csupMember(Object csupMember){this.put("csupMember", csupMember);return this;};
	 	/** and csup_member is null */
 		public M csupMemberNull(){if(this.get("csupMemberNot")==null)this.put("csupMemberNot", "");this.put("csupMember", null);return this;};
 		/** not .... */
 		public M csupMemberNot(){this.put("csupMemberNot", "not");return this;};
		public M csupMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csupMember$on", value);
			return this;
		};	
		/** 备注        **/
		public M csupRemark(Object csupRemark){this.put("csupRemark", csupRemark);return this;};
	 	/** and csup_remark is null */
 		public M csupRemarkNull(){if(this.get("csupRemarkNot")==null)this.put("csupRemarkNot", "");this.put("csupRemark", null);return this;};
 		/** not .... */
 		public M csupRemarkNot(){this.put("csupRemarkNot", "not");return this;};
		/** 有效期至 [非空]       **/
		public M csupUntilTime(Object csupUntilTime){this.put("csupUntilTime", csupUntilTime);return this;};
	 	/** and csup_until_time is null */
 		public M csupUntilTimeNull(){if(this.get("csupUntilTimeNot")==null)this.put("csupUntilTimeNot", "");this.put("csupUntilTime", null);return this;};
 		/** not .... */
 		public M csupUntilTimeNot(){this.put("csupUntilTimeNot", "not");return this;};
 		/** and csup_until_time >= ? */
 		public M csupUntilTimeStart(Object start){this.put("csupUntilTimeStart", start);return this;};			
 		/** and csup_until_time <= ? */
 		public M csupUntilTimeEnd(Object end){this.put("csupUntilTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csupUpdateTime(Object csupUpdateTime){this.put("csupUpdateTime", csupUpdateTime);return this;};
	 	/** and csup_update_time is null */
 		public M csupUpdateTimeNull(){if(this.get("csupUpdateTimeNot")==null)this.put("csupUpdateTimeNot", "");this.put("csupUpdateTime", null);return this;};
 		/** not .... */
 		public M csupUpdateTimeNot(){this.put("csupUpdateTimeNot", "not");return this;};
 		/** and csup_update_time >= ? */
 		public M csupUpdateTimeStart(Object start){this.put("csupUpdateTimeStart", start);return this;};			
 		/** and csup_update_time <= ? */
 		public M csupUpdateTimeEnd(Object end){this.put("csupUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csupAddTime(Object csupAddTime){this.put("csupAddTime", csupAddTime);return this;};
	 	/** and csup_add_time is null */
 		public M csupAddTimeNull(){if(this.get("csupAddTimeNot")==null)this.put("csupAddTimeNot", "");this.put("csupAddTime", null);return this;};
 		/** not .... */
 		public M csupAddTimeNot(){this.put("csupAddTimeNot", "not");return this;};
 		/** and csup_add_time >= ? */
 		public M csupAddTimeStart(Object start){this.put("csupAddTimeStart", start);return this;};			
 		/** and csup_add_time <= ? */
 		public M csupAddTimeEnd(Object end){this.put("csupAddTimeEnd", end);return this;};
		/** 续订状态 [非空]   0:不续订 1:待续订 2:已续订     **/
		public M csupState(Object csupState){this.put("csupState", csupState);return this;};
	 	/** and csup_state is null */
 		public M csupStateNull(){if(this.get("csupStateNot")==null)this.put("csupStateNot", "");this.put("csupState", null);return this;};
 		/** not .... */
 		public M csupStateNot(){this.put("csupStateNot", "not");return this;};
		/** 套餐状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
		public M csupStatus(Object csupStatus){this.put("csupStatus", csupStatus);return this;};
	 	/** and csup_status is null */
 		public M csupStatusNull(){if(this.get("csupStatusNot")==null)this.put("csupStatusNot", "");this.put("csupStatus", null);return this;};
 		/** not .... */
 		public M csupStatusNot(){this.put("csupStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有用户套餐 **/
		public @api List<CsUserPack> list(Integer size){
			return getCsUserPackList(this,size);
		}
		/** 获取用户套餐分页 **/
		public @api Page<CsUserPack> page(int page,int size){
			return getCsUserPackPage(page, size , this);
		}
		/** 根据查询条件取用户套餐 **/
		public @api CsUserPack get(){
			return getCsUserPack(this);
		}
		/** 获取用户套餐数 **/
		public @api Long count(){
			return getCsUserPackCount(this);
		}
		/** 获取用户套餐表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUserPackEval(strEval,this);
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
			updateCsUserPack(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csupId="csupId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csupHost="csupHost";
		/** 所属系统套餐 [非空] [CsPack]      **/
		public final static @type(Long.class)  String csupPack="csupPack";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csupMember="csupMember";
		/** 备注        **/
		public final static @type(String.class) @like String csupRemark="csupRemark";
		/** 有效期至 [非空]       **/
		public final static @type(Date.class)  String csupUntilTime="csupUntilTime";
	 	/** and csup_until_time >= ? */
 		public final static @type(Date.class) String csupUntilTimeStart="csupUntilTimeStart";
 		/** and csup_until_time <= ? */
 		public final static @type(Date.class) String csupUntilTimeEnd="csupUntilTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csupUpdateTime="csupUpdateTime";
	 	/** and csup_update_time >= ? */
 		public final static @type(Date.class) String csupUpdateTimeStart="csupUpdateTimeStart";
 		/** and csup_update_time <= ? */
 		public final static @type(Date.class) String csupUpdateTimeEnd="csupUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csupAddTime="csupAddTime";
	 	/** and csup_add_time >= ? */
 		public final static @type(Date.class) String csupAddTimeStart="csupAddTimeStart";
 		/** and csup_add_time <= ? */
 		public final static @type(Date.class) String csupAddTimeEnd="csupAddTimeEnd";
		/** 续订状态 [非空]   0:不续订 1:待续订 2:已续订     **/
		public final static @type(Short.class)  String csupState="csupState";
		/** 套餐状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
		public final static @type(Short.class)  String csupStatus="csupStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csupId="csup_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csupHost="csup_host";
		/** 所属系统套餐 [非空] [CsPack]      **/
		public final static String csupPack="csup_pack";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csupMember="csup_member";
		/** 备注        **/
		public final static String csupRemark="csup_remark";
		/** 有效期至 [非空]       **/
		public final static String csupUntilTime="csup_until_time";
		/** 修改时间 [非空]       **/
		public final static String csupUpdateTime="csup_update_time";
		/** 添加时间 [非空]       **/
		public final static String csupAddTime="csup_add_time";
		/** 续订状态 [非空]   0:不续订 1:待续订 2:已续订     **/
		public final static String csupState="csup_state";
		/** 套餐状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
		public final static String csupStatus="csup_status";
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
				$.Set(name,CsUserPack.getCsUserPack(params));
			else
				$.Set(name,CsUserPack.getCsUserPackList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取用户套餐数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUserPack) $.GetRequest("CsUserPack$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUserPack.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUserPack.getCsUserPack(params);
			else
				value = CsUserPack.getCsUserPackList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUserPack.Get($.add(CsUserPack.F.csupId,param));
		else if(!$.empty(param.toString()))
			value = CsUserPack.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUserPack$"+param.hashCode(), value);
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
	public void mergeSet(CsUserPack old){
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