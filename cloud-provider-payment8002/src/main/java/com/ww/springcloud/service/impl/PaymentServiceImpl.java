package com.ww.springcloud.service.impl;

import com.ww.springcloud.dao.PaymentDao;
import com.ww.springcloud.entities.Payment;
import com.ww.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int save(Payment payment){
        return paymentDao.save(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

}
