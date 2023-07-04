package com.example.qpid.domain.user.service;

import com.example.qpid.domain.user.domain.User;
import com.example.qpid.domain.user.domain.repository.UserRepository;
import com.example.qpid.domain.user.exception.UserAlreadyExistException;
import com.example.qpid.domain.user.presentation.dto.request.SignupRequest;
import com.example.qpid.global.error.excpetion.QpidException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(SignupRequest request) throws QpidException {
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build()
        );
    }
}
