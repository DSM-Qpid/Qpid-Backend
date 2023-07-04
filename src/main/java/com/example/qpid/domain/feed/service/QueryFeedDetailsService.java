package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.repository.FeedRepository;
import com.example.qpid.domain.feed.exception.FeedNotFoundException;
import com.example.qpid.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryFeedDetailsService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public QueryFeedDetailsResponse execute(Long feedId) {
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        return new QueryFeedDetailsResponse(feed.getTitle(), feed.getContent(), feed.getTag(), feed.getCreatedAt());
    }
}
