//package com.urunner.khweb;
//
//import com.urunner.khweb.entity.sort.Category;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//@Component
//public class CategoryAdd implements CommandLineRunner {
//
//   @Autowired
//   private EntityManager em;
//
//   @Transactional
//   @Override
//   public void run(String... args) throws Exception {
//
//
//               Category category1 = new Category();
//               Category category2 = new Category();
//               Category category3 = new Category();
//               Category category4 = new Category();
//               Category category5 = new Category();
//               Category category6 = new Category();
//               Category category7 = new Category();
//               Category category8 = new Category();
//               Category category9 = new Category();
//               Category category10 = new Category();
//               Category category11 = new Category();
//               Category category12 = new Category();
//               Category category13 = new Category();
//               Category category14 = new Category();
//               Category category15 = new Category();
//               Category category16 = new Category();
//               Category category17 = new Category();
//               Category category18 = new Category();
//               Category category19 = new Category();
//               Category category20 = new Category();
//               Category category21 = new Category();
//               Category category22 = new Category();
//               Category category23 = new Category();
//               Category category24 = new Category();
//               Category category25 = new Category();
//
//
//               category1.setCategoryName("자바");
//               category2.setCategoryName("개발 프로그래밍");
//               category3.setCategoryName("프론트엔드");
//               category4.setCategoryName("백엔드");
//               category5.setCategoryName("Vue");
//               category6.setCategoryName("React");
//               category7.setCategoryName("Html Css");
//               category8.setCategoryName("docker");
//               category9.setCategoryName("JavaScript");
//               category10.setCategoryName("게임 개발");
//               category11.setCategoryName("Golang");
//               category12.setCategoryName("데이터 사이언스");
//               category13.setCategoryName("Python");
//               category14.setCategoryName("인공지능");
//               category15.setCategoryName("딥러닝");
//               category16.setCategoryName("데이터베이스");
//               category17.setCategoryName("SQL");
//               category18.setCategoryName("MongoDB");
//               category19.setCategoryName("보안");
//               category20.setCategoryName("모바일 앱 개발");
//               category21.setCategoryName("Swift");
//               category22.setCategoryName("안드로이드");
//               category23.setCategoryName("Kotlin");
//               category24.setCategoryName("코딩테스트");
//               category25.setCategoryName("기타");
//
//               em.persist(category1);
//               em.persist(category2);
//               em.persist(category3);
//               em.persist(category4);
//               em.persist(category5);
//               em.persist(category6);
//               em.persist(category7);
//               em.persist(category8);
//               em.persist(category9);
//               em.persist(category10);
//               em.persist(category11);
//               em.persist(category12);
//               em.persist(category13);
//               em.persist(category14);
//               em.persist(category15);
//               em.persist(category16);
//               em.persist(category17);
//               em.persist(category18);
//               em.persist(category19);
//               em.persist(category20);
//               em.persist(category21);
//               em.persist(category22);
//               em.persist(category23);
//               em.persist(category24);
//               em.persist(category25);
//
//
//   }
//}
