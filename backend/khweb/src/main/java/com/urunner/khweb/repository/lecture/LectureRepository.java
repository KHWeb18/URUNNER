package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.lecture.LectureList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureRepositoryCustom {

    public List<Lecture> findByWriter(String writer);
    public Lecture findByTitle(String title);
    public void deleteAllByWriter(String writer);

//    @Query("select l from Lecture l join fetch l.lectureLists where l.lecture_id =: id")
//    public Optional<Lecture> letureAll(Long id);
//  NoSuchElementException fetch 조인시 테이블에 값이 존재하지않는다면 rollback되고 취소됨 - 강의 등록시 리뷰쪽 테이블에 하나만 열을 추가하면 해결은 가능
//   이경우 left조인으로는 가능하게 만들지만 영속성은 빠져있음 즉 N + 1 
//    이럴바에는 new Operator가 훨 낫다.
//  @Query(value = "select l from Lecture l join fetch l.categoryList c join fetch c.category cl left join l.reviews r where l.inProgress = :inProgress",
//        countQuery = "select count(l) from Lecture l")

    @Query(value = "select l from Lecture l join fetch l.categoryList c join fetch c.category cl where l.inProgress = :inProgress",
            countQuery = "select count(l) from Lecture l")
    Page<Lecture> findByInProgressTrue(@Param("inProgress")boolean inProgress, Pageable pageable);

    @Query(value = "select l from Lecture l join fetch l.lectureLists li join li.lectureVideos lv where lv.id = :id")
    public Optional<Lecture> getLectureFromVideo(@Param("id") Long id);

    @Query(value = "select l from Lecture l join fetch l.categoryList c join fetch c.category cl join fetch l.reviews r where l.inProgress = :inProgress " +
            "and r.rating > (select avg(rating) from Review)",
            countQuery = "select count(l) from Lecture l")
    Page<Lecture> getLectureOrderByReview(@Param("inProgress")boolean inProgress, Pageable pageable);

    @Query(value = "select count(*) from Lecture l where l.inProgress = true")
    public Long getLectureCount();

}
