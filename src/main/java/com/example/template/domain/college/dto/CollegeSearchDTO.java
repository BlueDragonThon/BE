package com.example.template.domain.college.dto;

import com.example.template.domain.college.entity.College;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class CollegeSearchDTO{
    private List<College> result;
    private int pageCount;

    public CollegeSearchDTO(Page<College> resData) {
        result = resData.toList();
        pageCount = resData.getTotalPages();
    }
}