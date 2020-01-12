package com.hanselnpetal.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
