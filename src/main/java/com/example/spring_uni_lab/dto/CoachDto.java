package com.example.spring_uni_lab.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachDto {
    private Long id;
    private String firstName;
    private String lastName;
}
