package com.urunner.khweb.controller.lecture;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.urunner.khweb.service.lecture.LectureService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/lecture")
public class LectureController {

    @Value("${image.location}")
    private String imageLocation;

    @Autowired
    private LectureService lectureService;

    private String thum;

    private String detail;

    private Long lectureId;

    @PostMapping("/upload/image/thumbnail")
    public @ResponseBody String LectureUpload(@RequestParam("thumbnailImage") List<MultipartFile> thumbnailImage,
                                                @RequestParam("imageDetail") List<MultipartFile> imageDetail,
//                                                String말고 Long으로
                                                @RequestParam("lectureId") Long lectureId ) throws IOException {

        log.info("image Name : " + lectureId);

        ClassPathResource resource1 = new ClassPathResource(imageLocation);

        System.out.println(resource1.getURL().toString());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("sc 객체 : " + authentication.getName());

        try {
            Path path = Paths.get(resource1.getURI());
            String dirPath = path.toString() + "/" + authentication.getName();
            File folder = new File(dirPath);

            System.out.println(path.toString());

            if (!folder.exists()) {
                try {
                    folder.mkdir();
                    System.out.println("폴더생성함");
                }catch (Exception e) {
                    e.getStackTrace();
                }
            } else {
                System.out.println("이미 존재하는 폴더입니다.");
            }

            UUID uuid = UUID.randomUUID();
            String randomString = uuid.toString() + "_";

            for (MultipartFile multipartFile : thumbnailImage) {
                String thumnailImageName = "Thum_" + randomString + multipartFile.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream(dirPath + "/" + thumnailImageName );
                writer.write(multipartFile.getBytes());
                writer.close();
                log.info("name for db : " + thumnailImageName);

                this.thum = thumnailImageName;
            }

            for (MultipartFile multipartFile : imageDetail) {
                String imageDetailName = "Detail_" + randomString + multipartFile.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream(dirPath + "/" + imageDetailName);
                writer.write(multipartFile.getBytes());
                writer.close();
                log.info("name for db : " + imageDetailName);

                this.detail = imageDetailName;
            }
            this.lectureId = lectureId;

            lectureService.lectureAddImage(this.thum, this.detail, this.lectureId);

        } catch (Exception e) {
            return "upload fail";
        }
        return "upload success";
    }

    @PostMapping("/newlecture")
    public String newLecture(@RequestBody LinkedHashMap test) throws JsonProcessingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String jsonTest = new JSONObject(test).toString();

        log.info(jsonTest.toString());

        org.json.JSONObject jsonObject = new org.json.JSONObject(jsonTest);

        org.json.JSONObject lectureInfo1 = jsonObject.getJSONObject("lectureInfo");

        String title = lectureInfo1.getString("title");
        Long price = lectureInfo1.getLong("price");
        String description = lectureInfo1.getString("description");
        String category = lectureInfo1.getString("category");

        String[] categoryArray = category.split(",");
        for (String s : categoryArray) {
            System.out.println(s);
        }

        lectureService.lectureRegister(authentication.getName(),title,price,description,category);
        System.out.println(Arrays.toString(categoryArray));

        return null;
    }
}
