package com.urunner.khweb.service.board;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureList;
import com.urunner.khweb.entity.lecture.LectureVideo;
import com.urunner.khweb.repository.board.CallLectureRepository;
import com.urunner.khweb.repository.lecture.LectureRepository;
import com.urunner.khweb.repository.lecture.LectureVideoRepository;
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

    @Autowired
    LectureVideoRepository lectureVideoRepository;

    @Autowired
    LectureRepository lectureRepository;
  
    public List<Object[]> selectLectureList() {
        return repository.selectLectureList();
    }

    public List<Object[]> selectLectureListWithTag(Long categoryId){
        return repository.selectLectureListWithTag(categoryId);
    }

    public List<Object[]> selectLectureListWithWord(String word) {
        return repository.selectLectureListWithWord(word);
    }

    public List<Object[]> selectMyLectureList(Long memberNo){
        return repository.selectMyLectureList(memberNo);
    }

    @Override
    public Optional<Lecture> callLatestLecture(Long videoId) throws Exception {
        //렉쳐비디오
        Optional<LectureVideo> latestLectureVideo = lectureVideoRepository.findById(videoId);
        //렉쳐반환
        log.info("렉쳐리스트 탐색시작");
        LectureList tmpLectureList = latestLectureVideo.get().getLectureList();
        log.info("렉쳐리스트는: "+ tmpLectureList.getLectureList_id());
        log.info("렉쳐 탐색시작");
        Optional<Lecture> lecture = lectureRepository.findById(tmpLectureList.getLectureList_id());

        return lecture;

    }
}