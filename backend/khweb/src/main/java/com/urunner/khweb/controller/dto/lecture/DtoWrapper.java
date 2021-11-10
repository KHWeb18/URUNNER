package com.urunner.khweb.controller.dto.lecture;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class DtoWrapper<T> {

    private T data;
    private Optional<?> opData;

    public DtoWrapper(Optional<?> opData) {
        this.opData = opData;
    }
}
