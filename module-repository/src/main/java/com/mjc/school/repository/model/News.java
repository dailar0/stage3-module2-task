package com.mjc.school.repository.model;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class News implements BaseEntity<Long> {
    private Long id;
    private String title;
    private String content;
    private final LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;
}
