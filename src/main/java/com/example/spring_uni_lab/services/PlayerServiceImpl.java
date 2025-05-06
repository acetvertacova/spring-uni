package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.EntityDtoMapper;
import com.example.spring_uni_lab.dto.PlayerDto;
import com.example.spring_uni_lab.entities.Player;
import com.example.spring_uni_lab.entities.Statistics;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.hibernate.repository.HbPlayerRepository;
import com.example.spring_uni_lab.hibernate.repository.HbTeamRepository;
import com.example.spring_uni_lab.jdbc.repositories.JdbcPlayerRepository;
import com.example.spring_uni_lab.jdbc.repositories.JdbcTeamRepository;
import com.example.spring_uni_lab.repositories.PlayerRepository;
import com.example.spring_uni_lab.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final HbPlayerRepository hbPlayerRepository;
    private final TeamRepository teamRepository;
    private final HbTeamRepository hbTeamRepository;
    private final JdbcPlayerRepository jdbcPlayerRepository;
    private final JdbcTeamRepository jdbcTeamRepository;

    @Override
    public List<PlayerDto> fetchPlayerList() {
        List<Player> playerList = jdbcPlayerRepository.findAll();

        return playerList.stream()
                .map(EntityDtoMapper::playerToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = EntityDtoMapper.playerToEntity(playerDto);

        if (playerDto.getTeamId() != null) {
            Team team = jdbcTeamRepository.findById(playerDto.getTeamId());
            player.setTeam(team);
        }

        jdbcPlayerRepository.save(player);
        return playerDto;
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto, long id) {
        Player player = jdbcPlayerRepository.findById(id);
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());

        if (playerDto.getTeamId() != null) {
            Team team = jdbcTeamRepository.findById(playerDto.getTeamId());
            player.setTeam(team);
        }

        player.setStatistics(Statistics.builder()
                .goals(playerDto.getStatistics().getGoals())
                .assists(playerDto.getStatistics().getAssists())
                .build());

        jdbcPlayerRepository.update(player);
        return EntityDtoMapper.playerToDto(player);

    }

    @Override
    public PlayerDto deletePlayerById(long id) {
        Player player = jdbcPlayerRepository.findById(id);
        jdbcPlayerRepository.deleteById(id);
        return EntityDtoMapper.playerToDto(player);
    }
}
