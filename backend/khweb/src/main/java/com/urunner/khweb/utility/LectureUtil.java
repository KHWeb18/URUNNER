package com.urunner.khweb.utility;


import com.urunner.khweb.entity.lecture.Lecture;
import com.urunner.khweb.repository.lecture.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.NoSuchElementException;
import java.util.Optional;


@Component
public class LectureUtil {

    @Value("${image.location}")
    String imageLocation;

//    new인스턴스화 하면 스프링이 관여하지않아서 어노테이션이 무시된다.
//    따라서 ioc를 두고 @AutoWired를 사용하면 해결할수있다.

    @Value("${video.location}")
    String videoLocation;


    @Autowired
    LectureRepository lectureRepository;

    public void deleteUtil(String form, String delPath){

        ClassPathResource resource;

        if (form.equals("image")) {
            ClassPathResource resource1 = new ClassPathResource(imageLocation);
            resource = resource1;
        } else {
            ClassPathResource resource2 = new ClassPathResource(videoLocation);
            resource = resource2;
        }

        try {
            Path path = Paths.get(resource.getURI());
            String dirPath = path.toString() + "/" + authentication() + "/" + delPath;

            File file = new File(dirPath);

            System.out.println(dirPath);

            if (file.exists()) {
//                원본파일이 잡혀있어서 삭제가 안되는경우
//                file.delete();
                System.gc();
                boolean delete = file.delete();
                System.out.println("삭제여부" + delete);
            }else {
                System.out.println("파일이 존재하지 않는다.");
            }

        } catch (Exception e) {
            System.out.println("삭제에러");
        }

    }

    public String authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    public void isProgress(Long id, boolean isProgress) {

        Optional<Lecture> lecture = lectureRepository.findById(id);

        lecture.orElseThrow(() -> new NoSuchElementException());

        lecture.ifPresent(l -> {
            l.setInProgress(isProgress);
            lectureRepository.save(l);
        });
    }

}
