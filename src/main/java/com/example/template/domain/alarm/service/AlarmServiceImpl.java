package com.example.template.domain.alarm.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.domain.alarm.dto.AlarmResponseDto;
import com.example.template.domain.alarm.entity.Alarm;
import com.example.template.domain.alarm.repository.AlarmRepository;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.repository.CollegeRepository;
import com.example.template.domain.college.repository.MemberCollegeRepository;
import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberCollegeRepository memberCollegeRepository;

    @Override
    public void createAlarm(MemberCollege memberCollege) {

        College college = memberCollege.getCollege();

        Alarm alarm = Alarm.builder()
                .title(college.getName() + "마감이 얼마 남지 않았어요!")
                .content(college.getDate().getMonthValue() + "월" + college.getDate().getDayOfMonth() + "일" + "까지 얼른 지원하세요!")
                .date(college.getDate())
                .memberCollege(memberCollege)
                .build();

        alarmRepository.save(alarm);

    }

    @Override
    public List<AlarmResponseDto> getAllAlarms(Member member) {

        List<Alarm> alarmList = alarmRepository.findAllByMemberCollege_Member(member);

        List<AlarmResponseDto> alarmResponseDtoList = new ArrayList<>(alarmList.stream()
                .map(alarm -> AlarmResponseDto.builder()
                        .id(alarm.getId())
                        .userId(alarm.getMemberCollege().getMember().getId())
                        .title(alarm.getTitle())
                        .content(alarm.getContent())
                        .date(alarm.getDate())
                        .build())
                .toList());

        alarmResponseDtoList.add(AlarmResponseDto.builder()
                .id(20L)
                .userId(20L)
                .title("노인 대학 마감이 얼마 남지 않았어요!")
                .content("2월 9일까지 얼른 지원하세요!")
                .date(LocalDate.now())
                .build());

        alarmResponseDtoList.add(AlarmResponseDto.builder()
                .id(21L)
                .userId(21L)
                .title("서울 노인 대학 마감이 얼마 남지 않았어요!")
                .content("2월 9일까지 얼른 지원하세요!")
                .date(LocalDate.now())
                .build());

        return alarmResponseDtoList;
    }

    @Override
    public Long deleteAlarm(Member member, Long notificationId) {

        Alarm alarm = alarmRepository.findById(notificationId)
                .orElseThrow(() -> new GeneralHandler(ErrorCode._BAD_REQUEST));

        alarmRepository.delete(alarm);

        return alarm.getId();
    }

    @Override
    public void deleteAlarm(MemberCollege memberCollege) {
        alarmRepository.deleteByMemberCollege(memberCollege);
    }
}
