package com.example.spring_uni_lab.controllers;

import com.example.spring_uni_lab.dto.CoachDto;
import com.example.spring_uni_lab.services.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("coachApi")
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/coaches")
    public List<CoachDto> displayAllCoaches(){
        return coachService.fetchCoachList();
    }

    @PostMapping("/coach")
    public CoachDto createCoach(@RequestBody CoachDto coachDto){
        return coachService.createCoach(coachDto);
    }

    @PutMapping("/coach/{id}")
    public CoachDto updateCoach(@RequestBody CoachDto coachDto, @PathVariable("id") long id){
        return coachService.updateCoach(coachDto, id);
    }

    @DeleteMapping("/coach/{id}")
    public CoachDto deleteById(@PathVariable("id") long id) {
        return coachService.deleteCoachById(id);
    }
}
