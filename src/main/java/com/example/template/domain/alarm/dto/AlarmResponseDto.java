package com.example.template.domain.alarm.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AlarmResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Date date;
}
