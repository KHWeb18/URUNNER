package com.urunner.khweb.controller.myPage;
import com.urunner.khweb.controller.dto.mypage.CheckCodeDto;
import com.urunner.khweb.controller.dto.mypage.MyPageRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.service.member.MemberService;
import com.urunner.khweb.service.mypage.MypageService;
import com.urunner.khweb.utility.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/my-page")
public class MyPageController {

    private String certCode;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MypageService mypageService;

    @Autowired
    private MemberService memberService;

    @PreAuthorize("hasRole('ROLE_USER')")
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

    @GetMapping("/mailcert")
    public ResponseEntity<Void> mailcert()throws Exception{
        log.info("mailcert()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        certCode = MailUtils.mailCertNumber().toString();


        log.info(email);
        memberService.sendMail(email,certCode);
        log.info("mailsend Success!");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/mailcert")
    public ResponseEntity<String> checkCertCode(@RequestBody CheckCodeDto checkCode)throws Exception{
        log.info("mailcheck() certCode: "+ certCode);
        log.info("checkCode : "+checkCode.getCheckCode());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        log.info("현재멤버는 : "+ member.getEmail());


        //인증확인코드
        if(certCode.equals(checkCode.getCheckCode())){
            //성공시 로직
            log.info("인증성공!");
            member.setCert(true);
            memberRepository.save(member);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }
        else{
            //인증실패시 로직
            log.info("인증실패!");
            return new ResponseEntity<>("fail",HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/getPoint")
    public ResponseEntity<Long> getPoint() {

        return new ResponseEntity<Long>(mypageService.getPoint(), HttpStatus.OK);

    }





}
