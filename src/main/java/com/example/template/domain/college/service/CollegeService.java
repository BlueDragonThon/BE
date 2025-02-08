package com.example.template.domain.college.service;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.template.domain.college.dto.CollegeResponseDto;

import java.util.List;

@Service
public interface CollegeService {
    List<CollegeResponseDto> getCollegeByName(String name);
    List<CollegeResponseDto> getCollegeByProgram(String program);
    Page<College> searchCollegesByDistance(Coordinate coordinate, int page);
    Page<College> searchCollegesByDistance(Coordinate coordinate);
}