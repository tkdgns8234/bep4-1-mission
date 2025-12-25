package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(int id, LocalDateTime createDate, LocalDateTime modifyDate,
                             int postId, int authorId, String authorName, String content) {

    public PostCommentDto(PostComment post) {
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getId(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getComment()
        );
    }
}
