package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.repository.board.CallLectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CallLectureServiceImpl implements CallLectureService {

    @Autowired
    private CallLectureRepository repository;

    public List<Object[]> selectLectureList() {
        return repository.selectLectureList();
    }

    public List<Object[]> selectLectureListWithTag(Long categoryId){
        return repository.selectLectureListWithTag(categoryId);
    }

    public List<Object[]> selectLectureListWithWord(String word) {
        return repository.selectLectureListWithWord(word);
    }


}