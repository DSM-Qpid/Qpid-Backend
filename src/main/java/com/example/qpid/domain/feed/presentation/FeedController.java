package com.example.qpid.domain.feed.presentation;

import com.example.qpid.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.example.qpid.domain.feed.presentation.dto.request.UpdateFeedRequest;
import com.example.qpid.domain.feed.presentation.dto.response.QueryAllFeedListResponse;
import com.example.qpid.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import com.example.qpid.domain.feed.presentation.dto.response.QueryKeywordListResponse;
import com.example.qpid.domain.feed.presentation.dto.response.QueryMyFeedListResponse;
import com.example.qpid.domain.feed.presentation.dto.response.QuerySearchFeedListResponse;
import com.example.qpid.domain.feed.service.CreateFeedService;
import com.example.qpid.domain.feed.service.DeleteFeedService;
import com.example.qpid.domain.feed.service.DeleteSearchKeywordService;
import com.example.qpid.domain.feed.service.QueryAllFeedListService;
import com.example.qpid.domain.feed.service.QueryFeedDetailsService;
import com.example.qpid.domain.feed.service.QueryMyFeedListService;
import com.example.qpid.domain.feed.service.QueryRecentKeywordService;
import com.example.qpid.domain.feed.service.QuerySearchFeedListService;
import com.example.qpid.domain.feed.service.UpdateFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {
    private final CreateFeedService createFeedService;
    private final UpdateFeedService updateFeedService;
    private final DeleteFeedService deleteFeedService;
    private final QueryAllFeedListService queryAllFeedListService;
    private final QueryMyFeedListService queryMyFeedListService;
    private final QueryFeedDetailsService queryFeedDetailsService;
    private final QuerySearchFeedListService querySearchFeedListService;
    private final QueryRecentKeywordService queryRecentKeywordService;
    private final DeleteSearchKeywordService deleteSearchKeywordService;

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

    @DeleteMapping("/{feed-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeed(@PathVariable("feed-id") Long feedId) {
        deleteFeedService.execute(feedId);
    }

    @GetMapping("/all")
    public QueryAllFeedListResponse queryAllFeed() {
        return queryAllFeedListService.execute();
    }

    @GetMapping("/mine")
    public QueryMyFeedListResponse queryMyFeedList() {
        return  queryMyFeedListService.execute();
    }

    @GetMapping("/details/{feed-id}")
    public QueryFeedDetailsResponse queryFeedDetails(@PathVariable("feed-id") Long feedId) {
        return queryFeedDetailsService.execute(feedId);
    }

    @GetMapping("/search")
    public QuerySearchFeedListResponse querySearchFeedList(@RequestParam("keyword") String keyword) {
        return querySearchFeedListService.execute(keyword);
    }

    @GetMapping("/recent/keyword")
    public QueryKeywordListResponse queryKeywordList() {
        return queryRecentKeywordService.execute();
    }

    @GetMapping("/recent/{search-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKeyword(@PathVariable("search-id") Long searchId) {
        deleteSearchKeywordService.execute(searchId);
    }
}
