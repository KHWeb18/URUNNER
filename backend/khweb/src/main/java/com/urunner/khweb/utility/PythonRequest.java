package com.urunner.khweb.utility;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class PythonRequest {
    public String findUserPw(String name, String email) {
        RestTemplate restTemplate = new RestTemplate();

        SendEmail sendName = new SendEmail(name, email, null);

        return restTemplate.postForObject(
                "http://localhost:5000/findPwByEmail",
                sendName,
                String.class
        );
    }
}

@Getter
class SendEmail {
    private String name;
    private String email;
    private String code;

    public SendEmail(String name, String email, String code) {
        this.name = name;
        this.email = email;
        this.code = code;
    }
}
