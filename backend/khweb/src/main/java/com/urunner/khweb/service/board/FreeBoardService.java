package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import java.util.List;
import java.util.Optional;

public interface FreeBoardService {
    Free register(FreeRequest freeRequest) throws Exception;
    List<Free> findAll() throws Exception;
    Optional<Free> findByBoardNo(Long boardNo) throws Exception;
    void updatePost(FreeRequest freeRequest) throws Exception;
    void delete(Long boardNo) throws Exception;
}