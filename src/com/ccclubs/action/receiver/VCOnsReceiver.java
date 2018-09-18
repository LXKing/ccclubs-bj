package com.ccclubs.action.receiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import org.apache.commons.lang3.StringUtils;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.parser.Feature;
import com.ccclubs.action.vc.constant.VcApiCons;
import com.ccclubs.action.vc.dto.VcSimpleCmdResult;
import com.ccclubs.action.vc.enums.MsgTagEnum;
import com.ccclubs.action.vc.enums.VcCmdEnum;
import com.ccclubs.action.vc.service.CarOrderScheduledPoolExecuter;
import com.ccclubs.action.vc.service.VcCmdApiService;
import com.ccclubs.action.vc.util.VcUtil;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsHistoryState;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsState;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsHistoryStateService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsRemoteService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.TerminalStatus;
import com.ccclubs.util.mq.entity.FurtherCar;
import com.ccclubs.util.mq.entity.OrderUpStream;
import com.ccclubs.util.mq.entity.RemoteOption;
import com.ccclubs.util.mq.entity.TakeCar;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 接收车机中心ons发布的消息
 * @author yeanzhi
 * 2018-8-2
 */
public class VCOnsReceiver implements MessageListener {
    ICsStateService csStateService;
    
    ICsHistoryStateService csHistoryStateService;
    
    CarOrderScheduledPoolExecuter carOrderScheduledPoolExecuter;
    
    ICsOrderService csOrderService;
    
    ICsCarService csCarService;
    
    VcCmdApiService vcCmdApiService;
    
    ICsRemoteService csRemoteService;
    
    static {
        System.out.println("----------------------ONS Server Starting--------------------------");
    }
    
    /**
     * 订单同步操作间隔时间（10秒）
     */
    private static final int ORDER_SYN_SCHEDULE_TIME = 10 * 1000;
    
    /*订单表名称*/
    private final static String ORDER_TABLE = "cs_order";
    /*远程指令记录表名称*/
    private final static String REMOTE_TABLE = "cs_remote";
    
    /**
     * 一系列魔法数值
     */
    private static Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
    private final static long ORDER_STATUS_RETURN = 2l;
    private final static long ORDER_STATUS_PEND = 5l;
    private final static byte ORDER_FURTHER_FUC_CODE_O = 0x42; // 汽油车
    private final static byte ORDER_FURTHER_FUC_CODE_E = 0x64; // 电动车

    /**
     * 日志
     */
    private static String LOG_PATH = null;
    
    BlockingQueue<ICarStatus> carStatusQueue;

    /**
     * 消费ons消息
     */
    @Override
    public Action consume(Message msg, ConsumeContext ctx) {
        String topic = msg.getTag();
        String tag = msg.getTag();
        byte[] msgBody = msg.getBody();
        String msgStr = new String(msgBody, Charset.forName("UTF-8"));
        $.trace("ONS EVENT: ------------------------------------------------------------");
        $.trace("ONS TAG: " + tag);
        $.trace("ONS EVENT: ------------------------------------------------------------");
        System.out.println("tag: " + tag);
        System.out.println("received msg:" + msgStr);
        
        MsgTagEnum msgTag = MsgTagEnum.getByTag(tag);
        if (null == msgTag) {
            // TODO 不能处理的消息tag
            $.trace("状态数据异常：不能处理的消息tag： " + tag);
            return Action.CommitMessage;
        }
        switch (msgTag) {
            case MQTT_STATUS:// 状态数据
                JSONObject msgBodyJosn = (JSONObject) JSONObject.parse(msgBody);
                TerminalStatus terminalStatus = JSON.parseObject(msgBodyJosn.toJSONString(), TerminalStatus.class);
                // 终端序列号
                final String terNo = terminalStatus.getCssNumber();
                if (StringUtils.isEmpty(terNo)) {
                    $.trace("状态数据异常：没有车机终端序列号cssNumber");
                    return Action.CommitMessage;
                }
                // 根据终端序列号查找车信息
                Map<String, Object> carQueryMap = new HashMap<>();
                // 1:上线
//                carQueryMap.put("cscStatus", (short)1);
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
                $.trace("收到ons中来自 " + carNo + "的状态数据");
                // 城市
                String carHost = String.valueOf(carInfo.getCscHost());
     
                CsState currentCsState = convertToCsState(carNo, terminalStatus, carHost);
                // 更新状态数据
                upSertCsStateToDB(currentCsState);
                CsHistoryState currHistoryState = convertToCsHistoryState(carNo, terminalStatus, carHost);
                // 新增历史状态数据
                saveCsStateHistoryToDB(currHistoryState);
                /**
                 * 【延迟任务】矫正订单状态
                 */
                correctionOrderStatus(currentCsState, carInfo);
                break;
            case MQTT_REMOTE:// 下行命令结果返回
                Object oj = JSON.parse(msgBody, new Feature[] {});
                JSONObject remoteJson = JSONObject.parseObject(oj.toString());
               
                VcSimpleCmdResult<?> cmdResult = remoteJson.toJavaObject(VcSimpleCmdResult.class);
                Long messageId = cmdResult.getMessageId();
                $.trace("remote messageId: " + messageId);
                Map<String, Object> remoteQuery = new HashMap<>();
                /**
                 * 根据唯一的messageId获取远程指令记录
                 * 这里不一定能获取到?
                 */
                remoteQuery.put("csrMessageId", messageId);
                CsRemote cmdRemote = csRemoteService.getCsRemote(remoteQuery);
                if (null == cmdRemote) {
                    throw new IllegalArgumentException("找不到对应的远程指令记录： messageId=" + messageId);
                }
                cmdRemote.setCsrUpdateTime(new Date());
                if (VcApiCons.VC_CMD_CODE_SUCCESS == cmdResult.getCode()) {
                    // 操作成功
                    cmdRemote.setCsrStatus((short) 1);
                } else if (VcApiCons.VC_CMD_CODE_FAIL == cmdResult.getCode()) {
                    // 操作失败
                    cmdRemote.setCsrStatus((short) 2);
                    // 记录失败原因
                    cmdRemote.setCsrCode(cmdResult.getMessage());
                }
                // 更新指令执行结果
                csRemoteService.updateCsRemote$NotNull(cmdRemote);
                writeLog("收到来自 车牌号：" + cmdRemote.getCsrNumber() + "，远程操作回复");
                break;
            case MQTT_TAKE_CAR_ACK:
                TakeCar takeCar = null;
                try {
                    takeCar = TakeCar.readObject(msgBody, TakeCar.class);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    $.trace("take car translate fail");
                }
                $.trace("takeCar: " + takeCar);
                if (takeCar != null && takeCar.mFucCode == MsgTagEnum.MQTT_TAKE_CAR_ACK.getHex()) {
                    updateTakeCarForOns(takeCar);
                }
                break;
            case MQTT_FURTHER_CAR_ACK_V1:
            case MQTT_FURTHER_CAR_ACK_V2:
                $.trace("MQTT_FURTHER_CAR_ACK ---------");
                FurtherCar furtherCar = null;
                try {
                    furtherCar = FurtherCar.readObject(msgBody, FurtherCar.class);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    $.trace("back car translate fail");
                }
                if (furtherCar != null) {
                    $.trace("furtherCar: " + furtherCar);
                    updateFurtherCarForOns(furtherCar);
                }
                break;
            case MQTT_ORDER_ACK:
                // 终端接收到订单后，终端主动上报一个应答信息
                // 订单应答功能码
                byte funCode = 0x44;
                OrderUpStream orderUpStream = null;
                try {
                    orderUpStream = OrderUpStream.readObject(
                            msg.getBody(), OrderUpStream.class);
                } catch (Exception e) {
                    $.trace("订单应答序列化失败： " + e.getMessage());
                }
                if (orderUpStream != null
                        && orderUpStream.mFucCode == funCode) {
                    OrderInfoReceiverThread.removeCachedOrderReult(orderUpStream.mCarNum, orderUpStream.mOrderId, orderUpStream.mFucCode);
                }
                break;
                default:
                    $.trace("error tag： " + msgTag);
                    break;
        }
        /**
         * 最终标记：消息已消费
         */
        return Action.CommitMessage;
    }
    
    /**
     * 更新（不存在时新增）车辆实时状态数据
     * @param currentState
     */
    private void upSertCsStateToDB(CsState currentState) {
        // 插入前检查是否已存在，已存在则更新，不存在则新增
        Map<String, Object> csStateQuery = new HashMap<>();
        csStateQuery.put("cssHost", currentState.getCssHost());
        csStateQuery.put("cssNumber", currentState.getCssNumber());
        CsState existedCsState = csStateService.getCsState(csStateQuery);
        if (null == existedCsState) {
            // 不存在：新增一个
            csStateService.saveCsState(currentState);
        } else {
            currentState.setCssId(existedCsState.getCssId());
            // 已存在：更新数据
            csStateService.updateCsState$NotNull(currentState);
        }
    }
    
    /**
     * 新增历史状态数据
     * @param currHistoryState
     */
    private void saveCsStateHistoryToDB(CsHistoryState currHistoryState) {
        csHistoryStateService.saveCsHistoryState(currHistoryState);
    }
    
    /**
     * 将当前状态数据转换成业务平台内的车辆状态数据
     * @param terminalStatus
     * @param host
     * @return
     */
    private CsState convertToCsState(String carNo, TerminalStatus terminalStatus, String host) {
        CsState csState = new CsState();
        // 车牌号
        csState.setCssNumber(carNo);
        csState.setCssHost(Long.valueOf(host));
        // TODO　csCar
        csState.setCssAddTime(new Date());
        // ?
        csState.setCssCurrentTime(new Date(terminalStatus.getCssCurrentTime()));
        csState.setCssRented(emptyReturnForValue(terminalStatus.getCssRented()));
        csState.setCssObdMile(bigDecimalToIntStr(terminalStatus.getCssObdMile()));
        csState.setCssEngineT(emptyReturnForValue(terminalStatus.getCssEngineT()));
        csState.setCssMileage(bigDecimalToIntStr(terminalStatus.getCssMileage()));
        csState.setCssSpeed(bigDecimalToIntStr(terminalStatus.getCssSpeed()));
        csState.setCssMotor(emptyReturnForValue(terminalStatus.getCssMotor()));
        csState.setCssOil(emptyReturnForValue(terminalStatus.getCssOil()));
        csState.setCssPower(emptyReturnForValue(terminalStatus.getCssPower()));
        csState.setCssEvBattery(emptyReturnForValue(terminalStatus.getCssEvBattery()));
        csState.setCssCharging(emptyReturnForValue(terminalStatus.getCssCharging()));
        csState.setCssEndurance(bigDecimalToIntStr(terminalStatus.getCssEndurance()));
        csState.setCssTemperature(emptyReturnForValue(terminalStatus.getCssTemperature()));
        csState.setCssCsq(emptyReturnForValue(terminalStatus.getCssCsq()));
        // 经纬度
        csState.setCssLongitude(emptyReturnForValue(terminalStatus.getCssLongitude()));
        csState.setCssLatitude(emptyReturnForValue(terminalStatus.getCssLatitude()));
        // 订单号
        csState.setCssOrder(terminalStatus.getCssOrder());
        return csState;
    }
    
    /**
     * 将当前状态数据转换成历史状态数据
     * @param carNo             车牌号
     * @param terminalStatus
     * @return
     */
    private CsHistoryState convertToCsHistoryState(String carNo, TerminalStatus terminalStatus, String host) {
        CsHistoryState currHistoryState = new CsHistoryState();
        currHistoryState.setCshsNumber(carNo);
        currHistoryState.cshsHost(Long.valueOf(host));
        // TODO　csCar
        currHistoryState.cshsAddTime(new Date());
        // ?
        currHistoryState.cshsCurrentTime(new Date(terminalStatus.getCssCurrentTime()));
        currHistoryState.cshsRented(emptyReturnForValue(terminalStatus.getCssRented()));
        currHistoryState.cshsObdMile(bigDecimalToIntStr(terminalStatus.getCssObdMile()));
        currHistoryState.cshsEngineT(emptyReturnForValue(terminalStatus.getCssEngineT()));
        currHistoryState.cshsMileage(bigDecimalToIntStr(terminalStatus.getCssMileage()));
        currHistoryState.cshsSpeed(bigDecimalToIntStr(terminalStatus.getCssSpeed()));
        currHistoryState.cshsMotor(emptyReturnForValue(terminalStatus.getCssMotor()));
        currHistoryState.setCshsOil(emptyReturnForValue(terminalStatus.getCssOil()));
        currHistoryState.cshsPower(emptyReturnForValue(terminalStatus.getCssPower()));
        currHistoryState.cshsEvBattery(emptyReturnForValue(terminalStatus.getCssEvBattery()));
        currHistoryState.cshsCharging(emptyReturnForValue(terminalStatus.getCssCharging()));
        currHistoryState.cshsEndurance(bigDecimalToIntStr(terminalStatus.getCssEndurance()));
        currHistoryState.cshsTemperature(emptyReturnForValue(terminalStatus.getCssTemperature()));
        currHistoryState.setCshsCsq(emptyReturnForValue(terminalStatus.getCssCsq()));
        // 经纬度
        currHistoryState.cshsLongitude(emptyReturnForValue(terminalStatus.getCssLongitude()));
        currHistoryState.cshsLatitude(emptyReturnForValue(terminalStatus.getCssLatitude()));
        // 订单号
        currHistoryState.cshsOrder(terminalStatus.getCssOrder());
        
        return currHistoryState;
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
    
    public static String bigDecimalToIntStr(BigDecimal decimal) {
        if (decimal != null) {
            return Integer.toString(decimal.intValue());
        }
        return null;
    }
    
    /**
     * 根据当前状态，矫正订单状态
     *      1、车辆状态 >= 3 时，如果订单状态是 0 ，则将订单状态变更为 1
     *      2、如果车辆上有订单，但是该订单状态是已取消或者已作废，发送初始化命令
     * @param currentCsState
     */
    private void correctionOrderStatus(final CsState currentCsState, final CsCar carInfo) {
        if (currentCsState.getCssOrder() == 0) {
            return;
        }
        
        /**
         * 提交一个订单矫正任务
         */
        carOrderScheduledPoolExecuter.addTask(new Runnable() {
            @Override
            public void run() {
                try {
                    CsOrder csOrder = csOrderService.getCsOrderById(currentCsState.getCssOrder());
                    if (null == csOrder) {
                        $.trace("订单不存在： id=", currentCsState.getCssOrder());
                        return;
                    }
                    Short orderStatus = csOrder.getCsoStatus();
                    if (3 == orderStatus || 4 == orderStatus || 7 == orderStatus) {
                        // 该订单状态是已取消（3）,已完成（4）或者已作废（7），发送初始化命令
                        
                        // 获取vin码
                        String vin = carInfo.getCscVin();
                        if (StringUtils.isEmpty(vin)) {
                            $.trace("车辆[" + carInfo.getCscNumber() + "]下发订单矫正指令时，vin码为空");
                            return;
                        }
                        // 发送初始化命令（初始化命令会初始化订单数据）
                        vcCmdApiService.sendControlCmd(VcCmdEnum.CMD_INIT, vin);
                        writeLog(VCOnsReceiver.class.getResource("/").toURI().getPath() + "RemoteTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt", currentCsState.getCssNumber() + "  " + currentCsState.getCssOrder() + " 车机上的订单状态为已取消或者已作废，发送初始化！");
                        return;
                    }
                    Integer carStatus = Integer.valueOf(currentCsState.getCssRented());
                    if (null != carStatus && carStatus >= 3 && 0 == orderStatus) {
                        // 车辆状态 >= 3 时，如果订单状态是 0 ，则将订单状态变更为 1
                        
                        CsOrder orderUpdate = new CsOrder();
                        orderUpdate.setCsoId(csOrder.getCsoId());
                        orderUpdate.setCsoState((short)2);
                        orderUpdate.setCsoStatus((short)1);
                        csOrderService.updateCsOrder$NotNull(orderUpdate);
                        writeLog(VCOnsReceiver.class.getResource("/").toURI().getPath() + "RemoteTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt", currentCsState.getCssNumber() + " 车机上的状态已经是在使用中，更新已预订状态为使用中");
                    }
                } catch (Throwable e) {
                    writeLog(e.getMessage() + " correctionOrderStatus 更正订单状态出错");
                    e.printStackTrace();
                }
            }
        }, ORDER_SYN_SCHEDULE_TIME);
    }
    
    
    
    
    
    
    
    
    
    // log
    //---------------------------------------------------------------------------------------------
    /**
     * 获取日志路径
     */
    private static void getLogPath() {
        try {
            LOG_PATH = VCOnsReceiver.class.getResource("/").toURI().getPath() + "VCOnsReceiverTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt";
        } catch (Exception ex) {
            System.out.println("获取文件路径时出错:" + ex.getMessage());
        }
    }
    
    /**
     * 写日志
     * 
     * @param content
     */
    private static void writeLog(String content) {
        String timeString = DateHelper.formatDate(new Date(), "HH:mm:ss");
        $.trace(timeString + "   -   " + content);
        // if (logPath == null) {
        getLogPath();
        // }
        if (LOG_PATH != null) {
            writeAppendToFile(LOG_PATH, timeString + "   -   " + content, "UTF-8");
        }
    }

    /**
     * 写日志，指定目录
     * 
     * @param content
     */
    private static void writeLog(String path, String content) {
        String timeString = DateHelper.formatDate(new Date(), "HH:mm:ss");
        $.trace(timeString + "   -   " + content);
        // if (logPath == null) {
        // getLogPath();
        // }
        // if (logPath != null) {
        writeAppendToFile(path, timeString + "   -   " + content, "UTF-8");
        // }
    }

    /**
     * 追加到文件末尾，并且换行
     * 
     * @param fileName
     * @param content
     * @param encode
     */
    private static void writeAppendToFile(String fileName, String content, String encode) {
        if (encode == null)
            encode = "UTF-8";
        FileWriter writer = null;
        try {
            fileName = fileName.replaceAll("\\\\", "/");
            fileName = fileName.replaceAll("//", "/");

            String filePath = fileName.substring(0, fileName.lastIndexOf("/"));

            if (!new File(filePath).exists())
                new File(filePath).mkdirs();

            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(fileName, true);
            writer.write(content + "\r\n");
        } catch (IOException ex) {
            System.out.println("写文件[" + fileName + "]到[" + fileName + "]时出错:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 更新取车信息
     * <p>
     * 根据取车订单号和车牌号，更新实际取车时间
     * </p>
     * <p>
     * 数据库操作
     * </p>
     * 
     * @param takeCar
     */
    private static void updateTakeCarForOns(TakeCar takeCar) {
        try {
            String takeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(VcUtil.getOrderTimeForNowIfZero(takeCar.mTakeTime));
            $.trace("takeTime: " + takeTime);
            
            String strSql = "UPDATE " + ORDER_TABLE
                    + " SET cso_status = 1, cso_take_time = '" + takeTime
                    + "' WHERE cso_id = " + takeCar.mOrderId;
            dao.executeUpdate(strSql);
            writeLog("收到来自 车牌号：" + takeCar.mCarNum + "，订单号：" + takeCar.mOrderId
                    + " 取车回复，取车内容，实际取车时间：" + takeTime);
            // $.trace(strSql);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            writeLog(e.getMessage() + " - updateTakeCar exception");
        }
    }

    /**
     * 更新还车信息
     * <p>
     * 根据还车订单号和车牌号，更新还车油里程，电里程为0，实际还车时间
     * </p>
     * <p>
     * 数据库操作
     * </p>
     * 
     * @param takeCar
     */
    private static void updateFurtherCarForOns(FurtherCar furtherCar) {
        try {
            String retTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(VcUtil.getOrderTimeForNowIfZero(furtherCar.mFurtherTime));
            $.trace("retTime: " + retTime);
            /**
             * 获取当前时间为还车时间
             * (因为不是mqtt返回，没有下位机时间)
             */
//            String retTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Long currentTime = System.currentTimeMillis();
            String memo = "";
            // 实际还车时间
            writeLog("收到来自 车牌号：" + furtherCar.mCarNum + "，订单号："
                    + furtherCar.mOrderId + " 还车回复，还车内容，订单里程："
                    + furtherCar.mMiles + "，实际还车时间：" + retTime);
            // 取得当前定单信息
            String strSql = "SELECT * FROM " + ORDER_TABLE + " WHERE cso_id ="
                    + furtherCar.mOrderId; // + "

            List<Map> list = dao.executeQuery(strSql);
            for (Map map : list) {
                // 更新还车油里程，电里程为0，实际还车时间
                if (map != null) {
                    // 首次还车
                    if (Integer.parseInt(map.get(CsOrder.C.csoStatus)
                            .toString()) == 0
                            || Integer.parseInt(map.get(CsOrder.C.csoStatus)
                                    .toString()) == 1) {
                        // add at 2015-01-28 09:58 by xiaogang 针对下位机还车信息发送延迟
                        memo = "车牌号：" + furtherCar.mCarNum + "，订单号："
                                + furtherCar.mOrderId + " 首次还车，订单里程："
                                + furtherCar.mMiles + "，实际还车时间：" + retTime;
                        if ((currentTime - ((Timestamp) map
                                .get(CsOrder.C.csoFinishTime)).getTime()) >= 1 * 60 * 60 * 1000l) {
                            // 修改为待处理
                            dao.executeUpdate(getFurtherCarSql(furtherCar,
                                    retTime, ORDER_STATUS_PEND,
                                    "超时还车，系统修改订单状态为待处理"));
                        } else {
                            // 修改为已还车
                            dao.executeUpdate(getFurtherCarSql(furtherCar,
                                    retTime, ORDER_STATUS_RETURN, "正常还车"));
                        }
                        writeLog(memo);
                        return;
                    } else {
                        // 里程不一样，油里程
                        if (furtherCar.mFucCode == ORDER_FURTHER_FUC_CODE_O) {
                            if (furtherCar.mMiles != SystemHelper.toInt(Double
                                    .parseDouble(map.get(CsOrder.C.csoFuel)
                                            .toString()))) {
                                memo = "车牌号：" + furtherCar.mCarNum + "，订单号："
                                        + furtherCar.mOrderId + " 多次还车，订单油里程："
                                        + furtherCar.mMiles + "，实际还车时间："
                                        + retTime + "  多次还车描述：前后油里程不一致，前："
                                        + map.get(CsOrder.C.csoFuel) + "  后："
                                        + furtherCar.mMiles;
                                // dao.executeUpdate(getFurtherCarSql(furtherCar,
                                // retTime, ORDER_STATUS_PEND,
                                // "多次还车，前后油里程不一致，系统修改订单状态为待处理"));
                                dao.execute(getOrderLogSql(furtherCar.mOrderId,
                                        memo));
                                writeLog(memo);
                                return;
                            }
                        } else {// 里程不一样，电里程
                            if (furtherCar.mMiles != SystemHelper.toInt(Double
                                    .parseDouble(map.get(CsOrder.C.csoElectric)
                                            .toString()))) {
                                memo = "车牌号：" + furtherCar.mCarNum + "，订单号："
                                        + furtherCar.mOrderId + " 多次还车，订单电里程："
                                        + furtherCar.mMiles + "，实际还车时间："
                                        + retTime + "  多次还车描述：前后里程不一致，前："
                                        + map.get(CsOrder.C.csoElectric)
                                        + "  后：" + furtherCar.mMiles;
                                // dao.executeUpdate(getFurtherCarSql(furtherCar,
                                // retTime, ORDER_STATUS_PEND,
                                // "多次还车，前后电里程不一致，系统修改订单状态为待处理"));
                                dao.execute(getOrderLogSql(furtherCar.mOrderId,
                                        memo));

                                writeLog(memo);
                                return;
                            }
                        }
                        // 还车时间在之前的5分钟左右
                        if (map.get(CsOrder.C.csoRetTime) != null) {
                            Long time = ((Timestamp) map
                                    .get(CsOrder.C.csoRetTime)).getTime();
                            if (currentTime >= (time + 5 * 60 * 1000l)) {
                                memo = "车牌号："
                                        + furtherCar.mCarNum
                                        + "，订单号："
                                        + furtherCar.mOrderId
                                        + " 多次还车，订单里程："
                                        + furtherCar.mMiles
                                        + "，实际还车时间："
                                        + retTime
                                        + "  多次还车描述：前后还车时间不一致，前："
                                        + new SimpleDateFormat(
                                                "yyyy-MM-dd HH:mm:ss")
                                                .format(new Date(time))
                                        + "  后："
                                        + new SimpleDateFormat(
                                                "yyyy-MM-dd HH:mm:ss")
                                                .format(new Date());
                                // dao.executeUpdate(getFurtherCarSql(furtherCar,
                                // retTime, ORDER_STATUS_PEND,
                                // "多次还车，前后还车时间不一致，系统修改订单状态为待处理"));
                                writeLog(memo);
                                dao.execute(getOrderLogSql(furtherCar.mOrderId,
                                        memo));
                                return;
                            }
                        }

                        memo = "车牌号：" + furtherCar.mCarNum + "，订单号："
                                + furtherCar.mOrderId + " 多次还车，订单里程："
                                + furtherCar.mMiles + "，实际还车时间：" + retTime;
                        // dao.executeUpdate(getFurtherCarSql(furtherCar,
                        // retTime, ORDER_STATUS_PEND,
                        // "多次还车，前后还车时间不一致，系统修改订单状态为待处理"));
                        writeLog(getOrderLogSql(furtherCar.mOrderId, memo));
                        dao.execute(getOrderLogSql(furtherCar.mOrderId, memo));
                        return;
                    }
                }
            }

            // $.trace(strSql);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            writeLog(e.getMessage());
        }
    }
    
    private static String getOrderLogSql(Long order, String memo) {
        String sqlString = "INSERT INTO cs_order_log (csol_title,csol_order,csol_editor,csol_remark,csol_add_time) VALUES ('收到多次还车信息',{order},0,'{remark}','{addtime}')";
        sqlString = sqlString.replace("{order}", order.toString()).replace(
                "{remark}", memo).replace("{addtime}",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return sqlString;
    }

    /**
     * 获得更新还车信息Sql语句
     * 
     * @param remoteOption
     */
    private static String getFurtherCarSql(FurtherCar furtherCar,
            String retTime, Long orderStatus, String memo) {
        // " + furtherCar.mMiles + "
        String strRetSql = "UPDATE "
                + ORDER_TABLE
                + " SET cso_status = {3}, cso_fuel = {0}, cso_electric = {1}, cso_ret_time = '"
                + retTime
                + "' , cso_remark = concat_ws('',cso_remark,'{4}') WHERE cso_id = "
                + furtherCar.mOrderId;
        strRetSql = strRetSql.replace("{3}", orderStatus.toString()).replace(
                "{4}", memo);
        switch (furtherCar.mFucCode) {
        case ORDER_FURTHER_FUC_CODE_O:
            strRetSql = strRetSql.replace("{0}",
                    String.valueOf(furtherCar.mMiles)).replace("{1}", "0");
            break;
        case ORDER_FURTHER_FUC_CODE_E:
            strRetSql = strRetSql.replace("{1}",
                    String.valueOf(furtherCar.mMiles)).replace("{0}", "0");
            break;
        default:
            strRetSql = strRetSql.replace("{0}",
                    String.valueOf(furtherCar.mMiles)).replace("{1}", "0");
            break;
        }

        return strRetSql;
    }

    /**
     * 远程操作回复
     * 
     * @param remoteOption
     */
    private static void updateRemote(RemoteOption remoteOption) {
        try {
            String timeString = DateHelper.formatDate(new Date(),
                    "yyyy-MM-dd HH:mm:ss");
            String strSql = "UPDATE " + REMOTE_TABLE
                    + " SET csr_status = 1,csr_update_time = '" + timeString
                    + "' WHERE csr_id = " + remoteOption.id;
            dao.executeUpdate(strSql);
            writeLog("收到来自 车牌号：" + remoteOption.number + "，远程操作回复");
        } catch (Exception e) {
            e.printStackTrace();
            writeLog(e.getMessage());
        }
    }

    public ICsStateService getCsStateService() {
        return csStateService;
    }

    public ICsHistoryStateService getCsHistoryStateService() {
        return csHistoryStateService;
    }

    public CarOrderScheduledPoolExecuter getCarOrderScheduledPoolExecuter() {
        return carOrderScheduledPoolExecuter;
    }

    public ICsOrderService getCsOrderService() {
        return csOrderService;
    }

    public ICsCarService getCsCarService() {
        return csCarService;
    }

    public VcCmdApiService getVcCmdApiService() {
        return vcCmdApiService;
    }

    public ICsRemoteService getCsRemoteService() {
        return csRemoteService;
    }

    public BlockingQueue<ICarStatus> getCarStatusQueue() {
        return carStatusQueue;
    }

    public void setCsStateService(ICsStateService csStateService) {
        this.csStateService = csStateService;
    }

    public void setCsHistoryStateService(ICsHistoryStateService csHistoryStateService) {
        this.csHistoryStateService = csHistoryStateService;
    }

    public void setCarOrderScheduledPoolExecuter(
            CarOrderScheduledPoolExecuter carOrderScheduledPoolExecuter) {
        this.carOrderScheduledPoolExecuter = carOrderScheduledPoolExecuter;
    }

    public void setCsOrderService(ICsOrderService csOrderService) {
        this.csOrderService = csOrderService;
    }

    public void setCsCarService(ICsCarService csCarService) {
        this.csCarService = csCarService;
    }

    public void setVcCmdApiService(VcCmdApiService vcCmdApiService) {
        this.vcCmdApiService = vcCmdApiService;
    }

    public void setCsRemoteService(ICsRemoteService csRemoteService) {
        this.csRemoteService = csRemoteService;
    }

    public void setCarStatusQueue(BlockingQueue<ICarStatus> carStatusQueue) {
        this.carStatusQueue = carStatusQueue;
    }
}
