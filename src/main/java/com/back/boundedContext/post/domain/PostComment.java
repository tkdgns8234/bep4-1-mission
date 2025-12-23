package com.back.boundedContext.post.domain;

import com.back.boundedContext.member.domain.Member;
import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class PostComment extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    Post post;

    @Column(columnDefinition = "TEXT")
    String comment;

    PostComment(Member author, Post post, String comment) {
        this.author = author;
        this.post = post;
        this.comment = comment;
    }
}
