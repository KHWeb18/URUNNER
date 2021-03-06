package com.urunner.khweb.service.lecture;


import com.urunner.khweb.controller.dto.lecture.*;
import com.urunner.khweb.entity.lecture.Lecture;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface LectureService {

//    public void lectureVideo(LectureVideo lectureVideo, EnrollLectureVideoDto enrollLectureVideoDto);

    public void lectureEnroll(Lecture lecture);

    public void lectureAddImage(String thum, String image, Long id);

    public void lectureRegister(String writer, String title, Long price, String desc, String content, String grade, String category);

    public void modifyLecture(Long lectureId, String writer, String title, Long price, String desc, String content, String grade, String category);

    public void saveLectureSection(Long lectureId, String topic);

    public Page<LectureListDto> findAllLectureSection(Long lectureId);

    public void videoUpload(String title, String desc, String duration, Long id, String path);

    public List<LectureDto> getLectureList(String writer);

    public List<LectureVideoDto> findAllLectureVideo(Long lectureListId);

    public Optional<LectureDto> getBasicInfo(Long lectureId);

    public void deleteThumbImg(Long lectureId);

    public void deleteDetailImg(Long lectureId);

    public void deleteLecture(Long lectureId);

    public void modifyLecture(LectureDto lectureDto);

    public DtoWrapper getSectionTopic(Long lectureListId);

    public void deleteSection(Long lectureListId);

    public void modifySectionTopic(LectureListDto lectureListDto);

    public DtoWrapper getLectureVideoInfo(Long videoId);

    public void deleteLectureVideo(Long videoId);

    public void inProgressToFalse(Long id);

    public void inProgressToTrue(Long id);

    public Optional<LectureVideoDto> modifyVideo(String title, String desc, String duration, Long id, String path);

    public void modifyVideoDelete(Long id);

    public DtoWrapper2 getLectureDetailInfo(Long lectureId);

    public  List<LectureDto> getAllLectureList();

    public Optional<LectureVideoInfo> getVideoInfo(Long lectureId);

    public Optional<LectureVideoInfo> getVideoInfo(Long lectureId, String name);

    public DtoWrapper lectureBanner(int page);

    public DtoWrapper getVideoInfoDetail(Long id);

    public DtoWrapper getCartList();

    public DtoWrapper getWishList();

    public DtoWrapper mainCartList(int page);

    public DtoWrapper mainWishList(int page);

    public Boolean regStudentComment(ReviewDto reviewDto);

    public List<GetReviewDto> reviewList(Page<LectureSearchDto> lectureSearchDtos);

    public DtoWrapper getReview(Long id);
}
