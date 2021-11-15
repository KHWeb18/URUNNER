
package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.Comment;
import com.urunner.khweb.entity.board.InqComment;
import com.urunner.khweb.repository.board.inq.InqCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InqCommentServiceImpl implements InqCommentService {

    @Autowired
    private InqCommentRepository repository;

    @Override
    public InqComment register(CommentRes commentRes) throws Exception {

        InqComment commentEntity = new InqComment(commentRes.getBoardNo(), commentRes.getContent(), commentRes.getWriter(),
                                            commentRes.getNickname(), commentRes.getLayer(), commentRes.getGroupNo());

        repository.save(commentEntity);

        if(commentRes.getGroupNo() == 0) { //대댓글이 아닐 경우
            Long commentNo = extractionCommentNo(); //코멘트 번호 추출
            log.info("************LAST로 추출한 commnetNO value : " + commentNo);
            commentEntity.setCommentNo(commentNo);
            changeGroupNo(commentEntity);
        }
        return null;
    }

    public List<InqComment> selectInqComment(Long boardNo) {
        return repository.selectInqComment(boardNo);
    }

    public void delete(Long commentNo) throws Exception {
        InqComment comment = new InqComment();
        comment.setCommentNo(commentNo);
        repository.delete(comment);
    }

    public Long extractionCommentNo(){
        return repository.extractionCommentNo();
    }

    public void changeGroupNo(InqComment comment){
        repository.changeGroupNo(comment);
    }

}