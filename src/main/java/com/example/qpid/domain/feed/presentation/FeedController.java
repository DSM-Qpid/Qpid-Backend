package com.example.qpid.domain.feed.presentation;

import com.example.qpid.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.example.qpid.domain.feed.presentation.dto.request.UpdateFeedRequest;
import com.example.qpid.domain.feed.service.CreateFeedService;
import com.example.qpid.domain.feed.service.UpdateFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {
    private final CreateFeedService createFeedService;
    private final UpdateFeedService updateFeedService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFeed(@RequestBody @Valid CreateFeedRequest request) {
        createFeedService.execute(request);
    }

    @PatchMapping("/{feed-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFeed(@PathVariable("feed-id") Long feedId, @RequestBody @Valid UpdateFeedRequest request) {
        updateFeedService.execute(feedId, request);
    }
}
