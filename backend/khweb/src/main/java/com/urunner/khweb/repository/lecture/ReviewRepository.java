package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @Query(value = "select avg(r.rating), count(r.rating) from Review r where r.lecture_id in :id", nativeQuery = true)
//    List<Object[]> getReview(@Param("id") List<Long> id);

    @Query("select r from Review r where r.lecture.lecture_id = :id")
    public Optional<List<Review>> getReviewId(@Param("id") Long id);

    @Query(value = "select * from review r where r.writer is not null order by rand() limit 10", nativeQuery = true)
    public List<Review> getRandomReviews();

}
