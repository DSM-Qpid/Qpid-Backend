package com.example.qpid.domain.feed.presentation.dto.response;

import com.example.qpid.domain.feed.domain.type.Tag;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedElement {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
}
