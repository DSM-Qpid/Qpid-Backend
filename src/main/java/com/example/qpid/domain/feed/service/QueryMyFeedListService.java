package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.repository.FeedRepository;
import com.example.qpid.domain.feed.presentation.dto.response.FeedElement;
import com.example.qpid.domain.feed.presentation.dto.response.QueryMyFeedListResponse;
import com.example.qpid.domain.user.domain.User;
import com.example.qpid.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyFeedListService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyFeedListResponse execute() {
        User user = userFacade.getCurrentUser();
        List<Feed> feedList = feedRepository.findAllByUser(user);

        return new QueryMyFeedListResponse(feedList.stream()
                .map(feed -> FeedElement.builder()
                        .title(feed.getTitle())
                        .content(feed.getContent())
                        .createdAt(feed.getCreatedAt())
                        .build()).collect(Collectors.toList()));
    }
}
