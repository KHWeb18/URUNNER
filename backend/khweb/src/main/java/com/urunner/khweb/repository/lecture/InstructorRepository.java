package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.lecture.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
