package com.example.qpid.domain.user.presentation;

import com.example.qpid.domain.user.presentation.dto.request.LoginRequest;
import com.example.qpid.domain.user.presentation.dto.request.SignupRequest;
import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import com.example.qpid.domain.user.service.LoginService;
import com.example.qpid.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final SignUpService signUpService;
    private final LoginService loginService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignupRequest request) {
        signUpService.execute(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }
}
