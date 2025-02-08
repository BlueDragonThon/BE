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
import org.springframework.web.bind.annotation.*;

@Tag(name = "캐스트 API", description = "캐스트 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/college")
public class CollegeController {

    private final CollegeService collegeService;
    @PostMapping("/search")
    @Operation(summary = "직선거리 기준 검색(사용자 데이터에 저장된 위치 값 이용)")
    public ApiResponse<CollegeSearchDTO> searchCollegesByMemberDistance(@Parameter(hidden = true) @AuthUser Member member,
                                                                        @Nullable @RequestParam("page") Integer page) {
        CollegeSearchDTO result;
        Coordinate coordinate = member.getCoordinate();
        if (coordinate==null)
            throw new NullPointerException("위도, 경도 정보가 없습니다.");
        if (page == null)
            result = collegeService.searchCollegesByDistance(member, coordinate);
        else result = collegeService.searchCollegesByDistance(member, coordinate, page);
        return ApiResponse.onSuccess(result);
    }
    @PostMapping("/distance")
    @Operation(summary = "직선거리 기준 검색(별도 제공된 위치 값 이용)")
    public ApiResponse<CollegeSearchDTO> searchCollegesByDistance(@Parameter(hidden = true) @AuthUser Member member,
                                                                  @RequestBody CollegeSearchParamDTO param) {
        CollegeSearchDTO result;
        if (param.getAcr()==null||param.getDwn()==null)
            throw new NullPointerException("위도, 경도 정보가 없습니다.");
        Coordinate coordinate = Coordinate.builder()
                .acr(param.getAcr())
                .dwn(param.getDwn()).build();
        if (member != null) {
            if (param.getPage() == null)
                result = collegeService.searchCollegesByDistance(member, coordinate);
            else result = collegeService.searchCollegesByDistance(member, coordinate, param.getPage());
        } else {
            if (param.getPage() == null)
                result = collegeService.searchCollegesByDistance(coordinate);
            else result = collegeService.searchCollegesByDistance(coordinate, param.getPage());
        }
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/name")
    @Operation(summary = "대학 이름 기반으로 검색")
    public ApiResponse<CollegeSearchDTO> searchCollegeByName(@Parameter(hidden = true) @AuthUser Member member,
                                                             @RequestBody NamePageDto dto) {
        String name = dto.getName();
        CollegeSearchDTO result;
        if (name==null||name.isBlank()) throw new NullPointerException("이름이 없습니다.");
        if (member != null) {
            if (dto.getPage()==null) result = collegeService.getCollegeByName(member, name);
            else result = collegeService.getCollegeByName(member, name, dto.getPage());
        }else{
            if (dto.getPage()==null) result = collegeService.getCollegeByName(name);
            else result = collegeService.getCollegeByName(name, dto.getPage());
        }
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/program")
    @Operation(summary = "프로그램 기반으로 검색")
    public ApiResponse<CollegeSearchDTO> searchCollegeByProgram(@Parameter(hidden = true) @AuthUser Member member,
                                                                @RequestBody ProgramPageDto dto) {
        String program = dto.getProgram();
        CollegeSearchDTO result;
        if (program==null||program.isBlank()) throw new NullPointerException("프로그램 정보가 없습니다.");
        if (member != null) {
            if (dto.getPage()==null) result = collegeService.getCollegeByProgram(member, program);
            else result = collegeService.getCollegeByProgram(member, program, dto.getPage());
        }else{
            if (dto.getPage()==null) result = collegeService.getCollegeByProgram(program);
            else result = collegeService.getCollegeByProgram(program, dto.getPage());
        }
        return ApiResponse.onSuccess(result);
    }

    @DeleteMapping("/like")
    @Operation(summary = "대학교 찜 해제하기")
    public ApiResponse<Long> deleteMemberCollege(@AuthUser Member member, @RequestParam("collegeId") Long collegeId){
        return ApiResponse.onSuccess(collegeService.deleteMemberCollege(member, collegeId));
    }

    @PostMapping("/like")
    @Operation(summary = "대학교 찜하기")
    public ApiResponse<Long> createMemberCollege(@Parameter(hidden = true) @AuthUser Member member, @RequestParam("collegeId") Long collegeId){
        return ApiResponse.onSuccess(collegeService.createMemberCollege(member, collegeId));
    }
    @PostMapping("/likeSearch")
    @Operation(summary = "찜한 목록 조회")
    public ApiResponse<CollegeSearchDTO> searchFavoriteColleges(@Parameter(hidden = true) @AuthUser Member member,
                                                                @Nullable @RequestParam("page") Integer page) {
        CollegeSearchDTO result;
        if (page == null)
            result = collegeService.searchFavorites(member);
        else result = collegeService.searchFavorites(member, page);
        return ApiResponse.onSuccess(result);
    }
}
