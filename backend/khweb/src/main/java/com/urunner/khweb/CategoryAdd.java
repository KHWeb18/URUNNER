package com.urunner.khweb;

import com.urunner.khweb.entity.sort.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class CategoryAdd implements CommandLineRunner {

    @Autowired
    private EntityManager em;

    @Transactional
    @Override
    public void run(String... args) throws Exception {


                Category category1 = new Category();
                Category category2 = new Category();
                Category category3 = new Category();
                Category category4 = new Category();
                Category category5 = new Category();
                Category category6 = new Category();
                Category category7 = new Category();
                Category category8 = new Category();


                category1.setCategoryName("자바");
                category2.setCategoryName("개발 프로그래밍");
                category3.setCategoryName("프론트엔드");
                category4.setCategoryName("백엔드");
                category5.setCategoryName("Vue");
                category6.setCategoryName("React");
                category7.setCategoryName("Html Css");
                category8.setCategoryName("docker");

                em.persist(category1);
                em.persist(category2);
                em.persist(category3);
                em.persist(category4);
                em.persist(category5);
                em.persist(category6);
                em.persist(category7);
                em.persist(category8);

    }
}
