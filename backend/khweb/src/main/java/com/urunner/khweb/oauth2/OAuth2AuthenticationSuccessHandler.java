package com.urunner.khweb.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.repository.member.MemberRepository;
import com.urunner.khweb.utility.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


// 성공핸들러 토큰,쿠키생성
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    private MemberRepository memberRepository;

    //  성공시 토큰 생성, 쿠키생성
    @Autowired
    OAuth2AuthenticationSuccessHandler(HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository, MemberRepository memberRepository) {
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, "redirect_uri")
                .map(Cookie::getValue);

        System.out.println("리다이렉트 uri" + redirectUri);

//        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
//            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
//        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());


//        String token = tokenProvider.createToken(authentication);
        System.out.println(authentication.getPrincipal().toString());

        String name = authentication.getName();

        Member memberInfo = memberRepository.findByEmail(name);

        List<String> authority = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Algorithm algorithm = Algorithm.HMAC256("urunner".getBytes());
        String access_token = JWT.create()
                .withSubject(name)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 10000)) // 10^-3 초
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", authority)
                .withClaim("name", memberInfo.getName())
                .withClaim("nickname", memberInfo.getNickname())
                .sign(algorithm);

        System.out.println(access_token);

        Map<String, String> tokens = new HashMap<>();

//        try {
//            tokens.put("access_token", access_token);
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.sendRedirect(targetUrl);
//            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//        } catch (Exception e) {
//            System.out.println("에러");
//        }

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("access_token", access_token)
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

//    private boolean isAuthorizedRedirectUri(String uri) {
//        URI clientRedirectUri = URI.create(uri);
//
//        return appProperties.getOauth2().getAuthorizedRedirectUris()
//                .stream()
//                .anyMatch(authorizedRedirectUri -> {
//                    // Only validate host and port. Let the clients use different paths if they want to
//                    URI authorizedURI = URI.create(authorizedRedirectUri);
//                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
//                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
//                        return true;
//                    }
//                    return false;
//                });
//    }
}
