package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class PostFacade {
    private final PostQueryUseCase postQueryUseCase;
    private final PostUseCase postUseCase;

    public Post write(Member author, String title, String content) {
        return postUseCase.write(author, title, content);
    }

    public Long count() {
        return postQueryUseCase.count();
    }

    public Optional<Post> findById(int id) {
        return postQueryUseCase.findById(id);
    }
}
