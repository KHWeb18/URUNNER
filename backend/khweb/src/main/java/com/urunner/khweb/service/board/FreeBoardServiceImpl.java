package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.FreeRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.repository.board.free.FreeBoardRepository;
import com.urunner.khweb.repository.board.qna.QnAMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FreeBoardServiceImpl implements FreeBoardService {

    @Autowired
    private FreeBoardRepository repository;

    @Autowired
    private QnAMemberRepository memberRepository;

    @Override
    public Free register(FreeRequest freeRequest) throws Exception {

        Free postEntity = new Free(freeRequest.getTitle(), freeRequest.getContent(), freeRequest.getWriter(),
                freeRequest.getNickname(), freeRequest.getComplete(), freeRequest.getCurrentNum(), freeRequest.getViews(),
                freeRequest.getComments(), freeRequest.getTags(), freeRequest.getNotice());

        return repository.save(postEntity);
    }

    public List<Free> findAll(){
        return repository.findAll();
    }

    public List<Free> selectFreeList() {
        return repository.selectFreeList();
    }

    public Optional<Free> findByBoardNo(Long boardNo){

        Optional<Free> board = repository.findByBoardNo(boardNo);

        System.out.println("*************************board read stage : " + board );
        Long views = board.get().getViews() + 1;
        repository.updateViews(views, boardNo); // 조회수 증가

        return board;
    }

    public List<Free> findByComplete(String complete){
        return repository.findByComplete(complete);
    }


    public void updatePost(FreeRequest freeRequest){

        repository.updatePost(freeRequest.getTitle(), freeRequest.getContent(), freeRequest.getBoardNo(),
                freeRequest.getComplete(), freeRequest.getCurrentNum(), freeRequest.getTags(), freeRequest.getNotice());
    }

    public void updateCurrentNum(FreeRequest freeRequest){

        repository.updateCurrentNum(freeRequest.getCurrentNum(), freeRequest.getBoardNo());
    }

    public void updateViews(FreeRequest freeRequest){

        repository.updateViews(freeRequest.getViews(), freeRequest.getBoardNo());
    }

    public void updateComments(Long boardNo, Long upDown) {
        repository.updateComments(boardNo, upDown);
    }

    public void delete(Long boardNo) throws Exception {
        Free free = new Free();
        free.setBoardNo(boardNo);
        repository.delete(free);
    }

    @Override
    public List<QnAMember> selectStudyBoardNo(Long boardNo) {
        return memberRepository.selectStudyBoardNo(boardNo);
    }

    public QnAMember applyMember(QnAMember qnAMember) {
        Optional<QnAMember> checkVal = memberRepository.checkMember(qnAMember.getEmail(), qnAMember.getBoardNo());
        System.out.println("######## applyMember checkMember() run success");
        System.out.println("######## valueOF(checkval) value is");
        log.info(String.valueOf(checkVal));

        String check = String.valueOf(checkVal);
        log.info("*********check value :  " + check);

        System.out.println("check.equals(Optional.empty) : " + check.equals("Optional.empty"));

        if (check.equals("Optional.empty"))
        {
            System.out.println("######## if is true");
            System.out.println(qnAMember.getEmail());
            System.out.println(qnAMember.getNickname());
            System.out.println(qnAMember.getBoardNo());
            System.out.println(qnAMember.getIntroduce());
            memberRepository.save(qnAMember);
        } else {
            System.out.println("######## if is false");
            memberRepository.delete(checkVal.get()); // 삭제, 'Optional.get()' without 'isPresent()' check 추후 수정할 것
        }
        return null;
    }

    public Long checkLength(Long boardNo) {
        return memberRepository.checkLength(boardNo);
    }
}