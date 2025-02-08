package com.example.template.domain.review.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponseDto {
    private long id;
    private String title;
    private String collegeName;
    private String programName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String content;
    private String writer;
    private Boolean isUserCreated;
}
