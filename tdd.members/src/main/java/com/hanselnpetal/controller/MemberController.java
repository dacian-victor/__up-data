package com.hanselnpetal.controller;

import com.hanselnpetal.domain.Board;
import com.hanselnpetal.domain.Member;
import com.hanselnpetal.domain.dto.MemberVO;
import com.hanselnpetal.service.BoardService;
import com.hanselnpetal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;

    @ResponseBody
    @GetMapping("/users/{userid}/boards")
    public ResponseEntity<List<Board>> findAllBoards(@PathVariable("userid") Long userid) {
        List<Board> boards = boardService.findAllByUserId(userid);
        return ResponseEntity.ok().body(boards);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/boards/{boardid}/members",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MemberVO>> findAllMembers(@PathVariable("boardid") Long boardid) {
        return ResponseEntity.ok().body(memberService.findAll(boardid));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/boards/{boardid}/invite",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accept(@PathVariable("boardid") Long boardid, @RequestBody Member member) {
        //Assert.isTrue(user.getId().equals(id), "User Id must match Url Id");
        member.setBoardId(boardid);
        return ResponseEntity.ok().body(memberService.invite(member));
    }

    @DeleteMapping("/users/{userid}/boards/{boardid}/accept")
    public ResponseEntity<?> accept(@PathVariable("userid") Long userid, @PathVariable("boardid") Long boardid) {
        memberService.accept(userid, boardid);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/users/{userid}/boards/{boardid}/reject")
    public ResponseEntity<?> reject(@PathVariable("userid") Long userid, @PathVariable("boardid") Long boardid) {
        memberService.reject(userid, boardid);
        return ResponseEntity.ok().body("");
    }
}