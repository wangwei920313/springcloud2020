package com.ww.springcloud.service;

import com.ww.springcloud.entities.Payment;

public interface PaymentService {

    public int save(Payment payment);

    public Payment getPaymentById(Long id);

}
