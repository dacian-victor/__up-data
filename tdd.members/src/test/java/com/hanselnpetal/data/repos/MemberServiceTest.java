package com.taskboard.application.service;

import com.taskboard.application.dto.MemberDTO;
import com.taskboard.application.exception.BoardNotFoundException;
import com.taskboard.application.exception.ResourceExistException;
import com.taskboard.application.util.DtoAssembler;
import com.taskboard.domain.entity.Member;
import com.taskboard.infrastructure.BoardRepository;
import com.taskboard.infrastructure.MemberRepository;
import com.taskboard.infrastructure.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DtoAssembler dtoAssembler;
    @Mock
    private ActivityService activityService;
    @InjectMocks
    private MemberService memberService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllByBoardId() {
    }

    @Test
    public void findAllByUserId() {
    }

    @Test
    public void invite() {
    }

    @Test(expected = ResourceExistException.class)
    public void invite_UserAndBoardEntryAlreadyExist_ShouldThrowException() {
        Member member = new Member(1L, 1L);
        when(memberRepository.findByUserIdAndBoardId(any(), any())).thenReturn(Optional.of(member));
        MemberDTO memberDto = new MemberDTO(1L, 1L);;
        memberService.invite(memberDto);
    }

    @Test
    public void accept() {
        Member member = new Member(1L, 1L);
        when(memberRepository.findByUserIdAndBoardId(any(), any())).thenReturn(Optional.of(member));
        when(memberRepository.save(member)).thenReturn(member);
        when(dtoAssembler.convertToDto(member)).thenReturn(new MemberDTO(1L, 1L));

        memberService.accept(1L, 1L);

        verify(memberRepository).save(argThat((Member arg) ->
                !arg.isWaitInPending()));
        verify(memberRepository).save(member);
        verify(dtoAssembler).convertToDto(member);
        verifyNoMoreInteractions(dtoAssembler);
    }

    @Test(expected = NoSuchElementException.class)
    public void accept_UserEntryNotFound_ShouldThrowException() {
        when(memberRepository.findByUserIdAndBoardId(any(), any())).thenReturn(Optional.empty());
        memberService.accept(1L, 1L);
    }

    @Test
    public void reject() {
        Member member = new Member(1L, 1L);
        when(memberRepository.findByUserIdAndBoardId(any(), any())).thenReturn(Optional.of(member));
        doNothing().when(memberRepository).delete(member);
        when(dtoAssembler.convertToDto(member)).thenReturn(new MemberDTO(1L, 1L));

        memberService.reject(1L, 1L);

        verify(memberRepository).delete(member);
        verify(dtoAssembler).convertToDto(member);
        verifyNoMoreInteractions(dtoAssembler);
    }

    @Test(expected = NoSuchElementException.class)
    public void reject_UserEntryNotFound_ShouldThrowException() {
        when(memberRepository.findByUserIdAndBoardId(any(), any())).thenReturn(Optional.empty());
        memberService.reject(1L, 1L);
    }
}