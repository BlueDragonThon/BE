package com.example.template.domain.college.dto;

import com.example.template.domain.college.entity.College;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollegeResponseDto {
    private int id;
    private String name;
    private String headmaster;
    private String contactInfo;
    private String address;
    private List<String> program;
    private Boolean favorites;

    public CollegeResponseDto(College college) {
        id = (int) college.getId();
        name = college.getName();
        headmaster = college.getHeadmaster();
        contactInfo = college.getContactNo();
        address = college.getAddress();
        if(college.getProgram()==null||college.getProgram().isBlank()) program = List.of();
        else program = Arrays.stream(college.getProgram().split(","))
                .map(String::trim)
                .toList();
        favorites = false;
        nullToNone();
    }

    public CollegeResponseDto(College college, boolean favorites) {
        this(college);
        this.favorites = favorites;
    }

    private static final String NONE_STR = "없음";
    private static final String EMPTY_SEAT_STR = "공석";
    private static final List<String> EMPTY_LIST = List.of(NONE_STR);

    private void nullToNone() {
        if (name==null||name.isBlank()) name = NONE_STR;
        if (headmaster==null||headmaster.isBlank()) headmaster = EMPTY_SEAT_STR;
        if (contactInfo==null||contactInfo.isBlank()) contactInfo = NONE_STR;
        if (address==null||address.isBlank()) address = NONE_STR;
        if (program.isEmpty()) program = EMPTY_LIST;
    }
}
