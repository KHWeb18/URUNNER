package com.urunner.khweb.utility;

import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.Cart;
import com.urunner.khweb.repository.mypage.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class MyPageUtil {

    @Autowired
    CartRepository cartRepository;

    public void deleteCart(Member member, Lecture lecture) {
        List<Cart> collect = new ArrayList<>(member.getMyPage().getCartList());

        for (Cart list : collect) {
            if (Objects.equals(list.getLecture().getLecture_id(), lecture.getLecture_id())) {
                member.getMyPage().getCartList().removeIf(w -> w.getId().equals(list.getId()));
                cartRepository.deleteById(list.getId());
                log.info("이미 담긴 강의" + lecture.getLecture_id());
            }
        }
    }
}
