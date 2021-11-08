package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.QnARequest;
import com.urunner.khweb.controller.dto.StudyRequest;
import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.entity.board.Study;
import com.urunner.khweb.entity.board.StudyMember;

import java.util.List;
import java.util.Optional;

public interface QnABoardService {
    QnA register(QnARequest qnARequest) throws Exception;
    List<QnA> findAll() throws Exception;
    Optional<QnA> findByBoardNo(Long boardNo) throws Exception;
    void updatePost(QnARequest qnARequest) throws Exception;
    void updateCurrentNum(QnARequest qnARequest) throws Exception;
    void updateViews(QnARequest qnARequest) throws Exception;
    void delete(Long boardNo) throws Exception;
    List<QnAMember> selectStudyBoardNo(Long boardNo);
    QnAMember applyMember(QnAMember qnAMember);
    Long checkLength(Long boardNo);
    void updateComments(Long boardNo, Long upDown);
}