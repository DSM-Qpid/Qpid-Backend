package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.repository.FeedRepository;
import com.example.qpid.domain.feed.presentation.dto.response.FeedElement;
import com.example.qpid.domain.feed.presentation.dto.response.QueryAllFeedListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryAllFeedListService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public QueryAllFeedListResponse execute() {
        List<Feed> feedList = feedRepository.findAll().stream().toList();

        return QueryAllFeedListResponse.builder()
                .feedList(feedList.stream()
                        .map(feed ->FeedElement.builder()
                                .title(feed.getTitle())
                                .content(feed.getContent())
                                .createdAt(feed.getCreatedAt())
                                .build())
                        .collect(Collectors.toList()))
                .build();

    }
}
