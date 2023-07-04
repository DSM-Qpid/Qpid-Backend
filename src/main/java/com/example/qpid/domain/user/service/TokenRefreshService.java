package com.example.qpid.domain.user.service;

import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TokenRefreshService {
    
    private final TokenRefreshUtil tokenRefreshUtil;
    
    @Transactional
    public TokenResponse execute(String refreshToken) {
        return tokenRefreshUtil.tokenRefresh(refreshToken);
    }
}
