package com.urunner.khweb.controller.myPage;


import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.UserDto;
import com.urunner.khweb.controller.dto.mypage.MyPageRes;
import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;
import com.urunner.khweb.service.member.SendEmailService;
import com.urunner.khweb.service.mypage.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/my-page")
public class MyPageController {

    @Autowired
    private MypageService mypageService;

    @Autowired
    SendEmailService sendEmailService;

    @GetMapping("")
    public ResponseEntity<MyPageRes> myPageGet() throws Exception{
        log.info("myPageGet");

        MyNote latestNote = mypageService.latestNote();

        //Lecture Entity변경시 데이터 타입 바뀌어야할 부분
        TempLecture latestLecture = mypageService.latestLec();

        MyPageRes myPageRes = new MyPageRes();
        myPageRes.setLatestLecture(latestLecture.getTitle());
        myPageRes.setLatestNote(latestNote.getTitle());



        return new ResponseEntity<MyPageRes>(myPageRes,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Void> mailcert(@RequestBody UserDto userDto)throws Exception{
        log.info("mailcert()");
        String email = userDto.getEmail();
        sendEmailService.sendMail(email);
        log.info("mailsend Success!");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }





}
