package com.hanselnpetal.service;

import com.hanselnpetal.data.repos.MemberRepository;
import com.hanselnpetal.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public List<Member> findAll(Long boardId) {
		return memberRepository.findByBoardId(boardId);
	}

	public Member invite(Member member) {
		// memberRepository.findByUserIdAndBoardId(member.getUserId(), member.getBoardId());
		return memberRepository.save(member);
	}

	public void accept(Long userid, Long boardid) {

	}

	public void reject(Long userid, Long boardid) {
		Optional<Member> member = memberRepository.findByUserIdAndBoardId(userid, boardid);
		if(member.isPresent()) {
			memberRepository.delete(member.get());
		}
	}
}
