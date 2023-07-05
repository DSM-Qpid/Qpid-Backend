package com.example.qpid.domain.user.service;

import com.example.qpid.domain.user.domain.User;
import com.example.qpid.domain.user.facade.UserFacade;
import com.example.qpid.domain.user.presentation.dto.response.QueryUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryUserInfoService {
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryUserInfoResponse execute() {
        User user = userFacade.getCurrentUser();
        return new QueryUserInfoResponse(user.getName());
    }
}
