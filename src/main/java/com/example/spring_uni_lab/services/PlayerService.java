package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.PlayerDto;
import com.example.spring_uni_lab.entities.Player;

import java.util.List;

public interface PlayerService {

    Player savePlayer(Player player);

    List<Player> fetchPlayerList();

    Player updatePlayer(Player player, long id);

    void deletePlayerById(long id);

}
