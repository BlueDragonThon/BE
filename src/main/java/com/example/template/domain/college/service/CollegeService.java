package com.example.template.domain.college.service;

import com.example.template.domain.college.dto.CollegeResponseDto;

import java.util.List;

public interface CollegeService {
    List<CollegeResponseDto> getCollegeByName(String name);
    List<CollegeResponseDto> getCollegeByProgram(String program);
}
