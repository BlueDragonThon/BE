package com.example.template.domain.review.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewUpdateDto {
    private long reviewId;
    private String content;
}
