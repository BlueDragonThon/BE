package com.example.template.domain.member.service;

import com.example.template.common.jwt.JwtUtil;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.repository.ExampleRepository;
import com.example.template.domain.member.dto.MemberRequestDto;
import com.example.template.domain.member.dto.MemberResponseDto;
import com.example.template.domain.member.entity.Member;
import com.example.template.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {

        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .age(memberRequestDto.getAge())
                .coordinate(Coordinate.builder()
                        .acr(memberRequestDto.getAcr())
                        .dwn(memberRequestDto.getDwn())
                        .build())
                .build();

        memberRepository.save(member);

        String token = jwtUtil.generateToken(member.getId());

        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .token(token)
                .build();
    }
}
