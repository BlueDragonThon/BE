package com.example.template.domain.review.dto;

import com.example.template.domain.member.entity.Member;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewResponseDto {
    private long id;
    private String title;
    private String content;
    private String writer;
    private Boolean isUserCreated;
}
