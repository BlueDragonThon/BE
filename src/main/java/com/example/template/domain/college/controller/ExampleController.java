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
@RequestMapping("/api/example")
public class ExampleController {

    @GetMapping("/keyword")
    @Operation(summary = "예시 API 입니다")
    public ApiResponse<String> createCastByKeyword() {
        return ApiResponse.of(SuccessCode._OK, "성공입니다");
    }

}
