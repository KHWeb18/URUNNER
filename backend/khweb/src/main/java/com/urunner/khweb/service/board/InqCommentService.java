
package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.InqComment;

import java.util.List;

public interface InqCommentService {
    InqComment register(CommentRes commentRes) throws Exception;
    List<InqComment> selectInqComment(Long boardNo) throws Exception;
    void delete(Long commentNo) throws Exception;
    Long extractionCommentNo() throws Exception;
    void changeGroupNo(InqComment comment) throws Exception;
}