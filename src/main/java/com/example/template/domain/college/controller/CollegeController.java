package com.example.template.domain.college.controller;

import com.example.template.common.annotation.AuthUser;
import com.example.template.common.response.ApiResponse;
import com.example.template.domain.college.dto.*;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.service.CollegeService;
import com.example.template.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "캐스트 API", description = "캐스트 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/college")
@Slf4j
public class CollegeController {

    private final CollegeService collegeService;

    @PostMapping("/search")
    @Operation(summary = "직선거리 기준 검색(사용자 데이터에 저장된 위치 값 이용)")
    public ApiResponse<CollegeSearchDTO> searchCollegesByMemberDistance(@Parameter(hidden = true) @AuthUser Member member,
                                                                        @Nullable @RequestParam("page") Integer page) {
        log.info("searchCollegesByMemberDistance 호출, member: {}, page: {}", member, page);

        CollegeSearchDTO result;
        Coordinate coordinate = member.getCoordinate();

        if (coordinate == null) {
            log.error("위도, 경도 정보가 없습니다.");
            throw new NullPointerException("위도, 경도 정보가 없습니다.");
        }

        if (page == null) {
            log.info("페이지 정보가 없으므로 기본 검색 수행.");
            result = collegeService.searchCollegesByDistance(member, coordinate);
        } else {
            log.info("페이지 정보가 있으므로 페이지 {}에 대한 검색 수행.", page);
            result = collegeService.searchCollegesByDistance(member, coordinate, page);
        }
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/distance")
    @Operation(summary = "직선거리 기준 검색(별도 제공된 위치 값 이용)")
    public ApiResponse<CollegeSearchDTO> searchCollegesByDistance(@Parameter(hidden = true) @AuthUser Member member,
                                                                  @RequestBody CollegeSearchParamDTO param) {
        log.info("searchCollegesByDistance 호출, member: {}, param: {}", member, param);

        CollegeSearchDTO result;
        if (param.getAcr() == null || param.getDwn() == null) {
            log.error("위도, 경도 정보가 없습니다.");
            throw new NullPointerException("위도, 경도 정보가 없습니다.");
        }

        Coordinate coordinate = Coordinate.builder()
                .acr(param.getAcr())
                .dwn(param.getDwn()).build();

        if (member != null) {
            if (param.getPage() == null) {
                log.info("페이지 정보가 없으므로 기본 검색 수행.");
                result = collegeService.searchCollegesByDistance(member, coordinate);
            } else {
                log.info("페이지 정보가 있으므로 페이지 {}에 대한 검색 수행.", param.getPage());
                result = collegeService.searchCollegesByDistance(member, coordinate, param.getPage());
            }
        } else {
            if (param.getPage() == null) {
                log.info("회원 정보가 없으므로 기본 검색 수행.");
                result = collegeService.searchCollegesByDistance(coordinate);
            } else {
                log.info("회원 정보가 없으므로 페이지 {}에 대한 검색 수행.", param.getPage());
                result = collegeService.searchCollegesByDistance(coordinate, param.getPage());
            }
        }
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/name")
    @Operation(summary = "대학 이름 기반으로 검색")
    public ApiResponse<CollegeSearchDTO> searchCollegeByName(@Parameter(hidden = true) @AuthUser Member member,
                                                             @RequestBody NamePageDto dto) {
        log.info("searchCollegeByName 호출, member: {}, dto: {}", member, dto);

        String name = dto.getName();
        CollegeSearchDTO result;

        if (name == null || name.isBlank()) {
            log.error("이름이 없습니다.");
            throw new NullPointerException("이름이 없습니다.");
        }

        if (member != null) {
            if (dto.getPage() == null) {
                log.info("페이지 정보가 없으므로 기본 검색 수행.");
                result = collegeService.getCollegeByName(member, name);
            } else {
                log.info("페이지 정보가 있으므로 페이지 {}에 대한 검색 수행.", dto.getPage());
                result = collegeService.getCollegeByName(member, name, dto.getPage());
            }
        } else {
            if (dto.getPage() == null) {
                log.info("회원 정보가 없으므로 기본 검색 수행.");
                result = collegeService.getCollegeByName(name);
            } else {
                log.info("회원 정보가 없으므로 페이지 {}에 대한 검색 수행.", dto.getPage());
                result = collegeService.getCollegeByName(name, dto.getPage());
            }
        }
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/program")
    @Operation(summary = "프로그램 기반으로 검색")
    public ApiResponse<CollegeSearchDTO> searchCollegeByProgram(@Parameter(hidden = true) @AuthUser Member member,
                                                                @RequestBody ProgramPageDto dto) {
        log.info("searchCollegeByProgram 호출, member: {}, dto: {}", member, dto);

        String program = dto.getProgram();
        CollegeSearchDTO result;

        if (program == null || program.isBlank()) {
            log.error("프로그램 정보가 없습니다.");
            throw new NullPointerException("프로그램 정보가 없습니다.");
        }

        if (member != null) {
            if (dto.getPage() == null) {
                log.info("페이지 정보가 없으므로 기본 검색 수행.");
                result = collegeService.getCollegeByProgram(member, program);
            } else {
                log.info("페이지 정보가 있으므로 페이지 {}에 대한 검색 수행.", dto.getPage());
                result = collegeService.getCollegeByProgram(member, program, dto.getPage());
            }
        } else {
            if (dto.getPage() == null) {
                log.info("회원 정보가 없으므로 기본 검색 수행.");
                result = collegeService.getCollegeByProgram(program);
            } else {
                log.info("회원 정보가 없으므로 페이지 {}에 대한 검색 수행.", dto.getPage());
                result = collegeService.getCollegeByProgram(program, dto.getPage());
            }
        }
        return ApiResponse.onSuccess(result);
    }

    @DeleteMapping("/like")
    @Operation(summary = "대학교 찜 해제하기")
    public ApiResponse<Long> deleteMemberCollege(@Parameter(hidden = true) @AuthUser Member member, @RequestParam("collegeId") Long collegeId) {
        log.info("deleteMemberCollege 호출, member: {}, collegeId: {}", member, collegeId);
        return ApiResponse.onSuccess(collegeService.deleteMemberCollege(member, collegeId));
    }

    @PostMapping("/like")
    @Operation(summary = "대학교 찜하기")
    public ApiResponse<Long> createMemberCollege(@Parameter(hidden = true) @AuthUser Member member, @RequestParam("collegeId") int collegeId) {
        log.info("createMemberCollege 호출, member: {}, collegeId: {}", member, collegeId);
        return ApiResponse.onSuccess(collegeService.createMemberCollege(member, (long) collegeId));
    }

    @PostMapping("/likeSearch")
    @Operation(summary = "찜한 목록 조회")
    public ApiResponse<CollegeSearchDTO> searchFavoriteColleges(@Parameter(hidden = true) @AuthUser Member member,
                                                                @Nullable @RequestParam("page") Integer page) {
        log.info("searchFavoriteColleges 호출, member: {}, page: {}", member, page);

        CollegeSearchDTO result;
        if (page == null) {
            log.info("페이지 정보가 없으므로 기본 찜 목록 조회 수행.");
            result = collegeService.searchFavorites(member);
        } else {
            log.info("페이지 정보가 있으므로 페이지 {}에 대한 찜 목록 조회 수행.", page);
            result = collegeService.searchFavorites(member, page);
        }
        return ApiResponse.onSuccess(result);
    }
}

