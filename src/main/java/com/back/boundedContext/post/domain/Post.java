package com.back.boundedContext.post.domain;

import com.back.boundedContext.member.domain.Member;
import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.dto.PostCommentDto;
import com.back.shared.post.event.PostCommentCreatedEvent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    public Member author;

    public String title;

    @Column(columnDefinition = "LONGTEXT")
    public String content;

    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    public List<PostComment> comments = new ArrayList<>();

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public PostComment addComment(Member author, String content) {
        PostComment postComment = new PostComment(author, this, content);
        comments.add(postComment);

        publishEvent(new PostCommentCreatedEvent(new PostCommentDto(postComment)));

        return postComment;
    }

    public Boolean hasComments() {
        return !comments.isEmpty();
    }
}
