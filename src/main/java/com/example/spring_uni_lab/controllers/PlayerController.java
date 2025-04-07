package com.example.spring_uni_lab.controllers;

import com.example.spring_uni_lab.dto.PlayerDto;
import com.example.spring_uni_lab.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("playerApi")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/players")
    public List<PlayerDto> displayAllPlayers(){
        return playerService.fetchPlayerList();
    }

    @PostMapping("/player")
    public PlayerDto createPlayer(@RequestBody PlayerDto playerDto){
        return playerService.createPlayer(playerDto);
    }

    @PutMapping("/player/{id}")
    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto, @PathVariable("id") long id){
        return playerService.updatePlayer(playerDto, id);
    }

    @DeleteMapping("/player/{id}")
    public PlayerDto deleteById(@PathVariable("id") long id){
        return playerService.deletePlayerById(id);
    }

}
