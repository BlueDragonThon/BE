package com.example.template.domain.alarm.entity;

import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

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

    private Date date;

}
