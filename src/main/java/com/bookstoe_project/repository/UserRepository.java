package com.bookstoe_project.repository;

import com.bookstoe_project.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findByEmail(String email);
}
