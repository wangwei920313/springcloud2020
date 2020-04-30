package com.ww.springcloud.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value="CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value="/payment/ribbon")
    public String getPaymentById();

    @GetMapping(value="/payment/feign/timeout")
    public Object paymentFeignTimeout();

}
