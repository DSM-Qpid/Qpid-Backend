package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.Search;
import com.example.qpid.domain.feed.domain.repository.FeedRepository;
import com.example.qpid.domain.feed.domain.repository.SearchRepository;
import com.example.qpid.domain.feed.exception.FeedNotFoundException;
import com.example.qpid.domain.feed.presentation.dto.response.FeedElement;
import com.example.qpid.domain.feed.presentation.dto.response.QuerySearchFeedListResponse;
import com.example.qpid.domain.user.domain.User;
import com.example.qpid.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuerySearchFeedListService {
    private final FeedRepository feedRepository;
    private final SearchRepository searchRepository;
    private final UserFacade userFacade;

    @Transactional
    public QuerySearchFeedListResponse execute(String keyword) {
        List<Feed> feedList = feedRepository.findAllByTitleContains(keyword);
        User user = userFacade.getCurrentUser();

        if (feedList == null) {
            throw FeedNotFoundException.EXCEPTION;
        }

        searchRepository.save(Search.builder()
                .keyword(keyword)
                .createdAt(LocalDateTime.now())
                .user(user)

                .build());

        return new QuerySearchFeedListResponse(feedList.stream()
                .map(feed -> FeedElement.builder()
                        .title(feed.getTitle())
                        .content(feed.getContent())
                        .createdAt(feed.getCreatedAt())
                        .build()).collect(Collectors.toList()));
    }
}
