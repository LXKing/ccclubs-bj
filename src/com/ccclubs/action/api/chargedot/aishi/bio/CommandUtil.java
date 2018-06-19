package com.ccclubs.action.api.chargedot.aishi.bio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * 指令
 * 
 * @author zhangjian
 *
 */
public class CommandUtil {
	
	/**
	 * 设备编号
	 * 
	 */
	private String did;	
	
	/**
	 * 客户端ID
	 * 
	 */
	private String cid;

	/**
	 * 客户端密码
	 * 
	 */
	private String password;
	
	/**
	 * 单位
	 * 
	 */
	private String company;
	
	/**
	 * 模式
	 * 
	 */
	private String mode;
	
	/**
	 * 权限认证码
	 * 
	 */
	private String aut;
	
	
	public CommandUtil(){
		
	}

	final static String default_company = "Com00001";
	final static String default_aut = "aut";
	final static String default_mode = "1";
	
	/**
	 * 登陆指令
	 * @param user
	 * @param password
	 * @return
	 */
	public String LoginCmd(String user, String password){
		Map<String, Object> command = new HashMap<String, Object>();
		command.put("cmd", "login");
		command.put("user",  user);
		command.put("password",  password);
		command.put("company",  StringUtils.isEmpty(getCompany()) ? default_company : getCompany());
		JSONObject jsonObj = JSONObject.fromObject(command);
		return jsonObj.toString();
	}
	
	/**
	 * 绑定指令
	 * @param did
	 * @param user
	 * @param tok
	 * @return
	 */
	public String JoinCmd(String did, String cid, String user){
		Map<String, Object> command = new HashMap<String, Object>();
		command.put("did", did);
		command.put("cmd", "join");
		command.put("aut",  StringUtils.isEmpty(getAut()) ? default_aut : getMode());
		command.put("cid",  cid);
		command.put("user",  user);
		command.put("mode", StringUtils.isEmpty(getMode()) ? default_mode : getMode());
		JSONObject jsonObj = JSONObject.fromObject(command);
		return jsonObj.toString();
	}
	
	/**
	 * 充电查询指令
	 * @param tok
	 * @return
	 */
	public String reqEleCmd(){
		Map<String, Object> command = new HashMap<String, Object>();
		command.put("cmd", "reqele");
		JSONObject jsonObj = JSONObject.fromObject(command);
		return jsonObj.toString();
	}
	
	/**
	 * 查询状态指令
	 * @param did
	 * @param tok
	 * @return
	 */
	public String reqStaCmd(String did){
		Map<String, Object> command = new HashMap<String, Object>();
		command.put("cmd", "reqsta");
		command.put("did", did);
		JSONObject jsonObj = JSONObject.fromObject(command);
		return jsonObj.toString();
	}
	
	/**
	 * 预约充电指令
	 * @return
	 */
	public String TimingCmd(){
		Map<String, Object> command = new HashMap<String, Object>();
		command.put("cmd", "timing");
		command.put("timing", "1417425394");
		JSONObject jsonObj = JSONObject.fromObject(command);
		return jsonObj.toString(); 
	}
		
	public static final String LOGIN_SUCCESS_TEXT = "登陆成功";
	
	/**
	 * 是否登陆成功
	 * @param r
	 * @return
	 */
	public static boolean isLogin(Result r){
		if(r!=null && r.getText().equals(LOGIN_SUCCESS_TEXT)){
			return true;
		}
		return false;
	}
	
	/**
	 * 消息读取
	 * @param message
	 */
	public static List<Result> read(String data){
		
		List<Result> value = new ArrayList<Result>();
		String[] messages = data.split("[{]");
		
		for(String message : messages){
			if(message.trim().equals(""))continue;
			message = "{"+message;
			
			Result r = new Result();
			r.setResult(message);
	
			String prop = null;
			JSONObject JSON = null;
			if(message.contains("loginack")){
				r.setCmdType(RespType.LOGIN);
				prop = "result:";
	     		String code = message.substring(message.indexOf(prop)+prop.length(), message.indexOf("}")); 
	     		r.setCode(Integer.valueOf(code));
	     		switch(r.getCode()){
	     			case 1:
	     				r.setText(LOGIN_SUCCESS_TEXT);
	     				break;
	     			default:
	     				r.setText("登陆失败");
	     				break;
	     		}
			}else if(message.contains("rescmd")){
				 JSONObject jsonObj = JSONObject.fromObject(message);
				 r.setCode(jsonObj.getInt("rescmd"));
				 switch(r.getCode()){
					case 31000:
							r.setCmdType(RespType.CHARGEPAUSE);
							r.setText("暂停充电成功");
						 break;
					case 32000:
							r.setCmdType(RespType.CHARGEPAUSE);
							r.setText("暂停充电失败");
						break;
				 	case 31001:
				 			r.setCmdType(RespType.TIMING);
				 			r.setText("预约充电成功");
				 		break;
				 	case 32001:
					 		r.setCmdType(RespType.TIMING);
					 		r.setText("预约充电失败");
				 		break;
				 	case 31004:
					 		r.setCmdType(RespType.CANCELTIMING);
					 		r.setText("取消预约充电成功");
				 		break;
				 	case 32004:
					 		r.setCmdType(RespType.CANCELTIMING);
					 		r.setText("取消预约充电失败");
				 		break;
				 	case 31008:
					 		r.setCmdType(RespType.JOIN);
					 		r.setText("绑定设备成功");
				 		break;
				 	case 32008:
					 		r.setCmdType(RespType.JOIN);
					 		r.setText("绑定设备失败");
				 		break;
				 }
				 
			 }else if(message.contains("ressta")){
				 Map<String, Object> map = parseLackJson(message);
				 r.setCmdType(RespType.QUERYSTU);
		     	 r.setCode(Integer.valueOf(map.get("sta").toString()));
		     	 switch(r.getCode()){
		     			case 20301:
		     				r.setText("设备断网");
		     				break;
		     			case 20000:
		     				r.setText("设备空闲");
		     				break;
		     			case 20001:
		     				r.setText("设备占用");
		     				break;
		     			case 20010:
		     				r.setText("设备报错");
		     				break;
		     			case 20201:
		     				r.setText("设备充电中");
		     				break;
		     			case 20202:
		     				r.setText("设备预约中");
		     				break;
		     			case 20203:
		     				r.setText("设备充电结束");
		     				break;
		     			default:
		     				break;
		     				
	     		 }
			 }else if(message.contains("kick")){
				 r.setCmdType(RespType.KICK);
				 r.setText("操作超时");
			 }else if(message.contains("resele")){
				 r.setCmdType(RespType.QUERYELE);
			 }else{
				 r.setCmdType(RespType.UNKNOW);
				 r.setText("未知的返回类型");
			 }
			
			value.add(r);
		}
		return value;
	}
	
	/**
	 * 解析缺少引号的JSON数据
	 * @param message
	 * @return
	 */
	public static Map<String, Object> parseLackJson(String message){
		Map<String, Object> result = new HashMap<String, Object>();
		if(message == null || message.indexOf(",") == -1){
			return result;
		}
		
		message = "{cmd:ressta,did:TEST01A11404P0000104,sta:20301,ele:0,time:0}";
		String[] strArr = message.split(",");
		for(String str : strArr){
			String[] kvArr = str.split(":");
			result.put(kvArr[0], kvArr[1]);
		}
		return result;
	}
	
	/**
	 * 是否接收到结束
	 * @param result
	 * @param endType
	 * @return
	 */
	public static boolean isEnd(List<Result> result, RespType endCmdType){
		if(result!=null){
			for(Result r : result){
				if(r.getCmdType() == endCmdType){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获取操作类型
	 * @param message
	 * @return
	 */
	public static RespType getResultType(String message){
		 if(message.contains("loginack")){
			 return RespType.LOGIN;
		 }else if(message.contains("rescmd")){
			 JSONObject jsonObj = JSONObject.fromObject(message);
			 Object cmd = jsonObj.get("rescmd");
			 if(cmd.toString().equals("31001") || cmd.toString().equals("32001")){		//预约充电
				 return RespType.TIMING;
			 }else if(cmd.toString().equals("31004") || cmd.toString().equals("32004")){	//取消预约
				 return RespType.CANCELTIMING;
			 }else if(cmd.toString().equals("31008") || cmd.toString().equals("32008")){	//绑定设备
				 return RespType.JOIN;
			 }
		 }else if(message.contains("ressta")){
			 return RespType.QUERYSTU;
		 }else if(message.contains("kick")){
			 return RespType.KICK;
		 }else if(message.contains("resele")){
			 return RespType.QUERYELE;
		 }
		 return RespType.UNKNOW;
	}
	
	public String getDid() {
		return did;
	}

	public CommandUtil setDid(String did) {
		this.did = did;
		return this;
	}

	public String getCid() {
		return cid;
	}

	public CommandUtil setCid(String cid) {
		this.cid = cid;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public CommandUtil setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getCompany() {
		return company;
	}

	public CommandUtil setCompany(String company) {
		this.company = company;
		return this;
	}

	public String getMode() {
		return mode;
	}

	public CommandUtil setMode(String mode) {
		this.mode = mode;
		return this;
	}

	public String getAut() {
		return aut;
	}

	public void setAut(String aut) {
		this.aut = aut;
	}
	
}
