package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.entity.board.QnAMember;

import java.util.List;
import java.util.Optional;

public interface FreeBoardService {
    Free register(FreeRequest freeRequest) throws Exception;
    List<Free> findAll() throws Exception;
    List<Free> selectFreeList() throws Exception;
    List<Free> findByComplete(String complete) throws Exception;
    Optional<Free> findByBoardNo(Long boardNo) throws Exception;
    void updatePost(FreeRequest freeRequest) throws Exception;
    void updateCurrentNum(FreeRequest freeRequest) throws Exception;
    void updateViews(FreeRequest freeRequest) throws Exception;
    void delete(Long boardNo) throws Exception;
    List<QnAMember> selectStudyBoardNo(Long boardNo);
    QnAMember applyMember(QnAMember qnAMember);
    Long checkLength(Long boardNo);
    void updateComments(Long boardNo, Long upDown);
}