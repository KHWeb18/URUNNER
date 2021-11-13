package com.urunner.khweb.controller.dto.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class DtoWrapper2 {

//    private T data;
    private Optional<?> opData;
    private Optional<?> opData2;

    public DtoWrapper2(Optional<?> opData, Optional<?> opData2 ) {
        this.opData = opData;
        this.opData2 = opData2;
    }
}
