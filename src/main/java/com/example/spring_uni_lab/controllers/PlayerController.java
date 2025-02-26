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

    @PostMapping("/players")
    public PlayerDto savePlayer(@RequestBody PlayerDto playerDto){
        return playerService.savePlayer(playerDto);
    }

    @PutMapping("/players/{id}")
    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto, @PathVariable("id") long id){
        return playerService.updatePlayer(playerDto, id);
    }

    @DeleteMapping("/players/{id}")
    public PlayerDto deleteById(@PathVariable("id") long id){
        return playerService.deletePlayerById(id);
    }

}
