package com.urunner.khweb.controller.dto.classLecture;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassCartDto {

    private Long id;
    private String lectureName;
    private Long price;
    private String title;
    private String writer;
    private String thumb_path;
    private boolean discounted;
    private boolean inProgress;

}
