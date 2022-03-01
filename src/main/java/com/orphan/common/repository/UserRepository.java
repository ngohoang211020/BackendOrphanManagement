package com.orphan.common.repository;

import com.orphan.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByIdentification(String identification);




}
