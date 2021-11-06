package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.CommentRes;
import com.urunner.khweb.entity.board.QnAComment;
import com.urunner.khweb.entity.board.StudyComment;

import java.util.List;

public interface QnACommentService {
    QnAComment register(CommentRes commentRes) throws Exception;
    List<QnAComment> selectStudyComment(Long boardNo) throws Exception;
    void delete(Long commentNo) throws Exception;
    Long extractCommentNo() throws Exception;
    void changeGroupNo(QnAComment comment) throws Exception;
}