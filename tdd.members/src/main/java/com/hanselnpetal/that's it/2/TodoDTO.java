package com.example.data.auditing.tdd.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String type;

    public TodoDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("type") String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}
