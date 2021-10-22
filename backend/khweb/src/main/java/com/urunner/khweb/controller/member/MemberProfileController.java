package com.urunner.khweb.controller.member;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.service.member.MemberProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberProfileController {

    @Autowired
    private MemberProfileService service;

    @PostMapping("/register")
    public ResponseEntity<Void> jpaRegister(@PathVariable("userId") String email,
                                                 @Validated @RequestBody MemberRes memberRes) throws Exception {

        log.info("vue에서 받아온 정보 : " + memberRes);
        memberRes.setEmail(email);

        service.register(memberRes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<MemberRes> profileUpdate(@PathVariable("userId") String email,
                                            @Validated @RequestBody MemberRes memberRes) throws Exception {

        memberRes.setEmail(email);
        log.info("vue에서 받아온 정보 : " + memberRes);

        if (Objects.equals(memberRes.getPassword(), "")) {
            service.update(memberRes);
        } else {
            service.update2(memberRes);
        }

        return new ResponseEntity<>(memberRes, HttpStatus.OK);
    }
}