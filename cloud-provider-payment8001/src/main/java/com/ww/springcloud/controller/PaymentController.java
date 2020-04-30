package com.ww.springcloud.controller;

import com.ww.springcloud.entities.CommonResult;
import com.ww.springcloud.entities.Payment;
import com.ww.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value="/payment/save")
    public CommonResult<Payment> save(@RequestBody  Payment payment){
        int result = paymentService.save(payment);
        log.info("插入结果1111："+result);
        if(result>0){
            return new CommonResult<Payment>(200,"插入数据库成功,port="+serverPort);
        }else{
            return new CommonResult<Payment>(500,"插入数据库失败,port="+serverPort);
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("插入结果："+payment);
        if(payment!=null){
            return new CommonResult<Payment>(200,"查询成功,port="+serverPort,payment);
        }else{
            return new CommonResult<Payment>(500,"查询失败,id="+id+",port="+serverPort,null);
        }
    }

    @GetMapping(value="/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services
             ) {
            log.info("....."+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances
             ) {
            log.info(instance.getInstanceId());
        }
        return discoveryClient;
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
