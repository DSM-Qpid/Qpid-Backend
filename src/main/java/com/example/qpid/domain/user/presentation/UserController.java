package com.example.qpid.domain.user.presentation;

import com.example.qpid.domain.user.presentation.dto.request.LoginRequest;
import com.example.qpid.domain.user.presentation.dto.request.SignupRequest;
import com.example.qpid.domain.user.presentation.dto.response.QueryUserInfoResponse;
import com.example.qpid.domain.user.presentation.dto.response.TokenResponse;
import com.example.qpid.domain.user.service.LoginService;
import com.example.qpid.domain.user.service.QueryUserInfoService;
import com.example.qpid.domain.user.service.SignUpService;
import com.example.qpid.domain.user.service.TokenRefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final SignUpService signUpService;
    private final LoginService loginService;
    private final TokenRefreshService tokenRefreshService;
    private final QueryUserInfoService queryUserInfoService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignupRequest request) {
        signUpService.execute(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

    @PutMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse refreshToken(@RequestHeader("Authorization") String refreshToken) {
        return tokenRefreshService.execute(refreshToken);
    }

    @GetMapping("/info")
    public QueryUserInfoResponse queryUserInfo() {
        return queryUserInfoService.execute();
    }
}
