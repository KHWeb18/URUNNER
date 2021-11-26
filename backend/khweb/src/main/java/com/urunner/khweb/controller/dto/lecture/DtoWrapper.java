package com.urunner.khweb.controller.dto.lecture;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class DtoWrapper<T> {

    private T data;
    private Optional<?> opData;
    private T reviewData;
    private T comment;

    public DtoWrapper(Optional<?> opData) {
        this.opData = opData;
    }

    public DtoWrapper(T data) {
        this.data = data;
    }

    public DtoWrapper(T data, T reviewData) {
        this.data = data;
        this.reviewData = reviewData;

    }

    public DtoWrapper(T data, T reviewData, T comment) {
        this.data = data;
        this.reviewData = reviewData;
        this.comment = comment;

    }
}
