package com.back.entity;

import com.back.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    Member author;

    public String title;

    @Column(columnDefinition = "LONGTEXT")
    public String content;

    public Post(Member author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
