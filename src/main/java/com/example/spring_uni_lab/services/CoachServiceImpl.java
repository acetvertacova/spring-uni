package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.CoachDto;
import com.example.spring_uni_lab.dto.EntityDtoMapper;
import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.hibernate.repository.HbCoachRepository;
import com.example.spring_uni_lab.jdbc.repositories.JdbcCoachRepository;
import com.example.spring_uni_lab.repositories.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;
    private final HbCoachRepository hbCoachRepository;
    private final JdbcCoachRepository jdbcCoachRepository;

    @Override
    public List<CoachDto> fetchCoachList() {
        List<Coach> coachesList = jdbcCoachRepository.findAll();

        return coachesList.stream()
                .map(EntityDtoMapper::coachtoDto)
                .collect(Collectors.toList());
    }

    @Override
    public CoachDto createCoach(CoachDto coachDto) {
        Coach coach = EntityDtoMapper.coachtoEntity(coachDto);

        jdbcCoachRepository.save(coach);
        return coachDto;
    }

    @Override
    public CoachDto updateCoach(CoachDto coachDto, long id) {
        Coach coach = jdbcCoachRepository.findById(id);

        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());

        jdbcCoachRepository.update(coach);
        return EntityDtoMapper.coachtoDto(coach);

    }

    @Override
    public CoachDto deleteCoachById(long id) {
        Coach coach = jdbcCoachRepository.findById(id);

        jdbcCoachRepository.deleteById(id);
        return EntityDtoMapper.coachtoDto(coach);
    }

}
