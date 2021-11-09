package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.entity.sort.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByCategoryName(String name);

}
