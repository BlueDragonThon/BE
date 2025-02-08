package com.example.template.domain.college.controller;

import com.example.template.common.response.ApiResponse;
import com.example.template.domain.college.dto.*;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.service.CollegeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "캐스트 API", description = "캐스트 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/college")
public class CollegeController {

    private final CollegeService collegeService;
    @PostMapping("/search")
    public ApiResponse<CollegeSearchDTO> searchCollegesByDistance(@RequestBody CollegeSearchParamDTO param) {
        CollegeSearchDTO result;
        if (param.getAcr()==null||param.getDwn()==null) throw new NullPointerException("위도, 경도 정보가 없습니다.");
        Coordinate coordinate = Coordinate.builder()
                .acr(param.getAcr())
                .dwn(param.getDwn()).build();
        if(param.getPage()==null)
            result = new CollegeSearchDTO(collegeService.searchCollegesByDistance(coordinate));
        else result = new CollegeSearchDTO(collegeService.searchCollegesByDistance(coordinate, param.getPage()));
        return ApiResponse.onSuccess(result);

    }

    @PostMapping("/name")
    @Operation(summary = "대학 이름 기반으로 검색")
    public ApiResponse<CollegeSearchDTO> searchCollegeByName(@RequestBody NamePageDto dto) {
        String name = dto.getName();
        CollegeSearchDTO result;
        if (name==null||name.isBlank()) throw new NullPointerException("이름이 없습니다.");
        if (dto.getPage()==null) result = new CollegeSearchDTO(collegeService.getCollegeByName(name));
        else result = new CollegeSearchDTO(collegeService.getCollegeByName(name, dto.getPage()));
        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/program")
    @Operation(summary = "프로그램 기반으로 검색")
    public ApiResponse<CollegeSearchDTO> searchCollegeByProgram(@RequestBody ProgramPageDto dto) {
        String program = dto.getProgram();
        CollegeSearchDTO result;
        if (program==null||program.isBlank()) throw new NullPointerException("프로그램 정보가 없습니다.");
        if (dto.getPage()==null) result = new CollegeSearchDTO(collegeService.getCollegeByProgram(program));
        else result = new CollegeSearchDTO(collegeService.getCollegeByProgram(program, dto.getPage()));
        return ApiResponse.onSuccess(result);
    }
}
