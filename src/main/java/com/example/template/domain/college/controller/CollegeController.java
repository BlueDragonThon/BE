package com.example.template.domain.college.controller;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Coordinate;
import com.example.template.domain.college.service.CollegeService;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CollegeController {
    private final CollegeService collegeService;
    @PostMapping("/search")
    CollegeSearchDTO searchCollegesByDistance(@RequestBody CollegeSearchParamDTO param) {
        if (param.getAcr()==null||param.getDwn()==null) throw new NullPointerException();
        Coordinate coordinate = Coordinate.builder()
                .acr(param.getAcr())
                .dwn(param.dwn).build();
        if(param.getPage()==null)
            return new CollegeSearchDTO(collegeService.searchCollegesByDistance(coordinate));
        else return new CollegeSearchDTO(collegeService.searchCollegesByDistance(coordinate, param.getPage()));

    }

    @PostMapping("/search/length")
    long numOfColleges() {
        return collegeService.numOfCollege();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class CollegeSearchParamDTO{
        private Double acr;
        private Double dwn;
        private Integer page; //Nullable
    }

    @Data
    private class CollegeSearchDTO{
        private List<College> result;
        private int pageCount;

        CollegeSearchDTO(Page<College> resData) {
            result = resData.toList();
            pageCount = resData.getTotalPages();
        }
    }
}