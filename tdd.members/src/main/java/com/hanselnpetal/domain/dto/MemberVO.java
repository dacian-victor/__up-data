package com.hanselnpetal.domain.dto;

import lombok.Data;
import lombok.Value;

@Data
public class MemberVO {
    private String name;
    private Long boardId;

    public MemberVO() {
    }

    public MemberVO(String name, Long boardId) {
        this.name = name;
        this.boardId = boardId;
    }

}
