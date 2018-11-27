package com.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "team")
    private List<Player> players;

    @Column(name = "playerId")
    @ElementCollection(targetClass=Long.class)
    private List<Long> playerId = new ArrayList<Long>();

    public Team() {
    }

    public Team(long l, String barcelona) {
        this.getPlayerId().add(l);
        this.setId(l);
        this.setName(barcelona);
    }
}
