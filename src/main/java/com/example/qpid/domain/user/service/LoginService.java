package com.example.qpid.domain.user.service;

import com.example.qpid.domain.user.domain.repository.UserRepository;
import com.example.qpid.domain.user.exception.UserNotFoundException;
import com.example.qpid.domain.user.presentation.dto.request.LoginRequest;
import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import com.example.qpid.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(LoginRequest request) {
        var userEntity = userRepository.findByAccountId(request.getAccountId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if(!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) throw UserNotFoundException.EXCEPTION;

        return jwtTokenProvider.generateToken(userEntity.getAccountId());

    }
}
