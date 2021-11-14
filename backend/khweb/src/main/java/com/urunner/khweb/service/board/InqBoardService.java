package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.InqRequest;
import com.urunner.khweb.entity.board.Inq;
import com.urunner.khweb.entity.board.QnAMember;

import java.util.List;
import java.util.Optional;

public interface InqBoardService {
    Inq register(InqRequest inqRequest) throws Exception;
    List<Inq> findAll() throws Exception;
    List<Inq> selectInqList() throws Exception;
    List<Inq> findByUserId(String userId) throws Exception;
    List<Inq> findByComplete(String complete) throws Exception;
    Optional<Inq> findByBoardNo(Long boardNo) throws Exception;
    void updatePost(InqRequest inqRequest) throws Exception;
    void updateCurrentNum(InqRequest inqRequest) throws Exception;
    void updateViews(InqRequest inqRequest) throws Exception;
    void delete(Long boardNo) throws Exception;
    List<QnAMember> selectInqBoardNo(Long boardNo);
    QnAMember applyMember(QnAMember qnAMember);
    Long checkLength(Long boardNo);
    void updateComments(Long boardNo, Long upDown);
}