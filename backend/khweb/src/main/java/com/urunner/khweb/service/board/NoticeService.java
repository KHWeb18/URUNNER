package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.board.Notice;
import com.urunner.khweb.repository.request.NoticeRequest;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    public void register(NoticeRequest noticeRequest) throws Exception;

    public List<Notice> noticeList() throws Exception;

    public Optional<Notice> readNotice (Long noticeNo) throws Exception;

    public  void modifyNotice(Notice Notice)throws Exception;

    public void deleteNotice (Long noticeNo) throws Exception;

}
