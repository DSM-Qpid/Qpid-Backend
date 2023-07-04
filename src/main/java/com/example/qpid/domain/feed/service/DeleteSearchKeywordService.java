package com.example.qpid.domain.feed.service;

import com.example.qpid.domain.feed.domain.Search;
import com.example.qpid.domain.feed.domain.repository.SearchRepository;
import com.example.qpid.domain.feed.exception.SearchNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteSearchKeywordService {
    private final SearchRepository searchRepository;

    @Transactional
    public void execute(Long searchId) {
        Search search = searchRepository.findById(searchId).orElseThrow(() -> SearchNotFoundException.EXCEPTION);
        searchRepository.delete(search);
    }
}
