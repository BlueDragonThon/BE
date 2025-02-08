package com.example.template.domain.review.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewRequestDto {
    private String content;
    private Long collegeId;
    private String program;
}
