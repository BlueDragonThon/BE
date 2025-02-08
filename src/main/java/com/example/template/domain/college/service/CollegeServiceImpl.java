package com.example.template.domain.college.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.domain.alarm.service.AlarmService;
import com.example.template.domain.college.dto.CollegeResponseDto;
import com.example.template.domain.college.dto.CollegeSearchDTO;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.repository.CollegeRepository;
import com.example.template.domain.college.repository.MemberCollegeRepository;
import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;
    private final MemberCollegeRepository memberCollegeRepository;
    private final AlarmService alarmService;
    private static final int PAGE_SIZE = 5;

    public CollegeSearchDTO getCollegeByName(String name, int page) {
        return new CollegeSearchDTO(
                collegeRepository.findAllByNameContaining(PageRequest.of(page-1, PAGE_SIZE),name));
    }

    public CollegeSearchDTO getCollegeByProgram(String program, int page) {
        return new CollegeSearchDTO(
                collegeRepository.findAllByProgramContaining(PageRequest.of(page-1, PAGE_SIZE),program));
    }

    @Override
    public CollegeSearchDTO getCollegeByName(String name) {
        return getCollegeByName(name, 1);
    }

    @Override
    public CollegeSearchDTO getCollegeByProgram(String program) {
        return getCollegeByProgram(program, 1);
    }

    public CollegeSearchDTO searchCollegesByDistance(Coordinate coordinate, int page) {
        return new CollegeSearchDTO(
                collegeRepository.searchCollegesByDistance(PageRequest.of(page-1, PAGE_SIZE),
                coordinate.getAcr(),coordinate.getDwn()));
    }

    public CollegeSearchDTO searchCollegesByDistance(Coordinate coordinate) {
        return searchCollegesByDistance(coordinate, 1);
    }

    @Override
    public Long createMemberCollege(Member member, Long collegeId) {

        College college = collegeRepository.findById(collegeId).orElseThrow(()-> new GeneralHandler(ErrorCode._BAD_REQUEST));

        MemberCollege memberCollege = MemberCollege.builder()
                 .member(member)
                .college(college)
                .build();

        memberCollegeRepository.save(memberCollege);

        alarmService.createAlarm(memberCollege);

        return memberCollege.getId();
    }

    @Override
    public CollegeSearchDTO getCollegeByName(Member member, String name, int page) {
        Page<CollegeResponseDto> result = collegeRepository.findAllByNameWithFavorites
                (PageRequest.of(page - 1, PAGE_SIZE), '%'+name+'%', member);
        return CollegeSearchDTO.builder()
                .result(result.toList())
                .pageCount(result.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO getCollegeByProgram(Member member, String program, int page) {
        Page<CollegeResponseDto> result = collegeRepository.findAllByProgramWithFavorites
                (PageRequest.of(page - 1, PAGE_SIZE), '%'+program+'%', member);
        return CollegeSearchDTO.builder()
                .result(result.toList())
                .pageCount(result.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO getCollegeByName(Member member, String name) {
        return getCollegeByName(member, name, 1);
    }

    @Override
    public CollegeSearchDTO getCollegeByProgram(Member member, String program) {
        return getCollegeByProgram(member, program, 1);
    }

    @Override
    public CollegeSearchDTO searchCollegesByDistance(Member member, Coordinate coordinate, int page) {
        Page<CollegeResponseDto> result = collegeRepository.searchCollegesByDistanceWithFavorites(
                PageRequest.of(page-1, PAGE_SIZE),
                coordinate.getAcr(),coordinate.getDwn(),member);
        return CollegeSearchDTO.builder()
                .result(result.toList())
                .pageCount(result.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO searchCollegesByDistance(Member member, Coordinate coordinate) {
        return searchCollegesByDistance(member, coordinate, 1);
    }

    @Override
    public CollegeSearchDTO searchFavorites(Member member, int page) {
        return new CollegeSearchDTO(
                memberCollegeRepository.findByFavorites(
                        PageRequest.of(page-1, PAGE_SIZE),member));
    }

    @Override
    public CollegeSearchDTO searchFavorites(Member member) {
        return searchFavorites(member, 1);
    }
}

