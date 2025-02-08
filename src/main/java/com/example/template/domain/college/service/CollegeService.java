package com.example.template.domain.college.service;

import com.example.template.domain.college.dto.CollegeSearchDTO;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface CollegeService {
    CollegeSearchDTO getCollegeByName(String name);
    CollegeSearchDTO getCollegeByProgram(String program);
    CollegeSearchDTO getCollegeByName(String name, int page);
    CollegeSearchDTO getCollegeByProgram(String program, int page);
    CollegeSearchDTO searchCollegesByDistance(Coordinate coordinate, int page);
    CollegeSearchDTO searchCollegesByDistance(Coordinate coordinate);
    Long createMemberCollege(Member member, Long collegeId);
    CollegeSearchDTO getCollegeByName(Member member, String name, int page);
    CollegeSearchDTO getCollegeByProgram(Member member, String program, int page);
    CollegeSearchDTO getCollegeByName(Member member, String name);
    CollegeSearchDTO getCollegeByProgram(Member member, String program);
    CollegeSearchDTO searchCollegesByDistance(Member member, Coordinate coordinate, int page);
    CollegeSearchDTO searchCollegesByDistance(Member member, Coordinate coordinate);
    CollegeSearchDTO searchFavorites(Member member, int page);
    CollegeSearchDTO searchFavorites(Member member);
}