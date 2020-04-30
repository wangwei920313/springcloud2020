package com.ww.springcloud.controller;

import com.ww.springcloud.Service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value="/consumer/payment/ribbon")
    public String getPaymentById(){
        return paymentFeignService.getPaymentById();
    }

}
