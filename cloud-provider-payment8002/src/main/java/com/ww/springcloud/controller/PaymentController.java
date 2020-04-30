package com.ww.springcloud.controller;

import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import com.ww.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value="/payment/save")
    public CommonResult<Payment> save(@RequestBody  Payment payment){
        int result = paymentService.save(payment);
        log.info("插入结果1111："+result);
        if(result>0){
            return new CommonResult<Payment>(200,"插入数据库成功");
        }else{
            return new CommonResult<Payment>(500,"插入数据库失败");
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果："+payment);
        if(payment!=null){
            return new CommonResult<Payment>(200,"查询成功",payment);
        }else{
            return new CommonResult<Payment>(500,"查询失败,id="+id,null);
        }
    }

    @GetMapping(value="/payment/ribbon")
    public Object ribbon(){
        return serverPort;
    }

    @GetMapping(value="/payment/feign/timeout")
    public Object paymentFeignTimeout(){
        return serverPort;
    }

    @GetMapping(value="/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
