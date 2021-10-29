package com.urunner.khweb.service.member;

import com.urunner.khweb.controller.dto.MemberRes;

public interface SendEmailService {
    void sendMail(String email) throws Exception;
}
