package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.Comment;
import com.urunner.khweb.repository.board.free.FreeCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class FreeCommentServiceImpl implements FreeCommentService {

    @Autowired
    private FreeCommentRepository repository;

    @Override
    public Comment register(CommentRes commentRes) throws Exception {

        Comment commentEntity = new Comment(commentRes.getBoardNo(), commentRes.getContent(), commentRes.getWriter(),
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

    public List<Comment> selectFreeComment(Long boardNo) {
        return repository.selectFreeComment(boardNo);
    }

    public void delete(Long commentNo) throws Exception {
        Comment comment = new Comment();
        comment.setCommentNo(commentNo);
        repository.delete(comment);
    }

    public Long extractionCommentNo(){
        return repository.extractionCommentNo();
    }

    public void changeGroupNo(Comment comment){
        repository.changeGroupNo(comment);
    }

}