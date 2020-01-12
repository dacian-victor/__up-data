package com.hanselnpetal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="board")
public class Board {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	private String title;

	public Board() {
	}

	public Board(String title) {
		this.title = title;
	}
}
