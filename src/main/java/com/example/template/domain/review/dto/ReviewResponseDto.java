package com.example.template.domain.review.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponseDto {
    private long id;
    private String collegeName;
    private String programName;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
    private String content;
    private String writer;
    private Boolean isUserCreated;
}
