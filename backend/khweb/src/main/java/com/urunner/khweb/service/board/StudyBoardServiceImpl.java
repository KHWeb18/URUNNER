package com.urunner.khweb.service.board;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.StudyRequest;
import com.urunner.khweb.entity.board.Free;
import com.urunner.khweb.entity.board.Study;
import com.urunner.khweb.entity.board.StudyMember;
import com.urunner.khweb.repository.board.study.StudyBoardRepository;
import com.urunner.khweb.repository.board.study.StudyMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class StudyBoardServiceImpl implements StudyBoardService {

    @Autowired
    private StudyBoardRepository repository;

    @Autowired
    private StudyMemberRepository memberRepository;

    @Override
    public Study register(StudyRequest studyRequest) throws Exception {

        Study postEntity = new Study(studyRequest.getTitle(), studyRequest.getContent(), studyRequest.getWriter(),
                studyRequest.getName(), studyRequest.getComplete(), studyRequest.getFit(), studyRequest.getCurrentNum());

        return repository.save(postEntity);
    }

    public List<Study> findAll(){
        return repository.findAll();
    }

    public Optional<Study> findByBoardNo(Long boardNo){
        return repository.findByBoardNo(boardNo);
    }

    public void updatePost(StudyRequest studyRequest){

        repository.updatePost(studyRequest.getTitle(), studyRequest.getContent(), studyRequest.getBoardNo(),
                studyRequest.getComplete(), studyRequest.getFit(), studyRequest.getCurrentNum());
    }

    public void updateCurrentNum(StudyRequest studyRequest){

        repository.updateCurrentNum(studyRequest.getCurrentNum(), studyRequest.getBoardNo());
    }


    public void delete(Long boardNo) throws Exception {
        Study study = new Study();
        study.setBoardNo(boardNo);
        repository.delete(study);
    }

    @Override
    public List<StudyMember> selectStudyBoardNo(Long boardNo) {
        return memberRepository.selectStudyBoardNo(boardNo);
    }

    public StudyMember applyMember(StudyMember studyMember) {
        Optional<StudyMember> checkVal = memberRepository.checkMember(studyMember.getEmail(), studyMember.getBoardNo());
        System.out.println("######## applyMember checkMember() run success");
        System.out.println("######## valueOF(checkval) value is");
        log.info(String.valueOf(checkVal));

        String check = String.valueOf(checkVal);
        log.info("*********check value :  " + check);

        System.out.println("check.equals(Optional.empty) : " + check.equals("Optional.empty"));

        if (check.equals("Optional.empty"))
        {
            System.out.println("######## if is true");
            System.out.println(studyMember.getEmail());
            System.out.println(studyMember.getName());
            System.out.println(studyMember.getBoardNo());
            System.out.println(studyMember.getIntroduce());
            memberRepository.save(studyMember);
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