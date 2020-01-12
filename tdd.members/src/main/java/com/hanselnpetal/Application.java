package com.hanselnpetal;

import com.hanselnpetal.data.repos.BoardRepository;
import com.hanselnpetal.data.repos.MemberRepository;
import com.hanselnpetal.data.repos.UserRepository;
import com.hanselnpetal.domain.Board;
import com.hanselnpetal.domain.Member;
import com.hanselnpetal.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MemberRepository memberRepository,
                                        UserRepository userRepository,
                                        BoardRepository boardRepository) {
        return strg -> {
            List<User> users = new ArrayList<>();
            users.add(userRepository.save(new User("Dacian")));
            users.add(userRepository.save(new User("Andreea")));
            users.add(userRepository.save(new User("Florin")));

            List<Board> boards = new ArrayList<>();
            boards.add(boardRepository.save(new Board("Unu")));
            boards.add(boardRepository.save(new Board("Doi")));
            boards.add(boardRepository.save(new Board("Trei")));
            boards.add(boardRepository.save(new Board("Patru")));
            boards.add(boardRepository.save(new Board("Cinci")));

            for(User user:users) {
                for(Board board:boards) {
                    //SHOULD BE A CHECK if already EXIST!!!
                    memberRepository.save(new Member(user.getId(), board.getId()));
                }
            }
        };
    }
}
