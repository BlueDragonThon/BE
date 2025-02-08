package com.example.template.domain.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Address {
    /**
     * 도(서울시는 미사용으로 서울시 데이터 넣음)
     */
    @Column(length = 5)
    private String doData;

    /**
     * 시
     */
    @Column(length = 5)
    private String siData;

    /**
     * 구
     */
    @Column(length = 5)
    private String guData;

    /**
     * 남은 데이터
     */
    @Column(length = 128)
    private String leftData;
}