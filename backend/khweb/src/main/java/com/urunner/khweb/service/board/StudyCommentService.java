package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.StudyComment;

import java.util.List;

public interface StudyCommentService {
    StudyComment register(CommentRes commentRes) throws Exception;
    List<StudyComment> selectStudyComment(Long boardNo) throws Exception;
    void delete(Long commentNo) throws Exception;
    Long extractionCommentNo() throws Exception;
    void changeGroupNo(StudyComment comment) throws Exception;
}