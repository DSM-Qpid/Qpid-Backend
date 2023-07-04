package com.example.qpid.domain.feed.domain.repository;

import com.example.qpid.domain.feed.domain.Search;
import com.example.qpid.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long> {
    List<Search> findAllByUser(User user);
}
