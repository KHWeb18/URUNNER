package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.repository.board.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class FreeBoardServiceImpl implements FreeBoardService {

//    @Autowired
//    private HttpSession session;

    @Autowired
    private FreeBoardRepository repository;

    @Override
    public Free register(FreeRequest freeRequest) throws Exception {

        Free postEntity = new Free(freeRequest.getTitle(), freeRequest.getContent(), freeRequest.getWriter(), freeRequest.getName());

        return repository.save(postEntity);
    }

    public List<Free> findAll() throws Exception {
        return repository.findAll();
    }

    public Optional<Free> findByBoardNo(Long boardNo) throws Exception {
        return repository.findByBoardNo(boardNo);
    }

    public void updatePost(FreeRequest freeRequest) throws Exception {

        repository.updatePost(freeRequest.getTitle(), freeRequest.getContent(), freeRequest.getBoardNo());
    }


    public void delete(Long boardNo) throws Exception {
        Free free = new Free();
        free.setBoardNo(boardNo);
        repository.delete(free);
    }
}