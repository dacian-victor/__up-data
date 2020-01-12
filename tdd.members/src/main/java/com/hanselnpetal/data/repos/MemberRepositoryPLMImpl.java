package com.hanselnpetal.data.repos;

import com.hanselnpetal.domain.dto.MemberVO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class MemberRepositoryPLMImpl {

    @PersistenceContext
    private EntityManager em;

    public List<MemberVO> findByBoardId(Long boardId) {
        // EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin();

        List<Object[]> results = em.createQuery("SELECT u.name, m.boardId FROM User u, Member m WHERE u.id = m.userId").getResultList();
        //for (Object[] result : results) {
            //log.info(result[0] + " " + result[1] + " - " + result[2]);
        //}
        //em.getTransaction().commit();
        //em.close();
        return results.stream()
                .map(result->{
                    return new MemberVO((String)result[0], (Long)result[1]);
                }).collect(Collectors.toList());
    }
}
