package com.ccclubs.action.app.official.event;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.lang3.StringUtils;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.ccclubs.action.vc.enums.MsgTagEnum;
import com.ccclubs.model.CsCar;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.TerminalStatus;
import com.ccclubs.util.mq.entity.CarStatusIV;
import com.lazy3q.web.helper.$;

/**
 * 从ons接收车辆状态数据
 * @author Administrator
 *
 */
// TODO 在app模块下注入
public class CarStatusOnsProducer implements MessageListener {
    // TODO 未注入
    ICsCarService csCarService;
    
    /**
     * 车辆状态消息队列
     */
    public static BlockingQueue<ICarStatus> carStatusQueue;

    @Override
    public Action consume(Message msg, ConsumeContext ctx) {
        String tag = msg.getTag();
        byte[] msgBody = msg.getBody();
        // 车机中心推送给业务平台的是json字节数组
        JSONObject msgBodyJosn = (JSONObject) JSONObject.parse(msgBody);
        
        System.out.println("received msg:" + msgBodyJosn.toJSONString());
       
        MsgTagEnum msgTag = MsgTagEnum.getByTag(tag);
        if (null == msgTag) {
            $.trace("状态数据异常：不能处理的消息tag： " + tag);
            return Action.CommitMessage;
        }
        switch (msgTag) {
            case MQTT_STATUS:// 状态数据
                TerminalStatus terminalStatus = msgBodyJosn.toJavaObject(TerminalStatus.class);
                // 终端序列号
                final String terNo = terminalStatus.getCssNumber();
                if (StringUtils.isEmpty(terNo)) {
                    $.trace("状态数据异常：没有车机终端序列号cssNumber");
                    return Action.CommitMessage;
                }
                // 根据终端序列号查找车信息
                Map<String, Object> carQueryMap = new HashMap<>();
                // 1:上线
                carQueryMap.put("cscStatus", (short)1);
                carQueryMap.put("cscTerNo", terNo);
                CsCar carInfo = csCarService.getCsCar(carQueryMap);
                if (null == carInfo) {
                    $.trace("状态数据异常：该终端序列号 [" + terNo + "] 找不到对应的车辆信息 ");
                    return Action.CommitMessage;
                } else {
                    //  绑定平台为车机中心                                                                     
                    if (1 != carInfo.getCscBindPlatform()) {
                        $.trace("该车辆不是绑定车机中心[" + carInfo.getCscBindPlatform() + "]");
                        return Action.CommitMessage;
                    }
                }
                // 车牌号
                final String carNo = carInfo.getCscNumber();
                $.trace("收到来自 " + carNo + "的状态数据");
     
                // 将车机中心的状态数据转换成北京出行的状态数据
                CarStatusIV carStatus = convertToBJCsState(terminalStatus, carNo);
                if (null != carStatusQueue) {
                    try {
                        carStatusQueue.put(carStatus);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        $.trace("车辆[" + carNo + "]" + "状态数据加入队列异常：" + e.getMessage());
                    }
                }
                break;
                default:
                    break;
        }
        /**
         * 最终标记：消息已消费
         */
        return Action.CommitMessage;
    }
    
    
    private CarStatusIV convertToBJCsState(TerminalStatus terminalStatus, String carNo) {
        CarStatusIV newCarStatus = new CarStatusIV();
        newCarStatus.mSOC = (byte) (terminalStatus.getCssEvBattery() & 0xFF);
        newCarStatus.mCarNum = carNo;
        return newCarStatus;
    }

}
