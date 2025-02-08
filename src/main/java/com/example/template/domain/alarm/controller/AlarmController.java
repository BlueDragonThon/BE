package com.example.template.domain.alarm.controller;

import com.example.template.common.annotation.AuthUser;
import com.example.template.common.response.ApiResponse;
import com.example.template.domain.alarm.dto.AlarmResponseDto;
import com.example.template.domain.alarm.service.AlarmService;
import com.example.template.domain.college.dto.CollegeSearchDTO;
import com.example.template.domain.college.dto.CollegeSearchParamDTO;
import com.example.template.domain.college.dto.NamePageDto;
import com.example.template.domain.college.dto.ProgramPageDto;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.service.CollegeService;
import com.example.template.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "알람 API", description = "알람 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/get")
    @Operation(summary = "알림가져오기")
    public ApiResponse<List<AlarmResponseDto>> getAlarm(@AuthUser Member member) {
        return ApiResponse.onSuccess(alarmService.getAllAlarms(member));
    }

    @DeleteMapping("/{notificationId}")
    @Operation(summary = "알림지우기")
    public ApiResponse<Long> deleteAlarm(@AuthUser Member member, @PathVariable Long notificationId) {
        return ApiResponse.onSuccess(alarmService.deleteAlarm(member, notificationId));
    }

}
