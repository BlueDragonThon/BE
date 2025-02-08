package com.example.template.domain.review.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewRequestDto {
    private String content;
    private String college;
    private String program;
}
