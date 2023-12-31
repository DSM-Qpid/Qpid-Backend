package com.example.qpid.domain.feed.domain;

import com.example.qpid.domain.feed.domain.type.Tag;
import com.example.qpid.domain.user.domain.User;
import com.example.qpid.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_feed")
public class Feed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String title;

    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String content;

    private LocalDateTime createdAt;

    @Column(columnDefinition = "char(5)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Feed(String title, String content,LocalDateTime createdAt, Tag tag, User user) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.tag = tag;
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    public void updateFeed(String title, String content, Tag tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }
}
