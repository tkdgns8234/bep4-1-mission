package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
public class PostFacade {
    private final PostQueryUseCase postQueryUseCase;
    private final PostMemberWriteUseCase postMemberWriteUseCase;
    private final PostCommandUseCase postCommandUseCase;
    private final PostMemberQueryUseCase postMemberQueryUseCase;

    public RsData<Post> write(PostMember author, String title, String content) {
        return postMemberWriteUseCase.write(author, title, content);
    }

    @Transactional
    public PostMember syncMember(MemberDto memberDto) {
        PostMember postMember = new PostMember(
                memberDto.id(),
                memberDto.createDate(),
                memberDto.modifyDate(),
                memberDto.username(),
                "",
                memberDto.nickname()
        );

        return postCommandUseCase.savePostMember(postMember);
    }

    public Long count() {
        return postQueryUseCase.count();
    }

    public Optional<Post> findById(int id) {
        return postQueryUseCase.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<PostMember> findPostMemberByUsername(String username) {
        return postMemberQueryUseCase.findByUsername(username);
    }
}
