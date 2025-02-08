package com.example.template.domain.member.entity;

import com.example.template.domain.college.entity.Coordinate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 대학 이름
     */
    @Column(length = 50)
    private String name;

    @Min(50)
    @Max(100)
    private int age;

    @Embedded
    private Coordinate coordinate;
}
