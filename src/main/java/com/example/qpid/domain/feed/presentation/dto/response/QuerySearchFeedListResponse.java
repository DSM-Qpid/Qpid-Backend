package com.example.qpid.domain.feed.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuerySearchFeedListResponse {
    private final List<FeedElement> feedList;
}
