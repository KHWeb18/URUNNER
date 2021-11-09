package com.urunner.khweb.controller.payment;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.urunner.khweb.controller.dto.payment.PaymentDto;
import com.urunner.khweb.service.payment.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {


    @Autowired
    PaymentService paymentService;



    @PostMapping("/success")
    public ResponseEntity<String> paymentSuccessCheck(@RequestBody PaymentDto paymentDto) throws Exception {
        log.info("paymentSuccessCheck()");
        String result = paymentService.successCheck(paymentDto);
        if(result.equals("success")){
            log.info("success");
            return new ResponseEntity<>("success", HttpStatus.OK);

        }else{
            log.info("fail");
            return new ResponseEntity<>("fail",HttpStatus.OK);
        }


    }

}
