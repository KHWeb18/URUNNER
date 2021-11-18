package com.urunner.khweb.service.payment;


import com.urunner.khweb.controller.dto.payment.PaymentCancelDto;
import com.urunner.khweb.controller.dto.payment.PaymentDto;

import java.util.List;

public interface PaymentService {

    public String successCheck(PaymentDto paymentDto, List<String> lectureList,String email) throws Exception;
    public String paymentCancel(PaymentCancelDto paymentCancelDto) throws Exception;


}
