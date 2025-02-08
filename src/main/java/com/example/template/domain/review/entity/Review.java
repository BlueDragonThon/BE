package com.example.template.domain.review.entity;

import com.example.template.common.entity.BaseTimeEntity;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private String program;

    @ManyToOne
    private Member member;

    @ManyToOne
    private College college;
}
