package com.hanselnpetal.data.repos;

import com.hanselnpetal.domain.Member;
import com.hanselnpetal.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

}
