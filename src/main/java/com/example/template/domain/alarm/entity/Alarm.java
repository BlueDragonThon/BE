package com.example.template.domain.alarm.entity;

import com.example.template.domain.mapping.MemberCollege;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private MemberCollege memberCollege;

    private String title;

    private String content;

    private LocalDate date;

}
