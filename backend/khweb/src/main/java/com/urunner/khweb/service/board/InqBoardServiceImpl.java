package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.board.InqRequest;
import com.urunner.khweb.entity.board.Inq;
import com.urunner.khweb.entity.board.QnAMember;
import com.urunner.khweb.repository.board.inq.InqBoardRepository;
import com.urunner.khweb.repository.board.qna.QnAMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InqBoardServiceImpl implements InqBoardService {

    @Autowired
    private InqBoardRepository repository;

    @Autowired
    private QnAMemberRepository memberRepository;

    @Override
    public Inq register(InqRequest inqRequest) throws Exception {

        Inq postEntity = new Inq(inqRequest.getTitle(), inqRequest.getContent(), inqRequest.getWriter(),
                inqRequest.getNickname(), inqRequest.getComplete(), inqRequest.getCurrentNum(), inqRequest.getViews(),
                inqRequest.getComments(), inqRequest.getTags(), inqRequest.getNotice(), inqRequest.getIsAuth());

        return repository.save(postEntity);
    }

    public List<Inq> findAll(){
        return repository.findAll();
    }

    public List<Inq> selectInqList() {
        return repository.selectInqList();
    }

    public Optional<Inq> findByBoardNo(Long boardNo){

        Optional<Inq> board = repository.findByBoardNo(boardNo);
        Long views = board.get().getViews() + 1;
        repository.updateViews(views, boardNo); // 조회수 증가

        return board;
    }

    public List<Inq> findByComplete(String complete){
        return repository.findByComplete(complete);
    }

    public List<Inq> findByUserId(String userId){
        return repository.findByUserId(userId);
    }

    public void updatePost(InqRequest inqRequest){

        repository.updatePost(inqRequest.getTitle(), inqRequest.getContent(), inqRequest.getBoardNo(),
                inqRequest.getComplete(), inqRequest.getCurrentNum(), inqRequest.getTags(), inqRequest.getNotice());
    }

    public void updateCurrentNum(InqRequest inqRequest){

        repository.updateCurrentNum(inqRequest.getCurrentNum(), inqRequest.getBoardNo());
    }

    public void updateViews(InqRequest inqRequest){

        repository.updateViews(inqRequest.getViews(), inqRequest.getBoardNo());
    }

    public void updateComments(Long boardNo, Long upDown) {
        repository.updateComments(boardNo, upDown);
    }

    public void delete(Long boardNo) throws Exception {
        Inq inq = new Inq();
        inq.setBoardNo(boardNo);
        repository.delete(inq);
    }

    @Override
    public List<QnAMember> selectInqBoardNo(Long boardNo) {
        return memberRepository.selectStudyBoardNo(boardNo);
    }

    public QnAMember applyMember(QnAMember qnAMember) {
        Optional<QnAMember> checkVal = memberRepository.checkMember(qnAMember.getEmail(), qnAMember.getBoardNo());
        String check = String.valueOf(checkVal);

        if (check.equals("Optional.empty"))
        {
            memberRepository.save(qnAMember);
        } else {
            memberRepository.delete(checkVal.get()); // 삭제, 'Optional.get()' without 'isPresent()' check 추후 수정할 것
        }
        return null;
    }

    public Long checkLength(Long boardNo) {
        return memberRepository.checkLength(boardNo);
    }
}