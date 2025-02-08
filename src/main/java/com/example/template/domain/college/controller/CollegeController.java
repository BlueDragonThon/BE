package com.example.template.domain.college.controller;

import com.example.template.common.response.ApiResponse;
import com.example.template.common.response.status.SuccessCode;
import com.example.template.domain.college.dto.CollegeResponseDto;
import com.example.template.domain.college.service.CollegeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "캐스트 API", description = "캐스트 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/college")
public class CollegeController {

    private final CollegeService collegeService;

    /*@GetMapping("/recommend")
    @Operation(summary = "사용자 정보를 바탕으로 추천해주는 api")
    public ApiResponse<String> createCastByKeyword() {
        return
    }
*/

    @PostMapping("/college/name")
    @Operation(summary = "대학 이름 기반으로 검색")
    public ApiResponse<List<CollegeResponseDto>> searchCollegeByName(@RequestParam("name") String name) {
        return ApiResponse.onSuccess(collegeService.getCollegeByName(name));
    }

    @PostMapping("/college/program")
    @Operation(summary = "프로그램 기반으로 검색")
    public ApiResponse<List<CollegeResponseDto>> searchCollegeByProgram(@RequestParam("program") String program) {
        return  ApiResponse.onSuccess(collegeService.getCollegeByProgram(program));
    }
}
