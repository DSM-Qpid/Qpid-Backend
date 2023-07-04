package com.example.qpid.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class QueryAllFeedListResponse {
    private final List<FeedElement> feedList;
}
