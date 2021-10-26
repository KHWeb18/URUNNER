package com.urunner.khweb.repository.mypage;

import com.urunner.khweb.entity.mypage.MyNote;
import com.urunner.khweb.entity.mypage.TempLecture;
import org.springframework.data.jpa.repository.JpaRepository;

//Entity변경후 수정필요함
public interface TempLectureRepository extends JpaRepository<TempLecture,Long> {

}
