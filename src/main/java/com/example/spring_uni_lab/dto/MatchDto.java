package com.example.spring_uni_lab.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private String title;
    private Date date;
}
