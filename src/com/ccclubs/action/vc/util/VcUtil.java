package com.ccclubs.action.vc.util;

import java.util.Date;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.ccclubs.action.receiver.VCOnsReceiver;
import com.ccclubs.action.vc.constant.VcApiCons;
import com.ccclubs.action.vc.dto.VcSimpleCmdResult;
import com.ccclubs.config.SYSTEM;
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
    
    /**
     * 【Add】2018-9-17 
     * 如果更新订单取还车时间时，发现应答中时间为0则取当前时间替换
     * @param carOrderTime  应答指令中获取的取车还车时间
     * @return
     */
    public static Date getOrderTimeForNowIfZero(int carOrderTime) {
        if (0 == carOrderTime) {
            return new Date();
        } else {
            return new Date(carOrderTime * 1000L + SYSTEM.MACHINE_TIME);
        }
    }

}
