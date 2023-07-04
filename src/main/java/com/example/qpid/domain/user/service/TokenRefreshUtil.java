package com.example.qpid.domain.user.service;

import com.example.qpid.domain.user.domain.repository.RefreshTokenRepository;
import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import com.example.qpid.global.exception.InvalidTokenException;
import com.example.qpid.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenRefreshUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${auth.jwt.refreshExp}") private long ttl;

    public TokenResponse tokenRefresh(String refreshToken) {
        if (jwtTokenProvider.isNotRefreshToken(refreshToken)) {
            throw InvalidTokenException.EXCEPTION;
        }

        return refreshTokenRepository
                .findByToken(refreshToken)
                .map(token -> {
                    String id = token.getId();

                    TokenResponse tokenResponse = jwtTokenProvider.generateToken(id);
                    token.update(tokenResponse.getRefreshToken(), ttl);
                    return new TokenResponse(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
                })
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }
}