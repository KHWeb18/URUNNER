package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.controller.dto.lecture.LectureSearchDto;
import com.urunner.khweb.controller.dto.lecture.SearchCondition;
import com.urunner.khweb.entity.lecture.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepositoryCustom {

    Page<LectureSearchDto> searchPage(SearchCondition condition, int page);
}
