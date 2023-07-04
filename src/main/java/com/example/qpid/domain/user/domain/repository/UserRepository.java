package com.example.qpid.domain.user.domain.repository;

import com.example.qpid.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByAccountId(String accountId);
}
