package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.Comment;

import java.util.List;

public interface FreeCommentService {
    Comment register(CommentRes commentRes) throws Exception;
    List<Comment> selectFreeComment(Long boardNo) throws Exception;
    void delete(Long commentNo) throws Exception;
    Long extractionCommentNo() throws Exception;
    void changeGroupNo(Comment comment) throws Exception;
}