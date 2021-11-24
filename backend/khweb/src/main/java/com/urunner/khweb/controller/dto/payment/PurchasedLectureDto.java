package com.urunner.khweb.controller.dto.payment;


import lombok.Data;

import java.util.Date;

@Data
public class PurchasedLectureDto {


    public PurchasedLectureDto(Long id, Date orderDate, String status, String title, Long price) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.title = title;
        this.price = price;
    }

    private Long id;
    private Date orderDate;
    private String status;
    private String title;
    private Long price;
}
