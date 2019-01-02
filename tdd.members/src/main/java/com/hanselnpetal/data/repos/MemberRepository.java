package com.hanselnpetal.data.repos;

import com.hanselnpetal.domain.*;
import com.hanselnpetal.domain.dto.MemberVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends CrudRepository<Member, Long> {

    //@Query(value = "SELECT u.name, m.boardId FROM User u, Member m WHERE u.id = m.userId")
    @Query("SELECT " +
            "   new com.hanselnpetal.domain.dto.MemberVO(u.name, m.boardId) " +
            "FROM " +
            "   User u, Member m " +
            "WHERE " +
            "   m.userId = ?1 " +
            "AND " +
            "   u.id = m.userId")
    public List<MemberVO> findByBoardId(Long boardId);

//    @Query("SELECT " +
//            "    new com.path.to.SurveyAnswerStatistics(v.answer, COUNT(v)) " +
//            "FROM " +
//            "    Survey v " +
//            "GROUP BY " +
//            "    v.answer")
//    List<SurveyAnswerStatistics> findSurveyCount();
//        @Query("select v.answer as answer, count(v) as cnt " +
//                "from Survey v " +
//                "group by v.answer")
//        List<SurveyAnswerStatistics> findSurveyCount();

    public List<Member> findByUserId(Long userId);

    public Optional<Member> findByUserIdAndBoardId(Long userId, Long boardId);

}
