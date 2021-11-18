package com.urunner.khweb.controller.lecture;


import com.urunner.khweb.controller.dto.lecture.JoinInstructorDto;
import com.urunner.khweb.service.member.MemberService;
import com.urunner.khweb.service.mypage.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/manageLecture")
@Slf4j
public class LectureManageController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MypageService mypageService;

    @PostMapping("/joinLecturer")
    public ResponseEntity<Boolean> joinLecturer(@RequestBody JoinInstructorDto joinInstructorDto) {

        return new ResponseEntity<Boolean>(memberService.joinInstructor(joinInstructorDto), HttpStatus.OK);
    }

    @GetMapping("/addToWish/{lectureId}")
    public ResponseEntity<Boolean> addToWish(@PathVariable("lectureId") Long lectureId) throws AuthenticationException {

        return new ResponseEntity<Boolean>(mypageService.lectureAddToWish(lectureId), HttpStatus.OK);

    }

    @GetMapping("/addToCart/{lectureId}")
    public ResponseEntity<Boolean> addToCart(@PathVariable("lectureId") Long lectureId) throws AuthenticationException {

        return new ResponseEntity<Boolean>(mypageService.lectureAddCart(lectureId), HttpStatus.OK);

    }



}
