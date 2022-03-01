package com.orphan.common.repository;

import com.orphan.common.entity.children.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children,Integer> {
}
