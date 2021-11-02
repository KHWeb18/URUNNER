package com.urunner.khweb.service.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureImage;
import com.urunner.khweb.entity.lecture.LectureList;
import com.urunner.khweb.entity.lecture.LectureVideo;
import com.urunner.khweb.entity.sort.Category;
import com.urunner.khweb.entity.sort.CategoryLecture;
import com.urunner.khweb.repository.lecture.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class LectureServiceImpl implements LectureService {


    @Autowired
    private LectureVideoRepository lectureVideoRepository;

    @Autowired
    private LectureListRepository lectureListRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private LectureImageRepository LectureImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void lectureVideo(LectureVideo lectureVideo, EnrollLectureVideoDto enrollLectureVideoDto) {

        LectureList lectureList = lectureListRepository.enrollLectureList(enrollLectureVideoDto.writer, enrollLectureVideoDto.topic);

        LectureVideo saveVideo = LectureVideo.builder()
                .title(lectureVideo.getTitle())
                .description(lectureVideo.getDescription())
                .sequence(lectureVideo.getSequence())
                .duration(lectureVideo.getDuration())
                .build();

        saveVideo.setLectureList(lectureList);

        lectureVideoRepository.save(saveVideo);
    }

    @Override
    public void lectureEnroll(Lecture lecture) {


        lectureRepository.save(lecture);
    }

    @Override
    public void lectureAddImage(String thum, String detail, Long id) {

        Lecture lecture = em.find(Lecture.class, id);

        LectureImage lectureImage = LectureImage.builder()
                .thumb_path(thum)
                .detail_path(detail)
                .build();

        lectureImage.setLecture(lecture);
        System.out.println(lectureImage.getThumb_path());

        LectureImageRepository.save(lectureImage);
    }

    @Override
    public void lectureRegister(String writer, String title, Long price, String desc, String categoryArray) {

        String[] category = categoryArray.split(",");

        List<String> cateList = new ArrayList();

        Lecture lecture = Lecture.builder()
                .writer(writer)
                .title(title)
                .price(price)
                .description(desc)
                .build();

        lectureRepository.save(lecture);

        for (String s : category) {
            System.out.println(s);
            cateList.add(s);
        }

        for (int i = 0; i < category.length; i++) {
            Category getCategory = categoryRepository.findByCategoryName(cateList.get(i));
            if (getCategory != null) {
                CategoryLecture tt = new CategoryLecture();
                tt.setLecture(lecture);
                tt.setCategory(getCategory);
                em.persist(tt);
            }
        }


    }
}
