package com.urunner.khweb.controller.dto.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private String paymentKey;
    private String orderId;
    private int amount;
}
