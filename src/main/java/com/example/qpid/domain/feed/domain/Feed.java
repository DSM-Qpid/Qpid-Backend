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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    @Column(columnDefinition = "char(5)", nullable = false)
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Feed(String title, String content, Tag tag, User user) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.user = user;
    }
}
