package com.urunner.khweb.service.payment;


import com.urunner.khweb.controller.dto.payment.PaymentDto;

public interface PaymentService {

    public String successCheck(PaymentDto paymentDto) throws Exception;
}
