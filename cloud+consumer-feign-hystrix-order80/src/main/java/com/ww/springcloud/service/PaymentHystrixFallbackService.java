package com.ww.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackService implements  PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "SB";
    }

    @Override
    public String paymentInfo_ERROR(Integer id) {
        return "DSB";
    }
}
