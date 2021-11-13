package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.CommentRes;
import com.urunner.khweb.entity.board.StudyComment;
import com.urunner.khweb.repository.board.study.StudyCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class StudyCommentServiceImpl implements StudyCommentService {

    @Autowired
    private StudyCommentRepository repository;

    @Override
    public StudyComment register(CommentRes commentRes) throws Exception {

        StudyComment commentEntity = new StudyComment(commentRes.getBoardNo(), commentRes.getContent(), commentRes.getWriter(),
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

    public List<StudyComment> selectStudyComment(Long boardNo) {
        return repository.selectStudyComment(boardNo);
    }

    public void delete(Long commentNo) throws Exception {
        StudyComment comment = new StudyComment();
        comment.setCommentNo(commentNo);
        repository.delete(comment);
    }

    public Long extractionCommentNo(){
        return repository.extractionCommentNo();
    }

    public void changeGroupNo(StudyComment comment){
        repository.changeGroupNo(comment);
    }

}