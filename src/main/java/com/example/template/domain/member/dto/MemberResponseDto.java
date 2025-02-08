package com.example.template.domain.member.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberResponseDto {
    long id;
    String name;
    String token;
}
