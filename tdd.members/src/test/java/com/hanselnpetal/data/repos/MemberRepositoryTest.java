package com.hanselnpetal.data.repos;

import com.hanselnpetal.domain.Member;
import com.hanselnpetal.domain.User;
import com.hanselnpetal.domain.dto.MemberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testFindByEmail() {

        // setup data scenario
        String userName = "TestUser";
        User aNewUser = new User(userName);
        entityManager.persist(aNewUser);
        Member aNewMember = new Member(aNewUser.getId(),4L);
        entityManager.persist(aNewMember);

        // Find an inserted record using repository class
        List<MemberVO> foundDTO = memberRepository.findByBoardId(aNewUser.getId());


        // Assertion
        assertThat(foundDTO.get(0).getBoardId(), is(equalTo(4L)));
        assertThat(foundDTO.get(0).getName(), is(equalTo(userName)));
    }

}