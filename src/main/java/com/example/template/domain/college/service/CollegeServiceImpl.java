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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;
    private final MemberCollegeRepository memberCollegeRepository;
    private final AlarmService alarmService;
    private static final int PAGE_SIZE = 5;

    public CollegeSearchDTO getCollegeByName(String name, int page) {
        log.info("Searching colleges by name: {}, page: {}", name, page);
        return new CollegeSearchDTO(
                collegeRepository.findAllByNameContaining(PageRequest.of(page - 1, PAGE_SIZE), name));
    }

    public CollegeSearchDTO getCollegeByProgram(String program, int page) {
        log.info("Searching colleges by program: {}, page: {}", program, page);
        return new CollegeSearchDTO(
                collegeRepository.findAllByProgramContaining(PageRequest.of(page - 1, PAGE_SIZE), program));
    }

    @Override
    public CollegeSearchDTO getCollegeByName(String name) {
        log.info("Searching colleges by name: {}", name);
        return getCollegeByName(name, 1);
    }

    @Override
    public CollegeSearchDTO getCollegeByProgram(String program) {
        log.info("Searching colleges by program: {}", program);
        return getCollegeByProgram(program, 1);
    }

    public CollegeSearchDTO searchCollegesByDistance(Coordinate coordinate, int page) {
        log.info("Searching colleges by distance, page: {}, coordinates: ({}, {})", page, coordinate.getAcr(), coordinate.getDwn());
        return new CollegeSearchDTO(
                collegeRepository.searchCollegesByDistance(PageRequest.of(page - 1, PAGE_SIZE),
                        coordinate.getAcr(), coordinate.getDwn()));
    }

    public CollegeSearchDTO searchCollegesByDistance(Coordinate coordinate) {
        log.info("Searching colleges by distance, coordinates: ({}, {})", coordinate.getAcr(), coordinate.getDwn());
        return searchCollegesByDistance(coordinate, 1);
    }

    @Override
    public Long createMemberCollege(Member member, Long collegeId) {
        log.info("Member {} is adding college with ID: {}", member.getName(), collegeId);

        College college = collegeRepository.findById(collegeId).orElseThrow(() -> new GeneralHandler(ErrorCode._BAD_REQUEST));

        MemberCollege memberCollege = MemberCollege.builder()
                .member(member)
                .college(college)
                .build();

        memberCollegeRepository.save(memberCollege);

        alarmService.createAlarm(memberCollege);

        log.info("Member college added with ID: {}", memberCollege.getId());
        return memberCollege.getId();
    }

    @Transactional
    public Long deleteMemberCollege(Member member, Long collegeId) {
        log.info("Member {} is deleting college with ID: {}", member.getName(), collegeId);

        College college = collegeRepository.findById(collegeId).orElseThrow(() -> new GeneralHandler(ErrorCode._BAD_REQUEST));

        Optional<MemberCollege> memberCollege = memberCollegeRepository.findByMemberAndCollege(member, college);

        if (memberCollege.isPresent()) {
            MemberCollege removeData = memberCollege.get();
            memberCollegeRepository.delete(removeData);
            alarmService.deleteAlarm(removeData);

            log.info("Member college with ID {} deleted successfully", removeData.getId());
            return removeData.getId();
        }
        throw new GeneralHandler(ErrorCode._BAD_REQUEST);
    }

    @Override
    public CollegeSearchDTO getCollegeByName(Member member, String name, int page) {
        Page<College> allByNameContaining = collegeRepository.findAllByNameContaining(PageRequest.of(page - 1, PAGE_SIZE), name);
        List<CollegeResponseDto> result = new ArrayList<>(PAGE_SIZE);
        for (College college : allByNameContaining) {
            result.add(new CollegeResponseDto(college,
                    memberCollegeRepository.findByMemberAndCollege(member, college).isPresent()));
        }
        return CollegeSearchDTO.builder()
                .result(result)
                .pageCount(allByNameContaining.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO getCollegeByProgram(Member member, String program, int page) {
        Page<College> allByNameContaining = collegeRepository.findAllByProgramContaining(PageRequest.of(page - 1, PAGE_SIZE), program);
        List<CollegeResponseDto> result = new ArrayList<>(PAGE_SIZE);
        for (College college : allByNameContaining) {
            result.add(new CollegeResponseDto(college,
                    memberCollegeRepository.findByMemberAndCollege(member, college).isPresent()));
        }
        return CollegeSearchDTO.builder()
                .result(result)
                .pageCount(allByNameContaining.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO getCollegeByName(Member member, String name) {
        log.info("Searching colleges by name for member: {}, name: {}", member.getName(), name);
        return getCollegeByName(member, name, 1);
    }

    @Override
    public CollegeSearchDTO getCollegeByProgram(Member member, String program) {
        log.info("Searching colleges by program for member: {}, program: {}", member.getName(), program);
        return getCollegeByProgram(member, program, 1);
    }

    @Override
    public CollegeSearchDTO searchCollegesByDistance(Member member, Coordinate coordinate, int page) {
        log.info("Searching colleges by distance for member: {}, page: {}, coordinates: ({}, {})", member.getName(), page, coordinate.getAcr(), coordinate.getDwn());
        Page<CollegeResponseDto> result = collegeRepository.searchCollegesByDistanceWithFavorites(
                PageRequest.of(page - 1, PAGE_SIZE),
                member, coordinate.getAcr(), coordinate.getDwn());
        return CollegeSearchDTO.builder()
                .result(result.toList())
                .pageCount(result.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO searchCollegesByDistance(Member member, Coordinate coordinate) {
        log.info("Searching colleges by distance for member: {}, coordinates: ({}, {})", member.getName(), coordinate.getAcr(), coordinate.getDwn());
        return searchCollegesByDistance(member, coordinate, 1);
    }

    @Override
    public CollegeSearchDTO searchFavorites(Member member, int page) {
        log.info("Searching favorite colleges for member: {}, page: {}", member.getName(), page);
        Page<College> byFavorites = memberCollegeRepository.findByFavorites(
                PageRequest.of(page - 1, PAGE_SIZE), member);
        return CollegeSearchDTO.builder()
                .result(byFavorites.stream().map(c -> new CollegeResponseDto(c, true)).toList())
                .pageCount(byFavorites.getTotalPages())
                .build();
    }

    @Override
    public CollegeSearchDTO searchFavorites(Member member) {
        log.info("Searching favorite colleges for member: {}", member.getName());
        return searchFavorites(member, 1);
    }
}
