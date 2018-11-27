package com.exposition.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.exposition.service.SoccerService;

import java.util.List;

@RestController
@RequestMapping(value = "/players")
public class TeamPlayerController {
    @Autowired
    SoccerService soccerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> getAllPlayers() {
        return soccerService.getAllTeamPlayers(1);
    }
}
