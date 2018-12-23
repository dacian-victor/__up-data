package com.hanselnpetal.data.repos;

import com.hanselnpetal.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends CrudRepository<Member, Long> {

    public List<Member> findByBoardId(Long boardId);

    public List<Member> findByUserId(Long userId);

    public Optional<Member> findByUserIdAndBoardId(Long userId, Long boardId);

}
