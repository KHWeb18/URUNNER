package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.CommentRes;
import com.urunner.khweb.entity.board.Comment;
import com.urunner.khweb.repository.board.FreeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FreeCommentServiceImpl implements FreeCommentService {

    @Autowired
    private FreeCommentRepository repository;

    @Override
    public Comment register(CommentRes commentRes) throws Exception {

        Comment commentEntity = new Comment(commentRes.getBoardNo(), commentRes.getContent(), commentRes.getWriter(), commentRes.getName());

        return repository.save(commentEntity);
    }

    public List selectFreeComment(Long boardNo) throws Exception {
        return repository.selectFreeComment(boardNo);
    }

    public void delete(Long commentNo) throws Exception {
        Comment comment = new Comment();
        comment.setCommentNo(commentNo);
        repository.delete(comment);
    }
}