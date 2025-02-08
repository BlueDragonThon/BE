package com.example.template.domain.member.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberRequestDto {
    String name;
    int age;
    private double acr;
    private double dwn;
}
