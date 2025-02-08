package com.example.template.domain.alarm.service;

import com.example.template.domain.alarm.dto.AlarmResponseDto;
import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;

import java.util.List;

public interface AlarmService {
    void createAlarm(MemberCollege memberCollege);
    List<AlarmResponseDto> getAllAlarms(Member member);
    Long deleteAlarm(Member member, Long notificationId);
}
