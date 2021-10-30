package com.urunner.khweb.controller.board;

import com.urunner.khweb.entity.board.Notice;
import com.urunner.khweb.repository.board.NoticeRepository;
import com.urunner.khweb.repository.request.NoticeRequest;
import com.urunner.khweb.service.board.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeRepository noticeRepository;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Validated @RequestBody NoticeRequest noticeRequest) throws Exception {

        log.info("noticeRegister(): " + noticeRequest.getTitle() + ", " + noticeRequest.getWriter());

        noticeService.register(noticeRequest);

        return new ResponseEntity<Void> (HttpStatus.OK);
    }

    @GetMapping("/noticeLists")
    public ResponseEntity<List<Notice>> getListsNotices () throws Exception {

        return new ResponseEntity<>(noticeService.noticeList(), HttpStatus.OK);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<Notice> readNotice(@PathVariable("noticeNo") Long noticeNo) throws Exception {

        Optional<Notice> jpaNotice = noticeService.readNotice(noticeNo);

        Notice NoticeNum = jpaNotice.get();

        return new ResponseEntity<Notice>(NoticeNum, HttpStatus.OK);
    }

    @PutMapping("/modifyNotice/{noticeNo}")
    public  ResponseEntity<Void> modifyNotice(@PathVariable("noticeNo")Long noticeNo,
                                              @Validated @RequestBody Notice Notice) throws  Exception {

        Notice.setNoticeNo(noticeNo);

        noticeService.modifyNotice(Notice);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeNo) throws Exception{

        noticeService.deleteNotice(noticeNo);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }
}
