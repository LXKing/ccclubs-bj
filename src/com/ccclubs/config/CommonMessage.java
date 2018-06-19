package com.ccclubs.config;

/**
 * 一些程序的消息
 * 
 * @author Administrator
 * 
 */
public final class CommonMessage {
	/**
	 * 系统错误,请稍候再试
	 */
	public static final String MSG0000 = "系统错误,请稍候再试";
	/**
	 * 租金工作日白天小时
	 */
	public static final String MSG0100 = "工作日·白天小时";
	/**
	 * 租金工作日晚上小时
	 */
	public static final String MSG0101 = "工作日·晚上小时";
	/**
	 * 你还没有输入帐号！
	 */
	public static final String MSG0102 = "你还没有输入帐号！";
	/**
	 * 你还没有输入密码！
	 */
	public static final String MSG0103 = "你还没有输入密码！";
	/**
	 * 不存在该帐户。
	 */
	public static final String MSG0104 = "不存在该帐户。";
	/**
	 * 你输入的帐号不正确，请重新输入。
	 */
	public static final String MSG0105 = "你输入的帐号不正确，请重新输入。";
	/**
	 * 你输入的密码不正确，请重新输入。
	 */
	public static final String MSG0106 = "你输入的密码不正确，请重新输入。";
	/**
	 * 该帐户已经失效，请联系北京出行
	 */
	public static final String MSG0107 = "该帐户已经失效，请联系北京出行。";

	// --------------------------------------------------------------------------------短信模板
	/**
	 * 您的校验码为：{code}，您正在注册北京出行，如有疑问请致电0571-88190338[北京出行]
	 */
	public static final String MSG0200 = "你的校验码为：{code}，你正在注册北京出行，如有疑问请致电0571-88190338。【北京出行】";
	
	/**
	 * 你的校验码为：{code}，你正在通过手机短信找回密码，如有疑问请致电0571-88190338。【北京出行】
	 */
	public static final String MSG0201 = "你的校验码为：{code}，你正在通过手机短信找回密码，如有疑问请致电北京出行400-666-9806。";
	
	/**
	 * 你的校验码为：{code}，你正在通过手机短信重置密码，如有疑问请致电0571-88190338。【北京出行】
	 */
	public static final String MSG0202 = "你的校验码为：{code}，你正在通过手机短信重置密码，如有疑问请致电北京出行400-666-9806。";
	
	/**
	 * 你的校验码为：{code}，你正在通过手机短信重置密码，如有疑问请致电0571-88190338。【北京出行】
	 */
	public static final String MSG0203 = "你的校验码为：{code}，你正在通过手机短信注册账号，如有疑问请致电北京出行400-666-9806。";
	

	
	//--------------------------------------------------------------------------------- 操作记录
	/**
	 * "免费小时兑换-你使用{amo unt}积分兑换了{freehour}个免费小时，有效期为1个月。"
	 */
	public static final String MSG0300 = "免费小时兑换 - 你使用{amount}积分兑换了{freehour}个免费小时，有效期为1个月。";
	/**
	 * left: {left_value}%; width: {widh_value}%;
	 */
	public static final String MSG0301 = "left: {left_value}%; width: {widh_value}%;";
	/**
	 * "手机认证-你通过了手机认证，获得{point}积分。"
	 */
	public static final String MSG0302 = "手机认证 - 你通过了手机认证，获得{point}积分。";
	/**
	 * 头像上传-第一次上传头像，获得{point}积分。
	 */
	public static final String MSG0303 = "头像上传 - 第一次上传头像，获得{point}积分。";
	/**
	 * 完善信息-第一次完善个人基本信息，获得{point}积分。
	 */
	public static final String MSG0304 = "完善信息 - 第一次完善个人基本信息，获得{point}积分。";
	/**
	 * 完善信息-第一次完善紧急联系人信息，获得{point}积分。
	 */
	public static final String MSG0305 = "完善信息 - 第一次完善紧急联系人信息，获得{point}积分。";
	/**
	 * 邀请好友-你邀请手机号为的{mobile}好友加入北京出行，获得{point}积分。
	 */
	public static final String MSG0306 = "你邀请手机号为的{mobile}好友加入北京出行，获得{point}积分。";
	/**
	 * "购买现金券-欢迎使用北京出行现金券服务，充值帐户支付{money}元，获得{coupon}元现金券。"
	 */
	public static final String MSG0307 = "购买现金券 - 欢迎使用北京出行现金券服务，充值帐户支付{money}元，获得{coupon}元现金券。";
	/**
	 * "购买现金券-欢迎使用北京出行现金券服务，充值帐户支付{money}元，获得{coupon}元现金券。"
	 */
	public static final String MSG0308 = "购买套餐 - 欢迎使用北京出行现超值套餐服务。";
	/**
	 * 充值-通过支付宝充值，金额为{0}元
	 */
	public static final String MSG0309 = "充值 - 通过 支付宝 充值，金额为 {money} 元。";
	/**
	 * 充值 - 通过 {bank} 充值，金额为 {money} 元。
	 */
	public static final String MSG0313 = "充值 - 通过 {bank} 充值，金额为 {money} 元。";
	/**
	 *  "退余额 - 你支付宝交易号 {trade} 有退款，金额为 {money} 元，可能需要1-3个工作日完成，请登录你的支付宝账号进行查看。"
	 */
	public static final String MSG0310 = "退余额 - 你支付宝交易号 {trade} 有退款，金额为 {money} 元，可能需要1-3个工作日完成，请登录你的支付宝账号进行查看。";
	/**
	 *  "退余额 - 你的 {bank} 有退款，金额为 {money} 元，可能需要1-3个工作日完成。"
	 */
	public static final String MSG0311 = "退余额 - 你的 {bank} 银行卡有退款，金额为 {money} 元，可能需要1-3个工作日完成，请登录网银查看。";
	/**
	 * 邀请好友 - 欢迎加入北京出行，你的推荐人手机号为的{mobile}，获得{point}积分。
	 */
	public static final String MSG0312 = "欢迎加入北京出行，你的推荐人手机号为的{mobile}，获得{point}积分。";
	/**
	 * 企业添加人员信息，自动生成六位数字密码，发送给相应的人员--欢迎使用公务公车分时租赁系统，您的登录名称为{mobile}，密码为{code}
	 */
	public static final String MSG0901 = "欢迎使用公务车分时租赁系统，你的系统登录名为{mobile}，密码为{code}。";
	/**
	 * 重置用户密码，自动生成六位数字密码，发送给相应的人员--欢迎使用公务公车分时租赁系统，您的密码已经重置，新密码为{code}
	 */
	public static final String MSG0902 = "欢迎使用北京出行，您的密码已经重置，新密码为{code}。";
}
