package com.urunner.khweb.controller.myPage;

import com.urunner.khweb.controller.dto.MemberRes;
import com.urunner.khweb.controller.dto.mypage.ProfileRes;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.service.lecture.LectureService;
import com.urunner.khweb.service.member.MemberProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/profile")
public class MemberProfileController {

    @Autowired
    private MemberProfileService service;

    @Value("${image.location}")
    private String imageLocation;

    @Value("${video.location}")
    private String videoLocation;

    private String thum;

    private Long lectureId;

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/upload/image/thumbnail/delete/{profile_no}")
    public ResponseEntity<Void> remove(@PathVariable("profile_no") Long profileNo) throws Exception {
        service.delete(profileNo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/upload/image/thumbnail")
    public @ResponseBody
    ResponseEntity<UrlResource> LectureUpload(@RequestParam("thumbnailImage") List<MultipartFile> thumbnailImage,
                                              @RequestParam("lectureId") Long lectureId) throws IOException {

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

            mkdirFolder(folder);

            UUID uuid = UUID.randomUUID();
            String randomString = uuid.toString() + "_";

            for (MultipartFile multipartFile : thumbnailImage) {
                String thumnailImageName = "Thum_" + randomString + multipartFile.getOriginalFilename();
                FileOutputStream writer = new FileOutputStream(dirPath + "/" + thumnailImageName);
                writer.write(multipartFile.getBytes());
                writer.close();
                log.info("name for db : " + thumnailImageName);

                this.thum = thumnailImageName;
            }
            this.lectureId = lectureId;
//            service.delete(lectureId);

            service.lectureAddImage(this.thum, this.lectureId);

        } catch (Exception e) {
            return null;
        }
//        UrlResource image = new UrlResource("classpath:" + imageLocation + "/" + authentication.getName() + "/" + this.detail);

        return null;
    }

    @GetMapping("/image/{path}/{writer}")
    public ResponseEntity<UrlResource> getThumnail(@PathVariable("path") String path, @PathVariable("writer") String writer) throws MalformedURLException {

        UrlResource image = new UrlResource("classpath:" + imageLocation + "/" + writer + "/" + path);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(image).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(image);
    }

    public void mkdirFolder(File folder) {

        if (!folder.exists()) {
            try {
                folder.mkdir();
                System.out.println("폴더생성함");
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("이미 존재하는 폴더입니다.");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/register")
    public ResponseEntity<Void> jpaRegister(@PathVariable("userId") String email,
                                                 @Validated @RequestBody MemberRes memberRes) throws Exception {

        log.info("vue에서 받아온 정보 : " + memberRes);
        memberRes.setEmail(email);

        service.register(memberRes);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{userId}")
    public ResponseEntity<MemberRes> profileUpdate(@PathVariable("userId") String email,
                                            @Validated @RequestBody MemberRes memberRes) throws Exception {

        memberRes.setEmail(email);
        log.info("vue에서 받아온 정보 : " + memberRes);

        if (Objects.equals(memberRes.getPassword(), "")) {
            service.update(memberRes);
        } else {
            service.update2(memberRes);
        }

        return new ResponseEntity<>(memberRes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/introduce/{userId}")
    public ResponseEntity<ProfileRes> findMyIntroduce(@PathVariable("userId") String email) throws Exception {

        ProfileRes profileRes = new ProfileRes();
        log.info("받아온 정보 : " + email);

        String introduce = service.findMyIntroduce(email);
        Long memberNo = service.findMyMemberNo(email);
        String thumb_path = service.findMyThumbPath(memberNo);
        Long profile_no = service.findMyProfileNo(memberNo);

        profileRes.setIntroduce(introduce);
        profileRes.setMember_no(memberNo);
        profileRes.setThumb_path(thumb_path);
        profileRes.setProfile_no(profile_no);

        return new ResponseEntity<>(profileRes, HttpStatus.OK);
    }
}