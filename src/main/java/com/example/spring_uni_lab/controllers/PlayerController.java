package com.example.spring_uni_lab.controllers;

import com.example.spring_uni_lab.dto.PlayerDto;
import com.example.spring_uni_lab.entities.Player;
import com.example.spring_uni_lab.services.PlayerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping("playerApi")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/players")
    public List<PlayerDto> fetchPlayerList() {
        return playerService.fetchPlayerList().stream()
                .map(player -> modelMapper.map(player, PlayerDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/players")
    public ResponseEntity<PlayerDto> savePlayer(@RequestBody PlayerDto playerDto){
        //Dto to entity
        Player playerRequest = modelMapper.map(playerDto, Player.class);
        Player player = playerService.savePlayer(playerRequest);

        // entity to DTO
        PlayerDto playerResponse = modelMapper.map(player, PlayerDto.class);
        return new ResponseEntity<PlayerDto>(playerResponse, HttpStatus.CREATED);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerDto playerDto, @PathVariable("id") long id) {

        // DTO to Entity
        Player playerRequest = modelMapper.map(playerDto, Player.class);
        Player player = playerService.updatePlayer(playerRequest, id);

        // entity to DTO
        PlayerDto playerResponse = modelMapper.map(player, PlayerDto.class);
        return ResponseEntity.ok().body(playerResponse);
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayerById(@PathVariable("id") long id){
        playerService.deletePlayerById(id);
    }

//
//
//    @GetMapping("/players")
//    public List<Player> players() {
//        return playerService.fetchPlayerList();
//    }
//
//    @PutMapping("/players")
//    public Player updatePlayer(Player playerToUpdate, int id){
//        return playerService.updatePlayer(playerToUpdate, id);
//    }
//
//    @DeleteMapping("/players")
//    public Player deletePlayer(Player playerToDelete, int id){
//        return playerService.deletePlayerById(id);
//    }

}
