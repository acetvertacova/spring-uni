package com.example.spring_uni_lab.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Statistics {

    private Integer goalsBoalsConflict;
    private Integer assists;

}
