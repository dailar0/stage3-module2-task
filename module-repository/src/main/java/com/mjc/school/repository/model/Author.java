package com.mjc.school.repository.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Author implements BaseEntity<Long> {
    private Long id;
    private String name;
    private final LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

}
