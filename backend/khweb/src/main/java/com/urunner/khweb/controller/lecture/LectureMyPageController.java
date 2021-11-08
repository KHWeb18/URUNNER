package com.urunner.khweb.controller.lecture;


import com.urunner.khweb.controller.dto.lecture.LectureListDto;
import com.urunner.khweb.controller.dto.lecture.LectureParse;
import com.urunner.khweb.controller.dto.lecture.LectureVideoDto;
import com.urunner.khweb.service.lecture.LectureService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/mypage")
@Slf4j
public class LectureMyPageController {


    @Autowired
    private LectureService lectureService;

    @PostMapping("/lecture/saveLectureSection")
    public void saveLectureSection(@RequestBody LinkedHashMap test) {

        org.json.JSONObject jsonObject1 = LectureParse.parsingInfo(test);
//      이부분은 objectMapper로도 될듯
        String topic = jsonObject1.getString("topic");
        Long lectureId = jsonObject1.getLong("lectureId");

        lectureService.saveLectureSection(lectureId, topic);
    }

    @PostMapping("/lecture/getLectureSection")
    public Page<LectureListDto> findAllLectureSection(@RequestBody LinkedHashMap test) {

        org.json.JSONObject jsonObject = LectureParse.parsingInfo(test);
        Long lectureId = jsonObject.getLong("lectureId");

        return lectureService.findAllLectureSection(lectureId);
    }

    @GetMapping("/videos/{name}/full")
    public ResponseEntity<UrlResource> getFullVideo(@PathVariable String name) throws MalformedURLException {
        UrlResource video = new UrlResource("file:${video.location}/${name}");
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }

    @PostMapping("/lecture/getLectureVideoList")
    public List<LectureVideoDto> findAllLectureVideo(@RequestBody LinkedHashMap test) {

        org.json.JSONObject jsonObject = LectureParse.parsingInfo(test);
        Long lectureListId = jsonObject.getLong("id");

        return lectureService.findAllLectureVideo(lectureListId);
    }

}
