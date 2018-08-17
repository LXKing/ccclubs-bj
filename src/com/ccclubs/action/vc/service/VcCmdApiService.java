package com.ccclubs.action.vc.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.ccclubs.action.vc.constant.VcApiCons;
import com.ccclubs.action.vc.dto.BindVehicleInput;
import com.ccclubs.action.vc.dto.IssueAuthOrderInput;
import com.ccclubs.action.vc.dto.UnBindVehicleInput;
import com.ccclubs.action.vc.dto.VehicleRegisterInput;
import com.ccclubs.action.vc.enums.VcCmdEnum;
import com.ccclubs.action.vc.enums.VcColorMap;
import com.ccclubs.helper.APICallHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.util.DateUtil;
import com.ccclubs.util.mq.entity.OrderDownStream;
import com.lazy3q.web.helper.$;
/**
 * 车机中心api服务
 * @author Administrator
 * @date 2018-8-3
 */
public class VcCmdApiService {
   
    private ICsCarService csCarService;

    /*************************************************
     * 在配置文件中注入的配置
     *************************************************
     */
    /**
     * api域名
     */
    private static final String API_HOST = $.config("vc_api_host");
    /**
     * api端口
     */
    private static final Integer API_PORT = $.config("vc_api_port")==null?null:Integer.parseInt($.config("vc_api_port"));
    /**
     * api域名
     */
    private static final String CMD_HOST = $.config("vc_cmd_host");
    /**
     * api端口
     */
    private static final Integer CMD_PORT = $.config("vc_cmd_port")==null?null:Integer.parseInt($.config("vc_cmd_port"));
    /**
     * api调用者身份appid
     */
    private static final String AUTH_APPID = $.config("vc_api_auth_appid");
    /**
     * api调用者身份appkey
     */
    private static final String AUTH_APP_KEY = $.config("vc_api_auth_appkey");
    /**
     * 日期工具类
     * （竟然...）
     */
    private static final DateUtil DATE_UTIL = new DateUtil();
    
    /**
     * 发送控制指令
     * @param cmdEnum       指令类型
     * @param vin           车辆vin码
     * @return              指令ID
     */
    public Long sendControlCmd(VcCmdEnum cmdEnum, String vin) {
        Objects.requireNonNull(cmdEnum);
        Objects.requireNonNull(vin);
        
        // 请求参数
        JSONObject paramJson = new JSONObject();
        paramJson.put("vin", vin);
        paramJson.put("cmd", cmdEnum.getVcCmdCode());
//        paramJson.put("resultType", 1);// 默认采用异步方式调用，且当前业务平台需要用异步方式
        
        // 请求url
        String reqUrl = apiUrl(VcApiCons.VC_API_CMD_SIMPLE, true);
        String params = paramJson.toJSONString();
        // 组装post请求
        JSONObject apiResult = dealApiPost("发送控制指令", reqUrl, params);
        
        if (VcApiCons.VC_API_CODE_SUCCESS == apiResult.getIntValue("code")) {
            return apiResult.getJSONObject("data").getLong("messageId");
        } else {
            // {"message":"Terminal is not online at current time","traceId":"150dd8e7-4353-493a-b127-f66a60c066e4","code":101024}
            $.trace("调用车机中心简单指令失败： url=[" + reqUrl + "], resp=[" + apiResult.toJSONString() + "]");
            return null;
        }
    }
    
    /**
     * 发送车辆注册信息，调用车机中心车辆注册
     * @param newCar
     * @return          返回的异常信息
     */
    public boolean sendCarRegister(CsCar newCar) {
        Objects.requireNonNull(newCar);
        
        VehicleRegisterInput registerInput = new VehicleRegisterInput();
        // 车牌号必填
        registerInput.setCsvCarNo(newCar.getCscNumber());
        // vin码必填
        registerInput.setCsvVin(newCar.getCscVin());
        // 发动机号必填
        registerInput.setCsvEngineNo(newCar.getCscEngineNo());
        // 合格证号必填
        registerInput.setCsvCertific(newCar.getCscCertific());
        // 映射车身颜色必填
        VcColorMap colorMap = (null==newCar.getCscColor())?null:VcColorMap.getByBjColorCode(newCar.getCscColor() & 0xFFFF);
        if (null == colorMap) {
            // 默认为黑色
            registerInput.setCsvColorCode((byte) 0);
        } else {
            registerInput.setCsvColorCode((byte)(colorMap.getVcColorCode() & 0xFF));
        }
        registerInput.setCsvInteriorColorCode(null);
        // 车型标志(车型备案号)必填
        if (null == newCar.get$cscModel().getCscmFlag()) {
            $.trace("调用车机中心api发送车辆注册失败： 车型备案号不能为空");
            return false;
        }
        registerInput.setCsvModel(newCar.get$cscModel().getCscmFlag());
        // 出厂日期（北京出行的购车日期-车机中心的出厂日期）必填
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null != newCar.getCscBuyDate()) {
            registerInput.setCsvProdDate(sdf.format(newCar.getCscBuyDate()));
        } else {
            registerInput.setCsvProdDate(sdf.format(new Date()));
        }
        // 终端序列号（在北京业务平台中必须存在）
        registerInput.setTeNo(newCar.getCscTerNo());
        
        /**
         * 北京业务平台车型备案号和车型代码没有，填充空字符串
         */
        registerInput.setCsvModelCodeFull("");
        registerInput.setCsvModelCodeSimple("");
        List<VehicleRegisterInput> inputs = new ArrayList<>();
        inputs.add(registerInput);
        JSONObject paraJson = new JSONObject();
        paraJson.put("inputs", inputs);
        
        String reqUrl = apiUrl(VcApiCons.VC_API_CAR_REGISTER, false);
        String params = paraJson.toJSONString();
        
        JSONObject apiResult = dealApiPost("发送车辆注册", reqUrl, params);
        if (VcApiCons.VC_API_CODE_SUCCESS == apiResult.getIntValue("code")) {
            if (apiResult.getJSONObject("data").getJSONArray("success").size() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            $.trace("调用车机中心api发送车辆注册失败： url=[" + reqUrl + "], resp=[" + apiResult.toJSONString() + "]");
            return false;
        }
    }
    
    /**
     * 调用车机中心带认证订单下发接口
     * @param downStream    下发的订单数据
     * @param vin           车辆vin码（必填）
     * @return              下发的指令ID（用于接收结果回调）
     */
    public boolean publishCarOrderInAuth(OrderDownStream downStream, String vin) {
        Objects.requireNonNull(vin);
        
        IssueAuthOrderInput apiOrderInput = new IssueAuthOrderInput();
        apiOrderInput.setVin(vin);
        apiOrderInput.setOrderId(downStream.mOrderId);
        // 时间格式化
        apiOrderInput.setStartTime(DATE_UTIL.longToString((long)downStream.mStartTime, null));// 会不会溢出?
        apiOrderInput.setEndTime(DATE_UTIL.longToString((long)downStream.mEndTime, null));
        apiOrderInput.setRfid(downStream.mRfid);
        apiOrderInput.setAuthCode(downStream.mCode);
        
        String reqUrl = apiUrl(VcApiCons.VC_API_CAR_ORDER, true);
        String params = JSON.toJSONString(apiOrderInput);
        
        JSONObject apiResult = dealApiPost("订单下发", reqUrl, params);
        if (VcApiCons.VC_API_CODE_SUCCESS == apiResult.getIntValue("code")) {
            return true;
        } else {
            $.trace("调用车机中心带认证订单下发接口失败： url=[" + reqUrl + "], resp=[" + apiResult.toJSONString() + "]");
            return false;
        }
    }
    
    /**
     * 调用车机中心车辆绑定终端接口
     * @param carInfo       车辆信息：必须包含vin和终端序列号
     * @return              true: 绑定成功，false：绑定失败
     */
    public boolean carBindTerminal(CsCar carInfo) {
        Objects.requireNonNull(carInfo.getCscTerNo());
        Objects.requireNonNull(carInfo.getCscVin());
        
        BindVehicleInput bindInput = new BindVehicleInput();
        bindInput.setTeNo(carInfo.getCscTerNo());
        bindInput.setVin(carInfo.getCscVin());
        
        String reqUrl = apiUrl(VcApiCons.VC_API_CAR_BIND_TER, false);
        String params = JSON.toJSONString(bindInput);
        
        JSONObject apiResult = dealApiPost("车辆绑定终端", reqUrl, params);
        if (VcApiCons.VC_API_CODE_SUCCESS == apiResult.getIntValue("code")) {
            return true;
        } else {
            $.trace("调用车机中心车辆绑定终端接口失败： url=[" + reqUrl + "], resp=[" + apiResult.toJSONString() + "]");
            return false;
        }
    }
    
    /**
     * 车辆与终端取消绑定
     * @param carInfo
     * @return          true: 绑定成功，false：绑定失败
     */
    public boolean carUnbindTerminal(CsCar carInfo) {
        Objects.requireNonNull(carInfo.getCscTerNo());
        Objects.requireNonNull(carInfo.getCscVin());
        
        UnBindVehicleInput unbindInput = new UnBindVehicleInput();
        unbindInput.setTeNo(carInfo.getCscTerNo());
        unbindInput.setVin(carInfo.getCscVin());
        
        String reqUrl = apiUrl(VcApiCons.VC_API_CAR_UNBIND_TER, false);
        String params = JSON.toJSONString(unbindInput);
        
        JSONObject apiResult = dealApiPost("车辆与终端解除绑定", reqUrl, params);
        if (VcApiCons.VC_API_CODE_SUCCESS == apiResult.getIntValue("code")) {
            return true;
        } else {
            $.trace("调用车机中心车辆取消绑定终端接口失败： url=[" + reqUrl + "], resp=[" + apiResult.toJSONString() + "]");
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 构造请求url
     * @param uri
     * @param cmd   是否为指令下发
     * @return
     */
    private static String apiUrl(String uri, boolean cmd) {
        StringBuilder urlSb = new StringBuilder();
        if (cmd) {
            urlSb.append("http").append("://").append(CMD_HOST);
            if (null != CMD_PORT) {
                urlSb.append(":").append(CMD_PORT);
            }
            urlSb.append(uri).toString();
        } else {
            urlSb.append("http").append("://").append(API_HOST);
            if (null != API_PORT) {
                urlSb.append(":").append(API_PORT);
            }
            urlSb.append(uri).toString();
        }
        return urlSb.toString();
    }
    
    /**
     * 构造api请求头
     * @param paramJsonStr
     * @return
     */
    private static Map<String, String> apiHeader(String paramJsonStr) {
        Map<String, String> headers = new HashMap<>();
        headers.put("appId", AUTH_APPID);
        headers.put("sign", sign(paramJsonStr));
        
        return headers;
    }
    
    /**
     * 构造请求签名
     * @param paramJsonStr
     * @return
     */
    private static String sign(String paramJsonStr) {
        String md5Hex = DigestUtils.md5Hex(paramJsonStr);
        String sign = HmacUtils.hmacSha1Hex(AUTH_APP_KEY, md5Hex);
        return sign;
    }
    
    /**
     * 统一处理post发送
     * @param cmd       请求描述
     * @param url       请求url
     * @param params    请求参数json字符串
     * @return          请求结果
     */
    private static JSONObject dealApiPost(String reqDes, String url, String params) {
        // 发送post请求
        String apiResult = APICallHelper.doPostJSON(url, params, apiHeader(params));
        
        // ---测试,待删除---
//        String apiResult = "{\r\n" + 
//                "success: true,\r\n" + 
//                "code: 100000,\r\n" + 
//                "data: {\"messageId\": 222}" + 
//                "}";
        // ---测试,待删除---
        
        // 日志记录
        StringBuilder traceSb = new StringBuilder();
        traceSb.append(reqDes).append(": ")
            .append("url=[").append(url).append("]").append(",")
            .append("params=[").append(params).append("]").append(",")
            .append("resp=[").append(apiResult).append("]");
        $.trace(traceSb.toString());
        JSONObject respJson = null;
        try {
            respJson = JSON.parseObject(apiResult);
        } catch (Exception e) {
            $.trace("车机中心返回的结果异常，json序列化失败: apiResult=" + apiResult);
            throw new IllegalStateException("车机中心返回的结果异常，json序列化失败");
        }
        return respJson;
    }

    public ICsCarService getCsCarService() {
        return csCarService;
    }

    public void setCsCarService(ICsCarService csCarService) {
        this.csCarService = csCarService;
    }
    
    /**
     * 为
     *  BigDecimal
     *  Integer
     * 提供为null时的null返回
     * @param value
     * @return
     */
    private String emptyReturnForValue(Object value) {
        if (null == value) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal)value).toPlainString();
        }
        if (value instanceof Integer) {
            return ""+value;
        }
        return null;
    }
    
    public static void main(String[] args) {
        new VcCmdApiService().sendCarRegister(null);
    }
    
    
}
