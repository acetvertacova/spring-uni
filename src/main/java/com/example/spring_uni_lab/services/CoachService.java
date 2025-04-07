package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.CoachDto;
import java.util.List;

public interface CoachService {
    CoachDto createCoach(CoachDto coach);

    List<CoachDto> fetchCoachList();

    CoachDto updateCoach(CoachDto coach, long id);

    CoachDto deleteCoachById(long id);
}
