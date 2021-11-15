package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.QnARequest;
import com.urunner.khweb.entity.board.QnA;
import com.urunner.khweb.entity.board.QnAComment;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.repository.board.qna.QnABoardRepository;
import com.urunner.khweb.repository.board.qna.QnAMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class QnABoardServiceImpl implements QnABoardService {

    @Autowired
    private QnABoardRepository repository;

    @Autowired
    private QnAMemberRepository memberRepository;

    @Override
    public QnA register(QnARequest qnARequest) throws Exception {

        QnA postEntity = new QnA(qnARequest.getTitle(), qnARequest.getContent(), qnARequest.getWriter(),
                qnARequest.getNickname(), qnARequest.getComplete(), qnARequest.getCurrentNum(), qnARequest.getViews(),
                qnARequest.getComments(), qnARequest.getTags(), qnARequest.getNotice());

        return repository.save(postEntity);
    }

    public List<QnA> findAll(){
        return repository.findAll();
    }

    public List<QnA> selectQnAList() {
        return repository.selectQnAList();
    }

    public Optional<QnA> findByBoardNo(Long boardNo){

        Optional<QnA> board = repository.findByBoardNo(boardNo);

        System.out.println("*************************board read stage : " + board );
        Long views = board.get().getViews() + 1;
        repository.updateViews(views, boardNo); // 조회수 증가

        return board;
    }

    public List<QnA> findByComplete(String complete){
        return repository.findByComplete(complete);
    }


    public void updatePost(QnARequest qnARequest){

        repository.updatePost(qnARequest.getTitle(), qnARequest.getContent(), qnARequest.getBoardNo(),
                qnARequest.getComplete(), qnARequest.getCurrentNum(), qnARequest.getTags(), qnARequest.getNotice());
    }

    public void updateCurrentNum(QnARequest qnARequest){

        repository.updateCurrentNum(qnARequest.getCurrentNum(), qnARequest.getBoardNo());
    }

    public void updateViews(QnARequest qnARequest){

        repository.updateViews(qnARequest.getViews(), qnARequest.getBoardNo());
    }

    public void updateComments(Long boardNo, Long upDown) {
        repository.updateComments(boardNo, upDown);
    }

    public void delete(Long boardNo) throws Exception {
        QnA qnA = new QnA();
        qnA.setBoardNo(boardNo);
        repository.delete(qnA);
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