package com.urunner.khweb.service.payment;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urunner.khweb.controller.dto.lecture.DtoWrapper;
import com.urunner.khweb.controller.dto.payment.PaymentCancelDto;
import com.urunner.khweb.controller.dto.payment.PaymentDto;
import com.urunner.khweb.controller.dto.payment.PurchasedLectureDto;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.PurchasedLecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.Cart;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.lecture.PurchasedLectureRepository;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.repository.mypage.CartRepository;
import com.urunner.khweb.utility.LectureUtil;
import com.urunner.khweb.utility.MyPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    private final String SECRET_KEY = "test_sk_O6BYq7GWPVv7BMZ0KwarNE5vbo1d";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Autowired
    PurchasedLectureRepository purchasedLectureRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartRepository cartRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    private MyPageUtil myPageUtil;



    @Transactional
    @Override
    public String successCheck(PaymentDto paymentDto, List<String> lectureList,String email) throws JsonProcessingException {


        Member member = memberRepository.findByEmail(email);
        String paymentKey = paymentDto.getPaymentKey();
        String orderId = paymentDto.getOrderId();
        int amount = paymentDto.getAmount();
        long point = member.getMyPage().getPoint() + (long)(amount*0.05);

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
            log.info("강의구매합니다");

            //강의 구매 로직 부분

            for(int i = 0; i<lectureList.size();i++){
                String title = lectureList.get(i);
                //lecture_id 할당부분 테이블직접연결시수정필요
                Lecture lecture = lectureRepository.findByTitle(title);
                Long lecture_id = lecture.getLecture_id();

                if(lecture != null ){
                    PurchasedLecture purchasedLecture = new PurchasedLecture();
                    purchasedLecture.setTitle(title);
                    purchasedLecture.setLecture_id(lecture_id);
                    purchasedLecture.setOrederId(orderId);
                    member.addPurchasedLecture(purchasedLecture);
                    member.getMyPage().setPoint(point);
                    log.info("멤버 저장합니다");
                    memberRepository.save(member);
                    
//                    카트 삭제 메서드
                    myPageUtil.deleteCart(member, lecture);

                }
                else{
                    log.info("해당강의가 존재 하지 않습니다");
                }

            }

            return "success";
        } else {
            JsonNode failNode = responseEntity.getBody();
            log.info(failNode.get("message").asText());
            log.info("fail");

            return "fail";
        }
    }

    //결제취소 메소드
    @Override
    public String paymentCancel(PaymentCancelDto paymentCancelDto) throws Exception {
        String paymentKey = paymentCancelDto.getPaymentKey();
        String result = paymentCancelDto.getResult();



        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((SECRET_KEY + ":").getBytes()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("result", result);

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(payloadMap), headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(
                "https://api.tosspayments.com/v1/payments/" + paymentKey+"/cancel", request, JsonNode.class);


        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            JsonNode successNode = responseEntity.getBody();
            log.info(successNode.toString());
            log.info("결제취소완료");
            return "success";

        } else {
            JsonNode failNode = responseEntity.getBody();
            log.info(failNode.get("message").asText());
            log.info("결제취소 실패");
            return "fail";
        }
    }

    @Override
    public DtoWrapper getPurchasedLecture() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = memberRepository.findByEmail(authentication.getName());

        List<PurchasedLecture> purchasedLectureList = purchasedLectureRepository.findByMemberNo(member.getMemberNo());

        String status = "주문완료";
        List<PurchasedLectureDto> purchasedLectureDtoList = purchasedLectureList.stream().map(
                l -> new PurchasedLectureDto(l.getPurchasedLectureId(), l.getRegDate(), status,
//                N + 1
                l.getTitle(), lectureRepository.findById(l.getLecture_id()).get().getPrice()
        )).collect(Collectors.toList());

        return new DtoWrapper(purchasedLectureDtoList);
    }


}
