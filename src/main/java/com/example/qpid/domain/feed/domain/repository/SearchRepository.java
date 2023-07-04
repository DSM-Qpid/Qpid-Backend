package com.example.qpid.domain.feed.domain.repository;

import com.example.qpid.domain.feed.domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
}
