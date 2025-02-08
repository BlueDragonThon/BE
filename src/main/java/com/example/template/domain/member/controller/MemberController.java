package com.example.template.domain.member.controller;

import com.example.template.common.annotation.AuthUser;
import com.example.template.common.response.ApiResponse;
import com.example.template.domain.college.dto.CollegeResponseDto;
import com.example.template.domain.member.dto.MemberRequestDto;
import com.example.template.domain.member.dto.MemberResponseDto;
import com.example.template.domain.member.entity.Member;
import com.example.template.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "멤버 API", description = "멤버 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "멤버 정보 저장하기")
    public ApiResponse<MemberResponseDto> searchCollegeByName(@RequestBody MemberRequestDto memberRequestDto) {

        return ApiResponse.onSuccess(memberService.createMember(memberRequestDto));
    }

    @GetMapping("login/info")
    @Operation(summary = "현재 멤버 정보 받아오기")
    public ApiResponse<MemberResponseDto> getCurrentMember(@Parameter(hidden = true) @AuthUser Member member) {

        return ApiResponse.onSuccess(MemberResponseDto.builder()
                        .id(member.getId())
                        .name(member.getName())
                .build());
    }

}
