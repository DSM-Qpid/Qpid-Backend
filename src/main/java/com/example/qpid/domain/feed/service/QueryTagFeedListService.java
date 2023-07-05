package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.repository.FeedRepository;
import com.example.qpid.domain.feed.domain.type.Tag;
import com.example.qpid.domain.feed.presentation.dto.response.FeedElement;
import com.example.qpid.domain.feed.presentation.dto.response.QueryTagFeedListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryTagFeedListService {
    private final FeedRepository feedRepository;

    @Transactional
    public QueryTagFeedListResponse execute(Tag tag) {
        List<Feed> feedList = feedRepository.findAllByTag(tag);

        return new QueryTagFeedListResponse(feedList.stream()
                .map(feed -> FeedElement.builder()
                        .id(feed.getId())
                        .title(feed.getTitle())
                        .content(feed.getContent())
                        .createdAt(feed.getCreatedAt())
                        .build())
                .collect(Collectors.toList()));
    }
}
