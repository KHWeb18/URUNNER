package com.urunner.khweb.controller.myPage;

import com.urunner.khweb.controller.dto.board.QnARequest;
import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureVideo;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.service.board.CallLectureService;
import com.urunner.khweb.service.board.QnABoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/myPage")
public class MyLectureController {

    @Autowired
    private CallLectureService service;

    @Autowired
    private MemberRepository memberRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myLecturelist/{memberNo}")
    public ResponseEntity<List<Object[]>> getMyLectureLists (@PathVariable("memberNo") Long memberNo) throws Exception {
        log.info("getMyLectureLists :  ");

        return new ResponseEntity<>(service.selectMyLectureList(memberNo), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/my-latest-lecture")
    public ResponseEntity<Lecture> getMyLatestLecture() throws Exception {
        log.info("getMyLatestLecture()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        Member member = memberRepository.findByEmail(authentication.getName());
        Long latestVideoId = member.getLatestVideoId();
        Lecture latestLecture= service.callLatestLecture(latestVideoId).get();

        log.info("latest Lecture: "+ latestLecture.getTitle());

        return new ResponseEntity<>(latestLecture,HttpStatus.OK);

    }
}