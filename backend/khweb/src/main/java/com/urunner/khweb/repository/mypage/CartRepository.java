package com.urunner.khweb.repository.mypage;

import com.urunner.khweb.entity.mypage.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
