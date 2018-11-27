package com.exposition.service;

import com.domain.model.Player;
import com.domain.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.infrastructure.repository.PlayerRepository;
import com.infrastructure.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SoccerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<String> getAllTeamPlayers(long teamId) {
        List<String> result = new ArrayList<String>();
        List<Player> players = playerRepository.findByTeamId(teamId);
        for (Player player : players) {
            result.add(player.getName());
        }

        return result;
    }

    public void addBarcelonaPlayer(String name, String position, int number) {

        Optional<Team> barcelona = teamRepository.findById(1l);

        if(!barcelona.isPresent()) {
            Team team = new Team(1l, "Barcelona");
            teamRepository.save(team);
            barcelona = teamRepository.findById(1l);
        }

        Player newPlayer = new Player();
        newPlayer.setName(name);
        newPlayer.setPosition(position);
        newPlayer.setNum(number);
        newPlayer.setTeam(barcelona.get());
        playerRepository.save(newPlayer);
    }
}
