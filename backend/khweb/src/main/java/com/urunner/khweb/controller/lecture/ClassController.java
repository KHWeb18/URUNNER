package com.urunner.khweb.controller.lecture;


import com.urunner.khweb.controller.dto.lecture.DtoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired


    @GetMapping("/getCart")
    public DtoWrapper getCart() {
        return new DtoWrapper();
    }

}
