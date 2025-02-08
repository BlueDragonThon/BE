package com.example.template.domain.college.dto;

import com.example.template.domain.college.entity.College;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CollegeSearchDTO{
    private List<CollegeResponseDto> result;
    private int pageCount;

    public CollegeSearchDTO(Page<College> resData) {
        result = resData.stream()
                .map(CollegeResponseDto::new)
                .toList();
        pageCount = resData.getTotalPages();
    }
}