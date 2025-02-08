package com.example.template.domain.college.service;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;
    private static final int PAGE_SIZE = 5;

    public Page<College> getCollegeByName(String name, int page) {
        return collegeRepository.findAllByNameContaining(PageRequest.of(page-1, PAGE_SIZE),name);
    }

    public Page<College> getCollegeByProgram(String program, int page) {
        return collegeRepository.findAllByProgramContaining(PageRequest.of(page-1, PAGE_SIZE),program);
    }

    @Override
    public Page<College> getCollegeByName(String name) {
        return getCollegeByName(name, 1);
    }

    @Override
    public Page<College> getCollegeByProgram(String program) {
        return getCollegeByProgram(program, 1);
    }

    public Page<College> searchCollegesByDistance(Coordinate coordinate, int page) {
        return collegeRepository.searchCollegesByDistance(PageRequest.of(page-1, PAGE_SIZE),
                coordinate.getAcr(),coordinate.getDwn());
    }

    public Page<College> searchCollegesByDistance(Coordinate coordinate) {
        return searchCollegesByDistance(coordinate, 1);
    }
}
