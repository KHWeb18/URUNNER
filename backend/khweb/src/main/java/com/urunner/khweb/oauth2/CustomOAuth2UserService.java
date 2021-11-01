package com.urunner.khweb.oauth2;


import com.urunner.khweb.entity.member.AuthProvider;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.oauth2.user.OAuth2UserInfo;
import com.urunner.khweb.oauth2.user.OAuth2UserInfoFactory;
import com.urunner.khweb.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

// 구글 로그인 이후 가져온 사용자의 정보(eaml,name,pcitrue등)들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//        현재 유저의 로그인이 성공한 상태에서 유저의 정보를 가져온다.

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

//        이 로직근처에서 중복이메일 두지않을거면 예외를 던지고 아니고 이메일중복허용하고 provider로 나눌꺼면 둬도 상관없을것같다.

        System.out.println("login success proccess : " + oAuth2User.toString());
        System.out.println();

        try {
//            로그인 후 진행절차메서드로 이동
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
//            비어있는 값이있는경우 예외처리
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        로그인한 provider를 찾는다.
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        System.out.println(oAuth2UserInfo.toString());



        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
//            provider가 제공하는 타입인지 체크한다.
            System.out.println("지원하지않는 이메일 프로바이더");
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
//      가입된유저인지 확인
        Member exitMember = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        Member user;
//        가입된경우
        if(exitMember != null) {
            user = exitMember;
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
//            새로운 정보로 업데이트 (구글,네이버계정내용 변경시)
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
//            새로운 유저정보 등록시 해당메서드로 이동
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
            System.out.println("일단은 회원가입은 됐음");
        }

        return UserPrincipal.create(user,oAuth2User.getAttributes());
}

    //  새로운유저 가입
    private Member registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Member user = new Member();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        return userRepository.save(user);
    }

    private Member updateExistingUser(Member existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
//        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

}
