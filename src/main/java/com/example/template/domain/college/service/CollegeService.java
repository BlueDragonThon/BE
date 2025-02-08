package com.example.template.domain.college.service;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CollegeService {
    Page<College> getCollegeByName(String name);
    Page<College> getCollegeByProgram(String program);
    Page<College> getCollegeByName(String name, int page);
    Page<College> getCollegeByProgram(String program, int page);
    Page<College> searchCollegesByDistance(Coordinate coordinate, int page);
    Page<College> searchCollegesByDistance(Coordinate coordinate);
    Long createMemberCollege(Member member, Long collegeId);
}