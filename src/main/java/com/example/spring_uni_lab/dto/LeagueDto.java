package com.example.spring_uni_lab.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LeagueDto {
    private Long id;
    private String name;
}
