package com.exposition.config;

import com.exposition.service.SoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner commandLineRunner(SoccerService soccerService) {
        return strng -> {
            soccerService.addBarcelonaPlayer("Xavi Hernandez", "Midfielder", 6);
            soccerService.addBarcelonaPlayer("Rafi Hernandez", "Midfielder", 6);
            soccerService.addBarcelonaPlayer("Gornu Hernandez", "Midfielder", 6);
        };
    }
}
