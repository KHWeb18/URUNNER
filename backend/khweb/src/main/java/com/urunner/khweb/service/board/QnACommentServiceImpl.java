package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.QnAComment;
import com.urunner.khweb.repository.board.qna.QnABoardRepository;
import com.urunner.khweb.repository.board.qna.QnACommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QnACommentServiceImpl implements QnACommentService {

    @Autowired
    private QnACommentRepository repository;

    @Autowired
    private QnABoardRepository boardRepository;

    @Override
    public QnAComment register(CommentRes commentRes) throws Exception {

        QnAComment commentEntity = new QnAComment(commentRes.getBoardNo(), commentRes.getContent(), commentRes.getWriter(),
                                            commentRes.getNickname(), commentRes.getLayer(), commentRes.getGroupNo(), commentRes.getThumb_path());

        repository.save(commentEntity);

        if(commentRes.getGroupNo() == 0) { //대댓글이 아닐 경우
            Long commentNo = extractCommentNo(); //코멘트 번호 추출
            log.info("************LAST로 추출한 commnetNO value : " + commentNo);
            commentEntity.setCommentNo(commentNo);
            changeGroupNo(commentEntity);
        }
        return null;
    }

    public List<QnAComment> selectStudyComment(Long boardNo) {
        return repository.selectStudyComment(boardNo);
    }

    public void delete(Long commentNo) throws Exception {
        QnAComment comment = new QnAComment();
        comment.setCommentNo(commentNo);
        repository.delete(comment);
    }

    public Long extractCommentNo(){
        return repository.extractCommentNo();
    }

    public void changeGroupNo(QnAComment comment){
        repository.changeGroupNo(comment);
    }
}