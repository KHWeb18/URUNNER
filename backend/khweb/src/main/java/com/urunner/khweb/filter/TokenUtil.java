package com.urunner.khweb.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {

    public static DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("urunner".getBytes());

            JWTVerifier verifier = JWT.require(algorithm).build();

            return verifier.verify(token);
        } catch (SignatureVerificationException e) {
            log.info("Request to parse JWT with invalid signature");
        } catch (TokenExpiredException e) {
            log.info("Request to parse expired JWT");
        }
        return null;
    }
}
