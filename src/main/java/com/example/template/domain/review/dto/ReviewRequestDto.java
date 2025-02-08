package com.example.template.domain.review.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewRequestDto {
    private String title;
    private String content;
}
