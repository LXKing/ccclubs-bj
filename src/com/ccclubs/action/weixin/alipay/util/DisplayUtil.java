package com.ccclubs.action.weixin.alipay.util;

import java.io.UnsupportedEncodingException;

import com.alipay.api.AlipayResponse;

public class DisplayUtil {

    /**
     * 打印异常码
     * 
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void displayErrorCode(AlipayResponse response) {
        System.out.println("--------------------Display Error Message-----------------------");
        System.out.println("ErrorCode=" + response.getCode());
        System.out.println("Msg=" + response.getMsg());
        System.out.println("SubCode=" + response.getSubCode());
        System.out.println("SubMsg=" + response.getSubMsg());

        try {
            String msg = response.getSubMsg();
            byte[] bs = msg.getBytes();
            System.out.println("after code = "+new String(bs, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
