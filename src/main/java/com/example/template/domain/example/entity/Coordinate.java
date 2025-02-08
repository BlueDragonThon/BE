package com.example.template.domain.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Coordinate {
    /**
     * 경도(가로)
     */
    @Column
    private double acr;

    /**
     * 위도(세로)
     */
    @Column
    private double dwn;
}