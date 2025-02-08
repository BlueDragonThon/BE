package com.example.template.domain.alarm.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.domain.alarm.dto.AlarmResponseDto;
import com.example.template.domain.alarm.entity.Alarm;
import com.example.template.domain.alarm.repository.AlarmRepository;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;

    @Override
    public void createAlarm(MemberCollege memberCollege) {

        College college = memberCollege.getCollege();

        Alarm alarm = Alarm.builder()
                .title(college.getName() + "마감이 얼마 남지 않았어요!")
                .content(college.getDate() + "까지 얼른 지원하세요!")
                .date(college.getDate())
                .memberCollege(memberCollege)
                .build();

        alarmRepository.save(alarm);

    }

    @Override
    public List<AlarmResponseDto> getAllAlarms(Member member) {

        List<Alarm> alarmList = alarmRepository.findAllByMemberCollege_Member(member);

        return alarmList.stream()
                .map(alarm -> AlarmResponseDto.builder()
                        .id(alarm.getId())
                        .userId(alarm.getMemberCollege().getMember().getId())
                        .title(alarm.getTitle())
                        .content(alarm.getContent())
                        .date(alarm.getDate())
                        .build())
                .toList();
    }

    @Override
    public Long deleteAlarm(Member member, Long notificationId) {
        Alarm alarm = alarmRepository.findById(notificationId)
                .orElseThrow(() -> new GeneralHandler(ErrorCode._BAD_REQUEST));

        alarmRepository.delete(alarm);

        return alarm.getId();
    }
}
