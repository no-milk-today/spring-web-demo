package com.yandex.blog.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// генерирует геттеры, сеттеры, toString, equals и hashCode.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private String tag;
    private Long likeCount;
    private LocalDateTime created;
    private LocalDateTime updated;
}

