package com.example.template.domain.college.service;

import com.example.template.domain.college.dto.CollegeResponseDto;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;
    private static final int PAGE_SIZE = 5;

    @Override
    public List<CollegeResponseDto> getCollegeByName(String name) {
        List<College> collegeList = collegeRepository.findAllByNameContaining(name);

        return collegeList.stream()
                .map(college -> CollegeResponseDto.builder()
                        .id(college.getId())
                        .name(college.getName())
                        .contactInfo(college.getContactNo())
                        .address(college.getAddress())
                        .program(college.getProgram())
                        .build())
                .toList();
    }

    @Override
    public List<CollegeResponseDto> getCollegeByProgram(String program) {
        List<College> collegeList = collegeRepository.findAllByProgramContaining(program);

        return collegeList.stream()
                .map(college -> CollegeResponseDto.builder()
                        .id(college.getId())
                        .name(college.getName())
                        .contactInfo(college.getContactNo())
                        .address(college.getAddress())
                        .program(college.getProgram())
                        .build())
                .toList();
    }

    public Page<College> searchCollegesByDistance(Coordinate coordinate, int page) {
        return collegeRepository.searchCollegesByDistance(PageRequest.of(page-1, PAGE_SIZE),
                coordinate.getAcr(),coordinate.getDwn());
    }

    public Page<College> searchCollegesByDistance(Coordinate coordinate) {
        return searchCollegesByDistance(coordinate, 1);
    }
}
