package com.urunner.khweb.controller.dto.lecture;

import com.urunner.khweb.entity.lecture.Lecture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class DtoWrapper2<T> {

//    private T data;
    private Optional<?> opData;
    private Optional<?> opData2;
    private Long wishListCount;
    private T data;

    public DtoWrapper2(Optional<?> opData, Optional<?> opData2 ) {
        this.opData = opData;
        this.opData2 = opData2;
    }

    public DtoWrapper2(Optional<?> opData, Optional<?> opData2, T data ) {
        this.opData = opData;
        this.opData2 = opData2;
        this.data = data;
    }
}
