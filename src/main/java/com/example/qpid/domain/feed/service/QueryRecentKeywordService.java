package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Search;
import com.example.qpid.domain.feed.domain.repository.SearchRepository;
import com.example.qpid.domain.feed.presentation.dto.response.QueryKeywordListResponse;
import com.example.qpid.domain.user.domain.User;
import com.example.qpid.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRecentKeywordService {
    private final SearchRepository searchRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryKeywordListResponse execute() {
        User user = userFacade.getCurrentUser();
        List<Search> searchList = searchRepository.findAllByUser(user);

        return new QueryKeywordListResponse(searchList.stream()
                .map(search -> QueryKeywordListResponse.KeywordDto.builder()
                        .keyword(search.getKeyword())
                        .build()).collect(Collectors.toList()));

    }
}
