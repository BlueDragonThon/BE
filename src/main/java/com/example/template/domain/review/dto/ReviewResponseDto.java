package com.example.template.domain.review.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponseDto {
    private int reviewId;
    private String university;
    private String program;
    private String content;
    private String writer;
    private String createdAt;
    private Boolean isUserCreated;
}
