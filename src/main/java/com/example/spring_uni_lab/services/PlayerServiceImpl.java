package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.entities.Player;
import com.example.spring_uni_lab.entities.Statistics;
import com.example.spring_uni_lab.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> fetchPlayerList(){
        return playerRepository.findAll();
    }

    @Override
    public Player savePlayer(Player player){
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(@RequestBody Player player, @PathVariable long id){
        playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        Statistics statistics = new Statistics(player.getStatistics().getGoals(), player.getStatistics().getAssists());

        Player updatedPlayer = Player.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .team(player.getTeam())
                .statistics(statistics)
                .build();

        return playerRepository.save(updatedPlayer);

    }

    @Override
    public void deletePlayerById(long id){
        playerRepository.deleteById(id);
    }















//    @Override
//    public PlayerDto savePlayer(Player player){
//        Player savedPlayer = this.playerRepository.save(player);
//        return modelMapper.map(savedPlayer, PlayerDto.class);
//    }

//    @Override
//    public List<Player> fetchPlayerList(){
//        List<Player> players = StreamSupport.stream(playerRepository
//                .findAll()
//                .spliterator(), false)
//                .toList();
//        return players;
//    }

//    @Override
//    public List<PlayerDto> fetchPlayerList(){
//        List<Player> players = StreamSupport.stream(playerRepository
//                .findAll()
//                .spliterator(), false)
//                .toList();
//
//        List<PlayerDto> playersDto = players.stream()
//                .map(player -> modelMapper.map(player, PlayerDto.class))
//                .collect(Collectors.toList());
//
//        return playersDto;
//    }

}
