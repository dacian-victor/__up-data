package com.hanselnpetal.service;


import com.hanselnpetal.data.repos.BoardRepository;
import com.hanselnpetal.data.repos.MemberRepository;
import com.hanselnpetal.domain.Board;
import com.hanselnpetal.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<Board> findAllByUserId(Long userId) {
        Map<Long, Boolean> idsMap =
                memberRepository.findByUserId(userId)
                        .stream()
                        .collect(
                                Collectors.toMap(Member::getBoardId, Member::getHasAccepted));

//        List<Long> ids =
//                memberRepository.findByUserId(userId)
//                        .stream().map(member -> {
//                            return member.getBoardId();
//                        })
//                        .filter(Objects::nonNull)
//                        .collect(Collectors.toList());
        List<Board> byIdIn =
                boardRepository.findByIdIn(
                        idsMap.keySet().stream().collect(Collectors.toList()));
        return byIdIn;
    }
}
