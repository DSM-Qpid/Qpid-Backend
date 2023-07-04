package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.repository.FeedRepository;
import com.example.qpid.domain.feed.exception.FeedNotFoundException;
import com.example.qpid.domain.feed.exception.WriterMisMatchedException;
import com.example.qpid.domain.user.domain.User;
import com.example.qpid.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteFeedService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();
        Feed feed = feedRepository.findById(feedId).orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if(!feed.getUserId().equals(user.getId())) {
            throw WriterMisMatchedException.EXCEPTION;
        }

        feedRepository.delete(feed);
    }
}
