package com.example.template.domain.college.controller;

import com.example.template.common.response.ApiResponse;
import com.example.template.common.response.status.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "캐스트 API", description = "캐스트 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/college")
public class CollegeController {

    @GetMapping("/recommend")
    @Operation(summary = "사용자 정보를 바탕으로 추천해주는 api")
    public ApiResponse<String> createCastByKeyword() {
        return ApiResponse.of(SuccessCode._OK, "성공입니다");
    }

}
