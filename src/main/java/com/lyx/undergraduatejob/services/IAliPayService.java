package com.lyx.undergraduatejob.services;


import com.alipay.api.AlipayApiException;

public interface IAliPayService {

    String genPayPic();

    void updateById(String id);

    String genPage(String totalAmount, String orderno);

    String refund(String outTradeNo, String balance) throws AlipayApiException;

    public boolean checkAlipay(String outTradeNo);

}
