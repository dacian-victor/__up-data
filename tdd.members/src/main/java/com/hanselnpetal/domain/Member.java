package com.hanselnpetal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="member")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
public class Member {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	private Long userId;
	private Long boardId;

	@JsonProperty
	private boolean hasAccepted = true;
	
	public Member() {
	}

	// https://stackoverflow.com/questions/21913955/json-post-request-for-boolean-field-sends-false-by-default
	public Member(Long userId, Long boardId) {
		this.userId = userId;
		this.boardId = boardId;
	}

	public boolean getHasAccepted() {
		return hasAccepted;
	}

	public void setHasAccepted(boolean hasAccepted) {
		this.hasAccepted = hasAccepted;
	}
}
