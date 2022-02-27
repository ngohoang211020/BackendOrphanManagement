package com.orphan.common.repository;

import com.orphan.common.entity.Role;
import com.orphan.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(ERole name);
}
