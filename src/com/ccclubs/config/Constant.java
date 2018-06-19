package com.ccclubs.config;

/**
 * 程序中用到的约束，及常量
 * 
 * @author Administrator
 * 
 */
public final class Constant {
	/**
	 * 网点区域数据库标识
	 */
	public static final String AREA_ROOT_TYPE_VALUE = "AREA_TYPE";
	/**
	 * 免责每公里
	 */
	public static final String INSURE = "INSURE";
	/**
	 * 免责每小时
	 */
	public static final String SECURE = "SECURE";

	/**
	 * 阿里巴巴网点对应的id
	 */
	public static final Long PRICES_OUTETS_VALUE = 1006l;
	/**
	 * 工作日
	 */
	public static final String DAYS = "工作日";
	/**
	 * 节假日
	 */
	public static final String HOLIDAYS = "节假日";
	/**
	 * 双休日
	 */
	public static final String WEEKENDS = "双休日";
	/**
	 * 白天档
	 */
	public static final String DAY_TIME = "白天";
	/**
	 * 油里程
	 */
	public static final String FUEL_MILE = "油里程";
	/**
	 * 验证码 session 标识
	 */
	public static final String VERIFY_CODE = "verify_code";
	/**
	 * 修改密码 手机号 session 标识
	 */
	public static final String VERIFY_MOBILE = "verify_mobile";
	/**
	 * 基本定单信息 session 标识
	 */
	public static final String BASE_ORDER_INFO = "base_order_info";
	/**
	 * 默认获取数据量
	 */
	public static final int LOADE_COUNT = 5;
	/**
	 * 自动生成用户名的长度
	 */
	public static final int USERNAME_LENGTH = 12;
	//------------------------------------------------------------------预订页面，默认或者天的价格排序
	/**
	 * 默认排序
	 */
	public static final String ORDER_SORT_DEFAULT = "default";
	/**
	 * 价格排序
	 */
	public static final String ORDER_SORT_PRICE = "csc_day_price";
	//------------------------------------------------------------------定单相关时间设置
	/**
	 * 订单，最早提前7天预定
	 */
	public static final int ORDER_DAYS_EARLY = 7;
	/**
	 * 订单，最大持续30天
	 */
	public static final int ORDER_DAYS_DUR	 = 30;
	/**
	 * 订单时间默认格式 yyyy-MM-dd HH:mm
	 */
	public static final String ORDER_NORMAL_FORMAT	 = "yyyy-MM-dd HH:mm";
	/**
	 * 订单时间短格式 yyyy-MM-dd
	 */
	public static final String ORDER_SHORT_FORMAT	 = "yyyy-MM-dd";
	// ------------------------------------------------------------------认证积分发放
	/**
	 * 邀请好友-邀请人
	 */
	public static final int VERY_INVITATION = 200;
	/**
	 * 邀请好友-被邀请人
	 */
	public static final int VERY_INVITATION_PASSIVE = 100;
	/**
	 * 手机认证
	 */
	public static final int VERY_MOBILE = 100;
	/**
	 * 头像上传
	 */
	public static final int VERY_HEADER = 100;
	/**
	 * 个人基本信息完善
	 */
	public static final int VERY_BASIC_INFO = 200;
	/**
	 * 紧急联系人信息完善
	 */
	public static final int VERY_CONTACT_INFO = 200;
}
