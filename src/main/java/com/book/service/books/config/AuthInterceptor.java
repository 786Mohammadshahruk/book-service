package com.book.service.books.config;

import com.book.service.books.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            response.setStatus(401);
            return false;
        }

        try {
            String userId = verifyToken(authorization, "http://localhost:8090/default/jwks");
            request.setAttribute("userId", userId);
            System.out.println("User id " + userId);
        } catch (InvalidTokenException e) {
            response.setStatus(401);
            return false;
        }

        return true;
    }

    public String verifyToken(String token, String jwksUrl) throws InvalidTokenException {
        try {
            HttpsJwks httpsJkws = new HttpsJwks(jwksUrl);
            HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);

            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime()
                    .setVerificationKeyResolver(httpsJwksKeyResolver)
                    .setSkipDefaultAudienceValidation()
                    .build();

            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);

            return jwtClaims.getClaimValueAsString("sub");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }

}
