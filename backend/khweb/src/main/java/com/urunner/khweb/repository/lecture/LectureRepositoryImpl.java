package com.urunner.khweb.repository.lecture;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.urunner.khweb.controller.dto.lecture.CategoryName;
import com.urunner.khweb.controller.dto.lecture.LectureSearchDto;
import com.urunner.khweb.controller.dto.lecture.QLectureSearchDto;
import com.urunner.khweb.controller.dto.lecture.SearchCondition;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.mypage.Cart;
import com.urunner.khweb.entity.mypage.WishList;
import com.urunner.khweb.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.urunner.khweb.entity.lecture.QLecture.*;
import static com.urunner.khweb.entity.mypage.QCart.*;

@Slf4j
@Transactional
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    private MemberRepository memberRepository;

    public LectureRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LectureSearchDto> searchPage(SearchCondition condition, int page) {

        PageRequest pageRequest = PageRequest.of(page, 4);

        List<LectureSearchDto> content = queryFactory.select(
                        new QLectureSearchDto(
                                lecture.lecture_id,
                                lecture.writer,
                                lecture.title,
                                lecture.description,
                                lecture.price,
                                lecture.inProgress,
                                lecture.discounted,
                                lecture.thumb_path,
                                lecture.detail_path,
                                lecture.grade
                        ))
                .from(lecture)
                .where(titleNameEq(condition.getName()),
                        categoryEq(condition.getCategoryName()),
                        lecture.inProgress.eq(true)
                )
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        JPAQuery<LectureSearchDto> countQuery = queryFactory.select(
                        new QLectureSearchDto(
                                lecture.lecture_id,
                                lecture.writer,
                                lecture.title,
                                lecture.description,
                                lecture.price,
                                lecture.inProgress,
                                lecture.discounted,
                                lecture.thumb_path,
                                lecture.detail_path,
                                lecture.grade
                        ))
                .from(lecture)
                .where(titleNameEq(condition.getName()),
                        categoryEq(condition.getCategoryName()),
                        lecture.inProgress.eq(true)
                );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username.equals("anonymousUser")) {
            log.info("로그인 되있지않은 사용자");
        } else {
            Member member = memberRepository.findByEmail(username);

            List<Cart> carts = new ArrayList<>(member.getMyPage().getCartList());

            List<WishList> wishLists = new ArrayList<>(member.getMyPage().getWishLists());

            if (wishLists.size() != 0) {
                for (int i = 0; i < content.size(); i++) {
                    for (int j = 0; j < wishLists.size(); j++) {
                        boolean exist = content.get(i).getId().equals(wishLists.get(j).getLecture().getLecture_id());
                        System.out.println("매칭 여부 확인 : " + exist);
                        if (exist) {
                            content.get(i).setWishList(true);
                        }
                    }
                }
            }
            if (carts.size() != 0) {
                for (int i = 0; i < content.size(); i++) {
                    for (int j = 0; j < carts.size(); j++) {
                        boolean exist = content.get(i).getId().equals(carts.get(j).getLecture().getLecture_id());
                        System.out.println("매칭 여부 확인 : " + exist);
                        if (exist) {
                            content.get(i).setCart(true);
                        }
                    }
                }
            }
        }


        return PageableExecutionUtils.getPage(content, pageRequest, countQuery::fetchCount);
    }

    private BooleanExpression titleNameEq(String name) {
        return StringUtils.hasText(name) ? lecture.title.contains(name) : null;
    }

    private BooleanExpression categoryEq(CategoryName categoryName) {
        return StringUtils.hasText(categoryName.getCategory()) ? lecture.categoryList.any().category.categoryName.eq(categoryName.getCategory()) : null;
    }


}
