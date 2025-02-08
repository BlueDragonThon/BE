package com.example.template.domain.member.service;

import com.example.template.domain.member.dto.MemberRequestDto;
import com.example.template.domain.member.dto.MemberResponseDto;

public interface MemberService {
    MemberResponseDto createMember(MemberRequestDto memberRequestDto);
}
