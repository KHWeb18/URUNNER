package com.urunner.khweb.service.payment;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urunner.khweb.controller.dto.payment.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    private final String SECRET_KEY = "test_sk_O6BYq7GWPVv7BMZ0KwarNE5vbo1d";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String successCheck(PaymentDto paymentDto) throws JsonProcessingException {
        String paymentKey = paymentDto.getPaymentKey();
        String orderId = paymentDto.getOrderId();
        int amount = paymentDto.getAmount();


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("orderId", orderId);
        payloadMap.put("amount", String.valueOf(amount));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey, request, JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode successNode = responseEntity.getBody();
            log.info(successNode.toString());
            log.info("success");
            //successNode.get("orderId").asText()
            //String secret = successNode.get("secret").asText(); // 가상계좌사용시 활성화
            /*
            성공시 구매한강의 추가로직 작성
             */
            return "success";
        } else {
            JsonNode failNode = responseEntity.getBody();
            log.info(failNode.get("message").asText());
            log.info("fail");
            //failNode.get("message").asText();
            //failNode.get("code").asText();
            return "fail";
        }
    }
}
