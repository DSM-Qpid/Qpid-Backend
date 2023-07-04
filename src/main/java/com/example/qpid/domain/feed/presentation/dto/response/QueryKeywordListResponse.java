package com.example.qpid.domain.feed.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryKeywordListResponse {
    private final List<KeywordDto> keywordList;

    @Getter
    @Builder
    public static class KeywordDto {
        private final String keyword;
    }
}
