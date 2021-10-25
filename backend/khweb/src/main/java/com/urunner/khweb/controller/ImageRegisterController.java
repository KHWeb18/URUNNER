package com.urunner.khweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class ImageRegisterController {

    @PostMapping("/uploadImg_Profile")
    @ResponseBody
    public String requestUploadFile3(
            @RequestParam("fileList") List<MultipartFile> fileList,
            @RequestParam("id") String userId) {

        log.info("requestUploadFile(): " + fileList);
        log.info("**params value( id ) : " + userId);

        String cut_userId = userId.substring(0, userId.length()-4);

        try {
            for (MultipartFile multipartFile : fileList) {
                log.info("requestUploadFile(): Make File");
                FileOutputStream writer = new FileOutputStream("./images/profiles/"+ cut_userId + ".gif");
                log.info("multipartFile.getOriginalFilename : " + multipartFile.getOriginalFilename());

                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            return "Fail";
        }
        log.info("requestUploadFile()");

        return "Success";
    }
}