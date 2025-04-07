package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.PlayerDto;

import java.util.List;

public interface PlayerService {

    PlayerDto createPlayer(PlayerDto player);

    List<PlayerDto> fetchPlayerList();

    PlayerDto updatePlayer(PlayerDto player, long id);

    PlayerDto deletePlayerById(long id);

}
