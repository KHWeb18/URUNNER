package com.urunner.khweb.repository.lecture;

import com.urunner.khweb.controller.dto.lecture.LectureSearchDto;
import com.urunner.khweb.controller.dto.lecture.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureRepositoryCustom {

    Page<LectureSearchDto> searchPage(SearchCondition condition, int page);
}
