package com.example.template.domain.college.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollegeSearchParamDTO{
    private Double acr;
    private Double dwn;
    private Integer page; //Nullable
}
