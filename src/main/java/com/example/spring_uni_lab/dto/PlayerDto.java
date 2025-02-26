package com.example.spring_uni_lab.dto;

import com.example.spring_uni_lab.entities.Statistics;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    private String firstName;
    private String lastName;
    private Long teamId;
    private Statistics statistics;

}
