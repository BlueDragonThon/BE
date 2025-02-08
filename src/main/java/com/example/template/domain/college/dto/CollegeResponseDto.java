package com.example.template.domain.college.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeResponseDto {
    private Long id;
    private String name;
    private String contactInfo;
    private String address;
    private String program;
}
