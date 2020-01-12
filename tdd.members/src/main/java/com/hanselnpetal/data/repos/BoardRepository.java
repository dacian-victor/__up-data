package com.hanselnpetal.data.repos;

import com.hanselnpetal.domain.Board;
import com.hanselnpetal.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BoardRepository extends CrudRepository<Board, Long> {

    public List<Board> findByIdIn(List<Long> ids);
}
