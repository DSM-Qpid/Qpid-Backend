package com.example.qpid.domain.feed.domain.repository;

import com.example.qpid.domain.feed.domain.Feed;
import com.example.qpid.domain.feed.domain.type.Tag;
import com.example.qpid.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByUser(User user);
    List<Feed> findAllByTitleContaining(String keyword);
    List<Feed> findAllByTag(Tag tag);
}
