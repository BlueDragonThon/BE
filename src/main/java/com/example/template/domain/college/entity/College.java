package com.example.template.domain.college.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 대학 이름
     */
    @Column(length = 50)
    private String name;

    /**
     * 대학장
     */
    @Column(length = 30)
    private String headmaster;

    /**
     * 연락처
     */
    @Column(length = 16)
    private String contactNo;

    /**
     * 주소
     */
    @Column(length = 255)
    private String address;

    /**
     * 위도, 경도
     */
    @Embedded
    private Coordinate coordinate;

    private String program;
}