package com.example.qpid.domain.user.service;

import com.example.qpid.domain.user.domain.repository.UserRepository;
import com.example.qpid.domain.user.exception.UserNotFoundException;
import com.example.qpid.domain.user.presentation.dto.request.LoginRequest;
import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import com.example.qpid.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(LoginRequest request) {
        return userRepository.findByAccountId(request.getAccountId())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> jwtTokenProvider.generateToken(user.getAccountId()))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
