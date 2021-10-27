package com.urunner.khweb.repository.mypage;

import com.urunner.khweb.entity.mypage.MyNote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyPageRepository extends JpaRepository<MyNote,Long> {

}
