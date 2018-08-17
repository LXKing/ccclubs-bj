package com.ccclubs.action.vc.util;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.ccclubs.action.receiver.VCOnsReceiver;
import com.ccclubs.action.vc.constant.VcApiCons;
import com.ccclubs.action.vc.dto.VcSimpleCmdResult;
import com.lazy3q.web.helper.$;

public class VcUtil {
    
    public static void testRemote() {
        VCOnsReceiver receiver = $.getBean("vcOnsReceiver");
        VcSimpleCmdResult result = new VcSimpleCmdResult();
        result.setCode(VcApiCons.VC_CMD_CODE_SUCCESS);
        result.setMessage("操作成功");
        result.setSuccess(true);
        result.setMessageId(4124);
        
        Message mqMessage = new Message();
        mqMessage.setTag("terminal_remote_16");
        mqMessage.setBody(JSON.toJSONBytes(result));
        receiver.consume(mqMessage, null);
    }
    
//    public static void testBackCar() {
//        VCOnsReceiver receiver = $.getBean("vcOnsReceiver");
//        VcSimpleCmdResult result = new VcSimpleCmdResult();
//        result.setCode(VcApiCons.VC_CMD_CODE_SUCCESS);
//        result.setMessage("操作成功");
//        result.setSuccess(true);
//        result.setMessageId(4124);
//        
//        Message mqMessage = new Message();
//        mqMessage.setTag("terminal_remote_16");
//        mqMessage.setBody(JSON.toJSONBytes(result));
//        receiver.consume(mqMessage, null);
//    }

}
